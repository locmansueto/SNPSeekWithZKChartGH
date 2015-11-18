package org.irri.iric.portal.domain;

/**
 * Implementation of MultiReferenceLocus
 * @author LMansueto
 *
 */
public class MultiReferenceLocusImpl implements  MultiReferenceLocus {
	
	private String organism;
	private String contig;
	private Integer start;
	private Integer end;
	private Integer strand;
	private String uniquename;
	
	
	public MultiReferenceLocusImpl(String organism, String contig, Integer start,
			Integer end, Integer strand) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.start = start;
		this.end = end;
		this.strand = strand;
		
	}
	public MultiReferenceLocusImpl(String organism, String contig, Integer start,
			Integer end, Integer strand, String name) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.uniquename=name;
		
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
	public Integer getFmin() {
		// TODO Auto-generated method stub
		return start;
	}

	@Override
	public Integer getFmax() {
		// TODO Auto-generated method stub
		return end;
	}

	@Override
	public Integer getStrand() {
		// TODO Auto-generated method stub
		return strand;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + organism + " " + contig + " " + start + "-" + end  + " " + strand + ")";
	}

	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return this.uniquename;
	}

	@Override
	public String getChr() {
		// TODO Auto-generated method stub
		return getContig();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
