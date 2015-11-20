package org.irri.iric.portal.chado.oracle.dao;

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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ScaffoldDAO;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allref;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.Scaffold;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.genotype.VariantIndelStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VConvertposNb2allref entities.
 * 
 */
//@Repository("VConvertposNb2allrefDAO")
@Repository("MultipleReferenceConverterDAO")

@Transactional
public class VConvertposNb2allrefDAOImpl extends AbstractJpaDao<VConvertposNb2allref>
		implements VConvertposNb2allrefDAO {

	
	@Autowired
	private OrganismDAO orgdao;
	@Autowired
	private ScaffoldDAO scaffolddao;
	
	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VConvertposNb2allref.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VConvertposNb2allrefDAOImpl
	 *
	 */
	public VConvertposNb2allrefDAOImpl() {
		super();
		orgdao= (OrganismDAO)AppContext.checkBean(orgdao, "OrganismDAO");
		scaffolddao= (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
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
	 * JPQL Query - findVConvertposNb2allrefByToRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcallContaining(String toRefcall) throws DataAccessException {

		return findVConvertposNb2allrefByToRefcallContaining(toRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcallContaining(String toRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByToRefcallContaining", startResult, maxRows, toRefcall);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcall(String toRefcall) throws DataAccessException {

		return findVConvertposNb2allrefByToRefcall(toRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcall(String toRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByToRefcall", startResult, maxRows, toRefcall);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToContigId
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToContigId(BigDecimal toContigId) throws DataAccessException {

		return findVConvertposNb2allrefByToContigId(toContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToContigId(BigDecimal toContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByToContigId", startResult, maxRows, toContigId);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToOrganismId(java.math.BigDecimal toOrganismId) throws DataAccessException {

		return findVConvertposNb2allrefByToOrganismId(toOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToOrganismId(java.math.BigDecimal toOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByToOrganismId", startResult, maxRows, toOrganismId);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcall(String fromRefcall) throws DataAccessException {

		return findVConvertposNb2allrefByFromRefcall(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcall(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByFromRefcall", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromPosition
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromPosition(BigDecimal fromPosition) throws DataAccessException {

		return findVConvertposNb2allrefByFromPosition(fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromPosition(BigDecimal fromPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByFromPosition", startResult, maxRows, fromPosition);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByPrimaryKey
	 *
	 */
	@Transactional
	public VConvertposNb2allref findVConvertposNb2allrefByPrimaryKey(BigDecimal snpFeatureId) throws DataAccessException {

		return findVConvertposNb2allrefByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByPrimaryKey
	 *
	 */

	@Transactional
	public VConvertposNb2allref findVConvertposNb2allrefByPrimaryKey(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposNb2allrefByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allref) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromOrganismId(BigDecimal fromOrganismId) throws DataAccessException {

		return findVConvertposNb2allrefByFromOrganismId(fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromOrganismId(BigDecimal fromOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByFromOrganismId", startResult, maxRows, fromOrganismId);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromContigId
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromContigId(BigDecimal fromContigId) throws DataAccessException {

		return findVConvertposNb2allrefByFromContigId(fromContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromContigId(BigDecimal fromContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByFromContigId", startResult, maxRows, fromContigId);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefBySnpFeatureId
	 *
	 */
	@Transactional
	public VConvertposNb2allref findVConvertposNb2allrefBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException {

		return findVConvertposNb2allrefBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefBySnpFeatureId
	 *
	 */

	@Transactional
	public VConvertposNb2allref findVConvertposNb2allrefBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposNb2allrefBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allref) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcallContaining(String fromRefcall) throws DataAccessException {

		return findVConvertposNb2allrefByFromRefcallContaining(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcallContaining(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByFromRefcallContaining", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVConvertposNb2allrefs
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findAllVConvertposNb2allrefs() throws DataAccessException {

		return findAllVConvertposNb2allrefs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVConvertposNb2allrefs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findAllVConvertposNb2allrefs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVConvertposNb2allrefs", startResult, maxRows);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToPosition
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToPosition(BigDecimal toPosition) throws DataAccessException {

		return findVConvertposNb2allrefByToPosition(toPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefByToPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToPosition(BigDecimal toPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefByToPosition", startResult, maxRows, toPosition);
		return new LinkedHashSet<VConvertposNb2allref>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VConvertposNb2allref entity) {
		return true;
	}

	@Override
	public MultiReferenceConversion convertPosition(
			MultiReferenceConversion fromPos, String toReference,
			String toContig) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
//
//	@Override
//	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus,
//			String toReference, String toContig) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public VariantStringData convertReferencePositions(
//			VariantStringData variantstringdataNPB,
//			MultiReferenceLocus npbMultirefLocus,
//			MultiReferenceLocus origMultiReferenceLocus, String toContig,
//			boolean isOtherRefs) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

	private List<VConvertposNb2allref> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		try { 
			List listResult = getSession().createSQLQuery(sql).addEntity(VConvertposNb2allref.class).list();
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
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	private  List<VConvertposNb2allref> findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig(BigDecimal fromOrg, String fromContig, Long fromPositionStart, Long fromPositionEnd, BigDecimal toOrg, String toContig, Long toPosStart, Long toPosEnd) throws DataAccessException {


		/*
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
		*/
		
		Scaffold fromScaffold = scaffolddao.getScaffold(fromContig, fromOrg);

		//int contig_id= Integer.valueOf(AppContext.guessChrFromString(fromContig))+2;
		String sql = "select * from v_convertpos_nb2allref c where c.from_contig_id="  + fromScaffold.getFeatureId()
					+  " and c.from_position between " + fromPositionStart + " and " + fromPositionEnd;
		
		if(toOrg!=null) {
			sql += " and c.to_organism_id=" + toOrg;
		}
		
		if(toContig!=null) {
			Scaffold toScaffold = scaffolddao.getScaffold(toContig, toOrg);
			sql += " and c.to_contig_id=" + toScaffold.getFeatureId(); 
			if(toPosStart!=null && toPosEnd!=null)
				sql += " and c.to_position between " + toPosStart + " and " + toPosEnd;
		}
		sql+=" order by c.snp_feature_id";

		//AppContext.debug(queryConversionOld( fromOrg,  fromContig,  fromPositionStart,  toOrg,  toContig));
		//AppContext.debug(queryConversionOld( fromOrg,  fromContig,  fromPositionEnd,  toOrg,  toContig));
		
		return executeSQL(sql);
	}
	
	
	@Override
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus, String toReference, String toContig)  throws Exception {
		// TODO Auto-generated method stub

		Map<String,Organism> mapName2Org = orgdao.getMapName2Organism();
		
		if( mapName2Org.get(fromLocus.getOrganism()).getName().equals(  AppContext.getDefaultOrganism()) ) throw new RuntimeException("sorce organism=" +  mapName2Org.get(fromLocus.getOrganism()).getName() + " not NB");
		
		List listConv = findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig( mapName2Org.get(fromLocus.getOrganism()).getOrganismId() ,  fromLocus.getContig(), fromLocus.getFmin().longValue(),fromLocus.getFmax().longValue(), 
					mapName2Org.get(toReference).getOrganismId(), null, null, null);

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
		
		orgdao.getMapName2Organism().get( npbMultirefLocus.getOrganism()  ).getOrganismId();
		
		//queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPos().longValue(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
		
		
		List setConversions = findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig( orgdao.getMapName2Organism().get( npbMultirefLocus.getOrganism()  ).getOrganismId() ,
				npbMultirefLocus.getContig(), npbstartpos.longValue(), npbendpos.longValue(), 
				orgdao.getMapName2Organism().get( origMultiReferenceLocus.getOrganism()  ).getOrganismId(), toContig, 
				origMultiReferenceLocus.getFmin().longValue(), origMultiReferenceLocus.getFmax().longValue());
			
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
	
	
	
	
	
	
	
	
}
