package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.postgres.domain.VScaffoldsOrganism;
import org.irri.iric.portal.domain.Scaffold;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VScaffoldsOrganism entities.
 * 
 */
//@Repository("VScaffoldsOrganismDAO")
@Repository("ScaffoldDAO")
@Primary()
@Transactional
public class VScaffoldsOrganismDAOImpl extends AbstractJpaDao<VScaffoldsOrganism>
		implements VScaffoldsOrganismDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VScaffoldsOrganism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VScaffoldsOrganismDAOImpl
	 *
	 */
	public VScaffoldsOrganismDAOImpl() {
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
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName) throws DataAccessException {

		return findVScaffoldsOrganismByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms() throws DataAccessException {

		return findAllVScaffoldsOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindAllVScaffoldsOrganisms", startResult, maxRows);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name) throws DataAccessException {

		return findVScaffoldsOrganismByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name) throws DataAccessException {

		return findVScaffoldsOrganismByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByName", startResult, maxRows, name);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */
	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(Long featureId) throws DataAccessException {

		return findVScaffoldsOrganismByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */

	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(Long featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindVScaffoldsOrganismByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VScaffoldsOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName) throws DataAccessException {

		return findVScaffoldsOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtype
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtype(String ftype) throws DataAccessException {

		return findVScaffoldsOrganismByFtype(ftype, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtype
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtype(String ftype, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByFtype", startResult, maxRows, ftype);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(Long seqlen) throws DataAccessException {

		return findVScaffoldsOrganismBySeqlen(seqlen, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(Long seqlen, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismBySeqlen", startResult, maxRows, seqlen);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */
	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(Long featureId) throws DataAccessException {

		return findVScaffoldsOrganismByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(Long featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindVScaffoldsOrganismByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VScaffoldsOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtypeContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtypeContaining(String ftype) throws DataAccessException {

		return findVScaffoldsOrganismByFtypeContaining(ftype, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtypeContaining(String ftype, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByFtypeContaining", startResult, maxRows, ftype);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(Long organismId) throws DataAccessException {

		return findVScaffoldsOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename) throws DataAccessException {

		return findVScaffoldsOrganismByUniquenameContaining(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByUniquenameContaining", startResult, maxRows, uniquename);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename) throws DataAccessException {

		return findVScaffoldsOrganismByUniquename(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByUniquename", startResult, maxRows, uniquename);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(Long typeId) throws DataAccessException {

		return findVScaffoldsOrganismByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(Long typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VScaffoldsOrganism entity) {
		return true;
	}

	/*(
	@Override
	public List<Scaffold> getScaffolds(String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Scaffold> getScaffolds(BigDecimal organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getScaffoldLength(String scaffold, String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getScaffoldLength(String scaffold, BigDecimal organism) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/
	

	@Override
	public List<Scaffold> getScaffolds(String organism) {
		// TODO Auto-generated method stub
		
		List list = new ArrayList();
		
		/*
		Iterator<VScaffoldsOrganism> itScaff =  findVScaffoldsOrganismByCommonName(organism).iterator();
		while(itScaff.hasNext()) {
			list
		}
		*/
		
		list.addAll(findVScaffoldsOrganismByCommonName(organism));
		return list;
	}

	@Override
	public Long getScaffoldLength(String scaffold, String organism) {
		// TODO Auto-generated method stub
		AppContext.debug("scafold=" + scaffold + ", organism=" + organism);
		
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByUniquenameCommonName", -1, -1, scaffold, organism);
		return ((VScaffoldsOrganism)query.getResultList().get(0)).getSeqlen().longValue();
	}

	@Override
	public List<Scaffold> getScaffolds(BigDecimal organism) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		list.addAll(findVScaffoldsOrganismByOrganismId( organism.longValue()));
		return list;
	}

	@Override
	public Long getScaffoldLength(String scaffold, BigDecimal organism) {
		// TODO Auto-generated method stub
		
		scaffold=scaffold.replace("chr0", "chr");
		AppContext.debug("scafold=" + scaffold + ", organism=" + organism);
		
		Query query = createNamedQuery("PGfindVScaffoldsOrganismByUniquenameOrganismId", -1, -1, scaffold, organism.longValue());
		return ((VScaffoldsOrganism)query.getResultList().get(0)).getSeqlen().longValue();
	}
	
	
	
}
