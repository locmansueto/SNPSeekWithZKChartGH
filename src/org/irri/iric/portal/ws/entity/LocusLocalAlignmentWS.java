package org.irri.iric.portal.ws.entity;

import org.irri.iric.portal.domain.LocalAlignment;
import org.irri.iric.portal.domain.LocalAlignmentImpl;

public class LocusLocalAlignmentWS implements LocalAlignment {

	private LocalAlignmentImpl localalignment;

	public LocusLocalAlignmentWS(LocalAlignmentImpl localalignment) {
		super();
		this.localalignment = localalignment;
	}

	@Override
	public String getQacc() {
		// TODO Auto-generated method stub
		return localalignment.getQacc() ;
	}

	@Override
	public String getSacc() {
		// TODO Auto-generated method stub
		return localalignment.getSacc();
	}

	@Override
	public Double getEvalue() {
		// TODO Auto-generated method stub
		return localalignment.getEvalue();
	}

	@Override
	public Long getQstart() {
		// TODO Auto-generated method stub
		return localalignment.getQstart();
	}

	@Override
	public Long getQend() {
		// TODO Auto-generated method stub
		return localalignment.getQend();
	}

	@Override
	public Long getSstart() {
		// TODO Auto-generated method stub
		return localalignment.getQstart();
	}

	@Override
	public Long getSend() {
		// TODO Auto-generated method stub
		return localalignment.getSend();
	}

	@Override
	public Integer getSstrand() {
		// TODO Auto-generated method stub
		return localalignment.getSstrand();
	}

	@Override
	public Double getRawScore() {
		// TODO Auto-generated method stub
		return localalignment.getRawScore();
	}

	@Override
	public Long getAlignmentLength() {
		// TODO Auto-generated method stub
		return localalignment.getAlignmentLength();
	}

	@Override
	public Double getPercentMatches() {
		// TODO Auto-generated method stub
		return localalignment.getPercentMatches();
	}

	@Override
	public Long getMatches() {
		// TODO Auto-generated method stub
		return localalignment.getMatches();
	}

	@Override
	public Long getMismatches() {
		// TODO Auto-generated method stub
		return localalignment.getMismatches();
	}

	@Override
	public String getQSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQSequence(String seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSequence(String seq) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	/*
	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getChr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getFmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getFmax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getStrand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return null;
	}

/*	
	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return localalignment.getUniquename();
	}

	@Override
	public Integer getChr() {
		// TODO Auto-generated method stub
		return localalignment.getChr();
	}

	@Override
	public Integer getFmin() {
		// TODO Auto-generated method stub
		return localalignment.getFmin();
	}

	@Override
	public Integer getFmax() {
		// TODO Auto-generated method stub
		return localalignment.getFmax();
	}

	@Override
	public Integer getStrand() {
		// TODO Auto-generated method stub
		return localalignment.getSstrand();
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return localalignment.getContig();
	}
	
	
	*/
	
}
