package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Locus;

/**
 * API functions used mostly by SNP Query Controller
 * @author LMansueto
 *
 */

public interface GenotypeFacade {

	
	/**
	 * Query mode
	 * SNPQUERY_VARIETIES	query and compare two varieties
	 * SNPQUERY_ALLREFPOS  all snp positions in the reference
	 * SNPQUERY_ALLVARIETIESPOS	query for all varieties
	 * @author lmansueto
	 *
	 */
	public static enum snpQueryMode { SNPQUERY_VARIETIES,  SNPQUERY_ALLVARIETIESPOS, } //SNPQUERY_ALLREFPOS,  }


	public static final String strBlank = "";
	public static final String strGap = "-";
	public static final String strN = "N";
	public static final String strMissing = "?";; 

	
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
	public Object[] constructPhylotree(String scale, String chr, int start, int end, String requestid);
	Object[] constructPhylotree(PhylotreeQueryParams params, String requestid);

	
	/**
	 * Get sorted variety based on the phylogenetic tree result
	 * @param tmpfile
	 * @param newick
	 * @return
	 */
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile, String newick);
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


	/**
	 * Get the indel type (reference, insertion, deletion, snp) for the given allele format
	 * @param allele
	 * @return
	 */
	String getIndelType(String allele);


	/**
	 * Query using GenotypeQueryParams class
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantStringData queryGenotype(GenotypeQueryParams params) throws Exception;
	

	/**
	 * Download the genotype (SNP/Indels) for the entire genome 
	 * @param params
	 * @throws Exception
	 */
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

	/**
	 * Get list of reference genomes
	 * @return
	 * @throws Exception
	 */
	public List getReferenceGenomes() throws Exception;
	
	/**
	 * Get contigs for reference
	 * @param reference
	 * @return
	 */
	public List getContigsForReference(String reference);
	
	/**
	 * Get length of sequence feature in organism
	 * @param feature
	 * @param organism
	 * @return
	 */
	Integer getFeatureLength(String feature, String organism);
	
	/**
	 * Get gene loci for reference
	 * @param organism
	 * @return
	 */
	public Collection getLociForReference(String organism);

	/**
	 * Query genotype for two varieties
	 * @param var1
	 * @param var2
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantStringData compare2Varieties(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params) throws Exception;


	/**
	 * Download FASTA generated from aligned SNP/Indel data, and reference sequence 
	 * @param param
	 * @param loc
	 * @param locusfilename
	 * @throws Exception
	 */
	void downloadFastaMSAPerLocus(GenotypeQueryParams param, Locus loc, String locusfilename) throws Exception;

	
}
