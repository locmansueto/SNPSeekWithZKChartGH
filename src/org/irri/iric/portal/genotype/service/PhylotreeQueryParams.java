package org.irri.iric.portal.genotype.service;

import java.util.Collection;

public class PhylotreeQueryParams    {

	private GenotypeQueryParams genotype;
	private String method;
	private int topN;
	private double minDist;
	private double scale;
	
	public PhylotreeQueryParams(GenotypeQueryParams genotype, String method,
			int topN, double minDist, double scale) {
		super();
		this.genotype = genotype;
		this.method = method;
		this.topN = topN;
		this.minDist = minDist;
		this.scale=scale;
	}
	public GenotypeQueryParams getGenotype() {
		return genotype;
	}
	public String getMethod() {
		return method;
	}
	public int getTopN() {
		return topN;
	}
	public double getMinDist() {
		return minDist;
	}
	public double getScale() {
		return scale;
	}
	
	
	
}
