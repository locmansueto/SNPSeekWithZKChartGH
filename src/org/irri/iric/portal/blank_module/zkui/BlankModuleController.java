package org.irri.iric.portal.blank_module.zkui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

@Controller("BlankModuleController")
public class BlankModuleController extends SelectorComposer<Window> {

	@Autowired
	VarietyFacade variety;
	@Wire
	private Listbox listboxOptions;
	@Wire
	private Listbox listboxSubpopulation;
	@Wire
	private Button buttonSearch;
	@Wire
	private Listbox listboxVariety;
	
	public BlankModuleController() {
		super();
	}
	
	/**
	 * Example to get varieties in selected subpopulation on button click
	 */
	@Listen("onClick =#buttonSearch")
	public void onclickStart() {

		Messagebox.show(listboxOptions.getSelectedItem().getLabel() + " selected from option BlankModule. " + 
				listboxSubpopulation.getSelectedItem().getLabel() + " selected from Variety.",
				"Message", Messagebox.OK, Messagebox.INFORMATION);
		
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		Set setVariety=variety.getGermplasmBySubpopulation( listboxSubpopulation.getSelectedItem().getLabel());
		List listVariety=new ArrayList();
		listVariety.addAll(setVariety);
		listboxVariety.setModel( new SimpleListModel(listVariety));
	}
}
