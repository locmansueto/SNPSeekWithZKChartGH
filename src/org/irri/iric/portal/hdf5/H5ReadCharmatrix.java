package org.irri.iric.portal.hdf5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.object.Dataset;
//import ncsa.hdf.object.Group;
//import ncsa.hdf.object.h5.H5File;



public class H5ReadCharmatrix {

	private static int DIM_POSITION = 1;
	private static int DIM_VARIETY = 0;
//	private static int MAX_VARIETY = 3000;
//	private static int MAX_POSITION = 20310628;
	
	
//	private String filename;
//	private Dataset dataset;
//	private H5File h5file;
	
 
   
   public  OutputMatrix read(H5Dataset hfdata, InputParamsIdxs input) throws Exception  {
		
	   Dataset dataset = hfdata.getDataset();
	   dataset.init();

       // start, stride and sizes will determined the selected subset
       long[] start = dataset.getStartDims();
       long[] stride = dataset.getStride();
       long[] sizes = dataset.getSelectedDims();

       // select the subset: set stride to (1, 1)
       stride[DIM_POSITION] = 1;
       stride[DIM_VARIETY] = 1;
       
       long n_dim_position=1;
       long n_dim_variety=1;

	    //dataset.setConvertByteToString(true);
 
       
	   Map listVarString = new LinkedHashMap();

	   Set setVarsIds = null;

	   n_dim_variety =  dataset.getMaxDims()[DIM_VARIETY];
       int rows = (int)n_dim_variety;
	       
		if(input.listVaridx==null) {
			start[DIM_VARIETY] = 0;
			sizes[DIM_VARIETY] = n_dim_variety;
			System.out.print("pri:getting " + rows + " rows x ");
			
		} else if (input.listVaridx!=null) {
		
			setVarsIds= new HashSet();
			for (int ivar=0; ivar<input.listVaridx.length; ivar++ )
				setVarsIds.add(input.listVaridx[ivar]);
			System.out.print("pri:getting " + setVarsIds.size() + " rows x ");
		} else throw new RuntimeException("No variety indexes");

		
		if(input.startPosidx !='\0' && input.endPosidx!='\0') {
	        // select the subset: starting at (4, 2)
	        start[DIM_POSITION] = input.startPosidx-1;
	        n_dim_position = input.endPosidx- input.startPosidx+ 1 ;
	        sizes[DIM_POSITION] = n_dim_position;
	        
		       int cols = (int)n_dim_position;
		       // read the data of the subset
		       byte[] dataRead = (byte[]) dataset.read();
		       
		       System.out.println(cols + " cols matrix");

		       // print out the data values
		       
		       for (int i = 0; i < rows ; i++) {
		    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
			       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+ cols ) );
			       	//AppContext.debug(s);
			       	listVarString.put( BigDecimal.valueOf(i+1) , s );
		       } 
	        
		} else if(input.listPosidx!=null) {

  	        Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();

		    System.out.println(input.listPosidx.length + " cols matrix");
		       
			for(int ipos=0; ipos<input.listPosidx.length; ipos++) {
		        start[DIM_POSITION] = input.listPosidx[ipos]-1;
		        n_dim_position = 1 ;
		        sizes[DIM_POSITION] = n_dim_position;

			       // cols = 1 ; //int cols = (int)n_dim_position;
			       // read the data of the subset
			       byte[] dataRead = (byte[]) dataset.read();
			       
			       // print out the data values
			       if(ipos==0) {
				       for (int i = 0; i < rows ; i++) {
				    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
					       	//AppContext.debug(s);
					       	StringBuffer buff=new StringBuffer();
					       	buff.append(new String( java.util.Arrays.copyOfRange(dataRead, i , i+1) ));
					       	mapVarStringBuff.put( BigDecimal.valueOf(i+1) , buff);
				       }
			       } else
			       {
				       for (int i = 0; i < rows ; i++) {
				    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
					       	mapVarStringBuff.get( BigDecimal.valueOf(i+1)).append( new String( java.util.Arrays.copyOfRange(dataRead, i, i+1) ) ) ;
				       }
			       }
			}
			Iterator<BigDecimal> itVar = mapVarStringBuff.keySet().iterator();
			while(itVar.hasNext())  {
				BigDecimal varid = itVar.next();
				listVarString.put(varid, mapVarStringBuff.get(varid).toString() );
			}
			
		} else throw new RuntimeException("No SNP position indexes");

       
       return new OutputMatrix(listVarString);
	}
   
   
   public  OutputMatrix read(H5Dataset hfdata, InputParams input) throws Exception  {
		
	   Dataset dataset = hfdata.getDataset();
	   dataset.init();

       // start, stride and sizes will determined the selected subset
       long[] start = dataset.getStartDims();
       long[] stride = dataset.getStride();
       long[] sizes = dataset.getSelectedDims();

       // select the subset: set stride to (1, 1)
       stride[DIM_POSITION] = 1;
       stride[DIM_VARIETY] = 1;
       
       long n_dim_position=1;
       long n_dim_variety=1;

	    //dataset.setConvertByteToString(true);
 
       
	   Map listVarString = new LinkedHashMap();

	   Set setVarsIds = null;

	   n_dim_variety =  dataset.getMaxDims()[DIM_VARIETY];
       int rows = (int)n_dim_variety;
	       
		if(input.listVaridx==null) {
			start[DIM_VARIETY] = 0;
			sizes[DIM_VARIETY] = n_dim_variety;
			System.out.print("obj:getting " + rows + " rows x ");
			
		} else if (input.listVaridx!=null) {
		
			setVarsIds= new HashSet();
			for (int ivar=0; ivar<input.listVaridx.size(); ivar++ )
				setVarsIds.add(input.listVaridx.get(ivar));
			System.out.print("obj:getting " + setVarsIds.size() + " rows x ");
		} else throw new RuntimeException("No variety indexes");

		
		if(input.startPosidx !=null  && input.endPosidx!= null) {
	        // select the subset: starting at (4, 2)
	        start[DIM_POSITION] = input.startPosidx.intValue()-1;
	        n_dim_position = input.endPosidx.intValue()- input.startPosidx.intValue()+ 1 ;
	        sizes[DIM_POSITION] = n_dim_position;
	        
		       int cols = (int)n_dim_position;
		       // read the data of the subset
		       byte[] dataRead = (byte[]) dataset.read();
		       
		       System.out.println(cols + " cols matrix");

		       // print out the data values
		       
		       for (int i = 0; i < rows ; i++) {
		    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
			       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+cols) );
			       	//AppContext.debug(s);
			       	listVarString.put( BigDecimal.valueOf(i+1) , s );
		       } 
	        
		} else if(input.listPosidx!=null) {

  	        Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();

		    System.out.println(input.listPosidx.size() + " cols matrix");
		       
			for(int ipos=0; ipos<input.listPosidx.size(); ipos++) {
		        start[DIM_POSITION] = input.listPosidx.get(ipos).intValue()-1;
		        n_dim_position = 1 ;
		        sizes[DIM_POSITION] = n_dim_position;

			       // cols = 1 ; //int cols = (int)n_dim_position;
			       // read the data of the subset
			       byte[] dataRead = (byte[]) dataset.read();
			       
			       // print out the data values
			       if(ipos==0) {
				       for (int i = 0; i < rows ; i++) {
				    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
					       	//AppContext.debug(s);
					       	StringBuffer buff=new StringBuffer();
					       	buff.append(new String( java.util.Arrays.copyOfRange(dataRead, i , i+1) ));
					       	mapVarStringBuff.put( BigDecimal.valueOf(i+1) , buff);
				       }
			       } else
			       {
				       for (int i = 0; i < rows ; i++) {
				    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
					       	mapVarStringBuff.get( BigDecimal.valueOf(i+1)).append( new String( java.util.Arrays.copyOfRange(dataRead, i, i+1) ) ) ;
				       }
			       }
			}
			Iterator<BigDecimal> itVar = mapVarStringBuff.keySet().iterator();
			while(itVar.hasNext())  {
				BigDecimal varid = itVar.next();
				listVarString.put(varid, mapVarStringBuff.get(varid).toString() );
			}
			
		} else throw new RuntimeException("No SNP position indexes");

       
       return new OutputMatrix(listVarString);
	}

   public   OutputMatrix readOld(H5Dataset hfdata, InputParams input) throws Exception {
	   	Dataset dataset = hfdata.getDataset();
	   	dataset.init();

        // start, stride and sizes will determined the selected subset
        long[] start = dataset.getStartDims();
        long[] stride = dataset.getStride();
        long[] sizes = dataset.getSelectedDims();

        // select the subset: set stride to (1, 1)
        stride[DIM_POSITION] = 1;
        stride[DIM_VARIETY] = 1;
        
        long n_dim_position=1;
        long n_dim_variety=1;

		if(input.startPosidx!=null && input.endPosidx!=null) {
	        // select the subset: starting at (4, 2)
	        start[DIM_POSITION] = input.startPosidx.intValue() ;
	        n_dim_position = input.endPosidx.intValue()- input.startPosidx.intValue() + 1 ;
	        sizes[DIM_POSITION] = n_dim_position;
		}
		
		
		if(input.listVaridx==null) {
			start[DIM_VARIETY] = 0;
			n_dim_variety =  dataset.getMaxDims()[DIM_VARIETY];
			sizes[DIM_VARIETY] = n_dim_variety;
		}
        
	    //dataset.setConvertByteToString(true);
        
		// read the data of the subset
        byte[] dataRead = (byte[]) dataset.read();

        // print out the data values
        int cols = (int)n_dim_position;
        int rows = (int)n_dim_variety;
        
        System.out.println("getting " + rows + " rows x " + cols + " cols matrix");
        
        Map listVarString = new LinkedHashMap();
        for (int i = 0; i < rows ; i++) {
 	       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+cols-1) );
 	       	//AppContext.debug(s);
 	       	listVarString.put( BigDecimal.valueOf(i+1) , s );
        } 
        
        
        return new OutputMatrix(listVarString);
	}

	public  List<OutputMatrix> read(H5Dataset hfdata, List inputs) {

		List outputs = new ArrayList();
		Iterator itParams = inputs.iterator();
		while(itParams.hasNext()) {
			try {
				Object param = itParams.next();
				if(param instanceof InputParams)
					outputs.add( read(hfdata, (InputParams)itParams.next()));
				else if (param instanceof InputParamsIdxs )
					outputs.add( read(hfdata, (InputParamsIdxs)itParams.next()));
				
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return outputs;
	}



}
