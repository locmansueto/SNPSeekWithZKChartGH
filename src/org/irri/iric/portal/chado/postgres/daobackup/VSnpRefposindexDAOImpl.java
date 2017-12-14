package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.irri.iric.portal.chado.postgres.domain.VSnpRefposindex;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpRefposindex entities.
 * 
 */
@Repository("VSnpRefposindexDAO")
@Primary()
@Transactional
public class VSnpRefposindexDAOImpl extends AbstractJpaDao<VSnpRefposindex>
		implements VSnpRefposindexDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpRefposindex.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpRefposindexDAOImpl
	 *
	 */
	public VSnpRefposindexDAOImpl() {
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
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(Integer position) throws DataAccessException {

		return findVSnpRefposindexByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(Integer position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(Integer typeId) throws DataAccessException {

		return findVSnpRefposindexByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(Integer chromosome) throws DataAccessException {

		return findVSnpRefposindexByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(Integer chromosome, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall) throws DataAccessException {

		return findVSnpRefposindexByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(Long snpFeatureId) throws DataAccessException {

		return findVSnpRefposindexByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpRefposindexByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.postgres.domain.VSnpRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall) throws DataAccessException {

		return findVSnpRefposindexByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findAllVSnpRefposindexs() throws DataAccessException {

		return findAllVSnpRefposindexs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findAllVSnpRefposindexs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpRefposindexs", startResult, maxRows);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpRefposindex findVSnpRefposindexBySnpFeatureId(Long snpFeatureId) throws DataAccessException {

		return findVSnpRefposindexBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpRefposindex findVSnpRefposindexBySnpFeatureId(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpRefposindexBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.postgres.domain.VSnpRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(Integer alleleIndex) throws DataAccessException {

		return findVSnpRefposindexByAlleleIndex(alleleIndex, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(Integer alleleIndex, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByAlleleIndex", startResult, maxRows, alleleIndex);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpRefposindex entity) {
		return true;
	}
	
	
	
	
	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type) {
		// TODO Auto-generated method stub
		
		
		return getSNPs( chromosome,  startPos,  endPos, type, -1, -1);
	}

	@Override
	public List getSNPs(String chr, Integer startPos, Integer endPos,
			BigDecimal type, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		chr = chr.toUpperCase().replace("CHR0","").replace("CHR","");
		AppContext.debug("VSnpRefposindexDAOPostges.getSNPs(" + chr + " " +  startPos + " " +  endPos + " " +  type + ")" );

		BigDecimal bdChr=null;
		try {
			
			//bdChr = BigDecimal.valueOf(Long.valueOf(chr));
			//Query query = createNamedQuery("findVSnpRefposindexByChrPosBetween", firstRow, maxRows, bdChr, BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), type);
			Query query = createNamedQuery("findVSnpRefposindexByChrPosBetween", firstRow, maxRows, Integer.valueOf(chr), startPos, endPos, type.intValue());
			return query.getResultList();
		} catch ( Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList();
	}

	@Override
	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type) {
		Set slicedset[] = AppContext.setSlicer(new HashSet(posset));
		List listPresent = new ArrayList();
		for(int iset=0; iset<slicedset.length; iset++) {
			listPresent.addAll( _getSNPsInChromosome(chr, slicedset[iset],  type) );
		}
		return listPresent;
	}
	
	
	
	private List _getSNPsInChromosome(String chr, Collection posset, BigDecimal type) {
		// TODO Auto-generated method stub
		
		chr = chr.toUpperCase().replace("CHR0","").replace("CHR","");
		AppContext.debug("VSnpRefposindexDAOPostges._getSNPsInChromosome(" + chr + " " +  posset + " " +  type + ")" );
		BigDecimal bdChr=null;
		if(chr.toLowerCase().equals("any")) {
			
			AppContext.debug("checking " + posset.size() + " snp positions");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			String sql = "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from PUBLIC.V_SNP_REFPOSINDEX WHERE 1=1 and (";
			Iterator<String> itContig= mapChr2Pos.keySet().iterator();
			while(itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection setPos = (Collection)mapChr2Pos.get(contigstr);
				sql+= "( chromosome=" + contig + " and position in (" + setPos.toString().replace("[", "").replace("]", "") + ")) ";
				if(itContig.hasNext()) 
					sql += " or ";
			};
			
			sql += ") and TYPE_ID=" + type + " order by CHROMOSOME, POSITION";
			return executeSQL(sql);
		}
		else if(chr.toLowerCase().equals("loci")) {
			
			AppContext.debug("checking " + posset.size() + " loci");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);
			String sql = "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from PUBLIC.V_SNP_REFPOSINDEX WHERE 1=1 and (";
			Iterator<String> itContig= mapChr2Pos.keySet().iterator();
			while(itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection<Locus> setLocus = (Collection)mapChr2Pos.get(contigstr);
				Iterator<Locus> itLocus=setLocus.iterator();
				while(itLocus.hasNext()) {
					Locus loc=itLocus.next();
					sql+= "( chromosome=" + contig + " and position between " + loc.getFmin() + " and " + loc.getFmax() + ") ";
					if(itContig.hasNext()) 
						sql += " or ";
				}
			};
			
			sql += ") and TYPE_ID=" + type + " order by CHROMOSOME, POSITION";
			return executeSQL(sql);
		}
		else {
			AppContext.debug("checking " + posset.size() + " snp positions");
			try {
				chr = chr.toUpperCase().replace("CHR0","").replace("CHR","");
				bdChr = BigDecimal.valueOf(Long.valueOf(chr));
				Query query = createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1, bdChr.intValue() ,  posset, type.intValue());
				return query.getResultList();
			} catch ( Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return new ArrayList();
	}

//	@Override
//	public List getSNPsInChromosome(Collection locuslist, BigDecimal type) {
//		// TODO Auto-generated method stub
//
//		String sql = "select * from IRIC.V_SNP_REFPOSINDEX where type_id=" + type.intValue() + " and (";
//		Iterator<Locus> itLocus = locuslist.iterator();
//		while(itLocus.hasNext()) {
//			Locus loc = itLocus.next();
//			sql += " (chromosome=" + loc.getChr() + " and position>=" + loc.getFmin() + " and position<=" + loc.getFmax() + ") "; 
//			if(itLocus.hasNext()) sql+=" or ";
//		}
//		sql += ")";
//		
//		return executeSQL(sql);
//	}

	

	private List executeSQL(String sql) {
		AppContext.debug("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VSnpRefposindex.class).list();
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
}
