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
	
	
	
	public GenotypeQueryParams(Collection colVarIds, String sChr, Long lStart,
			Long lEnd, boolean bSNP, boolean bIndel, boolean bCoreonly,
			boolean bMismatchonly, Collection poslist, String sSubpopulation,
			String sLocus) {
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
	 
	 
	
	
}
