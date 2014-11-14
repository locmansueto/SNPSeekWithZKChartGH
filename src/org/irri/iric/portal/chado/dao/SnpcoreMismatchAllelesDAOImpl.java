package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.irri.iric.portal.chado.domain.SnpcoreMismatchAlleles;
import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SnpcoreMismatchAlleles entities.
 * 
 */
@Repository("SnpcoreMismatchAllelesDAO")
@Transactional
public class SnpcoreMismatchAllelesDAOImpl extends AbstractJpaDao<SnpcoreMismatchAlleles>
		implements SnpcoreMismatchAllelesDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SnpcoreMismatchAlleles.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SnpcoreMismatchAllelesDAOImpl
	 *
	 */
	public SnpcoreMismatchAllelesDAOImpl() {
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
	 * JPQL Query - findSnpcoreMismatchAllelesByPrimaryKey
	 *
	 */
	@Transactional
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByPrimaryKey(Integer iricStockId) throws DataAccessException {

		return findSnpcoreMismatchAllelesByPrimaryKey(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByPrimaryKey
	 *
	 */

	@Transactional
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByPrimaryKey(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnpcoreMismatchAllelesByPrimaryKey", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.SnpcoreMismatchAlleles) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByIricStockId
	 *
	 */
	@Transactional
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByIricStockId(Integer iricStockId) throws DataAccessException {

		return findSnpcoreMismatchAllelesByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByIricStockId
	 *
	 */

	@Transactional
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnpcoreMismatchAllelesByIricStockId", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.SnpcoreMismatchAlleles) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByMismatch
	 *
	 */
	@Transactional
	public Set<SnpcoreMismatchAlleles> findSnpcoreMismatchAllelesByMismatch(Integer mismatch) throws DataAccessException {

		return findSnpcoreMismatchAllelesByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreMismatchAlleles> findSnpcoreMismatchAllelesByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpcoreMismatchAllelesByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<SnpcoreMismatchAlleles>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSnpcoreMismatchAlleless
	 *
	 */
	@Transactional
	public Set<SnpcoreMismatchAlleles> findAllSnpcoreMismatchAlleless() throws DataAccessException {

		return findAllSnpcoreMismatchAlleless(-1, -1);
	}

	/**
	 * JPQL Query - findAllSnpcoreMismatchAlleless
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreMismatchAlleles> findAllSnpcoreMismatchAlleless(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSnpcoreMismatchAlleless", startResult, maxRows);
		return new LinkedHashSet<SnpcoreMismatchAlleles>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SnpcoreMismatchAlleles entity) {
		return true;
	}
	
	
	private List<Snps2VarsCountmismatch> executeSQL(String sql) {
		return  getSession().createSQLQuery(sql).addEntity(SnpcoreMismatchAlleles.class).list();
	}

	private List<Snps2VarsCountmismatch> executeSQL(String sql, int firstRow, int maxRows) {
		SQLQuery query = getSession().createSQLQuery(sql);
		 query.addEntity(SnpcoreMismatchAlleles.class);
		 query.setMaxResults(maxRows);
		 query.setFirstResult(firstRow);
		return  query.list();
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
	private List getAllelesInPosition(Integer chr, long querystart, long queryend, int coverage, long countstart, long countend, int firstRow, int maxRows) {
	
		String sql="";
		
	//	if(firstRow>-1 && maxRows>-1)
	//		sql = "select * from (";
		
		sql += " with minidx as ( "
			+ " select * from (select min(alleles_index) minval"
			+ " from lmansueto.snpcore_posindex scp"
			+ " where scp.chromosome=" + chr
			+ " and scp.position between " + querystart + " and " + queryend
			+ " ) where rownum=1"
			+ " ),  maxidx as ("
			+ " select * from (select max(alleles_index) maxval"
			+ " from lmansueto.snpcore_posindex scp"
			+ " where scp.chromosome=" + chr
			+ " and scp.position between " + querystart + " and " + queryend
			+ " ) where rownum=1"
			+ " )"

//			--frag_start_idx frag_end_idx mismatch_desc_idx mc_iric_stock_id_idx
			+ " select /*+ index(mc MC_STOCK_START_END_MIS_IDX) index(sca sca_stock_chromosome_idx)  leading(sca) use_nl(mc) */ sca.iric_stock_id, mc.MISMATCH ,  substr(sca.alleles, minidx.minval, maxidx.maxval-minidx.minval+1) as alleles"
			+ " from minidx, maxidx, lmansueto.snpcore_alleles sca, lmansueto.mismatch_count mc"
			+ " where sca.chromosome=" + chr
			+ " and sca.IRIC_STOCK_ID=mc.IRIC_STOCK_ID"
			+ " and mc.FRAG_START<=" + countstart
			+ " and mc.FRAG_END>=" + countend
			//+ " and mc.COVERAGE=" + coverage

			+ " ORDER by MC.MISMATCH desc";
	
	//	if(firstRow>-1 && maxRows>-1)
	//		sql +=  ") where rownum>=" + firstRow + " and rownum<=" + (firstRow+maxRows-1);
			
		return executeSQL(sql, firstRow, maxRows);
	}

	@Override
	public List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start, BigDecimal end, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		
		// get most appropriate segment (where query is centered)
		
		long querystart=start.longValue();
		long queryend=end.longValue();
		long countstart = querystart;
		long countend = queryend;
		int coverage = 1;
		
		long halflength = (end.longValue() - start.longValue())/2;
		long queryCenterMod = ( (end.longValue() + start.longValue())/2 % 100000 );
		
		if(queryCenterMod>=25000 && queryCenterMod<=75000)
			coverage= 1;
		else coverage = 2;
		
		if(queryend<75000)
			coverage = 1;
		
		
		return  getAllelesInPosition( chr,   querystart,  queryend,  coverage, countstart, countend, firstRow,  maxRows) ;
	}
	
	
	
}
