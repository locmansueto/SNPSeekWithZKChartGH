package org.irri.iric.portal.genotype.zkui;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.Variety;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class SNPStringAllvarsRowRenderer implements SNPRowRendererStyle, RowRenderer{


	private Set setHighlighColumns = null;
	
	//private char allele2Matrix[][] = null;
	
	
	
	private int colorMode = COLOR_MISMATCH;	// 0
	private boolean graySynonymous = false;
	
	//private Map<Integer,Set<Character>> mapIdx2NonsynAlleles = null;
	//private List<boolean[]> listNonsynFlags;
	//private Map<Integer,boolean[]> mapIdx2NonsynFlags = null;
	
	//private java.util.Map<Integer,Character> mapIdx2Refnuc;
	private char[] refnuc; 
	private Map<BigDecimal, Variety> mapVarId2Var;
	
	@Override
	public void render(Row arg0, Object arg1, int i) throws Exception {
		// TODO Auto-generated method stub
		
		Row row = (Row)arg0;
		
		
/*
		if(arg2==0 || arg2==1) {
			row.setStyle(STYLE_HEADERNARROW);
			String[] varrow = ((String)arg1).split("\t") ;
			
			
			Label newcell= new Label(varrow[0]);
			newcell.setStyle(STYLE_HEADER);
			newcell.setParent(row);

			newcell= new Label(varrow[1]);
			newcell.setStyle(STYLE_HEADER);
			newcell.setParent(row);

			if(arg2==0)
				for( int j=2; j<varrow.length; j++ ) 
				{
					newcell= new Label(varrow[j]);
					newcell.setStyle(STYLE_HEADERNARROW);
					newcell.setParent(row);
				}
			else if(arg2==1)
				for( int j=2; j<varrow.length; j++ ) 
				{
					newcell= new Label(varrow[j]);
					newcell.setStyle(STYLE_HEADER);
					newcell.setParent(row);
				}
			
			return;
		}
	*/	
		
		SnpsStringAllvars snpstring = (SnpsStringAllvars)arg1;
		
		Map<Integer,Character> mapPosidx2allele2 = snpstring.getMapPosIdx2Allele2();
		//boolean isnonsyn[] = snpstring.getIsnonsyn();
		Set nonsynIdxset = snpstring.getNonsynIdxset();
		
		new Label(mapVarId2Var.get(snpstring.getVar()).getName()).setParent(row);
		new Label(snpstring.getMismatch().toString()).setParent(row);

		String snpstr = snpstring.getVarnuc().trim();
		
		
		if(snpstr.length()!=refnuc.length) {
			throw new RuntimeException(snpstring.getVar() + "  " + mapVarId2Var.get(snpstring.getVar()).getName()  + "snpstr.length()!=refnuc.length:" + snpstr + "  " + new String(refnuc));
		}
		
		//boolean nonsynflag[] = null;
		//if(listNonsynFlags!=null) nonsynflag=listNonsynFlags.get(i);
		//if(mapIdx2NonsynFlags!=null) nonsynflag=mapIdx2NonsynFlags.get(i);
			
		if(colorMode==this.COLOR_MISMATCH) {
		
			
			for( int j=0; j<snpstr.length(); j++ ) 
			{
				char element = snpstr.charAt(j);
				
				
				Label newlabel=null;
				String backcolor = "";
				if(setHighlighColumns!=null && setHighlighColumns.contains(j+1))
					backcolor = "background-color:lightgray";
				
				String hetero="";
				//if(allele2Matrix!=null && allele2Matrix[i][j]!='\0') hetero = "/" + allele2Matrix[i][j];
				if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) hetero = "/" + mapPosidx2allele2.get(j);
				
				
				if(refnuc[j]==element ) {
					newlabel = new Label(String.valueOf(element)+hetero);
					//newlabel.setStyle("align:center");
					if (!hetero.isEmpty()) {
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
							newlabel.setStyle(STYLE_BORING + ";" + backcolor);
						else
							newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
					}
					else newlabel.setStyle(STYLE_BORING + ";" + backcolor);
				}
				else {
					
					if(element=='0' || element=='.' || element=='*'  || element=='$' ) {
						newlabel = new Label("");
						newlabel.setStyle(backcolor);
					} else {
						newlabel = new Label( String.valueOf(element)+hetero);
						//newlabel.setStyle("align:center");
						if (!hetero.isEmpty()) {
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
						/*
						else if(mapIdx2NonsynAlleles!=null) {
							Set setAlleles = mapIdx2NonsynAlleles.get(j);
							if(setAlleles==null) { // assume this is noncoding or intron region because not in nonsynonymous aanalysis
								newlabel.setStyle(STYLE_DIFFFROMREF+ ";" + backcolor);
							} else if (setAlleles.contains( element )) {
								newlabel.setStyle(STYLE_DIFFFROMREF+ ";" + backcolor);
							} else 
								newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
						}
						*/
						/*
						else if(nonsynflag!=null) {
							if(nonsynflag[j])
								newlabel.setStyle(STYLE_DIFFFROMREF+ ";" + backcolor);
							else
								newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
						}
						*/
						else newlabel.setStyle(STYLE_DIFFFROMREF+ ";" + backcolor);
					}
				}
				
				newlabel.setParent(row);
			}
		} else if(colorMode==this.COLOR_NUCLEOTIDE) {

			for( int j=0; j<snpstr.length(); j++ ) 
			{
				char element = snpstr.charAt(j);
				Label newlabel=null;
				String backcolor = "";
				if(setHighlighColumns!=null && setHighlighColumns.contains(j+1))
					backcolor = "background-color:lightgray";
				
				String hetero="";
				//if(allele2Matrix!=null && allele2Matrix[i][j]!='\0') hetero = "/" + allele2Matrix[i][j];
				if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) hetero = "/" + mapPosidx2allele2.get(j);
				
				if(element=='0' || element=='.' || element=='*' || element=='$'){
					newlabel = new Label("");
					newlabel.setStyle(backcolor);
				}
				else {
					newlabel = new Label( String.valueOf(element)+hetero);
					//newlabel.setStyle("align:center");

					if(!hetero.isEmpty())  newlabel.setStyle(STYLE_HETERO + ";" + backcolor);
					else {
						 if(graySynonymous && nonsynIdxset!=null) {
							 	if(nonsynIdxset.contains(j)) {
									if(element=='A') newlabel.setStyle(STYLE_A + ";" + backcolor);
									else if(element=='T') newlabel.setStyle(STYLE_T+ ";" + backcolor);
									else if(element=='G') newlabel.setStyle(STYLE_G+ ";" + backcolor);
									else if(element=='C') newlabel.setStyle(STYLE_C+ ";" + backcolor);
								} else 
									newlabel.setStyle(STYLE_SYNONYMOUS+ ";" + backcolor);
						 } 
						else if(element=='A') newlabel.setStyle(STYLE_A + ";" + backcolor);
						else if(element=='T') newlabel.setStyle(STYLE_T+ ";" + backcolor);
						else if(element=='G') newlabel.setStyle(STYLE_G+ ";" + backcolor);
						else if(element=='C') newlabel.setStyle(STYLE_C+ ";" + backcolor);
					}
				}
				newlabel.setParent(row);
			}
			
		}
	}


	public void setMapVarId2Var(Map mapVarId2Var) {
		this.mapVarId2Var = mapVarId2Var;
	}


	public void setRefnuc(char[] refnuc) {
		this.refnuc = refnuc;
	}


	public void setColorMode(int colorMode) {
		this.colorMode = colorMode;
	}


	public void setSetHighlighColumns(Set setHighlighColumns) {
		this.setHighlighColumns = setHighlighColumns;
	}


	public void setGraySynonymous(boolean graySynonymous) {
		this.graySynonymous = graySynonymous;
	}


	/*
	public void setMapIdx2NonsynAlleles(
			Map<Integer, Set<Character>> mapIdx2NonsynAlleles) {
		this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
	}
*/



	//public void setMapIdx2NonsynFlags(Map<Integer, boolean[]> mapIdx2NonsynFlags) {
	//	this.mapIdx2NonsynFlags = mapIdx2NonsynFlags;
	//}


	/*
	public void setListNonsynFlags(List<boolean[]> listNonsynFlags) {
		this.listNonsynFlags = listNonsynFlags;
	}
	*/




	
	
}
