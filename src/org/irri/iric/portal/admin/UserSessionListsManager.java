package org.irri.iric.portal.admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genomics.LocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("UserSessionListsManager")
@Scope("prototype")
public class UserSessionListsManager {

	/**
	 * Stores a map of name 2 variety list
	 */
	private Map<String, Set> mapVarietyLists;
	private Map<String, Map<String, Set>> mapSnpposLists;
	private Map<String, Set> mapLocusLists;
	
	private Set setPoslistWithAllele=new LinkedHashSet();
	private Set setPoslistWithPvalue=new LinkedHashSet();
	
	@Autowired
	//VIricstockBasicprop2DAO varietyprop2DAO;
	@Qualifier("VarietyDAO")
	private VarietyDAO varietyprop2DAO;
	
	@Autowired
	//VLocusNotesDAO locusnotesDAO;
	private LocusService locusService;
	

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
	
	
	/**
	 * 
	 * @param name
	 * @param varietylist
	 * @return
	 */
	
	public boolean addLocusList(String name, Set locuslist) {
		if(mapLocusLists==null) mapLocusLists=new LinkedHashMap();
		if(mapLocusLists.containsKey(name)) return false;
		
		mapLocusLists.put(name,  locuslist);
		AppContext.debug(name + " added with " + locuslist.size() + " loci");
		return true;
	}
	
	public Set getLocuslistNames() {
		if(mapLocusLists==null) return new HashSet();
		return mapLocusLists.keySet();
	}
	
	public Set getLoci(String listname) {
		if(mapLocusLists==null) return new HashSet();
		
		Set loclist = mapLocusLists.get(listname);
		//valist AppContext.debug(listname + " retrieved with " + valist.size() + " varieties");
		return loclist;
	}
	
	public void deleteLocusList(String listname) {
		if(mapLocusLists==null) return;
		mapLocusLists.remove(listname);
	}
	
	
	/**
	 * 
	 * @param chromosome
	 * @param name
	 * @param poslist
	 * @param hasPvalue 
	 * @param hasAllele 
	 * @return
	 */

	//public boolean addSNPList(String contig, String name, Set poslist) {
	//	return addSNPList(contig, name, poslist, false, false);
	//}
	
	public boolean addSNPList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue) {
		
		if(mapSnpposLists==null) mapSnpposLists = new TreeMap();
		
		Map mapName2List = mapSnpposLists.get(contig);
		if(mapName2List==null) {
			mapName2List = new LinkedHashMap();
			mapSnpposLists.put( contig , mapName2List );
		}
		if(mapName2List.containsKey(name)) return false;
		mapName2List.put(name,  poslist);
		AppContext.debug(name + " added to chromosome " + contig + " with " + poslist.size() + " snp positions");
		if(hasAllele) setPoslistWithAllele.add(name);
		if(hasPvalue) setPoslistWithPvalue.add(name);
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
				//returnset.add("CHR " + chr + ": " + itNames.next());
				returnset.add(chr + ": " + itNames.next());
			}
		}
		return returnset;
	}
	
	public Set getSNPlistAlleleNames() {
		return this.setPoslistWithAllele;
	}	

	public Set getSNPlistPvalueNames() {
		return this.setPoslistWithPvalue;
	}	

	
	public Set getSNPs(String chromosome, String listname) {
		Set returnset = new LinkedHashSet();
		if(mapSnpposLists==null) return returnset;
		Map name2List = mapSnpposLists.get(chromosome);
		if(name2List==null) return returnset;
		Set namelist = (Set)name2List.get(listname);
		if(namelist==null)  return returnset;
		return namelist;
	}
	
	public boolean SNPhasAllele(String name) {
		return this.setPoslistWithAllele.contains(name);
	}
	public boolean SNPhasPvalue(String name) {
		return this.setPoslistWithPvalue.contains(name);
	}
	
	public void deleteSNPList(String chromosome, String listname) {

		this.setPoslistWithAllele.remove(listname);
		this.setPoslistWithPvalue.remove(listname);
		
		if(mapSnpposLists==null) return;
		Map name2List = mapSnpposLists.get(chromosome);
		if(name2List==null) return;
		name2List.remove(listname);
	}


	/**
	 * Recreate list from list file 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean uploadList(String list) throws Exception {
		
		String lines[] = list.split("\n");
		int state=0;
		String listname = "";
		String chr = "";
		boolean hasAllele = false;
		boolean hasPvalue = false;
		
		Set setListMembers = null;
		for(int i=0; i<lines.length; i++ ) {
			String l=lines[i];
			if(l.isEmpty()) continue;
			switch (state) {
				case 0:
					if(l.startsWith("VARIETY LISTS:")) state=1;
					else if(l.startsWith("SNP LISTS:")) state=2;
					else if(l.startsWith("LOCUS LISTS:")) state=3;
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
					} else if(l.startsWith("LOCUS LISTS:")) {
						if(setListMembers!=null && !listname.isEmpty())
							addVarietyList(listname, setListMembers);
						listname = "";
						setListMembers = null;
						state=3;
					}
					break;
				case 2:
					if(l.startsWith("#SNPLIST")) {
						if(setListMembers!=null && !listname.isEmpty())
							addSNPList(chr ,listname, setListMembers, hasAllele, hasPvalue);
						listname = l.split("\t")[2];
						chr = l.split("\t")[1];
						hasAllele = Boolean.valueOf( l.split("\t")[3] );
						hasPvalue = Boolean.valueOf( l.split("\t")[4] );
						setListMembers = new LinkedHashSet();
						
						//AppContext.debug("parsing snp list " + listname);
						
					} else if(l.startsWith("LOCUS LISTS:")) {
						if(setListMembers!=null && !listname.isEmpty())
							addSNPList(chr ,listname, setListMembers, hasAllele, hasPvalue);
						listname = "";
						setListMembers = null;
						state=3;
					}
					else if(l.startsWith("\t\t")) {
						//buff.append("\t\t\t" + itPos.next() + "\n");
						
						String pos = l.split("\t")[3];
						try{
							setListMembers.add( BigDecimal.valueOf(Long.valueOf(pos)) );
						} catch (Exception ex) {
							//AppContext.debug("parsing " + pos);
							if(pos.startsWith("(")) {
								if(hasAllele || hasPvalue) {
									setListMembers.add(new MultiReferencePositionImplAllelePvalue(pos));
								}
								else 
									setListMembers.add(new MultiReferencePositionImpl(pos));
							}
						}
						
						//setListMembers.add( l.split("\t")[3] ); 
					}
					break;
				case 3:
					if(l.startsWith("#LOCUSLIST")) {
						if(setListMembers!=null && !listname.isEmpty())
							addLocusList(listname, setListMembers);
						listname = l.split("\t")[1];
						setListMembers = new LinkedHashSet();
					}
					else if(l.startsWith("\t\t")) {
						//buff.append("\t\t" + loc.getUniquename() + "\t" + loc.getContig() + "\t" + loc.getFmin() + "\t" + loc.getFmax() + "\t" + loc.getStrand() + "\n");
						setListMembers.add( locusService.getLocusByName( l.split("\t")[2]));
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
						addSNPList(chr ,listname, setListMembers, hasAllele, hasPvalue);
				break;
			case 3:
				if(setListMembers!=null && !listname.isEmpty())
					addLocusList(listname, setListMembers);
			break;
		}
		
		return true;
	}
	
	
public boolean uploadListCookie(String list) {
		/*
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
				addSNPList(cols[1] ,cols[2].trim(), setListMembers, hasAllele, hasPvalue);
			}
		}
		*/
		
		return true;
	}
	

/**
 * Convert list to ASCII text
 * @return
 */
	public String downloadLists() {
		// TODO Auto-generated method stub
		StringBuffer buff = new StringBuffer();
		Map<BigDecimal, Variety> mapVarid2Var2 = null;
		
		
		Iterator<String> itNames = getVarietylistNames().iterator();
		
		if(itNames.hasNext()) {
			varietyprop2DAO = (VarietyDAO)AppContext.checkBean( varietyprop2DAO, "VarietyDAO");
			Iterator<Variety> itVars = varietyprop2DAO.findAllVariety().iterator();
			mapVarid2Var2 = new HashMap();
			while(itVars.hasNext()) {
				Variety var = itVars.next();
				mapVarid2Var2.put(var.getVarietyId(), var);
			}
			
			buff.append("VARIETY LISTS:\tLIST NAMES:\tVARIETIES (ID, NAME:ACCESSION, IRIS ID, BOX CODE, SUBPOPULATION, COUNTRY)\n");
		}
		
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
				String country="";
				String subpop="";
				if(var.getIrisId()!=null) irisid=var.getIrisId();
				if(var.getCountry() !=null) country=var.getCountry();
				if(var.getSubpopulation()!=null) subpop=var.getSubpopulation();
				
				
				Variety var2 = mapVarid2Var2.get(var.getVarietyId()); 
				if( var2!=null && var2.getBoxCode()!=null) boxcode = var2.getBoxCode();
				buff.append("\t\t" + var.getVarietyId() +  "\t" + var.getName() + "\t" + irisid + "\t" + boxcode + "\t" +subpop + "\t" + country + "\n");
			}
			buff.append("\n");
		}
		buff.append("\n");

		itNames = getSNPlistNames().iterator();
		
		if(itNames.hasNext())
			buff.append("SNP LISTS:\tCHROMOSOME:\tLIST NAMES:\tPOSITIONS\n");
		
		while(itNames.hasNext()) {
			String names[] = itNames.next().split(":");
			//Integer chr = Integer.valueOf(names[0].replace("CHR","").trim());
			String chr = names[0].trim();
			String listname = names[1].trim();
			Iterator<BigDecimal> itPos =  getSNPs( chr, listname).iterator();
			//buff.append("\t" + chr + "\t" + listname + "\n");
			//buff.append("#SNPLIST\t" + chr + "\t" + listname + "\n");
			buff.append("#SNPLIST\t" + chr + "\t" + listname + "\t" + SNPhasAllele(listname) + "\t" + SNPhasPvalue(listname) + "\n");
			while(itPos.hasNext()) {
				buff.append("\t\t\t" + itPos.next() + "\n");
			}
			buff.append("\n");
		}
		
		
		itNames = getLocuslistNames().iterator();
		if(itNames.hasNext())
			buff.append("LOCUS LISTS:\tLIST NAMES:\tLOCI (ACCESSION, CONTIG, START, END, STRAND)\n");
		
		while(itNames.hasNext()) {
			String name = itNames.next();
			//buff.append("\t" + name + "\n");
			buff.append("#LOCUSLIST\t" + name + "\n");
			Iterator<Locus> itLoc =  getLoci(name).iterator();
			while(itLoc.hasNext()) {
				Locus loc = itLoc.next();
				if(loc==null) continue;
				buff.append("\t\t" + loc.getUniquename() + "\t" + loc.getContig() + "\t" + loc.getFmin() + "\t" + loc.getFmax() + "\t" + loc.getStrand() + "\n"); 
			}
			buff.append("\n");
		}
		
		
		
		return buff.toString();
		
	}


/**
 * Convert list to ASCII text (compact version)
 * @return
 */
	public String downloadListsCookie() {
		// TODO Auto-generated method stub
		StringBuffer buff = new StringBuffer();
		
		varietyprop2DAO = (VarietyDAO)AppContext.checkBean( varietyprop2DAO, "VarietyDAO");
		Iterator<Variety> itVars = varietyprop2DAO.findAllVariety().iterator();
		Map<BigDecimal, Variety> mapVarid2Var2 = new HashMap();
		while(itVars.hasNext()) {
			Variety var = itVars.next();
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
			//Integer chr = Integer.valueOf(names[0].replace("CHR","").trim());
			String chr = names[0].trim();
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
