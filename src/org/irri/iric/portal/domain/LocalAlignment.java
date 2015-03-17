package org.irri.iric.portal.domain;

public interface LocalAlignment {


	/**
	 * Query accession
	 * @return
	 */
	public String getQacc();
	/**
	 * Subject accession
	 * @return
	 */
	public String getSacc();
	
	
	public Double getEvalue();
	public Long getQstart();
	public Long getQend();
	public Long getSstart();
	public Long getSend();
	public Integer getSStrand();
	
	public Double getRawScore();
	public Long getAlignmentLength();
	public Double getPercentMatches();
	public Long getMatches();
	public Long getMismatches();

	
	/* blast result tabular result columns
	score Raw score
    length means Alignment length
    pident means Percentage of identical matches
    nident means Number of identical matches
    mismatch means Number of mismatches
    */

}
