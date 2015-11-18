package org.irri.iric.portal.dao;

import java.util.List;

public interface CvTermPathDAO extends CvTermUniqueValuesDAO {
	/**
	 * Ancestors of cv term
	 * @param cv
	 * @param term
	 * @return
	 */
	List getAncestors(String cv, String term);
	
	
	/**
	 * Decendants of cv tern
	 * @param cv
	 * @param term
	 * @return
	 */
	List getDescendants(String cv, String term);

}
