package org.irri.iric.portal.chado.oracle.dao;

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
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VSnpGenotypeHdra;
import org.irri.iric.portal.chado.oracle.domain.VSnpRefposHdra;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpRefposHdra entities.
 * 
 */
@Repository("VSnpRefposHdraDAO")
@Transactional
public class VSnpRefposHdraDAOImpl extends AbstractJpaDao<VSnpRefposHdra>
		implements VSnpRefposHdraDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpRefposHdra.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpRefposHdraDAOImpl
	 *
	 */
	public VSnpRefposHdraDAOImpl() {
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
	 * JPQL Query - findVSnpRefposHdraByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpRefposHdra findVSnpRefposHdraByPrimaryKey(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpRefposHdraByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpRefposHdra findVSnpRefposHdraByPrimaryKey(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpRefposHdraByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VSnpRefposHdra) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcall
	 *
	 */
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcall(String refcall) throws DataAccessException {

		return findVSnpRefposHdraByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposHdraByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposHdra>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposHdraBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpRefposHdra findVSnpRefposHdraBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpRefposHdraBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposHdraBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpRefposHdra findVSnpRefposHdraBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpRefposHdraBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VSnpRefposHdra) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByChromosome
	 *
	 */
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByChromosome(BigDecimal chromosome) throws DataAccessException {

		return findVSnpRefposHdraByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByChromosome(BigDecimal chromosome, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposHdraByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VSnpRefposHdra>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByTypeId
	 *
	 */
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByTypeId(BigDecimal typeId) throws DataAccessException {

		return findVSnpRefposHdraByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposHdraByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VSnpRefposHdra>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpRefposHdras
	 *
	 */
	@Transactional
	public Set<VSnpRefposHdra> findAllVSnpRefposHdras() throws DataAccessException {

		return findAllVSnpRefposHdras(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpRefposHdras
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposHdra> findAllVSnpRefposHdras(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpRefposHdras", startResult, maxRows);
		return new LinkedHashSet<VSnpRefposHdra>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByPosition
	 *
	 */
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByPosition(BigDecimal position) throws DataAccessException {

		return findVSnpRefposHdraByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByPosition(BigDecimal position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposHdraByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpRefposHdra>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcallContaining
	 *
	 */
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcallContaining(String refcall) throws DataAccessException {

		return findVSnpRefposHdraByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposHdraByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposHdra>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpRefposHdra entity) {
		return true;
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type) {
		// TODO Auto-generated method stub
		
		return getSNPs(chromosome,startPos, endPos,
				type, -1,-1);
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		if(!type.equals(SnpsAllvarsPosDAO.TYPE_HDRASNP)) throw new RuntimeException("type=" + type + " for TYPE_HDRASNP");
		Query query = createNamedQuery("findVSnpRefposHdraByChrPosBetween", firstRow, maxRows, BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chromosome))), BigDecimal.valueOf(startPos),BigDecimal.valueOf(endPos));
		return query.getResultList();

	}

	

	private List executeSQL(String sql) 
	{
		/*if(AppContext.isLocalhost()) AppContext.debug("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VSnpRefposHdra.class).list();
		*/
		return AppContext.executeSQL(entityManager, VSnpRefposHdra.class, sql);
	}
	

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public List getSNPsInChromosome(String chr, Collection posset,
			BigDecimal type) {
		// TODO Auto-generated method stub
		/*
		if(!type.equals(SnpsAllvarsPosDAO.TYPE_HDRASNP)) throw new RuntimeException("type=" + type + " for TYPE_HDRASNP");
		Query query = createNamedQuery("findVSnpRefposHdraByChrPosIn", -1, -1, posset);
		return query.getResultList();
		*/
		
		BigDecimal bdChr=null;
		if(chr.toLowerCase().equals("any")) {
			
			long poscount=0;
			List listPresent = new ArrayList();
			AppContext.debug("checking " + posset.size() + " snp positions");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			String sql = ""; //select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from IRIC.V_SNP_REFPOSINDEX srp WHERE 1=1 and (";
			Iterator<String> itContig= mapChr2Pos.keySet().iterator();
			while(itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection setPos = (Collection)mapChr2Pos.get(contigstr);
				poscount+=setPos.size();
				
				Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos), 900);
				for(int iset=0; iset<slicedset.length; iset++) {
				
					sql += " select * from ( select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL from IRIC.V_SNP_REFPOS_HDRA srp WHERE 1=1 and (";

				//Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos));
					sql+= "( srp.TYPE_ID=" + type + " and srp.chromosome=" + contig + " and exists (select column_value from table(sys.odcinumberlist(" + slicedset[iset].toString().replace("[", "").replace("]", "") + " )) t where t.column_value=srp.position ))) order by srp.position) ";
					
					if(iset<slicedset.length-1) sql += " union ";
				}
				if(itContig.hasNext()) 
					sql += " union ";
			};
			
			sql += " order by 3,4";
			
			
			/*
			with names (fname,lname) as (
				    values
				        ('John','Smith'),
				        ('Mary','Jones')
				)
			select city from user
				    inner join names on
				        fname=firstName and
				        lname=lastName;
			
			*/
			AppContext.debug("querying  V_SNP_REFPOS_HDRA with " + poscount + " positions");
			return executeSQL(sql);
		}
		else if(chr.toLowerCase().equals("loci")) {
			
			long poscount=0;
			AppContext.debug("checking " + posset.size() + " loci");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);
			String sql = "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL from IRIC.V_SNP_REFPOS_HDRA WHERE 1=1 and (";
			Iterator<String> itContig= mapChr2Pos.keySet().iterator();
			while(itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection<Locus> setLocus = (Collection)mapChr2Pos.get(contigstr);
				poscount+=setLocus.size();
				Iterator<Locus> itLocus=setLocus.iterator();
				while(itLocus.hasNext()) {
					Locus loc=itLocus.next();
					sql+= "( chromosome=" + contig + " and position between " + loc.getFmin() + " and " + loc.getFmax() + ") ";
					if(itLocus.hasNext()) 
						sql += " or ";
				}
				if(itContig.hasNext()) 
					sql += " or ";

			};
			
			sql += ") and TYPE_ID=" + type + " order by CHROMOSOME, POSITION";
			
			AppContext.debug("querying  V_SNP_REFPOS_HDRA with " + poscount + " loci");

			return executeSQL(sql);
		}
		else {
			
			AppContext.debug("querying  V_SNP_REFPOS_HDRA with " + posset.size() + " positions");
			try {
				chr = chr.toUpperCase().replace("CHR0","").replace("CHR","");
				bdChr = BigDecimal.valueOf(Long.valueOf(chr));
				
				String sql = ""; //select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from IRIC.V_SNP_REFPOSINDEX srp WHERE 1=1 and (";
				Set slicedset[] = AppContext.setSlicer(new TreeSet(posset), 900);
				for(int iset=0; iset<slicedset.length; iset++) {
					sql += " select * from ( select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL from IRIC.V_SNP_REFPOS_HDRA srp WHERE 1=1 and (";
					sql+= "( srp.TYPE_ID=" + type + " and srp.chromosome=" + chr + " and exists (select column_value from table(sys.odcinumberlist(" + slicedset[iset].toString().replace("[", "").replace("]", "") + " )) t where t.column_value=srp.position ))) order by srp.position) ";
					if(iset<slicedset.length-1) sql += " union ";
				}
				sql += " order by 3,4";				
				
				return executeSQL(sql);
				
				/*
				Set slicedset[] = AppContext.setSlicer(new TreeSet(posset));
				List listPresent = new ArrayList();
				for(int iset=0; iset<slicedset.length; iset++) {
					
					Query query = createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1, bdChr ,  slicedset[iset], type);
					listPresent.addAll( query.getResultList() );
				}
				return listPresent;
				*/

			} catch ( Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return new ArrayList();

	}
	
	
	
	
}
