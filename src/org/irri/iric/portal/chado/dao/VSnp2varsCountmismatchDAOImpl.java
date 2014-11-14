package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
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
import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
//import org.irri.iric.portal.genotype.views.Snp2linesId;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnp2varsCountmismatch entities.
 * 
 */
//@Repository("Snps2VarsCountMismatchDAO")
@Repository("Snps2VarsCountMismatchDAO")
@Transactional
public class VSnp2varsCountmismatchDAOImpl extends AbstractJpaDao<VSnp2varsCountmismatch>
		implements VSnp2varsCountmismatchDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnp2varsCountmismatch.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnp2varsCountmismatchDAOImpl
	 *
	 */
	public VSnp2varsCountmismatchDAOImpl() {
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
	 * JPQL Query - findVSnp2varsCountmismatchByMismatch
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByMismatch(Integer mismatch) throws DataAccessException {

		return findVSnp2varsCountmismatchByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsCountmismatchByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar2
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar2(Integer var2) throws DataAccessException {

		return findVSnp2varsCountmismatchByVar2(var2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsCountmismatchByVar2", startResult, maxRows, var2);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnp2varsCountmismatchs
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs() throws DataAccessException {

		return findAllVSnp2varsCountmismatchs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnp2varsCountmismatchs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnp2varsCountmismatchs", startResult, maxRows);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar1
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar1(Integer var1) throws DataAccessException {

		return findVSnp2varsCountmismatchByVar1(var1, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsCountmismatchByVar1", startResult, maxRows, var1);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByPrimaryKey
	 *
	 */
	@Transactional
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(BigDecimal var1, BigDecimal var2) throws DataAccessException {

		return findVSnp2varsCountmismatchByPrimaryKey(var1, var2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByPrimaryKey
	 *
	 */

	@Transactional
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(BigDecimal var1, BigDecimal var2, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnp2varsCountmismatchByPrimaryKey", startResult, maxRows, var1, var2);
			return (org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch) query.getSingleResult();
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
	public boolean canBeMerged(VSnp2varsCountmismatch entity) {
		return true;
	}
	
	/*
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end) {
	
		String sql = "select var1, var2, count(*) as mismatch from IRIC." +  AppContext.getViewPrefix() +   "_SNP_2VARS where " 
			+ " var1nuc<>var2nuc "
			+ " and var1nuc is not null AND var2nuc is not null "
			//+ " and chr=" + chr 
			+ " and partition_id=" + (chr+2) 
			+ " and pos between " + start + " and " + end 
			+ " and var1<=var2 "
			+ " group by var1, var2 "
			+ " order by var1, var2";
			
		return executeSQL(sql);
	}
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, int topN) {
	
		String sql = "select * from " 
				
			+ "(select var1, var2, count(*) as mismatch from IRIC." +  AppContext.getViewPrefix()   + "_SNP_2VARS where " 
			+ " var1nuc<>var2nuc "
			+ " and var1nuc is not null AND var2nuc is not null "
			//+ " and chr=" + chr 
			+ " partition_id=" + (chr+2)
			+ " and pos between " + start + " and " + end 
			+ " and var1<=var2 "
			+ " group by var1, var2 "
			+ " order by mismatch desc, var1, var2) "
			
			+ " where rownum<=" + topN;
			
		return executeSQL(sql);
	}
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, Set varieties) {
	
		Iterator<BigDecimal> itVar = varieties.iterator();
		StringBuffer buff = new StringBuffer();
		while(itVar.hasNext()) {
			buff.append( itVar.next() );
			if( itVar.hasNext() ) buff.append(",");
		}
		
		String sql = "select var1, var2, count(*) as mismatch from IRIC."  +  AppContext.getViewPrefix()   + "_SNP_2VARS where " 
			+ " var1nuc<>var2nuc "
			+ " and var1nuc is not null AND var2nuc is not null "
			//+ " and chr=" + chr
			+ " and partition_id=" + (chr+2)
			+ " and pos between " + start + " and " + end 
			+ " and var1<=var2 "
			+ " and var1 in (" + buff.toString() + ") "
			+ " and var2 in (" + buff.toString() + ") "
			+ " group by var1, var2 "
			+ " order by mismatch desc, var1, var2";
			
		
		return executeSQL(sql);
	}
	
	*/
	
	
	private List<Snps2VarsCountmismatch> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VSnp2varsCountmismatch.class).list();
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

/*	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, boolean isCore) {
		// TODO Auto-generated method stub
		
		
//		select var1, var2,  count(*) mismatch from (
//				  
//				WITH subquery AS
//				(
//				  select sf.snp_feature_id, sg1.iric_stock_id, sg1.allele1 , sg1.partition_id, sfl.position, sf.refcall
//				  from snp_genotype sg1
//				    inner join snp_feature sf
//				    on sg1.SNP_FEATURE_ID=sf.snp_feature_id
//				    and  sg1.partition_id=3
//				    
//				    inner join snp_featureloc sfl
//				    on  sfl.snp_feature_id=sf.SNP_FEATURE_ID 
//				    and sfl.position between 50000 and 55000
//				    and sfl.srcfeature_id in (select feature_id from feature where name = 'Chr1')
//				)
//				select  subqueryA.iric_stock_id var1, subqueryB.iric_stock_id var2, subqueryA.snp_feature_id snp_feature_id, subqueryA.partition_id, subqueryA.position pos, subqueryA.refcall refnuc,   subqueryA.allele1 var1nuc,  subqueryB.allele1 var2nuc
//				from subquery subqueryA, subquery subqueryB
//				where  subqueryA.snp_feature_id=subqueryB.snp_feature_id
//				and subqueryA.iric_stock_id<=subqueryB.iric_stock_id
//				and  subqueryA.allele1<>subqueryB.allele1 ) t_2vars
//
//				where var1<=var2
//				group by var1, var2
//				order by mismatch
//		
		
				String chrom = "Chr" + chr;
					
		
				String sql = "select var1, var2,  count(*) mismatch from ( "
				+ "	WITH subquery AS"
				+ "	("
				+ "	  select sf.snp_feature_id, sg1.iric_stock_id, sg1.allele1 , sg1.partition_id, sfl.position, sf.refcall"
				+ "	  from iric.snp_genotype sg1"
				+ "	    inner join iric.snp_feature sf"
				+ "		    on sg1.SNP_FEATURE_ID=sf.snp_feature_id"
				+ "	    and  sg1.partition_id=" + (chr+2) 
					    
				+  "	    inner join iric.snp_featureloc sfl"
				+ "	    on  sfl.snp_feature_id=sf.SNP_FEATURE_ID" 
				+ "	    and sfl.position between " + start + " and " + end
				+  "	    and sfl.srcfeature_id in (select feature_id from iric.feature where name = '" + chrom + "')"
				+  "	) " 
				+ " select  subqueryA.iric_stock_id var1, subqueryB.iric_stock_id var2, subqueryA.snp_feature_id snp_feature_id, subqueryA.partition_id, subqueryA.position pos, subqueryA.refcall refnuc,   subqueryA.allele1 var1nuc,  subqueryB.allele1 var2nuc "
				+ " from subquery subqueryA, subquery subqueryB"
				+ " where  subqueryA.snp_feature_id=subqueryB.snp_feature_id"
				+ " and subqueryA.iric_stock_id<=subqueryB.iric_stock_id"
				+ " and  subqueryA.allele1<>subqueryB.allele1 ) t_2vars"

				+ " where var1<=var2"
				+ " group by var1, var2"
				+ " order by mismatch desc ";
				
				
		
		return executeSQL(sql);
	}
	

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, int topN, boolean isCore) {
		// TODO Auto-generated method stub
		String chrom = "Chr" + chr;
			

		String sql = " select * from  (" 
				
		+ " select var1, var2,  count(*) mismatch from ( "
		+ "	WITH subquery AS"
		+ "	("
		+ "	  select sf.snp_feature_id, sg1.iric_stock_id, sg1.allele1 , sg1.partition_id, sfl.position, sf.refcall"
		+ "	  from iric.snp_genotype sg1"
		+ "	    inner join iric.snp_feature sf"
		+ "		    on sg1.SNP_FEATURE_ID=sf.snp_feature_id"
		+ "	    and  sg1.partition_id=" + (chr+2) 
			    
		+  "	    inner join iric.snp_featureloc sfl"
		+ "	    on  sfl.snp_feature_id=sf.SNP_FEATURE_ID" 
		+ "	    and sfl.position between " + start + " and " + end
		+  "	    and sfl.srcfeature_id in (select feature_id from iric.feature where name = '" + chrom + "')"
		+  "	) " 
		+ " select  subqueryA.iric_stock_id var1, subqueryB.iric_stock_id var2, subqueryA.snp_feature_id snp_feature_id, subqueryA.partition_id, subqueryA.position pos, subqueryA.refcall refnuc,   subqueryA.allele1 var1nuc,  subqueryB.allele1 var2nuc "
		+ " from subquery subqueryA, subquery subqueryB"
		+ " where  subqueryA.snp_feature_id=subqueryB.snp_feature_id"
		+ " and subqueryA.iric_stock_id<=subqueryB.iric_stock_id"
		+ " and  subqueryA.allele1<>subqueryB.allele1 ) t_2vars"

		+ " where var1<=var2"
		+ " group by var1, var2"
		+ " order by mismatch desc "
		
		+ ") where rownum<=" + topN;

		return executeSQL(sql);
	}
		

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, Set<BigDecimal> varieties, boolean isCore) {
		// TODO Auto-generated method stub
		String chrom = "Chr" + chr;
		
			
		Iterator<BigDecimal> itVar = varieties.iterator();
		StringBuffer buff = new StringBuffer();
		while(itVar.hasNext()) {
			buff.append( itVar.next() );
			if( itVar.hasNext() ) buff.append(",");
		}
		
		String sql = "select var1, var2,  count(*) mismatch from ( "
		+ "	WITH subquery AS"
		+ "	("
		+ "	  select sf.snp_feature_id, sg1.iric_stock_id, sg1.allele1 , sg1.partition_id, sfl.position, sf.refcall"
		+ "	  from iric.snp_genotype sg1"
		+ "	    inner join iric.snp_feature sf"
		+ "		    on sg1.SNP_FEATURE_ID=sf.snp_feature_id"
		+ "	    and sg1.partition_id=" + (chr+2) 
		+ "     and sg1.iric_stock_id in (" + buff.toString() + ") "
			    
		+  "	    inner join iric.snp_featureloc sfl"
		+ "	    on  sfl.snp_feature_id=sf.SNP_FEATURE_ID" 
		+ "	    and sfl.position between " + start + " and " + end
		+  "	    and sfl.srcfeature_id in (select feature_id from iric.feature where name = '" + chrom + "')"
		+  "	) " 
		+ " select  subqueryA.iric_stock_id var1, subqueryB.iric_stock_id var2, subqueryA.snp_feature_id snp_feature_id, subqueryA.partition_id, subqueryA.position pos, subqueryA.refcall refnuc,   subqueryA.allele1 var1nuc,  subqueryB.allele1 var2nuc "
		+ " from subquery subqueryA, subquery subqueryB"
		+ " where  subqueryA.snp_feature_id=subqueryB.snp_feature_id"
		+ " and subqueryA.iric_stock_id<=subqueryB.iric_stock_id"
		+ " and  subqueryA.allele1<>subqueryB.allele1 ) t_2vars"

		+ " where var1<=var2"
		+ " group by var1, var2"
		+ " order by mismatch desc ";

		return executeSQL(sql);
	}*/

	
	
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, int topN, Set<BigDecimal> varieties) {
		return countMismatch( chr,
				 start,  end,  topN, varieties, true);
	}
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end) {
		// TODO Auto-generated method stub
		return countMismatch( chr,
				 start,  end, -1);
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, int topN) {
		// TODO Auto-generated method stub
		return countMismatch( chr,
				 start,  end,  topN, null, true);
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, Set<BigDecimal> varieties) {
		// TODO Auto-generated method stub
		return countMismatch( chr,
				 start,  end, -1 , varieties, true);
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, int topN, Set<BigDecimal> varieties, boolean isCore) {
		
	
	
		// TODO Auto-generated method stub
		String chrom = "Chr" + chr;
		
		StringBuffer buff = new StringBuffer();	
		
		if(varieties!=null && !varieties.isEmpty()) {
		Iterator<BigDecimal> itVar = varieties.iterator();
		while(itVar.hasNext()) {
			buff.append( itVar.next() );
			if( itVar.hasNext() ) buff.append(",");
		}
		}
		
		String sql = "";
		
		if(topN>0)
			sql += " select * from  ("; 
				
		sql+= " select var1, var2,  count(*) mismatch from ( ";
		
		if(isCore) {
			sql +=  "	WITH subquery AS"
					+ "	("
					+ "	  select sfl.snp_feature_id, sg1.iric_stock_id, sg1.allele1 , sg1.partition_id, sfl.position, sfl.refcall"
					+ "	  from iric.snp_genotype sg1"
					+ "	    inner join iric.mv_core_snps sfl"
					+ "		    on sg1.SNP_FEATURE_ID=sfl.snp_feature_id"
					+ "	    and sg1.partition_id=" + (chr+2) ;
			if(varieties!=null && !varieties.isEmpty())
					sql += "     and sg1.iric_stock_id in (" + buff.toString() + ") ";
			
					sql += "	    and sfl.position between " + start.intValue()+1 + " and " + end
					+  "	and sfl.srcfeature_id=" + (chr+2)
					+  "	) ";			
		}
		else {		
			sql +=  "	WITH subquery AS"
			+ "	("
			+ "	  select sf.snp_feature_id, sg1.iric_stock_id, sg1.allele1 , sg1.partition_id, sfl.position, sf.refcall"
			+ "	  from iric.snp_genotype sg1"
			+ "	    inner join iric.snp_feature sf"
			+ "		    on sg1.SNP_FEATURE_ID=sf.snp_feature_id"
			+ "	    and sg1.partition_id=" + (chr+2) ;
			
			if(varieties!=null && !varieties.isEmpty())
				sql += "     and sg1.iric_stock_id in (" + buff.toString() + ") ";
			
			sql +=  "	    inner join iric.snp_featureloc sfl"
			+ "	    on  sfl.snp_feature_id=sf.SNP_FEATURE_ID" 
			+ "	    and sfl.position between " + start.intValue()+1  + " and " + end
			+  "	    and sfl.srcfeature_id=" + (chr+2)
			+  "	) ";
		}
		
		sql += " select  subqueryA.iric_stock_id var1, subqueryB.iric_stock_id var2, subqueryA.snp_feature_id snp_feature_id, subqueryA.partition_id, subqueryA.position pos, subqueryA.refcall refnuc,   subqueryA.allele1 var1nuc,  subqueryB.allele1 var2nuc "
		+ " from subquery subqueryA, subquery subqueryB"
		+ " where  subqueryA.snp_feature_id=subqueryB.snp_feature_id"
		+ " and subqueryA.iric_stock_id<=subqueryB.iric_stock_id"
		+ " and  subqueryA.allele1<>subqueryB.allele1 ) t_2vars"

		+ " where var1<=var2"
		+ " group by var1, var2"
		+ " order by mismatch desc ";
		
		if(topN>0)
			sql += ") where rownum<=" + topN;
		

		return executeSQL(sql);
	}
}
