package org.irri.iric.portal.genotype.dao;

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
import org.irri.iric.portal.genotype.domain.VlSnpPos;

import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VlSnpPos entities.
 * 
 */
@Repository("VlSnpPosDAO")
@Transactional
public class VlSnpPosDAOImpl extends AbstractJpaDao<VlSnpPos> implements
		VlSnpPosDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VlSnpPos.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VlSnpPosDAOImpl
	 *
	 */
	public VlSnpPosDAOImpl() {
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
	 * JPQL Query - findVlSnpPosByPos
	 *
	 */
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByPos(Integer pos) throws DataAccessException {

		return findVlSnpPosByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpPosByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByPos(Integer pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpPosByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VlSnpPos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpPosByRefnuc
	 *
	 */
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByRefnuc(String refnuc) throws DataAccessException {

		return findVlSnpPosByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpPosByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpPosByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VlSnpPos>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVlSnpPoss
	 *
	 */
	@Transactional
	public Set<VlSnpPos> findAllVlSnpPoss() throws DataAccessException {

		return findAllVlSnpPoss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVlSnpPoss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpPos> findAllVlSnpPoss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVlSnpPoss", startResult, maxRows);
		return new LinkedHashSet<VlSnpPos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpPosByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByRefnucContaining(String refnuc) throws DataAccessException {

		return findVlSnpPosByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpPosByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpPosByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VlSnpPos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnpPosByPrimaryKey
	 *
	 */
	@Transactional
	public VlSnpPos findVlSnpPosByPrimaryKey(Integer chr, Integer pos) throws DataAccessException {

		return findVlSnpPosByPrimaryKey(chr, pos, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpPosByPrimaryKey
	 *
	 */

	@Transactional
	public VlSnpPos findVlSnpPosByPrimaryKey(Integer chr, Integer pos, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlSnpPosByPrimaryKey", startResult, maxRows, chr, pos);
			return (org.irri.iric.portal.genotype.domain.VlSnpPos) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlSnpPosByChr
	 *
	 */
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByChr(Integer chr) throws DataAccessException {

		return findVlSnpPosByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpPosByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpPos> findVlSnpPosByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpPosByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VlSnpPos>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VlSnpPos entity) {
		return true;
	}
	
	

	public List executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VlSnpPos.class).list();
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos) {
		// TODO Auto-generated method stub
		
		
		String sql = "select CHR, POS, REFNUC from VL_SNP_POS where ";
		  sql += " chr=" + chromosome + " and pos between " +
				startPos + " and " + endPos  + " order by POS";
		  
		  
		return executeSQL(sql);
	}
	
	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
}
