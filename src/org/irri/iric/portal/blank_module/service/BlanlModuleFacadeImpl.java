package org.irri.iric.portal.blank_module.service;

import java.util.ArrayList;
import java.util.List;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.blank_module.BlankModuleFacade;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BlankModuleFacade")
public class BlanlModuleFacadeImpl implements BlankModuleFacade {
	
	
	@Autowired
	private ListItemsDAO listitemsdao;

	public BlanlModuleFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
		AppContext.debug( this.getClass() + " loaded");
	}

	@Override
	public List getOptions() {
		// TODO Auto-generated method stub
		
		listitemsdao = (ListItemsDAO) AppContext.checkBean(  listitemsdao, "ListItemsDAO");
		List options=new ArrayList();
		
			options.add("Apple");
			options.add("Guava");
			options.add("Pichay");
			try {
			options.addAll( listitemsdao.getSubpopulations() ); 
			} catch (Exception ex) {
				ex.printStackTrace();
				AppContext.debug(ex.getMessage());
			};
		return options;
	}

}
