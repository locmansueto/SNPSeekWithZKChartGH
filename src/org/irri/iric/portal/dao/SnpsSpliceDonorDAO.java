package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsSpliceDonor;
import org.springframework.dao.DataAccessException;

/**
 * Get splice-donor site SNPs
 * 
 * @author LMansueto
 *
 */
public interface SnpsSpliceDonorDAO {

	public Set<SnpsSpliceDonor> getSNPsBetween(String chr, Integer start, Integer end, Set variantset)
			throws DataAccessException;

	public Set<SnpsSpliceDonor> getSNPsIn(String chr, Collection listpos, Set variantset) throws DataAccessException;

	// public Set<SnpsSpliceDonor> getSNPsBetween(String chr, Integer start, Integer
	// end, BigDecimal dataset) throws DataAccessException;
	// public Set<SnpsSpliceDonor> getSNPsIn(String chr, Collection listpos,
	// BigDecimal dataset) throws DataAccessException;

	// public Set<SnpsSpliceDonor> getSNPsIn(String chr, Collection listpos) throws
	// DataAccessException;
	Set<SnpsSpliceDonor> getSNPsByFeatureidIn(Collection featureid) throws DataAccessException;

}
