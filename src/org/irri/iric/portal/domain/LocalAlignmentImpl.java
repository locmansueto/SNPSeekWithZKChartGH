package org.irri.iric.portal.domain;

/**
 * Implementation of LocalAlignment
 * @author LMansueto
 *
 */
public class LocalAlignmentImpl  implements LocalAlignment {

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
	String ssequence;
	String qsequence;
	
	
	public LocalAlignmentImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LocalAlignmentImpl(String qacc, String sacc, Double evalue,
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
	
	
	public LocalAlignmentImpl(String qacc, String sacc, Double evalue,
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


/*
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
	
	*/

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
	public Integer getSstrand() {
		return sstrand;
	}


	public void setSstrand(Integer sstrand) {
		this.sstrand = sstrand;
	}


	public void setQacc(String qacc) {
		this.qacc = qacc;
	}


	public void setSacc(String sacc) {
		this.sacc = sacc;
	}


	public void setEvalue(Double evalue) {
		this.evalue = evalue;
	}


	public void setQstart(Long qstart) {
		this.qstart = qstart;
	}


	public void setQend(Long qend) {
		this.qend = qend;
	}


	public void setSstart(Long sstart) {
		this.sstart = sstart;
	}


	public void setSend(Long send) {
		this.send = send;
	}


	public void setRawScore(Double rawScore) {
		this.rawScore = rawScore;
	}


	public void setAlignmentLength(Long alignmentLength) {
		this.alignmentLength = alignmentLength;
	}


	public void setPercentMatches(Double percentMatches) {
		this.percentMatches = percentMatches;
	}


	public void setMatches(Long matches) {
		this.matches = matches;
	}


	public void setMismatches(Long mismatches) {
		this.mismatches = mismatches;
	}


	@Override
	public String getQSequence() {
		// TODO Auto-generated method stub
		return qsequence;
	}


	@Override
	public String getSSequence() {
		// TODO Auto-generated method stub
		return ssequence;
	}


	@Override
	public void setQSequence(String seq) {
		// TODO Auto-generated method stub
		qsequence=seq;
	}


	@Override
	public void setSSequence(String seq) {
		// TODO Auto-generated method stub
		ssequence=seq;
	}
	
	
	
	
	
}
