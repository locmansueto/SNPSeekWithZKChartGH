package org.irri.iric.portal.admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.irri.iric.portal.domain.Variety;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("UserSessionListsManager")
@Scope("prototype")
public class UserSessionListsManager {

	/**
	 * Stores a map of name 2 variety list
	 */
	private Map<String, Set> mapVarietyLists;
	private Map<Integer, Map<String, Set>> mapSnpposLists;

	public UserSessionListsManager() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("created UserSessionManager:" + this);
	}
	
	
	/**
	 * 
	 * @param name
	 * @param varietylist
	 * @return	false if name already exists
	 */
	public boolean addVarietyList(String name, Set varietylist) {
		if(mapVarietyLists==null) mapVarietyLists=new LinkedHashMap();
		if(mapVarietyLists.containsKey(name)) return false;
		mapVarietyLists.put(name,  varietylist);
		return true;
	}
	
	public Set getVarietylistNames() {
		if(mapVarietyLists==null) return new HashSet();
		return mapVarietyLists.keySet();
	}
	
	public Set getVarieties(String listname) {
		if(mapVarietyLists==null) return new HashSet();
		return mapVarietyLists.get(listname);
	}
	
	public void deleteVarietyList(String listname) {
		if(mapVarietyLists==null) return;
		mapVarietyLists.remove(listname);
	}
	

	public boolean addSNPList(Integer chromosome, String name, Set poslist) {
		
		if(mapSnpposLists==null) mapSnpposLists = new TreeMap();
		
		Map mapName2List = mapSnpposLists.get(chromosome);
		if(mapName2List==null) {
			mapName2List = new LinkedHashMap();
			mapSnpposLists.put( chromosome , mapName2List );
		}
		if(mapName2List.containsKey(name)) return false;
		mapName2List.put(name,  poslist);
		return true;
	}
	
	public Set getSNPlistNames() {
		
		Set returnset = new LinkedHashSet();
		if(mapSnpposLists==null) return returnset;
		Iterator itChr = mapSnpposLists.keySet().iterator();
		while(itChr.hasNext()) {
			Object chr = itChr.next();
			Iterator itNames = ((Map)mapSnpposLists.get(chr)).keySet().iterator();
			while(itNames.hasNext()) {
				returnset.add("CHR " + chr + ": " + itNames.next());
			}
		}
		return returnset;
	}
	
	public Set getSNPs(Integer chromosome, String listname) {
		Set returnset = new LinkedHashSet();
		if(mapSnpposLists==null) return returnset;
		Map name2List = mapSnpposLists.get(chromosome);
		if(name2List==null) return returnset;
		Set namelist = (Set)name2List.get(listname);
		if(namelist==null)  return returnset;
		return namelist;
		
	}
	
	public void deleteSNPList(Integer chromosome, String listname) {
		if(mapVarietyLists==null) return;
		mapVarietyLists.remove(listname);

		if(mapSnpposLists==null) return;
		Map name2List = mapSnpposLists.get(chromosome);
		if(name2List==null) return;
		name2List.remove(listname);
	}


	public StringBuffer downloadLists() {
		// TODO Auto-generated method stub
		StringBuffer buff = new StringBuffer();
		
		Iterator<String> itNames = getVarietylistNames().iterator();
		
		if(itNames.hasNext())
			buff.append("VARIETY LISTS:\tLIST NAMES:\tVARIETIES (ID, NAME:ACCESSION, IRIS ID)\n");
		
		while(itNames.hasNext()) {
			String name = itNames.next();
			buff.append("\t" + name + "\n");
			Iterator<Variety> itVar =  getVarieties(name).iterator();
			while(itVar.hasNext()) {
				Variety var = itVar.next();
				String irisid="";
				if(var.getIrisId()!=null) irisid=var.getIrisId();
				buff.append("\t\t" + var.getVarietyId() + "\t" + var.getName() + "\t" + irisid + "\n");
			}
			buff.append("\n");
		}
		buff.append("\n");

		itNames = getSNPlistNames().iterator();
		
		if(itNames.hasNext())
			buff.append("SNP LISTS:\tCHROMOSOME:\tLIST NAMES:\tPOSITIONS\n");
		
		while(itNames.hasNext()) {
			String names[] = itNames.next().split(":");
			Integer chr = Integer.valueOf(names[0].replace("CHR","").trim());
			String listname = names[1].trim();
			Iterator<BigDecimal> itPos =  getSNPs( chr, listname).iterator();
			buff.append("\t" + chr + "\t" + listname + "\n");
			while(itPos.hasNext()) {
				buff.append("\t\t\t" + itPos.next() + "\n");
			}
			buff.append("\n");
		}
		
		return buff;
		
	}

}
