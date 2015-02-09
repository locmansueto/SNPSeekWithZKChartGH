package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.genotype.service.GenotypeFacade.snpQueryMode;
import org.irri.iric.portal.domain.VariantTable;
import org.zkoss.zk.ui.select.annotation.Wire;

public interface GenotypeFacade {

	
	// Set session state values
	/**
	 * if true, use Core SNP only
	 * @param isCore
	 */
	public void setCore(boolean isCore);
	
	
	/**
	 * limit all queries to these set of varieties only
	 * @param varieties
	 */
	public void limitVarieties(Set varieties);
	
	/**
	 * Query mode
	 * SNPQUERY_VARIETIES	query and compare two varieties
	 * SNPQUERY_ALLREFPOS  all snp positions in the reference
	 * SNPQUERY_ALLVARIETIESPOS	query for all varieties
	 * @author lmansueto
	 *
	 */
	public static enum snpQueryMode { SNPQUERY_VARIETIES,  SNPQUERY_ALLREFPOS, SNPQUERY_ALLVARIETIESPOS }; 

	
	// Queries for UI list item values
	
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
	
	
	// Query for Variety instances from constraints
	
	/**
	 * 
	 * @param subpopulation
	 * @return
	 */
	public Set getVarietiesForSubpopulation(String subpopulation);
		
		
	
	
	
	// Query for Gene instance
	/**
	 * Get Gene object with name
	 * @param name
	 * @return
	 */
	public Gene getGeneFromName(String name);
	

	
	// Query for SNPs in variety comparison 	

	//public List<Snps2Vars> getSNPinVarieties(int n);
	
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
	
	
	// Query for SNPs for All Varieties
	// some methods are commented out because they are used only for SNP_GENOTYPE query, which might be used in the future
	
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
	//public List<SnpsAllvars> getSNPinAllVarieties(String genename, Integer plusminusBp);

	
	/**
	 * Get SNPs in chromosome region for all varieties
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @return
	 */
	//public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome) ;

	
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
	//public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) ;
	
	
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
	//public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean mismatchOnly) ;
	
	
	/**
	 * Get SNPs in region for a given set of variety ids
	 * @param varietyIds
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @param firstRow
	 * @param numRows
	 * @param mismatchOnly
	 * @return
	 */
	//public List<SnpsAllvars> getSNPinSetVarieties(Set varietyIds, Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean mismatchOnly) ;
	
	
	// Query for variety and reference mismatches 
	
	/**
	 * Count the number of mismatches for each variety in the region, sorted by decreasing number
	 * @param startPos
	 * @param endPos
	 * @param chromosome
	 * @param update	if true, update the counting, else use result from last getSNPinAllVarieties()
	 * @return
	 */
	//public List<SnpsAllvarsRefMismatch> countSNPMismatchesInAlllVarieties(Integer startPos, Integer endPos, String chromosome, boolean update);

	
	/**
	 * after calling countSNPMismatchesInAlllVarieties(), results and subsets can be accessed from these
	 * @return
	 */
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches();
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(int firstRow, int numRows);

	
	/**
	 * Sorting of varieties, based on number of varieties. call after countSNPMismatchesInAlllVarieties
	 * @return
	 */
	public Map<Integer, BigDecimal> getMapOrder2Variety();
	public Map<BigDecimal, Integer> getMapVariety2Order();
	
	
	/**
	 * variety to number of mismatch map
	 * @return
	 */
	public java.util.Map<BigDecimal, Integer> getMapVariety2Mismatch(); 
	
	
	
	// Methods for phylogenetic tree construction based on allele mismatches
	
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
	//public String[] constructPhylotreeTopN(String scale, String chr, int start, int end, int topN, String requestid);
	
	

/**
 * Construct phylogenetic tree based on mismatches within the region, 
 * limited by grouping nodes below mindist mismatches
 *  
 * @param scale
 * @param chr
 * @param start
 * @param end
 * @param mindist
 * @return
 */
//	public Object[] constructPhylotreeMindist(String scale, String chr, int start, int end, String mindist);

	
	/**
	 * Ordering of varieties from phylogenetic computation based on mismatches in region. call after constructPhylotree
	 * @return	null if not yet computed
	 */
	//public Map<BigDecimal, Integer> getMapVariety2PhyloOrder();
	
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile);


	
	/**
	 * SNPs string for all varieties with mismatch between positions in chromosome.
	 * SNPString are alleles in adjacent SNPs position, concatenated into one string
	 * @param start
	 * @param end
	 * @param chr
	 * @param firstRow
	 * @param maxRows
	 * @return
	 */
	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start, Integer end, Integer chr);

/*
	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start,
			Integer end, Integer chr, boolean exactMismatch, int firstRow,
			int maxRows);
*/	
	
	/**
	 * Get SNPs in the provided set of positions, for all varieties
	 * @param snpposlist
	 * @param chr
	 * @param exactMatch
	 * @param firstRow
	 * @param maxRows
	 * @return
	 */
	public List getSNPStringInAllVarieties(Set snpposlist, Integer chr);
	

	// Query for MyList definition
	/**
	 * Verify the snps in setSNP and return only the valid ones  
	 * @param chr
	 * @param setSNP
	 * @param type
	 * @return
	 */
	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type);



	/**
	 * Get SNPs in the provided set of positions, for two varieties
	 * @param snpposlist
	 * @param chr
	 * @param exactMatch
	 * @param firstRow
	 * @param maxRows
	 * @return
	 */
	public List getSNPinVarieties(String var1, String var2, Set snpposlist,
			String chr, snpQueryMode mode, boolean checked);


	/**
	 * Get heterozygous allele (allele2) matrix varietyXposition
	 * @return
	 */
	//char[][] getHeteroAlleleMatrix();


	
	//void setNonsynOnly(boolean isNonsynOnly);


	//void setColorByNonsyn(boolean isColorByNonsyn);


	/**
	 * Map of table index to Set of non-synonymous alleles
	 * @return
	 */
	//Map<Integer, Set<Character>> getMapIdx2NonsynAlleles();


	public void setColorByNonsyn(boolean selected);


	public void setNonsynOnly(boolean selected);


	void setMismatchOnly(boolean isMismatchOnly);


	void setIncludeSNP(boolean includeSNP);


	void setIncludeIndel(boolean includeIndel);



	Map<BigDecimal, IndelsAllvarsPos> getMapIndelId2Indel();


	Map<Integer, BigDecimal> getMapIndelIdx2Pos();


	Map<Integer, Integer> getMapMergedIdx2SnpIdx();


	Map<Integer, BigDecimal> getMapMergedIdx2Pos();


	Map<BigDecimal, Set<String>> getMapPos2Allele();


	String getIndelAlleleString(IndelsAllvarsPos indelpos);


	String getIndelType(String allele);



	VariantStringData queryGenotype(GenotypeQueryParams params) throws Exception;
	List<VariantStringData> queryGenotype(List listParams) throws Exception;
	void downloadGenotypeGenome(GenotypeQueryParams params) throws Exception;


	VariantTable fillGenotypeTable(VariantTable table, VariantStringData data,
			GenotypeQueryParams params) throws Exception;




	VariantStringData compare2Varieties(BigDecimal var1, BigDecimal var2,
			GenotypeQueryParams params) throws Exception;



	String[] constructPhylotree(PhylotreeQueryParams params, String requestid);


	//VariantTable fillGenotypeTable(VariantTable table, VariantStringData data,
	//		GenotypeQueryParams params) throws Exception;




	//Map<Integer, Set<Character>> getMapIndex2NonsynAlleles();


	//Map<Integer, boolean[]> getMapIdx2Nonsynflags();
	
}
