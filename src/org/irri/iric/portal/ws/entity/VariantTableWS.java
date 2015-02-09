package org.irri.iric.portal.ws.entity;

import java.util.Map;

import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTableArray;
import org.irri.iric.portal.genotype.service.GenotypeQueryParams;

public class VariantTableWS implements org.irri.iric.portal.domain.VariantTableArray  {

//	private String message;
//	private Long position[];
//	private String reference[];
//	private Object varalleles[][];
//	private Long varid[];
//	private String varname[];
//	private Double varmismatch[];
	
	private VariantTableArray table;
	
	
	
	public VariantTableWS(VariantTableArray table) {
		super();
		this.table = table;
	}

	@Override
	public String getMessage() {
		return table.getMessage();
	}

	@Override
	public void setMessage(String message) {
		table.setMessage(message);
	}

	@Override
	public Double[] getPosition() {
		return table.getPosition();
	}
	
	@Override
	public String[] getReference() {
		return table.getReference();
	}

	@Override
	public Object[][] getVaralleles() {
		return table.getVaralleles();
	}

	
	@Override
	public Long[] getVarid() {
		return table.getVarid();
	}

	@Override
	public String[] getVarname() {
		return table.getVarname();
	}


	@Override
	public Double[] getVarmismatch() {
		return table.getVarmismatch();
	}

	@Override
	public void setVariantStringData(VariantStringData data,
			GenotypeQueryParams params) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VariantStringData getVariantStringData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
