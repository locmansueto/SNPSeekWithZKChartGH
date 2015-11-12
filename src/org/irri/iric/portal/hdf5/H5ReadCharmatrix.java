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



import org.irri.iric.portal.AppContext;


//import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.object.Dataset;
//import ncsa.hdf.object.Group;
//import ncsa.hdf.object.h5.H5File;



public class H5ReadCharmatrix implements H5ReadMatrix {

	private static int DIM_POSITION = 1;
	private static int DIM_VARIETY = 0;

 
	//@Override
   public  OutputMatrix readAllrows(H5Dataset hfdata, InputParamsIdxs input) throws Exception  {

	   Dataset dataset = hfdata.getDataset();
	   
	   dataset.init();
	   
	   AppContext.debug("reading char hdf5 " + dataset.getFile()) ;

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

		AppContext.debug("input.startPosidx=" + input.startPosidx + " ;input.endPosidx=" + input.endPosidx  + " ;input.listStartEndPosidx=" + input.listStartEndPosidx + 
				" ;input.listPosidx=" + input.listPosidx);
		
		if(input.startPosidx >-1 && input.endPosidx>-1) {
	        // select the subset: starting at (4, 2)
	        //start[DIM_POSITION] = input.startPosidx-1;
			start[DIM_POSITION] = input.startPosidx;
	        n_dim_position = input.endPosidx- input.startPosidx+ 1 ;
	        sizes[DIM_POSITION] = n_dim_position;
	        
		       int cols = (int)n_dim_position;
		       // read the data of the subset
		       byte[] dataRead = (byte[]) dataset.read();
		       
		       AppContext.debug(cols + " cols matrix");

		       // print out the data values
		       
		       for (int i = 0; i < rows ; i++) {
		    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
			       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+ cols ) );
			       	//AppContext.debug(s);
			       	listVarString.put( BigDecimal.valueOf(i+1) , s );
		       } 
	        
		} else if(input.listStartEndPosidx!=null) {
			
			Map<BigDecimal,StringBuffer> mapVarid2Strbuff=new LinkedHashMap();
			
			for(int iposrange=0; iposrange<input.listStartEndPosidx.length; iposrange++) {
				
		        //start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0] -1;
				start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0];
		        n_dim_position = input.listStartEndPosidx[iposrange][1] - input.listStartEndPosidx[iposrange][0] + 1 ;
		        sizes[DIM_POSITION] = n_dim_position;
		        
			       int cols = (int)n_dim_position;
			       // read the data of the subset
			       byte[] dataRead = (byte[]) dataset.read();
			       
			       AppContext.debug( "locus " + iposrange + ", " + cols + " cols matrix");

			       // print out the data values
			       
			       for (int i = 0; i < rows ; i++) {
			    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
				       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+ cols ) );
				       	//AppContext.debug(s);
				       	StringBuffer buff = mapVarid2Strbuff.get(BigDecimal.valueOf(i+1));
				       	//listVarString.put( BigDecimal.valueOf(i+1) , s );
				       	if(buff==null) {
				       		buff = new StringBuffer();
				       		mapVarid2Strbuff.put( BigDecimal.valueOf(i+1) , buff);
				       	}
				       	buff.append(s);
			       } 
			}
			for(int i=0; i<rows; i++) {
				listVarString.put( BigDecimal.valueOf(i+1) , mapVarid2Strbuff.get(BigDecimal.valueOf(i+1)).toString());
			}
			
			AppContext.debug(mapVarid2Strbuff.get(BigDecimal.valueOf(1)).length() + " cols matrix");
			
		}
		else if(input.listPosidx!=null) {

  	        Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();

		    AppContext.debug(input.listPosidx.length + " cols matrix");
		       
			for(int ipos=0; ipos<input.listPosidx.length; ipos++) {
		        //start[DIM_POSITION] = input.listPosidx[ipos]-1;
				start[DIM_POSITION] = input.listPosidx[ipos];
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
  

   @Override
	public  List<OutputMatrix> read(H5Dataset hfdata, List inputs) {

		List outputs = new ArrayList();
		Iterator itParams = inputs.iterator();
		while(itParams.hasNext()) {
			try {
				Object param = itParams.next();
				//if(param instanceof InputParams)
				//	outputs.add( read(hfdata, (InputParams)itParams.next()));
				//else if (param instanceof InputParamsIdxs )
					outputs.add( read(hfdata, (InputParamsIdxs)itParams.next()));
				
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return outputs;
	}



	@Override
  public  OutputMatrix read(H5Dataset hfdata, InputParamsIdxs input) throws Exception  {

	   Dataset dataset = hfdata.getDataset();
	   
	   dataset.init();
	   
	   AppContext.debug("reading char hdf5 " + dataset.getFile()) ;

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
      
      AppContext.debug( "varieties=" +  n_dim_variety + ", positions=" + dataset.getMaxDims()[DIM_POSITION]);
      
      start[DIM_VARIETY] = 0;
      sizes[DIM_VARIETY] = n_dim_variety;

	       
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

		AppContext.debug("input.startPosidx=" + input.startPosidx + " ;input.endPosidx=" + input.endPosidx  + " ;input.listStartEndPosidx=" + input.listStartEndPosidx + 
				" ;input.listPosidx=" + input.listPosidx);
		
		if(input.startPosidx >-1 && input.endPosidx>-1) {
	        // select the subset: starting at (4, 2)
	        //start[DIM_POSITION] = input.startPosidx-1;
			start[DIM_POSITION] = input.startPosidx;
	        n_dim_position = input.endPosidx- input.startPosidx+ 1 ;
			//n_dim_position = input.endPosidx- input.startPosidx ;
	        sizes[DIM_POSITION] = n_dim_position;
	        
	        
	        int start_var=0; 
	        
	        if(setVarsIds==null || setVarsIds.size()==1) {
	        
		        if(setVarsIds!=null && setVarsIds.size()==1) {
		        	start_var=input.listVaridx[0]-1;
		        	start[DIM_VARIETY] = input.listVaridx[0]-1;
					sizes[DIM_VARIETY] = 1;
					rows=1;
		        }
		       int cols = (int)n_dim_position;
		       // read the data of the subset
		       byte[] dataRead = null;
		       try {
		       dataRead = (byte[]) dataset.read();
		       } catch (Exception ex) {
		    	   ex.printStackTrace();
		       }
		       if(dataRead==null)
		    	   throw new RuntimeException("dataRead=null");
		       for (int i = 0; i < rows ; i++) {
		    	   if(setVarsIds!=null && !setVarsIds.contains(i+1+start_var)) continue;
			       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+ cols ) );
			       	//AppContext.debug(s);
			       	listVarString.put( BigDecimal.valueOf(i+1+start_var) , s );
		       }
	        }
	        else if(setVarsIds.size()>1){ 
	        	
	        	for(int j=0; j<input.listVaridx.length; j++) {
	        	
		        	start_var=input.listVaridx[j]-1;
		        	start[DIM_VARIETY] = input.listVaridx[j]-1;
					sizes[DIM_VARIETY] = 1;

					int cols = (int)n_dim_position;
			       // read the data of the subset
			       byte[] dataRead = null;
			       try {
			       dataRead = (byte[]) dataset.read();
			       } catch (Exception ex) {
			    	   ex.printStackTrace();
			       }
			       if(dataRead==null)
			    	   throw new RuntimeException("dataRead=null");

			       if(setVarsIds!=null && !setVarsIds.contains(1+start_var)) {}
			       else {
				       	String s = new String( java.util.Arrays.copyOfRange(dataRead, 0, cols ) );
				       	//AppContext.debug(s);
				       	listVarString.put( BigDecimal.valueOf(1+start_var) , s );
			       }
	        	}
	        }
		       
		       
	        
		} else if(input.listStartEndPosidx!=null) {
			
			Map<BigDecimal,StringBuffer> mapVarid2Strbuff=new LinkedHashMap();
			
			for(int iposrange=0; iposrange<input.listStartEndPosidx.length; iposrange++) {
				
		        //start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0] -1;
				start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0];
		        n_dim_position = input.listStartEndPosidx[iposrange][1] - input.listStartEndPosidx[iposrange][0] + 1 ;
		        sizes[DIM_POSITION] = n_dim_position;
		        
			       int cols = (int)n_dim_position;
			       
			       
			       int start_var=0; 
			        
			        if(setVarsIds==null || setVarsIds.size()==1) {
			        
				        if(setVarsIds!=null && setVarsIds.size()==1) {
				        	start_var=input.listVaridx[0]-1;
				        	start[DIM_VARIETY] = input.listVaridx[0]-1;
							sizes[DIM_VARIETY] = 1;
							rows=1;
				        }
			       
				       // read the data of the subset
				       byte[] dataRead = (byte[]) dataset.read();
				       
				       AppContext.debug( "locus " + iposrange + ", " + cols + " cols matrix");
				       if(dataRead==null)
				    	   throw new RuntimeException("dataRead=null");
	
				       
				       // print out the data values
				       for (int i = 0; i < rows ; i++) {
				    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
					       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+ cols ) );
					       	//AppContext.debug(s);
					       	StringBuffer buff = mapVarid2Strbuff.get(BigDecimal.valueOf(i+1));
					       	//listVarString.put( BigDecimal.valueOf(i+1) , s );
					       	if(buff==null) {
					       		buff = new StringBuffer();
					       		mapVarid2Strbuff.put( BigDecimal.valueOf(i+1) , buff);
					       	}
					       	buff.append(s);
				       } 
			        } else if(setVarsIds.size()>1){ 
				        	
				        	for(int j=0; j<input.listVaridx.length; j++) {
					        	start_var=input.listVaridx[j]-1;
					        	start[DIM_VARIETY] = input.listVaridx[j]-1;
								sizes[DIM_VARIETY] = 1;
								
								
							   cols = (int)n_dim_position;
						       // read the data of the subset
						       byte[] dataRead = null;
						       try {
						       dataRead = (byte[]) dataset.read();
						       } catch (Exception ex) {
						    	   ex.printStackTrace();
						       }
						       if(dataRead==null)
						    	   throw new RuntimeException("dataRead=null");
	
						       if(setVarsIds!=null && !setVarsIds.contains(1+start_var)) {}
						       else {
							       	String s = new String( java.util.Arrays.copyOfRange(dataRead, 0, cols ) );
							       	//AppContext.debug(s);
							       	StringBuffer buff = mapVarid2Strbuff.get(BigDecimal.valueOf(start_var+1));
							       	if(buff==null) {
							       		buff = new StringBuffer();
							       		mapVarid2Strbuff.put( BigDecimal.valueOf(start_var+1) , buff);
							       	}
							       	buff.append(s);
							       	//listVarString.put( BigDecimal.valueOf(1+start_var) , s );
						       }
				        	}
			        }
			        

			}
		
	        Iterator<BigDecimal> itVar = mapVarid2Strbuff.keySet().iterator();
	        while(itVar.hasNext()) {
	        	BigDecimal varid=itVar.next();
	        	listVarString.put( varid , mapVarid2Strbuff.get(varid).toString());
	        }
	        
	        /*
	    	for(int i=0; i<rows; i++) {
				listVarString.put( BigDecimal.valueOf(i+1) , mapVarid2Strbuff.get(BigDecimal.valueOf(i+1)).toString());
			}
			*/
			
			//AppContext.debug(mapVarid2Strbuff.get(BigDecimal.valueOf(1)).length() + " cols matrix");
			
		}
		else if(input.listPosidx!=null) {

 	        Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();

		    AppContext.debug(input.listPosidx.length + " cols matrix");
		       
			for(int ipos=0; ipos<input.listPosidx.length; ipos++) {
		        //start[DIM_POSITION] = input.listPosidx[ipos]-1;
				start[DIM_POSITION] = input.listPosidx[ipos];
		        n_dim_position = 1 ;
		        sizes[DIM_POSITION] = n_dim_position;
		        int start_var=0; 
		        int cols = (int)n_dim_position;
		        
		        if(setVarsIds==null || setVarsIds.size()==1) {
			        
			        if(setVarsIds!=null && setVarsIds.size()==1) {
			        	start_var=input.listVaridx[0]-1;
			        	start[DIM_VARIETY] = input.listVaridx[0]-1;
						sizes[DIM_VARIETY] = 1;
						rows=1;
			        }
		        
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
		        }  else if(setVarsIds.size()>1){ 
		        	
		        	for(int j=0; j<input.listVaridx.length; j++) {
			        	start_var=input.listVaridx[j]-1;
			        	start[DIM_VARIETY] = input.listVaridx[j]-1;
						sizes[DIM_VARIETY] = 1;
						
						 	cols = (int)n_dim_position;
					       // read the data of the subset
					       byte[] dataRead = null;
					       try {
					       dataRead = (byte[]) dataset.read();
					       } catch (Exception ex) {
					    	   ex.printStackTrace();
					       }
					       if(dataRead==null)
					    	   throw new RuntimeException("dataRead=null");

					       // print out the data values
//					       if(ipos==0) {
//						       for (int i = 0; i < rows ; i++) {
//						    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
//							       	//AppContext.debug(s);
//							       	StringBuffer buff=new StringBuffer();
//							       	buff.append(new String( java.util.Arrays.copyOfRange(dataRead, i , i+1) ));
//							       	mapVarStringBuff.put( BigDecimal.valueOf(i+1) , buff);
//						       }
//					       } else
//					       {
//						       for (int i = 0; i < rows ; i++) {
//						    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
//							       	mapVarStringBuff.get( BigDecimal.valueOf(i+1)).append( new String( java.util.Arrays.copyOfRange(dataRead, i, i+1) ) ) ;
//						       }
//					       }					       
					       
					       if(ipos==0) {
						       if(setVarsIds!=null && !setVarsIds.contains(1+start_var)) {}
						       else {
							       	String s = new String( java.util.Arrays.copyOfRange(dataRead, 0, 1 ) );
							       	//AppContext.debug(s);
							       	StringBuffer buff = new StringBuffer(); // mapVarid2Strbuff.get(BigDecimal.valueOf(start_var+1));
							       	buff.append(  s );
							       	mapVarStringBuff.put( BigDecimal.valueOf(start_var+1) , buff);
						       }
					       } else {
						       if(setVarsIds!=null && !setVarsIds.contains(1+start_var)) {}
						       else {
							       	String s = new String( java.util.Arrays.copyOfRange(dataRead, 0, 1 ) );
							       	mapVarStringBuff.get( BigDecimal.valueOf(start_var+1)).append(s);
						       }
					       }
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
  
}
