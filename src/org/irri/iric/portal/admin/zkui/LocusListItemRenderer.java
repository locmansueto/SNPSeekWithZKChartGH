package org.irri.iric.portal.admin.zkui;

//import org.irri.iric.portal.chado.domain.VLocusNotes;
import java.util.Iterator;

import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MarkerAnnotation;
import org.irri.iric.portal.domain.MergedLoci;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Row;

public class LocusListItemRenderer implements ListitemRenderer {

	// private Map mapUniquename2Description;

	private String prefixDesc = "";

	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";

	/*
	 * public LocusGridRenderer(Map mapUniquename2Description) { super();
	 * this.mapUniquename2Description = mapUniquename2Description; }
	 */

	@Override
	public void render(Listitem listitem, Object data, int index) throws Exception {

		Locus locus = (Locus) data;
		listitem.setValue(locus);

		addListcell(listitem, locus.getUniquename());
		addListcell(listitem, locus.getContig());
		addListcell(listitem, locus.getFmin().toString());
		addListcell(listitem, locus.getFmax().toString());
		addListcell(listitem, locus.getStrand().toString());

		if (locus.getDescription() == null) {
			addListcell(listitem, "");
		} else {
			if (prefixDesc == null) {
				addListcell(listitem, locus.getDescription().split("\\s+", 2)[1]);
			} else {
				addListcell(listitem, prefixDesc + locus.getDescription());
			}
		}
	}

	public LocusListItemRenderer() {
		super();
		
	}

	public LocusListItemRenderer(String prefixDesc) {
		super();
		this.prefixDesc = prefixDesc;
	}

	private void addListcell(Listitem listitem, String value) {
		addListcell(listitem, value, STYLE_BORING);
	}

	private void addListcell(Listitem listitem, String value, String style) {
		Listcell lc = new Listcell();
		Label lb = new Label(value);
		if (!style.isEmpty())
			lb.setStyle(style);
		lb.setParent(lc);
		lc.setParent(listitem);
	}
}
