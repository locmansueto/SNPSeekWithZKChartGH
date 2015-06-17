package org.irri.iric.portal.dao;

import java.util.Set;

import org.irri.iric.portal.domain.Gene;

public interface GeneDAO {

	/**
	 * Get all genes
	 * @return
	 */
	//Set findAllGene();
	
	/**
	 * Get Gene object with name
	 * @param name
	 * @return
	 */
	//Gene findGeneByName(String name);

	Gene findGeneByName(String name, Integer organismId);

	Set findAllGene(Integer organismId);
	
}
