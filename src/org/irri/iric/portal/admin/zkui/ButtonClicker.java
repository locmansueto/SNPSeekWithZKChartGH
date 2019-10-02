package org.irri.iric.portal.admin.zkui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.irri.iric.portal.AppContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;

public class ButtonClicker extends Button {

	List lbuttons=new ArrayList();
	Method myMethod;
	Object obj;
	public ButtonClicker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ButtonClicker(String label, String image) {
		super(label, image);
		// TODO Auto-generated constructor stub
	}

	public ButtonClicker(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public void setButtons(List lbuttons) {
		this.lbuttons = lbuttons;
	}

	public void setMethod(Object o, Method m) {
		this.myMethod=m;
		obj=o;
	}

	public void click() {
		if(myMethod!=null) {
			try {
				AppContext.debug("invoking " + myMethod.getName() + " on " + obj.getClass() );
				myMethod.invoke(obj);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		/*
		for(Object b:lbuttons) {
			Events.postEvent((Component)b,  new Event(Events.ON_CLICK, (Component)b));
		}
		*/
	
	}
	
	
	
	
}
