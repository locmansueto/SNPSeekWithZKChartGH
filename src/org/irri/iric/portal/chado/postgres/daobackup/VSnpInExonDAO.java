package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VSnpInExon;
import org.irri.iric.portal.dao.SnpsInExonDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpInExon entities.
 * 
 */
public interface VSnpInExonDAO extends JpaDao<VSnpInExon>, SnpsInExonDAO {

	/**
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */
	public VSnpInExon findVSnpInExonByPrimaryKey(Long snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */
	public VSnpInExon findVSnpInExonByPrimaryKey(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */
	public VSnpInExon findVSnpInExonBySnpFeatureId(Long snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */
	public VSnpInExon findVSnpInExonBySnpFeatureId(Long snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpInExons
	 *
	 */
	public Set<VSnpInExon> findAllVSnpInExons() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpInExons
	 *
	 */
	public Set<VSnpInExon> findAllVSnpInExons(int startResult, int maxRows) throws DataAccessException;

}