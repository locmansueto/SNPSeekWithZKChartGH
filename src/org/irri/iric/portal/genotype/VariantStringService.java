package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;





/**
 * 
 * @author LMansueto
 *
 */
public interface VariantStringService {

	
	public static BigDecimal bd100 =  BigDecimal.valueOf(100);
	public static final int INDELTYPE_INSERTION=1;
	public static final int INDELTYPE_DELETION=2;
	public static final int INDELTYPE_SNP=3;
	public static final int INDELTYPE_REFERENCE=4;
	public static final int INDELTYPE_SUBSTITUTION=5;
	public static final int INDELTYPE_MISSING=6;
	public static final int INDELTYPE_EXTENDDELETION=7;
	public static final String INDEL_GAP="-"; // "&#151;";
	public static final String INDEL_REFCONSENSUS= "*";
	

	/**
	 * Get genotype data
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantStringData getVariantString(GenotypeQueryParams params) throws Exception;

	/**
	 * Check if positions have variants
	 * @param chr
	 * @param posset
	 * @param type
	 * @return positions with variant
	 */
	List checkSNPsInChromosome(String chr, Collection posset, BigDecimal type);
	
	
}