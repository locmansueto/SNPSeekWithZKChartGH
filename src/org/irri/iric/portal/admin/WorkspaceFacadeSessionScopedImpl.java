package org.irri.iric.portal.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Filedownload;
import org.springframework.context.annotation.ScopedProxyMode;

@Service("WorkspaceFacade")
@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
public class WorkspaceFacadeSessionScopedImpl  implements WorkspaceFacade {

	@Autowired
	private UserSessionListsManager sessionmgr;

	public WorkspaceFacadeSessionScopedImpl() {
		super();
		// TODO Auto-generated constructor stub
		//sessionmgr = new UserSessionManager();			
		AppContext.debug("created WorkspaceFacadeSessionScopedImpl:" + this);
	}
	

	private HttpSession getHttpSession() {
		return ((HttpSession)Sessions.getCurrent().getNativeSession());
	}
	
	
/*	
	private UserSessionListsManager getSessionManager(HttpSession session) {
		return null;
	}
	
	@Override
	public Set getVarietylistNames(HttpSession session) {
		return null;
	}

	@Override
	public Set getVarieties(HttpSession session, String listname) {
		return null;
	}


	@Override
	public boolean addVarietyList(HttpSession session, String name, Set varietylist) {
		return false;
	}

	@Override
	public void deleteVarietyList(HttpSession session, String listname) {

	}



	@Override
	public Set getSnpPositions(HttpSession nativeSession, String trim) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean addSnpPositionList(HttpSession nativeSession,
			String trim, Set setMatched) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Set getSnpPositionListNames(HttpSession nativeSession) {
		// TODO Auto-generated method stub
		return null;
	}
*/	
	

	@Override
	public void queryIric() {
		// TODO Auto-generated method stub
		//queryiric = (QueryIric)AppContext.checkBean(queryiric, "QueryIric");
		//queryiric.createSNPFile();
	}


	
	private UserSessionListsManager getSessionManager() {
		
		//UserSessionListsManager sessionmgr = (UserSessionListsManager)session.getAttribute("manager");
		
		sessionmgr = (UserSessionListsManager)AppContext.checkBean(sessionmgr , "UserSessionListsManager");
		
		if(sessionmgr==null) {
			sessionmgr = new UserSessionListsManager();
		
			HttpSession session= getHttpSession();
			if(session==null) throw new RuntimeException("session==null");
			
			session.setAttribute("manager", sessionmgr);
			AppContext.debug("UserSessionManager created for session " + session.getId() + "  created at:" + session.getCreationTime() );
		}
		return sessionmgr;
	}
	
	@Override
	public Set getVarietylistNames()
	{
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getVarietylistNames();
	}

	@Override
	public Set getVarieties( String listname) {
		UserSessionListsManager sessionmgr =  getSessionManager();
		return sessionmgr.getVarieties(listname);
	}


	@Override
	public boolean addVarietyList(String name, Set varietylist) {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addVarietyList(name, varietylist);
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
		return sessionmgr.getLocuslistNames();
	}

	@Override
	public Set getLoci( String listname) {
		UserSessionListsManager sessionmgr =  getSessionManager();
		return sessionmgr.getLoci(listname);
	}


	@Override
	public boolean addLocusList(String name, Set locuslist) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPs(chromosome, name);
	}


	@Override
	//public boolean addSnpPositionList(Integer chromosome, String name, Set poslist) {
	public boolean addSnpPositionList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue) {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addSNPList(contig, name, poslist, hasAllele, hasPvalue);
	}
	
	
	


	@Override
	public boolean SNPListhasAllele(String listname) {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.SNPhasAllele(listname);

	}


	@Override
	public boolean SNPListhasPvalue(String listname) {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.SNPhasPvalue(listname);
	}


	@Override
	public Set getSnpPositionListNames() {
		// TODO Auto-generated method stub
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
	public Set getLocusListNames() {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getLocuslistNames();
	}


	@Override
	public void uploadLists(String mylist) throws Exception {
		 UserSessionListsManager sessionmgr = getSessionManager();
		 sessionmgr.uploadList(mylist);
	}
	
	
	@Override
	public String getMyLists() {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.downloadLists();
	}

	@Override
	public String getMyListsCookie() {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.downloadListsCookie();
	}

	@Override
	public void setMyListsCookie(String mylist) {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.uploadListCookie(mylist);
	}
	
	
	@Override
	public void downloadLists() {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		String filename = "mylists-" + AppContext.createTempFilename()+ ".txt";
		
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
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		sessionmgr.deleteSNPList(chromosome, listname);  
	}
	
	
	
}
