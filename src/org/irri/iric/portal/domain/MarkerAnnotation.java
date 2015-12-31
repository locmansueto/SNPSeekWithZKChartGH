package org.irri.iric.portal.domain;

import java.util.List;

public interface MarkerAnnotation extends Position {
	
	List<Locus> getQTL1();
	List<Locus> getQTL2();
	List<Locus> getGenes();
	void addGene(Locus loc);
	void addQTL1(Locus loc);
	void addQTL2(Locus loc);
}
