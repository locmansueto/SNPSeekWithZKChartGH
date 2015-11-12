package org.irri.iric.portal.ws.entity;

import java.math.BigDecimal;
import java.util.Map;

import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTableArray;

public class VariantTableWS implements org.irri.iric.portal.genotype.VariantTableArray  {

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
	public Position[] getPosition() {
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

	@Override
	public String[] getContigs() {
		// TODO Auto-generated method stub
		return table.getContigs();
	}

	
	
}
