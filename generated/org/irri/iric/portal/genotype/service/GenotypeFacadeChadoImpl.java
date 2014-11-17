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
//public class GenotypeFacadeChadoImpl {

	private static final Log log = LogFactory.getLog(GenotypeFacadeChadoImpl.class);
	//private static String onlymismatchsql = " (REFNUC is not null or VAR1NUC is not null) and  REFNUC<>VAR1NUC and  ";
	//private static String onlymismatchsql = "";
	
	
	// Values updated on call to getSNPinAllVarieties w/ firstRow=1
	//private List<SnpsAllvarsRefMismatch> listSNPAllVarsMismatches;
	
	
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
	//private Set<SnpsNonsynAllele> nonsynAllele;
	private Map<Integer,Set<Character>>  mapIdx2NonsynAlleles;
	private Map<Integer,boolean[]>  mapIdx2Nonsynflags;
	
	private char[][] heteroAlleleMatrix;
	//private boolean[][] nonsynonymousAlleleMatrix;
	//private List<boolean[]> listNonsynFlags;
	
	
//	private java.util.List<String> varnames;
//	private java.util.List<String> genenames;
	
	
	@Autowired
	private ApplicationContext appContext;
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
	
	@Autowired
	//@Qualifier("SnpsAllvarsDAO")
	@Qualifier("VSnpAllvarsMinDAO")  // no snp_genotype_id
	private  SnpsAllvarsDAO snpallvarsService; // = new Snp2linesHome();
	
	@Autowired
	@Qualifier("SnpsAllvarsPosDAO")
	private  SnpsAllvarsPosDAO snpallvarsposService; // = new Snp2linesHome();

	@Autowired
	@Qualifier("MvCoreSnpsDAO")		// using core snps
	private SnpsAllvarsPosDAO snpcoreallvarsposService;


	
	
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
	private SnpsAllvarsPosDAO snpstringcoreallvarsposService;

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
	
	
	public GenotypeFacadeChadoImpl() {
		super();
		// TODO Auto-generated constructor stub
		
		System.out.println("created GenotypeFacadeChadoImpl: " + this );

	}
	
	
	


	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches() {
		return listSNPAllVarsMismatches;
	}

	public List<SnpsAllvarsRefMismatch> getListSNPAllVarsMismatches(int firstRow, int numRows) {
		List newlist = new java.util.ArrayList();
		if(numRows==0) {
			//numRows=newlist.size();
			numRows = listSNPAllVarsMismatches.size() - firstRow  +1 ;
		}

		for(int i=firstRow-1; i<firstRow-1+numRows && i<listSNPAllVarsMismatches.size() ; i++)
				newlist.add(listSNPAllVarsMismatches.get(i));
		
		System.out.println("created page with " + newlist.size() + " varieties");
		return newlist;
	}

	
	
	/**
	 * Get Gene object from name
	 */
	
	@Override
	public Gene getGeneFromName(String name) {
		// TODO Auto-generated method stub
		
		// temporary.. findGeneByName is case-sensitive??
		// return mapName2Gene.get(name.toUpperCase());
		
		return geneservice.findGeneByName(name);
	}



	
	/**
	 * Get varietry names 
	 */
	
	@Override
	public List<String> getVarnames() {
		// TODO Auto-generated method stub
		//if(varservice==null) throw new RuntimeException("varservice==null");		
		
		//return  mockGetVarnames() ;  // mocked db read; comment this in actual
		
		
		return varietyfacade.getVarietyNames();
		
	}
	
	@Override
	public List<String>getSubpopulations() {
		return varietyfacade.getSubpopulations();
	}
	 
	/**
	 * Use for development only
	 */
/*
	private List<String> mockGetVarnames() {
		if(varnames==null) {
			
			//if(varservice==null) throw new RuntimeException("varservice==null");
			
			
			varnames = new java.util.ArrayList();		

			 varnames.add("LABRA");
			 varnames.add("LEAD");
			 varnames.add("LEK");
			 varnames.add("LEKATAN");
			 varnames.add("LATA MUNA");
			 varnames.add("LAYANDABU");
			 
							
			}

		return varnames;	
	}
	*/

	/**
	 * Get all gene names
	 */
	@Override
	public List<String> getGenenames() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO");
		return listitemsDAO.getGenenames();
	}
	/*
	public List<String> getGenenamesOld() {
		
		if (geneservice==null) throw new java.lang.RuntimeException("geneservice==null");
 		
		//return mockGetGenenames() ; // mocked.. comment this and uncomment the next codes in actual
		
		
		// TODO Auto-generated method stub
		if(genenames==null || genenames.size()==0) {

			genenames = new java.util.ArrayList();
			
	
				
			java.util.Iterator<Gene> it = geneservice.findAllGene().iterator();
		    while(it.hasNext()) {
		    	Gene gene = it.next();
		    	
		    	String genename = gene.getUniquename();
		    	
		    	//mapName2Gene.put(genename.toUpperCase(), gene);
		    	
		    	genenames.add(genename.toUpperCase());
		    	genenames.add(genename.toLowerCase());
		    }
			
		}
		return genenames;
		
				
	} */
	
	/**
	 * used in development only
	 * @return
	 */
/*
	private List<String> mockGetGenenames() {
		// TODO Auto-generated method stub
		if(genenames==null) {
			if (geneservice==null) throw new java.lang.RuntimeException("geneservice==null");
			genenames = new java.util.ArrayList();
			

			genenames.add("LOC_Os01g01010");
			genenames.add("LOC_Os01g01019");
			genenames.add("LOC_Os01g01030");
			genenames.add("LOC_Os01g01040");
			genenames.add("LOC_Os01g01050");
			genenames.add("LOC_Os01g01060");
			genenames.add("LOC_Os01g01070");
			genenames.add("LOC_Os01g01080");
			genenames.add("LOC_Os01g01090");
			genenames.add("LOC_Os01g01520");			
			
		}
		return genenames;
	}
*/	
	/**
	 * Get chromosome names, mocked! replace with DB read later
	 */

	@Override
	//public java.util.Map<String,Integer> getChromosomes() {
	public java.util.List<String> getChromosomes() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

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

	
	
	
	public List<SnpsAllvarsPos> getSnpsposlist() {
		

		
		return snpsposlist;
		/*
		if(snpsposlist==null) {

			snpallvarsposService = (IViewSnpAllvarsPosHome)AppContext.checkBean(snpallvarsposService, "IViewSnpAllvarsPosHome") ; 
			
			String sql = "select CHR, POS, REFNUC from VIEW_SNP_ALLVARS_POS where ";
			  sql += " chr=" + chromosome + " and pos between " +
					(startPos -1) + " and " + (endPos + 1) + " order by POS";
			  
			snpsposlist = snpallvarsposService.executeSQL(sql);
			
			System.out.println(sql);
			System.out.println(snpsposlist.size() + " positions");
			System.out.println(sql);
			System.out.println(snpsposlist.size() + " snpsposlist");
		}
		
		return snpsposlist;
		*/
	}

	/*
	@Override
	public countSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr) {
		
	}
	*/
	
	
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
	
	public List<SnpsAllvarsRefMismatch> countSNPMismatchesInSetVarieties(Set setvarIds, Integer startPos, Integer endPos, String chromosome, boolean update)
	{
		if(update || listSNPAllVarsMismatches==null) {
			snpcountallvarsService = (SnpsAllvarsRefMismatchDAO)AppContext.checkBean(snpcountallvarsService, "SnpsAllvarsRefMismatchDAO") ; 
			
			if(setvarIds==null || setvarIds.isEmpty())
				listSNPAllVarsMismatches = snpcountallvarsService.countMismatches( chromosome,  startPos,  endPos, isCore);
			else {
				System.out.println("countMismatchesInvars  setvarIds:" + setvarIds);
				listSNPAllVarsMismatches = snpcountallvarsService.countMismatchesInvars(setvarIds, chromosome,  startPos,  endPos, isCore);
			}
		}
		return listSNPAllVarsMismatches;
	}
	
	
	/**
	 * Get the SNP alleles for ALL varieties
	 * for ALL varieties
	 */
	@Override
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome)
	{
		 return getSNPinAllVarieties(startPos, endPos, chromosome, 1,0); 
	}
	
	public List<SnpsAllvars> getSNPinSetVarieties(Set varids, Integer startPos, Integer endPos, String chromosome)
	{
		 return getSNPinSetVarieties(varids, startPos, endPos, chromosome, 1,0); 
	}
	
	
	/**
	 * Get the SNP alleles for ALL varieties
	 * startPos	start position
	 * endPos	end position
	 * chromosome
	 * 	
	 * firstRow  1-indexed, first variety, 1 triggers recount mismatches
	 * numRows	number of varieties to show per page, 0 to query all
	 * 
	 * 
	 * 
	 * if(firstRow==1) {
	 * 		listSNPAllVarsMismatches=countSNPMismatchesInAlllVarieties( startPos,  endPos,  chromosome, true);
			mapOrder2Variety = new java.util.HashMap<Integer,String>();
			mapVariety2Order = new java.util.HashMap<String,Integer>();
			mapVariety2Mismatch = new java.util.HashMap<String,Integer>();		
			}
	 * 
	 * for(long iVariety= firstRow-1;  iVariety<lastRow;  iVariety++ ) 
	 * 		construct query for firstRow to lastRow
	 * 		add varieties to mapOrder2Variety, mapVariety2Order, mapVariety2Mismatch
	 * 
	 * 
	 * 	 		
	 */
	
	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) {
		return getSNPinAllVarieties(startPos, endPos, chromosome, firstRow, numRows,AppContext.isSNPAllvarsFetchMismatchOnly()); 
	}
	public List<SnpsAllvars> getSNPinSetVarieties(Set varids, Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) {
		return getSNPinSetVarieties(varids, startPos, endPos, chromosome, firstRow, numRows,AppContext.isSNPAllvarsFetchMismatchOnly()); 
	}
	
	

	public List<SnpsAllvars> getSNPinAllVarieties(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean fetchMismatchOnly) {
		 return getSNPinSetVarieties(null,  startPos,  endPos,  chromosome,  firstRow,  numRows,  fetchMismatchOnly); 
	}
	
	public List<SnpsAllvars> getSNPinAllVarieties(Set setvarIds, Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean fetchMismatchOnly) {
		return getSNPinSetVarieties(setvarIds, startPos, endPos,  chromosome, firstRow, numRows, fetchMismatchOnly);
	}
	

	
	
	
	/*
	public List<SnpsAllvars> getSNPinAllVarietiesOld(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows, boolean fetchMismatchOnly) {
		// TODO Auto-generated method stub

		if(startPos<0)  startPos=0;
	
		// if first row=1, reset variety-mismatch sort order,  
		if(firstRow==1 ) {
			listSNPAllVarsMismatches=countSNPMismatchesInAlllVarieties( startPos,  endPos,  chromosome, true);
			
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
			
			SnpsAllvarsRefMismatch varmismatch = listSNPAllVarsMismatches.get((int)iVariety);
			
			mapOrder2Variety.put( Integer.valueOf((int)iVariety),  varmismatch.getVar()  );
			mapVariety2Order.put( varmismatch.getVar(), Integer.valueOf((int)iVariety)   );
			mapVariety2Mismatch.put( varmismatch.getVar(), varmismatch.getMismatch().intValue()  );
			varsMismatch.add(  varmismatch.getVar() );
		}
		
		 

			snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
			snpsposlist = snpallvarsposService.getSNPs(chromosome, startPos, endPos );
			
			if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");

			snpallvarsService = (SnpsAllvarsDAO)AppContext.checkBean(snpallvarsService, "VSnpAllvarsMinDAO") ; 
			
			Set<SnpsAllvars> snpslist; 
			
			if(fetchMismatchOnly)
				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetweenRefmismatch( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch ); 
			else
				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetween( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch); 
			

			log.debug( "snpslist Results: " + snpslist.size() ); 
			
			List<SnpsAllvars> list = new java.util.ArrayList<SnpsAllvars>();
			list.addAll(snpslist);
			return list;
	}
	*/
	
	



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
	/*
	public List<SnpsAllvars> getSNPinAllVarietiesPivot(Integer startPos, Integer endPos, String chromosome, long firstRow, long numRows) {
		// TODO Auto-generated method stub

		if(startPos<0)  startPos=0;
		
		// if first row, get the new variety-mismatch sort order
		if(firstRow==1 ) {
			listSNPAllVarsMismatches=countSNPMismatchesInAlllVarieties( startPos,  endPos,  chromosome, true);
			
			mapOrder2Variety = new java.util.HashMap<Integer,BigDecimal>();
			mapVariety2Order = new java.util.HashMap<BigDecimal,Integer>();
			mapVariety2Mismatch = new java.util.HashMap<BigDecimal,Integer>();		
			
		}
		
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
		
		for(long iVariety= firstRow-1;  iVariety<lastRow;  iVariety++ ) {
			
			SnpsAllvarsRefMismatch varmismatch = listSNPAllVarsMismatches.get((int)iVariety);
			mapOrder2Variety.put( Integer.valueOf((int)iVariety),  varmismatch.getVar()  );
			mapVariety2Order.put( varmismatch.getVar(), Integer.valueOf((int)iVariety)   );
			mapVariety2Mismatch.put( varmismatch.getVar(), varmismatch.getMismatch().intValue()  );
			varsMismatch.add(  varmismatch.getVar() );
		}
		
		try {

			snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
			snpsposlist = snpallvarsposService.getSNPs(chromosome, startPos, endPos );
			if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");

			snpallvarsService = (SnpsAllvarsDAO)AppContext.checkBean(snpallvarsService, "VSnpAllvarsMinDAO") ; 
			
			// use sql query because this object is mapped from a view without primary key
			
			String  sql2 = "";
			Set<SnpsAllvars> snpslist; 
			
			snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetween( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch); 
	
			log.debug( "snpslist Results: " + snpslist.size() ); 
			
			List<SnpsAllvars> list = new java.util.ArrayList<SnpsAllvars>();
			list.addAll(snpslist);
			return list;
		}
		catch (RuntimeException re) {
				log.error("getSNPinAllVarieties failed:", re);
				throw re;
		}

	}
	*/
	
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
		// TODO Auto-generated method stub

		//var1 = var1.split("::")[0].trim();
		//var2 = var2.split("::")[0].trim();
				
		//varservice = (VarietyDAO)AppContext.checkBean(varservice, "VarietyDAO");
		
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<String,Variety> mapVarname2Variety = varietyfacade.getMapVarname2Variety();
		
		try {
			
			// use sql query because this object is mapped from a view without primary key
			/*
			String sql = "select VAR1, VAR2, CHR, POS, REFNUC, VAR1NUC, VAR2NUC from V_SNP_2VARS where ";
			
			if(!var1.isEmpty())
			{
				sql += " var1='" + var1 + "' ";
				if(!var2.isEmpty())
					sql += " and var2='" + var2 + "' ";
			} else
			{
				if(!var2.isEmpty())
					sql += " var2='" + var2 + "' ";				
			}
				 
			  sql += " and chr=" + chromosome + " and pos between " +
					(startPos -1) + " and " + (endPos + 1);

			if(querymode==snpQueryMode.SNPQUERY_VARIETIES) {
				sql += " and VAR1NUC<>VAR2NUC ";
			}
			else if(querymode== snpQueryMode.SNPQUERY_REFERENCE) {
				sql += " and (REFNUC<>VAR1NUC or REFNUC<>VAR2NUC) ";
			}
            
			// checks in case snp2linesService is null!			
			
			log.debug( "Querying: " + sql); 
			
			List<Snps2Vars> snpslist = snp2linesService.executeSQL(sql,"");

			*/

			Set<Snps2Vars> snpslist=null;
			
			System.out.println("finding vars " +  var1 + "      " + var2 );
			
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

			System.out.println( "Results: " + snpslist.size() ); 
			
			List list = new ArrayList();
			list.addAll(snpslist);
			return list;
		}
		catch (RuntimeException re) {
				log.error("getSNPinVarieties failed:", re);
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
	
	
	
	@Override
	public List<SnpsAllvars> getSNPinAllVarieties(String genename, Integer plusminusBp) {
		// TODO Auto-generated method stub
		
		Gene gene = getGeneFromName( genename );
		log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
		
		return getSNPinAllVarieties(gene.getFmin() , gene.getFmax(), gene.getChr().toString());
		
	}
	
	
	/*
	@Override
	public List<Snps2Vars> getSNPinVarieties(String var1, String var2,
			Integer startPos, Integer endPos, String chromosome,
			snpQueryMode querymode, boolean isCore) {
		
		// TODO Auto-generated method stub
		return null;
	}
	 */






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
	
	
/**
 * mock, for testing
 */
	/*
	@Override
	public List<Snps2Vars> getSNPinVarieties(int n) {
		// TODO Auto-generated method stub
		java.util.Random rand = new java.util.Random();
		
		
		java.util.List<Snp2lines> snps = new java.util.ArrayList<Snp2lines>();
		for(int i=0; i<n; i++)
		{
			Snps2Vars snp1 = new Snp2linesId();
			snp1.setVar1nuc(getNuc(rand.nextInt(4) ));
			snp1.setVar2nuc(getNuc(rand.nextInt(4) ));
			snp1.setChr(  Short.valueOf(Integer.toString(rand.nextInt(12)+1 ) ) ); 
			snp1.setPos( Long.valueOf( rand.nextInt(10000)+1) );
			snp1.setRefnuc(getNuc(rand.nextInt(4)));		
			snp1.setVar1("IR64");
			snp1.setVar2("AZUCENA");
			
			snps.add(new Snp2lines(snp1));
		}
		return snps;		
	}
*/

	private String getNuc(int i) {
		switch (i) {
			case 0: return "A"; 
			case 1: return "T"; 
			case 2: return "G";
			case 3: return "C";
		}
		return "N";
	}


	
	/*
	 * 	
	 * Subset of SNPs

Not correlating (cc>.9) and closely located
(dist<100000 bps) at the same time

Major allele frequency <.95

Covers >=70% of varieties

Subset of ~100K SNPs often desirable for many analysis and visualizations.
These are the criteria to use for filtering.

SNP and other type of variants

Given two (or from 3 to 10) varieties and genome
region/gene find all SNPs between the varieties

Given genome region /gene find all SNPs for

–

–

Given a SNP ( or from 2 to 10 SNPs) find alleles for all
varieties

Visualize all SNPs for all varieties

Visualize all the results in Jbrowse or other genome
browser together with gene structures

Distinguish synonymous and non-synonymous SNPs

all varieties

within a subpopulation group

	 */
	 
	
	
public String[] constructPhylotree(String scale, String chr, int start, int end, String requestid) {
	return constructPhylotreeTopN(scale, chr, start, end, -1, requestid);
}	


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
			
			System.out.println(mismatches.size() + " mismatch pairs");
			
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
		snpstringcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringcoreallvarsposService, "SnpcoreRefposindexDAO") ; 
		if(isCore) {
			//snpcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService, "MvCoreSnpsDAO") ; 
			//snps = snpcoreallvarsposService.getSNPs(chr, start, end, null ).size();
			
			snps = snpstringcoreallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KCORESNP  ).size();
			
			 
		 }
		 else {
			snps = snpstringcoreallvarsposService.getSNPs(chr, start, end,  SnpcoreRefposindexDAO.TYPE_3KALLSNP   ).size();
			 //snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
			 // snps = snpallvarsposService.getSNPs(chr, start, end ,null ).size();
		 }
		 
		 
		 if(snps==0) return new String[] {"", "0","0"};
		
		//germplasms
		System.out.println(mismatches.size() + " mismatch pairs");
		
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
		
		System.out.println( setWithMismatch.size() + " unique names with mismatch");
		
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

		System.out.println("symdistmatrix done");
		
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
					//System.out.println( mapName2Row.get(dist3k.getVar1()) + ": " +  mapVarid2Variety.get(dist3k.getVar1()) + "\t" +  mapName2Row.get(dist3k.getVar2()) + ": " +    mapVarid2Variety.get(dist3k.getVar2())  + "  -- " + dist);
					dist = 0.0;
				}
				
				symdistmatrix.setValue( mapName2Row.get(dist3k.getVar1()) , mapName2Row.get(dist3k.getVar2()) , dist );
				symdistmatrix.setValue( mapName2Row.get(dist3k.getVar2()) , mapName2Row.get(dist3k.getVar1()),  dist );
				
			} catch(Exception ex) {
				
				System.out.println( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t" + mapName2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +  mapName2Row.get(dist3k.getVar2()) + "\t" + dist );
			
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

				System.out.println("process done");
			//tree.getN
			String newick = tree.getNewickString(false, true);
			
			
			Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
			
			//System.out.println(newick);
			Iterator<BigDecimal> itgerm2 = setWithMismatch.iterator();
			while(itgerm2.hasNext()) {
				BigDecimal c = itgerm2.next();
				//String newc = c.replace(' ', '_');
				// replace iris_id to varnames
				//String varname  = mapIRISId2Varname.get(c);
				//newick = newick.replace(c,  mapIRISId2Varname.get(c).replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
				//String subpop = mapVarname2IRISId.get(varname)[2];
				//if(subpop == null) subpop = ""; 
				
				
				//Variety var = mapVarid2Variety.get(c);
				//String subpop = "";
				//if( var.getSubpopulation()!=null) subpop = "/" +  var.getSubpopulation();
				//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + var.getIrisId() + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );

			
				Variety var = mapId2Variety.get(c);
				String subpop = "";
				if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
				String irisid = "";
				if( var.getIrisId()!=null) irisid=var.getIrisId();
				//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
				
			
			
			}
			
			
			System.out.println(newick);
			
			
			
			//System.out.println(newick);
			return new String[] {newick, Integer.toString(symdistmatrix.getSize()), Integer.toString( mismatches.size()) };
			
			
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	            //   NJTreeProgressListener _treeProgessListener)
		
	}
	

public String[] constructPhylotreeTopNOrig(String scale, String chr, int start, int end, int topN) {
	
	snpcount2linesService = (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService, "Snps2VarsCountMismatchDAO");
	
	List<Snps2VarsCountmismatch>  mismatches = null;
	
	AppContext.startTimer();
	
	if(topN>0) {
		
		if(limitVarIds!=null && !limitVarIds.isEmpty())
			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN, limitVarIds);
		else	
			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end), topN);
		
		System.out.println(mismatches.size() + " mismatch pairs");
		
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
	 if(isCore) {
		snpcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpcoreallvarsposService, "MvCoreSnpsDAO") ; 
		snps = snpcoreallvarsposService.getSNPs(chr, start, end, null ).size();
		 
	 }
	 else {
		snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
		snps = snpallvarsposService.getSNPs(chr, start, end, null ).size();
	 }
	 
	 if(snps==0) return new String[] {"", "0","0"};
	
	//germplasms
	System.out.println(mismatches.size() + " mismatch pairs");
	
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
	
	System.out.println( setWithMismatch.size() + " unique names with mismatch");
	
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

	System.out.println("symdistmatrix done");
	
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
				System.out.println( mapName2Row.get(dist3k.getVar1()) + ": " +  mapVarid2Variety.get(dist3k.getVar1()) + "\t" +  mapName2Row.get(dist3k.getVar2()) + ": " +    mapVarid2Variety.get(dist3k.getVar2())  + "  -- " + dist);
				dist = 0.0;
			}
			
			symdistmatrix.setValue( mapName2Row.get(dist3k.getVar1()) , mapName2Row.get(dist3k.getVar2()) , dist );
			symdistmatrix.setValue( mapName2Row.get(dist3k.getVar2()) , mapName2Row.get(dist3k.getVar1()),  dist );
			
		} catch(Exception ex) {
			
			System.out.println( "NULL EXCEPTION:\t" + dist + "\t" + dist3k.getVar1() + "\t" + mapName2Row.get(dist3k.getVar1()) + "\t" + dist3k.getVar2() + "\t" +  mapName2Row.get(dist3k.getVar2()) + "\t" + dist );
		
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

			System.out.println("process done");
		//tree.getN
		String newick = tree.getNewickString(false, true);
		
		
		Map<BigDecimal,Variety> mapId2Variety = varietyfacade.getMapId2Variety();
		
		//System.out.println(newick);
		Iterator<BigDecimal> itgerm2 = setWithMismatch.iterator();
		while(itgerm2.hasNext()) {
			BigDecimal c = itgerm2.next();
			//String newc = c.replace(' ', '_');
			// replace iris_id to varnames
			//String varname  = mapIRISId2Varname.get(c);
			//newick = newick.replace(c,  mapIRISId2Varname.get(c).replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
			//String subpop = mapVarname2IRISId.get(varname)[2];
			//if(subpop == null) subpop = ""; 
			
			
			//Variety var = mapVarid2Variety.get(c);
			//String subpop = "";
			//if( var.getSubpopulation()!=null) subpop = "/" +  var.getSubpopulation();
			//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + var.getIrisId() + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );

		
			Variety var = mapId2Variety.get(c);
			String subpop = "";
			if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
			String irisid = "";
			if( var.getIrisId()!=null) irisid=var.getIrisId();
			//newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + irisid + "/" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
			newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
			
		
		
		}
		
		
		System.out.println(newick);
		
		
		
		//System.out.println(newick);
		return new String[] {newick, Integer.toString(symdistmatrix.getSize()), Integer.toString( mismatches.size()) };
		
		
		
	} catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
	return null;
            //   NJTreeProgressListener _treeProgessListener)
	
}

	//@Override
	//public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String newick)
	
	@Override
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile)
	{

		//org.forester.application.phyloxml_converter
		    mapVariety2PhyloOrder = new HashMap<BigDecimal,Integer>(); 
		    
		    //Map<String,Integer> mapVarietyName2PhyloOrder = new HashMap<String,Integer>(); 
		    


				//String tmpfile = AppContext.getTempDir() + AppContext.createTempFilename() + ".newick";
				//PrintWriter out = new PrintWriter(tmpfile);
				//out.print(newick);
				//out.close();
				
		        // Reading-in of (a) tree(s) from a file.
		    	String treefilename = AppContext.getTempDir() + "/" + tmpfile + "newick";
		        File treefile = new File( treefilename );
		        PhylogenyParser parser = null;
		        try {
		            parser = ParserUtils.createParserDependingOnFileType( treefile, true );
		        	//parser = ParserUtils.createParserDependingOnSuffix( treefilename, false );

		            Phylogeny[] phys = null;

		            phys = PhylogenyMethods.readPhylogenies( parser, treefile );

		        
			        System.out.println("Newick postorder listing:");
			        Map<String,Variety> varname2var = varietyfacade.getMapVarname2Variety();
			        //Map<String,Variety> irisid2var = varietyfacade.getGermplasmByIrisId(name)
			        Map<String,Variety> irisid2var = varietyfacade.getIrisId2Variety();
			     
			        int leafcount = 0;
			        for(int iphy=0; iphy<phys.length; iphy++)
			        {
				        for(PhylogenyNodeIterator it = phys[iphy].iteratorPostorder(); it.hasNext(); ) {
				        	PhylogenyNode node = it.next();
				        	if(node.isExternal()) {
				        		//System.out.println( node.getName() );
				        		//mapVarietyName2PhyloOrder.put(node.getName(), leafcount);
				        		//mapVariety2PhyloOrder.put(  varname2var.get(node.getName().split("/")[0] ).getVarietyId() , leafcount);
				        		
				        		String[] nodenames = node.getName().split("\\|");
				        		if(nodenames.length!=3) System.out.println("Invalid nodename " +  node.getName());
				        		
				        		if(nodenames.length>1 &&  !nodenames[1].isEmpty() ){
				        			Variety varNode =   irisid2var.get(nodenames[1].replace("_"," ").toUpperCase() );  //varietyfacade.getGermplasmByIrisId(nodenames[1].replace("_"," "));
				        			if(varNode!=null) {
				        				mapVariety2PhyloOrder.put( varNode.getVarietyId() , leafcount);
				        				leafcount++; 
				        			} else
				        			{
				        				System.out.println("cant resolve irisid " + nodenames[1]);
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
				        				System.out.println("cant resolve variety name " + nodenames[0] );
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
		            System.out.println(e.getMessage());
		            throw new RuntimeException("newick parse error");
		        }
		    
		    
		    System.out.println( "mapVariety2PhyloOrder.size()=" + mapVariety2PhyloOrder.size());
		    
			return mapVariety2PhyloOrder;

	}
	/*
	public List getBySearchObject(HibernateSearchObject so, int start, int pageSize) {
		return null;
	}
*/

	@Override
	public List<Snps2Vars> getSNPinVarieties(int n) {
		// TODO Auto-generated method stub
		return null;
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
	public Set getVarietiesForSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return varietyfacade.getGermplasmBySubpopulation(subpopulation);
	}

	@Override
	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start, Integer end, Integer chr, int firstRow, int maxRows) {
		return getSNPStringInAllVarieties( start, end, chr, false, firstRow, maxRows);
	}
	
	/*
	@Override
	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start, Integer end, Integer chr, boolean exactMismatch, int firstRow, int maxRows) {
		return getSNPStringInAllVarieties( start,  end,  chr,  exactMismatch, null, firstRow, maxRows);
	}
*/
	
	@Override
	public List<SnpsStringAllvars> getSNPStringInAllVarieties(Integer start, Integer end, Integer chr, boolean exactMismatch,  int firstRow, int maxRows) {
		
		//snpstringcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringcoreallvarsposService, "SnpcoreRefposindexDAO") ; 
		//snpsposlist = snpstringcoreallvarsposService.getSNPs(chr.toString(), start, end );
		
		//snpstringAllvarsDao = (SnpsStringAllvarsDAO)AppContext.checkBean(snpstringAllvarsDao, "SnpsStringAllvarsDAO") ;
		//return snpstringAllvarsDao.getSNPsString(chr, new BigDecimal(start), new BigDecimal(end), firstRow, maxRows);
		
		
		
		//SnpcoreRefposindex startpos =  (SnpcoreRefposindex)snpsposlist.get(0);
		//SnpcoreRefposindex endpos =  (SnpcoreRefposindex)snpsposlist.get( snpsposlist.size()-1 );
		
//		snpstringallelesfileDao = (SnpsStringAllvarsDAO)AppContext.checkBean(snpstringallelesfileDao, "SnpstringAllelesFileDAO") ;
//		return snpstringallelesfileDao.getSNPsString(chr, new BigDecimal(start), new BigDecimal(end), firstRow, maxRows);
		
		listSNPAllVarsMismatches =  getSNPsString(chr, new BigDecimal(start), new BigDecimal(end), exactMismatch, firstRow, maxRows );
		return listSNPAllVarsMismatches;
		//return null;
	}
	


	private List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start,
			BigDecimal end, boolean exactMismatch, int firstRow, int maxRows) {
		return getSNPsString(chr,  start,  end,   null,  exactMismatch, firstRow,  maxRows); 
	}
	
	private List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start,
			BigDecimal end, Set setPositions, boolean exactMismatch, int firstRow, int maxRows) {
		// TODO Auto-generated method stub

		List listResult = new ArrayList();
		
		
		// get snp positions/index
		snpstringcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringcoreallvarsposService, "SnpcoreRefposindexDAO") ;
		
		
		
		
		
		List listpos = null;
		if(setPositions!=null && !setPositions.isEmpty()) {
			listpos = new ArrayList();
			listpos.addAll(new TreeSet(setPositions));

			if(this.isCore)
				snpsposlist  = snpstringcoreallvarsposService.getSNPsInChromosome(chr.toString(), listpos, SnpcoreRefposindexDAO.TYPE_3KCORESNP);
			else
				snpsposlist  = snpstringcoreallvarsposService.getSNPsInChromosome(chr.toString(),  listpos, SnpcoreRefposindexDAO.TYPE_3KALLSNP);
			
			if(snpsposlist.size()!=listpos.size())
				System.out.println("snpsposlist.size()!=listpos.size():" + snpsposlist.size()+ "!=" + listpos.size());
				

		}
		else {
			if(this.isCore) {
				snpsposlist  = snpstringcoreallvarsposService.getSNPs(chr.toString(), start.intValue(),  end.intValue(), SnpcoreRefposindexDAO.TYPE_3KCORESNP,   firstRow, maxRows);
			}
			else
				snpsposlist  = snpstringcoreallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),   SnpcoreRefposindexDAO.TYPE_3KALLSNP, firstRow, maxRows );
		}
		
		if(snpsposlist.isEmpty()) return listResult;
		

		// get allele column indices from start to end positions
		VSnpRefposindex startpos =  (VSnpRefposindex)snpsposlist.get(0);
		VSnpRefposindex endpos =  (VSnpRefposindex)snpsposlist.get( snpsposlist.size()-1 );

		// if recount
		String strRef=null;
		Map<Float,Map> mapMis2Vars = new TreeMap();
		
		int refLength=-1;
		
		System.out.println( snpsposlist.size() + " snpposlist");
		
		
//		if( exactMismatch ) {
			
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
			
			// get snpstring for each varieties
			// get allele2 for heterozygous varieties
			snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
			snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "SnpsNonsynAllvarsDAO");
			Set heteroSnps = null;
			Set nonsynAllele = null;
			
			if(listpos!=null && !listpos.isEmpty()) {
				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
					System.out.println("using readSNPString1");
					mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  indxs , filename);
					heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos, datatype);
				}
				else {
					System.out.println("using readSNPString2");
					mapVarid2Snpsstr = readSNPString(chr,  indxs, filename);
					heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosIn(chr, listpos, limitVarIds, datatype);
				}
				
				if(isNonsynOnly || isColorByNonsyn)
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
				
			}
			else {
				if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() )
				{
					System.out.println("using readSNPString3");
					mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
					heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosBetween(chr, start, end, limitVarIds, datatype);
				}
				else {
					System.out.println("using readSNPString4");
					mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
					heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
				}
				
				if(isNonsynOnly|| isColorByNonsyn)
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
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
			
			if(nonsynAllele!=null) System.out.println( nonsynAllele.size() + " non-synonymous alleles, " + mapSnpid2Idx.size() + " nonsys alleles positions/idx");
			
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
							System.out.println("var " + var + " all synonymous or unknowns");
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
			
			System.out.println(heteroSnps.size() + " allele2, " + mapVarid2Snpsstr.size() + " all varieties, " +  mapVariety2Order.size() + " non-syn varieties, " + snpsposlist.size() + " alleles");
			
			Iterator<SnpsHeteroAllele2> itSnp = heteroSnps.iterator();
			while(itSnp.hasNext()) {
				SnpsHeteroAllele2 snpallele2 = itSnp.next();
				
				//long pos = snpallele2.getSnp().longValue() % 100000000;
				Integer varidx = mapVariety2Order.get( snpallele2.getVar() );
				//if( varidx!=null)  heteroAlleleMatrix[ varidx ][ mapPos2Idx.get( pos ) ] = snpallele2.getNuc();
				
				if( mapSnpid2Idx.get( snpallele2.getSnp() )==null) {
					//System.out.println("snpid=" +  snpallele2.getSnp() + " in hetero not in core SNPs");	
					//throw new RuntimeException("mapSnpid2Idx.get( snpallele2.getSnp() )==null");
					continue;
				} 
				
				if( varidx!=null)  heteroAlleleMatrix[ varidx ][ mapSnpid2Idx.get( snpallele2.getSnp() ) ] = snpallele2.getNuc();
			}
			

			//nonsynonymousAlleleMatrix = new boolean[mapVariety2Order.size()][snpsposlist.size()];
			
			/*
			itVar = mapVar2NonsynFlags.keySet().iterator();
			listNonsynFlags = new ArrayList();
			while(itVar.hasNext()) {
				BigDecimal varid = itVar.next();
				
				System.out.println("varid=" + varid + " mapVariety2Order.get(varid)" +   mapVariety2Order.get(varid) + " mapVar2NonsynFlags.get(varid)=" +  mapVar2NonsynFlags.get(varid)); 
				
				listNonsynFlags.add(mapVariety2Order.get(varid) ,  mapVar2NonsynFlags.get(varid));
			}
			*/
			
		
//		else {
//		
//
//			// use precounted mismatches
//			refmismatchDAO = (SnpsAllvarsRefMismatchDAO)AppContext.checkBean(refmismatchDAO, "MismatchCountDAO") ; 
//			// get varieties with >0 mismatches in 100kb segment
//			List listmismatch = refmismatchDAO.countMismatches(chr.toString(), start.intValue(), end.intValue());
//			Iterator<SnpsAllvarsRefMismatch> itMis = listmismatch.iterator();
//			Map<BigDecimal,BigDecimal> mapVar2Mismatch = new HashMap();
//			while(itMis.hasNext()) {
//				SnpsAllvarsRefMismatch mismatch = itMis.next();
//				mapVar2Mismatch.put(mismatch.getVar() , mismatch.getMismatch());
//			}
//			
//			// get variety row index for varieties with mismatch
//			// right now, varindex = varid
//			
//			// read SNPString for row indices, from startpos index to endpos index
//			String filename="";
//			if(isCore)
//				filename = AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt";
//			else
//				filename = AppContext.getFlatfilesDir() +  "allsnp_chr-" + chr + ".txt";
//			Map  mapVarid2Snpsstr = readSNPString(mapVar2Mismatch.keySet(), chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename );
//			int ordercount=0;
//			mapVariety2Order = new HashMap();
//			itMis = listmismatch.iterator();
//			while(itMis.hasNext()) {
//				SnpsAllvarsRefMismatch mismatch = itMis.next();
//				String snpstr = (String)mapVarid2Snpsstr.get(mismatch.getVar());
//				
//				/*
//				if( exactMismatch ) {
//					int misCount = 0;
//					for(int iStr=0; iStr<refLength; iStr++) {
//						if(strRef.charAt(iStr)==snpstr.charAt(iStr)) misCount++;
//					}
//					if(misCount>0) {
//						
//						Map mapVarId2Snpstr = mapMis2Vars.get(-misCount);
//						if(mapVarId2Snpstr==null) {
//							mapVarId2Snpstr = new TreeMap();
//							mapMis2Vars.put(-misCount,  mapVarId2Snpstr );
//						}
//						mapVarId2Snpstr.put( mismatch.getVar(),  new SnpsStringAllvarsImpl(mismatch.getVar(), snpstr,  BigDecimal.valueOf(misCount) ));
//					}
//				}
//				else
//					*/
//					
//				listResult.add( new SnpsStringAllvarsImpl(mismatch.getVar(), Long.valueOf(chr), snpstr, mismatch.getMismatch() ) );
//				
//				mapVariety2Order.put(mismatch.getVar() ,ordercount);
//				ordercount++;
//
//			}
//		
//		}
//		
		
		

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
		System.out.println("File has " + firstline.length() + " alleles");
		System.out.println("reading for 3000 rows/varieties from " +  startIdx + "-" + endIdx + " (" + (endIdx-startIdx+1) + ") cols/snps");
		
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
		System.out.println("File has " + firstline.length() + " alleles");
		System.out.println("reading for 3000 rows/varieties for (" + posIdxs.length + ") cols/snps");
		
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
				System.out.println("readSNPBuffer.length()!=posIdxs.length:" + readSNPBuffer.length() +"!=" + posIdxs.length);
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
		System.out.println("File has " + firstline.length() + " alleles");
		System.out.println("reading for " + colVarids.size() + " rows/varieties for (" + posIdxs.length + ") cols/snps");
		
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
				System.out.println("readSNPBuffer.length()!=posIdxs.length:" + readSNPBuffer.length() +"!=" + posIdxs.length);
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
		System.out.println("File has " + firstline.length() + " alleles");
		System.out.println("reading for " + colVarids.size() + " rows/varieties for" +  startIdx + "-" + endIdx + " (" + (endIdx-startIdx+1) + ") cols/snps");
		
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
		
		snpstringcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean( snpstringcoreallvarsposService, "VSnpRefposindexDAO");
		//snpstringcoreallvarsposService.
		List listtmp = new ArrayList();
		listtmp.addAll(setSNP);
		return new HashSet(snpstringcoreallvarsposService.getSNPsInChromosome(chr, listtmp, type));

	}


	@Override
	public List getSNPStringInAllVarieties(Set snpposlist, Integer chr, boolean isCore, boolean exactMatch, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		
		return getSNPsString(chr, null, null,  snpposlist,  exactMatch,  firstRow,  maxRows);
	}


	@Override
	public Map<Integer, Set<Character>> getMapIdx2NonsynAlleles() {
		return mapIdx2NonsynAlleles;
	}





	@Override
	public void setCore(boolean isCore) {
		this.isCore = isCore;
	}

	@Override
	public void setNonsynOnly(boolean isNonsynOnly) {
		this.isNonsynOnly = isNonsynOnly;
	}



	@Override
	public void setColorByNonsyn(boolean isColorByNonsyn) {
		this.isColorByNonsyn = isColorByNonsyn;
	}




	@Override
	public Map<Integer, boolean[]> getMapIdx2Nonsynflags() {
		return mapIdx2Nonsynflags;
	}


	/*
	@Override
	public List<boolean[]> getListNonsynFlags() {
		return listNonsynFlags;
	}
	*/
	
	
	
	
}
