package org.irri.iric.portal.domain;

public class LocusLocalAlignment  implements Locus , LocalAlignment {

	String qacc;
	String sacc;
	Double evalue;
	Long qstart;
	Long qend;
	Long sstart;
	Long send;
	Integer sstrand;
	
	
	Double rawScore;
	Long alignmentLength;
	Double percentMatches;
	Long matches;
	Long mismatches;
	
	public LocusLocalAlignment(String qacc, String sacc, Double evalue,
			Long qstart, Long qend, Long sstart, Long send)  {
		super();
		this.qacc = qacc;
		this.sacc = sacc;
		this.evalue = evalue;
		this.qstart = qstart;
		this.qend = qend;
		this.sstart = sstart;
		this.send = send;
	}
	
	
	public LocusLocalAlignment(String qacc, String sacc, Double evalue,
			Long qstart, Long qend, Long sstart, Long send, Integer sstrand, Double rawScore,
			Long alignmentLength, Double percentMatches, Long matches,
			Long mismatches) {
		super();
		this.qacc = qacc;
		this.sacc = sacc;
		this.evalue = evalue;
		this.qstart = qstart;
		this.qend = qend;
		this.sstart = sstart;
		this.send = send;
		this.sstrand = sstrand;
		this.rawScore = rawScore;
		this.alignmentLength = alignmentLength;
		this.percentMatches = percentMatches;
		this.matches = matches;
		this.mismatches = mismatches;
	}



	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return this.sacc;
	}

	@Override
	public Integer getChr() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getFmin() {
		// TODO Auto-generated method stub
		return this.sstart.intValue();
	}

	@Override
	public Integer getFmax() {
		// TODO Auto-generated method stub
		return this.send.intValue() ;
	}

	@Override
	public Integer getStrand() {
		// TODO Auto-generated method stub
		return this.sstrand;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return this.sacc ;
	}

	@Override
	public String getQacc() {
		return qacc;
	}

	@Override
	public String getSacc() {
		return sacc;
	}

	@Override
	public Double getEvalue() {
		return evalue;
	}

	@Override
	public Long getQstart() {
		return qstart;
	}

	@Override
	public Long getQend() {
		return qend;
	}

	@Override
	public Long getSstart() {
		return sstart;
	}

	@Override
	public Long getSend() {
		return send;
	}

	@Override
	public Double getRawScore() {
		// TODO Auto-generated method stub
		return rawScore;
	}

	@Override
	public Long getAlignmentLength() {
		// TODO Auto-generated method stub
		return alignmentLength;
	}

	@Override
	public Double getPercentMatches() {
		// TODO Auto-generated method stub
		return percentMatches;
	}

	@Override
	public Long getMatches() {
		// TODO Auto-generated method stub
		return matches;
	}

	@Override
	public Long getMismatches() {
		// TODO Auto-generated method stub
		return mismatches;
	}


	@Override
	public Integer getSStrand() {
		// TODO Auto-generated method stub
		return sstrand;
	}
	
	
	
	
	
}
