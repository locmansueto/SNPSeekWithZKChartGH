/**
 * 
 */
package org.zkoss.zkspringessentials.init;

import org.irri.iric.portal.AppContext;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

/**
 * @author ashish
 *
 */
public class ExampleWebAppInit implements WebAppInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.util.WebAppInit#init(org.zkoss.zk.ui.WebApp)
	 */
	public void init(WebApp wapp) throws Exception {
		AppContext.debug("Webapp init...");

	}

}
