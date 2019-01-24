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

import org.zkoss.zkplus.spring.SpringUtil;
import org.irri.iric.portal.blank_module.BlankModuleFacade;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.zkui.VarietyListItemRenderer;

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

		/*
		 * Messagebox.show(listboxOptions.getSelectedItem().getLabel() +
		 * " selected from option BlankModule. " +
		 * listboxSubpopulation.getSelectedItem().getLabel() +
		 * " selected from Variety.", "Message", Messagebox.OK, Messagebox.INFORMATION);
		 */
		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
		Set setVariety = variety.getGermplasmBySubpopulation(listboxSubpopulation.getSelectedItem().getLabel(), "3k");
		List listVariety = new ArrayList();
		listVariety.addAll(setVariety);
		listboxVariety.setModel(new SimpleListModel(listVariety));
	}

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		
		super.doAfterCompose(comp);

		// initialization
		BlankModuleFacade module = (BlankModuleFacade) SpringUtil.getBean("BlankModuleFacade");
		List options = module.getOptions();

		VarietyFacade variety = (VarietyFacade) SpringUtil.getBean("VarietyFacade");
		List listVariety = variety.getSubpopulations("3k");

		listboxOptions.setModel(new SimpleListModel(options));
		listboxOptions.setSelectedIndex(0);

		listboxSubpopulation.setModel(new SimpleListModel(listVariety));
		listboxSubpopulation.setSelectedIndex(0);

		listboxVariety.setItemRenderer(new VarietyListItemRenderer());

	}

}
