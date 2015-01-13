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

import org.irri.iric.portal.chado.domain.VIndelAllvars;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIndelAllvars entities.
 * 
 */
@Repository("IndelAllvarsDAO")
@Transactional
public class VIndelAllvarsDAOImpl extends AbstractJpaDao<VIndelAllvars>
		implements VIndelAllvarsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIndelAllvars.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIndelAllvarsDAOImpl
	 *
	 */
	public VIndelAllvarsDAOImpl() {
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
	 * JPQL Query - findVIndelAllvarsByVar
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByVar(java.math.BigDecimal var) throws DataAccessException {

		return findVIndelAllvarsByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByVar
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByVar(java.math.BigDecimal var, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByVar", startResult, maxRows, var);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByIndelCallId
	 *
	 */
	@Transactional
	public VIndelAllvars findVIndelAllvarsByIndelCallId(Integer indelCallId) throws DataAccessException {

		return findVIndelAllvarsByIndelCallId(indelCallId, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByIndelCallId
	 *
	 */

	@Transactional
	public VIndelAllvars findVIndelAllvarsByIndelCallId(Integer indelCallId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIndelAllvarsByIndelCallId", startResult, maxRows, indelCallId);
			return (org.irri.iric.portal.chado.domain.VIndelAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPos
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPos(Integer pos) throws DataAccessException {

		return findVIndelAllvarsByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPos(Integer pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele1Id
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele1Id(Integer allele1Id) throws DataAccessException {

		return findVIndelAllvarsByAllele1Id(allele1Id, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele1Id
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele1Id(Integer allele1Id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByAllele1Id", startResult, maxRows, allele1Id);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIndelAllvarss
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findAllVIndelAllvarss() throws DataAccessException {

		return findAllVIndelAllvarss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIndelAllvarss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findAllVIndelAllvarss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIndelAllvarss", startResult, maxRows);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPrimaryKey
	 *
	 */
	@Transactional
	public VIndelAllvars findVIndelAllvarsByPrimaryKey(Integer indelCallId) throws DataAccessException {

		return findVIndelAllvarsByPrimaryKey(indelCallId, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPrimaryKey
	 *
	 */

	@Transactional
	public VIndelAllvars findVIndelAllvarsByPrimaryKey(Integer indelCallId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIndelAllvarsByPrimaryKey", startResult, maxRows, indelCallId);
			return (org.irri.iric.portal.chado.domain.VIndelAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPartitionId
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPartitionId(java.math.BigDecimal partitionId) throws DataAccessException {

		return findVIndelAllvarsByPartitionId(partitionId, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPartitionId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPartitionId(java.math.BigDecimal partitionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByPartitionId", startResult, maxRows, partitionId);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele2Id
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele2Id(Integer allele2Id) throws DataAccessException {

		return findVIndelAllvarsByAllele2Id(allele2Id, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele2Id
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele2Id(Integer allele2Id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByAllele2Id", startResult, maxRows, allele2Id);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIndelAllvars entity) {
		return true;
	}

	@Override
	public Set<IndelsAllvars> getAllIndelCalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Set findIndelAllvarsByChrPosBetween(Integer chr, BigDecimal start,
			BigDecimal end) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVIndelAllvarsByChrPosBetween", -1, -1, BigDecimal.valueOf(chr+2), start, end );
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
		
	}

	@Override
	public Set findIndelAllvarsByVarChrPosBetween(Collection varids,
			Integer chr, BigDecimal start, BigDecimal end) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVIndelAllvarsByVarChrPosBetween", -1, -1, BigDecimal.valueOf(chr+2), start, end, varids );
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	@Override
	public Set findIndelAllvarsByVarChrPosIn(Collection varList, Integer chr,
			Collection posList) {
		Query query = createNamedQuery("findVIndelAllvarsByVarChrPosIn", -1, -1, BigDecimal.valueOf(chr+2), varList, posList );
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	@Override
	public Set findIndelAllvarsByChrPosIn(Integer chr, Collection posList) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVIndelAllvarsByChrPosIn", -1, -1, BigDecimal.valueOf(chr+2), posList );
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}
	
	
	
	
	
}
