package org.irri.iric.portal.variety.zkui;


import org.irri.iric.portal.domain.Variety;
//import org.irri.iric.portal.genotype.views.Snp2linesId;
//import org.irri.iric.portal.variety.domain.List3k;
//import org.irri.iric.portal.variety.domain.Germplasm;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Listcell;

public class VarietyListItemRenderer implements ListitemRenderer {


	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	
	
	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
			Variety  item = (Variety)value;
		
	
	        // keep value in listitem
	        listitem.setValue(value);

	        addListcell(listitem, item.getName());
	        addListcell(listitem, item.getIrisId());
	        addListcell(listitem, item.getSubpopulation());
	        addListcell(listitem, item.getCountry());

	        
	       
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
