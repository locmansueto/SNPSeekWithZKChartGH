package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import oracle.sql.DATE;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.Variety;
import org.zkoss.zk.ui.Sessions;

/**
 * Genotype query parameters
 * @author lmansueto
 *
 */

public class GenotypeQueryParams {

	// reference organism
	private String organism = AppContext.getDefaultOrganism();
	// list of variety IDs from variety list
	private Collection colVarIds;
	// contig name
	private String sChr;
	// start bp position
	private Long lStart;
	// end base position
	private Long lEnd;
	// include SNPs 
	private boolean bSNP;
	// include indels
	private boolean bIndel;
	// include hetero
	private boolean bHeteroIndel;
	
	// use core SNPs only
	private boolean bCoreonly;
	// query mismatch only
	private boolean bMismatchonly;
	// list of positions from SNP list
	private Collection poslist;
	// return only varieties in subpopulation
	private String sSubpopulation;
	// query locus region
	private String sLocus;
	// query regions on list of loci, from Locus list
	private Collection colLoci;
	// query is 2-variety comparison
	private boolean bPairwiseComparison=false;
	// align the indels
	private boolean bAlignIndels=true;
	// delimiter in table download 
	private String delimiter;
	// filename in download
	private String filename;
	// color table alleles base on mismatch with reference
	private boolean bColorByMismatch=true;
	// color alleles by value
	private boolean bColorByAllele=false;
	// result is fasta format, don't display table
	private boolean bFastaSequence=false;
	
	// for the following options, query is for all snps and option
	// affects only the display, change of option after first query 
	// only redraws the table
	// display all snps
	private boolean bAllSnps=true;
	// display all snps, highlight the non-synonymous
	private boolean bHighlightNonsynSnps=false;
	// display only non-synonymous
	private boolean bNonsynSnps=false;
	// display only non-synonymous plus splice sites
	private boolean bNonsynPlusSpliceSnps=false;
	
	// for multiple reference query
	// return only contigs aligning with the query contigs
	private boolean limitToQueryContig=true;
	// if reference is not Nipponbare, display the Nipponbare coordinates
	private boolean bShowNPBPositions=false;
	// show alleles for all reference genomes
	private boolean bShowAllRefAlleles=false;
	
	// display phenotype in table
	private String phenotype=null;

	// dataset to use, 
	private Set setDataset=new HashSet();
	
	public void setbAlignIndels(boolean bAlignIndels) {
		this.bAlignIndels = bAlignIndels;
	}



	/**
	 * 
	 * @param colVarIds	Collection of variety IDs
	 * @param sChr	Contig name
	 * @param lStart	Start bp position
	 * @param lEnd	End bp position
	 * @param bSNP	Query SNPs
	 * @param bIndel	Query INDELs
	 * @param bCoreonly	Use core SNPs omly
	 * @param bMismatchonly	Include only varieties with mismatch in region
	 * @param poslist	Position list
	 * @param sSubpopulation	Query only subpopulation
	 * @param sLocus	Query within locus
	 * @param bAlignIndels	Display INDels as multiple sequence alignment
	 * @param bShowAllRefAlleles	Show alleles for all reference genomes
	 */
	public GenotypeQueryParams(Collection colVarIds, String sChr, Long lStart,
			Long lEnd, boolean bSNP, boolean bIndel, boolean bCoreonly,
			boolean bMismatchonly, Collection poslist, String sSubpopulation,
			String sLocus, boolean bAlignIndels, boolean bShowAllRefAlleles) {
		super();
		
		
		setDataset.add(AppContext.getSNPSet());
		//setDataset.add(SnpsAllvarsPosDAO.DATASET_SNPINDELV1);
		
		this.sChr = sChr;
		this.lStart = lStart;
		this.lEnd = lEnd;
		this.bSNP = bSNP;
		this.bIndel = bIndel;
		this.bCoreonly = bCoreonly;
		this.bMismatchonly = bMismatchonly;
		this.poslist = poslist;
		this.sSubpopulation = sSubpopulation;
		this.sLocus = sLocus;
		this.bAlignIndels=bAlignIndels;
		this.bShowAllRefAlleles = bShowAllRefAlleles;
		this.colVarIds = colVarIds;
		
		if(colVarIds!=null && !colVarIds.isEmpty()) {
			Set varids = new HashSet();
			Iterator itvarids= colVarIds.iterator();
			while(itvarids.hasNext()) {
				Object objvar = itvarids.next();
				if(objvar instanceof BigDecimal)
					varids.add(  objvar );
				else if(objvar instanceof Variety)
					varids.add(  ((Variety)objvar).getVarietyId() );
				else if(objvar instanceof Long)
					varids.add(  BigDecimal.valueOf( (Long)objvar) );
				else if(objvar instanceof Integer)
					varids.add(  BigDecimal.valueOf( (Integer)objvar) );
			}
			this.colVarIds = varids;
		}
		
		String posvarids = "";
		if(poslist!=null) posvarids+=";poslist=" + poslist.size();
		if(colVarIds!=null) posvarids+=";varlist=" + colVarIds.size();
		if(this.colLoci!=null) posvarids+=";colLoci=" + colLoci.size();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		if(Sessions.getCurrent()!=null) {
			AppContext.info("QUERY time:" + dateFormat.format(date) + ";locah host=" + Sessions.getCurrent().getLocalAddr() + "; " + Sessions.getCurrent().getLocalName() + ";" +
			";remote host=" + Sessions.getCurrent().getRemoteAddr() + "; " + Sessions.getCurrent().getRemoteHost() + 
			";chr=" + sChr +";start=" + lStart + ";end=" + lEnd + ";snp=" + bSNP + ";indel=" + bIndel + ";iscore=" + bCoreonly + ";mismatchonly=" + bMismatchonly + 
			";subpupulation=" + sSubpopulation + ";locus=" + sLocus + posvarids );
		} else {
			AppContext.info("QUERY time:" + dateFormat.format(date) + ";;;;;;" + 
					"chr=" + sChr +";start=" + lStart + ";end=" + lEnd + ";snp=" + bSNP + ";indel=" + bIndel + ";iscore=" + bCoreonly + ";mismatchonly=" + bMismatchonly + 
					";subpupulation=" + sSubpopulation + ";locus=" + sLocus + posvarids );
		}
		
		
		
	}
	
	
	/**
	 * Change the position
	 * @param newPos	New position
	 * @return Old position
	 */
	public MultiReferenceLocus setNewPosition(MultiReferenceLocus newPos) {
		MultiReferenceLocus origPos = new MultiReferenceLocusImpl( this.getOrganism(), this.sChr, this.lStart.intValue(), this.lEnd.intValue() , 1);
		this.sChr = newPos.getContig() ;
		this.lStart = newPos.getFmin().longValue();
		this.lEnd = newPos.getFmax().longValue();
		this.organism = newPos.getOrganism();
		
		return origPos;
	}
	

	/**
	 * Gets loci collection
	 * @return
	 */
	public Collection getColLoci() {
		return colLoci;
	}



	/**
	 * Sets loci collection
	 * @param colLoci
	 */
	public void setColLoci(Collection colLoci) {
		if(colLoci!=null && !colLoci.isEmpty())
			this.colLoci = colLoci;
	}



	/**
	 * Sets SNPs color scheme
	 * @param bColorByMismatch
	 * @param bColorByAllele
	 */
	public void setColors(boolean bColorByMismatch, boolean bColorByAllele) {
		this.bColorByMismatch = bColorByMismatch;
		this.bColorByAllele= bColorByAllele;
		AppContext.info("QUERY COLOR mismatch=" +   bColorByMismatch + ";allel=" + bColorByAllele);
	}

	/**
	 * Sets which SNP types to include
	 * @param bAllSnps	All SNPs
	 * @param bHighlightNonsynSnps	All, highlight non-synonymous SNPs 
	 * @param bNonsynSnps	Include non-synonymous SNPs only
	 * @param bNonsynPlusSpliceSnps	Include non-synonymous, splice site donor and acceptor SNPs
	 */
	public void setIncludedSnps(boolean bAllSnps, boolean bHighlightNonsynSnps, boolean bNonsynSnps, boolean bNonsynPlusSpliceSnps) {
		
		this.bAllSnps=bAllSnps;
		this.bHighlightNonsynSnps=bHighlightNonsynSnps;
		this.bNonsynSnps=bNonsynSnps;
		this.bNonsynPlusSpliceSnps=bNonsynPlusSpliceSnps;
		AppContext.info("QUERY SNP allsnp=" +   bAllSnps + ";highlightnonsyn=" + bHighlightNonsynSnps + ";nonsynonly=" + bNonsynSnps + ";nonsynplussplice" + bNonsynPlusSpliceSnps);
	}
	
	

	public Collection getColVarIds() {
		return colVarIds;
	}
	public String getsChr() {
		return sChr;
	}
	public Long getlStart() {
		return lStart;
	}
	public Long getlEnd() {
		return lEnd;
	}
	public boolean isbSNP() {
		return bSNP;
	}
	public boolean isbIndel() {
		return bIndel;
	}
	public boolean isbCoreonly() {
		return bCoreonly;
	}
	public boolean isbMismatchonly() {
		return bMismatchonly;
	}
	public Collection getPoslist() {
		return poslist;
	}
	public String getsSubpopulation() {
		return sSubpopulation;
	}
	public String getsLocus() {
		return sLocus;
	}

	public boolean isbAlignIndels() {
		return bAlignIndels;
	}
	public void setColVarIds(Collection colVarIds) {
		this.colVarIds = colVarIds;
	}
	public void setbMismatchonly(boolean bMismatchonly) {
		this.bMismatchonly = bMismatchonly;
	}
	public void setsSubpopulation(String sSubpopulation) {
		this.sSubpopulation = sSubpopulation;
	}
	public String getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	public void setsChr(String sChr) {
		this.sChr = sChr;
	}
	public void setlStart(Long lStart) {
		this.lStart = lStart;
	}
	public void setlEnd(Long lEnd) {
		this.lEnd = lEnd;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isbAllSnps() {
		return bAllSnps;
	}


	public boolean isbHighlightNonsynSnps() {
		return bHighlightNonsynSnps;
	}



	public boolean isbNonsynSnps() {
		return bNonsynSnps;
	}



	public boolean isbNonsynPlusSpliceSnps() {
		return bNonsynPlusSpliceSnps;
	}



	public boolean isbColorByMismatch() {
		return bColorByMismatch;
	}



	public void setbColorByMismatch(boolean bColorByMismatch) {
		this.bColorByMismatch = bColorByMismatch;
	}



	public boolean isbColorByAllele() {
		return bColorByAllele;
	}



	public void setbColorByAllele(boolean bColorByAllele) {
		this.bColorByAllele = bColorByAllele;
	}


	/**
	 * Query is two-variety comparison
	 * @param name1
	 * @param name2
	 */
	public void setPairwiseComparison(String name1, String name2) {
		
		AppContext.info("QUERY PAIRWISE var1=" + name1 + ";var2=" + name2);
		this.bPairwiseComparison = true;
		
	}

	public boolean isbPairwiseComparison() {
		return bPairwiseComparison;
	}

	public boolean hasLocusList() {
		// TODO Auto-generated method stub
		return this.colLoci!=null && !this.colLoci.isEmpty();
	}


	public String getOrganism() {
		return organism;
	}



	/**
	 * Sets the reference genome to query
	 * @param organism	Organism common name
	 * @param bShowNPBPositions	For non-Nipponbare reference, show the Nipponbare positions
	 * @param limitToQueryContig	Query only this contig
	 */
	public void setOrganism(String organism, boolean bShowNPBPositions, boolean limitToQueryContig) {
		this.organism = organism;
		this.bShowNPBPositions=bShowNPBPositions;
		this.limitToQueryContig = limitToQueryContig;
	}



	public boolean isbShowNPBPositions() {
		return bShowNPBPositions;
	}

	public boolean isLimitToQueryContig() {
		return limitToQueryContig;
	}

	
	public boolean isLocusList() {
		return this.colLoci!=null && !colLoci.isEmpty();
	}
	
	public boolean isSNPList() {
		return this.poslist!=null && !poslist.isEmpty();
	}


	public boolean isbShowAllRefAlleles() {
		return bShowAllRefAlleles;
	}
	
	
	public void addDataset(String setname) {
		this.setDataset.add(setname);
	}
	
	public boolean includeDataset(String setname) {
		return setDataset.contains(setname);
	}



	public boolean isbFastaSequence() {
		return bFastaSequence;
	}



	public void setbFastaSequence(boolean bFastaSequence) {
		this.bFastaSequence = bFastaSequence;
	}



	public String getPhenotype() {
		return phenotype;
	}



	public void setPhenotype(String phenotype) {
		this.phenotype = phenotype;
	}



	public void setbIndel(boolean bIndel) {
		this.bIndel = bIndel;
	}

	public boolean isbHeteroIndel() {
		return bHeteroIndel;
	}

	public void setbHeteroIndel(boolean bHeteroIndel) {
		this.bHeteroIndel = bHeteroIndel;
	}
	
	
	
}
