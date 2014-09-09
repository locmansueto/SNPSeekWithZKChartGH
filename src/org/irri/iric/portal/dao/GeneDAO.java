package org.irri.iric.portal.dao;

import java.util.Set;

import org.irri.iric.portal.domain.Gene;

public interface GeneDAO {

	Set findAllGene();
	Gene findGeneByName(String name);
	
}
