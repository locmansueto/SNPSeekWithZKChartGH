package org.irri.iric.portal.chado.dao;

import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpInExon;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpInExon entities.
 * 
 */
public interface VSnpInExonDAO extends JpaDao<VSnpInExon> {

	/**
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */
	public VSnpInExon findVSnpInExonByPrimaryKey(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */
	public VSnpInExon findVSnpInExonByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */
	public VSnpInExon findVSnpInExonBySnpFeatureId(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */
	public VSnpInExon findVSnpInExonBySnpFeatureId(Integer snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

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