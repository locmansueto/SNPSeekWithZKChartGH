package org.irri.iric.portal.genomics.service;

import java.util.Collection;

public interface GeneOntologyService {

	public static String PANTHER_ENRICHMENTTYPE_FUNCTION = "function";
	public static String PANTHER_ENRICHMENTTYPE_PROCESS = "process";
	public static String PANTHER_ENRICHMENTTYPE_CELLULARLOCATION = "cellular_location";
	public static String PANTHER_ENRICHMENTTYPE_PROTEINCLASS = "protein_class";
	public static String PANTHER_ENRICHMENTTYPE_PATHWAY = "pathway";
	
	
	public String queryGO(String q) throws Exception ; 
	public String overRepresentationTest(String organism, Collection genelist, String enrichmentType) throws Exception;
}
