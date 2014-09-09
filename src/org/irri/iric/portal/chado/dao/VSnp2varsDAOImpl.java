package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnp2vars;
import org.irri.iric.portal.domain.Snps2Vars;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnp2vars entities.
 * 
 */
@Repository("Snps2VarsDAO")
@Transactional
public class VSnp2varsDAOImpl extends AbstractJpaDao<VSnp2vars> implements
		VSnp2varsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnp2vars.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnp2varsDAOImpl
	 *
	 */
	public VSnp2varsDAOImpl() {
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
	 * JPQL Query - findVSnp2varsByVar2nucContaining
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar2nucContaining(String var2nuc) throws DataAccessException {

		return findVSnp2varsByVar2nucContaining(var2nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByVar2nucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar2nucContaining(String var2nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByVar2nucContaining", startResult, maxRows, var2nuc);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByPos
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findVSnp2varsByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	
	
	/*
	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr,
			BigDecimal start, BigDecimal end, BigDecimal var1Id,
			BigDecimal var2Id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1, BigDecimal var2) throws DataAccessException {
		return findVSnp2varsByVarsChrPosBetweenAll( chr,  start,   end, var1, var2,  -1, -1);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1, BigDecimal var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByVarsChrPosBetweenAll", startResult, maxRows,  chr, start, end,  var1, var2);
		return new LinkedHashSet<Snps2Vars>(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1, BigDecimal var2) throws DataAccessException {
		return findVSnp2varsByVarsChrPosBetweenMismatch( chr,  start,   end, var1, var2,  -1, -1);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1, BigDecimal var2, int startResult, int maxRows) throws DataAccessException {
		/*
		if( chr == 3305729 ) System.out.println("chr=3305729");
		if( var1 == 3305729 ) System.out.println("var1=3305729");
		if( var2 == 3305729 ) System.out.println("var2=3305729");
		if( start.intValueExact() == 3305729 ) System.out.println("start=3305729");
		if( end.intValueExact() == 3305729 ) System.out.println("end=3305729");
		*/	
		
		Query query = createNamedQuery("findVSnp2varsByVarsChrPosBetweenAll", startResult, maxRows,  chr, start, end,   var1,  var2);
		//Query query = createNamedQuery("findVSnp2varsByVarsChrPosBetweenAll", startResult, maxRows,  chr, start, end,   var1,   var2);
		return new LinkedHashSet<Snps2Vars>(query.getResultList());
	}
	
	
	
	
	
	
	
	
	/**
	 * JPQL Query - findVSnp2varsByVar1nuc
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar1nuc(String var1nuc) throws DataAccessException {

		return findVSnp2varsByVar1nuc(var1nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByVar1nuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar1nuc(String var1nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByVar1nuc", startResult, maxRows, var1nuc);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByVar2nuc
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar2nuc(String var2nuc) throws DataAccessException {

		return findVSnp2varsByVar2nuc(var2nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByVar2nuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar2nuc(String var2nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByVar2nuc", startResult, maxRows, var2nuc);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnp2varsBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByVar2
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar2(Integer var2) throws DataAccessException {

		return findVSnp2varsByVar2(var2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByVar2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByVar2", startResult, maxRows, var2);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByPrimaryKey
	 *
	 */
	@Transactional
	public VSnp2vars findVSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId) throws DataAccessException {

		return findVSnp2varsByPrimaryKey(var1, var2, snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByPrimaryKey
	 *
	 */

	@Transactional
	public VSnp2vars findVSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnp2varsByPrimaryKey", startResult, maxRows, var1, var2, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnp2vars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnp2varsByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByRefnucContaining(String refnuc) throws DataAccessException {

		return findVSnp2varsByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByChr
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByChr(Integer chr) throws DataAccessException {

		return findVSnp2varsByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByVar1
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar1(Integer var1) throws DataAccessException {

		return findVSnp2varsByVar1(var1, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByVar1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByVar1", startResult, maxRows, var1);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByRefnuc
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByRefnuc(String refnuc) throws DataAccessException {

		return findVSnp2varsByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnp2varss
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findAllVSnp2varss() throws DataAccessException {

		return findAllVSnp2varss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnp2varss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findAllVSnp2varss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnp2varss", startResult, maxRows);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsByVar1nucContaining
	 *
	 */
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar1nucContaining(String var1nuc) throws DataAccessException {

		return findVSnp2varsByVar1nucContaining(var1nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsByVar1nucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2vars> findVSnp2varsByVar1nucContaining(String var1nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsByVar1nucContaining", startResult, maxRows, var1nuc);
		return new LinkedHashSet<VSnp2vars>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnp2vars entity) {
		return true;
	}

	/*
	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr,
			BigDecimal start, BigDecimal end, BigDecimal var1Id,
			BigDecimal var2Id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr,
			BigDecimal start, BigDecimal end, BigDecimal var1Id,
			BigDecimal var2Id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr,
			BigDecimal start, BigDecimal end, BigDecimal var1, BigDecimal var2,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
