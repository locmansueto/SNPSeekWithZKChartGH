package org.test;

import org.junit.Test;

public class TestDLL {

	@Test
	public void testTimer() {
		java.util.concurrent.ExecutorService es = java.util.concurrent.Executors.newSingleThreadExecutor();
		java.util.concurrent.Future<String> result = es.submit(new java.util.concurrent.Callable<String>() {
			public String call() throws Exception {

				String hl = "test";

				// try {
				// boolean image = true;
				//
				// // SCAN FILE
				//
				// try {
				//
				// VirusTotalAPI virusTotal = VirusTotalAPI.configure(
				// "34686f8603548ba3ee358f6396331ac97839d2785d17f7a6c137a6390d7d35c5");
				// FileScanMetaData scanFile = virusTotal.scanFile(file);
				//
				// FileScanReport fileReport = null;
				//
				// boolean resultRetrieve = false;
				// Map scans = null;
				//
				// int tries = 0;
				//
				// while (!resultRetrieve && tries < 10) {
				// fileReport = virusTotal.getFileReport(scanFile.getMD5());
				// try {
				// scans = fileReport.getScans();
				// resultRetrieve = true;
				// } catch (NullPointerException e) {
				// System.out.println(
				// "Image ID: " + imgId + " is still being scan.. " + tries);
				// TimeUnit.SECONDS.sleep(30);
				// tries++;
				// }
				// }
				//
				// if (resultRetrieve) {
				// System.out.println("Image retrieved with id: " + imgId);
				// boolean isClean = true;
				//
				// for (String scan : scans.keySet()) {
				// FileScan report = scans.get(scan);
				// if (report.isDetected()) {
				// org.zkoss.zul.Messagebox
				// .show("Attachment found to be malicious");
				// isClean = false;
				// }
				//
				// }
				//
				// if (isClean) {
				//
				// System.out.println("Rendering....");
				//
				// hl.setSpacing("6px");
				// hl.setClass("newFile");
				//
				// //hl.appendChild(new Label(file.getAbsolutePath()).setVisible(false));
				// Label aPath = new Label(file.getAbsolutePath());
				// aPath.setVisible(false);
				// hl.appendChild(aPath);
				// hl.appendChild(new Label(media.getName()));
				// //hl.appendChild(new Progressmeter(50));
				// A rm = new A("remove item");
				// rm.addEventListener(Events.ON_CLICK,
				// new org.zkoss.zk.ui.event.EventListener() {
				// public void onEvent(Event event) throws Exception {
				// hl.detach();
				// }
				// });
				// hl.appendChild(rm);
				//
				// }
				// } else {
				// org.zkoss.zul.Messagebox.show(
				// "Scanning for virus/malware is taking a long time. Pls Try attaching the
				// image again later.");
				// }
				//
				// } catch (FileNotFoundException e) {
				// org.zkoss.zul.Messagebox.show("Cannot find your image attachment.");
				//
				// } catch (PublicAPIRequestRateLimitExceededException e) {
				// org.zkoss.zul.Messagebox.show(
				// "Attachment failed. Limit has been reached. Please try again later if you
				// like to include image attachments");
				// } catch (IOException e) {
				//
				// }
				//
				// // END SCAN
				//
				// } catch (Exception e) {
				// }
				//
				// // Hlayout hl = new Hlayout();
				// // hl.setSpacing("6px");
				// // hl.setClass("newFile");
				// // hl.setAttribute("id", imgId);
				// //
				// // numImages++;
				// //
				// //
				// // Label aPath = new Label(file.getAbsolutePath());
				// // aPath.setVisible(false);
				// // hl.appendChild(aPath);
				// // hl.appendChild(new Label(media.getName()));
				// // A rm = new A("scanning..");
				// // // rm.addEventListener(Events.ON_CLICK, new
				// org.zkoss.zk.ui.event.EventListener() {
				// // // public void onEvent(Event event) throws Exception {
				// // // hl.detach();
				// // // }
				// // // });
				//
				return hl;
			}
		});

		try {
			System.out.println(result.get());
		} catch (Exception e) {
			// failed
		}
		es.shutdown();
	}

}
