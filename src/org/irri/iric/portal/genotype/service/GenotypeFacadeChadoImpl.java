package org.irri.iric.portal.genotype.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.SequenceDAO;
import org.irri.iric.portal.domain.Gene;


import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Position;


import org.irri.iric.portal.domain.Scaffold;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.PhylotreeQueryParams;
import org.irri.iric.portal.genotype.PhylotreeService;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.irri.iric.portal.genotype.VarietiesGenotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



import org.springframework.stereotype.Service;
import org.zkoss.zul.Filedownload;
import org.irri.iric.portal.CreateZipMultipleFiles;

//@Secured("ROLE_IRRIUSER")
@Service("GenotypeFacade")

//The scope of this class is Session, it keeps a set of state variables for
//results from previous Request within the Session
//@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
//@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)

public class GenotypeFacadeChadoImpl implements GenotypeFacade {

	private static final Log log = LogFactory.getLog(GenotypeFacadeChadoImpl.class);
	
	@Autowired
	private SequenceDAO sequenceDAO;
	
	@Autowired
	@Qualifier("VarietyFacade")
	private VarietyFacade varietyfacade;

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsDAO;
	
	@Autowired
	private VarietiesGenotypeService genotypeservice;
	
	@Autowired
	@Qualifier("PhylotreeService")
	private PhylotreeService phyloservice;
	
	
	public GenotypeFacadeChadoImpl() {
		super();
		// TODO Auto-generated constructor stub
		AppContext.debug("created GenotypeFacadeChadoImpl: " + this );
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
	public Gene getGeneFromName(String name, String organism) {
		// TODO Auto-generated method stub
		return listitemsDAO.findGeneFromName( name, organism);
	}

	/**
	 * Get varietry names 
	 */
	
	@Override
	public List<String> getVarnames() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getVarietyNames();
		
	}
	
	@Override
	public List<String>getSubpopulations() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getSubpopulations();
	}
	 
	/**
	 * Get all gene names
	 */
	@Override
	public List<String> getGenenames() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getGenenames();
	}
	
	@Override
	public Collection getLociForReference(String organism) {
		// TODO Auto-generated method stub
		if(organism.equals( AppContext.getDefaultOrganism()))
			return  getGenenames();

		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		return AppContext.createUniqueUpperLowerStrings( listitemsDAO.getGenenames(organism) , true, false);
	}



	/**
	 * Get chromosome names for organism
	 */

	@Override
	public java.util.List<String> getChromosomes() {

		return getContigsForReference(AppContext.getDefaultOrganism());
	}
	
	/**
	 * Get length of features (ex. chromosome) from name, mocked! replace with DB read later
	 */


	@Override
	public Integer getFeatureLength(String feature, String organism) {
		return this.listitemsDAO.getFeatureLength(feature, organism).intValue();
	}
	
	@Override
	public Scaffold getFeature(String feature, String organism) {
		return this.listitemsDAO.getFeature(feature, organism);
	}

	
	@Override
	public List getReferenceGenomes() throws Exception {
		// TODO Auto-generated method stub
		Iterator<Organism> it = listitemsDAO.getOrganisms().iterator();
		List orgnames = new ArrayList();
		while(it.hasNext()) {
			orgnames.add( it.next().getName() ); 
		}
		return orgnames;
	}



	@Override
	public List getContigsForReference(String reference) {
		// TODO Auto-generated method stub
		return listitemsDAO.getContigs(reference);
	}

	
	
// ************************************* Methods for Phylogenetic tree construction ********************************************************************************	

	@Override
	public Object[] constructPhylotree(String scale, String chr, int start, int end, String requestid) {
		
		phyloservice = (PhylotreeService)AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.constructPhylotree(scale, chr, start, end, requestid);
	}	

	@Override
	public Object[] constructPhylotree(PhylotreeQueryParams params, String requestid) {
		
		phyloservice = (PhylotreeService)AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.constructPhylotree(params, requestid);
	}	

	
	@Override
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile)
	{
		phyloservice = (PhylotreeService)AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.orderVarietiesFromPhylotree(tmpfile);
	}
		
	@Override
	public Map<BigDecimal,Integer> orderVarietiesFromPhylotree(String tmpfile, String newick)
	{
		phyloservice = (PhylotreeService)AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.orderVarietiesFromPhylotree(tmpfile, newick);
	}
		
	
// ********************************************* Methods to query SNPs as SNPString *********************************************************************************	
	

	@Override
	public String getIndelType(String allele) {
		if(allele.startsWith("ref")) return "reference";
		else if(allele.startsWith("snp")) return "snp";
		else if(allele.startsWith("del")) {
			if(allele.contains("->"))
				return "substitution";
			else return "deletion";
		}
		else return "insertion";
	}

	@Override
	public VariantStringData queryGenotype(GenotypeQueryParams params ) throws Exception {
		genotypeservice = (VarietiesGenotypeService)AppContext.checkBean(genotypeservice , "VarietiesGenotypeService");
		return genotypeservice.queryVariantStringData(params);
	}

	@Override
	public VariantTable fillGenotypeTable(VariantTable table, VariantStringData data, GenotypeQueryParams params ) throws Exception {
		genotypeservice = (VarietiesGenotypeService)AppContext.checkBean(genotypeservice , "VarietiesGenotypeService");
		return genotypeservice.fillVariantTable( table , data, params);
	}
	

	@Override
	public VariantStringData compare2Varieties(BigDecimal var1, BigDecimal var2,
			GenotypeQueryParams params) throws Exception  {
		// TODO Auto-generated method stub
		genotypeservice = (VarietiesGenotypeService)AppContext.checkBean(genotypeservice , "VarietiesGenotypeService");
		return genotypeservice.compare2VariantStrings(var1, var2, params);
	}
	
	
	@Override
	public void downloadFastaMSAPerLocus(GenotypeQueryParams param, Locus loc, String locusfilename) throws Exception {		
		
		
		sequenceDAO = (SequenceDAO)AppContext.checkBean(sequenceDAO,"sequenceDAO");
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO,"ListItemsDAO");

	  	boolean showAllRefsAllele=false;
		BufferedWriter outfasta=new BufferedWriter(new FileWriter(locusfilename));
		BufferedWriter outcsv=new BufferedWriter(new FileWriter(locusfilename.replace(".fasta", ".csv")));
			
	  		String locusid=loc.getUniquename();
	  		if(locusid==null || locusid.isEmpty()) {
	  			locusid =  loc.getChr() + ":" + loc.getFmin() + ".."  + loc.getFmax();
	  		} else locusid = locusid +"|" + loc.getChr() + ":" + loc.getFmin() + ".."  + loc.getFmax();

	  		// query genotype
			GenotypeQueryParams params = new GenotypeQueryParams(param.getColLoci(), loc.getChr(), loc.getFmin().longValue(), loc.getFmax().longValue(), param.isbSNP(), param.isbIndel(), param.isbCoreonly() ,
				 param.isbMismatchonly(), param.getPoslist(), param.getsSubpopulation(), loc.getUniquename(), param.isbAlignIndels(), showAllRefsAllele );			
			VariantStringData queryRawResult = queryGenotype( params);
			Set setvars= new TreeSet(queryRawResult.getMapVariety2Order().keySet());
			
			// format into table
			VariantTableArray varianttable =  new VariantAlignmentTableArraysImpl();
			varianttable = (VariantTableArray)fillGenotypeTable(varianttable , queryRawResult, params) ;
			String locusseq = sequenceDAO.getSubSequence( loc.getChr(), loc.getFmin().longValue() , loc.getFmax().longValue(), listitemsDAO.getOrganismByName(param.getOrganism()).getOrganismId().intValue());
			
			if(varianttable.getPosition().length != varianttable.getReference().length ) throw new RuntimeException();
			if(varianttable.getPosition().length != varianttable.getVaralleles()[0].length ) throw new RuntimeException();
			
			// generate sequence between two variant positions 
			Position pos[] = varianttable.getPosition();
			Map<BigDecimal,String> mapPos2NextSequence=new HashMap();
			long lfmin=loc.getFmin().longValue();
			
			long querystart=0;
			long queryend=(pos[0].getPosition().longValue()-1) - lfmin +1;
			AppContext.debug(lfmin + "-" + (pos[0].getPosition().longValue()-1) + "==>" + querystart + "-" + (queryend-1) +"/" + locusseq.length());
			mapPos2NextSequence.put( BigDecimal.valueOf(loc.getFmin()), locusseq.substring((int)querystart, (int)queryend) );
			
			boolean contseq=true;
			for(int ipos=0; ipos<pos.length && contseq; ipos++) {

				BigDecimal bdposi= pos[ipos].getPosition();
				BigDecimal bdposip1=null;
				if(ipos==pos.length-1) {
					bdposip1= BigDecimal.valueOf( loc.getFmax() + 1 );
				}
				else 
					bdposip1=pos[ipos+1].getPosition();
				
				if(bdposip1.compareTo( BigDecimal.valueOf(loc.getFmax())) >0 ) {
					bdposip1= BigDecimal.valueOf( loc.getFmax() + 1 );
					contseq=false;
				}
				
				if( bdposi.remainder(BigDecimal.ONE ).compareTo(BigDecimal.ZERO)==0) {
					// whole number
					long lposi = bdposi.longValue();
					long lposip1= bdposip1.longValue();
					if(lposi+1==lposip1) mapPos2NextSequence.put(bdposi , strBlank);
					else if(lposi==lposip1) 
						mapPos2NextSequence.put(bdposi , strBlank);
					else {
						querystart= lposi+1-lfmin ;
						queryend= (lposip1)-lfmin ;
						 AppContext.debug( (lposi+1) + "-" + (lposip1-1) + "==>" + querystart + "-" + (queryend-1) +"/" + locusseq.length());
						 mapPos2NextSequence.put( bdposi, locusseq.substring((int)querystart, (int)queryend));
					}
				} else {
					// insertion
					if( bdposip1.remainder(BigDecimal.ONE ).compareTo(BigDecimal.ZERO)==0) {
						// next is wholenum
						long lposi = bdposi.longValue();
						long lposip1= bdposip1.longValue();
						querystart= lposi+1-lfmin;
						queryend= (lposip1)-lfmin;
						
						AppContext.debug( ( lposi+1) + "-" + (lposip1-1) + "==>" + querystart + "-" + (queryend-1) +"/" + locusseq.length());
						
						 mapPos2NextSequence.put( bdposi, locusseq.substring((int)querystart, (int)queryend) );
						 
					} else {
						mapPos2NextSequence.put( bdposi, strBlank );
					}
				}
			}
			
			
			// print table
			Object alleles[][] = varianttable.getVaralleles();
			Map<String,Integer> mapNames=new TreeMap();
			String varnames[] = varianttable.getVarname();
			for(int ivar=0; ivar<varnames.length; ivar++) {
				mapNames.put(varnames[ivar], ivar);
			}
			
			String reference[]=varianttable.getReference();

			StringBuffer buff=new StringBuffer();
			StringBuffer buffCsv=new StringBuffer();
			buff.append( ">REFERENCE_NIPPONBARE|" + locusid + "\n");
			buff.append(  mapPos2NextSequence.get( BigDecimal.valueOf(loc.getFmin()) ).toLowerCase());
			buffCsv.append("VARIETYxPOSITION,SUBPOPULATION,");
			for(int ipos=0; ipos<pos.length; ipos++) {
				BigDecimal bdposi= pos[ipos].getPosition();
				buffCsv.append(bdposi.toString().replaceAll(".00","") );
				if(ipos<pos.length-1) buffCsv.append(",");
			}
			buffCsv.append("\n");
			buffCsv.append("REFERENCE_NIPPONBARE,,");
			for(int ipos=0; ipos<pos.length; ipos++) {
				BigDecimal bdposi= pos[ipos].getPosition();
				buff.append( AppContext.getIUPAC( reference[ipos] ) );
				if(mapPos2NextSequence.containsKey(bdposi))
					buff.append( mapPos2NextSequence.get(bdposi).toLowerCase());
				
				buffCsv.append( AppContext.getIUPAC( reference[ipos] ) );
				if(ipos<pos.length-1) buffCsv.append(",");
			}
			buff.append("\n");
			buffCsv.append("\n");
			
			outfasta.append(buff);
			outcsv.append(buffCsv);
			
			Iterator<String> itNames = mapNames.keySet().iterator();
			while(itNames.hasNext()) {
				int ivar=mapNames.get(itNames.next() );
				
				Object ivaralleles[]=alleles[ivar];
				
				Variety var = listitemsDAO.getMapVarname2Variety().get(varnames[ivar].toUpperCase());
				if(var==null) throw new RuntimeException("can't find variety " + varnames[ivar].toUpperCase());
						
				buff=new StringBuffer();
				buffCsv=new StringBuffer();
				buff.append( ">" + varnames[ivar] + "|" + locusid + "\n");
				buff.append(  mapPos2NextSequence.get( BigDecimal.valueOf(loc.getFmin()) ).toLowerCase()); 
				
				buffCsv.append( "\"" +  varnames[ivar] +"\"," + var.getSubpopulation() +","); 
				for(int ipos=0; ipos<pos.length; ipos++) {
					BigDecimal bdposi= pos[ipos].getPosition();
					String iupac=AppContext.getIUPAC( (String)ivaralleles[ipos] ); 
					buff.append( iupac);
					if(mapPos2NextSequence.containsKey(bdposi))
						buff.append( mapPos2NextSequence.get(bdposi).toLowerCase());
					buffCsv.append( (String)ivaralleles[ipos] );
					if(ipos<pos.length-1) buffCsv.append(",");
				}
				
				buffCsv.append("\n");
				buff.append("\n");
				outfasta.append(buff);
				outcsv.append(buffCsv);
			}
			
			outfasta.flush(); outfasta.close();
			outcsv.flush(); outcsv.close();
			
			
			
 	}
	
	@Override
	public void downloadGenotypeGenome(GenotypeQueryParams params)
			throws Exception {
		// TODO Auto-generated method stub
		
		boolean bSplitAllele2=false;
		boolean hasRowHeader=true;
		boolean hasColHeader=true;
		String delimiter = params.getDelimiter();
		String filenames[] = new String[12];
		String tmpname = AppContext.createTempFilename();
		String fileext = ".txt";
		if(delimiter.equals(",")) fileext = ".csv";
		boolean bIsPlink=false;
		boolean bIsHapmap=false;
		if(delimiter.equals("plink")) {
			fileext = ".ped";
			bSplitAllele2 = true;
			hasColHeader=false;
			hasRowHeader=false;
			bIsPlink=true;
			delimiter="\t";
		} 
		if(delimiter.equals("hapmap")) {
			fileext = ".ped";
			bSplitAllele2 = true;
			hasColHeader=false;
			hasRowHeader=false;
			bIsHapmap=true;
			delimiter="\t";
		}
	
		AppContext.resetTimer("query whole genome start..");
		
		for(int ichr=1; ichr<=12; ichr++) {
			//Integer chrlen = getFeatureLength( Integer.toString(ichr));
			String chrstr= Integer.toString(ichr);
			if(ichr<10)
				chrstr = "0" + Integer.toString(ichr);
			Integer chrlen = getFeatureLength( chrstr, params.getOrganism());
			params.setlStart(0L);
			params.setlEnd(Long.valueOf(chrlen));
			params.setsChr( String.valueOf(ichr));
			VariantStringData varstrchr = queryGenotype(params);
			
			

			filenames[ichr-1]="chr-" + ichr + "-" +  params.getFilename() + "-" + tmpname +  fileext;
			
			if(bIsPlink) {
				StringBuffer buffMap = new StringBuffer();
				Iterator<SnpsAllvarsPos> itPos =  varstrchr.getListPos().iterator();
				while(itPos.hasNext()) {
					SnpsAllvarsPos posnuc=itPos.next();
					buffMap.append(posnuc.getPosition());
				}				
				
				//File file = new File(filenames[ichr-1]  + ".map");
				FileWriter fw = new FileWriter(filenames[ichr-1]  + ".map");
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(buffMap.toString());
				bw.flush();
				bw.close();
			}
			
			FileWriter writer = new FileWriter( filenames[ichr-1] );
			
			if(!bIsPlink && !bIsHapmap)
				writer.append(  "REGION: WHOLE CHR " + ichr + " " +  1 + ".." + chrlen + "\n" );
			
			//newpagelist = genotype.getSNPStringInAllVarieties(0, chrlen, Integer.valueOf(ichr)); //,  true,  -1,  -1 );

			VariantTable filetable = fillGenotypeTable(new VariantTableSerialImpl(fileext, writer, bSplitAllele2, hasRowHeader, hasColHeader), varstrchr, params);
			writer.flush();
			writer.close();
			//updateAllvarsList(newpagelist,true,filenames[ichr-1] ,delimiter,  "REGION: WHOLE CHR " + ichr + " " +  1 + ".." + chrlen, false , Integer.valueOf(ichr));
			
			
			
		}
		String allzipfilenames[] = filenames;
		if(bIsPlink) {
			filenames = new String[24];
			for(int i=1; i<12; i++) {
				allzipfilenames[i-1]=filenames[i-1];
			}
			for(int i=1; i<12; i++) {
				allzipfilenames[i+12-1]=filenames[i-1] + ".map";
			}
		}
		new CreateZipMultipleFiles(AppContext.getTempDir() + params.getFilename() + "-" + tmpname + ".zip", allzipfilenames ).create();
		Filedownload.save(new File(AppContext.getTempDir() + params.getFilename() + "-" + tmpname + ".zip") , "application/zip");
		AppContext.debug("File download complete! Saved to: "+ params.getFilename());
	    AppContext.resetTimer("query whole genome done..");	
		
	}


	@Override
	public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type) {
		
		// TODO Auto-generated method stub
		
		//snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean( snpstringallvarsposService, "VSnpRefposindexDAO");
		//snpstringallvarsposService.
		List listtmp = new ArrayList();
		listtmp.addAll(setSNP);
		//return new HashSet(snpstringallvarsposService.getSNPsInChromosome(chr, listtmp, type));
		
		genotypeservice = (VarietiesGenotypeService)AppContext.checkBean( genotypeservice, "VarietiesGenotypeService");
		return new HashSet(genotypeservice.checkSNPsInChromosome(chr, listtmp, type));
		
	}
}
	
//
//	/**
//	 * Count mismatch between nucelotide sequences, based on several criteria
//	 * @param var1	variety 1 allele1 string
//	 * @param var2	variety 2 allele1 string
//	 * @param var1isref	variety 1 is reference
//	 * @param var1allele2str	variety 1 allele2 string
//	 * @param var2allele2str	variety 2 allele2 string
//	 * @param mapIdx2NonsynAlleles	map table index 2 nonsysynonymous nucleotide set
//	 * @param setSnpInExonTableIdx	set of table indices in exon
//	 * @param setNonsynIdx		set of table indices with nonsynonymous (return value)
//	 * @param isNonsynOnly	include only nonsynonymous
//	 * @param isColorSynGray	color nonsynonymous as gray
//	 * @return
//	 */
//  public static double countVarpairMismatch(String var1, String var2, boolean var1isref, Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,	
//				Map<Integer,Set<Character>> mapIdx2NonsynAlleles, Set setSnpInExonTableIdx, Set setNonsynIdx, boolean isNonsynOnly) {
//
//		double misCount = 0;
//		for(int iStr=0; iStr<var2.length(); iStr++) {
//			char var1char = var1.charAt(iStr);
//			char var2char = var2.charAt(iStr);
//			boolean snpInExon = false;
//			if(setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr)) snpInExon=true;
//			
//			Boolean isNonsyn[] = new Boolean[2];
//			isNonsyn[0] = false;
//			isNonsyn[1] = false;
//			Character var1allele2 = null;
//			if(!var1isref && var1allele2str!=null) var1allele2 =  var1allele2str.get(iStr);
//			
//			Character var2allele2 = null;
//			if(var2allele2str!=null) var2allele2 = var2allele2str.get(iStr);
//			Set setNonsyns = null;
//			if(mapIdx2NonsynAlleles!=null) setNonsyns = mapIdx2NonsynAlleles.get(iStr);
//			
//			misCount += countVarpairMismatchNuc( var1.charAt(iStr),  var2.charAt(iStr),  var1isref, var1allele2, var2allele2,
//					setNonsyns, snpInExon,  isNonsyn,  isNonsynOnly);
//					
//			if(isNonsyn[0] || isNonsyn[1]) setNonsynIdx.add(iStr);					
//		}
//		return misCount;
//  }
//	  
//  public static double countVarpairMismatchNuc(char var1char, char var2char , boolean var1isref,Character var1allele2, Character var2allele2,	
//		Set<Character> setNonsynAlleles, boolean snpInExon, Boolean isNonsyn[], boolean isNonsynOnly) {
//		double misCount = 0;
//			
//				isNonsyn[0]=false;
//				isNonsyn[1]=false;
//				//boolNonsyn = false;
//				
//				if( var2allele2 != null) {
//					if(var2allele2=='*') var2allele2 = var2char;
//					else if( var2allele2=='0' || var2allele2=='.') var2allele2=null;
//				}
//				if( var1allele2 != null) {
//					if(var1allele2=='*') var1allele2 = var1char; 
//					else if(var1allele2=='0' || var1allele2=='.') var1allele2=null;
//				}
//				
//				
//				if(var2char=='0' || var2char=='.'  || var2char == '*')
//					{}
//				else if(!var1isref && (var1char=='0' || var1char=='.'  || var1char == '*')) 
//					{}
//				else {
//					if(snpInExon) {
//						// idx in exon
//						if(setNonsynAlleles!=null && (setNonsynAlleles.contains(var2char) || (var2allele2!=null && setNonsynAlleles.contains(var2allele2) ) ) )
//							// var2 allele1 or allele2 in nonsynonymous
//							isNonsyn[1]=true;
//						
//						if(!var1isref && setNonsynAlleles!=null && (setNonsynAlleles.contains(var1char) || (var1allele2!=null && setNonsynAlleles.contains(var1allele2) ) ) )
//							// var1 is not reference, and var1 allele1 or allele2 in nonsynonymous
//							isNonsyn[0]=true;
//					} 
//					else {
//						// not in exon, OR no exon information, include in nonsynonymous
//						isNonsyn[0]=true; 
//						isNonsyn[1]=true;
//					}
//				}
//				
//				if(isNonsynOnly && !isNonsyn[0]  && !isNonsyn[0]) return 0;
//
//				
//				if(var1isref) {
//					// compare with reference
//					
//					// assump: no 0 * . $ characters in reference
//					// if homozygous, mismatch allele1, miscount +1
//					// if heterozygous, match allele1 or allele2, miscount +0.5
//					// if not nonsynonymos and isNonsynOnly , no count 
//					if(var1char==var2char) {
//						if(var2allele2!=null && var2allele2!=var2char ) misCount+=0.5;
//					}
//					else if(var2char!='0' && var2char!='.'  &&  var2char!='*' &&  var2char!='$') {
//						//check with allele 2
//						if(var2allele2!=null &&  var2allele2==var1char) misCount+=0.5;
//						else misCount +=1.0;
//					}
//					
//				} else {
//					// pairwise comparison
//					
//					// check all pairs
//					if(var1char=='0' || var2char=='0' ||  var1char=='.' || var2char=='.' ||  var1char=='*' || var2char=='*' ) {}
//					else if(var1char==var2char) {
//						if(var2allele2!=null && var1char!= var2allele2)
//							misCount+=0.5;
//						if(var1allele2!=null && var2char!= var1allele2)
//							misCount+=0.5;
//						
//					}
//					else {
//						//var1 allele1 != var2 allele1
//						if(var1allele2==null && var2allele2==null) misCount+=1;  
//						else {
//							//if(var1allele2==null || var2allele2==null) misCount+=0.5;
//							if(var1allele2!=null && var2char!=var1allele2)
//								misCount+=0.5;
//							if(var2allele2!=null && var1char!=var2allele2)
//								misCount+=0.5;
//						}
//					}
//				}
//				
//		return misCount;
//	}



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

//@Autowired
//			//@Qualifier("SnpsAllvarsDAO")
//@Qualifier("VSnpAllvarsMinDAO")  // no snp_genotype_id
//private  SnpsAllvarsDAO snpallvarsService; // = new Snp2linesHome();

@Autowired
@Qualifier("SnpsAllvarsPosDAO")
private  SnpsAllvarsPosDAO snpallvarsposService; // = new Snp2linesHome();

//@Autowired
//@Qualifier("MvCoreSnpsDAO")		// using core snps
//private SnpsAllvarsPosDAO snpcoreallvarsposService;


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

//@Autowired
//@Qualifier("SnpstringAllelesFileDAO")	// using core snps, allelestring in flatfile
//private SnpsStringAllvarsDAO snpstringallelesfileDao;


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

	//@Autowired
	//@Qualifier("Snps2VarsDAO")
	//private  Snps2VarsDAO snp2linesService; 
	
	//@Autowired 
	//@Qualifier("Snps2VarsCountMismatchDAO")
	//@Qualifier("SnpsString2VarsCountMismatchDAO")
	//private  Snps2VarsCountMismatchDAO snpcount2linesService; 
	
	//@Autowired
	//@Qualifier("VSnpRefposindexDAO")		// snps reference, allele position file index
	//private SnpsAllvarsPosDAO snpstringallvarsposService;
	
	
*/



///**
// * Contains nucleotide string sequences for each variety based on query criteria
// * before mismatch (reference or pairwise) counting
// * @author lmansueto
// *
// */
//class SNPsStringData {
//	
//	private String  strRef;
//	private Map<BigDecimal,String>  mapVarid2Snpsstr;
//	private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
//	private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
//	private Set<Integer> setSnpInExonTableIdx;
//	
//	public SNPsStringData(String strRef, Map mapVarid2Snpsstr,
//			Map mapVarid2SnpsAllele2str, Map mapIdx2NonsynAlleles,
//			Set setSnpInExonTableIdx) {
//		super();
//		//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
//		//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
//		//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
//		
//		this.strRef = strRef;
//		this.mapVarid2Snpsstr = mapVarid2Snpsstr;
//		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
//		this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
//		this.setSnpInExonTableIdx = setSnpInExonTableIdx;
//	}
//	public String getStrRef() {
//		return strRef;
//	}
//	public Map<BigDecimal,String> getMapVarid2Snpsstr() {
//		return mapVarid2Snpsstr;
//	}
//	public Map getMapVarid2SnpsAllele2str() {
//		return mapVarid2SnpsAllele2str;
//	}
//	public Map getMapIdx2NonsynAlleles() {
//		return mapIdx2NonsynAlleles;
//	}
//	public Set getSetSnpInExonTableIdx() {
//		return setSnpInExonTableIdx;
//	}
//	
//}
//
//class IndelsStringData {
//	
//	private String  strRef;
//	private Map<BigDecimal,String>  mapVarid2Snpsstr;
//	private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
//	
//	public IndelsStringData(String strRef, Map mapVarid2Snpsstr,
//			Map mapVarid2SnpsAllele2str) {
//		super();
//		//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
//		//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
//		//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
//		
//		this.strRef = strRef;
//		this.mapVarid2Snpsstr = mapVarid2Snpsstr;
//		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
//	}
//	public String getStrRef() {
//		return strRef;
//	}
//	public Map<BigDecimal,String> getMapVarid2Snpsstr() {
//		return mapVarid2Snpsstr;
//	}
//	public Map getMapVarid2SnpsAllele2str() {
//		return mapVarid2SnpsAllele2str;
//	}
//	
//}
//

//
//	class Snps2VarsSorter implements Comparator {
//
//		@Override
//		public int compare(Object o1, Object o2) {
//			// TODO Auto-generated method stub
//			return ((Snps2Vars)o1).getPos().compareTo(  ((Snps2Vars)o2).getPos() );
//		}
//	}
//
//	class SnpsAllvarsPosSorter implements Comparator {
//
//		@Override
//		public int compare(Object o1, Object o2) {
//			// TODO Auto-generated method stub
//			return ((SnpsAllvarsPos)o1).getPos().compareTo(  ((SnpsAllvarsPos)o2).getPos() );
//		}
//	}
	

//
//	class SnpsAllvarsPosComparator implements Comparator {
//		@Override
//		public int compare(Object o1, Object o2) {
//			// TODO Auto-generated method stub
//			SnpsAllvarsPos pos1 = (SnpsAllvarsPos)o1;
//			SnpsAllvarsPos pos2 = (SnpsAllvarsPos)o2;
//			return pos1.getPos().compareTo(pos2.getPos());
//		}
//	}
//	
//	/**
//	 * Sorts variety by mismatch desc, subpopulation, then country, then id
//	 * Used in Mismatch ordering for the same number of mismatch,
//	 * assuming variety from same subpopulation, then country will be closer relative than random 
//	 * @author lmansueto
//	 *
//	 */
//	class  SnpsStringAllvarsImplSorter implements Comparator {
//		@Override
//		public int compare(Object o1, Object o2) {
//			// TODO Auto-generated method stub
//			SnpsStringAllvars s1 = (SnpsStringAllvars)o1; 
//			SnpsStringAllvars s2 = (SnpsStringAllvars)o2;
//			int ret = -s1.getMismatch().compareTo(s2.getMismatch());
//			if(ret==0) {
//				//return s1.getVar().compareTo( s2.getVar());
//				if(s1 instanceof IndelsStringAllvars && s2 instanceof IndelsStringAllvars) {
//					IndelsStringAllvars is1 = (IndelsStringAllvars)s1; 
//					IndelsStringAllvars is2 = (IndelsStringAllvars)s2;
//					//if(is1.getMapPos2Indels().size()<is2.getMapPos2Indels().size()) ret = 1;
//					//else if(is1.getMapPos2Indels().size()>is2.getMapPos2Indels().size()) ret = -1;
//					
//					//int sumIns1 = 0;
//					//int sumIns2 = 0;
//					//int sumDel1 = 0;
//					//int sumDel2 = 0;
//					
//					Set setAlleles1 = new HashSet();
//					Set setAlleles2 = new HashSet();
//					Iterator<IndelsAllvars> itIndels1 = is1.getMapPos2Indels().values().iterator();
//					Iterator<IndelsAllvars> itIndels2 = is1.getMapPos2Indels().values().iterator();
//					while(itIndels1.hasNext()) {
//						IndelsAllvars indel = itIndels1.next();
//						setAlleles1.add( indel.getAllele1() );
//					}
//					while(itIndels2.hasNext()) {
//						IndelsAllvars indel = itIndels2.next();
//						setAlleles2.add( indel.getAllele1() );
//					}
//					Set allele1notin2 = new HashSet(setAlleles1);
//					allele1notin2.removeAll(setAlleles2);
//					Set allele2notin1 = new HashSet(setAlleles2);
//					allele2notin1.removeAll(setAlleles1);
//					Set uniques = new HashSet(allele1notin2);
//					uniques.addAll(allele2notin1);
//					if(allele1notin2.size()>allele2notin1.size())
//						ret = uniques.size();
//					else if(allele1notin2.size()<allele2notin1.size())
//						ret = -uniques.size();
//					else if(uniques.size()!=0) {
//						if(setAlleles1.size()>setAlleles2.size())
//							return setAlleles1.size();
//						else if(setAlleles1.size()<setAlleles2.size())
//							return -setAlleles2.size();
//						else ret = 0;
//					} else ret=0;
//				}
//				
//				if(ret==0)
//				{
//				
//					Variety v1 =varietyfacade.getMapId2Variety().get(s1.getVar());
//					Variety v2 =varietyfacade.getMapId2Variety().get(s2.getVar());
//					if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
//					{
//						ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
//						if( ret==0 ) {
//							if(v1.getCountry()!=null && v2.getCountry()!=null) {
//								ret = v1.getCountry().compareTo(v2.getCountry());
//								if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
//								else return ret;
//							}
//						} else return ret;
//					} else if(v1.getCountry()!=null && v2.getCountry()!=null) {
//							ret = v1.getCountry().compareTo(v2.getCountry());
//							if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
//							else return ret;
//					} return v1.getVarietyId().compareTo(v2.getVarietyId());
//					
//				} else return ret;
//				
//				
//			} else return ret;
//		}
//	}
//	
//	/**
//	 * Sort pairs descending
//	 * @author lmansueto
//	 *
//	 */
//	class  SnpsString2VarsImplSorter implements Comparator {
//		@Override
//		public int compare(Object o1, Object o2) {
//			// TODO Auto-generated method stub
//			Snps2VarsCountmismatch s1 = (Snps2VarsCountmismatch)o1; 
//			Snps2VarsCountmismatch s2 = (Snps2VarsCountmismatch)o2;
//			return -s1.getMismatch().compareTo(s2.getMismatch());
//		}
//	}
//	
//	
//	class VarSubpopCntrySorter implements Comparator {
//		@Override
//		public int compare(Object o1, Object o2) {
//			// TODO Auto-generated method stub
//			Variety v1 =(Variety)o1;
//			Variety v2 =(Variety)o2;
//			if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
//			{
//				int ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
//				if( ret==0 ) {
//					if(v1.getCountry()!=null && v2.getCountry()!=null) {
//						ret = v1.getCountry().compareTo(v2.getCountry());
//						if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
//						else return ret;
//					}
//				} else return ret;
//			} else if(v1.getCountry()!=null && v2.getCountry()!=null) {
//					int ret = v1.getCountry().compareTo(v2.getCountry());
//					if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
//					else return ret;
//			} return v1.getVarietyId().compareTo(v2.getVarietyId());
//		}
//	}
//	
