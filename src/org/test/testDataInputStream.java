package org.test;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.irri.iric.portal.AppContext;

public class testDataInputStream {

	public static void main(String args[]) {

		FileInputStream fis;
		try {
			fis = new FileInputStream("E:/PersonalFiles/iric/iric_portal_files/gwas/emmax_2.dat");
			DataInputStream dis = new DataInputStream(fis);

			String fileName = "outputManhattan";

			String dir = "E:/";
			File file = new File(dir + fileName + ".txt");

			AppContext.debug(file.getAbsolutePath());

			int it = 1;
			boolean createFile = true;
			while (createFile) {
				if (!file.exists()) {
					file.createNewFile();
					createFile = false;
				} else {
					String Fname = fileName + "" + it;
					file = new File(dir + Fname + ".txt");
					it++;
				}

			}

			BufferedWriter output = new BufferedWriter(new FileWriter(file));

			output.write("TYPE\tCHR\tPOS\tPVAL");
			while (dis.available() > 0) {

				Integer type = dis.readInt();
				Byte chr = dis.readByte();
				Integer pos = dis.readInt();
				Float pval = dis.readFloat();
				output.newLine();
				output.write(type + "\t" + chr + "\t" + pos + "\t" + pval);

			}
			output.close();
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
