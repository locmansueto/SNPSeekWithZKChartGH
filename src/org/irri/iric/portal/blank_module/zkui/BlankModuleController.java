package org.irri.iric.portal.blank_module.zkui;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

@Controller("BlankModuleController")
public class BlankModuleController extends SelectorComposer<Window> {

	@Wire
	private Listbox listboxOptions;
	@Wire
	private Button buttonStart;
	
	
	public BlankModuleController() {
		super();
		
	}
	
	@Listen("onClick =#buttonStart")
	public void onclickStart() {
		
		Messagebox.show(listboxOptions.getSelectedItem().getLabel() + " selected. Copy and modify me to start a new module",
				"Message", Messagebox.OK, Messagebox.INFORMATION);
	}

	
}
