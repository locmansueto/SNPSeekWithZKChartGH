package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Container entity for SNP-Genotype data
 * @author lmansueto
 *
 */
public interface SnpsAllvars extends Position {
	
	/**
	 * Variety Id
	 * @return
	 */
	public BigDecimal getVar();
//	
//	/**
//	 * Chromosome
//	 * @return
//	 */
//	public Long getChr();
//	
//	/**
//	 * Position
//	 * @return
//	 */
//	public BigDecimal getPos();
	
//	/**
//	 * Reference base
//	 * @return
//	 */
//	public String getRefnuc();
	
	/**
	 * Variety allele at the position
	 * @return
	 */
	public String getVarnuc();

	
//	public String getContig();
	
}
