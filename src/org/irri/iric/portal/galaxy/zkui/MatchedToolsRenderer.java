package org.irri.iric.portal.galaxy.zkui;

import java.util.Map;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.github.jmchilton.blend4j.galaxy.beans.Tool;

public class MatchedToolsRenderer implements ListitemRenderer{

	
	
	public MatchedToolsRenderer() {
		super();
	
	}

	@Override
	public void render(Listitem listitem, Object arg1, int arg2) throws Exception {
		// TODO Auto-generated method stub
		Object[] o=(Object[])arg1;
		listitem.setValue(arg1);
		Tool t=(Tool)o[0];
		addListcell(listitem,t.getName());
		addListcell(listitem,o[2].toString());
		addListcell(listitem,o[1].toString());
		addListcell(listitem,t.getDescription());
		addListcell(listitem,t.getUrl());
	}
	
	private static String STYLE_INTERESTING = "font-weight:bold;color:red";
	private static String STYLE_BORING = "";

	private void addListcell(Listitem listitem, String value) {
		addListcell(listitem, value, STYLE_BORING);
	}

	private void addListcell(Listitem listitem, String value, String style) {
		Listcell lc = new Listcell();
		Label lb = new Label(value);
		if (!style.isEmpty())
			lb.setStyle(style);
		lb.setParent(lc);
		lc.setParent(listitem);
	}

}
