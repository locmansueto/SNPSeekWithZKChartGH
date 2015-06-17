package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
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
import org.irri.iric.portal.chado.domain.VLocusNotes;
import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusNotes entities.
 * 
 */
@Repository("VLocusNotesDAO")
//@Repository("LocusNotesDAO")
@Transactional
public class VLocusNotesDAOImpl extends AbstractJpaDao<VLocusNotes> implements
		VLocusNotesDAO {

	@Autowired
	private ListItemsDAO listitemsdao;
	
	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VLocusNotes.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusNotesDAOImpl
	 *
	 */
	public VLocusNotesDAOImpl() {
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
	 * JPQL Query - findVLocusNotesByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByNameContaining(String name) throws DataAccessException {

		return findVLocusNotesByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByStrand
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByStrand(java.math.BigDecimal strand) throws DataAccessException {

		return findVLocusNotesByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByStrand(java.math.BigDecimal strand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusNotess
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findAllVLocusNotess() throws DataAccessException {

		return findAllVLocusNotess(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusNotess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findAllVLocusNotess(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusNotess", startResult, maxRows);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByCommonName(String commonName) throws DataAccessException {

		return findVLocusNotesByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByFmax
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmax(java.math.BigDecimal fmax) throws DataAccessException {

		return findVLocusNotesByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmax(java.math.BigDecimal fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByName
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByName(String name) throws DataAccessException {

		return findVLocusNotesByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByCommonNameContaining(String commonName) throws DataAccessException {

		return findVLocusNotesByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByFmin
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmin(java.math.BigDecimal fmin) throws DataAccessException {

		return findVLocusNotesByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmin(java.math.BigDecimal fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByContigName
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigName(String contigName) throws DataAccessException {

		return findVLocusNotesByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigName(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByContigId
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigId(java.math.BigDecimal contigId) throws DataAccessException {

		return findVLocusNotesByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigId(java.math.BigDecimal contigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigNameContaining(String contigName) throws DataAccessException {

		return findVLocusNotesByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigNameContaining(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	
	
	/**
	 * JPQL Query - findVLocusNotesByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findVLocusNotesByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusNotesByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByFeatureId
	 *
	 */
	@Transactional
	public VLocusNotes findVLocusNotesByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVLocusNotesByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByFeatureId
	 *
	 */

	@Transactional
	public VLocusNotes findVLocusNotesByFeatureId(BigDecimal featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusNotesByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.domain.VLocusNotes) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusNotesByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusNotes findVLocusNotesByPrimaryKey(BigDecimal featureId) throws DataAccessException {

		return findVLocusNotesByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusNotes findVLocusNotesByPrimaryKey(BigDecimal featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusNotesByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.domain.VLocusNotes) query.getSingleResult();
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
	public boolean canBeMerged(VLocusNotes entity) {
		return true;
	}
	

	private List<Locus> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		List listResult = getSession().createSQLQuery(sql).addEntity(VLocusNotes.class).list();
		AppContext.debug("result " + listResult.size() + " loci");
		return listResult;
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List<Locus> getLocusByName(String name) {
		// TODO Auto-generated method stub
		
		String sql = "select distinct f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(f3.value,1000,1) notes, f.organism_id, o.common_name"
				+ " from iric.featureloc fl, iric.feature fsrc, iric.organism o , iric.cvterm cvtype,  iric.feature f"
				+ " left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.cvterm cv, iric.feature f2" 
				         + " where fp.feature_id=f2.feature_id and  fp.type_id=cv.cvterm_id and cv.name='Note') f3"
				         + " on f.feature_id=f3.feature_id" 
				+ " where f.feature_id=fl.feature_id "
				+ " and lower(f.name)='" + name.toLowerCase() + "'" 
				+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
				+ " and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
				+ " order by contig_name, fmin, fmax";

		
		         
		/*         
		String sql = "select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(fp.value,1000,1) notes, f.organism_id, o.common_name from iric.feature f, iric.featureloc fl, iric.featureprop fp, iric.cvterm cv , iric.feature fsrc, iric.organism o , iric.cvterm cvtype"
				+ " where fp.feature_id=f.feature_id and  fp.type_id=cv.cvterm_id and f.feature_id=fl.feature_id "
				+ " and lower(f.name)='" + name.toLowerCase() + "'" 
				+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
				+ " and cv.name='Note' and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
				+ " order by contig_name, fmin, fmax";
				*/
				
		return executeSQL(sql);
		//List listResult = executeSQL(sql);
		//return (Locus)listResult.get(0); 
	}

	@Override
	public List<Locus> getLocusByDescription(String description, String organism) {
		// TODO Auto-generated method stub
		
		List listresult = new ArrayList();
		String sql = "select distinct f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(fp.value,1000,1) notes, f.organism_id, o.common_name from iric.feature f, iric.featureloc fl, iric.featureprop fp, iric.cvterm cv , iric.feature fsrc, iric.organism o , iric.cvterm cvtype"
				+ " where fp.feature_id=f.feature_id and  fp.type_id=cv.cvterm_id and f.feature_id=fl.feature_id and "
				+ " contains(fp.value,'" + description + "',1)>0 "
		//		+ " lower(f.name) like '%" + description.toLowerCase() + "%' "
				+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
				+ " and cv.name='Note' and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
				+ " and  o.common_name='" + organism + "'"
				+ " order by contig_name, fmin, fmax";
		return executeSQL(sql);
		
		
		//List listresult = new ArrayList();
		//listresult.addAll( findVLocusNotesByNotesContaining("%" + description + "%", -1,-1) );
		//listresult.addAll( this.findVLocusNotesByNameContaining(description));
		
		//AppContext.debug("result " + listresult.size() + " loci");
		//return listresult;
	}

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism) {
		// TODO Auto-generated method stub
		
		// use left join to include even without description
		/*select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(f3.value,1000,1) notes, f.organism_id, o.common_name 
		  from iric.featureloc fl,  iric.feature fsrc, iric.organism o, iric.cvterm cvtype, iric.feature f   
		  left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.cvterm cv, iric.feature f2 
		          where fp.feature_id=f2.feature_id and  fp.type_id=cv.cvterm_id and cv.name='Note') f3
		         on f.feature_id=f3.feature_id 
		  where f.feature_id=fl.feature_id  and ((fl.fmin>=20000 and fl.fmax<=40000)  or (fl.fmin<=20000 and fl.fmax>=40000)  or (fl.fmin<=20000 and fl.fmax>=20000)  or (fl.fmin<=40000 and fl.fmax>=40000)  )
		  and lower(fsrc.uniquename)='chr01'  and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id 
		  and cvtype.name='gene' and cvtype.cvterm_id=f.type_id  
		  and o.common_name='Kasalath'  order by contig_name, fmin, fmax;
		  */
		
		String sql = "select distinct f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(f3.value,1000,1) notes, f.organism_id, o.common_name "
		  + " from iric.featureloc fl,  iric.feature fsrc, iric.organism o, iric.cvterm cvtype, iric.feature f"   
		  + " left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.cvterm cv, iric.feature f2" 
		         + " where fp.feature_id=f2.feature_id and  fp.type_id=cv.cvterm_id and cv.name='Note') f3"
		         + " on f.feature_id=f3.feature_id" 
		  + " where f.feature_id=fl.feature_id "
			+ " and ((fl.fmin>=" + start + " and fl.fmax<=" + end + ") "
			+ " or (fl.fmin<=" + start + " and fl.fmax>=" + end +") "
			+ " or (fl.fmin<=" + start + " and fl.fmax>=" + start +") "
			+ " or (fl.fmin<=" + end + " and fl.fmax>=" + end +") "
			+ " ) and lower(fsrc.uniquename)='" +  contig.toLowerCase() + "' "
			+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
			+ " and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
			+ " and o.common_name='" + organism + "' "
			+ " order by contig_name, fmin, fmax";
		         
		  
		/*
		String sql = "select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(fp.value,1000,1) notes, f.organism_id, o.common_name from iric.feature f, iric.featureloc fl, iric.featureprop fp, iric.cvterm cv , iric.feature fsrc, iric.organism o, iric.cvterm cvtype "
				+ " where fp.feature_id=f.feature_id and  fp.type_id=cv.cvterm_id and f.feature_id=fl.feature_id "
				 
				+ " and ((fl.fmin>=" + start + " and fl.fmax<=" + end + ") "
					+ " or (fl.fmin<=" + start + " and fl.fmax>=" + end +") "
					+ " or (fl.fmin<=" + start + " and fl.fmax>=" + start +") "
					+ " or (fl.fmin<=" + end + " and fl.fmax>=" + end +") "
				+ " ) and lower(fsrc.uniquename)='" +  contig.toLowerCase() + "' "
				+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
				+ " and cv.name='Note' and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "

				+ " and o.common_name='" + organism + "' "
				+ " order by contig_name, fmin, fmax";
				*/
		//AppContext.debug("sql query: " + sql);
		return executeSQL(sql);
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset,
			String organism) {
		Set allset=new HashSet();
		Set sets[] = AppContext.setSlicer(new HashSet(posset),500);
		for(int iset=0; iset<sets.length; iset++) {
			allset.addAll( _getLocusByContigPositions( contig,  sets[iset],  organism));
		}
		List listall=new ArrayList();
		listall.addAll(allset);
		return listall;
	}

	
	private List _getLocusByContigPositions(String contig, Collection posset, String organism) {
		
		/*
		// TODO Auto-generated method stub
		String sql = "select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(fp.value,1000,1) notes, f.organism_id, o.common_name from iric.feature f, iric.featureloc fl, iric.featureprop fp, iric.cvterm cv , iric.feature fsrc, iric.organism o, iric.cvterm cvtype "
				+ " where fp.feature_id=f.feature_id and  fp.type_id=cv.cvterm_id and f.feature_id=fl.feature_id "
				 
				+ " and ((fl.fmin>=" + start + " and fl.fmax<=" + end + ") "
					+ " or (fl.fmin<=" + start + " and fl.fmax>=" + end +") "
					+ " or (fl.fmin<=" + start + " and fl.fmax>=" + start +") "
					+ " or (fl.fmin<=" + end + " and fl.fmax>=" + end +") "
				+ " ) and lower(fsrc.uniquename)='" +  contig.toLowerCase() + "' "
				+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
				+ " and cv.name='Note' and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "

				+ " and o.common_name='" + organism + "' "
				+ " order by contig_name, fmin, fmax";
		AppContext.debug("sql query: " + sql);
		return executeSQL(sql);
		*/
		
		/*
		Set listLocus = new LinkedHashSet();
		Set sortsetpos = new TreeSet(posset);
		Iterator<BigDecimal> it=sortsetpos.iterator();
		while(it.hasNext()) {
			BigDecimal pos = it.next();
			listLocus.addAll( getLocusByRegion( contig, pos.longValue(), pos.longValue(),  organism) );
		}
		List list = new ArrayList();
		list.addAll(listLocus);
		return list;
		*/
		
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItemsDAO");

		StringBuffer sql = new StringBuffer();
		sql.append(	"select distinct f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(f3.value,1000,1) notes, f.organism_id, o.common_name "
		  + " from iric.featureloc fl,  iric.feature fsrc, iric.organism o, iric.cvterm cvtype, ");
		
		  sql.append( "  (select distinct column_value pos from table(sys.odcinumberlist(" + AppContext.toCSV(posset)+ "))) postable, ");
		
		sql.append(" iric.feature f"   
		  + " left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.cvterm cv, iric.feature f2" 
		         + " where fp.feature_id=f2.feature_id and  fp.type_id=cv.cvterm_id and cv.name='Note') f3"
		         + " on f.feature_id=f3.feature_id" 
		  + " where f.feature_id=fl.feature_id ");
		    
		
		/*
			+ " and ((fl.fmin>=" + start + " and fl.fmax<=" + end + ") "
			+ " or (fl.fmin<=" + start + " and fl.fmax>=" + end +") "
			+ " or (fl.fmin<=" + start + " and fl.fmax>=" + start +") "
			+ " or (fl.fmin<=" + end + " and fl.fmax>=" + end +") "
			+ " )" ;
			*/
		

			sql.append("  and lower(fsrc.uniquename)='" + contig.toLowerCase() + "' and postable.pos between fl.fmin and fl.fmax ");
			
			/*
			Iterator<String> itPoslist = AppContext.setSlicerIds(new HashSet(posset)).iterator();
			while(itPoslist.hasNext()) {
				sql.append(" pos in (" + itPoslist.next() + ") ");
				if(itPoslist.hasNext()) 
					sql.append(" or ");
			}
			*/
			
			
			sql.append(" and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
			+ " and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
			//+ " and o.common_name='" + organism + "' "
			+ " and o.organism_id=" + listitemsdao.getOrganismByName(organism).getOrganismId() 
			+ " order by contig_name, fmin, fmax");
			
		//AppContext.debug("sql query: " + sql);
		return executeSQL(sql.toString());
		
	}
	
	
	
	
	
	
	
}
