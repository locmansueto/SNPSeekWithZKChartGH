package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.Organism;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Organism entities.
 * 
 */
//@Repository("OrganismDAOPostgesAll")
@Repository("OrganismDAO")
@Transactional
public class OrganismDAOImpl extends AbstractJpaDao<Organism> implements
		OrganismDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Organism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new OrganismDAOImpl
	 *
	 */
	public OrganismDAOImpl() {
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
	 * JPQL Query - findOrganismByCommentContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByCommentContaining(String comment) throws DataAccessException {

		return findOrganismByCommentContaining(comment, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByCommentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByCommentContaining(String comment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByCommentContaining", startResult, maxRows, comment);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByCommonNameContaining(String commonName) throws DataAccessException {

		return findOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByPrimaryKey
	 *
	 */
	@Transactional
	public Organism findOrganismByPrimaryKey(Long organismId) throws DataAccessException {

		return findOrganismByPrimaryKey(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public Organism findOrganismByPrimaryKey(Long organismId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindOrganismByPrimaryKey", startResult, maxRows, organismId);
			return (org.irri.iric.portal.chado.postgres.domain.Organism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllOrganisms
	 *
	 */
	@Transactional
	public Set<Organism> findAllOrganisms() throws DataAccessException {

		return findAllOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findAllOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindAllOrganisms", startResult, maxRows);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByOrganismId
	 *
	 */
	@Transactional
	public Organism findOrganismByOrganismId(Long organismId) throws DataAccessException {

		return findOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByOrganismId
	 *
	 */

	@Transactional
	public Organism findOrganismByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindOrganismByOrganismId", startResult, maxRows, organismId);
			return (org.irri.iric.portal.chado.postgres.domain.Organism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOrganismByGenusContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByGenusContaining(String genus) throws DataAccessException {

		return findOrganismByGenusContaining(genus, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByGenusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByGenusContaining(String genus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByGenusContaining", startResult, maxRows, genus);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismBySpeciesContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismBySpeciesContaining(String species) throws DataAccessException {

		return findOrganismBySpeciesContaining(species, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismBySpeciesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismBySpeciesContaining(String species, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismBySpeciesContaining", startResult, maxRows, species);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByAbbreviationContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByAbbreviationContaining(String abbreviation) throws DataAccessException {

		return findOrganismByAbbreviationContaining(abbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByAbbreviationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByAbbreviationContaining(String abbreviation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByAbbreviationContaining", startResult, maxRows, abbreviation);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByAbbreviation
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByAbbreviation(String abbreviation) throws DataAccessException {

		return findOrganismByAbbreviation(abbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByAbbreviation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByAbbreviation(String abbreviation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByAbbreviation", startResult, maxRows, abbreviation);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByCommonName
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByCommonName(String commonName) throws DataAccessException {

		return findOrganismByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByComment
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByComment(String comment) throws DataAccessException {

		return findOrganismByComment(comment, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByComment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByComment(String comment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByComment", startResult, maxRows, comment);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismBySpecies
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismBySpecies(String species) throws DataAccessException {

		return findOrganismBySpecies(species, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismBySpecies
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismBySpecies(String species, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismBySpecies", startResult, maxRows, species);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByGenus
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByGenus(String genus) throws DataAccessException {

		return findOrganismByGenus(genus, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByGenus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByGenus(String genus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindOrganismByGenus", startResult, maxRows, genus);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Organism entity) {
		return true;
	}

	@Override
	public Map<String, org.irri.iric.portal.domain.Organism> getMapName2Organism() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public org.irri.iric.portal.domain.Organism getOrganismByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
