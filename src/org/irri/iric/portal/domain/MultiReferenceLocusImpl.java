package org.irri.iric.portal.domain;

public class MultiReferenceLocusImpl implements  MultiReferenceLocus {
	
	private String organism;
	private String contig;
	private Long start;
	private Long end;
	private Long strand;
	
	
	
	public MultiReferenceLocusImpl(String organism, String contig, Long start,
			Long end, Long strand) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.start = start;
		this.end = end;
		this.strand = strand;
	}

	@Override
	public String getOrganism() {
		// TODO Auto-generated method stub
		return organism;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return contig;
	}

	@Override
	public Long getStart() {
		// TODO Auto-generated method stub
		return start;
	}

	@Override
	public Long getEnd() {
		// TODO Auto-generated method stub
		return end;
	}

	@Override
	public Long getStrand() {
		// TODO Auto-generated method stub
		return strand;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + organism + " " + contig + " " + start + "-" + end  + " " + strand + ")";
	}

	
	
}
