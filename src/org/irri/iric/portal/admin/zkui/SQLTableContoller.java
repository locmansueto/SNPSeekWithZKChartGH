package org.irri.iric.portal.admin.zkui;

import java.util.List;

import org.irri.iric.portal.AppContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zhtml.Textarea;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;

@Controller
@Scope("session")
// @Scope("request")
public class SQLTableContoller extends SelectorComposer<Component> {

	@Wire
	private Button buttonQuery;

	@Wire
	private Textarea  textSql;
	
	@Wire
	private Grid gridModel;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	
	@Listen("onClick =#buttonQuery") 
	public void onclickQuery() {
	
		DynamicColumnModel m=new DynamicColumnModel();
		List l=m.executeQuery("select * from table");
		//gridModel.setModel(m);
		for(Object o:l) {
			AppContext.debug( o.toString());
		}
		
	}

}
