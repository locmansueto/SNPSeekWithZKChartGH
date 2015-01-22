package org.irri.iric.portal.ws.entity;

import java.util.Map;

import org.irri.iric.portal.domain.VariantStringData;

public class VariantTable implements org.irri.iric.portal.domain.VariantTable  {

	private String message;
	private Long position[];
	private String reference[];
	private Object varalleles[][];
	private Long varid[];
	private String varname[];
	private Double varmismatch[];
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	//@Override
	public Long[] getPosition() {
		return position;
	}
	//@Override
	public void setPosition(Long[] position) {
		this.position = position;
	}
	//@Override
	public String[] getReference() {
		return reference;
	}
	//@Override
	public void setReference(String[] reference) {
		this.reference = reference;
	}
	//@Override
	public Object[][] getVaralleles() {
		return varalleles;
	}
	//@Override
	public void setVaralleles(Object[][] varalleles) {
		this.varalleles = varalleles;
	}

	//@Override
	public Long[] getVarid() {
		return varid;
	}

	//@Override
	public void setVarid(Long[] varid) {
		this.varid = varid;
	}

	//@Override
	public String[] getVarname() {
		return varname;
	}

	//@Override
	public void setVarname(String[] varname) {
		this.varname = varname;
	}

	//@Override
	public Double[] getVarmismatch() {
		return varmismatch;
	}

	//@Override
	public void setVarmismatch(Double[] varmismatch) {
		this.varmismatch = varmismatch;
	}

	@Override
	public void setVariantStringData(VariantStringData data) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
