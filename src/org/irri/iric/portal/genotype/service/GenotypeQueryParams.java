package org.irri.iric.portal.genotype.service;

import java.util.Collection;

public class GenotypeQueryParams {

	
	private Collection colVarIds;
	private String sChr;
	private Long lStart;
	private Long lEnd;
	private boolean bSNP;
	private boolean bIndel;
	private boolean bCoreonly;
	private boolean bMismatchonly;
	private Collection poslist;
	private String sSubpopulation;
	private String sLocus;
	private boolean bGraySynonymous=false;
	private boolean bExcludeSynonymous=false;
	private boolean bAlignIndels=true;
	private String delimiter;
	private String filename;
	
	
	
	public void setbAlignIndels(boolean bAlignIndels) {
		this.bAlignIndels = bAlignIndels;
	}



	public GenotypeQueryParams(Collection colVarIds, String sChr, Long lStart,
			Long lEnd, boolean bSNP, boolean bIndel, boolean bCoreonly,
			boolean bMismatchonly, Collection poslist, String sSubpopulation,
			String sLocus, boolean bAlignIndels) {
		super();
		this.colVarIds = colVarIds;
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
	public boolean isbGraySynonymous() {
		return bGraySynonymous;
	}
	public boolean isbExcludeSynonymous() {
		return bExcludeSynonymous;
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
	
	
	 
	 
	
	
}
