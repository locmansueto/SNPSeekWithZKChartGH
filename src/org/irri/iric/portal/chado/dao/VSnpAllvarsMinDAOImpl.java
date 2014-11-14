package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;
import org.irri.iric.portal.chado.domain.VSnpAllvarsMin;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvarsMin entities.
 * 
 */
@Repository("VSnpAllvarsMinDAO")
@Transactional
public class VSnpAllvarsMinDAOImpl extends AbstractJpaDao<VSnpAllvarsMin>
		implements VSnpAllvarsMinDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpAllvarsMin.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpAllvarsMinDAOImpl
	 *
	 */
	public VSnpAllvarsMinDAOImpl() {
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
	 * JPQL Query - findVSnpAllvarsMinByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpAllvarsMin findVSnpAllvarsMinByPrimaryKey(Integer var, Integer pos, Integer chr) throws DataAccessException {

		return findVSnpAllvarsMinByPrimaryKey(var, pos, chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpAllvarsMin findVSnpAllvarsMinByPrimaryKey(Integer var, Integer pos, Integer chr, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsMinByPrimaryKey", startResult, maxRows, var, pos, chr);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsMin) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByPos
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByPos(Integer pos) throws DataAccessException {

		return findVSnpAllvarsMinByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByPos(Integer pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnuc(String varnuc) throws DataAccessException {

		return findVSnpAllvarsMinByVarnuc(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnuc(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByVarnuc", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnucContaining(String varnuc) throws DataAccessException {

		return findVSnpAllvarsMinByVarnucContaining(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnucContaining(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByVarnucContaining", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByVar
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVar(Integer var) throws DataAccessException {

		return findVSnpAllvarsMinByVar(var, -1, -1);
	}

	
	
	
	/**
	 * JPQL Query - findVSnpAllvarsMinByVar
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVar(Integer var, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByVar", startResult, maxRows, var);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpAllvarsMins
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findAllVSnpAllvarsMins() throws DataAccessException {

		return findAllVSnpAllvarsMins(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpAllvarsMins
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findAllVSnpAllvarsMins(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpAllvarsMins", startResult, maxRows);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnuc(String refnuc) throws DataAccessException {

		return findVSnpAllvarsMinByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByChr
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByChr(Integer chr) throws DataAccessException {

		return findVSnpAllvarsMinByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsMinByChrVar(Integer chr, BigDecimal var) throws DataAccessException {
		//Query query = createNamedQuery("findVSnpAllvarsMinByChrVar", -1, -1, chr, var);
		//return new LinkedHashSet<SnpsAllvars>(query.getResultList());
		
		//String sql = "select /*+ index(sg snp_genotype_idx sg_snpfeatureid_idx)  index(sfl snp_featureloc_idx) */ sg.iric_stock_id as var, 4 as chr, 
	    //sfl.position as pos, '' as refnuc, case when sg.allele1 is null then '0' else sg.allele1 end as varnuc from iric.snp_featureloc sfl 

		String sql = "select /*+ parallel index(sg snp_genotype_idx sg_snpfeatureid_idx)  index(sfl snp_featureloc_idx) */ " + var + " as var, " + (chr+2) + " as partition_id, " 
	     + " sfl.position as pos, '' as refnuc, case when sg.allele1 is null then '0' else sg.allele1 end as varnuc from iric.snp_featureloc sfl " 
		 + " left join iric.snp_genotype sg " 
		+ " on sfl.snp_feature_id=sg.snp_feature_id"
		+ " where sg.partition_id=" + (chr+2) 
		+ " and sg.iric_stock_id=" + var
		+ " and sfl.srcfeature_id=" + (chr+2);
	    return new HashSet(executeSQL(sql));
	}
	
	
	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnucContaining(String refnuc) throws DataAccessException {

		return findVSnpAllvarsMinByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsMinByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvarsMin>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpAllvarsMin entity) {
		return true;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end) throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByChrPosBetween( chr, start,  end , -1, -1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
		//Query query = createNamedQuery("findVSnpAllvarsMinByChrPosBetween", startResult, maxRows, new Long(chr), start, end);
		//return new LinkedHashSet<SnpsAllvars>(query.getResultList());
		
				
				String sql = "select /*+ parallel index(sg snp_genotype_idx sg_snpfeatureid_idx)  index(sfl snp_featureloc_idx) */ sg.iric_stock_id as var, " + (chr+2) + " as partition_id, " 
					     + " sfl.position as pos, '' as refnuc, case when sg.allele1 is null then '0' else sg.allele1 end as varnuc from iric.snp_featureloc sfl " 
						 + " left join iric.snp_genotype sg " 
						+ " on sfl.snp_feature_id=sg.snp_feature_id"
						+ " where sg.partition_id=" + (chr+2) 
						//+ " and sg.iric_stock_id=" + var
						+ " and sfl.srcfeature_id=" + (chr+2)
						+ " and sfl.position between " + start.intValue()+1 + " and " + end;
					    return new HashSet(executeSQL(sql));		
		
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetween( chr,  start, end ,  vars,  false, -1, -1);
	}
	
	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		return  findVSnpAllvarsByVarsChrPosBetween( chr,  start, end ,  vars,  false, startResult,maxRows);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end) throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByChrPosBetweenRefmismatch( chr, start,  end , -1, -1);
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpAllvarsMinByChrPosBetweenRefmismatch", startResult, maxRows, new Long(chr), start, end);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetweenRefmismatch( chr,  start, end ,  vars, -1, -1);
	}

	//@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch2(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		//Query query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch", startResult, maxRows, new Long(chr), start, end, vars);
		Query query ;
		
		if(vars.size()>2000)
		{	
			java.util.Set set1 = new HashSet();
			Iterator it=vars.iterator();
			for(int i=0; i<1000; i++)
				set1.add(  it.next() );
			java.util.Set set2 = new HashSet();
			for(int i=0; i<1000; i++)
				set2.add(  it.next() );
			java.util.Set set3 = new HashSet();
			while(it.hasNext())
				set3.add(it.next());
			
			query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch3", startResult, maxRows, new Long(chr+2), start, end, set1, set2, set3);
			
		} else if(vars.size()>1000)
		{
			java.util.Set set1 = new HashSet();
			Iterator it=vars.iterator();
			for(int i=0; i<1000; i++)
				set1.add(  it.next() );
			java.util.Set set2 = new HashSet();
			while(it.hasNext())
				set2.add(it.next());
			
			query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch2", startResult, maxRows, new Long(chr+2), start, end, set1, set2);
			
		} else
			 query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch", startResult, maxRows, new Long(chr+2), start, end, vars);
		
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}
	
	
	
	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(
			Integer chr, BigDecimal start, BigDecimal end,
			Set<BigDecimal> vars, boolean isCore) {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetweenRefmismatch( chr,
				 start,  end, vars,  isCore,
				-1, -1) ;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end,
			Set<BigDecimal> vars, boolean isCore) {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetween( chr,
				 start,  end, vars,  isCore,
				-1, -1) ;
	}
	
	
	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(
			Integer chr, BigDecimal start, BigDecimal end,
			Collection<BigDecimal> vars, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnpAllvarsByVarsChrPosBetweenRefmismatch( chr,
				 start,  end, vars, false,
				 startResult,  maxRows) ;
	}

	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars, boolean isCore,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		//Query query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch", startResult, maxRows, new Long(chr), start, end, vars);
		Query query ;
		
		if(vars==null)
		{
			return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(new Long(chr+2), start, end, null,null,null, isCore);
		}
		else
		{
			Set setSlices[] = AppContext.setSlicer((Set)vars);
			if(setSlices.length==3)
				return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(new Long(chr+2), start, end, setSlices[0],setSlices[1],setSlices[2], isCore);
			else if(setSlices.length==2)
				return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(new Long(chr+2), start, end, setSlices[0],setSlices[1],null, isCore);
			else
				return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(new Long(chr+2), start, end, vars,null,null, isCore);
		}			
		/*
		if(vars.size()>2000)
		{	
			java.util.Set set1 = new HashSet();
			Iterator it=vars.iterator();
			for(int i=0; i<1000; i++)
				set1.add(  it.next() );
			java.util.Set set2 = new HashSet();
			for(int i=0; i<1000; i++)
				set2.add(  it.next() );
			java.util.Set set3 = new HashSet();
			while(it.hasNext())
				set3.add(it.next());
			return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(new Long(chr+2), start, end, set1,set2,set3);
					
		} else if(vars.size()>1000)
		{
			java.util.Set set1 = new HashSet();
			Iterator it=vars.iterator();
			for(int i=0; i<1000; i++)
				set1.add(  it.next() );
			java.util.Set set2 = new HashSet();
			while(it.hasNext())
				set2.add(it.next());
			
			return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(new Long(chr+2), start, end, set1,set2,null);
			
		} else
			
			return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(new Long(chr+2), start, end, vars,null,null);
		*/
		
	}
	
	private Set queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(Long partition_id, BigDecimal start, BigDecimal end, Collection set1, Collection set2, Collection set3, boolean isCore ) {
		return  queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch( partition_id,  start,  end,  set1,  set2,  set3,  isCore,  true);
	}

	/**
	 * same as VSnpAllVarsCountrefmismatchDAO.countMismatchesInvars
	 * and used as subquery here to use the cached result of VSnpAllVarsCountrefmismatchDAO.countMismatchesInvars  
	 * @param varIds
	 * @param chr
	 * @param start
	 * @param end
	 * @param isCore
	 * @return
	 */
	private String countMismatchesInvars(Set<BigDecimal> varIds, String chr, Integer start, Integer end, boolean isCore) {
		// TODO Auto-generated method stub
				
		List<String> listVaridSets= AppContext.setSlicerIds(varIds);
				
		
		String sql="";
		
		if(isCore) {
			sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, ROW_NUMBER() OVER (ORDER BY count(*) DESC, sg.iric_stock_id ) AS row_number, count(*) as mismatch  "
				+ " from iric.mv_core_snps sfl, iric.snp_genotype sg "
				+ "   where 1=1"
				+ "   and sg.snp_feature_id=sfl.snp_feature_id"
				+ "   and sfl.refcall<>sg.allele1 "
				+ "   and sfl.refcall is not null "
				+ "   and sg.allele1 is not null "
				+ "   and sg.partition_id=" +  ( Integer.valueOf(chr) + 2)
				+ "   and sfl.srcfeature_id=" +  ( Integer.valueOf(chr) + 2)
				+ "   and sfl.position between " + start.intValue()+1 + " and " + end ;
				 
				  if(listVaridSets.size()>0) 
					  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(0)  + ")";
				  if(listVaridSets.size()>1) 
					  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(1)  + ")";
				  if(listVaridSets.size()>2) 
					  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(2)  + ")";
				  
			 sql+= "   group by sg.iric_stock_id  "
				+ "   order by mismatch desc, sg.iric_stock_id"; 
			}
			else {	
			sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, ROW_NUMBER() OVER (ORDER BY count(*) DESC, sg.iric_stock_id ) AS row_number, count(*) as mismatch "
				  + " from iric.snp_feature sf, iric.snp_featureloc sfl, iric.snp_genotype sg"
				  + " where sf.snp_feature_id=sfl.snp_feature_id"
				  + " and sf.snp_feature_id=sg.snp_feature_id"
				  + " and sf.refcall<>sg.allele1"
				  + " and sf.refcall is not null"
				  + " and sg.allele1 is not null";
				  
				  if(listVaridSets.size()>0) 
					  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(0)  + ")";
				  if(listVaridSets.size()>1) 
					  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(1)  + ")";
				  if(listVaridSets.size()>2) 
					  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(2)  + ")";
				  
			    sql += " and partition_id=" + ( Integer.valueOf(chr) + 2)
				  + " and sfl.position between " + start.intValue()+1 + " and " + end
				  + " and sfl.srcfeature_id=" + ( Integer.valueOf(chr) + 2)
				  + " group by sg.iric_stock_id "
				  + " order by mismatch desc, sg.iric_stock_id ";
			}

			return sql;
	}
			
	
	
	
	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(
			Integer chr, BigDecimal start, BigDecimal end,
			long firstRow, long l, boolean isCore) {
		// TODO Auto-generated method stub
		
		return queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(Long.valueOf(chr+2), start, end, null, null, null,isCore, AppContext.isSNPAllvarsFetchMismatchOnly());
		
	}
	

	private Set queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(Long partition_id, BigDecimal start, BigDecimal end, Collection set1, Collection set2, Collection set3, boolean isCore, boolean isMismatchOnly) {
		
		set1 = null;
		set2 = null;
		set3 = null;
		
		String sql="with countquery as ( "
				+ countMismatchesInvars(null, String.valueOf(partition_id-2), start.intValue(),end.intValue(), isCore)
				+ " ) ";
		
		if(isCore) {
			
			sql+=" select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, sg.partition_id, sfl.position  pos,  sfl.refcall  refnuc, sg.allele1  varnuc"
					  + " from iric.mv_core_snps sfl, iric.snp_genotype sg, countquery"
					  + " where sfl.snp_feature_id=sg.snp_feature_id";
				if(isMismatchOnly)
						  sql += " and sfl.refcall<>sg.allele1";
					  sql += " and sfl.refcall is not null"
					  + " and sfl.position between " + start.intValue()+1 + " and " + end
			 		  + " and sfl.srcfeature_id=" + partition_id	
					  + " and sg.allele1 is not null "
					  + " and sg.partition_id=" + partition_id
					  
					  + " and countquery.var=sg.iric_stock_id" 
					  + " and countquery.row_number between " + start.intValue()+1 + " and " + end;
					  
			
		} else {

			sql+=" select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, sg.partition_id, sfl.position  pos,  sf.refcall  refnuc, sg.allele1  varnuc"
					  + " from iric.snp_feature sf, iric.snp_featureloc sfl, iric.snp_genotype sg, countquery"
					  + " where sf.snp_feature_id=sfl.snp_feature_id"
					  + " and sf.snp_feature_id=sg.snp_feature_id";
			
			
			if(isMismatchOnly)
				  sql += " and sf.refcall<>sg.allele1";
			
					  sql += " and sf.refcall is not null"
					  + " and sg.allele1 is not null"
					  + " and partition_id=" + partition_id
					  + " and sfl.position between " + start.intValue()+1 + " and " + end
			 		  + " and sfl.srcfeature_id=" + partition_id
			 		  
			 		  + " and countquery.var=sg.iric_stock_id" 
					  + " and countquery.row_number between " + start.intValue()+1 + " and " + end;
		}
	
			if(set1!=null)
			{
				StringBuffer buff=new StringBuffer();
				Iterator itVar = set1.iterator();
				while(itVar.hasNext()) {
					buff.append( itVar.next());
					if(itVar.hasNext()) buff.append(",");
				}
  			   sql += " and ( sg.iric_stock_id in (" + buff.toString() + ") ";
			
				if(set2!=null) {
					
					buff=new StringBuffer();
					itVar = set2.iterator();
					while(itVar.hasNext()) {
						buff.append( itVar.next());
						if(itVar.hasNext()) buff.append(",");
					}
	  			   sql += " or sg.iric_stock_id in (" + buff.toString() + ") ";
	  			   
	
	  			   if(set3!=null) {
	  				   
	  					buff=new StringBuffer();
	  					itVar = set3.iterator();
	  					while(itVar.hasNext()) {
	  						buff.append( itVar.next());
	  						if(itVar.hasNext()) buff.append(",");
	  					}
	  	  			   sql += " or sg.iric_stock_id in (" + buff.toString() + "))";
	  	  			   
	  			   } else
	  				   sql += ")";
					
				} else
					sql += ")";
			}
	
			
		//sql += " order by sfl.position";
		sql += " order by countquery.mismatch desc, sfl.position, sg.iric_stock_id";
		return new LinkedHashSet(executeSQL(sql));
		
	}
	
	private Set queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch1(Long partition_id, BigDecimal start, BigDecimal end, Collection set1, Collection set2, Collection set3, boolean isCore, boolean isMismatchOnly) {
		
		
		String sql="";
		
		if(isCore) {
			
			sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, sg.partition_id, sfl.position  pos,  sfl.refcall  refnuc, sg.allele1  varnuc"
					  + " from iric.mv_core_snps sfl, iric.snp_genotype sg"
					  + " where sfl.snp_feature_id=sg.snp_feature_id";
				if(isMismatchOnly)
						  sql += " and sfl.refcall<>sg.allele1";
					  sql += " and sfl.refcall is not null"
					  + " and sfl.position between " + start.intValue()+1 + " and " + end
			 		  + " and sfl.srcfeature_id=" + partition_id	
					  + " and sg.allele1 is not null "
					  + " and sg.partition_id=" + partition_id;
			
		} else {

			sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, sg.partition_id, sfl.position  pos,  sf.refcall  refnuc, sg.allele1  varnuc"
					  + " from iric.snp_feature sf, iric.snp_featureloc sfl, iric.snp_genotype sg"
					  + " where sf.snp_feature_id=sfl.snp_feature_id"
					  + " and sf.snp_feature_id=sg.snp_feature_id";
			
			
			if(isMismatchOnly)
				  sql += " and sf.refcall<>sg.allele1";
			
					  sql += " and sf.refcall is not null"
					  + " and sg.allele1 is not null"
					  + " and partition_id=" + partition_id
					  + " and sfl.position between " + start.intValue()+1 + " and " + end
			 		  + " and sfl.srcfeature_id=" + partition_id;
		}
	
			if(set1!=null)
			{
				StringBuffer buff=new StringBuffer();
				Iterator itVar = set1.iterator();
				while(itVar.hasNext()) {
					buff.append( itVar.next());
					if(itVar.hasNext()) buff.append(",");
				}
  			   sql += " and ( sg.iric_stock_id in (" + buff.toString() + ") ";
			
				if(set2!=null) {
					
					buff=new StringBuffer();
					itVar = set2.iterator();
					while(itVar.hasNext()) {
						buff.append( itVar.next());
						if(itVar.hasNext()) buff.append(",");
					}
	  			   sql += " or sg.iric_stock_id in (" + buff.toString() + ") ";
	  			   
	
	  			   if(set3!=null) {
	  				   
	  					buff=new StringBuffer();
	  					itVar = set3.iterator();
	  					while(itVar.hasNext()) {
	  						buff.append( itVar.next());
	  						if(itVar.hasNext()) buff.append(",");
	  					}
	  	  			   sql += " or sg.iric_stock_id in (" + buff.toString() + "))";
	  	  			   
	  			   } else
	  				   sql += ")";
					
				} else
					sql += ")";
			}
	
			
		sql += " order by sfl.position";
		return new LinkedHashSet(executeSQL(sql));
		
	}
	
	
	private Set queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatchBackup(Long partition_id, BigDecimal start, BigDecimal end, Collection set1, Collection set2, Collection set3) {
		
		
		String sql;
		//if(partition_id==3) {
		if(true) {
			sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, sg.partition_id, sfl.position  pos,  sf.refcall  refnuc, sg.allele1  varnuc"
					  + " from iric.snp_feature sf, iric.snp_featureloc sfl, iric.snp_genotype sg"
					  + " where sf.snp_feature_id=sfl.snp_feature_id"
					  + " and sf.snp_feature_id=sg.snp_feature_id"
					  + " and sf.refcall<>sg.allele1"
					  + " and sf.refcall is not null"
					  + " and sg.allele1 is not null"
					  + " and partition_id=" + partition_id
					  + " and sfl.position between " + start.intValue()+1 + " and " + end
			 		  + " and sfl.srcfeature_id=" + partition_id;
		} else
		{
			
			  sql=" select sg.iric_stock_id as var, sg.partition_id, sfl.position  pos,  sf.refcall  refnuc, sg.allele1  varnuc"
					  + " from iric.snp_feature sf "
					  + " inner join iric.snp_featureloc sfl"
					  + "          on sf.snp_feature_id=sfl.snp_feature_id "
					  + "    inner join iric.snp_genotype sg "
					  + "          on sg.snp_feature_id=sfl.snp_feature_id "
					  + "          and sg.partition_id=" + partition_id
					  + "          and sf.refcall<>sg.allele1" ;
		}
			
			if(set1!=null)
			{
				StringBuffer buff=new StringBuffer();
				Iterator itVar = set1.iterator();
				while(itVar.hasNext()) {
					buff.append( itVar.next());
					if(itVar.hasNext()) buff.append(",");
				}
  			   sql += " and ( sg.iric_stock_id in (" + buff.toString() + ") ";
			}
			
			if(set2!=null) {
				
				StringBuffer buff=new StringBuffer();
				Iterator itVar = set2.iterator();
				while(itVar.hasNext()) {
					buff.append( itVar.next());
					if(itVar.hasNext()) buff.append(",");
				}
  			   sql += " or sg.iric_stock_id in (" + buff.toString() + ") ";
  			   

  			   if(set3!=null) {
  				   
  					buff=new StringBuffer();
  					itVar = set3.iterator();
  					while(itVar.hasNext()) {
  						buff.append( itVar.next());
  						if(itVar.hasNext()) buff.append(",");
  					}
  	  			   sql += " or sg.iric_stock_id in (" + buff.toString() + "))";
  	  			   
  			   } else
  				   sql += ")";
				
			} else
				sql += ")";
			
		//if(partition_id==3) {
		if(true) {
			
		} else {

			  sql += " where  "
			  + "  sf.refcall is not null "
			  + "  and sg.allele1 is not null "
			  + "  and sfl.position between " + start.intValue()+1 + " and " + end;

		}
			
		sql += " order by sfl.position";
		return new LinkedHashSet(executeSQL(sql));
		
	}
	
	
	private List<SnpsAllvarsRefMismatch> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VSnpAllvarsMin.class).list();
	}
	
	private Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
	
	
	/*
	String sql="select sg.iric_stock_id as var, count(*) as mismatch "
			  + " from iric.snp_feature sf, iric.snp_featureloc sfl, iric.snp_genotype sg"
			  + " where sf.snp_feature_id=sfl.snp_feature_id"
			  + " and sf.snp_feature_id=sg.snp_feature_id"
			  + " and sf.refcall<>sg.allele1"
			  + " and sf.refcall is not null"
			  + " and sg.allele1 is not null"
			  + " and partition_id=" + ( Integer.valueOf(chr) + 2)
			  + " and sfl.position between " + start + " and " + end
			  + " group by sg.iric_stock_id "
			  + " order by mismatch desc";

			if(Integer.valueOf(chr)>1) {
			
			  sql=" select sg.iric_stock_id as var, count(*) as mismatch"  
				  + " from iric.snp_feature sf "
				  + " inner join iric.snp_featureloc sfl"
				  + "          on sf.snp_feature_id=sfl.snp_feature_id "
				  + "    inner join iric.snp_genotype sg "
				  + "          on sg.snp_feature_id=sfl.snp_feature_id "
				  + "          and sg.partition_id=" + ( Integer.valueOf(chr) + 2)
				  + "          and sf.refcall<>sg.allele1"
				  + " where  "
				  + "  sf.refcall is not null "
				  + "  and sg.allele1 is not null "
				  + "  and sfl.position between " + start + " and " + end
				  + " group by sg.iric_stock_id " 
				  + " order by mismatch desc";
			 }
			
			*/
	
	/*
	@Override
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr,
			BigDecimal start, BigDecimal end, Collection<BigDecimal> vars,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetween", startResult, maxRows, new Long(chr), start, end, vars);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}
	*/
	

	//@SuppressWarnings("unchecked")
	//@Transactional
	private Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, boolean isCore, int startResult, int maxRows) throws DataAccessException {
		
		if(vars==null) {
			return  queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch( new Long(chr+2),  start,  end, null,  null, null,  isCore,  false); 
		}
		else {
		Set varsets[] = AppContext.setSlicer( (Set)vars);
		
		if(varsets.length==3)
			return  queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch( new Long(chr+2),  start,  end,  varsets[0],  varsets[1], varsets[2],  isCore,  false); 
		else if(varsets.length==2)
			return  queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch( new Long( chr+2),  start,  end,  varsets[0],  varsets[1], null,  isCore,  false); 
		else 
			return  queryfindVSnpAllvarsMinByVarsChrPosBetweenRefmismatch(  new Long(chr+2),  start,  end,  varsets[0],  null, null,  isCore,  false); 
		}
		
	}
	
	
	/*
	private Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenBackup(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, boolean isCore, int startResult, int maxRows) throws DataAccessException {
		Query query;

		List listVarids = AppContext.setSlicerIds( (Set)vars );
		
		if(isCore) {
			if(vars.size()>2000)
			{	
				java.util.Set set1 = new HashSet();
				Iterator it=vars.iterator();
				for(int i=0; i<1000; i++)
					set1.add(  it.next() );
				java.util.Set set2 = new HashSet();
				for(int i=0; i<1000; i++)
					set2.add(  it.next() );
				java.util.Set set3 = new HashSet();
				while(it.hasNext())
					set3.add(it.next());
				
				query = createNamedQuery("findVCoreSnpAllvarsMinByVarsChrPosBetween3", startResult, maxRows, new Long(chr+2), start, end, set1, set2, set3);
				
			} else if(vars.size()>1000)
			{
				java.util.Set set1 = new HashSet();
				Iterator it=vars.iterator();
				for(int i=0; i<1000; i++)
					set1.add(  it.next() );
				java.util.Set set2 = new HashSet();
				while(it.hasNext())
					set2.add(it.next());
				
				query = createNamedQuery("findVCoreSnpAllvarsMinByVarsChrPosBetween2", startResult, maxRows, new Long(chr+2), start, end, set1, set2);
				
			} else
				 query = createNamedQuery("findCoreVSnpAllvarsMinByVarsChrPosBetween", startResult, maxRows, new Long(chr+2), start, end, vars);
		} else
		{
			if(vars.size()>2000)
			{	
				java.util.Set set1 = new HashSet();
				Iterator it=vars.iterator();
				for(int i=0; i<1000; i++)
					set1.add(  it.next() );
				java.util.Set set2 = new HashSet();
				for(int i=0; i<1000; i++)
					set2.add(  it.next() );
				java.util.Set set3 = new HashSet();
				while(it.hasNext())
					set3.add(it.next());
				
				query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetween3", startResult, maxRows, new Long(chr+2), start, end, set1, set2, set3);
				
			} else if(vars.size()>1000)
			{
				java.util.Set set1 = new HashSet();
				Iterator it=vars.iterator();
				for(int i=0; i<1000; i++)
					set1.add(  it.next() );
				java.util.Set set2 = new HashSet();
				while(it.hasNext())
					set2.add(it.next());
				
				query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetween2", startResult, maxRows, new Long(chr+2), start, end, set1, set2);
				
			} else
				 query = createNamedQuery("findVSnpAllvarsMinByVarsChrPosBetween", startResult, maxRows, new Long(chr+2), start, end, vars);
		}
		
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}
	*/
	
	
	
	/*
	 * 
	 * 
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException {
		return findVSnpAllvarsByChrPosBetween( chr, start,  end , -1, -1);
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByChrPosBetween", startResult, maxRows, new Long(chr), start, end);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}

	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars) throws DataAccessException {
		return findVSnpAllvarsByVarsChrPosBetween( chr,  start, end ,  vars, -1, -1);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsByVarsChrPosBetween", startResult, maxRows, new Long(chr), start, end, vars);
		return new LinkedHashSet<SnpsAllvars>(query.getResultList());
	}
	 */
	
}
