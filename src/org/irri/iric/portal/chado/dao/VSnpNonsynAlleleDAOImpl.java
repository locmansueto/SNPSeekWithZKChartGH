package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VSnpNonsynAllele;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePosition;
//import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpNonsynAllele entities.
 * 
 */
@Repository("SnpNonsynAlleleDAO")
@Transactional
public class VSnpNonsynAlleleDAOImpl extends AbstractJpaDao<VSnpNonsynAllele>
		implements VSnpNonsynAlleleDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpNonsynAllele.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpNonsynAlleleDAOImpl
	 *
	 */
	public VSnpNonsynAlleleDAOImpl() {
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
	 * JPQL Query - findVSnpNonsynAlleleByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpNonsynAllele findVSnpNonsynAlleleByPrimaryKey(Integer snpFeatureId, String nonSynAllele) throws DataAccessException {

		return findVSnpNonsynAlleleByPrimaryKey(snpFeatureId, nonSynAllele, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynAlleleByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpNonsynAllele findVSnpNonsynAlleleByPrimaryKey(Integer snpFeatureId, String nonSynAllele, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpNonsynAlleleByPrimaryKey", startResult, maxRows, snpFeatureId, nonSynAllele);
			return (org.irri.iric.portal.chado.domain.VSnpNonsynAllele) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVSnpNonsynAlleles
	 *
	 */
	@Transactional
	public Set<VSnpNonsynAllele> findAllVSnpNonsynAlleles() throws DataAccessException {

		return findAllVSnpNonsynAlleles(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpNonsynAlleles
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynAllele> findAllVSnpNonsynAlleles(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpNonsynAlleles", startResult, maxRows);
		return new LinkedHashSet<VSnpNonsynAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAllele
	 *
	 */
	@Transactional
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAllele(String nonSynAllele) throws DataAccessException {

		return findVSnpNonsynAlleleByNonSynAllele(nonSynAllele, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAllele
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAllele(String nonSynAllele, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynAlleleByNonSynAllele", startResult, maxRows, nonSynAllele);
		return new LinkedHashSet<VSnpNonsynAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynAlleleBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpNonsynAlleleBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynAlleleBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnpNonsynAllele>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAlleleContaining
	 *
	 */
	@Transactional
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAlleleContaining(String nonSynAllele) throws DataAccessException {

		return findVSnpNonsynAlleleByNonSynAlleleContaining(nonSynAllele, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAlleleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAlleleContaining(String nonSynAllele, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynAlleleByNonSynAlleleContaining", startResult, maxRows, nonSynAllele);
		return new LinkedHashSet<VSnpNonsynAllele>(query.getResultList());
	}


	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(String chr, Integer start, Integer end) throws DataAccessException {
		System.out.println(" snpfeatureid between " + AppContext.convertRegion2Snpfeatureid( chr,  Long.valueOf(start)) + " and " + AppContext.convertRegion2Snpfeatureid( chr, Long.valueOf(end)) );
		Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdBetween", -1, -1, AppContext.convertRegion2Snpfeatureid( chr,  Long.valueOf(start)), AppContext.convertRegion2Snpfeatureid( chr, Long.valueOf(end)));
		return new HashSet<SnpsNonsynAllele>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(String chr, Collection poslist) throws DataAccessException {
		Set setSnpfeatureid = new HashSet();
		
		if(chr.toLowerCase().equals("any")) {
			Iterator<MultiReferencePosition> it = poslist.iterator();
			StringBuffer buff = new StringBuffer();
			while(it.hasNext()) {
				MultiReferencePosition pos = it.next();
				BigDecimal snpfearueid = AppContext.convertRegion2Snpfeatureid( pos.getContig(),  pos.getPosition().longValue()) ;
				setSnpfeatureid.add( snpfearueid);
				buff.append(snpfearueid + ", " );
			}
			//AppContext.debug(" snpfeatureid in " + buff);
			
			Set setAll = new HashSet();
			Set sets[] = AppContext.setSlicer(setSnpfeatureid);
			for(int iset=0; iset<sets.length; iset++) {
				Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1, -1,setSnpfeatureid);
				setAll.addAll(query.getResultList());
			}
			//return new HashSet<SnpsNonsynAllele>(query.getResultList());
			return setAll;
		}
		else if(chr.toLowerCase().equals("loci")) {
			Iterator<Locus> it = poslist.iterator();
			//StringBuffer buff = new StringBuffer();
			Set setPos = new TreeSet();
			while(it.hasNext()) {
				Locus loc=it.next();
				setPos.addAll(  findSnpNonsynAlleleByChrPosBetween(loc.getContig(),  loc.getFmin(),  loc.getFmax()) ); 
			}
			return setPos;
		}
		else {
			Iterator<BigDecimal> it = poslist.iterator();
			StringBuffer buff = new StringBuffer();
			while(it.hasNext()) {
				BigDecimal snpfearueid = AppContext.convertRegion2Snpfeatureid( chr,  it.next().longValue()) ;
				setSnpfeatureid.add( snpfearueid);
				buff.append(snpfearueid + ", " );
			}
			//System.out.println(" snpfeatureid in " + buff);
			//Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1, -1,setSnpfeatureid);
			//return new HashSet<SnpsNonsynAllele>(query.getResultList());
			
			Set setAll = new HashSet();
			Set sets[] = AppContext.setSlicer(setSnpfeatureid);
			for(int iset=0; iset<sets.length; iset++) {
				Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1, -1,setSnpfeatureid);
				setAll.addAll(query.getResultList());
			}
			//return new HashSet<SnpsNonsynAllele>(query.getResultList());
			return setAll;
		}
		
	}
	
	
	
	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpNonsynAllele entity) {
		return true;
	}
}
