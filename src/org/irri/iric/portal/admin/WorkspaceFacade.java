package org.irri.iric.portal.admin;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

public interface WorkspaceFacade {
	
	/*
	public Set getVarietylistNames(HttpSession session) ;
	public Set getVarieties(HttpSession session, String listname);
	public boolean addVarietyList(HttpSession session, String name, Set setVarieties);
	public void deleteVarietyList(HttpSession session, String listname);

	public Set getSnpPositions(HttpSession nativeSession, String trim);
	public boolean addSnpPositionList(HttpSession nativeSession, String trim,
			Set setMatched);
	public Set getSnpPositionListNames(HttpSession nativeSession);
	*/

	
	public Set getLocusListNames();
	
	public void queryIric();
	
	public Set getVarietylistNames() ;
	public Set getVarieties(String listname);
	public boolean addVarietyList(String name, Set setVarieties);
	public void deleteVarietyList(String listname);
	
	//public Set getSnpPositions(Integer chromosome, String name);
	public Set getSnpPositions(String contig, String name);
	public Set getSnpPositionListNames();
	public Set getSnpPositionAlleleListNames();
	public Set getSnpPositionPvalueListNames();
	
	//boolean addSnpPositionList(Integer chromosome, String name, Set poslist);
	//boolean addSnpPositionList(String contig, String name, Set poslist);
	boolean addSnpPositionList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue);

	public void downloadLists();

	
	String getMyLists();


	void uploadLists(String mylist) throws Exception;


	String getMyListsCookie();

	void setMyListsCookie(String mylist);

	Set getLocuslistNames();

	Set getLoci(String listname);

	boolean addLocusList(String name, Set varietylist);

	void deleteLocusList(String listname);
	//void deleteSNPList(String listname);

	public boolean SNPListhasAllele(String listname);
	public boolean SNPListhasPvalue(String listname);

	void deleteSNPList(String chromosome, String listname);

	
	
	
	
	
	
}
