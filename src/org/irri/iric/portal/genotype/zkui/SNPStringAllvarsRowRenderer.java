package org.irri.iric.portal.genotype.zkui;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.Variety;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class SNPStringAllvarsRowRenderer implements SNPRowRendererStyle, RowRenderer{


	private Set setHighlighColumns = null;
	
	private int colorMode = COLOR_MISMATCH;	// 0
	
	//private java.util.Map<Integer,Character> mapIdx2Refnuc;
	private char[] refnuc; 
	private Map<BigDecimal, Variety> mapVarId2Var;
	
	@Override
	public void render(Row arg0, Object arg1, int arg2) throws Exception {
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
		
		new Label(mapVarId2Var.get(snpstring.getVar()).getName()).setParent(row);
		new Label(snpstring.getMismatch().toString()).setParent(row);

		String snpstr = snpstring.getVarnuc().trim();
		
		if(snpstr.length()!=refnuc.length) {
			System.out.println(snpstring.getVar() + "  " + mapVarId2Var.get(snpstring.getVar()).getName()  + "snpstr.length()!=refnuc.length:" + snpstr + "  " + new String(refnuc));
			return;
		}
			
		if(colorMode==this.COLOR_MISMATCH) {
		
			for( int j=0; j<snpstr.length(); j++ ) 
			{
				char element = snpstr.charAt(j);
				
				
				Label newlabel=null;
				String backcolor = "";
				if(setHighlighColumns!=null && setHighlighColumns.contains(j))
					backcolor = "background-color;lightgray";
				
				if(element=='0' || element=='.' || element=='*' ) {
					newlabel = new Label("");
					newlabel.setStyle(backcolor);
				}
				else if(refnuc[j]==element ) {
					newlabel = new Label(String.valueOf(element));
					//newlabel.setStyle("align:center");
					newlabel.setStyle(STYLE_BORING + ";" + backcolor);
				}
				else {
						newlabel = new Label( String.valueOf(element));
						//newlabel.setStyle("align:center");
						newlabel.setStyle(STYLE_DIFFFROMREF+ ";" + backcolor);
				}
				newlabel.setParent(row);
					
			}
		} else if(colorMode==this.COLOR_NUCLEOTIDE) {

			for( int j=0; j<snpstr.length(); j++ ) 
			{
				char element = snpstr.charAt(j);
				Label newlabel=null;
				String backcolor = "";
				if(setHighlighColumns!=null && setHighlighColumns.contains(j))
					backcolor = "background-color;lightgray";
				
				if(element=='0' || element=='.' || element=='*'){
					newlabel = new Label("");
					newlabel.setStyle(backcolor);
				}
				else {
					newlabel = new Label( String.valueOf(element));
					//newlabel.setStyle("align:center");

					if(element=='A') newlabel.setStyle(STYLE_A + ";" + backcolor);
					else if(element=='T') newlabel.setStyle(STYLE_T+ ";" + backcolor);
					else if(element=='G') newlabel.setStyle(STYLE_G+ ";" + backcolor);
					else if(element=='C') newlabel.setStyle(STYLE_C+ ";" + backcolor);
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
	
	
	
	
}
