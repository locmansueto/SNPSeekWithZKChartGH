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

import org.irri.iric.portal.chado.domain.VSnpAllvars;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvars entities.
 * 
 */
@Repository("SnpsAllvarsDAO")
@Transactional
public class VSnpAllvarsDAOImpl extends AbstractJpaDao<VSnpAllvars> implements
		VSnpAllvarsDAO , SnpsAllvarsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpAllvars.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpAllvarsDAOImpl
	 *
	 */
	public VSnpAllvarsDAOImpl() {
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
	 * JPQL Query - findVSnpAllvarsByVarnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByVarnucContaining(String varnuc) throws DataAccessException {

		return findVSnpAllvarsByVarnucContaining(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByVarnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByVarnucContaining(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByVarnucContaining", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsByVar
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByVar(java.math.BigDecimal var) throws DataAccessException {

		return findVSnpAllvarsByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByVar
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByVar(java.math.BigDecimal var, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByVar", startResult, maxRows, var);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsBySnpGenotypeId
	 *
	 */
	@Transactional
	public VSnpAllvars findVSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId) throws DataAccessException {

		return findVSnpAllvarsBySnpGenotypeId(snpGenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsBySnpGenotypeId
	 *
	 */

	@Transactional
	public VSnpAllvars findVSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsBySnpGenotypeId", startResult, maxRows, snpGenotypeId);
			return (org.irri.iric.portal.chado.domain.VSnpAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVSnpAllvarss
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findAllVSnpAllvarss() throws DataAccessException {

		return findAllVSnpAllvarss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpAllvarss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findAllVSnpAllvarss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpAllvarss", startResult, maxRows);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsByVarnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByVarnuc(String varnuc) throws DataAccessException {

		return findVSnpAllvarsByVarnuc(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByVarnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByVarnuc(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByVarnuc", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsByPos
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findVSnpAllvarsByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	
	
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
	
	
	
	
	
	
	
	
	/**
	 * JPQL Query - findVSnpAllvarsByRefnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByRefnuc(String refnuc) throws DataAccessException {

		return findVSnpAllvarsByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpAllvars findVSnpAllvarsByPrimaryKey(Integer snpGenotypeId) throws DataAccessException {

		return findVSnpAllvarsByPrimaryKey(snpGenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpAllvars findVSnpAllvarsByPrimaryKey(Integer snpGenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsByPrimaryKey", startResult, maxRows, snpGenotypeId);
			return (org.irri.iric.portal.chado.domain.VSnpAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByRefnucContaining(String refnuc) throws DataAccessException {

		return findVSnpAllvarsByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsByChr
	 *
	 */
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByChr(Integer chr) throws DataAccessException {

		return findVSnpAllvarsByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars> findVSnpAllvarsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpAllvars>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpAllvars entity) {
		return true;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(
			Integer chr, BigDecimal start, BigDecimal end)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(
			Integer chr, BigDecimal start, BigDecimal end, int startResult,
			int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(
			Integer chr, BigDecimal start, BigDecimal end,
			Collection<BigDecimal> vars) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(
			Integer chr, BigDecimal start, BigDecimal end,
			Collection<BigDecimal> vars, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
}
