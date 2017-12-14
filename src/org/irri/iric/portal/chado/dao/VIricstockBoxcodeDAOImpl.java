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

import org.irri.iric.portal.chado.domain.VIricstockBoxcode;
import org.irri.iric.portal.domain.Variety;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockBoxcode entities.
 * 
 */
//@Repository("VIricstockBoxcodeDAO")
@Repository("IricStockDAO")
@Transactional
public class VIricstockBoxcodeDAOImpl extends AbstractJpaDao<VIricstockBoxcode>
		implements VIricstockBoxcodeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockBoxcode.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockBoxcodeDAOImpl
	 *
	 */
	public VIricstockBoxcodeDAOImpl() {
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
	 * JPQL Query - findVIricstockBoxcodeByName
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByName(String name) throws DataAccessException {

		return findVIricstockBoxcodeByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockId
	 *
	 */
	@Transactional
	public VIricstockBoxcode findVIricstockBoxcodeByIricStockId(BigDecimal iricStockId) throws DataAccessException {

		return findVIricstockBoxcodeByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockId
	 *
	 */

	@Transactional
	public VIricstockBoxcode findVIricstockBoxcodeByIricStockId(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockBoxcodeByIricStockId", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.VIricstockBoxcode) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByTypeId
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByTypeId(java.math.BigDecimal typeId) throws DataAccessException {

		return findVIricstockBoxcodeByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByTypeId(java.math.BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockBoxcode findVIricstockBoxcodeByPrimaryKey(BigDecimal iricStockId) throws DataAccessException {

		return findVIricstockBoxcodeByPrimaryKey(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockBoxcode findVIricstockBoxcodeByPrimaryKey(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockBoxcodeByPrimaryKey", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.VIricstockBoxcode) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByOrganismId
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findVIricstockBoxcodeByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByDbxrefId
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByDbxrefId(java.math.BigDecimal dbxrefId) throws DataAccessException {

		return findVIricstockBoxcodeByDbxrefId(dbxrefId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByDbxrefId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByDbxrefId(java.math.BigDecimal dbxrefId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByDbxrefId", startResult, maxRows, dbxrefId);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockGeolocationId
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByIricStockGeolocationId(java.math.BigDecimal iricStockGeolocationId) throws DataAccessException {

		return findVIricstockBoxcodeByIricStockGeolocationId(iricStockGeolocationId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockGeolocationId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByIricStockGeolocationId(java.math.BigDecimal iricStockGeolocationId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByIricStockGeolocationId", startResult, maxRows, iricStockGeolocationId);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByNameContaining(String name) throws DataAccessException {

		return findVIricstockBoxcodeByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockBoxcodes
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findAllVIricstockBoxcodes() throws DataAccessException {

		return findAllVIricstockBoxcodes(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockBoxcodes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findAllVIricstockBoxcodes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockBoxcodes", startResult, maxRows);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCode
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCode(String boxCode) throws DataAccessException {

		return findVIricstockBoxcodeByBoxCode(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCode(String boxCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByBoxCode", startResult, maxRows, boxCode);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCodeContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCodeContaining(String boxCode) throws DataAccessException {

		return findVIricstockBoxcodeByBoxCodeContaining(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCodeContaining(String boxCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBoxcodeByBoxCodeContaining", startResult, maxRows, boxCode);
		return new LinkedHashSet<VIricstockBoxcode>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockBoxcode entity) {
		return true;
	}

	@Override
	public Set findAllVariety() {
		// TODO Auto-generated method stub
		return this.findAllVIricstockBoxcodes();
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country,
			String subpopulation, String dataset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variety findVarietyByName(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVIricstockBoxcodeByName(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyByNameLike(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVIricstockBoxcodeByNameContaining(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyByIrisId(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVIricstockBoxcodeByBoxCode(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with Iris ID " + name);
		else if(setNames.isEmpty()) {
			return null;
		}
		
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyById(BigDecimal id) {
		// TODO Auto-generated method stub
		return this.findVIricstockBoxcodeByPrimaryKey(id);
	}

	@Override
	public Variety findVarietyByAccession(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variety> findVarietyByNamesLike(Collection names) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variety> findVarietyByNames(Collection names) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variety> findVarietyByIrisIds(Collection names) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByIrisId(String irisid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByAccession(String accession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByName(String names) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set findVarietiesByNameAccession(String varname, String accession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection findVarietyByIds(Set setQueryVarobj) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
