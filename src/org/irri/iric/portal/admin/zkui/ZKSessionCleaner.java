package org.irri.iric.portal.admin.zkui;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;



import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
//import org.irri.iric.portal.admin.dao.VOracleSessionsDAO;
//import org.irri.iric.portal.admin.domain.VOracleSessions;


import org.zkoss.zk.ui.util.SessionCleanup;

public class ZKSessionCleaner implements SessionCleanup {
	
	//@PersistenceContext(unitName = "IRIC_Production")
	//private EntityManager entityManager;
	
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	//@Autowired
	//private VOracleSessionsDAO oraclesessionDao;
	
	
	@Override
	public void cleanup(org.zkoss.zk.ui.Session zksession) throws Exception {
		
	}
	
	public void cleanupOld(org.zkoss.zk.ui.Session zksession) throws Exception {
		// TODO Auto-generated method stub
		
		// cancel running queries here
		
		
		// zksession.getNativeSession();
		
		//((HttpSession)Sessions.getCurrent().getNativeSession())
		
		
		/*
		oraclesessionDao = (VOracleSessionsDAO)AppContext.checkBean(oraclesessionDao, "VOracleSessionsDAO");
		Iterator<VOracleSessions> itOracleSession =  oraclesessionDao.findVOracleSessionsByAction( zksession.getNativeSession( ).toString()).iterator();
		while(itOracleSession.hasNext()) {
			VOracleSessions orasession = itOracleSession.next();
			System.out.println("Killing Oracle process: " +  orasession.getSid() + "," + orasession.getSerial_() + "," + orasession.getUsername() + "," +  orasession.getProgram() );
		}
		*/
		
		
		//((HttpSession)Sessions.getCurrent().getNativeSession()).removeAttribute("manager");
		Object manager = zksession.getAttribute("manager");
		if(manager!=null) {
			zksession.removeAttribute("manager");
			manager=null;
			System.out.println("manager removed from zksession and freed");
		}
		manager = ((HttpSession)zksession.getNativeSession()).getAttribute("manager");
		if(manager!=null) {
			zksession.removeAttribute("manager");
			manager=null;
			System.out.println("manager removed from getNativeSession and freed");
		}
		
		
		if(AppContext.isIRRILAN())
		//System.out.println("Cleaning onClose session " + zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  + "  id=" + ((HttpSession)Sessions.getCurrent().getNativeSession()).getId() );
			AppContext.debug("Cleaning onClose session " + zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  + "  id=" + ((HttpSession)zksession.getNativeSession()).getId() + " stostring=" + zksession.getNativeSession() );

		
		if(!getSession().isConnected() ) 
			System.out.println("session is not connected");
		else if (!getSession().isOpen() ) 
			System.out.println("session is not open");
		else {
			try {
				getSession().cancelQuery();
				if(AppContext.isIRRILAN())
					AppContext.debug("getSession().cancelQuery() success");
			}
			catch(Exception ex) {
				AppContext.debug("getSession().cancelQuery() failed");
				ex.printStackTrace();
			}
		}
			
		
		
		
		
		/*
		//zksession.get
		entityManager = (EntityManager)AppContext.checkBean(entityManager, "IRIC_Production");
		
		if(entityManager.isOpen()) {
			System.out.println("Closing entity manager");
			
			java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
			entityManager.close();
			if( connection!=null && !connection.isClosed()) {
				System.out.println("Closing connection");
				connection.close();
			}
					
		}
		*/
		
	
		
		
	}

	
	private org.hibernate.Session getSession() {

		if(entityManager==null) {
			EntityManagerFactory emf = (EntityManagerFactory)AppContext.checkBean(entityManager, "IRIC_Production");
			entityManager = emf.createEntityManager();
		}
		
		//entityManager = (EntityManager)AppContext.checkBean(entityManager, "IRIC_Production");
		
		
		if(entityManager!=null) {
			return entityManager.unwrap(org.hibernate.Session.class);
		} else
			return HibernateUtil.getSessionFactory().getCurrentSession();
				
	}
}
