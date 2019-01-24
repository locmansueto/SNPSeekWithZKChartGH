package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface CvTermLocusCountDAO {

	/**
	 * Count number of loci for CV term in organism
	 * 
	 * @param orgId
	 * @param loci
	 * @param cv
	 * @return
	 */
	public List getCvTermLocusCount(BigDecimal orgId, Collection loci, String cv);
}
