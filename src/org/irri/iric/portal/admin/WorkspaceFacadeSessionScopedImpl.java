package org.irri.iric.portal.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Filedownload;
import org.springframework.context.annotation.ScopedProxyMode;

@Service("WorkspaceFacade")
//@Scope("prototype")
@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
public class WorkspaceFacadeSessionScopedImpl  implements WorkspaceFacade {

	//@Autowired
	
	//private QueryIric queryiric;

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
		//Set restset = new HashSet();
		//restset.add("my varlist 1");
		//return restset;
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


	@Override
	public Set getSnpPositions(Integer chromosome, String name) {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPs(chromosome, name);
	}


	@Override
	public boolean addSnpPositionList(Integer chromosome, String name, Set poslist) {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.addSNPList(chromosome, name, poslist);
	}


	@Override
	public Set getSnpPositionListNames() {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		return sessionmgr.getSNPlistNames();
	}


	@Override
	public void downloadLists() {
		// TODO Auto-generated method stub
		UserSessionListsManager sessionmgr = getSessionManager();
		String filename = "mylists.txt";
		StringBuffer buff = sessionmgr.downloadLists();
		
		try {
			
			File file = new File(filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(buff.toString());
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
	
	
	
}
