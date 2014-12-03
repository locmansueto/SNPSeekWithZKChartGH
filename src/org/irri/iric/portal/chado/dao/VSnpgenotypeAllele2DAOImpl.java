package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
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
import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;
import org.irri.iric.portal.chado.domain.VSnpgenotypeAllele2;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.SnpsHeteroAllele2;
import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpgenotypeAllele2 entities.
 * 
 */
//@Repository("VSnpgenotypeAllele2DAO")
@Repository("SnpsHeteroAllvarsDAO")
@Transactional
public class VSnpgenotypeAllele2DAOImpl extends AbstractJpaDao<VSnpgenotypeAllele2>
		implements VSnpgenotypeAllele2DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpgenotypeAllele2.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpgenotypeAllele2DAOImpl
	 *
	 */
	public VSnpgenotypeAllele2DAOImpl() {
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
	 * JPQL Query - findVSnpgenotypeAllele2BySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2BySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpgenotypeAllele2BySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2BySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2BySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpgenotypeAllele2BySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnpgenotypeAllele2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpgenotypeAllele2 findVSnpgenotypeAllele2ByPrimaryKey(Integer snpFeatureId, Integer iricStockId) throws DataAccessException {

		return findVSnpgenotypeAllele2ByPrimaryKey(snpFeatureId, iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpgenotypeAllele2 findVSnpgenotypeAllele2ByPrimaryKey(Integer snpFeatureId, Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpgenotypeAllele2ByPrimaryKey", startResult, maxRows, snpFeatureId, iricStockId);
			return (org.irri.iric.portal.chado.domain.VSnpgenotypeAllele2) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2Containing
	 *
	 */
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2Containing(String allele2) throws DataAccessException {

		return findVSnpgenotypeAllele2ByAllele2Containing(allele2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2Containing(String allele2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpgenotypeAllele2ByAllele2Containing", startResult, maxRows, allele2);
		return new LinkedHashSet<VSnpgenotypeAllele2>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpgenotypeAllele2s
	 *
	 */
	@Transactional
	public Set<VSnpgenotypeAllele2> findAllVSnpgenotypeAllele2s() throws DataAccessException {

		return findAllVSnpgenotypeAllele2s(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpgenotypeAllele2s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpgenotypeAllele2> findAllVSnpgenotypeAllele2s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpgenotypeAllele2s", startResult, maxRows);
		return new LinkedHashSet<VSnpgenotypeAllele2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2
	 *
	 */
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2(String allele2) throws DataAccessException {

		return findVSnpgenotypeAllele2ByAllele2(allele2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2(String allele2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpgenotypeAllele2ByAllele2", startResult, maxRows, allele2);
		return new LinkedHashSet<VSnpgenotypeAllele2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByIricStockId
	 *
	 */
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByIricStockId(Integer iricStockId) throws DataAccessException {

		return findVSnpgenotypeAllele2ByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpgenotypeAllele2ByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<VSnpgenotypeAllele2>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpgenotypeAllele2 entity) {
		return true;
	}

	@Override
	public Set<SnpsHeteroAllele2> findSnpsHeteroVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars, BigDecimal type)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findSnpsHeteroAllvars( chr, start, end, null, vars, type);
	}

	@Override
	public Set<SnpsHeteroAllele2> findSnpsHeteroAllvarsChrPosBetween(
			Integer chr, BigDecimal start, BigDecimal end, BigDecimal type)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findSnpsHeteroAllvars( chr, start, end, null, null, type);
	}

	@Override
	public Set<SnpsHeteroAllele2> findSnpsHeteroVarsChrPosIn(Integer chr,
			Collection<BigDecimal> snps, Collection<BigDecimal> vars, BigDecimal type)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findSnpsHeteroAllvars( chr, null, null, snps, vars, type);
	}

	@Override
	public Set<SnpsHeteroAllele2> findSnpsHeteroAllvarsChrPosIn(Integer chr,
			Collection<BigDecimal> snps, BigDecimal type) throws DataAccessException {
		// TODO Auto-generated method stub
		return findSnpsHeteroAllvars( chr, null, null, snps, null, type); 
	}
	
	
	/*
	static public Map getMapVar2Snpid2Allele2(Set setAllele2)
	{
		Map mapVar2Snps = new HashMap();
		Iterator<SnpsHeteroAllele2> itSnps = setAllele2.iterator();
		while(itSnps.hasNext()) {
			SnpsHeteroAllele2 allele2 = itSnps.next();
			mapVar2Snps.get(allele2.getVar());
		}
		
	}
	*/
	
	private Set<SnpsHeteroAllele2> findSnpsHeteroAllvars(Integer chr, BigDecimal start, BigDecimal end,
			Collection<BigDecimal> snpspos, Collection<BigDecimal> vars, BigDecimal type) throws DataAccessException {
		// TODO Auto-generated method stub
		

		String sql = "select sg.snp_feature_id, sg.iric_stock_id, sg.allele2 from iric.snp_genotype sg "; 
		
		if(type==SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
			sql += ", iric.core_snp cs where sg.snp_feature_id=cs.snp_feature_id ";	
		}
		else if (type==SnpcoreRefposindexDAO.TYPE_3KALLSNP)
		{
			sql += " where 1=1 ";	
		};
		
		
		/*
		String snpfeatureid = "1";
		if(chr<10)
			snpfeatureid += "0" + chr;
		else
			snpfeatureid += chr;
		*/
		String sqlsnpfeaturid = "";
		if( start!= null && end!= null)
		{
			//sqlsnpfeaturid = " and snp_feature_id between " + snpfeatureid + String.format("%08d", start.longValue() ) + " and " +  snpfeatureid + String.format("%08d", end.longValue()); 
			sqlsnpfeaturid = " and sg.snp_feature_id between " + AppContext.convertRegion2Snpfeatureid(chr, start.longValue()) + " and " +  AppContext.convertRegion2Snpfeatureid(chr, end.longValue()) ;
		} else if (snpspos!=null) {
			
			
			List listIds = AppContext.setSlicerIds(new HashSet(snpspos));
			
			/*
			StringBuffer buff = new StringBuffer();
			Iterator<BigDecimal> itSnpid = snpspos.iterator();
			while(itSnpid.hasNext()) {
				//buff.append( snpfeatureid + itSnpid.next() );
				buff.append(  AppContext.convertRegion2Snpfeatureid(chr, itSnpid.next().longValue() ) );
				if( itSnpid.hasNext() ) buff.append(",");
			}
			sqlsnpfeaturid = " and sg.snp_feature_id in (" +  buff.toString() + ")";
			*/
			if(listIds.size()==1)
				sqlsnpfeaturid = " and sg.snp_feature_id in ( "  + listIds.get(0) + ") ";
			else if(listIds.size()>1) {
				sqlsnpfeaturid = " and ( sg.snp_feature_id in ( "  + listIds.get(0) + ") ";
				sqlsnpfeaturid += " or sg.snp_feature_id in ( "  + listIds.get(1) + ") ";
				if(listIds.size()>2) 
					sqlsnpfeaturid += " or sg.snp_feature_id in ( "  + listIds.get(2) + ") ";
				if(listIds.size()>3) 
				    sqlsnpfeaturid += " or sg.snp_feature_id in ( "  + listIds.get(3) + ") ";
				sqlsnpfeaturid += " ) ";
			}
		}
		
		
		sql += " and sg.typ='e' and sg.allele2 is not null and sg.partition_id = " + (chr+2) + sqlsnpfeaturid;
		
		if(vars!=null)
		{
			StringBuffer buff = new StringBuffer();
			Iterator<BigDecimal> itVar = vars.iterator();
			while(itVar.hasNext()) {
				buff.append(itVar.next() );
				if( itVar.hasNext() ) buff.append(",");
			}
			sql += " and sg.iric_stock_id in (" +  buff.toString() + ")";
		}
		
		
		return new HashSet(executeSQL(sql));
	}
	
	
	private List<SnpsHeteroAllele2> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		
		return getSession().createSQLQuery(sql).addEntity(  VSnpgenotypeAllele2.class ).list();
		
		//return getSession().get	
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
	
}
