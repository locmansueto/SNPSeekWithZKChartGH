package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public class ScaffoldImpl implements Scaffold {

	private BigDecimal featureId;
	private long length;
	private String name;
	private String uniquename;

	
	public ScaffoldImpl(BigDecimal featureId, long length, String name, String uniquename) {
		super();
		this.featureId = featureId;
		this.length = length;
		this.name = name;
		this.uniquename = uniquename;
	}

	@Override
	public BigDecimal getFeatureId() {
		
		return featureId;
	}

	@Override
	public long getLength() {
		
		return length;
	}

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public String getUniquename() {
		return uniquename;
	}

}
