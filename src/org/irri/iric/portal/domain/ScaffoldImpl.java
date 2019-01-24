package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public class ScaffoldImpl implements Scaffold {

	private BigDecimal featureId;
	private long length;
	private String name;

	public ScaffoldImpl(BigDecimal featureId, long length, String name) {
		super();
		this.featureId = featureId;
		this.length = length;
		this.name = name;
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

}
