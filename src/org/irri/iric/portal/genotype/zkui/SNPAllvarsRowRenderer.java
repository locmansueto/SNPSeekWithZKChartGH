package org.irri.iric.portal.genotype.zkui;

import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class SNPAllvarsRowRenderer implements RowRenderer{

	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	private static String STYLE_DIFFFROMREF = "font-weight:bold;color:red";
	private static String STYLE_SAMEASREF = "";
	
	private java.util.Map mapIdx2Refnuc;
	
	
	@Override
	public void render(Row arg0, Object arg1, int arg2) throws Exception {
		// TODO Auto-generated method stub
		
		Row row = (Row)arg0;
		String[] varrow = (String[])arg1;

		new Label(varrow[0]).setParent(row);
		new Label(varrow[1]).setParent(row);
		
		for( int j=2; j<varrow.length; j++ ) 
		{
			/*
			String element = varrow[j];
			
			if(element==null || element.isEmpty() ||  mapIdx2Refnuc==null || mapIdx2Refnuc.get(j)==null  )
				new Label("").setParent(row);
			else new Label(element).setParent(row);
			*/
			
			String element = varrow[j];
			if(element==null || element.isEmpty() ||  mapIdx2Refnuc==null || mapIdx2Refnuc.get(j)==null  )
				new Label("").setParent(row);
			else if(mapIdx2Refnuc.get(j).equals(element.charAt(0)))
				new Label(element).setParent(row);
			else {
					Label newlabel = new Label(element);
					newlabel.setStyle(STYLE_DIFFFROMREF);
					newlabel.setParent(row);
			}
					
		}
	}


	public void setMapIdx2Refnuc(java.util.Map mapIdx2Refnuc) {
		this.mapIdx2Refnuc = mapIdx2Refnuc;
	}
	
	
	
	
}
