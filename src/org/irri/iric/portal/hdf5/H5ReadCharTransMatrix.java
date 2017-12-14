package org.irri.iric.portal.hdf5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import java.util.TreeMap;

import org.irri.iric.portal.AppContext;




//import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.object.Dataset;
//import ncsa.hdf.object.Group;
//import ncsa.hdf.object.h5.H5File;


// read transposed matrix
public class H5ReadCharTransMatrix implements H5ReadMatrix {

	private static int DIM_POSITION = 0;
	private static int DIM_VARIETY = 1;

 
	//@Override
//   public  OutputMatrix readAllrows(H5Dataset hfdata, InputParamsIdxs input) throws Exception  {
//
//	   Dataset dataset = hfdata.getDataset();
//	   
//	   dataset.init();
//	   
//	   AppContext.debug("reading char hdf5 " + dataset.getFile()) ;
//
//       // start, stride and sizes will determined the selected subset
//       long[] start = dataset.getStartDims();
//       long[] stride = dataset.getStride();
//       long[] sizes = dataset.getSelectedDims();
//
//       // select the subset: set stride to (1, 1)
//       stride[DIM_POSITION] = 1;
//       stride[DIM_VARIETY] = 1;
//       
//       long n_dim_position=1;
//       long n_dim_variety=1;
//
//	    //dataset.setConvertByteToString(true);
// 
//       
//	   Map listVarString = new LinkedHashMap();
//
//	   Set setVarsIds = null;
//
//	   n_dim_variety =  dataset.getMaxDims()[DIM_VARIETY];
//       int rows = (int)n_dim_variety;
//	       
//		if(input.listVaridx==null) {
//			start[DIM_VARIETY] = 0;
//			sizes[DIM_VARIETY] = n_dim_variety;
//			System.out.print("pri:getting " + rows + " rows x ");
//			
//		} else if (input.listVaridx!=null) {
//		
//			setVarsIds= new HashSet();
//			for (int ivar=0; ivar<input.listVaridx.length; ivar++ )
//				setVarsIds.add(input.listVaridx[ivar]);
//			System.out.print("pri:getting " + setVarsIds.size() + " rows x ");
//		} else throw new RuntimeException("No variety indexes");
//
//		AppContext.debug("input.startPosidx=" + input.startPosidx + " ;input.endPosidx=" + input.endPosidx  + " ;input.listStartEndPosidx=" + input.listStartEndPosidx + 
//				" ;input.listPosidx=" + input.listPosidx);
//		
//		if(input.startPosidx >-1 && input.endPosidx>-1) {
//	        // select the subset: starting at (4, 2)
//	        //start[DIM_POSITION] = input.startPosidx-1;
//			start[DIM_POSITION] = input.startPosidx;
//	        n_dim_position = input.endPosidx- input.startPosidx+ 1 ;
//	        sizes[DIM_POSITION] = n_dim_position;
//	        
//		       int cols = (int)n_dim_position;
//		       // read the data of the subset
//		       byte[] dataRead = (byte[]) dataset.read();
//		       
//		       AppContext.debug(cols + " cols matrix");
//
//		       // print out the data values
//		       
//		       for (int i = 0; i < rows ; i++) {
//		    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
//			       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+ cols ) );
//			       	//AppContext.debug(s);
//			       	listVarString.put( BigDecimal.valueOf(i+1) , s );
//		       } 
//	        
//		} else if(input.listStartEndPosidx!=null) {
//			
//			Map<BigDecimal,StringBuffer> mapVarid2Strbuff=new LinkedHashMap();
//			
//			for(int iposrange=0; iposrange<input.listStartEndPosidx.length; iposrange++) {
//				
//		        //start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0] -1;
//				start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0];
//		        n_dim_position = input.listStartEndPosidx[iposrange][1] - input.listStartEndPosidx[iposrange][0] + 1 ;
//		        sizes[DIM_POSITION] = n_dim_position;
//		        
//			       int cols = (int)n_dim_position;
//			       // read the data of the subset
//			       byte[] dataRead = (byte[]) dataset.read();
//			       
//			       AppContext.debug( "locus " + iposrange + ", " + cols + " cols matrix");
//
//			       // print out the data values
//			       
//			       for (int i = 0; i < rows ; i++) {
//			    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
//				       	String s = new String( java.util.Arrays.copyOfRange(dataRead, i * cols, i * cols+ cols ) );
//				       	//AppContext.debug(s);
//				       	StringBuffer buff = mapVarid2Strbuff.get(BigDecimal.valueOf(i+1));
//				       	//listVarString.put( BigDecimal.valueOf(i+1) , s );
//				       	if(buff==null) {
//				       		buff = new StringBuffer();
//				       		mapVarid2Strbuff.put( BigDecimal.valueOf(i+1) , buff);
//				       	}
//				       	buff.append(s);
//			       } 
//			}
//			for(int i=0; i<rows; i++) {
//				listVarString.put( BigDecimal.valueOf(i+1) , mapVarid2Strbuff.get(BigDecimal.valueOf(i+1)).toString());
//			}
//			
//			AppContext.debug(mapVarid2Strbuff.get(BigDecimal.valueOf(1)).length() + " cols matrix");
//			
//		}
//		else if(input.listPosidx!=null) {
//
//  	        Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();
//
//		    AppContext.debug(input.listPosidx.length + " cols matrix");
//		       
//			for(int ipos=0; ipos<input.listPosidx.length; ipos++) {
//		        //start[DIM_POSITION] = input.listPosidx[ipos]-1;
//				start[DIM_POSITION] = input.listPosidx[ipos];
//		        n_dim_position = 1 ;
//		        sizes[DIM_POSITION] = n_dim_position;
//
//			       // cols = 1 ; //int cols = (int)n_dim_position;
//			       // read the data of the subset
//			       byte[] dataRead = (byte[]) dataset.read();
//			       
//
//			       
//			       // print out the data values
//			       if(ipos==0) {
//				       for (int i = 0; i < rows ; i++) {
//				    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
//					       	//AppContext.debug(s);
//					       	StringBuffer buff=new StringBuffer();
//					       	buff.append(new String( java.util.Arrays.copyOfRange(dataRead, i , i+1) ));
//					       	mapVarStringBuff.put( BigDecimal.valueOf(i+1) , buff);
//				       }
//			       } else
//			       {
//				       for (int i = 0; i < rows ; i++) {
//				    	   if(setVarsIds!=null && !setVarsIds.contains(i+1)) continue;
//					       	mapVarStringBuff.get( BigDecimal.valueOf(i+1)).append( new String( java.util.Arrays.copyOfRange(dataRead, i, i+1) ) ) ;
//				       }
//			       }
//			}
//			Iterator<BigDecimal> itVar = mapVarStringBuff.keySet().iterator();
//			while(itVar.hasNext())  {
//				BigDecimal varid = itVar.next();
//				listVarString.put(varid, mapVarStringBuff.get(varid).toString() );
//			}
//			
//		} else throw new RuntimeException("No SNP position indexes");
//
//       
//       return new OutputMatrix(listVarString);
//	}
  

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
	   
	   AppContext.debugIterate("reading char hdf5 " + dataset.getFile()) ;

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

      
	   Map<BigDecimal,String> listVarString = new LinkedHashMap();

	   Set setVarsIds = null;

	   n_dim_variety =  dataset.getMaxDims()[DIM_VARIETY];
      //int rows = (int)n_dim_variety;
	   int cols = (int)n_dim_variety;
      
      long maxpositions=dataset.getMaxDims()[DIM_POSITION];
      AppContext.debugIterate( "varieties=" +  n_dim_variety + ", positions=" + maxpositions);
      
      start[DIM_VARIETY] = 0;
      sizes[DIM_VARIETY] = n_dim_variety;

	       
		if(input.listVaridx==null) {
			start[DIM_VARIETY] = 0;
			sizes[DIM_VARIETY] = n_dim_variety;
			AppContext.debugIterate("pri:getting " + cols + " cols x ");
			
		} else if (input.listVaridx!=null) {
		
			setVarsIds= new HashSet();
			for (int ivar=0; ivar<input.listVaridx.length; ivar++ )
				setVarsIds.add(BigDecimal.valueOf(input.listVaridx[ivar]));
			
			AppContext.debugIterate("pri:getting " + setVarsIds.size() + " cols x ");
		} else throw new RuntimeException("No variety indexes");

		if(input.endPosidx>maxpositions-1) input.endPosidx= (int)maxpositions-1;
			
		AppContext.debug("input.startPosidx=" + input.startPosidx + " ;input.endPosidx=" + input.endPosidx  + " ;input.listStartEndPosidx=" + (input.listStartEndPosidx!=null? input.listStartEndPosidx.length: "null") + 
				" ;input.listPosidx=" + (input.listPosidx!=null?input.listPosidx.length:"null")  + "input.startendVaridx=" + (input.startendVaridx!=null? input.startendVaridx[0] + "-" + input.startendVaridx[1]:"null") );
		
		if(input.startPosidx >-1 && input.endPosidx>-1) {
			
			//AppContext.debug("range query not implemeted for transposed geno matrix");
			//throw new RuntimeException("range query not implemeted for transposed geno matrix");
			//return null;
			
			long n_varieties=dataset.getMaxDims()[DIM_VARIETY];
			int varstartidx=0;
			Map<Integer,char[]> mapPosidxString = new TreeMap();
			start[DIM_POSITION] = input.startPosidx; //input.listStartEndPosidx[iposrange][0];
	        n_dim_position =  input.endPosidx-input.startPosidx+1; //input.listStartEndPosidx[iposrange][1] - input.listStartEndPosidx[iposrange][0] + 1 ;
	        sizes[DIM_POSITION] = n_dim_position;
	        
	        //if(setVarsIds==null) {
			       // read the data of the subset
			       byte[] dataRead = (byte[]) dataset.read();
			       // print out the data values
			       cols=(int)n_varieties;
			       for(int ipos=0; ipos<n_dim_position; ipos++)
			    	   mapPosidxString.put(ipos, new String( java.util.Arrays.copyOfRange(dataRead, (int)ipos*cols , (int)(ipos+1)*cols )).toCharArray() );
			       
			       
			       Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();
			       if(setVarsIds==null) {
						for(long ivar=0; ivar<n_varieties; ivar++) 
							mapVarStringBuff.put( BigDecimal.valueOf(ivar+1) ,new StringBuffer());
			
						Iterator<Integer> itPos=mapPosidxString.keySet().iterator();
						while(itPos.hasNext()) {
							Integer pos=itPos.next();
							char strvars[]=mapPosidxString.get(pos);
							for(int ivar=0; ivar<n_varieties; ivar++) {
								mapVarStringBuff.get(BigDecimal.valueOf(ivar+1)).append( strvars[ivar] );
							}
						}
						
						Iterator<BigDecimal> itVar = mapVarStringBuff.keySet().iterator();
						while(itVar.hasNext())  {
							BigDecimal varid = itVar.next();
							listVarString.put(varid, mapVarStringBuff.get(varid).toString() );
						}
					} else {
						Iterator<BigDecimal> itVarid=setVarsIds.iterator(); 
						while(itVarid.hasNext()) {
							BigDecimal varid=itVarid.next();
							
							StringBuffer buffStr = new StringBuffer();
							Iterator<Integer> itPos=mapPosidxString.keySet().iterator();
							while(itPos.hasNext()) {
								Integer pos=itPos.next();
								char strvars[]=mapPosidxString.get(pos);
								buffStr.append( strvars[varid.intValue()-1-varstartidx] );
							}
							listVarString.put( varid , buffStr.toString());
						}
					}
			       
	        
		} else if(input.listStartEndPosidx!=null) {
			/*
			if(input.startendVaridx!=null) {
				AppContext.debug("startendVarids range query not implemeted for listStartEndPosidx transposed geno matrix");
				throw new RuntimeException("startendVarids query not implemeted for listStartEndPosidx transposed geno matrix");

			}
			*/
			Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();
			Map<Integer,char[]> mapPosidxString = new TreeMap();
			
	        sizes[DIM_VARIETY]=dataset.getMaxDims()[DIM_VARIETY];
	        start[DIM_VARIETY]=0;
	        long n_varieties=dataset.getMaxDims()[DIM_VARIETY];
	        
	        int varstartidx=0;
	        if(input.startendVaridx!=null) {
	        	varstartidx=input.startendVaridx[0]-1;
	        	for(int iposrange=0; iposrange<input.listStartEndPosidx.length; iposrange++) {
					
			        //start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0] -1;
					start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0];
			        n_dim_position = input.listStartEndPosidx[iposrange][1] - input.listStartEndPosidx[iposrange][0] + 1 ;
			        sizes[DIM_POSITION] = n_dim_position;
			        
			        start[DIM_VARIETY] = varstartidx; //input.startendVaridx[0]-1;
			        sizes[DIM_VARIETY] = input.startendVaridx[1]-input.startendVaridx[0]+1;
			        int query_vars= input.startendVaridx[1]-input.startendVaridx[0]+1;
			        
			        //if(setVarsIds==null) {
					       // read the data of the subset
					       byte[] dataRead = (byte[]) dataset.read();
					       // print out the data values
					       cols=(int)query_vars;
					       for(int ipos=0; ipos<n_dim_position; ipos++)
					    	   mapPosidxString.put(ipos, new String( java.util.Arrays.copyOfRange(dataRead, (int)ipos*cols , (int)(ipos+1)*cols )).toCharArray() );
			        //}
				}
	        	
				setVarsIds=new LinkedHashSet();
				for(int ivar=input.startendVaridx[0]; ivar<=input.startendVaridx[1]; ivar++) {
					setVarsIds.add(BigDecimal.valueOf(ivar));
				}
				
	        }
	        else {
				for(int iposrange=0; iposrange<input.listStartEndPosidx.length; iposrange++) {
					
			        //start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0] -1;
					start[DIM_POSITION] = input.listStartEndPosidx[iposrange][0];
			        n_dim_position = input.listStartEndPosidx[iposrange][1] - input.listStartEndPosidx[iposrange][0] + 1 ;
			        sizes[DIM_POSITION] = n_dim_position;
			        
			        //if(setVarsIds==null) {
					       // read the data of the subset
					       byte[] dataRead = (byte[]) dataset.read();
					       // print out the data values
					       cols=(int)n_varieties;
					       for(int ipos=0; ipos<n_dim_position; ipos++)
					    	   mapPosidxString.put(ipos, new String( java.util.Arrays.copyOfRange(dataRead, (int)ipos*cols , (int)(ipos+1)*cols )).toCharArray() );
			        //}
				}
	        }
		        
			if(setVarsIds==null) {
				for(long ivar=0; ivar<n_varieties; ivar++) 
					mapVarStringBuff.put( BigDecimal.valueOf(ivar+1) ,new StringBuffer());
	
				Iterator<Integer> itPos=mapPosidxString.keySet().iterator();
				while(itPos.hasNext()) {
					Integer pos=itPos.next();
					char strvars[]=mapPosidxString.get(pos);
					for(int ivar=0; ivar<n_varieties; ivar++) {
						mapVarStringBuff.get(BigDecimal.valueOf(ivar+1)).append( strvars[ivar] );
					}
				}
				
				Iterator<BigDecimal> itVar = mapVarStringBuff.keySet().iterator();
				while(itVar.hasNext())  {
					BigDecimal varid = itVar.next();
					listVarString.put(varid, mapVarStringBuff.get(varid).toString() );
				}
			} else {
				Iterator<BigDecimal> itVarid=setVarsIds.iterator(); 
				while(itVarid.hasNext()) {
					BigDecimal varid=itVarid.next();
					
					StringBuffer buffStr = new StringBuffer();
					Iterator<Integer> itPos=mapPosidxString.keySet().iterator();
					while(itPos.hasNext()) {
						Integer pos=itPos.next();
						char strvars[]=mapPosidxString.get(pos);
						buffStr.append( strvars[varid.intValue()-1-varstartidx] );
					}
					listVarString.put( varid , buffStr.toString());
				}
			}
			
		}
		else if(input.listPosidx!=null) {

 	        Map<BigDecimal,StringBuffer> mapVarStringBuff = new HashMap();
			Map<Integer,char[]> mapPosidxString = new TreeMap();

		    AppContext.debug(input.listPosidx.length + " cols matrix");
		    
		    
	        sizes[DIM_VARIETY]=dataset.getMaxDims()[DIM_VARIETY];
	        start[DIM_VARIETY]=0;
	        
	        long n_varieties=dataset.getMaxDims()[DIM_VARIETY];
		    int varstartidx=0;
	        
	        if(input.startendVaridx!=null) {
	        	varstartidx=input.startendVaridx[0]-1;
				for(int ipos=0; ipos<input.listPosidx.length; ipos++) {
			        //start[DIM_POSITION] = input.listPosidx[ipos]-1;
					start[DIM_POSITION] = input.listPosidx[ipos];
			        sizes[DIM_POSITION] = 1;
			        //int rows=1;

			        start[DIM_VARIETY] = varstartidx; //input.startendVaridx[0]-1;
			        sizes[DIM_VARIETY] = input.startendVaridx[1]-input.startendVaridx[0]+1;
			        int query_vars= input.startendVaridx[1]-input.startendVaridx[0]+1;
			        
			        //int start_var=0; 
			        //int cols = (int)n_dim_position;
			        
			        //if(setVarsIds==null) {
				        
				       // read the data of the subset
				       byte[] dataRead = (byte[]) dataset.read();
				       
				       // print out the data values
				       mapPosidxString.put(ipos , new String( java.util.Arrays.copyOfRange(dataRead, 0 , query_vars) ).toCharArray() );
			        //}
				}
				setVarsIds=new LinkedHashSet();
				for(int ivar=input.startendVaridx[0]; ivar<=input.startendVaridx[1]; ivar++) {
					setVarsIds.add(BigDecimal.valueOf(ivar));
				}
	        }
	        else {
				for(int ipos=0; ipos<input.listPosidx.length; ipos++) {
			        //start[DIM_POSITION] = input.listPosidx[ipos]-1;
					start[DIM_POSITION] = input.listPosidx[ipos];
			        sizes[DIM_POSITION] = 1;
			        //int rows=1;
			        
			        //int start_var=0; 
			        //int cols = (int)n_dim_position;
			        
			        //if(setVarsIds==null) {
				        
				       // read the data of the subset
				       byte[] dataRead = (byte[]) dataset.read();
				       
				       // print out the data values
				       mapPosidxString.put(ipos , new String( java.util.Arrays.copyOfRange(dataRead, 0 , (int)n_varieties) ).toCharArray() );
			        //}
				}
	        }

			
			/*
			for(long ivar=0; ivar<n_varieties; ivar++) 
				mapVarStringBuff.put(ivar ,new StringBuffer());

			Iterator<Integer> itPos=mapPosidxString.keySet().iterator();
			while(itPos.hasNext()) {
				Integer pos=itPos.next();
				char strvars[]=mapPosidxString.get(pos);
				for(long ivar=0; ivar<n_varieties; ivar++) {
					mapVarStringBuff.get(ivar).append( strvars[(int)ivar] );
				}
			}
			
			Iterator<Long> itVar = mapVarStringBuff.keySet().iterator();
			while(itVar.hasNext())  {
				Long varid = itVar.next();
				listVarString.put(BigDecimal.valueOf(varid), mapVarStringBuff.get(varid).toString() );
			}
			*/
			
			if(setVarsIds==null) {
				for(long ivar=0; ivar<n_varieties; ivar++) 
					mapVarStringBuff.put( BigDecimal.valueOf(ivar+1) ,new StringBuffer());
	
				Iterator<Integer> itPos=mapPosidxString.keySet().iterator();
				while(itPos.hasNext()) {
					Integer pos=itPos.next();
					char strvars[]=mapPosidxString.get(pos);
					for(int ivar=0; ivar<n_varieties; ivar++) {
						mapVarStringBuff.get(BigDecimal.valueOf(ivar+1)).append( strvars[ivar] );
					}
				}
				
				Iterator<BigDecimal> itVar = mapVarStringBuff.keySet().iterator();
				while(itVar.hasNext())  {
					BigDecimal varid = itVar.next();
					listVarString.put(varid, mapVarStringBuff.get(varid).toString() );
				}
			} else {
				Iterator<BigDecimal> itVarid=setVarsIds.iterator(); 
				while(itVarid.hasNext()) {
					BigDecimal varid=itVarid.next();
					
					StringBuffer buffStr = new StringBuffer();
					Iterator<Integer> itPos=mapPosidxString.keySet().iterator();
					while(itPos.hasNext()) {
						Integer pos=itPos.next();
						char strvars[]=mapPosidxString.get(pos);
						//if(setVarsIds.size()!=strvars.length) throw new RuntimeException("setVarsIds.size()!=strvars.length " + setVarsIds.size() +"!=" +strvars.length);
						buffStr.append( strvars[varid.intValue()-1-varstartidx] );
					}
					listVarString.put( varid , buffStr.toString());
				}
			}
		
		} else throw new RuntimeException("No SNP position indexes");

      
      return new OutputMatrix(listVarString);
	}
  
}
