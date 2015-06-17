package org.irri.iric.portal.chado.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VLocusCvterm;
import org.irri.iric.portal.chado.domain.VLocusNotes;
import org.irri.iric.portal.domain.Locus;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusCvterm entities.
 * 
 */
@Repository("VLocusCvtermDAO")
@Transactional
public class VLocusCvtermDAOImpl extends AbstractJpaDao<VLocusCvterm> implements
		VLocusCvtermDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VLocusCvterm.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusCvtermDAOImpl
	 *
	 */
	public VLocusCvtermDAOImpl() {
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
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName) throws DataAccessException {

		return findVLocusCvtermByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name) throws DataAccessException {

		return findVLocusCvtermByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */
	@Transactional
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId) throws DataAccessException {

		return findVLocusCvtermByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */

	@Transactional
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.domain.VLocusCvterm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByName(String name) throws DataAccessException {

		return findVLocusCvtermByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigId(java.math.BigDecimal contigId) throws DataAccessException {

		return findVLocusCvtermByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigId(java.math.BigDecimal contigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByStrand(java.math.BigDecimal strand) throws DataAccessException {

		return findVLocusCvtermByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByStrand(java.math.BigDecimal strand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmax(java.math.BigDecimal fmax) throws DataAccessException {

		return findVLocusCvtermByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmax(java.math.BigDecimal fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName) throws DataAccessException {

		return findVLocusCvtermByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName) throws DataAccessException {

		return findVLocusCvtermByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvterm(String cvterm) throws DataAccessException {

		return findVLocusCvtermByCvterm(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvterm(String cvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvterm", startResult, maxRows, cvterm);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName) throws DataAccessException {

		return findVLocusCvtermByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmin(java.math.BigDecimal fmin) throws DataAccessException {

		return findVLocusCvtermByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmin(java.math.BigDecimal fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByAccessionContaining(String accession) throws DataAccessException {

		return findVLocusCvtermByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findVLocusCvtermByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(java.math.BigDecimal cvtermId) throws DataAccessException {

		return findVLocusCvtermByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(java.math.BigDecimal cvtermId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName) throws DataAccessException {

		return findVLocusCvtermByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId) throws DataAccessException {

		return findVLocusCvtermByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.domain.VLocusCvterm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermByAccession
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByAccession(String accession) throws DataAccessException {

		return findVLocusCvtermByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByAccession(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName) throws DataAccessException {

		return findVLocusCvtermByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findAllVLocusCvterms() throws DataAccessException {

		return findAllVLocusCvterms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findAllVLocusCvterms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusCvterms", startResult, maxRows);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvtermContaining(String cvterm) throws DataAccessException {

		return findVLocusCvtermByCvtermContaining(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvtermContaining(String cvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvtermContaining", startResult, maxRows, cvterm);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusCvterm entity) {
		return true;
	}

	@Override
	public List<Locus> getLocusByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Locus> getLocusByDescription(String goterm, String organism) {
		// TODO Auto-generated method stub
		
		
		String sql = "select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, cv.cvterm_id, db.accession, c.name cv_name, "
		  + " dbms_lob.substr(fp.value,1000,1) || '; GOterm=' || cv.name  cvterm, f.organism_id, o.common_name " 
		  + " from iric.feature f, iric.featureloc fl, iric.feature_cvterm fc, iric.cvterm cv , iric.feature fsrc, iric.organism o, iric.dbxref db, iric.cv c , IRIC.FEATUREPROP FP , iric.cvterm CV2 " 
		+ " where fc.feature_id=f.feature_id and  fc.cvterm_id=cv.cvterm_id and fsrc.feature_id=fl.srcfeature_id and fl.feature_id=f.feature_id and f.organism_id=o.organism_id "
		+ " and c.cv_id=cv.cv_id and c.name in('molecular_function','biological_process','cellular_component') "
		+ " and cv.dbxref_id=db.dbxref_id AND FP.FEATURE_ID=F.FEATURE_ID AND FP.TYPE_ID=CV2.CVTERM_ID AND CV2.name='Note'" 
		+ " and cv.name='" + goterm.replace("'", "''") + "'"
		+ " and o.common_name='" + organism + "'"
		+ " order by contig_name, fmin, fmax";
		
		//List list=new ArrayList();
		//list.addAll( findVLocusCvtermByCvterm(goterm) );
		//return list;
		return executeSQL(sql);
	}

	@Override
	public List getAllTerms() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List getAllTerms(String organism) {
		// TODO Auto-generated method stub
		Set setlocusterms = findVLocusCvtermByCommonName(organism);
		Iterator<VLocusCvterm> itlocusterms = setlocusterms.iterator();
		Set setTerms=new TreeSet();
		while(itlocusterms.hasNext()) {
			setTerms.add( itlocusterms.next().getCvterm() );
		}
		List list=new ArrayList();
		list.addAll(setTerms);
		
		AppContext.debug("VLocusCvtermDAO getAllTerms " + organism + " result=" + list.size() + "; setlocusterms=" + setlocusterms.size() );

		return list;
	}
	

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end,
			String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset,
			String organism) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	private List<Locus> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		List listResult = getSession().createSQLQuery(sql).addEntity(VLocusCvterm.class).list();
		AppContext.debug("result " + listResult.size() + " loci");
		return listResult;
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List getAllTerms(String cv, String organism) {
		// TODO Auto-generated method stub
		return null;
	}


}
