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

import org.hibernate.Session;
import org.irri.iric.portal.chado.domain.VIricstockPhenotypeValues;
import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;
import org.irri.iric.portal.domain.CvTermUniqueValues;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPhenotypeValues entities.
 * 
 */
//@Repository("VIricstockPhenotypeValuesDAO")
@Repository("VCvPhenotypeValuesDAO")
@Transactional
public class VIricstockPhenotypeValuesDAOImpl extends AbstractJpaDao<VIricstockPhenotypeValues>
		implements VIricstockPhenotypeValuesDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockPhenotypeValues.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPhenotypeValuesDAOImpl
	 *
	 */
	public VIricstockPhenotypeValuesDAOImpl() {
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
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValueContaining(String qualValue) throws DataAccessException {

		return findVIricstockPhenotypeValuesByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValueContaining(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeValuesByQualValueContaining", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstockPhenotypeValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByPhenotypeId(java.math.BigDecimal phenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeValuesByPhenotypeId(phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByPhenotypeId(java.math.BigDecimal phenotypeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeValuesByPhenotypeId", startResult, maxRows, phenotypeId);
		
	
		System.out.println("findVIricstockPhenotypeValuesByPhenotypeId:" +  query.toString());
		
		//Session session = em.unwrap(JpaEntityManager.class).getActiveSession();
		//DatabaseQuery databaseQuery = ((EJBQueryImpl)query).getDatabaseQuery();
		//String sqlString = databaseQuery.getTranslatedSQLString(session, recordWithValues);
		
		return new LinkedHashSet<VIricstockPhenotypeValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQuanValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQuanValue(Integer quanValue) throws DataAccessException {

		return findVIricstockPhenotypeValuesByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQuanValue(Integer quanValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeValuesByQuanValue", startResult, maxRows, quanValue);
		return new LinkedHashSet<VIricstockPhenotypeValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeValuess
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeValues> findAllVIricstockPhenotypeValuess() throws DataAccessException {

		return findAllVIricstockPhenotypeValuess(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeValuess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeValues> findAllVIricstockPhenotypeValuess(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPhenotypeValuess", startResult, maxRows);
		return new LinkedHashSet<VIricstockPhenotypeValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValue(String qualValue) throws DataAccessException {

		return findVIricstockPhenotypeValuesByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValue(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeValuesByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstockPhenotypeValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPhenotypeValues findVIricstockPhenotypeValuesByPrimaryKey(Integer quanValue, String qualValue, java.math.BigDecimal phenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeValuesByPrimaryKey(quanValue, qualValue, phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPhenotypeValues findVIricstockPhenotypeValuesByPrimaryKey(Integer quanValue, String qualValue, java.math.BigDecimal phenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPhenotypeValuesByPrimaryKey", startResult, maxRows, quanValue, qualValue, phenotypeId);
			return (org.irri.iric.portal.chado.domain.VIricstockPhenotypeValues) query.getSingleResult();
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
	public boolean canBeMerged(VIricstockPhenotypeValues entity) {
		return true;
	}

	@Override
	public Set getUniqueValues(BigDecimal typeId) {
		// TODO Auto-generated method stub
		
		
		String sql = "select distinct quan_value, qual_value, phenotype_id from iric.iric_stock_phenotype where phenotype_id=" + typeId + " order by quan_value, qual_value";
		
		//System.out.println( "phenotype_id=" + typeId);
		
		//System.out.println( this.findAllVIricstockPhenotypeValuess().size() + " findAllVIricstockPhenotypeValuess" );
		
		//System.out.println( this.findAllVIricstockPhenotypeValuess());
		
		//return this.findVIricstockPhenotypeValuesByPhenotypeId(typeId);
		
		Set ret =new java.util.LinkedHashSet<CvTermUniqueValues>(executeSQL(sql));
		
		System.out.println(sql);
		System.out.println(ret.size() + " result=" + ret.size() );
		return ret;
	}
	
	private List<CvTermUniqueValues> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VIricstockPhenotypeValues.class).list();
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
}
