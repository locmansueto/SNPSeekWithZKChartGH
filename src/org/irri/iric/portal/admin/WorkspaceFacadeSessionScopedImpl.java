package org.irri.iric.portal.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.servlet.http.HttpSession;


import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.VarietyPlus;
import org.irri.iric.portal.domain.VarietyPlusPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
//import org.zkoss.zk.ui.Sessions;
//import org.zkoss.zul.Filedownload;
import org.springframework.context.annotation.ScopedProxyMode;
import org.zkoss.zkmax.zul.Filedownload;
import org.zkoss.zul.Messagebox;

@Service("WorkspaceFacade")
@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
public class WorkspaceFacadeSessionScopedImpl  implements WorkspaceFacade {

	


	//@Autowired
	private UserSessionListsManager sessionmgr;

	public WorkspaceFacadeSessionScopedImpl() {
		super();
		
		//sessionmgr = new UserSessionManager();			
		AppContext.debug("created WorkspaceFacadeSessionScopedImpl:" + this);
	}
	
	

	@Override
	public void queryIric() {
		
		//queryiric = (QueryIric)AppContext.checkBean(queryiric, "QueryIric");
		//queryiric.createSNPFile();
	}


	
	private UserSessionListsManager getSessionManager() {
		
		//UserSessionListsManager sessionmgr = (UserSessionListsManager)session.getAttribute("manager");
		
		//sessionmgr = (UserSessionListsManager)AppContext.checkBean(sessionmgr , "UserSessionListsManager");
		if(sessionmgr==null) sessionmgr=new UserSessionListsManager();
		
		/*
		if(sessionmgr==null) {
			sessionmgr = new UserSessionListsManager();
		
			HttpSession session= getHttpSession();
			if(session==null) throw new RuntimeException("session==null");
			
			session.setAttribute("manager", sessionmgr);
			AppContext.debug("UserSessionManager created for session " + session.getId() + "  created at:" + session.getCreationTime() );
		}
		*/
		
		return sessionmgr;
	}
	
	@Override
	public Set getVarietylistNames()
	{
		UserSessionListsManager sessionmgr = getSessionManager();
		Set s=sessionmgr.getVarietylistNames();
		AppContext.debug("varlist=" + s.size());
		return s;
	}

	@Override
	public Set getVarieties( String listname) {
		UserSessionListsManager sessionmgr =  getSessionManager();
		return sessionmgr.getVarieties(listname);
	}

	@Override
	public boolean addVarietyList(String name, Set setVarieties, Set dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addVarietyList(name, setVarieties,  dataset);
	}


	@Override
	public boolean addVarietyList(String name, Set setVarieties, Set dataset, int hasPhenotype, List phenname, Map<BigDecimal, Object[]> mapVarid2Phen) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		boolean suc=true;
		for(Object ds:dataset) {
			suc=suc && sessionmgr.addVarietyList(name, setVarieties,  (String)ds, hasPhenotype,phenname, mapVarid2Phen);
		}
		return suc;
	}
	
	
	
	@Override
	public void deleteVarietyList(String listname) {
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.deleteVarietyList( listname); 
	}


	
	
	/*************************************************************/
	
	@Override
	public Set getLocuslistNames()
	{
		UserSessionListsManager sessionmgr = getSessionManager();
		Set s=sessionmgr.getLocuslistNames();
		AppContext.debug("loclist=" + s.size());
		return s;
	}

	@Override
	public Set getLoci( String listname) {
		UserSessionListsManager sessionmgr =  getSessionManager();
		return sessionmgr.getLoci(listname);
	}


	@Override
	public boolean addLocusList(String name, Set locuslist) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addLocusList(name, locuslist);
	}

	@Override
	public void deleteLocusList(String listname) {
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.deleteLocusList( listname); 
	}
	
	
	
	/*************************************************************/
	
	
	@Override
	public Set getSnpPositions(String chromosome, String name) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		Set s=sessionmgr.getSNPs(chromosome, name);
		AppContext.debug("snplist=" + s.size());
		return s;
	}


	@Override
	//public boolean addSnpPositionList(Integer chromosome, String name, Set poslist) {
	public boolean addSnpPositionList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addSNPList(contig, name, poslist, hasAllele, hasPvalue);
	}
	
	
	


	@Override
	public boolean SNPListhasAllele(String listname) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.SNPhasAllele(listname);

	}


	@Override
	public boolean SNPListhasPvalue(String listname) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.SNPhasPvalue(listname);
	}


	@Override
	public Set getSnpPositionListNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPlistNames();
	}

	@Override
	public Set getSnpPositionAlleleListNames() {
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPlistAlleleNames();
	}

	@Override
	public Set getSnpPositionPvalueListNames() {
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPlistPvalueNames();
	}

	
	
	
	@Override
	public Set getVarietyQuantPhenotypelistNames(String dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyQuantPhenotypelistNames(dataset);
	}

	@Override
	public Set getVarietyCatPhenotypelistNames(String dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyCatPhenotypelistNames(dataset);
	}


	@Override
	public Collection getVarietyQuantPhenotypelistNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyQuantPhenotypelistNames();
	}

	@Override
	public Collection getVarietyCatPhenotypelistNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietyCatPhenotypelistNames();
	}
	
	

	@Override
	public Map getVarietylistPhenotypeValues(String phenotype, String dataset) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		Map m=sessionmgr.getVarietyPhenotypeValues(phenotype.split("\\:\\:")[0], dataset, phenotype.split("\\:\\:")[1]);
		AppContext.debug("1phenvalues for " + phenotype + " , " + m.size());
		m=sessionmgr.getVarietyPhenotypeValues(phenotype.split("\\:\\:")[0], dataset, phenotype.split("\\:\\:")[1]);
		AppContext.debug("1phenvalues for " + phenotype + " , " + m.size());
		return m;
	}
	
	/*
	@Override
	public Set getLocusListNames() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getLocuslistNames();
	}
*/

	@Override
	public boolean uploadLists(String mylist) throws Exception {
		//Messagebox.show("Temporarily disabled");
		 UserSessionListsManager sessionmgr = getSessionManager();
		 return sessionmgr.uploadList(mylist);
	}
	
	
	@Override
	public String getMyLists() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.downloadLists();
	}

	@Override
	public String getMyListsCookie() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.downloadListsCookie();
	}

	@Override
	public void setMyListsCookie(String mylist) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.uploadListCookie(mylist);
	}
	
	
	@Override
	public void downloadLists() {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		String filename = AppContext.getTempDir() + "mylists-" + AppContext.createTempFilename()+ ".txt";
		
		try {
			
			File file = new File(filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write( sessionmgr.downloadLists() );
			bw.flush();
			bw.close();
			
			AppContext.debug("Mylist write complete! Saved to: "+ file.getAbsolutePath());
			
			try {
				String filetype = "text/plain";
				//Filedownload.save(  new File(filename), filetype);
				
				Filedownload.save(  new File(filename), filetype);
				} catch(Exception ex)
				{
					ex.printStackTrace();
				}

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	@Override
	public void deleteSNPList(String chromosome, String listname) {
		
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.deleteSNPList(chromosome, listname);  
	}



	
}
