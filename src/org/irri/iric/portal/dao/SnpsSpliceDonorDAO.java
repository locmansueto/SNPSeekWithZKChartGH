package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsSpliceDonor;
import org.springframework.dao.DataAccessException;

public interface SnpsSpliceDonorDAO {
	public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosBetween(Integer chr, Integer start, Integer end) throws DataAccessException;
	public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosIn(Integer chr, Collection listpos)  throws DataAccessException;

}
