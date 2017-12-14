package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.Gene;

public interface GeneDAO {
	
	/**
	 * Get Gene object with name
	 * @param name
	 * @return
	 */
	Gene findGeneByName(String name, Integer organismId);
	List<Gene>  findGeneWithNames(Collection<String> genenames, Integer organismId);

	/**
	 * Get all genes for organism
	 * @param organismId
	 * @return
	 */
	Set findAllGene(Integer organismId);
	
	
	
}
