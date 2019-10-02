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
		
		 boolean cached= path.contains("/_variety.zul") ||
				 path.contains("/test-blbox.zul") ||
				 path.contains("/_snp.zul") ||
				 path.contains("/_locus.zul") ||
				 path.contains("/_jbrowse.zul") || 
				 path.contains("/_gwas.zul");
		 AppContext.debug( path + " cached=" + cached);

		 return cached;
	}


	@Override
	protected boolean shallExpunge(Desktop desktop, String path, int secElapsed) {
		// TODO Auto-generated method stub
		return super.shallExpunge(desktop, path, secElapsed);
		
		//return path.contains("/test-blbox.zul");
	}


	@Override
	protected boolean shallReuse(Desktop desktop, String path, int secElapsed) {
		// TODO Auto-generated method stub
		//return super.shallReuse(desktop, path, secElapsed);
		// return secElapsed >= 600; // refresh if > 10mins
		 boolean cached= path.contains("/_variety.zul") ||
				 path.contains("/test-blbox.zul") ||
				 path.contains("/_snp.zul") ||
				 path.contains("/_locus.zul") ||
				 path.contains("/_jbrowse.zul") || 
				 path.contains("/_gwas.zul");
		 cached = cached && secElapsed<600;
		 AppContext.debug( path + " reuse=" + cached);
		 return cached;

	} // implements  org.zkoss.zk.ui.util.DesktopRecycle {


	
	@Override
	public void afterService(Desktop desktop) {
		// TODO Auto-generated method stub
		super.afterService(desktop);

		/*
		if(desktop.getSession()!=null) {
			try {
				ButtonClickerWindow win=(ButtonClickerWindow)desktop.getSession().getAttribute("winSNP");
				if(win!=null) win.click();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			try {
					Button win=(Button)desktop.getSession().getAttribute("buttonRefreshSNP");
					if(win!=null) Events.postEvent(win,  new Event(Events.ON_CLICK, win));
					else AppContext.debug( "buttonRefreshSNP==null");
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			 AppContext.debug( "desktop.getSession()==null");
		}
		*/

		if( desktop.getRequestPath().contains("/_1snp.zul")) {
//			if(desktop.getRequestPath().contains("/_snp.zul")) {
			AppContext.debug("invalidate desktop " + desktop.getRequestPath());
			
			for(Page page: desktop.getPages()) {
				if(!page.isAlive()) continue;
				AppContext.debug("afterservice pages " + page.getId() + "    " + page.getTitle());
				AppContext.debug("attributes:" + page.getAttributes().keySet());
				//page.getListenerIterator();
				AppContext.debug("fellows:" );
				for(Component  f:page.getFellows()) {
					//if(f.getId().getClass().getSimpleName().equals("Button") || f.getId().getClass().getSimpleName().equals("Window") )
					AppContext.debug(f.getId() + "  "   + f.getClass());
					for(Component  f2:f.getChildren()) {
						//if(f2.getId().getClass().getSimpleName().equals("Button") || f2.getId().getClass().getSimpleName().equals("Window") )
							AppContext.debug("	   " + f2.getId() + "  "   + f2.getClass());
						for(Component  f3:f2.getChildren()) {
							//if(f3.getId().getClass().getSimpleName().equals("Button") || f3.getId().getClass().getSimpleName().equals("Window") )
							AppContext.debug("		 "  + f3.getId() + "  "   + f3.getClass());
							
							/*if(f3.getId().equals("win")) {
								//Events.postEvent(f3,  ne Events.ON_CHANGE);
								//Events.sendEvent(f3,new Event(Events.ON_CHANGE));
								//continue;
								AppContext.debug("refreshing...");
								((ButtonClickerWindow)f3).click();
								//((SNPQueryController)f3).refresh();
							}*/
							
							if(f3.getId().equals("win")) {
								try {
									SNPQueryController comp2= (SNPQueryController) ((Window)f3).getAttribute("win$composer");
									comp2.refresh();
								} catch(Exception ex) {
									ex.printStackTrace();
								}
								break;
							}
							for(Component  f4:f3.getChildren()) {
								//if(f4.getId().getClass().getSimpleName().equals("Button"))
								AppContext.debug("			" + f4.getId() + "  "   + f4.getClass());
								
								if(f4.getId().equals("bc")) {
									try {
									((ButtonClicker)f4).click();
									} catch(Exception ex) {
										ex.printStackTrace();
									}
									//b.
									break;
								}
								
								for(Component  f5:f4.getChildren()) {
									//if(f5.getId().getClass().getSimpleName().equals("Button"))
									AppContext.debug("				" + f5.getId() + "  "   + f5.getClass());
									for(Component  f6:f5.getChildren()) {
										//if(f6.getId().getClass().getSimpleName().equals("Button"))
										AppContext.debug("					" + f6.getId() + "  "   + f6.getClass());
										for(Component  f7:f6.getChildren()) {
											//if(f6.getId().getClass().getSimpleName().equals("Button"))
											AppContext.debug("						" + f7.getId() + "  "   + f7.getClass());
										}
										}
								}
								
							}
							
						}
					}
				}
			}
			
			//desktop.invalidate();
			
			//AppContext.debug("refreshed desktop " + desktop.getRequestPath());
			//Executions.sendRedirect("");
		}
		

	}

	@Override
	public Desktop beforeService(Execution arg0, String arg1) {
		// TODO Auto-generated method stub
		return super.beforeService(arg0, arg1);
	}


	@Override
	public void afterRemove(Session sess, Desktop desktop) {
		// TODO Auto-generated method stub
		super.afterRemove(sess, desktop);
	}


	@Override
	public boolean beforeRemove(Execution arg0, Desktop arg1, int arg2) {
		// TODO Auto-generated method stub
		return super.beforeRemove(arg0, arg1, arg2);
	}

	public HttpSession getSession() {
		return (HttpSession) Executions.getCurrent().getSession().getNativeSession();
	}

	
}
