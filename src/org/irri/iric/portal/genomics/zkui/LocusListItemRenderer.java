package org.irri.iric.portal.genomics.zkui;

//import org.irri.iric.portal.chado.domain.VLocusNotes;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.Variety;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class LocusListItemRenderer implements ListitemRenderer {
	
	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";

	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
		Locus  item = (Locus)value;
		
		if(item==null || listitem==null) return;

        // keep value in listitem
        listitem.setValue(value);
        addListcell(listitem, item.getUniquename());
        addListcell(listitem, item.getContig());
        addListcell(listitem, item.getFmin().toString());
        addListcell(listitem, item.getFmax().toString());
        addListcell(listitem, item.getDescription());
        
        /*
        if( item instanceof VLocusNotes) {
        	addListcell(listitem, ((VLocusNotes)item).getNotes());
        }
        */
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
