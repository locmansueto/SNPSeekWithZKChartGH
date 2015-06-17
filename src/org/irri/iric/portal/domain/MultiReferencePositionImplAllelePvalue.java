package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.AppContext;
import org.zkoss.lang.reflect.Fields;

public class MultiReferencePositionImplAllelePvalue extends
		MultiReferencePositionImpl {

	private String allele;
	private Double pvalue;
	
	
	public MultiReferencePositionImplAllelePvalue(String orgcontigpos) {
		super();
			
		orgcontigpos = orgcontigpos = orgcontigpos.replace("(","").replace(")", "");
		String fields[] = orgcontigpos.split(",");

		try {

			organism=fields[0].trim();
			contig = fields[1].trim();
			position = BigDecimal.valueOf(Long.valueOf(fields[2].trim()));
			
			String f = fields[3].trim();
			if(!f.isEmpty()) allele = f; 
			
			f = fields[4].trim();
			if(!f.isEmpty()) {
				try{
					pvalue = Double.valueOf(f); 
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		
		} catch(Exception ex) {
			//AppContext.debug(orgcontigpos + "  " + fields.length );
			//ex.printStackTrace();
		}
		
		
	}
	
	public MultiReferencePositionImplAllelePvalue(String organism,
			String contig, BigDecimal position, String allele, Double pvalue) {
		super(organism, contig, position);
		// TODO Auto-generated constructor stub
		this.allele=allele;
		this.pvalue=pvalue;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String a="";
		String p="";
		if(allele!=null) a=allele;
		if(pvalue!=null) p=pvalue.toString();
		
		return "(" + organism + ", " + contig + "," + position + ","  + a + ","  + p + ")" ;
		
	}

	public String getAllele() {
		return allele;
	}

	public Double getPvalue() {
		return pvalue;
	}
	
	

}
