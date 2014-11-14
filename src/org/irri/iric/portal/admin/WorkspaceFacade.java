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

	public void queryIric();
	
	public Set getVarietylistNames() ;
	public Set getVarieties(String listname);
	public boolean addVarietyList(String name, Set setVarieties);
	public void deleteVarietyList(String listname);
	
	public Set getSnpPositions(Integer chromosome, String name);
	public Set getSnpPositionListNames();
	boolean addSnpPositionList(Integer chromosome, String name, Set poslist);

	public void downloadLists();
	
	
	
}
