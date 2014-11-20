package org.irri.iric.portal.genotype.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.Snps2VarsCountMismatchDAO;
import org.irri.iric.portal.dao.Snps2VarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsAllvarsRefMismatchDAO;
import org.irri.iric.portal.dao.SnpsHeteroAllvarsDAO;
import org.irri.iric.portal.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.portal.dao.SnpsStringAllvarsDAO;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsHeteroAllele2;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
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
@Service("GenotypeFacade")
//@Scope("prototype")
@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
public class GenotypeFacadeChadoImpl implements GenotypeFacade {

	private static final Log log = LogFactory.getLog(GenotypeFacadeChadoImpl.class);

	// The scope of this class is Session, it keeps a set of state variables to keep
	// results from previous Request within the Session
	
	// private state variables
	
	private List listSNPAllVarsMismatches;
	private HashMap<Integer,BigDecimal> mapOrder2Variety; // 0-indexed
	private HashMap<BigDecimal, Integer> mapVariety2Order;  // 0-indexed
	private HashMap<BigDecimal, Integer> mapVariety2Mismatch;
	private HashMap<BigDecimal, Integer> mapVariety2PhyloOrder;
	private List<SnpsAllvarsPos> snpsposlist;
	private Set<BigDecimal> limitVarIds;
	private boolean isCore=false;
	private boolean isNonsynOnly=false;
	private boolean isColorByNonsyn=false;
	private Map<Integer,Set<Character>>  mapIdx2NonsynAlleles;
	private Map<Integer,boolean[]>  mapIdx2Nonsynflags;
	private char[][] heteroAlleleMatrix;
	
	
	@Autowired
	private ApplicationContext appContext;

	@Autowired
	@Qualifier("VarietyFacade")
	private VarietyFacade varietyfacade;

	@Autowired
	private GeneDAO geneservice; 
	
	@Autowired
	private ListItemsDAO listitemsDAO;
	
	@Autowired
	@Qualifier("Snps2VarsDAO")
	private  Snps2VarsDAO snp2linesService; 
	
	@Autowired 
	//@Qualifier("Snps2VarsCountMismatchDAO")
	@Qualifier("SnpsString2VarsCountMismatchDAO")
	private  Snps2VarsCountMismatchDAO snpcount2linesService; 
	
	@Autowired
	@Qualifier("VSnpRefposindexDAO")		// snps reference, allele position file index
	private SnpsAllvarsPosDAO snpstringallvarsposService;

	@Autowired
	SnpsHeteroAllvarsDAO snpsheteroDAO;
	
	@Autowired
	SnpsNonsynAllvarsDAO snpsnonsynDAO;
	
	
	// ******************* backup DAO references ******************
	/*
	@Autowired
	private GeneDAO geneservice; // = new org.irri.iric.portal.genotype.service.GeneServiceImpl();
	//@Autowired 
	//@Qualifier("VarietyDAO")
	//private VarietyDAO varservice;
	
	@Autowired
	private ListItemsDAO listitemsDAO;
	
	@Autowired
	@Qualifier("Snps2VarsDAO")
	private  Snps2VarsDAO snp2linesService; // = new Snp2linesHome();
	
	//private  org.irri.iric.portal.genotype.views.ISnp2linesHome snp2linesService; // = new Snp2linesHome();
	
//	@Autowired
//				//@Qualifier("SnpsAllvarsDAO")
//	@Qualifier("VSnpAllvarsMinDAO")  // no snp_genotype_id
//	private  SnpsAllvarsDAO snpallvarsService; // = new Snp2linesHome();
	
	@Autowired
	@Qualifier("SnpsAllvarsPosDAO")
	private  SnpsAllvarsPosDAO snpallvarsposService; // = new Snp2linesHome();

//	@Autowired
//	@Qualifier("MvCoreSnpsDAO")		// using core snps
//	private SnpsAllvarsPosDAO snpcoreallvarsposService;


	@Autowired
	@Qualifier("SnpsAllvarsRefMismatchDAO")
	private  SnpsAllvarsRefMismatchDAO snpcountallvarsService;

	@Autowired 
	//@Qualifier("Snps2VarsCountMismatchDAO")
	@Qualifier("SnpsString2VarsCountMismatchDAO")
	private  Snps2VarsCountMismatchDAO snpcount2linesService; // = new Snp2linesHome();
	
	
	@Autowired
	@Qualifier("SnpcoreMismatchAllelesDAO")	
	private SnpsStringAllvarsDAO snpstringAllvarsDao; // using core snps, allelestring in database
	
//	@Autowired
//	@Qualifier("SnpstringAllelesFileDAO")	// using core snps, allelestring in flatfile
//	private SnpsStringAllvarsDAO snpstringallelesfileDao;


	@Autowired
	@Qualifier("VSnpRefposindexDAO")		// using core snps, allelestring in database
	private SnpsAllvarsPosDAO snpstringallvarsposService;

	@Autowired
	@Qualifier("MismatchCountDAO")
	private SnpsAllvarsRefMismatchDAO refmismatchDAO;

	@Autowired
	SnpsHeteroAllvarsDAO snpsheteroDAO;
	
	@Autowired
	SnpsNonsynAllvarsDAO snpsnonsynDAO;
	
	@Autowired
	@Qualifier("VarietyFacade")
	private VarietyFacade varietyfacade;
	
	*/
	
	
	public GenotypeFacadeChadoImpl() {
		super();
		// TODO Auto-generated constructor stub
		
		AppContext.debug("created GenotypeFacadeChadoImpl: " + this );

	}

	

// ********************************************** Set State ************************************************************

	@Override
	public void limitVarieties(Set varieties) {
		if(varieties!=null)
		{
			Set setVarid=new HashSet();
			Iterator<Variety> itVar=varieties.iterator();
			while(itVar.hasNext())
			{
				Variety var = itVar.next();
				
				if(var!=null) setVarid.add(var.getVarietyId());
			}
			limitVarIds=setVarid;
		}
		else limitVarIds=null;
	}
	
	@Override
	public void setCore(boolean isCore) {
		this.isCore = isCore;
	}

	@Override
	public void setColorByNonsyn(boolean selected) {
		// TODO Auto-generated method stub
		this.isColorByNonsyn = selected;
		
	}


	@Override
	public void setNonsynOnly(boolean selected) {
		// TODO Auto-generated method stub
		this.isNonsynOnly = selected;
		
	}

// ************************************************ Get State ***********************************************************

	@Override
	public Map<Integer, Set<Character>> getMapIdx2NonsynAlleles() {
		return mapIdx2NonsynAlleles;
	}

	
	@Override
	public HashMap<Integer, BigDecimal> getMapOrder2Variety() {
		// TODO Auto-generated method stub
		return this.mapOrder2Variety;
	}


	@Override
	public HashMap<BigDecimal, Integer> getMapVariety2Order() {
		// TODO Auto-generated method stub
		return this.mapVariety2Order;
	}


	@Override
	public HashMap<BigDecimal, Integer> getMapVariety2Mismatch() {
		// TODO Auto-generated method stub
		return this.mapVariety2Mismatch;
	}

	@Override
	public java.util.HashMap<BigDecimal, Integer> getMapVariety2PhyloOrder() {
		return mapVariety2PhyloOrder;
	}

	@Override
	public List<SnpsAllvarsPos> getSnpsposlist() {
		
		return snpsposlist;
	}
	

	
// ************************************* Methods for UI Listboxes *******************************************************
	

	@Override
	public Set getVarietiesForSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return varietyfacade.getGermplasmBySubpopulation(subpopulation);
	}
	
	
	/**
	 * Get Gene object from name
	 */
	@Override
	public Gene getGeneFromName(String name) {
		// TODO Auto-generated method stub
		return geneservice.findGeneByName(name);
	}

	/**
	 * Get varietry names 
	 */
	
	@Override
	public List<String> getVarnames() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO");
		return listitemsDAO.getVarietyNames();
		
	}
	
	@Override
	public List<String>getSubpopulations() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO");
		return listitemsDAO.getSubpopulations();
	}
	 
	/**
	 * Get all gene names
	 */
	@Override
	public List<String> getGenenames() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO");
		return listitemsDAO.getGenenames();
	}

	/**
	 * Get chromosome names, mocked! replace with DB read later
	 */

	@Override
	public java.util.List<String> getChromosomes() {
		java.util.List<String> chr = new java.util.ArrayList<String>();
		for(int i=1; i<13; i++)  chr.add( Integer.toString(i));
		return chr;
	}
	
	/**
	 * Get length of features (ex. chromosome) from name, mocked! replace with DB read later
	 */

	@Override
	public Integer getFeatureLength(String feature) {
		// TODO Auto-generated method stub
		
		java.util.Map<String,Integer> chrLength = new java.util.HashMap<String,Integer>();		
		chrLength.put("1", 43270923);
		chrLength.put("2", 35937250);
		chrLength.put("3",36413819);
		chrLength.put("4",35502694);
		chrLength.put("5",29958434);
		chrLength.put("6",31248787);
		chrLength.put("7",29697621);
		chrLength.put("8",28443022);
		chrLength.put("9",23012720);
		chrLength.put("10",23207287);
		chrLength.put("11",29021106);
		chrLength.put("12",27531856);
		
		return chrLength.get(feature);
	}

	
// **************************************   Methods to query SNPs for all varieties *********************************************************************************
//
// these methods use the SNP_GENOTYPE oracle table. To improve speed, these methods have been replaced with SNPString that queries the flatfiles
// or HDF5. These code are kept for future reference for cases to raed the SNP_GENOTYPE table.   	
//	
	
/*
	@Override
	public List<SnpsAllvarsRefMismatch> countSNPMismatchesInAlllVarieties(Integer startPos, Integer endPos, String chromosome, boolean update)
	{
		
		if(limitVarIds!=null && !limitVarIds.isEmpty()){
				return countSNPMismatchesInSetVarieties(limitVarIds,  startPos,  endPos,  chromosome,  update);
		}
		
		if(update || listSNPAllVarsMismatches==null) {
			snpcountallvarsService = (SnpsAllvarsRefMismatchDAO)AppContext.checkBean(snpcountallvarsService, "SnpsAllvarsRefMismatchDAO") ; 
			listSNPAllVarsMismatches = snpcountallvarsService.countMismatches( chromosome,  startPos,  endPos, isCore);
		}
		return listSNPAllVarsMismatches;
	}

	private List<SnpsAllvarsRefMismatch> countSNPMismatchesInSetVarieties(Set setvarIds, Integer startPos, Integer endPos, String chromosome, boolean update)
	{
		if(update || listSNPAllVarsMismatches==null) {
			snpcountallvarsService = (SnpsAllvarsRefMismatchDAO)AppContext.checkBean(snpcountallvarsService, "SnpsAllvarsRefMismatchDAO") ; 
			
			if(setvarIds==null || setvarIds.isEmpty())
				listSNPAllVarsMismatches = snpcountallvarsService.countMismatches( chromosome,  startPos,  endPos, isCore);
			else {
				AppContext.debug("countMismatchesInvars  setvarIds:" + setvarIds);
				listSNPAllVarsMismatches = snpcountallvarsService.countMismatchesInvars(setvarIds, chromosome,  startPos,  endPos, isCore);
			}
		}
		return listSNPAllVarsMismatches;
	}
	
	@Override
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome)
	{
		 return getSNPinAllVarieties(startPos, endPos, chromosome, 1,0); 
	}
	

	@Override
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) {
		return getSNPinAllVarieties(startPos, endPos, chromosome, firstRow, numRows,AppContext.isSNPAllvarsFetchMismatchOnly()); 
	}
	
	@Override
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean fetchMismatchOnly) {
		 return getSNPinSetVarieties(null,  startPos,  endPos,  chromosome,  firstRow,  numRows,  fetchMismatchOnly); 
	}

	
	@Override
	public List<SnpsAllvars> getSNPinAllVarieties(String genename, Integer plusminusBp) {
		// TODO Auto-generated method stub
		
		Gene gene = getGeneFromName( genename );
		log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
		
		return getSNPinAllVarieties(gene.getFmin() , gene.getFmax(), gene.getChr().toString());
	}
	

	@Override
	public List<SnpsAllvars> getSNPinSetVarieties(Set setvarIds, Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean fetchMismatchOnly) {
		// TODO Auto-generated method stub

		if(startPos<0)  startPos=0;
	
		
		if(setvarIds==null || setvarIds.isEmpty()) {
			if(limitVarIds!=null && !limitVarIds.isEmpty())
				setvarIds=limitVarIds;
		}
		
		// if first row=1, reset variety-mismatch sort order,  
		if(firstRow==1 ) {
			listSNPAllVarsMismatches=countSNPMismatchesInSetVarieties(setvarIds, startPos,  endPos,  chromosome, true);
			
			mapOrder2Variety = new java.util.HashMap<Integer,BigDecimal>();
			mapVariety2Order = new java.util.HashMap<BigDecimal,Integer>();
			mapVariety2Mismatch = new java.util.HashMap<BigDecimal,Integer>();		
			
		}

		// if no SNP result, return empty
		if(listSNPAllVarsMismatches.size()==0) {
			snpsposlist = new java.util.ArrayList();
			return new java.util.ArrayList();
		}
		
		StringBuffer topVarieties = new StringBuffer();
		
		long lastRow = firstRow + numRows - 1;  // 1-indexed
		if(listSNPAllVarsMismatches.size() >  lastRow)
		{
		} else
			lastRow = listSNPAllVarsMismatches.size();
		
		if(numRows==0)  lastRow = listSNPAllVarsMismatches.size();
		

		Set<BigDecimal> varsMismatch = new HashSet();
		
		// generate variety listing order
		for(long iVariety= firstRow-1;  iVariety<lastRow;  iVariety++ ) {
			
			SnpsAllvarsRefMismatch varmismatch = (SnpsAllvarsRefMismatch)listSNPAllVarsMismatches.get((int)iVariety);
			
			mapOrder2Variety.put( Integer.valueOf((int)iVariety),  varmismatch.getVar()  );
			mapVariety2Order.put( varmismatch.getVar(), Integer.valueOf((int)iVariety)   );
			mapVariety2Mismatch.put( varmismatch.getVar(), varmismatch.getMismatch().intValue()  );
			varsMismatch.add(  varmismatch.getVar() );
		}
		
		if(numRows==0)  varsMismatch=null;
		
		
		 if(isCore) {
				snpcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService, "MvCoreSnpsDAO") ; 
				snpsposlist = snpcoreallvarsposService.getSNPs(chromosome, startPos, endPos, null );
			 
		 }
		 else {
			snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
			snpsposlist = snpallvarsposService.getSNPs(chromosome, startPos, endPos , null);
		 }
			if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");

			snpallvarsService = (SnpsAllvarsDAO)AppContext.checkBean(snpallvarsService, "VSnpAllvarsMinDAO") ; 
			
			Set<SnpsAllvars> snpslist; 
			
			if(fetchMismatchOnly)
				//snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetweenRefmismatch( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch, isCore); 
				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetweenRefmismatch( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), firstRow, lastRow+1, isCore);
			else
				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetween( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch, isCore); 
			

			log.debug( "snpslist Results: " + snpslist.size() ); 
			
			List<SnpsAllvars> list = new java.util.ArrayList<SnpsAllvars>();
			list.addAll(snpslist);
			return list;
	

	}
	*/

	
// ************************************************************ SNP Query between two varieties **********************************************************************************	
	
	/**
	 * Get the SNP alleles for varieties
	 * var1	variety 1
	 * var2	variety 2
	 * startPos	start position
	 * endPos	end position
	 * chromosome
	 * querymode	what alleles to query, allowed values
	 * 				enum snpQueryMode { SNPQUERY_VARIETIES		alleles between the two varieties
	 * 								,  SNPQUERY_REFERENCE	allele with the reference
	 * 								//, SNPQUERY_ALLREFPOS 	all snp positions in the reference
	 * 	
	 */
	@Override
	public List<Snps2Vars> getSNPinVarieties(String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean isCore) {
		return getSNPinVarieties(var1, var2, startPos, endPos, chromosome,  querymode,  isCore, null) ;
	}
	
	
	private List<Snps2Vars> getSNPinVarieties(String var1, String var2, Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode, boolean isCore, Set snpposlist ) {
		
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<String,Variety> mapVarname2Variety = varietyfacade.getMapVarname2Variety();
		
		try {

			Set<Snps2Vars> snpslist=null;
			
			AppContext.debug("finding vars " +  var1 + "      " + var2 );
			
			BigDecimal var1Id = mapVarname2Variety.get(var1.toUpperCase()).getVarietyId();
			BigDecimal var2Id = mapVarname2Variety.get(var2.toUpperCase()).getVarietyId();

			
			if(snpposlist!=null && !snpposlist.isEmpty()) {
				if(querymode==snpQueryMode.SNPQUERY_VARIETIES) {
					snpslist = snp2linesService.findVSnp2varsByVarsChrPosInMismatch(Integer.valueOf(chromosome), snpposlist, var1Id, var2Id, isCore);
				}
				else if(querymode== snpQueryMode.SNPQUERY_ALLREFPOS) { //SNPQUERY_REFERENCE) {
					snpslist = snp2linesService.findVSnp2varsByVarsChrPosInAll(Integer.valueOf(chromosome), snpposlist, var1Id, var2Id, isCore);
				}
			} else {
				if(querymode==snpQueryMode.SNPQUERY_VARIETIES) {
					snpslist = snp2linesService.findVSnp2varsByVarsChrPosBetweenMismatch(Integer.valueOf(chromosome), BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), var1Id, var2Id, isCore);
				}
				else if(querymode== snpQueryMode.SNPQUERY_ALLREFPOS) { //SNPQUERY_REFERENCE) {
					snpslist = snp2linesService.findVSnp2varsByVarsChrPosBetweenAll(Integer.valueOf(chromosome), BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), var1Id, var2Id, isCore);
				}
			}

			AppContext.debug("Results: " + snpslist.size() ); 
			
			List list = new ArrayList();
			list.addAll(snpslist);
			return list;
		}
		catch (RuntimeException re) {
			AppContext.error("getSNPinVarieties failed:"+ re.getMessage());
			throw re;
		}

	}
	
	/**
	 * Get snps within a gene. Get the chromosome and location of the gene, then call getSNPinVarieties(String var1, String var2,
	 *		Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode) 
	 *var1	variety 1
	 *var2	variety 2
	 *genename	
	 *plusminusbp	number of basepairs before and after the gene to include
	 *querymode	 what alleles to query, enum snpQueryMode 
	 *
	 */
	
	@Override
	public List<Snps2Vars> getSNPinVarieties(String var1, String var2, String genename, Integer plusminusBp, snpQueryMode querymode) {
		// TODO Auto-generated method stub
		Gene gene = getGeneFromName( genename );
		log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
		return getSNPinVarieties(var1, var2,  gene.getFmin()  , gene.getFmax(), 
				gene.getChr().toString(), querymode, AppContext.isCore() , null);
	}

	@Override
	public List getSNPinVarieties(String var1, String var2, Set snpposlist, String chr, snpQueryMode mode, boolean isCore) {
		// TODO Auto-generated method stub
		return getSNPinVarieties(var1, var2, null,null, chr, mode, isCore , snpposlist);
	}
	
	
	private String getNuc(int i) {
		switch (i) {
			case 0: return "A"; 
			case 1: return "T"; 
			case 2: return "G";
			case 3: return "C";
		}
		return "N";
	}

	
// ************************************* Methods for Phylogenetic tree construction ********************************************************************************	

	@Override
	public String[] constructPhylotree(String scale, String chr, int start, int end, String requestid) {
		return constructPhylotreeTopN(scale, chr, start, end, -1, requestid);
	}	


	@Override
	public String[] constructPhylotreeTopN(String scale, String chr, int start, int end, int topN,  String requestid) {
		
		//snpcount2linesService = (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService, "Snps2VarsCountMismatchDAO");
		snpcount2linesService = (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService, "SnpsString2VarsCountMismatchDAO");
		
		List<Snps2VarsCountmismatch>  mismatches = null;
		
		AppContext.startTimer();
		
		if(topN>0) {
			
			if(limitVarIds!=null && !limitVarIds.isEmpty())
				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN, limitVarIds);
			else	
				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN);
			
			AppContext.debug(mismatches.size() + " mismatch pairs");
			
			// get varieties in topN
			java.util.Iterator<Snps2VarsCountmismatch>  itdist = mismatches.iterator();		
			Set topVars =new java.util.HashSet();
			while(itdist.hasNext())
			{
				Snps2VarsCountmismatch dist3k = itdist.next();
				topVars.add( dist3k.getVar1());
				topVars.add( dist3k.getVar2());
			}
			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topVars);
			AppContext.resetTimer(" topN distance calc");
			
		}
		else {
			if(limitVarIds!=null && !limitVarIds.isEmpty())
				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), limitVarIds);
			else
				mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end));
			
			
			
			AppContext.resetTimer(" all distance calc");
		}
		
		int snps = -1;
		//List snps = null;
		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "SnpcoreRefposindexDAO") ; 
		if(isCore) {
			//snpcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService, "MvCoreSnpsDAO") ; 
			//snps = snpcoreallvarsposService.getSNPs(chr, start, end, null ).size();
			
			snps = snpstringallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KCORESNP  ).size();
			
			 
		 }
		 else {
			snps = snpstringallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KALLSNP   ).size();
			 //snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
			 // snps = snpallvarsposService.getSNPs(chr, start, end ,null ).size();
		 }
		 
		 
		 if(snps==0) return new String[] {"", "0","0"};
		
		//germplasms
		AppContext.debug(mismatches.size() + " mismatch pairs");
		
		java.util.Map<BigDecimal, Integer> mapName2Row = new java.util.HashMap<BigDecimal, Integer>();
		

		Set<BigDecimal> setWithMismatch = new java.util.TreeSet();
		
		//java.util.Map<String, BigDecimal> mapPairMismatch = new java.util.HashMap<String, BigDecimal>();
		java.util.Iterator<Snps2VarsCountmismatch>  itdist = mismatches.iterator();		
		while(itdist.hasNext())
		{
			
			Snps2VarsCountmismatch dist3k = itdist.next();
			//mapPairMismatch.put( dist3k.getVar1() + ":" + dist3k.getVar2(), dist3k.getMismatch());
			setWithMismatch.add( dist3k.getVar1());
			setWithMismatch.add( dist3k.getVar2());
		}
		
		
		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix( setWithMismatch.size() );
		
		AppContext.debug( setWithMismatch.size() + " unique names with mismatch");
		
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
		
		int i=0;
		Iterator<BigDecimal> itgerm = setWithMismatch.iterator();
		while(itgerm.hasNext()) {
			BigDecimal varid = itgerm.next();
			mapName2Row.put(varid , i);
			symdistmatrix.setIdentifier( i, "varid_" + varid );
			i++;
		}		

		AppContext.debug("symdistmatrix done");
		
		double distscale = 1.0; 
		
		java.util.Iterator<Snps2VarsCountmismatch>  itdist2 = mismatches.iterator();		
		while(itdist2.hasNext())
		{
			Snps2VarsCountmismatch dist3k = itdist2.next();
			double dist =0 ;
			if(snps > dist3k.getMismatch().intValue())
				dist =   dist3k.getMismatch().intValue()*distscale /( snps -  dist3k.getMismatch().intValue() );
			else
				dist = AppContext.getMaxPhylodistance();
			
			try {
				
				if(  mapName2Row.get(dist3k.getVar1()).equals(  mapName2Row.get(dist3k.getVar2()) ) ) {
					//AppContext.debug( mapName2Row.get(dist3k.getVar1()) + ": " +  mapVarid2Variety.get(dist3k.getVar1()) + "\t" +  mapName2Row.get(dist3k.getVar2()) + ": " +    mapVarid2Variety.get(dist3k.getVar2())  + "  -- " + dist);
					dist = 0.0;
				}
				
				symdistmatrix.setValue( mapName2Row.get(dist3k.getVar1()) , mapName2Row.get(dist3k.getVar2()) , dist );
				symdistmatrix.setValue( mapName2Row.get(dist3k.getVar2()) , mapName2Row.get(dist3k.getVar1()),  dist );
				
			} catch(Exception ex) {
				
				AppContext.debug( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t" + mapName2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +  mapName2Row.get(dist3k.getVar2()) + "\t" + dist );
			
			}
		}
		
		System.out.print(symdistmatrix.getSize() + " symdistmatrix ready");
		
		try {
			
			TreeConstructor tree = new  TreeConstructor(symdistmatrix,
				org.biojava3.phylo.TreeType.NJ ,
				org.biojava3.phylo.TreeConstructionAlgorithm.PID ,
			//	null);
				new org.biojava3.phylo.ProgessListenerStub());
				tree.process();

				AppContext.debug("process done");
			String newick = tree.getNewickString(false, true);
			
			
			Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
			
			//AppContext.debug(newick);
			Iterator<BigDecimal> itgerm2 = setWithMismatch.iterator();
			while(itgerm2.hasNext()) {
				BigDecimal c = itgerm2.next();
			
				Variety var = mapId2Variety.get(c);
				String subpop = "";
				if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
				String irisid = "";
				if( var.getIrisId()!=null) irisid=var.getIrisId();
				//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
			
			}
			AppContext.debug(newick);
			
			return new String[] {newick, Integer.toString(symdistmatrix.getSize()), Integer.toString( mismatches.size()) };
			
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
		
	}
	
		
	@Override
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile)
	{

		    mapVariety2PhyloOrder = new HashMap<BigDecimal,Integer>(); 

		    String treefilename = AppContext.getTempDir() + "/" + tmpfile + "newick";
		        File treefile = new File( treefilename );
		        PhylogenyParser parser = null;
		        try {
		            parser = ParserUtils.createParserDependingOnFileType( treefile, true );
		        	//parser = ParserUtils.createParserDependingOnSuffix( treefilename, false );

		            Phylogeny[] phys = null;

		            phys = PhylogenyMethods.readPhylogenies( parser, treefile );

		        
			        AppContext.debug("Newick postorder listing:");
			        Map<String,Variety> varname2var = varietyfacade.getMapVarname2Variety();
			        Map<String,Variety> irisid2var = varietyfacade.getIrisId2Variety();
			     
			        int leafcount = 0;
			        for(int iphy=0; iphy<phys.length; iphy++)
			        {
				        for(PhylogenyNodeIterator it = phys[iphy].iteratorPostorder(); it.hasNext(); ) {
				        	PhylogenyNode node = it.next();
				        	if(node.isExternal()) {
				        		
				        		String[] nodenames = node.getName().split("\\|");
				        		if(nodenames.length!=3) AppContext.debug("Invalid nodename " +  node.getName());
				        		
				        		if(nodenames.length>1 &&  !nodenames[1].isEmpty() ){
				        			Variety varNode =   irisid2var.get(nodenames[1].replace("_"," ").toUpperCase() );  //varietyfacade.getGermplasmByIrisId(nodenames[1].replace("_"," "));
				        			if(varNode!=null) {
				        				mapVariety2PhyloOrder.put( varNode.getVarietyId() , leafcount);
				        				leafcount++; 
				        			} else
				        			{
				        				AppContext.debug("cant resolve irisid " + nodenames[1]);
				        			}				        			
				        		}
				        		else if(!nodenames[0].isEmpty())
				        		{
				        			Variety varNode =  varname2var.get(nodenames[0].replace("_"," ").replace("//",""));
				        			if(varNode!=null) {
				        				mapVariety2PhyloOrder.put(  varNode.getVarietyId() , leafcount);
				        				leafcount++;
				        			} else
				        			{
				        				AppContext.debug("cant resolve variety name " + nodenames[0] );
				        			}
				        		}
				        	}
				        }
			        }
			        
			        // Display of the tree(s) with Archaeopteryx.													
			       // Archaeopteryx.createApplication( phys );
	
		        }
		        
		        catch ( final IOException e ) {
		            e.printStackTrace();
		            AppContext.debug(e.getMessage());
		            throw new RuntimeException("newick parse error");
		        }
		    
		    
		    AppContext.debug( "mapVariety2PhyloOrder.size()=" + mapVariety2PhyloOrder.size());
		    
			return mapVariety2PhyloOrder;

	}

	
// ********************************************* Methods to query SNPs as SNPString *********************************************************************************	
	
	
	@Override
	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start, Integer end, Integer chr) {
		
		listSNPAllVarsMismatches =  getSNPsString(chr, new BigDecimal(start), new BigDecimal(end),  null);
		return listSNPAllVarsMismatches;
		
	}

	@Override
	public List getSNPStringInAllVarieties(Set snpposlist, Integer chr) {
		// TODO Auto-generated method stub
		
		listSNPAllVarsMismatches = getSNPsString(chr, null, null,  snpposlist); 
		return listSNPAllVarsMismatches;
	}

	
	private List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start,
			BigDecimal end, Set setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
		// TODO Auto-generated method stub

		List listResult = new ArrayList();
		
		
		// get snp positions/index
		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "SnpcoreRefposindexDAO") ;
		
		
		AppContext.resetTimer("getSNPsString start");
		
		
		List listpos = null;
		if(setPositions!=null && !setPositions.isEmpty()) {
			listpos = new ArrayList();
			listpos.addAll(new TreeSet(setPositions));

			if(this.isCore)
				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(), listpos, SnpcoreRefposindexDAO.TYPE_3KCORESNP);
			else
				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(),  listpos, SnpcoreRefposindexDAO.TYPE_3KALLSNP);
			
			if(snpsposlist.size()!=listpos.size())
				AppContext.debug("snpsposlist.size()!=listpos.size():" + snpsposlist.size()+ "!=" + listpos.size());
				

		}
		else {
			if(this.isCore) {
				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(),  end.intValue(), SnpcoreRefposindexDAO.TYPE_3KCORESNP, -1, -1);
			}
			else
				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),   SnpcoreRefposindexDAO.TYPE_3KALLSNP, -1, -1);
		}
		
		if(snpsposlist.isEmpty()) return listResult;
		

		// get allele column indices from start to end positions
		VSnpRefposindex startpos =  (VSnpRefposindex)snpsposlist.get(0);
		VSnpRefposindex endpos =  (VSnpRefposindex)snpsposlist.get( snpsposlist.size()-1 );

		// if recount
		String strRef=null;
		Map<Float,Map> mapMis2Vars = new TreeMap();
		
		int refLength=-1;
		
		AppContext.debug( snpsposlist.size() + " snpposlist");
		
			
			int indxs[] = new int[snpsposlist.size()];
			int indxscount = 0;
			//Map<Long, Integer> mapPos2Idx = new HashMap();
			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
			Map<BigDecimal, Integer> mapSnpid2Idx = new HashMap();
			
			Iterator itSnppos =snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();
			
			while(itSnppos.hasNext()) {
				VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next(); 
				buffRef.append( snppos.getRefnuc());
				indxs[indxscount] =  snppos.getAlleleIndex().intValue();
				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
				mapSnpid2Idx.put(snppos.getSnpFeatureId(), indxscount);
				indxscount++;
			}
			strRef = buffRef.toString();
			refLength = strRef.length();
			
			
			String filename="";
			BigDecimal datatype=null;
			if(isCore) {
				filename = AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt";
				datatype = SnpcoreRefposindexDAO.TYPE_3KCORESNP;
			}
			else {
				filename = AppContext.getFlatfilesDir() +  "varsorted_allelestring_chr" + chr + ".txt" ; //allsnp_chr-" + chr + ".txt";
				datatype = SnpcoreRefposindexDAO.TYPE_3KALLSNP;
			}
			
			Map  mapVarid2Snpsstr = null;
			
			
			AppContext.resetTimer(" to get position indexes from database..");
			
			// get snpstring for each varieties
			// get allele2 for heterozygous varieties
			snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
			snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "SnpsNonsynAllvarsDAO");
			Set heteroSnps = null;
			Set nonsynAllele = null;
			
			
			
			if(listpos!=null && !listpos.isEmpty()) {
				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
					
					AppContext.resetTimer("using readSNPString1 start");
					mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  indxs , filename);
					AppContext.resetTimer("using readSNPString1 end");
					heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos, datatype);
				}
				else {
					AppContext.resetTimer("using readSNPString2 start");
					mapVarid2Snpsstr = readSNPString(chr,  indxs, filename);
					AppContext.resetTimer("using readSNPString2 end");
					heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosIn(chr, listpos, limitVarIds, datatype);
				}
				
				if(isNonsynOnly || isColorByNonsyn)
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
				
			}
			else {
				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() )
				{
					AppContext.resetTimer("using readSNPString3 start");
					mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
					AppContext.resetTimer("using readSNPString3 end");
					heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosBetween(chr, start, end, limitVarIds, datatype);
				}
				else {
					AppContext.resetTimer("using readSNPString4 start");
					mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
					AppContext.resetTimer("using readSNPString4 end");
					heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
				}
				
				AppContext.resetTimer("to read allele2 database..");
				
				if(isNonsynOnly|| isColorByNonsyn) {
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
					AppContext.resetTimer("to read nonsynonymous allele database..");
				}
			}
				
			//Map<Integer,Set<Character>> mapIdx2NonsynAlleles = new HashMap();
			mapIdx2NonsynAlleles = new HashMap();
			if(isNonsynOnly || isColorByNonsyn) {
				
				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
				while(itNonsyn.hasNext()) {
					SnpsNonsynAllele nonsynallele = itNonsyn.next();
					Set<Character> alleles =  mapIdx2NonsynAlleles.get( mapSnpid2Idx.get( nonsynallele.getSnp() )  );
					if(alleles==null) {
						alleles = new HashSet();
						mapIdx2NonsynAlleles.put( mapSnpid2Idx.get( nonsynallele.getSnp() )  , alleles);
					}
					alleles.add( nonsynallele.getAllele() );
				}
			}
			
			if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapSnpid2Idx.size() + " nonsys alleles positions/idx");
			
			Map<BigDecimal, boolean[]> mapVar2NonsynFlags = new HashMap();
			
			Iterator<BigDecimal> itVar = mapVarid2Snpsstr.keySet().iterator();
			while(itVar.hasNext()) {
				BigDecimal var = itVar.next();
				String snpstr = (String)mapVarid2Snpsstr.get(var);

					boolean isnonsyn[] = new boolean[refLength];
					if(isNonsynOnly || isColorByNonsyn) {
						boolean includeVar = false;
						for(int iStr=0; iStr<refLength; iStr++) {
							char charatistr=snpstr.charAt(iStr);
							if(charatistr=='0' || charatistr=='.'  || charatistr=='*') 
								isnonsyn[iStr]= false;
							else {
								
								Set<Character> nonsynalleles =  mapIdx2NonsynAlleles.get(iStr);
								if(nonsynalleles==null) {
									isnonsyn[iStr]= false;
								} else {
									if(nonsynalleles.contains( charatistr ) ) {
										isnonsyn[iStr]= true;
										includeVar = true;
									}
									else
										isnonsyn[iStr]= false;
								}
							}
						}
						
						if(!includeVar && isNonsynOnly) {
							// do not include variety
							AppContext.debug("var " + var + " all synonymous or unknowns");
							continue;
						}
					}
					
					float misCount = 0;
					for(int iStr=0; iStr<refLength; iStr++) {
						char charatistr=snpstr.charAt(iStr);
						if(strRef.charAt(iStr)==charatistr) {}
						else if(charatistr!='0' && charatistr!='.'  && charatistr!='*' && charatistr!='$') misCount++;
						
						//if(charatistr!='0' && charatistr!='.'  && charatistr!='*' && charatistr!='$' && strRef.charAt(iStr)!=charatistr) misCount++;
					}
					if(misCount>0) {
						
						Map mapVarId2Snpstr = mapMis2Vars.get(-misCount);
						if(mapVarId2Snpstr==null) {
							mapVarId2Snpstr = new TreeMap();
							mapMis2Vars.put(-misCount,  mapVarId2Snpstr );
						}
						mapVarId2Snpstr.put( var,  new SnpsStringAllvarsImpl(var,Long.valueOf(chr), snpstr,  BigDecimal.valueOf(misCount) ));
						mapVar2NonsynFlags.put(var, isnonsyn  );
					} 
			}	
			
			
			// sort included varieties
			
			
			Map mapVarid2Var = varietyfacade.getMapId2Variety();
			
			Iterator itMisNew = mapMis2Vars.keySet().iterator();
			mapVariety2Order = new HashMap();
			mapIdx2Nonsynflags = new HashMap();
			
			int ordercount = 0;
			while(itMisNew.hasNext()) {
				Map mapVarId2Snpstr = mapMis2Vars.get( itMisNew.next() );
				
				// sort var by subpop, then country, then name here
				itVar = mapVarId2Snpstr.keySet().iterator();
				Set sortedVarieties = new TreeSet(new VarSubpopCntrySorter());
				while(itVar.hasNext()) {
					BigDecimal varid = itVar.next();
					sortedVarieties.add( mapVarid2Var.get(varid) );
				}
				
				Iterator<Variety> itVariety = sortedVarieties.iterator();
				while(itVariety.hasNext()) {
					Variety varid = itVariety.next();
					
					listResult.add(  mapVarId2Snpstr.get(varid.getVarietyId()) );
					mapVariety2Order.put(varid.getVarietyId() ,ordercount);
					mapIdx2Nonsynflags.put( ordercount ,  mapVar2NonsynFlags.get(varid.getVarietyId() ));
					
					ordercount++;
				}
				
				/*itVar = mapVarId2Snpstr.keySet().iterator();
				while(itVar.hasNext()) {
					BigDecimal varid = itVar.next();
					
					listResult.add(  mapVarId2Snpstr.get(varid) );
					mapVariety2Order.put(varid ,ordercount);
					ordercount++;
				}
				*/
			}
			
			heteroAlleleMatrix = new char[mapVariety2Order.size()][snpsposlist.size()];
			
			AppContext.debug(heteroSnps.size() + " allele2, " + mapVarid2Snpsstr.size() + " all varieties, " +  mapVariety2Order.size() + " non-syn varieties, " + snpsposlist.size() + " alleles");
			
			Iterator<SnpsHeteroAllele2> itSnp = heteroSnps.iterator();
			while(itSnp.hasNext()) {
				SnpsHeteroAllele2 snpallele2 = itSnp.next();
				
				//long pos = snpallele2.getSnp().longValue() % 100000000;
				Integer varidx = mapVariety2Order.get( snpallele2.getVar() );
				//if( varidx!=null)  heteroAlleleMatrix[ varidx ][ mapPos2Idx.get( pos ) ] = snpallele2.getNuc();
				
				if( mapSnpid2Idx.get( snpallele2.getSnp() )==null) {
					//AppContext.debug("snpid=" +  snpallele2.getSnp() + " in hetero not in core SNPs");	
					//throw new RuntimeException("mapSnpid2Idx.get( snpallele2.getSnp() )==null");
					continue;
				} 
				
				if( varidx!=null)  heteroAlleleMatrix[ varidx ][ mapSnpid2Idx.get( snpallele2.getSnp() ) ] = snpallele2.getNuc();
			}
			
			AppContext.resetTimer("to prepare for display:.. count mismatches, sort, create Allele2 matrix, create nonsynonymous flag matrix ...");
			
			
		return listResult;
		
	}
	
	
	
	@Override
	public char[][] getHeteroAlleleMatrix() {
		return heteroAlleleMatrix;
	}


	/**
	 * Sorts variety by subpopulation, then country, then id
	 * Used in Mismatch ordering for the same number of mismatch,
	 * assuming variety from same subpopulation, then country will be closer relative than random 
	 * @author lmansueto
	 *
	 */
	class VarSubpopCntrySorter implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			Variety v1 =(Variety)o1;
			Variety v2 =(Variety)o2;
			if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
				return v1.getSubpopulation().compareTo(v2.getSubpopulation());
			if(v1.getCountry()!=null && v2.getCountry()!=null)
				return v1.getCountry().compareTo(v2.getCountry());			
			if(v1.getName()!=null && v2.getName()!=null)
				return v1.getName().compareTo(v2.getName());			
			return v1.getVarietyId().compareTo(v2.getVarietyId()) ;
		}
		
	}
	
	
// ********************************************* Flatfile SNPString Readers *********************************************************************************	
	
	public static Map readSNPString(int chr,  int startIdx,  int endIdx,  String filename) {
		
		/*
		if(limitVarIds!=null && !limitVarIds.isEmpty()){
			return readSNPString(limitVarIds,  chr,   startIdx,   endIdx, filename); 
		}
		*/
		
		Map mapVarid2Snps = new HashMap();
		
		//File file = new File( AppContext.getFlatfilesDir() + "chr-" + chr + ".txt");
		File file = new File( filename);
		try {
		RandomAccessFile raf = new RandomAccessFile(file,"r");
		
		String firstline = raf.readLine();
		AppContext.debug("File has " + firstline.length() + " alleles");
		AppContext.debug("reading for 3000 rows/varieties from " +  startIdx + "-" + endIdx + " (" + (endIdx-startIdx+1) + ") cols/snps");
		
		long rowlength = firstline.length() + 1;
		// return to start
		raf.seek(0);
		
		byte readBuffer[] = new byte[endIdx-startIdx+1];

		for(int varid=1; varid<=3000; varid++) {
			raf.seek(  (varid-1)*rowlength + startIdx -1 );
			raf.read( readBuffer );
			mapVarid2Snps.put(BigDecimal.valueOf(varid) , new String(readBuffer));
		}
		
		raf.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return mapVarid2Snps;
		
		
	}
	
	public static Map readSNPString(int chr,  int posIdxs[],  String filename) {
		
		/*
		if(limitVarIds!=null && !limitVarIds.isEmpty()){
			return readSNPString(limitVarIds,  chr,   startIdx,   endIdx, filename); 
		}
		*/
		
		Map mapVarid2Snps = new HashMap();
		
		//File file = new File( AppContext.getFlatfilesDir() + "chr-" + chr + ".txt");
		File file = new File( filename);
		try {
		RandomAccessFile raf = new RandomAccessFile(file,"r");
		
		String firstline = raf.readLine();
		AppContext.debug("File has " + firstline.length() + " alleles");
		AppContext.debug("reading for 3000 rows/varieties for (" + posIdxs.length + ") cols/snps");
		
		long rowlength = firstline.length() + 1;
		// return to start
		raf.seek(0);
		
		byte readBuffer[] = new byte[1];

		for(int varid=1; varid<=3000; varid++) {
			StringBuffer readSNPBuffer = new StringBuffer();
			for(int iIdx=0; iIdx<posIdxs.length; iIdx++) {
				raf.seek(  (varid-1)*rowlength + posIdxs[iIdx]-1 );
				raf.read( readBuffer );
				readSNPBuffer.append( new String(readBuffer)); 
			}
			if(readSNPBuffer.length()!=posIdxs.length) {
				AppContext.debug("readSNPBuffer.length()!=posIdxs.length:" + readSNPBuffer.length() +"!=" + posIdxs.length);
			}
			mapVarid2Snps.put(BigDecimal.valueOf(varid) , readSNPBuffer.toString() );
		}
		
		raf.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return mapVarid2Snps;
		
		
	}
	
	public static Map readSNPString(Set colVarids, int chr,  int posIdxs[],  String filename) {
		
		// order varids based on file ordering for 1pass/smooth disk read
		Set orderedVarids = new TreeSet(colVarids);
		
		Map mapVarid2Snps = new HashMap();
		
		//File file = new File(AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt");
		File file = new File( filename);
		try {
		RandomAccessFile raf = new RandomAccessFile(file,"r");
		
		String firstline = raf.readLine();
		AppContext.debug("File has " + firstline.length() + " alleles");
		AppContext.debug("reading for " + colVarids.size() + " rows/varieties for (" + posIdxs.length + ") cols/snps");
		
		long rowlength = firstline.length() + 1;
		// return to start
		raf.seek(0);
		
		Iterator<BigDecimal> itVarid = orderedVarids.iterator();
		
		byte readBuffer[] = new byte[1];
		while(itVarid.hasNext()) {
			BigDecimal bdVarid = itVarid.next(); 
			int varid = bdVarid.intValue();
			StringBuffer readSNPBuffer = new StringBuffer();
			for(int iIdx=0; iIdx<posIdxs.length; iIdx++) {
				raf.seek(  (varid-1)*rowlength + posIdxs[iIdx]-1 );
				raf.read( readBuffer );
				readSNPBuffer.append( new String(readBuffer)); 
			}
			if(readSNPBuffer.length()!=posIdxs.length) {
				AppContext.debug("readSNPBuffer.length()!=posIdxs.length:" + readSNPBuffer.length() +"!=" + posIdxs.length);
			}
			mapVarid2Snps.put(bdVarid , readSNPBuffer.toString() );
		}
			
		raf.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
		return mapVarid2Snps;
		
	}


	
	public static Map readSNPString(Set colVarids, int chr,  int startIdx, int endIdx,  String filename) {
		
		// order varids based on file ordering for 1pass/smooth disk read
		Set orderedVarids = new TreeSet(colVarids);
		
		Map mapVarid2Snps = new HashMap();
		
		//File file = new File(AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt");
		File file = new File( filename);
		try {
		RandomAccessFile raf = new RandomAccessFile(file,"r");
		
		String firstline = raf.readLine();
		AppContext.debug("File has " + firstline.length() + " alleles");
		AppContext.debug("reading for " + colVarids.size() + " rows/varieties for" +  startIdx + "-" + endIdx + " (" + (endIdx-startIdx+1) + ") cols/snps");
		
		long rowlength = firstline.length() + 1;
		// return to start
		raf.seek(0);
		
		Iterator<BigDecimal> itVarid = orderedVarids.iterator();
		
		byte readBuffer[] = new byte[endIdx-startIdx+1];
		while(itVarid.hasNext()) {
			BigDecimal bdVarid = itVarid.next(); 
			int varid = bdVarid.intValue();
			raf.seek(  (varid-1)*rowlength + startIdx-1 );
			raf.read( readBuffer );
			mapVarid2Snps.put(bdVarid , new String(readBuffer) );
		}
			
		raf.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return mapVarid2Snps;
		
	}




	@Override
	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type) {
		
		// TODO Auto-generated method stub
		
		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean( snpstringallvarsposService, "VSnpRefposindexDAO");
		//snpstringallvarsposService.
		List listtmp = new ArrayList();
		listtmp.addAll(setSNP);
		return new HashSet(snpstringallvarsposService.getSNPsInChromosome(chr, listtmp, type));

	}

	// SNP query from the SNP_GENOTYPE table (old implementation)
	
	@Override
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches() {
		return listSNPAllVarsMismatches;
	}

	@Override
	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(int firstRow, int numRows) {
		List newlist = new java.util.ArrayList();
		if(numRows==0) {
			//numRows=newlist.size();
			numRows = listSNPAllVarsMismatches.size() - firstRow  +1 ;
		}

		for(int i=firstRow-1; i<firstRow-1+numRows && i<listSNPAllVarsMismatches.size() ; i++)
				newlist.add(listSNPAllVarsMismatches.get(i));
		
		AppContext.debug("created page with " + newlist.size() + " varieties");
		return newlist;
	}


	
	
	
}
