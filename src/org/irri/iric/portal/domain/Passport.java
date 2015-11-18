package org.irri.iric.portal.domain;

/**
 * Interface for passport values
 * @author LMansueto
 *
 */
public interface Passport {

	/**
	 * Passport definition
	 * @return
	 */
	public String getDefinition();
	
	/**
	 * Passport name/field
	 * @return
	 */
	public String getName();
	
	/**
	 * Passport value
	 * @return
	 */
	public String getValue();
}
