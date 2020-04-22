package org.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;

import me.vighnesh.api.virustotal.VirusTotalAPI;
import me.vighnesh.api.virustotal.dao.FileScan;
import me.vighnesh.api.virustotal.dao.FileScanMetaData;
import me.vighnesh.api.virustotal.dao.FileScanReport;
import me.vighnesh.api.virustotal.net.http.PublicAPIRequestRateLimitExceededException;

public class TestScanVirus {

	public void testScan() {

		try {
			File file = new File("C:\\Users\\lhbarboza\\Pictures\\backup-cpc.jpg");
			VirusTotalAPI virusTotal = VirusTotalAPI
					.configure("34686f8603548ba3ee358f6396331ac97839d2785d17f7a6c137a6390d7d35c5");
			FileScanMetaData scanFile = virusTotal.scanFile(file);

			System.out.println("---SCAN META DATA---");
			System.out.println("");
			System.out.println("MD5 : " + scanFile.getMD5());
			System.out.println("SH-1 : " + scanFile.getSHA1());
			System.out.println("SHA-256 : " + scanFile.getSHA256());
			System.out.println("Permalink : " + scanFile.getPermalink());
			System.out.println("Resource : " + scanFile.getResource());
			System.out.println("Scan Id : " + scanFile.getScanId());
			System.out.println("Response Code : " + scanFile.getResponseCode());
			System.out.println("Verbose Message : " + scanFile.getVerboseMessage());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getResults() {
//		MD5 : b05f12ae7ddf3c5277f6d325f023a34b
//		SH-1 : 3bc4188b7bc32951719e435964a5c66bbe030ffa
//		SHA-256 : 4ae01dc5629acdb3e86f3085bafb3949c2bd62bc0c5c6a8523cea35c1e060417
//		Permalink : https://www.virustotal.com/file/4ae01dc5629acdb3e86f3085bafb3949c2bd62bc0c5c6a8523cea35c1e060417/analysis/1585725790/
//		Resource : 4ae01dc5629acdb3e86f3085bafb3949c2bd62bc0c5c6a8523cea35c1e060417
//		Scan Id : 4ae01dc5629acdb3e86f3085bafb3949c2bd62bc0c5c6a8523cea35c1e060417-1585725790

		String fileId = "2e30e3345969cb66dd0c268f0414dc29";
		VirusTotalAPI virusTotal = VirusTotalAPI
				.configure("34686f8603548ba3ee358f6396331ac97839d2785d17f7a6c137a6390d7d35c5");
		FileScanReport fileReport = virusTotal.getFileReport(fileId);
		Map scans = fileReport.getScans();
		scans.keySet().stream().forEach((scan) -> {
			FileScan report = (FileScan) scans.get(scan);
			System.out.println("Scan Engine : " + scan);
			if (report.isDetected()) {
				System.out.println("Version : " + report.getVersion());
				System.out.println("Update : " + report.getUpdate());
				System.out.println("Malware : " + report.getMalware());
			} else {
				System.out.println("No Virus Detected");
			}
		});
	}

	
	public void getScanAndGetResults() {
		try {
			File file = new File("C:\\Users\\lhbarboza\\Pictures\\backup-cpc.jpg");
			VirusTotalAPI virusTotal = VirusTotalAPI
					.configure("34686f8603548ba3ee358f6396331ac97839d2785d17f7a6c137a6390d7d35c5");
			FileScanMetaData scanFile = virusTotal.scanFile(file);

//			System.out.println("---SCAN META DATA---");
//			System.out.println("");
//			System.out.println("MD5 : " + scanFile.getMD5());
//			System.out.println("SH-1 : " + scanFile.getSHA1());
//			System.out.println("SHA-256 : " + scanFile.getSHA256());
//			System.out.println("Permalink : " + scanFile.getPermalink());
//			System.out.println("Resource : " + scanFile.getResource());
//			System.out.println("Scan Id : " + scanFile.getScanId());
//			System.out.println("Response Code : " + scanFile.getResponseCode());
//			System.out.println("Verbose Message : " + scanFile.getVerboseMessage());

			FileScanReport fileReport = virusTotal.getFileReport(scanFile.getMD5());
			Map scans = fileReport.getScans();
			scans.keySet().stream().forEach((scan) -> {
				FileScan report = (FileScan) scans.get(scan);
				System.out.println("Scan Engine : " + scan);
				if (report.isDetected()) {
					System.out.println("Version : " + report.getVersion());
					System.out.println("Update : " + report.getUpdate());
					System.out.println("Malware : " + report.getMalware());
				} else {
					System.out.println("No Virus Detected");
				}
			});
		
			
			Hlayout h1 = new Hlayout();
			Label test = new Label(file.getAbsolutePath());
			test.setVisible(false);
			h1.appendChild(test);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PublicAPIRequestRateLimitExceededException e) {
			System.out.println("Attachment failed. Please try again later if you like to include image attachments");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testFormat() {
		
	}

}
