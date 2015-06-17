package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpNonsynAllele;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.springframework.dao.DataAccessException;

public interface SnpsNonsynAllvarsDAO {
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(String chr, Integer start, Integer end) throws DataAccessException;

	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(String chr, Collection listpos)  throws DataAccessException;
}
