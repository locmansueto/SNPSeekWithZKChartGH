package org.irri.iric.portal.genotype.zkui;

import java.math.BigDecimal;
import java.util.HashSet;
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

public class VariantTableRenderer implements MatrixRenderer, SNPRowRendererStyle {

	private int frozenCols=3;
	
	private Set setHighlighColumns = null;
	private int colorMode = COLOR_MISMATCH;	// 0
	private boolean graySynonymous = false;
	private String refnuc[]; 
	private Map<BigDecimal, Variety> mapVarId2Var;
	//private Map<Integer, BigDecimal> mapIdx2Pos;
	private Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indels;
	

	private Map<Integer, Integer> mapMergedIdx2SnpIdx;
	private Map<Integer, BigDecimal> mapMergedIdx2Pos;
	
	
		
	private VariantStringData data;
	
	
	public VariantTableRenderer(VariantStringData data, GenotypeQueryParams params, Map mapVarId2Var) {
		super();
		// TODO Auto-generated constructor stub
		this.data=data;
		
		this.mapVarId2Var=mapVarId2Var;
		refnuc = data.getRefnuc();
		graySynonymous = params.isbGraySynonymous();
		mapMergedIdx2Pos = data.getMapIdx2Pos();
		if(data.hasSnp()) {
			 if(data.hasIndel()) {
				 mapMergedIdx2SnpIdx =  data.getSnpstringdata().getMapMergedIdx2SnpIdx();
			 }
		}

		if(data.hasIndel()) {
			mapIndelId2Indels = data.getIndelstringdata().getMapIndelId2Indel();
		}
		
		
	}
	

	@Override
	public String renderCell(Component owner, Object celldata,  //List<String> data,
			int rowIndex, int colIndex) throws Exception {
	//	String d = data.get(colIndex);
	//	d = d.replace("ZK", "<span class='red' title='ZK'>ZK</span>")
	//			.replace("Hello", "<span class='blue' title='Hello'>Hello</span>");
	//	return "<div class='images_" + (colIndex%28) + "' title='x=" + 
	//	colIndex + ",y=" + rowIndex + "'>" + d + "</div>";
	//	return d.substring(colIndex, colIndex+1);

		SnpsStringAllvars snpstr = (SnpsStringAllvars)((List)celldata).get(colIndex);
		
		
		
		if(colIndex>=frozenCols) {
			

			
			if(snpstr instanceof IndelsStringAllvars) {
				IndelsStringAllvars indelstr = (IndelsStringAllvars)snpstr;
				
				StringBuffer buffCell = new StringBuffer();
				buffCell.append( getIndelCellStyle( indelstr  , colIndex-frozenCols) );
				
				if(indelstr.getVarnuc()!=null) 
					buffCell.append( getSNPCellStyle(snpstr,  colIndex-frozenCols) );
				return buffCell.toString();

			}
			else
				return getSNPCellStyle(snpstr,  colIndex-frozenCols);
			/*
			String retStr = "<div ";
			retStr+= " style=\"" +  getCellStyle(snpstr,colIndex-2) + "\">"; 
			
			
			if(snpstr.getMapPosIdx2Allele2().containsKey(colIndex-2))
				retStr+=snpstr.getVarnuc().substring(colIndex-2, colIndex-1) + "/" + snpstr.getMapPosIdx2Allele2().get(colIndex-2) ;
			else
				retStr+=snpstr.getVarnuc().substring(colIndex-2, colIndex-1);

			retStr+= "</div>";
			return retStr;
			*/

		}
		else if (colIndex==2)
			return snpstr.getMismatch().toString();
		else if (colIndex==1)
			return mapVarId2Var.get( snpstr.getVar() ).getIrisId();
		else {
			
			//owner.setAttribute("colWidth", "150px",true);
			//Objects.toString(owner);
			//return  "<div style=\"width:150px\">" +  mapVarId2Var.get( snpstr.getVar() ).getName() + "</div>" ;
			return   mapVarId2Var.get( snpstr.getVar() ).getName();
		}
		
	}

	@Override
	public String renderHeader(Component owner,  Object celldata, // List<String> data,
			int rowIndex, int colIndex) throws Exception {
		//return "<div class='images_" + (colIndex % 28) + "' title='"
		//		+ images[colIndex % 28] + "'>" + data.get(colIndex)
		//		+ "</div>";
		
//		if(colIndex==0) {
//			//owner.setAttribute("colWidth", "150px",true);
//			// return  "<div style=\"width:150px\">" +  (String)((List)data).get(colIndex)  + "</div>" ; //.split("\t")[rowIndex];
//			 return  "<div style=\"width:150px\">" +  (String)((List)data).get(colIndex)  + "</div>" ; //.split("\t")[rowIndex];
//		}
//		else return (String)((List)data).get(colIndex); //.split("\t")[rowIndex];
		return (String)((List)celldata).get(colIndex); 
	}
	
	
	
	
	
	public void setMapMergedIdx2SnpIdx(Map<Integer, Integer> mapMergedIdx2SnpIdx) {
		this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
	}

	public void setMapMergedIdx2Pos(Map<Integer, BigDecimal> mapMergedIdx2Pos) {
		this.mapMergedIdx2Pos = mapMergedIdx2Pos;
	}

	public void setSetHighlighColumns(Set setHighlighColumns) {
		this.setHighlighColumns = setHighlighColumns;
	}

	public void setColorMode(int colorMode) {
		this.colorMode = colorMode;
	}

	public void setGraySynonymous(boolean graySynonymous) {
		this.graySynonymous = graySynonymous;
	}

	public void setRefnuc(String[] refnuc) {
		this.refnuc = refnuc;
	}

	public void setMapVarId2Var(Map<BigDecimal, Variety> mapVarId2Var) {
		this.mapVarId2Var = mapVarId2Var;
	}

		

	public void setMapIndelId2Indels(
			Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indels) {
		this.mapIndelId2Indels = mapIndelId2Indels;
	}


	private String getAlignedIndelCellStyle(IndelsStringAllvars indelstring,  int j) {
		return "";
	}
	
	private String getIndelCellStyle(IndelsStringAllvars indelstring,  int j) {
		
		
		BigDecimal pos = mapMergedIdx2Pos.get(j);
		if(pos==null) return "";
		
		StringBuffer buff = new StringBuffer();
		BigDecimal alleleid = indelstring.getAllele1( pos );
		if(alleleid==null) return "";
		
		IndelsAllvarsPos indelpos = mapIndelId2Indels.get(alleleid);
		if(indelpos==null) return "";
	
		
		int dellen = indelpos.getDellength();
		
		if(dellen>0) {
			String insstr = indelpos.getInsString();
			if(insstr!=null && insstr.length()>0) {
				if(dellen==1 && insstr.length()==1)
					buff.append("<div  style=\"text-align:center;color:red\">snp ->" + insstr + "</div>" );
				else buff.append("<div style=\"text-align:center;color:red\">del " + dellen + "->" + insstr + "</div>" );
			} else
			{
				buff.append("<div  style=\"text-align:center;color:blue\">del " + dellen + "</div>");	
			}
		} else {
			String insstr = indelpos.getInsString();
			if(insstr!=null && insstr.length()>0) {
				buff.append("<div style=\"text-align:center;color:green\">" + insstr + "</div>");
			} else 
			{
				buff.append("<div style=\"text-align:center;color:black\">ref" + "</div>");
			}
		}
		
		
		
		BigDecimal alleleid2 = indelstring.getAllele2( pos );
		if(alleleid2==null || alleleid2.equals(alleleid)) {
			return buff.toString();
		}
		alleleid=alleleid2;
		
		indelpos = mapIndelId2Indels.get(alleleid);
		if(indelpos==null) {
			//AppContext.logger("no IndelsAllvarsPos for alleleid=" + alleleid);
			return buff.toString();
		}
		
		dellen = indelpos.getDellength();
		if(dellen>0) {
			String insstr = indelpos.getInsString();
			if(insstr!=null && insstr.length()>0) {
				if(dellen==1 && insstr.length()==1)
					buff.append("/<div style=\"text-align:center;color:red\">snp ->" + insstr +"</div>");
				else buff.append("/<div style=\"text-align:center;color:red\">del " + dellen + "->" + insstr +"</div>");
			} else
			{
				buff.append("/<div style=\"text-align:center;color:blue\">del " + dellen + "</div>");	
			}
		} else {
			String insstr = indelpos.getInsString();
			if(insstr!=null && insstr.length()>0) {
				buff.append("/<div style=\"text-align:center;color:green\">"  + insstr  + "</div>");
			} else 
			{
				buff.append("/<div style=\"text-align:center;color:black\">ref</div>");
			}
		}
		
		return buff.toString();
		//return "<div style=\"" +  newlabel.getStyle() + "\">" + newlabel.getValue() + "</div>";
	}
	
	//private String getCellStyle(SnpsStringAllvars snpstring, int j) {
	private String getSNPCellStyle(SnpsStringAllvars snpstring,  int inj) {

		int j=inj;
		if(mapMergedIdx2SnpIdx!=null)
		{
			if(!mapMergedIdx2SnpIdx.containsKey(inj)) return ""; //throw new RuntimeException("mapMergedIdx2SnpIdx no key at " + inj);
			if(mapMergedIdx2SnpIdx.get(inj)==null) throw new RuntimeException("mapMergedIdx2SnpIdx at " + inj + " is null");
			j = mapMergedIdx2SnpIdx.get(inj);
		}
		
		Map<Integer,Character> mapPosidx2allele2 = snpstring.getMapPosIdx2Allele2();
		
		String element = snpstring.getVarnuc().substring(j, j+1);
		
		//boolean isnonsyn[] = snpstring.getIsnonsyn();
		Set nonsynIdxset = snpstring.getNonsynIdxset();
		
		//new Label(mapVarId2Var.get(snpstring.getVar()).getName()).setParent(row);
		//new Label(snpstring.getMismatch().toString()).setParent(row);

//		String snpstr = snpstring.getVarnuc().trim();
		
		
//		if(snpstr.length()!=refnuc.length) {
//			throw new RuntimeException(snpstring.getVar() + "  " + mapVarId2Var.get(snpstring.getVar()).getName()  + "snpstr.length()!=refnuc.length:" + snpstr + "  " + new String(refnuc));
//		}
		
		//boolean nonsynflag[] = null;
		//if(listNonsynFlags!=null) nonsynflag=listNonsynFlags.get(i);
		//if(mapIdx2NonsynFlags!=null) nonsynflag=mapIdx2NonsynFlags.get(i);
			
		Label newlabel=null;		
		
		if(colorMode==this.COLOR_MISMATCH) {
		
			
//			for( int j=0; j<snpstr.length(); j++ ) 
//			{
//				char element = snpstr.charAt(j);
//				String element = allele;
				
				
//				Label newlabel=null;
				String backcolor = "";
				if(setHighlighColumns!=null && setHighlighColumns.contains(j+1))
					backcolor = "background-color:lightgray";
				
				//String hetero="";
				//if(allele2Matrix!=null && allele2Matrix[i][j]!='\0') hetero = "/" + allele2Matrix[i][j];
				if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
					if(element.compareTo(mapPosidx2allele2.get(j).toString())<0)
						element +=  "/" + mapPosidx2allele2.get(j);
					else
						element =  mapPosidx2allele2.get(j) + "/" + element;
				}
				
				
				if(refnuc[inj].equals(element) ) {
					newlabel = new Label(element);
					//newlabel.setStyle("align:center");
					if (element.contains("/")) {
						if(graySynonymous && nonsynIdxset!=null) {
							if(nonsynIdxset.contains(j))
								newlabel.setStyle(STYLE_HETERO + ";" + backcolor);
							else
								newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
						} 
						else
							newlabel.setStyle(STYLE_HETERO + ";" + backcolor);
					}
					else if(graySynonymous && nonsynIdxset!=null) {
						//newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
						
						if(nonsynIdxset.contains(j))
							newlabel.setStyle(STYLE_BORING + ";" + backcolor);
						else
							newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
							
					}
					else newlabel.setStyle(STYLE_BORING + ";" + backcolor);
				}
				else {
					
					if(element.equals("0") || element.equals(".") || element.equals("*")  || element.equals("$") ) {
						newlabel = new Label("");
						newlabel.setStyle(backcolor);
					} else {
						newlabel = new Label(element);
						//newlabel.setStyle("align:center");
						if (element.contains("/")) {
							if(graySynonymous && nonsynIdxset!=null) {
								if(nonsynIdxset.contains(j))
									newlabel.setStyle(STYLE_HETERO + ";" + backcolor);
								else
									newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
							} 
							else
								newlabel.setStyle(STYLE_HETERO + ";" + backcolor);
						}
						else if(graySynonymous && nonsynIdxset!=null) {
							if(nonsynIdxset.contains(j))
								newlabel.setStyle(STYLE_DIFFFROMREF+ ";" + backcolor);
							else
								newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
						}
						else newlabel.setStyle(STYLE_DIFFFROMREF+ ";" + backcolor);
					}
				}
				
//				newlabel.setParent(row);
//			}
		} else if(colorMode==this.COLOR_NUCLEOTIDE) {

//			for( int j=0; j<snpstr.length(); j++ ) 
//			{
//				char element = snpstr.charAt(j);
//				Label newlabel=null;
				String backcolor = "";
				if(setHighlighColumns!=null && setHighlighColumns.contains(j+1))
					backcolor = "background-color:lightgray";
				
				//String hetero="";
				//if(allele2Matrix!=null && allele2Matrix[i][j]!='\0') hetero = "/" + allele2Matrix[i][j];
				if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
					if(element.compareTo(mapPosidx2allele2.get(j).toString())<0)
						element +=  "/" + mapPosidx2allele2.get(j);
					else
						element =  mapPosidx2allele2.get(j) + "/" + element;
				}

				
				if(element.equals("0") || element.equals(".") || element.equals("*")  || element.equals("$") ) {
					newlabel = new Label("");
					newlabel.setStyle(backcolor);
				}
				else {
					newlabel = new Label( element );
					//newlabel.setStyle("align:center");

					if(element.contains("/"))  newlabel.setStyle(STYLE_HETERO + ";" + backcolor);
					else {
						 if(graySynonymous && nonsynIdxset!=null) {
							 	if(nonsynIdxset.contains(j)) {
									if(element.equals("A")) newlabel.setStyle(STYLE_A + ";" + backcolor);
									else if(element.equals("T")) newlabel.setStyle(STYLE_T+ ";" + backcolor);
									else if(element.equals("G")) newlabel.setStyle(STYLE_G+ ";" + backcolor);
									else if(element.equals("C")) newlabel.setStyle(STYLE_C+ ";" + backcolor);
								} else 
									newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
						 }
						 else if(element.equals("A")) newlabel.setStyle(STYLE_A + ";" + backcolor);
						else if(element.equals("T")) newlabel.setStyle(STYLE_T+ ";" + backcolor);
						else if(element.equals("G")) newlabel.setStyle(STYLE_G+ ";" + backcolor);
						else if(element.equals("C")) newlabel.setStyle(STYLE_C+ ";" + backcolor);
						 
					}
				}
		}
		return "<div style=\"text-align:center;" +  newlabel.getStyle() + "\">" + newlabel.getValue() + "</div>";
	}
	

}
