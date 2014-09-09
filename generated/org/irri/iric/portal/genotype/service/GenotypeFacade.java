package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;






import java.util.Set;

import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
//import org.irri.iric.portal.genotype.domain.Gene;
//import org.irri.iric.portal.genotype.views.Snp2lines;
import org.irri.iric.portal.genotype.views.ViewCountVarrefMismatchId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvars;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId;
import org.irri.iric.portal.utils.zkui.HibernateSearchObject;
import org.zkoss.zk.ui.select.annotation.Wire;
//import org.zkoss.zul.Combobox;
//import org.zkoss.zul.Intbox;
//import org.zkoss.zul.Selectbox;

public interface GenotypeFacade {
	
	
	
	public static enum snpQueryMode { SNPQUERY_VARIETIES,  SNPQUERY_REFERENCE, SNPQUERY_ALLREFPOS ,SNPQUERY_ALLVARIETIESPOS} ;

	public java.util.List<String> getVarnames();
	public java.util.List<String> getGenenames();
	public java.util.List<String> getChromosomes();
	public Integer getFeatureLength(String feature);
	public List<Snps2Vars> getSNPinVarieties(
			String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode);
	public List<Snps2Vars> getSNPinVarieties(
			String var1, String var2, String genename, Integer plusminusBp,  snpQueryMode querymode);
	
	
	public List<Snps2Vars> getSNPinVarieties(int n);	
	
	public Gene getGeneFromName(String name);
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome) ;
	public List<SnpsAllvars> getSNPinAllVarieties(String genename, Integer plusminusBp);
	//public List<ViewSnpAllvarsPos> getSnpsposlist() ;
	public List<SnpsAllvarsPos>  getSnpsposlist() ;
	
	//public List getBySearchObject(HibernateSearchObject so, int start, int pageSize);
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) ;
	public List<SnpsAllvarsRefMismatch> countSNPMismatchesInAlllVarieties(Integer startPos, Integer endPos, String chromosome, boolean update);

	
	public HashMap<Integer, BigDecimal> getMapOrder2Variety();
	public HashMap<BigDecimal, Integer> getMapVariety2Order();
	public java.util.HashMap<BigDecimal, Integer> getMapVariety2Mismatch(); 
	
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches();
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(int firstRow, int numRows);
	
	public String constructPhylotree(String scale, String chr, int start, int end);
	public String constructPhylotreeTopN(String scale, String chr, int start, int end, int topN);

}
