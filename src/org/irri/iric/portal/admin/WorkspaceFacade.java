package org.irri.iric.portal.admin;


import java.util.Set;



/**
 * API for user interactions
 * @author LMansueto
 *
 */
public interface WorkspaceFacade {
	
	
	public Set getLocusListNames();

	/**
	 * Variety list functions
	 */
	public Set getVarietylistNames() ;
	public Set getVarieties(String listname);
	public boolean addVarietyList(String name, Set setVarieties);
	public void deleteVarietyList(String listname);
	

	/**
	 * SNP list functions
	 * @return
	 */
	public Set getSnpPositionListNames();
	public Set getSnpPositions(String contig, String name);
	public Set getSnpPositionAlleleListNames();
	public Set getSnpPositionPvalueListNames();
	boolean addSnpPositionList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue);
	public boolean SNPListhasAllele(String listname);
	public boolean SNPListhasPvalue(String listname);
	void deleteSNPList(String chromosome, String listname);


	/**
	 * Locus list functions
	 * @return
	 */
	Set getLocuslistNames();
	Set getLoci(String listname);
	boolean addLocusList(String name, Set varietylist);
	void deleteLocusList(String listname);


	
	/**
	 * List in string format
	 * @return
	 */
	String getMyLists();

	
	/**
	 * Download/Upload list
	 */
	public void downloadLists();
	void uploadLists(String mylist) throws Exception;
	String getMyListsCookie();
	void setMyListsCookie(String mylist);
	
	// temp
	public void queryIric();

	
	
	
	
	
}
