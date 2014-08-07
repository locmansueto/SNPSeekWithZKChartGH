package org.irri.iric.portal.genotype.zkui;


import java.util.Map;

import org.irri.iric.portal.genotype.views.Snp2linesId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Listcell;

public class SNPAllvarsListItemRenderer implements ListitemRenderer {

	private java.util.List<ViewSnpAllvarsPosId> allposrefs;
//	private java.util.List<String> varlist;
//	private java.util.List<Long> mismatchlist;
	
	private   java.util.List<SNPAllvarsListItem> SNPAllvarsListItemList;
	
	
	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	private static String STYLE_DIFFFROMREF = "font-weight:bold;color:red";
	private static String STYLE_SAMEASREF = "";
	
	
	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
			//Map<Long, Character>  item = (Map<Long, Character>)value;
			SNPAllvarsListItem  item = (SNPAllvarsListItem)value;
		
			listitem.setValue(value);
			
			//if(varlist.get(index).toString().isEmpty()) return;
			
			addListcell(listitem, item.getVarname() ,  STYLE_SAMEASREF );

			/*long mismatches = 0;
			for(int col=0; col<allposrefs.size(); col++ ){
				Long pos = allposrefs.get(col).getPos();
				if(item.containsKey(pos ))
				{
					Character nuc = item.get(pos);
					//if(!allposrefs.get(col).getRefnuc().equals(nuc))
					if(!allposrefs.get(col).getRefnuc().equals(nuc))
						mismatches++;
				}
			}
			*/

			
			addListcell(listitem, item.getMismatches().toString() ,  STYLE_SAMEASREF );

			Map<Long, Character> mappos2nuc = item.getPos2nuc();
			for(int col=0; col<allposrefs.size(); col++ ){
				Long pos = allposrefs.get(col).getPos();
				if(mappos2nuc.containsKey(pos ))
				{
					Character nuc = mappos2nuc.get(pos);
					if(allposrefs.get(col).getRefnuc().equals(nuc))
						addListcell(listitem, mappos2nuc.get(pos).toString(), STYLE_SAMEASREF );
					else
					{
						addListcell(listitem, mappos2nuc.get(pos).toString(), STYLE_DIFFFROMREF );

					}
				}
				else
					addListcell(listitem, "");
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

		public void setSNPAllvarsListItemList(
				java.util.List<SNPAllvarsListItem> sNPAllvarsListItemList) {
			SNPAllvarsListItemList = sNPAllvarsListItemList;
		}


		public void setArrRefnucPos(java.util.List<ViewSnpAllvarsPosId> allposrefs) {
			this.allposrefs=allposrefs;
		}
/*		
		public void setVarlist(java.util.List varlist) {
			this.varlist=varlist;
		}

		public void setMismatchlist(java.util.List<Long> mismatchlist) {
			this.mismatchlist = mismatchlist;
		}
*/
	    


}


