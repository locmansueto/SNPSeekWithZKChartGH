package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.portal.domain.Scaffold;

public interface ScaffoldDAO {

	/**
	 * Get scaffolds/contigs for organism name
	 * @param organism
	 * @return
	 */
	public List<Scaffold> getScaffolds(String organism);

	/**
	 * Get scaffolds/contigs for organism ID
	 * @param organism
	 * @return
	 */
	
	public List<Scaffold> getScaffolds(BigDecimal organism);
	
	/**
	 * Get length of scaffolds/contigs for organism name
	 * @param organism
	 * @return
	 */
	public Long getScaffoldLength(String scaffold, String organism);

	
	/**
	 * Get length of scaffolds/contigs for organism ID
	 * @param organism
	 * @return
	 */
	public Long getScaffoldLength(String scaffold, BigDecimal organism);
}
