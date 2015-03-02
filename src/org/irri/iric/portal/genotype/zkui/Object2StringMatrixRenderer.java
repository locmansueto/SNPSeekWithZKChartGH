package org.irri.iric.portal.genotype.zkui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
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
	
	private int frozenCols = 3;
		
	Map<Integer,Integer> mapTableIdx2SnpIdx;
	Map<Integer,Set<Character>> mapTableIdx2NonsynAlleles;
	Set<Integer> setSnpInExonTableIdx;
	Set<BigDecimal> setDonorPos;
	Set<BigDecimal> setAcceptorPos;
	Map<Integer,BigDecimal> mapIdx2Pos;
	
	public Object2StringMatrixRenderer(VariantStringData data, Map mapVarId2Var, GenotypeQueryParams params) {
		super();
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
		
		if(params.isbSNP()) {
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
		
		mapIdx2Pos = data.getMapIdx2Pos();

	}

	@Override
	public String renderCell(Component owner, Object data,  //List<String> data,
			int rowIndex, int colIndex) throws Exception {
		
		
		
		if(colIndex>2) {
			//return ((Object[])data)[colIndex].toString();
			
			//AppContext.debug(  ((List)data).get(colIndex).getClass() + "  j="+ colIndex + "   " +  ((List)data).get(colIndex));
			//return  ((List)data).get(colIndex) .toString();
			
			int tableidx=colIndex-frozenCols;
			String cellval = ((Object[])((List)data).get(colIndex))[tableidx].toString();
			//AppContext.debug( rowIndex + "," + colIndex + "  " + cellval);
			
			String indelposref="";
			if(params.isbIndel() && params.isbAlignIndels() ) { // tabledata.hasIndel()) {
				//if(tabledata.getIndelstringdata()!=null && tabledata.getIndelstringdata().getMapIndelpos2Refnuc()!=null)
					indelposref=tabledata.getIndelstringdata().getMapIndelpos2Refnuc().get(   tabledata.getListPos().get(tableidx).getPos() );
			}
			
			
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
			else if(cellval.equals("*") || cellval.equals("-") || cellval.equals( tabledata.getListPos().get(tableidx).getRefnuc() ) || cellval.equals(indelposref))
				 return  "<div style=\"text-align:center\">" +  cellval + "</div>";
			else {
				
				//if(params.isbSNP() && params.isbColorSpliceSNP()) {
				if(params.isbSNP()) {
					if( setDonorPos.contains( mapIdx2Pos.get(tableidx) ) ) {
						return  "<div style=\"" + STYLE_SPLICE_DONOR + "\">" +  cellval + "</div>";
					}
					if( setAcceptorPos.contains( mapIdx2Pos.get(tableidx))) {
						return  "<div style=\"" + STYLE_SPLICE_ACCEPTOR + "\">" +  cellval + "</div>";
					}
				}
				
				return  "<div style=\"" + STYLE_MISMATCH + "\">" +  cellval + "</div>";
			}
			
			
		}
		else if (colIndex==2) {
			BigDecimal varid = mapOrder2VarId.get(rowIndex); 
			return tabledata.getMapVariety2Mismatch().get(varid).toString();
		}
		else if (colIndex==1)
			return mapVarId2Var.get(  mapOrder2VarId.get(rowIndex)  ).getIrisId();
		else {
			return  "<div style=\"width:150px\">" +  mapVarId2Var.get(mapOrder2VarId.get(rowIndex)   ).getName() + "</div>" ;
		}
		
	}

	@Override
	public String renderHeader(Component owner,  Object data, // List<String> data,
			int rowIndex, int colIndex) throws Exception {
		if(rowIndex==0) {
			String posvalue = ((List)data).get(colIndex).toString();
			String color="black";
			
			if(colIndex>=frozenCols) {
				if( params.isbIndel() && params.isbAlignIndels() ) { // tabledata.hasIndel()) {
					
					
					//AppContext.debug( "deletion pos=" + tabledata.getIndelstringdata().getSetPosDeletionRegion());
					//AppContext.debug( "insertion pos=" + tabledata.getIndelstringdata().getSetPosGapRegion());
					
					//if(tabledata.getIndelstringdata().getSetPosGapRegion()!=null && tabledata.getIndelstringdata().getSetPosDeletionRegion()!=null) {
						if( tabledata.getIndelstringdata().getSetPosGapRegion().contains(BigDecimal.valueOf( Double.valueOf(posvalue))) )
							color = "green";
						else if( tabledata.getIndelstringdata().getSetPosDeletionRegion().contains(Long.valueOf(posvalue)) )
							color = "blue";
					//}
						
				}
			}
			
			return "<div style=\"color:" + color +"\"><span class=\"vertical\">" + posvalue  +"</span></div>";
		}
		else {
			String refnuc = (String)((List)data).get(colIndex); 
			if(colIndex>frozenCols && refnuc.isEmpty()) {
				//tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-frozenCols);
				BigDecimal pos = tabledata.getListPos().get(colIndex-frozenCols).getPos();
				if(tabledata.getIndelstringdata().getMapIndelpos2Refnuc()!=null)
					refnuc = tabledata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
			}
			return "<div align=center>" + refnuc.replace("null", "").trim() + "</div>" ; //.split("\t")[rowIndex];
		}
	}
	
	
	
	
}
