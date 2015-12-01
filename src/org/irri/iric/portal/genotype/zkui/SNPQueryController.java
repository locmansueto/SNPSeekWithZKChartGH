package org.irri.iric.portal.genotype.zkui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
 


import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import oracle.sql.DATE;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.Variety;
//import org.irri.iric.portal.genotype.domain.Gene;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantStringService;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.service.IndelStringHDF5nRDBMSHybridService;
//import org.irri.iric.portal.genotype.service.IndelStringService;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;


/*
import org.irri.iric.portal.genotype.service.GenotypeFacadeImpl;
import org.irri.iric.portal.genotype.views.Snp2lines;
import org.irri.iric.portal.genotype.views.Snp2linesHome;
import org.irri.iric.portal.genotype.views.Snp2linesId;
import org.irri.iric.portal.genotype.views.ViewCountVarrefMismatchId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvars;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId;
*/
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.chart.AxisLabels;
import org.zkoss.chart.Chart;
import org.zkoss.chart.Charts;
import org.zkoss.chart.ChartsSelectionEvent;
import org.zkoss.chart.Legend;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.ResetZoomButton;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.chart.plotOptions.LinePlotOptions;
import org.zkoss.chart.plotOptions.ScatterPlotOptions;
import org.zkoss.lang.Objects;
//import org.zkoss.addon.Biglistbox;
//import org.zkoss.addon.MatrixComparatorProvider;
//import org.zkoss.addon.MatrixRenderer;
import org.zkoss.zkmax.zul.MatrixComparatorProvider;
import org.zkoss.zkmax.zul.MatrixModel;
import org.zkoss.zkmax.zul.MatrixRenderer;
import org.zkoss.zkmax.zul.Biglistbox;
import org.zkoss.zkmax.zul.event.CellClickEvent;
import org.zkoss.zkmax.zul.event.ScrollEventExt;
import org.zkoss.zkmax.zul.event.SortEventExt;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.SelectEvent;
//import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Initiator;


import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelExt;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemComparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.ListDataListener;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.ext.Selectable;



@Controller
@Scope(value="session")
public class SNPQueryController extends SelectorComposer<Window> { //<Component>  { //implements Initiator {

    private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(SNPQueryController.class);
    
	// url address of tab frames
	private String urljbrowse,gfffile,urlphylo,urljbrowsephylo;
	private String vistaurl;
	private String vistaurlrev;


	// number of 
	private int nAllelefreqLabels=25;
	
	//int screenwidth=0;
	//int screenheight=0;
	
	// bottom visible row in variant table display
	private int lastY;
	// number of frozen columns in variant table display
	int frozenCols=3;
	//private Map<Integer,BigDecimal> mapIdx2Pos;
	
	// display tall (for all varieties) or short (for few varieties) jbrowse frame
	private boolean tallJbrowse;
	
	// list of contigs for selected reference genomes
	private List listContigs;
	// list of gene loci  for selected reference genomes
	private List listLoci;

	
    final private String classGenotypefacade = "GenotypeFacade";
    
    private Object2StringMatrixModel biglistboxModel=null;
    private MatrixComparatorProvider matriccmpproviderAsc;
	private MatrixComparatorProvider matriccmpproviderDesc;
	

	// data for allele and genotype frequence chart
	private AlleleFreqLineData allelefreqlines = new AlleleFreqLineData();
	private AlleleFreqLineData genotypefreqlines = new AlleleFreqLineData();
	

	// holds SNP query result
    private VariantStringData queryRawResult;
    
    // holds SNP result for display
    private VariantStringData queryResult;
    
    // holds SNP result for variant table display
    private VariantTable varianttable;

	// number of visible rows in variant table display 
	private int biglistboxRows=16;

	
    @Autowired
    @Qualifier("GenotypeFacade")
    private GenotypeFacade  genotype; 
    
    @Autowired
    @Qualifier("VarietyFacade")
    private VarietyFacade  varietyfacade;
    
    @Autowired
    @Qualifier("WorkspaceFacade")
    private WorkspaceFacade workspace;

    
    // attributes for SNP Query controller components
	@Wire
	private Button buttonResetzoom;
	
	@Wire
	private Checkbox checkboxHeteroindels;
	@Wire
	private Div divHeteroindels;
	
	@Wire
	private Button buttonDownloadPairwiseTab;
	@Wire
	private Button buttonDownloadPairwiseCsv;
	@Wire
	private Div divPairwise;
	
	@Wire
	private Listbox listboxPhenotype;
	
	@Wire
	private Button buttonDownloadFasta;
	
	@Wire
	private Div divTablePanel;
	@Wire
	private Charts chartAlleleFrequency;
	
	@Wire
	private Radio radioShowGenotypeCount;
	@Wire
	private Radio radioShowGenotypeFrequency;
	
	@Wire
	private Radio radioShowAlleleFrequency;
	@Wire
	private Radio radioShowAlleleCount;
	@Wire
	private Radio radioMajorAlleles;
	@Wire
	private Radio radioMinorAlleles;
	@Wire
	private Radio radio3rdAlleles; 
	
	
	@Wire
	private Button buttonSNPSorter;
	@Wire
	private Listbox listboxSNPListAlleles;
	
	
	@Wire
	private Tab tabVistaNPB;
	@Wire
	private Iframe iframeNPBvsIR6421;
	@Wire
	private Iframe iframeNPBvs9311;
	@Wire
	private Iframe iframeNPBvsDJ123;
	@Wire
	private Iframe iframeNPBvsKasalath;
	@Wire
	private Tab tabNPBvsIR6421;
	@Wire
	private Tab tabNPBvs9311;
	@Wire
	private Tab tabNPBvsDJ123;
	@Wire
	private Tab tabNPBvsKasalath;
	
	
	@Wire
	private Label labelChrExample;
	@Wire
	private Label labelChr;
	
    @Wire 
	private Auxheader auxheader3Header;
    @Wire 
	private Column column4Header;
    
	@Wire
    private Auxhead auxheadAlleles1;
	@Wire
    private Auxheader auxheaderAlleles1;
	@Wire
    private Auxhead auxheadAlleles2;
	@Wire
    private Auxheader auxheaderAlleles2;
	@Wire
    private Auxhead auxheadAlleles3;
	@Wire
    private Auxheader auxheaderAlleles3;
	@Wire
    private Auxhead auxheadAlleles4;
	@Wire
    private Auxheader auxheaderAlleles4;
	@Wire
	private Auxheader auxheader3Phenotype;
    
    @Wire
    private Auxhead auxheadMultirefPairwise;
    @Wire
    private Auxheader auxheaderMultirefPairwise;
    @Wire
    private Listheader listheader1MultirefPairwise;
    @Wire
    private Listheader listheader2MultirefPairwise;
    @Wire
    private Listheader listheader3MultirefPairwise;
    
    @Wire
    private Listheader listheaderVar1MultirefPairwise;
    @Wire
    private Listheader listheaderVar2MultirefPairwise;
	
	
    @Wire
    private Listbox listboxReference;
    
    @Wire
    private Checkbox checkboxShowAllRefAlleles;
    
   
	@Wire
	private Tab tabVista;
	@Wire
	private Tabpanel tabpanelVista;
	@Wire
	private Iframe iframeVista;
	@Wire
	private Tab tabVistaRev;
	@Wire
	private Tabpanel tabpanelVistaRev;
	@Wire
	private Iframe iframeVistaRev;
	
	@Wire		
	private Button buttonDownloadNewick;
	
	@Wire
	private Label labelLocusExample;
	@Wire
	private Hbox divAlignIndels;
	@Wire
	private Div divIndelLegend;
	@Wire
	private Biglistbox biglistboxArray;
	@Wire
	private Biglistbox biglistboxArrayLarge;
	@Wire
	private Grid gridBiglistheader;

	@Wire
	private Radiogroup radiogroupShowsnps;
	
	
	@Wire
	private Radio radioAllSnps;
	@Wire
	private Radio radioNonsynSnps;
	@Wire
	private Radio radioNonsynHighlights;
	//@Wire
	//private Checkbox checkboxSpliceSnps;
	@Wire
	private Radio radioNonsynSnpsPlusSplice;


	//@Wire
	//private Biglistbox myComp;
	@Wire
	private Div tip;
	@Wire
	private Textbox content;
	@Wire
	private Tabpanel tabpanelTable;
	
	@Wire
	private Label labelFilterResult;
	
	@Wire
	private Checkbox checkboxSNP;
	@Wire
	private Checkbox checkboxIndel;
	
	@Wire
	private Vbox buttonsDownloadNodisplay;
	
	
//    @Wire
//    private Label labelScreenWidth;
    @Wire
    private Button buttonReset;
    @Wire
    private Intbox intStart;
    @Wire
    private Intbox intStop;
    @Wire
    private Combobox comboGene;
    @Wire
    private Combobox selectChr;
    @Wire
    private Listbox listboxSnpresult;
    @Wire
    private Textbox msgbox;
    @Wire
    private Button buttonSearch;
    @Wire
    private Label labelLength;
    @Wire
    private Combobox comboVar1;
    @Wire
    private Combobox comboVar2;
    @Wire
    private Checkbox checkboxMismatchOnly;
    
    @Wire
    private Checkbox checkboxAllvarieties;
    @Wire
    //private Combobox comboSubpopulation;
    private Listbox listboxSubpopulation;
    
    @Wire
    private Listbox listboxMyVarieties;
    
    
    @Wire
    private Div divNucleotide;
    @Wire
    private Div divNucleotideIndels;

    @Wire
    private Div divMismatch;
    
    @Wire
    private Div divBlackSyn;


    @Wire
    private Iframe iframeAlignment;
    @Wire
    private Iframe iframeJbrowse;
    @Wire
    private Label msgJbrowse;
    @Wire
    private Iframe iframeJbrowsePhylo;
    @Wire
    private Label msgJbrowsePhylo;
    //@Wire
    //private Grid snpallvarsresult;
   // @Wire
    //private Paging snpallvarsresultpaging;
    @Wire
    private Button buttonDownloadCsv;
    @Wire
    private Button buttonDownloadTab;
    @Wire
    private Button buttonDownloadPlink;
    @Wire
    private Auxhead auxheadpos;
    @Wire
    private Radio radioColorAllele;
    @Wire
    private Radio radioColorMismatch;
    //@Wire
    //private Window win;
    @Wire
    private Iframe iframePhylotree;
    @Wire
    private Tab tabJbrowse;
    @Wire
    private Tab tabMSA;
    @Wire
    private Tab tabPhylo;
    @Wire
    private Listbox selectPhyloTopN;
    
    @Wire
    private Listbox selectPhyloMindist;
    
    
    @Wire
    private Listbox listboxMyLocusList;
    
    @Wire
    private Intbox intPhyloMindist;
    
    @Wire
    private Tab tabJBrowsePhylo;
    @Wire 
    private Tab tabTable;
    @Wire 
    private Tab tabTableLarge;
    @Wire
    private Tabbox tabboxDisplay;
    @Wire
    private Checkbox checkboxCoreSNP;
    @Wire
    private Checkbox checkboxExactMismatch;
    @Wire
    private Label  labelSpliceSNP;
    
    @Wire
    private Listbox selectAllele;
    @Wire
    private Listbox listboxPosition;
    @Wire
    private Listbox listboxAllele;
    
    @Wire
    private Button buttonFilterAllele;
    @Wire
    private Button buttonClearFilterAllele;
    @Wire
    private Hbox hboxFilterAllele;
    @Wire
    private Hbox hboxDownload;
    @Wire
    private Vbox vboxFilterAllele;
    @Wire
    private Listbox listboxMySNPList;
    @Wire
    private Button buttonCreateVarlist;
    @Wire
    private Textbox textboxVarlistName; 
    @Wire
    private Radio radioGraySynonymous;
    @Wire
    private Radio radioNonsynOnly;
    
    @Wire
    private Button buttonRenderTree;
    
    @Wire
    private Button buttonDownloadNoDisplayCSV;
    @Wire
    private Button buttonDownloadNoDisplayTab;
    
    @Wire
    private Button buttonDownloadNoDisplayPlink;
    @Wire
    private Button buttonDownloadNoDisplayHapmap;

    @Wire
    private Checkbox checkboxAlignIndels;
    
    @Wire
    private Div divShowNPBPositions;
    @Wire
    private Checkbox checkboxShowNPBPosition;
	
    @Wire
    private Auxhead auxhead1Header;
    @Wire
    private Auxheader auxheader11Header;
    @Wire
    private Auxhead auxhead2Header;
    @Wire
    private Auxheader auxheader21Header;
    

public SNPQueryController() {
	super();
	// TODO Auto-generated constructor stub
	AppContext.debug("created SNPQueryController " + this);
}

public HttpSession getSession() {
	   return (HttpSession) Executions.getCurrent().getSession().getNativeSession();
}



// *******************************   ACTION BUTTONS HANDLER ********************************************************* 




/**
 * Search button clicked, Query variants 
 * 
 * @param filename	if not null, download result in delimited format, else display in table
 * @param delimiter	delimiter of download file
 */
@Listen("onClick = #buttonSearch")  
//public  void queryVariants(String filename, String delimiter)    {
public  void queryVariants()    {
	
	GenotypeFacade.snpQueryMode mode=null;

	try {
	
	
		// initialize SNP Query displays 
		
		// empty table from previous results
		listboxSnpresult.setModel(new SimpleListModel(new java.util.ArrayList()));

		// hide the tables
		divPairwise.setVisible(false);
		gridBiglistheader.setVisible(false);
		biglistboxArray.setVisible(false);

	    //snpallvarsresult.setWidth("1000px");
		listboxSnpresult.setWidth("1000px");
	    
	    tabTable.setSelected(true);
	    tabTableLarge.setVisible(false);
	    tabJbrowse.setVisible(false);
	    tabMSA.setVisible(false);
	    tabPhylo.setVisible(false);
	    tabJBrowsePhylo.setVisible(false);
	    tabVistaNPB.setVisible(false);
	    tabVistaRev.setVisible(false);
	
	    
		gfffile=null;
		urljbrowse = null;	// if has URL address, will render on TabSelect on JBrowse
		urlphylo = null;   // if has URL address, will render on TabSelect on Phylotree
		tallJbrowse = false;	// jbrowse if tall (for all variety) or short (for 2 varieties)
		urljbrowsephylo = null;
		queryResult = null;
		hboxFilterAllele.setVisible(false);
		hboxDownload.setVisible(false);
		
		labelFilterResult.setValue("");
		lastY=0;
		
		iframePhylotree.setVisible(false);
	
	
		genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	
		
		
		/*
		String var1= comboVar1.getValue().trim().toUpperCase();
		String var2 =  comboVar2.getValue().trim().toUpperCase();
		Set setVarieties = null;
		
		String sSubpopulation = null;
		
		if(checkboxAllvarieties.isChecked()) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		} else if( listboxSubpopulation.getSelectedIndex()>0) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = genotype.getVarietiesForSubpopulation( listboxSubpopulation.getSelectedItem().getLabel() );
			
			if(listboxSubpopulation.getSelectedIndex()>1) sSubpopulation=listboxSubpopulation.getSelectedItem().getLabel();

		} else if( listboxMyVarieties.getSelectedIndex()>0) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = workspace.getVarieties( listboxMyVarieties.getSelectedItem().getLabel()  );
		}
		else {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
			if(var1.isEmpty() || var2.isEmpty() )
			{
				Messagebox.show("Two varieties are required for comparison, or check All Varieties", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;	
			}
		}

	

		String genename = comboGene.getValue().trim().toUpperCase();
		//gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
		
		AppContext.startTimer();

		String sLocus = null;
		if( !genename.isEmpty() )
		{
			// fill the contig,start,stop boxes
			String genename2 = comboGene.getValue().trim();
			Gene gene2 =  genotype.getGeneFromName( genename2, this.listboxReference.getSelectedItem().getLabel());
			
			intStart.setValue(gene2.getFmin() );	
			intStop.setValue(gene2.getFmax() );	
			selectChr.setValue( gene2.getContig().toLowerCase() ); 
			sLocus = genename;
		}
		
			Set snpposlist = null;
			Set locuslist = null;

			
			if(listboxMySNPList.getSelectedIndex()>0) {
				
				String chrlistname[] = listboxMySNPList.getSelectedItem().getLabel().split(":");
				//snpposlist = workspace.getSnpPositions( Integer.valueOf( chrlistname[0].replace("CHR","").trim() ) , chrlistname[1].trim() );
				snpposlist = workspace.getSnpPositions( chrlistname[0].trim()  , chrlistname[1].trim() );
				
			} 
			else if(listboxMyLocusList.getSelectedIndex()>0) {
				
				locuslist = workspace.getLoci(  listboxMySNPList.getSelectedItem().getLabel().trim() );
				
			}
			else if(selcontig.equals("whole genome") ) {
				if( this.listboxSubpopulation.getSelectedIndex() > 0 ) {
					Messagebox.show("Limit to 100 varieties (using a list) for every whole genome download", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
					return;
				}   		
			}
			else {
				
				if(selcontig.isEmpty()) {
					Messagebox.show("Please select chromosome/contig", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					return;
				}
				
				//int chrlen = genotype.getFeatureLength( selectChr.getValue());
				int chrlen = genotype.getFeatureLength(selcontig, this.listboxReference.getSelectedItem().getLabel() ); // .getFeatureLength( selectChr.getValue());
				
				if(intStop==null || intStart==null || !intStop.isValid() || !intStart.isValid() || intStop.getValue()==null || intStart.getValue()==null )
				{
					Messagebox.show("Please provide start and end positions", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					return;  					
				}   
				
				if(intStop.getValue()> chrlen  || intStart.getValue()> chrlen)
				{
					Messagebox.show("Positions should be less than length", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					return;  					
				} 
				if(intStop.getValue()<1  || intStart.getValue()<1)
				{
					Messagebox.show("Positions should be positive integer", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					return;  					
				}   				
				if(intStop.getValue()<intStart.getValue())
				{
					Messagebox.show("End should be greater than or equal to start", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					return;  					
				}  

				int maxlength = -1;
				String limitmsg = "";

				if(!checkboxCoreSNP.isChecked()) {
					maxlength = AppContext.getMaxlengthUni();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for All Snps, or " + AppContext.getMaxlengthCore()/1000 + " kbp for Core Snps, All Varieties query.";
				} else {
					maxlength = AppContext.getMaxlengthCore();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Core Snps, all varieties query";
				}
				
				
				int querylength = intStop.getValue()-intStart.getValue(); 
				if( querylength > maxlength )
				{
					if( querylength > AppContext.getMaxlengthCore() ) { 
						Messagebox.show(limitmsg, "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
						msgbox.setStyle( "font-color:red" );
						return;
					} else
					{
						
						if( Messagebox.show("Query too long for all SNPs, will use Core SNPs instead. Do you want to porceed?", "OPERATION NOT ALLOWED", Messagebox.NO | Messagebox.YES , Messagebox.QUESTION)
								== Messagebox.YES  )
						{
							checkboxCoreSNP.setChecked(true);
							maxlength =  AppContext.getMaxlengthCore();
						}
						else return;
					}
				}   
			}

			*/
		
			if( (mode=validateValues())==null) return;
		
			// gather form entries
			GenotypeQueryParams params =  fillGenotypeQueryParams();

			
			String selcontig = params.getsChr();
			if(this.listboxMySNPList.getSelectedIndex()>0)
				msgbox.setValue("SEARCHING: in chromosome " + selcontig + " , list " + listboxMySNPList.getSelectedItem().getLabel() );
			else if(intStart.getValue()!=null && intStop.getValue()!=null && !selcontig.isEmpty())
				msgbox.setValue("SEARCHING: in chromosome " + selcontig+ " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
			else 
				msgbox.setValue("SEARCHING: in chromosome " +selcontig);


			
			// initialize empty SNP list
			List listSNPs = new java.util.ArrayList();
			
			// all varieties query
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){

				varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade,"VarietyFacade");
				varianttable =  new VariantAlignmentTableArraysImpl();
					
					// Deligate query to API 
					queryRawResult = genotype.queryGenotype( params);

					if(!params.getOrganism().equals(AppContext.getDefaultOrganism())) {
					if(	queryRawResult.getSnpstringdata()!=null) {
						AppContext.debug( "queryRawResult.getSnpstringdata().getMapMSU7Pos2ConvertedPos()=" + queryRawResult.getSnpstringdata().getMapMSU7Pos2ConvertedPos().size() + ": " + queryRawResult.getSnpstringdata().getMapMSU7Pos2ConvertedPos());
					}
					
					if(	queryRawResult.getIndelstringdata()!=null) {
						AppContext.debug( "queryRawResult.getIndelstringdata().getMapMSU7Pos2ConvertedPos()=" +  queryRawResult.getIndelstringdata().getMapMSU7Pos2ConvertedPos().size() + ": " +  queryRawResult.getIndelstringdata().getMapMSU7Pos2ConvertedPos());
					}
					}

					// format result to table
					varianttable = genotype.fillGenotypeTable(varianttable , queryRawResult, params);

					// query variants
					queryResult = varianttable.getVariantStringData();
					
					listSNPs = queryRawResult.getListVariantsString();
					
					varietyfacade= (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
					
					Map<BigDecimal, Object> mapVarid2Phenotype=null;
					String sPhenotype=null;
					if(params.getPhenotype()!=null && !params.getPhenotype().isEmpty()) {
						sPhenotype=params.getPhenotype();
						mapVarid2Phenotype = varietyfacade.getPhenotypeValues(sPhenotype);
					}

					// set table column width
					biglistboxArray.setRowHeight("29px");
					biglistboxArray.setWidth( "1100px" );
					int biglistcolwidth=60;
					int biglistcolwidthOverview=10;
					if(checkboxIndel.isChecked() &&  !checkboxAlignIndels.isChecked()) {
						// display indel in description format
						
						// adjust column width to fill 1000px
						if( queryResult.getListPos().size() < (1000/60-3) )
							biglistcolwidth = 1000/(3+queryResult.getListPos().size());
						else // biglistboxArray.setColWidth("60px");
							biglistcolwidth = 60;
					}
					else {
						// show MSA format
						if( queryResult.getListPos().size() < (1000/30-3) ) {
							biglistcolwidth = 1000/(3+queryResult.getListPos().size());
						}
						else //biglistboxArray.setColWidth("30px"); {
						{
							biglistcolwidth = 30;
						}
						
						if( queryResult.getListPos().size() < (1000/10-3) ) {
							biglistcolwidthOverview = 1000/(3+queryResult.getListPos().size());
						}
						else //biglistboxArray.setColWidth("30px"); {
						{
							biglistcolwidthOverview = 10;
						}
						

						

					}
					biglistboxArray.setColWidth(biglistcolwidth + "px");

					// set table model and renderer
					Object2StringMatrixRenderer renderer  = new Object2StringMatrixRenderer(queryResult,  params);
					biglistboxArray.setMatrixRenderer(renderer);
					biglistboxModel=new Object2StringMatrixModel(varianttable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety(), gridBiglistheader, mapVarid2Phenotype, sPhenotype);
					biglistboxModel.setHeaderRows(biglistboxRows, lastY);
					biglistboxArray.setModel(biglistboxModel); //strRef));
					
					matriccmpproviderAsc = new Object2StringMatrixComparatorProvider<Object[]>(true);
					matriccmpproviderDesc = new Object2StringMatrixComparatorProvider<Object[]>(false);
					biglistboxArray.setSortAscending(matriccmpproviderAsc);
					biglistboxArray.setSortDescending(matriccmpproviderDesc);
					    
					//biglistboxArray.invalidate();
					biglistboxArrayLarge.setSortAscending(matriccmpproviderAsc);
					biglistboxArrayLarge.setSortDescending(matriccmpproviderDesc);
					biglistboxArrayLarge.setWidth( "1100px" );
					biglistboxArrayLarge.setRowHeight("10px");
					biglistboxArrayLarge.setColWidth("10px");
					biglistboxArrayLarge.setStyle("font-size:6px");
					


					// setup header-rows (the frozen list at the left of the bigtable)
					gridBiglistheader.setWidth("200px");
					gridBiglistheader.setOddRowSclass(biglistboxArray.getSclass());
					gridBiglistheader.setSclass( biglistboxArray.getOddRowSclass() );
					
					if(sPhenotype!=null) {
						auxheader3Phenotype.setLabel(sPhenotype);
						gridBiglistheader.getColumns().getLastChild().setVisible(true);
					} else {
						auxheader3Phenotype.setLabel("");
						gridBiglistheader.getColumns().getLastChild().setVisible(false);
					}
					gridBiglistheader.setRowRenderer(new BiglistRowheaderRenderer());
					biglistboxModel.setHeaderRows(biglistboxRows, lastY);
					Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
					
					List headerlist = model.getRowHeaderHeaderList();
					auxheadAlleles1.setVisible(false);
					auxheadAlleles2.setVisible(false);
					auxheadAlleles3.setVisible(false);
					auxheadAlleles4.setVisible(false);
					
					AppContext.debug("headers: " + headerlist);

					// display or hide header rows based on reference and reference alleles options
					biglistboxRows = 20;
					
					if(!params.getOrganism().equals(AppContext.getDefaultOrganism())) {
						
						// reference is not nipponbare
						if(params.isbShowNPBPositions()) {
							this.auxheader11Header.setLabel( params.getOrganism() + " positions" );
							this.auxheader21Header.setLabel(params.getOrganism() + " allele" );
							this.auxheader3Header.setLabel("Nipponbare positions" );
							this.column4Header.setLabel("Nipponbare" );
							this.auxhead1Header.setVisible(true);
							this.auxhead2Header.setVisible(true);
							biglistboxRows = 16;
							
							if(headerlist.size()>4) {
								this.auxheaderAlleles1.setLabel((String)headerlist.get(4));
								this.auxheadAlleles1.setVisible(true);
								biglistboxRows--;
							}
							if(headerlist.size()>5) {
								this.auxheaderAlleles2.setLabel((String)headerlist.get(5));
								this.auxheadAlleles2.setVisible(true);
								biglistboxRows--;
							}
							if(headerlist.size()>6) {
								this.auxheaderAlleles3.setLabel((String)headerlist.get(6));
								this.auxheadAlleles3.setVisible(true);
								biglistboxRows--;
							}
							
						} else {
							this.auxheader3Header.setLabel( params.getOrganism() + " positions" );
							this.column4Header.setLabel(params.getOrganism() + " allele" );
							this.auxhead1Header.setVisible(false);
							this.auxhead2Header.setVisible(false);
							
							if(headerlist.size()>2) {
								this.auxheaderAlleles1.setLabel((String)headerlist.get(2));
								this.auxheadAlleles1.setVisible(true);
								biglistboxRows--;
							}
							if(headerlist.size()>3) {
								this.auxheaderAlleles2.setLabel((String)headerlist.get(3));
								this.auxheadAlleles2.setVisible(true);
								biglistboxRows--;
							}
							if(headerlist.size()>4) {
								this.auxheaderAlleles3.setLabel((String)headerlist.get(4));
								this.auxheadAlleles3.setVisible(true);
								biglistboxRows--;
							}
							if(headerlist.size()>5) {
								this.auxheaderAlleles4.setLabel((String)headerlist.get(5));
								this.auxheadAlleles4.setVisible(true);
								biglistboxRows--;
							}
						}
							
					} else {
						
						// reference is nipponbare
						
						this.auxheader3Header.setLabel("Nipponbare positions" );
						this.column4Header.setLabel("Nipponbare" );
						this.auxhead1Header.setVisible(false);
						this.auxhead2Header.setVisible(false);
						
						if(headerlist.size()>2) {
							this.auxheaderAlleles1.setLabel((String)headerlist.get(2));
							this.auxheadAlleles1.setVisible(true);
							biglistboxRows--;
						}
						if(headerlist.size()>3) {
							this.auxheaderAlleles2.setLabel((String)headerlist.get(3));
							this.auxheadAlleles2.setVisible(true);
							biglistboxRows--;
						}
						if(headerlist.size()>4) {
							this.auxheaderAlleles3.setLabel((String)headerlist.get(4));
							this.auxheadAlleles3.setVisible(true);
							biglistboxRows--;
						}
						if(headerlist.size()>5) {
							this.auxheaderAlleles4.setLabel((String)headerlist.get(5));
							this.auxheadAlleles4.setVisible(true);
							biglistboxRows--;
						}
					}
					// adjust row header lines
					gridBiglistheader.setModel( new BiglistRowheaderModel(  model.getRowHeaderList(biglistboxRows )));

					// set message
					msgbox.setValue(new  String(msgbox.getValue() +  varianttable.getMessage()).replace("\n\n", "\n"));
					
					
					getSession().putValue("queryResult", queryRawResult);
					getSession().putValue("variantTable", varianttable);
					
					if(queryResult==null) throw new RuntimeException("queryResult==null");
					if(queryResult.getMapIdx2Pos()==null) throw new RuntimeException("queryResult.getMapIdx2Pos==null");
					
					divIndelLegend.setVisible(checkboxIndel.isChecked());
					divAlignIndels.setVisible(AppContext.isLocalhost());

					gridBiglistheader.setVisible(true);
					biglistboxArray.setVisible(true);
					
					buttonDownloadPlink.setDisabled( checkboxIndel.isChecked() );
					
					updateVista(params,varianttable);
					
					AppContext.logSystemStatus();
				
					tallJbrowse = true;
		        
		        if( queryResult.getListPos()!=null) {
			        if(listSNPs.size()>0 )
			        	{
			        		// setup allele filters
			        	 	java.util.List listPosition = new java.util.ArrayList();
			        		listPosition.add("");
			        		
			        		model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
			        		int poscol=-1;
			        		List listPos = (List)model.getHeadAt(0);
			        		for(int i=0; i<model.getColumnSize(); i++) {
			        			listPosition.add( listPos.get(i).toString() );
			        		}
			        		
			        		listboxPosition.setModel( new SimpleListModel(listPosition));
			        		labelFilterResult.setValue("None .. " + listSNPs.size() + " varieties");
			        		hboxFilterAllele.setVisible(true);
			        		hboxDownload.setVisible(true);
			        	}
		        } 
			}
			else {	
				// SNPs on 2 varieties in region
					
					varianttable =  new VariantAlignmentTableArraysImpl();

					String var1= comboVar1.getValue().trim().toUpperCase();
					String var2 =  comboVar2.getValue().trim().toUpperCase();

					//params.setbMismatchonly(false);
					params.setPairwiseComparison( var1, var2);
					
					varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade,"VarietyFacade");
					queryRawResult = genotype.compare2Varieties( varietyfacade.getGermplasmByName(var1).getVarietyId(),
							varietyfacade.getGermplasmByName(var2).getVarietyId(),  params);
					varianttable = genotype.fillGenotypeTable(varianttable , queryRawResult, params);
					queryResult = varianttable.getVariantStringData();

					// update table header
					Listheader lhr = (Listheader)listboxSnpresult.getListhead().getFirstChild();    	
					
					String var1header = this.comboVar1.getValue();
					String var2header = this.comboVar2.getValue();
					
					if(queryResult.getListVariantsString().size()==2) {
						var1header=varietyfacade.getMapId2Variety().get( queryResult.getListVariantsString().get(0).getVar() ).getName();
						var2header=varietyfacade.getMapId2Variety().get( queryResult.getListVariantsString().get(1).getVar() ).getName();
					}
					
					
					//if(! params.getOrganism().equals(AppContext.getDefaultOrganism()) ) {
					if(params.isbShowNPBPositions()) {
						auxheaderMultirefPairwise.setLabel(params.getOrganism().toUpperCase());
						auxheadMultirefPairwise.setVisible(true);
						listheader1MultirefPairwise.setVisible(true);
						listheader2MultirefPairwise.setVisible(true);
						listheader3MultirefPairwise.setVisible(true);
					} else {
						auxheadMultirefPairwise.setVisible(false);
						listheader1MultirefPairwise.setVisible(false);
						listheader2MultirefPairwise.setVisible(false);
						listheader3MultirefPairwise.setVisible(false);
					}
					
					listheaderVar1MultirefPairwise.setLabel(var1header);
					listheaderVar2MultirefPairwise.setLabel(var2header);
					  
					listboxSnpresult.setItemRenderer( new PairwiseListItemRenderer(queryResult, params));
					listboxSnpresult.setModel( new SimpleListModel( ((VariantAlignmentTableArraysImpl)varianttable).getCompare2VarsList( this.selectChr.getValue(), params ) ));
					divPairwise.setVisible(true);
					
					listSNPs = queryRawResult.getListVariantsString();
							
					gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
					create2VarietiesGFF(listSNPs, gfffile, queryResult.getListPos(), queryResult.getRefnuc() );
					
					msgbox.setValue(new  String(msgbox.getValue() + " ... RESULT: " +  listboxSnpresult.getModel().getSize() + " positions" 
							+  varianttable.getMessage()).replace("\n\n", "\n"));
					
					tallJbrowse= false;
					
			}

			
			if(mode==GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS  && listSNPs.size()>0)
			{
				// non-empty all-variety query, display JBrowse, Phylo and Alignment tabs
				
				if(listboxMySNPList.getSelectedIndex()>0) {
					tabJbrowse.setVisible(false);
					tabTableLarge.setVisible(false);
					tabPhylo.setVisible(false);
					tabMSA.setVisible(false);
				}
				else {
					if(checkboxIndel.isChecked() && !checkboxAlignIndels.isChecked()) 
						tabMSA.setVisible(false);	
					else tabMSA.setVisible(true);

					String chr = selectChr.getValue().trim();
					if(!chr.isEmpty()){
						updateJBrowse(chr,  intStart.getValue().toString() , intStop.getValue().toString(), "");
						tabJbrowse.setVisible(true);
						
						if(tallJbrowse) {
							update_phylotree(chr.toUpperCase().replace("CHR0","").replace("CHR",""), intStart.getValue().toString() , intStop.getValue().toString() , listSNPs.size() );
							tabPhylo.setVisible(true);
						}
						
						//tabTableLarge.setVisible(true);

					}
					allelefreqlines=null;
					calculateAlleleFrequencies();
					hboxDownload.setVisible(true);
					
					tabVistaNPB.setVisible(true);
					//tabVistaRev.setVisible(true);
				}
				
				divTablePanel.setVisible(true);
				divPairwise.setVisible(false);
				
			} else if(mode==GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES && listSNPs.size()>0) {
				
				// show two-varieties table
				tabJbrowse.setVisible(true);
				msgJbrowse.setVisible(false);
				divTablePanel.setVisible(false);
				//listboxSnpresult.setVisible(true);
			}
			else {
				tabJbrowse.setVisible(false);
				tabPhylo.setVisible(false);
				tabMSA.setVisible(false);
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
				divTablePanel.setVisible(false);
			}
	
	} catch(InvocationTargetException ex) {
		ex.getCause().printStackTrace();
		Messagebox.show( ex.getCause().getMessage(), "InvocationTargetException", Messagebox.OK, Messagebox.ERROR);
		
	} catch(Exception ex) {
		ex.printStackTrace();
		Messagebox.show( ex.getMessage(), "Exception", Messagebox.OK, Messagebox.ERROR);
	}

}


/**
 * Validate values in form
 * @return GenotypeFacade.snpQueryMode, null if invalid
 */
private GenotypeFacade.snpQueryMode validateValues() {
	
	String var1= comboVar1.getValue().trim().toUpperCase();
	String var2 =  comboVar2.getValue().trim().toUpperCase();
	Set setVarieties = null;
	String selcontig = selectChr.getValue();
	
	String sSubpopulation = null;
	GenotypeFacade.snpQueryMode mode=null;
	
	if(checkboxAllvarieties.isChecked()) {
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
	} else if( listboxSubpopulation.getSelectedIndex()>0) {
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		setVarieties = genotype.getVarietiesForSubpopulation( listboxSubpopulation.getSelectedItem().getLabel() );
		
		if(listboxSubpopulation.getSelectedIndex()>1) sSubpopulation=listboxSubpopulation.getSelectedItem().getLabel();

	} else if( listboxMyVarieties.getSelectedIndex()>0) {
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		setVarieties = workspace.getVarieties( listboxMyVarieties.getSelectedItem().getLabel()  );
	}
	else {
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
		if(var1.isEmpty() || var2.isEmpty() )
		{
			Messagebox.show("Two varieties are required for comparison, or check All Varieties", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
			return null;	
		}
	}


	String genename = comboGene.getValue().trim().toUpperCase();
	//gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
	
	AppContext.startTimer();

	String sLocus = null;
	if( !genename.isEmpty() )
	{
		// fill the contig,start,stop boxes
		String genename2 = comboGene.getValue().trim();
		Gene gene2 =  genotype.getGeneFromName( genename2, this.listboxReference.getSelectedItem().getLabel());
		
		intStart.setValue(gene2.getFmin() );	
		intStop.setValue(gene2.getFmax() );	
		selectChr.setValue( gene2.getContig().toLowerCase() ); 
		sLocus = genename;
	}
	
		Set snpposlist = null;
		Set locuslist = null;

		
		if(listboxMySNPList.getSelectedIndex()>0) {
			
			String chrlistname[] = listboxMySNPList.getSelectedItem().getLabel().split(":");
			//snpposlist = workspace.getSnpPositions( Integer.valueOf( chrlistname[0].replace("CHR","").trim() ) , chrlistname[1].trim() );
			snpposlist = workspace.getSnpPositions( chrlistname[0].trim()  , chrlistname[1].trim() );
			
		} 
		else if(listboxMyLocusList.getSelectedIndex()>0) {
			
			locuslist = workspace.getLoci(  listboxMySNPList.getSelectedItem().getLabel().trim() );
			
		}
		else if(selcontig.equals("whole genome") ) {
			if( this.listboxSubpopulation.getSelectedIndex() > 0 ) {
				Messagebox.show("Limit to 100 varieties (using a list) for every whole genome download", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
				return null;
			}   		
		}
		else {
			
			if(selcontig.isEmpty()) {
				Messagebox.show("Please select chromosome/contig", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return  null;
			}
			
			//int chrlen = genotype.getFeatureLength( selectChr.getValue());
			int chrlen = genotype.getFeatureLength(selcontig, this.listboxReference.getSelectedItem().getLabel() ); // .getFeatureLength( selectChr.getValue());
			
			if(intStop==null || intStart==null || !intStop.isValid() || !intStart.isValid() || intStop.getValue()==null || intStart.getValue()==null )
			{
				Messagebox.show("Please provide start and end positions", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return  null;  					
			}   
			
			if(intStop.getValue()> chrlen  || intStart.getValue()> chrlen)
			{
				Messagebox.show("Positions should be less than length", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return  null;  					
			} 
			if(intStop.getValue()<1  || intStart.getValue()<1)
			{
				Messagebox.show("Positions should be positive integer", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return  null;  					
			}   				
			if(intStop.getValue()<intStart.getValue())
			{
				Messagebox.show("End should be greater than or equal to start", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return  null;  					
			}  

			int maxlength = -1;
			String limitmsg = "";

			if(!checkboxCoreSNP.isChecked()) {
				maxlength = AppContext.getMaxlengthUni();
				limitmsg = "Limit to " + (maxlength/1000) + " kbp range for All Snps, or " + AppContext.getMaxlengthCore()/1000 + " kbp for Core Snps, All Varieties query.";
			} else {
				maxlength = AppContext.getMaxlengthCore();
				limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Core Snps, all varieties query";
			}
			
			
			int querylength = intStop.getValue()-intStart.getValue(); 
			if( querylength > maxlength )
			{
				if( querylength > AppContext.getMaxlengthCore() ) { 
					Messagebox.show(limitmsg, "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
					msgbox.setStyle( "font-color:red" );
					return  null;
				} else
				{
					
					if( Messagebox.show("Query too long for all SNPs, will use Core SNPs instead. Do you want to porceed?", "OPERATION NOT ALLOWED", Messagebox.NO | Messagebox.YES , Messagebox.QUESTION)
							== Messagebox.YES  )
					{
						checkboxCoreSNP.setChecked(true);
						maxlength =  AppContext.getMaxlengthCore();
					}
					else return  null;
				}
			}   
		}
		return mode;
}


@Listen("onClick = #buttonDownloadNoDisplayTab")
public void downloadNoDisplayTab() {
	Clients.showBusy("Fetching from Database");
	downloadGenome("snp3k","\t");
	Clients.clearBusy();
	
}

@Listen("onClick = #buttonDownloadNoDisplayCSV")
public void downloadNoDisplayCsv() {
	AppContext.debug("downloading csv");
	Clients.showBusy("Fetching from Database");
	downloadGenome("snp3k",",");
	Clients.clearBusy();
}

@Listen("onClick = #buttonDownloadNoDisplayPlink")
public void downloadNoDisplayPlink() {
	AppContext.debug("downloading plink");
	Clients.showBusy("Fetching from Database");
	downloadGenome("snp3k","plink");
	Clients.clearBusy();
}
@Listen("onClick = #buttonDownloadNoDisplayHapmap")
public void downloadNoDisplayHapmap() {
	AppContext.debug("downloading hapmap");
	Clients.showBusy("Fetching from Database");
	downloadGenome("snp3k","hapmap");
	Clients.clearBusy();
}


/**
 * read the query components to GenotypeQueryParams
 * @return
 */
private GenotypeQueryParams fillGenotypeQueryParams() {

	
	
	Set setVarieties = null;
	String sSubpopulation = null;
	
	genotype = (GenotypeFacade)AppContext.checkBean(genotype,"GenotypeFacade");
	workspace = (WorkspaceFacade)AppContext.checkBean(workspace,"WorkspaceFacade");
	
	String contig =  selectChr.getValue();
	 
	if( listboxSubpopulation.getSelectedIndex()>0) {
		setVarieties = genotype.getVarietiesForSubpopulation( listboxSubpopulation.getSelectedItem().getLabel() );
		if(listboxSubpopulation.getSelectedIndex()>1) sSubpopulation=listboxSubpopulation.getSelectedItem().getLabel();
	
	} else if( listboxMyVarieties.getSelectedIndex()>0) {
		setVarieties = workspace.getVarieties( listboxMyVarieties.getSelectedItem().getLabel()  );
	}
	
	Set snpposlist = null;
	if(listboxMySNPList.getSelectedIndex()>0) {
		String chrlistname[] = listboxMySNPList.getSelectedItem().getLabel().split(":");
		//snpposlist = workspace.getSnpPositions( Integer.valueOf( chrlistname[0].replace("CHR","").trim() ) , chrlistname[1].trim() );
		snpposlist = workspace.getSnpPositions( chrlistname[0].trim()  , chrlistname[1].trim() );
		contig =  chrlistname[0].trim(); 
	}
	
	Set locuslist = null;
	if(listboxMyLocusList.getSelectedIndex()>0) {
		locuslist = workspace.getLoci( listboxMyLocusList.getSelectedItem().getLabel() );
		contig = "loci";
	}

	
	String genename = comboGene.getValue().trim().toUpperCase();
	String sLocus = null;
	if( !genename.isEmpty() ) sLocus = genename;
	
	
	Long lStart = null;
	Long lStop = null;
	if(intStart.getValue()!=null) lStart= new Long( intStart.getValue());
	if(intStop.getValue()!=null) lStop= new Long( intStop.getValue());
	
	GenotypeQueryParams params = new GenotypeQueryParams(setVarieties, contig , lStart,
			lStop ,  checkboxSNP.isChecked() , checkboxIndel.isChecked(), checkboxCoreSNP.isChecked() ,
			checkboxMismatchOnly.isChecked(), snpposlist,  sSubpopulation, sLocus, checkboxAlignIndels.isChecked(), checkboxShowAllRefAlleles.isChecked());
	//params.setColors(this.radioGraySynonymous.isSelected(), this.radioNonsynOnly.isSelected() , true  , this.radioColorMismatch.isSelected(), this.radioColorAllele.isSelected());
	
	params.setColors(this.radioColorMismatch.isSelected(), this.radioColorAllele.isSelected());
	params.setIncludedSnps(this.radioAllSnps.isSelected(), this.radioNonsynHighlights.isSelected(), this.radioNonsynSnps.isSelected(), this.radioNonsynSnpsPlusSplice.isSelected());
	if(this.comboVar1.getValue()!=null && !this.comboVar1.getValue().isEmpty() && this.comboVar2.getValue()!=null && !this.comboVar2.getValue().isEmpty()) {
		params.setPairwiseComparison(comboVar1.getValue(), comboVar2.getValue());
		
		if(checkboxIndel.isChecked()) {
			Messagebox.show("Indels not yet included in pairwise comparison.");
			params.setbIndel(false);
			checkboxIndel.setChecked(false);
		}
		
	}
	
	params.setbHeteroIndel( checkboxHeteroindels.isChecked());
	params.setColLoci(locuslist);
	params.setOrganism(listboxReference.getSelectedItem().getLabel(), this.checkboxShowNPBPosition.isChecked(), true);
	
	if(listboxPhenotype.getSelectedItem()!=null)
		params.setPhenotype(listboxPhenotype.getSelectedItem().getLabel());
	
	return params;
	
}




/**
 * Clears all inputs
 */
@Listen("onClick = #buttonReset")    
public void reset()    {
    comboVar1.setValue("");
    comboVar2.setValue("");
    checkboxMismatchOnly.setChecked(true);
    checkboxAllvarieties.setChecked(true);
    
    this.listboxMyVarieties.setSelectedIndex(0);
    //this.comboSubpopulation.setValue("");
    listboxSubpopulation.setSelectedIndex(0);
    
    intStart.setValue(null);
    intStop.setValue(null);
    comboGene.setValue("");
    selectChr.setValue("");
    this.listboxMySNPList.setSelectedIndex(0);
    this.listboxMyLocusList.setSelectedIndex(0);
    
    this.checkboxCoreSNP.setChecked(false);
    this.selectPhyloTopN.setSelectedIndex(0);
    
    this.radioColorMismatch.setSelected(true);
    this.listboxPhenotype.setSelectedIndex(0);
    
    msgbox.setValue("Select varieties, then set chromosome region or gene locus");
    
    this.listboxPhenotype.setSelectedIndex(0);
    this.checkboxIndel.setChecked(false);
    
    setchrlength();
}


//***********************************************  HANDLE QUERY INTERFACE EVENTS **********************************************


@Listen("onClick =#checkboxShowAllRefAlleles")
public void onclickShowallrefss() {
	if(checkboxShowAllRefAlleles.isChecked()) {
		this.checkboxIndel.setChecked(false);
		this.checkboxIndel.setDisabled(true);
	} else {
		this.checkboxIndel.setDisabled(false);
	}
}


@Listen("onClick =#checkboxIndel")
public void onclickIndels() {
	if(checkboxIndel.isChecked()) {
		// disable display of other references for INDELS
		this.checkboxShowAllRefAlleles.setChecked(false);
		this.checkboxShowAllRefAlleles.setDisabled(true);
	} else {
		this.checkboxShowAllRefAlleles.setDisabled(false);
	}
}


@Listen("onClick =#radioColorMismatch")
public void onclickcolorMismatch() {
	try {
		if(biglistboxArray==null) return;
		//Object2StringMatrixRenderer renderer =  (Object2StringMatrixRenderer)this.biglistboxArray.getMatrixRenderer();
		Object2StringMatrixModel model  =  (Object2StringMatrixModel)this.biglistboxArray.getModel();
		 if(model==null || model.getData()==null) return;
		
		Object2StringMatrixRenderer renderer = new Object2StringMatrixRenderer( model.getData().getVariantStringData(),   fillGenotypeQueryParams() );
		biglistboxArray.setMatrixRenderer(renderer);
		this.divMismatch.setVisible(true);
		this.divNucleotide.setVisible(false);
	} catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}

@Listen("onClick =#radioColorAllele")
public void onclickcolorAllele() {
	try {
		if(biglistboxArray==null) return;
		Object2StringMatrixModel model  =  (Object2StringMatrixModel)this.biglistboxArray.getModel();
		if(model==null || model.getData()==null) return;
		
		Object2StringMatrixRenderer renderer = new Object2StringMatrixRenderer( model.getData().getVariantStringData(),   fillGenotypeQueryParams() );
		biglistboxArray.setMatrixRenderer(renderer);
		this.divNucleotide.setVisible(true);
		this.divMismatch.setVisible(false);
	} catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}

@Listen("onSelect =#listboxReference") 
public void onselectReference() {
	
	try {
		genotype = (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		
		
		String selOrg = listboxReference.getSelectedItem().getLabel();

		// get contigs for selected reference
		listContigs = AppContext.createUniqueUpperLowerStrings(genotype.getContigsForReference(  selOrg ), true,true);
		
		// get loci for reference
		listLoci = AppContext.createUniqueUpperLowerStrings(  genotype.getLociForReference( selOrg ), true,true);

		AppContext.debug(selectChr.getChildren().size() + " contigs, " + listLoci.size() + " loci in combobox ");
		
		selectChr.setModel(new SimpleListModel(listContigs));
		comboGene.setModel( new SimpleListModel(listLoci) );
		
		if(selOrg.equals("93-11"))
			labelChrExample.setValue("(ex. 9311_chr01, scaffold01_4619) " );
		else if(selOrg.equals("Kasalath"))
			labelChrExample.setValue("(ex. chr01, um) " );
		else
			labelChrExample.setValue("(ex. " + listContigs.get(1).toString().toLowerCase() + ") " );
		
		if(selOrg.equals("93-11"))
			labelChr.setValue("Chromosome/Scaffold");
		else if( selOrg.equals(AppContext.getDefaultOrganism()) || selOrg.equals("Kasalath"))
			labelChr.setValue("Chromosome");
		else 
			labelChr.setValue("Scaffold");
		
		selectChr.setValue("");
		
		// hide/disable some features if reference is not Nipponbare
		if(!selOrg.equals( AppContext.getDefaultOrganism())) {
			checkboxShowNPBPosition.setChecked(false);
			divShowNPBPositions.setVisible(true);
			comboGene.setValue("");
			//this.comboGene.setDisabled(true);
			listboxMySNPList.setSelectedIndex(0);
			this.listboxMySNPList.setDisabled(true);
			this.listboxMyLocusList.setDisabled(true);
		} else {
			checkboxShowNPBPosition.setChecked(false);
			divShowNPBPositions.setVisible(false);
			//this.comboGene.setDisabled(false);
			this.listboxMySNPList.setDisabled(false);
			this.listboxMyLocusList.setDisabled(false);
		}
		
	} catch(Exception ex) {
		ex.printStackTrace();
	}
}

/**
 * Update SNP types to display, no requery just update in displays
 */
@Listen("onCheck = #radiogroupShowsnps") 
public void oncheckShowsnps() {
	if(this.queryRawResult==null) return;

	if(this.divPairwise.isVisible()) {
		try {
		GenotypeQueryParams params =  fillGenotypeQueryParams();
		updatePairwiseListbox(params);
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "Exception in oncheckShowsnps.updatePairwiseListbox", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	if(biglistboxArray.isVisible()) {
		try {
		GenotypeQueryParams params =  fillGenotypeQueryParams();
		updateBiglistboxArray(params);
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "Exception in oncheckShowsnps.updateBiglistboxArray", Messagebox.OK, Messagebox.ERROR);
		}
	}
}


private void updatePairwiseListbox(GenotypeQueryParams params) {
	listboxSnpresult.setItemRenderer( new PairwiseListItemRenderer(queryResult, params));
	listboxSnpresult.setModel( new SimpleListModel( ((VariantAlignmentTableArraysImpl)varianttable).getCompare2VarsList( this.selectChr.getValue(), params ) ));
	divPairwise.setVisible(true);
	
	String msgs[]= msgbox.getValue().split("...");
	if(msgs.length>0)
		msgbox.setValue(msgs[0] + " ... RESULT: " +  listboxSnpresult.getModel().getSize() + " positions" );
	else msgbox.setValue(msgbox.getValue() + " ... RESULT: " +  listboxSnpresult.getModel().getSize() + " positions" );

}




@Listen("onScrollY = #biglistboxArray")
public void onScrollYTable(ScrollEventExt event) {
	
	//AppContext.debug("onScrollY:" +  event.getName() + " " + event.getX() + " " + event.getY());
	int firstrow = event.getY();
	if(	firstrow%2 == 0)
	{
		gridBiglistheader.setOddRowSclass( biglistboxArray.getSclass() );
		gridBiglistheader.setSclass( biglistboxArray.getOddRowSclass() );
	} else {
		gridBiglistheader.setSclass( biglistboxArray.getSclass() );
		gridBiglistheader.setOddRowSclass( biglistboxArray.getOddRowSclass() );
	}
	
	lastY=event.getY();
	biglistboxModel.setHeaderRows(biglistboxRows, lastY);
	
	Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	//gridBiglistheader.setModel( new SimpleListModel(  model.getRowHeaderList(biglistboxRows, lastY) ));
	gridBiglistheader.setModel( new BiglistRowheaderModel(  model.getRowHeaderList(biglistboxRows, lastY) ));
	
	
	
	//gridBiglistheader.setModel( new SimpleListModel(((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList(biglistboxRows, event.getY()) ));


}

// biglistbox (Variant Table) events


@Listen("onSort =#biglistboxArray") 
public void onsortBiglistbox(SortEventExt event) {
	
	Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	getSession().putValue("variantTable", model.getData());
	gfffile=null;
	//AppContext.debug("reset gff=null");
}


/**
 *  Handle create variety list from table
 */

@Listen("onClick = #buttonCreateVarlist")
public void onclickCreateVarlist() {
	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade , "VarietyFacade");
	
	Map<String,Variety> mapName2Var = varietyfacade.getMapVarname2Variety();
	Set setVarieties = new LinkedHashSet();
	Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	for(int i=0; i<model.getSize(); i++) {
		Object  varname =  ((Object[])model.getElementAt(i).get(0))[0];
		setVarieties.add( mapName2Var.get(varname) );
	}
	workspace.addVarietyList( textboxVarlistName.getValue().trim(), setVarieties);
	textboxVarlistName.setValue("");
	
	
}

/**
 *  Handle variety list selections
 */
@Listen("onSelect = #listboxMyVarieties")    
public void  onselectMyVarieties() {
	if(listboxMyVarieties.getSelectedIndex()>0) {
		listboxSubpopulation.setSelectedIndex(0);
		checkboxAllvarieties.setChecked(false);
		comboVar1.setValue("");
		comboVar2.setValue("");
		
		if( listboxMyVarieties.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety&src=snp");
		}
	}
}

/**
 * Handle locus list selections
 */
@Listen("onSelect = #listboxMyLocusList")    
public void  onselectMyLocusList() {
	if(listboxMyLocusList.getSelectedIndex()>0) {

		if( listboxMyLocusList.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=locus&src=snp");
		}
		else {
			this.selectChr.setValue("");
			this.comboGene.setValue("");
			this.intStart.setValue(null);
			this.intStop.setValue(null);
			listboxMySNPList.setSelectedIndex(0);
			
			this.listboxReference.setSelectedIndex(0); 
			this.checkboxShowAllRefAlleles.setChecked(false);
			this.checkboxShowAllRefAlleles.setDisabled(true);

			this.checkboxShowNPBPosition.setChecked(false);
			this.divShowNPBPositions.setVisible(false);

		}
	} else {

		this.divShowNPBPositions.setVisible(false);
	}
}

/**
 * Handle SNP list selections
 */
@Listen("onSelect = #listboxMySNPList")    
public void  onselectMySNPList() {
	if(listboxMySNPList.getSelectedIndex()>0) {

		if( listboxMySNPList.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=snp&src=snp");
		}
		else {
			String selChr = listboxMySNPList.getSelectedItem().getLabel().split(":")[0].toUpperCase();
			if(selChr.equals("ANY")) {
				//selectChr.setValue("");
				selectChr.setValue("");
				//listboxMySNPList.setSelectedIndex(0);
				//Messagebox.show("Multiple chromosome/contig SNP query not yet implemented");
			} else {
				try {
					//this.selectChr.setSelectedIndex( Integer.parseInt( selChr.replace("CHR ","").replace("CHR0","").replace("CHR","") ));
					this.selectChr.setValue( selChr.toLowerCase() );
					listboxMyLocusList.setSelectedIndex(0);
				}catch( Exception ex) {
					ex.printStackTrace();
				}
			}
			this.comboGene.setValue("");
			this.intStart.setValue(null);
			this.intStop.setValue(null);
			
			this.listboxReference.setSelectedIndex(0); 
			this.checkboxShowAllRefAlleles.setChecked(false);
			this.checkboxShowAllRefAlleles.setDisabled(true);
			this.checkboxShowNPBPosition.setChecked(false);

			this.divShowNPBPositions.setVisible(false);
			
		}
	} else {
		this.checkboxShowAllRefAlleles.setDisabled(false);
	}
}


@Listen("onClick = #checkboxAllvarieties")
public void  oncheckAllVarieties() {
	if(checkboxAllvarieties.isChecked()) {
		//comboSubpopulation.setValue("");
		listboxSubpopulation.setSelectedIndex(0);
		listboxMyVarieties.setSelectedIndex(0);
		comboVar1.setValue("");
		comboVar2.setValue("");
	}
}




@Listen("onSelect = #listboxSubpopulation")
public void  onselectSubpopulation() {
	//if(comboSubpopulation.getSelectedIndex()>0) {
	if(listboxSubpopulation.getSelectedIndex()>0) {
		checkboxAllvarieties.setChecked(false); 
		listboxMyVarieties.setSelectedIndex(0);
		comboVar1.setValue("");
		comboVar2.setValue("");
		
		
		if(listboxSubpopulation.getSelectedItem().getLabel().equals("all varieties")) {
			checkboxAllvarieties.setChecked(true);
		} else {
			checkboxAllvarieties.setChecked(false);
		}
	}
}

@Listen("onChange = #comboVar1") 
public void  onchangedVar1() {
	if(!comboVar1.getValue().isEmpty() || !comboVar2.getValue().isEmpty()) {
		checkboxAllvarieties.setChecked(false); 
		listboxMyVarieties.setSelectedIndex(0);
		//comboSubpopulation.setValue("");
		listboxSubpopulation.setSelectedIndex(0);
	}
}
@Listen("onChange = #comboVar2") 
public void  onchangedVar2() {
	if(!comboVar1.getValue().isEmpty() || !comboVar2.getValue().isEmpty()) {
		checkboxAllvarieties.setChecked(false); 
		listboxMyVarieties.setSelectedIndex(0);
		//comboSubpopulation.setValue("");
		listboxSubpopulation.setSelectedIndex(0);
	}
}

@Listen("onChange = #comboGene")
public void onchangeGene() {
	onSelectGene();
}

@Listen("onSelect = #comboGene")
public void onSelectGene() {
	if(!comboGene.getValue().isEmpty()) {
		try {
		genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
		Gene gene = genotype.getGeneFromName( comboGene.getValue(), this.listboxReference.getSelectedItem().getLabel() );
		
		selectChr.setValue(gene.getContig().toLowerCase());
		
		/*
		SimpleListModel model=(SimpleListModel)selectChr.getModel();
		int lcount=0;
		String genecontig=gene.getContig().toUpperCase();
		for(int i=0; i<model.getSize(); i++ ) {
			String item = (String)model.getElementAt(i);
			if(item.toUpperCase().equals(genecontig  )) {
				AppContext.debug( model.getSize() + " contigs, selecting contig " + genecontig + " at index " + lcount );
				break;
			}
			lcount++;
		}
		*/
		
		/*
		Iterator<Comboitem> itCombo = selectChr.getItems().iterator();
		int lcount=0;
		while(itCombo.hasNext()) {
			Comboitem item = itCombo.next();
			if(item.getLabel().equalsIgnoreCase(  gene.getContig()  )) break;
			lcount++;
		}
		*/
		
		//selectChr.setSelectedIndex( Integer.valueOf( AppContext.guessChrFromString(gene.getChr())));
		//selectChr.setSelectedIndex( lcount );
		//selectChr.setSelectedItem( selectChr.getItemAtIndex(lcount)  );
		
		

		
		intStart.setValue(gene.getFmin());
		intStop.setValue(gene.getFmax());
		listboxMySNPList.setSelectedIndex(0);
		this.listboxMyLocusList.setSelectedIndex(0);
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

/**
 * Chromosome selected
 */
@Listen("onSelect = #selectChr")
public void setchrlength() {
	
	try {
	
	if( selectChr.getValue().trim().isEmpty()) return;
	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
	
	listboxMySNPList.setSelectedIndex(0);
	comboGene.setValue("");
	
	
	if( selectChr.getValue().equals("whole genome") ) {
		this.intStart.setValue(null);
		this.intStop.setValue(null);
		//labelLength.setValue("End:");
		
		this.intStart.setDisabled(true);
		intStop.setDisabled(true);
		this.checkboxCoreSNP.setChecked(true);
		checkboxCoreSNP.setDisabled(true);
		this.buttonSearch.setDisabled(true);
		
		buttonsDownloadNodisplay.setVisible(true);
		
		this.listboxSubpopulation.setSelectedIndex(0);
		listboxSubpopulation.setDisabled(true);
		this.comboGene.setValue("");
		comboGene.setDisabled(true);
		this.listboxMySNPList.setSelectedIndex(0);
		listboxMySNPList.setDisabled(true);
		
		
	} else {
		Integer length = getchrLength();
		labelLength.setValue(length + " bp" );
		//labelLength.setValue("End:" );
		//chrlength=length.intValue();
		
		checkboxCoreSNP.setDisabled(false);
		this.buttonSearch.setDisabled(false);
		this.intStart.setDisabled(false);
		intStop.setDisabled(false);
		
		buttonsDownloadNodisplay.setVisible(false);
		
		listboxSubpopulation.setDisabled(false);
		
		if(listboxReference.getSelectedItem().getLabel().equals(AppContext.getDefaultOrganism())) {
			comboGene.setDisabled(false);
			listboxMySNPList.setDisabled(false);
		}
	}
	
	} catch(Exception ex) {
		
		ex.printStackTrace();
	}
}


@Listen("onChange = #intStart")
public void onchangedStart() {
	listboxMySNPList.setSelectedIndex(0);
	comboGene.setValue("");
}

@Listen("onChange = #intStop")
public void onchangedStop() {
	listboxMySNPList.setSelectedIndex(0);
	comboGene.setValue("");
}

private Integer getchrLength() {
	//return genotype.getFeatureLength(selectChr.getValue());
	return genotype.getFeatureLength(selectChr.getValue(), listboxReference.getSelectedItem().getLabel());
}


@Listen("onClick = #checkboxAlignIndels")
public void onclickAlignIndels() {

		GenotypeQueryParams params =  fillGenotypeQueryParams();
		params.setbAlignIndels( checkboxAlignIndels.isChecked() );
		updateBiglistboxArray(params);
}




private void updateBiglistboxArray(GenotypeQueryParams params)  {
	
	//if(!biglistboxArray.isVisible()) return;
	
	//if(this.queryRawResult==null) return;
	
	try {
	varianttable = genotype.fillGenotypeTable(new VariantAlignmentTableArraysImpl() , queryRawResult, params);
	queryResult = varianttable.getVariantStringData();
	
	this.msgbox.setValue(  varianttable.getMessage() );
	
	
	if(checkboxIndel.isChecked() &&  !checkboxAlignIndels.isChecked())
	biglistboxArray.setColWidth("60px");
	else biglistboxArray.setColWidth("30px");

	AppContext.debug("updateBiglistboxArray: queryResult=" + queryResult.getListVariantsString().size() + " x " +  queryResult.getListPos().size());
	
	Object2StringMatrixRenderer renderer  = new Object2StringMatrixRenderer(queryResult,  params);
	biglistboxArray.setMatrixRenderer(renderer);
	biglistboxModel=new Object2StringMatrixModel(varianttable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety(), gridBiglistheader);
	biglistboxModel.setHeaderRows(biglistboxRows, lastY);
	biglistboxArray.setModel(biglistboxModel); //strRef));
	
	
	biglistboxArray.setWidth("100%");
	gridBiglistheader.invalidate();
	
	this.tabTable.setSelected(true);

	// create new gff for 
	urljbrowse = null;
	//gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";	
	gfffile = null;
	
	if(tabJbrowse.isVisible())
		updateJBrowse( selectChr.getValue().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), this.comboGene.getValue());
	
	
	Object2StringMatrixModel model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
	int poscol=-1;
	List listPos = (List)model.getHeadAt(0);
	//for(int i=0; i<biglistboxArray.getCols(); i++) {
	//mapIdx2Pos=new HashMap();
	List listPosition=new ArrayList();
	for(int i=0; i<model.getColumnSize(); i++) {
		if(listPos.get(i).toString().isEmpty()) continue;
		listPosition.add( listPos.get(i).toString() );
		//try {
		//	mapIdx2Pos.put(i, BigDecimal.valueOf(Double.valueOf(listPos.get(i).toString()) ));
		//} catch(Exception ex) {
		//	
		//}
	}
	listboxPosition.setModel( new SimpleListModel(listPosition));
	labelFilterResult.setValue("None .. " + queryResult.getListVariantsString().size() + " varieties");
	
	
	
	} catch (Exception ex) {
		ex.printStackTrace();
		Messagebox.show(ex.getMessage(), "Exception in updateBiglistboxArray", Messagebox.OK, Messagebox.ERROR);
	}
}


@Listen("onSelect =#listboxPhenotype")
public void onselectPhenotype() {
	
}


//***************************** HANDLE ALLELE FILTER **************************

@Listen("onSelect = #listboxPosition") 
public void onselectposition() {
	
	String selpos = listboxPosition.getSelectedItem().getLabel();
	if(!selpos.isEmpty()) {
		
		try {
		
		// get alleles for position
		// get position column index

		Object2StringMatrixModel model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
		int poscol=-1;
		List listPos = (List)model.getHeadAt(0);
		//for(int i=0; i<biglistboxArray.getCols(); i++) {
		for(int i=0; i<model.getColumnSize(); i++) {
			//AppContext.debug(listPos.get(i).getClass() + ", " +  listPos.get(i).toString());
			if( listPos.get(i).toString().equals(selpos) ) {
				poscol=i;
				break;
			}
		}
		
		//AppContext.debug("posco=" +poscol);
		
		Set setAlleles = new TreeSet();
		for(int i=0; i<model.getSize(); i ++) {
			Object allele = ((Object[])model.getElementAt(i).get(poscol))[poscol];
			setAlleles.add(allele.toString());
		}
	
		List listAllele= new ArrayList();
		listAllele.addAll(setAlleles);
     //listAllele.add("");listAllele.add("A");listAllele.add("T");listAllele.add("G");listAllele.add("C");
     listboxAllele.setModel(new SimpleListModel(listAllele));
     listboxAllele.setSelectedIndex(0);
//		}
//		catch(InvocationTargetException ex) {
//			ex.getCause().printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
     
	} else
		listboxAllele.setModel(new SimpleListModel(new ArrayList()));
	
}


@Listen("onSelect = #listboxSNPListAlleles")
public void onSelectlistboxSNPListAlleles() {

	String selitem = listboxSNPListAlleles.getSelectedItem().getLabel();
	if(selitem.startsWith("create new list")) {
		Executions.sendRedirect("_workspace.zul?from=snp&src=snp");
	}
	
}

@Listen("onClick = #buttonFilterAllele")
public void onclickFilterAllele() {

	try {
	

	String selpos = listboxPosition.getSelectedItem().getLabel();
	if(selpos.isEmpty()) {
		biglistboxModel= new Object2StringMatrixModel(varianttable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety(), gridBiglistheader );
		biglistboxModel.setHeaderRows(biglistboxRows, lastY);
		biglistboxArray.setModel(biglistboxModel);
		//gridBiglistheader.setModel(new BiglistRowheaderModel( ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList( biglistboxRows) ));
		gridBiglistheader.setModel(new BiglistRowheaderModel( biglistboxModel.getRowHeaderList( biglistboxRows) ));
	} else {
	
		BigDecimal pos = null;
		
		int selPosIdx=listboxPosition.getSelectedIndex();
		if(selPosIdx>3) {
			
			try {
			pos =  BigDecimal.valueOf(Double.valueOf(selpos.replace(".00","").replace(".0","")));
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		String alleleFilter = listboxAllele.getSelectedItem().getLabel();
		
		
		boolean include = false;
		List listInclude=new ArrayList();
		List listVarids=new ArrayList();
		List listMismatch=new ArrayList();
		List listNames = new ArrayList();
		

		Object2StringMatrixModel origModel = (Object2StringMatrixModel)this.biglistboxArray.getModel();
		VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)origModel.getData();
		Object[][] varalleles = table.getVaralleles();

		
		for(int i=0; i<origModel.getSize(); i ++) {
			Object alleleTable = ((Object[])origModel.getElementAt(i).get(selPosIdx-1))[selPosIdx-1];
			if(alleleTable.toString().equals(alleleFilter.toString())) {
				listInclude.add(varalleles[i]);
				listVarids.add(table.getVarid()[i]);
				listNames.add(table.getVarname()[i]);
				listMismatch.add(table.getVarmismatch()[i]);
				
				if(selPosIdx-1<4) {
					AppContext.debug( "tablevalue=" +  alleleTable + "; varid=" + table.getVarid()[i] + ", name="  + table.getVarname()[i] + ",mismatch=" + table.getVarmismatch()[i] );
				}
			}
			//setAlleles.add(allele.toString());
		}

		
		String filteredvaralleles[][] = new String[listInclude.size()][];
		Long filteredvarids[] = new Long[listInclude.size()];
		Double filteredvarmis[] = new Double[listInclude.size()];
		String filteredvarnames[] = new String[listInclude.size()];
		for(int ifil=0; ifil<listInclude.size(); ifil++) {
			filteredvaralleles[ifil]= (String[])listInclude.get(ifil);
			filteredvarids[ifil]= (Long)listVarids.get(ifil);
			filteredvarnames[ifil]= (String)listNames.get(ifil);
			filteredvarmis[ifil]= (Double)listMismatch.get(ifil);
		}
		
		VariantAlignmentTableArraysImpl newtable = new VariantAlignmentTableArraysImpl(table);
		newtable.setAllelestring( filteredvaralleles );
		newtable.setVarids(filteredvarids);
		newtable.setVarmismatch(filteredvarmis);
		newtable.setVarnames(filteredvarnames);
		
		//this.filteredList =
		
		biglistboxModel= new Object2StringMatrixModel(newtable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety(),gridBiglistheader);
		biglistboxModel.setHeaderRows(biglistboxRows, lastY);
		biglistboxArray.setModel(biglistboxModel);
		
		getSession().putValue("variantTable", newtable);
		
		String activefilters=labelFilterResult.getValue().split("\\.\\.")[0].replace("Active Filter:", "").replace("None","").trim();
		
		if(!activefilters.isEmpty()) activefilters += ",";
		activefilters +=  selpos +"=" + alleleFilter ;
		
		labelFilterResult.setValue( activefilters + " .. " + listInclude.size() + "/" + queryRawResult.getListVariantsString().size() + " varieties" );
		
		gridBiglistheader.setModel( new BiglistRowheaderModel(  biglistboxModel.getRowHeaderList(biglistboxRows) ));
		
		allelefreqlines=null;
		genotypefreqlines=null;
		
		calculateAlleleFrequencies();
		
		gfffile=null;
		//AppContext.debug("reset gff=null");
	}
	
	} catch(Exception ex) {
		ex.printStackTrace();
	}
}


@Listen("onClick = #buttonClearFilterAllele")
public void onclickClearFilterAllele() {

	try {
	biglistboxModel= new Object2StringMatrixModel(varianttable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety(), gridBiglistheader );
	biglistboxModel.setHeaderRows(biglistboxRows, lastY);
	biglistboxArray.setModel(biglistboxModel);

	gridBiglistheader.setModel(new BiglistRowheaderModel( biglistboxModel.getRowHeaderList( biglistboxRows) ));
	labelFilterResult.setValue("None .. " +  queryRawResult.getListVariantsString().size() + " varieties");
	

	allelefreqlines=null;
	genotypefreqlines=null;
	calculateAlleleFrequencies();
	
	gfffile=null;
	
	} catch(Exception ex) {
		ex.printStackTrace();
	}
}



@Listen("onSelect = #tabMSA")    
public void onselectTabMSA() {
	
	iframeAlignment.invalidate();
}

@Listen("onSelect = #tabTableLarge")    
public void onselectTabTableLarge() {
	
		biglistboxArrayLarge.setMatrixRenderer( biglistboxArray.getMatrixRenderer() );
		biglistboxArrayLarge.setModel( biglistboxArray.getModel() );
		biglistboxArrayLarge.setVisible(true);
	
}



//********************************************** HANDLE JBROWSE TAB EVENTS ********************************************

/**
 * JBrowse tab selected    
 */
@Listen("onSelect = #tabJbrowse")    
public void onselectTabJbrowse() {
	//tabboxDisplay.setHeight( "100%");
	
	 AppContext.debug("onselectTabJbrowse gfffile=" + gfffile + "\nurljbrowse=" + urljbrowse);
	 
	if(urljbrowse!=null  ) {

		if(gfffile!=null){
			//same data, don't recreate gff
			return;
		}

		AppContext.debug("loading " + urljbrowse);
		
		Clients.showBusy("Loading JBrowse");
		if(tallJbrowse) {
			gfffile =  "tmp_" + AppContext.createTempFilename() + ".gff";	

			
			int jbrowsebp_margin =     (intStop.getValue()-intStart.getValue())*AppContext.getJBrowseMargin();
			int start = intStart.getValue()-jbrowsebp_margin;
			if(start<0) start=1;

			//VariantStringData queryDisplayData = queryRawResult;
			VariantStringData queryDisplayData = queryResult;
			
			//if(queryResult.getListVariantsString()!=null) {
			if(queryDisplayData!=null) {
				
				try {
				
				List listGFF = new ArrayList();
				if(this.checkboxSNP.isChecked() && checkboxIndel.isChecked())
				{
					List listGFFSNP = new ArrayList();
					List listGFFIndel = new ArrayList();
				
					Iterator<SnpsStringAllvars> itSnpstring = queryDisplayData.getListVariantsString().iterator();
					while(itSnpstring.hasNext()) {
						SnpsStringAllvars snpstring = itSnpstring.next();
						if(snpstring instanceof IndelsStringAllvars) 
							listGFFIndel.add(snpstring);
						else
							listGFFSNP.add(snpstring);
					}
					AppContext.debug("listGFFIndel.size=" + listGFFIndel.size() +", listGFFSNP.size=" + listGFFSNP.size() );
					listGFF.addAll( createSNPPointStringVarietyGFFFromTable(listGFFSNP,  1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) );
					listGFF.addAll( createIndelStringVarietyGFFFromTable(listGFFIndel,   1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected() || !checkboxMismatchOnly.isChecked() ) );
				}
				else if(this.checkboxSNP.isChecked())
				{
					listGFF = createSNPStringVarietyGFFFromTable(queryDisplayData.getListVariantsString(),    1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) ;
				}
				else if(this.checkboxIndel.isChecked())
				{
					listGFF = createIndelStringVarietyGFFFromTable(queryDisplayData.getListVariantsString(),   1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  );
				}
				
				// update positions and refnuc in list gff 
				// listGFF
				
				if(!this.listboxReference.equals(AppContext.getDefaultOrganism())) {
					if(!this.checkboxIndel.isChecked())
						listGFF = convertGFFReferencePosInterval(listGFF);
					else
						listGFF = convertGFFReferencePosPoint(listGFF);
				}
				
				writeGFF(listGFF, AppContext.getTempDir() + gfffile);
				
				AppContext.debug(AppContext.getTempDir() + gfffile + " done..");
				
				AppContext.waitForFile( AppContext.getTempDir() + gfffile);
				
				AppContext.resetTimer("write gff");
				AppContext.resetTimer("viewing " + urljbrowse.replace("GFF_FILE" ,gfffile) );
				
				//String gfffileonly=
				//urlphylo = urlphylo.replace("GFF_FILE",  gfffile.replace(".gff", "") ); //  = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=GFF_FILE&mindist=" + intPhyloMindist.getValue();

				iframeJbrowse.setSrc( urljbrowse.replace("GFF_FILE" ,gfffile) );
				
				//divBlackSyn.setVisible(false);
				divMismatch.setVisible(false);
				divNucleotide.setVisible(false);
				divNucleotideIndels.setVisible(false);
				
				if(radioColorAllele.isSelected()) {
					if(this.checkboxIndel.isChecked())
						 divNucleotideIndels.setVisible(true);
					else divNucleotide.setVisible(true);
				}
				if(this.radioColorMismatch.isSelected()) divMismatch.setVisible(true);
				} catch( Exception ex) {
					ex.printStackTrace();
					Messagebox.show(ex.getMessage(), "Load JBrowse Exception", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}
			else {
				throw new RuntimeException("onselectTabJbrowse: queryResult!=null");
			}
		}
		else {
			AppContext.resetTimer("viewing " + urljbrowse.replace("GFF_FILE" ,gfffile) );
			iframeJbrowse.setSrc( urljbrowse.replace("GFF_FILE" ,gfffile)  );
			//
		}
	    msgJbrowse.setVisible(true);
	    iframeJbrowse.setVisible(true);
	    Clients.clearBusy();
	    
	    AppContext.debug("gfffile=" + gfffile);
	}
	AppContext.debug("onselectTabJbrowse gfffile=" + gfffile);
		
}


/**
 * JBrowse using phylotree ordering, visible only after Phylotree is computed
 */
@Listen("onSelect = #tabJBrowsePhylo")    
public void onselectTabJbrowsePhylo() {

	//tabboxDisplay.setHeight( "100%");
	AppContext.debug("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
	Clients.showBusy("Calculating JBrowsePhylo"); 
	if(urljbrowsephylo==null) {	
			
			if(tallJbrowse) {
				updateJBrowsePhylo( selectChr.getValue().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
				genotype = (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
				
				if(gfffile==null) gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";	
				
				AppContext.debug("reading newick gfffile=" + gfffile);
				
				Map mapVariety2Order = (Map)Sessions.getCurrent().getAttribute("phyloorder");
				
				if(mapVariety2Order==null) AppContext.debug("mapVariety2Order==null from session");
				else {
					AppContext.debug("mapVariety2Order.size() from session= " + mapVariety2Order.size());
					//AppContext.debug(mapVariety2Order.toString());
				}
				
				List newpagelist;
				
				int jbrowsebp_margin =     (intStop.getValue()-intStart.getValue())*AppContext.getJBrowseMargin();
				
				newpagelist= this.queryResult.getListVariantsString();
				
				List listGFF = createSNPStringVarietyGFF(newpagelist,  mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()-jbrowsebp_margin), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  false );
				
				String phylofile=AppContext.getTempDir() + gfffile+".phylo.gff";
				
				AppContext.resetTimer("writing  " +  phylofile);
				writeGFF(listGFF, phylofile);
			}
		}
	
	
	if(urljbrowsephylo!=null) {

		AppContext.debug("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
//		int maxwait=0;
//		while(true) {
//
//			if(Sessions.getCurrent().getAttribute("phyloorder")!=null) break;
//			if(maxwait>50) break;
//			maxwait++;
//			
//			/*
//			AppContext.debug("reading " + AppContext.getTempDir() +  gfffile.replace(".gff", ".newick"));
//			File f = new File( AppContext.getTempDir() +  gfffile.replace(".gff", ".newick") );
//			if(f.exists()) break;
//			*/
//			try {
//				Thread.sleep(5000);
//				AppContext.debug("waiting phylotree in session");
//			}
//			catch(Exception ex) {
//				ex.printStackTrace();
//			}
//			
//		}
		
		iframeJbrowsePhylo.setSrc( urljbrowsephylo.replace("GFF_FILE",gfffile) );
	    msgJbrowsePhylo.setVisible(true);
	    iframeJbrowsePhylo.setVisible(true);

	}
    Clients.clearBusy();
	//urljbrowsephylo=null;
		
}



private void updateJBrowsePhylo(String chr, String start, String end, String locus) {
	//return;
	
	chr = chr.trim();
	String chrpad = chr;
	if(chrpad.length()==1) chrpad="0" + chr;
	
	
	
	if (iframeJbrowsePhylo==null) throw new RuntimeException("jbrowsephylo==null");
	
	String displaymode="%22displayMode%22:%22normal%22,%22maxHeight%22:%222000%22";
	
	if(tallJbrowse) {
		iframeJbrowsePhylo.setStyle("width:1500px;height:1000px;border:0px inset;" );
		//displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%2232000%22";
		displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%222000%22";
	}
	
	String  rendertype="";
	
	if(locus.isEmpty())		
		msgJbrowsePhylo.setValue("Chromosome " + chr + " [" + start + ".." + end + "]");
	else
		msgJbrowsePhylo.setValue(locus + "  Chromosome " + chr + " [" + start + ".." + end + "]");
	
	iframeJbrowsePhylo.setScrolling("yes");
	iframeJbrowsePhylo.setAlign("left");		

	//String urltemplate = "..%2F..%2F" + AppContext.getHostDirectory() + "%2Ftmp%2F" + gfffile + ".phylo.gff";
	String urltemplate = "..%2F..%2Ftemp%2FGFF_FILE.phylo.gff";
	
	
	String snp3kcore="";
	if(checkboxSNP.isChecked()) { 
		if(checkboxCoreSNP.isChecked()) snp3kcore="msu7coresnpsv2%2C";
	}
	if(checkboxIndel.isChecked())
		snp3kcore += "msu7indelsv2%2C";
	String tracks=AppContext.getJBrowseDefaulttracks() + "," + snp3kcore;
	
		if(tallJbrowse) {
			// for all varieties
			rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2Variety%22";
			
			//urljbrowsephylo= AppContext.getHostname() + "/" + AppContext.getJbrowseDir() + "/?loc="  + chrpad + AppContext.getJbrowseContigSuffix() + ":" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2Csnp3k%2CSNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
			urljbrowsephylo= AppContext.getHostname() + "/" + AppContext.getJbrowseDir() + "/?loc="  + chrpad + AppContext.getJbrowseContigSuffix() + ":" + start + ".." + end +   "&tracks=" + tracks + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
				 "%22}}&addTracks=[{%22label%22%3A%22SNP%20Genotyping%22%2C%22type%22%3A" + rendertype + "%2C%22store%22%3A%22url%22%2C%20" + displaymode 
				 + ",%22metadata%22:{%22Description%22%3A%20%22Varieties%20SNP%20Genotyping%20in%20the%20region.%20Each%20row%20is%20a%20variety.%20Red%20means%20there%20is%20variation%20with%20the%20reference%22}"  
				 + ",%22fmtDetailValue_Name%22%3A%20%22function(name)%20%7B%20return%20%27%3Ca%20target%3D%5C%22variety%5C%22%20href%3D%5C%22/" + AppContext.getHostDirectory()  + "/_variety.zul%3Fname%3D%27%2Bname%2B%27%5C%22%3E%27%2Bname%2B%27%3C%2Fa%3E%27%3B%20%7D%22%20"
				 + "%2C%20%22style%22%3A{%22showLabels%22%3A%22false%22%2C%22textFont%22%3A%22normal%208px%20Univers%2CHelvetica%2CArial%2Csans-serif%22}}]&highlight=";

		}
	
			
	//log.debug(urljbrowsephylo);
	
	//AppContext.debug("urljbrowsephylo= " + urljbrowsephylo);
		
}




/**
 * Class to write GFF file from SNP for use in JBrowse
 * @author lmansueto
 *
 */
	 private class GFF {
		private String left;
		private String right;
		private long start;
		private long end;
		private long position;
		public GFF(String left, String right, long start, long end, long position) {
			super();
			this.left = left;
			this.right = right;
			this.start = start;
			this.end = end;
			this.position = position;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.left + start +"\t" + end + this.right ;
		}
		public long getStart() {
			return start;
		}
		
		public long getEnd() {
			return end;
		}
		
		Long getPosition() {
			return this.position;
		}
		
		String getLeft() {
			return this.left;
		}
		String getRight() {
			return this.right;
		}
		
	}

	 /**
	  * Sort GFF by start (to be used by JBrowse all variety track)
	  * @author LMansueto
	  *
	  */
	 private class GFFStartComparator implements  Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			GFF snp1=(GFF)o1;
			GFF snp2=(GFF)o2;
			
			if(snp1.getStart()<snp2.getStart())
				return -1;
			if(snp1.getStart()>snp2.getStart())
				return 1;
			if(snp1.getEnd()<snp2.getEnd())
				return 1;
			if(snp1.getEnd()>snp2.getEnd())
				return -1;
			return 0;
		}
	}
	 

	 /**
	  * calculate position boundary
	  * @param setPositions
	  * @param start
	  * @param end
	  * @return
	  */
	 private Map<Long, long[]> createMapGFFPosBounds(Set<Long> setPositions , BigDecimal start, BigDecimal end) {
		 
			Iterator<Long> itPos = setPositions.iterator();

			Map<Long, Long> mapPrevPos = new java.util.HashMap<Long, Long>();
			Map<Long, Long> mapNextPos = new java.util.HashMap<Long, Long>();
			
			long lPos[] = new long[setPositions.size()];
			char cRef[] = new char[setPositions.size()];
			
			int iPosCount = 0;
			//		BigDecimal prevPos=start;
			StringBuffer bufPos = new StringBuffer();
			long prevpos = start.longValue();
			if(itPos.hasNext()) {
				//SnpsAllvarsPos pos = itPos.next();
				//long curpos = pos.getPos().longValue();
				
				long curpos = itPos.next();
				
				lPos[iPosCount]=curpos;
				//cRef[iPosCount]=pos.getRefnuc().charAt(0);
				
				mapPrevPos.put(curpos, prevpos);
				mapNextPos.put(prevpos, curpos);
				prevpos = curpos;
				bufPos.append( curpos ).append(",");
			}

			Map<Long, long[]> mapPos2Bounds = new java.util.HashMap<Long, long[]>();

			iPosCount++;
			while(itPos.hasNext()) {
				//SnpsAllvarsPos pos = itPos.next();
				//long curpos = pos.getPos().longValue();
				
				long curpos = itPos.next();
				
				mapPrevPos.put(curpos, prevpos);
				mapNextPos.put(prevpos, curpos);
				
				lPos[iPosCount]=curpos;
				//if(pos.getRefnuc()==null || pos.getRefnuc().isEmpty())
				//	cRef[iPosCount]=' ';
				//else cRef[iPosCount]=pos.getRefnuc().charAt(0);
				
				//cRef[iPosCount]=pos.getRefnuc().charAt(0);

				mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
				
				prevpos = curpos;
				bufPos.append( curpos ).append(",");
				iPosCount++;
				
			}
			mapNextPos.put(prevpos, end.longValue());
			mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
			
			return mapPos2Bounds;
			
	 }
	 
	 /**
	  * get order of varities for display in JBrowse
	  * @return
	  */
	 private Map createMapVar2Order() {
		 
			varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade , "VarietyFacade");
			Map<String,Variety> mapName2Var = varietyfacade.getMapVarname2Variety();
			Map<BigDecimal,Integer> mapVar2Order = new HashMap();
			Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
			for(int i=0; i<model.getSize(); i++) {
				Object  varname =  ((Object[])model.getElementAt(i).get(0))[0];
				mapVar2Order.put(mapName2Var.get(varname).getVarietyId(),i);
			}
		return 	mapVar2Order;
	 }
	 
	 

	/**
	 * convert GFF position to a point (for indel display) 
	 * @param listNPB
	 * @return
	 */
	private List convertGFFReferencePosPoint(List listNPB) {
		
		List newGFF= null;
		if(queryResult.isNipponbareReference()) {
			newGFF = listNPB;
		} else {
			newGFF = new ArrayList();
			
			Map mapMSU7Pos2ConvertedPos = queryResult.getMapMSU7Pos2ConvertedPos();
			
			String newSrc = this.selectChr.getValue();
			String org = this.listboxReference.getSelectedItem().getLabel(); 
			
			/*
			if(org.equals(AppContext.getDefaultOrganism())) 
					newSrc +=  AppContext.getJbrowseContigSuffix(); //"|msu7";
			else if(org.equals("IR64-21"))
					newSrc += "|ir6421v1";
			else if(org.equals("93-11"))
					newSrc += "|9311v1";
			else if(org.equals("DJ123"))
					newSrc += "|dj123v1";
			else if(org.equals("Kasalath"))
					newSrc += "|kasv1"; 
		*/
			
			Set<Long> setPositions = new TreeSet();
			Iterator<GFF> itGFF=listNPB.iterator();
			while(itGFF.hasNext()) {
				GFF npbGFF = itGFF.next();
				
				if(npbGFF.getStart()!=npbGFF.getPosition().longValue())
				{
					AppContext.info("GFF start " + npbGFF.getStart() + "!= GFF position " + npbGFF.getPosition() );
					//continue;
				}
				
				MultiReferenceConversion refpos = (MultiReferenceConversion)mapMSU7Pos2ConvertedPos.get(BigDecimal.valueOf( npbGFF.getPosition() ));
				if(refpos==null) continue;
				
				Long newpos =  refpos.getToPosition().longValue();
				Long newEnd = newpos + (npbGFF.getEnd() - npbGFF.getStart()  );
				
				String npbleft[] = npbGFF.getLeft().split("\t");
				String newRight = npbGFF.getRight().replace("Position=" + npbGFF.getPosition().longValue() ,  "Position=" + newpos).replace("\n","") +
						";Contig=" + refpos.getToContig() + ";Organism=" + refpos.getToOrganism() + ";NPBPosition=" +  npbGFF.getPosition().longValue() + ";NPBContig=" + npbleft[0] + "\n";
				GFF convertedGFF = new GFF(newSrc + "\t" + npbleft[1] +"\t" + npbleft[2] +"\t",newRight,  newpos , newEnd,  newpos );
				newGFF.add( convertedGFF );
	
			}
			
		}
		return newGFF; 
		
	}
	
	
	/**
	 * convert GFF position to a point (for SNP display) 
	 * @param listNPB
	 * @return
	 */
	
	private List convertGFFReferencePosInterval(List listNPB) {
		
		List newGFF= null;
		if(queryResult.isNipponbareReference()) {
			newGFF = listNPB;
		} else {
			newGFF = new ArrayList();
			
			Map mapMSU7Pos2ConvertedPos = queryResult.getMapMSU7Pos2ConvertedPos();
			
			Set<Long> setPositions = new TreeSet();
			Iterator<GFF> itGFF=listNPB.iterator();
			while(itGFF.hasNext()) {
				GFF npbGFF = itGFF.next();
				MultiReferenceConversion refpos = (MultiReferenceConversion)mapMSU7Pos2ConvertedPos.get(BigDecimal.valueOf( npbGFF.getPosition() ));
				//MultiReferencePosition refpos = (MultiReferencePosition)mapMSU7Pos2ConvertedPos.get(BigDecimal.valueOf( npbGFF.getPosition() ));
				if(refpos==null) continue;
				setPositions.add( refpos.getToPosition().longValue() );
			}
			
			Map<Long, long[]> mapPos2Bounds = createMapGFFPosBounds(setPositions , BigDecimal.valueOf(Long.valueOf(intStart.getValue())), BigDecimal.valueOf(Long.valueOf(intStop.getValue()))); 
			
			String newSrc = this.selectChr.getValue();
			String org = this.listboxReference.getSelectedItem().getLabel(); 
			
			/*
			if(org.equals(AppContext.getDefaultOrganism())) 
					newSrc += AppContext.getJbrowseContigSuffix(); //"|msu7";
			else if(org.equals("IR64-21"))
					newSrc += "|ir6421v1";
			else if(org.equals("93-11"))
					newSrc += "|9311v1";
			else if(org.equals("DJ123"))
					newSrc += "|dj123v1";
			else if(org.equals("Kasalath"))
					newSrc += "|kasv1";
					*/ 
		
			Iterator<GFF> itGFF2=listNPB.iterator();
			while(itGFF2.hasNext()) {
				GFF npbGFF = itGFF2.next();
				MultiReferenceConversion refpos = (MultiReferenceConversion)mapMSU7Pos2ConvertedPos.get(BigDecimal.valueOf( npbGFF.getPosition() )); 
				
				if(refpos==null) continue;
				
					String npbleft[] = npbGFF.getLeft().split("\t");
					String newRight = npbGFF.getRight().replace("Position=" + npbGFF.getPosition().longValue() ,  "Position=" + refpos.getToPosition().longValue()).replace("\n","") +
							";Contig=" + refpos.getToContig() + ";Organism=" + refpos.getToOrganism() + ";NPBPosition=" +  npbGFF.getPosition().longValue() + ";NPBContig=" + npbleft[0] + "\n";
					
					long[] bounds = mapPos2Bounds.get( refpos.getToPosition().longValue() );
					GFF convertedGFF = new GFF(newSrc + "\t" + npbleft[1] +"\t" + npbleft[2] +"\t",newRight,  bounds[0], bounds[1],  refpos.getToPosition().longValue() );
					newGFF.add( convertedGFF );
					//AppContext.debug( npbGFF  + " --> " + convertedGFF);
				
			}
		}
		
		return newGFF; 
		
	}


	 private List createSNPStringVarietyGFFFromTable(List<SnpsStringAllvars> listSNPs, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			return createSNPStringVarietyGFF(listSNPs,  createMapVar2Order() , bpgap, start, end, showAll);
	 }
	 
	 /**
	  * 
	  * @param listSNPs	SNPString for all variety
	  * @param var2order
	  * @param bpgap
	  * @param start
	  * @param end
	  * @param showAll	showAll alleles
	  * @return
	  */
	 private List createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs,  Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
		 	VariantStringData queryDisplayData = queryResult;
		 
			List listSnpPos = queryDisplayData.getListPos(); // genotype.getSnpsposlist() ;
			Iterator<SnpsAllvarsPos> itPos = listSnpPos.iterator();

			Map<Long, Long> mapPrevPos = new java.util.HashMap<Long, Long>();
			Map<Long, Long> mapNextPos = new java.util.HashMap<Long, Long>();
			
			long lPos[] = new long[listSnpPos.size()];
			char cRef[] = new char[listSnpPos.size()];
			
			int iPosCount = 0;
			//		BigDecimal prevPos=start;
			StringBuffer bufPos = new StringBuffer();
			long prevpos = start.longValue();
			if(itPos.hasNext()) {
				SnpsAllvarsPos pos = itPos.next();
				long curpos = pos.getPos().longValue();
				
				lPos[iPosCount]=curpos;
				cRef[iPosCount]=pos.getRefnuc().charAt(0);
				
				mapPrevPos.put(curpos, prevpos);
				mapNextPos.put(prevpos, curpos);
				prevpos = curpos;
				bufPos.append( curpos ).append(",");
			}

			Map<Long, long[]> mapPos2Bounds = new java.util.HashMap<Long, long[]>();

			iPosCount++;
			while(itPos.hasNext()) {
				SnpsAllvarsPos pos = itPos.next();
				long curpos = pos.getPos().longValue();
				mapPrevPos.put(curpos, prevpos);
				mapNextPos.put(prevpos, curpos);
				
				lPos[iPosCount]=curpos;
				if(pos.getRefnuc()==null || pos.getRefnuc().isEmpty())
					cRef[iPosCount]=' ';
				else cRef[iPosCount]=pos.getRefnuc().charAt(0);
				
				//cRef[iPosCount]=pos.getRefnuc().charAt(0);

				mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
				
				prevpos = curpos;
				bufPos.append( curpos ).append(",");
				iPosCount++;
				
			}
			mapNextPos.put(prevpos, end.longValue());
			mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
			
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
			
			java.util.List listGFF = new java.util.ArrayList();
			
			try {
		
				Iterator<SnpsStringAllvars> itsnpstring = listSNPs.iterator();
				
				while(itsnpstring.hasNext()) {
					SnpsStringAllvars snpvars = itsnpstring.next();
					
					if(!var2order.containsKey( snpvars.getVar() )) continue;
					
					Set setposAcceptor = snpvars.getAcceptorPosset();
					Set setposDonor = snpvars.getDonorPosset();
					
					Set setNonsyn = snpvars.getNonsynPosset();
					
					Map<Position,Character> mapPos2allele2 = snpvars.getMapPos2Allele2();
					
					String chr = snpvars.getChr().toString(); 
					if(chr.length()==1)
						chr= "chr0" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
					else 
						chr= "chr" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
		
					String order =  var2order.get(snpvars.getVar() ).toString();
					Variety var=mapId2Variety.get( snpvars.getVar() );
					if(var==null) throw new RuntimeException(snpvars.getVar()  + " not in mapId2Variety");

					String splicestr = "";

					String colormode="";
					if(this.radioColorMismatch.isSelected())
						colormode=";colormode=1";
					else
						colormode=";colormode=0";
					
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
						String synstr = "";
						
						if(setNonsyn!=null) {
							if(setNonsyn.contains( listSnpPos.get(iPos)  )) synstr = ";Synonymous=0";
							else {
								synstr = ";Synonymous=1";
								//AppContext.debug("synonymous snpIdx=" + iPos );
							}
						}
						String al2 = "";
						if(mapPos2allele2!=null) {
							Character allele2 = mapPos2allele2.get( listSnpPos.get(iPos) );
							if(allele2!=null) al2="/" + allele2;
						}
						
						if( showAll || (charAtPos!='0' && cRef[iPos]!= charAtPos) ) {

							splicestr="";

							if(setposAcceptor!=null) {
								if(setposAcceptor.contains( BigDecimal.valueOf(lPos[iPos]) ))
									splicestr=";Acceptor=1";	
								else splicestr=";Acceptor=0";
							}
								
							if(setposDonor!=null) {
								if(setposDonor.contains( BigDecimal.valueOf( lPos[iPos]) ) )
								splicestr+=";Donor=1";	
								else splicestr+=";Donor=0";
							}

							String ismissing=";Missing=0";
							if(charAtPos=='?') ismissing=";Missing=1";
						
							String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
									";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  lPos[iPos] + 
									";AlleleRef=" +   cRef[iPos] + 
									";AlleleAlt=" + charAtPos + al2 +
									";Position=" +  lPos[iPos] +
									synstr +
									splicestr + colormode +
									ismissing + 
									";order=" + order +
									"\n";	
		
							long bounds[] = mapPos2Bounds.get( lPos[iPos] );
							listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  bounds[0],  bounds[1],  lPos[iPos] ));
						}
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return listGFF;
		}
	 	 

	 /**
	  * Display SNP as points in JBrowse
	  * @param listSNPs
	  * @param bpgap
	  * @param start
	  * @param end
	  * @param showAll
	  * @return
	  */
	 private List createSNPPointStringVarietyGFFFromTable(List<SnpsStringAllvars> listSNPs,   int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
		 return createSNPPointStringVarietyGFF(listSNPs,  createMapVar2Order(),bpgap, start,  end,  showAll);
	 }
	 
	 private List createSNPPointStringVarietyGFF(List<SnpsStringAllvars> listSNPs,  Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
		 	VariantStringData queryDisplayData=queryResult;
		 
			//List listSnpPos = queryRawResult.getListPos(); // genotype.getSnpsposlist() ;
		    List listSnpPos = queryDisplayData.getSnpstringdata().getListPos(); // genotype.getSnpsposlist() ;
			Iterator<SnpsAllvarsPos> itPos = listSnpPos.iterator();
			
			long lPos[] = new long[listSnpPos.size()];
			char cRef[] = new char[listSnpPos.size()];
			
			int iPosCount = 0;
			long prevpos = start.longValue();
			while(itPos.hasNext()) {
				SnpsAllvarsPos pos = itPos.next();
				long curpos = pos.getPos().longValue();
				lPos[iPosCount]=curpos;
				if(!pos.getRefnuc().isEmpty())
					cRef[iPosCount]=pos.getRefnuc().charAt(0);
				else
					cRef[iPosCount]=' ';
				iPosCount++;
			}
			
			 Map mapPos2NonsynAlleles = queryDisplayData.getSnpstringdata().getMapPos2NonsynAlleles();
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
			
			java.util.List listGFF = new java.util.ArrayList();			
			
			try {
		
				Iterator<SnpsStringAllvars> itsnpstring = listSNPs.iterator();
				
				while(itsnpstring.hasNext()) {
					SnpsStringAllvars snpvars = itsnpstring.next();
					
					Set setposNonsyn = snpvars.getNonsynPosset();
					
					Set setposAcceptor = snpvars.getAcceptorPosset();
					Set setposDonor = snpvars.getDonorPosset();
					
					Map<Position,Character> mapPos2allele2 = snpvars.getMapPos2Allele2();

					String chr = snpvars.getChr().toString(); 
					if(chr.length()==1)
						chr= "chr0" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
					else 
						chr= "chr" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
		
					String order =  var2order.get(snpvars.getVar() ).toString();
					Variety var=mapId2Variety.get( snpvars.getVar() );
					if(var==null) throw new RuntimeException(snpvars.getVar()  + " not in mapId2Variety");

					String colormode="";
					if(this.radioColorMismatch.isSelected())
						colormode=";colormode=1";
					else
						colormode=";colormode=0";
									
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						Integer snpIdx = iPos;
						char charAtPos = snpvars.getVarnuc().substring(snpIdx,snpIdx+1).charAt(0);
						
						String synstr = "";
						
						if(mapPos2NonsynAlleles!=null) {
								Set setnonsyn =  (Set)mapPos2NonsynAlleles.get( listSnpPos.get(snpIdx) );
								if(setnonsyn==null || !setnonsyn.contains(  charAtPos )) {
									synstr= ";Synonymous=1";
									//AppContext.debug("synonymous mergedidx=" + iPos + ", snpidx=" + snpIdx);
								} else 
									synstr = ";Synonymous=0";
					}
						
						String al2 = "";
						if(mapPos2allele2!=null) {
							Character allele2 = mapPos2allele2.get(  listSnpPos.get(snpIdx)  );
							if(allele2!=null) al2="/" + allele2;
						}
						
						if( showAll || (charAtPos!='0' && cRef[iPos]!= charAtPos) ) {
						
							String splicestr="";
							if(setposAcceptor!=null) {
							if(setposAcceptor.contains( BigDecimal.valueOf(lPos[iPos]) ))
								splicestr=";Acceptor=1";	
								else splicestr=";Acceptor=0";
							}
								
							if(setposDonor!=null) {
							if(setposDonor.contains( BigDecimal.valueOf( lPos[iPos]) ) )
								splicestr+=";Donor=1";	
								else splicestr+=";Donor=0";
							}
							
							String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
									";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  lPos[iPos] + 
									";AlleleRef=" +   cRef[iPos] + 
									";AlleleAlt=" + charAtPos + al2 +
									";Position=" +  lPos[iPos] +
									 synstr +
									 splicestr + colormode +
									";order=" + order +
									"\n";	
		
							//AppContext.debug("snpidx=" + iPos + ", pos=" +lPos[iPos] + ", strref="+ cRef[iPos]  + ", gff=" +   line_right);
							
							listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  lPos[iPos],  lPos[iPos],  lPos[iPos]));
						}
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return listGFF;
		}
	 
	 
	 /**
	  * Display Indels as intervals in JBrowse
	  * @param listSNPs
	  * @param bpgap
	  * @param start
	  * @param end
	  * @param showAll
	  * @return
	  */
	 private List createIndelStringVarietyGFFFromTable(List listSNPs, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
		 return createIndelStringVarietyGFF(listSNPs, createMapVar2Order(), bpgap,  start, end, showAll);
	 }
	 
	 private List createIndelStringVarietyGFF(List listSNPs, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
			java.util.List listGFF = new java.util.ArrayList();
			
			try {
		
				Iterator<IndelsStringAllvars> itsnpstring = listSNPs.iterator();
			
							
				
				while(itsnpstring.hasNext()) {
					IndelsStringAllvars snpvars = itsnpstring.next();
					
					
					Set setposNonsyn = snpvars.getNonsynPosset();
					Set setposAcceptor = snpvars.getAcceptorPosset();
					Set setposDonor = snpvars.getDonorPosset();

					Map<Position,Character> mapPos2allele2 = snpvars.getMapPos2Allele2();
					
					//long longsnpvar = snpvars.getVar().longValueExact();
					//long longsnppos = snpvars.getPos().longValueExact();

					String chr = "";
					if(snpvars.getChr()==null) {
						chr = snpvars.getContig() +  AppContext.getJbrowseContigSuffix(); 
					} else {
						chr=snpvars.getChr().toString();
						if(chr.length()==1)
							chr= "chr0" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
						else 
							chr= "chr" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
					}
		
					String order =  var2order.get(snpvars.getVar() ).toString();
					Variety var=mapId2Variety.get( snpvars.getVar() );
					if(var==null) throw new RuntimeException(snpvars.getVar()  + " not in mapId2Variety");

					String colormode="";
					if(this.radioColorMismatch.isSelected())
						colormode=";colormode=1";
					else
						colormode=";colormode=0";
					
					
					Map mapPos2Indels = snpvars.getMapPos2Indels();
					Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel = queryRawResult.getIndelstringdata().getMapIndelId2Indel();
					Iterator<BigDecimal> itPos =  mapPos2Indels.keySet().iterator();
					while(itPos.hasNext()) {
						BigDecimal pos = itPos.next();
						IndelsAllvars indels = (IndelsAllvars)mapPos2Indels.get(pos);
						
						String type = "";
						int allele1len = 0;
						String allele1 = "";
						if(indels.getAllele1()!=null){
							IndelsAllvarsPos indel = mapIndelId2Indel.get( indels.getAllele1() );
							
							
							int indeltype =  IndelStringHDF5nRDBMSHybridService.getIndelType ( indel.getInsString() );


							switch(indeltype) {

								case VariantStringService.INDELTYPE_DELETION:
									allele1 = "del " + indel.getInsString(); 
									allele1len = Integer.valueOf(indel.getInsString());
									type = "deletion";
									break;
								case VariantStringService.INDELTYPE_EXTENDDELETION:
									allele1 = "del " + indel.getInsString(); 
									allele1len = Integer.valueOf(indel.getInsString());
									type = "deletion";
									break;
								case VariantStringService.INDELTYPE_SNP:
									allele1 =  "snp ->" + indel.getInsString();
									allele1len = 1;
									type = "snp";
									break;
								case VariantStringService.INDELTYPE_SUBSTITUTION:	
									allele1 =  "sub ->" + indel.getInsString();
									allele1len =  indel.getInsString().length();
									type = "substitution";
									break;
								case VariantStringService.INDELTYPE_REFERENCE:	
									allele1 = "ref";
									allele1len = 1;
									type = "reference";
									if(!showAll) continue;
									break;
								case VariantStringService.INDELTYPE_INSERTION:	
									allele1 = indel.getInsString();
									allele1len = indel.getInsString().length();
									type = "insertion";
									break;
								case VariantStringService.INDELTYPE_MISSING:
									continue;
							}			
		
							
						}
						int allele2len = 0;
						String allele2 = "";
						String type2="";
						if(indels.getAllele2()!=null){
							
							IndelsAllvarsPos indel2=mapIndelId2Indel.get( indels.getAllele2() );
							int indeltype2 =  IndelStringHDF5nRDBMSHybridService.getIndelType ( indel2.getInsString() );
							

							
							switch(indeltype2) {

								case VariantStringService.INDELTYPE_EXTENDDELETION:
									allele2 = "/del " + indel2.getInsString(); 
									allele2len = Integer.valueOf(indel2.getInsString());
									type2 = "deletion";
									break;
								case VariantStringService.INDELTYPE_DELETION:
									allele2 = "/del " + indel2.getInsString(); 
									allele2len = Integer.valueOf(indel2.getInsString());
									type2 = "deletion";
									break;
								case VariantStringService.INDELTYPE_SNP:
									allele2 =  "/snp ->" + indel2.getInsString();
									allele2len = 1;
									type2 = "snp";
									break;
								case VariantStringService.INDELTYPE_SUBSTITUTION:	
									allele2 =  "/sub ->" + indel2.getInsString();
									allele2len =  indel2.getInsString().length();
									type2 = "substitution";
									break;
								case VariantStringService.INDELTYPE_REFERENCE:	
									allele2 = "/ref";
									allele2len = 1;
									type2 = "reference";
									if(!showAll) continue;
									break;
								case VariantStringService.INDELTYPE_INSERTION:	
									allele2 = "/" + indel2.getInsString();
									allele2len = indel2.getInsString().length();
									type2 = "insertion";
									break;
								case VariantStringService.INDELTYPE_MISSING:
									continue;

							}			
						}
							
						
						int maxlen = allele1len;
						if(allele2len>maxlen) maxlen=allele2len;
						
						String allele12=allele1;
						if(!allele2.equals("/"+allele1)) {
							allele12=allele1+allele2;
						}
						
						String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
								";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  pos + 
								";AlleleAlt=" + allele12 +
								";Position=" +  pos +  colormode +
								";order=" + order +
								"\n";	
	
						if(!type.equals(type2)) {
							type=type+"/"+type2;
						}
							
						listGFF.add(new GFF( chr + "\tIRIC\t" + type + "\t", line_right,  pos.longValue(),  pos.longValue() + maxlen -1,  pos.longValue() ));						
						
					}
					
					if(snpvars.getVarnuc()!=null) {
						List<SnpsAllvarsPos> listSnpPos = queryRawResult.getSnpstringdata().getListPos();
						
						Map<Position,String> mapIndelpos2Refnuc = null;
						if(queryRawResult.hasIndel())
							mapIndelpos2Refnuc = queryRawResult.getIndelstringdata().getMapIndelpos2Refnuc();
						
			
						Set tempsetall=new TreeSet();
						Set tempsetsnps=new TreeSet();
						for(int iMergedPos = 0; iMergedPos<listSnpPos.size(); iMergedPos ++ ) {
							
							int iPos=iMergedPos;
							SnpsAllvarsPos pos = listSnpPos.get(iMergedPos);
							
							String  strRef=null;
							
							if(mapIndelpos2Refnuc!=null)
								strRef = mapIndelpos2Refnuc.get(pos.getPos());
							if(strRef==null) strRef=pos.getRefnuc();
							
							// no ref value
							if(strRef==null || strRef.isEmpty()) continue; 
							
							char cRef = strRef.charAt(0);
							char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
							String synstr = "1";
							
							if(setposNonsyn!=null && setposNonsyn.contains( listSnpPos.get(iPos) )) synstr = "0";
							String al2 = "";
							if(mapPos2allele2!=null) {
								Character allele2 = mapPos2allele2.get(  listSnpPos.get(iPos) );
								if(allele2!=null) al2="/" + allele2;
							}
							
							String splicestr = "";
							if(setposAcceptor!=null) {
							if(setposAcceptor.contains(pos.getPos()))
								splicestr=";Acceptor=1";	
								else splicestr=";Acceptor=0";
							}
							
							if(setposDonor!=null) {
							if(setposDonor.contains(pos.getPos())) 
								splicestr+=";Donor=1";	
								else splicestr+=";Donor=0";
							}

							
							if( showAll || (charAtPos!='0' && cRef!= charAtPos && charAtPos!='.' && charAtPos!=' ') ) {
								
								String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
										";ID=VAR" +  var.getVarietyId() + "-" +  snpvars.getContig() + "-" +  pos.getPos() + 
										";AlleleRef=" +   cRef + 
										";AlleleAlt=" + charAtPos + al2 +
										";Position=" +  pos.getPos() +
										";Synonymous=" + synstr +
										 splicestr + colormode +
										";order=" + order +
										"\n";	
								
								listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  pos.getPos().longValue() , pos.getPos().longValue(), pos.getPos().longValue() ));
							}
						}						

					}
					
				}
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return listGFF;
		}
	 
	 /**
	  * Write SNP GFF to file to be displayed in JBrowse
	  * @param listGFF
	  * @param filename
	  */
	 private void writeGFF(List listGFF, String filename) {
		
		java.util.Collections.sort( listGFF, new GFFStartComparator());
		 
		try {
			//File file = new File(AppContext.getTempDir() + filename);	
			File file = new File( filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			//BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(fw);
				
			int linecount = 0;
			
			Iterator<GFF> itGFF = listGFF.iterator();
			StringBuffer buff = new StringBuffer();
			buff.append("##gff-version 3\n#Note: See http://song.sourceforge.net\n\n");
			
			while(itGFF.hasNext()) {
				GFF gff = itGFF.next();
				
				buff.append(gff.toString());
				
				if(linecount > 100 ) {
					buff.append("###\n");
					//pw.write(buff.toString());
					//AppContext.debug(buff.toString());
					pw.append(buff.toString());
					buff.delete(0, buff.length());
					buff = new StringBuffer();
					pw.flush();
					linecount = 0;
				}
				linecount++;
				
			}
			
			//pw.write(buff.toString());
			pw.append(buff.toString());
			//buff.delete(0, buff.length());
			//buff = new StringBuffer();
			pw.flush();
			pw.close();
			
			AppContext.debug("temgff written in: " + file.getAbsolutePath() );
			log.info( "temgff written in: " + file.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	 }
	 
	 
	
	private void create2VarietiesGFF(List<SnpsStringAllvars> listSNPs, String filename, List<SnpsAllvarsPos> listPos, String refnuc) {
		create2VarietiesGFF(listSNPs, filename, false, listPos,  refnuc);
	}
	
	/**
	 * Create SNP GFF for 2-varieties query
	 * @param listSNPs
	 * @param filename
	 * @param mismatchOnly
	 */
	private void create2VarietiesGFF(List<SnpsStringAllvars> listSNPs, String filename, boolean mismatchOnly, List<SnpsAllvarsPos>  listPos, String refnuc) {
		
		//if(!checkShowsnp.isChecked()) return; 
		
		
		StringBuffer buff = new StringBuffer();
		

		
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapId2Var=varietyfacade.getMapId2Variety();
		
		try {
			File file = new File( AppContext.getTempDir() + filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			if(listSNPs.size()==2) {
				
				int linecount = 0;
				
				SnpsStringAllvars var1variants = listSNPs.get(0);
				SnpsStringAllvars var2variants = listSNPs.get(1);
				
				if(var1variants instanceof IndelsStringAllvars) {
					IndelsStringAllvars var1indels  = (IndelsStringAllvars)var1variants;
					//var1indels.get
					var1indels.getMapPos2Indels().values();
				}
				
				//Iterator<Snps2Vars> itsnp = null ; //listSNPs.iterator();
				//Iterator<Snps2Vars> itsnp = listSNPs.iterator();
	
				
				String chr=var1variants.getContig() +  AppContext.getJbrowseContigSuffix();
				/*
				if(var1variants.getChr()<10)
					chr= "chr0" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
				else 
					chr= "chr" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
					*/
				
				//if(! var1variants.getRefnuc().equals(var2variants.getRefnuc())) {
				//	throw new RuntimeException("!var1variants.getRefnuc().equals(var2variants.getRefnuc())");
				//}
					
			
				
				
				for(int ipos=0; ipos<refnuc.length(); ipos++ ) {
					
					
						/*
						if(snpvars.getRefnuc()==null || snpvars.getRefnuc().trim().isEmpty()) {
							// indel
							
							//AppContext.debug("indelGFF");
							
							if(!mismatchOnly || !snpvars.getVar2nuc().equals( snpvars.getVar1nuc()  ) ) {
								
								String type = genotype.getIndelType( snpvars.getVar1nuc() );
								int allelelen = 1;
								if(type.equals("deletion"))
									allelelen = Integer.parseInt( snpvars.getVar1nuc().replace("del","").trim() );
								else if(type.equals("insertion")) {
									allelelen = snpvars.getVar1nuc().length();
								}
								
								buff.append(chr).append("\tIRIC\t" + type + "\t");
								buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos().intValue() + allelelen - 1 );
								
								buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( snpvars.getVar1() ).getName()
										+";ID=" +  snpvars.getVar1() + "-" + snpvars.getPos() +
										//";AlleleRef=" +   snpvars.getRefnuc() +
										 ";AlleleAlt=" + snpvars.getVar1nuc() +
										 ";Position=" +  snpvars.getPos() +
										"\n");
	
							   type = genotype.getIndelType( snpvars.getVar2nuc() );
								allelelen = 1;
								if(type.equals("deletion"))
									allelelen = Integer.parseInt( snpvars.getVar2nuc().replace("del","").trim() );
								else if(type.equals("insertion")) {
									allelelen = snpvars.getVar2nuc().length();
								}
									
								buff.append(chr).append("\tIRIC\t" + type + "\t");
								buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos().intValue() + allelelen - 1  );
								
								buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( snpvars.getVar2() ).getName()
										+";ID=" +  snpvars.getVar2() + "-" + snpvars.getPos() +
										//";AlleleRef=" +   snpvars.getRefnuc() +
										 ";AlleleAlt=" + snpvars.getVar2nuc() +
										 ";Position=" +  snpvars.getPos() +
										"\n");
							}
						} */
						
						if(true) {
							//snps
							

							
							Character refNuc = refnuc.charAt(ipos);
							Character var2Nuc = var2variants.getVarnuc().charAt(ipos);
							Character var1Nuc = var1variants.getVarnuc().charAt(ipos);
							
							String strPos = listPos.get(ipos).getPos().toString().replace(".00", "").replace(".0", "");
									
							if(!mismatchOnly || ! var2Nuc.equals(var1Nuc) ) {
								buff.append(chr); buff.append("\tIRIC\tsnp\t");
								buff.append( strPos ); buff.append( "\t" ); buff.append( strPos );
								buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( var1variants.getVar() ).getName()
										+";ID=" +  var1variants.getVar() + "-" + chr + "-" + strPos +
										";AlleleRef=" +   refNuc +
										 ";AlleleAlt=" + var1Nuc +
										 ";Position=" +  strPos +
										"\n");

								buff.append(chr); buff.append("\tIRIC\tsnp\t");
								buff.append( strPos ); buff.append( "\t" ); buff.append( strPos );
								buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( var2variants.getVar() ).getName()
										+";ID=" +  var2variants.getVar() + "-" + chr + "-" + strPos +
										";AlleleRef=" +   refNuc +
										 ";AlleleAlt=" + var2Nuc +
										 ";Position=" +  strPos +
										"\n");
							}
						}
						
						if(linecount > 100 ) {
							buff.append("###\n");
							bw.write(buff.toString());
							buff.delete(0, buff.length());
							buff = new StringBuffer();
							bw.flush();
							linecount = 0;
						}
						linecount++;
					}
			}
	
			bw.write(buff.toString());
			buff.delete(0, buff.length());
			buff = new StringBuffer();
			bw.flush();
			bw.close();
			
			AppContext.debug("temgff written in: " + file.getAbsolutePath() );
			log.info( "temgff written in: " + file.getAbsolutePath());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * Prepare JBRowse URL. JBrowse is not fetched/rendered until the tab is clicked, but the URL is already formulated
	 * @param chr
	 * @param start
	 * @param end
	 * @param locus
	 */
	private void updateJBrowse(String chr, String start, String end, String locus) {
		//return;
		
		chr = chr.trim();
		String chrpad = chr;
		if(chrpad.length()==1) chrpad="0" + chr;
		
		gfffile=null;
		
		String org = this.listboxReference.getSelectedItem().getLabel(); 
		/*
		if(org.equals(AppContext.getDefaultOrganism())) 
			chrpad = "loc=" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
		else if(org.equals("IR64-21"))
			chrpad = "data=ir6421v1&loc="+  chr + "|ir6421v1";
		else if(org.equals("93-11"))
			chrpad =  "data=9311v1&loc=" + chr + "|9311v1";
		else if(org.equals("DJ123"))
			chrpad = "data=dj123v1&loc=" + chr + "|dj123v1";
		else if(org.equals("Kasalath"))
			chrpad = "data=kasv1&loc=" + chr + "|kasv1";
			*/
		if(org.equals(AppContext.getDefaultOrganism())) 
			chrpad = "loc=" + chr ;
		else if(org.equals("IR64-21"))
			chrpad = "data=ir6421v1&loc="+  chr ;
		else if(org.equals("93-11"))
			chrpad =  "data=9311v1&loc=" + chr ;
		else if(org.equals("DJ123"))
			chrpad = "data=dj123v1&loc=" + chr ;
		else if(org.equals("Kasalath"))
			chrpad = "data=kasv1&loc=" + chr ;
		
		if (iframeJbrowse==null) throw new RuntimeException("jbrowse==null");
		
		String displaymode="%22displayMode%22:%22normal%22,%22maxHeight%22:%222000%22";
		
		if(tallJbrowse) {
			iframeJbrowse.setStyle("width:1500px;height:1000px;border:0px inset;" );
			//iframeJbrowse.setStyle("width:95%;height:1000px;border:0px inset;" );
			//displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%2232000%22";
			displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%222000%22";
		}
		else
			iframeJbrowse.setStyle("width:1500px;height:800px;border:0px inset;" );
			//iframeJbrowse.setStyle("width:95%;height:800px;border:0px inset;" );
		
		String  rendertype="";

			
		
		if(locus.isEmpty())		
			msgJbrowse.setValue("Chromosome " + chr + " [" + start + ".." + end + "]");
		else
			msgJbrowse.setValue(locus + "  Chromosome " + chr + " [" + start + ".." + end + "]");
		
		iframeJbrowse.setScrolling("yes");
		iframeJbrowse.setAlign("left");		

		//String urltemplate = "..%2F..%2F" + AppContext.getHostDirectory() + "%2Ftmp%2F" + "GFF_FILE";
		String urltemplate = "..%2F..%2Ftemp%2F" + "GFF_FILE";
		
		//if(checkShowsnp.isChecked() ) {

		
		String snp3kcore="";
		if(checkboxSNP.isChecked()) { 
			if(checkboxCoreSNP.isChecked()) snp3kcore="msu7coresnpsv2%2C";
		}
		if(checkboxIndel.isChecked())
			snp3kcore += "msu7indelsv2%2C";

		
		if(true) {
		
			if(tallJbrowse) {
				// for all varieties
				rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatchSynIndels%22";
				
				String showTracks =  AppContext.getJBrowseDefaulttracks() + "%2C" + snp3kcore;
				
				if(org.equals(AppContext.getDefaultOrganism())) 
						{}
				else if(org.equals("IR64-21"))
					showTracks = "IR64-21%20DNA,ir6421v1rengff,alignir6421v1vsmsu7%2C";
				else if(org.equals("93-11"))
					showTracks  = "9311%20DNA%2C9311v1rengff,";
				else if(org.equals("DJ123"))
					showTracks = "DJ123%20DNA%2C,dj123v1rengff,aligndj123v1vsmsu7%2C";
				else if(org.equals("Kasalath"))
						showTracks = "Kasalath%20DNA%2Ckasrapv1rengff,";
				
				
				//urljbrowse= AppContext.getHostname() + "/" + AppContext.getJbrowseDir() + "/?loc=chr"  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2Csnp3k%2C" + snp3kcore + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
				
				//urljbrowse= AppContext.getHostname() + "/" + AppContext.getJbrowseDir() + "/?loc="  + chrpad + ":" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2C" + snp3kcore + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
				urljbrowse= AppContext.getHostname() + "/" + AppContext.getJbrowseDir() + "/?" + chrpad + ":" + start + ".." + end +   "&tracks=" + showTracks  + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
					 "%22}}&addTracks=[{%22label%22%3A%22SNP%20Genotyping%22%2C%22type%22%3A" + rendertype + "%2C%22store%22%3A%22url%22%2C%20" + displaymode 
					 + ",%22metadata%22:{%22Description%22%3A%20%22Varieties%20SNP%20Genotyping%20in%20the%20region.%20Each%20row%20is%20a%20variety.%20Red%20means%20there%20is%20variation%20with%20the%20reference%22}"  
					 + ",%22fmtDetailValue_Name%22%3A%20%22function(name)%20%7B%20return%20%27%3Ca%20target%3D%5C%22variety%5C%22%20href%3D%5C%22/" + AppContext.getHostDirectory()  + "/_variety.zul%3Fname%3D%27%2Bname%2B%27%5C%22%3E%27%2Bname%2B%27%3C%2Fa%3E%27%3B%20%7D%22%20"
					 + "%2C%20%22style%22%3A{%22showLabels%22%3A%22false%22%2C%22textFont%22%3A%22normal%208px%20Univers%2CHelvetica%2CArial%2Csans-serif%22}}]&highlight=";
					//"fmtDetailValue_Name": "function(name) { return '<a target=\"variety\" href=\"_variety.zul?name='+name+'\">'+name+'</a>'; }" 
			}
			else
			{
				if(radioColorAllele.isSelected()) {
					rendertype = "CanvasFeatures2Vars";
				} else if (radioColorMismatch.isSelected() )
				{			
					 rendertype = "CanvasFeatures2VarsMismatch";
				}
				//String snp3kcore="";
				//if(checkboxCoreSNP.isChecked()) snp3kcore="snp3kcore,";
				
				String showTracks = AppContext.getJBrowseDefaulttracks() + "," + snp3kcore;
				if(org.equals(AppContext.getDefaultOrganism())) 
					{} 
				else if(org.equals("IR64-21"))
					showTracks = "IR64-21%20DNA,ir6121v1rengff,alignir6421v1vsmsu7";
				else if(org.equals("93-11"))
					showTracks  = "9311%20DNA,9311v1rengff";
				else if(org.equals("DJ123"))
					showTracks = "DJ123%20DNA%2Cdj123v1rengff,aligndj123v1vsmsu7";
				else if(org.equals("Kasalath"))
						showTracks = "Kasalath%20DNA,kasrapv1rengff";
				
				
				// for 2 varieties
				//urljbrowse= AppContext.getHostname() + "/" + AppContext.getJbrowseDir() + "/?loc=" + chrpad + ":" + start + ".." + end + 
				urljbrowse= AppContext.getHostname() + "/" + AppContext.getJbrowseDir() + "/?" +  chrpad + ":" + start + ".." + end +
					//"&tracks=DNA,msu7gff,snp3k," + snp3kcore + "SNP%20Genotyping&addStores={%22url%22:{%22type%22:%22JBrowse/Store/SeqFeature/GFF3%22,%22urlTemplate%22:%22" + urltemplate
					"&tracks=" + showTracks  + "&SNP%20Genotyping&addStores={%22url%22:{%22type%22:%22JBrowse/Store/SeqFeature/GFF3%22,%22urlTemplate%22:%22" + urltemplate
					+ "%22}}&addTracks=[{%22label%22:%22SNP%20Genotyping%22,%22type%22:%22JBrowse/View/Track/" + rendertype + "%22,%22store%22:%22url%22," + displaymode + 
					 ",%22fmtDetailValue_Name%22%3A%20%22function(name)%20%7B%20return%20%27%3Ca%20target%3D%5C%22variety%5C%22%20href%3D%5C%22/" + AppContext.getHostDirectory() + "/_variety.zul%3Fname%3D%27%2Bname%2B%27%5C%22%3E%27%2Bname%2B%27%3C%2Fa%3E%27%3B%20%7D%22%20"
					+ ",%22style%22:{%22showLabels%22:%22false%22,%22textFont%22:%22normal 8px Univers,Helvetica,Arial,sans-serif%22," +
					"%22showLabels%22:%22false%22,%22label%22:%22Name%22,%22strandArrow%22:%22false%22}}]";
			
			}
		}
			
	}
	



// **********************************************  HANDLE PHYLOGENETIC TREE EVENTS *************************************************************

@Listen("onClick = #buttonRenderTree")
//@Listen("onSelect = #tabPhylo")    
public void onselectTabPhylo() {
	
	AppContext.debug("loading phylotree " + urlphylo);
	urljbrowsephylo=null;
	boolean success=true;
	
	if(urlphylo!=null) {
		
		buttonDownloadNewick.setVisible(false);
		tabJBrowsePhylo.setVisible(false);
		
		Sessions.getCurrent().removeAttribute("phyloorder");
		Sessions.getCurrent().removeAttribute("newick");
		
		Clients.showBusy("Computing Phylogenetic Tree");
		iframePhylotree.setSrc( urlphylo );
		
		buttonRenderTree.setDisabled(true);
		int count=0;
		
		while(Sessions.getCurrent().getAttribute("newick")==null) {
			try {
			Thread.sleep(5000);
			//AppContext.debug("waiting phylotree in session");
			count++;
			
			if(count>120) {
				AppContext.debug("phylotree timeout 10 mins");
				success=false;
				break;
			}
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		buttonRenderTree.setDisabled(false);
		Clients.clearBusy();
	}

	if(success) {
		buttonDownloadNewick.setVisible(true);
		iframePhylotree.setVisible(true);
		tabJBrowsePhylo.setVisible(true);
	} else 
		Messagebox.show("Phylogenetic tree construction timed out!");
	
	urlphylo=null;

}




/**
 * Prepare phylogenetic tree URL. Tree is not computed/displayed until the tab is clicked
 * @param chr
 * @param start
 * @param end
 */

private void update_phylotree(String  chr, String start, String end, int nvars) {

		//int nvars = genotype.getListSNPAllVarsMismatches().size();
		int height = nvars*21;
		int width = nvars*30;
		
		int treescale = 1;
		
		iframePhylotree.setStyle("height:" + Integer.toString(height) + "px;width:1400px");
		iframePhylotree.setScrolling("yes");
		
		String topN="-1";
		if(!selectPhyloTopN.getSelectedItem().getLabel().equals("all"))
			topN=selectPhyloTopN.getSelectedItem().getLabel();

		//String mindist = selectPhyloMindist.getSelectedItem().getLabel();
		
		//if(gfffile==null) gfffile = "tmp_" + AppContext.createTempFilename() + ".gff"; 
		
		
		//urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=" + gfffile.replace(".gff","") + "&mindist=" + intPhyloMindist.getValue();
		//urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=" + gfffile.replace(".gff","") + "&mindist=" + intPhyloMindist.getValue();
		urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=GFF_FILE&mindist=" + intPhyloMindist.getValue();
		//log.debug(urlphylo);
		//AppContext.debug(urlphylo);
}

@Listen("onClick = #buttonDownloadNewick")
public void onselectdownloadnewick() {
	
	String newick = (String)Sessions.getCurrent().getAttribute("newick");
	if(newick!=null) {
		try {
			String filetype = "text/plain";
			Filedownload.save(  newick , filetype , "newick_" + AppContext.createTempFilename() + ".newick");
			//AppContext.debug("File download complete! Saved to: "+filename);
			org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
			AppContext.debug("newick downlaod complete! Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
	} else
		Messagebox.show("Phylogenetic tree not yet computed");
}


// **************************************************** HANDLE DOWNLOAD SNPS *******************************

/**
 * Download Tab format
 */
@Listen("onClick = #buttonDownloadTab")    
public void downloadTab()    {
	if(biglistboxArray.isVisible())
		downloadBigListbox(AppContext.getTempDir() + "snp3kvars-" + queryFilename() + ".txt", "\t");
	else if(divPairwise.isVisible())
		download2VarsList(AppContext.getTempDir() + "snp2vars-" +   queryFilename() + ".txt", "\t");
}


@Listen("onClick = #buttonDownloadPlink")    
public void downloadPlink()    {
	 //downloadDelimited("snpfile.txt", "\t"); 
	if(biglistboxArray.isVisible())
		downloadBigListboxPlinkZip(AppContext.getTempDir() + "snp3kvars-" + queryFilename() + "-" +  AppContext.createTempFilename() );
	
}

/**
 * Download CSV format
 */
@Listen("onClick = #buttonDownloadCsv")    
public void downloadCsv()    {
	if(this.biglistboxArray.isVisible())
		downloadBigListbox(AppContext.getTempDir() + "snp3kvars-" + queryFilename() + ".csv", ",");
	else if(divPairwise.isVisible())
		download2VarsList(AppContext.getTempDir() +  "snp2vars-" +   queryFilename() + ".csv", ",");
}


@Listen("onClick = #buttonDownloadPairwiseCsv")    
public void downloadPairwiseCsv()    {
	if(divPairwise.isVisible())
		download2VarsList(AppContext.getTempDir() +  "snp2vars-" +   queryFilename() + ".csv", ",");
}

@Listen("onClick = #buttonDownloadPairwiseTab")    
public void downloadPairwiseTab()    {
	if(divPairwise.isVisible())
		download2VarsList(AppContext.getTempDir() +  "snp2vars-" +   queryFilename() + ".txt", ",");
}




private void download2VarsList(String filename, String delimiter) {
	AppContext.debug("downloading from 2vars table...");
	StringBuffer buff = new StringBuffer();
	
	//buff.append("CONTIG").append(delimiter).append("POSITION").append(delimiter).append("REFERENCE").append(delimiter);
	
	Iterator itHead =  listboxSnpresult.getHeads().iterator();
	while(itHead.hasNext()) {
		Component comp = (Component)itHead.next();
		if(!comp.isVisible()) continue;
		if(comp instanceof Listhead) {
			Listhead cols = ( Listhead )comp;
			Iterator itHeadcol = cols.getChildren().iterator();
			while(itHeadcol.hasNext()) {
				Listheader header =  (Listheader)itHeadcol.next();
				if(!header.isVisible())  continue;
				buff.append( header.getLabel() );
				if(itHeadcol.hasNext()) buff.append(delimiter);
			}
		}
		if(comp instanceof Auxhead) {
			Auxhead cols = ( Auxhead )comp;
			Iterator itHeadcol = cols.getChildren().iterator();
			while(itHeadcol.hasNext()) {
				Auxheader header =  (Auxheader)itHeadcol.next();
				if(!header.isVisible()) continue;
				buff.append( header.getLabel() );
				if(itHeadcol.hasNext()) buff.append(delimiter);
			}
		} 
		if(comp instanceof Columns) {
			Columns cols = ( Columns )comp;
			Iterator itCol = cols.getChildren().iterator();
			while(itCol.hasNext()) {
				Column col = (Column)itCol.next();
				if(!col.isVisible())  continue;
				buff.append(col.getLabel());
				if(itCol.hasNext()) buff.append(delimiter);
			}
		}
		buff.append("\n");
	}
	
	ListModel model=listboxSnpresult.getModel();
	
	for(int i=0; i<model.getSize(); i++) {
		Object[] row =  (Object[])model.getElementAt(i);
		

		
		for(int j=0; j<row.length; j++) {
			
			//AppContext.debug(j + "   "  + row[j]);
			
			if(j==0) continue;
			if(j==1) {
				buff.append( ((Position)row[j]).getContig());
				buff.append(delimiter);
				buff.append( ((Position)row[j]).getPosition().toString().replaceAll(".00",""));
				buff.append(delimiter);
				continue;
			}
			if(row[j]!=null)
				buff.append(row[j].toString());
			else buff.append("");
			if(j<row.length-1) buff.append(delimiter);
		}
		buff.append("\n");
	}
	
	
	try {
		String filetype = "text/plain";
		if(delimiter.equals(",")) filetype="text/csv";
		Filedownload.save(  buff.toString(), filetype , filename);
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
		AppContext.debug("snp2vars downlaod complete!"+ filename +  " Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );

		
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	

}

private void downloadBigListbox(String filename, String delimiter) {
	
	try {
	
	Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
	
	
	StringBuffer buff = new StringBuffer();
	
	buff.append(this.listboxReference.getSelectedItem().getLabel().toUpperCase() + " POSITIONS").append(delimiter).append("IRIS ID").append(delimiter).append("SUBPOPULATION").append(delimiter).append("MISMATCH").append(delimiter);

	/*
	if(this.checkboxShowNPBPosition.isChecked())
		buff.append(this.listboxReference.getSelectedItem().getLabel().toUpperCase() + " POSITIONS").append(delimiter).append("IRIS ID").append(delimiter).append("SUBPOPULATION").append(delimiter).append("MISMATCH").append(delimiter);
	else
		buff.append("VARIETY").append(delimiter).append("IRIS ID").append(delimiter).append("SUBPOPULATION").append(delimiter).append("MISMATCH").append(delimiter);
	*/
	
	String[] contigs = table.getContigs();
	//BigDecimal[] positions = table.getPosition(); 
	Position[] positions = table.getPosition();
	StringBuffer buffPos = new StringBuffer();
	
	// check if multiple contig

	boolean isMulticontig=false;
	String contig0=positions[0].getContig();
	for(int i=2; i<positions.length; i++) {
		if(!contig0.equals(positions[i].getContig())) {
			isMulticontig=true;
			break;
		}
	}
		
	for(int i=0; i<positions.length; i++) {
		if(isMulticontig) buff.append( positions[i].getContig()).append("-");
		buff.append(positions[i].getPosition());
		if(i<positions.length-1) buff.append(delimiter);
	}

	buff.append("\n" + listboxReference.getSelectedItem().getLabel().toUpperCase() + " ALLELES").append(delimiter).append(delimiter).append(delimiter).append(delimiter);
	
	/*
	if(this.checkboxShowNPBPosition.isChecked())
		buff.append("\n" + listboxReference.getSelectedItem().getLabel().toUpperCase() + " ALLELES").append(delimiter).append(delimiter).append(delimiter).append(delimiter);
	else 
		buff.append("\nREFERENCE").append(delimiter).append(delimiter).append(delimiter).append(delimiter);
	*/
	
	String refs[] =  table.getReference();
	for(int i=0; i<refs.length; i++) {
		
		String refnuc = refs[i]; 
		if(refnuc==null || refnuc.isEmpty()) {
			//tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-frozenCols);
			BigDecimal pos = table.getVariantStringData().getListPos().get(i).getPos();
			if(table.getVariantStringData().getIndelstringdata()!=null && table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc()!=null)
				refnuc = table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
		}
		
		if(refnuc==null)
			buff.append("");
		else buff.append(refnuc);
		
		if(i<refs.length-1) buff.append(delimiter);
	}
	buff.append("\n");
	

	
	if(checkboxShowNPBPosition.isChecked()) {
		buff.append("NIPPONBARE POSITION").append(delimiter).append("").append(delimiter).append("").append(delimiter).append(delimiter);
		
		
		positions = table.getPositionNPB(); 
		for(int i=0; i<positions.length; i++) {
			buff.append(positions[i]);
			if(i<positions.length-1) buff.append(delimiter);
		}

		buff.append("\nNIPPONBARE ALLELES").append(delimiter).append(delimiter).append(delimiter).append(delimiter);
		
		refs =  table.getReferenceNPB();
		for(int i=0; i<refs.length; i++) {
			
			String refnuc = refs[i]; 
			if(refnuc.isEmpty()) {
				//tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-frozenCols);
				BigDecimal pos = table.getVariantStringData().getListPos().get(i).getPos();
				if(table.getVariantStringData().getIndelstringdata()!=null && table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc()!=null)
					refnuc = table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
			}
			
			buff.append(refnuc);
			if(i<refs.length-1) buff.append(delimiter);
		}
		buff.append("\n");
		
		
	}
	
	
	if(this.checkboxShowAllRefAlleles.isChecked()) {
		 String allrefsalleles[][] = table.getAllrefalleles();
		 String refnames[] = table.getAllrefallelesnames();
		 for(int iref=0; iref<refnames.length; iref++) {
			 buff.append(refnames[iref]).append(delimiter).append(delimiter).append(delimiter).append(delimiter);

			String irefs[] = allrefsalleles[iref];
			for(int i=0; i<refs.length; i++) {
				
				String refnuc = irefs[i];
				/*
				if(refnuc.isEmpty()) {
					//tabledata.getIndelstringdata().getMapIndelIdx2Refnuc().get(colIndex-frozenCols);
					BigDecimal pos = table.getVariantStringData().getListPos().get(i).getPos();
					if(table.getVariantStringData().getIndelstringdata()!=null && table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc()!=null)
						refnuc = table.getVariantStringData().getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
				}
				*/
				buff.append(refnuc);
				if(i<refs.length-1) buff.append(delimiter);
			}
			buff.append("\n");
		 }
	}

	
	Object varalleles[][] = table.getVaralleles();
	AppContext.debug( "mxn=" + varalleles.length  + "x" + varalleles[0].length);
	AppContext.debug("positions = " + refs.length);
	AppContext.debug("varids = " + table.getVarname().length);
	
	Map<BigDecimal,Variety> mapVarId2Var = varietyfacade.getMapId2Variety();
	
	for(int i=0; i< table.getVarid().length; i++) {
		String varname=table.getVarname()[i];
		if(delimiter.equals(",") && varname.contains(","))
			varname = "\"" + varname + "\"";
		
		Variety var = mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i]));
		buff.append(varname).append(delimiter).append(  var.getIrisId()  ).append(delimiter).append(var.getSubpopulation()).append(delimiter).append(table.getVarmismatch()[i]).append(delimiter);
		for(int j=0; j<refs.length; j++) {
			Object allele = varalleles[i][j];
			if(allele==null)
				buff.append( "");
			else buff.append( varalleles[i][j] );
			if(j<refs.length-1) buff.append(delimiter);
		}
		buff.append("\n");
	}
	
	
	
		String filetype = "text/plain";
		if(delimiter.equals(",")) filetype="text/csv";
		Filedownload.save(  buff.toString(), filetype , filename);
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
		AppContext.debug("snpallvars download complete!"+ filename +  " Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
		
	} catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}


private void downloadBigListboxPlinkZip(String filename) {
	
//	MAP files
//	The fields in a MAP file are:
//	Chromosome
//	Marker ID
//	Genetic distance
//	Physical position
//
//	PED files
//	The fields in a PED file are
//	Family ID
//	Sample ID
//	Paternal ID
//	Maternal ID
//	Sex (1=male; 2=female; other=unknown)
//	Affection (0=unknown; 1=unaffected; 2=affected)
//	Genotypes (space or tab separated, 2 for each marker. 0=missing)
//	
	
	
//	-HapMap file format:
//		The current release consists of text-table files only, with the following columns:
//
//		Col1: refSNP rs# identifier at the time of release (NB: it might merge 
//		  with another rs# in the future)
//		Col2: SNP alleles according to dbSNP
//		Col3: chromosome that SNP maps to 
//		Col4: chromosome position of SNP, in basepairs on reference sequence
//		Col5: strand of reference sequence that SNP maps to
//		Col6: version of reference sequence assembly (currently NCBI build36)
//		Col7: HapMap genotyping center that produced the genotypes
//		Col8: LSID for HapMap protocol used for genotyping
//		Col9: LSID for HapMap assay used for genotyping
//		Col10: LSID for panel of individuals genotyped
//		Col11: QC-code, currently 'QC+' for all entries (for future use)
//		Col12 and on: observed genotypes of samples, one per column, sample
//		     identifiers in column headers (Coriell catalog numbers, example:
//		      NA10847).
		      
		      
	try {
	String chr= this.selectChr.getValue();
	
	
	Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
	
	
	
	StringBuffer buff = new StringBuffer();
	//buff.append("POSITION").append(delimiter).append("MISMATCH").append(delimiter);
	Position[] positions = table.getPosition();
	String refs[] =  table.getReference();
	String contigs[] = table.getContigs();
	for(int i=0; i<positions.length; i++) {
		if(!refs[i].equals("-")) {
			int pos = positions[i].getPosition() .intValue();
			if(contigs!=null)
				buff.append(chr).append("\t").append( "1" +  String.format("%02d", Integer.valueOf(AppContext.guessChrFromString(contigs[i]))) +  String.format("%08d", pos) ) .append("\t0\t").append(pos).append("\n");
			//buff.append(chr).append("\t").append("snp" + chr + "-" + pos) .append("\t0\t").append(pos+1).append("\n");
			else buff.append(chr).append("\t").append( "1" +  String.format("%02d", Integer.valueOf(chr.toLowerCase().replace("chr", ""))) +  String.format("%08d", pos) ) .append("\t0\t").append(pos).append("\n");
		}
	}

		//String filetype = "text/plain";
		//Filedownload.save(  buff.toString(), filetype , filename + ".map");
		
		FileWriter writer = new FileWriter( filename + ".map");
		writer.append(buff.toString());
		writer.flush();
		writer.close();
		
		//AppContext.debug("map: "+ buff.toString() );
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
		AppContext.debug("snpallvars download complete!"+ filename +".map Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
		
	
	
	String allzipfilenames[] = new String[]{ filename + ".map",  filename + ".ped"};
	
	buff = new StringBuffer();
	
	Object[][] varalleles = table.getVaralleles();
	Map<BigDecimal,Variety> mapVarId2Var = varietyfacade.getMapId2Variety();
	for(int i=0; i< table.getVarid().length; i++) {
		
		//if(mapVarId2Var.get(table.getVarid()[i])==null) throw new RuntimeException("null variety with id=" + table.getVarid()[i])
		String indvid= mapVarId2Var.get( BigDecimal.valueOf(table.getVarid()[i]) ).getIrisId().replaceAll(" ","_");
		buff.append( indvid).append("\t").append( indvid).append("\t0\t0\t0\t-9\t");

//		Family ID
//		Sample ID
//		Paternal ID
//		Maternal ID
//		Sex (1=male; 2=female; other=unknown)
//		Affection (0=unknown; 1=unaffected; 2=affected)
//		Genotypes (space or tab separated, 2 for each marker. 0=missing)
		
		for(int j=0; j<refs.length; j++) {
			String allele1= (String)varalleles[i][j];
			if(allele1.isEmpty()) buff.append("0\t0");
			else {
				String alleles[] = allele1.split("/");
				if(alleles.length==1)
					buff.append(allele1).append("\t").append(allele1);
				else 
					buff.append(alleles[0]).append("\t").append(alleles[1]);
			}
			if(j<refs.length-1) buff.append("\t");
		}
		buff.append("\n");
	}
	

		
		writer = new FileWriter( filename + ".ped");
		writer.append(buff.toString());
		writer.flush();
		writer.close();
		
		new CreateZipMultipleFiles(filename + "-plink-" + ".zip", allzipfilenames ).create();
		Filedownload.save(new File(filename + "-plink-" +  ".zip") , "application/zip");

		zksession = Sessions.getCurrent();
		AppContext.debug("snpallvars download complete!"+ filename +  ".ped Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
		
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
}


private String queryFilename() {
	String filename = comboGene.getValue();
	if(filename.isEmpty()) {
		if( intStart !=null && intStart.getValue()!=null && intStop !=null && intStop.getValue()!=null )
			filename = "chr" + selectChr.getValue() + "-" +  intStart.getValue() + "-" + intStop.getValue();
		else if(this.listboxMySNPList.getSelectedIndex()>0) {
			filename = this.listboxMySNPList.getSelectedItem().getLabel();			
		}
	}
	return filename;
}



/**
 * Download entire genome SNPs for varieties in list, to filename using delimiter
 * @param filename
 * @param delimiter
 */
private void downloadGenome(String filename, String delimiter) {

	try {
		workspace = (WorkspaceFacade)AppContext.checkBean(workspace,"WorkspaceFacade");
		Set setVarieties = workspace.getVarieties( listboxMyVarieties.getSelectedItem().getLabel() );
		GenotypeQueryParams params = new GenotypeQueryParams(setVarieties, selectChr.getValue(), null,
				null ,  checkboxSNP.isChecked() , checkboxIndel.isChecked(), checkboxCoreSNP.isChecked() ,
				checkboxMismatchOnly.isChecked(), null, null, null, checkboxAlignIndels.isChecked(), this.checkboxShowAllRefAlleles.isChecked());			
		params.setDelimiter(delimiter);
		params.setFilename(filename);
		genotype.downloadGenotypeGenome(params);
	} catch(Exception ex)
	{
		ex.printStackTrace();
		Messagebox.show(ex.getMessage(), null, Messagebox.OK, Messagebox.ERROR);
	}
}


@Listen("onClick = #buttonDownloadFasta")
public void onclickDownloadFasta() {

	try {
		GenotypeQueryParams params =  fillGenotypeQueryParams();
		genotype = (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		
		String filename=null;
		Collection colLoci=params.getColLoci();
		if(colLoci==null || colLoci.isEmpty()) {
			colLoci=new ArrayList();
			colLoci.add(new MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), 1, params.getsLocus()));
			if(params.getsLocus()!=null && !params.getsLocus().isEmpty())
				filename=  params.getsChr() + "_" + params.getlStart() + "-" + params.getlEnd() + "_" + params.getsLocus();
			else filename=  params.getsChr() + "_" + params.getlStart() + "-" + params.getlEnd();
		} else {
			 filename = listboxMyLocusList.getSelectedItem().getLabel();
		}

		String tmpdir = AppContext.getTempDir() + "fasta-" + AppContext.createTempFilename();
		String locusfilenames[] = new String[colLoci.size()*2];
		
		Iterator<Locus>  itLocus =  colLoci.iterator();
		int locuscount=0;
		while(itLocus.hasNext()) {
			Locus loc=itLocus.next();
			String locusfilename=loc.getUniquename();
			if(locusfilename==null || locusfilename.isEmpty()) {
				locusfilename=loc.getChr() + "_" + loc.getFmin() + "-" + loc.getFmax();
			}
			locusfilename+= ".fasta";
			new File(tmpdir).mkdir();
			locusfilename= tmpdir + "/" + locusfilename;
			locusfilenames[locuscount]=locusfilename;
			
			genotype.downloadFastaMSAPerLocus( params,  loc, locusfilename);
			
			locuscount++;
			
			locusfilenames[locuscount]=locusfilename.replace(".fasta",".csv") ;
			
			locuscount++;
		}
		
		new CreateZipMultipleFiles(filename + "-fasta" + ".zip", locusfilenames ).create();
		Filedownload.save(new File(filename + "-fasta" +  ".zip") , "application/zip");
		AppContext.debug("File download complete! Saved to: "+ filename + "-fasta-" +  ".zip");

	} catch(Exception ex) {
		ex.printStackTrace();
		Messagebox.show(ex.getMessage());
		
	}
		
}



	// ********************************************* HANDLE ALLELEFREQUENCY CHART EVENTS *********************************************************


	@Listen("onClick=#radioShowGenotypeFrequency")
	public void onclickradioShowGenotypeFrequency() {
		this.updateAlleleFrequencyChart();
		radioMajorAlleles.setLabel("Major genotypes");
		radioMinorAlleles.setLabel("Minor genotypes");
		radio3rdAlleles.setLabel("3rd genotypes");


	}
	@Listen("onClick=#radioShowGenotypeCount")
	public void onclickradioShowGenotypeCount() {
		this.updateAlleleFrequencyChart();
		radioMajorAlleles.setLabel("Major genotypes");
		radioMinorAlleles.setLabel("Minor genotypes");
		radio3rdAlleles.setLabel("3rd genotypes");
	}

	@Listen("onClick=#radioShowAlleleFrequency")
	public void onclickradioShowAlleleFrequency() {
		this.updateAlleleFrequencyChart();
		radioMajorAlleles.setLabel("Major alleles");
		radioMinorAlleles.setLabel("Minor alleles");
		radio3rdAlleles.setLabel("3rd alleles");
	}
	@Listen("onClick=#radioShowAlleleCount")
	public void onclickradioShowAlleleCount() {
		this.updateAlleleFrequencyChart();
		radioMajorAlleles.setLabel("Major alleles");
		radioMinorAlleles.setLabel("Minor alleles");
		radio3rdAlleles.setLabel("3rd alleles");
	}



	@Listen("onClick=#radioMajorAlleles")
	public void onclickradioMajorAlleles() {
		this.updateAlleleFrequencyChart();
	}
	@Listen("onClick=#radioMinorAlleles")
	public void onclickradioMinorAlleles() {
		this.updateAlleleFrequencyChart();
	}
	@Listen("onClick=#radio3rdAlleles")
	public void onclickradio3rdAlleles() {
		this.updateAlleleFrequencyChart();
	}


	private class AlleleFreqLineData {
		CategoryModel linecountmajormodel; 
		CategoryModel linecountminormodel;
		CategoryModel line3rdallelemodel;
		CategoryModel linepercentmajormodel;
		CategoryModel linepercentminormodel;
		CategoryModel linepercent3rdmodel;
		Map<String, List> mapPos2Alleles;
	}

	/**
	 * calculate allele and genotype counts from table data
	 */
	private void calculateAlleleFrequencies() {
		
		Map<String, Map<String, Map<String,Integer>>> mapPos2Subpop2Allele2Count=new TreeMap();
		Map<String, Map<String, Map<String,Integer>>> mapPos2Subpop2Genotype2Count=new TreeMap();
		
		Object2StringMatrixModel model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
		int poscol=-1;
		List listPos = (List)model.getHeadAt(0);
		Set subpops = new LinkedHashSet();
		subpops.add("all");
		//for(int i=0; i<biglistboxArray.getCols(); i++) {
		
		int startCol=frozenCols + 1;
		if(listboxPhenotype.getSelectedItem()!=null && !listboxPhenotype.getSelectedItem().getLabel().isEmpty()) {
			startCol++;
		};
		
		for(int i= startCol ; i<model.getColumnSize(); i++) {
			
			//AppContext.debug(listPos.get(i).getClass() + ", " +  listPos.get(i).toString());
			
			 String contigpos = listPos.get(i).toString();
			 Map mapSubpop2Allele2Count =  new TreeMap();
			 Map mapSubpop2Genotype2Count =  new TreeMap();
			 
			 for(int j=0; j<model.getSize(); j++) {
				 
				 //String subpop = model.getCellAt(  model.getElementAt(j), 2).toString();
				 
				 String subpop = ((Object[])model.getElementAt(j).get(2))[2].toString();
				 
				 
				 subpops.add(subpop);
				 Map mapAllele2Count =  (Map)mapSubpop2Allele2Count.get(subpop);
				 if(mapAllele2Count==null) {
					 mapAllele2Count=new HashMap();
					 mapSubpop2Allele2Count.put(subpop, mapAllele2Count);
				 }
				 
				 String allele  = ((Object[])model.getElementAt(j).get(i))[i].toString();
				 
				 allele=allele.trim();
				 
				 //if(allele.isEmpty()) allele=" ";
				 if(allele.isEmpty()) continue;
				 
				 if(allele.contains("/")) {

					 // heterogygous
					 
					 String alleles12[]=null;
					 try {
						 alleles12 = new String[] { String.valueOf(allele.charAt(0)), String.valueOf(allele.charAt(2)) };
						 
					 }catch(Exception ex) {
						 AppContext.debug("allele=" + allele);
						 //ex.printStackTrace();
						 //throw new RuntimeException(ex);
						 if(allele.startsWith("/")) 
							 alleles12 = new String[] { String.valueOf(" "), String.valueOf(allele.charAt(1))};
						 else if(allele.endsWith("/")) 
							 alleles12 = new String[] { String.valueOf(allele.charAt(1)), String.valueOf(" ")};
						 else alleles12 = new String[] {String.valueOf(" "), String.valueOf(" ")};
					 }
					 
					 Integer allele1count = (Integer)mapAllele2Count.get(alleles12[0]);
					 if(allele1count==null) allele1count=0;
					 allele1count=allele1count+1;
					 mapAllele2Count.put(alleles12[0] , allele1count);
					 
					 Integer allele2count = (Integer)mapAllele2Count.get(alleles12[1]);
					 if(allele2count==null) allele2count=0;
					 allele2count=allele2count+1;
					 mapAllele2Count.put(alleles12[1] , allele2count);

					 //AppContext.debug(subpop + "  "  + contigpos + "  " + allele +  " mapAllele2Count=" + mapAllele2Count + "   added alleles " + alleles12[0] + ", " + alleles12[1]);
					 
					 mapSubpop2Allele2Count.put(subpop, mapAllele2Count);
					 
					 
					 Map mapAllele2CountAll =  (Map)mapSubpop2Allele2Count.get("all");
					 if(mapAllele2CountAll==null) {
						 mapAllele2CountAll=new HashMap();
						 mapSubpop2Allele2Count.put("all", mapAllele2CountAll);
					 }

					 Integer allele1countall = (Integer)mapAllele2CountAll.get(alleles12[0]);
					 if(allele1countall==null) allele1countall=0;
					 allele1countall=allele1countall+1;
					 mapAllele2CountAll.put(alleles12[0] , allele1countall);
					 

					 Integer allele2countall = (Integer)mapAllele2CountAll.get(alleles12[1]);
					 if(allele2countall==null) allele2countall=0;
					 allele2countall=allele2countall+1;
					 mapAllele2CountAll.put(alleles12[1] , allele2countall);

					 mapSubpop2Allele2Count.put("all", mapAllele2CountAll);

				 }
				 else {
					 
					 // homozygous
					 
					 Integer allelecount = (Integer)mapAllele2Count.get(allele);
					 if(allelecount==null) allelecount=0;
					 allelecount=allelecount+2;
					 mapAllele2Count.put(allele , allelecount);
				 
					 mapSubpop2Allele2Count.put(subpop, mapAllele2Count);
					 
					 Map mapAllele2CountAll =  (Map)mapSubpop2Allele2Count.get("all");
					 if(mapAllele2CountAll==null) {
						 mapAllele2CountAll=new HashMap();
						 mapSubpop2Allele2Count.put("all", mapAllele2CountAll);
					 }
					 Integer allelecountall = (Integer)mapAllele2CountAll.get(allele);
					 if(allelecountall==null) allelecountall=0;
					 allelecountall=allelecountall+2;
					 mapAllele2CountAll.put(allele , allelecountall);
					 
					 //AppContext.debug("i=" + i + "  j=" + j + " subpop=" + subpop + "  allele=" + allele + "  allelecount=" +  allelecount + "  allelecountall=" +  allelecountall);
					 
					 mapSubpop2Allele2Count.put("all", mapAllele2CountAll);
				 }

				 
				 // genotype count
				 
				 Map mapGenotype2Count =  (Map)mapSubpop2Genotype2Count.get(subpop);
				 if(mapGenotype2Count==null) {
					 mapGenotype2Count=new HashMap();
					 mapSubpop2Genotype2Count.put(subpop, mapGenotype2Count);
				 }

				 
				 Integer genotypecount = (Integer)mapGenotype2Count.get(allele);
				 if(genotypecount==null) genotypecount=0;
				 genotypecount=genotypecount+1;
				 mapGenotype2Count.put(allele , genotypecount);
			 
				 mapSubpop2Genotype2Count.put(subpop, mapGenotype2Count);
				 
				 Map mapGenotype2CountAll =  (Map)mapSubpop2Genotype2Count.get("all");
				 if(mapGenotype2CountAll==null) {
					 mapGenotype2CountAll=new HashMap();
					 mapSubpop2Genotype2Count.put("all", mapGenotype2CountAll);
				 }
				 Integer genotypecountall = (Integer)mapGenotype2CountAll.get(allele);
				 if(genotypecountall==null) genotypecountall=0;
				 genotypecountall=genotypecountall+1;
				 mapGenotype2CountAll.put(allele , genotypecountall);
				 
				 //AppContext.debug("i=" + i + "  j=" + j + " subpop=" + subpop + "  allele=" + allele + "  allelecount=" +  allelecount + "  allelecountall=" +  allelecountall);
				 
				 mapSubpop2Genotype2Count.put("all", mapGenotype2CountAll);
				 
				 
			 }
			 
			 mapPos2Subpop2Allele2Count.put(contigpos, mapSubpop2Allele2Count);
			 mapPos2Subpop2Genotype2Count.put(contigpos, mapSubpop2Genotype2Count);
		}
		
		//AppContext.debug("varieties " + model.getSize());
		//AppContext.debug("mapPos2Subpop2Allele2Count=" + mapPos2Subpop2Allele2Count);
		//AppContext.debug("mapPos2Subpop2Genotype2Count=" + mapPos2Subpop2Genotype2Count);
		
		allelefreqlines = calcFreq( subpops,  mapPos2Subpop2Allele2Count);
		genotypefreqlines = calcFreq( subpops,  mapPos2Subpop2Genotype2Count);

		updateAlleleFrequencyChart(); 
		
	}

	/**
	 * Calculate frequency from count
	 * @param subpops
	 * @param mapPos2Subpop2Allele2Count
	 * @return
	 */
	private AlleleFreqLineData calcFreq(Collection subpops, Map<String, Map<String, Map<String,Integer>>>  mapPos2Subpop2Allele2Count) {

		AlleleFreqLineData freqlines =  new AlleleFreqLineData();
		
		freqlines.linecountmajormodel = new DefaultCategoryModel(); 
		freqlines.linecountminormodel = new DefaultCategoryModel();
		freqlines.line3rdallelemodel = new DefaultCategoryModel();
		freqlines.linepercentmajormodel = new DefaultCategoryModel();
		freqlines.linepercentminormodel = new DefaultCategoryModel();
		freqlines.linepercent3rdmodel = new DefaultCategoryModel();
		freqlines.mapPos2Alleles = new HashMap();
		
		
		Iterator<String> itSubpop =  subpops.iterator();
		while(itSubpop.hasNext()) {
			String subpop = itSubpop.next();
			 
			Iterator<String> itPos =  mapPos2Subpop2Allele2Count.keySet().iterator();
			while(itPos.hasNext()) {
				String pos = itPos.next();
				Map mapSub2Allele =  mapPos2Subpop2Allele2Count.get(pos);
				if(mapSub2Allele==null) continue;
				
				Map mapAllele2Count = (Map)mapSub2Allele.get(subpop);
				if(mapAllele2Count==null) continue;
				
				// get major, minor allele
				
				List listCount = new ArrayList();
				listCount.addAll(mapAllele2Count.values());
				Collections.sort(listCount);
				freqlines.mapPos2Alleles.put( pos , listCount);
				
				Map mapCount2Allele=new TreeMap();
				Iterator<String> itAl=mapAllele2Count.keySet().iterator();
				while(itAl.hasNext()) {
					String al = itAl.next();
					mapCount2Allele.put(mapAllele2Count.get(al),al);
				}
				
				int allallelecount = 0;
				Iterator<Integer> itCount = listCount.iterator();
				while(itCount.hasNext()) allallelecount+=itCount.next();
				
				//AppContext.debug( subpop + ", pos=" + pos + ",  count=" +  allallelecount );
				
				if(mapAllele2Count.keySet().size()==1) {
					//pos = pos + " [" + mapCount2Allele.get(listCount.get(0)) + " 100%" + "]";  
					freqlines.linecountmajormodel.setValue( subpop, pos, (Integer)listCount.get(0));
					freqlines.linepercentmajormodel.setValue( subpop, pos, 100);
				} else if(mapAllele2Count.keySet().size()==2) {

					int major = (Integer)listCount.get(1);
					int minor = (Integer)listCount.get(0);
					double percentmajor = major*100.0/allallelecount;
					double percentminor = minor*100.0/allallelecount;

					//pos = pos + " [" + mapCount2Allele.get(listCount.get(1)) + " " + String.format( "%.2f", percentmajor) + "%" + ", " + 
					//		mapCount2Allele.get(listCount.get(0)) + " " + String.format( "%.2f", percentminor) + "%]";  

					freqlines.linecountmajormodel.setValue( subpop, pos, major);
					freqlines.linecountminormodel.setValue( subpop, pos, minor);
					freqlines.linepercentmajormodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f", percentmajor)));
					freqlines.linepercentminormodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f", percentminor)));
				} else if(mapAllele2Count.keySet().size()>2) {
					int allelecount = mapAllele2Count.keySet().size();
					int major = (Integer)listCount.get(allelecount-1);
					int minor = (Integer)listCount.get(allelecount-2);
					int allele3 = (Integer)listCount.get(allelecount-3);
					double percentmajor = major*100.0/allallelecount;
					double percentminor = minor*100.0/allallelecount;
					double percent3rd = allele3*100.0/allallelecount;
					
					//pos = pos + " [" + mapCount2Allele.get(listCount.get(allelecount-1)) + " " + String.format( "%.2f", percentmajor) + "%, " + 
					//		mapCount2Allele.get(listCount.get(allelecount-2)) + " " + String.format( "%.2f", percentminor) + "%, " + 
					//		mapCount2Allele.get(listCount.get(allelecount-3)) + " " + String.format( "%.2f", percent3rd) + "%]";  

					freqlines.linecountmajormodel.setValue( subpop, pos,  major);
					freqlines.linecountminormodel.setValue( subpop, pos, minor);
					freqlines.linepercentmajormodel.setValue( subpop, pos,  Double.valueOf(String.format( "%.2f", percentmajor)));
					freqlines.linepercentminormodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f",percentminor)));
					freqlines.line3rdallelemodel.setValue( subpop, pos, allele3);
					freqlines.linepercent3rdmodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f",percent3rd)));
				}
			}
			
		}
		
		return freqlines;
	}

	/**
	 * Update allele frequency chart
	 */
	private void updateAlleleFrequencyChart( ) {

		if(allelefreqlines==null) calculateAlleleFrequencies();

		
		if(radioShowAlleleFrequency.isSelected()) {
		     
			if(radioMajorAlleles.isSelected())
				chartAlleleFrequency.setModel(allelefreqlines.linepercentmajormodel);
			else if(radioMinorAlleles.isSelected())
				chartAlleleFrequency.setModel(allelefreqlines.linepercentminormodel);
			else if(radio3rdAlleles.isSelected())
				chartAlleleFrequency.setModel(allelefreqlines.linepercent3rdmodel);
			
			chartAlleleFrequency.getYAxis().setTitle("Allele frequency (%)");
			chartAlleleFrequency.setTitle("Allele frequency (%)");

		} else if(radioShowAlleleCount.isSelected()) {
			if(radioMajorAlleles.isSelected())
				chartAlleleFrequency.setModel(allelefreqlines.linecountmajormodel);
			else if(radioMinorAlleles.isSelected())
				chartAlleleFrequency.setModel(allelefreqlines.linecountminormodel);
			else if(radio3rdAlleles.isSelected())
				chartAlleleFrequency.setModel(allelefreqlines.line3rdallelemodel);
			
			chartAlleleFrequency.getYAxis().setTitle("Allele count");
			chartAlleleFrequency.setTitle("Allele count");
			
		}
		else if(radioShowGenotypeFrequency.isSelected()) {
		     
			if(radioMajorAlleles.isSelected())
				chartAlleleFrequency.setModel(genotypefreqlines.linepercentmajormodel);
			else if(radioMinorAlleles.isSelected())
				chartAlleleFrequency.setModel(genotypefreqlines.linepercentminormodel);
			else if(radio3rdAlleles.isSelected())
				chartAlleleFrequency.setModel(genotypefreqlines.linepercent3rdmodel);
			
			chartAlleleFrequency.getYAxis().setTitle("Genotype frequency (%)");
			chartAlleleFrequency.setTitle("Genotype frequency");


		} else if(radioShowGenotypeCount.isSelected()) {
			if(radioMajorAlleles.isSelected())
				chartAlleleFrequency.setModel(genotypefreqlines.linecountmajormodel);
			else if(radioMinorAlleles.isSelected())
				chartAlleleFrequency.setModel(genotypefreqlines.linecountminormodel);
			else if(radio3rdAlleles.isSelected())
				chartAlleleFrequency.setModel(genotypefreqlines.line3rdallelemodel);
			
			chartAlleleFrequency.getYAxis().setTitle("Genotype count");
			chartAlleleFrequency.setTitle("Genotype count");
			
		}
		
	    PlotLine plotLine = new PlotLine();
	    plotLine.setValue(0);
	    plotLine.setWidth(1);
	    plotLine.setColor("#808080");
	    chartAlleleFrequency.getYAxis().addPlotLine(plotLine);
	    
	    AxisLabels xlabels = chartAlleleFrequency.getXAxis().getLabels();
	    xlabels.setRotation(-75);
	    xlabels.setAlign("right");
	    chartAlleleFrequency.getXAxis().setTickInterval(null);
	    
	    double min = 0;
	    double max = chartAlleleFrequency.getXAxisSize();
	    long interval = Math.max(1,  Double.valueOf(  Math.ceil( Double.valueOf((max-min)/nAllelefreqLabels)) ).longValue() );
		xlabels.setStep( interval );
		
		
	     Legend legend = chartAlleleFrequency.getLegend();
	     legend.setLayout("vertical");
	     legend.setAlign("right");
	     legend.setVerticalAlign("middle");
	     legend.setBorderWidth(0);
	}


	/**
	 * On selection in chart
	 * @param event
	 */
	@Listen("onSelection = #chartAlleleFrequency")
	public void doSelection(ChartsSelectionEvent event) {
	    // doing the zooming in function
		
		
	    double min = event.getXAxisMin().doubleValue();
	    double max = event.getXAxisMax().doubleValue();
	    //updateSelection(min, max);
	    long interval = Math.max(1,  Double.valueOf((max-min)/nAllelefreqLabels).longValue());
	    
	    // enable the zooming out button
	    //btn.setVisible(true);
	    //Events.sendEvent(event.getName(), chartManhattan, null);
	    
	    AppContext.debug("1event.getName()=" + event.getName() + "  min=" + min + " max=" + max + " interval=" + interval);
	    
	    chartAlleleFrequency.getXAxis().getLabels().setStep( interval );
	    /*
	    chartAlleleFrequency.getXAxis().setMax(max);
	    chartAlleleFrequency.getXAxis().setMin(min);
	    */
	    
	    ResetZoomButton reset=chartAlleleFrequency.getChart().getResetZoomButton();
	    Map mapChartAttribute=new HashMap();
	    if(reset!=null)
	    	mapChartAttribute=reset.getTheme();
		mapChartAttribute.put("visibility","hidden");
		if(reset!=null)
			reset.setTheme(mapChartAttribute);
	}

	
	
	/**
	 * for bigmatrix viewer
	 */
	
	
		class BiglistRowheaderModel extends SimpleListModel implements ListModelExt {

			private Comparator<Cell> _sorting;
			private boolean _sortDir = true;
			
			public BiglistRowheaderModel(List data) {
				super(data);
				// TODO Auto-generated constructor stub
			}
			
			@Override
			public void sort(Comparator cmpr, boolean ascending) {
				// TODO Auto-generated method stub
				
				try {
				// sort biglistboxarray
				_sorting = cmpr;
				_sortDir = ascending;
				
				AppContext.debug("sorting biglist from header");
				
				Object2StringMatrixModel model =  (Object2StringMatrixModel)biglistboxArray.getModel();
				model.sort(cmpr, ascending);
				
				} catch(Exception ex) {
					ex.printStackTrace();
					
				}
				
			}
	
			@Override
			public String getSortDirection(Comparator cmpr) {
				// TODO Auto-generated method stub
				
				if (Objects.equals(_sorting, cmpr))
					return _sortDir ? "ascending" : "descending";
				return "natural";
			}
			
		}

	public class Object2StringMatrixComparatorProvider<T> implements MatrixComparatorProvider<Object[]> {
		private int _x = -1;
		
		private boolean _acs;
		
		private Object2StringComparator _cmpr;
		
		public Object2StringMatrixComparatorProvider(boolean asc) {
			_acs = asc;
			_cmpr = new Object2StringComparator(this);
		}
		
		@Override
		public Comparator<Object[]> getColumnComparator(int columnIndex) {
			this._x = columnIndex;
			
			//AppContext.debug("sort by column " + columnIndex);
			
			return _cmpr;
		
		}
			
			// a real String comparator
			public class Object2StringComparator implements Comparator<Object[]> {
				private Object2StringMatrixComparatorProvider _mmc;
				
				public int getColumn() {
					return _mmc._x;
				}
			
				public Object2StringComparator(Object2StringMatrixComparatorProvider mmc) {
					_mmc = mmc;
				}
			
				@Override
				public int compare(Object[] o1, Object[] o2) {
					
					//try {
					//AppContext.debug( "o1_0=" + o1.get(0)  + "  o2_0="  + o2.get(0));
					//AppContext.debug( "o1[" + _mmc._x + "]=" + o1[_mmc._x]  + ", o2[" + _mmc._x + "]=" + o2[_mmc._x]);
					//System.out.println( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
					
					//return o1.get(_mmc._x).compareTo(o2.get(_mmc._x)) * (_acs ? 1 : -1);
		
					
					Object o1x=o1[_mmc._x];
					Object o2x=o2[_mmc._x];
					//returno1x.toString().compareTo(  o2x.toString() ) * (_acs ? 1 : -1);
					
					String s1="";
					if(o1x!=null) s1=o1x.toString();
					String s2="";
					if(o2x!=null) s2=o2x.toString();
					
					//AppContext.debug(s1 + "\t" + s2);
					
					if(s1.isEmpty() && s2.isEmpty()) return 0;
					if(s1.isEmpty()) return -1*(_acs ? 1 : -1);
					if(s2.isEmpty()) return (_acs ? 1 : -1);
		
					return ((Comparable)o1x).compareTo( o2x ) * (_acs ? 1 : -1);
				}
			}
		}
	
		class BiglistRowheaderRenderer implements  RowRenderer{

			@Override
			public void render(Row row, Object data, int index)
					throws Exception {
				// TODO Auto-generated method stub
				Object[] obj = (Object[])data;
				addListcell (row, obj[0]); 
				addListcell (row, obj[1]);
				addListcell (row, obj[2]);
				addListcell (row, obj[3]);
				if(obj.length>4) addListcell (row, obj[4]);
				
			}
			
		   private void addListcell (Row listitem, Object value) {
		        Label lb = new Label(value.toString());
		        lb.setParent(listitem);
		    }
		}



		// **************************************************   HANDLE VISTA TAB EVENTS ************************************************************

		@Listen("onClick =#tabVista")
		public void onclickTabvista() {
			if(this.vistaurl!=null) {
				this.iframeVista.setSrc(vistaurl);
				AppContext.debug("displaying: " + vistaurl);
				
				this.vistaurl=null;
			}
		}

		@Listen("onClick =#tabVistaRev")
		public void onclickTabvistaRev() {
			if(this.vistaurlrev!=null) {
				this.iframeVistaRev.setSrc(vistaurlrev);
				AppContext.debug("displaying: " + vistaurlrev);
				
				this.vistaurlrev=null;
			}
		}

		private String createVistaNPBurl(String org, String npbcontig, long npbstart, long npbend ) {
			String url="";
			if(org.equalsIgnoreCase("DJ123")) {
				 //vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat12&run=21641-znnYkRUW#&base=4153&run=21641-znnYkRUW&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
				url= "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=21641-znnYkRUW#&base=4143&run=21641-znnYkRUW&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
			}
			else if(org.equalsIgnoreCase("IR64-21")) {
				//vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat08&run=37870-HIlpnZhn#&base=4146&run=37870-HIlpnZhn&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
				url= "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=37870-HIlpnZhn#&base=4143&run=37870-HIlpnZhn&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
			}
			else if(org.equalsIgnoreCase("93-11")) {
				//vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat07&run=37668-Nvvq15gS#&base=4144&run=37668-Nvvq15gS&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
				url= "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=37668-Nvvq15gS#&base=4143&run=37668-Nvvq15gS&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
			}
			else if(org.equalsIgnoreCase("Kasalath")) {
				//vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat11&run=37665-aKx0cjrR#&base=3591&run=37665-aKx0cjrR&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
				url= "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=37665-aKx0cjrR#&base=4143&run=37665-aKx0cjrR&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
			}

			AppContext.debug("displaying: " + url);
			return url;
		}

		@Listen("onClick =#tabVistaNPB")
		public void onclickTabVistaNPB() {
			onclickTabNPBvsIR6421(); 
		}

		@Listen("onClick =#tabNPBvsIR6421")
		public void onclickTabNPBvsIR6421() {
			this.iframeNPBvsIR6421.setSrc(  createVistaNPBurl("IR64-21", selectChr.getValue(), intStart.getValue(), intStop.getValue() )  );
		}

		@Listen("onClick =#tabNPBvs9311")
		public void onclicktabNPBvs9311() {
			this.iframeNPBvs9311.setSrc(  createVistaNPBurl("93-11", selectChr.getValue(), intStart.getValue(), intStop.getValue() )  );
			
		}
		@Listen("onClick =#tabNPBvsKasalath")
		public void onclickTabNPBvsKas() {
			this.iframeNPBvsKasalath.setSrc(  createVistaNPBurl("Kasalath", selectChr.getValue(), intStart.getValue(), intStop.getValue() )  );
			
		}
		@Listen("onClick =#tabNPBvsDJ123")
		public void onclickTabNPBvsDJ123() {
			this.iframeNPBvsDJ123.setSrc(  createVistaNPBurl("DJ123", selectChr.getValue(), intStart.getValue(), intStop.getValue() )  );
			
		}

		
		private void updateVista(GenotypeQueryParams params, VariantTable vartable) {
			
			if(!AppContext.isIRRILAN()) {
				
				//tabVista.setVisible(false);
				//tabVistaRev.setVisible(false);
				//tabVistaNPB.setVisible(false);
				return;
			}
			
			String org = params.getOrganism();
			List<SnpsAllvarsPos> listpos = vartable.getVariantStringData().getListPos();
					
			if(!org.equals(AppContext.getDefaultOrganism()) && listpos.size()>1 ) {
				String origcontig = params.getsChr();
				Long start = params.getlStart();
				Long end= params.getlEnd();
				String npbcontig= listpos.get(0).getContig();
				Long npbstart= listpos.get(0).getPos().longValue();
				Long npbend=listpos.get(listpos.size()-1).getPos().longValue();
				//vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=21641-znnYkRUW#&base=4143&run=21641-znnYkRUW&pos=chr09:1-100000&genes=user&indx=0&cutoff=50";
				vistaurl = null;
				vistaurlrev= null;
				
				
				// base nippo 4143 orySat06, 9311 4144 orySat07, 1r64 4146 orySat08, kasalath 3591 orySat11, dj123 4153 orySat12
				if(org.equalsIgnoreCase("DJ123")) {
					 vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat12&run=21641-znnYkRUW#&base=4153&run=21641-znnYkRUW&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
					 vistaurlrev = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=21641-znnYkRUW#&base=4143&run=21641-znnYkRUW&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
				}
				else if(org.equalsIgnoreCase("IR64-21")) {
					vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat08&run=37870-HIlpnZhn#&base=4146&run=37870-HIlpnZhn&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
					vistaurlrev = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=37870-HIlpnZhn#&base=4143&run=37870-HIlpnZhn&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
				}
				else if(org.equalsIgnoreCase("93-11")) {
					vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat07&run=37668-Nvvq15gS#&base=4144&run=37668-Nvvq15gS&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
					vistaurlrev = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=37668-Nvvq15gS#&base=4143&run=37668-Nvvq15gS&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
				}
				else if(org.equalsIgnoreCase("Kasalath")) {
					vistaurl = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat11&run=37665-aKx0cjrR#&base=3591&run=37665-aKx0cjrR&pos=" + origcontig + ":" + start + "-" + end + "&genes=user&indx=0&cutoff=50";
					vistaurlrev = "http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=37665-aKx0cjrR#&base=4143&run=37665-aKx0cjrR&pos=" + npbcontig + ":" + npbstart + "-" + npbend + "&genes=user&indx=0&cutoff=50";
				}
				
				//http://pipeline.lbl.gov/tbrowser/tbrowser/?base=orySat06&run=21641-znnYkRUW#&base=4143&run=21641-znnYkRUW&pos=chr09:1-100000&genes=user&indx=0&cutoff=50
				
				this.tabVistaNPB.setLabel("Compare " + org + " vs. Nipponbare");
				this.tabVistaRev.setLabel( "Compare Nipponbare vs. " + org );
				if(vistaurl!=null) {
					//tabVista.setVisible(true);
					//tabVistaRev.setVisible(true);
					tabVistaNPB.setVisible(true);
				}

				
					
			}
			else {
				vistaurl=null;
				vistaurlrev=null;
				tabVista.setVisible(false);
				tabVistaRev.setVisible(false);
				
				if( listpos.size()<2) return;
				if(selectChr.getValue()!=null && !selectChr.getValue().isEmpty()) tabVistaNPB.setVisible(true);
				
			}
			  
		}
	
		
}


// ****************************  OLD CODES RETAINED ****************************************************************


//// Matrix comparator provider 
//private class MyMatrixComparatorProvider2<T> implements
//		MatrixComparatorProvider<List<String>> {
//	private int _x = -1;
//
//	private boolean _acs;
//
//	private MyComparator _cmpr;
//
//	public MyMatrixComparatorProvider2(boolean asc) {
//		_acs = asc;
//		_cmpr = new MyComparator(this);
//	}
//
//	@Override
//	public Comparator<List<String>> getColumnComparator(int columnIndex) {
//		this._x = columnIndex;
//		
//		//AppContext.debug("sort by column " + columnIndex);
//		
//		return _cmpr;
//
//	}
//
//	// a real String comparator
//	private class MyComparator implements Comparator<List<String>> {
//		private MyMatrixComparatorProvider2 _mmc;
//
//		public MyComparator(MyMatrixComparatorProvider2 mmc) {
//			_mmc = mmc;
//		}
//
//		@Override
//		public int compare(List<String> o1, List<String> o2) {
//			//AppContext.debug( "o1_0=" + o1.get(0)  + "  o2_0="  + o2.get(0));
//			AppContext.debug( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//			System.out.println( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//			
//			return o1.get(_mmc._x).compareTo(o2.get(_mmc._x)) * (_acs ? 1 : -1);
//			//return ((Object[])o1.get(_mmc._x))[_mmc._x].compareTo(  ((Object[])o2.get(_mmc._x))[_mmc._x]) * (_acs ? 1 : -1);
//			
//		}
//	}
//}



/*
		private class MyMatrixComparatorProvider<List<String[]>> implements
				MatrixComparatorProvider<List<String[]>> {
			private int _x = -1;
			
			private boolean _acs;
			
			private MyComparator _cmpr;
			
			public MyMatrixComparatorProvider(boolean asc) {
				_acs = asc;
				_cmpr = new MyComparator(this);
			}
			
			@Override
			public Comparator<List<String[]>> getColumnComparator(int columnIndex) {
				this._x = columnIndex;
				
				//AppContext.debug("sort by column " + columnIndex);
				
				return _cmpr;
			
			}
			
			// a real String comparator
			private class MyComparator implements Comparator<List<String>> {
				private MyMatrixComparatorProvider _mmc;
			
				public MyComparator(MyMatrixComparatorProvider mmc) {
					_mmc = mmc;
				}
			
				@Override
				public int compare(List<String[]> o1, List<String[]> o2) {
					//AppContext.debug( "o1_0=" + o1.get(0)  + "  o2_0="  + o2.get(0));
					AppContext.debug( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
					System.out.println( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
					
					//return o1.get(_mmc._x).compareTo(o2.get(_mmc._x)) * (_acs ? 1 : -1);
					//return ((Object[])o1.get(_mmc._x))[_mmc._x].compareTo(  ((Object[])o2.get(_mmc._x))[_mmc._x]) * (_acs ? 1 : -1);
					return ((String[])o1.get(_mmc._x))[_mmc._x].compareTo(  ((String[])o2.get(_mmc._x))[_mmc._x] ) * (_acs ? 1 : -1);
					
				}
			}
			}
		*/


/*
@Listen("onSelect =#biglistboxArray")
public void onselectbiglistbox(Event e) {
	SelectEvent ev = (SelectEvent)e;
	AppContext.debug( "onSelect " +  ev.getName() + "  " + ev.getData().toString());
	
}
	  
@Listen("onCellClick =#biglistboxArray")
public void oncellclickbiglistbox(Event e) {
	CellClickEvent ev = (CellClickEvent)e;
	AppContext.debug( "onCellClick " +  ev.getName() + "  data=" + ev.getData().toString()  +  "  colidx=" + ev.getColumnIndex() + ", rowidx=" + ev.getRowIndex());
}

 */
/*
@Override
public void doAfterCompose(Window comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	
	   biglistboxArray.addEventListener("onMouseOver", new EventListener() {
		     public void onEvent(Event e) {
		       System.out.println("onMouseOver: target=" + e.getTarget() + " " + e.getTarget().getClass()  + "  name=" + e.getName() + "  data=" + e.getData());
		     }
		  });

	   biglistboxArray.addEventListener("onMouseOut", new EventListener() {
		    public void onEvent(Event e) {
		      System.out.println("onMouseOut: target=" + e.getTarget()  + " " + e.getTarget().getClass() +  "  name=" + e.getName() + "  data=" + e.getData());
		   }
		  });
}
*/

/*
private void downloadBigListboxPlink(String filename) {
	
//	MAP files
//	The fields in a MAP file are:
//	Chromosome
//	Marker ID
//	Genetic distance
//	Physical position
//
//	PED files
//	The fields in a PED file are
//	Family ID
//	Sample ID
//	Paternal ID
//	Maternal ID
//	Sex (1=male; 2=female; other=unknown)
//	Affection (0=unknown; 1=unaffected; 2=affected)
//	Genotypes (space or tab separated, 2 for each marker. 0=missing)
//	
	
	
//	-HapMap file format:
//		The current release consists of text-table files only, with the following columns:
//
//		Col1: refSNP rs# identifier at the time of release (NB: it might merge 
//		  with another rs# in the future)
//		Col2: SNP alleles according to dbSNP
//		Col3: chromosome that SNP maps to 
//		Col4: chromosome position of SNP, in basepairs on reference sequence
//		Col5: strand of reference sequence that SNP maps to
//		Col6: version of reference sequence assembly (currently NCBI build36)
//		Col7: HapMap genotyping center that produced the genotypes
//		Col8: LSID for HapMap protocol used for genotyping
//		Col9: LSID for HapMap assay used for genotyping
//		Col10: LSID for panel of individuals genotyped
//		Col11: QC-code, currently 'QC+' for all entries (for future use)
//		Col12 and on: observed genotypes of samples, one per column, sample
//		     identifiers in column headers (Coriell catalog numbers, example:
//		      NA10847).

	try {
		      
	String chr= this.selectChr.getValue();
	
	
	Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
	
	
	StringBuffer buff = new StringBuffer();
	//buff.append("POSITION").append(delimiter).append("MISMATCH").append(delimiter);
	Position[] positions = table.getPosition();
	String refs[] =  table.getReference();
	for(int i=0; i<positions.length; i++) {
		if(!refs[i].equals("-")) {
			int pos = positions[i].getPosition().intValue();
			buff.append(chr).append("\t").append("snp" + chr + "-" + pos) .append("\t0\t").append(pos+1).append("\n");
		}
	}

		String filetype = "text/plain";
		Filedownload.save(  buff.toString(), filetype , filename + ".map");
		AppContext.debug("map: "+ buff.toString() );
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
		AppContext.debug("snpallvars download complete!"+ filename +".map Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
		

	
	
	buff = new StringBuffer();
	
	Object[][] varalleles = table.getVaralleles();
	Map<BigDecimal,Variety> mapVarId2Var = varietyfacade.getMapId2Variety();
	for(int i=0; i< table.getVarid().length; i++) {
		
		//if(mapVarId2Var.get(table.getVarid()[i])==null) throw new RuntimeException("null variety with id=" + table.getVarid()[i])
		
		buff.append("0\t").append( mapVarId2Var.get( BigDecimal.valueOf(table.getVarid()[i]) ).getIrisId()).append("\t0\t0\t1\0\t0\t");

//		Family ID
//		Sample ID
//		Paternal ID
//		Maternal ID
//		Sex (1=male; 2=female; other=unknown)
//		Affection (0=unknown; 1=unaffected; 2=affected)
//		Genotypes (space or tab separated, 2 for each marker. 0=missing)
		
		for(int j=0; j<refs.length; j++) {
			String allele1= (String)varalleles[i][j];
			if(allele1.isEmpty()) buff.append("0\t0");
			else {
				String alleles[] = allele1.split("/");
				if(alleles.length==1)
					buff.append(allele1).append("\t").append(allele1);
				else 
					buff.append(alleles[0]).append("\t").append(alleles[1]);
			}
			if(j<refs.length-1) buff.append("\t");
		}
		buff.append("\n");
	}
	
	
		filetype = "text/plain";
		Filedownload.save(  buff.toString(), filetype , filename + ".ped");
		
		AppContext.debug("ped: "+ buff.toString() );
		//AppContext.debug("File download complete! Saved to: "+filename);
		zksession = Sessions.getCurrent();
		AppContext.debug("snpallvars download complete!"+ filename +  ".ped Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );

	} catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}
*/

//
//class BiglistRowheaderListitemComparator extends ListitemComparator {
//
//	private int colIdx=-1;
//	private boolean asc;
//	
//	public BiglistRowheaderListitemComparator(int index,
//			boolean ascending, boolean ignoreCase) {
//		
//		super(index, ascending, ignoreCase);
//		// TODO Auto-generated constructor stub
//		asc=ascending;
//		colIdx=index;
//	}
//
//	public int getColIdx() {
//		return colIdx;
//	}
//
//	public boolean isAsc() {
//		return asc;
//	}
//
//	
//}
//
//
//private class Object2StringMatrixComparatorProvider<T> implements
//			MatrixComparatorProvider<List<Object[]>> {
//		private int _x = -1;
//		
//		private boolean _acs;
//		
//		private Object2StringComparator _cmpr;
//		
//		public Object2StringMatrixComparatorProvider(boolean asc) {
//			_acs = asc;
//			_cmpr = new Object2StringComparator(this);
//		}
//		
//		@Override
//		public Comparator<List<Object[]>> getColumnComparator(int columnIndex) {
//			this._x = columnIndex;
//			
//			//AppContext.debug("sort by column " + columnIndex);
//			
//			return _cmpr;
//		
//		}
//		
//		// a real String comparator
//		private class Object2StringComparator implements Comparator<List<Object[]>> {
//			private Object2StringMatrixComparatorProvider _mmc;
//		
//			public Object2StringComparator(Object2StringMatrixComparatorProvider mmc) {
//				_mmc = mmc;
//			}
//		
//			@Override
//			public int compare(List<Object[]> o1, List<Object[]> o2) {
//				
//				try {
//				//AppContext.debug( "o1_0=" + o1.get(0)  + "  o2_0="  + o2.get(0));
//				AppContext.debug( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//				//System.out.println( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//				
//				//return o1.get(_mmc._x).compareTo(o2.get(_mmc._x)) * (_acs ? 1 : -1);
//				return ((Object[])o1.get(_mmc._x))[_mmc._x].toString().compareTo(  ((Object[])o2.get(_mmc._x))[_mmc._x].toString() ) * (_acs ? 1 : -1);
//				
//				} catch(Exception ex) {
//					ex.printStackTrace();
//					return 0;
//				}
//				
//			}
//		}
//}

//
///**
// * Write SNP for 2 varieties in file
// * @param filename	filename to write
// * @param header	to write in first line
// * @param var1		variety 1
// * @param var2		variety 2
// * @param delimiter	
// * @param listSNPs	SNPs list
// */
//private void writeSNP2Lines2File(String filename, String header,  String var1, String var2, String delimiter, List<Snps2Vars> listSNPs )
//{
//	
//	Iterator<Snps2Vars> it=listSNPs.iterator();
//	StringBuffer buff= new StringBuffer();
//	
//	if(header!=null && !header.isEmpty()) {
//		buff.append(header); buff.append("\n");
//	}
//	
//	buff.append("CHR"); buff.append(delimiter); buff.append("POS");  buff.append(delimiter);
//	buff.append("REF");  buff.append(delimiter); 
//	buff.append(var1.toUpperCase());  buff.append(delimiter); buff.append(var2.toUpperCase());
//	buff.append("\n");
//	
//		while(it.hasNext()) {
//			Snps2Vars snp2lines= it.next();
//			buff.append( snp2lines.getChr() );  buff.append(delimiter);
//			buff.append( snp2lines.getPos() );  buff.append(delimiter);
//			buff.append( snp2lines.getRefnuc() );  buff.append(delimiter);
//			buff.append( snp2lines.getVar1nuc() );  buff.append(delimiter);
//			buff.append( snp2lines.getVar2nuc());  buff.append("\n");		
//			
//		}		
//		
//		try {
//			String filetype = "text/plain";
//			if(delimiter.equals(",")) filetype="text/csv";
//				
//			Filedownload.save( buff.toString(), filetype, filename);
//			AppContext.debug("File download complete! Saved to: "+ filename);
//			} catch(Exception ex)
//			{
//				ex.printStackTrace();
//			}
//		
//}



//private void createSNP2linesGFF(List<Snps2Vars> listSNPs, String filename) {
//	createSNP2linesGFF(listSNPs, filename, false);
//}
//
///**
// * Create SNP GFF for 2-varieties query
// * @param listSNPs
// * @param filename
// * @param mismatchOnly
// */
//private void createSNP2linesGFF(List<Snps2Vars> listSNPs, String filename, boolean mismatchOnly) {
//	
//	//if(!checkShowsnp.isChecked()) return; 
//	
//	StringBuffer buff = new StringBuffer();
//	
//
//	varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
//	Map<BigDecimal,Variety> mapId2Var=varietyfacade.getMapId2Variety();
//	
//	try {
//		File file = new File( AppContext.getTempDir() + filename);
//		FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		BufferedWriter bw = new BufferedWriter(fw);
//		
//		int linecount = 0;
//		Iterator<Snps2Vars> itsnp = listSNPs.iterator();
//		while(itsnp.hasNext()) {
//				Snps2Vars snpvars = itsnp.next();
//			
//				
//				String chr = snpvars.getChr().toString(); 
//				if(chr.length()==1)
//					chr= "chr0" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
//				else 
//					chr= "chr" + chr + AppContext.getJbrowseContigSuffix(); //"|msu7";
//				
//				String colormode="";
//				if(this.radioColorMismatch.isSelected())
//					colormode=";colormode=1";
//				else
//					colormode=";colormode=0";
//
//				if(snpvars.getRefnuc()==null || snpvars.getRefnuc().trim().isEmpty()) {
//					// indel
//					
//					//AppContext.debug("indelGFF");
//					
//					if(!mismatchOnly || !snpvars.getVar2nuc().equals( snpvars.getVar1nuc()  ) ) {
//						
//						String type = genotype.getIndelType( snpvars.getVar1nuc() );
//						int allelelen = 1;
//						if(type.equals("deletion"))
//							allelelen = Integer.parseInt( snpvars.getVar1nuc().replace("del","").trim() );
//						else if(type.equals("insertion")) {
//							allelelen = snpvars.getVar1nuc().length();
//						}
//						
//						buff.append(chr).append("\tIRIC\t" + type + "\t");
//						buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos().intValue() + allelelen - 1 );
//						
//						buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( snpvars.getVar1() ).getName()
//								+";ID=" +  snpvars.getVar1() + "-" + snpvars.getPos() +
//								//";AlleleRef=" +   snpvars.getRefnuc() +
//								 ";AlleleAlt=" + snpvars.getVar1nuc() +
//								 ";Position=" +  snpvars.getPos() + colormode +
//								"\n");
//
//					   type = genotype.getIndelType( snpvars.getVar2nuc() );
//						allelelen = 1;
//						if(type.equals("deletion"))
//							allelelen = Integer.parseInt( snpvars.getVar2nuc().replace("del","").trim() );
//						else if(type.equals("insertion")) {
//							allelelen = snpvars.getVar2nuc().length();
//						}
//							
//						buff.append(chr).append("\tIRIC\t" + type + "\t");
//						buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos().intValue() + allelelen - 1  );
//						
//						buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( snpvars.getVar2() ).getName()
//								+";ID=" +  snpvars.getVar2() + "-" + snpvars.getPos() +
//								//";AlleleRef=" +   snpvars.getRefnuc() +
//								 ";AlleleAlt=" + snpvars.getVar2nuc() +
//								 ";Position=" +  snpvars.getPos() + colormode+
//								"\n");
//					}
//				} 
//				else {
//					//snps
//					
//					if(!mismatchOnly || !snpvars.getRefnuc().equals( snpvars.getVar1nuc()  ) ) {
//						buff.append(chr); buff.append("\tIRIC\tsnp\t");
//						buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
//						
//						buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( snpvars.getVar1() ).getName()
//								+";ID=" +  snpvars.getVar1() + "-" + snpvars.getPos() +
//								";AlleleRef=" +   snpvars.getRefnuc() +
//								 ";AlleleAlt=" + snpvars.getVar1nuc() +
//								 ";Position=" +  snpvars.getPos() + colormode +
//								"\n");
//					}
//	
//					if(!mismatchOnly || !snpvars.getRefnuc().equals( snpvars.getVar2nuc()  ) ) {
//						buff.append(chr); buff.append("\tIRIC\tsnp\t");
//						buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
//						buff.append( "\t.\t.\t.\tName=" +  mapId2Var.get( snpvars.getVar2() ).getName()
//								+ ";ID=" +  snpvars.getVar2() + "-" + snpvars.getPos() + 
//								";AlleleRef=" +   snpvars.getRefnuc() +
//								";AlleleAlt=" + snpvars.getVar2nuc() +
//								";Position=" +  snpvars.getPos() + colormode +
//								"\n");
//					}
//				}
//				
//				if(linecount > 100 ) {
//					buff.append("###\n");
//					bw.write(buff.toString());
//					buff.delete(0, buff.length());
//					buff = new StringBuffer();
//					bw.flush();
//					linecount = 0;
//				}
//				linecount++;
//			}
//
//		bw.write(buff.toString());
//		buff.delete(0, buff.length());
//		buff = new StringBuffer();
//		bw.flush();
//		bw.close();
//		
//		AppContext.debug("temgff written in: " + file.getAbsolutePath() );
//		log.info( "temgff written in: " + file.getAbsolutePath());
//		
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}
//}
//
//
