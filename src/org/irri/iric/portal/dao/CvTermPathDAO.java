package org.irri.iric.portal.dao;

import java.util.List;

public interface CvTermPathDAO extends CvTermUniqueValuesDAO {
	List getAncestors(String cv, String term);
	List getDescendants(String cv, String term);

}
