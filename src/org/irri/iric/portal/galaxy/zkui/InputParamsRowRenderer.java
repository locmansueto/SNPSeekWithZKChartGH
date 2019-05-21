package org.irri.iric.portal.galaxy.zkui;

import java.lang.reflect.InvocationTargetException;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Configuration;
import org.zkoss.zul.Button;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

public class InputParamsRowRenderer implements RowRenderer{

	

	
	@Override
	public void render(Row row, Object obj, int i) throws Exception {
		// TODO Auto-generated method stub
		String[] params=null;
		try {
			params=(String[])obj;
			
			//input id="inp0" type="text" zk:onChange="add()"
					
			//AppContext.debug("render..." + params[0] + "   " + params[1]);
			Html h=new Html();
			
			Input inp=new Input();
			inp.setAttribute("type","file");
			//inp.adda
			Textbox tb=new Textbox(params[1]); 
			//tb.setWidth("95%");
			tb.setInplace(true);
			tb.setReadonly(false);
			tb.setStyle("border:normal");

			Button b=new Button("...");
			b.setWidth("15px");
			b.addEventListener("onClick", new EventListener() {
	           		 public void onEvent(Event event) throws Exception {
	           			 /*
	           		 Desktop desktop = Executions.getCurrent().getDesktop(); 
					//Desktop desktop = self.getDesktop(); 
					Configuration conf = desktop.getWebApp().getConfiguration(); 
					conf.setMaxUploadSize(1024); 
					conf.setUploadCharset("gbk"); 
					//desktop.
					 * */
					 
	           			//Fileupload.get(true);
	           			
						Fileupload.get(new EventListener() {
	           				@Override
	           				public void onEvent(Event event) throws Exception {
	           					try {
	           						//org.zkoss.util.media.Media media = ((UploadEvent) event).getMedia();
	           						org.zkoss.util.media.Media media = ((UploadEvent) event).getMedia();
	           						media.getStringData();
	           						//tb.setValue(media.getName());
	           					} catch (Exception ex) {
	           						AppContext.debug(ex.getMessage());
	           						ex.getStackTrace();
	           						AppContext.debug("Select file failed");
	           					}
	           				}
	           			});
	           			
	           			
         	   }});

			Hbox hbox=new Hbox();
				tb.setParent(hbox);
				b.setParent(hbox);
           		 			
			String[] labels=params[0].split("\\:");			
			new Label(labels[0]).setParent(row);
			if(!labels[1].equals("data"))
				tb.setParent(row);
			else hbox.setParent(row); //  inp.setParent(row);			
			if(labels.length>2)
				new Label(labels[2]).setParent(row);
			else new Label("").setParent(row);
			
			if(tb.getValue().trim().isEmpty()) {
				if(labels.length>3)
					tb.setValue( labels[3] );			
				else tb.setValue("");
			} else {
				tb.setReadonly(true);
			}
			String typestepno=labels[1];
			if(labels.length>4) typestepno+=":"+labels[4];
			else typestepno+=":";
			
			// set stepno=annot
			//String  typestepno=labels[1]+":"+labels[2];
			
			new Textbox(typestepno).setParent(row);
		} catch(Exception ex) {
			ex.printStackTrace();
			AppContext.debug(params[0]);
		}

	}

}
