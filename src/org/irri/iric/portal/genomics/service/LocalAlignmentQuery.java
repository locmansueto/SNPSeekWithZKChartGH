package org.irri.iric.portal.genomics.service;

public class LocalAlignmentQuery {
	
	String queryseq;
	String dbname;
	double evalue;
	//String pvalue;
	String querytype;
	
	public LocalAlignmentQuery(String queryseq, String dbname, String querytype) {
		super();
		this.queryseq = queryseq;
		this.dbname = dbname;
		this.querytype = querytype;
	}
	
	public String getQueryseq() {
		return queryseq;
	}
	public String getDbname() {
		return dbname;
	}

	public double getEvalue() {
		return evalue;
	}

	public void setEvalue(double evalue) {
		this.evalue = evalue;
	}

	public String getQuerytype() {
		return querytype;
	}

	
	
	
	
}