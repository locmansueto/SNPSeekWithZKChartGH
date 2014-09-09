package org.irri.iric.portal.genotype.zkui;


import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
//import org.irri.iric.portal.genotype.views.Snp2linesId;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Listcell;

public class SNPListItemRenderer implements ListitemRenderer {

	//private short querymode = 0;
	GenotypeFacade.snpQueryMode querymode;
	private String var1;
	private String var2;
	private String varref;
	
	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	
	
	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
		//Snp2linesId  item = (Snp2linesId)value;
		Snps2Vars  item = (Snps2Vars)value;
		
	
	        // keep value in listitem
	        listitem.setValue(value);

	        addListcell(listitem, item.getChr().toString());
	        addListcell(listitem, item.getPos().toString());

	        
	        if(querymode == GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES) {
	        	// two varieties are alleles with each other
	        	// all are interesting
	        	String  nuc1 = item.getVar1nuc();
	        	String  nuc2 = item.getVar2nuc();
	        	String  nucref = item.getRefnuc();
		        
        		addListcell(listitem, nucref) ;
        		
        		if(nuc1.equals(nuc2)) {
        			addListcell(listitem, nuc1);
        			addListcell(listitem, nuc2) ;
        		} else
        		{
        			addListcell(listitem, nuc1, STYLE_INTERESTING);
        			addListcell(listitem, nuc2, STYLE_INTERESTING) ;
        		}

	        	
	        } else if(querymode== GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE) {
	        	// variety is allele with reference
	        	// interesting is allele with reference
	        	String  nuc1 = item.getVar1nuc();
	        	String  nuc2 = item.getVar2nuc();
	        	String  nucref = item.getRefnuc();
	        	
		        addListcell(listitem, nucref);
	        	if(nucref.equals(nuc1))
	        		addListcell(listitem, nuc1) ;
	        	else
	        		addListcell(listitem, nuc1, STYLE_INTERESTING) ;
	        	
	        	if(nucref.equals(nuc2))
	        		addListcell(listitem, nuc2);
	        	else
	        		addListcell(listitem, nuc2, STYLE_INTERESTING) ;
	        	
	        	
		    } else if(querymode==GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS) {
		    	// all allele positions,
		    	// interesting is alleles
		    	String  nuc1 = item.getVar1nuc();
		    	String  nuc2 = item.getVar2nuc();
		    	String  nucref = item.getRefnuc();
		    	addListcell(listitem, nucref) ;
		    	
		    	if(nuc2.equals(nuc1)) {
		    		addListcell(listitem, nuc1) ;
		    		addListcell(listitem, nuc2) ;
		    	}
		    	else {
		    		addListcell(listitem, nuc1 , STYLE_INTERESTING) ;
		    		addListcell(listitem, nuc2, STYLE_INTERESTING) ;
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

		public void setVar1(String var1) {
			this.var1 = var1;
		}

		public void setVar2(String var2) {
			this.var2 = var2;
		}

		public void setVarref(String varref) {
			this.varref = varref;
		}

		public void setQuerymode(GenotypeFacade.snpQueryMode querymode) {
			this.querymode = querymode;
		}


	
	    

}
