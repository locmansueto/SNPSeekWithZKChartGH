package org.irri.iric.portal.admin.zkui;


import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
import org.irri.iric.portal.genomics.service.GenomicsFacade;
import org.irri.iric.portal.genomics.zkui.LocusListItemRenderer;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.hdf5.dao.SNPUni3kVarietiesAllele1DAO;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.irri.iric.portal.variety.zkui.VarietyListItemRenderer;
import org.irri.iric.portal.zk.CookieController;
import org.irri.iric.portal.zk.SessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

@Controller
@Scope("value=session")
//@Scope("request")
public class WorkspaceController  extends SelectorComposer<Component> {

	
	CookieController  cookieController  = new CookieController();
	SessionController sessionController = new SessionController();
	 
    @Autowired
    @Qualifier("WorkspaceFacade")
    private WorkspaceFacade  workspace;
    @Autowired
    private VarietyFacade  variety;
    @Autowired
    private GenotypeFacade  genotype;
    @Autowired
    private GenomicsFacade  genomics;

	@Wire
	private Checkbox checkboxSavedata;

    
    @Wire
    private Listbox listboxListnames;
    @Wire
    private Listbox listboxVarieties;
    @Wire
    private Listbox listboxPositions;
    @Wire
    private Listbox listboxLocus;
    @Wire
    private Button  buttonQueryIric;
    @Wire
    private Button buttonCreate;
    @Wire
    private Button buttonSave;
    @Wire
    private Button buttonCancel;
    @Wire
    private Button buttonDelete;
    @Wire
    private Vbox vboxEditNewList;
    @Wire
    private Textbox txtboxEditNewList;
    @Wire
    private Textbox txtboxEditListname;
    @Wire
    private Button buttonDownload;
    
    @Wire
    private Button buttonUpload;
    //@Wire
    //private Fileupload fileupload; 
    
    @Wire
    private Radio radioVariety;
    @Wire
    private Radio radioSNP;
    @Wire
    private Radio radioLocus;
    @Wire
    private Listbox selectChromosome;
    @Wire
    private Div divMsgVariety;
    @Wire
    private Div divMsgSNP;
    
    @Wire
    private Div divMsgLocus;
    @Wire
    private Label labelNItems;
    
    @Wire
    private Div divSetOps;
    @Wire
    private Button  buttonUnion;
    @Wire
    private Button  buttonIntersect;
    @Wire
    private Button  buttonAminusB;
    @Wire
    private Button  buttonBminusA;
    @Wire
    private Textbox textboxResultSet;
    
    @Wire
    private Vbox vboxListMembers;
    
    @Wire
    private Textbox textboxFrom;
    
    
    public WorkspaceController() {
		super();
		// TODO Auto-generated constructor stub
		AppContext.debug("created WorkspaceController:" + this);
	}

    private HttpSession getHttpSession() {
    	return (HttpSession)Sessions.getCurrent().getNativeSession(); 
    }
    
    
    @Listen("onClick =#buttonDownload")
    public void onclickDownloadLists() {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	workspace.downloadLists();
    }
    
    
    /*
    @Listen("onUpload =#fileupload")
    public void onupload() {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	workspace.uploadLists();
    	
    }
    */
    
    
    @Listen("onClick =#buttonUpload")
    public void onclickUploadLists() {
    
    	Fileupload.get(new EventListener(){
					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						
                        org.zkoss.util.media.Media media = ((UploadEvent)event).getMedia();

                    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
                    	workspace.uploadLists(media.getStringData());

                    	if(radioSNP.isSelected())
                    		Events.sendEvent( "onClick", radioSNP, null);
                    	else if(radioVariety.isSelected())
                    		Events.sendEvent( "onClick", radioVariety, null);
                    	else if(radioLocus.isSelected())
                    		Events.sendEvent( "onClick", radioLocus, null);
                    	
					}
                });
    
    }
    
    @Listen("onClick = #radioVariety") 
    public void onclickVariety() {
    
    	radioVariety.setSelected(true);
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List listVarlistNames=new ArrayList();
    	listVarlistNames.addAll( workspace.getVarietylistNames());
    	SimpleListModel model =  new SimpleListModel(listVarlistNames);
    	model.setMultiple(true);
    	listboxListnames.setModel(model);
    	if(listVarlistNames.size()>0) {
	    	listboxListnames.setSelectedIndex( listVarlistNames.size() -1 );
	    	
	    	AppContext.debug( listboxListnames.getSelectedItem().getLabel() + "  selected");
	    	Events.sendEvent( "onSelect", listboxListnames, null);
    	}  else labelNItems.setVisible(false);
    	listboxPositions.setVisible(false);
    	listboxVarieties.setVisible(true);
    	listboxLocus.setVisible(false);
    	
    	//labelNItems.setVisible(false);
    	
    	divMsgVariety.setVisible(true);
    	divMsgSNP.setVisible(false);
    	divMsgLocus.setVisible(false);
    	
    }
    
    
    @Listen("onClick = #radioLocus") 
    public void onclickLocus() {
    
    	radioLocus.setSelected(true);
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List listLocuslistNames=new ArrayList();
    	listLocuslistNames.addAll( workspace.getLocuslistNames());
    	SimpleListModel model =  new SimpleListModel(listLocuslistNames);
    	model.setMultiple(true);
    	listboxListnames.setModel(model);
    	if(listLocuslistNames.size()>0) {
	    	listboxListnames.setSelectedIndex( listLocuslistNames.size() -1 );
	    	
	    	AppContext.debug( listboxListnames.getSelectedItem().getLabel() + "  selected");
	    	Events.sendEvent( "onSelect", listboxListnames, null);
    	}  else labelNItems.setVisible(false);
    	listboxPositions.setVisible(false);
    	listboxVarieties.setVisible(false);
    	listboxLocus.setVisible(true);
    	
    	//labelNItems.setVisible(false);
    	
    	divMsgVariety.setVisible(false);
    	divMsgSNP.setVisible(false);
    	divMsgLocus.setVisible(true);
    }
    

    @Listen("onClick = #radioSNP") 
    public void onclickSNP() {
    	
    	radioSNP.setSelected(true);
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List listNames=new ArrayList();
    	listNames.addAll( workspace.getSnpPositionListNames( ));
    	SimpleListModel model =  new SimpleListModel(listNames);
    	model.setMultiple(true);
    	listboxListnames.setModel(model);
    	if(listNames.size()>0) {
    		
	    	listboxListnames.setSelectedIndex( listNames.size() -1 );
	    	
	    	AppContext.debug( listboxListnames.getSelectedItem().getLabel() + "  selected");
	    	Events.sendEvent( "onSelect", listboxListnames, null);
    	} else labelNItems.setVisible(false);
    		
    	listboxVarieties.setVisible(false);
    	listboxPositions.setVisible(true);
    	//labelNItems.setVisible(false);
    	listboxLocus.setVisible(false);
    	
    	divMsgVariety.setVisible(false);
    	divMsgSNP.setVisible(true);
    	divMsgLocus.setVisible(false);
    	
    }
    
    private Integer getChrFromSNPListLabel(String strlabel) {
    	return Integer.valueOf( strlabel.split(":")[0].replace("CHR","").trim() );
    }
    
    @Listen("onClick = #buttonUnion") 
    public void onclickUnion() {
    			Set setUnion = new HashSet();
    			Iterator<String> itSelitems = getListNamesSelection().iterator();
    			workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    			while(itSelitems.hasNext()) {
    				String listname = itSelitems.next() ;
    				if(radioVariety.isSelected())
    					setUnion.addAll(  workspace.getVarieties(  listname )  );
    			}
				if(radioVariety.isSelected())
					addVarlistFromSetops(setUnion);	
    			
    }
    
    @Listen("onClick = #buttonIntersect") 
    public void onclickIntersect() {
    	Set setUnion = new HashSet();
		Iterator<String> itSelitems = getListNamesSelection().iterator();

		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
		if(itSelitems.hasNext())
		{
			String listname = itSelitems.next();
			if(radioVariety.isSelected())
				setUnion.addAll(  workspace.getVarieties(  listname )  );
		}
		while(itSelitems.hasNext()) {
			String listname = itSelitems.next();
			
			if(radioVariety.isSelected())
				setUnion.retainAll( workspace.getVarieties(  listname )  );
		}
		if(radioVariety.isSelected())
			addVarlistFromSetops(setUnion);	
    	
    }
    @Listen("onClick = #buttonAminusB") 
    public void onclickAminusB() {
    	Set setUnion = new HashSet();
		
		Iterator<String> itSelitems = getListNamesSelection().iterator();
		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
		if(itSelitems.hasNext())
		{
			String listname = itSelitems.next() ;
			if(radioVariety.isSelected())
				setUnion.addAll(  workspace.getVarieties(  listname )  );
		}
		if (itSelitems.hasNext()) {
			String listname = itSelitems.next() ;
			if(radioVariety.isSelected())
				setUnion.removeAll( workspace.getVarieties(  listname )  );
		}
		if(radioVariety.isSelected())
			addVarlistFromSetops(setUnion);	
    }
    
    
    @Listen("onClick = #buttonBminusA") 
    public void onclickBminusA() {
    	Set setLast = new HashSet();
    	Set setUnion = new HashSet();
		Iterator<String> itSelitems = getListNamesSelection().iterator();
		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
		if(itSelitems.hasNext())
		{
			String listname = itSelitems.next();
			if(radioVariety.isSelected())
				setUnion.addAll(  workspace.getVarieties(  listname )  );
		}
		if (itSelitems.hasNext()) {
			String listname = itSelitems.next();
			if(radioVariety.isSelected()) 
				setLast.addAll( workspace.getVarieties(  listname )); 
		}
		setLast.removeAll( setUnion );
		
		if(radioVariety.isSelected())
			addVarlistFromSetops(setLast);	
    }
    
    
    private Set<String> getListNamesSelection() {
    	SimpleListModel listmodel = (SimpleListModel)listboxListnames.getModel();
    	Set setsel = listmodel.getSelection();
    	if(setsel.size()==0 && listmodel.getSize()>0) {

    		try {
	    		Object selobj = listmodel.getElementAt( listmodel.getSize()-1);
	    		Set newsel = new HashSet();
	    		newsel.add( selobj );
	    		listmodel.setSelection( newsel );
	    		return newsel;
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	return setsel;
    }
    
	@Listen("onSelect = #listboxListnames")
    public void onselectListnames() {
    	
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");


    	Set<String> selSelection = getListNamesSelection();
    	//Set<Listitem> setListitems = listboxVarietylist.getSelectedItems();
    	//listboxVarietylist.setSeltype(seltype);
    	
    	AppContext.debug(selSelection.size() + " getSelections selected" );
    	
    	//AppContext.debug(setListitems.size() + " listitems selected" );
    	//AppContext.debug(listboxVarietylist.getSelectedItem() + " item selected");
    		
    	if(selSelection.size()==0) return;
    	
    	
    	//AppContext.debug( selSelection.iterator().next() + " getSelections first value" );
    	
    	
    	if(selSelection.size()>1) {
    		vboxListMembers.setVisible(false);
    		divSetOps.setVisible(true);
    		
    		/*
    		if(selSelection.size()>2) {
    			buttonAminusB.setVisible(false);
    			buttonBminusA.setVisible(false);
    		} else {
    			buttonAminusB.setVisible(true);
    			buttonBminusA.setVisible(true);
    			
    			Iterator<String> itSelitems = selSelection.iterator();
    			String nameA=itSelitems.next();
    			String nameB=itSelitems.next();
    			buttonAminusB.setLabel( nameA + " Minus " +  nameB );
    			buttonBminusA.setLabel( nameB + " Minus " +  nameA );
    		}
    		*/
    		    
    	} else {
    	
    		divSetOps.setVisible(false);
    		vboxListMembers.setVisible(true);
    		
	    	List listTmp = new ArrayList();
	    	
	    	if(this.radioVariety.isSelected() ) {
	    		listboxPositions.setVisible(false);
	    		listboxLocus.setVisible(false);
		    	listboxVarieties.setItemRenderer( new VarietyListItemRenderer() );
		    	//listTmp.addAll( workspace.getVarieties( listboxVarietylist.getSelectedItem().getLabel() )  );
		    	listTmp.addAll( workspace.getVarieties( selSelection.iterator().next() )  );
		    	SimpleListModel model= new SimpleListModel( listTmp );
		    	model.setMultiple(true);
	    		listboxVarieties.setModel(model);
	    		listboxVarieties.setVisible(true);
	    		
	    		AppContext.debug(listTmp.size() + " variety lists");
	    		
	    	}
	    	else if(this.radioLocus.isSelected() ) {
	    		listboxVarieties.setVisible(false);
	    		listboxPositions.setVisible(false);
		    	listboxLocus.setItemRenderer( new LocusListItemRenderer() );
		    	//listTmp.addAll( workspace.getVarieties( listboxVarietylist.getSelectedItem().getLabel() )  );
		    	listTmp.addAll( workspace.getLoci( selSelection.iterator().next() )  );
		    	SimpleListModel model= new SimpleListModel( listTmp );
		    	model.setMultiple(true);
	    		listboxLocus.setModel(model);
	    		listboxLocus.setVisible(true);
	    		
	    		AppContext.debug(listTmp.size() + " locus lists");
	    		
	    	}
	    	else if(this.radioSNP.isSelected() ) {
	    		listboxVarieties.setVisible(false);
	    		listboxLocus.setVisible(false);
	    		//String snplabels[] = listboxVarietylist.getSelectedItem().getLabel().trim().split(":");
	    		String snplabels[] =  selSelection.iterator().next().trim().split(":"); 
	    		Integer chr =  Integer.valueOf( snplabels[0].replace("CHR","").trim() );
		    	((SNPChrPositionListitemRenderer)listboxPositions.getItemRenderer()).setChromosome( chr.toString() );
	    		listTmp.addAll(workspace.getSnpPositions( chr, snplabels[1].trim()  ) ) ;
		    	SimpleListModel model= new SimpleListModel( listTmp );
		    	model.setMultiple(true);
	    		listboxPositions.setModel(model);  
	    		listboxPositions.setVisible(true);
	    		
	    		AppContext.debug(listTmp.size() + " position lists");
	
	    	}
	    	
	    	
	    	
			labelNItems.setValue(listTmp.size() + " items in list");
	    	labelNItems.setVisible(true);
    	}
    }
    
	@Listen("onClick =#buttonQueryIric") 
	public void onbuttonQueryIric() {
		
		System.out.println("onClick =#buttonQueryIric");
		System.out.println("in queryIric..." );
		SNPUni3kVarietiesAllele1DAO snpuniDAO = new SNPUni3kVarietiesAllele1DAO();
	  	Map mapVar2Str =  snpuniDAO.readSNPString(1,  1000, 1100);
	  	Iterator itVar = mapVar2Str.keySet().iterator();
	  	while(itVar.hasNext()) {
	  		Object var = itVar.next();
	  		System.out.println( var + " : " + mapVar2Str.get(var));
	  	}
	  	
		//workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
		//workspace.queryIric();
	}
	
	
    @Listen("onClick =#buttonCreate")
    public void onbuttonCreate() {
    	listboxVarieties.setVisible(false);
    	listboxPositions.setVisible(false);
    	listboxLocus.setVisible(false);
    	
    	vboxEditNewList.setVisible(true);
    	buttonCreate.setVisible(false);
    	buttonDelete.setVisible(false);
    	buttonSave.setVisible(true);
    	buttonCancel.setVisible(true);
    	labelNItems.setVisible(false);
    	
    	radioSNP.setDisabled(true);
    	radioVariety.setDisabled(true);
    	radioLocus.setDisabled(true);
    }
    
    @Listen("onClick =#buttonSave")
    public void onbuttonSave() {
    	boolean success=false;
    	if(radioVariety.isSelected())
    		success = onbuttonSaveVariety();
    	else if(radioSNP.isSelected())
    		success =  onbuttonSaveSNP();
    	else if(radioLocus.isSelected())
    		success =  onbuttonSaveLocus();
    	
    	radioSNP.setDisabled(false);
    	radioVariety.setDisabled(false);
    	radioLocus.setDisabled(false);

    	if(checkboxSavedata.isChecked()) {
        	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    		cookieController.setCookie("mylist", workspace.getMyListsCookie(), 999999999);
   	      	cookieController.setCookie("storemylist",  String.valueOf(checkboxSavedata.isChecked()), 999999999 );
    	} else
    	{
    		cookieController.setCookie("mylist", null, 0);
    		cookieController.setCookie("storemylist", null, 0);
    	}
    	    	
    	if(textboxFrom!=null && success) {
    		if(textboxFrom.getValue().equals("variety")) {
    			Executions.sendRedirect("_variety.zul?from=varietylist");
    		} else if(textboxFrom.getValue().equals("snp") )
    		{
    			Executions.sendRedirect("_snp.zul?from=snplist");
    		} else if(textboxFrom.getValue().equals("locus") )
    		{
    			Executions.sendRedirect("_locus.zul?from=locuslist");
    		}
    	}
    	
    }
    
    @Listen("onClick =#buttonCancel")
    public void onbuttonCancel() {
    	vboxEditNewList.setVisible(false);
    	buttonCancel.setVisible(false);
    	buttonSave.setVisible(false);
    	buttonCreate.setVisible(true);
    	buttonDelete.setVisible(true);
    	if(radioSNP.isSelected()) Events.sendEvent( "onClick", radioSNP, null); //onclickSNP();
    	else if (radioVariety.isSelected()) Events.sendEvent( "onClick", radioVariety, null); // onclickVariety();
    	else if (radioLocus.isSelected()) Events.sendEvent( "onClick", radioLocus, null); // onclickVariety();

    	radioSNP.setDisabled(false);
    	radioVariety.setDisabled(false);
    	radioLocus.setDisabled(false);
    	
    	
    	if(textboxFrom!=null) {
    		if(textboxFrom.getValue().equals("variety")) {
    			Executions.sendRedirect("_variety.zul");
    		} else if(textboxFrom.getValue().equals("snp") )
    		{
    			Executions.sendRedirect("_snp.zul");
    		} else if(textboxFrom.getValue().equals("locus") )
    		{
    			Executions.sendRedirect("_locus.zul");
    		}
    	}
    	

    }
    
    private void addPoslistFromSetops(Set setMatched) { /*
    	Integer chr =  Integer.valueOf(selectChromosome.getSelectedItem().getLabel());
    	if(setMatched.size()>0) {
    		
    		AppContext.debug("Adding SNP list");
    		
    		String newlistname = txtboxEditListname.getValue().replaceAll(":", "").trim();
    		if(newlistname.isEmpty()) {
    			Messagebox.show("Provide unique list name","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;
    		}
    		if(workspace.getSnpPositions(chr, newlistname)!=null && !workspace.getSnpPositions(chr,newlistname).isEmpty())
    		{
    			Messagebox.show("Listname already exists","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;   			
    		}
    		if(workspace.addSnpPositionList(chr, newlistname , setMatched)) {
    			
    			AppContext.debug(newlistname + " added with " + setMatched.size() +  " items" );
    			
    			txtboxEditNewList.setValue("");
    	    	txtboxEditListname.setValue("");
    	     	listboxVarieties.setVisible(true);
    	    	vboxEditNewList.setVisible(false);
    	    	buttonCreate.setVisible(true);
    	    	buttonDelete.setVisible(true);
    	    	buttonSave.setVisible(false);
    	    	buttonCancel.setVisible(false);
    	       	
    	    	onclickSNP();
    			
    		}
    		else {
    			Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
    		}
    	}
    	*/
    }
    
    
    private void addVarlistFromSetops(Set setMatched) {
    	if(setMatched.size()>0) {
    		
    		AppContext.debug("Adding variety list");
    		
    		if( this.textboxResultSet.getValue().trim().isEmpty()) {
    			Messagebox.show("Provide unique list name","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;
    		}
    		if(workspace.getVarieties(textboxResultSet.getValue().trim())!=null && !workspace.getVarieties(textboxResultSet.getValue().trim()).isEmpty())
    		{
    			Messagebox.show("Listname already exists","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;   			
    		}
    		if(workspace.addVarietyList(textboxResultSet.getValue().trim() , setMatched)) {
    			
    			AppContext.debug( textboxResultSet.getValue().trim() + " added with " + setMatched.size() +  " items" );
    			
    			//txtboxEditNewList.setValue("");
    			textboxResultSet.setValue("");
    	     	listboxVarieties.setVisible(true);
    			//labelNItems.setValue(listTmp.size() + " items in list");
    	    	
    	    	
    	     	Events.sendEvent( "onClick", radioVariety, null);
    			//onclickVariety();
    			
    			labelNItems.setVisible(true);
    			
    			/*
    	     	listboxVarieties.setVisible(true);
    	    	vboxEditNewList.setVisible(false);
    	    	buttonCreate.setVisible(true);
    	    	buttonDelete.setVisible(true);
    	    	buttonSave.setVisible(false);
    	    	buttonCancel.setVisible(false);
    	    	onclickVariety();
    	    	
    	    	*/
    			
    		}
    		else {
    			Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
    		}
    	}
    	else Messagebox.show("Resulting list has zero element","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    	
    }
    
    private boolean isMsgboxEventSuccess=false;
    
    private boolean onbuttonSaveSNP() {
    	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	isMsgboxEventSuccess = false;

    	String lines[] = txtboxEditNewList.getValue().trim().split("\n");
		
    	Set setSNP = new HashSet();
    	for(int isnp=0; isnp<lines.length; isnp++) {
    		try {
    			String num = lines[isnp].trim();
    			if(num.isEmpty()) continue;
    			setSNP.add(  BigDecimal.valueOf( Long.valueOf(  num )));
    		} catch (Exception ex)
    		{
    			
    		}
    	}
    	
    	//Set snps = ;

    	//AppContext.displayCollection("setSNP", setSNP);

    	
    	Set<BigDecimal> setSNPDBPos = new HashSet();
    	Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(selectChromosome.getSelectedItem().getLabel(), setSNP,  SnpcoreRefposindexDAO.TYPE_3KALLSNP ).iterator();
    	while(itSnpsDB.hasNext()) {
    		setSNPDBPos.add( itSnpsDB.next().getPos() );
    	}
    	
    	//AppContext.displayCollection("setSNPDBPos", setSNPDBPos);

    	
    	Set<BigDecimal> setCoreSNPDBPos = new HashSet();
    	Iterator<SnpsAllvarsPos> itCoreSnpsDB = genotype.checkSNPInChromosome(selectChromosome.getSelectedItem().getLabel(), setSNP, SnpcoreRefposindexDAO.TYPE_3KCORESNP ).iterator();
    	while(itCoreSnpsDB.hasNext()) {
    		setCoreSNPDBPos.add( itCoreSnpsDB.next().getPos() );
    	}

    	AppContext.displayCollection("setCoreSNPDBPos", setCoreSNPDBPos);

    	
    	// list in snp universe
    	final Set setMatched = new TreeSet(setSNP);
    	setMatched.retainAll(setSNPDBPos);
    	
    	//AppContext.displayCollection("setMatched", setMatched);

    	
    	if(setMatched.size()==0) {
    		Messagebox.show("No identified SNP positions","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
    	}
    	
    	// list not in snp universe
    	Set setMinus = new TreeSet(setSNP);
    	setMinus.removeAll( setSNPDBPos );

    	//AppContext.displayCollection("setMinus", setMinus);

    	
    	// list in snp universe not in core
		Set setMatchedNotInCore = new TreeSet(setMatched);
		setMatchedNotInCore.removeAll(setCoreSNPDBPos);
		
    	//AppContext.displayCollection("setMatchedNotInCore", setMatchedNotInCore);

    	
    	if(setMinus.size()>0 || setMatchedNotInCore.size()>0) {
    		
    		if(setMatched.size()>0) {
	    		StringBuffer buff = new StringBuffer();
	    		if(setMinus.size()>0) {
		    		buff.append("Not SNP positions: " + setMinus.size() + "\n");
		    		Iterator itVar = setMinus.iterator();
		    		while(itVar.hasNext()) {
		    			buff.append(itVar.next());
		    			buff.append("\n");
		    		}
	    		}
	    		
	    		buff.append("SNP positions in the list: " + setMatched.size() + "\n");
	    		
	    		if(setMatchedNotInCore.size()>0) {
		    		buff.append("SNP positions not in Core set: " + setMatchedNotInCore.size() + "\n");
		    		Iterator itVar = setMatchedNotInCore.iterator();
		    		while(itVar.hasNext()) {
		    			buff.append(itVar.next());
		    			buff.append("\n");
		    		}
	    		}

	    		buff.append("Do you want to proceed?");
	    	
	    		/*
	    		int intret = Messagebox.show(buff.toString(),"WARNING",Messagebox.YES | Messagebox.NO, Messagebox.QUESTION); 
	    		System.out.print("intret=" + intret);
	    		if(intret==Messagebox.YES )
	    		{} 
	    		else return;
	    		*/
	    		
	    		
	    		
	    		Messagebox.show(buff.toString(),"WARNING",Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, 0,
	    				 	new org.zkoss.zk.ui.event.EventListener() {
								@Override
								public void onEvent(Event e) throws Exception {
									// TODO Auto-generated method stub
									AppContext.debug( e.getName() + " pressed");
									
				                    if(Messagebox.ON_YES.equals(e.getName())){
				                    	
				                    	// add pos list
				                    	
				                    	Integer chr = Integer.valueOf(selectChromosome.getSelectedItem().getLabel());
				                    	if(setMatched.size()>0) {
				                    		
				                    		AppContext.debug("Adding SNP list");
				                    		
				                    		String newlistname = txtboxEditListname.getValue().replaceAll(":", "").trim();
				                    		if(newlistname.isEmpty()) {
				                    			Messagebox.show("Provide unique list name","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
				                    			return;
				                    		}
				                    		if(workspace.getSnpPositions(chr, newlistname)!=null && !workspace.getSnpPositions(chr,newlistname).isEmpty())
				                    		{
				                    			Messagebox.show("Listname already exists","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
				                    			return;   			
				                    		}
				                    		if(workspace.addSnpPositionList(chr, newlistname , setMatched)) {
				                    			
				                    			AppContext.debug(newlistname + " added with " + setMatched.size() +  " items" );
				                    			
				                    			txtboxEditNewList.setValue("");
				                    	    	txtboxEditListname.setValue("");
				                    	     	listboxVarieties.setVisible(false);
				                    	    	vboxEditNewList.setVisible(false);
				                    	    	buttonCreate.setVisible(true);
				                    	    	buttonDelete.setVisible(true);
				                    	    	buttonSave.setVisible(false);
				                    	    	buttonCancel.setVisible(false);
				                    	       	
				                    	    	Events.sendEvent( "onClick", radioSNP, null);
				                    	    	
				                    	    	//onclickSNP();
				                    	    	isMsgboxEventSuccess = true;
				                    			
				                    		}
				                    		else 
				                    			Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);

				                    	}
				                    } 
				                    else
				                    	Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
								}
	    				});
	    		
	    		return isMsgboxEventSuccess;
	    		
    		} else
    		{
    			Messagebox.show("No identified SNP positions","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
    			return false;
    			
    		}
    	
    	}
    	return true;
    
    }
    
    private void addVarlist(Set setMatched) {
    	if(setMatched.size()>0) {
    		
    		AppContext.debug("Adding variety list");
    		
    		if(txtboxEditListname.getValue().trim().isEmpty()) {
    			Messagebox.show("Provide unique list name","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;
    		}
    		if(workspace.getVarieties(txtboxEditListname.getValue().trim())!=null && !workspace.getVarieties(txtboxEditListname.getValue().trim()).isEmpty())
    		{
    			Messagebox.show("Listname already exists","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;   			
    		}
    		if(workspace.addVarietyList(txtboxEditListname.getValue().trim() , setMatched)) {
    			
    			AppContext.debug( txtboxEditListname.getValue().trim() + " added with " + setMatched.size() +  " items" );
    			
    			txtboxEditNewList.setValue("");
    	    	txtboxEditListname.setValue("");
    	     	listboxVarieties.setVisible(true);
    	    	vboxEditNewList.setVisible(false);
    	    	buttonCreate.setVisible(true);
    	    	buttonDelete.setVisible(true);
    	    	buttonSave.setVisible(false);
    	    	buttonCancel.setVisible(false);
    	       	
    	    	Events.sendEvent( "onClick", radioVariety, null);
    	    	//onclickVariety();
    			
    		}
    		else {
    			Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
    		}
    	}
    }
    
    private void addLocuslist(Set setMatched) {
    	if(setMatched.size()>0) {
    		
    		AppContext.debug("Adding locus list");
    		
    		if(txtboxEditListname.getValue().trim().isEmpty()) {
    			Messagebox.show("Provide unique list name","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;
    		}
    		if(workspace.getLoci(txtboxEditListname.getValue().trim())!=null && !workspace.getLoci(txtboxEditListname.getValue().trim()).isEmpty())
    		{
    			Messagebox.show("Listname already exists","INVALID VALUE",Messagebox.OK, Messagebox.EXCLAMATION);
    			return;   			
    		}
    		if(workspace.addLocusList(txtboxEditListname.getValue().trim() , setMatched)) {
    			
    			AppContext.debug( txtboxEditListname.getValue().trim() + " added with " + setMatched.size() +  " items" );
    			
    			txtboxEditNewList.setValue("");
    	    	txtboxEditListname.setValue("");
    	     	//listboxVarieties.setVisible(true);
    	    	listboxLocus.setVisible(true);
    	    	vboxEditNewList.setVisible(false);
    	    	buttonCreate.setVisible(true);
    	    	buttonDelete.setVisible(true);
    	    	buttonSave.setVisible(false);
    	    	buttonCancel.setVisible(false);
    	       	
    	    	Events.sendEvent( "onClick", radioLocus, null);
    	    	//onclickVariety();
    			
    		}
    		else {
    			Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
    		}
    	}
    }
    
    
    private class LocusComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			Locus l1 =(Locus)o1;
			Locus l2 =(Locus)o2;
			
			int ret = l1.getContig().compareTo(l2.getContig());
			if(ret==0) {
				ret = l1.getFmin().compareTo(l2.getFmin());
				if(ret==0) {
					ret = l1.getFmax().compareTo(l2.getFmax());
					if(ret==0) {
						ret = l1.getUniquename().compareToIgnoreCase(l2.getUniquename());
					}
				}
			}
			
			return ret;
		}
    	
    }
    
    private boolean onbuttonSaveLocus() {
    	
    	//Messagebox.show("Not yet implemented");
    	
    	
    	isMsgboxEventSuccess = false;
    	genomics =  (GenomicsFacade)AppContext.checkBean(genomics , "GenomicsFacade");
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	Set setReadNames = new HashSet();
    	
    	List listNoMatch = new ArrayList();
    	//final Set setMatched = new TreeSet();
    	final Set setMatched = new TreeSet(new LocusComparator());
    	String lines[] = txtboxEditNewList.getValue().trim().split("\n");
    	for (int i=0; i<lines.length; i++) {
    		
    		Locus locus = null;
    		String locusstr=lines[i].trim().toUpperCase();
    		
    		AppContext.debug("checking locus " + locusstr );
    		
    		if(locusstr.isEmpty()) continue;
    		if(setReadNames.contains(locusstr)) continue;
    		setReadNames.add(locusstr);
    		
    		try {
    			locus = genomics.getLocusByName(locusstr);
	    		if(locus==null) 
	    			listNoMatch.add(locusstr);
	    		else
	    			setMatched.add(locus);
    		
            } catch (InvocationTargetException e) {
                // Answer:
                e.getCause().printStackTrace();
                Messagebox.show(e.getCause().getMessage(), "LOCUS QUERY InvocationTargetException", Messagebox.OK,  Messagebox.EXCLAMATION);
    		} catch(Exception ex) {
    			ex.printStackTrace();
    			Messagebox.show(ex.getMessage(), "LOCUS QUERY EXCEPTION", Messagebox.OK,  Messagebox.EXCLAMATION);
    		}
    	}
    	
     	if(setMatched.size()==0) {
    		Messagebox.show("No identified loci","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
    	}
     	
    	if(listNoMatch.size()>0) {
    		
    		if(setMatched.size()>0) {
	    		StringBuffer buff = new StringBuffer();
	    		buff.append("Recognized loci in the list: "  +  setMatched.size() + "\n");
	    		
	    		buff.append("Cannot identify these loci: " + listNoMatch.size() + "\n");
	    		Iterator itVar = listNoMatch.iterator();
	    		while(itVar.hasNext()) {
	    			buff.append(itVar.next());
	    			buff.append("\n");
	    		}
	    		buff.append("Do you want to proceed?");
	    		
	   	    		
	    		Messagebox.show(buff.toString(),"WARNING",Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, 0,
	    				 	new org.zkoss.zk.ui.event.EventListener() {
								@Override
								public void onEvent(Event e) throws Exception {
									// TODO Auto-generated method stub
									AppContext.debug( e.getName() + " pressed");
									
				                    if(Messagebox.ON_YES.equals(e.getName())){
				                        //OK is clicked
				                    	addLocuslist(setMatched);
				                    	isMsgboxEventSuccess = true;
				                    	
				                    } else
				                    	Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
								}
	    				});
	    		
	    		return isMsgboxEventSuccess;
	    				
    		} else
    		{
    			Messagebox.show("No identified loci","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
    			return false;
    			
    		}
    		
    	} else {
    		addLocuslist(setMatched);
    		return true;
    	}
    	
    	
    }
    
    private boolean onbuttonSaveVariety() {
    	
    	isMsgboxEventSuccess = false;
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List listNoMatch = new ArrayList();
    	final Set setMatched = new TreeSet();
    	String lines[] = txtboxEditNewList.getValue().trim().split("\n");
    	for (int i=0;i<lines.length; i++) {
    		
    		Variety var = null;
    		String varstr=lines[i].trim().toUpperCase();
    		if(varstr.isEmpty()) continue;
    		try {
	    		var = variety.getGermplasmByName(varstr);
	    		if(var==null)
	    			var = variety.getGermplasmByName(varstr.replaceAll("_"," "));
	    		if(var==null)
	    			var = variety.getGermplasmByIrisId(varstr);
	    		if(var==null)
	    			var = variety.getGermplasmByIrisId(varstr.replaceAll("_"," ") );
	    		if(var==null)
	    			var = variety.getGermplasmByIrisId("IRIS " + varstr);
	    		if(var==null)
	    			var = variety.getGermplasmByNameLike(varstr);
	    		if(var==null)
	    			var = variety.getGermplasmByNameLike("%:" + varstr);
	    		if(var==null)
	    			var = variety.getGermplasmByNameLike("%:irgc " + varstr);
	    		if(var==null)
	    			var = variety.getGermplasmByNameLike(varstr+"%");
    		}
    		catch(Exception ex) {
    			Messagebox.show(ex.getMessage(), "VARIETY QUERY EXCEPTION", Messagebox.OK,  Messagebox.EXCLAMATION);
    		}
    		
    		if(var==null) 
    			listNoMatch.add(varstr);
    		else
    			setMatched.add(var);
    	}
    	
     	if(setMatched.size()==0) {
    		Messagebox.show("No identified varieties","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
    	}
     	
    	if(listNoMatch.size()>0) {
    		
    		if(setMatched.size()>0) {
	    		StringBuffer buff = new StringBuffer();
	    		buff.append("Recognized varieties in the list: "  +  setMatched.size() + "\n");
	    		
	    		buff.append("Cannot identify these varieties: " + listNoMatch.size() + "\n");
	    		Iterator itVar = listNoMatch.iterator();
	    		while(itVar.hasNext()) {
	    			buff.append(itVar.next());
	    			buff.append("\n");
	    		}
	    		buff.append("Do you want to proceed?");
	    		
	   	    		
	    		Messagebox.show(buff.toString(),"WARNING",Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, 0,
	    				 	new org.zkoss.zk.ui.event.EventListener() {
								@Override
								public void onEvent(Event e) throws Exception {
									// TODO Auto-generated method stub
									AppContext.debug( e.getName() + " pressed");
									
				                    if(Messagebox.ON_YES.equals(e.getName())){
				                        //OK is clicked
				                    	addVarlist(setMatched);
				                    	isMsgboxEventSuccess = true;
				                    	
				                    } else
				                    	Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
								}
	    				});
	    		
	    		return isMsgboxEventSuccess;
	    				
    		} else
    		{
    			Messagebox.show("No identified varieties","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
    			return false;
    			
    		}
    		
    	} else {
    		addVarlist(setMatched);
    		return true;
    	}
    	
    	
    }
    
    @Listen("onClick =#buttonDelete")
    public void onbuttonDelete() {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	workspace.deleteVarietyList( listboxListnames.getSelectedItem().getLabel() );
//    	listboxVarietylist.setSelectedIndex(0);
//    	Executions.sendRedirect("_workspace.zul");
    	
    	List listVarlistNames=new ArrayList();
    	listVarlistNames.addAll( workspace.getVarietylistNames( ));
    	listboxListnames.setModel( new SimpleListModel(listVarlistNames));
    	listboxListnames.setSelectedIndex(0);
    	
    	//AppContext.debug( listboxVarietylist.getSelectedItem().getLabel() + "  selected");
    	
    	//Events.sendEvent( "onSelect", listboxVarietylist, null);

    	
    }
    

    @Override
    public void doAfterCompose(Component comp) throws Exception {
      super.doAfterCompose(comp);
      init();
    }
    
    
    private void init() {
    	
    	 if (isLoggedIn()) {
    	      // Log in the man
    	      //switchToLoggedInView();
    	    } else {
    	      //switchToLoggedOutView();
    	      // Try to fill the form with saved data from cookie
    	      autoFillMyList();
    	    }
    }
    
    private void autoFillMyList() {
    	sessionController.setSessionObject("isLoggedIn", true);
    	String storestr = cookieController.getCookie("storemylist");
    	
    	if(storestr!=null) checkboxSavedata.setValue(  Boolean.valueOf(storestr) );
    	
    	String mylist = cookieController.getCookie("mylist");
    	
    	if(mylist!=null) {
    		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    		workspace.setMyListsCookie(mylist);
    	}
    }

     	
     	
    /**
     * Checks if the user is already logged in
     *
     * @return Returns true if the user is logged in, false if not.
     */
    private boolean isLoggedIn() {
      if (sessionController.sessionIsNew()) {
        // Return false if session is fresh
        return false;
      } else {
        // Returns the status that's set in the session object
        Object status = sessionController.getSessionObject("isLoggedIn");
        if (status == null) {
          return false;
        } else {
          return (Boolean) status;
        }
      }
    }
    
    
}
