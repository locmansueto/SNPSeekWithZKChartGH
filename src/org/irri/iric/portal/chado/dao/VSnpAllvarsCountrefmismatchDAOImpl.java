package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
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
import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
//import org.irri.iric.portal.genotype.views.IViewCountVarrefMismatchHome;
//import org.irri.iric.portal.genotype.views.ViewCount2linesMismatchId;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvarsCountrefmismatch entities.
 * 
 */
@Repository("SnpsAllvarsRefMismatchDAO")
@Transactional
public class VSnpAllvarsCountrefmismatchDAOImpl extends AbstractJpaDao<VSnpAllvarsCountrefmismatch>
		implements VSnpAllvarsCountrefmismatchDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpAllvarsCountrefmismatch.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpAllvarsCountrefmismatchDAOImpl
	 *
	 */
	public VSnpAllvarsCountrefmismatchDAOImpl() {
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
	 * JPQL Query - findAllVSnpAllvarsCountrefmismatchs
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs() throws DataAccessException {

		return findAllVSnpAllvarsCountrefmismatchs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpAllvarsCountrefmismatchs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpAllvarsCountrefmismatchs", startResult, maxRows);
		return new LinkedHashSet<VSnpAllvarsCountrefmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByVar
	 *
	 */
	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByVar(Integer var) throws DataAccessException {

		return findVSnpAllvarsCountrefmismatchByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByVar
	 *
	 */

	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByVar(Integer var, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsCountrefmismatchByVar", startResult, maxRows, var);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(BigDecimal var) throws DataAccessException {

		return findVSnpAllvarsCountrefmismatchByPrimaryKey(var, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(BigDecimal var, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsCountrefmismatchByPrimaryKey", startResult, maxRows, var);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByMismatch
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findVSnpAllvarsCountrefmismatchByMismatch(java.math.BigDecimal mismatch) throws DataAccessException {

		return findVSnpAllvarsCountrefmismatchByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findVSnpAllvarsCountrefmismatchByMismatch(java.math.BigDecimal mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsCountrefmismatchByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<VSnpAllvarsCountrefmismatch>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpAllvarsCountrefmismatch entity) {
		return true;
	}

	public List countMismatchesOld(String chr, Integer start, Integer end) {
		// TODO Auto-generated method stub

			String sql="select var, count(*) as mismatch from IRIC." + AppContext.getViewPrefix() + "_SNP_ALLVARS where (REFNUC<>VARNUC) and REFNUC is not null AND VARNUC is not null and " 
					//+ " chr=" + chr
					+ " partition_id=" + (Integer.valueOf(chr)+2)
					+ " and pos between " + start + " and " + end
					+ " group by var order by mismatch desc, var ";
		return executeSQL(sql) ;
	}
	
	@Override
	public List countMismatches(String chr, Integer start, Integer end) {
		// TODO Auto-generated method stub
		return countMismatchesInvars(null, chr, start, end, false); 
	}
		
	@Override
	public List countMismatches(String chr, Integer start, Integer end, boolean isCore) {
		// TODO Auto-generated method stub
		return countMismatchesInvars(null, chr, start, end, isCore); 

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
			            
		return executeSQL(sql) ;
		*/
	}
	
	@Override
	public List countMismatchesInvars(Set<BigDecimal> varIds, String chr, Integer start, Integer end) {
		return  countMismatchesInvars(varIds,  chr, start,  end,  true);
	}
	
	
	@Override
	public List countMismatchesInvars(Set<BigDecimal> varIds, String chr, Integer start, Integer end, boolean isCore) {
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

					            
				return executeSQL(sql) ;
	}
			
	//@Override
	public List countMismatchesInvars1(Set<BigDecimal> varIds, String chr, Integer start, Integer end, boolean isCore) {
		// TODO Auto-generated method stub

		
		List<String> listVaridSets= AppContext.setSlicerIds(varIds);
				
		
		String sql="";
		
		if(isCore) {
			sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, count(*) as mismatch  "
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
			sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, count(*) as mismatch "
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

					            
				return executeSQL(sql) ;
	}
	
	public List countMismatchesInvarsBackup(Set<BigDecimal> varIds, String chr, Integer start, Integer end) {
		// TODO Auto-generated method stub

		
		List<String> listVaridSets= AppContext.setSlicerIds(varIds);
				
		
		String sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, count(*) as mismatch "
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
				  + " order by mismatch desc";

				//if(Integer.valueOf(chr)>1) {
			    if(false) {
				
				  sql=" select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, count(*) as mismatch"  
					  + " from iric.snp_feature sf "
					  + " 	 inner join iric.snp_featureloc sfl"
					  + "          on sf.snp_feature_id=sfl.snp_feature_id "
					  + "  		   and sfl.position between " + start.intValue()+1 + " and " + end
					  + "    inner join iric.snp_genotype sg "
					  + "          on sg.snp_feature_id=sfl.snp_feature_id "
					  + "          and sg.partition_id=" + ( Integer.valueOf(chr) + 2)
					  + "          and sf.refcall<>sg.allele1" ;
				  
					  if(listVaridSets.size()>0) 
						  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(0)  + ")";
					  if(listVaridSets.size()>1) 
						  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(1)  + ")";
					  if(listVaridSets.size()>2) 
						  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(2)  + ")";
				  
				  sql  += " where  "
					  + "  sf.refcall is not null "
					  + "  and sg.allele1 is not null "
					 // + "  and sfl.position between " + start + " and " + end
					  + " group by sg.iric_stock_id " 
					  + " order by mismatch desc";
				 }
					            
				return executeSQL(sql) ;
	}
	
	public List countMismatchesInvarsSubquery1(Set<BigDecimal> varIds, String chr, Integer start, Integer end) {
		// TODO Auto-generated method stub

		
		List<String> listVaridSets= AppContext.setSlicerIds(varIds);
				
		
		String sql="select /*+ leading(sfl) use_nl(sg) */ sg.iric_stock_id as var, count(*) as mismatch "
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
				  + " group by sg.iric_stock_id "
				  + " order by mismatch desc";

				//if(Integer.valueOf(chr)>1) {
			    if(false) {
				/*
			    	select sg.iric_stock_id , count(*) mismatch from 
			    	(
			    		select iric_stock_id , snp_feature_id , allele1 from snp_genotype
			    		where partition_id=3
			    		and iric_stock_id in (?)
			    	) sg
			    	inner join (
			    		select snp_feature_id, refcall from snp_featureloc
			    		where position between ? and ?
			    		and srcfeature_id in (
			    			select feature_id from feature where name='Chr1'
			    		)
			    	) sf
			    	on sf.snp_feature_id = sg.snp_feature_id 
			    	and sf.refcall<>sg.allele1

			    group by sg.iric_stock_id 
			    order by mismatch desc
			    */
				  sql=" select sg.iric_stock_id as var, count(*) as mismatch"  
					  + " from iric.snp_feature sf "
					  + " 	 inner join iric.snp_featureloc sfl"
					  + "          on sf.snp_feature_id=sfl.snp_feature_id "
					  + "  		   and sfl.position between " + start.intValue()+1 + " and " + end
					  + "    inner join iric.snp_genotype sg "
					  + "          on sg.snp_feature_id=sfl.snp_feature_id "
					  + "          and sg.partition_id=" + ( Integer.valueOf(chr) + 2)
					  + "          and sf.refcall<>sg.allele1" ;
				  
					  if(listVaridSets.size()>0) 
						  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(0)  + ")";
					  if(listVaridSets.size()>1) 
						  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(1)  + ")";
					  if(listVaridSets.size()>2) 
						  sql+= " and sg.iric_stock_id in( " + listVaridSets.get(2)  + ")";
				  
				  sql  += " where  "
					  + "  sf.refcall is not null "
					  + "  and sg.allele1 is not null "
					 // + "  and sfl.position between " + start + " and " + end
					  + " group by sg.iric_stock_id " 
					  + " order by mismatch desc";
				 }
					            
				return executeSQL(sql) ;
	}

	private List<SnpsAllvarsRefMismatch> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		
		return getSession().createSQLQuery(sql).addEntity(  VSnpAllvarsCountrefmismatch.class ).list();
		
		//return getSession().get	
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	/*
	 * 
	private List<SnpsAllvarsRefMismatch> executeSQL2(String sql) {
		PreparedStatement stmt = entityManager.unwrap(Session.class).connection().prepareStatement(sql);
		//stmt.
		//return entityManager.unwrap(Session.class);
	
		getSession()
		
		final PreparedStatement[] stmt = new PreparedStatement[1];
		final int i = (Integer)getJdbcTemplate().query(new PreparedStatementCreator() {
		    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		        stmt[0] = connection.prepareStatement("select max(gameid) from game");
		        return stmt[0];
		    }
		}, new ResultSetExtractor() {
		    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		        return resultSet.getString(1);
		    }
		});
	}
	
	
	private Connection getConnection() {
		
		return entityManager.unwrap(Session.class).connection();
	}
	
	*/
	private void test() {
		//getSession()
		//entityManager.
	}
}
