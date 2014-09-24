package org.irri.iric.portal.variety.zkui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.CvTermUniqueValues;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyImpl;
import org.irri.iric.portal.domain.VarietyPlus;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Splitter;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

@Controller
public class VarietyQueryController extends SelectorComposer<Component> {

	
    private static final long serialVersionUID = 1L;
    
    //private boolean isShowAll = false;

    
    @Wire
    private Combobox comboVarname;
    @Wire
    private Combobox comboIrisId;

    
    @Wire
    private Combobox comboCountry;
    
    @Wire
    private Combobox comboSubpopulation;
    
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
    private Tab tabTable;
    //@Wire
    //private Grid gridQuery;
    
    @Wire
    private Vbox hboxQuery;
    
    
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
    private Listbox listboxPhenValue;
    
	
    
    @Wire
    private Listbox listboxGermPassport;
    
    @Wire
    private Iframe iframePhylotree;
    

    @Listen("onUser = #winVariety")
    //public void onUser$info(Event event){
    public void onUser(Event event){
    	String eventParam  = event.getData().toString();
        //ForwardEvent eventx = (ForwardEvent)event;
        //String eventParam = eventx.getOrigin().getData().toString(); 
        //System.out.println( eventParam ); 
        
        //Messagebox.show(eventParam);
        
        variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
        Variety varselected = variety.getGermplasmByIrisId(eventParam.replace("_"," ") );
        if(varselected==null) throw new RuntimeException(eventParam.replace("_"," ") + " not found");
        show_passport(varselected);
      
        //Event eventx = Events.getRealOrigin((ForwardEvent)event);
        //System.out.println(eventx.getData());
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

    	//System.out.println("loading phylotree " + urlphylo);
    	Clients.showBusy("Computing Phylogenetic Tree");
    	gridGermplasm.setVisible(false);
    	Clients.clearBusy();
    }
    
    
    @Listen("onClick = #resetButton")   
    public void reset()
    {
    	comboVarname.setValue("");
    	comboIrisId.setValue("");
    	comboCountry.setValue("");
    	comboSubpopulation.setValue("");
    }
    
    
    
    @Listen("onClick = #showallButton")   
    public void showAll()
    {
    	reset();
    	tabTable.setSelected(true);
    	variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
		
		java.util.List<Variety> varsresult = new java.util.ArrayList<Variety>();
		varsresult.addAll( variety.getGermplasm() );
		
		msgbox.setValue( "ALL RESULT: " + varsresult.size() + " rows" );
		
    	displayResults(varsresult,true);
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
		
		java.util.List<Variety> varsresult = new java.util.ArrayList<Variety>();
		
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
			
			if( (comboCountry==null || comboCountry.getValue().isEmpty()) && (comboSubpopulation==null || comboSubpopulation.getValue().isEmpty())
				&& (listboxPassport.getSelectedItem()==null || listboxPassport.getSelectedItem().getLabel().trim().isEmpty()) 
				&& (listboxPhenotypes.getSelectedItem()==null || listboxPhenotypes.getSelectedItem().getLabel().trim().isEmpty())	
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
			
			
			
			if( (comboCountry==null || comboCountry.getValue().isEmpty()) && (comboSubpopulation==null || comboSubpopulation.getValue().isEmpty()) ) {}
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
		
				if(comboSubpopulation==null) throw new RuntimeException("comboSubpopulation==null");
				if(comboSubpopulation.getValue()!=null && !comboSubpopulation.getValue().isEmpty() )	{	
					example.setSubpopulation( comboSubpopulation.getValue());
					if(msg.length()>0) msg.append(" AND ");
						
					msg.append(" SUBPOPULATION=" + comboSubpopulation.getValue().toUpperCase());
				}
				
				msgbox.setValue("VARIETY WHERE " + msg.toString());
						
				if(variety==null) throw new RuntimeException("variety==null");
				if(example==null) throw new RuntimeException("example==null");
				if(varsresult==null) throw new RuntimeException("varsresult==null");
				
				
				varsresult.addAll(  variety.getGermplasmByExample( example )  );
			}
			
			if(listboxPassport.getSelectedItem()!=null && !listboxPassport.getSelectedItem().getLabel().trim().isEmpty()) {
				
				List listVarsByPassport = variety.getVarietyByPassport(listboxPassport.getSelectedItem().getLabel().trim(), listboxPassportValue.getSelectedItem().getLabel().trim());
				varsresult = intersectVarietyList(varsresult, listVarsByPassport);
			}
			

			msgbox.setValue( msgbox.getValue() + " ... RESULT: " + varsresult.size() + " rows" );
			msgbox.setStyle( "font-color:black" );
			
			displayResults(varsresult, false);
		}
		
		
		
		//gmaps.setCenter( 14.167774, 121.254547);
		//gmaps.setZoom(15);
		
	}
	
	
	
	private List<Variety> intersectVarietyList(java.util.Collection<Variety> list1, Collection<Variety> list2) {
		
		List listNew = new ArrayList();
		
		if(list1.size()==0)
			return (List)list2;
		
		
		Set<BigDecimal> setList1Id=new HashSet();
		Iterator<Variety> itVar = list1.iterator();
		while(itVar.hasNext()) {
			setList1Id.add( itVar.next().getVarietyId() );
		}
		
		Iterator<Variety> it2Var = list2.iterator();
		while(it2Var.hasNext()) {
			Variety var2 = it2Var.next();
			if(setList1Id.contains(  var2.getVarietyId() ))
				listNew.add( var2 );
		}
		return listNew;
		
	}
	
	private void displayResults(List varsresult, boolean showAll) {
		displayResults( varsresult, showAll, null); 	
	}
	
	private void displayResults(List varsresult, boolean showAll, String extrafield) {
		
		boolean hasExtraField = (extrafield!=null && !extrafield.isEmpty());
		
		System.out.println("Displaying " +  varsresult.size() + " varieties");
		
		// update table header
				Listheader lhr = (Listheader)varietyresult.getListhead().getFirstChild();    	
				String[] headers;
				if(hasExtraField)
					headers = new String[]{"NAME","IRIS ID", "SUBPOPULATION", "COUNTRY", extrafield};
				else
					headers = new String[]{"NAME","IRIS ID", "SUBPOPULATION", "COUNTRY"};
				int i = 0;
				while(lhr != null) {
					lhr.setLabel(headers[i] );
					lhr = (Listheader)lhr.getNextSibling();
					i++;
				}
				
				if(varsresult.size()>2)
				{
					if(showAll) {
						show_phylotree_allvars();
					}
					else {
						StringBuffer varids = new StringBuffer();
						java.util.Iterator<Variety> itvars = varsresult.iterator();
						while(itvars.hasNext()) {
							varids.append(  itvars.next().getVarietyId()  );
							if(itvars.hasNext()) varids.append(",");
						}
						show_phylotree(varids.toString()); 
						//iframePhylotree.setVisible(true);
					}
					tabPhylo.setVisible(true);
				} 
				else
					//iframePhylotree.setVisible(false);
					tabPhylo.setVisible(false);
				
				
				
				varietyresult.setModel( new SimpleListModel(varsresult) );
				
				gridGermplasm.setVisible(false);
				tabboxDisplay.setVisible(true);
				
			//	if(showAll)
			//		tabPhylo.setSelected(true);
				
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
		
		
		java.util.List listPassport = new java.util.ArrayList();
		listPassport.addAll( variety.getPassportByVarietyid( variety2.getVarietyId())  );
		
		listboxGermPassport.setModel(new SimpleListModel( listPassport ));
		
		listboxGermPhenotypes.setModel( new SimpleListModel(variety.getPhenotypesByGermplasm(  variety2 )) );
		
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
			String url = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&varid=" + varids ;
			System.out.println( url );
			iframePhylotree.setSrc( url );
	
		
			
	}
	
	private void show_phylotree_allvars() {
		
		iframePhylotree.setStyle("height:1500px;width:1500px");
		iframePhylotree.setScrolling("yes");
		String url = "treeBrowser1k.htm";
		System.out.println( url );
		iframePhylotree.setSrc( url );
		iframePhylotree.setVisible(true);

	}
	
	
	@Listen("onSelect = #listboxPassport")
	public void setPassportConstraint() {
		
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
	  
		System.out.println("passport selected:" + listboxPassport.getSelectedItem().getLabel());
		
		List listValues = new java.util.ArrayList();
		
		if(!listboxPassport.getSelectedItem().getLabel().trim().isEmpty())
		{
			Iterator<CvTermUniqueValues> itValues = variety.getPassportUniqueValues( listboxPassport.getSelectedItem().getLabel() ).iterator();
			while(itValues.hasNext()) {
				CvTermUniqueValues value= itValues.next();
				listValues.add(value.getValue());
				
				//System.out.println(value.getValue());
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
		
		System.out.println("phenotype selected:" + listboxPhenotypes.getSelectedItem().getLabel());
		
		if(!listboxPhenotypes.getSelectedItem().getLabel().trim().isEmpty())
		{
			Iterator<CvTermUniqueValues> itValues = variety.getPhenotypeUniqueValues( listboxPhenotypes.getSelectedItem().getLabel() ).iterator();
			while(itValues.hasNext()) {
				CvTermUniqueValues value= itValues.next();
				
				if(value==null){  
					System.out.println("null value");
					continue;
				}
				
				System.out.println(value.toString());
				
				listValues.add(value.getValue());
				
				//System.out.println(value.getValue());
			}
		}
		
		listboxPhenValue.setModel(new SimpleListModel(listValues ));
		if(listValues.size()>0)
			listboxPhenValue.setSelectedIndex(0);
	}
	
}
