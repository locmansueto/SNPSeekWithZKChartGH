package org.irri.iric.portal.genotype.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.genotype.domain.VlSnpAllvars;
import org.irri.iric.portal.genotype.views.ViewCount2linesMismatchId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsId;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VlSnpAllvars entities.
 * 
 */
@Repository("VlSnpAllvarsDAO")
@Transactional
public class VlSnpAllvarsDAOImpl extends AbstractJpaDao<VlSnpAllvars> implements
		VlSnpAllvarsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VlSnpAllvars.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;


	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
	
	/**
	 * Instantiates a new VlSnpAllvarsDAOImpl
	 *
	 */
	public VlSnpAllvarsDAOImpl() {
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
	 * JPQL Query - findVlSnpAllvarsByRefnuc
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnuc(String refnuc) throws DataAccessException {

		return findVlSnpAllvarsByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnuc
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnuc(String varnuc) throws DataAccessException {

		return findVlSnpAllvarsByVarnuc(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnuc(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsByVarnuc", startResult, maxRows, varnuc);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByChr
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByChr(Integer chr) throws DataAccessException {

		return findVlSnpAllvarsByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpAllvarsBySnpGenotypeId
	 *
	 */
	@Transactional
	public VlSnpAllvars findVlSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId) throws DataAccessException {

		return findVlSnpAllvarsBySnpGenotypeId(snpGenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsBySnpGenotypeId
	 *
	 */

	@Transactional
	public VlSnpAllvars findVlSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlSnpAllvarsBySnpGenotypeId", startResult, maxRows, snpGenotypeId);
			return (org.irri.iric.portal.genotype.domain.VlSnpAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByPrimaryKey
	 *
	 */
	@Transactional
	public VlSnpAllvars findVlSnpAllvarsByPrimaryKey(Integer snpGenotypeId) throws DataAccessException {

		return findVlSnpAllvarsByPrimaryKey(snpGenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByPrimaryKey
	 *
	 */

	@Transactional
	public VlSnpAllvars findVlSnpAllvarsByPrimaryKey(Integer snpGenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlSnpAllvarsByPrimaryKey", startResult, maxRows, snpGenotypeId);
			return (org.irri.iric.portal.genotype.domain.VlSnpAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnucContaining(String refnuc) throws DataAccessException {

		return findVlSnpAllvarsByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByVar
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByVar(java.math.BigDecimal var) throws DataAccessException {

		return findVlSnpAllvarsByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByVar
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByVar(java.math.BigDecimal var, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsByVar", startResult, maxRows, var);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnucContaining
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnucContaining(String varnuc) throws DataAccessException {

		return findVlSnpAllvarsByVarnucContaining(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnucContaining(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsByVarnucContaining", startResult, maxRows, varnuc);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByPos
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findVlSnpAllvarsByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findVlSnpAllvarsByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVlSnpAllvarss
	 *
	 */
	@Transactional
	public Set<VlSnpAllvars> findAllVlSnpAllvarss() throws DataAccessException {

		return findAllVlSnpAllvarss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVlSnpAllvarss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvars> findAllVlSnpAllvarss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVlSnpAllvarss", startResult, maxRows);
		return new LinkedHashSet<VlSnpAllvars>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VlSnpAllvars entity) {
		return true;
	}

	

	
	public List executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VlSnpAllvars.class).list();
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end) throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByChrPosBetween( chr,
				start, end,-1,-1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
		String sql = "select SNP_GENOTYPE_ID, VAR, CHR, POS, REFNUC, VARNUC from VL_SNP_ALLVARS where " // + onlymismatchsql
				+ " chr=" + chr + " and pos between " +
				start.intValue() + " and " + end.intValue()  + " order by var, CHR, POS" ;	
		
		return new java.util.LinkedHashSet<SnpsAllvars>(executeSQL(sql));
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<Integer> vars)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetween( chr,
				 start,  end,  vars,-1,-1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<Integer> vars,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		
		StringBuffer topVarieties= new StringBuffer();
		Iterator<Integer> itVars = vars.iterator();
		while(itVars.hasNext()) {
			topVarieties.append(itVars);
			if(itVars.hasNext())
				topVarieties.append(",");
		}
		
		String sql = "SELECT SNP_GENOTYPE_ID, var, chr, pos, refnuc, varnuc  FROM VL_SNP_ALLVARS  where " //+ onlymismatchsql 
		+ " chr="
		+ chr + " and pos between " + start.intValue() + " and " + end.intValue() + 
		" and var in ("  + topVarieties.toString()
		+  ") order by var, chr, pos" ;
		
		return new java.util.LinkedHashSet<SnpsAllvars>(executeSQL(sql));
	}

}
