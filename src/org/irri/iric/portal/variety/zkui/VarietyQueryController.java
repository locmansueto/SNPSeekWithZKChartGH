package org.irri.iric.portal.variety.zkui;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.admin.zkui.InputMessageBox;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.CvTermUniqueValues;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyImpl;
import org.irri.iric.portal.domain.VarietyPlus;
import org.irri.iric.portal.domain.VarietyPlusPlus;
import org.irri.iric.portal.domain.VarietyPlusPlusImpl;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.variety.service.Data;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Splitter;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.chart.Charts;
import org.zkoss.chart.ChartsEvent;
import org.zkoss.chart.Legend;
import org.zkoss.chart.Marker;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.chart.XAxis;
import org.zkoss.chart.model.DefaultXYModel;
import org.zkoss.chart.model.XYModel;
import org.zkoss.chart.plotOptions.ScatterPlotOptions;


@Controller
@Scope("session")
public class VarietyQueryController extends SelectorComposer<Component>  {

	
    private static final long serialVersionUID = 1L;
    
    private java.util.List<Variety> varsresult;
    
    private boolean isdonePhylo=false;
    private boolean isdoneMDS=false;
    private boolean istreebrowser=true;
    
    private List<ListboxPhenotype> listPhenotypeListbox= new ArrayList();
    private int phenotypevalue_type= ListItemsDAO.PHENOTYPETYPE_NONE;
    
    
    @Wire
    private Window winVariety;
    
	//private Window winUserInput;
	private String strUserInput;
	
    @Autowired
    private WorkspaceFacade workspace;
    
    @Wire
    private Combobox comboVarname;
    
    @Wire
    private Combobox comboIrisId;

    @Wire
    private Combobox comboCountry;
    
    @Wire
    //private Combobox comboSubpopulation;
    private Listbox listboxSubpopulation;
    
    @Wire
    private Textbox msgbox;
    
    @Wire
    private Button resetButton;

    @Wire
    private Button searchButton;
    
    @Wire
    private Button showallButton;
    
    @Autowired
    @Qualifier("VarietyFacade")
    private VarietyFacade  variety;
    
    @Wire 
    private Listbox varietyresult;
    
    @Wire
    private Tabbox tabboxDisplay;
    @Wire
    private Tab tabPhylo;
    
    @Wire
    private Tab tabMDS;
    
    @Wire
    private Charts chartMDS;
    
    @Wire
    private Charts chartMDSNeighbors;
    
    @Wire
    private Tab tabTable;
    
    @Wire
    private Vbox hboxQuery;
    
    @Wire
    private Checkbox checkboxShowMDSLabel;
    
    @Wire
    private Grid gridGermplasm;
    @Wire
    private Textbox textboxGermAccession;
   
    @Wire
    private Textbox textboxGermSubpopulation;
    @Wire
    private Textbox textboxGermCountry;
    
    @Wire
    private Listbox listboxGermPhenotypes;	
    @Wire
    private Textbox textboxIRISId;
    
    @Wire 
    private Splitter splitter;
    
    @Wire
    private Listbox listboxPassport;
    @Wire
    private Listbox listboxPassportValue;
    
    @Wire
    private Listbox listboxPhenotypes;

    @Wire
    private Listbox listboxPhenComparator;
    @Wire
    private Listbox listboxPhenValue;
    
    @Wire
    private Vbox vboxPhenConstraints;
    @Wire
    private Button buttonAddPhenConstraint;
    
    @Wire
    private Vbox vboxPassportConstraints;
    
    @Wire
    private Listbox listboxGermPassport;
    
    @Wire
    private Iframe iframePhylotree;

    @Wire
    private Button buttonCheckAll;
    @Wire
    private Button buttonUncheckAll;
    @Wire
    private Button buttonAddToList;
    
    @Wire
    private Textbox txtboxListname;
    
    @Wire
    private Hbox hboxAddtolist;
    
    
    @Wire
    private Listbox listboxMyVariety;
    
    @Wire
    private Button buttonDownloadCSV;
    @Wire
    private Button buttonDownloadTab;
    
    
    @Wire
    private Button buttonDownloadFastq;
    @Wire
    private Button buttonDownloadBAM;
    @Wire
    private Button buttonDownloadVCF;
    
    /*
    @Listen("onClick = #buttonDownloadFastq")
    public void clickDownloadFastQ() {
    	buttonDownloadVCF.sett
    }
    @Listen("onClick = #buttonDownloadBAM")
    public void clickDownloadBAM() {
    	
    }
    @Listen("onClick = #buttonDownloadVCF")
    public void clickDownloadVCF() {
    }
    */
    
    
    @Listen("onClick = #buttonDownloadCSV")
    public void clickDownloadCSV() {
    	downloadVarieties("varieties-" + AppContext.createTempFilename()  + ".csv", ",");
    }
    
    @Listen("onClick = #buttonDownloadTab")
    public void clickDownloadTab() {
    	downloadVarieties("varieties-" +  AppContext.createTempFilename() + ".txt", "\t");
    }
    
    private void downloadVarieties(String filename, String delimiter ) {
    	
    	StringBuffer buff = new StringBuffer();
    	
    	
    	Listhead lhd= varietyresult.getListhead();
    	Iterator itHeader = lhd.getChildren().iterator();
		while(itHeader.hasNext())
		{
			Listheader lh = (Listheader)itHeader.next();
			buff.append( lh.getLabel() );
			if(itHeader.hasNext()) buff.append(delimiter);
		}
		buff.append("\n");
    	
     	Iterator<Variety> itvar =  varsresult.iterator();
     
    	while(itvar.hasNext()) {
    		buff.append(  itvar.next().printFields(delimiter));
    		if(itvar.hasNext()) buff.append("\n");
    	}
   	
//    		String filetype = "text/plain";
//			if(delimiter.equals(",")) filetype="text/csv";
//			
//			try {
//				Filedownload.save(  buff.toString(), filetype, filename);
//				AppContext.debug("Varieties write complete! Saved to: "+ filename);
//				} catch(Exception ex)
//				{
//					ex.printStackTrace();
//				}

     	
    	try {
    		String filetype = "text/plain";
			if(delimiter.equals(",")) filetype="text/csv";
				
			File file = new File(AppContext.getTempDir() + filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(buff.toString());
			bw.flush();
			bw.close();
			
			org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
			AppContext.debug("Variety write complete!"+ file.getAbsolutePath() +  " Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
			
			try {
				Filedownload.save(  new File(AppContext.getTempDir() + filename), filetype);
				} catch(Exception ex)
				{
					ex.printStackTrace();
				}

		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }
    
    @Listen("onClick = #buttonCheckAll")
    public void markAllVarieties() {
    	varietyresult.setSelectedItems(new HashSet(varietyresult.getItems()) );
    }
    
    @Listen("onClick = #buttonUncheckAll")
    public void unmarkAllVarieties() {
    	varietyresult.setSelectedItems(new HashSet());
    };
    
    
    @Listen("onClick = #buttonAddToList")
    public void addToList() {
    	
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	if(varietyresult.getItems().size()==0) {
    		Messagebox.show( "EMPTY VARIETY LIST");
    		return;
    	}
    	if(txtboxListname.getValue().isEmpty()) {
    		Messagebox.show( "PROVIDE LIST NAME");
    		return;
    	}
    	
    	Set setVarieties = new LinkedHashSet();
    	
    	//varietyresult.getModel()
    	
    	//Iterator<Listitem> itItems= varietyresult.getItems().iterator();

    	/*
    	Iterator<Variety> itItems= varsresult.iterator();
    	while(itItems.hasNext()) {
    		setVarieties.add(  (Variety)itItems.next().getValue()) ;
    	}
    	*/
    	
    	workspace.addVarietyList( txtboxListname.getValue().trim(), new LinkedHashSet(varsresult) );
    	txtboxListname.setValue("");
    }
    
//    public void addToList2() {
//    	
//    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
//    	
//    	if(varietyresult.getSelectedItems().size()>0) {
//    	
//    		strUserInput="";
//    		/*
//    		while(strUserInput.isEmpty()) {
//    		
//    			final Window win;
//		    	final Window dialog = (Window) Executions.createComponents("window.zul", winVariety  , null);
//	    		dialog.doModal();
//	    		Button okBtn = (Button) dialog.getFellow("okBtn");
//	    		okBtn.addEventListener("onClick", new EventListener() {
//	    			@Override
//	    			public void onEvent(Event event) throws Exception {
//	    				Textbox tb = (Textbox) dialog.getFellow("tb");
//	    				strUserInput = tb.getValue();
//	    				dialog.detach();
//	    			}
//	    		});
//    		}
//    		*/
//    		
//    		try {
//    		String object = (String)InputMessageBox.showQuestion("MY QUESTION?", "MY APPLICATION TITLE");
//    		strUserInput = object;
//    		AppContext.debug("RESULT: " + object);
//    		} catch(Exception ex) {
//    			ex.printStackTrace();
//    			AppContext.debug(ex.getMessage());
//    		}
//    		
//    		
//		} else return;
//    	
//    	Set setVarieties = new LinkedHashSet();
//    	Iterator<Listitem> itItems= varietyresult.getSelectedItems().iterator();
//    	
//    	while(itItems.hasNext()) {
//    		setVarieties.add(  (Variety)itItems.next().getValue()) ;
//    	}
//    	workspace.addVarietyList( strUserInput, setVarieties);
//    }
    
    
    @Listen("onClick =#buttonAddPhenConstraint")
    public void buttonAddPhenConstraint() {
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
         
    	
    	Hbox hboxPhen = new Hbox();
    	
    	Listbox listboxPhenValues = new Listbox();
    	listboxPhenValues.setMold("select");
    	listboxPhenValues.setRows(1);
    	
    	List listQuanOperator = new ArrayList();
        listQuanOperator.add(VarietyFacade.COMPARATOR_EQUALS); listQuanOperator.add(VarietyFacade.COMPARATOR_LESSTHAN);listQuanOperator.add(VarietyFacade.COMPARATOR_GREATERTHAN);
        
        Listbox listboxComparator = new Listbox();
        listboxComparator.setMold("select");
        listboxComparator.setWidth("40px");
        listboxComparator.setRows(1);
        listboxComparator.setModel(new SimpleListModel(listQuanOperator));
        listboxComparator.setSelectedIndex(0);
         
    	
        java.util.List listPhenotype = new java.util.ArrayList();
        listPhenotype.addAll( variety.getPhenotypeDefinitions().keySet());
        
    	Listbox listboxMorePhenConstraint= new ListboxPhenotype(listboxPhenValues, variety, listboxComparator);
        
    	listboxMorePhenConstraint.setRows(1);
    	listboxMorePhenConstraint.setMold("select");
    	listboxMorePhenConstraint.setWidth("300px");
    	
        listboxMorePhenConstraint.setModel( new SimpleListModel(listPhenotype));
       
        hboxPhen.appendChild(listboxMorePhenConstraint);
        hboxPhen.appendChild(listboxComparator);
        hboxPhen.appendChild(listboxPhenValues);
        
        hboxPhen.appendChild(vboxPhenConstraints.getLastChild().getLastChild());
        
        listPhenotypeListbox.add( (ListboxPhenotype)listboxMorePhenConstraint);
        vboxPhenConstraints.appendChild( hboxPhen );
    }
    
    
    @Listen("onClick =#buttonAddPassConstraint")
    public void buttonAddPassConstraint() {
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
         
    	Hbox hboxPass = new Hbox();
    	
    	Listbox listboxPassValues = new Listbox();
    	listboxPassValues.setMold("select");
    	listboxPassValues.setRows(1);
    	
        java.util.List listPassport = new java.util.ArrayList();
        listPassport.addAll( variety.getPassportDefinitions().keySet());
    	Listbox listboxMorePassConstraint= new ListboxPassport(listboxPassValues, variety );
    	listboxMorePassConstraint.setRows(1);
    	listboxMorePassConstraint.setMold("select");
    	listboxMorePassConstraint.setWidth("300px");
    	
    	listboxMorePassConstraint.setModel( new SimpleListModel(listPassport));
       
        hboxPass.appendChild(listboxMorePassConstraint);
        
        Label eqlabel = new Label();
        eqlabel.setValue(" = ");
        eqlabel.setPre(true);
        hboxPass.appendChild(eqlabel);
        hboxPass.appendChild(listboxPassValues);
        
        hboxPass.appendChild(vboxPassportConstraints.getLastChild().getLastChild());
        
        vboxPassportConstraints.appendChild( hboxPass );
    }
    
    private Set getVariety(String varstrs) {
    	Set varset = new LinkedHashSet();
    	String varstrarr[] = varstrs.split(",");
    	for(int ivar = 0; ivar<  varstrarr.length ; ivar++ ) {
    		String varstr = varstrarr[ivar];
    		try {
				Variety var = variety.getGermplasmByName(varstr);
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
				if(var!=null) 
					varset.add(var);
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	return varset;
	}
    
    private void show_varlist(Set varset) {
    	List newvarlist = new ArrayList();
    	newvarlist.addAll(varset);
    	varsresult = newvarlist;
    	displayResults(false);
    	tabTable.setSelected(true);
    	tabTable.setVisible(true);
    	tabPhylo.setVisible(true);
    	tabMDS.setVisible(false);
    	
    	istreebrowser=true;
    	//iframePhylotree.setSrc("treeBrowser3k.htm");

    }
    
    
    @Listen("onUser = #winVariety")
    //public void onUser$info(Event event){
    public void onUser(Event event){
    	String eventParam  = event.getData().toString();
        //ForwardEvent eventx = (ForwardEvent)event;
        //String eventParam = eventx.getOrigin().getData().toString(); 
        //AppContext.debug( eventParam ); 
        
        //Messagebox.show(eventParam);
        
        variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
        
        
        String[] names = eventParam.split("\\&"); 
        if(names.length>1) {

        	Set varsSelected = getVariety(names[0].replace("irisid=","").replace("name=","").replace("_", " "));
     		varsSelected.addAll( getVariety(names[1].replace("irisid=","").replace("name=","").replace("_", " ")));
     		
     		show_varlist(varsSelected);
        	
        }
        else {	
        	
        	//String names2[] = names[0].split(",");
	        String irisidOrName = eventParam.replace("_"," ");
	        
	        Variety varselected=null; 
	        if(irisidOrName.startsWith("irisid=")) {
	        	String irisids[] = irisidOrName.replace("irisid=","").split(",");
	        	if(irisids.length>1) {
	        		show_varlist( getVariety(irisidOrName.replace("irisid=","")) );
	        	}
	        	else {
	        		varselected = variety.getGermplasmByIrisId(irisids[0]);
	        		show_passport(varselected);
	        	}
	        	
	        }
	        else if(irisidOrName.startsWith("name=")) {
	        	String varnames[] = irisidOrName.replace("name=","").split(",");
	        	if(varnames.length>1) {
	        		show_varlist( getVariety(irisidOrName.replace("name=","")) );
	        	}
	        	else {
	        		varselected =  variety.getGermplasmByName(varnames[0]);
	        		show_passport(varselected);
	        	}
	        }
	        
	
	        //if(varselected==null) throw new RuntimeException(irisidOrName+ " not found");
	        
	      
	        //Event eventx = Events.getRealOrigin((ForwardEvent)event);
	        //AppContext.debug(eventx.getData());
        }
    }
    
    /**
     * When a row in variety result list is selected, show passport and phenotype data
     */
    @Listen("onSelect = #varietyresult")
    public void selectVariety() {
    	
    	Listitem selItem =  varietyresult.getSelectedItem();
    	
    	Listcell lc = (Listcell)selItem.getChildren().get(0);
    	Label lb = (Label)lc.getChildren().get(0);
    	String varname = lb.getValue();
    	
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
    	Variety selVar = variety.getGermplasmByName(varname);
    	show_passport(selVar);
    	

    }
    
    
    /**
     * Phylogenetic tree tab is clicked
     */
    @Listen("onSelect = #tabPhylo")    
    public void onselectTabPhylo() {

    	if(isdonePhylo) return;
    	
    	if(istreebrowser) {
    		iframePhylotree.setSrc("treeBrowser3k.htm");
    		isdonePhylo=true;
    		return;
    	}

    	//AppContext.debug("loading phylotree " + urlphylo);
    	Clients.showBusy("Computing Phylogenetic Tree");
    	gridGermplasm.setVisible(false);
    	
		if(varsresult.size()>2)
		{
			show_phylotree(varsresult); 
		} 
		
    	Clients.clearBusy();
    	isdonePhylo=true;
    }
    
        
    @Listen("onSelect = #tabMDS")    
    public void onselectTabMDS() {

    	if(isdoneMDS) return;
    	
    	//AppContext.debug("loading phylotree " + urlphylo);
    	Clients.showBusy("Computing MDS plot");
    	gridGermplasm.setVisible(false);
    	
		if(varsresult.size()>2)
		{
			// show_phylotree(varsresult); 
			Iterator<Variety> listIds = varsresult.iterator();
			List<BigDecimal> ids = new ArrayList();
			StringBuffer varids = new StringBuffer();
			while(listIds.hasNext()) {
				BigDecimal id = listIds.next().getVarietyId() ; 
				varids.append( id );
				ids.add( id );
				if(listIds.hasNext()) varids.append(",");
			}
			
			plotXY(chartMDS, variety.constructMDSPlot( ids , "1", false), "Varieties MDS Plot", varids.toString(), null );
		} 
    	
    	//variety.constructMDSPlot(ids, scale)
    	
    	Clients.clearBusy();
    	isdoneMDS=true;
    }
    
    public void showMDSNeighbors() {

    	chartMDSNeighbors.setModel(new DefaultXYModel());
    	showmds_allvars(chartMDSNeighbors, textboxGermAccession.getValue());
    	
    } 
    
    @Listen("onClick = #resetButton")   
    public void reset()
    {
    	comboVarname.setValue("");
    	comboIrisId.setValue("");
    	comboCountry.setValue("");
    	//comboSubpopulation.setValue("");
    	listboxSubpopulation.setSelectedIndex(0);
    	
    	listboxPhenotypes.setSelectedIndex(0);
    	listboxPhenValue.setModel(new SimpleListModel(new ArrayList()));
    	listboxPhenComparator.setSelectedIndex(0);
    	
    	Component hboxes = vboxPhenConstraints.getFirstChild().getNextSibling();
    	List listRemove = new ArrayList();
    	while(hboxes!=null) {
    		listRemove.add(hboxes);
    		hboxes=hboxes.getNextSibling();
    	}
    	if(vboxPhenConstraints.getChildren().size()>1)
    		vboxPhenConstraints.getFirstChild().appendChild( vboxPhenConstraints.getLastChild().getLastChild() );
    	
    	Iterator<Component> itRemove=listRemove.iterator();
    	while(itRemove.hasNext()) {
    		vboxPhenConstraints.removeChild( itRemove.next() );
    	}
    	
    	listboxPassport.setSelectedIndex(0);
    	listboxPassportValue.setModel(new SimpleListModel(new ArrayList()));
    	
    	hboxes = vboxPassportConstraints.getFirstChild().getNextSibling();
    	listRemove = new ArrayList();
    	while(hboxes!=null) {
    		listRemove.add(hboxes);
    		hboxes=hboxes.getNextSibling();
    	}
    	if(vboxPassportConstraints.getChildren().size()>1)
    		vboxPassportConstraints.getFirstChild().appendChild( vboxPassportConstraints.getLastChild().getLastChild() );

    	itRemove=listRemove.iterator();
    	while(itRemove.hasNext()) {
    		vboxPassportConstraints.removeChild( itRemove.next() );
    	}
    	
    	
    }
    
    @Listen("onClick = #showallButton")   
    public void showAll()
    {
    	reset();
    	tabTable.setSelected(true);
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
		
		varsresult = new java.util.ArrayList<Variety>();
		varsresult.addAll( variety.getGermplasm() );
		
		msgbox.setValue( "ALL RESULT: " + varsresult.size() + " rows" );
		
    	displayResults(true);
    }
    
    /**
     * Search button is clicked
     * 1. check if inputs are valid
     * 2. if variety name or iris id are set, show passport and phenotype
     *    else query using values for country and subpopulation
     * 3. display result in table   
     * 4. if result is more than 2, generate phylogenetic tree
     */
	@Listen("onClick = #searchButton")    
	public void searchList3k()    {
		
		variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
		
		tabTable.setSelected(true);
		
		varsresult = new java.util.ArrayList<Variety>();
		
		if(comboVarname.getValue()!=null &&  !comboVarname.getValue().isEmpty() && comboIrisId.getValue()!=null &&  !comboIrisId.getValue().isEmpty())
		{
			//msgbox.setValue( "INVALID INPUT: Only one of either Variety name or IRIS ID can be specified" );
			//msgbox.setStyle( "font-color:red" );
			Messagebox.show( "INVALID INPUT: Only one of either Variety name or IRIS ID can be specified" );
			return;
		}
		
		if(comboVarname.getValue()!=null &&  !comboVarname.getValue().isEmpty() ) {
			
			Variety varQuery = variety.getGermplasmByName( comboVarname.getValue() ); 
			if(varQuery==null) {
				Messagebox.show( "VARIETY NOT FOUND: " +  comboVarname.getValue().toUpperCase());
				return;
			}
			msgbox.setValue(comboVarname.getValue().toUpperCase() +  " PASSPORT DATA"); 
			show_passport( varQuery );
			tabboxDisplay.setVisible(false);
			gridGermplasm.setVisible(true);

		} else if (comboIrisId.getValue()!=null &&  !comboIrisId.getValue().isEmpty() ) 
		{
			Variety varQuery = variety.getGermplasmByIrisId( comboIrisId.getValue() );
			if(varQuery==null) {
				Messagebox.show( "VARIETY NOT FOUND: " + comboIrisId.getValue().toUpperCase() );
				return;
			}

			msgbox.setValue(comboIrisId.getValue().toUpperCase() +  " PASSPORT DATA"); 
			show_passport( varQuery );
			tabboxDisplay.setVisible(false);
			gridGermplasm.setVisible(true);
			
		}
		else {
			
			//if( (comboCountry==null || comboCountry.getValue().isEmpty()) && (comboSubpopulation==null || comboSubpopulation.getValue().isEmpty())
			if( (comboCountry==null || comboCountry.getValue().isEmpty()) && (listboxSubpopulation==null || listboxSubpopulation.getSelectedIndex()==0)
				&& (listboxPassport.getSelectedItem()==null || listboxPassport.getSelectedItem().getLabel().trim().isEmpty()) 
				&& (listboxPhenotypes.getSelectedItem()==null || listboxPhenotypes.getSelectedItem().getLabel().trim().isEmpty()) 
				//&&	(listboxMyVariety.getSelectedItem()==null || listboxMyVariety.getSelectedItem().getLabel().trim().isEmpty() )		
			    )	
			{
				//msgbox.setValue( "INVALID INPUT: Provide at least one constraint" );
				//msgbox.setStyle( "font-color:red" );
				Messagebox.show( "INVALID INPUT: Provide at least one constraint" );
				return;				
			}
			
			gridGermplasm.setVisible(false);
			tabboxDisplay.setVisible(true);
			varietyresult.setVisible(true);
			
			
			
			//if( (comboCountry==null || comboCountry.getValue().isEmpty()) && (comboSubpopulation==null || comboSubpopulation.getValue().isEmpty()) ) {}
			if( (comboCountry==null || comboCountry.getValue().isEmpty()) && (listboxSubpopulation==null || listboxSubpopulation.getSelectedIndex()<1 ) ) {}
			else {
						
				//Variety example = new Variety();
				Variety example = new VarietyImpl();
				
				StringBuffer msg= new StringBuffer();
				if(comboCountry==null) throw new RuntimeException("comboCountry==null");
				if(comboCountry.getValue()==null) throw new RuntimeException("comboCountry.getValue()==null");
				
				if(comboCountry.getValue()!=null && !comboCountry.getValue().isEmpty() ) {
					example.setCountry(  comboCountry.getValue());
					msg.append(" COUNTRY=" + comboCountry.getValue().toUpperCase());
				}
		
				/*
				if(comboSubpopulation==null) throw new RuntimeException("comboSubpopulation==null");
				if(comboSubpopulation.getValue()!=null && !comboSubpopulation.getValue().isEmpty() )	{	
					example.setSubpopulation( comboSubpopulation.getValue());
					if(msg.length()>0) msg.append(" AND ");
						
					msg.append(" SUBPOPULATION=" + comboSubpopulation.getValue().toUpperCase());
				}
				*/
				if(listboxSubpopulation==null) throw new RuntimeException("comboSubpopulation==null");
				if(listboxSubpopulation.getSelectedIndex()>0)	{	
					example.setSubpopulation( listboxSubpopulation.getSelectedItem().getLabel());
					if(msg.length()>0) msg.append(" AND ");
						
					msg.append(" SUBPOPULATION=" + listboxSubpopulation.getSelectedItem().getLabel().toUpperCase());
				}

				
				
				msgbox.setValue("VARIETY WHERE " + msg.toString());
						
				if(variety==null) throw new RuntimeException("variety==null");
				if(example==null) throw new RuntimeException("example==null");
				if(varsresult==null) throw new RuntimeException("varsresult==null");
				
				
				varsresult.addAll(  variety.getGermplasmByExample( example )  );
			}
			
			List listExtraheader = new ArrayList();

			
			Component hbchildPassConstraint = vboxPassportConstraints.getFirstChild();
			while(hbchildPassConstraint!=null) 
			{
				Listbox  hblistboxPassport = (Listbox)hbchildPassConstraint.getFirstChild();
				Listbox  hblistboxPassValue =  (Listbox)hblistboxPassport.getNextSibling().getNextSibling();
				
				
				
				if(hblistboxPassport.getSelectedItem()!=null && !hblistboxPassport.getSelectedItem().getLabel().trim().isEmpty()) {
					String definition = hblistboxPassport.getSelectedItem().getLabel().trim();
					List<VarietyPlus> listVarsByPassport = variety.getVarietyByPassport(definition, 
							hblistboxPassValue.getSelectedItem().getLabel().trim());
					
					
					varsresult = intersectVarietyListPlus(varsresult, listVarsByPassport, definition );
					listExtraheader.add( definition );
					String msg =  msgbox.getValue().trim();
					if( !msg.isEmpty() )
						msg+=",";
					
					msgbox.setValue( msg +definition + "=" + 
							hblistboxPassValue.getSelectedItem().getLabel());
				}
				hbchildPassConstraint = hbchildPassConstraint.getNextSibling();
			}
			
			
			Component hbchildPhenConstraint = vboxPhenConstraints.getFirstChild();
			int listcount = 0;
			while(hbchildPhenConstraint!=null) 
			{
				Listbox  hblistboxPhenotypes = (Listbox)hbchildPhenConstraint.getFirstChild();
				Listbox  hblistboxPhenComparator = (Listbox)hblistboxPhenotypes.getNextSibling();
				Listbox  hblistboxPhenValue =  (Listbox)hblistboxPhenComparator.getNextSibling();
				
				
				
				if(hblistboxPhenotypes.getSelectedItem()!=null && !hblistboxPhenotypes.getSelectedItem().getLabel().trim().isEmpty()) {
					String definition = hblistboxPhenotypes.getSelectedItem().getLabel().trim();
					
					int phenotypetype;
					if(listcount==0)
						phenotypetype = phenotypevalue_type;
					else 
						phenotypetype = listPhenotypeListbox.get(listcount-1).getPhenotype_type();
					
					List<VarietyPlus> listVarsByPhenotype = variety.getVarietyByPhenotype(definition, 
							hblistboxPhenComparator.getSelectedItem().getLabel(),	
							hblistboxPhenValue.getSelectedItem().getLabel().trim(), phenotypetype );
					
					
					varsresult = intersectVarietyListPlus(varsresult, listVarsByPhenotype, definition );
					listExtraheader.add( definition );
					String msg =  msgbox.getValue().trim();
					if( !msg.isEmpty() )
						msg+=",";
					
					msgbox.setValue( msg +definition + hblistboxPhenComparator.getSelectedItem().getLabel() + 
							hblistboxPhenValue.getSelectedItem().getLabel());
				}
				hbchildPhenConstraint = hbchildPhenConstraint.getNextSibling();
				listcount++;
			}
			
			if(listboxMyVariety.getSelectedItem()==null || listboxMyVariety.getSelectedItem().getLabel().trim().isEmpty()) {}
			else {
				String msg =  msgbox.getValue().trim();
				if( !msg.isEmpty() )
					msg+=", AND VARIETY IN LIST " + listboxMyVariety.getSelectedItem().getLabel().trim();
				msgbox.setValue(msg);
				
				List newlist = new ArrayList();
				Iterator<Variety> itVar = varsresult.iterator();
				
				workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
				Set setVarlist = workspace.getVarieties( listboxMyVariety.getSelectedItem().getLabel().trim() );
				
				varsresult.retainAll(setVarlist);
			}
			
			msgbox.setValue( msgbox.getValue() + " ... RESULT: " + varsresult.size() + " rows" );
			msgbox.setStyle( "font-color:black" );
			
			displayResults(false, listExtraheader);
		}
		
		isdoneMDS=false;
		isdonePhylo=false;
		istreebrowser=false;
				
	}
	
	private List intersectVarietyListPlus(java.util.Collection<Variety> list1, Collection<VarietyPlus> list2, String valuename) {
		
		List<Variety> listNew = new ArrayList<Variety>();
		
		if(list1.size()==0) {
			//return (List)list2;
		
			Iterator<VarietyPlus> it2Var = list2.iterator();
			while(it2Var.hasNext()) {
				VarietyPlus var2 = it2Var.next();
				VarietyPlusPlus var1 = new VarietyPlusPlusImpl( var2, valuename );
					listNew.add( var1 );
			}				
			return listNew;
		}
		
		
		Map<BigDecimal, Variety> setList1Id=new HashMap();
		Iterator<Variety> itVar = list1.iterator();
		
		boolean list1IsVarietyPlus=false;
		boolean list1IsVarietyPlusPlus=false;

		
		// get vars in list1
		while(itVar.hasNext()) {
			Variety var = itVar.next();
			if(!list1IsVarietyPlus && var instanceof VarietyPlus) {
				list1IsVarietyPlus=true;
				if(!list1IsVarietyPlusPlus && var instanceof VarietyPlusPlus) 
					list1IsVarietyPlusPlus=true;	
			}
			setList1Id.put(var.getVarietyId(), var );
		}

		if(list1IsVarietyPlus) {
			
			if(list1IsVarietyPlusPlus) {
				// add value to map
				Iterator<VarietyPlus> it2Var = list2.iterator();
				while(it2Var.hasNext()) {
					VarietyPlus var2 = it2Var.next();
					if(setList1Id.containsKey(  var2.getVarietyId() )) {
						VarietyPlusPlus var1 = (VarietyPlusPlus)setList1Id.get( var2.getVarietyId()  );
						var1.addValue(valuename, var2.getValue());
						listNew.add( var1 );
					}
				}
				
			} else
			{
				// create a VarietyPlusPlus from var1 and var2
				Iterator<VarietyPlus> it2Var = list2.iterator();
				while(it2Var.hasNext()) {
					VarietyPlus var2 = it2Var.next();
					if(setList1Id.containsKey(  var2.getVarietyId() )) {
						VarietyPlusPlus var1 = new VarietyPlusPlusImpl( (VarietyPlus)setList1Id.get(var2.getVarietyId()) , var2, valuename );
						listNew.add( var1 );
					}
				}			
			}
		}
		else
		{
			// use var2 in return (Return is VarietyPlus list)
			// add vars in list2 only if in list 1
			Iterator<VarietyPlus> it2Var = list2.iterator();
			while(it2Var.hasNext()) {
				VarietyPlus var2 = it2Var.next();
				if(setList1Id.containsKey(  var2.getVarietyId() )) {
					//var2.setValueName(valuename);
					//listNew.add( var2 );
					
					VarietyPlusPlus var1 = new VarietyPlusPlusImpl(setList1Id.get( var2.getVarietyId() ),  var2, valuename );
					listNew.add( var1 );
					
				}
			}
		}
		
		return listNew;
	
	}

	private void displayResults(boolean showAll) {
		displayResults(showAll, null);
		
	}
	
	private void displayResults(boolean showAll, Collection extrafield) {
		
		boolean hasExtraField = (extrafield!=null && !extrafield.isEmpty());
		
		AppContext.debug("Displaying " +  varsresult.size() + " varieties");
		
		// update table header

		Map<String,String> listheaders = new LinkedHashMap();
		listheaders.put("NAME","name"); listheaders.put("IRIS ID","irisId"); listheaders.put("SUBPOPULATION","subpopulation"); listheaders.put("COUNTRY","country");
		
		if(hasExtraField) {
			Iterator<String> itHead=extrafield.iterator();
			while(itHead.hasNext()) {
				String headlabel = itHead.next(); 
				listheaders.put(headlabel,  "" );
			}
		}
		
		
				Listhead lhd= varietyresult.getListhead();
				lhd.getChildren().clear();
				Iterator<String> itHeader = listheaders.keySet().iterator();
				while(itHeader.hasNext())
				{
					Listheader lh = new Listheader();
					
					String headlabel =  itHeader.next();
					lh.setLabel(headlabel);
					
					String field= listheaders.get(headlabel);
					if(!field.isEmpty()) lh.setSort("auto(" + field +")");
					else {
						VarietyPlusPlusComparator compAsc = new VarietyPlusPlusComparator(true, headlabel);
						VarietyPlusPlusComparator compDesc = new VarietyPlusPlusComparator(false, headlabel);
						lh.setSortAscending( compAsc );
						lh.setSortDescending( compDesc );
					}
					
					
					lh.setParent(lhd);
				}

				
				if(varsresult.size()>2)
				{
					tabPhylo.setVisible(true);
					tabMDS.setVisible(true);
				} 
				else {
					tabPhylo.setVisible(false);
					tabMDS.setVisible(false);
				}
				
				
				varietyresult.setModel( new SimpleListModel(varsresult) );
				
				if(varsresult.size()>0)
					hboxAddtolist.setVisible(true);
				else
					hboxAddtolist.setVisible(false);
				
				
				gridGermplasm.setVisible(false);
				tabboxDisplay.setVisible(true);
				
	}
	
	/**
	 * Show passpot and phenotype data
	 * @param variety2
	 */
	private void show_passport(Variety variety2) {
		

	    textboxGermAccession.setValue( variety2.getName());
	    textboxIRISId.setValue( variety2.getIrisId());
		textboxGermCountry.setValue( variety2.getCountry());
		textboxGermSubpopulation.setValue( variety2.getSubpopulation());
		
		
		buttonDownloadBAM.setHref("http://172.29.4.93/cgi-bin/fqget.pl?q=" + variety2.getIrisId().replace("IRIS","").trim() + "&t=bam" );
		buttonDownloadFastq.setHref("http://172.29.4.93/cgi-bin/fqget.pl?q=" + variety2.getIrisId().replace("IRIS","").trim() + "&t=fastq" );
		buttonDownloadVCF.setHref("http://172.29.4.93/cgi-bin/fqget.pl?q=" + variety2.getIrisId().replace("IRIS","").trim() + "&t=vcf" );
		
		java.util.List listPassport = new java.util.ArrayList();
		listPassport.addAll( variety.getPassportByVarietyid( variety2.getVarietyId())  );
		
		listboxGermPassport.setModel(new SimpleListModel( listPassport ));
		
		listboxGermPhenotypes.setModel( new SimpleListModel(variety.getPhenotypesByGermplasm(  variety2 )) );
		
		showMDSNeighbors();
		
		gridGermplasm.setVisible(true);
		splitter.setOpen(true);
		
	}

	/**
	 * Compute phylogenetic tree using jsp/phylotreeGermplasms.jsp
	 * @param varids	list of variety IDs
	 */
	private void show_phylotree(String varids) {
		
			int nvars = varids.split(",").length;
			int height = nvars*21;
			int width = nvars*30;
			
			int treescale = 1;
			
			
			iframePhylotree.setStyle("height:" + Integer.toString(height) + "px;width:1500px");
			iframePhylotree.setScrolling("yes");
			
			iframePhylotree.setVisible(true);
			String url = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&varid=" + varids + "&requestid="  + (HttpSession)Sessions.getCurrent().getNativeSession();
			AppContext.debug( url );
			iframePhylotree.setSrc( url );
	
	}
	
	private void show_phylotree(List varsresult1) {
		StringBuffer varids = new StringBuffer();
		java.util.Iterator<Variety> itvars = varsresult1.iterator();
		while(itvars.hasNext()) {
			varids.append(  itvars.next().getVarietyId()  );
			if(itvars.hasNext()) varids.append(",");
		}
		show_phylotree(varids.toString()); 
	}
	
	
	   /**
     * Show MDS for all varieties
     */
	
	@Listen("onShowAllMDS = #winVariety")
    public void showmds_allvars() {
    	showmds_allvars(chartMDS); 
    }
    

	private void showmds_allvars(Charts mdsChart) {
		showmds_allvars(mdsChart, null);
	}
	
    private void showmds_allvars(Charts mdsChart, String centerName) {
    	List listIds = new ArrayList(); 
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
    	  
    	listIds.addAll( variety.getMapId2Variety().keySet() );
    	
		StringBuffer varids = new StringBuffer();
		java.util.Iterator<Variety> itvars = listIds.iterator();
		while(itvars.hasNext()) {
			varids.append(  itvars.next() );
			if(itvars.hasNext()) varids.append(",");
		}   	
    	plotXY(mdsChart, variety.constructMDSPlot( listIds , "1", true), "Varieties MDS Plot", varids.toString(), centerName );
    }

    
	
	@Listen("onPlotClick = #checkboxShowMDSLabel")
	private void clickShowMDSLabel() {
		ScatterPlotOptions plotOptions = chartMDS.getPlotOptions().getScatter();
		plotOptions.getDataLabels().setEnabled( checkboxShowMDSLabel.isChecked() );
		
	}
	
	
	private void plotXY(Charts mdsChart, double xy[][], String title, String varids, String centerName) {
	
		mdsChart.setModel(new DefaultXYModel());
				
		ScatterPlotOptions plotOptions = mdsChart.getPlotOptions().getScatter();
        Marker marker = plotOptions.getMarker();
        marker.setRadius(4);
        marker.getStates().getHover().setEnabled(true);
        marker.getStates().getHover().setLineColor("rgb(100,100,100)");
        plotOptions.getStates().getHover().getMarker().setEnabled(false);
        
        //plotOptions.getTooltip().setValueDecimals(3);
        
       
        
        plotOptions.getTooltip().setHeaderFormat("");
        plotOptions.getTooltip().setPointFormat("{point.name}<br>{point.x},{point.y}");
        
        plotOptions.getDataLabels().setFormat("{point.name}");
        plotOptions.getDataLabels().setEnabled(false);
        
        int n= xy[0].length;          
        String varid[] = varids.trim().split(",");
        Map<BigDecimal,Variety> mapId2Var = variety.getMapId2Variety();
        
        Map<String,Series> mapSubpop2Series = new HashMap();
        
        //Series series1 = chartMDS.getSeries();
        //series1.setName("None");
        //mapSubpop2Series.put("None", series1);
            
        boolean hasHighlight = false;
        if(centerName!=null && !centerName.isEmpty()) hasHighlight=true;
        	
        Point highlightedpoint = null;
        
        for (int i=0; i<n; i++) {
        	
        	Variety var = mapId2Var.get( BigDecimal.valueOf(Long.valueOf(varid[i]) ) );
        	
        	Series series4var;
        	if(var.getSubpopulation()==null || var.getSubpopulation().isEmpty()){
        		series4var=mapSubpop2Series.get("None");
        		if(series4var==null) {
        			series4var = mdsChart.getSeries( mapSubpop2Series.keySet().size() ); 
        			series4var.setName( "none" );
        			series4var.setColor(Data.getSubpopulationColor("none"));
        			mapSubpop2Series.put("none" , series4var);
        		}
        	}
        	else
        	{
        		if(mapSubpop2Series.containsKey(var.getSubpopulation() ))
        			series4var = mapSubpop2Series.get(var.getSubpopulation() );
        		else {
        			series4var = mdsChart.getSeries( mapSubpop2Series.keySet().size() ); 
        			series4var.setName( var.getSubpopulation()  );
         			series4var.setColor(Data.getSubpopulationColor( var.getSubpopulation() ));
         		    mapSubpop2Series.put( var.getSubpopulation() , series4var);
        		}
        	}
        	
        	if(hasHighlight && centerName.toUpperCase().equals(var.getName().toUpperCase())) {
        		series4var.addPoint(var.getName(), Double.valueOf(String.format( "%.6f",xy[1][i])) , Double.valueOf(String.format( "%.6f",xy[0][i])) );
        		Point centerPoint = series4var.getPoint( series4var.getData().size() - 1);
        		highlightedpoint = centerPoint;
        		hasHighlight = false;
        	}
        	else
        		series4var.addPoint(var.getName(), Double.valueOf(String.format( "%.6f",xy[1][i])) , Double.valueOf(String.format( "%.6f",xy[0][i])) );
        }
        
        if(highlightedpoint!=null)
        {
        	
    		Marker ptmarker = highlightedpoint.getMarker();
    		ptmarker.setRadius(  ptmarker.getRadius().intValue()*3 );
    		ptmarker.getLineColor().setColor("red");
    		ptmarker.setLineWidth(  ptmarker.getLineWidth().intValue()*3  );
    		highlightedpoint.setMarker(ptmarker);
    		
//    		ptmarker.setLineColor( RED );
//    		highlightedpoint.setMarker( ptmarker );
    		
    		mdsChart.getXAxis().setMax( highlightedpoint.getX().doubleValue() + 0.005  );
    		mdsChart.getXAxis().setMin( highlightedpoint.getX().doubleValue() - 0.005  );
    		mdsChart.getYAxis().setMax( highlightedpoint.getY().doubleValue() + 0.005  );
    		mdsChart.getYAxis().setMin( highlightedpoint.getY().doubleValue() - 0.005  );
    		//highlightedpoint.setSelected(true);
        	
        }
        
	}
	
	@Listen("onPlotClick = #chartMDS")
    public void showVarietyFromMDS(ChartsEvent event) {
        // Open an invisible popup at where the point clicked. 
		Point point = event.getPoint();
		Variety varselected = variety.getGermplasmByName(point.getName());
		show_passport(varselected);
	}
	
	@Listen("onPlotClick = #chartMDSNeighbors")
    public void showVarietyFromMDSNeighbors(ChartsEvent event) {
        // Open an invisible popup at where the point clicked. 
		Point point = event.getPoint();
		Variety varselected = variety.getGermplasmByName(point.getName());
		show_passport(varselected);
	}

	
	@Listen("onSelect = #listboxPassport")
	public void setPassportConstraint() {
		
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
	  
		AppContext.debug("passport selected:" + listboxPassport.getSelectedItem().getLabel());
		
		List listValues = new java.util.ArrayList();
		
		if(!listboxPassport.getSelectedItem().getLabel().trim().isEmpty())
		{
			Iterator<CvTermUniqueValues> itValues = variety.getPassportUniqueValues( listboxPassport.getSelectedItem().getLabel() ).iterator();
			while(itValues.hasNext()) {
				CvTermUniqueValues value= itValues.next();
				if(value==null) continue;
				listValues.add(value.getValue());
			}
		}
		
		listboxPassportValue.setModel(new SimpleListModel(listValues ));
		if(listValues.size()>0)
			listboxPassportValue.setSelectedIndex(0);
	}
	
	
	@Listen("onSelect = #listboxPhenotypes")
	public void setPhenotypeConstraint() {
		
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
	  
		
		List listValues = new java.util.ArrayList();
		
		AppContext.debug("phenotype selected:" + listboxPhenotypes.getSelectedItem().getLabel());
		
		if(!listboxPhenotypes.getSelectedItem().getLabel().trim().isEmpty())
		{
			Object retobj[] = variety.getPhenotypeUniqueValues( listboxPhenotypes.getSelectedItem().getLabel() ); //variety.getPhenotypeUniqueValues( listboxPhenotypes.getSelectedItem().getLabel() ).iterator();
			Iterator<CvTermUniqueValues> itValues =  ((Set)retobj[0]).iterator();
			//listPhenotypeListbox.get(0).setPhenotype_type( (Integer)retobj[1]);
			//listboxPhenotypes.setPhenotype_type( (Integer)retobj[1]);
			phenotypevalue_type=(Integer)retobj[1];
			//phenotype_type= (Integer)retobj[1];
			while(itValues.hasNext()) {
				CvTermUniqueValues value= itValues.next();
				
				if(value==null){  
					AppContext.debug("null value");
					continue;
				}
				
				AppContext.debug(value.toString());
				
				listValues.add(value.getValue());
				
				//AppContext.debug(value.getValue());
			}
		}
		
		listboxPhenValue.setModel(new SimpleListModel(listValues ));
		if(listValues.size()>0)
			listboxPhenValue.setSelectedIndex(0);
	}
	
}
