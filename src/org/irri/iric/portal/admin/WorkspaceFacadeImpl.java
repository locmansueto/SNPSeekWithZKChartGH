package org.irri.iric.portal.admin;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.dao.VSnpAllvarsMinDAO;
import org.irri.iric.portal.hdf5.dao.SNPUni3kVarietiesAllele1DAO;
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
		
		@Override
		public void queryIric() {
			
			System.out.println("in queryIric..." );
			SNPUni3kVarietiesAllele1DAO snpuniDAO = new SNPUni3kVarietiesAllele1DAO();
		  	Map mapVar2Str =  snpuniDAO.readSNPString(1,  1000, 1100);
		  	Iterator itVar = mapVar2Str.keySet().iterator();
		  	while(itVar.hasNext()) {
		  		Object var = itVar.next();
		  		System.out.println( var + " : " + mapVar2Str.get(var));
		  	}
		  	
			// System.out.println("queryIric()"); 
			 
			// TODO Auto-generated method stub
			//queryiric = (QueryIric)AppContext.checkBean(queryiric, "QueryIric");
			//queryiric.createSNPFile();
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
