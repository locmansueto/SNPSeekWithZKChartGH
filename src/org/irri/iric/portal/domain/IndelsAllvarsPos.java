package org.irri.iric.portal.domain;

public interface IndelsAllvarsPos extends SnpsAllvarsPos, Comparable {

	public Integer getMaxDellength();
	public String getInsString();
	public Integer getMaxInsLength();
	public Integer getDellength();
}
