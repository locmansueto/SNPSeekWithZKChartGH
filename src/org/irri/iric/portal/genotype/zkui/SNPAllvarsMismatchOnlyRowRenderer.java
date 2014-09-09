package org.irri.iric.portal.genotype.zkui;

import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class SNPAllvarsMismatchOnlyRowRenderer implements RowRenderer{

	private static String STYLE_A = "color:green";
	private static String STYLE_T = "color:red";
	private static String STYLE_G = "color:orange";
	private static String STYLE_C = "color:blue";

	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	private static String STYLE_DIFFFROMREF = "font-weight:bold;color:red";
	private static String STYLE_HEADERNARROW = "font-weight:normal;color:black;background-color:lightgray;font-family:Arial Narrow;";
	private static String STYLE_HEADER = "font-weight:bold;color:black;background-color:lightgray;";
	private static String STYLE_SAMEASREF = "";
	
	public static short COLOR_MISMATCH = 0;
	public static short COLOR_NUCLEOTIDE = 1;
	
	private int colorMode = COLOR_MISMATCH;	// 0
	
	private java.util.Map mapIdx2Refnuc;
	
	
	@Override
	public void render(Row arg0, Object arg1, int arg2) throws Exception {
		// TODO Auto-generated method stub
		
		Row row = (Row)arg0;
		String[] varrow = (String[])arg1;

		if(arg2==0 || arg2==1) {
			row.setStyle(STYLE_HEADERNARROW);
			
			
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
		
		
		new Label(varrow[0]).setParent(row);
		new Label(varrow[1]).setParent(row);

		if(colorMode==this.COLOR_MISMATCH) {
		
			for( int j=2; j<varrow.length; j++ ) 
			{
				String element = varrow[j];
				if(element==null || element.isEmpty() ||  mapIdx2Refnuc==null || mapIdx2Refnuc.get(j)==null  )
					new Label("").setParent(row);
				//else if(mapIdx2Refnuc.get(j).equals(element.charAt(0) ))
				else if(mapIdx2Refnuc.get(j).toString().equals(element ))
					new Label(element).setParent(row);
				else {
						Label newlabel = new Label(element);
						newlabel.setStyle(STYLE_DIFFFROMREF);
						newlabel.setParent(row);
				}
						
			}
		} else if(colorMode==this.COLOR_NUCLEOTIDE) {

			for( int j=2; j<varrow.length; j++ ) 
			{
				String element = varrow[j];
				if(element==null || element.isEmpty() ||  mapIdx2Refnuc==null || mapIdx2Refnuc.get(j)==null  )
					new Label("").setParent(row);
				else {
					Label newlabel = new Label(element);
					if(element.equals("A")) newlabel.setStyle(STYLE_A);
					else if(element.equals("T")) newlabel.setStyle(STYLE_T);
					else if(element.equals("G")) newlabel.setStyle(STYLE_G);
					else if(element.equals("C")) newlabel.setStyle(STYLE_C);
					newlabel.setParent(row);
				}
									
			}			
		}
	}


	public void setMapIdx2Refnuc(java.util.Map mapIdx2Refnuc) {
		this.mapIdx2Refnuc = mapIdx2Refnuc;
	}


	public void setColorMode(int colorMode) {
		this.colorMode = colorMode;
	}
	
	
	
	
}
