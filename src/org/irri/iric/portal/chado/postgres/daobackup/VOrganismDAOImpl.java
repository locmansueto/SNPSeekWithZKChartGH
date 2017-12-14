package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.VOrganism;
import org.irri.iric.portal.domain.Organism;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VOrganism entities.
 * 
 */
@Repository("OrganismDAO")
@Primary()
@Transactional
public class VOrganismDAOImpl extends AbstractJpaDao<VOrganism> implements
		VOrganismDAO {

	Map mapName2Organism;
	
	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VOrganism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VOrganismDAOImpl
	 *
	 */
	public VOrganismDAOImpl() {
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
	 * JPQL Query - findAllVOrganisms
	 *
	 */
	@Transactional
	public Set<VOrganism> findAllVOrganisms() throws DataAccessException {

		return findAllVOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findAllVOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVOrganisms", startResult, maxRows);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByCommonName
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByCommonName(String commonName) throws DataAccessException {

		return findVOrganismByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByCommonNameContaining(String commonName) throws DataAccessException {

		return findVOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByCommentContaining
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByCommentContaining(String comment) throws DataAccessException {

		return findVOrganismByCommentContaining(comment, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByCommentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByCommentContaining(String comment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByCommentContaining", startResult, maxRows, comment);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByGenusContaining
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByGenusContaining(String genus) throws DataAccessException {

		return findVOrganismByGenusContaining(genus, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByGenusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByGenusContaining(String genus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByGenusContaining", startResult, maxRows, genus);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByComment
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByComment(String comment) throws DataAccessException {

		return findVOrganismByComment(comment, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByComment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByComment(String comment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByComment", startResult, maxRows, comment);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByOrganismId
	 *
	 */
	@Transactional
	public VOrganism findVOrganismByOrganismId(Integer organismId) throws DataAccessException {

		return findVOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByOrganismId
	 *
	 */

	@Transactional
	public VOrganism findVOrganismByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVOrganismByOrganismId", startResult, maxRows, organismId);
			return (org.irri.iric.portal.chado.postgres.domain.VOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVOrganismByPrimaryKey
	 *
	 */
	@Transactional
	public VOrganism findVOrganismByPrimaryKey(Integer organismId) throws DataAccessException {

		return findVOrganismByPrimaryKey(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public VOrganism findVOrganismByPrimaryKey(Integer organismId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVOrganismByPrimaryKey", startResult, maxRows, organismId);
			return (org.irri.iric.portal.chado.postgres.domain.VOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVOrganismBySpecies
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismBySpecies(String species) throws DataAccessException {

		return findVOrganismBySpecies(species, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismBySpecies
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismBySpecies(String species, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismBySpecies", startResult, maxRows, species);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByAbbreviation
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByAbbreviation(String abbreviation) throws DataAccessException {

		return findVOrganismByAbbreviation(abbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByAbbreviation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByAbbreviation(String abbreviation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByAbbreviation", startResult, maxRows, abbreviation);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByAbbreviationContaining
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByAbbreviationContaining(String abbreviation) throws DataAccessException {

		return findVOrganismByAbbreviationContaining(abbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByAbbreviationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByAbbreviationContaining(String abbreviation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByAbbreviationContaining", startResult, maxRows, abbreviation);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismBySpeciesContaining
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismBySpeciesContaining(String species) throws DataAccessException {

		return findVOrganismBySpeciesContaining(species, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismBySpeciesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismBySpeciesContaining(String species, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismBySpeciesContaining", startResult, maxRows, species);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOrganismByGenus
	 *
	 */
	@Transactional
	public Set<VOrganism> findVOrganismByGenus(String genus) throws DataAccessException {

		return findVOrganismByGenus(genus, -1, -1);
	}

	/**
	 * JPQL Query - findVOrganismByGenus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOrganism> findVOrganismByGenus(String genus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVOrganismByGenus", startResult, maxRows, genus);
		return new LinkedHashSet<VOrganism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VOrganism entity) {
		return true;
	}

	@Override
	public Map<String, Organism> getMapName2Organism() {
		// TODO Auto-generated method stub
		if(mapName2Organism==null) {
			mapName2Organism=new LinkedHashMap();
			Iterator<VOrganism> itOrg= findAllVOrganisms().iterator();
			while(itOrg.hasNext()) {
				Organism org=itOrg.next();
				mapName2Organism.put(org.getName(),org);
			}
			
		}
		return mapName2Organism;
	}
	
	
	@Override
	public List<org.irri.iric.portal.domain.Organism> getOrganisms() {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		list.addAll(findAllVOrganisms());
		return list;
	}
	
	
	
	
}
