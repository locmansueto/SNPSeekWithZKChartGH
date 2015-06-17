package org.irri.iric.portal.genomics.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.iric.portal.remoteserver.LocalClient;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.LocalAlignmentDAO;
import org.irri.iric.portal.genomics.service.LocalAlignmentQuery;
import org.irri.iric.portal.genomics.service.LocalAlignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("LocalAlignmentDAORemoteBlast")
public class LocalAlignmentBLASTremoteDAOImpl implements LocalAlignmentDAO {

	
	@Override
	public List alignWithDB(LocalAlignmentQuery query) throws Exception {
		// TODO Auto-generated method stub
		
		//LocalClient wsclient = new LocalClient("http://" + AppContext.getHostname() + "/iric-portal-dev");
		
		LocalClient wsclient=new LocalClient(AppContext.getBlastServer());
		/*
		if(AppContext.isLocalhost() || AppContext.isPollux())
			wsclient = new LocalClient(AppContext "http://pollux:8080/iric-portal-dev");
		else
			wsclient = new LocalClient("http://pollux:8080/iric-portal-dev");
		 */
		
		List listresult = wsclient.getBlast(query);
		AppContext.debug("ws result:" + listresult.size());
		return listresult;
	}

	
}
