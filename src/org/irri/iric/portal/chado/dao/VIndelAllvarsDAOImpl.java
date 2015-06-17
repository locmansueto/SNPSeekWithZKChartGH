package org.irri.iric.portal.chado.dao;

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
import org.irri.iric.portal.chado.domain.VConvertRefposPrecomp;
import org.irri.iric.portal.chado.domain.VIndelAllvars;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePosition;
//import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIndelAllvars entities.
 * 
 */
@Repository("IndelAllvarsDAO")
@Transactional
public class VIndelAllvarsDAOImpl extends AbstractJpaDao<VIndelAllvars>
		implements VIndelAllvarsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIndelAllvars.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIndelAllvarsDAOImpl
	 *
	 */
	public VIndelAllvarsDAOImpl() {
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
	 * JPQL Query - findVIndelAllvarsByVar
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByVar(java.math.BigDecimal var) throws DataAccessException {

		return findVIndelAllvarsByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByVar
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByVar(java.math.BigDecimal var, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByVar", startResult, maxRows, var);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByIndelCallId
	 *
	 */
	@Transactional
	public VIndelAllvars findVIndelAllvarsByIndelCallId(Integer indelCallId) throws DataAccessException {

		return findVIndelAllvarsByIndelCallId(indelCallId, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByIndelCallId
	 *
	 */

	@Transactional
	public VIndelAllvars findVIndelAllvarsByIndelCallId(Integer indelCallId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIndelAllvarsByIndelCallId", startResult, maxRows, indelCallId);
			return (org.irri.iric.portal.chado.domain.VIndelAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPos
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPos(Integer pos) throws DataAccessException {

		return findVIndelAllvarsByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPos(Integer pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele1Id
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele1Id(Integer allele1Id) throws DataAccessException {

		return findVIndelAllvarsByAllele1Id(allele1Id, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele1Id
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele1Id(Integer allele1Id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByAllele1Id", startResult, maxRows, allele1Id);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIndelAllvarss
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findAllVIndelAllvarss() throws DataAccessException {

		return findAllVIndelAllvarss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIndelAllvarss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findAllVIndelAllvarss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIndelAllvarss", startResult, maxRows);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPrimaryKey
	 *
	 */
	@Transactional
	public VIndelAllvars findVIndelAllvarsByPrimaryKey(Integer indelCallId) throws DataAccessException {

		return findVIndelAllvarsByPrimaryKey(indelCallId, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPrimaryKey
	 *
	 */

	@Transactional
	public VIndelAllvars findVIndelAllvarsByPrimaryKey(Integer indelCallId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIndelAllvarsByPrimaryKey", startResult, maxRows, indelCallId);
			return (org.irri.iric.portal.chado.domain.VIndelAllvars) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPartitionId
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPartitionId(java.math.BigDecimal partitionId) throws DataAccessException {

		return findVIndelAllvarsByPartitionId(partitionId, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByPartitionId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByPartitionId(java.math.BigDecimal partitionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByPartitionId", startResult, maxRows, partitionId);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele2Id
	 *
	 */
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele2Id(Integer allele2Id) throws DataAccessException {

		return findVIndelAllvarsByAllele2Id(allele2Id, -1, -1);
	}

	/**
	 * JPQL Query - findVIndelAllvarsByAllele2Id
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelAllvars> findVIndelAllvarsByAllele2Id(Integer allele2Id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIndelAllvarsByAllele2Id", startResult, maxRows, allele2Id);
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIndelAllvars entity) {
		return true;
	}

	@Override
	public Set<IndelsAllvars> getAllIndelCalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Set findIndelAllvarsByChrPosBetween(String chr, BigDecimal start, BigDecimal end) {
		// TODO Auto-generated method stub
		chr=AppContext.guessChrFromString(chr);
		Query query = createNamedQuery("findVIndelAllvarsByChrPosBetween", -1, -1,  BigDecimal.valueOf(Long.valueOf(chr)+2), start, end );
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	@Override
	public Set findIndelAllvarsByVarChrPosBetween(Collection varids, String chr, BigDecimal start, BigDecimal end) {
		// TODO Auto-generated method stub
		chr=AppContext.guessChrFromString(chr);
		Query query = createNamedQuery("findVIndelAllvarsByVarChrPosBetween", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)+2), start, end, varids );
		return new LinkedHashSet<VIndelAllvars>(query.getResultList());
	}

	@Override
	public Set findIndelAllvarsByVarChrPosIn(Collection varList, String chr, Collection posList) {
		if(chr.toLowerCase().equals("loci")) {
			
			StringBuffer buffVar = new StringBuffer();
			Iterator<String> itList = AppContext.setSlicerIds((Set)varList).iterator();
			buffVar.append(" ( ");
			while(itList.hasNext()) {
				 buffVar.append(  " var in (" +  itList.next() + ") ");
				 if(itList.hasNext()) buffVar.append(" or ");
			}
			buffVar.append(")");
			
			String sql="select * from iric.V_INDEL_ALLVARS where " + buffVar.toString() + " and (";
			Iterator<Locus> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				Locus loc =  itLoc.next();
				sql += "( partition_id=" + ( Integer.valueOf(AppContext.guessChrFromString(loc.getContig()))+2) + " and pos between " + loc.getFmin() + " and " + loc.getFmax() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else if(chr.toLowerCase().equals("any")) {
			AppContext.debug("snp list not supported for indel.");
			StringBuffer buffVar = new StringBuffer();
			Iterator<String> itList = AppContext.setSlicerIds((Set)varList).iterator();
			buffVar.append(" ( ");
			while(itList.hasNext()) {
				 buffVar.append(  " var in (" +  itList.next() + ") ");
				 if(itList.hasNext()) buffVar.append(" or ");
			}
			buffVar.append(")");
			String sql="select * from iric.V_INDEL_ALLVARS where " + buffVar.toString() + " and (";
			Iterator<MultiReferencePosition> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				MultiReferencePosition loc =  itLoc.next();
				sql += "( partition_id=" + (Integer.valueOf( AppContext.guessChrFromString(loc.getContig()))+2) + " and pos=" + loc.getPosition() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else {
			chr=AppContext.guessChrFromString(chr);
			Query query = createNamedQuery("findVIndelAllvarsByVarChrPosIn", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)+2), varList, posList );
			return new LinkedHashSet<VIndelAllvars>(query.getResultList());
		}
	}

	@Override
	public Set findIndelAllvarsByChrPosIn(String chr, Collection posList) {
		// TODO Auto-generated method stub
		
		if(chr.toLowerCase().equals("loci")) {
			
			String sql="select * from iric.V_INDEL_ALLVARS where (";
			Iterator<Locus> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				Locus loc =  itLoc.next();
				sql += "( partition_id=" + ( Integer.valueOf(AppContext.guessChrFromString(loc.getContig()))+2) + " and pos between " + loc.getFmin() + " and " + loc.getFmax() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else if(chr.toLowerCase().equals("any")) {
			AppContext.debug("snp list not supported for indel.");
			String sql="select * from iric.V_INDEL_ALLVARS where (";
			Iterator<MultiReferencePosition> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				MultiReferencePosition loc =  itLoc.next();
				sql += "( partition_id=" + ( Integer.valueOf(AppContext.guessChrFromString(loc.getContig()))+2) + " and pos=" + loc.getPosition() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else {
			chr=AppContext.guessChrFromString(chr);
			Query query = createNamedQuery("findVIndelAllvarsByChrPosIn", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)+2), posList );
			return new LinkedHashSet<VIndelAllvars>(query.getResultList());
		}	
		
		
	}
	
	

	private List<VIndelAllvars> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		try { 
			List listResult = getSession().createSQLQuery(sql).addEntity(VIndelAllvars.class).list();
			//AppContext.debug("result " + listResult.size() + " VConvertRefpositions");
			return listResult;
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList();
		}
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
	
}
