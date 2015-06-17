package org.irri.iric.portal.genotype.zkui;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.Variety;
import org.zkoss.lang.Objects;
//import org.zkoss.addon.MatrixRenderer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkmax.zul.MatrixRenderer;
import org.zkoss.zul.Label;
import org.irri.iric.portal.genotype.service.GenotypeQueryParams;
import org.irri.iric.portal.genotype.zkui.SNPRowRendererStyle;

public class Object2StringMatrixRenderer implements MatrixRenderer, SNPRowRendererStyle {

	private VariantStringData tabledata;
	private Map<Integer,BigDecimal> mapOrder2VarId;
	private Map<BigDecimal, Variety> mapVarId2Var;
	private GenotypeQueryParams params;
	
	private int frozenCols = 4;
	private int preloadRows = 50;
		
	Map<Integer,Integer> mapTableIdx2SnpIdx;
	Map<Integer,Set<Character>> mapTableIdx2NonsynAlleles;
	Set<Integer> setSnpInExonTableIdx;
	Set<BigDecimal> setDonorPos;
	Set<BigDecimal> setAcceptorPos;
	Map<Integer,BigDecimal> mapIdx2Pos;
	Map<BigDecimal, BigDecimal> mapRefPos2MSU7Pos;
	
	Set<BigDecimal> setPosGapRegion;
	Set<Long> setPosDeletionRegion;
	
	Component firstRow = null;
	
	Map<Integer,String> mapIdx2Refnuc;
		
	
	public Object2StringMatrixRenderer(VariantStringData data, Map mapVarId2Var, GenotypeQueryParams params) {
		super();
		
		firstRow = null;
		
		this.tabledata = data;
		this.mapVarId2Var=mapVarId2Var;
		this.params=params;
		
		Map<BigDecimal,Integer> mapVar2Order = data.getMapVariety2Order();
		mapOrder2VarId = new HashMap();
		Iterator<BigDecimal> itVar = mapVar2Order.keySet().iterator();
		while(itVar.hasNext()) {
			BigDecimal var = itVar.next();
			mapOrder2VarId.put( mapVar2Order.get(var) , var);
		}
		
		if(params.isbSNP() && tabledata.getSnpstringdata()!=null) {
			mapTableIdx2SnpIdx =  tabledata.getSnpstringdata().getMapMergedIdx2SnpIdx();
			//if(params.isbGraySynonymous()) {
			if(params.isbHighlightNonsynSnps() || params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {
				mapTableIdx2NonsynAlleles =   tabledata.getSnpstringdata().getMapIdx2NonsynAlleles();
				setSnpInExonTableIdx = tabledata.getSnpstringdata().getSetSnpInExonTableIdx();
				AppContext.debug( "mapTableIdx2NonsynAlleles=" + mapTableIdx2NonsynAlleles.size() + "; setSnpInExonTableIdx=" + setSnpInExonTableIdx.size());
			}
			
			//if(params.isbColorSpliceSNP()) {
			if(true) {
				setDonorPos = tabledata.getSnpstringdata().getSetSnpSpliceDonorPos();
				setAcceptorPos = tabledata.getSnpstringdata().getSetSnpSpliceAcceptorPos();
				
				AppContext.debug( "setDonorPos=" + setDonorPos.size() + "; setAcceptorPos=" + setAcceptorPos.size());
			}
			
		}
		
		if( params.isbIndel() && params.isbAlignIndels() ) {
			setPosGapRegion = tabledata.getIndelstringdata().getSetPosGapRegion();
			setPosDeletionRegion=  tabledata.getIndelstringdata().getSetPosDeletionRegion();
		}
		
		if(!data.isNipponbareReference()) {
			mapRefPos2MSU7Pos = new HashMap();
			Map<BigDecimal,MultiReferencePosition> mapMSU2Others =  data.getMapMSU7Pos2ConvertedPos();
			Iterator<BigDecimal> itMSU = mapMSU2Others.keySet().iterator();
			while( itMSU.hasNext() ) {
				BigDecimal msu7pos = itMSU.next();
				MultiReferencePosition otherrefpos = mapMSU2Others.get(msu7pos);
				if(otherrefpos==null) continue;
				mapRefPos2MSU7Pos.put( otherrefpos.getPosition() , msu7pos);
			}
			
			if(params.isbSNP()) {
			
				setDonorPos=new HashSet();
				Iterator<BigDecimal> itPos = tabledata.getSnpstringdata().getSetSnpSpliceDonorPos().iterator();
				while(itPos.hasNext()) {
					MultiReferencePosition otherrefpos = mapMSU2Others.get(itPos.next() );
					if(otherrefpos==null) continue;
					setDonorPos.add(  otherrefpos.getPosition()  );
				}
				setAcceptorPos = new HashSet();
				itPos = tabledata.getSnpstringdata().getSetSnpSpliceAcceptorPos().iterator();
				while(itPos.hasNext()) {
					MultiReferencePosition otherrefpos = mapMSU2Others.get(itPos.next() );
					if(otherrefpos==null) continue;
					setAcceptorPos.add( otherrefpos.getPosition()  );
				}
			}
			
			if( params.isbIndel() && params.isbAlignIndels() ) { // tabledata.hasIndel()) {
				setPosGapRegion = new HashSet();
				Iterator<BigDecimal> itPos =  tabledata.getIndelstringdata().getSetPosGapRegion().iterator();
				while(itPos.hasNext()) {
					MultiReferencePosition otherrefpos = mapMSU2Others.get(itPos.next() );
					if(otherrefpos==null) continue;
					setPosGapRegion.add(  otherrefpos.getPosition()  );
				}
				
				setPosDeletionRegion = new HashSet();
				Iterator<Long> itPosLong =  tabledata.getIndelstringdata().getSetPosDeletionRegion().iterator();
				while(itPosLong.hasNext()) {
					MultiReferencePosition otherrefpos = mapMSU2Others.get( BigDecimal.valueOf(itPosLong.next()) );
					if(otherrefpos==null) continue;
					setPosDeletionRegion.add( otherrefpos.getPosition().longValueExact() );
				}
			}
			
		}
				
		mapIdx2Pos = data.getMapIdx2Pos();
		
		mapIdx2Refnuc = new HashMap();

		AppContext.debug("Object2StringMatrixRenderer..started");
	}

	@Override
	public String renderCell(Component owner, Object data,  //List<String> data,
			int rowIndex, int colIndex) throws Exception {
		
		try {
			
		
		if(rowIndex==0 && colIndex==0) firstRow = owner;
		
		//AppContext.debug("rowIndex=" + rowIndex + ",colIndex=" + colIndex + " = " + data.getClass() + "  " + data.toString());
	
		//AppContext.debug("renderCell " + rowIndex + "," + colIndex  + "  " + data );
		
		//if(initDisplay) {
		
		if(colIndex>(frozenCols-1)) {
			//return ((Object[])data)[colIndex].toString();
			
			//AppContext.debug(  ((List)data).get(colIndex).getClass() + "  j="+ colIndex + "   " +  ((List)data).get(colIndex));
			//return  ((List)data).get(colIndex) .toString();
			
			int tableidx=colIndex-frozenCols;
			//int tableidx=colIndex;
			String cellval = "";
			//Object cellobj = ((Object[])((List)data).get(colIndex))[tableidx+frozenCols];
			Object cellobj = ((Object[])((List)data).get(colIndex))[tableidx+frozenCols];
				if(cellobj!=null) cellval= cellobj.toString();
			
			//AppContext.debug( rowIndex + "," + colIndex + "  " + cellval);
			
			String indelposref="";
			if(params.isbIndel() && params.isbAlignIndels() ) { // tabledata.hasIndel()) {
				//if(tabledata.getIndelstringdata()!=null && tabledata.getIndelstringdata().getMapIndelpos2Refnuc()!=null)
					if(tabledata.getIndelstringdata().getMapIndelpos2Refnuc()==null) 
						; //AppContext.debug("tabledata.getIndelstringdata().getMapIndelpos2Refnuc()==null");
					else
						indelposref=tabledata.getIndelstringdata().getMapIndelpos2Refnuc().get(   tabledata.getListPos().get(tableidx).getPos() );
					//if(indelposref==null) AppContext.info("indelposref==null");
			}
			
			if(cellval.equals("-")) cellval="&#151;"; // "&#151;"
			
			if(mapTableIdx2NonsynAlleles!=null) {
				if(!cellval.isEmpty()) {
					Integer snpidx= mapTableIdx2SnpIdx.get(tableidx); 
					if(snpidx!=null && setSnpInExonTableIdx.contains(snpidx)) {
						//Set setnonsyn =  mapTableIdx2NonsynAlleles.get(BigDecimal.valueOf(snpidx.longValue()));
						Set setnonsyn =  mapTableIdx2NonsynAlleles.get(snpidx);
						if(setnonsyn==null) {
							// synonymous
						} else {
							if(setnonsyn.contains(cellval.charAt(0))) {
								return  "<div style=\"" + STYLE_NONSYNONYMOUS + "\">" +  cellval + "</div>";
							} 
						}
						
						/*
						if(setnonsyn==null || !setnonsyn.contains(cellval.charAt(0))) {
							// synonymous
							//return  "<div style=\"" + STYLE_SYNONYMOUS + "\">" +  cellval + "</div>";
						} else
							return  "<div style=\"" + STYLE_NONSYNONYMOUS + "\">" +  cellval + "</div>";
							*/
					}
				}
			}
			
			
			if(cellval.isEmpty())
				return "";
			
			if(params.isbColorByMismatch()) {
			
				//if(cellval.equals("*") || cellval.equals("-") || cellval.equals("&#151;") || cellval.equals( tabledata.getListPos().get(tableidx).getRefnuc() ) || (indelposref!=null && cellval.equals(indelposref)) )
					
				if(cellval.equals("*") || cellval.equals("-") || cellval.equals("&#151;") || cellval.equals( mapIdx2Refnuc.get(tableidx) ) || (indelposref!=null && cellval.equals(indelposref)) )					
					return  "<div    style=\"text-align:center\">" +  cellval + "</div>";
				else {
					
					//if(params.isbSNP() && params.isbColorSpliceSNP()) {
					if(params.isbSNP()) {
						if( setDonorPos.contains( mapIdx2Pos.get(tableidx) ) ) {
							return  "<div   style=\"" + STYLE_SPLICE_DONOR + "\">" +  cellval + "</div>";
						}
						if( setAcceptorPos.contains( mapIdx2Pos.get(tableidx))) {
							return  "<div  style=\"" + STYLE_SPLICE_ACCEPTOR + "\">" +  cellval + "</div>";
						}
					}
					
					return  "<div   style=\"" + STYLE_MISMATCH + "\">" +  cellval + "</div>";
				}
			} else if(params.isbColorByAllele()) {
				
				if(cellval.equals("*") || cellval.equals("-") || cellval.equals("&#151;")  || (indelposref!=null && cellval.equals(indelposref)) )
					 return  "<div   style=\"text-align:center\">" +  cellval + "</div>";
				else {
					
					//if(params.isbSNP() && params.isbColorSpliceSNP()) {
					if(params.isbSNP()) {
						if( setDonorPos.contains( mapIdx2Pos.get(tableidx) ) ) {
							return  "<div   style=\"" + STYLE_SPLICE_DONOR + "\">" +  cellval + "</div>";
						}
						if( setAcceptorPos.contains( mapIdx2Pos.get(tableidx))) {
							return  "<div   style=\"" + STYLE_SPLICE_ACCEPTOR + "\">" +  cellval + "</div>";
						}
					}
					
					if(cellval.equals("A"))
							return  "<div   style=\"" + STYLE_A+ "\">" +  cellval + "</div>";
					else if(cellval.equals("C"))
						return  "<div   style=\"" + STYLE_C+ "\">" +  cellval + "</div>";
					else if(cellval.equals("G"))
						return  "<div   style=\"" + STYLE_G+ "\">" +  cellval + "</div>";
					else if(cellval.equals("T"))
						return  "<div   style=\"" + STYLE_T+ "\">" +  cellval + "</div>";
					else if(cellval.contains("/"))
						return  "<div    style=\"" + STYLE_HETERO+ "\">" +  cellval + "</div>";
					
				}

				
			}
			
			return "";
				
			
		}
		else {
			
			//AppContext.debug(  ((Object[])((List)data).get(colIndex))[colIndex].toString() );
			
			return ((Object[])((List)data).get(colIndex))[colIndex].toString();
		
		}			
		
		} catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
//	public String renderCellOrig(Component owner, Object data,  //List<String> data,
//			int rowIndex, int colIndex) throws Exception {
//		
//	
//		//AppContext.debug("renderCell " + rowIndex + "," + colIndex  + "  " + data );
//		
//		//if(initDisplay) {
//		
//		if(colIndex>2) {
//			//return ((Object[])data)[colIndex].toString();
//			
//			//AppContext.debug(  ((List)data).get(colIndex).getClass() + "  j="+ colIndex + "   " +  ((List)data).get(colIndex));
//			//return  ((List)data).get(colIndex) .toString();
//			
//			int tableidx=colIndex-frozenCols;
//			String cellval = "";
//			Object cellobj = ((Object[])((List)data).get(colIndex))[tableidx];
//				if(cellobj!=null) cellval= cellobj.toString();
//			
//			//AppContext.debug( rowIndex + "," + colIndex + "  " + cellval);
//			
//			String indelposref="";
//			if(params.isbIndel() && params.isbAlignIndels() ) { // tabledata.hasIndel()) {
//				//if(tabledata.getIndelstringdata()!=null && tabledata.getIndelstringdata().getMapIndelpos2Refnuc()!=null)
//					if(tabledata.getIndelstringdata().getMapIndelpos2Refnuc()==null) 
//						; //AppContext.debug("tabledata.getIndelstringdata().getMapIndelpos2Refnuc()==null");
//					else
//						indelposref=tabledata.getIndelstringdata().getMapIndelpos2Refnuc().get(   tabledata.getListPos().get(tableidx).getPos() );
//					if(indelposref==null) AppContext.info("indelposref==null");
//			}
//			
//			if(cellval.equals("-")) cellval="&#151;"; // "&#151;"
//			
//			if(mapTableIdx2NonsynAlleles!=null) {
//				if(!cellval.isEmpty()) {
//					Integer snpidx= mapTableIdx2SnpIdx.get(tableidx); 
//					if(snpidx!=null && setSnpInExonTableIdx.contains(snpidx)) {
//						//Set setnonsyn =  mapTableIdx2NonsynAlleles.get(BigDecimal.valueOf(snpidx.longValue()));
//						Set setnonsyn =  mapTableIdx2NonsynAlleles.get(snpidx);
//						if(setnonsyn==null) {
//							// synonymous
//						} else {
//							if(setnonsyn.contains(cellval.charAt(0))) {
//								return  "<div style=\"" + STYLE_NONSYNONYMOUS + "\">" +  cellval + "</div>";
//							} 
//						}
//						
//						/*
//						if(setnonsyn==null || !setnonsyn.contains(cellval.charAt(0))) {
//							// synonymous
//							//return  "<div style=\"" + STYLE_SYNONYMOUS + "\">" +  cellval + "</div>";
//						} else
//							return  "<div style=\"" + STYLE_NONSYNONYMOUS + "\">" +  cellval + "</div>";
//							*/
//					}
//				}
//			}
//			
//			
//			if(cellval.isEmpty())
//				return "";
//			else if(cellval.equals("*") || cellval.equals("-") || cellval.equals("&#151;") || cellval.equals( tabledata.getListPos().get(tableidx).getRefnuc() ) || (indelposref!=null && cellval.equals(indelposref)) )
//				 return  "<div style=\"text-align:center\">" +  cellval + "</div>";
//			else {
//				
//				//if(params.isbSNP() && params.isbColorSpliceSNP()) {
//				if(params.isbSNP()) {
//					if( setDonorPos.contains( mapIdx2Pos.get(tableidx) ) ) {
//						return  "<div style=\"" + STYLE_SPLICE_DONOR + "\">" +  cellval + "</div>";
//					}
//					if( setAcceptorPos.contains( mapIdx2Pos.get(tableidx))) {
//						return  "<div style=\"" + STYLE_SPLICE_ACCEPTOR + "\">" +  cellval + "</div>";
//					}
//				}
//				
//				return  "<div style=\"" + STYLE_MISMATCH + "\">" +  cellval + "</div>";
//			}
//			
//			
//		}
//		else if (colIndex==2) {
//			BigDecimal varid = mapOrder2VarId.get(rowIndex); 
//			return tabledata.getMapVariety2Mismatch().get(varid).toString();
//		}
//		else if (colIndex==1)
//			return mapVarId2Var.get(  mapOrder2VarId.get(rowIndex)  ).getIrisId();
//		else {
//			return  "<div style=\"width:150px\">" +  mapVarId2Var.get(mapOrder2VarId.get(rowIndex)   ).getName() + "</div>" ;
//		}
//			
//	}

	@Override
	public String renderHeader(Component owner,  Object data, // List<String> data,
			int rowIndex, int colIndex) throws Exception {
	
		try {
		//AppContext.debug("renderHeader " + rowIndex + "," + colIndex + "  " + data );
		
		Set setHeaderPosGapRegion=null;
		Set setHeaderPosDeletionRegion=null;
		
		if( params.isbIndel() && params.isbAlignIndels() ) {
			if(rowIndex==0) {
				setHeaderPosGapRegion=setPosGapRegion;
				setHeaderPosDeletionRegion=setPosDeletionRegion;
			} else if(rowIndex==2 && params.isbShowNPBPositions()) {
				setHeaderPosGapRegion=tabledata.getIndelstringdata().getSetPosGapRegion();
				setHeaderPosDeletionRegion=tabledata.getIndelstringdata().getSetPosDeletionRegion();
			}
		}
			
		
		
		if(rowIndex==0 || (rowIndex==2 && params.isbShowNPBPositions())) {
			String posvalue = ((List)data).get(colIndex).toString();
			
			if(posvalue.isEmpty()) return "";
			
			String posvalueonly = posvalue;
			if(colIndex>=frozenCols && !tabledata.isNipponbareReference() && posvalue.contains("-")){ // mapRefPos2MSU7Pos!=null) {
				posvalueonly = posvalue.split("\\-")[1].trim();
			}
			
			String color="black";
			
			if(colIndex>=frozenCols) {
				if( params.isbIndel() && params.isbAlignIndels() ) { // tabledata.hasIndel()) {

				if(setHeaderPosGapRegion.contains(BigDecimal.valueOf( Double.valueOf(posvalueonly))) )
					color = "green";
				else if( setHeaderPosDeletionRegion.contains(Long.valueOf(posvalueonly)) )
					color = "blue";

					
					//}
						
				}
			}
			
			if(rowIndex==0 || (rowIndex==2 && params.isbShowNPBPositions()))
				return "<div style=\"color:" + color +"\"><span class=\"vertical\">" + posvalue  +"</span></div>";
			else 
				return "<div style=\"color:" + color +"\">" + posvalue  + "</div>";
			
		}
		else {
			// allele values
			
			//AppContext.debug("rowIndex=" + rowIndex + ",colIndex=" + colIndex); 
			
			String refnuc = (String)((List)data).get(colIndex); 
			if(colIndex>=frozenCols && refnuc.isEmpty()) {
				//tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-frozenCols);
				BigDecimal pos = tabledata.getListPos().get(colIndex-frozenCols).getPos();
				if(tabledata.getIndelstringdata()!=null && tabledata.getIndelstringdata().getMapIndelpos2Refnuc()!=null)
					refnuc = tabledata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
			}
			
 
			if(rowIndex==1) {
				if(refnuc==null)
					mapIdx2Refnuc.put(colIndex-frozenCols , "");
				else mapIdx2Refnuc.put(colIndex-frozenCols , refnuc.trim());
			}
			
			if(refnuc==null) return "";
			 
			if(colIndex>frozenCols && !mapIdx2Refnuc.get(colIndex-frozenCols).equals(refnuc) ) {
			 	if(refnuc.equals("-")) refnuc="&#151;"; // "&#151;"
			 	
			 	String ttt = "<div align=\"center\" style=\"" + STYLE_MISMATCH + "\" >" +  refnuc.replace("null", "").trim()  + "</div>" ;
			 	//String ttt = "<label  align=\"center\"  style=\"" + STYLE_MISMATCH + "\" >" +  refnuc.replace("null", "").trim()  + "</label>" ;
			 			
			 	//String ttt = "<div style=\"" + STYLE_MISMATCH + "\" tooltip=\"headertooltip_" + colIndex + "\">" +  refnuc.replace("null", "").trim()  + "</div>" +
				//		"<popup id=\"headertooltip_" + colIndex + "\"><include src=\"biglistbox_headerpopup.zul\" colIdx=\"" + colIndex + "\"/></popup>";
			 	//AppContext.debug(ttt);
				return	 ttt; 
						
		 	} else {
				if(refnuc.equals("-")) refnuc="&#151;"; // "&#151;"
				String ttt = "<div align=\"center\" >" + refnuc.replace("null", "").trim() + "</div>" ;
				//String ttt = "<label align=\"center\"  >" + refnuc.replace("null", "").trim() + "</label>" ;
				
			 	//String ttt = "<div align=center tooltip=\"headertooltip_" + colIndex + "\">" + refnuc.replace("null", "").trim() + "</div>" + 
			 	//		"<popup id=\"headertooltip_" + colIndex + "\"><include src=\"biglistbox_headerpopup.zul\" colIdx=\"" + colIndex + "\"/></popup>" ;
			 	//AppContext.debug(ttt);
				return	 ttt; 
			 	
			 	
		 	}

			
			/*
			if(rowIndex==3) {
				if(colIndex>frozenCols && !mapIdx2Refnuc.get(colIndex-frozenCols).equals(refnuc) ) {
				 	if(refnuc.equals("-")) refnuc="&#151;"; // "&#151;"
					return	 "<div style=\"" + STYLE_MISMATCH + "\">" +  refnuc.replace("null", "").trim()  + "</div>";
			 	} else {
					if(refnuc.equals("-")) refnuc="&#151;"; // "&#151;"
				 	return "<div align=center>" + refnuc.replace("null", "").trim() + "</div>" ; //.split("\t")[rowIndex];
			 	}
			} else 
			{
				if(refnuc.equals("-")) refnuc="&#151;"; // "&#151;"
				return "<div align=center>" + refnuc.replace("null", "").trim() + "</div>" ; //.split("\t")[rowIndex];
			}
			*/
			
		}
		
		} catch(Exception ex) {
			ex.printStackTrace();throw ex;
		}
		
	
	}

	public Component getFirstRow() {
		return firstRow;
	}
	
	
	
	
}
