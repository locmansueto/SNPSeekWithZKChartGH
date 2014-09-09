package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnpAllvarsMin;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvarsMin entities.
 * 
 */
@Repository("VSnpAllvarsMinDAO")
@Transactional
public class VSnpAllvarsMinDAOImpl extends AbstractJpaDao<VSnpAllvarsMin>
		implements VSnpAllvarsMinDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpAllvarsMin.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpAllvarsMinDAOImpl
	 *
	 */
	public VSnpAllvarsMinDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpAllvarsMin findVSnpAllvarsMinByPrimaryKey(Integer var, Integer pos, Integer chr) throws DataAccessException {

		return findVSnpAllvarsMinByPrimaryKey(var, pos, chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpAllvarsMin findVSnpAllvarsMinByPrimaryKey(Integer var, Integer pos, Integer chr, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsMinByPrimaryKey", startResult, maxRows, var, pos, chr);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsMin) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByPos
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByPos(Integer pos) throws DataAccessException {

		return findVSnpAllvarsMinByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByPos(Integer pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnuc(String varnuc) throws DataAccessException {

		return findVSnpAllvarsMinByVarnuc(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnuc(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByVarnuc", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnucContaining(String varnuc) throws DataAccessException {

		return findVSnpAllvarsMinByVarnucContaining(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnucContaining(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByVarnucContaining", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVar
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVar(Integer var) throws DataAccessException {

		return findVSnpAllvarsMinByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVar
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVar(Integer var, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByVar", startResult, maxRows, var);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpAllvarsMins
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findAllVSnpAllvarsMins() throws DataAccessException {

		return findAllVSnpAllvarsMins(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpAllvarsMins
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findAllVSnpAllvarsMins(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpAllvarsMins", startResult, maxRows);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnuc(String refnuc) throws DataAccessException {

		return findVSnpAllvarsMinByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByChr
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByChr(Integer chr) throws DataAccessException {

		return findVSnpAllvarsMinByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnucContaining(String refnuc) throws DataAccessException {

		return findVSnpAllvarsMinByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpAllvarsMin entity) {
		return true;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end) throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByChrPosBetween( chr, start,  end , -1, -1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpAllvarsMinByChrPosBetween", startResult, maxRows, new Long(chr), start, end);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetween( chr,  start, end ,  vars, -1, -1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetween", startResult, maxRows, new Long(chr), start, end, vars);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}
	
	
	
	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end) throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByChrPosBetweenRefmismatch( chr, start,  end , -1, -1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpAllvarsMinByChrPosBetweenRefmismatch", startResult, maxRows, new Long(chr), start, end);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetweenRefmismatch( chr,  start, end ,  vars, -1, -1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch", startResult, maxRows, new Long(chr), start, end, vars);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}
	
	
	
	
	/*
	 * 
	 * 
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException {
		return findVSnpAllvarsByChrPosBetween( chr, start,  end , -1, -1);
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByChrPosBetween", startResult, maxRows, new Long(chr), start, end);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}

	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars) throws DataAccessException {
		return findVSnpAllvarsByVarsChrPosBetween( chr,  start, end ,  vars, -1, -1);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByVarsChrPosBetween", startResult, maxRows, new Long(chr), start, end, vars);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}
	 */
	
}
