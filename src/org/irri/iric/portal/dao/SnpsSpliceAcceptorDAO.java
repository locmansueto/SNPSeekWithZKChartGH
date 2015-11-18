package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;



import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.springframework.dao.DataAccessException;

/**
 * Get splice acceptor site SNPs
 * @author LMansueto
 *
 */
public interface SnpsSpliceAcceptorDAO {

	public Set<SnpsSpliceAcceptor> getSNPsIn(String chr, Collection listpos)  throws DataAccessException;
	public Set<SnpsSpliceAcceptor> getSNPsBetween(String chr, Integer start, Integer end) throws DataAccessException;

}
