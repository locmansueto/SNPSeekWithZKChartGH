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

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.dao.VIricstockBasicprop2DAO;
import org.irri.iric.portal.chado.domain.VIricstockBasicprop2;
import org.irri.iric.portal.domain.Variety;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	VIricstockBasicprop2DAO varietyprop2DAO;
	

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
		AppContext.debug(name + " added with " + varietylist.size() + " varieties");
		return true;
	}
	
	public Set getVarietylistNames() {
		if(mapVarietyLists==null) return new HashSet();
		return mapVarietyLists.keySet();
	}
	
	public Set getVarieties(String listname) {
		if(mapVarietyLists==null) return new HashSet();
		
		Set valist = mapVarietyLists.get(listname);
		//valist AppContext.debug(listname + " retrieved with " + valist.size() + " varieties");
		return valist;
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
		AppContext.debug(name + " added to chromosome " + chromosome + " with " + poslist.size() + " snp positions");
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


	
	public boolean uploadList(String list) {
		
		String lines[] = list.split("\n");
		int state=0;
		String listname = "";
		String chr = "";
		Set setListMembers = null;
		for(int i=0; i<lines.length; i++ ) {
			String l=lines[i];
			switch (state) {
				case 0:
					if(l.startsWith("VARIETY LISTS:")) state=1;
					else if(l.startsWith("SNP LISTS:")) state=2;
					break;
				case 1:
					if(l.startsWith("#VARLIST")) {
						if(setListMembers!=null && !listname.isEmpty())
							addVarietyList(listname, setListMembers);
						listname = l.split("\t")[1];
						setListMembers = new LinkedHashSet();
					}
					else if(l.startsWith("\t\t")) {
						//buff.append("\t\t" + var.getVarietyId() +  "\t" + var.getName() + "\t" + irisid + "\t" + boxcode + "\n");
						setListMembers.add( varietyprop2DAO.findVarietyById( BigDecimal.valueOf(Long.valueOf( l.split("\t")[2]))) );
					} else if(l.startsWith("SNP LISTS:")) {
						if(setListMembers!=null && !listname.isEmpty())
							addVarietyList(listname, setListMembers);
						listname = "";
						setListMembers = null;
						state=2;
					}
					break;
				case 2:
					if(l.startsWith("#SNPLIST")) {
						if(setListMembers!=null && !listname.isEmpty())
							addSNPList(Integer.valueOf(chr) ,listname, setListMembers);
						listname = l.split("\t")[2];
						chr = l.split("\t")[1];
						setListMembers = new LinkedHashSet();
					}
					else if(l.startsWith("\t\t")) {
						//buff.append("\t\t\t" + itPos.next() + "\n");
						setListMembers.add( l.split("\t")[3] ); 
					}
			}
		}
		
		switch (state) {
			case 0:
				break;
			case 1:
					if(setListMembers!=null && !listname.isEmpty())
						addVarietyList(listname, setListMembers);
				break;
			case 2:
				if(setListMembers!=null && !listname.isEmpty())
						addSNPList(Integer.valueOf(chr) ,listname, setListMembers);
				break;
		}
		
		return true;
	}
	
	
public boolean uploadListCookie(String list) {
		
		String lines[] = list.split("\n");
		for(int i=0; i<lines.length; i++ ) {
			String l=lines[i];
			
			if(l.startsWith("#VARLIST\t")) {
				String[] cols = l.split("\t");
				Set setListMembers = new LinkedHashSet();
				String varids[] = cols[2].split(",");
				for(int iVar=0; iVar<varids.length; iVar++) {
					setListMembers.add( varietyprop2DAO.findVarietyById( BigDecimal.valueOf(Long.valueOf( varids[iVar] ))) );
				}
				addVarietyList(cols[1].trim(), setListMembers);
			} else if(l.startsWith("#SNPLIST")) {

				String[] cols = l.split("\t");
				Set setListMembers = new LinkedHashSet();
				String pos[] = cols[3].split(",");
				for(int ipos=0; ipos<pos.length; ipos++) {
					setListMembers.add( pos[ipos] );
				}
				addSNPList(Integer.valueOf(cols[1]) ,cols[2].trim(), setListMembers);
			}
		}
		
		return true;
	}
	

	public String downloadLists() {
		// TODO Auto-generated method stub
		StringBuffer buff = new StringBuffer();
		
		varietyprop2DAO = (VIricstockBasicprop2DAO)AppContext.checkBean( varietyprop2DAO, "VIricstockBasicprop2DAO");
		Iterator<VIricstockBasicprop2> itVars = varietyprop2DAO.findAllVariety().iterator();
		Map<BigDecimal, VIricstockBasicprop2> mapVarid2Var2 = new HashMap();
		while(itVars.hasNext()) {
			VIricstockBasicprop2 var = itVars.next();
			mapVarid2Var2.put(var.getVarietyId(), var);
		}
		
		Iterator<String> itNames = getVarietylistNames().iterator();
		
		if(itNames.hasNext())
			buff.append("VARIETY LISTS:\tLIST NAMES:\tVARIETIES (ID, NAME:ACCESSION, IRIS ID, BOX CODE)\n");
		
		while(itNames.hasNext()) {
			String name = itNames.next();
			//buff.append("\t" + name + "\n");
			buff.append("#VARLIST\t" + name + "\n");
			Iterator<Variety> itVar =  getVarieties(name).iterator();
			while(itVar.hasNext()) {
				Variety var = itVar.next();
				if(var==null) continue;
				String irisid="";
				String boxcode="";
				if(var.getIrisId()!=null) irisid=var.getIrisId();
				
				VIricstockBasicprop2 var2 = mapVarid2Var2.get(var.getVarietyId()); 
				if( var2!=null && var2.getBoxCode()!=null) boxcode = var2.getBoxCode();
				buff.append("\t\t" + var.getVarietyId() +  "\t" + var.getName() + "\t" + irisid + "\t" + boxcode + "\n");
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
			//buff.append("\t" + chr + "\t" + listname + "\n");
			buff.append("#SNPLIST\t" + chr + "\t" + listname + "\n");
			while(itPos.hasNext()) {
				buff.append("\t\t\t" + itPos.next() + "\n");
			}
			buff.append("\n");
		}
		
		return buff.toString();
		
	}

	public String downloadListsCookie() {
		// TODO Auto-generated method stub
		StringBuffer buff = new StringBuffer();
		
		varietyprop2DAO = (VIricstockBasicprop2DAO)AppContext.checkBean( varietyprop2DAO, "VIricstockBasicprop2DAO");
		Iterator<VIricstockBasicprop2> itVars = varietyprop2DAO.findAllVariety().iterator();
		Map<BigDecimal, VIricstockBasicprop2> mapVarid2Var2 = new HashMap();
		while(itVars.hasNext()) {
			VIricstockBasicprop2 var = itVars.next();
			mapVarid2Var2.put(var.getVarietyId(), var);
		}
		
		Iterator<String> itNames = getVarietylistNames().iterator();
		
		while(itNames.hasNext()) {
			String name = itNames.next();
			//buff.append("\t" + name + "\n");
			buff.append("#VARLIST\t" + name + "\t");
			Iterator<Variety> itVar =  getVarieties(name).iterator();
			while(itVar.hasNext()) {
				Variety var = itVar.next();
				if(var==null) continue;
				buff.append( var.getVarietyId());
				if(itVar.hasNext()) buff.append(",");
			}
			buff.append("\n");
		}

		itNames = getSNPlistNames().iterator();
		
		while(itNames.hasNext()) {
			String names[] = itNames.next().split(":");
			Integer chr = Integer.valueOf(names[0].replace("CHR","").trim());
			String listname = names[1].trim();
			Iterator<BigDecimal> itPos =  getSNPs( chr, listname).iterator();
			//buff.append("\t" + chr + "\t" + listname + "\n");
			buff.append("#SNPLIST\t" + chr + "\t" + listname + "\t");
			while(itPos.hasNext()) {
				buff.append(itPos.next());
				if(itPos.hasNext()) buff.append(",");
			}
			buff.append("\n");
		}
		
		return buff.toString();
		
	}

}
