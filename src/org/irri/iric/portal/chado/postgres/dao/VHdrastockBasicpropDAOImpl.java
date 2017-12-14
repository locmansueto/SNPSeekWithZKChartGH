package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VHdrastockBasicprop;
import org.irri.iric.portal.chado.oracle.domain.VIricstockBasicprop2;
import org.irri.iric.portal.domain.Variety;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VHdrastockBasicprop entities.
 * 
 */
@Repository("VarietyHdraDAO")
@Transactional
public class VHdrastockBasicpropDAOImpl extends AbstractJpaDao<VHdrastockBasicprop>
		implements VHdrastockBasicpropDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VHdrastockBasicprop.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VHdrastockBasicpropDAOImpl
	 *
	 */
	public VHdrastockBasicpropDAOImpl() {
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
	 * JPQL Query - findVHdrastockBasicpropByOrganismId
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findVHdrastockBasicpropByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpop
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpop(String fsSubpop) throws DataAccessException {

		return findVHdrastockBasicpropByFsSubpop(fsSubpop, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpop
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpop(String fsSubpop, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropByFsSubpop", startResult, maxRows, fsSubpop);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVHdrastockBasicprops
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findAllVHdrastockBasicprops() throws DataAccessException {

		return findAllVHdrastockBasicprops(-1, -1);
	}

	/**
	 * JPQL Query - findAllVHdrastockBasicprops
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findAllVHdrastockBasicprops(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVHdrastockBasicprops", startResult, maxRows);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByNameContaining
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByNameContaining(String name) throws DataAccessException {

		return findVHdrastockBasicpropByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByHdraStockId
	 *
	 */
	@Transactional
	public VHdrastockBasicprop findVHdrastockBasicpropByHdraStockId(BigDecimal hdraStockId) throws DataAccessException {

		return findVHdrastockBasicpropByHdraStockId(hdraStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByHdraStockId
	 *
	 */

	@Transactional
	public VHdrastockBasicprop findVHdrastockBasicpropByHdraStockId(BigDecimal hdraStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVHdrastockBasicpropByHdraStockId", startResult, maxRows, hdraStockId);
			return (org.irri.iric.portal.chado.oracle.domain.VHdrastockBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionIdContaining
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionIdContaining(String accessionId) throws DataAccessException {

		return findVHdrastockBasicpropByAccessionIdContaining(accessionId, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionIdContaining(String accessionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropByAccessionIdContaining", startResult, maxRows, accessionId);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByName
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByName(String name) throws DataAccessException {

		return findVHdrastockBasicpropByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropByName", startResult, maxRows, name);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSetContaining
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSetContaining(String sampleSet) throws DataAccessException {

		return findVHdrastockBasicpropBySampleSetContaining(sampleSet, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSetContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSetContaining(String sampleSet, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropBySampleSetContaining", startResult, maxRows, sampleSet);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionId
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionId(String accessionId) throws DataAccessException {

		return findVHdrastockBasicpropByAccessionId(accessionId, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionId(String accessionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropByAccessionId", startResult, maxRows, accessionId);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSet
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSet(String sampleSet) throws DataAccessException {

		return findVHdrastockBasicpropBySampleSet(sampleSet, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSet
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSet(String sampleSet, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropBySampleSet", startResult, maxRows, sampleSet);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByPrimaryKey
	 *
	 */
	@Transactional
	public VHdrastockBasicprop findVHdrastockBasicpropByPrimaryKey(BigDecimal hdraStockId) throws DataAccessException {

		return findVHdrastockBasicpropByPrimaryKey(hdraStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByPrimaryKey
	 *
	 */

	@Transactional
	public VHdrastockBasicprop findVHdrastockBasicpropByPrimaryKey(BigDecimal hdraStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVHdrastockBasicpropByPrimaryKey", startResult, maxRows, hdraStockId);
			return (org.irri.iric.portal.chado.oracle.domain.VHdrastockBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpopContaining
	 *
	 */
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpopContaining(String fsSubpop) throws DataAccessException {

		return findVHdrastockBasicpropByFsSubpopContaining(fsSubpop, -1, -1);
	}

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpopContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpopContaining(String fsSubpop, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVHdrastockBasicpropByFsSubpopContaining", startResult, maxRows, fsSubpop);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VHdrastockBasicprop entity) {
		return true;
	}

	@Override
	public Set findAllVariety() {
		// TODO Auto-generated method stub
		return this.findAllVHdrastockBasicprops() ;
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVHdrastockBasicpropByCountry", -1, -1, country);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());

	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return this.findVHdrastockBasicpropByFsSubpop(subpopulation);
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country,
			String subpopulation, String dataset) {
		
		Query query = createNamedQuery("findVHdrastockBasicpropByCountrySubpop", -1, -1, country, subpopulation);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());
		// TODO Auto-generated method stub
	}

	@Override
	public Variety findVarietyByName(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVHdrastockBasicpropByName(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varieties with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyByNameLike(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVHdrastockBasicpropByNameContaining(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varieties with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyByIrisId(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variety findVarietyById(BigDecimal id) {
		// TODO Auto-generated method stub
		return findVarietyById(id);
	}
	
	
	


	@Override
	public List<Variety> findVarietyByNamesLike(Collection names) {
		// TODO Auto-generated method stub
		List listVars=new ArrayList();
		Set setnames[] = AppContext.setSlicer(new LinkedHashSet(names),900);
		for(int i=0; i<setnames.length; i++) {
			StringBuffer buffnames=new StringBuffer();
			Iterator<String> itNames=setnames[i].iterator();
			while(itNames.hasNext()) {
				String name=itNames.next();
				try {
					Integer.valueOf(name);
					buffnames.append("'").append(name).append("'");
				} catch(Exception ex) {
					buffnames.append("'%").append(name.replace("'","''")).append("%'");
				}
				if(itNames.hasNext()) buffnames.append(",");
			}
			String sql = "select v.* from  iric.V_HDRASTOCK_BASICPROP v,  table (sys.ODCIVarchar2List (" + buffnames + ")) n where upper(v.name) like n.column_value";
			listVars.addAll(executeSQL(sql)); 
		}
		return listVars;
	}

	@Override
	public List<Variety> findVarietyByNames(Collection names) {
		// TODO Auto-generated method stub
		
		List listVars=new ArrayList();
		Set setnames[] = AppContext.setSlicer(new LinkedHashSet(names),900);
		for(int i=0; i<setnames.length; i++) {
			StringBuffer buffnames=new StringBuffer();
			Iterator<String> itNames=setnames[i].iterator();
			while(itNames.hasNext()) {
				String name=itNames.next();
				if(name.startsWith("IRGC")) name=name.substring(4).trim();
				buffnames.append("'").append(name.replace("'","''")).append("'");
				if(itNames.hasNext()) buffnames.append(",");
			}
			String sql = "select v.* from   iric.V_HDRASTOCK_BASICPROP v,  table (sys.ODCIVarchar2List (" + buffnames + ")) n where n.column_value=upper(v.name) or n.column_value=upper(v.accession_id)";
			listVars.addAll(executeSQL(sql)); 
		}
		
		return listVars;
				
		
		/*
		select emp.* from  emp,
		table (sys.ODCIVarchar2List ('SCOTT','ALLEN','KING','KINGDOM'))
		where column_value=ename 
		*/
		//return null;
	}

	@Override
	public List<Variety> findVarietyByIrisIds(Collection names) {
		// TODO Auto-generated method stub
		
		
		List listVars=new ArrayList();
		
		/*
		Set setnames[] = AppContext.setSlicer(new LinkedHashSet(names),900);
		for(int i=0; i<setnames.length; i++) {
			StringBuffer buffnames=new StringBuffer();
			Iterator<String> itNames=setnames[i].iterator();
			while(itNames.hasNext()) {
				String name=itNames.next();
				name="IRIS " + name.toUpperCase().replace("IRIS", "").replace("_", "").trim();
				buffnames.append("'").append(name.replace("'","''")).append("'");
				if(itNames.hasNext()) buffnames.append(",");
			}
			String sql = "select v.* from  iric.V_HDRASTOCK_BASICPROP v,  table (sys.ODCIVarchar2List (" + buffnames + ")) n where n.column_value=v.IRIS_UNIQUE_ID";
			listVars.addAll(executeSQL(sql)); 
		}
		*/
		return listVars;

	}
	
	private List executeSQL(String sql) 
	{
		/*if(AppContext.isLocalhost()) AppContext.debug("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VHdrastockBasicprop.class).list();
		*/
		return AppContext.executeSQL(entityManager, VHdrastockBasicprop.class, sql);
	}
	

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	

	@Override
	public Variety findVarietyByAccession(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVHdrastockBasicpropByAccessionId(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varieties with accession " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Set<Variety> findVarietiesByIrisId(String irisid) {
		// TODO Auto-generated method stub
		return new HashSet();
	}

	@Override
	public Set findVarietiesByAccession(String accession) {
		// TODO Auto-generated method stub
		return findVHdrastockBasicpropByAccessionId(accession);
	}

	@Override
	public Set findVarietiesByName(String names) {
		// TODO Auto-generated method stub
		return findVHdrastockBasicpropByName(names);
	}

	@Override
	public Set findVarietiesByNameAccession(String varname, String accession) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVHdrastockBasicpropByNameAccession", -1,-1, varname, accession);
		return new LinkedHashSet<VHdrastockBasicprop>(query.getResultList());

	}
	
	
	
	
}
