package org.irri.iric.portal.genotype.service;

import java.util.List;

import org.irri.iric.portal.genotype.domain.Gene;
import org.irri.iric.portal.genotype.views.Snp2lines;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Selectbox;

public interface GenotypeFacade {
	
	
	
	public enum snpQueryMode { SNPQUERY_VARIETIES,  SNPQUERY_REFERENCE, SNPQUERY_ALLREFPOS } ;

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
	
	
}
