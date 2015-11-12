package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VConvertRefposPrecomp;
import org.irri.iric.portal.chado.domain.VConvertRefposition;
import org.irri.iric.portal.dao.OrganismDAO;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceConversionImpl;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.genotype.VariantIndelStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VConvertRefposPrecomp entities.
 * 
 */
//@Repository("VConvertRefposPrecompDAO")
@Repository("MultipleReferenceConverterDAO")
//@Repository("MultipleReferenceConverterDAO")

@Transactional
public class VConvertRefposPrecompDAOImpl extends AbstractJpaDao<VConvertRefposPrecomp>
		implements VConvertRefposPrecompDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VConvertRefposPrecomp.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VConvertRefposPrecompDAOImpl
	 *
	 */
	public VConvertRefposPrecompDAOImpl() {
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
	 * JPQL Query - findVConvertRefposPrecompByFromPosition
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromPosition(Integer fromPosition) throws DataAccessException {

		return findVConvertRefposPrecompByFromPosition(fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromPosition(Integer fromPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromPosition", startResult, maxRows, fromPosition);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigContaining
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigContaining(String fromContig) throws DataAccessException {

		return findVConvertRefposPrecompByFromContigContaining(fromContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigContaining(String fromContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromContigContaining", startResult, maxRows, fromContig);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToOrganismId(java.math.BigDecimal toOrganismId) throws DataAccessException {

		return findVConvertRefposPrecompByToOrganismId(toOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToOrganismId(java.math.BigDecimal toOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByToOrganismId", startResult, maxRows, toOrganismId);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContig
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContig(String fromContig) throws DataAccessException {

		return findVConvertRefposPrecompByFromContig(fromContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContig
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContig(String fromContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromContig", startResult, maxRows, fromContig);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToPosition
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToPosition(Integer toPosition) throws DataAccessException {

		return findVConvertRefposPrecompByToPosition(toPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToPosition(Integer toPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByToPosition", startResult, maxRows, toPosition);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigContaining
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigContaining(String toContig) throws DataAccessException {

		return findVConvertRefposPrecompByToContigContaining(toContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigContaining(String toContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByToContigContaining", startResult, maxRows, toContig);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByPrimaryKey
	 *
	 */
	@Transactional
	public VConvertRefposPrecomp findVConvertRefposPrecompByPrimaryKey(Integer fromContigFeatureId, Integer fromPosition, Integer toContigFeatureId, Integer toPosition) throws DataAccessException {

		return findVConvertRefposPrecompByPrimaryKey(fromContigFeatureId, fromPosition, toContigFeatureId, toPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByPrimaryKey
	 *
	 */

	@Transactional
	public VConvertRefposPrecomp findVConvertRefposPrecompByPrimaryKey(Integer fromContigFeatureId, Integer fromPosition, Integer toContigFeatureId, Integer toPosition, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertRefposPrecompByPrimaryKey", startResult, maxRows, fromContigFeatureId, fromPosition, toContigFeatureId, toPosition);
			return (org.irri.iric.portal.chado.domain.VConvertRefposPrecomp) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigFeatureId
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigFeatureId(Integer fromContigFeatureId) throws DataAccessException {

		return findVConvertRefposPrecompByFromContigFeatureId(fromContigFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigFeatureId(Integer fromContigFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromContigFeatureId", startResult, maxRows, fromContigFeatureId);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrganismId(java.math.BigDecimal fromOrganismId) throws DataAccessException {

		return findVConvertRefposPrecompByFromOrganismId(fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrganismId(java.math.BigDecimal fromOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromOrganismId", startResult, maxRows, fromOrganismId);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcallContaining(String toRefcall) throws DataAccessException {

		return findVConvertRefposPrecompByToRefcallContaining(toRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcallContaining(String toRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByToRefcallContaining", startResult, maxRows, toRefcall);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcallContaining(String fromRefcall) throws DataAccessException {

		return findVConvertRefposPrecompByFromRefcallContaining(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcallContaining(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromRefcallContaining", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcall
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcall(String fromRefcall) throws DataAccessException {

		return findVConvertRefposPrecompByFromRefcall(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcall(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromRefcall", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigFeatureId
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigFeatureId(Integer toContigFeatureId) throws DataAccessException {

		return findVConvertRefposPrecompByToContigFeatureId(toContigFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigFeatureId(Integer toContigFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByToContigFeatureId", startResult, maxRows, toContigFeatureId);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcall
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcall(String toRefcall) throws DataAccessException {

		return findVConvertRefposPrecompByToRefcall(toRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcall(String toRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByToRefcall", startResult, maxRows, toRefcall);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContig
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContig(String toContig) throws DataAccessException {

		return findVConvertRefposPrecompByToContig(toContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContig
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContig(String toContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByToContig", startResult, maxRows, toContig);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVConvertRefposPrecomps
	 *
	 */
	@Transactional
	public Set<VConvertRefposPrecomp> findAllVConvertRefposPrecomps() throws DataAccessException {

		return findAllVConvertRefposPrecomps(-1, -1);
	}

	/**
	 * JPQL Query - findAllVConvertRefposPrecomps
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposPrecomp> findAllVConvertRefposPrecomps(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVConvertRefposPrecomps", startResult, maxRows);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VConvertRefposPrecomp entity) {
		return true;
	}

	/*
	@Override
	public MultiReferencePosition convertPosition(
			MultiReferencePosition fromPos, String toReference, String toContig)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus,
			String toReference, String toContig) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantStringData convertReferencePositions(
			VariantStringData variantstringdataNPB,
			MultiReferenceLocus npbMultirefLocus,
			MultiReferenceLocus origMultiReferenceLocus, String toContig)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/
	
	//@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosToOrg", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition=?3 and myVConvertRefposPrecomp.toOrganismId=?4"),
	//@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosToOrgContig", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition=?3 and myVConvertRefposPrecomp.toOrganismId=?4 and myVConvertRefposPrecomp.toContig=?5"),
	//@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosrangeToOrg", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition between ?3 and ?4 and myVConvertRefposPrecomp.toOrganismId=?5 order by  myVConvertRefposPrecomp.toContig, myVConvertRefposPrecomp.toPosition"),
	//@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition= between ?3 and ?4 and myVConvertRefposPrecomp.toOrganismId=?5 and myVConvertRefposPrecomp.toContig=?6 and myVConvertRefposPrecomp.toPosition between ?7 and ?8 order by myVConvertRefposPrecomp.toPosition"),


	private List<VConvertRefposPrecomp> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		try { 
			List listResult = getSession().createSQLQuery(sql).addEntity(VConvertRefposPrecomp.class).list();
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
	
	
	private String queryConversionOld(BigDecimal fromOrg, String fromContig, Long fromPosition, BigDecimal toOrg, String toContig) {
		
		/*
		select  CONTIG2 AS FROM_CONTIG,  ORGANISM_ID2 AS FROM_ORGANISM_ID,  CONTIG2_FEATURE_ID AS FROM_CONTIG_FEATURE_ID, 15182315 AS FROM_POSITION, CONTIG1 AS TO_CONTIG, ORGANISM_ID1 as TO_ORGANISM_ID, CONTIG1_FEATURE_ID TO_CONTIG_FEATURE_ID, START1+(15182315-START2) as TO_POSITION from iric.REFERENCE_ALIGNMENT where 
		ORGANISM_ID2=14 and ORGANISM_ID1=9
		and CONTIG2='9311_chr07'
		and 15182315 between START2 and STOP2;
		*/
			/*
			select distinct ra.organism_id1, o1.common_name name1,  ra.organism_id2 , o2.common_name name2
			9	Japonica nipponbare	13	IR64-21
			9	Japonica nipponbare	14	93-11
			9	Japonica nipponbare	15	DJ123
			9	Japonica nipponbare	16	Kasalath
			13	IR64-21	15	DJ123
			13	IR64-21	16	Kasalath
			14	93-11	13	IR64-21
			14	93-11	15	DJ123
			14	93-11	16	Kasalath
			16	Kasalath	15	DJ123
			*/
			
			BigDecimal bdNPB = BigDecimal.valueOf(9);
			BigDecimal bdIR6421 = BigDecimal.valueOf(13);
			BigDecimal bdDJ123 = BigDecimal.valueOf(15);
			BigDecimal bdKas = BigDecimal.valueOf(16);
			BigDecimal bd9311 = BigDecimal.valueOf(14);
			
			String sql = "";
			
			if(toOrg.equals(bdNPB)
					) {
				sql = "select  CONTIG2 AS FROM_CONTIG,  ORGANISM_ID2 AS FROM_ORGANISM_ID,  CONTIG2_FEATURE_ID AS FROM_CONTIG_FEATURE_ID, " + fromPosition + " AS FROM_POSITION, '' as FROM_REFCALL,  CONTIG1 AS TO_CONTIG, ORGANISM_ID1 as TO_ORGANISM_ID, CONTIG1_FEATURE_ID TO_CONTIG_FEATURE_ID, " 
						+ " START1+(" + fromPosition + "-START2) as TO_POSITION, '' AS TO_REFCALL from iric.REFERENCE_ALIGNMENT where " 
						+ " ORGANISM_ID2=" + fromOrg + " and ORGANISM_ID1=" + toOrg
						+ " and CONTIG2='" + fromContig + "' and "
						+ fromPosition + " between START2 and STOP2 ";
				if(toContig!=null && !toContig.isEmpty())
					sql += " and CONTIG1='" + toContig + "'";

				sql += " order by TO_POSITION" ;
				
			} else if(fromOrg.equals(bdNPB) 
					) {
				
				 sql = "select CONTIG1 AS FROM_CONTIG, ORGANISM_ID1 AS FROM_ORGANISM_ID,  CONTIG1_FEATURE_ID AS FROM_CONTIG_FEATURE_ID,  " + fromPosition + " AS FROM_POSITION, '' AS FROM_REFCALL, CONTIG2 AS TO_CONTIG, ORGANISM_ID2 as TO_ORGANISM_ID, CONTIG2_FEATURE_ID as TO_CONTIG_FEATURE_ID, "
						 	+ " START2+(" + fromPosition + "-START1) as TO_POSITION, '' TO_REFCALL "
							+ " from iric.REFERENCE_ALIGNMENT " 
							+ " where  ORGANISM_ID2=" + toOrg + " and ORGANISM_ID1=" + fromOrg 
							+ "	and CONTIG1='" + fromContig +  "' and " + fromPosition + " between START1 and STOP1 ";
							
							if(toContig!=null && !toContig.isEmpty())
								sql += " and CONTIG2='" + toContig + "'";
							
				sql += " order by TO_POSITION" ;
							
			}
			
			//System.out.println("old SQL: " + sql);
			return sql;
			
		}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrgContigPosToOrgContig(BigDecimal fromOrg, String fromContig, Long fromPosition, BigDecimal toOrg, String toContig) throws DataAccessException {
		//Query query = createNamedQuery("findVConvertRefposPrecompByFromOrgContigPosToOrgContig",   -1, -1, fromOrg, fromContig, BigDecimal.valueOf(fromPosition), toOrg, toContig );
		//return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());

		String sql = "select  ffr.uniquename AS FROM_CONTIG,  fromfl.organism_id AS FROM_ORGANISM_ID,  fromfl.srcfeature_id  AS FROM_CONTIG_FEATURE_ID, fromfl.position +1 AS FROM_POSITION,  fromfl.refcall AS FROM_REFCALL,  fto.uniquename AS TO_CONTIG, tofl.organism_id as TO_ORGANISM_ID, tofl.srcfeature_id TO_CONTIG_FEATURE_ID, tofl.position +1 as TO_POSITION,  tofl.refcall AS TO_REFCALL" 
		+ " from iric.snp_featureloc fromfl, iric.snp_featureloc tofl, iric.feature ffr, iric.feature fto"
		+ " where fromfl.snp_feature_id=tofl.snp_feature_id	and fromfl.srcfeature_id=ffr.feature_id	and tofl.srcfeature_id=fto.feature_id"
		+ " and fromfl.organism_id=" + fromOrg + " and ffr.uniquename='" + fromContig + "' and fromfl.position=" + (fromPosition-1) 
		+ " and tofl.organism_id=" + toOrg;
		
		if(toContig!=null) {
			sql += " and  fto.uniquename='" + toContig + "'";
		}
		sql+=" order by fto.uniquename, tofl.position";
		
		
		AppContext.debug( queryConversionOld( fromOrg,  fromContig,  fromPosition,  toOrg,  toContig) );
		
		return executeSQL(sql);
	}
	
	/*
	@SuppressWarnings("unchecked")
	@Transactional
	public  Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrgContigPosToOrg(BigDecimal fromOrg, String fromContig, Long fromPosition, BigDecimal toOrg) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefposPrecompByFromOrgContigPosToOrg",   -1, -1, fromOrg, fromContig, BigDecimal.valueOf(fromPosition), toOrg);
		return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
	}
	*/
	
	@SuppressWarnings("unchecked")
	@Transactional
	public  List<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig(BigDecimal fromOrg, String fromContig, Long fromPositionStart, Long fromPositionEnd, BigDecimal toOrg, String toContig, Long toPosStart, Long toPosEnd) throws DataAccessException {
		//Query query = createNamedQuery("findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig",   -1, -1, fromOrg, fromContig, BigDecimal.valueOf(fromPosition1),  BigDecimal.valueOf(fromPosition2), toOrg, toContig,  toPosStart,  toPosEnd);
		//return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
		String sql = "select  ffr.uniquename AS FROM_CONTIG,  fromfl.organism_id AS FROM_ORGANISM_ID,  fromfl.srcfeature_id  AS FROM_CONTIG_FEATURE_ID, fromfl.position+1 AS FROM_POSITION,  fromfl.refcall AS FROM_REFCALL,  fto.uniquename AS TO_CONTIG, tofl.organism_id as TO_ORGANISM_ID, tofl.srcfeature_id TO_CONTIG_FEATURE_ID, tofl.position+1  as TO_POSITION, tofl.refcall AS TO_REFCALL" 
		+ " from iric.snp_featureloc fromfl, iric.snp_featureloc tofl, iric.feature ffr, iric.feature fto"
		+ " where fromfl.snp_feature_id=tofl.snp_feature_id	and fromfl.srcfeature_id=ffr.feature_id	and tofl.srcfeature_id=fto.feature_id"
		+ " and fromfl.organism_id=" + fromOrg + " and ffr.uniquename='" + fromContig + "' and fromfl.position between " + (fromPositionStart-1) + " and " + (fromPositionEnd-1) 
		+ " and tofl.organism_id=" + toOrg;
		
		if(toContig!=null) {
			sql += " and  fto.uniquename='" + toContig + "'";
			if(toPosStart!=null && toPosEnd!=null)
				sql += " and tofl.position between " + toPosStart + " and " + toPosEnd;
		}
		sql+=" order by fto.uniquename, tofl.position";
		
		AppContext.debug(queryConversionOld( fromOrg,  fromContig,  fromPositionStart,  toOrg,  toContig));
		AppContext.debug(queryConversionOld( fromOrg,  fromContig,  fromPositionEnd,  toOrg,  toContig));
		
		return executeSQL(sql);
	}
	
	/*
	@SuppressWarnings("unchecked")
	@Transactional
	public  Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrgContigPosrangeToOrg(BigDecimal fromOrg, String fromContig, Long fromPosition1, Long fromPositionStart,  Long fromPositionEnd, BigDecimal toOrg) throws DataAccessException {
		//Query query = createNamedQuery("findVConvertRefposPrecompByFromOrgContigPosrangeToOrg",   -1, -1, fromOrg, fromContig, BigDecimal.valueOf(fromPosition1), BigDecimal.valueOf(fromPosition2), toOrg);
		//return new LinkedHashSet<VConvertRefposPrecomp>(query.getResultList());
		String sql = "select  ffr.uniquename AS FROM_CONTIG,  fromfl.organism_id AS FROM_ORGANISM_ID,  fromfl.srcfeature_id  AS FROM_CONTIG_FEATURE_ID, fromfl.position AS FROM_POSITION,  fromfl.position AS FROM_POSITION,  fto.uniquename AS TO_CONTIG, tofl.organism_id as TO_ORGANISM_ID, tofl.srcfeature_id TO_CONTIG_FEATURE_ID, tofl.position  as TO_POSITION" 
		+ " from iric.snp_featureloc fromfl, iric.snp_featureloc tofl, iric.feature ffr, iric.feature fto"
		+ " where fromfl.snp_feature_id=tofl.snp_feature_id	and fromfl.srcfeature_id=ffr.feature_id	and tofl.srcfeature_id=fto.feature_id"
		+ " fromfl.organism_id=" + fromOrg + " and ffr.uniquename='" + fromContig + "' and fromfl.position between " + fromPositionStart + " and " + fromPositionEnd 
		+ " and tofl.organism_id=" + toOrg;
		return new LinkedHashSet<VConvertRefposPrecomp>(executeSQL(sql));
	}
	*/

	
	private List queryConversion(BigDecimal fromOrg, String fromContig, BigDecimal fromPosition, BigDecimal toOrg, String toContig) {
		return queryConversion( fromOrg,  fromContig,  fromPosition.longValue(),  toOrg,  toContig);
	}
	
	
	private List queryConversion(BigDecimal fromOrg, String fromContig, Long fromPosition, BigDecimal toOrg, String toContig) {
		
		if(toOrg.equals( BigDecimal.valueOf(9) )) {
			return executeSQL( queryConversionOld( fromOrg,  fromContig,  fromPosition,  toOrg,  toContig) ); 	
		}
		else return findVConvertRefposPrecompByFromOrgContigPosToOrgContig( fromOrg, fromContig,fromPosition, toOrg, toContig );
	}
	
	
	@Override
	public MultiReferenceConversion convertPosition(
			MultiReferenceConversion fromPos, String toReference, String toContig) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
		
		/*
		List listConv = queryConversion(   getOrganismId( fromPos.getFromOrganism() ) , fromPos.getFromContig(), fromPos.getFromPosition(), getOrganismId( toReference ), toContig);
		if(listConv.size()==0) throw new RuntimeException("No reference coordinate conversion to " + toReference + " found");
		if(listConv.size()>1) throw new RuntimeException("Multiple (" + listConv.size() + ") reference coordinate conversions to " + toReference + " found. (overlapped)");
		//VConvertRefposition coversion = (VConvertRefposition)listConv.get(0);
		MultiReferencePosition coversion = (MultiReferencePosition)listConv.get(0);
		
		AppContext.debug("convert pos: " + fromPos  + " to " + coversion );
		
		MultiReferencePosition to = new MultiReferencePositionImpl( toReference, coversion.getToContig(), coversion.getToPosition() );
		AppContext.debug("convert pos: " + fromPos  + " to " + to );
		return to;
		*/
	}

	//@Override
	public MultiReferenceLocus convertLocusOld(MultiReferenceLocus fromLocus,
			String toReference, String toContig)  throws Exception {
		// TODO Auto-generated method stub

		List listConv = queryConversion( getOrganismId( fromLocus.getOrganism() ) , fromLocus.getContig(), BigDecimal.valueOf(fromLocus.getFmin()), getOrganismId( toReference ), toContig);
		if(listConv.size()==0) throw new RuntimeException("No reference coordinate conversion at start (" + fromLocus.getFmin() + ") to " + toReference + " found");
		//if(listConv.size()>1) throw new RuntimeException("Multiple (" + listConv.size() + ") reference coordinate conversions at start to " + toReference + " found. (overlapped)");
		MultiReferenceConversion mindiff_VConvertRefposition = null;
		if(listConv.size()>1) {
			AppContext.debug("Multiple " + listConv + " reference coordinate conversions at start (" + fromLocus.getFmin() + ") ... will use lowest");
			/*
			long mindiff = Long.MAX_VALUE;
			Iterator<VConvertRefposition> itConv = listConv.iterator();
			while(itConv.hasNext()) {
				VConvertRefposition refpos = itConv.next(); 
				long diff = refpos.getToPosition().longValue() - fromLocus.getFmin().longValue();
				if( Math.abs(diff)<mindiff ) {
					mindiff = Math.abs(diff);
					mindiff_VConvertRefposition = refpos;
				}
			}
			*/
			mindiff_VConvertRefposition = (MultiReferenceConversion)listConv.get(0);
		} else {
			mindiff_VConvertRefposition = (MultiReferenceConversion)listConv.get(0);
		}
		
		MultiReferenceConversion coversionStart = mindiff_VConvertRefposition;

		AppContext.debug("coversionStart:" + coversionStart);
		
		List listConv2 = queryConversion( getOrganismId( fromLocus.getOrganism() ) , fromLocus.getContig(),BigDecimal.valueOf( fromLocus.getFmax()), getOrganismId( toReference ), toContig);
		if(listConv2.size()==0) throw new RuntimeException("No reference coordinate conversion at end (" + fromLocus.getFmax() + ") to " + toReference + " found");
		//if(listConv2.size()>1) throw new RuntimeException("Multiple (" + listConv2.size() + ") reference coordinate conversions at end to " + toReference + " found. (overlapped)");
		
		mindiff_VConvertRefposition = null;
		if(listConv2.size()>1) {
			AppContext.debug("Multiple " + listConv2 + " reference coordinate conversions at end (" + fromLocus.getFmax() + ") ... will use largest");
			/*
			long mindiff = Long.MAX_VALUE;
			Iterator<VConvertRefposition> itConv2 = listConv2.iterator();
			while(itConv2.hasNext()) {
				VConvertRefposition refpos = itConv2.next(); 
				long diff = refpos.getToPosition().longValue() - fromLocus.getFmax().longValue();
				if( Math.abs(diff)<mindiff ) {
					mindiff = Math.abs(diff);
					mindiff_VConvertRefposition = refpos;
				}
			}
			*/
			mindiff_VConvertRefposition = (MultiReferenceConversion)listConv2.get( listConv2.size()-1 );
		} else {
			mindiff_VConvertRefposition = (MultiReferenceConversion)listConv2.get(0);
		}
		
		
		MultiReferenceConversion coversionEnd = (MultiReferenceConversion)mindiff_VConvertRefposition;
		
		AppContext.debug("coversionEnd:" + coversionEnd);
		
		if(!coversionEnd.getToContig().equals(coversionStart.getToContig())) {
			throw new RuntimeException("Aligned contig at start (" + coversionStart.getToContig() + ") is not the same as aligned contig at end (" + coversionEnd.getToContig() + " )");
		}

		
		
		Long strand = 1L;
		if(coversionStart.getToPosition().longValue() > coversionEnd.getToPosition().longValue() )
			strand = -1L;
		
		MultiReferenceLocus toLocus = new MultiReferenceLocusImpl( toReference, coversionStart.getToContig(), coversionStart.getToPosition().intValue() , coversionEnd.getToPosition().intValue(), strand.intValue() );
		AppContext.debug("convert locus:" + fromLocus + " to " + toLocus );
		return toLocus;
		
	}
	
	@Override
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus,
			String toReference, String toContig)  throws Exception {
		// TODO Auto-generated method stub

		
		List listConv = findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig( getOrganismId(  fromLocus.getOrganism()) ,  fromLocus.getContig(), fromLocus.getFmin().longValue(),fromLocus.getFmax().longValue(), getOrganismId(toReference), null, null, null);

		if(listConv.size()<2) throw new RuntimeException("No reference coordinate conversion at start (" + fromLocus.getFmin() + ") to " + toReference + " found");

		Collections.sort(listConv, new MultiReferenceToPositionSorter()); 
		
		
		MultiReferenceConversion coversionStart= (MultiReferenceConversion)listConv.get(0);
		MultiReferenceConversion coversionEnd= (MultiReferenceConversion)listConv.get(listConv.size()-1);
		
		if(!coversionEnd.getToContig().equals(coversionStart.getToContig())) {
			throw new RuntimeException("Aligned contig at start (" + coversionStart.getToContig() + ") is not the same as aligned contig at end (" + coversionEnd.getToContig() + " )" + " in " + coversionEnd.getToOrganism());
		}

		Long strand = 1L;
		if(coversionStart.getToPosition().longValue() > coversionEnd.getToPosition().longValue() )
			strand = -1L;
		
		MultiReferenceLocus toLocus = new MultiReferenceLocusImpl( toReference, coversionStart.getToContig(), coversionStart.getToPosition().intValue() , coversionEnd.getToPosition().intValue(), strand.intValue() );
		AppContext.debug("convert locus:" + fromLocus + " to " + toLocus );
		return toLocus;
		
	}
	
	public class MultiReferenceToPositionSorter implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			MultiReferenceConversion p1=(MultiReferenceConversion)o1;
			MultiReferenceConversion p2=(MultiReferenceConversion)o2;
			int ret = p1.getToContig().compareTo( p2.getToContig() );
			if(ret==0)
				ret = p1.getToPosition().compareTo( p2.getToPosition());
			return ret;
		}
		
		
		
	}
	
	

	private class MultiReferencePositionComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			MultiReferencePosition p1=(MultiReferencePosition)o1;
			MultiReferencePosition p2=(MultiReferencePosition)o2;
			int ret = p1.getOrganism().compareTo(p2.getOrganism());
			if(ret==0)
				ret = p1.getContig().compareTo(p2.getContig());
			if(ret==0)
				ret = p1.getPosition().compareTo(p2.getPosition());
			return ret;
		}
	}
	
	private class MultiReferencePositionToComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			MultiReferenceConversion p1=(MultiReferenceConversion)o1;
			MultiReferenceConversion p2=(MultiReferenceConversion)o2;
			int ret = p1.getToOrganism().compareTo(p2.getToOrganism());
			if(ret==0)
				ret = p1.getToContig().compareTo(p2.getToContig());
			if(ret==0)
				ret = p1.getToPosition().compareTo(p2.getToPosition());
			return ret;
		}
	}

	private class MultiReferencePositionFromComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			MultiReferenceConversion p1=(MultiReferenceConversion)o1;
			MultiReferenceConversion p2=(MultiReferenceConversion)o2;
			int ret = p1.getFromOrganism().compareTo(p2.getFromOrganism());
			if(ret==0)
				ret = p1.getFromContig().compareTo(p2.getFromContig());
			if(ret==0)
				ret = p1.getFromPosition().compareTo(p2.getFromPosition());
			return ret;
		}
	}

	
	@Override
	public VariantStringData convertReferencePositions( VariantStringData variantstringdataNPB, MultiReferenceLocus npbMultirefLocus,  MultiReferenceLocus origMultiReferenceLocus , String toContig, boolean isOtherRefs ) throws Exception {
		// TODO Auto-generated method stub
		
		AppContext.debug( "convertReferencePositions:" + variantstringdataNPB.toString() );
		AppContext.debug( "convertReferencePositions:" + variantstringdataNPB.getMessage() );
		
		VariantStringData origvariantstringdataNPB=variantstringdataNPB;
		if(variantstringdataNPB instanceof VariantIndelStringData){
			variantstringdataNPB = ((VariantIndelStringData)variantstringdataNPB).getAlignedIndels();
		}
		
		double prevPos = origMultiReferenceLocus.getFmin().doubleValue();
		Integer npbstartpos=npbMultirefLocus.getFmin();
		Integer npbendpos=npbMultirefLocus.getFmax();
				
		if(npbMultirefLocus.getStrand()<0) {
			prevPos = origMultiReferenceLocus.getFmax().doubleValue();
			npbendpos=npbMultirefLocus.getFmin();
			npbstartpos=npbMultirefLocus.getFmax();
		}
		
		
		StringBuffer strMessage=new StringBuffer();
		
		//queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPos().longValue(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
		
		
		List setConversions = findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig( getOrganismId(  npbMultirefLocus.getOrganism()) ,  npbMultirefLocus.getContig(), npbstartpos.longValue(), npbendpos.longValue(), 
										getOrganismId( origMultiReferenceLocus.getOrganism()), toContig, origMultiReferenceLocus.getFmin().longValue(), origMultiReferenceLocus.getFmax().longValue());
			
		//Map<BigDecimal,MultiReferencePosition> mapNPBPos2Conv=new HashMap();
		//Map<BigDecimal,MultiReferencePosition> mapXRefPos2Conv=new HashMap();

		Map<BigDecimal,Set<MultiReferencePosition>> mapNPBPos2Conv=new HashMap();
		Map<BigDecimal,Set<MultiReferencePosition>> mapXRefPos2Conv=new HashMap();

		
		Iterator<MultiReferenceConversion> itConv = setConversions.iterator();
		while(itConv.hasNext()) {
			MultiReferenceConversion conv = itConv.next();
			
			Set setConv = mapNPBPos2Conv.get(  conv.getFromPosition() );
			if(setConv==null) {
				setConv=new HashSet();
				mapNPBPos2Conv.put( conv.getFromPosition()  , setConv);
			}
			setConv.add(new MultiReferencePositionImpl( conv.getToOrganism(), conv.getToContig(), conv.getToPosition(), conv.getToRefcall()));
			
			setConv = mapXRefPos2Conv.get(  conv.getToPosition() );
			if(setConv==null) {
				setConv=new HashSet();
				mapNPBPos2Conv.put( conv.getToPosition()  , setConv);
			}
			setConv.add(new MultiReferencePositionImpl( conv.getFromOrganism(), conv.getFromContig(), conv.getFromPosition(), conv.getFromRefcall()));
			//mapNPBPos2Conv.put(  conv.getFromPosition()  , conv);
			//mapXRefPos2Conv.put( conv.getToPosition()  , conv);
		}
		
		/*
		String subseq = "";
		try {
			//subseq = indelSequenceDAO.getSubSequence(BigDecimal.valueOf(chr+2), indelstringdao.getListPos().get(0).getPos().longValue() , end+100);
			subseq = indelSequenceDAO.getSubSequence(chr, indelstringdao.getListPos().get(0).getPos().longValue() , end+100, organismId);
			AppContext.debug( "indelsequence length=" + subseq.length() );
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		*/
		
		AppContext.debug(" mapNPBPos2Conv=" +  mapNPBPos2Conv);
		
		String firstContig=null;
		//Map<BigDecimal,MultiReferenceConversion> mapMSU7Pos2ConvertedPos = new LinkedHashMap();
		Map<BigDecimal,MultiReferencePosition> mapMSU7Pos2ConvertedPos = new LinkedHashMap();
		Iterator<SnpsAllvarsPos> itPos =  variantstringdataNPB.getListPos().iterator();
		while(itPos.hasNext()) {
			
			SnpsAllvarsPos snppos = itPos.next();
			
			boolean hasFraction = false;
			boolean limitToContig=(toContig!=null && !toContig.isEmpty());
			
			//Set insertionRegions = new HashSet();
			List listConv = null;
			//if(snppos.getPos().toString().contains(".")) {
			if(!snppos.getPos().remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) hasFraction=true;
				
//			if(!snppos.getPos().remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
				// Position has fractional part, .. insertion region
				//listConv = queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPos().longValue(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
				
				//if(toContig!=null && !toContig.isEmpty()) {
					List filteredList = new java.util.ArrayList();	
					//Iterator<VConvertRefposition> itConv = listConv.iterator();
					//while(itConv.hasNext()) {
					//	VConvertRefposition conv = itConv.next();
						
						Set setconv = null;
						if(hasFraction)
							setconv = mapNPBPos2Conv.get( BigDecimal.valueOf(snppos.getPos().longValue()));
						else
							setconv = mapNPBPos2Conv.get( snppos.getPos() );
						
						if(setconv!=null && !setconv.isEmpty()) {
					
							Iterator<MultiReferencePosition> itConvPos = setconv.iterator();
							while(itConvPos.hasNext()) {
								MultiReferencePosition multirefpos = itConvPos.next(); // mapNPBPos2Conv.get(snppos.getPos());
								
								//if(conv==null) {
								//	strMessage.append("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus +"\n");
								//	continue;
								//}
							
								if(limitToContig) {
									if(!multirefpos.getContig().equals( toContig )){
										strMessage.append("Converted position " + multirefpos + " not the same contig as query " + toContig +"\n");
										continue;
									}
								} else {
									if(firstContig==null) firstContig=multirefpos.getContig();
									else {
										if( !firstContig.equals(multirefpos.getContig()) ) {
											strMessage.append(multirefpos + " don't match contig of start position " + firstContig);
											AppContext.debug( multirefpos + " don't match contig of start position " + firstContig );
											continue;
										}
									}
									
								}
								
								if(multirefpos.getPosition().longValue() <= origMultiReferenceLocus.getFmax()  && multirefpos.getPosition().longValue() >= origMultiReferenceLocus.getFmin()) {
									// include
									//filteredList.add(conv);
								
									//conv.setFromPosition( conv.getFromPosition().add(  snppos.getPos().remainder(BigDecimal.ONE) ) );
									//conv.setToPosition( conv.getToPosition().add(  snppos.getPos().remainder(BigDecimal.ONE) ) );
									
									BigDecimal to = null;
									if(hasFraction)
										to = multirefpos.getPosition().add(snppos.getPos().remainder(BigDecimal.ONE) );
									else 
										to = multirefpos.getPosition();
									
									AppContext.debug("insertion region:"  + snppos.getPos() + " to " + to);
									
									//MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), to);
									filteredList.add(multirefpos);
								} else {
									AppContext.debug("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + multirefpos.getPosition() + " not within query region "  + origMultiReferenceLocus );
									strMessage.append("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + multirefpos.getPosition() + " not within query region "  + origMultiReferenceLocus +"\n" );
								}
							}
						} else {
							strMessage.append("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus +"\n");
						}

					//}
					//listConv = filteredList;
				//}
				
		//	}
		
		/*
			else  {
				
				// Position has no fractional part, .. anchor point or deletion region
				
				//listConv = queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPos().longValueExact(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
			
				if(toContig!=null && !toContig.isEmpty()) {
					List filteredList = new java.util.ArrayList();	
					//Iterator<VConvertRefposition> itConv = listConv.iterator();
					//while(itConv.hasNext()) {
					//	VConvertRefposition conv = itConv.next();
						
						Set set
					
						MultiReferenceConversion conv = mapNPBPos2Conv.get(snppos.getPos());
						
						if(conv==null) {
							strMessage.append("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus +"\n");
							continue;
						}
						
						if(!conv.getToContig().equals( toContig )) continue;
						
						if(conv.getToPosition().longValue() <= origMultiReferenceLocus.getFmax()  &&  conv.getToPosition().longValue() >= origMultiReferenceLocus.getFmin()) {
							// include
							//AppContext.debug("SNP, anchor point or deletion region:"  + snppos.getPos() + " to " + conv.getToPosition());
							filteredList.add(conv);
							
						} else
						{
							AppContext.debug("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus );
							strMessage.append("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus+"\n" );
						}
						//filteredList.add(conv);
					//}
					listConv = filteredList;
				} else {
					// any contig
					
						List filteredList = new java.util.ArrayList();	
						MultiReferenceConversion conv = mapNPBPos2Conv.get(snppos.getPos());
						
						if(conv==null) {
							strMessage.append("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus +"\n");
							continue;
						}
						
						//if(!conv.getToContig().equals( toContig )) continue;
						if(firstContig==null) firstContig=conv.getToContig();
						else {
							if( !firstContig.equals(conv.getToContig()) ) {
								strMessage.append(conv + " don't match contig of start position " + firstContig);
								AppContext.debug( conv + " don't match contig of start position " + firstContig );
								continue;
							}
						}
						
						if(conv.getToPosition().longValue() <= origMultiReferenceLocus.getFmax()  &&  conv.getToPosition().longValue() >= origMultiReferenceLocus.getFmin()) {
							// include
							//AppContext.debug("SNP, anchor point or deletion region:"  + snppos.getPos() + " to " + conv.getToPosition());
							filteredList.add(conv);
							
						} else
						{
							AppContext.debug("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus );
							strMessage.append("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus+"\n" );
						}
						//filteredList.add(conv);
					//}
					listConv = filteredList;
					
				}
				
			}
			
			*/
			
			
			
			
			//if(listConv.size()==0) throw new RuntimeException("No reference coordinate conversion from " +  npbMultirefLocus + " to " + origMultiReferenceLocus );
			//if(listConv.size()>1) throw new RuntimeException("Multiple (" + listConv.size() + ") reference coordinate conversions from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped)");
			Set setConv = new LinkedHashSet(filteredList);
			listConv = new ArrayList();
			listConv.addAll(setConv);
			
			if(listConv.size()==0) {
				AppContext.debug("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus );
				strMessage.append("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + "\n");
			}
			else if(listConv.size()>1) {
				//Iterator<MultiReferencePosition> itConv = listConv.iterator();
				Iterator<MultiReferencePosition> itConvPos = listConv.iterator();
				while(itConv.hasNext()) {
					MultiReferencePosition conv = itConvPos.next();
					
					if(npbMultirefLocus.getStrand()>0 && conv.getPosition().doubleValue() >prevPos) {
						AppContext.debug("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + " from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest right from previous");
						strMessage.append("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + " from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest right from previous\n");
						//MultiReferencePosition coversion = new MultiReferencePositionImpl( origMultiReferenceLocus.getOrganism(), conv.getContig(), conv.getToPosition(), conv.getToRefcall());
						//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
						//AppContext.debug("conv:" + conv);
						AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPos() + " to " + conv );
						mapMSU7Pos2ConvertedPos.put( snppos.getPos(), conv);
						prevPos = conv.getPosition().doubleValue();
						break;
					}
					else if(npbMultirefLocus.getStrand()<0 && conv.getPosition().doubleValue() < prevPos) {
						AppContext.debug("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest left from previous");
						strMessage.append("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest left from previous\n");
						//MultiReferencePosition coversion = new MultiReferencePositionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), conv.getToPosition(), conv.getToRefcall());
						//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
						//AppContext.debug("conv:" + conv);
						AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPos() + " to " + conv );
						mapMSU7Pos2ConvertedPos.put( snppos.getPos(), conv);
						prevPos = conv.getPosition().doubleValue();
						break;
					}
				}
				
			} else {
				/*
				//MultiReferencePosition coversion = (MultiReferencePosition)listConv.get(0);
				MultiReferenceConversion conv = (MultiReferenceConversion)listConv.get(0);
				MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), conv.getToPosition(), conv.getToRefcall());
				//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
				//AppContext.debug("conv:" + conv);
				//AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPos() + " to " + coversion );
				mapMSU7Pos2ConvertedPos.put( snppos.getPos(), coversion);
				*/
				MultiReferencePosition conv = (MultiReferencePosition)listConv.get(0);
				mapMSU7Pos2ConvertedPos.put( snppos.getPos(), conv);
				
				prevPos = conv.getPosition().doubleValue();
			}
			
			
		}
		
		AppContext.debug("convertReferencePositions: isNPB=" + variantstringdataNPB.isNipponbareReference() + "; msg=" + variantstringdataNPB.getMessage());
		
		if(isOtherRefs) {
			if(origvariantstringdataNPB instanceof VariantIndelStringData) {
				// because AlignedIndel is used
				origvariantstringdataNPB.addMapOrg2MSU7PosConverterPos( origMultiReferenceLocus.getOrganism(), mapMSU7Pos2ConvertedPos, npbMultirefLocus.getContig());
				if(strMessage.length()>0)
					origvariantstringdataNPB.setMessage( origvariantstringdataNPB.getMessage() + "\n" + strMessage );
				return origvariantstringdataNPB;
			}
			else 	{
				variantstringdataNPB.addMapOrg2MSU7PosConverterPos(origMultiReferenceLocus.getOrganism(), mapMSU7Pos2ConvertedPos, npbMultirefLocus.getContig() );
				if(strMessage.length()>0)
					origvariantstringdataNPB.setMessage( origvariantstringdataNPB.getMessage() + "\n" + strMessage );
				return variantstringdataNPB;
			}
		}
		else {
			if(origvariantstringdataNPB instanceof VariantIndelStringData) {
				// because AlignedIndel is used
				origvariantstringdataNPB.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos, npbMultirefLocus.getContig());
				if(strMessage.length()>0)
					origvariantstringdataNPB.setMessage( origvariantstringdataNPB.getMessage() + "\n" + strMessage );
				return origvariantstringdataNPB;
			}
			else 	{
				variantstringdataNPB.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos, npbMultirefLocus.getContig() );
				if(strMessage.length()>0)
					origvariantstringdataNPB.setMessage( origvariantstringdataNPB.getMessage() + "\n" + strMessage );
				return variantstringdataNPB;
			}
		}
		
	}
	
	/*
	//@Override
	public VariantStringData convertReferencePositionsOld( VariantStringData variantstringdataNPB, MultiReferenceLocus npbMultirefLocus,  MultiReferenceLocus origMultiReferenceLocus , String toContig ) throws Exception {
		// TODO Auto-generated method stub
		
		AppContext.debug( "convertReferencePositions:" + variantstringdataNPB.toString() );
		AppContext.debug( "convertReferencePositions:" + variantstringdataNPB.getMessage() );
		
		VariantStringData origvariantstringdataNPB=variantstringdataNPB;
		if(variantstringdataNPB instanceof VariantIndelStringData){
			variantstringdataNPB = ((VariantIndelStringData)variantstringdataNPB).getAlignedIndels();
		}
		
		double prevPos = origMultiReferenceLocus.getFmin().doubleValue();
		
		if(npbMultirefLocus.getStrand()<0)
			prevPos = origMultiReferenceLocus.getFmax().doubleValue();
		
		StringBuffer strMessage=new StringBuffer();
		
		Iterator<SnpsAllvarsPos> itPos =  variantstringdataNPB.getListPos().iterator();
		//Map<BigDecimal,SnpsAllvarsPos> mapMSU7toNewref = new LinkedHashMap();
		Map<BigDecimal,MultiReferenceConversion> mapMSU7Pos2ConvertedPos = new LinkedHashMap();
		while(itPos.hasNext()) {
			
			SnpsAllvarsPos snppos = itPos.next();
			
			//Set insertionRegions = new HashSet();
			List listConv = null;
			//if(snppos.getPos().toString().contains(".")) {
			if(!snppos.getPos().remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
				// Position has fractional part, .. insertion region
				
				listConv = queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPos().longValue(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
				
				if(toContig!=null && !toContig.isEmpty()) {
					List filteredList = new java.util.ArrayList();	
					Iterator<VConvertRefposition> itConv = listConv.iterator();
					while(itConv.hasNext()) {
						VConvertRefposition conv = itConv.next();
						if(!conv.getToContig().equals( toContig )) continue;
						
						if(conv.getToPosition().longValue() <= origMultiReferenceLocus.getFmax()  && conv.getToPosition().longValue() >= origMultiReferenceLocus.getFmin()) {
							// include
							//filteredList.add(conv);
						
							//conv.setFromPosition( conv.getFromPosition().add(  snppos.getPos().remainder(BigDecimal.ONE) ) );
							//conv.setToPosition( conv.getToPosition().add(  snppos.getPos().remainder(BigDecimal.ONE) ) );
							
							BigDecimal to = conv.getToPosition().add(  snppos.getPos().remainder(BigDecimal.ONE) );
							AppContext.debug("insertion region:"  + snppos.getPos() + " to " + to);
							
							MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), to);
							filteredList.add(coversion);
						} else {
							AppContext.debug("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus );
							strMessage.append("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus +"\n" );
						}

					}
					listConv = filteredList;
				}
				
			}
			else  {
				
				// Position has no fractional part, .. anchor point or deletion region
				
				listConv = queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPos().longValueExact(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
			
				if(toContig!=null && !toContig.isEmpty()) {
					List filteredList = new java.util.ArrayList();	
					Iterator<VConvertRefposition> itConv = listConv.iterator();
					while(itConv.hasNext()) {
						VConvertRefposition conv = itConv.next();
						if(!conv.getToContig().equals( toContig )) continue;
						
						if(conv.getToPosition().longValue() <= origMultiReferenceLocus.getFmax()  &&  conv.getToPosition().longValue() >= origMultiReferenceLocus.getFmin()) {
							// include
							//AppContext.debug("SNP, anchor point or deletion region:"  + snppos.getPos() + " to " + conv.getToPosition());
							filteredList.add(conv);
							
						} else
						{
							AppContext.debug("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus );
							strMessage.append("SNP npb:" + snppos.getPos() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus+"\n" );
						}
						//filteredList.add(conv);
					}
					listConv = filteredList;
				}
			
				
			}
			
			
			//if(listConv.size()==0) throw new RuntimeException("No reference coordinate conversion from " +  npbMultirefLocus + " to " + origMultiReferenceLocus );
			//if(listConv.size()>1) throw new RuntimeException("Multiple (" + listConv.size() + ") reference coordinate conversions from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped)");
			Set setConv = new LinkedHashSet(listConv);
			listConv = new ArrayList();
			listConv.addAll(setConv);
			
			if(listConv.size()==0) {
				AppContext.debug("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus );
				strMessage.append("No reference coordinate conversion at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + "\n");
			}
			else if(listConv.size()>1) {
				Iterator<MultiReferenceConversion> itConv = listConv.iterator();
				while(itConv.hasNext()) {
					MultiReferenceConversion conv = itConv.next();
					
					if(npbMultirefLocus.getStrand()>0 && conv.getToPosition().doubleValue() >prevPos) {
						AppContext.debug("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + " from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest right from previous");
						strMessage.append("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + " from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest right from previous\n");
						MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), conv.getToPosition());
						//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
						//AppContext.debug("conv:" + conv);
						AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPos() + " to " + coversion );
						mapMSU7Pos2ConvertedPos.put( snppos.getPos(), coversion);
						prevPos = coversion.getToPosition().doubleValue();
						break;
					}
					else if(npbMultirefLocus.getStrand()<0 && conv.getToPosition().doubleValue() < prevPos) {
						AppContext.debug("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest left from previous");
						strMessage.append("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPos() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest left from previous\n");
						MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), conv.getToPosition());
						//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
						//AppContext.debug("conv:" + conv);
						AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPos() + " to " + coversion );
						mapMSU7Pos2ConvertedPos.put( snppos.getPos(), coversion);
						prevPos = coversion.getToPosition().doubleValue();
						break;
					}
				}
				
			} else {
				//MultiReferencePosition coversion = (MultiReferencePosition)listConv.get(0);
				MultiReferenceConversion conv = (MultiReferenceConversion)listConv.get(0);
				MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), conv.getToPosition());
				//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
				//AppContext.debug("conv:" + conv);
				//AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPos() + " to " + coversion );
				
				mapMSU7Pos2ConvertedPos.put( snppos.getPos(), coversion);
				
				prevPos = coversion.getToPosition().doubleValue();
			}
			
			
		}
		
		AppContext.debug("convertReferencePositions: isNPB=" + variantstringdataNPB.isNipponbareReference() + "; msg=" + variantstringdataNPB.getMessage());
		
		if(origvariantstringdataNPB instanceof VariantIndelStringData) {
			origvariantstringdataNPB.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos, npbMultirefLocus.getContig());
			if(strMessage.length()>0)
				origvariantstringdataNPB.setMessage( origvariantstringdataNPB.getMessage() + "\n" + strMessage );
			return origvariantstringdataNPB;
		}
		else 	{
			variantstringdataNPB.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos, npbMultirefLocus.getContig() );
			if(strMessage.length()>0)
				origvariantstringdataNPB.setMessage( origvariantstringdataNPB.getMessage() + "\n" + strMessage );
			return variantstringdataNPB;
		}
		
	}

*/





	@Autowired
	//@Qualifier("OrganismDAO")
	private OrganismDAO organismdao;
	
	//private Map<String,BigDecimal> mapOrgname2Id=null;

	
	private BigDecimal getOrganismId(String organism) {
		BigDecimal orgid=null;
		try{
			orgid = BigDecimal.valueOf( Long.valueOf(organism) );
		} catch (Exception ex) {
			return organismdao.getMapName2Organism().get(organism).getOrganismId();
		}
		return orgid;
	}
	

	
	
}
