package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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
import org.irri.iric.portal.chado.postgres.domain.VLocusNotes;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.Locus;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusNotes entities.
 * 
 */
@Repository("LocusNotesDAO")
@Primary()
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
	 * EntityManager injected by Spring for persistence unit VM_IRIC
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
	 * JPQL Query - findVLocusNotesByFeatureId
	 *
	 */
	@Transactional
	public VLocusNotes findVLocusNotesByFeatureId(Long featureId) throws DataAccessException {

		return findVLocusNotesByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByFeatureId
	 *
	 */

	@Transactional
	public VLocusNotes findVLocusNotesByFeatureId(Long featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindVLocusNotesByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusNotes) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
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
		Query query = createNamedQuery("PGfindAllVLocusNotess", startResult, maxRows);
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
		Query query = createNamedQuery("PGfindVLocusNotesByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByContigId
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigId(Long contigId) throws DataAccessException {

		return findVLocusNotesByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByContigId(Long contigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVLocusNotesByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByStrand
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByStrand(Integer strand) throws DataAccessException {

		return findVLocusNotesByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVLocusNotesByStrand", startResult, maxRows, strand);
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
		Query query = createNamedQuery("PGfindVLocusNotesByCommonName", startResult, maxRows, commonName);
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
		Query query = createNamedQuery("PGfindVLocusNotesByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByFmax
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmax(Integer fmax) throws DataAccessException {

		return findVLocusNotesByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVLocusNotesByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByNotesContaining(String notes) throws DataAccessException {

		return findVLocusNotesByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVLocusNotesByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByOrganismId(Long organismId) throws DataAccessException {

		return findVLocusNotesByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVLocusNotesByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusNotes findVLocusNotesByPrimaryKey(Long featureId) throws DataAccessException {

		return findVLocusNotesByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusNotes findVLocusNotesByPrimaryKey(Long featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindVLocusNotesByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusNotes) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusNotesByFmin
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmin(Integer fmin) throws DataAccessException {

		return findVLocusNotesByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVLocusNotesByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusNotesByNotes
	 *
	 */
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByNotes(String notes) throws DataAccessException {

		return findVLocusNotesByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusNotesByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusNotes> findVLocusNotesByNotes(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVLocusNotesByNotes", startResult, maxRows, notes);
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
		Query query = createNamedQuery("PGfindVLocusNotesByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusNotes>(query.getResultList());
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

	

	@Override
	public List<Locus> getLocusByName(String name) {
		// TODO Auto-generated method stub
		
		String sql = "select distinct f.feature_id, f.name \"name\", fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, f3.value notes, f.organism_id, o.common_name"
				+ " from public.featureloc fl, public.feature fsrc, public.organism o , public.cvterm cvtype,  public.feature f"
				+ " left join ( select f2.feature_id, fp.value from public.featureprop fp, public.cvterm cv, public.feature f2" 
				         + " where fp.feature_id=f2.feature_id and  fp.type_id=cv.cvterm_id and cv.name='Note') f3"
				         + " on f.feature_id=f3.feature_id" 
				+ " where f.feature_id=fl.feature_id "
				+ " and lower(f.name)='" + name.toLowerCase() + "'" 
				+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id  and fsrc.organism_id=f.organism_id "
				+ " and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
				+ " order by contig_name, fmin, fmax";
		         
		return executeSQL(sql);
	}

	@Override
	public List<Locus> getLocusByDescription(String description, String organism) {
		// TODO Auto-generated method stub
		
		List listresult = new ArrayList();
		/*
		String sql = "select distinct f.feature_id, f.uniquename \"name\", fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, fp.value notes, f.organism_id, o.common_name from public.feature f, public.featureloc fl, public.featureprop fp, public.cvterm cv , public.feature fsrc, public.organism o , public.cvterm cvtype"
				+ " where fp.feature_id=f.feature_id and  fp.type_id=cv.cvterm_id and f.feature_id=fl.feature_id and "
				//+ " contains(fp.value,'" + description + "',1)>0 "
				+ " lower(fp.value) like '%" + description.toLowerCase() + "%' "
				+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id "
				+ " and cv.name='Note' and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
				+ " and  o.common_name='" + organism + "'"
				+ " order by contig_name, fmin, fmax";
		*/
		
		//String sql = "select distinct feature_id, name, fmin, fmax, strand, contig_id, contig_name, lower(notes) notes, organism_id, common_name from ("
		String sql = 
		 "select distinct f.feature_id, f.name \"name\", fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, substring(fp.value from 1 for 1000) notes, f.organism_id, o.common_name "
		+ " from public.feature f, public.featureloc fl, public.featureprop fp, public.cvterm cv , public.feature fsrc, public.organism o, public.cvterm cvtype "
		+ " where fp.feature_id=f.feature_id and  fp.type_id=cv.cvterm_id and f.feature_id=fl.feature_id and "
		//+ " contains(fp.value,'" + description + "',1)>0 "
		+ " lower(fp.value) like '%" + description.toLowerCase() + "%' "
		+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id  and fsrc.organism_id=f.organism_id"
		+ " and cv.name='Note' and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
		+ " and  o.common_name='" + organism + "'"
		

		+ " union select f.feature_id, f.name \"name\", fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name , '(' || lower(s.name) || ') ' || substring(fp.value from 1 for 1000)  notes, f.organism_id, o.common_name" 
		+ " from public.feature f, public.featureloc fl,   public.feature fsrc, public.organism o, public.synonym s, public.feature_synonym fsyn,   public.featureprop fp, public.cvterm cv , public.cvterm cvtype"
		+ " where f.feature_id=fsyn.feature_id and fsyn.synonym_id=s.synonym_id"
		+ " and f.feature_id=fl.feature_id and"
		+ " fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id and fsrc.organism_id=f.organism_id"
		+ " and lower(s.name) like '%" + description.toLowerCase() + "%' "
		+ " and  o.common_name='" + organism + "'"
		
		+ " and fp.feature_id=f.feature_id and  fp.type_id=cv.cvterm_id"
		+ " and cv.name='Note' and f.type_id=cvtype.cvterm_id and cvtype.name='gene' "
		
		+ " order by contig_name, fmin, fmax";
		
		
		return executeSQL(sql);
		
		
	}

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism) {
		// TODO Auto-generated method stub
		
		// use left join to include even without description
		/*select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, dbms_lob.substr(f3.value,1000,1) notes, f.organism_id, o.common_name 
		  from public.featureloc fl,  public.feature fsrc, public.organism o, iric.cvterm cvtype, iric.feature f   
		  left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.cvterm cv, iric.feature f2 
		          where fp.feature_id=f2.feature_id and  fp.type_id=cv.cvterm_id and cv.name='Note') f3
		         on f.feature_id=f3.feature_id 
		  where f.feature_id=fl.feature_id  and ((fl.fmin>=20000 and fl.fmax<=40000)  or (fl.fmin<=20000 and fl.fmax>=40000)  or (fl.fmin<=20000 and fl.fmax>=20000)  or (fl.fmin<=40000 and fl.fmax>=40000)  )
		  and lower(fsrc.uniquename)='chr01'  and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id 
		  and cvtype.name='gene' and cvtype.cvterm_id=f.type_id  
		  and o.common_name='Kasalath'  order by contig_name, fmin, fmax;
		  */
		
		String sql = "select distinct f.feature_id, f.name \"name\", fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, f3.value notes, f.organism_id, o.common_name "
		  + " from public.featureloc fl,  public.feature fsrc, public.organism o, public.cvterm cvtype, public.feature f"   
		  + " left join ( select f2.feature_id, fp.value from public.featureprop fp, public.cvterm cv, public.feature f2" 
		         + " where fp.feature_id=f2.feature_id and  fp.type_id=cv.cvterm_id and cv.name='Note') f3"
		         + " on f.feature_id=f3.feature_id" 
		  + " where f.feature_id=fl.feature_id "
			+ " and ((fl.fmin>=" + start + " and fl.fmax<=" + end + ") "
			+ " or (fl.fmin<=" + start + " and fl.fmax>=" + end +") "
			+ " or (fl.fmin<=" + start + " and fl.fmax>=" + start +") "
			+ " or (fl.fmin<=" + end + " and fl.fmax>=" + end +") "
			+ " ) and lower(fsrc.uniquename)='" +  contig.toLowerCase() + "' "
			+ " and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id  and fsrc.organism_id=f.organism_id "
			+ " and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
			+ " and o.common_name='" + organism + "' "
			+ " order by contig_name, fmin, fmax";
		         
		  
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
		
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");

		StringBuffer sql = new StringBuffer();
		sql.append(	"select distinct f.feature_id, f.name \"name\", fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name, f3.value notes, f.organism_id, o.common_name "
		  + " from public.featureloc fl,  public.feature fsrc, public.organism o, public.cvterm cvtype, ");
		
		  sql.append( "  (select distinct column_value pos from table(sys.odcinumberlist(" + AppContext.toCSV(posset)+ "))) postable, ");
		
		sql.append(" public.feature f"   
		  + " left join ( select f2.feature_id, fp.value from public.featureprop fp, public.cvterm cv, public.feature f2" 
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
			
			
			sql.append(" and fsrc.feature_id=fl.srcfeature_id and f.organism_id=o.organism_id and fsrc.organism_id=f.organism_id "
			+ " and cvtype.name='gene' and cvtype.cvterm_id=f.type_id "
			//+ " and o.common_name='" + organism + "' "
			+ " and o.organism_id=" + listitemsdao.getOrganismByName(organism).getOrganismId() 
			+ " order by contig_name, fmin, fmax");
			
		//AppContext.debug("sql query: " + sql);
		return executeSQL(sql.toString());
		
	}
	
	

	private List<Locus> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		List listResult = new ArrayList();
		listResult.addAll( new TreeSet(getSession().createSQLQuery(sql).addEntity(VLocusNotes.class).list()));
		AppContext.debug("result " + listResult.size() + " loci");
		return listResult;
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
}
