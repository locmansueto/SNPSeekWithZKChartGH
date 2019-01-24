package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import org.irri.iric.portal.AppContext;
//import org.irri.iric.portal.chado.dao.VConvertRefposPrecompDAOImpl.MultiReferenceToPositionSorter;
import org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allrefs;
import org.irri.iric.portal.dao.OrganismDAO;
import org.irri.iric.portal.dao.ScaffoldDAO;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Scaffold;
import org.irri.iric.portal.genotype.VariantStringData;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VConvertposNb2allrefs entities.
 * 
 */
@Repository("VConvertposNb2allrefsDAO")
// @Repository("MultipleReferenceConverterDAO")
@Transactional
public class VConvertposNb2allrefsDAOImpl extends AbstractJpaDao<VConvertposNb2allrefs>
		implements VConvertposNb2allrefsDAO {

	@Autowired
	OrganismDAO orgdao;
	@Autowired
	ScaffoldDAO scaffolddao;

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VConvertposNb2allrefs.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VConvertposNb2allrefsDAOImpl
	 *
	 */
	public VConvertposNb2allrefsDAOImpl() {
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
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcall(String fromRefcall)
			throws DataAccessException {

		return findVConvertposNb2allrefsByFromRefcall(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcall(String fromRefcall, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefsByFromRefcall", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertposNb2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsBySnpFeatureId
	 *
	 */
	@Transactional
	public VConvertposNb2allrefs findVConvertposNb2allrefsBySnpFeatureId(BigDecimal snpFeatureId)
			throws DataAccessException {

		return findVConvertposNb2allrefsBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcallContaining(String fromRefcall)
			throws DataAccessException {

		return findVConvertposNb2allrefsByFromRefcallContaining(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcallContaining(String fromRefcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefsByFromRefcallContaining", startResult, maxRows,
				fromRefcall);
		return new LinkedHashSet<VConvertposNb2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVConvertposNb2allrefss
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allrefs> findAllVConvertposNb2allrefss() throws DataAccessException {

		return findAllVConvertposNb2allrefss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVConvertposNb2allrefss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findAllVConvertposNb2allrefss(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVConvertposNb2allrefss", startResult, maxRows);
		return new LinkedHashSet<VConvertposNb2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromContigId
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromContigId(BigDecimal fromContigId)
			throws DataAccessException {

		return findVConvertposNb2allrefsByFromContigId(fromContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromContigId(BigDecimal fromContigId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefsByFromContigId", startResult, maxRows, fromContigId);
		return new LinkedHashSet<VConvertposNb2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromPosition
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromPosition(BigDecimal fromPosition)
			throws DataAccessException {

		return findVConvertposNb2allrefsByFromPosition(fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromPosition(BigDecimal fromPosition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefsByFromPosition", startResult, maxRows, fromPosition);
		return new LinkedHashSet<VConvertposNb2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromOrganismId(java.math.BigDecimal fromOrganismId)
			throws DataAccessException {

		return findVConvertposNb2allrefsByFromOrganismId(fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromOrganismId(java.math.BigDecimal fromOrganismId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefsByFromOrganismId", startResult, maxRows,
				fromOrganismId);
		return new LinkedHashSet<VConvertposNb2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByPrimaryKey
	 *
	 */
	@Transactional
	public VConvertposNb2allrefs findVConvertposNb2allrefsByPrimaryKey(BigDecimal snpFeatureId)
			throws DataAccessException {

		return findVConvertposNb2allrefsByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsByPrimaryKey
	 *
	 */

	@Transactional
	public VConvertposNb2allrefs findVConvertposNb2allrefsByPrimaryKey(BigDecimal snpFeatureId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposNb2allrefsByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allrefs) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVConvertposNb2allrefsBySnpFeatureId
	 *
	 */

	@Transactional
	public VConvertposNb2allrefs findVConvertposNb2allrefsBySnpFeatureId(BigDecimal snpFeatureId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposNb2allrefsBySnpFeatureId", startResult, maxRows,
					snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allrefs) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromOrgIdContigIdPosBetween(
			java.math.BigDecimal fromOrganismId, BigDecimal contigId, BigDecimal start, BigDecimal end)
			throws DataAccessException {
		Query query = createNamedQuery("findVConvertposNb2allrefsByFromOrgIdContigIdPosBetween", -1, -1, fromOrganismId,
				contigId, start, end);
		return new LinkedHashSet<VConvertposNb2allrefs>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromOrgIdContigIdPosBetween(String fromOrganism,
			String contig, BigDecimal start, BigDecimal end) throws DataAccessException {

		orgdao = (OrganismDAO) AppContext.checkBean(orgdao, "OrganismDAO");
		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");

		Organism npborg = orgdao.getMapName2Organism().get(fromOrganism);

		/*
		 * Map<BigDecimal, String> mapOrgId2Name=new HashMap(); Iterator<Organism>
		 * itOrgs = orgdao.getMapName2Organism().values().iterator();
		 * while(itOrgs.hasNext()) { Organism org = itOrgs.next(); mapOrgId2Name.put(
		 * org.getOrganismId(), org.getName()); }
		 */

		Scaffold npbcontig = scaffolddao.getScaffold(contig, npborg.getOrganismId());
		return findVConvertposNb2allrefsByFromOrgIdContigIdPosBetween(npborg.getOrganismId(), npbcontig.getFeatureId(),
				start, end);
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VConvertposNb2allrefs entity) {
		return true;
	}

	@Override
	public MultiReferenceConversion convertPosition(MultiReferenceConversion fromPos, String toReference,
			String toContig) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus, String toReference, String toContig)
			throws Exception {
		

		Set setConv = findVConvertposNb2allrefsByFromOrgIdContigIdPosBetween(fromLocus.getOrganism(),
				fromLocus.getContig(), BigDecimal.valueOf(fromLocus.getFmin()),
				BigDecimal.valueOf(fromLocus.getFmax()));
		List listConv = new ArrayList();
		listConv.addAll(setConv);

		if (listConv.size() < 2)
			throw new RuntimeException("No reference coordinate conversion at start (" + fromLocus.getFmin() + ") to "
					+ toReference + " found");

		// Collections.sort(listConv, new MultiReferenceToPositionSorter());

		VConvertposNb2allrefs coversionStart = (VConvertposNb2allrefs) listConv.get(0);
		VConvertposNb2allrefs coversionEnd = (VConvertposNb2allrefs) listConv.get(listConv.size() - 1);

		BigDecimal actualToContigStart = null;
		BigDecimal actualToContigEnd = null;
		BigDecimal toStart = null;
		BigDecimal toEnd = null;
		if (toReference.equals(Organism.REFERENCE_9311)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.get_311ContigId();
				actualToContigEnd = coversionEnd.get_311ContigId();
			}
			toStart = coversionStart.get_311Position();
			toEnd = coversionEnd.get_311Position();
		} else if (toReference.equals(Organism.REFERENCE_IR64)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.getIr64ContigId();
				actualToContigEnd = coversionEnd.getIr64ContigId();
			}
			toStart = coversionStart.getIr64Position();
			toEnd = coversionEnd.getIr64Position();
		} else if (toReference.equals(Organism.REFERENCE_DJ123)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.getDj123ContigId();
				actualToContigEnd = coversionEnd.getDj123ContigId();
			}
			toStart = coversionStart.getDj123Position();
			toEnd = coversionEnd.getDj123Position();
		} else if (toReference.equals(Organism.REFERENCE_KASALATH)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.getKasalathContigId();
				actualToContigEnd = coversionEnd.getKasalathContigId();
			}
			toStart = coversionStart.getKasalathPosition();
			toEnd = coversionEnd.getKasalathPosition();
		}

		/*
		 * if(!actualToContigStart.equals(actualToContigEnd )) { throw new
		 * RuntimeException("Aligned contig at start (" + coversionStart.getToContig() +
		 * ") is not the same as aligned contig at end (" + coversionEnd.getToContig() +
		 * " )" + " in " + coversionEnd.getToOrganism()); }
		 */

		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		String strToContig1 = scaffolddao.getScaffold(actualToContigStart).getName();
		String strToContig2 = scaffolddao.getScaffold(actualToContigEnd).getName();

		if (toContig != null) {
			if (toContig.equals(strToContig1) || toContig.equals(strToContig2)) {
			} else {
				throw new RuntimeException("Aligned contig " + strToContig1 + "," + strToContig2
						+ " did not matched with specified contig " + toContig + " in " + toReference);
			}
		}

		Long strand = 1L;
		if (toStart.compareTo(toEnd) < 0)
			strand = -1L;

		MultiReferenceLocus toLocus = new MultiReferenceLocusImpl(toReference, strToContig1, toStart.intValue(),
				toEnd.intValue(), strand.intValue());
		AppContext.debug("convert locus:" + fromLocus + " to " + toLocus);
		return toLocus;
	}

	@Override
	public VariantStringData convertReferencePositions(VariantStringData variantstringdataNPB,
			MultiReferenceLocus npbMultirefLocus, MultiReferenceLocus origMultiReferenceLocus, String toContig,
			boolean isOtherRefs) throws Exception {
		

		if (origMultiReferenceLocus != null)
			throw new RuntimeException("origMultiReferenceLocus!=null: " + origMultiReferenceLocus);
		// if(npbMultirefLocus!=null) throw new
		// RuntimeException("npbMultirefLocus!=null: " + npbMultirefLocus);
		if (toContig != null)
			throw new RuntimeException("toContig!=null: " + toContig);
		if (isOtherRefs)
			throw new RuntimeException("toContig!=null: " + toContig);

		orgdao = (OrganismDAO) AppContext.checkBean(orgdao, "OrganismDAO");
		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");

		Map<BigDecimal, String> mapOrgId2Name = new HashMap();
		Organism npborg = orgdao.getMapName2Organism().get(npbMultirefLocus.getOrganism());

		Iterator<Organism> itOrgs = orgdao.getMapName2Organism().values().iterator();
		while (itOrgs.hasNext()) {
			Organism org = itOrgs.next();
			mapOrgId2Name.put(org.getOrganismId(), org.getName());
		}

		orgdao = (OrganismDAO) AppContext.checkBean(orgdao, "OrganismDAO");
		Map mapOrgname2Org = orgdao.getMapName2Organism();
		BigDecimal orgid = null;

		Scaffold npbcontig = scaffolddao.getScaffold(npbMultirefLocus.getContig(),
				((Organism) mapOrgname2Org.get(npbMultirefLocus.getOrganism())).getOrganismId());

		// variantstringdataNPB.getListPos()

		Set setConv = findVConvertposNb2allrefsByFromOrgIdContigIdPosBetween(npborg.getOrganismId(),
				npbcontig.getFeatureId(), BigDecimal.valueOf(npbMultirefLocus.getFmin()),
				BigDecimal.valueOf(npbMultirefLocus.getFmax()));

		if (variantstringdataNPB.getListPos().size() != setConv.size())
			throw new RuntimeException("variantstringdataNPB.getListPos().size()!=setConv.size():"
					+ variantstringdataNPB.getListPos().size() + "," + setConv.size());
		Iterator<VConvertposNb2allrefs> itConv = setConv.iterator();

		Map<BigDecimal, String> mapContigId2Name = new HashMap();

		Map mapMSU7Pos2ConvertedPos_9311 = new HashMap();
		Map mapMSU7Pos2ConvertedPos_IR64 = new HashMap();
		Map mapMSU7Pos2ConvertedPos_DJ123 = new HashMap();
		Map mapMSU7Pos2ConvertedPos_Kas = new HashMap();

		while (itConv.hasNext()) {
			VConvertposNb2allrefs posconv = itConv.next();

			if (posconv.get_311ContigId() != null) {
				String cont_9311 = mapContigId2Name.get(posconv.get_311ContigId());
				if (cont_9311 == null) {

					if (scaffolddao.getScaffold(posconv.get_311ContigId()) == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.get_311ContigId())==null: "
								+ posconv.get_311ContigId());
					else {
						cont_9311 = scaffolddao.getScaffold(posconv.get_311ContigId()).getName();
						mapContigId2Name.put(posconv.get_311ContigId(), cont_9311);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_9311, cont_9311,
						posconv.get_311Position(), posconv.get_311Refcall());
				mapMSU7Pos2ConvertedPos_9311.put(posconv.getNbPosition(), conv);
			}

			if (posconv.getIr64ContigId() != null) {
				String cont_IR64 = mapContigId2Name.get(posconv.getIr64ContigId());
				if (cont_IR64 == null) {
					if (scaffolddao.getScaffold(posconv.getIr64ContigId()) == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.getIr64ContigId())==null: "
								+ posconv.getIr64ContigId());
					else {
						cont_IR64 = scaffolddao.getScaffold(posconv.getIr64ContigId()).getName();
						mapContigId2Name.put(posconv.getIr64ContigId(), cont_IR64);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_IR64, cont_IR64,
						posconv.getIr64Position(), posconv.getIr64Refcall());
				mapMSU7Pos2ConvertedPos_IR64.put(posconv.getNbPosition(), conv);
			}

			if (posconv.getDj123ContigId() != null) {
				String cont_DJ123 = mapContigId2Name.get(posconv.getDj123ContigId());
				if (cont_DJ123 == null) {
					if (scaffolddao.getScaffold(posconv.getDj123ContigId()) == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.getDj123ContigId())==null: "
								+ posconv.getDj123ContigId());
					else {
						cont_DJ123 = scaffolddao.getScaffold(posconv.getDj123ContigId()).getName();
						mapContigId2Name.put(posconv.getDj123ContigId(), cont_DJ123);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_DJ123, cont_DJ123,
						posconv.getDj123Position(), posconv.getDj123Refcall());
				mapMSU7Pos2ConvertedPos_DJ123.put(posconv.getNbPosition(), conv);
			}

			if (posconv.getKasalathContigId() != null) {
				String cont_Kasalath = mapContigId2Name.get(posconv.getKasalathContigId());
				if (cont_Kasalath == null) {
					if (scaffolddao.getScaffold(posconv.getKasalathContigId()) == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.getKasalathContigId())==null: "
								+ posconv.getKasalathContigId());
					else {
						cont_Kasalath = scaffolddao.getScaffold(posconv.getKasalathContigId()).getName();
						mapContigId2Name.put(posconv.getKasalathContigId(), cont_Kasalath);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_KASALATH, cont_Kasalath,
						posconv.getKasalathPosition(), posconv.getKasalathRefcall());
				mapMSU7Pos2ConvertedPos_Kas.put(posconv.getNbPosition(), conv);
			}

		}
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_9311, mapMSU7Pos2ConvertedPos_9311,
				npbcontig.getName());
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_IR64, mapMSU7Pos2ConvertedPos_IR64,
				npbcontig.getName());
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_DJ123, mapMSU7Pos2ConvertedPos_DJ123,
				npbcontig.getName());
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_KASALATH, mapMSU7Pos2ConvertedPos_Kas,
				npbcontig.getName());

		return variantstringdataNPB;
	}

}
