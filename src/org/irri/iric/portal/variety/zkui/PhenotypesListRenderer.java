package org.irri.iric.portal.variety.zkui;

import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;


public class PhenotypesListRenderer implements ListitemRenderer {


	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	
	
	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
			Phenotypes  item = (Phenotypes)value;
		
	
	        // keep value in listitem
	        listitem.setValue(value);

	        String val = "";
	        if (item.getVal()!=null) val= item.getVal().toString();
	        addListcell(listitem, item.getTrait().toUpperCase());
	        addListcell(listitem, val );
	        addListcell(listitem, "");

	        
	       
	    }
	
		private void addListcell (Listitem listitem, String value) {
			addListcell ( listitem,  value, STYLE_BORING );
		}
	
	    private void addListcell (Listitem listitem, String value, String style) {
	        Listcell lc = new Listcell ();
	        Label lb = new Label(value);
	        if(!style.isEmpty()) 
	        	lb.setStyle(style);
	        lb.setParent(lc);
	        lc.setParent(listitem);
	    }

}
