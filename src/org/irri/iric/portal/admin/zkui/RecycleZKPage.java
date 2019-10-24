package org.irri.iric.portal.admin.zkui;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.zkui.SNPQueryController;
import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;
@Scope(value = "session")
public class RecycleZKPage  extends org.zkoss.zkmax.ui.util.DesktopRecycle {
//public class RecycleZKPage  extends org.zkoss.zk.ui.http.DesktopRecycles {

	@Override
	protected boolean shallCache(Desktop desktop, String path, int cause) {
		// TODO Auto-generated method stub
		//return super.shallCache(desktop, path, cause);
		//if(path.contains("refresh")) return false;
		
		 boolean cached= path.contains("/_variety.zul") ||
				 path.contains("/test-blbox.zul") ||
				 path.contains("/_snp.zul") ||
				 path.contains("/_locus.zul") ||
				 path.contains("/_jbrowse.zul") || 
				 path.contains("/galaxy.zul") || 
				 path.contains("/_gwas.zul")  ;
		 AppContext.debug( path + " cached=" + cached);

		 return cached;
	}


	@Override
	protected boolean shallReuse(Desktop desktop, String path, int secElapsed) {
		// TODO Auto-generated method stub
		//return super.shallReuse(desktop, path, secElapsed);
		// return secElapsed >= 600; // refresh if > 10mins
		if(path.contains("refresh")) return false;

		
		 boolean cached= path.contains("/_variety.zul") ||
				 path.contains("/test-blbox.zul") ||
				 path.contains("/_snp.zul") ||
				 path.contains("/_locus.zul") ||
				 path.contains("/_jbrowse.zul") || 
				 path.contains("/galaxy.zul") || 
				 path.contains("/_gwas.zul");
		 cached = cached && secElapsed<600;
		 AppContext.debug( path + " reuse=" + cached);
		 return cached;

	} // implements  org.zkoss.zk.ui.util.DesktopRecycle {


	
	
}
