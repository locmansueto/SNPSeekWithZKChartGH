package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.Cv;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Cv entities.
 * 
 */
@Repository("CvDAO")
@Primary
@Transactional
public class CvDAOImpl extends AbstractJpaDao<Cv> implements CvDAO {

	private Map mapName2Cv;
	
	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Cv.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CvDAOImpl
	 *
	 */
	public CvDAOImpl() {
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
	 * JPQL Query - findCvByDefinition
	 *
	 */
	@Transactional
	public Set<Cv> findCvByDefinition(String definition) throws DataAccessException {

		return findCvByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findCvByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cv> findCvByDefinition(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCvByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<Cv>(query.getResultList());
	}

	/**
	 * JPQL Query - findCvByName
	 *
	 */
	@Transactional
	public Set<Cv> findCvByName(String name) throws DataAccessException {

		return findCvByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findCvByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cv> findCvByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCvByName", startResult, maxRows, name);
		return new LinkedHashSet<Cv>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCvs
	 *
	 */
	@Transactional
	public Set<Cv> findAllCvs() throws DataAccessException {

		return findAllCvs(-1, -1);
	}

	/**
	 * JPQL Query - findAllCvs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cv> findAllCvs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCvs", startResult, maxRows);
		return new LinkedHashSet<Cv>(query.getResultList());
	}

	/**
	 * JPQL Query - findCvByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<Cv> findCvByDefinitionContaining(String definition) throws DataAccessException {

		return findCvByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findCvByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cv> findCvByDefinitionContaining(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCvByDefinitionContaining", startResult, maxRows, definition);
		return new LinkedHashSet<Cv>(query.getResultList());
	}

	/**
	 * JPQL Query - findCvByPrimaryKey
	 *
	 */
	@Transactional
	public Cv findCvByPrimaryKey(Integer cvId) throws DataAccessException {

		return findCvByPrimaryKey(cvId, -1, -1);
	}

	/**
	 * JPQL Query - findCvByPrimaryKey
	 *
	 */

	@Transactional
	public Cv findCvByPrimaryKey(Integer cvId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCvByPrimaryKey", startResult, maxRows, cvId);
			return (org.irri.iric.portal.chado.postgres.domain.Cv) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCvByNameContaining
	 *
	 */
	@Transactional
	public Set<Cv> findCvByNameContaining(String name) throws DataAccessException {

		return findCvByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findCvByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cv> findCvByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCvByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Cv>(query.getResultList());
	}

	/**
	 * JPQL Query - findCvByCvId
	 *
	 */
	@Transactional
	public Cv findCvByCvId(Integer cvId) throws DataAccessException {

		return findCvByCvId(cvId, -1, -1);
	}

	/**
	 * JPQL Query - findCvByCvId
	 *
	 */

	@Transactional
	public Cv findCvByCvId(Integer cvId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCvByCvId", startResult, maxRows, cvId);
			return (org.irri.iric.portal.chado.postgres.domain.Cv) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Cv entity) {
		return true;
	}

	@Override
	public Map<String, org.irri.iric.portal.domain.Cv> getMapName2Cv() {
		// TODO Auto-generated method stub
		if(mapName2Cv==null) {
			mapName2Cv=new HashMap();
			Iterator<Cv> itCv=this.findAllCvs().iterator();
			while(itCv.hasNext()) {
				Cv cv=itCv.next();
				mapName2Cv.put(cv.getName(), cv);
			}
		}
		return mapName2Cv;
	}
	
	
	
}
