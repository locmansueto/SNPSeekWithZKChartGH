package org.irri.iric.portal.admin;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.dao.VSnpAllvarsMinDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("WorkspaceFacadeSingleton")
public class WorkspaceFacadeImpl implements WorkspaceFacade {

	
		@Autowired
		private QueryIric queryiric;
	
		//private UserSessionManager sessionmgr;

		public WorkspaceFacadeImpl() {
			super();
			// TODO Auto-generated constructor stub
			//sessionmgr = new UserSessionManager();			
			System.out.println("created WorkspaceFacadeImpl:" + this);
		}
		
		
		private UserSessionListsManager getSessionManager(HttpSession session) {
			UserSessionListsManager sessionmgr = (UserSessionListsManager)session.getAttribute("manager");
			if(sessionmgr==null) {
				sessionmgr = new UserSessionListsManager();
				session.setAttribute("manager", sessionmgr);
				System.out.println("UserSessionManager created for session " + session.getId() + "  created at:" + session.getCreationTime() );
			}
			return sessionmgr;
		}
		/*
		@Override
		public Set getVarietylistNames(HttpSession session) {
			UserSessionListsManager sessionmgr = getSessionManager(session);
			return sessionmgr.getVarietylistNames();
		}

		@Override
		public Set getVarieties(HttpSession session, String listname) {
			UserSessionListsManager sessionmgr =  getSessionManager(session);
			return sessionmgr.getVarieties(listname);
		}


		@Override
		public boolean addVarietyList(HttpSession session, String name, Set varietylist) {
			// TODO Auto-generated method stub
			UserSessionListsManager sessionmgr = getSessionManager(session);
			return sessionmgr.addVarietyList(name, varietylist);
		}
	
		@Override
		public void deleteVarietyList(HttpSession session, String listname) {
			UserSessionListsManager sessionmgr = getSessionManager(session);
			sessionmgr.deleteVarietyList( listname); 
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
			queryiric = (QueryIric)AppContext.checkBean(queryiric, "QueryIric");
			queryiric.createSNPFile();
		}

		@Override
		public Set getVarietylistNames() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Set getVarieties(String listname) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public boolean addVarietyList(String name, Set setVarieties) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public void deleteVarietyList(String listname) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public Set getSnpPositions(Integer chr, String trim) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public boolean addSnpPositionList(Integer chr, String trim, Set setMatched) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public Set getSnpPositionListNames() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void downloadLists() {
			// TODO Auto-generated method stub
			
		}
		
		
		
	
}
