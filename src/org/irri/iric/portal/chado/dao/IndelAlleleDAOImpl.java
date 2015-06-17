package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.IndelAllele;
import org.irri.iric.portal.chado.domain.MismatchCount;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceConversionImpl;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage IndelAllele entities.
 * 
 */
@Repository("IndelsAllvarsPosDAO")
@Transactional
public class IndelAlleleDAOImpl extends AbstractJpaDao<IndelAllele> implements
		IndelAlleleDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { IndelAllele.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new IndelAlleleDAOImpl
	 *
	 */
	public IndelAlleleDAOImpl() {
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
	 * JPQL Query - findAllIndelAlleles
	 *
	 */
	@Transactional
	public Set<IndelAllele> findAllIndelAlleles() throws DataAccessException {

		return findAllIndelAlleles(-1, -1);
	}

	/**
	 * JPQL Query - findAllIndelAlleles
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findAllIndelAlleles(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllIndelAlleles", startResult, maxRows);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findIndelAlleleByInsStrContaining
	 *
	 */
	@Transactional
	public Set<IndelAllele> findIndelAlleleByInsStrContaining(String insStr) throws DataAccessException {

		return findIndelAlleleByInsStrContaining(insStr, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleByInsStrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleByInsStrContaining(String insStr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleByInsStrContaining", startResult, maxRows, insStr);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findIndelAlleleBySrcFeatureId
	 *
	 */
	@Transactional
	public Set<IndelAllele> findIndelAlleleBySrcFeatureId(Integer srcFeatureId) throws DataAccessException {

		return findIndelAlleleBySrcFeatureId(srcFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleBySrcFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleBySrcFeatureId(Integer srcFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleBySrcFeatureId", startResult, maxRows, srcFeatureId);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findIndelAlleleByPos
	 *
	 */
	@Transactional
	public Set<IndelAllele> findIndelAlleleByPos(Integer pos) throws DataAccessException {

		return findIndelAlleleByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleByPos(Integer pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleByPos", startResult, maxRows, pos);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}
	


	/**
	 * JPQL Query - findIndelAlleleByInsStr
	 *
	 */
	@Transactional
	public Set<IndelAllele> findIndelAlleleByInsStr(String insStr) throws DataAccessException {

		return findIndelAlleleByInsStr(insStr, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleByInsStr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleByInsStr(String insStr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleByInsStr", startResult, maxRows, insStr);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findIndelAlleleByDelLen
	 *
	 */
	@Transactional
	public Set<IndelAllele> findIndelAlleleByDelLen(Integer delLen) throws DataAccessException {

		return findIndelAlleleByDelLen(delLen, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleByDelLen
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleByDelLen(Integer delLen, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleByDelLen", startResult, maxRows, delLen);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findIndelAlleleByMethod
	 *
	 */
	@Transactional
	public Set<IndelAllele> findIndelAlleleByMethod(Integer method) throws DataAccessException {

		return findIndelAlleleByMethod(method, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleByMethod
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleByMethod(Integer method, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleByMethod", startResult, maxRows, method);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findIndelAlleleByIndelId
	 *
	 */
	@Transactional
	public IndelAllele findIndelAlleleByIndelId(Integer indelId) throws DataAccessException {

		return findIndelAlleleByIndelId(indelId, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleByIndelId
	 *
	 */

	@Transactional
	public IndelAllele findIndelAlleleByIndelId(Integer indelId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIndelAlleleByIndelId", startResult, maxRows, indelId);
			return (org.irri.iric.portal.chado.domain.IndelAllele) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIndelAlleleByPrimaryKey
	 *
	 */
	@Transactional
	public IndelAllele findIndelAlleleByPrimaryKey(Integer indelId) throws DataAccessException {

		return findIndelAlleleByPrimaryKey(indelId, -1, -1);
	}

	/**
	 * JPQL Query - findIndelAlleleByPrimaryKey
	 *
	 */

	@Transactional
	public IndelAllele findIndelAlleleByPrimaryKey(Integer indelId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIndelAlleleByPrimaryKey", startResult, maxRows, indelId);
			return (org.irri.iric.portal.chado.domain.IndelAllele) query.getSingleResult();
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
	public boolean canBeMerged(IndelAllele entity) {
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleByChrPosBetween(Integer chr, Integer posStart, Integer posStop) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleBySrcfeatureidPosBetween", -1, -1, BigDecimal.valueOf(chr+2),  BigDecimal.valueOf(posStart), BigDecimal.valueOf(posStop));
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IndelAllele> findIndelAlleleByChrPosIn(Integer chr, Collection poslist) throws DataAccessException {
		Query query = createNamedQuery("findIndelAlleleBySrcfeatureidPosIn", -1, -1,  BigDecimal.valueOf(chr+2), poslist);
		return new LinkedHashSet<IndelAllele>(query.getResultList());
	}
	
	
	
	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type) {
		// TODO Auto-generated method stub
		if(type!=SnpcoreRefposindexDAO.TYPE_3KALLINDEL) throw new RuntimeException("type should be SnpcoreRefposindexDAO.TYPE_3KALLINDEL");
		
		List retlist = new ArrayList();
		retlist.addAll( findIndelAlleleByChrPosBetween(Integer.valueOf(AppContext.guessChrFromString(chromosome)), startPos, endPos) );
		return retlist;
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		 return getSNPs(chromosome, startPos, endPos, type);
	}


	@Override
	public Map<BigDecimal,IndelAllele> getMapIndelId2Indels(String chromosome, Integer startPos, Integer endPos) {
		// TODO Auto-generated method stub
		Iterator<IndelAllele> itIndels =  findIndelAlleleByChrPosBetween(Integer.valueOf( AppContext.guessChrFromString(chromosome)), startPos, endPos).iterator();
		
		Map mapIndelid2Indels = new HashMap();
		while(itIndels.hasNext()) {
			IndelAllele indel = itIndels.next();
			mapIndelid2Indels.put(indel.getIndelId(), indel);
		}
		return mapIndelid2Indels;
	}

	

	
	private List<IndelAllele> executeSQL(String sql) {
		AppContext.debug("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(IndelAllele.class).list();
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
	@Override
	public Map<BigDecimal,IndelAllele> getMapIndelId2Indels(String chromosome, Collection poslist) {
		// TODO Auto-generated method stub
		
		Iterator<IndelAllele> itIndels=null;
		if(chromosome.toLowerCase().equals("any")) {
			Map<String,Set<BigDecimal>> mapcontig2pos = MultiReferencePositionImpl.getMapContig2SNPPos(poslist);
			Iterator<String> itCont = mapcontig2pos.keySet().iterator();
			StringBuffer buff = new StringBuffer();
			while(itCont.hasNext()) {
				String cont=itCont.next();
				Set pos = mapcontig2pos.get(cont);
				buff.append(  " (SRC_FEATURE_ID=" + (Integer.valueOf(AppContext.guessChrFromString(cont))+2) + " and POS IN (" ); 
				Iterator<BigDecimal> itPos = pos.iterator();
				while(itPos.hasNext()) {
					buff.append( itPos.next().longValue()-1 );
					if(itPos.hasNext()) buff.append(",");
				}
				buff.append(")) ");
				if(itCont.hasNext()) buff.append(" OR ");
			}
			buff.append("");
			String sql = "select * from iric.indel_allele where " + buff + " order by SRC_FEATURE_ID, POS";
			itIndels=executeSQL(sql).iterator();
		}
		else if(chromosome.toLowerCase().equals("loci")) {
			Map<String,Set<Locus>> mapcontig2locus =  MultiReferencePositionImpl.getMapContig2Loci(poslist);
			
			Iterator<String> itCont = mapcontig2locus.keySet().iterator();
			StringBuffer buff = new StringBuffer();
			while(itCont.hasNext()) {
				String cont=itCont.next();
				Set pos = mapcontig2locus.get(cont);
				buff.append(  " (SRC_FEATURE_ID=" + (Integer.valueOf(AppContext.guessChrFromString(cont))+2) + " and (" ) ;  
				Iterator<Locus> itPos = pos.iterator();
				while(itPos.hasNext()) {
					Locus loc = itPos.next();
					buff.append(" (POS between " + (loc.getFmin().longValue()-1) + " and " + (loc.getFmax().longValue()-1) + ") ");
					if(itPos.hasNext()) buff.append(" or ");
				}
				buff.append(")) ");
				if(itCont.hasNext()) buff.append(" OR ");
			}
			buff.append("");
			String sql = "select * from iric.indel_allele where " + buff + " order by SRC_FEATURE_ID, POS";
			itIndels=executeSQL(sql).iterator();
		} else {
			itIndels =  findIndelAlleleByChrPosIn(Integer.valueOf(AppContext.guessChrFromString(chromosome)), poslist).iterator();
		}
		
		Map mapIndelid2Indels = new HashMap();
		while(itIndels.hasNext()) {
			IndelAllele indel = itIndels.next();
			mapIndelid2Indels.put(indel.getIndelId(), indel);
		}
		return mapIndelid2Indels;
		
		
	}

	/*
	@Override
	public Map getMapIndelId2IndelsByIndelId(Collection indelids) {
		// TODO Auto-generated method stub
		return null;
	}
*/

	@Override
	public List getSNPsInChromosome(String chr, Collection posset,
			BigDecimal type) {
		// TODO Auto-generated method stub
		if(type!=SnpcoreRefposindexDAO.TYPE_3KALLINDEL) throw new RuntimeException("type should be SnpcoreRefposindexDAO.TYPE_3KALLINDEL");
		List retlist = new ArrayList();
		retlist.addAll(findIndelAlleleByChrPosIn(Integer.valueOf(chr)+2, posset) );
		return  retlist;
	}
//
//	@Override
//	public List getSNPsInChromosomes(Map<String, Collection> mapChr2Posset,
//			BigDecimal type) {
//		// TODO Auto-generated method stub
//		
//		List listSNPs = new ArrayList();
//		Iterator<String> itchr=mapChr2Posset.keySet().iterator();
//		while(itchr.hasNext()) {
//			String chr = itchr.next();
//			Collection colPos = mapChr2Posset.get(chr);
//			listSNPs.addAll( getSNPsInChromosome(chr, colPos, type)); 
//		}
//		
//		return listSNPs;
//	}
//	
	
	
	
}
