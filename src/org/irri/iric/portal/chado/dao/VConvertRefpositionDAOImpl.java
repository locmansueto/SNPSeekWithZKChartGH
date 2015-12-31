package org.irri.iric.portal.chado.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VConvertRefposition;
import org.irri.iric.portal.dao.OrganismDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceConversionImpl;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsPosImpl;
import org.irri.iric.portal.genotype.VariantIndelStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VConvertRefposition entities.
 * 
 */
//@Repository("VConvertRefpositionDAO")
@Repository("MultipleReferenceConverterOldDAO")
@Transactional
public class VConvertRefpositionDAOImpl extends AbstractJpaDao<VConvertRefposition>
		implements VConvertRefpositionDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VConvertRefposition.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VConvertRefpositionDAOImpl
	 *
	 */
	public VConvertRefpositionDAOImpl() {
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
	 * JPQL Query - findVConvertRefpositionByFromPosition
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromPosition(Integer fromPosition) throws DataAccessException {

		return findVConvertRefpositionByFromPosition(fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromPosition(Integer fromPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByFromPosition", startResult, maxRows, fromPosition);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToContigContaining
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToContigContaining(String toContig) throws DataAccessException {

		return findVConvertRefpositionByToContigContaining(toContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToContigContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToContigContaining(String toContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByToContigContaining", startResult, maxRows, toContig);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToContig
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToContig(String toContig) throws DataAccessException {

		return findVConvertRefpositionByToContig(toContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToContig
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToContig(String toContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByToContig", startResult, maxRows, toContig);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToContigFeatureId
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToContigFeatureId(java.math.BigDecimal toContigFeatureId) throws DataAccessException {

		return findVConvertRefpositionByToContigFeatureId(toContigFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToContigFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToContigFeatureId(java.math.BigDecimal toContigFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByToContigFeatureId", startResult, maxRows, toContigFeatureId);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVConvertRefpositions
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findAllVConvertRefpositions() throws DataAccessException {

		return findAllVConvertRefpositions(-1, -1);
	}

	/**
	 * JPQL Query - findAllVConvertRefpositions
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findAllVConvertRefpositions(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVConvertRefpositions", startResult, maxRows);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromOrganismId(Integer fromOrganismId) throws DataAccessException {

		return findVConvertRefpositionByFromOrganismId(fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromOrganismId(Integer fromOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByFromOrganismId", startResult, maxRows, fromOrganismId);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromContig
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromContig(String fromContig) throws DataAccessException {

		return findVConvertRefpositionByFromContig(fromContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromContig
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromContig(String fromContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByFromContig", startResult, maxRows, fromContig);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigFeatureId
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigFeatureId(Integer fromContigFeatureId) throws DataAccessException {

		return findVConvertRefpositionByFromContigFeatureId(fromContigFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigFeatureId(Integer fromContigFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByFromContigFeatureId", startResult, maxRows, fromContigFeatureId);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigContaining
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigContaining(String fromContig) throws DataAccessException {

		return findVConvertRefpositionByFromContigContaining(fromContig, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigContaining(String fromContig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByFromContigContaining", startResult, maxRows, fromContig);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByPrimaryKey
	 *
	 */
	@Transactional
	public VConvertRefposition findVConvertRefpositionByPrimaryKey(Integer fromOrganismId, Integer fromContigFeatureId, Integer fromPosition) throws DataAccessException {

		return findVConvertRefpositionByPrimaryKey(fromOrganismId, fromContigFeatureId, fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByPrimaryKey
	 *
	 */

	@Transactional
	public VConvertRefposition findVConvertRefpositionByPrimaryKey(Integer fromOrganismId, Integer fromContigFeatureId, Integer fromPosition, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertRefpositionByPrimaryKey", startResult, maxRows, fromOrganismId, fromContigFeatureId, fromPosition);
			return (org.irri.iric.portal.chado.domain.VConvertRefposition) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToOrganismId(java.math.BigDecimal toOrganismId) throws DataAccessException {

		return findVConvertRefpositionByToOrganismId(toOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToOrganismId(java.math.BigDecimal toOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByToOrganismId", startResult, maxRows, toOrganismId);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToPosition
	 *
	 */
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToPosition(java.math.BigDecimal toPosition) throws DataAccessException {

		return findVConvertRefpositionByToPosition(toPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertRefpositionByToPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertRefposition> findVConvertRefpositionByToPosition(java.math.BigDecimal toPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertRefpositionByToPosition", startResult, maxRows, toPosition);
		return new LinkedHashSet<VConvertRefposition>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VConvertRefposition entity) {
		return true;
	}

	private List queryConversion(BigDecimal fromOrg, String fromContig, BigDecimal fromPosition, BigDecimal toOrg, String toContig) {
		return queryConversion( fromOrg,  fromContig,  fromPosition.longValue(),  toOrg,  toContig);
	}
	private List queryConversion(BigDecimal fromOrg, String fromContig, Long fromPosition, BigDecimal toOrg, String toContig) {
	
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
			sql = "select  CONTIG2 AS FROM_CONTIG,  ORGANISM_ID2 AS FROM_ORGANISM_ID,  CONTIG2_FEATURE_ID AS FROM_CONTIG_FEATURE_ID, " + fromPosition + " AS FROM_POSITION, '' AS FROM_REFCALL, CONTIG1 AS TO_CONTIG, ORGANISM_ID1 as TO_ORGANISM_ID, CONTIG1_FEATURE_ID TO_CONTIG_FEATURE_ID, " 
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
					 	+ " START2+(" + fromPosition + "-START1) as TO_POSITION, '' AS TO_REFCALL "
						+ " from iric.REFERENCE_ALIGNMENT " 
						+ " where  ORGANISM_ID2=" + toOrg + " and ORGANISM_ID1=" + fromOrg 
						+ "	and CONTIG1='" + fromContig +  "' and " + fromPosition + " between START1 and STOP1 ";
						
						if(toContig!=null && !toContig.isEmpty())
							sql += " and CONTIG2='" + toContig + "'";
						
			sql += " order by TO_POSITION" ;
						
		}
		
		return executeSQL(sql);
		
	}
	
	@Override
	public MultiReferenceConversion convertPosition(
			MultiReferenceConversion fromPos, String toReference, String toContig) throws Exception {
		// TODO Auto-generated method stub
		
		
		List listConv = queryConversion(   getOrganismId( fromPos.getToOrganism() ) , fromPos.getToContig(), fromPos.getToPosition(), getOrganismId( toReference ), toContig);
		if(listConv.size()==0) throw new RuntimeException("No reference coordinate conversion to " + toReference + " found");
		if(listConv.size()>1) throw new RuntimeException("Multiple (" + listConv.size() + ") reference coordinate conversions to " + toReference + " found. (overlapped)");
		VConvertRefposition coversion = (VConvertRefposition)listConv.get(0);
		
		AppContext.debug("convert pos: " + fromPos  + " to " + coversion );
		
		MultiReferenceConversion to = new MultiReferenceConversionImpl( toReference, coversion.getToContig(), coversion.getToPosition() );
		AppContext.debug("convert pos: " + fromPos  + " to " + to );
		return to;
	}

	@Override
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus,
			String toReference, String toContig)  throws Exception {
		// TODO Auto-generated method stub

		List listConv = queryConversion( getOrganismId( fromLocus.getOrganism() ) , fromLocus.getContig(), BigDecimal.valueOf(fromLocus.getFmin()), getOrganismId( toReference ), toContig);
		if(listConv.size()==0) throw new RuntimeException("No reference coordinate conversion at start (" + fromLocus.getFmin() + ") to " + toReference + " found");
		//if(listConv.size()>1) throw new RuntimeException("Multiple (" + listConv.size() + ") reference coordinate conversions at start to " + toReference + " found. (overlapped)");
		VConvertRefposition mindiff_VConvertRefposition = null;
		if(listConv.size()>1) {
			AppContext.debug("Multiple " + listConv + " reference coordinate conversions at start (" + fromLocus.getFmin() + ") ... will use lowest");
			/*
			long mindiff = Long.MAX_VALUE;
			Iterator<VConvertRefposition> itConv = listConv.iterator();
			while(itConv.hasNext()) {
				VConvertRefposition refpos = itConv.next(); 
				long diff = refpos.getToPosition().longValue() - fromLocus.getStart().longValue();
				if( Math.abs(diff)<mindiff ) {
					mindiff = Math.abs(diff);
					mindiff_VConvertRefposition = refpos;
				}
			}
			*/
			mindiff_VConvertRefposition = (VConvertRefposition)listConv.get(0);
		} else {
			mindiff_VConvertRefposition = (VConvertRefposition)listConv.get(0);
		}
		
		VConvertRefposition coversionStart = mindiff_VConvertRefposition;

		AppContext.debug("coversionStart:" + coversionStart);
		
		List listConv2 = queryConversion( getOrganismId( fromLocus.getOrganism() ) , fromLocus.getContig(), BigDecimal.valueOf(fromLocus.getFmax()), getOrganismId( toReference ), toContig);
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
				long diff = refpos.getToPosition().longValue() - fromLocus.getEnd().longValue();
				if( Math.abs(diff)<mindiff ) {
					mindiff = Math.abs(diff);
					mindiff_VConvertRefposition = refpos;
				}
			}
			*/
			mindiff_VConvertRefposition = (VConvertRefposition)listConv2.get( listConv2.size()-1 );
		} else {
			mindiff_VConvertRefposition = (VConvertRefposition)listConv2.get(0);
		}
		
		
		VConvertRefposition coversionEnd = (VConvertRefposition)mindiff_VConvertRefposition;
		
		AppContext.debug("coversionEnd:" + coversionEnd);
		
		MultiReferenceLocus toLocus=null; 
		
		try {
		
			AppContext.debug("creating locus 1");
			if(coversionEnd==null) AppContext.debug("coversionEnd==null");
			AppContext.debug("creating locus 1b");
			if(coversionStart==null) AppContext.debug("coversionStart==null");
			AppContext.debug("creating locus 1c");
			if(coversionEnd.getToContig()==null) AppContext.debug("coversionEndgetToContig()==null");
			AppContext.debug("creating locus 1d");
			if(coversionStart.getToContig()==null) AppContext.debug("coversionStartgetToContig()==null");
			AppContext.debug("creating locus 1e");


			
			if(!coversionEnd.getToContig().equals(coversionStart.getToContig())) {
				throw new RuntimeException("Aligned contig at start (" + coversionStart.getToContig() + ") is not the same as aligned contig at end (" + coversionEnd.getToContig() + " )");
			}
			AppContext.debug("creating locus 2");

			Long strand = 1L;
			if(coversionStart.getToPosition().longValue() > coversionEnd.getToPosition().longValue() )
				strand = -1L;
			
			AppContext.debug("creating locus 3");

			toLocus = new MultiReferenceLocusImpl( toReference, coversionStart.getToContig(), coversionStart.getToPosition().intValue() , coversionEnd.getToPosition().intValue(), strand.intValue() );
			AppContext.debug("convert locus:" + fromLocus + " to " + toLocus );
		
		//} catch( InvocationTargetException ex) {
		//	ex.getCause().printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		return toLocus;
		
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
			if(!snppos.getPosition().remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
				// Position has fractional part, .. insertion region
				
				listConv = queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPosition().longValue(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
				
				if(toContig!=null && !toContig.isEmpty()) {
					List filteredList = new java.util.ArrayList();	
					Iterator<VConvertRefposition> itConv = listConv.iterator();
					while(itConv.hasNext()) {
						VConvertRefposition conv = itConv.next();
						if(!conv.getToContig().equals( toContig )) continue;
						
						if(conv.getToPosition().longValue() <= origMultiReferenceLocus.getFmax().longValue()  && conv.getToPosition().longValue() >= origMultiReferenceLocus.getFmin().longValue()) {
							// include
							//filteredList.add(conv);
						
							//conv.setFromPosition( conv.getFromPosition().add(  snppos.getPos().remainder(BigDecimal.ONE) ) );
							//conv.setToPosition( conv.getToPosition().add(  snppos.getPos().remainder(BigDecimal.ONE) ) );
							
							BigDecimal to = conv.getToPosition().add(  snppos.getPosition().remainder(BigDecimal.ONE) );
							AppContext.debug("insertion region:"  + snppos.getPosition() + " to " + to);
							
							MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), to);
							filteredList.add(coversion);
						} else {
							AppContext.debug("SNP npb:" + snppos.getPosition() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus );
							strMessage.append("SNP npb:" + snppos.getPosition() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus +"\n" );
						}

					}
					listConv = filteredList;
				}
			}
			else  {
				
				// Position has no fractional part, .. anchor point or deletion region
				
				listConv = queryConversion( getOrganismId(  npbMultirefLocus.getOrganism() ) , npbMultirefLocus.getContig(), snppos.getPosition().longValueExact(),  getOrganismId( origMultiReferenceLocus.getOrganism() ), toContig);
			
				if(toContig!=null && !toContig.isEmpty()) {
					List filteredList = new java.util.ArrayList();	
					Iterator<VConvertRefposition> itConv = listConv.iterator();
					while(itConv.hasNext()) {
						VConvertRefposition conv = itConv.next();
						if(!conv.getToContig().equals( toContig )) continue;
						
						if(conv.getToPosition().longValue() <= origMultiReferenceLocus.getFmax().longValue()  &&  conv.getToPosition().longValue() >= origMultiReferenceLocus.getFmin().longValue()) {
							// include
							//AppContext.debug("SNP, anchor point or deletion region:"  + snppos.getPos() + " to " + conv.getToPosition());
							filteredList.add(conv);
							
						} else
						{
							AppContext.debug("SNP npb:" + snppos.getPosition() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus );
							strMessage.append("SNP npb:" + snppos.getPosition() + "-->" + origMultiReferenceLocus.getOrganism() +":" + conv.getToPosition() + " not within query region "  + origMultiReferenceLocus+"\n" );
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
				AppContext.debug("No reference coordinate conversion at " + snppos.getPosition() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus );
				strMessage.append("No reference coordinate conversion at " + snppos.getPosition() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + "\n");
			}
			else if(listConv.size()>1) {
				Iterator<MultiReferenceConversion> itConv = listConv.iterator();
				while(itConv.hasNext()) {
					MultiReferenceConversion conv = itConv.next();
					
					if(npbMultirefLocus.getStrand()>0 && conv.getToPosition().doubleValue() >prevPos) {
						AppContext.debug("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPosition() + " from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest right from previous");
						strMessage.append("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPosition() + " from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest right from previous\n");
						MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), conv.getToPosition());
						//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
						//AppContext.debug("conv:" + conv);
						AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPosition() + " to " + coversion );
						mapMSU7Pos2ConvertedPos.put( snppos.getPosition(), coversion);
						prevPos = coversion.getToPosition().doubleValue();
						break;
					}
					else if(npbMultirefLocus.getStrand()<0 && conv.getToPosition().doubleValue() < prevPos) {
						AppContext.debug("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPosition() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest left from previous");
						strMessage.append("Multiple (" + listConv + ") reference coordinate conversions at " + snppos.getPosition() + "  from " +  npbMultirefLocus + " to " + origMultiReferenceLocus + " (overlapped).. will use nearest left from previous\n");
						MultiReferenceConversion coversion = new MultiReferenceConversionImpl( origMultiReferenceLocus.getOrganism(), conv.getToContig(), conv.getToPosition());
						//mapMSU7toNewref.put(snppos.getPos() ,  new SnpsAllvarsPosImpl(BigDecimal.valueOf(val)coversion.getPosition(), snppos.getRefnuc()) );
						//AppContext.debug("conv:" + conv);
						AppContext.debug("convert snp pos: from NPB " + npbMultirefLocus.getContig() + " " + snppos.getPosition() + " to " + coversion );
						mapMSU7Pos2ConvertedPos.put( snppos.getPosition(), coversion);
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
				
				mapMSU7Pos2ConvertedPos.put( snppos.getPosition(), coversion);
				
				prevPos = coversion.getToPosition().doubleValue();
			}
			
			
		}
		
		AppContext.debug("convertReferencePositions: isNPB=" + variantstringdataNPB.isNipponbareReference() + "; msg=" + variantstringdataNPB.getMessage());
		
		throw new RuntimeException("should not be reached");
		
		/*
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
		*/
		
	}







	@Autowired
	//@Qualifier("OrganismDAO")
	//@Qualifier("OrganismDAOPostges")
	private OrganismDAO organismdao;
	
	//private Map<String,BigDecimal> mapOrgname2Id=null;

	
	private BigDecimal getOrganismId(String organism) {
		BigDecimal orgid=null;
		try{
			orgid = BigDecimal.valueOf( Long.valueOf(organism) );
		} catch (Exception ex) {
			
			return organismdao.getMapName2Organism().get(organism).getOrganismId();
			/*
			if(mapOrgname2Id==null) {
				mapOrgname2Id = new HashMap();
				organismdao = (OrganismDAO)AppContext.checkBean(organismdao, "OrganismDAO");
				Iterator<Organism> itOrgs = organismdao.getOrganisms().iterator();
				while(itOrgs.hasNext()) {
					Organism org = itOrgs.next();
					mapOrgname2Id.put(org.getName(), org.getOrganismId());
				}
			}
			return mapOrgname2Id.get(organism);
			*/
		}
		return orgid;
	}
	

	private List<VConvertRefposition> executeSQL(String sql) {
		
		try {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		List listResult = getSession().createSQLQuery(sql).addEntity(VConvertRefposition.class).list();
		AppContext.debug("result " + listResult.size() + " VConvertRefpositions");
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
