package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.Collection;
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
	
	
	// Query for Variety instances from constraints
	
	/**
	 * Gets varieties in a subpopulation
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
	public Gene getGeneFromName(String name, String organism);
	

	
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
	
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile);

	// Query for MyList definition
	/**
	 * Verify the snps in setSNP and return only the valid ones  
	 * @param chr
	 * @param setSNP
	 * @param type
	 * @return
	 */
	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type);


	String getIndelType(String allele);


	/**
	 * Query using GenotypeQueryParams class
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantStringData queryGenotype(GenotypeQueryParams params) throws Exception;
	
	//List<VariantStringData> queryGenotype(List listParams) throws Exception;
	void downloadGenotypeGenome(GenotypeQueryParams params) throws Exception;


	/**
	 * Populate the variant table VariantTable with query result VariantStringData
	 * @param table
	 * @param data
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantTable fillGenotypeTable(VariantTable table, VariantStringData data,
			GenotypeQueryParams params) throws Exception;


	//VariantStringData compare2Varieties(BigDecimal var1, BigDecimal var2,
	//		GenotypeQueryParams params) throws Exception;

	
	String[] constructPhylotree(PhylotreeQueryParams params, String requestid);

	public List getReferenceGenomes() throws Exception;
	public List getContigsForReference(String reference);
	Integer getFeatureLength(String feature, String organism);
	public Collection getLociForReference(String organism);

	VariantStringData compare2Varieties(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params) throws Exception;

	
}
