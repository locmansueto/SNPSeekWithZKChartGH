package org.irri.iric.portal.service;

import org.irri.iric.portal.domain.Gene;

public interface GeneService {
	
	public Gene findGeneByName(String name);
}
