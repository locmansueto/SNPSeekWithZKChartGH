package org.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.irri.iric.portal.AppContext;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vlayout;

import me.vighnesh.api.virustotal.VirusTotalAPI;
import me.vighnesh.api.virustotal.dao.FileScan;
import me.vighnesh.api.virustotal.dao.FileScanMetaData;
import me.vighnesh.api.virustotal.dao.FileScanReport;
import me.vighnesh.api.virustotal.net.http.PublicAPIRequestRateLimitExceededException;

public class ImageAttachmentThread implements Runnable {

	private Vlayout flist;
	private Media media;

	public ImageAttachmentThread(Vlayout flist, Media media) {
		this.flist = flist;
		this.media = media;
	}

	@Override
	public void run() {

		boolean image = true;

		if (media instanceof org.zkoss.image.Image) {

			try {
				String path = AppContext.getFlatfilesDir();

				File file = new File(path + media.getName());
				file.createNewFile();
				System.out.println(path + media.getName());

				FileOutputStream outputStream = new FileOutputStream(file);
				outputStream.write(media.getByteData());
				outputStream.close();

				// SCAN FILE

				VirusTotalAPI virusTotal = VirusTotalAPI
						.configure("34686f8603548ba3ee358f6396331ac97839d2785d17f7a6c137a6390d7d35c5");
				FileScanMetaData scanFile = virusTotal.scanFile(file);

				// System.out.println(scanFile.getMD5());
				// System.out.println("Getting Response Code: " + scanFile.getResponseCode());
				FileScanReport fileReport = null;

				boolean resultRetrieve = false;
				Map<String, FileScan> scans = null;

				int tries = 0;
				while (!resultRetrieve && tries < 10) {
					fileReport = virusTotal.getFileReport(scanFile.getMD5());
					System.out.println("Query for result");
					try {
						scans = fileReport.getScans();
						resultRetrieve = true;
					} catch (NullPointerException e) {
						System.out.println("waiting.. try.." + tries);
						try {
							TimeUnit.SECONDS.sleep(30);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						tries++;
					}
				}

				System.out.println("quit querying Loop");

				if (resultRetrieve) {
					System.out.println("retrieved");
					boolean isClean = true;

					for (String scan : scans.keySet()) {
						FileScan report = scans.get(scan);
						if (report.isDetected()) {
							org.zkoss.zul.Messagebox.show("Attachment found to be malicious");
							isClean = false;
						}

					}

					if (isClean) {

						Hlayout hl = new Hlayout();
						hl.setSpacing("6px");
						hl.setClass("newFile");

						// hl.appendChild(new Label(file.getAbsolutePath()).setVisible(false));
						Label aPath = new Label(file.getAbsolutePath());
						aPath.setVisible(false);
						hl.appendChild(aPath);
						hl.appendChild(new Label(media.getName()));
						// hl.appendChild(new Progressmeter(50));
						A rm = new A("remove item");
						rm.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event event) throws Exception {
								hl.detach();
							}
						});
						hl.appendChild(rm);
						flist.appendChild(hl);

					}
				} else {
					org.zkoss.zul.Messagebox.show(
							"Scanning for virus/malware is taking a long time. Pls Try attaching the image again later.");
				}

			} catch (FileNotFoundException e) {
				org.zkoss.zul.Messagebox.show("Cannot find your image attachment.");

			} catch (PublicAPIRequestRateLimitExceededException e) {
				org.zkoss.zul.Messagebox.show(
						"Attachment failed. Limit has been reached. Please try again later if you like to include image attachments");
			} catch (IOException e) {

			}

			// END SCAN

		} else {
			org.zkoss.zul.Messagebox.show("Only Accepts image file.");
		}

	}

}
