package org.irri.iric.portal.genotype.dao;

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

import org.hibernate.Session;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.genotype.domain.VlSnp2vars;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VlSnp2vars entities.
 * 
 */
@Repository("VlSnp2VarsDAO")
@Transactional
public class VlSnp2varsDAOImpl extends AbstractJpaDao<VlSnp2vars> implements
		VlSnp2varsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VlSnp2vars.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VlSnp2varsDAOImpl
	 *
	 */
	public VlSnp2varsDAOImpl() {
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
	 * JPQL Query - findVlSnp2varsByVar1nuc
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar1nuc(String var1nuc) throws DataAccessException {

		return findVlSnp2varsByVar1nuc(var1nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar1nuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar1nuc(String var1nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByVar1nuc", startResult, maxRows, var1nuc);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar2nucContaining
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar2nucContaining(String var2nuc) throws DataAccessException {

		return findVlSnp2varsByVar2nucContaining(var2nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar2nucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar2nucContaining(String var2nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByVar2nucContaining", startResult, maxRows, var2nuc);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByRefnuc
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByRefnuc(String refnuc) throws DataAccessException {

		return findVlSnp2varsByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar1
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar1(Integer var1) throws DataAccessException {

		return findVlSnp2varsByVar1(var1, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByVar1", startResult, maxRows, var1);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar2
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar2(Integer var2) throws DataAccessException {

		return findVlSnp2varsByVar2(var2, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByVar2", startResult, maxRows, var2);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVlSnp2varsBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar1nucContaining
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar1nucContaining(String var1nuc) throws DataAccessException {

		return findVlSnp2varsByVar1nucContaining(var1nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar1nucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar1nucContaining(String var1nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByVar1nucContaining", startResult, maxRows, var1nuc);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVlSnp2varss
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findAllVlSnp2varss() throws DataAccessException {

		return findAllVlSnp2varss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVlSnp2varss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findAllVlSnp2varss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVlSnp2varss", startResult, maxRows);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByRefnucContaining(String refnuc) throws DataAccessException {

		return findVlSnp2varsByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByPrimaryKey
	 *
	 */
	@Transactional
	public VlSnp2vars findVlSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId) throws DataAccessException {

		return findVlSnp2varsByPrimaryKey(var1, var2, snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByPrimaryKey
	 *
	 */

	@Transactional
	public VlSnp2vars findVlSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlSnp2varsByPrimaryKey", startResult, maxRows, var1, var2, snpFeatureId);
			return (org.irri.iric.portal.genotype.domain.VlSnp2vars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlSnp2varsByPos
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findVlSnp2varsByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar2nuc
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar2nuc(String var2nuc) throws DataAccessException {

		return findVlSnp2varsByVar2nuc(var2nuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByVar2nuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByVar2nuc(String var2nuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByVar2nuc", startResult, maxRows, var2nuc);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsByChr
	 *
	 */
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByChr(Integer chr) throws DataAccessException {

		return findVlSnp2varsByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2vars> findVlSnp2varsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VlSnp2vars>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VlSnp2vars entity) {
		return true;
	}
	
	
	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}

	public List executeSQL(String sql) {
		System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VlSnp2vars.class).list();
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnp2varsByVarsChrPosBetweenAll(chr,
				 start,  end,  var1,  var2,
				 -1, -1);
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		String sql = "select VAR1, VAR2, SNP_FEATURE_ID, CHR, POS, REFNUC, VAR1NUC, VAR2NUC from VL_SNP_2VARS where ";
		
		if(var1!=null)
		{
			sql += " var1=" + var1;
			if(var2!=null)
				sql += " and var2=" + var2 ;
		} else
		{
			if(var2!=null)
				sql += " var2=" + var2 ;				
		}
			 
		  sql += " and chr=" + chr + " and pos between " +
				start.intValue() + " and " + end.intValue();
		  sql += " and (REFNUC<>VAR1NUC or REFNUC<>VAR2NUC) ";
		
		 return new java.util.LinkedHashSet<Snps2Vars>(executeSQL(sql));
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
		return findVSnp2varsByVarsChrPosBetweenMismatch( chr,
				 start,  end, var1,  var2,
				 -1, -1);
		
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		String sql = "select VAR1, VAR2, SNP_FEATURE_ID, CHR, POS, REFNUC, VAR1NUC, VAR2NUC from VL_SNP_2VARS where ";
		
		//System.out.printl(b);
		if(var1!=null)
		{
			sql += " var1=" + var1 ;
			if(var2!=null)
				sql += " and var2=" + var2;
		} else
		{
			if(var2!=null)
				sql += " var2=" + var2;				
		}
			 
		  sql += " and chr=" + chr + " and pos between " +
				start.intValue() + " and " + end.intValue() ;
  		  sql += " and VAR1NUC<>VAR2NUC ";
		
		return new java.util.LinkedHashSet<Snps2Vars>(executeSQL(sql));
	}
	
	
}
