package org.irri.iric.portal.genomics.zkui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.LocalAlignment;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocalAlignmentImpl;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Row;
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
	Label labelChrExample;
	
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
	
	@Wire
	private Button buttonCountGOTermLoci;
	@Wire
	private Grid gridGOTerms;
	@Wire
	private Row rowCountGOTermLoci;
	
	//@Wire 
	//private Textbox textboxGOTerm;
	@Wire
	private Listbox listboxOrganism;
	
	@Wire
	private Listbox listboxGOTerm;
	
	@Wire
	private Listbox listboxCV;
	
	@Wire
	private Listbox listboxGOAncestors;
	@Wire
	private Listbox listboxGODescendants;
	@Wire
	private Label labelGOAncestors;
	@Wire
	private Label labelGODescendants;
	
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
	private List<LocalAlignmentImpl> locusalignmentresult;
	
	
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
	private Combobox listboxContig;
	@Wire
	private Intbox intboxContigStart;
	@Wire
	private Intbox intboxContigEnd;
	
	@Wire
	private Listbox listboxSearchby;
	@Wire
	private Row rowGeneFunction;
	@Wire
	private Row rowGoTerm;
	@Wire
	private Row rowRegion;
	@Wire
	private Row rowSequence;
	@Wire
	private Row rowMySnpList;
	@Wire
	private Button searchMySnpListButton;
	@Wire
	private Listbox listboxMySNPList;
	
	
	@Listen("onSelect =#listboxSearchby")
	public void onselectSearchby() {
		
		rowGeneFunction.setVisible(false);
		rowGoTerm.setVisible(false);
		rowRegion.setVisible(false);
		rowSequence.setVisible(false);
		rowMySnpList.setVisible(false);
		rowCountGOTermLoci.setVisible(false);
		String searchby = listboxSearchby.getSelectedItem().getLabel();
		
		if(searchby.isEmpty()) return;
		
		try {
			if(searchby.equals("Gene function")) {
				textboxGenefunction.setValue("");
				rowGeneFunction.setVisible(true);
			}
			else if(searchby.equals("Gene Ontology term")) {
				
				genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
				
				listboxCV.setSelectedIndex(0);
				
				List listGoterms = new ArrayList();
				listGoterms.add("");
				listGoterms.addAll(genomics.getGotermsByOrganism("biological_process",AppContext.getDefaultOrganism()) );

				listboxGOTerm.setModel(new SimpleListModel(listGoterms));
				listboxGOTerm.setSelectedIndex(0);
				rowGoTerm.setVisible(true);
				
				workspace  = (WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
				List listNew = new ArrayList();
		    	listNew.add("");
		    	listNew.addAll( workspace.getLocuslistNames() );
		    	listNew.add("create new list...");
		    	this.listboxMyLocusList.setModel(new SimpleListModel(listNew ));
		    	listboxMyLocusList.setSelectedIndex(0);
		    	
				rowCountGOTermLoci.setVisible(true);
			}
			else if(searchby.equals("Region")) {
				
				genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
				
				intboxContigStart.setValue(null);
				intboxContigEnd.setValue(null);
				
				List conts =  genomics.getContigsByOrganism( this.listboxOrganism.getSelectedItem().getLabel() ) ;
				//listboxContig.setPreloadSize(50);
				
				List listContigs = AppContext.createUniqueUpperLowerStrings(conts,true, true);
				listboxContig.setModel(  new SimpleListModel( listContigs ));
				//listboxContig.setSelectedIndex(0);
				listboxContig.setValue("");
				
				
				String selOrg = listboxOrganism.getSelectedItem().getLabel();
				if(selOrg.equals("93-11"))
					labelChrExample.setValue("(ex. 9311_chr01, scaffold01_4619) " );
				else if(selOrg.equals("Kasalath"))
					labelChrExample.setValue("(ex. chr01, um) " );
				else
					labelChrExample.setValue("(ex. " + listContigs.get(1).toString().toLowerCase() + ") " );
					//labelChrExample.setValue("(ex. scaffold...)" );
				
				rowRegion.setVisible(true);
			}
			else if(searchby.equals("My SNP List")) {
				
				workspace  = (WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
				List listMySnps = new ArrayList();
				listMySnps.add("");
				listMySnps.addAll(workspace.getSnpPositionListNames());
				listMySnps.add("create new list...");
				this.listboxMySNPList.setModel(new SimpleListModel(listMySnps));
				this.rowMySnpList.setVisible(true);
			}
			else if(searchby.equals("Sequence")) {
				//this.listboxBlastType.setSelectedIndex(0);
				//this.listboxBlastDBType.setSelectedIndex(0);
				this.textboxEvalue.setValue("10");
				textboxSequence.setValue("");
				
				String org = listboxOrganism.getItems().get(listboxOrganism.getSelectedIndex()).getLabel();

//				List listBlasttype = new ArrayList();
//				listBlasttype.add("");
//				if(org.equals(AppContext.getDefaultOrganism())) {
//					listBlasttype.add("blastn");listBlasttype.add("blastp");listBlasttype.add("blastx");
//					listBlasttype.add("tblastn");listBlasttype.add("tblastx");
//					
//				} else {
//					listBlasttype.add("blastn");listBlasttype.add("tblastn");listBlasttype.add("tblastx");
//				}
//				this.listboxBlastType.setModel(new SimpleListModel(listBlasttype));
				listboxBlastType.setSelectedIndex(0);
//				//listboxBlastType.invalidate();
//				// onselectBlastType();
//				listboxBlastDBType.setModel(new SimpleListModel(new ArrayList()));
	
				//this.searchGenefunctionButton.setDisabled(!org.equals(AppContext.getDefaultOrganism()));
				//this.searchGOButton.setDisabled(!org.equals(AppContext.getDefaultOrganism()));
				//this.searchRegionButton.setDisabled(!org.equals(AppContext.getDefaultOrganism()));
				
				
				List listDBtype=new ArrayList();
				if(org.equals(AppContext.getDefaultOrganism())) {
					listDBtype.add("dna");listDBtype.add("cdna");listDBtype.add("cds");
				}
				else {
					listDBtype.add("dna");listDBtype.add("cdna");listDBtype.add("cds");listDBtype.add("upstream3000");
				}
				
				this.listboxBlastDBType.setModel( new SimpleListModel(listDBtype) );
				
				this.labelBlastType.setValue("Select Blast program and database sequence.");			
				
				
				rowSequence.setVisible(true);
			}
		
		} catch(Exception ex) {
			ex.printStackTrace();
		} 
	}
	
	@Listen("onSelect= #listboxCV")
	public void onselectCV() {
		List listGoterms = new ArrayList();
		listGoterms.add("");
		listGoterms.addAll(genomics.getGotermsByOrganism((String)listboxCV.getSelectedItem().getValue(),AppContext.getDefaultOrganism()) );

		listboxGOTerm.setModel(new SimpleListModel(listGoterms));
		listboxGOTerm.setSelectedIndex(0);
		listboxGODescendants.setModel(new SimpleListModel(new ArrayList()));
		listboxGOAncestors.setModel(new SimpleListModel(new ArrayList()));
		labelGOAncestors.setValue("");
		labelGODescendants.setValue("");
	}
	
	@Listen("onSelect= #listboxGOTerm")
	public void onselectGOterm() {
		List listGotermsA = new ArrayList();
		listGotermsA.addAll(genomics.getCVtermAncestors((String)listboxCV.getSelectedItem().getValue(), listboxGOTerm.getSelectedItem().getLabel() ));
		listboxGOAncestors.setModel(new SimpleListModel(listGotermsA));
		labelGOAncestors.setValue(listGotermsA.size() +  " ancestors");
		List listGotermsD = new ArrayList();
		listGotermsD.addAll(genomics.getCVtermDescendants((String)listboxCV.getSelectedItem().getValue(), listboxGOTerm.getSelectedItem().getLabel() ));
		listboxGODescendants.setModel(new SimpleListModel(listGotermsD));
		labelGODescendants.setValue(listGotermsD.size() +  " descendants");

	}
	
	
	
	@Listen("onSelect = #listboxOrganism")    
	public void  onselectOrganism() { 

		rowGoTerm.setVisible(false);
		rowRegion.setVisible(false);
		rowMySnpList.setVisible(false);
		rowGeneFunction.setVisible(false);
		rowSequence.setVisible(false);

		String selorg = listboxOrganism.getSelectedItem().getLabel();
		if( selorg.equalsIgnoreCase("ALL")) {
			List listSearch = new ArrayList();
			//listSearch.add("");
			//listSearch.add("Gene function");
			//listSearch.add("Gene Ontology term");
			//listSearch.add("Region");
			listSearch.add("Sequence");
			//listSearch.add("My SNP List");
			listboxSearchby.setModel(new SimpleListModel(listSearch));
			listboxSearchby.setSelectedIndex(0);
			this.rowSequence.setVisible(true);
			this.searchGenefunctionButton.setDisabled(false);
			
			labelChrExample.setValue("");
		}
		else if( selorg.equals(AppContext.getDefaultOrganism()) ) {

			List listSearch = new ArrayList();
			//listSearch.add("");
			listSearch.add("Gene function");
			listSearch.add("Gene Ontology term");
			listSearch.add("Region");
			listSearch.add("Sequence");
			listSearch.add("My SNP List");
			listboxSearchby.setModel(new SimpleListModel(listSearch));
			listboxSearchby.setSelectedIndex(0);
			rowGeneFunction.setVisible(true);
			this.searchGenefunctionButton.setDisabled(false);
			//onselectSearchby();
			
			labelChrExample.setValue("(ex. chr01)" );
			
		} else {
			List listSearch = new ArrayList();
			//listSearch.add("");
			listSearch.add("Gene function");
			this.searchGenefunctionButton.setDisabled(true);
			listSearch.add("Gene Ontology term");
			listSearch.add("Region");
			listSearch.add("Sequence");
			//listSearch.add("My SNP List");
			listboxSearchby.setModel(new SimpleListModel(listSearch));
			listboxSearchby.setSelectedIndex(0);
			//this.rowGoTerm.setVisible(true);
			rowGeneFunction.setVisible(true);
			
			String selOrg = listboxOrganism.getSelectedItem().getLabel();
			if(selOrg.equals("93-11"))
				labelChrExample.setValue("(ex. 9311_chr01, scaffold01_4619) " );
			else if(selOrg.equals("Kasalath"))
				labelChrExample.setValue("(ex. chr01, um) " );
			else
				//labelChrExample.setValue("(ex. " + listContigs.get(1).toString().toLowerCase() + ") " );
				labelChrExample.setValue("(ex. scaffold...)" );

			
		}

		/*
		if( listboxOrganism.getSelectedItem().getLabel().equals(AppContext.getDefaultOrganism()) ) {

			List listSearch = new ArrayList();
			//listSearch.add("");
			listSearch.add("Gene function");
			listSearch.add("Gene Ontology term");
			listSearch.add("Region");
			listSearch.add("Sequence");
			listSearch.add("My SNP List");
			listboxSearchby.setModel(new SimpleListModel(listSearch));
			listboxSearchby.setSelectedIndex(0);
			rowGeneFunction.setVisible(true);
			//onselectSearchby();
		} else {
			List listSearch = new ArrayList();
			//listSearch.add("");
			listSearch.add("Sequence");
			listboxSearchby.setModel(new SimpleListModel(listSearch));
			listboxSearchby.setSelectedIndex(0);
			rowSequence.setVisible(true);

		}
		*/


		
		//String searchby = this.listboxSearchby.getSelectedItem().getLabel();
		
		/*
		String org = listboxOrganism.getItems().get(listboxOrganism.getSelectedIndex()).getLabel();
		List listBlasttype = new ArrayList();
		listBlasttype.add("");
		if(org.equals(AppContext.getDefaultOrganism())) {
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

		this.searchGenefunctionButton.setDisabled(!org.equals(AppContext.getDefaultOrganism()));
		this.searchGOButton.setDisabled(!org.equals(AppContext.getDefaultOrganism()));
		this.searchRegionButton.setDisabled(!org.equals(AppContext.getDefaultOrganism()));
		
		this.labelBlastType.setValue("Select Blast program and database sequence.");
		*/
		
	}
	
	private void initResults() {
		gridAlignment.setVisible(false);
		gridLocus.setVisible(false);
		gridGOTerms.setVisible(false);
		msgbox.setValue("");
		hboxAddtolist.setVisible(false);
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
	
		
		AppContext.debug("searching region in " + listboxContig.getValue());
		
		//if(this.listboxContig.getSelectedIndex()==0 || this.intboxContigStart.getValue()==null || this.intboxContigEnd.getValue()==null ) {
		if(this.listboxContig.getValue()==null || this.listboxContig.getValue().trim().isEmpty() || this.intboxContigStart.getValue()==null || this.intboxContigEnd.getValue()==null ) {
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
			
			//locusresult=new ArrayList();
				locusresult = genomics.getLociByRegion(listboxContig.getValue().trim() , Long.valueOf(intboxContigStart.getValue()), Long.valueOf(intboxContigEnd.getValue()), 
						this.listboxOrganism.getSelectedItem().getLabel());
			gridLocus.setRowRenderer(new LocusGridRenderer());
			gridLocus.setModel( new SimpleListModel( locusresult ));
			
			if(locusresult.size()>0) hboxAddtolist.setVisible(true);
			else hboxAddtolist.setVisible(false);
			
			this.msgbox.setValue("Search by region " + listboxContig.getValue().trim() + " [" + intboxContigStart.getValue().intValue() +"-" + intboxContigEnd.getValue().intValue() + "] ... RESULT:" + locusresult.size() + " loci");
			
			gridLocus.setVisible(true);
			
			
			
			} catch(Exception ex) {
				ex.printStackTrace();
				Messagebox.show( ex.getMessage(), "onlclickSearchFunction Exception", Messagebox.OK, Messagebox.ERROR );
			}
	}
	
	
	@Listen("onSelect =#listboxMySNPList") 
	public void onselectMySnpList() {
		
		if(listboxMySNPList.getSelectedIndex()>0) {

			if( listboxMySNPList.getSelectedItem().getLabel().equals("create new list...")) {
				Executions.sendRedirect("_workspace.zul?from=snp&src=locus");
			}
		}
	}
	
	@Listen("onClick =#searchMySnpListButton") 
	public void searchbyMySnpList() {
		initResults();
		
		try {
			genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
			
			workspace =(WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
			
			String mylist = this.listboxMySNPList.getSelectedItem().getLabel();
			
			String contigname[] =  mylist.trim().split(":"); 
			
			Collection colPos = workspace.getSnpPositions(contigname[0].trim(), contigname[1].trim());
			
				locusresult = genomics.getLociByContigPositions(contigname[0].trim() ,
						colPos, 
						this.listboxOrganism.getSelectedItem().getLabel());
			gridLocus.setRowRenderer(new LocusGridRenderer());
			gridLocus.setModel( new SimpleListModel( locusresult ));
			
			if(locusresult.size()>0) hboxAddtolist.setVisible(true);
			else hboxAddtolist.setVisible(false);
			
			this.msgbox.setValue("Search by SNP List positions: Contig " + contigname[0].trim() + ", SNP List " + listboxMySNPList.getSelectedItem().getLabel() + " ... RESULT:" + locusresult.size() + " loci");
			
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
		else if(blastdbtype.equals("upstream3000"))
			blastDBMsg += "3000bp upstream regions";
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
	
	
	
	@Listen("onSelect = #listboxBlastType")    
	public void  onselectBlastType() {
		//listboxBlastType.invalidate();
		//String blasttype = listboxBlastType.getItems().get(listboxBlastType.getSelectedIndex()).getLabel();
		String blasttype = listboxBlastType.getSelectedItem().getLabel();

		if(blasttype.isEmpty()) blasttype="blastn";
		
		List listDBType = new ArrayList();
		
		listDBType.add("");
		if(listboxOrganism.getSelectedItem().getLabel().equals(AppContext.getDefaultOrganism())) {
		
			if(blasttype.equals("blastn") || blasttype.equals("tblastn") || blasttype.equals("tblastx")) {
				listDBType.add( "dna" ); listDBType.add( "cds" ); listDBType.add( "cdna" );
			} else {
				listDBType.add( "pep" );
			}
		
		} else {
			if(blasttype.equals("blastn") || blasttype.equals("tblastn") || blasttype.equals("tblastx")) {
				listDBType.add( "dna" ); listDBType.add( "cds" ); listDBType.add( "cdna" ); listDBType.add( "upstream3000" );
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
			//locusresult = genomics.getLociByDescription( textboxGenefunction.getValue() );
			locusresult = genomics.getLociByDescription( textboxGenefunction.getValue(), listboxOrganism.getSelectedItem().getLabel() );
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
	public void onclickSearchGO() { 
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
			Messagebox.show( ex.getMessage(), "onclickSearchGO Exception", Messagebox.OK, Messagebox.ERROR );
		}
	}

	@Listen("onClick =#buttonCountGOTermLoci") 
	public void onclickCountGOTerm() {
		initResults();
		
		
		try {
			genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
			workspace = (WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
			String locuslistname = this.listboxMyLocusList.getSelectedItem().getLabel();
			if(locuslistname.isEmpty() || locuslistname.startsWith("create new list")) {
				Messagebox.show("Select a Locus List", "Incomplete Info", Messagebox.OK , Messagebox.EXCLAMATION);
				return;
			}
			
			
			//List listGOTermCount = genomics.getGOTermLociCounts( this.listboxOrganism.getSelectedItem().getLabel(), workspace.getLoci(  locuslistname ), (String)this.listboxCV.getSelectedItem().getValue() );
			List listGOTermCount = genomics.getGOTermLociCounts( this.listboxOrganism.getSelectedItem().getLabel(), workspace.getLoci(  locuslistname ), null);
			this.gridGOTerms.setModel(new SimpleListModel(listGOTermCount));
			gridGOTerms.setRowRenderer(new CvTermLocusCountGridRenderer());
			gridGOTerms.setVisible(true);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show( ex.getMessage(), "onclickCountGOTerm Exception", Messagebox.OK, Messagebox.ERROR );
		}
			
	}
	
	
	@Listen("onClick =#searchEnrichment")
	public void enrichList() {
		initResults();
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
