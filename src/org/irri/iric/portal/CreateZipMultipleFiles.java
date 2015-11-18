package org.irri.iric.portal;

/*
Create Zip File From Multiple Files using ZipOutputStream Example
This Java example shows how create zip file containing multiple files
using Java ZipOutputStream class.
*/

import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;

import java.util.zip.ZipOutputStream;

public class CreateZipMultipleFiles {

	private String zipFile; 
	private String[] sourceFiles;

	
	
	public CreateZipMultipleFiles(String zipFile, String[] sourceFiles) {
		super();
		this.zipFile = zipFile;
		this.sourceFiles = sourceFiles;
	}
	
	public CreateZipMultipleFiles(String zipFile, List listFiles) {
		super();
		sourceFiles = new String[listFiles.size()];
		this.zipFile = zipFile;
		
		Iterator<String> itFilename = listFiles.iterator();
		int icount=0;
		while(itFilename.hasNext()) {
			sourceFiles[icount] = itFilename.next();
			icount++;
		}
	}


	public void create() {
		create(false);
	}
	
	public void create(boolean includePath)
	{
		try
        {
                //String zipFile = "C:/FileIO/zipdemo.zip";
                //String[] sourceFiles = {"C:/sourcefile1.doc", "C:/sourcefile2.doc"};
               
                //create byte buffer
                byte[] buffer = new byte[1024];
               
                /*
                 * To create a zip file, use
                 *
                 * ZipOutputStream(OutputStream out)
                 * constructor of ZipOutputStream class.
                */
                 
                 //create object of FileOutputStream
                 FileOutputStream fout = new FileOutputStream(zipFile);
                 
                 //create object of ZipOutputStream from FileOutputStream
                 ZipOutputStream zout = new ZipOutputStream(fout);
                 
                 for(int i=0; i < sourceFiles.length; i++)
                 {
                       
                        AppContext.debug("Adding " + sourceFiles[i]);
                        //create object of FileInputStream for source file
                        FileInputStream fin = new FileInputStream(sourceFiles[i]);

                        /*
                         * To begin writing ZipEntry in the zip file, use
                         *
                         * void putNextEntry(ZipEntry entry)
                         * method of ZipOutputStream class.
                         *
                         * This method begins writing a new Zip entry to
                         * the zip file and positions the stream to the start
                         * of the entry data.
                         */

                        if(includePath) {
                        	zout.putNextEntry(new ZipEntry(sourceFiles[i]));
                        }
                        else { 
                        	File addfile=new File(sourceFiles[i]);
                        	zout.putNextEntry(new ZipEntry(addfile.getName()));
                        }
                        
                        
                        //zout.putNextEntry(new ZipEntry(sourceFiles[i]));

                        /*
                         * After creating entry in the zip file, actually
                         * write the file.
                         */
                        int length;

                        while((length = fin.read(buffer)) > 0)
                        {
                           zout.write(buffer, 0, length);
                        }

                        /*
                         * After writing the file to ZipOutputStream, use
                         *
                         * void closeEntry() method of ZipOutputStream class to
                         * close the current entry and position the stream to
                         * write the next entry.
                         */

                         zout.closeEntry();

                         //close the InputStream
                         fin.close();
                       
                 }
               
                 
                  //close the ZipOutputStream
                  zout.close();
                 
                  AppContext.debug("Zip file has been created!");
       
        }
        catch(IOException ioe)
        {
        	AppContext.debug("IOException :" + ioe);
        }
       
}
}