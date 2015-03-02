package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;


import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.springframework.dao.DataAccessException;

public interface SnpsSpliceAcceptorDAO {

	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosBetween(Integer chr, Integer start, Integer end) throws DataAccessException;
	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosIn(Integer chr, Collection listpos)  throws DataAccessException;

}
