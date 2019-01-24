package org.irri.iric.portal.variety.zkui;

import java.math.BigDecimal;
import java.util.Iterator;

//import org.irri.iric.portal.chado.oracle.domain.VHdrastockBasicprop;
//import org.irri.iric.portal.chado.oracle.domain.VIricstockBasicprop2;

//import org.irri.iric.portal.chado.postgres.domain.VHdrastockBasicprop;
//import org.irri.iric.portal.chado.postgres.domain.VIricstockBasicprop2;

//import org.irri.iric.portal.chado.domain.VIricStockBoxcode;
//import org.irri.iric.portal.chado.domain.VIricstockBasicprop2;
//import org.irri.iric.portal.chado.domain.VIricstockBoxcode;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyPlus;
import org.irri.iric.portal.domain.VarietyPlusPlus;
//import org.irri.iric.portal.genotype.views.Snp2linesId;
//import org.irri.iric.portal.variety.domain.List3k;
//import org.irri.iric.portal.variety.domain.Germplasm;

import org.irri.iric.portal.genotype.zkui.SNPRowRendererStyle;
import org.irri.iric.portal.variety.service.Data;
import org.zkoss.zul.Label;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Listcell;

public class VarietyListItemRenderer implements ListitemRenderer {

	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";

	private boolean isBasic = true;

	public VarietyListItemRenderer(boolean isBasic) {
		super();
		this.isBasic = isBasic;
	}

	public VarietyListItemRenderer() {
		super();
		this.isBasic = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem,
	 * java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		
		Variety item = (Variety) value;

		if (item == null || listitem == null)
			return;

		// keep value in listitem
		listitem.setValue(value);

		addListcell(listitem, item.getName());

		// if( (item.getIrisId()==null || item.getIrisId().isEmpty()) && item instanceof
		// VIricstockBoxcode ) {
		// VIricstockBoxcode varprop2 = (VIricstockBoxcode)item;
		// addListcell(listitem, varprop2.getBoxCode());
		// }

		/*
		 * if( (item.getIrisId()==null || item.getIrisId().isEmpty()) && item instanceof
		 * Variety ) {
		 * 
		 * Variety varprop2 = (Variety)item; addListcell(listitem,
		 * varprop2.getBoxCode()); } else addListcell(listitem, item.getIrisId());
		 */

		/*
		 * if(item instanceof VHdrastockBasicprop) { if( (item.getAccession()==null ||
		 * item.getAccession().isEmpty())) { addListcell(listitem, ""); } else
		 * addListcell(listitem, item.getAccession()); } else { if(
		 * (item.getIrisId()==null || item.getIrisId().isEmpty())) { Variety varprop2 =
		 * (Variety)item; addListcell(listitem, varprop2.getBoxCode()); } else
		 * addListcell(listitem, item.getIrisId());
		 * 
		 * if( (item.getAccession()==null || item.getAccession().isEmpty())) {
		 * addListcell(listitem, ""); } else addListcell(listitem, item.getAccession());
		 * }
		 */

		if ((item.getIrisId() == null || item.getIrisId().isEmpty())) {
			Variety varprop2 = (Variety) item;
			// addListcell(listitem, varprop2.getBoxCode());
			addListcell(listitem, "");
		} else
			addListcell(listitem, item.getIrisId());

		if ((item.getAccession() == null || item.getAccession().isEmpty())) {
			addListcell(listitem, "");
		} else
			addListcell(listitem, item.getAccession());

		if (item.getSubpopulation() == null || item.getSubpopulation().isEmpty())
			addListcell(listitem, "");
		else
			addListcell(listitem, item.getSubpopulation(),
					"color:" + Data.getSubpopulationColor(item.getSubpopulation()));

		if (item.getCountry() != null)
			addListcell(listitem, item.getCountry());
		else
			addListcell(listitem, "");

		/*
		 * if(item instanceof VHdrastockBasicprop) {} else addListcell(listitem,
		 * item.getCountry());
		 */

		if (!isBasic) {
			if (item instanceof VarietyPlusPlus) {

				Iterator itValname = ((VarietyPlusPlus) item).getValueMap().keySet().iterator();
				// Iterator itValues =
				// ((VarietyPlusPlus)item).getValueMap().values().iterator();
				while (itValname.hasNext()) {
					Object valname = itValname.next();
					Object val = ((VarietyPlusPlus) item).getValueMap().get(valname);
					// Object val=itValues.next();
					String strval = "";
					if (val instanceof BigDecimal || val instanceof Double || val instanceof Float)
						strval = String.format("%.3f", val).replaceAll("\\.?0+$", "");
					else if (val != null)
						strval = val.toString();

					if (valname.toString().toLowerCase().equals("genotype")) {
						String style = "color:black;align:center";
						if (strval.equals("A"))
							style = SNPRowRendererStyle.STYLE_A;
						else if (strval.equals("C"))
							style = SNPRowRendererStyle.STYLE_C;
						else if (strval.equals("G"))
							style = SNPRowRendererStyle.STYLE_G;
						else if (strval.equals("T"))
							style = SNPRowRendererStyle.STYLE_T;
						else if (strval.contains("/"))
							style = SNPRowRendererStyle.STYLE_HETERO;
						addListcell(listitem, strval, style);
					} else
						addListcell(listitem, strval);
				}

				/*
				 * Iterator itValues =
				 * ((VarietyPlusPlus)item).getValueMap().values().iterator();
				 * while(itValues.hasNext()) { Object val=itValues.next(); String strval=null;
				 * if(val instanceof BigDecimal || val instanceof Double || val instanceof
				 * Float) strval = String.format("%.3f", val).replaceAll("\\.?0+$",""); else
				 * val.toString();
				 * 
				 * if()
				 * 
				 * addListcell(listitem, strval ); }
				 */
			} else if (item instanceof VarietyPlus) {
				Object val = ((VarietyPlus) item).getValue();
				String strval = "";
				if (val instanceof BigDecimal || val instanceof Double || val instanceof Float)
					strval = String.format("%.3f", val);
				else if (val != null)
					strval = val.toString();
				addListcell(listitem, strval);
			}
		}

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

	public void setBasic(boolean isBasic) {
		this.isBasic = isBasic;
	}

}
