package org.irri.iric.portal.genomics.zkui;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.LocalAlignment;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocusLocalAlignment;
import org.irri.iric.portal.genomics.service.GeneOntologyService;
import org.irri.iric.portal.genomics.service.GenomicsFacade;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@Controller
public class LocusQueryController extends SelectorComposer<Window> {

	
	@Autowired
	private GenomicsFacade  genomics;
	
	
	@Autowired
	@Qualifier("WorkspaceFacade")
	private WorkspaceFacade workspace;
	    
	  
	@Wire
	private Textbox msgbox;
	
	@Wire
	private Grid gridLocus;
	@Wire
	private Grid gridAlignment;
	@Wire
	private Button searchButton;
	@Wire 
	private Textbox textboxGenefunction;
	
	@Wire
	private Button searchRegionButton;
	
	//@Wire 
	//private Textbox textboxGOTerm;
	@Wire
	private Listbox listboxOrganism;
	
	@Wire
	private Listbox listboxGOTerm;
	
	@Wire
	private Listbox listboxMyLocusList;
	
	@Wire
	private Button searchGenefunctionButton;
	@Wire
	private Button searchGOButton;
	
	@Wire
	private Hbox hboxAddtolist;
	@Wire
	private Button buttonDownloadCSV;
	@Wire
	private Button buttonDownloadTab;
	@Wire
	private Button buttonAddToList;
	@Wire
	private Textbox txtboxListname;
	
	private List<Locus> locusresult;
	private List<LocusLocalAlignment> locusalignmentresult;
	
	
	@Wire
	private Radio radioFunction;
	@Wire
	private Radio radioProcess;
	@Wire
	private Radio radioLocation;
	@Wire
	private Radio radioProtein;
	@Wire
	private Radio radioPathway;
	
	@Wire
	private Button searchEnrichment;
	
	@Wire
	private Textbox textboxSequence;
	@Wire
	private Listbox listboxBlastType;
	@Wire
	private Listbox listboxBlastDBType;
	@Wire
	private Label labelBlastType;
	@Wire
	private Button searchByBlast;
	@Wire
	private Textbox textboxEvalue;
	
	@Wire
	private Listbox listboxContig;
	@Wire
	private Intbox intboxContigStart;
	@Wire
	private Intbox intboxContigEnd;
	
	
	
	private void initResults() {
		gridAlignment.setVisible(false);
		gridLocus.setVisible(false);
		msgbox.setValue("");
	}
	

	
	
	@Listen("onChanging =#textboxEvalue")
	public void onchangingEvalue() {
	
		if(textboxEvalue.getValue()!=null && !textboxEvalue.getValue().isEmpty()) {
			try {
			Double.valueOf(textboxEvalue.getValue());
			} catch(Exception ex) {
				Messagebox.show(ex.getMessage(),"Exception", Messagebox.OK, Messagebox.EXCLAMATION);
				textboxEvalue.setValue("");
			}
		}
	}
	
	@Listen("onClick =#searchRegionButton") 
	public void searchByRegion() {
	
		
		if(this.listboxContig.getSelectedIndex()==0 || this.intboxContigStart.getValue()==null || this.intboxContigEnd.getValue()==null ) {
			Messagebox.show("Required contig, start and end positions","Incomplete info", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		if( this.intboxContigStart.getValue().intValue()> this.intboxContigEnd.getValue().intValue()) {
			Messagebox.show("End should be greater than or equal to start","Invalid values", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		if( this.intboxContigStart.getValue().intValue()<1 ||  this.intboxContigEnd.getValue().intValue()<1) {
			Messagebox.show("Start and End should be greater than zero","Invalid values", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		initResults();
		try {
			genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
			
			locusresult=new ArrayList();
				locusresult = genomics.getLociByRegion(listboxContig.getSelectedItem().getLabel() , Long.valueOf(intboxContigStart.getValue()), Long.valueOf(intboxContigEnd.getValue()), 
						this.listboxOrganism.getSelectedItem().getLabel());
			gridLocus.setRowRenderer(new LocusGridRenderer());
			gridLocus.setModel( new SimpleListModel( locusresult ));
			
			if(locusresult.size()>0) hboxAddtolist.setVisible(true);
			else hboxAddtolist.setVisible(false);
			
			this.msgbox.setValue("Search by region " + listboxContig.getSelectedItem().getLabel() + " [" + intboxContigStart.getValue().intValue() +"-" + intboxContigEnd.getValue().intValue() + "] ... RESULT:" + locusresult.size() + " loci");
			
			gridLocus.setVisible(true);
			
			
			
			} catch(Exception ex) {
				ex.printStackTrace();
				Messagebox.show( ex.getMessage(), "onlclickSearchFunction Exception", Messagebox.OK, Messagebox.ERROR );
			}
	}
	
	@Listen("onClick =#searchByBlast") 
	public void searchByBlast() {
	
		initResults();
		if(textboxSequence.getValue().isEmpty()) {
			Messagebox.show("Empty sequence","Incomplete info", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		if(this.listboxBlastDBType.getSelectedIndex()==0) {
			Messagebox.show("Select database sequence type","Incomplete info", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		if(this.listboxBlastType.getSelectedIndex()==0) {
			Messagebox.show("Select BLAST program to use","Incomplete info", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		try {
			genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");

			locusalignmentresult = genomics.getLociBySequence(textboxSequence.getValue(), this.listboxOrganism.getSelectedItem().getLabel(), listboxBlastType.getSelectedItem().getLabel(), 
					listboxBlastDBType.getSelectedItem().getLabel(), Double.valueOf(textboxEvalue.getValue()));
			
				gridAlignment.setRowRenderer(new LocusBlastresultGridRenderer());
				gridAlignment.setModel( new SimpleListModel( locusalignmentresult ));
			
			gridAlignment.setVisible(true);
			
			String dbtype = this.listboxBlastDBType.getSelectedItem().getLabel();
			if( (dbtype.equals("cdna") || dbtype.equals("cds") || dbtype.equals("pep")) && locusalignmentresult.size()>0 ) {
				hboxAddtolist.setVisible(true);
			} else {
				hboxAddtolist.setVisible(false);
			}
			
			this.msgbox.setValue("Search by sequence ... RESULT:" + locusalignmentresult.size() + " loci");
			
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show( ex.getMessage(), "searchByBlast Exception", Messagebox.OK, Messagebox.ERROR );
		}
							
		
		
	}
	
	private void setListboxCurrent(Listbox listbox, Listitem item) {
		
		
		//listbox.setc
	}
	

	private void createBlastHint() {
		
		String blastdbtype = listboxBlastDBType.getSelectedItem().getLabel();
		String blastDBMsg = listboxOrganism.getSelectedItem().getLabel() + " ";
		String blasttype = listboxBlastType.getSelectedItem().getLabel();
		
		//listboxBlastType.invalidate();listboxBlastDBType.invalidate();listboxBlastDBType.invalidate();
		
		//String blastdbtype = listboxBlastDBType.getItemAtIndex(listboxBlastDBType.getSelectedIndex()).getLabel();
		//String blastDBMsg = listboxOrganism.getItemAtIndex(listboxOrganism.getSelectedIndex()).getLabel() + " ";
		//String blasttype = listboxBlastType.getItemAtIndex(listboxBlastType.getSelectedIndex()).getLabel();

		
		
		if(blasttype.isEmpty()) {
			blasttype = "blastn";
			blastdbtype = "dna";
		}

		//if(listboxBlastDBType.getItems().size()==1) {
		//	blastdbtype = listboxBlastDBType.getItems().get(0).getLabel();
		//}
		
		if(blastdbtype.isEmpty()) {
			labelBlastType.setValue("Select database sequence type");
			return;
			// blastdbtype = "dna";
		} 
		
		/*
		if(blastdbtype.isEmpty()) {
			if(blasttype.equals("blastp") || blasttype.equals("blastx"))
				blastdbtype = "pep";
			else
				blastdbtype = "dna";
		}
		*/
		
		AppContext.debug( "blastdbtype=" + blastdbtype);
		
		if(blastdbtype.equals("dna"))
			blastDBMsg += "genome";
		else if(blastdbtype.equals("cds"))
			blastDBMsg += "coding sequences";
		else if(blastdbtype.equals("cdna"))
			blastDBMsg += "locus regions";
		else if(blastdbtype.equals("pep"))
			blastDBMsg += "protein sequences";

		
		String blastMsg="";
		

		if(blasttype.equals("blastn"))
			blastMsg = "Search " + blastDBMsg + " using a nucleotide query";
		else if(blasttype.equals("blastp"))
			blastMsg = "Search " + blastDBMsg  + " using a protein query";
		else if(blasttype.equals("blastx"))
			blastMsg = "Search " + blastDBMsg + " using a translated nucleotide query";
		else if(blasttype.equals("tblastn"))
			blastMsg = "Search translated " + blastDBMsg + " using a protein query";
		else if(blasttype.equals("tblastx"))
			blastMsg = "Search translated " + blastDBMsg + " using a translated nucleotide query";

		
		AppContext.debug( "blasttype=" + blasttype);
		AppContext.debug( "blastMsg=" + blastMsg);
		
		labelBlastType.setValue(blastMsg);

	}
	
	@Listen("onSelect = #listboxOrganism")    
	public void  onselectOrganism() { 
		String org = listboxOrganism.getItems().get(listboxOrganism.getSelectedIndex()).getLabel();
		List listBlasttype = new ArrayList();
		listBlasttype.add("");
		if(org.equals("rice")) {
			listBlasttype.add("blastn");listBlasttype.add("blastp");listBlasttype.add("blastx");
			listBlasttype.add("tblastn");listBlasttype.add("tblastx");
			
		} else {
			listBlasttype.add("blastn");listBlasttype.add("tblastn");listBlasttype.add("tblastx");
		}
		this.listboxBlastType.setModel(new SimpleListModel(listBlasttype));
		listboxBlastType.setSelectedIndex(0);
		//listboxBlastType.invalidate();
		
		
		AppContext.debug( "org=" + org);
		
		// onselectBlastType();
		listboxBlastDBType.setModel(new SimpleListModel(new ArrayList()));

		this.searchGenefunctionButton.setDisabled(!org.equals("rice"));
		this.searchGOButton.setDisabled(!org.equals("rice"));
		this.searchRegionButton.setDisabled(!org.equals("rice"));
		
		this.labelBlastType.setValue("Select Blast program and database sequence.");
		
		
	}
	
	@Listen("onSelect = #listboxBlastType")    
	public void  onselectBlastType() {
		//listboxBlastType.invalidate();
		//String blasttype = listboxBlastType.getItems().get(listboxBlastType.getSelectedIndex()).getLabel();
		String blasttype = listboxBlastType.getSelectedItem().getLabel();

		if(blasttype.isEmpty()) blasttype="blastn";
		
		List listDBType = new ArrayList();
		
		listDBType.add("");
		if(listboxOrganism.getSelectedItem().getLabel().equals("rice")) {
		
			if(blasttype.equals("blastn") || blasttype.equals("tblastn") || blasttype.equals("tblastx")) {
				listDBType.add( "dna" ); listDBType.add( "cds" ); listDBType.add( "cdna" ); 
			} else {
				listDBType.add( "pep" );
			}
		
		} else {
			if(blasttype.equals("blastn") || blasttype.equals("tblastn") || blasttype.equals("tblastx")) {
				listDBType.add( "dna" );
			} else {
				listDBType.add( "pep" );
			}
		}
				
		listboxBlastDBType.setModel(new SimpleListModel(listDBType));

		//Events.sendEvent( "onSelect", listboxBlastDBType, 0);
		listboxBlastDBType.setSelectedIndex(0);
		createBlastHint();
		
	}

	@Listen("onSelect = #listboxBlastDBType")    
	public void  onselectBlastDBType() {
		createBlastHint();
		
	}

	
	@Listen("onSelect = #listboxMyLocusList")    
	public void  onselectMyVarieties() {
			if( listboxMyLocusList.getSelectedItem().getLabel().equals("create new list...")) {
				Executions.sendRedirect("_workspace.zul?from=locus");
			}
	}

	@Listen("onClick =#buttonAddToList") 
	public void onlclickAddtolist() {
		// add to locus list
	
		try {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List addLocuslist = null;
    	
    	if(this.gridAlignment.isVisible()) {
    		genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
    		addLocuslist = genomics.getLociFromAlignment(locusalignmentresult);
    	} else if (this.gridLocus.isVisible()) {
    		addLocuslist = locusresult;
    	}
    	
    	if(addLocuslist!=null &&  addLocuslist.size()==0) {
    		
    		Messagebox.show( "EMPTY LOCUS LIST");
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
    	
    	workspace.addLocusList( txtboxListname.getValue().trim(), new LinkedHashSet(addLocuslist) );
    	txtboxListname.setValue("");
		
    	
    	List listNew = new ArrayList();
    	listNew.add("");
    	listNew.addAll( workspace.getLocuslistNames() );
    	listNew.add("create new list...");
    	this.listboxMyLocusList.setModel(new SimpleListModel(listNew ));
    	
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "Addtolist Exception", Messagebox.OK, Messagebox.ERROR);
		}
		 
    	
	}
	
	@Listen("onClick =#searchGenefunctionButton") 
	public void onlclickSearchGenefunction() { 
		initResults();
		try {
		genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		
		locusresult=new ArrayList();
		if(textboxGenefunction.getValue()!=null && !textboxGenefunction.getValue().isEmpty()) {
			locusresult = genomics.getLociByDescription( textboxGenefunction.getValue() );
		}
		gridLocus.setRowRenderer(new LocusGridRenderer());
		gridLocus.setModel( new SimpleListModel( locusresult ));
		
		if(locusresult.size()>0) hboxAddtolist.setVisible(true);
		else hboxAddtolist.setVisible(false);
		
		this.msgbox.setValue("Search by function '" + textboxGenefunction.getValue() + "' ... RESULT:" + locusresult.size() + " loci");
		
		gridLocus.setVisible(true);
		
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show( ex.getMessage(), "onlclickSearchFunction Exception", Messagebox.OK, Messagebox.ERROR );
		}
				
	}

	@Listen("onClick =#searchGOButton") 
	public void onlclickSearchGO() { 
		initResults();
		try {
			genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
			
			locusresult=new ArrayList();
			
			if(this.listboxGOTerm.getSelectedIndex()>0) {
				locusresult = genomics.getLociByGOTerm( listboxGOTerm.getSelectedItem().getLabel(), this.listboxOrganism.getSelectedItem().getLabel() );
			}

			if(locusresult.size()>0) hboxAddtolist.setVisible(true);
			else hboxAddtolist.setVisible(false);
			
			gridLocus.setRowRenderer(new LocusGridRenderer());
			gridLocus.setModel( new SimpleListModel( locusresult ));

			this.msgbox.setValue("Search by GO term '" + listboxGOTerm.getSelectedItem().getLabel() + "' ... RESULT:" + locusresult.size() + " loci");
			
			gridLocus.setVisible(true);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show( ex.getMessage(), "onlclickSearchGO Exception", Messagebox.OK, Messagebox.ERROR );
		}
	}

	@Listen("onClick =#searchEnrichment")
	public void enrichList() {
		try {
			if(listboxMyLocusList.getSelectedIndex()==0) {
				Messagebox.show("Select a Locus list", "Incomplete info", Messagebox.OK  , Messagebox.EXCLAMATION );
				return;
			}
			
			workspace = (WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
			Set setLoci = workspace.getLoci( listboxMyLocusList.getSelectedItem().getLabel() );
			
			genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
			
			String enrichmentType="";
			if(this.radioFunction.isSelected() )
				enrichmentType=GeneOntologyService.PANTHER_ENRICHMENTTYPE_FUNCTION;
			else if(this.radioProcess.isSelected() )
				enrichmentType=GeneOntologyService.PANTHER_ENRICHMENTTYPE_PROCESS;
			else if(this.radioLocation.isSelected() )
				enrichmentType=GeneOntologyService.PANTHER_ENRICHMENTTYPE_CELLULARLOCATION;
			else if(this.radioProtein.isSelected() )
				enrichmentType=GeneOntologyService.PANTHER_ENRICHMENTTYPE_PROTEINCLASS;
			else if(this.radioPathway.isSelected() )
				enrichmentType=GeneOntologyService.PANTHER_ENRICHMENTTYPE_PATHWAY;
			
			String result = genomics.overRepresentationTest(listboxOrganism.getSelectedItem().getLabel(), setLoci, enrichmentType);
			
			AppContext.debug(result);
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show( ex.getMessage(), "enrichList Exception", Messagebox.OK, Messagebox.ERROR );
		}
		
	}
	
	
	/*
	<label value="PANTHER  " pre="true"/>
	<radiogroup id="radiogroupenrichment"/>
	<radio id="radioFunction" label="Function" radiogroup="radiogroupenrichment" selected="true"/><vbox width="10px"/>
	<radio id="radioProcess" label="Process" radiogroup="radiogroupenrichment" /><vbox width="10px"/>
	<radio id="radioLocation" label="Cell location" radiogroup="radiogroupenrichment" /><vbox width="10px"/>
	<radio id="radioProtein" label="Protein class" radiogroup="radiogroupenrichment" /><vbox width="10px"/>
	<radio id="radioPathway" label="Pathway" radiogroup="radiogroupenrichment" />
	</hbox>
	</vbox>

	 <button id="searchEnrichment" label="Enrich Gene List"   style="font-size:14px;font-weight:bold;color:gray;"
	  width="150px" height="30px"  autodisable="self"
	  />
	
	
	   */

	 
	 
	@Listen("onClick =#searchButton")
	public void onlclickSearch() {
		try {
		genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		
		List listResult=new ArrayList();
		if(textboxGenefunction.getValue()!=null && !textboxGenefunction.getValue().isEmpty()) {
			listResult = genomics.getLociByDescription( textboxGenefunction.getValue() );
			gridLocus.setRowRenderer(new LocusGridRenderer());
			gridLocus.setModel( new SimpleListModel( listResult ));
		}
		

		//if(this.textboxGOTerm.getValue()!=null && !textboxGOTerm.getValue().isEmpty()) {
		
		//	AppContext.debug( "GO result: " + genomics.queryGO(textboxGOTerm.getValue()) );
			
		//}
		
		
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show( ex.getMessage(), "onlclickSearch Exception", Messagebox.OK, Messagebox.ERROR );
		}
		
		
	}
	
	
	
	
}
