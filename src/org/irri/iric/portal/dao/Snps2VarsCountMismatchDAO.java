package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.Snps2VarsCountmismatch;

public interface Snps2VarsCountMismatchDAO {
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end);
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, int topN);
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, Set varieties);
	
}
