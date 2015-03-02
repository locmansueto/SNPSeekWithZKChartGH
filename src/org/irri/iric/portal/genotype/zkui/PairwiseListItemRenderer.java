package org.irri.iric.portal.genotype.zkui;

import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.genotype.service.GenotypeQueryParams;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class PairwiseListItemRenderer  implements SNPRowRendererStyle, ListitemRenderer {

	private VariantStringData data;
	private GenotypeQueryParams params;

	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";
	private static int colorMode = COLOR_MISMATCH;
	
	public PairwiseListItemRenderer(VariantStringData data,
			GenotypeQueryParams params) {
		super();
		this.data = data;
		this.params = params;
	}

	
	
	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	@Override
	public void render(Listitem listitem, Object value, int index) throws Exception {
		// TODO Auto-generated method stub
		//Snp2linesId  item = (Snp2linesId)value;
		//Snps2Vars  item = (Snps2Vars)value;
		
	
	        // keep value in listitem
	        //listitem.setValue(data);
		
			Object obj[] = (Object[])value;
			
		
	        addListcell(listitem, obj[0].toString());
	        addListcell(listitem, obj[1].toString());

	        addListcell(listitem, (String)obj[2]);
	        addListcell(listitem, (String)obj[3]);
	        addListcell(listitem, (String)obj[4]);
	        
	        
//	        //if(querymode == GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES) {
//	        	// two varieties are alleles with each other
//	        	// all are interesting
//	        addListcell(listitem, (String)obj[3]);
//	        
//	    		String  nuc2s[] = ((String)obj[4]).split("/");
//        	
//	        	String  nuc1 = nuc1s[0];
//	        	String  nuc2 =  nuc2s[0];
//	        	String  nucref = (String)obj[2];
//	        	
//
//	        	

		        
//	        	if(colorMode==COLOR_MISMATCH) {
//	        	
//	        		addListcell(listitem, nucref) ;
//	        		
//	        		if( (nuc1_allele2!=null && !nuc1_allele2.isEmpty()) ||
//		        			(nuc2_allele2!=null && !nuc2_allele2.isEmpty()) )
//		        		{
//	        				
//	        				if(this.isGraySynonymous) {
//		        				if( nuc1_allele2!=null && !nuc1_allele2.isEmpty()  ) 
//		        				{
//		        					if( item.isVar1Nonsyn() )
//		        						addListcell(listitem, nuc1 + "/" + nuc1_allele2, STYLE_INTERESTING);
//		        					else
//		        						addListcell(listitem, nuc1 + "/" + nuc1_allele2, STYLE_SYNONYMOUS);
//		        				}
//		        				else {
//		        					if( item.isVar1Nonsyn() )
//		        						addListcell(listitem, nuc1, STYLE_INTERESTING);
//		        					else
//		        						addListcell(listitem, nuc1, STYLE_SYNONYMOUS);
//		        				}
//	
//		        				if( nuc2_allele2!=null && !nuc2_allele2.isEmpty() ) 
//		        				{
//		        					if( item.isVar2Nonsyn() )
//		        						addListcell(listitem, nuc2 + "/" + nuc2_allele2, STYLE_INTERESTING);
//		        					else 
//		        						addListcell(listitem, nuc2 + "/" + nuc2_allele2, STYLE_SYNONYMOUS);
//		        				}
//		        				else {
//		        					if( item.isVar2Nonsyn() )
//		        						addListcell(listitem, nuc2, STYLE_INTERESTING);
//		        					else
//		        						addListcell(listitem, nuc2, STYLE_SYNONYMOUS);
//		        				}
//		        				
//		        				
//	        				} else {
//		        				if( nuc1_allele2!=null && !nuc1_allele2.isEmpty() ) 
//				        			addListcell(listitem, nuc1 + "/" + nuc1_allele2, STYLE_INTERESTING);
//		        				else 
//		        					addListcell(listitem, nuc1, STYLE_INTERESTING);
//	
//		        				if( nuc2_allele2!=null && !nuc2_allele2.isEmpty() ) 
//				        			addListcell(listitem, nuc2 + "/" + nuc2_allele2, STYLE_INTERESTING);
//		        				else 
//		        					addListcell(listitem, nuc2, STYLE_INTERESTING);
//	        				}
//		        		}
//	        		else if(nuc1.equals(nuc2)) {
//	        			if(this.isGraySynonymous) {
//		        			if(item.isVar1Nonsyn()) 
//		        				addListcell(listitem, nuc1);
//		        			else 
//		        				addListcell(listitem, nuc1,STYLE_SYNONYMOUS);
//	
//		        			if(item.isVar2Nonsyn()) 
//		        				addListcell(listitem, nuc2) ;
//		        			else 
//		        				addListcell(listitem, nuc2,STYLE_SYNONYMOUS);
//	        			} else {
//		        				addListcell(listitem, nuc1);
//		        				addListcell(listitem, nuc2) ;
//	        			}
//
//	        			
//	        		} else
//	        		{
//	        			if(this.isGraySynonymous) {
//		        			if(item.isVar1Nonsyn()) 
//		        				addListcell(listitem, nuc1, STYLE_INTERESTING);
//		        			else
//		        				addListcell(listitem, nuc1, STYLE_SYNONYMOUS);
//		        				
//		        			if(item.isVar2Nonsyn()) 
//		        				addListcell(listitem, nuc2, STYLE_INTERESTING) ;
//		        			else
//		        				addListcell(listitem, nuc2, STYLE_SYNONYMOUS);
//	        			} else {
//	        				
//		        				addListcell(listitem, nuc1, STYLE_INTERESTING);
//		        				addListcell(listitem, nuc2, STYLE_INTERESTING) ;
//	        			}
//	        		}
//	        		
//	        	} else {
//	        		addListcell(listitem, nucref, getColor(nucref)) ;
//
//	        		if(this.isGraySynonymous) {
//		        		if(nuc1_allele2!=null && !nuc1_allele2.isEmpty()) {
//		        			if(item.isVar1Nonsyn())
//		        				addListcell(listitem, nuc1 + "/" + nuc1_allele2, STYLE_HETERO );
//		        			else
//		        				addListcell(listitem, nuc1 + "/" + nuc1_allele2, STYLE_SYNONYMOUS);
//		        		}
//		        		else {
//		        			if(item.isVar1Nonsyn())
//		        				addListcell(listitem, nuc1, getColor(nuc1)) ;
//		        			else
//		        				addListcell(listitem, nuc1, STYLE_SYNONYMOUS) ;
//		        		}
//		        		
//		        		if(nuc2_allele2!=null && !nuc2_allele2.isEmpty()) {
//		        			if(item.isVar2Nonsyn())
//		        				addListcell(listitem, nuc2 + "/" + nuc2_allele2, STYLE_HETERO );
//		        			else
//		        				addListcell(listitem, nuc2 + "/" + nuc2_allele2, STYLE_SYNONYMOUS );
//		        		}
//		        		else {
//		        			if(item.isVar2Nonsyn())
//		        				addListcell(listitem, nuc2, getColor(nuc2)) ;
//		        			else
//		        				addListcell(listitem, nuc2, STYLE_SYNONYMOUS) ;
//		        		}
//	        			
//	        		} else
//	        		{
//		        		if(nuc1_allele2!=null && !nuc1_allele2.isEmpty())
//			        			addListcell(listitem, nuc1 + "/" + nuc1_allele2, STYLE_HETERO );
//		        		else
//			        		addListcell(listitem, nuc1, getColor(nuc1)) ;
//		        		
//		        		if(nuc2_allele2!=null && !nuc2_allele2.isEmpty())
//		        			addListcell(listitem, nuc2 + "/" + nuc2_allele2, STYLE_HETERO );
//		        		else
//		        			addListcell(listitem, nuc2, getColor(nuc2)) ;
//	        		}
//	        	}

	        	
	        /* }  else if(querymode== GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE) {
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
	        	
	        	
		    } 
	        else if(querymode==GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS) {
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
		    */

	       
	    }
	
	
		private String getColor(String nuc) {
			
			if(nuc==null) return "color:black";
			
			if(nuc.equals("A"))
				return STYLE_A;
			if(nuc.equals("T"))
				return STYLE_T;
			if(nuc.equals("G"))
				return STYLE_G;
			if(nuc.equals("C"))
				return STYLE_C;
			
			return "color:black";
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
//
//		public void setVar1(String var1) {
//			this.var1 = var1;
//		}
//
//		public void setVar2(String var2) {
//			this.var2 = var2;
//		}
//
//		public void setVarref(String varref) {
//			this.varref = varref;
//		}
//
//		public void setQuerymode(GenotypeFacade.snpQueryMode querymode) {
//			//this.querymode = querymode;
//		}
//
//
//		public void setColorMode(int mode) {
//			this.colorMode = mode;
//		}
//	    
//		public void setGraySynonymous(boolean isgray) {
//			this.isGraySynonymous = isgray;
//		}

}
