package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface CvTermUniqueValuesDAO {
	
		public Set getUniqueValues(BigDecimal typeId);
	
}
