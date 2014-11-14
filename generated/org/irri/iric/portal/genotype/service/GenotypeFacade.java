package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;






import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.genotype.service.GenotypeFacade.snpQueryMode;
//import org.irri.iric.portal.genotype.domain.Gene;
//import org.irri.iric.portal.genotype.views.Snp2lines;
//import org.irri.iric.portal.genotype.views.ViewCountVarrefMismatchId;
//import org.irri.iric.portal.genotype.views.ViewSnpAllvars;
//import org.irri.iric.portal.genotype.views.ViewSnpAllvarsId;
//import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos;
//import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId;
//import org.irri.iric.portal.utils.zkui.HibernateSearchObject;
import org.zkoss.zk.ui.select.annotation.Wire;
//import org.zkoss.zul.Combobox;
//import org.zkoss.zul.Intbox;
//import org.zkoss.zul.Selectbox;

public interface GenotypeFacade {

	
	/**
	 * if true, use Core SNP only
	 * @param isCore
	 */
	public void setCoreSnp(boolean isCore);
	
	
	/**
	 * limit all queries to these set of varieties only
	 * @param varieties
	 */
	public void limitVarieties(Set varieties);
	
	/**
	 * Query mode
	 * SNPQUERY_VARIETIES	query and compare two varieties
	 *  SNPQUERY_REFERENCE  allele with the reference
	 *  SNPQUERY_ALLREFPOS  all snp positions in the reference
	 * SNPQUERY_ALLVARIETIESPOS	query for all varieties
	 * @author lmansueto
	 *
	 */
	//public static enum snpQueryMode { SNPQUERY_VARIETIES,  SNPQUERY_REFERENCE, SNPQUERY_ALLREFPOS ,SNPQUERY_ALLVARIETIESPOS, SNPQUERY_SETVARIETIESPOS} ;
	public static enum snpQueryMode { SNPQUERY_VARIETIES,  SNPQUERY_ALLREFPOS, SNPQUERY_ALLVARIETIESPOS }; //, SNPQUERY_SETVARIETIESPOS} ;
	
	/**
	 * Get list of variety names
	 * @return
	 */
	public java.util.List<String> getVarnames();
	
	/**
	 * Get variety subpopulations
	 * @return
	 */
	public java.util.List<String> getSubpopulations();
	
	
	/**
	 * 
	 * @param subpopulation
	 * @return
	 */
	public Set getVarietiesForSubpopulation(String subpopulation);
	
	/**
	 * Get list of gene names
	 * @return
	 */
	public java.util.List<String> getGenenames();
	
	/**
	 * Get list of chromosome names
	 * @return
	 */
	public java.util.List<String> getChromosomes();
	
	/**
	 * Get length of feature
	 * @param feature
	 * @return
	 */
	public Integer getFeatureLength(String feature);
	
	/**
	 * Get list of SNPs for two varieties in chromosome position
	 * @param var1
	 * @param var2
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @param querymode
	 * @param b 
	 * @return
	 */
	public List<Snps2Vars> getSNPinVarieties(
			String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean b);
	
	/**
	 *  Get list of SNPs for two varieties within gene plus bp basepairs up and downstream
	 * @param var1
	 * @param var2
	 * @param genename
	 * @param plusminusBp
	 * @param querymode
	 * @return
	 */
	public List<Snps2Vars> getSNPinVarieties( String var1, String var2, String genename, Integer plusminusBp,  snpQueryMode querymode);
	
	
	public List<Snps2Vars> getSNPinVarieties(int n);	
	
	/**
	 * Get Gene object with name
	 * @param name
	 * @return
	 */
	public Gene getGeneFromName(String name);
	
	
	/**
	 * Get SNP positions within the queried region, based on last call of getSNPinAllVarieties()
	 * @return
	 */
	public List<SnpsAllvarsPos>  getSnpsposlist() ;
	
	
	/**
	 * Get list of SNPs for all varieties within gene plus bp basepairs up and downstream
	 * fetch only mismatch/or all depending on AppContext.isSNPAllvarsFetchMismatchOnly
	 * 
	 * @param genename
	 * @param plusminusBp
	 * @return
	 */
	public List<SnpsAllvars> getSNPinAllVarieties(String genename, Integer plusminusBp);

	
	/**
	 * Get SNPs in chromosome region for all varieties
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @return
	 */
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome) ;

	
	/**
	 * Get SNPs in chromosome region for varieties from irstRow to (firstRow+numRows-1), based on the sorting,
	 * fetch only mismatch/or all depending on AppContext.isSNPAllvarsFetchMismatchOnly
	 * 
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @param firstRow
	 * @param numRows
	 * @return
	 */
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) ;
	
	
	/**
	 * Get SNPs in chromosome region for varieties from irstRow to (firstRow+numRows-1), based on the sorting
	 * 
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @param firstRow
	 * @param numRows
	 * @param mismatchOnly	if true, fetch only mismatch with reference, else fetch all
	 * @return
	 */
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean mismatchOnly) ;
	
	
	public List<SnpsAllvars> getSNPinSetVarieties(Set varietyIds, Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean mismatchOnly) ;
	
	
	/**
	 * Count the number of mismatches for each variety in the region, sorted by decreasing number
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @param update	if true, update the counting, else use result from last getSNPinAllVarieties()
	 * @return
	 */
	public List<SnpsAllvarsRefMismatch> countSNPMismatchesInAlllVarieties(Integer startPos, Integer endPos, String chromosome, boolean update);

	/**
	 * after calling countSNPMismatchesInAlllVarieties(), results and subsets can be accessed from these
	 * @return
	 */
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches();
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(int firstRow, int numRows);

	
	/**
	 * Sorting of varieties
	 * @return
	 */
	public HashMap<Integer, BigDecimal> getMapOrder2Variety();
	public HashMap<BigDecimal, Integer> getMapVariety2Order();
	
	/**
	 * variety to mismatch map
	 * @return
	 */
	public java.util.HashMap<BigDecimal, Integer> getMapVariety2Mismatch(); 
	
	
	/**
	 * Construct phylogenetic tree based on mismatches within the region
	 * @param scale
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	public String[] constructPhylotree(String scale, String chr, int start, int end, String requestid);
	
	/**
	 * Construct phylogenetic tree based on mismatches within the region, 
	 *  limited by the top N distances, then all varieties in these N pairs 
	 * @param scale
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	public String[] constructPhylotreeTopN(String scale, String chr, int start, int end, int topN, String requestid);
	
	/**
	 * Ordering of varieties from phylogenetic computation based on mismatches in region
	 * @return	null if not yet computed
	 */
	public java.util.HashMap<BigDecimal, Integer> getMapVariety2PhyloOrder();
	
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile);


	/**
	 * SNPs string for all varieties with mismatch between positions in chromosome
	 * @param start
	 * @param end
	 * @param chr
	 * @param firstRow
	 * @param maxRows
	 * @return
	 */
	List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start, Integer end,
			Integer chr, int firstRow, int maxRows);


	List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start,
			Integer end, Integer chr, boolean exactMismatch, int firstRow,
			int maxRows);
/*
	List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start,
			Integer end, Integer chr, boolean exactMismatch, Map mapPos2Allele, int firstRow,
			int maxRows);
*/




	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type);


	/**
	 * Get SNPs in the provided set of positions
	 * @param snpposlist
	 * @param chr
	 * @param exactMatch
	 * @param firstRow
	 * @param maxRows
	 * @return
	 */


	public List getSNPStringInAllVarieties(Set snpposlist, Integer valueOf,
			boolean isCore, boolean exactMatch, int firstRow, int maxRows);


	public List getSNPinVarieties(String var1, String var2, Set snpposlist,
			String chr, snpQueryMode mode, boolean checked);
	
}
