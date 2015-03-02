package org.irri.iric.portal.hdf5;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.SnpsStringDAO;

import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.object.Dataset;
import ncsa.hdf.object.Group;
import ncsa.hdf.object.h5.*;

//import org.irri.iric.portal.AppContext;


public abstract class H5Dataset implements SnpsStringDAO {
	
	private String filename;
	private Dataset dataset;
	private H5File h5file;
	private H5ReadCharmatrix matrixReader;
	
	/*
	static {
		AppContext.debug("java.library.path=" + System.getProperty("java.library.path"));
		
	    try {
	    	
	    	
	    	AppContext.debug("Loading Native code library jhdf5.dll");
	    	
	    	if(AppContext.isWindows()) {
	    		System.setProperty("java.library.path", System.getProperty("java.library.path")+";E:\\HDF_Group\\HDFView\\2.10.1\\lib"  );
	    		AppContext.debug("java.library.path=" + System.getProperty("java.library.path"));
	    		System.load(  "E:\\HDF_Group\\HDFView\\2.10.1\\lib\\jhdf5.dll");
	    	}
	    	else
	    		System.load(  AppContext.getFlatfilesDir() + "lib/jhdf5.lib");
	    	
	    	AppContext.debug("loading jhdf5.dll ... success");
	    	
	    } catch (UnsatisfiedLinkError e) {
	      System.err.println("Native code library failed to load.\n" + e);
	      System.err.println(e.getMessage());
	      e.printStackTrace();
	    } catch (Exception e) {
		      System.err.println(e.getMessage());
		      e.printStackTrace();
	    }
	    
	  };
	  */
	  
	public H5Dataset(String filename) {
		super();
		
		
		this.filename = filename;
		matrixReader = new H5ReadCharmatrix();
	}
/*
	public Dataset getDataset4() throws Exception {
		if(dataset==null) { 
				
			FileFormat fileFormat = FileFormat.getFileFormat(FileFormat.FILE_TYPE_HDF5);
		
		     if (fileFormat == null) {
		         System.err.println("Cannot find HDF5 FileFormat.");
		         return null;
		     }
		
		     // open the file with read and write access
		     FileFormat testFile = fileFormat.createInstance(filename, FileFormat.READ);
		
		     if (testFile == null) {
		         System.err.println("Failed to open file: " + filename);
		         return null;
		     }
		
		     // open the file and retrieve the file structure
		     testFile.open();
		     Group root = (Group) ((javax.swing.tree.DefaultMutableTreeNode) testFile.getRootNode()).getUserObject();
		     dataset = (Dataset) root.getMemberList().get(0);
		     long[] maxdims = dataset.getMaxDims();
		        if(maxdims!=null) {
			        StringBuffer buff = new StringBuffer();
			        for(int idim=0; idim< maxdims.length; idim++ ) {
			        	buff.append("hdf5 file " + filename + " dim[" + idim + "]=" + maxdims[idim] + ",");
			        }
			        AppContext.debug( buff.toString());
		        }
		}
		return dataset;
	}
     */
	
	public Dataset getDataset() throws Exception {
		if(dataset==null) { 
				
			//System.out.println( "HDF5Constants.H5F_ACC_RDONLY="+  HDF5Constants.H5F_ACC_RDONLY ); //  HDF5Constants.H5F_ACC_RDONLY );
			
			h5file = new H5File( filename, H5File.READ ); //HDF5Constants.H5F_ACC_RDONLY );
			//h5file = new H5File( filename); //, HDF5Constants.H5F_ACC_RDONLY);
			//h5file = new H5File( filename, H5File.READ ); //, HDF5Constants.H5F_ACC_RDONLY);
	
			// open file and retrieve the file structure
	        h5file.open();
	        Group  root =  (Group) ((javax.swing.tree.DefaultMutableTreeNode) h5file.getRootNode()).getUserObject();
	        dataset = (Dataset) root.getMemberList().get(0);
	        
	        long[] maxdims = dataset.getMaxDims();
	        if(maxdims!=null) {
		        StringBuffer buff = new StringBuffer();
		        for(int idim=0; idim< maxdims.length; idim++ ) {
		        	buff.append("hdf5 file " + filename + " dim[" + idim + "]=" + maxdims[idim] + ",");
		        }
		        System.out.println(buff.toString());
	        }
		}
		return dataset;
	}
	
	
	public void close() {
	       try { 
	    	    h5file.close(); 
	    	    System.out.println("hdf5 file " + filename + " closed.");
		   }
		   catch (Exception ex ) {}
	}
	
	@Override
	public void finalize() {
	   close();
	}
	
	

	@Override
	public Map readSNPString(int chr,  int startIdx,  int endIdx) {
		try {
		return matrixReader.read( this , new InputParamsIdxs(startIdx,endIdx)).getMapVar2String() ;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return  null;
	}
	
	@Override
	public Map readSNPString(int chr,  int posIdxs[])  {
		try {
		return matrixReader.read( this , new InputParamsIdxs(posIdxs)).getMapVar2String() ;
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return  null;
	}
	
	@Override
	public Map readSNPString(Set<BigDecimal> colVarids, int chr,  int posIdxs[]) {
		try {
		// order varids based on file ordering for 1pass/smooth disk read
		Set orderedVarids = new TreeSet(colVarids);
		Iterator<BigDecimal> itVarid = orderedVarids.iterator();
		
		int varids[] = new int[orderedVarids.size()];
		int icount = 0;
		while(itVarid.hasNext()) {
			varids[icount]=itVarid.next().intValue();
			icount++;
		}
		return matrixReader.read( this , new InputParamsIdxs(posIdxs, varids)).getMapVar2String() ;
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return  null;
	}

	@Override
	public Map readSNPString(Set<BigDecimal> colVarids, int chr,  int startIdx, int endIdx)  {
		try {
		Set orderedVarids = new TreeSet(colVarids);
		Iterator<BigDecimal> itVarid = orderedVarids.iterator();
		
		int varids[] = new int[orderedVarids.size()];
		int icount = 0;
		while(itVarid.hasNext()) {
			
			varids[icount]=itVarid.next().intValue();
			icount++;
		}
		return matrixReader.read( this , new InputParamsIdxs(startIdx, endIdx, varids)).getMapVar2String() ;
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return  null;
	}
	
}
