package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.Snps2VarsCountmismatch;

public interface Snps2VarsCountMismatchDAO {
	
	/**
	 * Count number of allele nismatches between 2 varieties within region
	 * @param chr	chromosome
	 * @param start	region start
	 * @param end	region end
	 * @return
	 */
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end);
	
	/**
	 * Count number of allele nismatches between 2 varieties within region.
	 * Returning only the top N largest distance pairs
	 * @param chr	chromosome
	 * @param start	region start
	 * @param end	region end
	 * @param topN
	 * @return
	 */
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, int topN);
	
	/**
	 * Count number of allele nismatches between 2 varieties within region.
	 * Returning only varieties in Set of Variety IDs
	 * @param chr	chromosome
	 * @param start	region start
	 * @param end	region end
	 * @param varieties
	 * @return
	 */
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, Set<BigDecimal> varieties);
	
	/**
	 * Count number of allele nismatches between 2 varieties within region.
	 * Returning only the top N largest distance pairs and only varieties in Set of Variety IDs
	 * @param chr	chromosome
	 * @param start	region start
	 * @param end	region end
	 * @param topN
	 * @return
	 */
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, int topN, Set<BigDecimal> varieties);

	List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start,
			BigDecimal end, int topN, Set<BigDecimal> varieties, boolean isCore);
	
	
}
