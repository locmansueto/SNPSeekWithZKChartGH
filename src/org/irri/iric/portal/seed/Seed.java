package org.irri.iric.portal.seed;

import org.zkoss.bind.annotation.NotifyChange;

public class Seed implements Comparable {

	@Override
	public int compareTo(Object o) {
		
		return accession.compareToIgnoreCase((String) o);
	}

	public Double getPrice() {
		// return Math.max(0,gram-maxFree)*pricePer10Gram/10;
		return Double.valueOf(Math.max(0, gram - maxFree) * pricePer10Gram * 10).intValue() * 1.0 / 100;
	}

	/*
	 * public void setPrice(Double price) { this.price = price; }
	 */
	public Seed(String accession, String varname, String stockType, Integer gram, Double pricePer10Gram,
			Double maxFree) {
		super();
		this.accession = accession;
		this.varname = varname;
		this.stockType = stockType;
		this.gram = gram;
		this.pricePer10Gram = pricePer10Gram;
		// this.price= Math.max(0,gram-maxFree)*pricePer10Gram/10;
		this.maxFree = maxFree;

	}

	public String getAccession() {
		return accession;
	}

	public void setAccession(String accession) {
		this.accession = accession;
	}

	public String getVarname() {
		return varname;
	}

	public void setVarname(String varname) {
		this.varname = varname;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public Integer getGram() {
		return gram;
	}

	@NotifyChange({ "gram", "price" })
	public void setGram(Integer gram) {
		this.gram = gram;
		// setPrice(Math.max(0,gram-maxFree)*getPricePerGram()/10);

	}

	public Double getPricePerGram() {
		return pricePer10Gram;
	}

	@NotifyChange({ "pricePerGram", "price" })
	public void setPricePerGram(Double pricePer10Gram) {
		this.pricePer10Gram = pricePer10Gram;
		// setPrice(pricePer10Gram*Math.max(0, getGram()-maxFree)/10);
	}

	@NotifyChange({ "maxFree", "price" })
	public void setMaxFree(Double maxFree) {
		this.maxFree = maxFree;
	}

	private String accession;
	private String varname;
	private String stockType;
	private Integer gram;
	private Double pricePer10Gram;
	// private Double price;
	private Double maxFree = 0.0;

}
