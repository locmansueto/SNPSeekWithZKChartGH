package org.irri.iric.portal.genotype.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.irri.iric.portal.dao.Snps2VarsCountMismatchDAO;
import org.irri.iric.portal.dao.Snps2VarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsAllvarsRefMismatchDAO;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.Variety;
//import org.irri.iric.portal.domain.VarietyService;
//import org.irri.iric.portal.genotype.domain.Variety3k;
//import org.irri.iric.portal.genotype.views.*;
import org.irri.iric.portal.service.GeneService;
import org.irri.iric.portal.service.Snps2VarsService;
import org.irri.iric.portal.service.VarietyService;
//import org.irri.iric.portal.utils.zkui.HibernateSearchObject;
import org.irri.iric.portal.variety.service.VarietyFacade;
//import org.irri.iric.portal.variety.views.IViewDist3kHome;
//import org.irri.iric.portal.variety.views.ViewDist3kId;
//import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
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
public class GenotypeFacadeChadoImpl implements GenotypeFacade {
//public class GenotypeFacadeChadoImpl {

	private static final Log log = LogFactory.getLog(GenotypeFacadeChadoImpl.class);
	private static String onlymismatchsql = " (REFNUC is not null or VAR1NUC is not null) and  REFNUC<>VAR1NUC and  ";
	//private static String onlymismatchsql = "";
	
	
	// Values updated on call to getSNPinAllVarieties w/ firstRow=1
	private List<SnpsAllvarsRefMismatch> listSNPAllVarsMismatches;
	private java.util.HashMap<Integer,BigDecimal> mapOrder2Variety; // 0-indexed
	private java.util.HashMap<BigDecimal, Integer> mapVariety2Order;  // 0-indexed
	private java.util.HashMap<BigDecimal, Integer> mapVariety2Mismatch;


	private List<SnpsAllvarsPos> snpsposlist; 
	private java.util.List<String> varnames;
	private java.util.List<String> genenames;
	
	
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private GeneDAO geneservice; // = new org.irri.iric.portal.genotype.service.GeneServiceImpl();
	//@Autowired 
	//@Qualifier("VarietyDAO")
	//private VarietyDAO varservice;
	
	
	
	
	@Autowired
	@Qualifier("Snps2VarsDAO")
	private  Snps2VarsDAO snp2linesService; // = new Snp2linesHome();
	
	//private  org.irri.iric.portal.genotype.views.ISnp2linesHome snp2linesService; // = new Snp2linesHome();
	
	@Autowired
	//@Qualifier("SnpsAllvarsDAO")
	@Qualifier("VSnpAllvarsMinDAO")
	private  SnpsAllvarsDAO snpallvarsService; // = new Snp2linesHome();
	
	
	
	@Autowired
	@Qualifier("SnpsAllvarsPosDAO")
	private  SnpsAllvarsPosDAO snpallvarsposService; // = new Snp2linesHome();
	@Autowired
	@Qualifier("SnpsAllvarsRefMismatchDAO")
	private  SnpsAllvarsRefMismatchDAO snpcountallvarsService;
	@Autowired 
	@Qualifier("Snps2VarsCountMismatchDAO")
	private  Snps2VarsCountMismatchDAO snpcount2linesService; // = new Snp2linesHome();
	
	@Autowired
	@Qualifier("VarietyFacade")
	private VarietyFacade varietyfacade;
	
	
	public GenotypeFacadeChadoImpl() {
		super();
		// TODO Auto-generated constructor stub

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

		for(int i=firstRow-1; i<firstRow-1+numRows; i++)
				newlist.add(listSNPAllVarsMismatches.get(i));

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
	

	/**
	 * Use for development only
	 */

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
	

	/**
	 * Get all gene names
	 */
	@Override
	public List<String> getGenenames() {
		
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
		
				
	}
	
	/**
	 * used in development only
	 * @return
	 */

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
		if(update || listSNPAllVarsMismatches==null) {
			snpcountallvarsService = (SnpsAllvarsRefMismatchDAO)AppContext.checkBean(snpcountallvarsService, "SnpsAllvarsRefMismatchDAO") ; 
		
			/*
			String sql="select VARNAME, count(*) as mismatch from VIEW_SNP_ALLVARS where " + onlymismatchsql 
					+ " chr=" + chromosome 
					+ " and pos between " + (startPos-1) + " and " + (endPos+1)
					+ " group by VARNAME  order by mismatch desc, VARNAME";
			listSNPAllVarsMismatches = snpcountallvarsService.executeSQL(sql);
			*/
			
			listSNPAllVarsMismatches = snpcountallvarsService.countMismatches( chromosome,  startPos,  endPos);
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
		// TODO Auto-generated method stub

		if(startPos<0)  startPos=0;
		
		boolean fetchMismatchOnly = AppContext.isSNPAllvarsFetchMismatchOnly();
		
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
			
			//throw new RuntimeException("listSNPAllVarsMismatches.size()==0"); 
		}
		
		
		//String firstVariety = listSNPAllVarsMismatches.get((int) firstRow-1).getVarname();
		//String lastVariety = null;
		
		StringBuffer topVarieties = new StringBuffer();
		
		long lastRow = firstRow + numRows - 1;  // 1-indexed
		if(listSNPAllVarsMismatches.size() >  lastRow)
		{
		//	lastVariety = listSNPAllVarsMismatches.get((int) lastRow-1).getVarname();			
		} else
			lastRow = listSNPAllVarsMismatches.size();
		
		if(numRows==0)  lastRow = listSNPAllVarsMismatches.size();
		

		Set<BigDecimal> varsMismatch = new HashSet();
		
		for(long iVariety= firstRow-1;  iVariety<lastRow;  iVariety++ ) {
			
			SnpsAllvarsRefMismatch varmismatch = listSNPAllVarsMismatches.get((int)iVariety);
			
			/*
			topVarieties.append("'"); 
			topVarieties.append( varmismath.getVarname().replace("'","''")  ); 
			topVarieties.append("'"); 
			if(iVariety+1<lastRow) topVarieties.append(","); 
			*/
			
			mapOrder2Variety.put( Integer.valueOf((int)iVariety),  varmismatch.getVar()  );
			mapVariety2Order.put( varmismatch.getVar(), Integer.valueOf((int)iVariety)   );
			mapVariety2Mismatch.put( varmismatch.getVar(), varmismatch.getMismatch().intValue()  );
			//System.out.println(iVariety + "  " + varmismath.getMismatch().intValue()  +  "   "  + varmismath.getVarname()  );
			
			
			
			varsMismatch.add(  varmismatch.getVar() );
			
			//System.out.println( varmismatch.getVar());
		}
		
		 
	
		
		try {

			
		

			snpallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpallvarsposService, "SnpsAllvarsPosDAO") ; 
			/*
			String sql = "select CHR, POS, REFNUC from VIEW_SNP_ALLVARS_POS where ";
			  sql += " chr=" + chromosome + " and pos between " +
					(startPos -1) + " and " + (endPos + 1) + " order by POS";
			 */ 
			snpsposlist = snpallvarsposService.getSNPs(chromosome, startPos, endPos );
			
			if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");

			snpallvarsService = (SnpsAllvarsDAO)AppContext.checkBean(snpallvarsService, "VSnpAllvarsMinDAO") ; 
			
			// use sql query because this object is mapped from a view without primary key
			
			String  sql2 = "";
			Set<SnpsAllvars> snpslist; 
			
			/*
			if(firstRow==1 && numRows==0)
				//sql2 = "select VARNAME, CHR, POS, REFNUC, VAR1NUC from VIEW_SNP_ALLVARS where " // + onlymismatchsql
				//+ " chr=" + chromosome + " and pos between " +
				//(startPos -1) + " and " + (endPos + 1) + " order by VARNAME, CHR, POS" ;		
				 snpslist = snpallvarsService.findVSnpAllvarsByChrPosBetween( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos)); //.getSNPs(chromosome, startPos, endPos );
			else
				//sql2 = "SELECT VARNAME, chr, pos, refnuc, var1nuc  FROM VIEW_SNP_ALLVARS  where " //+ onlymismatchsql 
				//+ " chr="
				//+ chromosome + " and pos between " + (startPos-1) + " and " + (endPos+1) + 
				//" and varname in ("  + topVarieties.toString().replace("\"","\\\"")
				//+  ") order by VARNAME, chr, pos" ;				
				
				//snpslist = snpallvarsService.getSNPs(chromosome, startPos, endPos, varieties );
				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetween( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch); 
			*/
			
			if(fetchMismatchOnly)
				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetweenRefmismatch( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch); 
			else
				snpslist = snpallvarsService.findVSnpAllvarsByVarsChrPosBetween( Integer.valueOf(chromosome) , BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), varsMismatch); 
			
//			//if(firstRow>-1 && numRows>0)
//			//if(firstRow>-1)			
//			if(numRows>0)
//				/*
//				sql2 ="SELECT VARNAME, chr, pos, refnuc, var1nuc from " +
//					"( SELECT VARNAME, chr, pos, refnuc, var1nuc, ROWNUM r FROM " +
//					" ( SELECT VARNAME, chr, pos, refnuc, var1nuc  FROM VIEW_SNP_ALLVARS  where  chr="
//					+ chromosome + " and pos between " + startPos + " and " + endPos + " order by VARNAME, chr, pos ) WHERE ROWNUM <= " + ( firstRow + numRows - 1)
//					+ ") WHERE r >= " + firstRow;
//					*/
//				sql2 = "SELECT VARNAME, chr, pos, refnuc, var1nuc  FROM VIEW_SNP_ALLVARS  where " //+ onlymismatchsql 
//						+ " chr="
//						+ chromosome + " and pos between " + (startPos-1) + " and " + (endPos+1) + 
//						//" and varname in ("  + topVarieties.toString().replace("(","\\(").replace(")", "\\)").replace("\"","\\\"")
//						" and varname in ("  + topVarieties.toString().replace("\"","\\\"")
//						+  ") order by VARNAME, chr, pos" ;				
//			else 
//			{
//				sql2 = "select VARNAME, CHR, POS, REFNUC, VAR1NUC from VIEW_SNP_ALLVARS where " // + onlymismatchsql
//						+ " chr=" + chromosome + " and pos between " +
//				(startPos -1) + " and " + (endPos + 1) + " order by VARNAME, CHR, POS" ;
//			}
						
			
			
			//List<ViewSnpAllvarsId> snpslist = snpallvarsService.executeSQL(sql2);
			
			
			
			//System.out.println(sql2);
			//System.out.println(snpslist.size() + " ViewSnpAllvarsId");

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
	 * 								, SNPQUERY_ALLREFPOS 	all snp positions in the reference
	 * 	
	 */
	@Override
	public List<Snps2Vars> getSNPinVarieties(String var1, String var2,
			 Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode) {
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

			if(querymode==snpQueryMode.SNPQUERY_VARIETIES) {
				snpslist = snp2linesService.findVSnp2varsByVarsChrPosBetweenMismatch(Integer.valueOf(chromosome), BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), var1Id, var2Id);
			}
			else if(querymode== snpQueryMode.SNPQUERY_REFERENCE) {
				snpslist = snp2linesService.findVSnp2varsByVarsChrPosBetweenAll(Integer.valueOf(chromosome), BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), var1Id, var2Id);
			}
			

			log.debug( "Results: " + snpslist.size() ); 
			
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
				gene.getChr().toString(), querymode);
		

		
	}

	@Override
	public List<SnpsAllvars> getSNPinAllVarieties(String genename, Integer plusminusBp) {
		// TODO Auto-generated method stub
		
		Gene gene = getGeneFromName( genename );
		log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
		
		return getSNPinAllVarieties(gene.getFmin() , gene.getFmax(), 
				gene.getChr().toString());
		
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
	 
/*
	public String constructPhylotree(String scale, String chr, int start, int end) {
		
		snpcount2linesService = (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService, "Snps2VarsCountMismatchDAO");
		
		List<Snps2VarsCountmismatch>  mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end));
		
		
		int snps = getSnpsposlist().size();
		
		
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
			double dist =   dist3k.getMismatch().intValue()*distscale /( snps -  dist3k.getMismatch().intValue() );
			
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
			
			
			
			System.out.println(newick);
			Iterator<BigDecimal> itgerm2 = setWithMismatch.iterator();
			while(itgerm2.hasNext()) {
				BigDecimal c = itgerm2.next();
				//String newc = c.replace(' ', '_');
				// replace iris_id to varnames
				//String varname  = mapIRISId2Varname.get(c);
				//newick = newick.replace(c,  mapIRISId2Varname.get(c).replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
				//String subpop = mapVarname2IRISId.get(varname)[2];
				//if(subpop == null) subpop = ""; 
				
				Variety var = mapVarid2Variety.get(c);
				
				String subpop = "";
				if( var.getSubpopulation()!=null) subpop = "/" +  var.getSubpopulation();
				
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + var.getIrisId() + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
			}
			
			
			System.out.println(newick);
			
			
			
			//System.out.println(newick);
			return newick;
			
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	            //   NJTreeProgressListener _treeProgessListener)
		
	}
	*/
	
	
public String constructPhylotree(String scale, String chr, int start, int end) {
	return constructPhylotreeTopN(scale, chr, start, end, -1);
}	


public String constructPhylotreeTopN(String scale, String chr, int start, int end, int topN) {
		
		snpcount2linesService = (Snps2VarsCountMismatchDAO)AppContext.checkBean(snpcount2linesService, "Snps2VarsCountMismatchDAO");
		
		List<Snps2VarsCountmismatch>  mismatches = null;
		
		AppContext.startTimer();
		
		if(topN>0) {
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
			mismatches =  snpcount2linesService.countMismatch(Integer.valueOf(chr), BigDecimal.valueOf(start), BigDecimal.valueOf(end));
			AppContext.resetTimer(" all distance calc");
		}
		
		int snps = getSnpsposlist().size();
		
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
			double dist =   dist3k.getMismatch().intValue()*distscale /( snps -  dist3k.getMismatch().intValue() );
			
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
			
			
			
			System.out.println(newick);
			Iterator<BigDecimal> itgerm2 = setWithMismatch.iterator();
			while(itgerm2.hasNext()) {
				BigDecimal c = itgerm2.next();
				//String newc = c.replace(' ', '_');
				// replace iris_id to varnames
				//String varname  = mapIRISId2Varname.get(c);
				//newick = newick.replace(c,  mapIRISId2Varname.get(c).replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
				//String subpop = mapVarname2IRISId.get(varname)[2];
				//if(subpop == null) subpop = ""; 
				
				Variety var = mapVarid2Variety.get(c);
				
				String subpop = "";
				if( var.getSubpopulation()!=null) subpop = "/" +  var.getSubpopulation();
				
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + var.getIrisId() + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
			}
			
			
			System.out.println(newick);
			
			
			
			//System.out.println(newick);
			return newick;
			
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	            //   NJTreeProgressListener _treeProgessListener)
		
	}
	

/*	public String constructPhylotreeWithCommon(String scale, String chr, int start, int end) {
		
		snpcount2linesService = (IViewCount2linesMismatchHome)AppContext.checkBean(snpcount2linesService, "IViewCount2linesMismatchHome");
		
		
		String sql = "select upper(var1) var1, upper(var2) var2, count(*) mismatch from snp_2lines where " 
				+ " var1nuc<>var2nuc "
				+ " and (var1nuc is not null or var2nuc is not null) "
				+ " and chr=" + chr 
				+ " and pos between " + (start-1) + " and " + (end+1) 
				+ " and var1<=var2 "
				+ " group by var1, var2 "
				+ " order by var1, var2";
				
		List<ViewCount2linesMismatchId>  mismatches =  snpcount2linesService.executeSQL( sql );
		
		
		String sql2 = "select upper(var1) var1, upper(var2) var2, count(*) mismatch from snp_2lines where " 
				+ " var1nuc=var2nuc "
				+ " and (var1nuc is not null or var2nuc is not null) "
				+ " and chr=" + chr 
				+ " and pos between " + (start-1) + " and " + (end+1) 
				+ " and var1<=var2 "
				+ " group by var1, var2 "
				+ " order by var1, var2";
				
		List<ViewCount2linesMismatchId>  common =  snpcount2linesService.executeSQL( sql2 );
		
		
		
		
		//germplasms
		System.out.println(mismatches.size() + " mismatch pairs");
		System.out.println(common.size() + " common pairs");
		
		java.util.Map<String, Integer> mapName2Row = new java.util.HashMap<String, Integer>();
		

		Set setNames = new java.util.TreeSet();
		
		java.util.Map<String, BigDecimal> mapPairMismatch = new java.util.HashMap<String, BigDecimal>();
		java.util.Iterator<ViewCount2linesMismatchId>  itdist = mismatches.iterator();		
		while(itdist.hasNext())
		{
			
			ViewCount2linesMismatchId dist3k = itdist.next();
			mapPairMismatch.put( dist3k.getVar1() + ":" + dist3k.getVar2(), dist3k.getMismatch());
			setNames.add( dist3k.getVar1());
			setNames.add( dist3k.getVar2());
		}
		
		
	
		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix( setNames.size() );
		
		System.out.println( setNames.size() + " unique names with mismatch");
		
		
		int i=0;
		Iterator<String> itgerm = setNames.iterator();
		while(itgerm.hasNext()) {
			String c = itgerm.next();
			mapName2Row.put(c , i);
			symdistmatrix.setIdentifier( i, c );
			i++;
		}		
		

		

		System.out.println("symdistmatrix done");

		
		
		
		//setNames = new java.util.HashSet();
		
		//double distscale = Integer.valueOf(scale)*20.0/(end-start) ; // Integer.parseInt(scale);
		
		double distscale = 1.0; 
		
		// distance = # mismatch / # common snps
		

		
		java.util.Iterator<ViewCount2linesMismatchId>  itcommon = common.iterator();		
		
		while(itcommon.hasNext())
		{
			
			ViewCount2linesMismatchId paircommon = itcommon.next();
			
			String pair = paircommon.getVar1() + ":" + paircommon.getVar2();
			BigDecimal pairmismatch =  mapPairMismatch.get(pair );
			
			mapPairMismatch.remove( pair );
			
			if( pairmismatch==null) {
				pairmismatch = BigDecimal.ZERO ;
				//continue;
			}
			
			
			
			Double dist = distscale*pairmismatch.doubleValue() / paircommon.getMismatch().doubleValue() ;

			
			try {
						
				symdistmatrix.setValue( mapName2Row.get(paircommon.getVar1()) , mapName2Row.get(paircommon.getVar2()) , dist );
				symdistmatrix.setValue( mapName2Row.get(paircommon.getVar2()) , mapName2Row.get(paircommon.getVar1()),  dist );
				
			} catch(Exception ex) {
				
				System.out.println( "NULL EXCEPTION:\t" + dist + "\t" + paircommon.getVar1() + "\t" + mapName2Row.get(paircommon.getVar1()) + "\t" + paircommon.getVar2() + "\t" +  mapName2Row.get(paircommon.getVar2()) );
				
			//	ex.printStackTrace();
			}
		}
		
		System.out.println(mapPairMismatch.size() + " pairs no common");

		
		
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
			
			newick = newick.replace(' ', '_').replace("'","").replace(":-",":");
			//newick = newick.replace("IRIS", newChar)
	
			
			//System.out.println(newick);
			
			orderVarietiesFromPhylotree( newick);
			
			return newick;
			
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	            //   NJTreeProgressListener _treeProgessListener)
		
	}*/
	
	
	public Map<String,Integer> orderVarietiesFromPhylotree(String newick)
	{

		//org.forester.application.phyloxml_converter
		   Map<String,Integer> mapVariety2Order = new HashMap<String,Integer>(); 
			try {

				String tmpfile = AppContext.tempdir + AppContext.createTempFilename() + ".newick";
				PrintWriter out = new PrintWriter(tmpfile);
				out.print(newick);
				out.close();
				
		        // Reading-in of (a) tree(s) from a file.
		        File treefile = new File( tmpfile );
		        PhylogenyParser parser = null;
		        try {
		            parser = ParserUtils.createParserDependingOnFileType( treefile, true );
		        }
		        catch ( final IOException e ) {
		            e.printStackTrace();
		        }
		        Phylogeny[] phys = null;
		        try {
		            phys = PhylogenyMethods.readPhylogenies( parser, treefile );
		        }
		        catch ( final IOException e ) {
		            e.printStackTrace();
		        }
		        
		        System.out.println("Newick postorder listing:");
		     
		        int leafcount = 0;
		        for(int iphy=0; iphy<phys.length; iphy++)
		        {
			        for(PhylogenyNodeIterator it = phys[iphy].iteratorPostorder(); it.hasNext(); ) {
			        	PhylogenyNode node = it.next();
			        	if(node.isExternal()) {
			        		System.out.println( node.getName() );
			        		mapVariety2Order.put(node.getName(), leafcount);
			        		leafcount++;
			        	}
			        }
		        }
		        
		        // Display of the tree(s) with Archaeopteryx.													
		       // Archaeopteryx.createApplication( phys );
		        
			} catch(IOException e ) {
				e.printStackTrace();
			}
			
			return mapVariety2Order;

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

	
	
	
}
