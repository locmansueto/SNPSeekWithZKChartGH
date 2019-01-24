package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Interface for scaffold/contig
 * 
 * @author LMansueto
 *
 */
public interface Scaffold {

	/**
	 * DB Id
	 * 
	 * @return
	 */
	public BigDecimal getFeatureId();

	/**
	 * Scaffold length
	 * 
	 * @return
	 */
	public long getLength();

	/**
	 * scaffold/contig Name
	 * 
	 * @return
	 */
	public String getName();
}
