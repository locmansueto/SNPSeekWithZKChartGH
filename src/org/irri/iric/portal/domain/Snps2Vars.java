package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Set;


/**
 * SNP-Genotype between 2 varieties
 * @author lmansueto
 *
 */
public interface Snps2Vars {

	/**
	 * Variety1 Id
	 * @return
	 */
	public BigDecimal getVar1();
	
	/**
	 * Variety2 Id
	 * @return
	 */
	public BigDecimal getVar2();
	
	/**
	 * SNP_FEATURE_ID
	 * @return
	 */
	public BigDecimal getSnpFeatureId();
	
	/**
	 * Chromosome number
	 * @return
	 */
	public Integer getChr();
	
	/**
	 * SNP position
	 * @return
	 */
	public BigDecimal getPos();
	
	/**
	 * Reference base
	 * @return
	 */
	public String getRefnuc();
	
	/**
	 * Variety1 allele
	 * @return
	 */
	public String getVar1nuc();
	
	/**
	 * Variety2 allele
	 * @return
	 */
	public String getVar2nuc();

	/**
	 * Variety1 allele
	 * @return
	 */
	public String getVar1nuc2();
	
	/**
	 * Variety2 allele
	 * @return
	 */
	public String getVar2nuc2();
	
	public boolean isVar1Nonsyn();
	public boolean isVar2Nonsyn();
	
}
