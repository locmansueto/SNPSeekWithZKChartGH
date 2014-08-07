package org.irri.iric.portal.genotype.service;

import java.util.List;

import org.irri.iric.portal.genotype.domain.Gene;
import org.irri.iric.portal.genotype.views.Snp2lines;
import org.irri.iric.portal.genotype.views.ViewCountVarrefMismatchId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvars;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId;
import org.irri.iric.portal.utils.zkui.HibernateSearchObject;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Selectbox;

public interface GenotypeFacade {
	
	
	
	public static enum snpQueryMode { SNPQUERY_VARIETIES,  SNPQUERY_REFERENCE, SNPQUERY_ALLREFPOS ,SNPQUERY_ALLVARIETIESPOS} ;

	public java.util.List<String> getVarnames();
	public java.util.List<String> getGenenames();
	public java.util.List<String> getChromosomes();
	public Integer getFeatureLength(String feature);
	public List<Snp2lines> getSNPinVarieties(
			String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode);
	public List<Snp2lines> getSNPinVarieties(
			String var1, String var2, String genename, Integer plusminusBp,  snpQueryMode querymode);
	
	
	public List<Snp2lines> getSNPinVarieties(int n);	
	
	public Gene getGeneFromName(String name);
	public List<ViewSnpAllvarsId> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome) ;
	public List<ViewSnpAllvarsId> getSNPinAllVarieties(String genename, Integer plusminusBp);
	//public List<ViewSnpAllvarsPos> getSnpsposlist() ;
	public List<ViewSnpAllvarsPosId>  getSnpsposlist() ;
	
	public List getBySearchObject(HibernateSearchObject so, int start, int pageSize);
	public List<ViewSnpAllvarsId> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) ;
	public List<ViewCountVarrefMismatchId> countSNPMismatchesInAlllVarieties(Integer startPos, Integer endPos, String chromosome, boolean update);

	
	public java.util.HashMap<Integer, String> getMapOrder2Variety();
	public java.util.HashMap<String, Integer> getMapVariety2Order();
	public java.util.HashMap<String, Integer> getMapVariety2Mismatch(); 
	
	public List<ViewCountVarrefMismatchId> getListSNPAllVarsMismatches();
	public List<ViewCountVarrefMismatchId> getListSNPAllVarsMismatches(int firstRow, int numRows);
	
	public String constructPhylotree(String scale, String chr, int start, int end);

}
