package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.chado.domain.VSnpAllvarsPos;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvarsPos entities.
 * 
 */
@Repository("SnpsAllvarsPosDAO")
@Transactional
public class VSnpAllvarsPosDAOImpl extends AbstractJpaDao<VSnpAllvarsPos>
		implements VSnpAllvarsPosDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpAllvarsPos.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpAllvarsPosDAOImpl
	 *
	 */
	public VSnpAllvarsPosDAOImpl() {
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
	 * JPQL Query - findAllVSnpAllvarsPoss
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsPos> findAllVSnpAllvarsPoss() throws DataAccessException {

		return findAllVSnpAllvarsPoss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpAllvarsPoss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findAllVSnpAllvarsPoss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpAllvarsPoss", startResult, maxRows);
		return new LinkedHashSet<VSnpAllvarsPos>(query.getResultList());
	}

	
	
	
	
	/**
	 * JPQL Query - findVSnpAllvarsPosBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpAllvarsPos findVSnpAllvarsPosBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpAllvarsPosBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpAllvarsPos findVSnpAllvarsPosBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsPosBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsPos) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnucContaining(String refnuc) throws DataAccessException {

		return findVSnpAllvarsPosByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsPosByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvarsPos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpAllvarsPos findVSnpAllvarsPosByPrimaryKey(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpAllvarsPosByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpAllvarsPos findVSnpAllvarsPosByPrimaryKey(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsPosByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsPos) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByChr
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChr(Integer chr) throws DataAccessException {

		return findVSnpAllvarsPosByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsPosByChr", startResult, maxRows, "Chr" + chr.toString());
		return new LinkedHashSet<VSnpAllvarsPos>(query.getResultList());
	}


	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException {
		return  findVSnpAllvarsPosByChrPosBetween(chr,start,end, -1,-1); 
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end, int startResult, int maxRows) throws DataAccessException {
		
	//	System.out.println(chr + "  " +  chr.TYPE);
		Query query = createNamedQuery("findVSnpAllvarsPosByChrPosBetween", startResult, maxRows, "Chr" + chr , start, end);
		return new LinkedHashSet<VSnpAllvarsPos>(query.getResultList());
	}
	
	
	
	
	
	
	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnuc(String refnuc) throws DataAccessException {

		return findVSnpAllvarsPosByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsPosByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvarsPos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByPos
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findVSnpAllvarsPosByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsPosByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsPosByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VSnpAllvarsPos>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpAllvarsPos entity) {
		return true;
	}

	
	
	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type,
			int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type) {
		// TODO Auto-generated method stub
		List list = new java.util.ArrayList();
		
		list.addAll(findVSnpAllvarsPosByChrPosBetween(Integer.valueOf(chromosome), new BigDecimal(startPos), new BigDecimal(endPos)));
		
		return list;
		
				/*
		 String sql = "select sf.snp_feature_id, to_number(replace(upper(f.name),'CHR','')) as chr,  sfl.position as pos,  sf.refcall as refnuc " +
		 "from snp_feature sf, snp_featureloc sfl, feature f " + 
		 "where sf.snp_feature_id=sfl.snp_feature_id " +
		 "and f.feature_id=sfl.srcfeature_id " +
		 "and f.name='CHR" + chromosome + "' " +
		 " and sfl.position between " + startPos + " and " + endPos;
		 
		
		return executeSQL(sql); 
		*/
	}
	
	private List<Snps2VarsCountmismatch> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VSnpAllvarsPos.class).list();
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
