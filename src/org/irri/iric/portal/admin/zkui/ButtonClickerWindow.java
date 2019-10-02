package org.irri.iric.portal.admin.zkui;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

public class ButtonClickerWindow extends Window {

	List lbuttons=new ArrayList();

	
	
	public ButtonClickerWindow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ButtonClickerWindow(String title, String border, boolean closable) {
		super(title, border, closable);
		// TODO Auto-generated constructor stub
	}

	public void setButtons(List l) {
		lbuttons=l;
	}
	
	public void click() {
		for(Object b:lbuttons) {
			Events.postEvent((Component)b,  new Event(Events.ON_CLICK, (Component)b));
		}
	}
}
