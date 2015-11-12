package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VSnpSynallelePos;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.irri.iric.portal.domain.SnpsSynAllele;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpSynallelePos entities.
 * 
 */
@Repository("VSnpSynallelePosDAO")
@Transactional
public class VSnpSynallelePosDAOImpl extends AbstractJpaDao<VSnpSynallelePos>
		implements VSnpSynallelePosDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpSynallelePos.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpSynallelePosDAOImpl
	 *
	 */
	public VSnpSynallelePosDAOImpl() {
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
	 * JPQL Query - findVSnpSynallelePosByPosition
	 *
	 */
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByPosition(BigDecimal position) throws DataAccessException {

		return findVSnpSynallelePosByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByPosition(BigDecimal position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSynallelePosByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpSynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByTypeId
	 *
	 */
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByTypeId(BigDecimal typeId) throws DataAccessException {

		return findVSnpSynallelePosByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSynallelePosByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VSnpSynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSynallelePosBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpSynallelePosBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSynallelePosBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSynallelePosBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnpSynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpSynallelePoss
	 *
	 */
	@Transactional
	public Set<VSnpSynallelePos> findAllVSnpSynallelePoss() throws DataAccessException {

		return findAllVSnpSynallelePoss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpSynallelePoss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSynallelePos> findAllVSnpSynallelePoss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpSynallelePoss", startResult, maxRows);
		return new LinkedHashSet<VSnpSynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleNonsyn
	 *
	 */
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSyn(String alleleNonsyn) throws DataAccessException {

		return findVSnpSynallelePosByAlleleSyn(alleleNonsyn, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleNonsyn
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSyn(String alleleNonsyn, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSynallelePosByAlleleSyn", startResult, maxRows, alleleNonsyn);
		return new LinkedHashSet<VSnpSynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpSynallelePos findVSnpSynallelePosByPrimaryKey(BigDecimal chr, BigDecimal position, String alleleNonsyn) throws DataAccessException {

		return findVSnpSynallelePosByPrimaryKey(chr, position, alleleNonsyn, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpSynallelePos findVSnpSynallelePosByPrimaryKey(BigDecimal chr, BigDecimal position, String alleleNonsyn, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSynallelePosByPrimaryKey", startResult, maxRows, chr, position, alleleNonsyn);
			return (org.irri.iric.portal.chado.oracle.domain.VSnpSynallelePos) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByChr
	 *
	 */
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByChr(BigDecimal chr) throws DataAccessException {

		return findVSnpSynallelePosByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByChr(BigDecimal chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSynallelePosByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleNonsynContaining
	 *
	 */
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSynContaining(String alleleNonsyn) throws DataAccessException {

		return findVSnpSynallelePosByAlleleSynContaining(alleleNonsyn, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleNonsynContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSynContaining(String alleleNonsyn, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSynallelePosByAlleleSynContaining", startResult, maxRows, alleleNonsyn);
		return new LinkedHashSet<VSnpSynallelePos>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpSynallelePos entity) {
		return true;
	}

	

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosBetween(String chr, Integer start, Integer end, BigDecimal dataset) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSynallelePosByPositionBetween", -1, -1,  BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))), BigDecimal.valueOf(start), BigDecimal.valueOf(end), dataset);
		java.util.List list=query.getResultList();
		java.util.Set set=new LinkedHashSet<SnpsSynAllele>(list);
		AppContext.debug( "findVSnpSynallelePosByPositionBetween list=" + list.size() + " set=" + set.size());
		return set;
	}
	

	@Override
	public Set<SnpsSynAllele> findSnpSynAlleleBySnpfeatureidIn(
			Collection listFeatureids, BigDecimal dataset) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println(" snpfeatureid in " +  listFeatureids.size()); // + AppContext.convertRegion2Snpfeatureid( chr,  Long.valueOf(start)) + " and " + AppContext.convertRegion2Snpfeatureid( chr, Long.valueOf(end)) );
		Query query = createNamedQuery("findVSnpsynallelePosBySnpFeatureIdIn", -1, -1, listFeatureids, dataset);
		return new HashSet<SnpsSynAllele>(query.getResultList());
	}

	@Override
	public Set<SnpsSynAllele> findSnpSynAlleleBySnpfeatureidBetween( BigDecimal start, BigDecimal end, BigDecimal dataset) {
		// TODO Auto-generated method stub
		System.out.println(" snpfeatureid between " + start + " and " + end );
		Query query = createNamedQuery("findVSnpSynallelePosBySnpFeatureIdBetween", -1, -1, start, end , dataset);
		return new HashSet<SnpsSynAllele>(query.getResultList());
	}

	@Override
	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosIn(String chr,
			Collection poslist, BigDecimal dataset) {
		// TODO Auto-generated method stub
	
		Set setSnpfeatureid = new HashSet();
		if(chr.toLowerCase().equals("any")) {
			

			Set setAll = new HashSet();
			
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(poslist);
			Iterator<String> itChr=mapChr2Pos.keySet().iterator();
			while(itChr.hasNext()) {
				String chrstr=itChr.next(); 
				Set sets[] = AppContext.setSlicer(new LinkedHashSet((Set)mapChr2Pos.get(chrstr)));
				for(int iset=0; iset<sets.length; iset++) {
					//Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1, -1,setSnpfeatureid);
					
					Query query = createNamedQuery("findVSnpSynallelePosByPositionIn", -1, -1, BigDecimal.valueOf(Long.valueOf( AppContext.guessChrFromString(chrstr))), sets[iset], dataset);
					setAll.addAll(query.getResultList());
				}
			}
			//return new HashSet<SnpsNonsynAllele>(query.getResultList());
			return setAll;

		}
		else if(chr.toLowerCase().equals("loci")) {
			Iterator<Locus> it = poslist.iterator();
			//StringBuffer buff = new StringBuffer();
			Set setPos = new TreeSet();
			while(it.hasNext()) {
				Locus loc=it.next();
				setPos.addAll(  findSnpSynAlleleByChrPosBetween(loc.getContig(),  loc.getFmin(),  loc.getFmax(), dataset) ); 
			}
			return setPos;
		}
		else {
			
			Set setAll = new HashSet();
			Set sets[] = AppContext.setSlicer(new LinkedHashSet(poslist));
			for(int iset=0; iset<sets.length; iset++) {
				//Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1, -1,setSnpfeatureid);
				
				Query query = createNamedQuery("findVSnpSynallelePosByPositionIn", -1, -1, BigDecimal.valueOf(Long.valueOf( AppContext.guessChrFromString(chr))), sets[iset], dataset);
				setAll.addAll(query.getResultList());
			}
			//return new HashSet<SnpsNonsynAllele>(query.getResultList());
			return setAll;
		}
	}
	
	
}
