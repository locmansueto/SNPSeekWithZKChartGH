package org.irri.iric.portal.blank_module.service;

import java.util.ArrayList;
import java.util.List;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.blank_module.BlankModuleFacade;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BlankModuleFacade")
public class BlankModuleFacadeImpl implements BlankModuleFacade {
	
	public BlankModuleFacadeImpl() {
		super();
		AppContext.debug( this.getClass() + " loaded");
	}

	@Override
	public List getOptions() {
		
		List options=new ArrayList();
			options.add("option 1");
			options.add("option 2");
			options.add("option 3");
		return options;
	}

}
