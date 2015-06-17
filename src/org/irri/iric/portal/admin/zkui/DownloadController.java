package org.irri.iric.portal.admin.zkui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.irri.iric.portal.variety.service.VarietyPropertiesService;
import org.irri.iric.portal.variety.zkui.VarietyListItemRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleListModel;



@Controller()
public class DownloadController   extends SelectorComposer<Component>  {

	@Wire
	private Grid gridRawVarietyFiles;


	@Autowired
	private VarietyFacade varietyfacade;
	
	@Autowired
	@Qualifier("WorkspaceFacade")
	private WorkspaceFacade workspacefacade;

	
	@Wire
	private Checkbox checkboxFastq;
	@Wire
	private Checkbox checkboxBAM;
	@Wire
	private Checkbox checkboxVCF;
	@Wire
	private Listbox listboxVarlist;
	@Wire
	private Button buttonUnmarkAll;
	@Wire  
	private Button buttonDownloadMarked;
	@Wire
	private Listbox listboxSite;
	
	
	
	@Listen("onClick = #tabRawfiles")
	public void onclickRawfiles() {
		
		try {
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			
			Set<Variety> allvars = new TreeSet(varietyfacade.getGermplasm());
			
			List listvars = new ArrayList();
			listvars.addAll(allvars);
			gridRawVarietyFiles.setModel( new SimpleListModel( listvars ));
			gridRawVarietyFiles.setRowRenderer(new VarietyDownloadsRowRenderer( varietyfacade.getVarietyExternalURL(  VarietyPropertiesService.ID_ERS ), 
					 varietyfacade.getVarietyExternalURL(  VarietyPropertiesService.ID_SRA )));
			
			AppContext.debug(listvars.size() + " varieties in file download list");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	@Listen("onClick =#buttonUnmarkAll")
	public void onclickUnmarkall() {
		VarietyDownloadsRowRenderer renderer = (VarietyDownloadsRowRenderer)gridRawVarietyFiles.getRowRenderer();
		Iterator<Checkbox> itChecks = renderer.getListchecks().iterator();
		while(itChecks.hasNext()) {
			Checkbox check = itChecks.next();
			check.setChecked(false);
		}
	}
	
	@Listen("onClick =#buttonDownloadMarked")
	public void onclickDownload() {
		
		String selSite = listboxSite.getSelectedItem().getLabel();
		if(selSite.equals("AWS HTTP")) {
			if( this.checkboxFastq.isChecked()) {
				
			}
			if( this.checkboxBAM.isChecked()) {
				
			}
			if( this.checkboxVCF.isChecked()) {
				
			}
		} 
		else if(selSite.equals("ASTI HTTP")) {
			
		}
		else if(selSite.equals("ASTI FTP")) {
			
		}	
	     
	}
	
	
	
	@Listen("onSelect =#listboxVarlist")
	public void onselectvarlist() {
		
		workspacefacade = (WorkspaceFacade)AppContext.checkBean(workspacefacade, "WorkspaceFacade");
		
		String selitem = listboxVarlist.getSelectedItem().getLabel().trim();
		if(selitem.isEmpty()) return;
		if(selitem.equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety&src=download");
			return;
		}
		
		Set setVarieties = workspacefacade.getVarieties( selitem );
		
		Set setvarnames = new HashSet();
		Iterator<Variety> itVar =  setVarieties.iterator();
		while(itVar.hasNext()) {
			Variety var = itVar.next();
			setvarnames.add(var.getName());
		}
		
		
		VarietyDownloadsRowRenderer renderer = (VarietyDownloadsRowRenderer)gridRawVarietyFiles.getRowRenderer();
		Iterator<String> itNames = renderer.getListNames().iterator();
		
		List listChecks = renderer.getListchecks();
		int icount=0;
		while(itNames.hasNext()) {
			String name = itNames.next();
			if(setvarnames.contains(name)) {
				Checkbox cb = (Checkbox)listChecks.get(icount);
				cb.setChecked(true);
			}
			icount++;
		}
		
	}
	
	
}
