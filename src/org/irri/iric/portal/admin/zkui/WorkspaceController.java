package org.irri.iric.portal.admin.zkui;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.irri.iric.portal.variety.zkui.VarietyListItemRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
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
//@Scope("session")
//@Scope("request")
public class WorkspaceController  extends SelectorComposer<Component> {

    @Autowired
    @Qualifier("WorkspaceFacade")
    private WorkspaceFacade  workspace;
    @Autowired
    private VarietyFacade  variety;
    @Autowired
    private GenotypeFacade  genotype;
	
    @Wire
    private Listbox listboxVarietylist;
    @Wire
    private Listbox listboxVarieties;
    @Wire
    private Listbox listboxPositions;
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
    private Vbox vboxEditVariety;
    @Wire
    private Textbox txtboxEditVariety;
    @Wire
    private Textbox txtboxEditListname;
    @Wire
    private Button buttonDownload;
    @Wire
    private Radio radioVariety;
    @Wire
    private Radio radioSNP;
    @Wire
    private Listbox selectChromosome;
    @Wire
    private Div divMsgVariety;
    @Wire
    private Div divMsgSNP;
    @Wire
    private Label labelNItems;
    
    
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
    
    
    @Listen("onClick = #radioVariety") 
    public void onclickVariety() {
    
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List listVarlistNames=new ArrayList();
    	listVarlistNames.addAll( workspace.getVarietylistNames());
    	listboxVarietylist.setModel( new SimpleListModel(listVarlistNames));
    	if(listVarlistNames.size()>0) {
	    	listboxVarietylist.setSelectedIndex( listVarlistNames.size() -1 );
	    	
	    	AppContext.debug( listboxVarietylist.getSelectedItem().getLabel() + "  selected");
	    	Events.postEvent( "onSelect", listboxVarietylist, null);
    	}
    	listboxPositions.setVisible(false);
    	listboxVarieties.setVisible(true);
    	
    	labelNItems.setVisible(false);
    	
    	divMsgVariety.setVisible(true);
    	divMsgSNP.setVisible(false);
    	
    }

    @Listen("onClick = #radioSNP") 
    public void onclickSNP() {
    	
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List listNames=new ArrayList();
    	listNames.addAll( workspace.getSnpPositionListNames( ));
    	listboxVarietylist.setModel( new SimpleListModel(listNames));
    	if(listNames.size()>0) {
    		
	    	listboxVarietylist.setSelectedIndex( listNames.size() -1 );
	    	
	    	AppContext.debug( listboxVarietylist.getSelectedItem().getLabel() + "  selected");
	    	Events.postEvent( "onSelect", listboxVarietylist, null);
    	}
    	listboxVarieties.setVisible(false);
    	listboxPositions.setVisible(true);
    	
    	labelNItems.setVisible(false);
    	
    	divMsgVariety.setVisible(false);
    	divMsgSNP.setVisible(true);
    	
    }
    

	@Listen("onSelect = #listboxVarietylist")
    public void onselectVarlist() {
    	
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");

    	List listTmp = new ArrayList();
    	
    	if(this.radioVariety.isSelected() ) {
    		listboxPositions.setVisible(false);
	    	listboxVarieties.setItemRenderer( new VarietyListItemRenderer() );
	    	listTmp.addAll( workspace.getVarieties( listboxVarietylist.getSelectedItem().getLabel() )  );
    		listboxVarieties.setModel(new SimpleListModel( listTmp ));
    		listboxVarieties.setVisible(true);
    	}
    	else if(this.radioSNP.isSelected() ) {
    		listboxVarieties.setVisible(false);
    		String snplabels[] = listboxVarietylist.getSelectedItem().getLabel().trim().split(":");
    		Integer chr =  Integer.valueOf( snplabels[0].replace("CHR","").trim() );
	    	((SNPChrPositionListitemRenderer)listboxPositions.getItemRenderer()).setChromosome( chr.toString() );
    		listTmp.addAll(workspace.getSnpPositions( chr, snplabels[1].trim()  ) ) ;   	
    		listboxPositions.setModel(new SimpleListModel(listTmp));  
    		listboxPositions.setVisible(true);

    	}
		labelNItems.setValue(listTmp.size() + " items in list");
    	labelNItems.setVisible(true);
    }
    
	@Listen("onClick =#buttonQueryIric") 
	public void onbuttonQueryIric() {
		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
		workspace.queryIric();
	}
	
	
    @Listen("onClick =#buttonCreate")
    public void onbuttonCreate() {
    	listboxVarieties.setVisible(false);
    	listboxPositions.setVisible(false);
    	
    	vboxEditVariety.setVisible(true);
    	buttonCreate.setVisible(false);
    	buttonDelete.setVisible(false);
    	buttonSave.setVisible(true);
    	buttonCancel.setVisible(true);
    	labelNItems.setVisible(false);
    	
    	radioSNP.setDisabled(true);
    	radioVariety.setDisabled(true);
    }
    
    @Listen("onClick =#buttonSave")
    public void onbuttonSave() {
    	if(radioVariety.isSelected())
    		 onbuttonSaveVariety();
    	else if(radioSNP.isSelected())
    		 onbuttonSaveSNP();
    	
    	radioSNP.setDisabled(false);
    	radioVariety.setDisabled(false);

    }
    
    @Listen("onClick =#buttonCancel")
    public void onbuttonCancel() {
    	vboxEditVariety.setVisible(false);
    	buttonCancel.setVisible(false);
    	buttonSave.setVisible(false);
    	buttonCreate.setVisible(true);
    	buttonDelete.setVisible(true);
    	if(radioSNP.isSelected()) onclickSNP();
    	else if (radioVariety.isSelected()) onclickVariety();

    	radioSNP.setDisabled(false);
    	radioVariety.setDisabled(false);

    }
    
    
    private void onbuttonSaveSNP() {
    	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	

    	String lines[] = txtboxEditVariety.getValue().trim().split("\n");
		
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

    	AppContext.displayCollection("setSNP", setSNP);

    	
    	Set<BigDecimal> setSNPDBPos = new HashSet();
    	Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(selectChromosome.getSelectedItem().getLabel(), setSNP,  SnpcoreRefposindexDAO.TYPE_3KALLSNP ).iterator();
    	while(itSnpsDB.hasNext()) {
    		setSNPDBPos.add( itSnpsDB.next().getPos() );
    	}
    	
    	AppContext.displayCollection("setSNPDBPos", setSNPDBPos);

    	
    	Set<BigDecimal> setCoreSNPDBPos = new HashSet();
    	Iterator<SnpsAllvarsPos> itCoreSnpsDB = genotype.checkSNPInChromosome(selectChromosome.getSelectedItem().getLabel(), setSNP, SnpcoreRefposindexDAO.TYPE_3KCORESNP ).iterator();
    	while(itCoreSnpsDB.hasNext()) {
    		setCoreSNPDBPos.add( itCoreSnpsDB.next().getPos() );
    	}

    	AppContext.displayCollection("setCoreSNPDBPos", setCoreSNPDBPos);

    	
    	// list in snp universe
    	final Set setMatched = new TreeSet(setSNP);
    	setMatched.retainAll(setSNPDBPos);
    	
    	AppContext.displayCollection("setMatched", setMatched);

    	
    	if(setMatched.size()==0) {
    		Messagebox.show("No identified SNP positions","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
			return;
    	}
    	
    	// list not in snp universe
    	Set setMinus = new TreeSet(setSNP);
    	setMinus.removeAll( setSNPDBPos );

    	AppContext.displayCollection("setMinus", setMinus);

    	
    	// list in snp universe not in core
		Set setMatchedNotInCore = new TreeSet(setMatched);
		setMatchedNotInCore.removeAll(setCoreSNPDBPos);
		
    	AppContext.displayCollection("setMatchedNotInCore", setMatchedNotInCore);

    	
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
									//AppContext.debug( e.getName() + " pressed");
									
				                    if(Messagebox.ON_YES.equals(e.getName())){
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
				                    			
				                    			txtboxEditVariety.setValue("");
				                    	    	txtboxEditListname.setValue("");
				                    	     	listboxVarieties.setVisible(true);
				                    	    	vboxEditVariety.setVisible(false);
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
				                    }
								}
	    				});
	    		
    		} else
    		{
    			Messagebox.show("No identified SNP positions","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
    			return;
    			
    		}
    	}
    
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
    			
    			txtboxEditVariety.setValue("");
    	    	txtboxEditListname.setValue("");
    	     	listboxVarieties.setVisible(true);
    	    	vboxEditVariety.setVisible(false);
    	    	buttonCreate.setVisible(true);
    	    	buttonDelete.setVisible(true);
    	    	buttonSave.setVisible(false);
    	    	buttonCancel.setVisible(false);
    	       	
    	    
    	    	onclickVariety();
    			
    		}
    		else {
    			Messagebox.show("Failed to add list","OPERATION FAILED",Messagebox.OK, Messagebox.EXCLAMATION);
    		}
    	}
    }
    
    private void onbuttonSaveVariety() {
    	
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List listNoMatch = new ArrayList();
    	final Set setMatched = new TreeSet();
    	String lines[] = txtboxEditVariety.getValue().trim().split("\n");
    	for (int i=0;i<lines.length; i++) {
    		
    		Variety var = null;
    		String varstr=lines[i].trim().toUpperCase();
    		if(varstr.isEmpty()) continue;
    		try {
	    		var = variety.getGermplasmByName(varstr);
	    		if(var==null)
	    			var = variety.getGermplasmByIrisId(varstr);
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
			return;
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
				                    	
				                    }
								}
	    				});
	    				
    		} else
    		{
    			Messagebox.show("No identified varieties","WARNING",Messagebox.OK,Messagebox.EXCLAMATION);
    			return;
    			
    		}
    		
    	} else {
    		addVarlist(setMatched);
    	}
    	
    	
    }
    
    @Listen("onClick =#buttonDelete")
    public void onbuttonDelete() {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	workspace.deleteVarietyList( listboxVarietylist.getSelectedItem().getLabel() );
//    	listboxVarietylist.setSelectedIndex(0);
//    	Executions.sendRedirect("_workspace.zul");
    	
    	List listVarlistNames=new ArrayList();
    	listVarlistNames.addAll( workspace.getVarietylistNames( ));
    	listboxVarietylist.setModel( new SimpleListModel(listVarlistNames));
    	listboxVarietylist.setSelectedIndex(0);
    	
    	//AppContext.debug( listboxVarietylist.getSelectedItem().getLabel() + "  selected");
    	
    	//Events.postEvent( "onSelect", listboxVarietylist, null);

    	
    }
    

    
    
}
