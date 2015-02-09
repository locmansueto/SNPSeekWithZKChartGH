package org.irri.iric.portal.genotype.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.biojava3.phylo.TreeConstructor;
import org.forester.evoinference.matrix.distance.BasicSymmetricalDistanceMatrix;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VSnp2vars;
import org.irri.iric.portal.dao.GeneDAO;
import org.irri.iric.portal.dao.IndelStringDAO;
import org.irri.iric.portal.dao.IndelStringDAOImpl;
import org.irri.iric.portal.dao.IndelsAllvarsDAO;
import org.irri.iric.portal.dao.IndelsAllvarsPosDAO;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.Snps2VarsCountMismatchDAO;
import org.irri.iric.portal.dao.Snps2VarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsAllvarsRefMismatchDAO;
import org.irri.iric.portal.dao.SnpsHeteroAllvarsDAO;
import org.irri.iric.portal.dao.SnpsInExonDAO;
import org.irri.iric.portal.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.portal.dao.SnpsStringAllvarsDAO;
import org.irri.iric.portal.dao.SnpsStringDAO;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.Snp;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.domain.Snps2VarsCountmismatchImpl;
import org.irri.iric.portal.domain.Snps2VarsImpl;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsHeteroAllele2;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
//import org.irri.iric.portal.flatfile.domain.SnpcoreRefposindex;
import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;
import org.irri.iric.portal.genotype.service.GenotypeFacade.snpQueryMode;
//import org.irri.iric.portal.domain.VarietyService;
//import org.irri.iric.portal.genotype.domain.Variety3k;
//import org.irri.iric.portal.genotype.views.*;
//import org.irri.iric.portal.service.GeneService;
//import org.irri.iric.portal.service.Snps2VarsService;
//import org.irri.iric.portal.service.VarietyService;
//import org.irri.iric.portal.utils.zkui.HibernateSearchObject;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.irri.iric.portal.domain.VariantTable;
//import org.irri.iric.portal.variety.views.IViewDist3kHome;
//import org.irri.iric.portal.variety.views.ViewDist3kId;
//import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import org.forester.archaeopteryx.Archaeopteryx;
import org.forester.io.parsers.util.ParserUtils;
import org.forester.io.parsers.PhylogenyParser;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyMethods;
import org.forester.phylogeny.PhylogenyNode;
import org.forester.phylogeny.iterators.PhylogenyNodeIterator;

//@Secured("ROLE_IRRIUSER")
//@Service("GenotypeFacadeChado")
@Service("CopyOfGenotypeFacade")
//@Scope("prototype")
@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
public class CopyOfGenotypeFacadeChadoImpl implements GenotypeFacade {

	@Override
	public List<VariantStringData> queryGenotype(List listParams)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void downloadGenotypeGenome(GenotypeQueryParams params)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCore(boolean isCore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limitVarieties(Set varieties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getVarnames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSubpopulations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getGenenames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getChromosomes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getFeatureLength(String feature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getVarietiesForSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gene getGeneFromName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Snps2Vars> getSNPinVarieties(String var1, String var2,
			Integer startPos, Integer endPos, String chromosome,
			snpQueryMode querymode, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Snps2Vars> getSNPinVarieties(String var1, String var2,
			String genename, Integer plusminusBp, snpQueryMode querymode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SnpsAllvarsPos> getSnpsposlist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(
			int firstRow, int numRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, BigDecimal> getMapOrder2Variety() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<BigDecimal, Integer> getMapVariety2Order() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<BigDecimal, Integer> getMapVariety2Mismatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] constructPhylotree(String scale, String chr, int start,
			int end, String requestid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start,
			Integer end, Integer chr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSNPStringInAllVarieties(Set snpposlist, Integer chr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSNPinVarieties(String var1, String var2, Set snpposlist,
			String chr, snpQueryMode mode, boolean checked) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColorByNonsyn(boolean selected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNonsynOnly(boolean selected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMismatchOnly(boolean isMismatchOnly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIncludeSNP(boolean includeSNP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIncludeIndel(boolean includeIndel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<BigDecimal, IndelsAllvarsPos> getMapIndelId2Indel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, BigDecimal> getMapIndelIdx2Pos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Integer> getMapMergedIdx2SnpIdx() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, BigDecimal> getMapMergedIdx2Pos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<BigDecimal, Set<String>> getMapPos2Allele() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIndelAlleleString(IndelsAllvarsPos indelpos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIndelType(String allele) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantStringData queryGenotype(GenotypeQueryParams params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantTable fillGenotypeTable(VariantTable table,
			VariantStringData data, GenotypeQueryParams params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantStringData compare2Varieties(BigDecimal var1,
			BigDecimal var2, GenotypeQueryParams params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] constructPhylotree(PhylotreeQueryParams params,
			String requestid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
//	@Override
//	public void setCore(boolean isCore) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void limitVarieties(Set varieties) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<String> getVarnames() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<String> getSubpopulations() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<String> getGenenames() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<String> getChromosomes() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer getFeatureLength(String feature) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Set getVarietiesForSubpopulation(String subpopulation) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Gene getGeneFromName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Snps2Vars> getSNPinVarieties(String var1, String var2,
//			Integer startPos, Integer endPos, String chromosome,
//			snpQueryMode querymode, boolean b) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Snps2Vars> getSNPinVarieties(String var1, String var2,
//			String genename, Integer plusminusBp, snpQueryMode querymode) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<SnpsAllvarsPos> getSnpsposlist() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(
//			int firstRow, int numRows) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<Integer, BigDecimal> getMapOrder2Variety() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<BigDecimal, Integer> getMapVariety2Order() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<BigDecimal, Integer> getMapVariety2Mismatch() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String[] constructPhylotree(String scale, String chr, int start,
//			int end, String requestid) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String[] constructPhylotreeTopN(String scale, String chr, int start,
//			int end, int topN, String requestid) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object[] constructPhylotreeMindist(String scale, String chr,
//			int start, int end, String mindist) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<BigDecimal, Integer> getMapVariety2PhyloOrder() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start,
//			Integer end, Integer chr) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List getSNPStringInAllVarieties(Set snpposlist, Integer chr) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List getSNPinVarieties(String var1, String var2, Set snpposlist,
//			String chr, snpQueryMode mode, boolean checked) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setColorByNonsyn(boolean selected) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setNonsynOnly(boolean selected) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setMismatchOnly(boolean isMismatchOnly) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setIncludeSNP(boolean includeSNP) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setIncludeIndel(boolean includeIndel) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Map<BigDecimal, IndelsAllvarsPos> getMapIndelId2Indel() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<Integer, BigDecimal> getMapIndelIdx2Pos() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<Integer, Integer> getMapMergedIdx2SnpIdx() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<Integer, BigDecimal> getMapMergedIdx2Pos() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<BigDecimal, Set<String>> getMapPos2Allele() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getIndelAlleleString(IndelsAllvarsPos indelpos) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getIndelType(String allele) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public VariantStringData queryGenotype(GenotypeQueryParams params)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public VariantTable fillGenotypeTable(VariantTable table,
//			VariantStringData data, GenotypeQueryParams params)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public VariantStringData compare2Varieties(BigDecimal var1,
//			BigDecimal var2, GenotypeQueryParams params) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//	
//	
////
////	private static final Log log = LogFactory.getLog(CopyOfGenotypeFacadeChadoImpl.class);
////
////	// The scope of this class is Session, it keeps a set of state variables to keep
////	// results from previous Request within the Session
////	
////	// private state variables
////	
////	private List listSNPAllVarsMismatches;
////	private Map<Integer,BigDecimal> mapOrder2Variety; // 0-indexed
////	private Map<BigDecimal, Integer> mapVariety2Order;  // 0-indexed
////	private Map<BigDecimal, Integer> mapVariety2Mismatch;
////	private Map<BigDecimal, Integer> mapVariety2PhyloOrder;
////	//private Map<Integer,Set<Character>>	 mapIndex2NonsynAlleles;
////	
////	private Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel;
////	private Map<Integer, BigDecimal> mapIndelIdx2Pos;
////	private Map<Integer, Integer> mapMergedIdx2SnpIdx;
//////	private Map<Integer, BigDecimal> mapSNPIdx2Pos;
////	private Map<Integer, BigDecimal> mapMergedIdx2Pos;
////	
////	private Map<BigDecimal, Set<String>> mapPos2Allele;
////	
////	
////	private List<SnpsAllvarsPos> snpsposlist;
////
////	private Set<BigDecimal> limitVarIds;
////	private boolean isCore=false;
////	private boolean isNonsynOnly=false;
////	private boolean isColorByNonsyn=false;
////	//private Map<Integer,Set<Character>>  mapIdx2NonsynAlleles;
////	//private Map<Integer,boolean[]>  mapIdx2Nonsynflags;
////	//private char[][] heteroAlleleMatrix;
////	private boolean isMismatchOnly;
////	private boolean includeSNP=true;
////	private boolean includeIndel=false;
////	
////	
////	@Autowired
////	private ApplicationContext appContext;
////
////	@Autowired
////	@Qualifier("VarietyFacade")
////	private VarietyFacade varietyfacade;
////
////	//@Autowired
////	//private GeneDAO geneservice; 
////	
////	@Autowired
////	private ListItemsDAO listitemsDAO;
////	
////	@Autowired
////	@Qualifier("Snps2VarsDAO")
////	private  Snps2VarsDAO snp2linesService; 
////	
////	@Autowired 
////	//@Qualifier("Snps2VarsCountMismatchDAO")
////	@Qualifier("SnpsString2VarsCountMismatchDAO")
////	private  Snps2VarsCountMismatchDAO snpcount2linesService; 
////	
////	@Autowired
////	@Qualifier("VSnpRefposindexDAO")		// snps reference, allele position file index
////	private SnpsAllvarsPosDAO snpstringallvarsposService;
////
////	@Autowired
////	private SnpsHeteroAllvarsDAO snpsheteroDAO;
////	
////	@Autowired
////	private SnpsNonsynAllvarsDAO snpsnonsynDAO;
////	
////	@Autowired
////	private SnpsInExonDAO snpsinexonDAO;
////	
////	@Autowired
////	@Qualifier("H5SNPUniAllele1DAO")
////	private SnpsStringDAO snpstrAllsnpsAllele1AllvarsDAO;
////
////	@Autowired
////	@Qualifier("H5SNPUniAllele2DAO")
////	private SnpsStringDAO snpstrAllsnpsAllele2AllvarsDAO;
////
////	
////	@Autowired
////	@Qualifier("H5SNPCoreAllele1DAO")
////	private SnpsStringDAO snpstrCoresnpsAllele1AllvarsDAO;
////	
////	@Autowired
////	@Qualifier("H5SNPCoreAllele2DAO")
////	private SnpsStringDAO snpstrCoresnpsAllele2AllvarsDAO;
////	
////	/*
////	@Autowired
////	@Qualifier("IndelStringAllvarsDAO")
////	private SnpsStringDAO indelstrAllsnpsAlleleAllvarsDAO;
////	
////	
////	@Autowired
////	@Qualifier("IndelAlleleDAO")
////	private SnpsStringDAO indelstrAllsnpsAllele2AllvarsDAO;
////	*/
////	
////	@Autowired
////	@Qualifier("IndelsAllvarsPosDAO")
////	private IndelsAllvarsPosDAO indelsAllvarsPosDAO;
////	
////	@Autowired
////	//@Qualifier("IndelCallDAO")
////	@Qualifier("IndelAllvarsDAO")
////	private IndelsAllvarsDAO indelsAllvarsDAO;
////
////	
////	// using services
////	
////	@Autowired
////	private VarietiesGenotypeService genotypeservice;
////	
////	
////	
////	// ******************* backup DAO references ******************
////	/*
////	@Autowired
////	private GeneDAO geneservice; // = new org.irri.iric.portal.genotype.service.GeneServiceImpl();
////	//@Autowired 
////	//@Qualifier("VarietyDAO")
////	//private VarietyDAO varservice;
////	
////	@Autowired
////	private ListItemsDAO listitemsDAO;
////	
////	@Autowired
////	@Qualifier("Snps2VarsDAO")
////	private  Snps2VarsDAO snp2linesService; // = new Snp2linesHome();
////	
////	//private  org.irri.iric.portal.genotype.views.ISnp2linesHome snp2linesService; // = new Snp2linesHome();
////	
//////	@Autowired
//////				//@Qualifier("SnpsAllvarsDAO")
//////	@Qualifier("VSnpAllvarsMinDAO")  // no snp_genotype_id
//////	private  SnpsAllvarsDAO snpallvarsService; // = new Snp2linesHome();
////	
////	@Autowired
////	@Qualifier("SnpsAllvarsPosDAO")
////	private  SnpsAllvarsPosDAO snpallvarsposService; // = new Snp2linesHome();
////
//////	@Autowired
//////	@Qualifier("MvCoreSnpsDAO")		// using core snps
//////	private SnpsAllvarsPosDAO snpcoreallvarsposService;
////
////
////	@Autowired
////	@Qualifier("SnpsAllvarsRefMismatchDAO")
////	private  SnpsAllvarsRefMismatchDAO snpcountallvarsService;
////
////	@Autowired 
////	//@Qualifier("Snps2VarsCountMismatchDAO")
////	@Qualifier("SnpsString2VarsCountMismatchDAO")
////	private  Snps2VarsCountMismatchDAO snpcount2linesService; // = new Snp2linesHome();
////	
////	
////	@Autowired
////	@Qualifier("SnpcoreMismatchAllelesDAO")	
////	private SnpsStringAllvarsDAO snpstringAllvarsDao; // using core snps, allelestring in database
////	
//////	@Autowired
//////	@Qualifier("SnpstringAllelesFileDAO")	// using core snps, allelestring in flatfile
//////	private SnpsStringAllvarsDAO snpstringallelesfileDao;
////
////
////	@Autowired
////	@Qualifier("VSnpRefposindexDAO")		// using core snps, allelestring in database
////	private SnpsAllvarsPosDAO snpstringallvarsposService;
////
////	@Autowired
////	@Qualifier("MismatchCountDAO")
////	private SnpsAllvarsRefMismatchDAO refmismatchDAO;
////
////	@Autowired
////	SnpsHeteroAllvarsDAO snpsheteroDAO;
////	
////	@Autowired
////	SnpsNonsynAllvarsDAO snpsnonsynDAO;
////	
////	@Autowired
////	@Qualifier("VarietyFacade")
////	private VarietyFacade varietyfacade;
////	
////	*/
////	
////	
////	public CopyOfGenotypeFacadeChadoImpl() {
////		super();
////		// TODO Auto-generated constructor stub
////		
////		AppContext.debug("created GenotypeFacadeChadoImpl: " + this );
////
////	}
////
////	
////
////// ********************************************** Set State ************************************************************
////
////	@Override
////	public void limitVarieties(Set varieties) {
////		if(varieties!=null)
////		{
////			Set setVarid=new HashSet();
////			//Iterator<Variety> itVar=varieties.iterator();
////			Iterator itVar=varieties.iterator();
////			while(itVar.hasNext())
////			{
////				Object objvar = itVar.next();
////				if(objvar==null) continue;
////				
////				if(objvar instanceof Variety)
////					setVarid.add( ((Variety)objvar).getVarietyId());
////				else 
////					setVarid.add( objvar );
////			}
////			limitVarIds=setVarid;
////		}
////		else limitVarIds=null;
////	}
////	
////	@Override
////	public void setCore(boolean isCore) {
////		this.isCore = isCore;
////	}
////
////	@Override
////	public void setColorByNonsyn(boolean selected) {
////		// TODO Auto-generated method stub
////		this.isColorByNonsyn = selected;
////		
////	}
////
////
////	@Override
////	public void setNonsynOnly(boolean selected) {
////		// TODO Auto-generated method stub
////		this.isNonsynOnly = selected;
////		
////	}
////	
////	@Override
////	public void setMismatchOnly(boolean isMismatchOnly) {
////		this.isMismatchOnly = isMismatchOnly;
////	}
////	
////
////	@Override
////	public void setIncludeSNP(boolean includeSNP) {
////		this.includeSNP = includeSNP;
////	}
////
////
////	@Override
////	public void setIncludeIndel(boolean includeIndel) {
////		this.includeIndel = includeIndel;
////	}
////	
////	
////
////// ************************************************ Get State ***********************************************************
////
////
//////	@Override
//////	public Map<Integer, Set<Character>> getMapIdx2NonsynAlleles() {
//////		return mapIdx2NonsynAlleles;
//////	}
////
////
////
////
////	@Override
////	public Map<Integer, BigDecimal> getMapOrder2Variety() {
////		// TODO Auto-generated method stub
////		return this.mapOrder2Variety;
////	}
////
////
////	@Override
////	public Map<BigDecimal, Integer> getMapVariety2Order() {
////		// TODO Auto-generated method stub
////		return this.mapVariety2Order;
////	}
////
////
////	@Override
////	public Map<BigDecimal, Integer> getMapVariety2Mismatch() {
////		// TODO Auto-generated method stub
////		return this.mapVariety2Mismatch;
////	}
////
////	@Override
////	public java.util.Map<BigDecimal, Integer> getMapVariety2PhyloOrder() {
////		return mapVariety2PhyloOrder;
////	}
////
////	@Override
////	public List<SnpsAllvarsPos> getSnpsposlist() {
////		
////
////			return snpsposlist;
////	}
////	
////
////	
////	
////	@Override
////	public Map<BigDecimal, IndelsAllvarsPos> getMapIndelId2Indel() {
////		return mapIndelId2Indel;
////	}
////
////	
////	@Override
////	public Map<Integer, BigDecimal> getMapIndelIdx2Pos() {
////		return mapIndelIdx2Pos;
////	}
////
////	@Override
////	public Map<Integer, Integer> getMapMergedIdx2SnpIdx() {
////		return mapMergedIdx2SnpIdx;
////	}
////
////
////	@Override
////	public Map<Integer, BigDecimal> getMapMergedIdx2Pos() {
////		return mapMergedIdx2Pos;
////	}
////	
////
////	@Override
////	public Map<BigDecimal, Set<String>> getMapPos2Allele() {
////		return mapPos2Allele;
////	}
////
////	
////	
////// ************************************* Methods for UI Listboxes *******************************************************
////	
////
////
////
////
////	@Override
////	public Set getVarietiesForSubpopulation(String subpopulation) {
////		// TODO Auto-generated method stub
////		return varietyfacade.getGermplasmBySubpopulation(subpopulation);
////	}
////	
////	
////	/**
////	 * Get Gene object from name
////	 */
////	@Override
////	public Gene getGeneFromName(String name) {
////		// TODO Auto-generated method stub
////		return listitemsDAO.getGeneFromName( name);
////	}
////
////	/**
////	 * Get varietry names 
////	 */
////	
////	@Override
////	public List<String> getVarnames() {
////		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO");
////		return listitemsDAO.getVarietyNames();
////		
////	}
////	
////	@Override
////	public List<String>getSubpopulations() {
////		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO");
////		return listitemsDAO.getSubpopulations();
////	}
////	 
////	/**
////	 * Get all gene names
////	 */
////	@Override
////	public List<String> getGenenames() {
////		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO");
////		return listitemsDAO.getGenenames();
////	}
////
////	/**
////	 * Get chromosome names, mocked! replace with DB read later
////	 */
////
////	@Override
////	public java.util.List<String> getChromosomes() {
////		java.util.List<String> chr = new java.util.ArrayList<String>();
////		for(int i=1; i<13; i++)  chr.add( Integer.toString(i));
////		return chr;
////	}
////	
////	/**
////	 * Get length of features (ex. chromosome) from name, mocked! replace with DB read later
////	 */
////
////	@Override
////	public Integer getFeatureLength(String feature) {
////		return this.listitemsDAO.getFeatureLength(feature);
////	}
////
////
//////	@Override
//////	public Integer getFeatureLength(String feature) {
//////		// TODO Auto-generated method stub
//////		
//////		java.util.Map<String,Integer> chrLength = new java.util.HashMap<String,Integer>();		
//////		chrLength.put("1", 43270923);
//////		chrLength.put("2", 35937250);
//////		chrLength.put("3",36413819);
//////		chrLength.put("4",35502694);
//////		chrLength.put("5",29958434);
//////		chrLength.put("6",31248787);
//////		chrLength.put("7",29697621);
//////		chrLength.put("8",28443022);
//////		chrLength.put("9",23012720);
//////		chrLength.put("10",23207287);
//////		chrLength.put("11",29021106);
//////		chrLength.put("12",27531856);
//////		
//////		return chrLength.get(feature);
//////	}
////
////	
////// **************************************   Methods to query SNPs for all varieties *********************************************************************************
//////
////// these methods use the SNP_GENOTYPE oracle table. To improve speed, these methods have been replaced with SNPString that queries the flatfiles
////// or HDF5. These code are kept for future reference for cases to raed the SNP_GENOTYPE table.   	
//////	
////	
/////*
////	@Override
////	public List<SnpsAllvarsRefMismatch> countSNPMismatchesInAlllVarieties(Integer startPos, Integer endPos, String chromosome, boolean update)
////	{
////		
////		if(limitVarIds!=null && !limitVarIds.isEmpty()){
////				return countSNPMismatchesInSetVarieties(limitVarIds,  startPos,  endPos,  chromosome,  update);
////		}
////		
////		if(update || listSNPAllVarsMismatches==null) {
////			snpcountallvarsService = (SnpsAllvarsRefMismatchDAO)AppContext.checkBean(snpcountallvarsService, "SnpsAllvarsRefMismatchDAO") ; 
////			listSNPAllVarsMismatches = snpcountallvarsService.countMismatches( chromosome,  startPos,  endPos, isCore);
////		}
////		return listSNPAllVarsMismatches;
////	}
////
////	private List<SnpsAllvarsRefMismatch> countSNPMismatchesInSetVarieties(Set setvarIds, Integer startPos, Integer endPos, String chromosome, boolean update)
////	{
////		if(update || listSNPAllVarsMismatches==null) {
////			snpcountallvarsService = (SnpsAllvarsRefMismatchDAO)AppContext.checkBean(snpcountallvarsService, "SnpsAllvarsRefMismatchDAO") ; 
////			
////			if(setvarIds==null || setvarIds.isEmpty())
////				listSNPAllVarsMismatches = snpcountallvarsService.countMismatches( chromosome,  startPos,  endPos, isCore);
////			else {
////				AppContext.debug("countMismatchesInvars  setvarIds:" + setvarIds);
////				listSNPAllVarsMismatches = snpcountallvarsService.countMismatchesInvars(setvarIds, chromosome,  startPos,  endPos, isCore);
////			}
////		}
////		return listSNPAllVarsMismatches;
////	}
////	
////	@Override
////	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome)
////	{
////		 return getSNPinAllVarieties(startPos, endPos, chromosome, 1,0); 
////	}
////	
////
////	@Override
////	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) {
////		return getSNPinAllVarieties(startPos, endPos, chromosome, firstRow, numRows,AppContext.isSNPAllvarsFetchMismatchOnly()); 
////	}
////	
////	@Override
////	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean fetchMismatchOnly) {
////		 return getSNPinSetVarieties(null,  startPos,  endPos,  chromosome,  firstRow,  numRows,  fetchMismatchOnly); 
////	}
////
////	
////	@Override
////	public List<SnpsAllvars> getSNPinAllVarieties(String genename, Integer plusminusBp) {
////		// TODO Auto-generated method stub
////		
////		Gene gene = getGeneFromName( genename );
////		log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
////		
////		return getSNPinAllVarieties(gene.getFmin() , gene.getFmax(), gene.getChr().toString());
////	}
////	
////
////	@Override
////	public List<SnpsAllvars> getSNPinSetVarieties(Set setvarIds, Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean fetchMismatchOnly) {
////		// TODO Auto-generated method stub
////
////		if(startPos<0)  startPos=0;
////	
////		
////		if(setvarIds==null || setvarIds.isEmpty()) {
////			if(limitVarIds!=null && !limitVarIds.isEmpty())
////				setvarIds=limitVarIds;
////		}
////		
////		// if first row=1, reset variety-mismatch sort order,  
////		if(firstRow==1 ) {
////			listSNPAllVarsMismatches=countSNPMismatchesInSetVarieties(setvarIds, startPos,  endPos,  chromosome, true);
////			
////			mapOrder2Variety = new java.util.HashMap<Integer,BigDecimal>();
////			mapVariety2Order = new java.util.HashMap<BigDecimal,Integer>();
////			mapVariety2Mismatch = new java.util.HashMap<BigDecimal,Integer>();		
////			
////		}
////
////		// if no SNP result, return empty
////		if(listSNPAllVarsMismatches.size()==0) {
////			snpsposlist = new java.util.ArrayList();
////			return new java.util.ArrayList();
////		}
////		
////		StringBuffer topVarieties = new StringBuffer();
////		
////		long lastRow = firstRow + numRows - 1;  // 1-indexed
////		if(listSNPAllVarsMismatches.size() >  lastRow)
////		{
////		} else
////			lastRow = listSNPAllVarsMismatches.size();
////		
////		if(numRows==0)  lastRow = listSNPAllVarsMismatches.size();
////		
////
////		Set<BigDecimal> varsMismatch = new HashSet();
////		
////		// generate variety listing order
////		for(long iVariety= firstRow-1;  iVariety<lastRow;  iVariety++ ) {
////			
////			SnpsAllvarsRefMismatch varmismatch = (SnpsAllvarsRefMismatch)listSNPAllVarsMismatches.get((int)iVariety);
////			
////			mapOrder2Variety.put( Integer.valueOf((int)iVariety),  varmismatch.getVar()  );
////			mapVariety2Order.put( varmismatch.getVar(), Integer.valueOf((int)iVariety)   );
////			mapVariety2Mismatch.put( varmismatch.getVar(), varmismatch.getMismatch().intValue()  );
////			varsMismatch.add(  varmismatch.getVar() );
////		}
////		
////		if(numRows==0)  varsMismatch=null;
////		
////		
////		 if(isCore) {
////				snpcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService, "MvCoreSnpsDAO") ; 
////				snpsposlist = snpcoreallvarsposService.getSNPs(chromosome, startPos, endPos, null );
////			 
////		 }
////		 else {
////			snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
////			snpsposlist = snpallvarsposService.getSNPs(chromosome, startPos, endPos , null);
////		 }
////			if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
////
////			snpallvarsService = (SnpsAllvarsDAO)AppContext.checkBean(snpallvarsService, "VSnpAllvarsMinDAO") ; 
////			
////			Set<SnpsAllvars> snpslist; 
////			
////			if(fetchMismatchOnly)
////				//snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetweenRefmismatch( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch, isCore); 
////				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetweenRefmismatch( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), firstRow, lastRow+1, isCore);
////			else
////				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetween( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch, isCore); 
////			
////
////			log.debug( "snpslist Results: " + snpslist.size() ); 
////			
////			List<SnpsAllvars> list = new java.util.ArrayList<SnpsAllvars>();
////			list.addAll(snpslist);
////			return list;
////	
////
////	}
////	*/
////
////	
////// ************************************************************ SNP Query between two varieties **********************************************************************************	
////	
////	/**
////	 * Get the SNP alleles for varieties
////	 * var1	variety 1
////	 * var2	variety 2
////	 * startPos	start position
////	 * endPos	end position
////	 * chromosome
////	 * querymode	what alleles to query, allowed values
////	 * 				enum snpQueryMode { SNPQUERY_VARIETIES		alleles between the two varieties
////	 * 								,  SNPQUERY_REFERENCE	allele with the reference
////	 * 								//, SNPQUERY_ALLREFPOS 	all snp positions in the reference
////	 * 	
////	 */
////	@Override
////	public List<Snps2Vars> getSNPinVarieties(String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean isCore) {
////		return getSNPinVarieties(var1, var2, startPos, endPos, chromosome,  querymode,  isCore, null) ;
////	}
////	
////	
////	private List<Snps2Vars> getSNPinVarietiesOrig2(String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean isCore, Set snpposlimit ) {
////		
////		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
////		Map<String,Variety> mapVarname2Variety = varietyfacade.getMapVarname2Variety();
////		limitVarIds = new LinkedHashSet();
////		BigDecimal varid1= mapVarname2Variety.get(var1.toUpperCase()).getVarietyId() ; 
////		BigDecimal varid2= mapVarname2Variety.get(var2.toUpperCase()).getVarietyId() ;
////		limitVarIds.add( varid1 );
////		limitVarIds.add( varid2 );
////		
////		Integer chr = Integer.valueOf(chromosome.toLowerCase().replace("chr","").trim() );
////		
////		
////		SNPsStringData snpstringdata = null;
////		if(startPos==null || endPos==null)
////			snpstringdata = getSNPsStringData( chr ,null, null, snpposlimit );
////		else
////			snpstringdata = getSNPsStringData( chr , new BigDecimal(startPos), new BigDecimal(endPos), null );
////		
////		String var1all1str = (String)snpstringdata.getMapVarid2Snpsstr().get(varid1);
////		String var2all1str = (String)snpstringdata.getMapVarid2Snpsstr().get(varid2);
////		Map<Integer,Character> mapPos1idx2Allele2 = (Map)snpstringdata.getMapVarid2SnpsAllele2str().get(var1);
////		Map<Integer,Character> mapPos2idx2Allele2 = (Map)snpstringdata.getMapVarid2SnpsAllele2str().get(var2);
////		//Map<Integer,Set<Character>> mapIdx2NonsynAlleles  = (Map)snpstringdata.getMapIdx2NonsynAlleles();
////		Map<Integer,Set<Character>> mapIndex2NonsynAlleles  = (Map<Integer,Set<Character>>)snpstringdata.getMapIdx2NonsynAlleles();
////		Set setSnpInExonTableIdx = snpstringdata.getSetSnpInExonTableIdx();
////		
////		String refstr = snpstringdata.getStrRef();
////		
////		Set setNonsynIdx = new HashSet();
////		
////		//AppContext.debug("var1all1str=" + var1all1str);
////		//AppContext.debug("var2all1str=" + var2all1str);
////		
////		List snplist = new ArrayList();
////		for(int iStr=0; iStr<refstr.length(); iStr++) {
////			
////			SnpsAllvarsPos snp = getSnpsposlist().get(iStr);
////
////			boolean inExon = false; 
////			if(setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr)) inExon=true;
////			
////			Boolean isNonSyn[] = new Boolean[2];
////			isNonSyn[0]=true;	
////			isNonSyn[1]=true;
////			
////			Character var1allele2 = null;
////			if(mapPos1idx2Allele2!=null) var1allele2 =  mapPos1idx2Allele2.get(iStr);
////			Character var2allele2 = null;
////			if(mapPos2idx2Allele2!=null) var2allele2 = mapPos2idx2Allele2.get(iStr);
////			Set setNonsyns = null;
////			if(mapIndex2NonsynAlleles!=null) setNonsyns = mapIndex2NonsynAlleles.get(iStr);
////
////			
////			double misCount = countVarpairMismatchNuc(var1all1str.charAt(iStr), var2all1str.charAt(iStr), false,
////					var1allele2,var2allele2,
////					setNonsyns , inExon, isNonSyn, isNonsynOnly);
////			
////			if(isNonSyn[0] || isNonSyn[1]) setNonsynIdx.add(iStr);
////			
////			
////			if(!isMismatchOnly || misCount>0 )
////				//Snps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
////				//		BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
////				//		String var1nuc, String var2nuc, String var1nuc2, String var2nuc2)
////				
////				/* nps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
////						BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
////						String var1nuc, String var2nuc, String var1nuc2, String var2nuc2,
////						Boolean var1Nonsyn, Boolean var2Nonsyn) { */
////
////				snplist.add(   new Snps2VarsImpl(AppContext.convertRegion2Snpfeatureid(chr,snp.getPos()), varid1, varid2,
////					chr, snp.getPos(), snp.getRefnuc(), 
////					var1all1str.charAt(iStr) , var2all1str.charAt(iStr), 
////					var1allele2 , var2allele2 , isNonSyn[0], isNonSyn[1] ) );
////		}
////		
////		return snplist;
////		//snpstringdata
////		//snpstringdata
////		//this.countVarpairMismatch(var1, var2, var1isref, var1allele2str, var2allele2str, mapIdx2NonsynAlleles, setSnpInExonTableIdx, setNonsynIdx, isCore)
////	}
////	
////	private List<Snps2Vars> getSNPinVarietiesOrig(String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean isCore, Set snpposlist ) {
////		
////		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
////		Map<String,Variety> mapVarname2Variety = varietyfacade.getMapVarname2Variety();
////		
////		try {
////
////			Set<Snps2Vars> snpslist=null;
////			
////			AppContext.debug("finding vars " +  var1 + "      " + var2 );
////			
////			BigDecimal var1Id = mapVarname2Variety.get(var1.toUpperCase()).getVarietyId();
////			BigDecimal var2Id = mapVarname2Variety.get(var2.toUpperCase()).getVarietyId();
////
////			
////			if(snpposlist!=null && !snpposlist.isEmpty()) {
////				if(querymode==snpQueryMode.SNPQUERY_VARIETIES) {
////					snpslist = snp2linesService.findVSnp2varsByVarsChrPosInMismatch(Integer.valueOf(chromosome), snpposlist, var1Id, var2Id, isCore);
////				}
////				else if(querymode== snpQueryMode.SNPQUERY_ALLREFPOS) { //SNPQUERY_REFERENCE) {
////					snpslist = snp2linesService.findVSnp2varsByVarsChrPosInAll(Integer.valueOf(chromosome), snpposlist, var1Id, var2Id, isCore);
////				}
////			} else {
////				if(querymode==snpQueryMode.SNPQUERY_VARIETIES) {
////					snpslist = snp2linesService.findVSnp2varsByVarsChrPosBetweenMismatch(Integer.valueOf(chromosome), BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), var1Id, var2Id, isCore);
////				}
////				else if(querymode== snpQueryMode.SNPQUERY_ALLREFPOS) { //SNPQUERY_REFERENCE) {
////					snpslist = snp2linesService.findVSnp2varsByVarsChrPosBetweenAll(Integer.valueOf(chromosome), BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), var1Id, var2Id, isCore);
////				}
////			}
////
////			AppContext.debug("Results: " + snpslist.size() ); 
////			
////			List list = new ArrayList();
////			list.addAll(snpslist);
////			return list;
////		}
////		catch (RuntimeException re) {
////			AppContext.error("getSNPinVarieties failed:"+ re.getMessage());
////			throw re;
////		}
////
////	}
////	
////	/**
////	 * Get snps within a gene. Get the chromosome and location of the gene, then call getSNPinVarieties(String var1, String var2,
////	 *		Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode) 
////	 *var1	variety 1
////	 *var2	variety 2
////	 *genename	
////	 *plusminusbp	number of basepairs before and after the gene to include
////	 *querymode	 what alleles to query, enum snpQueryMode 
////	 *
////	 */
////	
////	@Override
////	public List<Snps2Vars> getSNPinVarieties(String var1, String var2, String genename, Integer plusminusBp, snpQueryMode querymode) {
////		// TODO Auto-generated method stub
////		Gene gene = getGeneFromName( genename );
////		log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
////		return getSNPinVarieties(var1, var2,  gene.getFmin()  , gene.getFmax(), 
////				gene.getChr().toString(), querymode, AppContext.isCore() , null);
////	}
////
////	@Override
////	public List getSNPinVarieties(String var1, String var2, Set snpposlist, String chr, snpQueryMode mode, boolean isCore) {
////		// TODO Auto-generated method stub
////		return getSNPinVarieties(var1, var2, null,null, chr, mode, isCore , snpposlist);
////	}
////	
////	
////	private String getNuc(int i) {
////		switch (i) {
////			case 0: return "A"; 
////			case 1: return "T"; 
////			case 2: return "G";
////			case 3: return "C";
////		}
////		return "N";
////	}
////
////	
////// ************************************* Methods for Phylogenetic tree construction ********************************************************************************	
////
////	@Override
////	public String[] constructPhylotree(String scale, String chr, int start, int end, String requestid) {
////		return constructPhylotreeTopN(scale, chr, start, end, -1, requestid);
////	}	
////
////
////	@Override
////	public String[] constructPhylotreeTopN(String scale, String chr, int start, int end, int topN,  String requestid) {
////		
////		//snpcount2linesService = (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService, "Snps2VarsCountMismatchDAO");
////		snpcount2linesService = (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService, "SnpsString2VarsCountMismatchDAO");
////		
////		List<Snps2VarsCountmismatch>  mismatches = null;
////		
////		AppContext.startTimer();
////		
////		if(topN>0) {
////			
////			if(limitVarIds!=null && !limitVarIds.isEmpty())
////				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN, limitVarIds);
////			else	
////				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN);
////			
////			AppContext.debug(mismatches.size() + " mismatch pairs");
////			
////			// get varieties in topN
////			java.util.Iterator<Snps2VarsCountmismatch>  itdist = mismatches.iterator();		
////			Set topVars =new java.util.HashSet();
////			while(itdist.hasNext())
////			{
////				Snps2VarsCountmismatch dist3k = itdist.next();
////				topVars.add( dist3k.getVar1());
////				topVars.add( dist3k.getVar2());
////			}
////			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topVars);
////			AppContext.resetTimer(" topN distance calc");
////			
////		}
////		else {
////			if(limitVarIds!=null && !limitVarIds.isEmpty())
////				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), limitVarIds);
////			else
////				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end));
////			
////			
////			
////			AppContext.resetTimer(" all distance calc");
////		}
////		
////		int snps = -1;
////		//List snps = null;
////		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "VSnpRefposindexDAO") ; 
////		if(isCore) {
////			//snpcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService, "MvCoreSnpsDAO") ; 
////			//snps = snpcoreallvarsposService.getSNPs(chr, start, end, null ).size();
////			
////			snps = snpstringallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KCORESNP  ).size();
////			
////			 
////		 }
////		 else {
////			snps = snpstringallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KALLSNP   ).size();
////			 //snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
////			 // snps = snpallvarsposService.getSNPs(chr, start, end ,null ).size();
////		 }
////		 
////		 
////		 if(snps==0) return new String[] {"", "0","0"};
////		
////		//germplasms
////		AppContext.debug(mismatches.size() + " mismatch pairs");
////		
////		java.util.Map<BigDecimal, Integer> mapName2Row = new java.util.HashMap<BigDecimal, Integer>();
////		
////
////		Set<BigDecimal> setWithMismatch = new java.util.TreeSet();
////		
////		//java.util.Map<String, BigDecimal> mapPairMismatch = new java.util.HashMap<String, BigDecimal>();
////		java.util.Iterator<Snps2VarsCountmismatch>  itdist = mismatches.iterator();		
////		while(itdist.hasNext())
////		{
////			
////			Snps2VarsCountmismatch dist3k = itdist.next();
////			//mapPairMismatch.put( dist3k.getVar1() + ":" + dist3k.getVar2(), dist3k.getMismatch());
////			setWithMismatch.add( dist3k.getVar1());
////			setWithMismatch.add( dist3k.getVar2());
////		}
////		
////		
////		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix( setWithMismatch.size() );
////		
////		AppContext.debug( setWithMismatch.size() + " unique names with mismatch");
////		
////		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
////		Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
////		
////		int i=0;
////		Iterator<BigDecimal> itgerm = setWithMismatch.iterator();
////		while(itgerm.hasNext()) {
////			BigDecimal varid = itgerm.next();
////			mapName2Row.put(varid , i);
////			symdistmatrix.setIdentifier( i, "varid_" + varid );
////			i++;
////		}		
////
////		AppContext.debug("symdistmatrix done");
////		
////		double distscale = 1.0; 
////		
////		java.util.Iterator<Snps2VarsCountmismatch>  itdist2 = mismatches.iterator();		
////		while(itdist2.hasNext())
////		{
////			Snps2VarsCountmismatch dist3k = itdist2.next();
////			double dist =0 ;
////			if(snps > dist3k.getMismatch().intValue())
////				dist =   dist3k.getMismatch().intValue()*distscale /( snps -  dist3k.getMismatch().intValue() );
////			else
////				dist = AppContext.getMaxPhylodistance();
////			
////			try {
////				
////				if(  mapName2Row.get(dist3k.getVar1()).equals(  mapName2Row.get(dist3k.getVar2()) ) ) {
////					//AppContext.debug( mapName2Row.get(dist3k.getVar1()) + ": " +  mapVarid2Variety.get(dist3k.getVar1()) + "\t" +  mapName2Row.get(dist3k.getVar2()) + ": " +    mapVarid2Variety.get(dist3k.getVar2())  + "  -- " + dist);
////					dist = 0.0;
////				}
////				
////				symdistmatrix.setValue( mapName2Row.get(dist3k.getVar1()) , mapName2Row.get(dist3k.getVar2()) , dist );
////				symdistmatrix.setValue( mapName2Row.get(dist3k.getVar2()) , mapName2Row.get(dist3k.getVar1()),  dist );
////				
////			} catch(Exception ex) {
////				
////				AppContext.debug( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t" + mapName2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +  mapName2Row.get(dist3k.getVar2()) + "\t" + dist );
////			
////			}
////		}
////		
////		AppContext.debug(symdistmatrix.getSize() + " symdistmatrix ready");
////		
////		try {
////			
////			TreeConstructor tree = new  TreeConstructor(symdistmatrix,
////				org.biojava3.phylo.TreeType.NJ ,
////				org.biojava3.phylo.TreeConstructionAlgorithm.PID ,
////			//	null);
////				new org.biojava3.phylo.ProgessListenerStub());
////				tree.process();
////
////				AppContext.debug("process done");
////			String newick = tree.getNewickString(false, true);
////			
////			
////			Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
////			
////			//AppContext.debug(newick);
////			Iterator<BigDecimal> itgerm2 = setWithMismatch.iterator();
////			while(itgerm2.hasNext()) {
////				BigDecimal c = itgerm2.next();
////			
////				Variety var = mapId2Variety.get(c);
////				String subpop = "";
////				if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
////				String irisid = "";
////				if( var.getIrisId()!=null) irisid=var.getIrisId();
////				//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
////				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
////			
////			}
////			//AppContext.debug(newick);
////			
////			return new String[] {newick, Integer.toString(symdistmatrix.getSize()), Integer.toString( mismatches.size()) };
////			
////			
////		} catch(Exception ex)
////		{
////			ex.printStackTrace();
////		}
////		
////		return null;
////		
////	}
////	
////static boolean isLessThan(BigDecimal n1, BigDecimal n2) {
////	return n1.compareTo(n2)<0;
////}
////static boolean isGreaterThan(BigDecimal n1, BigDecimal n2) {
////	return n1.compareTo(n2)>0;
////}
////
/////**
//// * Move the group origkey to newkey, chaneg group assignments, remove orig key group 
//// * @param origkey
//// * @param newkey
//// * @param mapGroup2Set
//// * @param mapVar2Group
//// */
////private Map[] changeGroupKey(BigDecimal origkey, BigDecimal newkey, Map<BigDecimal,Set<BigDecimal>> mapGroup2Set, Map<BigDecimal,BigDecimal> mapVar2Group) {
////	
////	Set origset = mapGroup2Set.get(origkey);
////	Iterator<BigDecimal> itSet = origset.iterator();
////	while(itSet.hasNext()) {
////		BigDecimal var = itSet.next();
////		mapVar2Group.put(var, newkey);
////	}
////	mapVar2Group.put(newkey, newkey);
////	origset.add(newkey);
////	mapGroup2Set.put(newkey, origset);
////	mapGroup2Set.remove(origkey);
////	return new Map[]{mapVar2Group, mapGroup2Set};
////}
////
/////**
//// * merge 2 groups, from srcVarGroup add to destVarGroup, and remove srcVarGroup
//// * @param destVarGroup
//// * @param srcVarGroup
//// * @param mapGroup2Set
//// * @param mapVar2Group
//// */
////private Map[] mergeGroups(BigDecimal destVarGroup, BigDecimal srcVarGroup, Map<BigDecimal,Set<BigDecimal>> mapGroup2Set, Map<BigDecimal,BigDecimal> mapVar2Group) {
////	
////	Set origset = mapGroup2Set.get(srcVarGroup);
////	Iterator<BigDecimal> itSet = origset.iterator();
////	while(itSet.hasNext()) {
////		BigDecimal var = itSet.next();
////		mapVar2Group.put(var, destVarGroup);
////	}
////	
////	Set minvarset = mapGroup2Set.get(destVarGroup);
////	minvarset.addAll( origset );
////	mapGroup2Set.remove(srcVarGroup);
////	mapGroup2Set.put(destVarGroup , minvarset);
////	return new Map[]{mapVar2Group, mapGroup2Set};
////	
////}
////	
/////**
//// * 
//// * @param var1
//// * @param var2
//// * @param mapVar2Group
//// * @param mapGroup2Varset
//// */
////private double linkUngroupedToGroup(BigDecimal var1, BigDecimal var2, Map<BigDecimal,BigDecimal> mapVar2Group, 
////		Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset, Map<BigDecimal,Map<BigDecimal,Double>> mapdistances) {
////		
////		Set groupset = mapGroup2Varset.get( mapVar2Group.get(var2) );
////		Iterator<BigDecimal> itGroupmems = groupset.iterator();
////		double total = 0;
////		while(itGroupmems.hasNext()) {
////			BigDecimal varmax =  itGroupmems.next();
////			BigDecimal varmin = var1;
////			if( isGreaterThan(var1,varmax) ) {
////				varmin = varmax;
////				varmax = var1;
////			}
////			//total += mapdistances.get(varmin).[varmax.intValue()-1];
////			total += mapdistances.get(varmin).get(varmax);
////		}
////		return total/groupset.size(); 
////}
////
////private double linkgroupedTogroup(BigDecimal var1, BigDecimal var2, Map<BigDecimal,BigDecimal> mapVar2Group, 
////		Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset, Map<BigDecimal,Map<BigDecimal,Double>>  mapdistances) {
////		
////		Set groupset1 = mapGroup2Varset.get( mapVar2Group.get(var1) );
////		Set groupset2 = mapGroup2Varset.get( mapVar2Group.get(var2) );
////		Iterator<BigDecimal> itGroup1mems = groupset1.iterator();
////		double total = 0;
////		int ncount=0;
////		while(itGroup1mems.hasNext()) {
////			BigDecimal vargroup1 =  itGroup1mems.next();
////
////			Iterator<BigDecimal> itGroup2mems = groupset2.iterator();
////			while(itGroup2mems.hasNext()) {
////				BigDecimal vargroup2 =  itGroup2mems.next();
////				if( isGreaterThan(vargroup1,vargroup2) ) continue;
////				//total += mapdistances.get(vargroup1)[vargroup2.intValue()-1];
////				total += mapdistances.get(vargroup1).get(vargroup2);
////				ncount++;
////			}
////		}
////		return total/ncount; 
////}
////
////
////
////@Override
//////public Object[] constructPhylotreeTopNNew(String scale, String chr, int start, int end, int topN,  String requestid) {
////public Object[] constructPhylotreeMindist(String scale, String chr, int start, int end, String mindist) {
////		
////		
////		SNPsStringData snpstrdata = getSNPsStringData(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), null );
////		if(snpstrdata==null) return new String[] {"", "0","0"};
////	 
////		double minCount = 0;
////		if(mindist!=null) {
////			try {
////				minCount = Double.valueOf(mindist);
////				AppContext.debug("grouping pairs with distance below " + minCount + " pairs (" + mindist +")");
////			} catch(Exception ex) {
////				ex.printStackTrace();
////			}
////		}
////		minCount=1;
////				
////		// use minVar in Varset as group key
////		Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset = new HashMap();
////		
////		// groups refer to group key
////		Map<BigDecimal,BigDecimal> mapVar2Group = new HashMap();
////		
////		Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
////	 
////		Set setNonsynIdx=new HashSet();
////		//Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
////		
////		//Set sortedVarietiesPairsDesc = new TreeSet(new SnpsString2VarsImplSorter());
////		List sortedVarietiesPairsDesc = new ArrayList();
////		
////		Map<BigDecimal,Map<BigDecimal,Double>> mapVar1ToVar2Dist = new  HashMap();
////		
////	 	Iterator<BigDecimal> itVar1 = mapVarid2Snpsstr.keySet().iterator();
////	 	while(itVar1.hasNext()) {
////			BigDecimal var1 = itVar1.next();
////			String snpstr1 = (String)mapVarid2Snpsstr.get(var1);
////			Map getMapVar12SnpsAllele2str = (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var1);
////			
////			//double var2distarray[] = new double[var1.intValue()];
////			Map<BigDecimal,Double> var2distmap = new HashMap();
////			
////		 	Iterator<BigDecimal> itVar2 = mapVarid2Snpsstr.keySet().iterator();
////			while(itVar2.hasNext()) {
////				BigDecimal var2 = itVar2.next();
////				if( isGreaterThan(var1, var2)) continue;
////				
////				
////				String snpstr2 = (String)mapVarid2Snpsstr.get(var2);
////				Set varNonsynIdx = new HashSet();
////				
////				double misCount = countVarpairMismatch(snpstr1, snpstr2, false,  getMapVar12SnpsAllele2str, (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var2),  
////						(Map)snpstrdata.getMapIdx2NonsynAlleles(),  snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,  isNonsynOnly );
////				
////				sortedVarietiesPairsDesc.add( new Snps2VarsCountmismatchImpl( var1,  var2, BigDecimal.valueOf(misCount)) ); 
////				//var2distarray[var2.intValue()-1] = misCount;
////				var2distmap.put(var2, misCount);
////				
////			}
////			mapVar1ToVar2Dist.put(var1, var2distmap);
////	 	}
////	 	
////	 	Collections.sort(sortedVarietiesPairsDesc, new SnpsString2VarsImplSorter());
////	 	
////	 	// filter sorted pairs 
////	 	
////	 	Iterator<Snps2VarsCountmismatch>  itPairs = sortedVarietiesPairsDesc.iterator();
////	 	//Set setVarietyDontGroup = new HashSet();
////	 	Set setVarietyNotGrouped = new HashSet();
////	 	Set setVarietyGrouped = new HashSet();
////	 	
////	 	while(itPairs.hasNext()) {
////	 		Snps2VarsCountmismatch pair = itPairs.next();
////	 		//AppContext.debug(pair.getMismatch().toString());
////	 	
////				if(pair.getMismatch().doubleValue()>=minCount) {
////					//mismatches.add( pair );
////					//setVarietyDontGroup.add( pair.getVar1() );
////					//setVarietyDontGroup.add( pair.getVar2() );
////					setVarietyNotGrouped.add( pair.getVar1().intValue() );
////					setVarietyNotGrouped.add( pair.getVar2().intValue() );
////					
////				//} else if( setVarietyDontGroup.contains(pair.getVar1()) || setVarietyDontGroup.contains(pair.getVar2()) ) {} 
////				}
////				else {
////					// both nodes not in paired, sould be grouped
////					//setVarietyNotGrouped.remove( pair.getVar1() );
////					//setVarietyNotGrouped.remove( pair.getVar2() );
////					setVarietyGrouped.add( pair.getVar1().intValue());
////					setVarietyGrouped.add( pair.getVar2().intValue());
////					
////					// create groups for mincount
////					BigDecimal minVar = pair.getVar1();
////					BigDecimal maxVar = pair.getVar2();
////					if( isGreaterThan(minVar,maxVar)) {
////						minVar = pair.getVar2();
////						maxVar = pair.getVar1();
////					}
////					
////					// check if var1 or var2 in the groups
////					BigDecimal minVarGroup = mapVar2Group.get(minVar);
////					if(minVarGroup==null) {
////						// minvar has no group
////						BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
////						if(maxVarGroup==null) {
////							//maxvar has no group
////							
////							// make new groups
////							Set newgroup = new HashSet();
////							newgroup.add(minVar);
////							newgroup.add(maxVar);
////							mapVar2Group.put(minVar ,minVar);
////							mapVar2Group.put(maxVar ,minVar);
////							mapGroup2Varset.put(minVar, newgroup);
////							
////						} else {
////							// add minvar to max's group
////							// check nodename
////							if( isLessThan(maxVarGroup, minVar) ) {
////								mapGroup2Varset.get(maxVarGroup).add(minVar);
////							} else {
////								Map[] groupmaps = changeGroupKey(maxVarGroup, minVar,  mapGroup2Varset, mapVar2Group);
////								mapVar2Group = groupmaps[0];
////								mapGroup2Varset = groupmaps[1];
////							}
////						}
////					} else {
////						BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
////						if(maxVarGroup==null) {
////							//maxvar has no group
////							// add maxvar to minvars group
////							mapGroup2Varset.get( minVarGroup ).add(  maxVar );
////							mapVar2Group.put(maxVar, minVarGroup );
////						} else {
////							// merge min's and max's groups
////							if(isLessThan(minVarGroup,maxVarGroup)) {
////								// merge to minvar's group
////								Map[] groupmaps = mergeGroups( minVarGroup, maxVarGroup, mapGroup2Varset, mapVar2Group);
////								mapVar2Group = groupmaps[0];
////								mapGroup2Varset = groupmaps[1];
////							} else if(isGreaterThan(minVarGroup, maxVarGroup) )
////							{
////								// merge to maxvar's group
////								Map[] groupmaps = mergeGroups( maxVarGroup, minVarGroup,  mapGroup2Varset, mapVar2Group);
////								mapVar2Group = groupmaps[0];
////								mapGroup2Varset = groupmaps[1];
////
////							} // else they are from the same group, do nothing
////						}
////					}
////					
////				}
////		}
////	 	
////	 	AppContext.debug( mapGroup2Varset.size() + " groups, " +  setVarietyGrouped.size() + " varieties grouped, "  + setVarietyNotGrouped.size() + " not grouped");
////	 	
////	 	Set grouponlySet = new HashSet();
////	 	grouponlySet.addAll( setVarietyGrouped);
////	 	grouponlySet.removeAll( setVarietyNotGrouped  );
////	 	Set notgrouponlySet = new HashSet();
////	 	notgrouponlySet.addAll( setVarietyNotGrouped);
////	 	notgrouponlySet.removeAll( setVarietyGrouped  );
////	 	Set bothSet = new HashSet();
////	 	bothSet.addAll( setVarietyNotGrouped);
////	 	bothSet.retainAll( setVarietyGrouped  );
////	 	AppContext.debug(grouponlySet.size() + " group only, " + notgrouponlySet.size() + " ungroup only, " + bothSet.size() + " both" );
////	 	
////	 	// linked groups to ungroupd pairs
////	 	
////	 	AppContext.debug(sortedVarietiesPairsDesc.size() + " distance pairs");
////	 	
////	 	Set setUGToUGVarietyPairs = new HashSet();
////	 	Set setGroupToGroupPairs = new HashSet();
////	 	Set setVarietyToGroupPairs = new HashSet();
////		itPairs = sortedVarietiesPairsDesc.iterator();
////		
////		long paircnt = 0;
////		int paircnt2 = 0;
////		while(itPairs.hasNext()) {
////			Snps2VarsCountmismatch pair = itPairs.next();
////			BigDecimal var1 = pair.getVar1();
////			BigDecimal var2 = pair.getVar2();
////			int ivar1 = var1.intValue();
////			int ivar2 = var2.intValue();
////					
////
////			//if(pair.getMismatch().doubleValue()>=minCount) continue;
////			
////			paircnt++;
////			if(paircnt> 1000) {
////				paircnt =0;
////				AppContext.debug("pair " + paircnt2 + "k pairs");
////				paircnt2 ++;
////			}
////			
////			if(notgrouponlySet.contains(ivar1) && notgrouponlySet.contains(ivar2)) {
////				setUGToUGVarietyPairs.add( new Snps2VarsCountmismatchImpl( var1,  mapVar2Group.get(var2) , pair.getMismatch()) ); 
////			}
////			else if(grouponlySet.contains(ivar1) && notgrouponlySet.contains(ivar2)) {
////				// link var2 to group of var1, using average distance between var2 and all group of var1
////				double avgdist = linkUngroupedToGroup(var2, var1, mapVar2Group, mapGroup2Varset , mapVar1ToVar2Dist);
////				setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var2,  mapVar2Group.get(var1) , BigDecimal.valueOf(avgdist)) ); 
////			}
////			else if(grouponlySet.contains(ivar2) && notgrouponlySet.contains(ivar1)) {
////				// link var1 to group of var2, using average distance between var1 and all group of var2
////				double avgdist = linkUngroupedToGroup(var1, var2, mapVar2Group, mapGroup2Varset , mapVar1ToVar2Dist);
////				setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var1,  mapVar2Group.get(var2) , BigDecimal.valueOf(avgdist)) ); 
////			}
////			else if(bothSet.contains(ivar1) && notgrouponlySet.contains(ivar2)) {
////				// link var2 to group of var1, using average distance between var2 and all group of var1
////				double avgdist = linkUngroupedToGroup(var2, var1, mapVar2Group, mapGroup2Varset , mapVar1ToVar2Dist);
////				setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var2,  mapVar2Group.get(var1) , BigDecimal.valueOf( (avgdist+pair.getMismatch().doubleValue())/2 )) ); 
////			}
////			else if(bothSet.contains(ivar2) && notgrouponlySet.contains(ivar1)) {
////				// link var1 to group of var2, using average distance between var1 and all group of var2
////				double avgdist = linkUngroupedToGroup(var1, var2, mapVar2Group, mapGroup2Varset , mapVar1ToVar2Dist);
////				setVarietyToGroupPairs.add( new Snps2VarsCountmismatchImpl( var1,  mapVar2Group.get(var2) , BigDecimal.valueOf( (avgdist+pair.getMismatch().doubleValue())/2 )) ); 
////			}
////			else if( grouponlySet.contains(ivar1) &&  grouponlySet.contains(ivar2) ) {
////				throw new RuntimeException("cannot be both members of  grouponly because their groups should have been merged earlier");
////				//double avgdist = linkgroupedTogroup(var1, var2, mapVar2Group, mapGroup2Varset , mapVar1ToVar2Dist);
////				//setGroupToGroupPairs.add( new Snps2VarsCountmismatchImpl( mapVar2Group.get(var1),  mapVar2Group.get(var2) , BigDecimal.valueOf(avgdist)) ); 
////			}
////			else if( (grouponlySet.contains(ivar1) || bothSet.contains(ivar1)) &&  (grouponlySet.contains(ivar2) || bothSet.contains(ivar2)) ) {
////				//throw new RuntimeException("cannot be both members of  grouponly because their groups should have been merged earlier");
////				double avgdist = linkgroupedTogroup(var1, var2, mapVar2Group, mapGroup2Varset , mapVar1ToVar2Dist);
////				setGroupToGroupPairs.add( new Snps2VarsCountmismatchImpl( mapVar2Group.get(var1),  mapVar2Group.get(var2) , BigDecimal.valueOf(avgdist)) ); 
////			}
////			else throw new RuntimeException("cannot be both members of bothSet because their groups should have been merged earlier");
////		}
////			
////		
////		AppContext.debug(mapGroup2Varset.size() + " groups created for tree" );
////		
////		varietyfacade = (VarietyFacade)AppContext.checkBean( varietyfacade, "VarietyFacade" );
////
////		// name groups (using minvar and subpop count)
////		Map<BigDecimal,Variety> mapVarid2Var = varietyfacade.getMapId2Variety();
////		Map<BigDecimal,String> mapGroup2Name = new HashMap();
////		Iterator<BigDecimal> itGroup = mapGroup2Varset.keySet().iterator();
////		while(itGroup.hasNext()) {
////			BigDecimal group = itGroup.next();
////			Iterator<BigDecimal> itVarset = mapGroup2Varset.get(group).iterator();
////			Map<String, Integer> mapGroupSubpopCount = new HashMap();
////			while(itVarset.hasNext()) {
////				BigDecimal varsetmember = itVarset.next();
////				String subpop = mapVarid2Var.get(varsetmember).getSubpopulation(); 
////				Integer subcount = mapGroupSubpopCount.get(subpop);
////				if(subcount==null) {
////					Integer count = 1;
////					mapGroupSubpopCount.put(subpop, count);
////				} else {
////					mapGroupSubpopCount.remove(subpop);
////					mapGroupSubpopCount.put(subpop, subcount+1) ;
////				}
////			}
////			
////			
////			
////			// create name
////			Iterator<String> itSub = new TreeSet(mapGroupSubpopCount.keySet()).iterator();
////			StringBuffer buff = new StringBuffer();
////			buff.append("GROUP " + mapVarid2Var.get(group).getName() + " :"  );
////			while(itSub.hasNext()) {
////				String subpop = itSub.next();
////				 buff.append(subpop +" " +  mapGroupSubpopCount.get(subpop));
////				 if(itSub.hasNext()) buff.append(":");
////			}
////			mapGroup2Name.put(group,buff.toString());
////			
////			AppContext.debug("group " + group + ": " +  mapGroup2Name.get(group) + " has size " + mapGroup2Varset.get(group).size() );
////		}
////		
////			AppContext.startTimer();
////		 
////		 int snps = snpstrdata.getStrRef().length();
////		 if(snps==0) return new String[] {"", "0","0"};
////		
////		//germplasms
////		 
////
////		 
////		AppContext.debug(setUGToUGVarietyPairs.size() + " mismatch var-var pairs");
////		AppContext.debug(setVarietyToGroupPairs.size() + " mismatch var-group pairs");
////		AppContext.debug(setGroupToGroupPairs.size() +  " mismatch group-group pairs");
////		
////		List<Snps2VarsCountmismatch>  mismatches = new ArrayList();
////		mismatches.addAll(setUGToUGVarietyPairs);
////		mismatches.addAll(setVarietyToGroupPairs );
////		mismatches.addAll(setGroupToGroupPairs );
////	
////		
////		Set setNodes = new TreeSet();
////		
////		//AppContext.debug(grouponlySet.size() + " group only, " + notgrouponlySet.size() + " ungroup only, " + bothSet.size() + " both" );
////		//setNodes.addAll( setVarietyDontGroup );
////		setNodes.addAll( notgrouponlySet );
////		setNodes.addAll( mapGroup2Varset.keySet() );
////		
////		
////		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix( setNodes.size() );
////
////		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
////		Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
////		
////		Map<BigDecimal,Integer> mapVarid2Row = new HashMap();
////		
////		int i=0;
////		Iterator<BigDecimal> itgerm = setNodes.iterator();
////		while(itgerm.hasNext()) {
////			BigDecimal varid = itgerm.next();
////			mapVarid2Row.put(varid , i);
////			symdistmatrix.setIdentifier( i, "varid_" + varid );
////			i++;
////		}		
////
////		AppContext.debug("symdistmatrix done");
////		
////		double distscale = 1.0; 
////		
////		java.util.Iterator<Snps2VarsCountmismatch>  itdist2 = mismatches.iterator();		
////		while(itdist2.hasNext())
////		{
////			Snps2VarsCountmismatch dist3k = itdist2.next();
////			double dist =0 ;
////			if(snps > dist3k.getMismatch().intValue())
////				dist =   dist3k.getMismatch().intValue()*distscale /( snps -  dist3k.getMismatch().intValue() );
////			else
////				dist = AppContext.getMaxPhylodistance();
////			
////			try {
////				
////				if(  mapVarid2Row.get(dist3k.getVar1()).equals(  mapVarid2Row.get(dist3k.getVar2()) ) ) {
////					//AppContext.debug( mapName2Row.get(dist3k.getVar1()) + ": " +  mapVarid2Variety.get(dist3k.getVar1()) + "\t" +  mapName2Row.get(dist3k.getVar2()) + ": " +    mapVarid2Variety.get(dist3k.getVar2())  + "  -- " + dist);
////					dist = 0.0;
////				}
////				
////				symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar1()) , mapVarid2Row.get(dist3k.getVar2()) , dist );
////				symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar2()) , mapVarid2Row.get(dist3k.getVar1()),  dist );
////				
////			} catch(Exception ex) {
////				
////				AppContext.debug( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t" + mapVarid2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +  mapVarid2Row.get(dist3k.getVar2()) + "\t" + dist );
////			
////			}
////		}
////		
////		AppContext.debug(symdistmatrix.getSize() + " symdistmatrix ready");
////		
////		try {
////			
////			TreeConstructor tree = new  TreeConstructor(symdistmatrix,
////				org.biojava3.phylo.TreeType.NJ ,
////				org.biojava3.phylo.TreeConstructionAlgorithm.PID ,
////			//	null);
////				new org.biojava3.phylo.ProgessListenerStub());
////				tree.process();
////
////				AppContext.debug("process done");
////			String newick = tree.getNewickString(false, true);
////			
////			
////			Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
////			
////			//AppContext.debug(newick);
////			Iterator<BigDecimal> itgerm2 = setNodes.iterator();
////			while(itgerm2.hasNext()) {
////				BigDecimal c = itgerm2.next();
////			
////				Variety var = mapId2Variety.get(c);
////				
////				String nodename = mapGroup2Name.get(var.getVarietyId());
////				if(nodename==null) {
////					String subpop = "";
////					if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
////					String irisid = "";
////					if( var.getIrisId()!=null) irisid=var.getIrisId();
////					//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
////					newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
////				} else {
////					newick = newick.replace("varid_" + c + ":", nodename.replace("::", "_").replace(":","_") + ":");
////				}
////			
////			}
////			//AppContext.debug(newick);
////			
////			return new Object[] {newick, Integer.toString(symdistmatrix.getSize()), Integer.toString( mismatches.size()) , mapGroup2Varset, mapGroup2Name};
////			
////			
////		} catch(Exception ex)
////		{
////			ex.printStackTrace();
////		}
////		
////		return null;
////		
////	}
////	
////
////public Object[] constructPhylotreeMindist1(String scale, String chr, int start, int end, String mindist) {
////	
////	List<Snps2VarsCountmismatch>  mismatches = new ArrayList();
////	SNPsStringData snpstrdata = getSNPsStringData(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), null );
////	if(snpstrdata==null) return new String[] {"", "0","0"};
//// 
////	double minCount = 0;
////	if(mindist!=null) {
////		try {
////			minCount = Double.valueOf(mindist);
////			AppContext.debug("grouping pairs with distance below " + minCount + " pairs (" + mindist +")");
////		} catch(Exception ex) {
////			ex.printStackTrace();
////		}
////	}
////	minCount=1;
////			
////	// use minVar in Varset as group key
////	Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset = new HashMap();
////	
////	// groups refer to group key
////	Map<BigDecimal,BigDecimal> mapVar2Group = new HashMap();
////	
////	Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
//// 
////	Set setNonsynIdx=new HashSet();
////	//Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
////	
////	//Set sortedVarietiesPairsDesc = new TreeSet(new SnpsString2VarsImplSorter());
////	List sortedVarietiesPairsDesc = new ArrayList();
////	
//// 	Iterator<BigDecimal> itVar1 = mapVarid2Snpsstr.keySet().iterator();
//// 	while(itVar1.hasNext()) {
////		BigDecimal var1 = itVar1.next();
////		String snpstr1 = (String)mapVarid2Snpsstr.get(var1);
////		Map getMapVar12SnpsAllele2str = (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var1);
////		
////	 	Iterator<BigDecimal> itVar2 = mapVarid2Snpsstr.keySet().iterator();
////		while(itVar2.hasNext()) {
////			BigDecimal var2 = itVar2.next();
////			if( isGreaterThan(var1, var2)) continue;
////			
////			String snpstr2 = (String)mapVarid2Snpsstr.get(var2);
////			Set varNonsynIdx = new HashSet();
////			
////			double misCount = countVarpairMismatch(snpstr1, snpstr2, false,  getMapVar12SnpsAllele2str, (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var2),  
////					(Map)snpstrdata.getMapIdx2NonsynAlleles(),  snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,  isNonsynOnly );
////			
////			sortedVarietiesPairsDesc.add( new Snps2VarsCountmismatchImpl( var1,  var2, BigDecimal.valueOf(misCount)) ); 
////			
////		}
//// 	}
//// 	
//// 	Collections.sort(sortedVarietiesPairsDesc, new SnpsString2VarsImplSorter());
//// 	
//// 	// filter sorted pairs 
//// 	
//// 	Iterator<Snps2VarsCountmismatch>  itPairs = sortedVarietiesPairsDesc.iterator();
//// 	//Set setVarietyDontGroup = new HashSet();
//// 	Set setVarietyNotGrouped = new HashSet();
//// 	Set setVarietyGrouped = new HashSet();
//// 	
//// 	while(itPairs.hasNext()) {
//// 		Snps2VarsCountmismatch pair = itPairs.next();
//// 		//AppContext.debug(pair.getMismatch().toString());
//// 	
////			if(pair.getMismatch().intValue()>=minCount) {
////				mismatches.add( pair );
////				//setVarietyDontGroup.add( pair.getVar1() );
////				//setVarietyDontGroup.add( pair.getVar2() );
////				setVarietyNotGrouped.add( pair.getVar1() );
////				setVarietyNotGrouped.add( pair.getVar2() );
////				
////			//} else if( setVarietyDontGroup.contains(pair.getVar1()) || setVarietyDontGroup.contains(pair.getVar2()) ) {} 
////			}
////			else {
////				// both nodes not in paired, sould be grouped
////				//setVarietyNotGrouped.remove( pair.getVar1() );
////				//setVarietyNotGrouped.remove( pair.getVar2() );
////				setVarietyGrouped.add( pair.getVar1());
////				setVarietyGrouped.add( pair.getVar2());
////				
////				// create groups for mincount
////				BigDecimal minVar = pair.getVar1();
////				BigDecimal maxVar = pair.getVar2();
////				if( isGreaterThan(minVar,maxVar)) {
////					minVar = pair.getVar2();
////					maxVar = pair.getVar1();
////				}
////				
////				// check if var1 or var2 in the groups
////				BigDecimal minVarGroup = mapVar2Group.get(minVar);
////				if(minVarGroup==null) {
////					// minvar has no group
////					BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
////					if(maxVarGroup==null) {
////						//maxvar has no group
////						
////						// make new groups
////						Set newgroup = new HashSet();
////						newgroup.add(minVar);
////						newgroup.add(maxVar);
////						mapVar2Group.put(minVar ,minVar);
////						mapVar2Group.put(maxVar ,minVar);
////						mapGroup2Varset.put(minVar, newgroup);
////						
////					} else {
////						// add minvar to max's group
////						// check nodename
////						if( isLessThan(maxVarGroup, minVar) ) {
////							mapGroup2Varset.get(maxVarGroup).add(minVar);
////						} else {
////							changeGroupKey(maxVarGroup, minVar,  mapGroup2Varset, mapVar2Group);
////						}
////					}
////				} else {
////					BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
////					if(maxVarGroup==null) {
////						//maxvar has no group
////						// add maxvar to minvars group
////						mapGroup2Varset.get( minVarGroup ).add(  maxVar );
////						mapVar2Group.put(maxVar, minVarGroup );
////					} else {
////						// merge min's and max's groups
////						if(isLessThan(minVarGroup,maxVarGroup)) {
////							// merge to minvar's group
////							mergeGroups( minVarGroup, maxVarGroup, mapGroup2Varset, mapVar2Group);
////						} else if(isGreaterThan(minVarGroup, maxVarGroup) )
////						{
////							// merge to maxvar's group
////							mergeGroups( maxVarGroup, minVarGroup,  mapGroup2Varset, mapVar2Group);
////						} // else they are from the same group, do nothing
////					}
////				}
////				
////			}
////	}
//// 	
//// 	AppContext.debug(mapGroup2Varset.keySet().size() + " groups, " +  setVarietyGrouped.size() + " varieties grouped, "  + setVarietyNotGrouped.size() + " not grouped");
//// 	
//// 	Set grouponlySet = new HashSet();
//// 	grouponlySet.addAll( setVarietyGrouped);
//// 	grouponlySet.removeAll( setVarietyNotGrouped  );
//// 	Set notgrouponlySet = new HashSet();
//// 	notgrouponlySet.addAll( setVarietyNotGrouped);
//// 	notgrouponlySet.removeAll( setVarietyGrouped  );
//// 	Set bothSet = new HashSet();
//// 	bothSet.addAll( setVarietyNotGrouped);
//// 	bothSet.retainAll( setVarietyGrouped  );
//// 	AppContext.debug(grouponlySet.size() + " group only, " + notgrouponlySet.size() + " ungroup only, " + bothSet.size() + " both" );
//// 	// linked groups to ungroupd pairs
////
//// 	Set setVarietyToGroupPairs = new HashSet();
////	itPairs = sortedVarietiesPairsDesc.iterator();
////	while(itPairs.hasNext()) {
////		Snps2VarsCountmismatch pair = itPairs.next();
////		BigDecimal var1 = pair.getVar1();
////		BigDecimal var2 = pair.getVar2();
////		
////		if(pair.getMismatch().intValue()>=minCount) {
////			
////			// if var1 in group, link node to group
////			if(mapVar2Group.containsKey(var1) && !mapVar2Group.containsKey(var2)) {
////				// link var2 to group of var1
////				setVarietyToGroupPairs.add(   new Snps2VarsCountmismatchImpl( var2,  mapVar2Group.get(var1) , pair.getMismatch()  ) );
////			}
////			else if(!mapVar2Group.containsKey(var1) && mapVar2Group.containsKey(var2)) {
////				// link var1 to group of var2
////				setVarietyToGroupPairs.add(   new Snps2VarsCountmismatchImpl( var1,  mapVar2Group.get(var2) , pair.getMismatch()  ) );
////				
////			} else if(mapVar2Group.containsKey(var1) && mapVar2Group.containsKey(var2)) {
////				// link the groups of var1 and var2
////				setVarietyToGroupPairs.add(   new Snps2VarsCountmismatchImpl( mapVar2Group.get(var2),  mapVar2Group.get(var2) , pair.getMismatch()  ) );
////			}
////			
////			
////			/*
////			if(!setVarietyDontGroup.contains(pair.getVar1()) && setVarietyDontGroup.contains(var2))
////			{
////				// var1 in groups
////				// fnd in group, and join in pair
////				BigDecimal var1group = mapVar2Group.get(  var1 );
////				if(var1group==null) throw new RuntimeException("var1 supposed to be in groups");
////				setVarietyToGroupPairs.add(   new Snps2VarsCountmismatchImpl( var2,  var1group , pair.getMismatch()  ) );
////			}
////			else if(!setVarietyDontGroup.contains( var2 ) && setVarietyDontGroup.contains(var1) ) 
////			{
////				// var1 in groups
////				// fnd in group, and join in pair
////				BigDecimal var2group = mapVar2Group.get(  var2 );
////				if(var2group==null) throw new RuntimeException("var2 supposed to be in groups");
////				setVarietyToGroupPairs.add(   new Snps2VarsCountmismatchImpl( var1,  var2group , pair.getMismatch()  ) );
////			}
////			//else throw new RuntimeException("Cannot be, 1 var should be grouped, and 1 not grouped in pair");
////			 * */
////			 
////		}
////	}
////					
////					
////					
//// 	
//////	while(itVar1.hasNext()) {
//////		BigDecimal var1 = itVar1.next();
//////		String snpstr1 = (String)mapVarid2Snpsstr.get(var1);
//////		Map getMapVar12SnpsAllele2str = (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var1);
//////		
//////	 	Iterator<BigDecimal> itVar2 = mapVarid2Snpsstr.keySet().iterator();
//////		while(itVar2.hasNext()) {
//////			BigDecimal var2 = itVar2.next();
//////			if( isGreaterThan(var1, var2) || var1.equals(var2) ) continue;
//////			
//////			String snpstr2 = (String)mapVarid2Snpsstr.get(var2);
//////
//////			Set varNonsynIdx = new HashSet();
//////			
//////			double misCount = countVarpairMismatch(snpstr1, snpstr2, false,  getMapVar12SnpsAllele2str, (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var2),  
//////					(Map)snpstrdata.getMapIdx2NonsynAlleles(),  snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,  isNonsynOnly );
//////
//////			if(misCount>=minCount) {
//////				mismatches.add( new Snps2VarsCountmismatchImpl( var1,  var2, BigDecimal.valueOf(misCount)) );
//////			} else {
//////				
//////				
//////				// create groups for mincount
//////				BigDecimal minVar = var1;
//////				BigDecimal maxVar = var1;
//////				if( isGreaterThan(var1,var2)) {
//////					minVar = var2;
//////					maxVar = var1;
//////				}
//////				
//////				// check if var1 or var2 in the groups
//////				BigDecimal minVarGroup = mapVar2Group.get(minVar);
//////				if(minVarGroup==null) {
//////					// minvar has no group
//////					BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
//////					if(maxVarGroup==null) {
//////						//maxvar has no group
//////						
//////						// make new groups
//////						Set newgroup = new HashSet();
//////						newgroup.add(minVar);
//////						newgroup.add(maxVar);
//////						mapVar2Group.put(minVar ,minVar);
//////						mapVar2Group.put(maxVar ,minVar);
//////						mapGroup2Varset.put(minVar, newgroup);
//////						
//////					} else {
//////						// add minvar to max's group
//////						// check nodename
//////						if( isLessThan(maxVarGroup, minVar) ) {
//////							mapGroup2Varset.get(maxVarGroup).add(minVar);
//////						} else {
//////							changeGroupKey(maxVarGroup, minVar,  mapGroup2Varset, mapVar2Group);
//////						}
//////					}
//////				} else {
//////					BigDecimal maxVarGroup = mapVar2Group.get(maxVar);
//////					if(maxVarGroup==null) {
//////						//maxvar has no group
//////						// add maxvar to minvars group
//////						mapGroup2Varset.get( minVarGroup ).add(  maxVar );
//////						mapVar2Group.put(maxVar, minVarGroup );
//////					} else {
//////						// merge min's and max's groups
//////						if(isLessThan(minVarGroup,maxVarGroup)) {
//////							// merge to minvar's group
//////							mergeGroups( minVarGroup, maxVarGroup, mapGroup2Varset, mapVar2Group);
//////						} else if(isGreaterThan(minVarGroup, maxVarGroup) )
//////						{
//////							// merge to maxvar's group
//////							mergeGroups( maxVarGroup, minVarGroup,  mapGroup2Varset, mapVar2Group);
//////						} // else they are from the same group, do nothing
//////					}
//////				}
//////				
//////			}
//////			
//////		}
//////	}
////	
////	AppContext.debug(mapGroup2Varset.size() + " groups created for tree" );
////	
////	varietyfacade = (VarietyFacade)AppContext.checkBean( varietyfacade, "VarietyFacade" );
////
////	// name groups (using minvar and subpop count)
////	Map<BigDecimal,Variety> mapVarid2Var = varietyfacade.getMapId2Variety();
////	Map<BigDecimal,String> mapGroup2Name = new HashMap();
////	Iterator<BigDecimal> itGroup = mapGroup2Varset.keySet().iterator();
////	while(itGroup.hasNext()) {
////		BigDecimal group = itGroup.next();
////		Iterator<BigDecimal> itVarset = mapGroup2Varset.get(group).iterator();
////		Map<String, Integer> mapGroupSubpopCount = new HashMap();
////		while(itVarset.hasNext()) {
////			BigDecimal varsetmember = itVarset.next();
////			String subpop = mapVarid2Var.get(varsetmember).getSubpopulation(); 
////			Integer subcount = mapGroupSubpopCount.get(subpop);
////			if(subcount==null) {
////				Integer count = 1;
////				mapGroupSubpopCount.put(subpop, count);
////			} else {
////				mapGroupSubpopCount.remove(subpop);
////				mapGroupSubpopCount.put(subpop, subcount+1) ;
////			}
////		}
////		
////		
////		
////		// create name
////		Iterator<String> itSub = new TreeSet(mapGroupSubpopCount.keySet()).iterator();
////		StringBuffer buff = new StringBuffer();
////		buff.append("GROUP " + mapVarid2Var.get(group).getName() + " :"  );
////		while(itSub.hasNext()) {
////			String subpop = itSub.next();
////			 buff.append(subpop +" " +  mapGroupSubpopCount.get(subpop));
////			 if(itSub.hasNext()) buff.append(":");
////		}
////		mapGroup2Name.put(group,buff.toString());
////		
////		AppContext.debug("group " + group + ": " +  mapGroup2Name.get(group) + " has size " + mapGroup2Varset.get(group).size() );
////	}
////	
////	
////	
////	AppContext.startTimer();
////	
//////	
//////	if(topN>0) {
//////		
//////		if(limitVarIds!=null && !limitVarIds.isEmpty())
//////			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN, limitVarIds);
//////		else	
//////			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN);
//////		
//////		AppContext.debug(mismatches.size() + " mismatch pairs");
//////		
//////		// get varieties in topN
//////		java.util.Iterator<Snps2VarsCountmismatch>  itdist = mismatches.iterator();		
//////		Set topVars =new java.util.HashSet();
//////		while(itdist.hasNext())
//////		{
//////			Snps2VarsCountmismatch dist3k = itdist.next();
//////			topVars.add( dist3k.getVar1());
//////			topVars.add( dist3k.getVar2());
//////		}
//////		mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topVars);
//////		AppContext.resetTimer(" topN distance calc");
//////		
//////	}
//////	else {
//////		if(limitVarIds!=null && !limitVarIds.isEmpty())
//////			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), limitVarIds);
//////		else
//////			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end));
//////		
//////		
//////		
//////		AppContext.resetTimer(" all distance calc");
//////	}
////	
//////	int snps = -1;
//////	//List snps = null;
//////	snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "SnpcoreRefposindexDAO") ; 
//////	if(isCore) {
//////		//snpcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService, "MvCoreSnpsDAO") ; 
//////		//snps = snpcoreallvarsposService.getSNPs(chr, start, end, null ).size();
//////		
//////		snps = snpstringallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KCORESNP  ).size();
//////		
//////		 
//////	 }
//////	 else {
//////		snps = snpstringallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KALLSNP   ).size();
//////		 //snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
//////		 // snps = snpallvarsposService.getSNPs(chr, start, end ,null ).size();
//////	 }
////	 
////	 int snps = snpstrdata.getStrRef().length();
////	 if(snps==0) return new String[] {"", "0","0"};
////	
////	//germplasms
////	 
////
////	AppContext.debug(mismatches.size() + " mismatch var pairs");
////	AppContext.debug(setVarietyToGroupPairs.size() + " mismatch var-group pairs");
////	mismatches.addAll(setVarietyToGroupPairs );
////	
////	
//////	java.util.Map<BigDecimal, Integer> mapName2Row = new java.util.HashMap<BigDecimal, Integer>();
//////
//////	Set<BigDecimal> setWithMismatch = new java.util.TreeSet();
//////	
//////	List listMismatchGrouped = new ArrayList();
//////	
//////	//java.util.Map<String, BigDecimal> mapPairMismatch = new java.util.HashMap<String, BigDecimal>();
//////	java.util.Iterator<Snps2VarsCountmismatch>  itdist = mismatches.iterator();		
//////	while(itdist.hasNext())
//////	{
//////		
//////		Snps2VarsCountmismatch dist3k = itdist.next();
//////		//mapPairMismatch.put( dist3k.getVar1() + ":" + dist3k.getVar2(), dist3k.getMismatch());
//////		
//////		BigDecimal node1 = dist3k.getVar1();
//////		BigDecimal node2 = dist3k.getVar2();
//////		BigDecimal var1group = mapVar2Group.get(node1);
//////		BigDecimal var2group = mapVar2Group.get(node2);
//////		if(var1group!=null) node1 = var1group;
//////		if(var2group!=null) node2 = var2group;
//////		
//////		if(var1group!=null || var2group!=null) {
//////			listMismatchGrouped.add( new Snps2VarsCountmismatchImpl(node1,node2, dist3k.getMismatch()) );
//////		} else
//////			listMismatchGrouped.add(dist3k );
//////		
//////		setWithMismatch.add(  node1 );
//////		setWithMismatch.add(  node2 );
//////		
//////		//setWithMismatch.add( dist3k.getVar1());
//////		//setWithMismatch.add( dist3k.getVar2());
//////	}
//////	
//////	mismatches = listMismatchGrouped;
//////	
////	
////	Set setNodes = new TreeSet();
////	
////	//setNodes.addAll( setVarietyDontGroup );
////	setNodes.addAll( setVarietyNotGrouped );
////	setNodes.addAll( mapGroup2Varset.keySet() );
////	
////	AppContext.debug(setVarietyNotGrouped.size() + " varieties not grouped, " + setVarietyGrouped.size() + " grouped," +  mapGroup2Varset.keySet().size() + " groups, " + setNodes.size() + " treenodes");
////	
////	BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix( setNodes.size() );
////
////	varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
////	Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
////	
////	Map<BigDecimal,Integer> mapVarid2Row = new HashMap();
////	
////	int i=0;
////	Iterator<BigDecimal> itgerm = setNodes.iterator();
////	while(itgerm.hasNext()) {
////		BigDecimal varid = itgerm.next();
////		mapVarid2Row.put(varid , i);
////		symdistmatrix.setIdentifier( i, "varid_" + varid );
////		i++;
////	}		
////
////	AppContext.debug("symdistmatrix done");
////	
////	double distscale = 1.0; 
////	
////	java.util.Iterator<Snps2VarsCountmismatch>  itdist2 = mismatches.iterator();		
////	while(itdist2.hasNext())
////	{
////		Snps2VarsCountmismatch dist3k = itdist2.next();
////		double dist =0 ;
////		if(snps > dist3k.getMismatch().intValue())
////			dist =   dist3k.getMismatch().intValue()*distscale /( snps -  dist3k.getMismatch().intValue() );
////		else
////			dist = AppContext.getMaxPhylodistance();
////		
////		try {
////			
////			if(  mapVarid2Row.get(dist3k.getVar1()).equals(  mapVarid2Row.get(dist3k.getVar2()) ) ) {
////				//AppContext.debug( mapName2Row.get(dist3k.getVar1()) + ": " +  mapVarid2Variety.get(dist3k.getVar1()) + "\t" +  mapName2Row.get(dist3k.getVar2()) + ": " +    mapVarid2Variety.get(dist3k.getVar2())  + "  -- " + dist);
////				dist = 0.0;
////			}
////			
////			symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar1()) , mapVarid2Row.get(dist3k.getVar2()) , dist );
////			symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar2()) , mapVarid2Row.get(dist3k.getVar1()),  dist );
////			
////		} catch(Exception ex) {
////			
////			AppContext.debug( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t" + mapVarid2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +  mapVarid2Row.get(dist3k.getVar2()) + "\t" + dist );
////		
////		}
////	}
////	
////	AppContext.debug(symdistmatrix.getSize() + " symdistmatrix ready");
////	
////	try {
////		
////		TreeConstructor tree = new  TreeConstructor(symdistmatrix,
////			org.biojava3.phylo.TreeType.NJ ,
////			org.biojava3.phylo.TreeConstructionAlgorithm.PID ,
////		//	null);
////			new org.biojava3.phylo.ProgessListenerStub());
////			tree.process();
////
////			AppContext.debug("process done");
////		String newick = tree.getNewickString(false, true);
////		
////		
////		Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
////		
////		//AppContext.debug(newick);
////		Iterator<BigDecimal> itgerm2 = setNodes.iterator();
////		while(itgerm2.hasNext()) {
////			BigDecimal c = itgerm2.next();
////		
////			Variety var = mapId2Variety.get(c);
////			
////			String nodename = mapGroup2Name.get(var.getVarietyId());
////			if(nodename==null) {
////				String subpop = "";
////				if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
////				String irisid = "";
////				if( var.getIrisId()!=null) irisid=var.getIrisId();
////				//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
////				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
////			} else {
////				newick = newick.replace("varid_" + c + ":", nodename.replace("::", "_").replace(":","_") + ":");
////			}
////		
////		}
////		//AppContext.debug(newick);
////		
////		return new Object[] {newick, Integer.toString(symdistmatrix.getSize()), Integer.toString( mismatches.size()) , mapGroup2Varset, mapGroup2Name};
////		
////		
////	} catch(Exception ex)
////	{
////		ex.printStackTrace();
////	}
////	
////	return null;
////	
////}
////		
////	@Override
////	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile)
////	{
////
////		    mapVariety2PhyloOrder = new HashMap<BigDecimal,Integer>(); 
////
////		    String treefilename = AppContext.getTempDir() + "/" + tmpfile + "newick";
////		        File treefile = new File( treefilename );
////		        PhylogenyParser parser = null;
////		        try {
////		            parser = ParserUtils.createParserDependingOnFileType( treefile, true );
////		        	//parser = ParserUtils.createParserDependingOnSuffix( treefilename, false );
////
////		            Phylogeny[] phys = null;
////
////		            phys = PhylogenyMethods.readPhylogenies( parser, treefile );
////
////		        
////			        AppContext.debug("Newick postorder listing:");
////			        Map<String,Variety> varname2var = varietyfacade.getMapVarname2Variety();
////			        Map<String,Variety> irisid2var = varietyfacade.getIrisId2Variety();
////			     
////			        int leafcount = 0;
////			        for(int iphy=0; iphy<phys.length; iphy++)
////			        {
////				        for(PhylogenyNodeIterator it = phys[iphy].iteratorPostorder(); it.hasNext(); ) {
////				        	PhylogenyNode node = it.next();
////				        	if(node.isExternal()) {
////				        		
////				        		String[] nodenames = node.getName().split("\\|");
////				        		if(nodenames.length!=3) AppContext.debug("Invalid nodename " +  node.getName());
////				        		
////				        		if(nodenames.length>1 &&  !nodenames[1].isEmpty() ){
////				        			Variety varNode =   irisid2var.get(nodenames[1].replace("_"," ").toUpperCase() );  //varietyfacade.getGermplasmByIrisId(nodenames[1].replace("_"," "));
////				        			if(varNode!=null) {
////				        				mapVariety2PhyloOrder.put( varNode.getVarietyId() , leafcount);
////				        				leafcount++; 
////				        			} else
////				        			{
////				        				AppContext.debug("cant resolve irisid " + nodenames[1]);
////				        			}				        			
////				        		}
////				        		else if(!nodenames[0].isEmpty())
////				        		{
////				        			Variety varNode =  varname2var.get(nodenames[0].replace("_"," ").replace("//",""));
////				        			if(varNode!=null) {
////				        				mapVariety2PhyloOrder.put(  varNode.getVarietyId() , leafcount);
////				        				leafcount++;
////				        			} else
////				        			{
////				        				AppContext.debug("cant resolve variety name " + nodenames[0] );
////				        			}
////				        		}
////				        	}
////				        }
////			        }
////			        
////			        // Display of the tree(s) with Archaeopteryx.													
////			       // Archaeopteryx.createApplication( phys );
////	
////		        }
////		        
////		        catch ( final IOException e ) {
////		            e.printStackTrace();
////		            AppContext.debug(e.getMessage());
////		            throw new RuntimeException("newick parse error");
////		        }
////		    
////		    
////		    AppContext.debug( "mapVariety2PhyloOrder.size()=" + mapVariety2PhyloOrder.size());
////		    
////			return mapVariety2PhyloOrder;
////
////	}
////
////	
////// ********************************************* Methods to query SNPs as SNPString *********************************************************************************	
////	
////	
////	@Override
////	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start, Integer end, Integer chr) {
////		
////		listSNPAllVarsMismatches =  getSNPsString(chr, new BigDecimal(start), new BigDecimal(end),  null);
////		return listSNPAllVarsMismatches;
////		
////	}
////
////	@Override
////	public List getSNPStringInAllVarieties(Set snpposlist, Integer chr) {
////		// TODO Auto-generated method stub
////		
////		listSNPAllVarsMismatches = getSNPsString(chr, null, null,  snpposlist); 
////		return listSNPAllVarsMismatches;
////	}
////
////	/**
////	 * Contains nucleotide string sequences for each variety based on query criteria
////	 * before mismatch (reference or pairwise) counting
////	 * @author lmansueto
////	 *
////	 */
////	class SNPsStringData {
////		
////		private String  strRef;
////		private Map<BigDecimal,String>  mapVarid2Snpsstr;
////		private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
////		private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
////		private Set<Integer> setSnpInExonTableIdx;
////		
////		public SNPsStringData(String strRef, Map mapVarid2Snpsstr,
////				Map mapVarid2SnpsAllele2str, Map mapIdx2NonsynAlleles,
////				Set setSnpInExonTableIdx) {
////			super();
////			//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
////			//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
////			//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
////			
////			this.strRef = strRef;
////			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
////			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
////			this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
////			this.setSnpInExonTableIdx = setSnpInExonTableIdx;
////		}
////		public String getStrRef() {
////			return strRef;
////		}
////		public Map<BigDecimal,String> getMapVarid2Snpsstr() {
////			return mapVarid2Snpsstr;
////		}
////		public Map getMapVarid2SnpsAllele2str() {
////			return mapVarid2SnpsAllele2str;
////		}
////		public Map getMapIdx2NonsynAlleles() {
////			return mapIdx2NonsynAlleles;
////		}
////		public Set getSetSnpInExonTableIdx() {
////			return setSnpInExonTableIdx;
////		}
////		
////	}
////	
////	class IndelsStringData {
////		
////		private String  strRef;
////		private Map<BigDecimal,String>  mapVarid2Snpsstr;
////		private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
////		
////		public IndelsStringData(String strRef, Map mapVarid2Snpsstr,
////				Map mapVarid2SnpsAllele2str) {
////			super();
////			//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
////			//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
////			//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
////			
////			this.strRef = strRef;
////			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
////			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
////		}
////		public String getStrRef() {
////			return strRef;
////		}
////		public Map<BigDecimal,String> getMapVarid2Snpsstr() {
////			return mapVarid2Snpsstr;
////		}
////		public Map getMapVarid2SnpsAllele2str() {
////			return mapVarid2SnpsAllele2str;
////		}
////		
////	}
////	
////
////@Override		
////public String getIndelAlleleString(IndelsAllvarsPos indelpos) {
////
////	if(indelpos==null) return "";
////	if(indelpos.getDellength()==0) {
////		if(indelpos.getInsString()==null || indelpos.getInsString().trim().isEmpty()) 
////			return "ref";
////		else return indelpos.getInsString();
////	} else {
////		if(indelpos.getInsString()!=null && !indelpos.getInsString().trim().isEmpty() ) {
////			if(indelpos.getInsString().trim().length()==1)
////				return "snp -> " + indelpos.getInsString();
////			else return "del " + indelpos.getDellength() + " -> " + indelpos.getInsString();
////		}
////		else return "del " + indelpos.getDellength();
////	}
////}
////
////@Override
////public String getIndelType(String allele) {
////	if(allele.startsWith("ref")) return "reference";
////	else if(allele.startsWith("snp")) return "snp";
////	else if(allele.startsWith("del")) {
////		if(allele.contains("->"))
////			return "substitution";
////		else return "deletion";
////	}
////	else return "insertion";
////}
////
////	
////	
//////private List<Snps2Vars> getIndelsinVarieties(String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean isCore, Set snpposlimit ) {
////private List<Snps2Vars> getSNPinVarieties(String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean isCore, Set snpposlimit ) {
////		
////	
////		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
////		Map<String,Variety> mapVarname2Variety = varietyfacade.getMapVarname2Variety();
////		limitVarIds = new LinkedHashSet();
////		BigDecimal varid1= mapVarname2Variety.get(var1.toUpperCase()).getVarietyId() ; 
////		BigDecimal varid2= mapVarname2Variety.get(var2.toUpperCase()).getVarietyId() ;
////		limitVarIds.add( varid1 );
////		limitVarIds.add( varid2 );
////		Integer chr = Integer.valueOf(chromosome.toLowerCase().replace("chr","").trim() );
////	
////
////		List snplist = null; 
////		List localsnpposlist = null;
////		List localindelposlist = null; 
////		
////
////		if(this.includeSNP) {
////		
////			SNPsStringData snpstringdata = null;
////			if(startPos==null || endPos==null)
////				snpstringdata = getSNPsStringData( chr ,null, null, snpposlimit );
////			else
////				snpstringdata = getSNPsStringData( chr , new BigDecimal(startPos), new BigDecimal(endPos), null );
////			
////			String var1all1str = (String)snpstringdata.getMapVarid2Snpsstr().get(varid1);
////			String var2all1str = (String)snpstringdata.getMapVarid2Snpsstr().get(varid2);
////			Map<Integer,Character> mapPos1idx2Allele2 = (Map)snpstringdata.getMapVarid2SnpsAllele2str().get(var1);
////			Map<Integer,Character> mapPos2idx2Allele2 = (Map)snpstringdata.getMapVarid2SnpsAllele2str().get(var2);
////			//Map<Integer,Set<Character>> mapIdx2NonsynAlleles  = (Map)snpstringdata.getMapIdx2NonsynAlleles();
////			Map<Integer,Set<Character>> mapIndex2NonsynAlleles  = (Map<Integer,Set<Character>>)snpstringdata.getMapIdx2NonsynAlleles();
////			Set setSnpInExonTableIdx = snpstringdata.getSetSnpInExonTableIdx();
////			
////			String refstr = snpstringdata.getStrRef();
////			
////			Set setNonsynIdx = new HashSet();
////			
////			//AppContext.debug("var1all1str=" + var1all1str);
////		    //AppContext.debug("var2all1str=" + var2all1str);
////			
////			snplist = new ArrayList();
////			localsnpposlist = this.snpsposlist;
////			
////			for(int iStr=0; iStr<refstr.length(); iStr++) {
////				
////				SnpsAllvarsPos snp = getSnpsposlist().get(iStr);
////	
////				boolean inExon = false; 
////				if(setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr)) inExon=true;
////				
////				Boolean isNonSyn[] = new Boolean[2];
////				isNonSyn[0]=true;	
////				isNonSyn[1]=true;
////				
////				Character var1allele2 = null;
////				if(mapPos1idx2Allele2!=null) var1allele2 =  mapPos1idx2Allele2.get(iStr);
////				Character var2allele2 = null;
////				if(mapPos2idx2Allele2!=null) var2allele2 = mapPos2idx2Allele2.get(iStr);
////				Set setNonsyns = null;
////				if(mapIndex2NonsynAlleles!=null) setNonsyns = mapIndex2NonsynAlleles.get(iStr);
////	
////				
////				double misCount = countVarpairMismatchNuc(var1all1str.charAt(iStr), var2all1str.charAt(iStr), false,
////						var1allele2,var2allele2,
////						setNonsyns , inExon, isNonSyn, isNonsynOnly);
////				
////				if(isNonSyn[0] || isNonSyn[1]) setNonsynIdx.add(iStr);
////				
////				
////				if(!isMismatchOnly || misCount>0 )
////					//Snps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
////					//		BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
////					//		String var1nuc, String var2nuc, String var1nuc2, String var2nuc2)
////					
////					/* nps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
////							BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
////							String var1nuc, String var2nuc, String var1nuc2, String var2nuc2,
////							Boolean var1Nonsyn, Boolean var2Nonsyn) { */
////	
////					snplist.add(   new Snps2VarsImpl(AppContext.convertRegion2Snpfeatureid(chr,snp.getPos()), varid1, varid2,
////						chr, snp.getPos(), snp.getRefnuc(), 
////						var1all1str.charAt(iStr) , var2all1str.charAt(iStr), 
////						var1allele2 , var2allele2 , isNonSyn[0], isNonSyn[1] ) );
////			}
////		}
////		
////		List indellist = null;
////		
////		if(includeIndel) {
////
////			indelsAllvarsPosDAO = (IndelsAllvarsPosDAO)AppContext.checkBean(indelsAllvarsPosDAO, "IndelsAllvarsPosDAO");
////			indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean(  indelsAllvarsDAO , "IndelCallDAO");
////			
////			IndelStringDAOImpl indelstringdao = new IndelStringDAOImpl(indelsAllvarsPosDAO, indelsAllvarsDAO, isMismatchOnly);
////			Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring=null;
////			if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
////				
////				AppContext.resetTimer("using readIndelString1 start");
////				mapVar2Indelstring=indelstringdao.readSNPString(limitVarIds, chr, startPos.intValue(), endPos.intValue());
////				AppContext.resetTimer("using readIndelString1 end");
////		
////			}
////			else {
////				AppContext.resetTimer("using readIndelString2 start");
////				mapVar2Indelstring=indelstringdao.readSNPString(chr, startPos.intValue(), endPos.intValue());
////				AppContext.resetTimer("using readIndelString2 end");
////			}
////			
////			//indelstringdao.get
////			//listVariants.addAll( indelstringdao.getListResult());
////			
////			
////			//mapIndelVariety2Mismatch = indelstringdao.getMapVariety2Mismatch();
////			//mapIndelVariety2Order = indelstringdao.getMapVariety2Order();
////			localindelposlist = indelstringdao.getListPos();
////			
////			this.mapIndelId2Indel =  indelstringdao.getMapAlleleId2Indel();
////			this.mapIndelIdx2Pos = indelstringdao.getMapIdx2Pos();
////			
////			IndelsStringAllvars indelvar1 = mapVar2Indelstring.get( varid1 );
////			IndelsStringAllvars indelvar2 = mapVar2Indelstring.get( varid2 );
////			
////			indellist = new ArrayList();
////			
////			
////			Iterator<SnpsAllvarsPos> itIndelpos =  localindelposlist.iterator();
////			while(itIndelpos.hasNext()) {
////				SnpsAllvarsPos indelpos = itIndelpos.next();
////				
////				/*
////				if(indelpos==null) {
////					AppContext.debug("indelpos==null)");
////					continue;
////				}
////				
////				if(indelvar1==null) {
////					AppContext.debug("indelvar1==null) at pos=" + indelpos.getPos());
////					continue;
////				}
////				*/
////				
////				BigDecimal var1allele1 =  indelvar1.getAllele1(indelpos.getPos());
////				BigDecimal var1allele2 = indelvar1.getAllele2(indelpos.getPos());
////				BigDecimal var2allele1 =  indelvar2.getAllele1(indelpos.getPos());
////				BigDecimal var2allele2 = indelvar2.getAllele2(indelpos.getPos());
////				
////				String v1ind1="";
////				String v1ind2="";
////				String v2ind1="";
////				String v2ind2="";
////				
////				if(var1allele1!=null) v1ind1 = getIndelAlleleString( mapIndelId2Indel.get(var1allele1));
////				if(var1allele2!=null  && !var1allele2.equals(var1allele1) ) v1ind2 = getIndelAlleleString( mapIndelId2Indel.get(var1allele2));
////				if(var2allele1!=null) v2ind1 = getIndelAlleleString( mapIndelId2Indel.get(var2allele1));
////				if(var2allele2!=null && !var2allele2.equals(var2allele1) ) v2ind2 = getIndelAlleleString( mapIndelId2Indel.get(var2allele2));
////				
////				
////				double misCount = countVarpairMismatchIndel( mapIndelId2Indel.get(var1allele1), mapIndelId2Indel.get(var2allele1), false,
////						mapIndelId2Indel.get(var1allele2) ,mapIndelId2Indel.get(var2allele2));
////				
////				
////				if(!isMismatchOnly || misCount>0 ) {
////					indellist.add(   new Snps2VarsImpl(AppContext.convertRegion2Indelalleleid(chr,indelpos.getPos()), varid1, varid2,
////							chr, indelpos.getPos(), "", 
////							v1ind1  ,v2ind1 , 
////							v1ind2 , v2ind2 , true, true ) );
////				}
////			}
////		}
////		
////		List mergedList = null;
////		
////		if(includeSNP && includeIndel) {
////			
////			//merge listVariants
////			mergedList = new ArrayList();
////			mergedList.addAll( snplist );
////			mergedList.addAll( indellist );
////			
////			snpsposlist = new ArrayList();
////			snpsposlist.addAll(localsnpposlist);
////			snpsposlist.addAll(localindelposlist);
////			
//////			// varwithsnps
//////			Set hasSnps = new HashSet();
//////			Iterator<SnpsStringAllvars> itSnpString = listVariantsSnps.iterator();
//////			Map<BigDecimal, SnpsStringAllvars> mapVar2SnpStringAllvars = new HashMap();
//////			while(itSnpString.hasNext()) {
//////				SnpsStringAllvars snpstring = itSnpString.next();
//////				mapVar2SnpStringAllvars.put(snpstring.getVar() , snpstring);
//////				hasSnps.add(snpstring.getVar());
//////			}
//////			
//////			// varswithindels
//////			Set hasIndels = new HashSet();
//////			itSnpString = listVariantsIndels.iterator();
//////			while(itSnpString.hasNext()) {
//////				IndelsStringAllvars indelstring = (IndelsStringAllvars)itSnpString.next();
//////				BigDecimal var = indelstring.getVar();
//////				indelstring.delegateSnpString( mapVar2SnpStringAllvars.get(var) );
//////				/*
//////				SnpsStringAllvars snpsstring =  mapVar2SnpStringAllvars.get(var);
//////				if(snpsstring!=null) {
//////					
//////					indelstring.setVarnuc( snpsstring.getVarnuc() );
//////					indelstring.setMapPosIdx2Allele2( snpsstring.getMapPosIdx2Allele2());
//////					indelstring.setMismatch( BigDecimal.valueOf(snpsstring.getMismatch().longValue() +  indelstring.getMismatch().longValue() ));
//////					indelstring.setNonsynIdxset( snpsstring.getNonsynIdxset() );
//////				}
//////				*/
//////				
//////				hasIndels.add(var);
//////				listMergedVariants.add(indelstring);
//////			}
//////			
//////			// has snps only
//////			Set hasSnpsOnly = new HashSet( hasSnps );
//////			hasSnpsOnly.removeAll( hasIndels );
//////			itSnpString = hasSnpsOnly.iterator();
//////			while(itSnpString.hasNext()) {
//////				listMergedVariants.add( mapVar2SnpStringAllvars.get(itSnpString.next() )  );
//////			}			
//////			
//////		 	Collections.sort(listMergedVariants, new SnpsStringAllvarsImplSorter());
//////		 	
//////			
//////			// merge snps and indel positions
//////			Map mapSNPVariety2Mismatch = this.mapVariety2Mismatch;
//////			Map mapSNPVariety2Order =  this.mapVariety2Order;
//////			List listSNPsnpsposlist = new ArrayList();
//////			listSNPsnpsposlist.addAll( this.snpsposlist );
//////			
//////			// merge pos
//////			Set setMergedAllvarsPos = new TreeSet(new SnpsAllvarsPosComparator());
//////			setMergedAllvarsPos.addAll( listIndelsnpsposlist );
//////			setMergedAllvarsPos.addAll( this.snpsposlist );
//////			this.snpsposlist = new ArrayList();
//////			this.snpsposlist.addAll(setMergedAllvarsPos);
//////
//////			
//////			// update mismatches and order
//////			Map mapMergedVar2Mismatch = new HashMap();
//////			Map mapMergedVar2Order = new HashMap();
//////			Iterator<SnpsStringAllvars> itSnpstring = listMergedVariants.iterator();
//////			int ordercnt = 0;
//////			while(itSnpstring.hasNext()) {
//////				SnpsStringAllvars snpstring = itSnpstring.next();
//////				mapMergedVar2Mismatch.put(snpstring.getVar() , snpstring.getMismatch() );
//////				mapMergedVar2Order.put(snpstring.getVar() , ordercnt);
//////				ordercnt++;
//////			}
//////			
//////			 this.mapVariety2Mismatch = mapMergedVar2Mismatch;
//////			 this.mapVariety2Order = mapMergedVar2Order;
//////			
//////
//////			 
//////			 Map<BigDecimal, Integer> mapSNPPos2SnpIdx = new HashMap();
//////			 Iterator<SnpsAllvarsPos> itSNPosList = listSNPsnpsposlist.iterator();
//////			 int idxcnt = 0;
//////			 while(itSNPosList.hasNext()) {
//////				 SnpsAllvarsPos snppos = itSNPosList.next();
//////				 mapSNPPos2SnpIdx.put(snppos.getPos(), idxcnt);
//////				 idxcnt++;
//////			 }
//////			 
//////			 this.mapMergedIdx2SnpIdx = new HashMap();
//////			// merge column indexing
//////			 Map<Integer, BigDecimal> mapMergedIndex = new HashMap(); 
//////			 Iterator<SnpsAllvarsPos> itSnppos = snpsposlist.iterator();
//////			 idxcnt=0;
//////			 while(itSnppos.hasNext()) {
//////				 SnpsAllvarsPos posallvars = itSnppos.next();
//////				 mapMergedIndex.put( idxcnt, posallvars.getPos() );
//////				 if(mapSNPPos2SnpIdx.containsKey( posallvars.getPos()))
//////					 mapMergedIdx2SnpIdx.put(idxcnt, mapSNPPos2SnpIdx.get( posallvars.getPos() ) );
//////				 idxcnt++;
//////			 }
//////			 this.mapMergedIdx2Pos =  mapMergedIndex;
////			
////			
////		} else if(includeSNP) {
//////			listMergedVariants = listVariantsSnps;
////			
////			mergedList = snplist;
////			snpsposlist = localsnpposlist;
////			
////		} else if(includeIndel) {
//////			listMergedVariants = listVariantsIndels;
//////			this.mapVariety2Mismatch = mapIndelVariety2Mismatch;
//////			this.mapVariety2Order = mapIndelVariety2Order;
//////			this.snpsposlist = listIndelsnpsposlist;
////			
////			mergedList = indellist;
////			snpsposlist = localindelposlist;
////		}
////
////	
////		Collections.sort(mergedList , new Snps2VarsSorter());
////		Collections.sort(snpsposlist , new SnpsAllvarsPosSorter());
////		
////		return mergedList;
////		//snpstringdata
////		//snpstringdata
////		//this.countVarpairMismatch(var1, var2, var1isref, var1allele2str, var2allele2str, mapIdx2NonsynAlleles, setSnpInExonTableIdx, setNonsynIdx, isCore)
////	}
////
////
////	class Snps2VarsSorter implements Comparator {
////
////		@Override
////		public int compare(Object o1, Object o2) {
////			// TODO Auto-generated method stub
////			return ((Snps2Vars)o1).getPos().compareTo(  ((Snps2Vars)o2).getPos() );
////		}
////		
////	}
////
////	class SnpsAllvarsPosSorter implements Comparator {
////
////		@Override
////		public int compare(Object o1, Object o2) {
////			// TODO Auto-generated method stub
////			return ((SnpsAllvarsPos)o1).getPos().compareTo(  ((SnpsAllvarsPos)o2).getPos() );
////		}
////	}
////	
////
////	@Override
////	public VariantTable queryGenotype(VariantTable table, GenotypeQueryParams params ) throws Exception {
////		genotypeservice = (VarietiesGenotypeService)AppContext.checkBean(genotypeservice , "VarietiesGenotypeService");
////		return genotypeservice.fillVariantTable( table , params);
////	}
////
////	private List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start,
////			BigDecimal end, Set setPositions) { 
////		
////		
////		listSNPAllVarsMismatches=null;
////		mapOrder2Variety=null; // 0-indexed
////		mapVariety2Order=null;  // 0-indexed
////		mapVariety2Mismatch=null;
////		mapVariety2PhyloOrder=null;
////		mapIndelId2Indel=null;
////		mapIndelIdx2Pos=null;
////		mapMergedIdx2SnpIdx=null;
//////		private Map<Integer, BigDecimal> mapSNPIdx2Pos;
////		mapMergedIdx2Pos=null;
////		snpsposlist=null;
////		mapPos2Allele = new TreeMap();
////		
////		
////		List listVariantsSnps = null;
////		List listVariantsIndels = null;
////		List listMergedVariants = new ArrayList();
////		
////		
////		if(this.includeSNP) {
////			//listVariants.addAll( _getSNPsString( chr,  start,  end, setPositions));
////			listVariantsSnps = _getSNPsString( chr,  start,  end, setPositions);
////			
////			// get pos2allele
////
////			Map<BigDecimal,Integer> mapPos2Idx = new HashMap();
////			Iterator<SnpsAllvarsPos> itSnpsstr =  snpsposlist.iterator();
////			int idxcount = 0;
////			while(itSnpsstr.hasNext()) {
////				SnpsAllvarsPos snpspos = itSnpsstr.next(); 
////				Set setalleles = mapPos2Allele.get(snpspos.getPos());
////				if(setalleles==null) {
////					setalleles = new TreeSet();
////					mapPos2Allele.put(snpspos.getPos(), setalleles);
////				}
////				setalleles.add( snpspos.getRefnuc() );
////				mapPos2Idx.put(snpspos.getPos() ,idxcount);
////				idxcount++;
////			}
////			Set snpposcheck = new HashSet(mapPos2Allele.keySet());
////			Iterator<SnpsStringAllvars> itsnpsstr =  listVariantsSnps.iterator();
////			while(itsnpsstr.hasNext()) {
////				SnpsStringAllvars snpstr = itsnpsstr.next();
////				Map pos2Allele2 = snpstr.getMapPosIdx2Allele2();
////				String nucstr = snpstr.getVarnuc();
////				Set removePos = new HashSet();
////				Iterator<BigDecimal> itPoscheck= snpposcheck.iterator();
////				while(itPoscheck.hasNext()) {
////					BigDecimal pos = itPoscheck.next();
////					int idx=mapPos2Idx.get(pos);
////					String nuc = nucstr.substring(idx, idx+1);
////					if(pos2Allele2!=null) {
////						Character allele2 = (Character)pos2Allele2.get(pos);
////						if(allele2!=null) nuc+="/" + allele2;
////					}
////					Set setposalleles = mapPos2Allele.get(pos);
////					if(nuc.equals("0") || nuc.equals("*") || nuc.equals(".")) {}  
////					else setposalleles.add(nuc);
////					if(setposalleles.size()==3) removePos.add(pos);
////				}
////				snpposcheck.removeAll(removePos);
////				if(snpposcheck.isEmpty()) break;
////			}
////						
////		}
////		
////		Map mapIndelVariety2Mismatch = null;
////		Map mapIndelVariety2Order =  null;
////		List listIndelsnpsposlist =  null;
////		if(this.includeIndel) {
////
////			
////			
////			indelsAllvarsPosDAO = (IndelsAllvarsPosDAO)AppContext.checkBean(indelsAllvarsPosDAO, "IndelsAllvarsPosDAO");
////			indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean(  indelsAllvarsDAO , "IndelCallDAO");
////			
////			IndelStringDAOImpl indelstringdao = new IndelStringDAOImpl(indelsAllvarsPosDAO, indelsAllvarsDAO, isMismatchOnly);
////			Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring=null;
////			
////			if(setPositions==null) {
////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
////					
////					AppContext.resetTimer("using readIndelString1 start");
////					mapVar2Indelstring=indelstringdao.readSNPString(limitVarIds, chr, start.intValue(), end.intValue());
////					AppContext.resetTimer("using readIndelString1 end");
////	
////				}
////				else {
////					AppContext.resetTimer("using readIndelString2 start");
////					mapVar2Indelstring=indelstringdao.readSNPString(chr, start.intValue(), end.intValue());
////					AppContext.resetTimer("using readIndelString2 end");
////				}
////			} else {
////				
////				int indxs[] = new int[setPositions.size()];
////				int indxscount = 0;
////				Iterator itSnppos =setPositions.iterator();
////				while(itSnppos.hasNext()) {
////					BigDecimal snppos = (BigDecimal)itSnppos.next(); 
////					indxs[indxscount] =  snppos.intValue();
////					indxscount++;
////				}
////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
////					
////					AppContext.resetTimer("using readIndelString3 start");
////					mapVar2Indelstring=indelstringdao.readSNPString(limitVarIds, chr, indxs);
////					AppContext.resetTimer("using readIndelString3 end");
////	
////				}
////				else {
////					AppContext.resetTimer("using readIndelString4 start");
////					mapVar2Indelstring=indelstringdao.readSNPString(chr, indxs);
////					AppContext.resetTimer("using readIndelString4 end");
////				}
////			}
////			
////			//indelstringdao.get
////			//listVariants.addAll( indelstringdao.getListResult());
////			listVariantsIndels = indelstringdao.getListResult();
////			
////			mapIndelVariety2Mismatch = indelstringdao.getMapVariety2Mismatch();
////			mapIndelVariety2Order = indelstringdao.getMapVariety2Order();
////			listIndelsnpsposlist = indelstringdao.getListPos();
////			this.mapIndelId2Indel =  indelstringdao.getMapAlleleId2Indel();
////			this.mapIndelIdx2Pos = indelstringdao.getMapIdx2Pos();
////			
////			// get pos2allele
////
////			Iterator<IndelsAllvarsPos> itIndels =  mapIndelId2Indel.values().iterator();
////			while(itIndels.hasNext()) {
////				IndelsAllvarsPos indelpos = itIndels.next(); 
////				Set setalleles = mapPos2Allele.get(indelpos.getPos());
////				if(setalleles==null) {
////					setalleles = new TreeSet();
////					mapPos2Allele.put(indelpos.getPos(), setalleles);
////				}
////				setalleles.add( getIndelAlleleString( indelpos) );
////			}
////			
////		}
////		
////		if(includeSNP && includeIndel) {
////			
////			//merge listVariants
////			listMergedVariants = new ArrayList();
////			
////			// varwithsnps
////			Set hasSnps = new HashSet();
////			Iterator<SnpsStringAllvars> itSnpString = listVariantsSnps.iterator();
////			Map<BigDecimal, SnpsStringAllvars> mapVar2SnpStringAllvars = new HashMap();
////			while(itSnpString.hasNext()) {
////				SnpsStringAllvars snpstring = itSnpString.next();
////				mapVar2SnpStringAllvars.put(snpstring.getVar() , snpstring);
////				hasSnps.add(snpstring.getVar());
////			}
////			
////			// varswithindels
////			Set hasIndels = new HashSet();
////			itSnpString = listVariantsIndels.iterator();
////			while(itSnpString.hasNext()) {
////				IndelsStringAllvars indelstring = (IndelsStringAllvars)itSnpString.next();
////				
////				//if( this.isMismatchOnly && indelstring.getMismatch().longValue()==0) continue;
////				
////				BigDecimal var = indelstring.getVar();
////				indelstring.delegateSnpString( mapVar2SnpStringAllvars.get(var) );
////				/*
////				SnpsStringAllvars snpsstring =  mapVar2SnpStringAllvars.get(var);
////				if(snpsstring!=null) {
////					
////					indelstring.setVarnuc( snpsstring.getVarnuc() );
////					indelstring.setMapPosIdx2Allele2( snpsstring.getMapPosIdx2Allele2());
////					indelstring.setMismatch( BigDecimal.valueOf(snpsstring.getMismatch().longValue() +  indelstring.getMismatch().longValue() ));
////					indelstring.setNonsynIdxset( snpsstring.getNonsynIdxset() );
////				}
////				*/
////				
////				hasIndels.add(var);
////				listMergedVariants.add(indelstring);
////			}
////			
////			// has snps only
////			Set hasSnpsOnly = new HashSet( hasSnps );
////			hasSnpsOnly.removeAll( hasIndels );
////			itSnpString = hasSnpsOnly.iterator();
////			while(itSnpString.hasNext()) {
////				listMergedVariants.add( mapVar2SnpStringAllvars.get(itSnpString.next() )  );
////			}			
////			
////		 	Collections.sort(listMergedVariants, new SnpsStringAllvarsImplSorter());
////		 	
////			
////			// merge snps and indel positions
////			Map mapSNPVariety2Mismatch = this.mapVariety2Mismatch;
////			Map mapSNPVariety2Order =  this.mapVariety2Order;
////			List listSNPsnpsposlist = new ArrayList();
////			listSNPsnpsposlist.addAll( this.snpsposlist );
////			
////			// merge pos
////			Set setMergedAllvarsPos = new TreeSet(new SnpsAllvarsPosComparator());
////			setMergedAllvarsPos.addAll( listIndelsnpsposlist );
////			setMergedAllvarsPos.addAll( listSNPsnpsposlist );
////			this.snpsposlist = new ArrayList();
////			this.snpsposlist.addAll(setMergedAllvarsPos);
////
////			// recount mismatches
////			/*
////			Map mapMergedVar2Mismatch = new HashMap();
////			Iterator<BigDecimal> itVar =  mapSNPVariety2Mismatch.keySet().iterator();
////			while(itVar.hasNext()) {
////				BigDecimal var =itVar.next();
////				Integer misSNP = (Integer)mapSNPVariety2Mismatch.get(var);
////				Integer misIndel = (Integer)mapIndelVariety2Mismatch.get(var);
////				if(misIndel==null)
////					mapMergedVar2Mismatch.put(var, misSNP);
////				else
////					mapMergedVar2Mismatch.put(var, misSNP+misIndel);
////			}
////			
////			// for vars in indels not in snps
////			Set setIndelNotInSNP = new HashSet(mapIndelVariety2Mismatch.keySet());
////			setIndelNotInSNP.removeAll( mapSNPVariety2Mismatch.keySet() );
////			itVar =  setIndelNotInSNP.iterator();
////			while(itVar.hasNext()) {
////				BigDecimal var =itVar.next();
////				mapMergedVar2Mismatch.put(var, mapIndelVariety2Mismatch.get(var));
////			}			
////			 this.mapVariety2Mismatch = mapMergedVar2Mismatch;
////
////
////			*/
////			
////			// update mismatches and order
////			Map mapMergedVar2Mismatch = new HashMap();
////			Map mapMergedVar2Order = new HashMap();
////			Iterator<SnpsStringAllvars> itSnpstring = listMergedVariants.iterator();
////			int ordercnt = 0;
////			while(itSnpstring.hasNext()) {
////				SnpsStringAllvars snpstring = itSnpstring.next();
////				mapMergedVar2Mismatch.put(snpstring.getVar() , snpstring.getMismatch() );
////				mapMergedVar2Order.put(snpstring.getVar() , ordercnt);
////				ordercnt++;
////			}
////			
////			 this.mapVariety2Mismatch = mapMergedVar2Mismatch;
////			 this.mapVariety2Order = mapMergedVar2Order;
////			
////
////			 
////			 Map<BigDecimal, Integer> mapSNPPos2SnpIdx = new HashMap();
////			 Iterator<SnpsAllvarsPos> itSNPosList = listSNPsnpsposlist.iterator();
////			 int idxcnt = 0;
////			 while(itSNPosList.hasNext()) {
////				 SnpsAllvarsPos snppos = itSNPosList.next();
////				 mapSNPPos2SnpIdx.put(snppos.getPos(), idxcnt);
////				 idxcnt++;
////			 }
////			 
////			 this.mapMergedIdx2SnpIdx = new HashMap();
////			// merge column indexing
////			 Map<Integer, BigDecimal> mapMergedIndex = new HashMap(); 
////			 Iterator<SnpsAllvarsPos> itSnppos = snpsposlist.iterator();
////			 idxcnt=0;
////			 while(itSnppos.hasNext()) {
////				 SnpsAllvarsPos posallvars = itSnppos.next();
////				 mapMergedIndex.put( idxcnt, posallvars.getPos() );
////				 if(mapSNPPos2SnpIdx.containsKey( posallvars.getPos()))
////					 mapMergedIdx2SnpIdx.put(idxcnt, mapSNPPos2SnpIdx.get( posallvars.getPos() ) );
////				 idxcnt++;
////			 }
////			 this.mapMergedIdx2Pos =  mapMergedIndex;
////			
////			
////		} else if(includeSNP) {
////			listMergedVariants = listVariantsSnps;
////			
////		} else if(includeIndel) {
////			listMergedVariants = listVariantsIndels;
////			this.mapVariety2Mismatch = mapIndelVariety2Mismatch;
////			this.mapVariety2Order = mapIndelVariety2Order;
////			this.snpsposlist = listIndelsnpsposlist;
////		}
////			
////		
////		return listMergedVariants;
////				
////	}
////	
////	class SnpsAllvarsPosComparator implements Comparator {
////		@Override
////		public int compare(Object o1, Object o2) {
////			// TODO Auto-generated method stub
////			SnpsAllvarsPos pos1 = (SnpsAllvarsPos)o1;
////			SnpsAllvarsPos pos2 = (SnpsAllvarsPos)o2;
////			return pos1.getPos().compareTo(pos2.getPos());
////		}
////	}
////	
////	private List<SnpsStringAllvars> _getSNPsString(Integer chr, BigDecimal start,
////			BigDecimal end, Set setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
////		// TODO Auto-generated method stub
////		
////			SNPsStringData snpstrdata = getSNPsStringData( chr,  start,  end,  setPositions); 
////			if(snpstrdata==null) return new ArrayList();
////		 
////			Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
////		 
////			Set setNonsynIdx=new HashSet();
////			Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
////			
////			Map mapIndex2NonsynAlleles = snpstrdata.getMapIdx2NonsynAlleles();
////			
////		 	Iterator<BigDecimal> itVar = mapVarid2Snpsstr.keySet().iterator();
////			while(itVar.hasNext()) {
////				BigDecimal var = itVar.next();
////				String snpstr = (String)mapVarid2Snpsstr.get(var);
////
////				//countVarpairMismatch(String var1, String var2, boolean var1isref, String var1allele2str, String var2allele2str, 
////				//	Map<Integer,Set<Character>> mapIdx2NonsynAlleles, Set setSnpInExonTableIdx, Set setNonsynIdx, boolean isNonsynOnly);
////
////				Set varNonsynIdx = new HashSet();
////				
////				//AppContext.debug("v1al1= " + snpstrdata.getStrRef());
////				//AppContext.debug("v2al1= " + snpstr);
////				
////
////				
////				double misCount = countVarpairMismatch(snpstrdata.getStrRef(), snpstr, true, null, (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var),  
////						(Map)snpstrdata.getMapIdx2NonsynAlleles(),  snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,  isNonsynOnly );
////				setNonsynIdx.addAll(varNonsynIdx);
////				
////						
////				if(!isMismatchOnly || misCount>0) {
////					
////					/*
////					String strmsg = " 0 allele2 positions "; 
////					if( snpstrdata.getMapVarid2SnpsAllele2str().containsKey(var))
////						strmsg = ((Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var)).size() + " allele2 positions ";
////					if(snpstrdata.getSetSnpInExonTableIdx()!=null)
////						strmsg += snpstrdata.getSetSnpInExonTableIdx().size() + " snps in exon; ";
////					else
////						strmsg += " 0 snps in exon; ";
////					
////					if(snpstrdata.getMapIdx2NonsynAlleles()!=null )
////						strmsg += snpstrdata.getMapIdx2NonsynAlleles().size() +  " indices w/ non-syn alleles; ";
////					else
////						strmsg +=  " 0 indices w/ non-syn alleles; ";
////					
////					if(varNonsynIdx!=null)
////						strmsg += varNonsynIdx.size() + " non-syn indices for var " + var;
////					else
////						strmsg += " 0 non-syn indices for var " + var;
////					
////					AppContext.debug( strmsg );
////					*/
////					
////					sortedVarieties.add( new SnpsStringAllvarsImpl(var,Long.valueOf(chr), snpstr, 
////							BigDecimal.valueOf(misCount) , (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var), varNonsynIdx) );
////				} 
////
////			}
////			
////			List<SnpsStringAllvars> listResult = new ArrayList();
////			// sort included varieties
////			mapVariety2Order = new HashMap();
////			mapVariety2Mismatch = new HashMap();
////			int ordercount = 0;
////			Iterator itSorVars =  sortedVarieties.iterator();
////			while(itSorVars.hasNext()) {
////				SnpsStringAllvars snpstrvar = (SnpsStringAllvars)itSorVars.next();
////				listResult.add( snpstrvar );
////				mapVariety2Order.put(snpstrvar.getVar() ,ordercount);
////				mapVariety2Mismatch.put( snpstrvar.getVar(), snpstrvar.getMismatch().intValue());
////				ordercount++;
////			}
////			
////			
////			AppContext.debug( listResult.size() + " sortedvarieties in list, "  + sortedVarieties.size() + " in set" );
////			
////			return listResult;					
////	}
////	
////	
////	//private List<SnpsStringAllvars> 
////	private SNPsStringData getSNPsStringData(Integer chr, BigDecimal start,
////			BigDecimal end, Set setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
////		// TODO Auto-generated method stub
////
////		
////		
////		// get snp positions/index
////		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "VSnpRefposindexDAO") ;
////		
////		
////		AppContext.resetTimer("getSNPsString start");
////		
////		
////		List listpos = null;
////		if(setPositions!=null && !setPositions.isEmpty()) {
////			listpos = new ArrayList();
////			listpos.addAll(new TreeSet(setPositions));
////
////			if(this.isCore)
////				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(), listpos, SnpcoreRefposindexDAO.TYPE_3KCORESNP);
////			else
////				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(),  listpos, SnpcoreRefposindexDAO.TYPE_3KALLSNP);
////			
////			if(snpsposlist.size()!=listpos.size())
////				AppContext.debug("snpsposlist.size()!=listpos.size():" + snpsposlist.size()+ "!=" + listpos.size());
////				
////
////		}
////		else {
////			if(this.isCore) {
////				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(),  end.intValue(), SnpcoreRefposindexDAO.TYPE_3KCORESNP, -1, -1);
////			}
////			else
////				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),   SnpcoreRefposindexDAO.TYPE_3KALLSNP, -1, -1);
////		}
////		
////		if(snpsposlist.isEmpty()) return new SNPsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
////		
////		
////		
////		Map mapVarid2Var = varietyfacade.getMapId2Variety();
////
////		
////
////		// get allele column indices from start to end positions
////		VSnpRefposindex startpos =  (VSnpRefposindex)snpsposlist.get(0);
////		VSnpRefposindex endpos =  (VSnpRefposindex)snpsposlist.get( snpsposlist.size()-1 );
////
////		// if recount
////		String strRef=null;
////		Map<Float,Map> mapMis2Vars = new TreeMap();
////		
////		int refLength=-1;
////		
////		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPos() +  "-" + endpos.getPos() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
////		
////		
////			
////			int indxs[] = new int[snpsposlist.size()];
////			int indxscount = 0;
////			//Map<Long, Integer> mapPos2Idx = new HashMap();
////			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
////			Map<BigDecimal, Integer> mapSnpid2TableIdx = new HashMap();
////			
////			Iterator itSnppos =snpsposlist.iterator();
////			StringBuffer buffRef = new StringBuffer();
////			
////			while(itSnppos.hasNext()) {
////				VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next(); 
////				buffRef.append( snppos.getRefnuc());
////				indxs[indxscount] =  snppos.getAlleleIndex().intValue();
////				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
////				mapSnpid2TableIdx.put(snppos.getSnpFeatureId(), indxscount);
////				indxscount++;
////			}
////			strRef = buffRef.toString();
////			refLength = strRef.length();
////			
////			
////			String filename="";
////			String filename_allele2="";
////			
////			BigDecimal datatype=null;
////			if(isCore) {
////				filename = AppContext.getFlatfilesDir() + "chr-" + chr + ".txt"; // "3kcorev21-chr-" + chr + ".txt";  //"chr-" + chr + ".txt";
////				filename_allele2 = AppContext.getFlatfilesDir() + "allele2-chr-" + chr + ".txt";
////				datatype = SnpcoreRefposindexDAO.TYPE_3KCORESNP;
////			}
////			else {
////				filename = AppContext.getFlatfilesDir() +  "varsorted_allelestring_chr" + chr + ".txt" ; //allsnp_chr-" + chr + ".txt";
////				datatype = SnpcoreRefposindexDAO.TYPE_3KALLSNP;
////			}
////			
////			Map  mapVarid2Snpsstr = null;
////			Map mapVarid2Snpsstr_allele2 = null;
////			
////			AppContext.resetTimer(" to get position indexes from database..");
////			
////			// get snpstring for each varieties
////			// get allele2 for heterozygous varieties
////			snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
////			snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "SnpsNonsynAllvarsDAO");
////			snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
////			snpstrAllsnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1DAO");
////			snpstrAllsnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2DAO");
////			
////			snpstrCoresnpsAllele1AllvarsDAO =  (SnpsStringDAO)AppContext.checkBean(snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1DAO");
////			snpstrCoresnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(snpstrCoresnpsAllele2AllvarsDAO, "H5SNPCoreAllele2DAO");
////			
////			
////			
////			Set heteroSnps = null;
////			Set nonsynAllele = null;
////			Set inexonSnps = null;
////			
////			// using flatfiles
////			/*
////			if(listpos!=null && !listpos.isEmpty()) {
////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
////					
////					AppContext.resetTimer("using readSNPString1 start");
////					mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  indxs , filename);
////					
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						mapVarid2Snpsstr_allele2= readSNPString(limitVarIds, chr,  indxs , filename_allele2);
////					}
////					else {
////						heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos, datatype);
////					}
////
////					AppContext.resetTimer("using readSNPString1 end");
////
////				}
////				else {
////					AppContext.resetTimer("using readSNPString2 start");
////						mapVarid2Snpsstr = readSNPString(chr,  indxs, filename);
////
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						mapVarid2Snpsstr_allele2=  readSNPString(chr,  indxs, filename_allele2);
////					}
////					else {
////						heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosIn(chr, listpos, limitVarIds, datatype);
////					}
////					AppContext.resetTimer("using readSNPString2 end");
////				}
////				
////				if(isNonsynOnly || isColorByNonsyn) {
////					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
////					inexonSnps = snpsinexonDAO.getSnps(chr, listpos);
////					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
////				}
////				
////			}
////			else {
////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() )
////				{
////					AppContext.resetTimer("using readSNPString3 start");
////					mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
////
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						mapVarid2Snpsstr_allele2 = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename_allele2);
////					}
////					else {
////						heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosBetween(chr, start, end, limitVarIds, datatype);
////					}
////
////					AppContext.resetTimer("using readSNPString3 end");
////				}
////				else {
////					AppContext.resetTimer("using readSNPString4 start");
////					mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
////					
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						mapVarid2Snpsstr_allele2 = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename_allele2);
////					}
////					else {
////						heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
////					}
////					AppContext.resetTimer("using readSNPString4 end");
////				}
////				
////				AppContext.resetTimer("to read allele2 database..");
////				
////				if(isNonsynOnly|| isColorByNonsyn) {
////					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
////					inexonSnps = snpsinexonDAO.getSnps(chr,start.intValue(), end.intValue()); 
////					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
////				}
////			}
////			*/
////			
////			// using hdf5
////			if(listpos!=null && !listpos.isEmpty()) {
////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
////					
////					AppContext.resetTimer("using readSNPString1 start");
////					//	mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  indxs , filename);
////					
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						// mapVarid2Snpsstr_allele2= readSNPString(limitVarIds, chr,  indxs , filename_allele2);
////						
////						mapVarid2Snpsstr = snpstrCoresnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, indxs);
////						mapVarid2Snpsstr_allele2= snpstrCoresnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr,  indxs );
////					}
////					else {
////						mapVarid2Snpsstr = snpstrAllsnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, indxs);
////						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos,  datatype);	
////						mapVarid2Snpsstr_allele2= snpstrAllsnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr,  indxs );
////						
////					}
////
////					AppContext.resetTimer("using readSNPString1 end");
////
////				}
////				else {
////					AppContext.resetTimer("using readSNPString2 start");
////					//	mapVarid2Snpsstr = readSNPString(chr,  indxs, filename);
////
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						//mapVarid2Snpsstr_allele2=  readSNPString(chr,  indxs, filename_allele2);
////						mapVarid2Snpsstr = snpstrCoresnpsAllele1AllvarsDAO.readSNPString(chr,  indxs);
////						mapVarid2Snpsstr_allele2=  snpstrCoresnpsAllele2AllvarsDAO.readSNPString(chr,  indxs);
////					}
////					else {
////						mapVarid2Snpsstr = snpstrAllsnpsAllele1AllvarsDAO.readSNPString(chr,  indxs);
////						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos,  datatype);
////						mapVarid2Snpsstr_allele2=  snpstrAllsnpsAllele2AllvarsDAO.readSNPString(chr,  indxs);
////						
////					}
////					AppContext.resetTimer("using readSNPString2 end");
////				}
////				
////				if(isNonsynOnly || isColorByNonsyn) {
////					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
////					inexonSnps = snpsinexonDAO.getSnps(chr, listpos);
////					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
////				}
////				
////			}
////			else {
////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() )
////				{
////					AppContext.resetTimer("using readSNPString3 start");
////					//mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
////
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						//mapVarid2Snpsstr_allele2 = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename_allele2);
////						mapVarid2Snpsstr = snpstrCoresnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////						mapVarid2Snpsstr_allele2 = snpstrCoresnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////					}
////					else {
////						mapVarid2Snpsstr = snpstrAllsnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////						//heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosBetween(chr, start, end, limitVarIds, datatype);
////						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
////						mapVarid2Snpsstr_allele2 = snpstrAllsnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////					}
////
////					AppContext.resetTimer("using readSNPString3 end");
////				}
////				else {
////					AppContext.resetTimer("using readSNPString4 start");
////					//mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
////					
////					if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////						//mapVarid2Snpsstr_allele2 = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename_allele2);
////						mapVarid2Snpsstr = snpstrCoresnpsAllele1AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////						mapVarid2Snpsstr_allele2 = snpstrCoresnpsAllele2AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////					}
////					else {
////						mapVarid2Snpsstr = snpstrAllsnpsAllele1AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
////						mapVarid2Snpsstr_allele2 = snpstrAllsnpsAllele2AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
////					}
////					AppContext.resetTimer("using readSNPString4 end");
////				}
////				
////				AppContext.resetTimer("to read allele2 database..");
////				
////				if(isNonsynOnly|| isColorByNonsyn) {
////					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
////					inexonSnps = snpsinexonDAO.getSnps(chr,start.intValue(), end.intValue()); 
////					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
////				}
////			}
////
////			
////			 
////			// filter varieties here
////			if(datatype == SnpcoreRefposindexDAO.TYPE_3KALLSNP && limitVarIds!=null && !limitVarIds.isEmpty() && heteroSnps!=null) {
////				Iterator<SnpsHeteroAllele2> itHetero = heteroSnps.iterator();
////				Set setNewAllele2 = new LinkedHashSet();
////				while(itHetero.hasNext()) {
////					SnpsHeteroAllele2 all2 =  itHetero.next();
////					if(limitVarIds.contains(all2.getVar()) )
////						setNewAllele2.add( all2 );
////				}
////				heteroSnps = setNewAllele2;	
////			}
////			
////			
////			// non-synonymous alleles for given table index
////			Map<Integer,Set<Character>> mapIdx2NonsynAlleles = new HashMap();
////			
////			//mapIdx2NonsynAlleles = new HashMap();
////			Set<Integer> setSnpInExonTableIdx = null;
////			if(isNonsynOnly || isColorByNonsyn) {
////				
////				setSnpInExonTableIdx = new HashSet();
////				Iterator<Snp> itInexon = inexonSnps.iterator();
////				while(itInexon.hasNext()) setSnpInExonTableIdx.add( mapSnpid2TableIdx.get( itInexon.next().getSnpFeatureId() ) );
////				
////				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
////				while(itNonsyn.hasNext()) {
////					SnpsNonsynAllele nonsynallele = itNonsyn.next();
////					
////					Set<Character> alleles =  mapIdx2NonsynAlleles.get( mapSnpid2TableIdx.get( nonsynallele.getSnp() )  );
////					if(alleles==null) {
////						alleles = new HashSet();
////						mapIdx2NonsynAlleles.put( mapSnpid2TableIdx.get( nonsynallele.getSnp() )  , alleles);
////					}
////					alleles.add( nonsynallele.getAllele() );
////				}
////			}
////			
////			if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapSnpid2TableIdx.size() + " nonsys alleles positions/idx");
////			
////
////			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
////			if(heteroSnps!=null) {
////				Iterator<SnpsHeteroAllele2> itSnp = heteroSnps.iterator();
////				while(itSnp.hasNext()) {
////					SnpsHeteroAllele2 snpallele2 = itSnp.next();
////					Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( snpallele2.getVar() );
////					if(mapTableidx2Nuc==null) {
////						mapTableidx2Nuc = new HashMap();
////						mapVarid2SnpsAllele2str.put(snpallele2.getVar() , mapTableidx2Nuc);
////					}
////					mapTableidx2Nuc.put(mapSnpid2TableIdx.get( snpallele2.getSnp() )  , snpallele2.getNuc() );
////				}
////			} else if(mapVarid2Snpsstr_allele2!=null) {
////				
////				Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
////				while(itVarid.hasNext()) {
////					BigDecimal varid= (BigDecimal)itVarid.next();
////					
////					String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
////					for(int iStr=0; iStr<allele2str.length(); iStr++) {
////						char allele2i =allele2str.charAt(iStr);
////						if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {
////					
////							Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
////							if(mapTableidx2Nuc==null) {
////								mapTableidx2Nuc = new HashMap();
////								mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
////							}
////							mapTableidx2Nuc.put( iStr, allele2i);
////						}
////					}
////				}
////			}; // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
////			
////			/*
////			
////			mapVarid2Snpsstr=strRef
////			var1allele2str =mapVarid2SnpsAllele2str
////			var2allele2str
////			mapIdx2NonsynAlleles = mapIdx2NonsynAlleles
////			setSnpInExonTableIdx = setSnpInExonTableIdx
////
////			*/
////			
////
////			SNPsStringData snpstrdata = new  SNPsStringData(strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str, mapIdx2NonsynAlleles, setSnpInExonTableIdx);
////			
////			return snpstrdata;
////			
////			
////			
////			
//////			Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
//////			Map<BigDecimal, boolean[]> mapVar2NonsynFlags = new HashMap();
//////			
//////			Iterator<BigDecimal> itVar = mapVarid2Snpsstr.keySet().iterator();
//////			while(itVar.hasNext()) {
//////				BigDecimal var = itVar.next();
//////				String snpstr = (String)mapVarid2Snpsstr.get(var);
//////
//////				
//////				
//////					boolean isnonsyn[] = new boolean[refLength];
//////					if(isNonsynOnly || isColorByNonsyn) {
//////						boolean includeVar = false;
//////						
//////						// check for positions with non-synonymous alleles
//////						// include variety if it has one or more non-synonymous allele snp
//////						for(int iStr=0; iStr<refLength; iStr++) {
//////							char charatistr=snpstr.charAt(iStr);
//////							if(charatistr=='0' || charatistr=='.'  || charatistr=='*') 
//////								isnonsyn[iStr]= false;
//////							else {
//////								if(setSnpInExonTableIdx.contains(iStr)) {
//////									// idx in exon
//////									Set<Character> nonsynalleles =  mapIdx2NonsynAlleles.get(iStr);
//////									if(nonsynalleles==null) {
//////										isnonsyn[iStr]= false;
//////									} else {
//////										if(nonsynalleles.contains( charatistr ) ) {
//////											isnonsyn[iStr]= true;
//////											includeVar = true;
//////										}
//////										else
//////											isnonsyn[iStr]= false;
//////									}
//////								} 
//////								else {
//////									// not in exon, include as nonsynonymous
//////									isnonsyn[iStr]= true;
//////									includeVar = true;
//////								}
//////							}
//////						}
//////						
//////						if(!includeVar && isNonsynOnly && isMismatchOnly) {
//////							// do not include variety
//////							AppContext.debug("var " + var + " all synonymous or unknowns");
//////							continue;
//////						}
//////					}
//////					
//////					
//////					Map<Integer, Character> mapTableIdx2Allele2 =   mapVarid2SnpsAllele2str.get(var);
//////					
//////					// count mismatches
//////					// include variety if mismatch>0 or unchecked mismatch only
//////					
//////					float misCount = 0;
//////					for(int iStr=0; iStr<refLength; iStr++) {
//////						char charatistr=snpstr.charAt(iStr);
//////						
//////						Character allele2 = null;
//////						if(mapTableIdx2Allele2!=null) {
//////							allele2 = mapTableIdx2Allele2.get(iStr);
//////							if(allele2!=null && allele2==charatistr) throw new RuntimeException(charatistr + "=" + allele2 + " allele1==allele2 for var=" + var + " pos=" +  snpsposlist.get(iStr).getPos() +  "  refnuc=" + snpsposlist.get(iStr).getRefnuc() );
//////						}
//////
//////						
//////						// if homozygous, mismatch allele1, miscount +1
//////						// if heterozygous, match allele1 or allele2, miscount +0.5
//////						// if not non-synonymos and (isNonsynOnly or isColorByNonsyn), no count 
//////						
//////						if(strRef.charAt(iStr)==charatistr) {
//////							if( (isNonsynOnly || isColorByNonsyn) && !isnonsyn[iStr] ) {}
//////							else if(allele2!=null) misCount+=0.5;
//////						}
//////						else if(charatistr!='0' && charatistr!='.'  && charatistr!='*' && charatistr!='$') {
//////							
//////							// if consider non-syn only, dont count synonymous as mismatch
//////							if( (isNonsynOnly || isColorByNonsyn) && !isnonsyn[iStr] ) {}
//////							else if(allele2!=null &&  allele2.charValue()==strRef.charAt(iStr) ) misCount+=0.5;
//////							else misCount++;
//////						}
//////						//if(charatistr!='0' && charatistr!='.'  && charatistr!='*' && charatistr!='$' && strRef.charAt(iStr)!=charatistr) misCount++;
//////					}
//////					
//////					if(!isMismatchOnly || misCount>0) {
//////						
//////						sortedVarieties.add( new SnpsStringAllvarsImpl(var,Long.valueOf(chr), snpstr,  BigDecimal.valueOf(misCount) , mapTableIdx2Allele2, isnonsyn) );
//////						
//////					} 
//////			}	
//////			
//////			
//////			// sort included varieties
//////			mapVariety2Order = new HashMap();
//////			int ordercount = 0;
//////			Iterator itSorVars =  sortedVarieties.iterator();
//////			while(itSorVars.hasNext()) {
//////				SnpsStringAllvars snpstrvar = (SnpsStringAllvars)itSorVars.next();
//////				listResult.add( snpstrvar );
//////				mapVariety2Order.put(snpstrvar.getVar() ,ordercount);
//////				ordercount++;
//////			}
//////			
//////			
//////			AppContext.debug( listResult.size() + " sortedvarieties in list, "  + sortedVarieties.size() + " in set" );
////			
////			
////			
//////			Iterator itMisNew = mapMis2Vars.keySet().iterator();
//////			
//////			
//////			mapVariety2Order = new HashMap();
//////			mapIdx2Nonsynflags = new HashMap();
//////			
//////			int ordercount = 0;
//////			while(itMisNew.hasNext()) {
//////				Object miscnt =  itMisNew.next();
//////				Map mapVarId2Snpstr = mapMis2Vars.get(miscnt );
//////				
//////				// sort var by subpop, then country, then name here
//////				//itVar = mapVarId2Snpstr.keySet().iterator();
//////				itVar = mapVarId2Snpstr.keySet().iterator();
//////				Set sortedVarieties = new TreeSet(new VarSubpopCntrySorter());
//////				while(itVar.hasNext()) {
//////					BigDecimal varid = itVar.next();
//////					sortedVarieties.add( mapVarid2Var.get(varid) );
//////				}
//////
//////				AppContext.debug("miscount=" + miscnt + "  nvars=" + mapVarId2Snpstr.size()+  "  nvars sorted=" + sortedVarieties.size());
//////				
//////				Iterator<Variety> itVariety = sortedVarieties.iterator();
//////				while(itVariety.hasNext()) {
//////					Variety varid = itVariety.next();
//////					
//////					listResult.add(  mapVarId2Snpstr.get(varid.getVarietyId()) );
//////					mapVariety2Order.put(varid.getVarietyId() ,ordercount);
//////					mapIdx2Nonsynflags.put( ordercount ,  mapVar2NonsynFlags.get(varid.getVarietyId() ));
//////					
//////					ordercount++;
//////				}
//////				
//////				/*itVar = mapVarId2Snpstr.keySet().iterator();
//////				while(itVar.hasNext()) {
//////					BigDecimal varid = itVar.next();
//////					
//////					listResult.add(  mapVarId2Snpstr.get(varid) );
//////					mapVariety2Order.put(varid ,ordercount);
//////					ordercount++;
//////				}
//////				*/
//////			}
//////			
//////			heteroAlleleMatrix = new char[mapVariety2Order.size()][snpsposlist.size()];
//////			
//////			AppContext.debug(heteroSnps.size() + " allele2, " + mapVarid2Snpsstr.size() + " all varieties, " +  mapVariety2Order.size() + " non-syn varieties, " + snpsposlist.size() + " alleles");
//////			
//////			itSnp = heteroSnps.iterator();
//////			while(itSnp.hasNext()) {
//////				SnpsHeteroAllele2 snpallele2 = itSnp.next();
//////				
//////				//long pos = snpallele2.getSnp().longValue() % 100000000;
//////				Integer varidx = mapVariety2Order.get( snpallele2.getVar() );
//////				//if( varidx!=null)  heteroAlleleMatrix[ varidx ][ mapPos2Idx.get( pos ) ] = snpallele2.getNuc();
//////				
//////				if( mapSnpid2TableIdx.get( snpallele2.getSnp() )==null) {
//////					//AppContext.debug("snpid=" +  snpallele2.getSnp() + " in hetero not in core SNPs");	
//////					//throw new RuntimeException("mapSnpid2Idx.get( snpallele2.getSnp() )==null");
//////					continue;
//////				} 
//////				
//////				if( varidx!=null)  heteroAlleleMatrix[ varidx ][ mapSnpid2TableIdx.get( snpallele2.getSnp() ) ] = snpallele2.getNuc();
//////			}
//////			
//////			AppContext.resetTimer("to prepare for display:.. count mismatches, sort, create Allele2 matrix, create nonsynonymous flag matrix ...");
//////			
////			
//////		return listResult;
////		
////	}
////	/*
////	private IndelsStringData getIndelsStringData(Integer chr, BigDecimal start,
////			BigDecimal end, Set setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
////	
////		
////		IndelStringDAOImpl indelstringdao = new IndelStringDAOImpl(indelsAllvarsPosDAO, indelsAllvarsDAO);
////		
////		Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring=null;
////		if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
////			
////			AppContext.resetTimer("using readIndelString1 start");
////			mapVar2Indelstring=indelstringdao.readSNPString(limitVarIds, chr, start.intValue(), end.intValue());
////			AppContext.resetTimer("using readIndelString1 end");
////
////		}
////		else {
////			AppContext.resetTimer("using readIndelString2 start");
////			mapVar2Indelstring=indelstringdao.readSNPString(chr, start.intValue(), end.intValue());
////			AppContext.resetTimer("using readIndelString2 end");
////		}
////		
////		//indelstringdao.get
////		indelstringdao.getListResult();
////		
////		return null;
////		
////	}
////	*/
////	
//////	private IndelsStringData _getIndelsStringData(Integer chr, BigDecimal start,
//////			BigDecimal end, Set setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
//////		// TODO Auto-generated method stub
//////		
//////		
//////		// get snp positions/index
//////		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "VSnpRefposindexDAO") ;
//////		
//////		AppContext.resetTimer("getIndelsString start");
//////		
//////		
//////		List listpos = null;
//////		if(setPositions!=null && !setPositions.isEmpty()) {
//////			listpos = new ArrayList();
//////			listpos.addAll(new TreeSet(setPositions));
//////			
//////			snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(),  listpos, SnpcoreRefposindexDAO.TYPE_3KALLINDEL);
//////			
//////			if(snpsposlist.size()!=listpos.size())
//////				AppContext.debug("snpsposlist.size()!=listpos.size():" + snpsposlist.size()+ "!=" + listpos.size());
//////		}
//////		else {
//////			snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),   SnpcoreRefposindexDAO.TYPE_3KALLINDEL, -1, -1);
//////		}
//////		
//////		//if(snpsposlist.isEmpty()) return new IndelsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
//////		if(snpsposlist.isEmpty()) return new IndelsStringData("",  new HashMap(), new HashMap());
//////		
//////		
//////		
//////		Map mapVarid2Var = varietyfacade.getMapId2Variety();
//////
//////		
//////
//////		// get allele column indices from start to end positions
//////		VSnpRefposindex startpos =  (VSnpRefposindex)snpsposlist.get(0);
//////		VSnpRefposindex endpos =  (VSnpRefposindex)snpsposlist.get( snpsposlist.size()-1 );
//////
//////		// if recount
//////		String strRef=null;
//////		Map<Float,Map> mapMis2Vars = new TreeMap();
//////		
//////		int refLength=-1;
//////		
//////		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPos() +  "-" + endpos.getPos() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
//////		
//////		
//////			
//////			int indxs[] = new int[snpsposlist.size()];
//////			int indxscount = 0;
//////			//Map<Long, Integer> mapPos2Idx = new HashMap();
//////			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
//////			Map<BigDecimal, Integer> mapSnpid2TableIdx = new HashMap();
//////			
//////			Iterator itSnppos =snpsposlist.iterator();
//////			StringBuffer buffRef = new StringBuffer();
//////			
//////			while(itSnppos.hasNext()) {
//////				VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next(); 
//////				buffRef.append( snppos.getRefnuc());
//////				indxs[indxscount] =  snppos.getAlleleIndex().intValue();
//////				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
//////				mapSnpid2TableIdx.put(snppos.getSnpFeatureId(), indxscount);
//////				indxscount++;
//////			}
//////			strRef = buffRef.toString();
//////			refLength = strRef.length();
//////			
//////			
//////			
//////			Map  mapVarid2Snpsstr = null;
//////			Map mapVarid2Snpsstr_allele2 = null;
//////			
//////			AppContext.resetTimer(" to get position indexes from database..");
//////			
//////			// get snpstring for each varieties
//////			// get allele2 for heterozygous varieties
//////			//snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
//////			
//////			indelstrAllsnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(indelstrAllsnpsAllele1AllvarsDAO, "IndelAlleleDAO");
//////			indelstrAllsnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(indelstrAllsnpsAllele2AllvarsDAO, "IndelAlleleDAO");
//////			
//////			
//////			
//////			Set nonsynAllele = null;
//////			Set inexonSnps = null;
//////			
//////			// using hdf5
//////			if(listpos!=null && !listpos.isEmpty()) {
//////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
//////					
//////					AppContext.resetTimer("using readIndelString1 start");
//////					
//////					mapVarid2Snpsstr = indelstrAllsnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, indxs);
//////					mapVarid2Snpsstr_allele2= indelstrAllsnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr,  indxs );
//////						
//////					AppContext.resetTimer("using readIndelString1 end");
//////
//////				}
//////				else {
//////					AppContext.resetTimer("using readIndelString2 start");
//////
//////					mapVarid2Snpsstr = indelstrAllsnpsAllele1AllvarsDAO.readSNPString(chr,  indxs);
//////					mapVarid2Snpsstr_allele2=  indelstrAllsnpsAllele2AllvarsDAO.readSNPString(chr,  indxs);
//////						
//////					AppContext.resetTimer("using readIndelString2 end");
//////				}
//////				
//////			}
//////			else {
//////				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() )
//////				{
//////					AppContext.resetTimer("using readIndelString3 start");
//////
//////						mapVarid2Snpsstr = indelstrAllsnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//////						mapVarid2Snpsstr_allele2 = indelstrAllsnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//////
//////					AppContext.resetTimer("using readIndelString3 end");
//////				}
//////				else {
//////					AppContext.resetTimer("using readIndelString4 start");
//////					
//////						mapVarid2Snpsstr = indelstrAllsnpsAllele1AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//////						mapVarid2Snpsstr_allele2 = indelstrAllsnpsAllele2AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//////
//////						AppContext.resetTimer("using readIndelString4 end");
//////				}
//////				
//////				AppContext.resetTimer("to read allele2 database..");
//////				
//////			}
//////
//////
//////			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
//////			if(mapVarid2Snpsstr_allele2!=null) {
//////				
//////				Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
//////				while(itVarid.hasNext()) {
//////					BigDecimal varid= (BigDecimal)itVarid.next();
//////					
//////					String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//////					for(int iStr=0; iStr<allele2str.length(); iStr++) {
//////						char allele2i =allele2str.charAt(iStr);
//////						if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {
//////					
//////							Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
//////							if(mapTableidx2Nuc==null) {
//////								mapTableidx2Nuc = new HashMap();
//////								mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
//////							}
//////							mapTableidx2Nuc.put( iStr, allele2i);
//////						}
//////					}
//////				}
//////			}; // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
//////			
//////
//////
//////			//SNPsStringData snpstrdata = new  SNPsStringData(strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str, mapIdx2NonsynAlleles, setSnpInExonTableIdx);
//////			IndelsStringData snpstrdata = new  IndelsStringData(strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str);
//////			
//////			return snpstrdata;
//////			
//////	}
//////	
////	
////	
////	
//////	
//////	@Override
//////	public char[][] getHeteroAlleleMatrix() {
//////		return heteroAlleleMatrix;
//////	}
////
////
////	/**
////	 * Sorts variety by mismatch desc, subpopulation, then country, then id
////	 * Used in Mismatch ordering for the same number of mismatch,
////	 * assuming variety from same subpopulation, then country will be closer relative than random 
////	 * @author lmansueto
////	 *
////	 */
////	class  SnpsStringAllvarsImplSorter implements Comparator {
////		@Override
////		public int compare(Object o1, Object o2) {
////			// TODO Auto-generated method stub
////			SnpsStringAllvars s1 = (SnpsStringAllvars)o1; 
////			SnpsStringAllvars s2 = (SnpsStringAllvars)o2;
////			int ret = -s1.getMismatch().compareTo(s2.getMismatch());
////			if(ret==0) {
////				//return s1.getVar().compareTo( s2.getVar());
////				if(s1 instanceof IndelsStringAllvars && s2 instanceof IndelsStringAllvars) {
////					IndelsStringAllvars is1 = (IndelsStringAllvars)s1; 
////					IndelsStringAllvars is2 = (IndelsStringAllvars)s2;
////					//if(is1.getMapPos2Indels().size()<is2.getMapPos2Indels().size()) ret = 1;
////					//else if(is1.getMapPos2Indels().size()>is2.getMapPos2Indels().size()) ret = -1;
////					
////					//int sumIns1 = 0;
////					//int sumIns2 = 0;
////					//int sumDel1 = 0;
////					//int sumDel2 = 0;
////					
////					Set setAlleles1 = new HashSet();
////					Set setAlleles2 = new HashSet();
////					Iterator<IndelsAllvars> itIndels1 = is1.getMapPos2Indels().values().iterator();
////					Iterator<IndelsAllvars> itIndels2 = is1.getMapPos2Indels().values().iterator();
////					while(itIndels1.hasNext()) {
////						IndelsAllvars indel = itIndels1.next();
////						setAlleles1.add( indel.getAllele1() );
////					}
////					while(itIndels2.hasNext()) {
////						IndelsAllvars indel = itIndels2.next();
////						setAlleles2.add( indel.getAllele1() );
////					}
////					Set allele1notin2 = new HashSet(setAlleles1);
////					allele1notin2.removeAll(setAlleles2);
////					Set allele2notin1 = new HashSet(setAlleles2);
////					allele2notin1.removeAll(setAlleles1);
////					Set uniques = new HashSet(allele1notin2);
////					uniques.addAll(allele2notin1);
////					if(allele1notin2.size()>allele2notin1.size())
////						ret = uniques.size();
////					else if(allele1notin2.size()<allele2notin1.size())
////						ret = -uniques.size();
////					else if(uniques.size()!=0) {
////						if(setAlleles1.size()>setAlleles2.size())
////							return setAlleles1.size();
////						else if(setAlleles1.size()<setAlleles2.size())
////							return -setAlleles2.size();
////						else ret = 0;
////					} else ret=0;
////				}
////				
////				if(ret==0)
////				{
////				
////					Variety v1 =varietyfacade.getMapId2Variety().get(s1.getVar());
////					Variety v2 =varietyfacade.getMapId2Variety().get(s2.getVar());
////					if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
////					{
////						ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
////						if( ret==0 ) {
////							if(v1.getCountry()!=null && v2.getCountry()!=null) {
////								ret = v1.getCountry().compareTo(v2.getCountry());
////								if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
////								else return ret;
////							}
////						} else return ret;
////					} else if(v1.getCountry()!=null && v2.getCountry()!=null) {
////							ret = v1.getCountry().compareTo(v2.getCountry());
////							if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
////							else return ret;
////					} return v1.getVarietyId().compareTo(v2.getVarietyId());
////					
////				} else return ret;
////				
////				
////			} else return ret;
////		}
////	}
////	
////	/**
////	 * Sort pairs descending
////	 * @author lmansueto
////	 *
////	 */
////	class  SnpsString2VarsImplSorter implements Comparator {
////		@Override
////		public int compare(Object o1, Object o2) {
////			// TODO Auto-generated method stub
////			Snps2VarsCountmismatch s1 = (Snps2VarsCountmismatch)o1; 
////			Snps2VarsCountmismatch s2 = (Snps2VarsCountmismatch)o2;
////			return -s1.getMismatch().compareTo(s2.getMismatch());
////		}
////	}
////	
////	
////	class VarSubpopCntrySorter implements Comparator {
////		@Override
////		public int compare(Object o1, Object o2) {
////			// TODO Auto-generated method stub
////			Variety v1 =(Variety)o1;
////			Variety v2 =(Variety)o2;
////			if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
////			{
////				int ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
////				if( ret==0 ) {
////					if(v1.getCountry()!=null && v2.getCountry()!=null) {
////						ret = v1.getCountry().compareTo(v2.getCountry());
////						if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
////						else return ret;
////					}
////				} else return ret;
////			} else if(v1.getCountry()!=null && v2.getCountry()!=null) {
////					int ret = v1.getCountry().compareTo(v2.getCountry());
////					if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
////					else return ret;
////			} return v1.getVarietyId().compareTo(v2.getVarietyId());
////		}
////	}
////	
////
////	@Override
////	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type) {
////		
////		// TODO Auto-generated method stub
////		
////		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean( snpstringallvarsposService, "VSnpRefposindexDAO");
////		//snpstringallvarsposService.
////		List listtmp = new ArrayList();
////		listtmp.addAll(setSNP);
////		return new HashSet(snpstringallvarsposService.getSNPsInChromosome(chr, listtmp, type));
////
////	}
////	
////	
////	
////	
////	
////
////	/**
////	 * Count mismatch between nucelotide sequences, based on several criteria
////	 * @param var1	variety 1 allele1 string
////	 * @param var2	variety 2 allele1 string
////	 * @param var1isref	variety 1 is reference
////	 * @param var1allele2str	variety 1 allele2 string
////	 * @param var2allele2str	variety 2 allele2 string
////	 * @param mapIdx2NonsynAlleles	map table index 2 nonsysynonymous nucleotide set
////	 * @param setSnpInExonTableIdx	set of table indices in exon
////	 * @param setNonsynIdx		set of table indices with nonsynonymous (return value)
////	 * @param isNonsynOnly	include only nonsynonymous
////	 * @param isColorSynGray	color nonsynonymous as gray
////	 * @return
////	 */
////  public static double countVarpairMismatch(String var1, String var2, boolean var1isref, Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,	
////				Map<Integer,Set<Character>> mapIdx2NonsynAlleles, Set setSnpInExonTableIdx, Set setNonsynIdx, boolean isNonsynOnly) {
////
////		double misCount = 0;
////		for(int iStr=0; iStr<var2.length(); iStr++) {
////			char var1char = var1.charAt(iStr);
////			char var2char = var2.charAt(iStr);
////			boolean snpInExon = false;
////			if(setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr)) snpInExon=true;
////			
////			Boolean isNonsyn[] = new Boolean[2];
////			isNonsyn[0] = false;
////			isNonsyn[1] = false;
////			Character var1allele2 = null;
////			if(!var1isref && var1allele2str!=null) var1allele2 =  var1allele2str.get(iStr);
////			
////			Character var2allele2 = null;
////			if(var2allele2str!=null) var2allele2 = var2allele2str.get(iStr);
////			Set setNonsyns = null;
////			if(mapIdx2NonsynAlleles!=null) setNonsyns = mapIdx2NonsynAlleles.get(iStr);
////			
////			misCount += countVarpairMismatchNuc( var1.charAt(iStr),  var2.charAt(iStr),  var1isref, var1allele2, var2allele2,
////					setNonsyns, snpInExon,  isNonsyn,  isNonsynOnly);
////					
////			if(isNonsyn[0] || isNonsyn[1]) setNonsynIdx.add(iStr);					
////		}
////		return misCount;
////  }
////	  
////  public static double countVarpairMismatchNuc(char var1char, char var2char , boolean var1isref,Character var1allele2, Character var2allele2,	
////		Set<Character> setNonsynAlleles, boolean snpInExon, Boolean isNonsyn[], boolean isNonsynOnly) {
////		double misCount = 0;
//////		for(int iStr=0; iStr<var2.length(); iStr++) {
//////			char var1char = var1.charAt(0);
//////			char var2char = var2.charAt(0);
////			
////				isNonsyn[0]=false;
////				isNonsyn[1]=false;
////				//boolNonsyn = false;
////				
////				if( var2allele2 != null) {
////					if(var2allele2=='*') var2allele2 = var2char;
////					else if( var2allele2=='0' || var2allele2=='.') var2allele2=null;
////				}
////				if( var1allele2 != null) {
////					if(var1allele2=='*') var1allele2 = var1char; 
////					else if(var1allele2=='0' || var1allele2=='.') var1allele2=null;
////				}
////				
////				
////				if(var2char=='0' || var2char=='.'  || var2char == '*')
////					{}
////				else if(!var1isref && (var1char=='0' || var1char=='.'  || var1char == '*')) 
////					{}
////				else {
////					if(snpInExon) {
////						// idx in exon
////						if(setNonsynAlleles!=null && (setNonsynAlleles.contains(var2char) || (var2allele2!=null && setNonsynAlleles.contains(var2allele2) ) ) )
////							// var2 allele1 or allele2 in nonsynonymous
////							isNonsyn[1]=true;
////						
////						if(!var1isref && setNonsynAlleles!=null && (setNonsynAlleles.contains(var1char) || (var1allele2!=null && setNonsynAlleles.contains(var1allele2) ) ) )
////							// var1 is not reference, and var1 allele1 or allele2 in nonsynonymous
////							isNonsyn[0]=true;
////					} 
////					else {
////						// not in exon, OR no exon information, include in nonsynonymous
////						isNonsyn[0]=true; 
////						isNonsyn[1]=true;
////					}
////				}
////				
////				if(isNonsynOnly && !isNonsyn[0]  && !isNonsyn[0]) return 0;
////
////				
////				if(var1isref) {
////					// compare with reference
////					
////					// assump: no 0 * . $ characters in reference
////					// if homozygous, mismatch allele1, miscount +1
////					// if heterozygous, match allele1 or allele2, miscount +0.5
////					// if not nonsynonymos and isNonsynOnly , no count 
////					if(var1char==var2char) {
////						if(var2allele2!=null && var2allele2!=var2char ) misCount+=0.5;
////					}
////					else if(var2char!='0' && var2char!='.'  &&  var2char!='*' &&  var2char!='$') {
////						//check with allele 2
////						if(var2allele2!=null &&  var2allele2==var1char) misCount+=0.5;
////						else misCount +=1.0;
////					}
////					
////				} else {
////					// pairwise comparison
////					
////					// check all pairs
////					if(var1char=='0' || var2char=='0' ||  var1char=='.' || var2char=='.' ||  var1char=='*' || var2char=='*' ) {}
////					else if(var1char==var2char) {
////						if(var2allele2!=null && var1char!= var2allele2)
////							misCount+=0.5;
////						if(var1allele2!=null && var2char!= var1allele2)
////							misCount+=0.5;
////						
////					}
////					else {
////						//var1 allele1 != var2 allele1
////						if(var1allele2==null && var2allele2==null) misCount+=1;  
////						else {
////							//if(var1allele2==null || var2allele2==null) misCount+=0.5;
////							if(var1allele2!=null && var2char!=var1allele2)
////								misCount+=0.5;
////							if(var2allele2!=null && var1char!=var2allele2)
////								misCount+=0.5;
////						}
////					}
////				}
////				
////				//String ismis = "          *** ";
////				//if(var1char==var2char) ismis= "";
////				//AppContext.debug("var1isref=" + var1isref + "  var1char=" + var1char + "  var2char=" + var2char + " var1allele2=" + var1allele2 + " var2allele2=" + var2allele2  + "  mis=" + misCount  +  ismis);
////				
////		//}
////				
////		return misCount;
////	}
////
////  public static double countVarpairMismatchIndel(IndelsAllvarsPos var1char, IndelsAllvarsPos var2char , boolean var1isref, IndelsAllvarsPos var1allele2, IndelsAllvarsPos var2allele2) {
////			
////	  			
////	  
////	  				double misCount = 0;
////	  				if(var1char==null && var2char==null) {
////	  					
////	  				}
////	  				else if(var1char==null && var2char!=null) {
////						if(var2char.getInsString()!=null) misCount+=var2char.getInsString().length();
////						 misCount+= var2char.getDellength();
////						 
////						 if(var2allele2!=null) {
////								if(var2allele2.getInsString()!=null) misCount+=var2allele2.getInsString().length();
////								 misCount+= var2allele2.getDellength();
////						 }
////	  				} else if(var2char==null && var1char!=null) {
////						if(var1char.getInsString()!=null) misCount+=var1char.getInsString().length();
////						 misCount+= var1char.getDellength();
////						 
////						 if(var1allele2!=null) {
////								if(var1allele2.getInsString()!=null) misCount+=var1allele2.getInsString().length();
////								 misCount+= var1allele2.getDellength();
////						 }
////	  					
////	  				} else if(var1isref) {
////						if(var2char.getInsString()!=null) misCount+=var2char.getInsString().length();
////						 misCount+= var2char.getDellength();
////						 
////						 if(var2allele2!=null) {
////								if(var2allele2.getInsString()!=null) misCount+=var2allele2.getInsString().length();
////								 misCount+= var2allele2.getDellength();
////						 }
////							
////						
////					} else {
////				
////						// allele2 not yet considered in for pairwise comparison
////						
////						
////						if(var1char.getInsString()!=null && !var1char.getInsString().isEmpty()) 
////						{
////							if(var2char.getInsString()!=null && !var2char.getInsString().isEmpty()) {
////								// both are ins 
////								if(var2char.getInsString().equals(var1char.getInsString())) {
////									// equal
////								} else if(var2char.getInsString().startsWith( var1char.getInsString() ) || var1char.getInsString().startsWith( var2char.getInsString() )  ) {
////									// substring
////									double deflength =  var2char.getInsString().length() -  var1char.getInsString().length();
////									
////									if(deflength>0)
////										misCount +=  deflength;
////									else 
////										misCount +=  -deflength;
////								} else {
////									misCount += var2char.getInsString().length() +  var1char.getInsString().length();
////								}
////							} else {
////								// var2 is del
////								misCount += var1char.getInsString().length();
////								misCount += var2char.getDellength();
////							} 
////						} else {
////
////							// var1 is del
////							if(var2char.getInsString()!=null && !var2char.getInsString().isEmpty()) {
////								// var2 is ins
////								misCount += var2char.getInsString().length() +  var1char.getDellength();
////							} else {
////								// var2 is del
////								double defdel = var1char.getDellength() -   var2char.getDellength();
////								if(defdel>0)
////									misCount += defdel; 
////								else 
////									misCount += - defdel;
////							} 
////						}
////					}
////					
////			return misCount;
////		}
////  
////  
////  
////  
////  // for generic genotype table
////  
////  
////
////	 private VariantTable getVariantTable(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP, boolean bIndel,
////			 boolean bCoreonly, boolean bMismatchonly, Collection poslist, String sSubpopulation, String sLocus) throws Exception {
////		 
////			
////			// true if output is written to file
////		 	boolean bAllvars = colVarIds==null;
////			
////			// to facilitate render on Tab select for JBrowse and phylogenetic tree
////			GenotypeFacade.snpQueryMode mode=null;
////			
////			
////				
////			String msgbox = "";
////			if(poslist==null)
////				msgbox="SEARCHING: in chromosome " + sChr + " [" + lStart +  "-" + lEnd +  "]" ;
////			else 
////				msgbox="SEARCHING: in chromosome " + sChr;
////			
////				
////				
////				Set setVarieties = null; 
////				
////				if(colVarIds==null) {
////					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
////				} else {
////					setVarieties = new HashSet(colVarIds);
////					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
////				}
////				
////				if( sSubpopulation!=null && !sSubpopulation.isEmpty()) {
////					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
////					setVarieties = getVarietiesForSubpopulation( sSubpopulation );
////				}
////
////				
////				// set genotype facade state
////				limitVarieties(setVarieties);	
////				setCore( bCoreonly );
////				setColorByNonsyn( false );
////				setNonsynOnly( false );
////				setMismatchOnly( bMismatchonly );
////				setIncludeSNP( bSNP );
////				setIncludeIndel( bIndel );
////				
////				// initialize empty SNP list
////				List listSNPs = new java.util.ArrayList();
////
////				String genename = "";
////				if(sLocus!=null) genename = sLocus.toUpperCase();
////				//gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
////				
////				AppContext.startTimer();
////
////				if( !genename.isEmpty() )
////				{
////					Gene gene2 = getGeneFromName( genename);
////					lStart = Long.valueOf(gene2.getFmin()) ;	
////					lEnd =  Long.valueOf(gene2.getFmax());	
////					//selectChr.setSelectedIndex( gene2.getChr()-1 );
////					//sChr =
////					sChr = gene2.getChr().toString();
////				}
////				
////			
////					Set snpposlist = null;
////					
////					
////						int chrlen = listitemsDAO.getFeatureLength( sChr );
////						
////						
////						if(lEnd> chrlen  ||lStart> chrlen)
////						{
////							throw new Exception("Positions should be less than length");
////						} 
////						if(lEnd<1  || lStart<1)
////						{
////							throw new Exception("Positions should be positive integer");
////						}   				
////						if(lEnd<lStart)
////						{
////							throw new Exception("End should be greater than or equal to start");
////						}  
////
////						// if length > maxlengthUni, change to core
////						
////						// if length > maxlengthCore, not allowed
////
////						
////						int maxlength = -1;
////						String limitmsg = "";
////						if(bAllvars && !bCoreonly) {
////							maxlength = AppContext.getMaxlengthUni();
////							limitmsg = "Limit to " + (maxlength/1000) + " kbp range for All Snps, or " + AppContext.getMaxlengthCore()/1000 + " kbp for Core Snps, All Varieties query.";
////						} else if(bAllvars && bCoreonly ) {
////							maxlength = AppContext.getMaxlengthCore();
////							limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Core Snps, all varieties query";
////						} else
////						{
////							maxlength = AppContext.getMaxlengthPairwise();
////							limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Pairwise variety query";
////						}
////						
////						long querylength = lEnd-lStart; 
////						//if(!checkboxCoreSNP.isChecked() && querylength > maxlength )
////						if( querylength > maxlength ) throw new Exception(limitmsg);
////						
////
////						
////					String chr= sChr;
////					if(sLocus!=null && !sLocus.isEmpty())
////						msgbox="Searching within GENE " + sLocus.toUpperCase()  + " " + chr + " [" + lStart + ".." + lEnd + "] ";
////					else
////						msgbox="SEARCHING: in chromosome " + chr + " [" + lStart +  "-" + lEnd +  "]" ;
////					
////					
////					if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){
////
////
////						List  newpagelist; 
////						
////						//snpallvarsresult.setAttribute("org.zkoss.zul.grid.rod", true);
////						
////						if(snpposlist!=null && !snpposlist.isEmpty()) {
////							newpagelist = getSNPStringInAllVarieties(snpposlist, Integer.valueOf(chr)); //, this.checkboxCoreSNP.isChecked());  true,  -1,  -1 );
////							
////						}
////						else {
////							newpagelist = getSNPStringInAllVarieties(new Long(lStart).intValue(), new Long(lEnd).intValue(), Integer.valueOf(chr)); //,   true,  -1,  -1 );
////						}
////						
////						//updateAllvarsList(newpagelist, true, null, null, "",-1,-1);
////						
////						listSNPs = newpagelist;
////						
////						if(getSnpsposlist()!=null &&  getMapIndelIdx2Pos()!=null &&getSnpsposlist().size()!=getMapIndelIdx2Pos().size())
////							msgbox += " ... RESULT: " + newpagelist.size() + " varieties x " + (getSnpsposlist().size()-getMapIndelIdx2Pos().size()) + 
////									" SNP, " + getMapIndelIdx2Pos().size() + " INDEL positions";
////						else if(getMapIndelIdx2Pos()!=null)
////							msgbox +=  " ... RESULT: " + newpagelist.size() + " varieties x " + getMapIndelIdx2Pos().size() + " INDEL positions" ;
////						else if(getSnpsposlist()!=null)
////							msgbox +=  " ... RESULT: " + newpagelist.size() + " varieties x " + getSnpsposlist().size() + " SNP positions" ;
////
////				        AppContext.resetTimer("create table" );
////
////				        
////						VariantTable variants = createGenotypeTable(newpagelist);
////						variants.setMessage(msgbox);
////						
////						return variants;
////
////				        
////					}
////					
////					return null;
////		}
////	 
////	 
////
////private VariantTable createGenotypeTable(List<SnpsStringAllvars> listSNPs) //, boolean updateHeaders, String filename, String delimiter, String header, boolean doDownload, Integer chromosome) //, int firstRow, int numRows)
////{
////
////	VariantTable vartable = new VariantTable();
////	
////	boolean fetchMismatchOnly =  AppContext.isSNPAllvarsFetchMismatchOnly();  //listSNPs contains only NULL and referfence mismatches
////	
////	
////	List<SnpsAllvarsPos> snpsposlist = getSnpsposlist();
////		
////	
////	Long posarr[] = new Long[snpsposlist.size()]; 
////	String refnuc[] = new String[snpsposlist.size()];
////	Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
////	int poscount = 0;
////	while(itPos.hasNext()) {
////		SnpsAllvarsPos posnuc=itPos.next();
////		posarr[poscount] = posnuc.getPos().longValue();
////		refnuc[poscount] = posnuc.getRefnuc();
////		poscount++;
////	}
////	vartable.setPosition(posarr);
////	vartable.setReference(refnuc);
////		
////	
////	
////		List listTable= listSNPs;
////		
////		
////		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
////		Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
////			
////
////		String varnames[] = new String[listTable.size()];
////		Long varids[] = new Long[listTable.size()];
////		Double varmismatch[] = new Double[listTable.size()];
////		String allelestring[][] = new String[listTable.size()][snpsposlist.size()];
////	
////		Iterator<SnpsStringAllvars> itSnpstring = listTable.iterator();
////		int varcount = 0;
////		while(itSnpstring.hasNext()) {
////			SnpsStringAllvars snpstr = itSnpstring.next();
////			
////			varmismatch[varcount]=snpstr.getMismatch().doubleValue();
////			varnames[varcount]=mapVarid2Variety.get( snpstr.getVar() ).getName();
////			varids[varcount]= snpstr.getVar().longValue();
////			
////			allelestring[varcount] = getVarietyIndelString(snpstr,snpsposlist.size() );
////			varcount++;
////		}
////		
////		vartable.setVarname(varnames);
////		vartable.setVarid(varids);
////		vartable.setVaralleles(allelestring);
////		vartable.setVarmismatch(varmismatch);
////		
////		return vartable;
////	
////}
////  
////  
////
////
////private String[] getVarietyIndelString(SnpsStringAllvars snpstr, int cols) {
////	Map<Integer,BigDecimal> mapMergedIdx2Pos = getMapMergedIdx2Pos();
////	Map<Integer,BigDecimal> mapIdx2Pos  = getMapIndelIdx2Pos();
////	Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels =getMapIndelId2Indel();
////	Map<Integer, Integer> mapMergedIdx2SnpIdx = getMapMergedIdx2SnpIdx();
////
////	String allelesstr[] = new String[cols];  
////	for(int iCols=0; iCols<cols; iCols++) {
////		//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
////		StringBuffer buff = new StringBuffer();
////		
////			if(snpstr instanceof IndelsStringAllvars) {
////				
////				int j=iCols;
////				
////				BigDecimal pos = null;
////				if(mapMergedIdx2Pos!=null) 
////					pos = mapMergedIdx2Pos.get(j);
////				else 
////					pos = mapIdx2Pos.get(j);
////				
////				if(pos!=null) {
////				
////					IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
////					
////					
////					
////					BigDecimal alleleid = indelstring.getAllele1( pos );
////					IndelsAllvarsPos indelpos = mapIndelId2Indels.get(alleleid);
////					
////					//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
////					if(alleleid!=null && indelpos!=null) {
////						
////						buff.append( getIndelAlleleString(indelpos) );
////						
////						BigDecimal alleleid2 = indelstring.getAllele2( pos );
////						indelpos = mapIndelId2Indels.get(alleleid2);
////						
////						if(alleleid2!=null && !alleleid2.equals(alleleid)) {
////							alleleid=alleleid2;
////							indelpos = mapIndelId2Indels.get(alleleid);
////							if(indelpos!=null) {
////								buff.append("/").append(getIndelAlleleString(indelpos) );
////							}								
////						}
////					}
////					
////					if(indelstring.getVarnuc()!=null) {
////						
////						// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
////						if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
////							j = mapMergedIdx2SnpIdx.get(iCols);
////
////							//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
////							char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
////							if(element!='0' && element!='.' && element!=' ' && element!='*') {
////								buff.append(element);
////							
////								Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
////								if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
////									element = mapPosidx2allele2.get(j);
////									if(element!='0' && element!='.' && element!=' ' && element!='*') 
////										buff.append("/").append(element);
////								}
////							}
////						}
////					}
////				}
////				
////			} else {
////				int j=iCols;
////				// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
////				if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
////					j = mapMergedIdx2SnpIdx.get(iCols);
////				}
////				
////				if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
////					//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
////					char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
////					if(element!='0' && element!='.' && element!=' ' && element!='*') {
////						buff.append(element);
////						Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
////						if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
////							element = mapPosidx2allele2.get(j);
////							if(element!='0' && element!='.' && element!=' ' && element!='*') 
////								buff.append("/").append(  element );
////						}
////					}
////				}
////				
////			}
////			allelesstr[iCols] = buff.toString();
////		}
////	return allelesstr;
////}
////
////  
////// ************************************* old implementations  ************************************************	
////	
////
////	// SNP query from the SNP_GENOTYPE table (old implementation)
////	
////	@Override
////	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches() {
////		return listSNPAllVarsMismatches;
////	}
////
////	@Override
////	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(int firstRow, int numRows) {
////		List newlist = new java.util.ArrayList();
////		if(numRows==0) {
////			//numRows=newlist.size();
////			numRows = listSNPAllVarsMismatches.size() - firstRow  +1 ;
////		}
////
////		for(int i=firstRow-1; i<firstRow-1+numRows && i<listSNPAllVarsMismatches.size() ; i++)
////				newlist.add(listSNPAllVarsMismatches.get(i));
////		
////		AppContext.debug("created page with " + newlist.size() + " varieties");
////		return newlist;
////	}
////
/////*
////	@Override
////	public Map<Integer, Set<Character>> getMapIndex2NonsynAlleles() {
////		return mapIndex2NonsynAlleles;
////	}
////*/
////
////	
////	
////	
//	
//	
//	
//	
	
}
