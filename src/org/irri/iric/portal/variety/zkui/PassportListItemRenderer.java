package org.irri.iric.portal.variety.zkui;


import org.irri.iric.portal.chado.domain.VIricstockPassport;
import org.irri.iric.portal.domain.Variety;
//import org.irri.iric.portal.genotype.views.Snp2linesId;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Listcell;

public class PassportListItemRenderer implements ListitemRenderer {


	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	
	
	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
			VIricstockPassport  item = (VIricstockPassport)value;
		
	
	        // keep value in listitem
	        listitem.setValue(value);

	        addListcell(listitem, item.getDefinition());
	        addListcell(listitem, item.getName());
	        addListcell(listitem, item.getValue());

	        
	       
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
