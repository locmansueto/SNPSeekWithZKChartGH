package org.irri.iric.portal.variety.zkui;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;




//import org.irri.iric.portal.chado.domain.VIricStockBoxcode;
//import org.irri.iric.portal.chado.domain.VIricstockBasicprop2;
//import org.irri.iric.portal.chado.domain.VIricstockBoxcode;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyPlus;
import org.irri.iric.portal.domain.VarietyPlusPlus;
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
	
	private boolean isBasic = true;
	
	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
			Variety  item = (Variety)value;
		
			if(item==null || listitem==null) return;
	
	        // keep value in listitem
	        listitem.setValue(value);

	        
	        
	        addListcell(listitem, item.getName());
	        
	        //if( (item.getIrisId()==null || item.getIrisId().isEmpty()) && item instanceof VIricstockBoxcode ) {
	        //	VIricstockBoxcode varprop2 = (VIricstockBoxcode)item;
	        //	addListcell(listitem, varprop2.getBoxCode());
	        //}
	        if( (item.getIrisId()==null || item.getIrisId().isEmpty()) && item instanceof Variety ) {
	        	 
	        	Variety  varprop2 = (Variety)item;
	        	addListcell(listitem, varprop2.getBoxCode());
	        }
	        else
	        	addListcell(listitem, item.getIrisId());
	        
	        addListcell(listitem, item.getSubpopulation());
	        addListcell(listitem, item.getCountry());
	        
	        if(!isBasic) {
		        if(item instanceof VarietyPlusPlus) {
		        	Iterator itValues = ((VarietyPlusPlus)item).getValueMap().values().iterator();
		        	while(itValues.hasNext()) {
		        		Object val=itValues.next();
		        		String strval=null; 
		        		if(val instanceof BigDecimal || val instanceof Double || val instanceof Float)
		        			strval = String.format("%.3f", val).replaceAll("\\.?0+$","");
		        		else val.toString();
		        		addListcell(listitem,  strval );
		        	}
		        }
		        else if(item instanceof VarietyPlus ) {
	        		Object val=((VarietyPlus)item).getValue();
	        		String strval=null; 
	        		if(val instanceof BigDecimal || val instanceof Double || val instanceof Float)
	        			strval = String.format("%.3f", val);
	        		else val.toString();
	        		addListcell(listitem, strval );
		        }
	        }
	       
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

		public void setBasic(boolean isBasic) {
			this.isBasic = isBasic;
		}
	    
	    

}
