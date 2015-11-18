package org.irri.iric.portal.dao;

import java.math.BigDecimal;


import java.util.Set;

public interface CvTermUniqueValuesDAO {

		/**
		 * Unique values of cvterm 
		 * @param typeId
		 * @return
		 */
		public Set getUniqueValues(BigDecimal typeId);

}
