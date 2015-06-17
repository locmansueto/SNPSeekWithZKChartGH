package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface CvTermLocusCountDAO {

	public List getCvTermLocusCount(BigDecimal orgId, Collection loci, String cv);
}
