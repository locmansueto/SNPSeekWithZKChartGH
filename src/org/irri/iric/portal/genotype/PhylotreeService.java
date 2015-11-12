package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Phylogenetic tree construction service
 * @author LMansueto
 *
 */
public interface PhylotreeService {

	public static String PHYLOTREE_METHOD_TOPN="topn";
	public static String PHYLOTREE_METHOD_MINDIST="mindist";
	
	/**
	 * Construct phylogenetic tree
	 * @param params
	 * @param requestid
	 * @return
	 */
	Object[] constructPhylotree(PhylotreeQueryParams params, String requestid);
	Object[] constructPhylotree(String scale, String chr, int start, int end, String requestid);
	

	/**
	 * Get map of variety Id to DFS (depth first search) order in the tree 
	 * @param tmpfile
	 * @return
	 */
	Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile);
	Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, String newick);

}
