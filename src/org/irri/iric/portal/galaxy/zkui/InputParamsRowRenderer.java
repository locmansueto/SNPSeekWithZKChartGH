package org.irri.iric.portal.galaxy.zkui;

import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

public class InputParamsRowRenderer implements RowRenderer{

	@Override
	public void render(Row row, Object obj, int i) throws Exception {
		// TODO Auto-generated method stub
		
		String[] params=(String[])obj;
		Textbox tb=new Textbox(params[1]); 
		tb.setWidth("100%");
		tb.setInplace(true);
		tb.setReadonly(false);
		new Label(params[0]).setParent(row);
		tb.setParent(row);

	}

}
