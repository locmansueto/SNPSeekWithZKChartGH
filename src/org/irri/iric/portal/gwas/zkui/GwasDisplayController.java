package org.irri.iric.portal.gwas.zkui;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
//import org.irri.iric.portal.chado.oracle.domain.Organism;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.dao.LocusCvTermDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.LocalAlignment;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocusPromoter;
import org.irri.iric.portal.domain.MarkerAnnotation;
import org.irri.iric.portal.domain.MergedLoci;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.PositionImpl;
import org.irri.iric.portal.domain.PositionLogPvalue;
import org.irri.iric.portal.domain.PositionLogPvalueImpl;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyPlusPlus;
import org.irri.iric.portal.domain.VarietyPlusPlusImpl;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genomics.service.GenomicsFacadeImpl;
import org.irri.iric.portal.genomics.zkui.MarkerAnnotationSorter;
import org.irri.iric.portal.genomics.zkui.MarkerGridRenderer;
import org.irri.iric.portal.genomics.zkui.MarkerVarListItemRenderer;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.gwas.GwasFacade;
import org.irri.iric.portal.gwas.domain.ManhattanPlotImpl;
import org.irri.iric.portal.gwas.service.GwasFacadeImpl.ScoreFeature;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.service.Data;
import org.irri.iric.portal.variety.zkui.VarietyListItemRenderer;
import org.irri.iric.portal.variety.zkui.VarietyPlusPlusComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.zkoss.chart.AxisLabels;
import org.zkoss.chart.Chart;
import org.zkoss.chart.Charts;
import org.zkoss.chart.ChartsEvent;
import org.zkoss.chart.ChartsSelectionEvent;
import org.zkoss.chart.Legend;
import org.zkoss.chart.Marker;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.chart.Tooltip;
import org.zkoss.chart.XAxis;
import org.zkoss.chart.plotOptions.ScatterPlotOptions;


import org.zkoss.chart.plotOptions.TooltipPlotOptions;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.chart.model.DefaultXYModel;
import org.zkoss.json.JavaScriptValue;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;


@Controller("GwasDisplayController")
@Scope(value="session")
public class GwasDisplayController extends SelectorComposer<Window> {

	
	private int nLabels=25;
	private int nMarkers=100;
	private Map<String,Integer> mapChr2Offset=new HashMap();
	
	@Autowired
	@Qualifier("GwasFacade")
	GwasFacade gwas;
	@Autowired
	@Qualifier("GenomicsFacade")
	GenomicsFacade genomics;
	@Autowired
	@Qualifier("GenotypeFacade")
	GenotypeFacade genotype;
	@Autowired
	@Qualifier("VarietyFacade")
	VarietyFacade variety;
	
	@Autowired
	@Qualifier("WorkspaceFacade")
	WorkspaceFacade workspace;
	
	

	@Wire
	private Div divMultipositions;
	    
	
	@Wire
	private Charts chartManhattanXY;
	
	@Wire
	private Label labelAnnotations;
	
	@Wire
	private Div divVarietylist;
	@Wire
	private Textbox txtboxListnameVar;
	@Wire
	private Button buttonAddToListVar;

	@Wire
	private Textbox txtboxListnameSnp;
	@Wire
	private Button buttonAddToListSnp;

	@Wire
	private Hbox hboxAddtolist;
	@Wire
	private Hbox hboxDownload;
	@Wire
	private Button buttonDownloadCSV;
	@Wire
	private Button buttonDownloadTab;
	
	@Wire
	private Listheader listheaderPhenotype;
	
	@Wire
	private Listbox listboxVariety;
	@Wire
	private Label labelVarietyMsg;
	@Wire
	//private Combobox comboboxMinlogP;
	private Listbox comboboxMinlogP;
	
	@Wire
	private Label labelPositions;
	
	@Wire
	private Charts chartAlleleHist;
	
	@Wire
	private Charts chartSubpopHist;
	@Wire
	private Charts chartGenSubpopHist;
	@Wire
	private Charts chartGenotypeHist;
	@Wire
	private Checkbox checkboxNormalize;
	
	//@Wire
	//private Vbox vboxSubpopCheckbox;
	
	@Wire
	private Listbox listboxTrait;
	
	@Wire
	private Listbox listboxSnplist;
	
	@Wire
	private Listbox listboxPosition;
	
	@Wire
	private Listbox listboxMultiPosition;
	@Wire
	private Button buttonDisplayHist;
	
	@Wire
	private Listbox listboxSubpopulation;
	
	@Wire
	private Listbox listboxPhenotypeBins;
	/*
	@Wire
	private Button buttonResetzoom;
	*/
	
	@Wire
	private Label labelManhattan;
	
	
	@Wire
	private Tab tabRegion;
	@Wire
	private Tab tabPosition;
	@Wire
	private Tab tabPopulation;

	@Wire
	private Checkbox checkboxNormalizePopulation;
	
	@Wire
	//private Grid gridMarker;
	private Listbox listboxMarkerVar;
	@Wire
	private Borderlayout borderMarkerVar;
	
	//@Wire
	//private Charts chartManhattan;
	@Wire
	private Iframe iframeJbrowse;
	@Wire
	private Label msgJbrowse;
	
	@Wire
	private Checkbox checkboxJbrowse;
	
	@Wire
	private Listbox listboxVarietylist;
	
	@Wire
	private Image imageQQ;
	
	@Wire
	private Tab tabAnalysis;
	@Wire
	private Listbox listboxGenotype1;
	@Wire
	private Listbox listboxGenotype2;
	@Wire
	private Charts chartSmoothHist;
	@Wire
	private Intbox intboxBins;
	
	@Wire
	private Button buttonMinOverlap;
	
	@Wire
	private Intbox intboxMaxFeatures;
	@Wire
	private Intbox intboxMinCountPercent;
	@Wire
	private Charts chartSmoothPhen;
	@Wire
	private Listbox listboxBest;
	@Wire
	private Charts chartsTopScores;
	@Wire
	private Listbox listboxMLIteration;
	private List[]  listMapalleleScores;
	
	
	@Wire
	private Radio radioGroupbyMarker;
	@Wire
	private Radio radioGroupbyGene;
	@Wire
	private Radio radioGroupbyQtl;
	
	@Wire
	private Listheader headerLogp;

	// copied from locus to create  gene list

	@Wire
	private Hbox hboxAddtolistGenes;
	@Wire
	private Textbox txtboxListnameGene;
	@Wire
	private Button buttonAddToListGene;
	@Wire
	private Checkbox checkboxListModel;
	@Wire
	private Checkbox checkboxListPromoter;
	@Wire
	private Checkbox checkboxListRicenet;
	@Wire
	private Checkbox checkboxListPrin;

	@Wire
	private Checkbox checkboxIncludeInteractions;
	@Wire
	private Checkbox checkboxIncludePromoters;
	@Wire
	private Checkbox checkboxIncludeGO;
	@Wire
	private Checkbox checkboxIncludePOTO;
	@Wire
	private Checkbox checkboxIncludeQTL;
	@Wire
	private Button  buttonUpdateAnnotations;
	
	
	@Wire
	private Listbox listboxChromosome;
	
	private Map<String,Double> mapPos2Values;
	private Map<String,Integer> mapPos2Index;
	private Map<Integer,String> mapIndex2Pos;
	private Map<String,Integer> mapViewPos2Index;
	private Map<Integer,String> mapViewIndex2Pos;
	private Map<BigDecimal, Object> mapVarid2Phenotype;
	private List<Variety> listVarieties;
	
	private Map<Object, Map<Object,Collection>> mapSubpopGen2Phenotype2Count;
	private Map<Object, Map<Object,Collection>> mapSubpop2Phenotype2Count;
	private Map<Object, Map<Object,Collection>> mapGenotype2Phenotype2Count;
	private Map<Object, Map<Object,Integer>> mapAllele2Phenotype2Count;
	private Map<Object, Map<Object,Collection>> mapAllele2Phenotype2Set;
	
	private Map<Object, Map<Object,Collection>> mapGenotype2Phenotype2Frequency;
	
	private Map<Long,Object> mapVarid2Genotype;


	private List markerresult;
	private Set setAnnotations;
	private String urljbrowse;
	
	private String dataset=VarietyFacade.DATASET_SNPINDELV2_IUPAC;
	
	public GwasDisplayController() {
		super();
		// TODO Auto-generated constructor stub
		//chartManhattan.getChart().getResetZoomButton().
		
		mapChr2Offset.put("1", 0);
		mapChr2Offset.put("2", 43270923);
		mapChr2Offset.put("3", 79208173);
		mapChr2Offset.put("4", 115621992);
		mapChr2Offset.put("5", 151124686);
		mapChr2Offset.put("6", 181083120);
		mapChr2Offset.put("7", 212331907);
		mapChr2Offset.put("8", 242029528);
		mapChr2Offset.put("9", 270472550);
		mapChr2Offset.put("10", 293485270);
		mapChr2Offset.put("11", 316692557);
		mapChr2Offset.put("12", 345713663);

		AppContext.debug(this.getClass() + " started");
		
    

	}
	
	
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		AppContext.debug("doAfterCompose started");    
		super.doAfterCompose(comp);
	        try {
		        AppContext.debug("doAfterCompose starting...");
		        
		        gwas=(GwasFacade)AppContext.checkBean(gwas,"GwasFacade");
		        workspace=(WorkspaceFacade)AppContext.checkBean(workspace,"WorkspaceFacade");
		        genotype=(GenotypeFacade)AppContext.checkBean(genotype,"GenotypeFacade");
		        genomics=(GenomicsFacade)AppContext.checkBean(genomics,"GenomicsFacade");
		        variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
		        
		        
		        AppContext.debug("init subpoptrait");
		        listboxSubpopulation.setModel(new SimpleListModel(AppContext.createUniqueUpperLowerStrings(gwas.getSubpopulations(),false,true)));
		        listboxTrait.setModel(new SimpleListModel(AppContext.createUniqueUpperLowerStrings(gwas.getTraits("all varieties"),false,true)));
	        
		        AppContext.debug("set selected");
		        listboxSubpopulation.setSelectedIndex(3);
		        listboxTrait.setSelectedIndex(0);
		        AppContext.debug("init snplistt");
		        listboxSnplist.setModel( new SimpleListModel( AppContext.createUniqueUpperLowerStrings( workspace.getSnpPositionPvalueListNames() , false, true) ));
		        AppContext.debug("init selected");
		        listboxSnplist.setSelectedIndex(0);
		        
		        List listVarphen=new ArrayList();
		        AppContext.debug("init varlistt");
		        listVarphen.addAll(workspace.getVarietyQuantPhenotypelistNames());
		        listVarphen.addAll(workspace.getVarietyCatPhenotypelistNames());
		        listboxVarietylist.setModel( new SimpleListModel( AppContext.createUniqueUpperLowerStrings( listVarphen, false, true) ));
		        AppContext.debug("init selected");
		        listboxVarietylist.setSelectedIndex(0);
		  
		        //divMultipositions.setVisible(AppContext.isLocalhost());
		        
		        /*
		        List listPvals=new ArrayList();
		        for(int i=50; i<9; i-=5) {
		        	listPvals.add(Integer.toString(i));
		        }
		        for(int i=9; i<1; i--) {
		        	listPvals.add(Integer.toString(i));
		        }
		        this.comboboxMinlogP.setModel(new SimpleListModel(listPvals));
		        */
		        //comboboxMinlogP.setSelectedIndex(13);
		        AppContext.debug("doAfterCompose done");
	        } catch(Exception ex) {
	        	ex.printStackTrace();
	        	throw ex;
	        }
	        
	 }
	


	@Listen("onSelect =#listboxTrait")
	public void onselectTrait() {
		if(listboxTrait.getSelectedIndex()==0) {
			//this.initDispayManhattan();
			this.chartManhattanXY.setVisible(false);
			return;
		}
		comboboxMinlogP.setSelectedIndex(13);
		displayManhattanXY();
	}

	@Listen("onSelect =#comboboxMinlogP")
	public void onselectMinlogp() {
		//if(comboboxMinlogP.getValue().isEmpty()) return;
		if(listboxSnplist.getSelectedIndex()>0 && listboxVarietylist.getSelectedIndex()>0)
			displayManhattanXY(listboxSnplist.getSelectedItem().getLabel(), listboxVarietylist.getSelectedItem().getLabel());
		else
			displayManhattanXY();
	}
	
	
	@Listen("onChange =#comboboxMinlogP")
	public void onchangeMinlogp() {
		//if(comboboxMinlogP.getValue().isEmpty()) return;
		if(listboxSnplist.getSelectedIndex()>0 && listboxVarietylist.getSelectedIndex()>0)
			displayManhattanXY(listboxSnplist.getSelectedItem().getLabel(), listboxVarietylist.getSelectedItem().getLabel());
		else
			displayManhattanXY();
	}
	

	@Listen("onSelect =#listboxSubpopulation")
	public void onselectSubpop() {
		List l=new ArrayList();
		l.add("");
		l.addAll(gwas.getTraits(listboxSubpopulation.getSelectedItem().getLabel()));
		listboxTrait.setModel(new SimpleListModel(  l ) );
		listboxTrait.setSelectedIndex(0);
		this.chartManhattanXY.setVisible(false);
		return;

		//displayManhattanXY();
	}
	
	@Listen("onSelect =#listboxChromosome")
	public void onselectChr() {
		//if(comboboxMinlogP.getValue().isEmpty()) return;
		if(listboxSnplist.getSelectedIndex()>0 && listboxVarietylist.getSelectedIndex()>0)
			displayManhattanXY(listboxSnplist.getSelectedItem().getLabel(), listboxVarietylist.getSelectedItem().getLabel());
		else
			displayManhattanXY();
	}
	

	@Listen("onSelect = #listboxSnplist")
	public void onselectSnpListbox() {
		
		if(listboxSnplist.getSelectedIndex()>0) {
			
			if(listboxVarietylist.getSelectedIndex()>0)
				displayManhattanXY(listboxSnplist.getSelectedItem().getLabel(), listboxVarietylist.getSelectedItem().getLabel());
			//else Messagebox.show("Select a variety list with phenotypes");
			else displayManhattanXY(listboxSnplist.getSelectedItem().getLabel(), null);
			
		}
	}
	
	@Listen("onSelect = #listboxVarietylist")
	public void onselectVarietylist() {
		
		if(listboxVarietylist.getSelectedIndex()>0) {
			if(listboxSnplist.getSelectedIndex()>0)
				displayManhattanXY(listboxSnplist.getSelectedItem().getLabel(), listboxVarietylist.getSelectedItem().getLabel());
			else Messagebox.show("Select a SNP list with -logP values");
		}
		
	}
	
	

	private void displayManhattanXY(String snplist, String varietylist ) {
		
		this.listboxSubpopulation.setSelectedIndex(0);this.listboxTrait.setSelectedIndex(0);
		
		
		//if(snplist.isEmpty() || varietylist.isEmpty()) return;
		if(snplist.isEmpty()) return;

		initDispayManhattan();
		
		workspace=(WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
		
		//int minlogp=Integer.valueOf(comboboxMinlogP.getValue());
		int minlogp=Integer.valueOf(comboboxMinlogP.getSelectedItem().getLabel());
		Set setSnps = workspace.getSnpPositions(snplist.split(":")[0], snplist.split(":")[1]);
		
		Set setVars=null;
		Set s=new HashSet(); s.add(dataset);
		if(varietylist!=null) setVars= workspace.getVarieties(varietylist);
		else setVars=variety.getGermplasm(s);
		AppContext.debug("plotting " + setSnps.size() + " snps");

		mapPos2Values=new TreeMap();
		mapPos2Index = new TreeMap();
		mapIndex2Pos=new TreeMap();
		int idx=0;
		Iterator itSnp=setSnps.iterator();
		while(itSnp.hasNext()) {
			MultiReferencePositionImplAllelePvalue pos= (MultiReferencePositionImplAllelePvalue)itSnp.next();
			//PositionLogPvalue pos= (PositionLogPvalue)itSnp.next();
			//AppContext.debug( pos.getClass() + "  " + pos);
			//Byte chr,  BigDecimal position, BigDecimal minp
			//sortPos.add(new ManhattanPlotImpl(Byte.valueOf(pos.getChr().toString()), pos.getPosition() ,BigDecimal.valueOf(pos.getPvalue())));
			String posstr=pos.getChr() + "-" + pos.getPosition();
			
			//if(pos.getLogPvalue()<minlogp) break;
			if(pos.getMinusLogPvalue()<minlogp) 
			
			mapPos2Values.put(posstr, pos.getMinusLogPvalue());
			mapPos2Index.put(posstr, idx);
			mapIndex2Pos.put(idx,  posstr);
			idx++;
		}
		displayManhattanXY(mapPos2Values);
		
		ArrayList listPos = new ArrayList();
		listPos.addAll(mapPos2Values.keySet());
		SimpleListModel lmodel=new SimpleListModel(listPos);
		lmodel.setMultiple(true);
		this.listboxMultiPosition.setModel(lmodel);
		this.tabPosition.setDisabled(false);
		
	}
	
	
	
//	private void displayManhattan() {
//	
//		
//		String trait= listboxTrait.getSelectedItem().getLabel();
//		String subpop= this.listboxSubpopulation.getSelectedItem().getLabel();
//		double minlogp= Double.valueOf(listboxMinlogP.getSelectedItem().getLabel());
//		
//		
//		if(trait==null || trait.isEmpty() || subpop==null || subpop.isEmpty()) return;
//		
//		//chartManhattan.getChart().setResetZoomButton(null);
//		
//	    AxisLabels xlabels = chartManhattan.getXAxis().getLabels();
//	    
//	    xlabels.setRotation(-80);
//	    xlabels.setAlign("right");
//	    //xlabels.setMaxStaggerLines(20);
//	    
//	    //xlabels.setStaggerLines(10);
//
//	    chartManhattan.getXAxis().setMinorTickInterval((Number)null);
//	    chartManhattan.getXAxis().setShowFirstLabel(true);
//	    chartManhattan.getXAxis().setShowLastLabel(true);
//	    //chartManhattan.getXAxis().setTickPixelInterval(200);
//	    //chartManhattan.getXAxis().setMinorTickInterval("auto");
//	    //chartManhattan.getXAxis().setMinTickInterval(10);
//	    //chartManhattan.getXAxis().setTickInterval(null);
//	    //chartManhattan.getXAxis().getm
//	    
//		chartManhattan.getYAxis().setTitle("-logP");
//		chartManhattan.setTitle("Manhattan plot");
//
//		DefaultCategoryModel model=readManhattan(trait,subpop,minlogp);
//	  	double min = 0;
//	    //double max = mapPos2Values.size(); //  genotypefreqlines.linecountmajormodel.getSeries(0). //chartAlleleFrequency.getXAxisSize();
//	  	double max  = this.mapViewPos2Index.size();
//	    
//	    long interval = Math.max(1,  Double.valueOf(  Math.ceil( Double.valueOf((max-min)/nLabels)) ).longValue() );
//	    if(interval>nMarkers)
//	    	interval=nMarkers/nLabels;
//	    //chartManhattan.getXAxis().setTickAmount(nLabels);
//		xlabels.setStep( interval );
//		
//		//chartManhattan.getPlotOptions().getScatter().getDataLabels()
//		chartManhattan.setModel(model);
//		//AppContext.debug("linewidth=" +  chartManhattan.getPlotOptions().getLine().getLineWidth());
//		chartManhattan.getPlotOptions().getSeries().setLineWidth(0);
//		chartManhattan.getPlotOptions().getSeries().getMarker().setWidth(3);
//		//chartManhattan.getPlotOptions().getLine().set .setLineWidth(0);
//		//chartManhattan.getPlotOptions().getLine().setColor(color);
//		
//
//		//msgJbrowse.setValue("Select a narrow region in a single contig/chromosome to JBrowse tracks. Click a peak to display allele distributions.");
//		labelManhattan.setValue(this.mapPos2Index.size() + " markers, select a narrow region to zoom, show JBrowse tracks and marker annotations. Click a marker to display allele distributions and varieties.");
//		labelManhattan.setVisible(true);
//		this.tabRegion.setSelected(true);
//		
//		borderMarkerVar.setVisible(false);
//		listboxMarkerVar.setVisible(false);
//		listboxMarkerVar.setModel(new SimpleListModel(new ArrayList()));
//		
//		msgJbrowse.setVisible(false);
//		iframeJbrowse.setVisible(false);
//		
//		List listpos=new ArrayList();
//		listpos.addAll( mapPos2Index.keySet() );
//		this.listboxPosition.setModel(new SimpleListModel(listpos));
//
//	}
	
	
	@Listen("onClick =#radioGroupbyMarker")
	public void groupbyMarker() {
		listboxMarkerVar.setModel( new SimpleListModel( markerresult ));
	}
	
	@Listen("onClick =#radioGroupbyGene")
	public void groupbyGene() {
		
		listboxMarkerVar.setModel( new SimpleListModel( genomics.getMarkerAnnotsByGene(markerresult , 0)));
		
	}

	@Listen("onClick =#radioGroupbyQtl")
	public void groupbyQtl() {
		listboxMarkerVar.setModel( new SimpleListModel( genomics.getMarkerAnnotsByQTL(markerresult , 0)));
		
	}
	public void searchbyMySnpListQtl() {
		//initResults();
		
		try {
				if(markerresult.size()>0) {
					List<Component> listheadersmarker = listboxMarkerVar.getListhead().getChildren();
					listheadersmarker.clear();
					Listheader header=new Listheader();
					header.setLabel("CONTIG,POS,-LOGP"); header.setWidth("150px");  
					header.setSortAscending(new MarkerAnnotationSorter(true,0)); header.setSortDescending(new MarkerAnnotationSorter(false,0));
					listheadersmarker.add(header);

					boolean hasPvalue=true;
					if(hasPvalue) {
						headerLogp=new Listheader();
						headerLogp.setLabel("-LOGP"); headerLogp.setWidth("100px");  
						headerLogp.setSortAscending(new MarkerAnnotationSorter(true,1)); headerLogp.setSortDescending(new MarkerAnnotationSorter(false,1));
						listheadersmarker.add(headerLogp);
						AppContext.debug("headerLogp created");
					} else AppContext.debug("headerLogp NOT created");

					/*
					header=new Listheader();
					header.setLabel("POSITION"); header.setWidth("100px");  
					header.setSortAscending(new MarkerAnnotationSorter(true,1)); header.setSortDescending(new MarkerAnnotationSorter(false,1));
					listheadersmarker.add(header);
					*/
					header=new Listheader();
					header.setLabel("GENE MODELS"); header.setWidth("300px");
					header.setSortAscending(new MarkerAnnotationSorter(true,"GENE MODELS")); header.setSortDescending(new MarkerAnnotationSorter(false,"GENE MODELS"));
					listheadersmarker.add(header);
					
					//Iterator itAnnotnames =  markerresult.get(0).getAnnotations().iterator();
					Iterator itAnnotnames =  setAnnotations.iterator();
					while(itAnnotnames.hasNext()) {
						String annotname=(String)itAnnotnames.next();
						if(annotname.equals("GENE MODELS")) continue;
						header=new Listheader();
						header.setLabel(annotname.toUpperCase());
						if(setAnnotations.size()>3)
							header.setWidth("400px"); 
						else header.setWidth("700px");
						header.setSortAscending(new MarkerAnnotationSorter(true,annotname)); header.setSortDescending(new MarkerAnnotationSorter(false,annotname));
						listheadersmarker.add(header);
					}
				};
				
				listboxMarkerVar.setItemRenderer(new MarkerVarListItemRenderer(setAnnotations));
				listboxMarkerVar.setModel( new SimpleListModel( markerresult ));
			
			hboxAddtolist.setVisible(markerresult.size()>0);
			hboxAddtolistGenes.setVisible(markerresult.size()>0);
				 
			//this.msgbox.setValue("Search by SNP List positions: Contig " + contigname[0].trim() + ", SNP List " + listboxMySNPList.getSelectedItem().getLabel() + " ... RESULT:" + markerresult.size() + " markers");
			
			//listboxMarker.setVisible(true);
			listboxMarkerVar.setVisible(true);
			borderMarkerVar.setVisible(true);
			hboxDownload.setVisible(true);
			
			} catch(Exception ex) {
				ex.printStackTrace();
				Messagebox.show( ex.getMessage(), "onlclickSearchFunction Exception", Messagebox.OK, Messagebox.ERROR );
			}
		
		//updateGenesetLinks();
		
	}	
	private  void searchbyMySnpListQtlOld() {
		
		try {
				if(markerresult.size()>0) {
					List<Component> listheadersmarker = listboxMarkerVar.getListhead().getChildren();
					listheadersmarker.clear();
					Listheader header=new Listheader();
					header.setLabel("CONTIG,POS,-LOGP"); header.setWidth("150px");  
					header.setSortAscending(new MarkerAnnotationSorter(true,0)); header.setSortDescending(new MarkerAnnotationSorter(false,0));
					listheadersmarker.add(header);
					/*
					header=new Listheader();
					header.setLabel("POSITION"); header.setWidth("100px");  
					header.setSortAscending(new MarkerAnnotationSorter(true,1)); header.setSortDescending(new MarkerAnnotationSorter(false,1));
					listheadersmarker.add(header);
					*/
					header=new Listheader();
					header.setLabel("GENE MODELS"); header.setWidth("300px");
					header.setSortAscending(new MarkerAnnotationSorter(true,"GENE MODELS")); header.setSortDescending(new MarkerAnnotationSorter(false,"GENE MODELS"));
					listheadersmarker.add(header);
					//Iterator itAnnotnames =  markerresult.get(0).getAnnotations().iterator();
					Iterator itAnnotnames =  setAnnotations.iterator();
					while(itAnnotnames.hasNext()) {
						String annotname=(String)itAnnotnames.next();
						if(annotname.equals("GENE MODELS")) continue;
						header=new Listheader();
						header.setLabel(annotname.toUpperCase());
						if(setAnnotations.size()>3)
							header.setWidth("400px"); 
						else header.setWidth("700px");
						header.setSortAscending(new MarkerAnnotationSorter(true,annotname)); header.setSortDescending(new MarkerAnnotationSorter(false,annotname));
						listheadersmarker.add(header);
					}
				};
				
				listboxMarkerVar.setItemRenderer(new MarkerVarListItemRenderer(setAnnotations));
				listboxMarkerVar.setModel( new SimpleListModel( markerresult ));
			
			if(markerresult.size()>0) hboxAddtolist.setVisible(true);
			else hboxAddtolist.setVisible(false);
			//this.msgbox.setValue("Search by SNP List positions: Contig " + contigname[0].trim() + ", SNP List " + listboxMySNPList.getSelectedItem().getLabel() + " ... RESULT:" + markerresult.size() + " markers");
			listboxMarkerVar.setVisible(true);
			borderMarkerVar.setVisible(true);

			hboxDownload.setVisible(true);
			
			} catch(Exception ex) {
				ex.printStackTrace();
				Messagebox.show( ex.getMessage(), "onlclickSearchFunction Exception", Messagebox.OK, Messagebox.ERROR );
			}
	}
	

	@Listen("onClick =#buttonDownloadCSV") 
	public void downloadtableCSV() {
		downloadtable(",",";");
	}
	
	@Listen("onClick =#buttonDownloadTab") 
	public void downloadtableTab() {
		downloadtable("\t",";");
	}
	
	public void downloadtable(String delimiter, String celldelimiter) {
		
		int plusminus=0;
		
		AppContext.debug("executing downloadtable...");
    	if(this.listboxMarkerVar.isVisible()) {

    		
    		StringBuffer buff=new StringBuffer();
    		//buff.append("CONTIG-POSITION").append(delimiter).append("POSITION").append(delimiter).append("GENE MODELS");
    		buff.append("\"CONTIG,POS,-LOGP\"").append(delimiter).append("\"GENE MODELS\"");
    		
    		Set tempset = new LinkedHashSet(setAnnotations);
    		//tempset.remove("GENE MODELS");
    		
    		Iterator<String> itAnnots = tempset.iterator();
    		if(itAnnots.hasNext()) buff.append(delimiter);
			while(itAnnots.hasNext()) {
				buff.append("\"").append(itAnnots.next()).append("\"");
				if(itAnnots.hasNext()) buff.append(delimiter);
			}
			buff.append("\n");
    		
			List displaylist=markerresult;
			if(radioGroupbyGene.isSelected())
				displaylist=genomics.getMarkerAnnotsByGene(markerresult, plusminus);
			else if(radioGroupbyQtl.isSelected())
				displaylist=genomics.getMarkerAnnotsByQTL(markerresult, plusminus);
			
			Iterator<MarkerAnnotation> itMarker=displaylist.iterator();
    		while(itMarker.hasNext()) {
    			MarkerAnnotation annot = itMarker.next();
    			String rows[]=GenomicsFacadeImpl.MarkerAnnotationTable(annot, tempset,celldelimiter);
    			for(int col=0;col<rows.length; col++) {
    				if(rows[col]!=null)
    					buff.append("\"").append(rows[col]).append("\"");
    				
    				if(col<rows.length-1) buff.append(delimiter);
    			}
    			buff.append("\n");
    		}
			
			
    		String filetype = "text/plain";
    		String fileext=".txt";
    		if(delimiter.equals(",")) {
    			filetype="text/csv";
    			fileext=".csv";
    		}
    		
    		try {
	    		String filename="markers-" + AppContext.createTempFilename() + fileext;
	    		AppContext.debug("downloading... " + filename);
	    		Filedownload.save(  buff.toString(), filetype , filename) ;
	    		//AppContext.debug("File download complete! Saved to: "+filename);
	    		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
	    		AppContext.debug("markers download complete!"+ filename +  " " + markerresult.size() + " MarkerAnnots,  Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
    		} catch(Exception ex) {
    			ex.printStackTrace();
    			Messagebox.show(ex.getMessage());
    		}
    		
    		
    	}
	}
	

	@Listen("onClick =#buttonAddToListSnp") 
	public void onclickAddtolistSnp() {
		// add to locus list
	
		try {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List addSnplist = null;
    	
    	if(this.listboxMarkerVar.isVisible()) {
    		addSnplist=new ArrayList();
    		Iterator<MarkerAnnotation> itMarker=markerresult.iterator();
    		while(itMarker.hasNext()) {
    			MarkerAnnotation m=itMarker.next();
    			addSnplist.add( new PositionLogPvalueImpl(m.getContig() ,m.getPosition(), m.getChr().intValue(), m.getMinusLogPvalue())) ;
    		}
    		//addSnplist= markerresult;
    	} else if(mapPos2Values!=null && !mapPos2Values.isEmpty()) {
    		addSnplist=new ArrayList();
    		Iterator<String> itPos=mapPos2Values.keySet().iterator();
    		while(itPos.hasNext()) {
    			String posstr=itPos.next();
    			String pos[]=posstr.split("\\-");
    			addSnplist.add( new PositionLogPvalueImpl(pos[0] , BigDecimal.valueOf(Long.valueOf(pos[1]) ),Integer.valueOf(AppContext.guessChrFromString(pos[0])), mapPos2Values.get(posstr) )) ;
    		}
    	}
    	
    	
    	if(addSnplist!=null &&  addSnplist.size()==0) {
    		Messagebox.show( "EMPTY SNP LIST");
    		return;
    	}
    	if(txtboxListnameSnp.getValue().isEmpty()) {
    		Messagebox.show( "PROVIDE LIST NAME");
    		return;
    	}
    	
    	
    	workspace.addSnpPositionList("ANY", txtboxListnameSnp.getValue(), new LinkedHashSet(addSnplist), false, true); 
    	txtboxListnameSnp.setValue("");
		
/*    	
    	List listNew = new ArrayList();
    	listNew.add("");
    	listNew.addAll( workspace.getSnpPositionPvalueListNames());
    	listNew.add("create new list...");
    	this.listboxMyLocusList.setModel(new SimpleListModel(listNew ));
  */  	
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "Addtolist Exception", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	public void onclickAddtolistSnpOld() {
		// add to locus list
	
		try {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List addSnplist = null;
    	
    	if(this.listboxMarkerVar.isVisible()) {
    		addSnplist= markerresult;
    	} 
    	
    	
    	if(addSnplist!=null &&  addSnplist.size()==0) {
    		Messagebox.show( "EMPTY LOCUS LIST");
    		return;
    	}
    	if(txtboxListnameSnp.getValue().isEmpty()) {
    		Messagebox.show( "PROVIDE LIST NAME");
    		return;
    	}
    	
    	
    	workspace.addSnpPositionList("ANY", txtboxListnameSnp.getValue(), new LinkedHashSet(addSnplist), false, true); 
    	txtboxListnameSnp.setValue("");
		
/*    	
    	List listNew = new ArrayList();
    	listNew.add("");
    	listNew.addAll( workspace.getSnpPositionPvalueListNames());
    	listNew.add("create new list...");
    	this.listboxMyLocusList.setModel(new SimpleListModel(listNew ));
  */  	
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "Addtolist Exception", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	

	@Listen("onClick =#buttonAddToListGene") 
	public void onclickAddtolistGene() {
		// add to locus list
	
		try {
    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
    	
    	List addLocuslist = null;
    	
    	if (this.listboxMarkerVar.isVisible()) {
    		Set[] setLociName= collectLoci();
    		AppContext.debug("adding genes " + setLociName[0] + setLociName[1]);
    		Set setLoci=new LinkedHashSet();
    		if(!setLociName[0].isEmpty())
    			setLoci.addAll(genomics.getLocusByName(setLociName[0]));
    		if(!setLociName[1].isEmpty())
    			setLoci.addAll(genomics.getLocusByName(setLociName[1]));
    		addLocuslist=new ArrayList();
    		addLocuslist.addAll(setLoci);
    	}
    	if(addLocuslist!=null &&  addLocuslist.size()==0) {
    		
    		Messagebox.show( "EMPTY LOCUS LIST");
    		return;
    	}
    	if(txtboxListnameGene.getValue().isEmpty()) {
    		Messagebox.show( "PROVIDE LIST NAME");
    		return;
    	}
    	
    	workspace.addLocusList( txtboxListnameGene.getValue().trim(), new LinkedHashSet(addLocuslist) );
    	txtboxListnameGene.setValue("");
    	
    	
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "Addtolist Exception", Messagebox.OK, Messagebox.ERROR);
		}
		 
    	
	}
	
	private Set[] collectLoci() {
		Set setMSU=new HashSet();
		Set setRAP=new HashSet();
		if(this.listboxMarkerVar.isVisible()) {
			/*
			boolean includeRicenet= checkboxListRicenet.isChecked();
			boolean includeGenemodel= checkboxListModel.isChecked();
			boolean includePrin= checkboxListPrin.isChecked();
			boolean includePromoter= checkboxListPromoter.isChecked();
			*/
			boolean includeRicenet=checkboxIncludeInteractions.isChecked();
			boolean includePrin=includeRicenet;
			boolean includePromoter=checkboxIncludePromoters.isChecked();
			boolean includeGenemodel=true;
			/*
			<checkbox id="checkboxIncludeInteractions" label="Interactions (RiceNetv2,PRIN)"/>
			<checkbox id="checkboxIncludePromoters" label="Promoters (FGenesh++,PlantPromDB)"/>
			<checkbox id="checkboxIncludeGO" label="Gene Ontology"/>
			<checkbox id="checkboxIncludePOTO" label="PO, TO, OGRO"/>
			<checkbox id="checkboxIncludeQTL" label="Q-TARO QTLs"/>
			*/
			Iterator<MarkerAnnotation> itMarker=markerresult.iterator();
    		while(itMarker.hasNext()) {
    			MarkerAnnotation annot = itMarker.next();
    			Set allgenes=new HashSet();
    			
    			if(includeGenemodel) {
	    			if(annot.getAnnotation(MarkerAnnotation.GENE_MODEL)!=null && !annot.getAnnotation(MarkerAnnotation.GENE_MODEL).isEmpty()) {
	    				allgenes.addAll( annot.getAnnotation(MarkerAnnotation.GENE_MODEL) );
	    			}
    			}
    			
    			if(includeRicenet) {
    				if(annot.getAnnotation(MarkerAnnotation.GENE_INT_RICENETV2)!=null && !annot.getAnnotation(MarkerAnnotation.GENE_INT_RICENETV2).isEmpty())  {
    					allgenes.addAll( annot.getAnnotation(MarkerAnnotation.GENE_INT_RICENETV2) );
    				}
    			}
    			if(includePrin) {
    				if(annot.getAnnotation(MarkerAnnotation.GENE_INT_PRINEXPT)!=null && !annot.getAnnotation(MarkerAnnotation.GENE_INT_PRINEXPT).isEmpty())  {
    					allgenes.addAll( annot.getAnnotation(MarkerAnnotation.GENE_INT_PRINEXPT) );
    				}
    				if(annot.getAnnotation(MarkerAnnotation.GENE_INT_PRINPRED)!=null && !annot.getAnnotation(MarkerAnnotation.GENE_INT_PRINPRED).isEmpty())  {
    					allgenes.addAll( annot.getAnnotation(MarkerAnnotation.GENE_INT_PRINPRED) );
    				}
    			}
    			if(includePromoter) {
    				if(annot.getAnnotation(MarkerAnnotation.PROM_FGENESH1K)!=null && !annot.getAnnotation(MarkerAnnotation.PROM_FGENESH1K).isEmpty())  {
    					allgenes.addAll( annot.getAnnotation(MarkerAnnotation.PROM_FGENESH1K) );
    				}
    				
    				if(annot.getAnnotation(MarkerAnnotation.PROM_PLANTPROMDB)!=null && !annot.getAnnotation(MarkerAnnotation.PROM_PLANTPROMDB).isEmpty())  {
    					allgenes.addAll( annot.getAnnotation(MarkerAnnotation.PROM_PLANTPROMDB) );
    				}
    			}

				Iterator<Locus> itLocus=allgenes.iterator();
				while(itLocus.hasNext()) {
					Locus loc= itLocus.next();
					if(loc.getUniquename().toUpperCase().startsWith("LOC_")) setMSU.add(loc.getUniquename());
					else if(loc.getUniquename().toUpperCase().startsWith("OS0") || loc.getUniquename().toUpperCase().startsWith("OS1")) setRAP.add(loc.getUniquename());
					if(loc instanceof MergedLoci) {
						MergedLoci ml=(MergedLoci)loc;
						StringBuffer loci=new StringBuffer();
						if(ml.getMSU7Name()!=null) {
							String[] locnames= ml.getMSU7Name().split(",");
							for(int iloc=0; iloc<locnames.length; iloc++) setMSU.add(locnames[iloc]);
						}
						if(ml.getRAPRepName()!=null) {
							String[] locnames= ml.getRAPRepName().split(",");
							for(int iloc=0; iloc<locnames.length; iloc++) setRAP.add(locnames[iloc]);
						}
						if(ml.getRAPPredName()!=null) {
							String[] locnames= ml.getRAPPredName().split(",");
							for(int iloc=0; iloc<locnames.length; iloc++) setRAP.add(locnames[iloc]);
						}
					}

					if(loc instanceof LocusPromoter) {
						Set  proms = ((LocusPromoter)loc).getOverlappingGenes();
						Iterator<String> itProms=proms.iterator();
						while(itProms.hasNext()) {
							String promname=itProms.next();
							if(promname.toUpperCase().startsWith("LOC_")) setMSU.add(promname);
							else if(promname.toUpperCase().startsWith("OS0") || promname.toUpperCase().startsWith("OS1")) setRAP.add(promname);
						}
					}
					
					if(loc.getUniquename().startsWith("OsNippo") && !(loc instanceof MergedLoci) && !(loc instanceof LocusPromoter)) {
						AppContext.debug("loc.getUniquename().startsWith(OsNippo) && !(loc instanceof MergedLoci) && !(loc instanceof LocusPromoter) for " + loc.getUniquename() ); 
					}


				}

    			/*
    			if(annot.getAnnotation(MarkerAnnotation.GENE_MODEL)!=null && !annot.getAnnotation(MarkerAnnotation.GENE_MODEL).isEmpty()) {
    				Iterator<Locus> itLocus=annot.getAnnotation(MarkerAnnotation.GENE_MODEL).iterator();
    				while(itLocus.hasNext()) {
    					Locus loc= itLocus.next();
    					if(loc.getUniquename().toUpperCase().startsWith("LOC_")) setMSU.add(loc.getUniquename());
    					else if(loc.getUniquename().toUpperCase().startsWith("OS0") || loc.getUniquename().toUpperCase().startsWith("OS1")) setRAP.add(loc.getUniquename());
    					if(loc instanceof MergedLoci) {
    						MergedLoci ml=(MergedLoci)loc;
    						StringBuffer loci=new StringBuffer();
    						if(ml.getMSU7Name()!=null) setMSU.add(ml.getMSU7Name());
    						if(ml.getRAPRepName()!=null) setRAP.add( ml.getRAPRepName());
    						if(ml.getRAPPredName()!=null) setRAP.add( ml.getRAPPredName());    				
    					}
    				}
    			}
    			*/
    			
    			
    			
    		}
		}
		
		return new Set[] {setMSU, setRAP };
	}

	
	private void initDispayManhattan() {
		//this.chartManhattanXY.setModel(new DefaultCategoryModel());
		msgJbrowse.setVisible(false);
	    iframeJbrowse.setVisible(false);
	    //gridMarker.setVisible(false);
		borderMarkerVar.setVisible(false);
	    listboxMarkerVar.setVisible(false);
	    labelAnnotations.setVisible(false);
	    this.labelManhattan.setValue("");
	}
	
	private void displayManhattanXY() {
	    
	    //listboxSnplist.setSelectedIndex(0);
		Set snplist=null;
		if(listboxSnplist.getSelectedIndex()>0) {
			String listname[]=listboxSnplist.getSelectedItem().getLabel().split(":");
			snplist=workspace.getSnpPositions(listname[0],listname[1]);
		}
	    this.listboxVarietylist.setSelectedIndex(0);
	    
	    
		String trait= listboxTrait.getSelectedItem().getLabel();
		String subpop= this.listboxSubpopulation.getSelectedItem().getLabel();
		
		

		initDispayManhattan();
		if(trait==null || trait.isEmpty() || subpop==null || subpop.isEmpty()) return;

		//double minlogp= Double.valueOf(comboboxMinlogP.getValue());
		double minlogp=Double.valueOf(comboboxMinlogP.getSelectedItem().getLabel());

		AxisLabels xlabels = chartManhattanXY.getXAxis().getLabels();
	    xlabels.setRotation(-80);
	    xlabels.setAlign("right");
	    //chartManhattanXY.getXAxis().setMinorTickInterval((Number)null);
	    chartManhattanXY.getYAxis().setTitle("-logP");
	    chartManhattanXY.setTitle("Manhattan plot");
	    
		readManhattanXY(trait,subpop,minlogp, snplist, listboxChromosome.getSelectedItem().getLabel());
		
			List listpos=new ArrayList();
			listpos.addAll( mapPos2Index.keySet() );
			this.listboxPosition.setModel(new SimpleListModel(listpos));
			SimpleListModel lmodel=new SimpleListModel(listpos);
			lmodel.setMultiple(true);
			this.listboxMultiPosition.setModel(lmodel);

		
		
		if(mapPos2Index.size()==0)
			labelManhattan.setValue(this.mapPos2Index.size() + " markers, decrease Minimum -logP.");
		else labelManhattan.setValue(this.mapPos2Index.size() + " markers, select a narrow region to zoom, show JBrowse tracks and marker annotations. Click a marker to display allele distributions and varieties.");

		
		this.tabRegion.setSelected(true);
		
		if(this.listboxSnplist.getSelectedIndex()>0)
			 this.tabPosition.setDisabled(false);
		else this.tabPosition.setDisabled(true);

		if(mapPos2Index.size()<100) {
			tabPosition.setDisabled(false);
		}

		
//		
//		if(trait==null || trait.isEmpty() || subpop==null || subpop.isEmpty()) return;
//		
//		//chartManhattan.getChart().setResetZoomButton(null);
//		
//	    AxisLabels xlabels = chartManhattan.getXAxis().getLabels();
//	    
//	    xlabels.setRotation(-80);
//	    xlabels.setAlign("right");
//	    //xlabels.setMaxStaggerLines(20);
//	    
//	    //xlabels.setStaggerLines(10);
//
//	    chartManhattan.getXAxis().setMinorTickInterval((Number)null);
//	    chartManhattan.getXAxis().setShowFirstLabel(true);
//	    chartManhattan.getXAxis().setShowLastLabel(true);
//	    //chartManhattan.getXAxis().setTickPixelInterval(200);
//	    //chartManhattan.getXAxis().setMinorTickInterval("auto");
//	    //chartManhattan.getXAxis().setMinTickInterval(10);
//	    //chartManhattan.getXAxis().setTickInterval(null);
//	    //chartManhattan.getXAxis().getm
//	    
//		chartManhattan.getYAxis().setTitle("-logP");
//		chartManhattan.setTitle("Manhattan plot");
//
//		
//		
//		DefaultCategoryModel model=readManhattan(trait,subpop,minlogp);
//	  	double min = 0;
//	    //double max = mapPos2Values.size(); //  genotypefreqlines.linecountmajormodel.getSeries(0). //chartAlleleFrequency.getXAxisSize();
//	  	double max  = this.mapViewPos2Index.size();
//	    
//	    long interval = Math.max(1,  Double.valueOf(  Math.ceil( Double.valueOf((max-min)/nLabels)) ).longValue() );
//	    if(interval>nMarkers)
//	    	interval=nMarkers/nLabels;
//	    //chartManhattan.getXAxis().setTickAmount(nLabels);
//		xlabels.setStep( interval );
//		
//		//chartManhattan.getPlotOptions().getScatter().getDataLabels()
//		chartManhattan.setModel(model);
//		//AppContext.debug("linewidth=" +  chartManhattan.getPlotOptions().getLine().getLineWidth());
//		chartManhattan.getPlotOptions().getSeries().setLineWidth(0);
//		chartManhattan.getPlotOptions().getSeries().getMarker().setWidth(3);
//		//chartManhattan.getPlotOptions().getLine().set .setLineWidth(0);
//		//chartManhattan.getPlotOptions().getLine().setColor(color);
//		
//
//		//msgJbrowse.setValue("Select a narrow region in a single contig/chromosome to JBrowse tracks. Click a peak to display allele distributions.");
//		labelManhattan.setValue(this.mapPos2Index.size() + " markers, select a narrow region to zoom, show JBrowse tracks and marker annotations. Click a marker to display allele distributions and varieties.");
//		labelManhattan.setVisible(true);
//		this.tabRegion.setSelected(true);
//		
//		gridMarker.setVisible(false);
//		msgJbrowse.setVisible(false);
//		gridMarker.setModel(new SimpleListModel(new ArrayList()));
//		iframeJbrowse.setVisible(false);
	}
	

	/*
	@Listen("onClick =#buttonResetzoom")
	public void oncleckReset() {
		reloadManhattan();
	}
	*/
	
//	@Listen("onSelection = #chartManhattan")
//	public void doSelection(ChartsSelectionEvent event) {
//	    // doing the zooming in function
//		
//		urljbrowse=null;
//		
//	    int min= event.getXAxisMin().intValue();
//	    int max = event.getXAxisMax().intValue();
//	    long interval = Math.max(1,  Double.valueOf((max-min)/nLabels).longValue());
//	    AppContext.debug("event.getName()=" + event.getName() + "  min=" + min + " max=" + max + " interval=" + interval);
//	    AppContext.debug("left=" +  mapViewIndex2Pos.get( min) + ", right=" +   mapViewIndex2Pos.get(max));
//	    chartManhattan.getXAxis().getLabels().setStep( interval );
//	    
//	    msgJbrowse.setVisible(false);
//	    this.labelAnnotations.setVisible(false);
//	    iframeJbrowse.setVisible(false);
//		borderMarkerVar.setVisible(false);
//	    listboxMarkerVar.setVisible(false);
//    	
//
//	    if(mapViewIndex2Pos.get(min)==null) {
//	    	AppContext.debug("mapViewIndex2Pos.get(min)==null" + min);
//	    	return;
//	    }
//	    if(mapViewIndex2Pos.get(max)==null) {
//	    	AppContext.debug("mapViewIndex2Pos.get(max)==null" + max);
//	    	return;
//	    }
//	    
//    	int globalidxmin = mapPos2Index.get(mapViewIndex2Pos.get(min));
//    	int globalidxmax = mapPos2Index.get(mapViewIndex2Pos.get(max));
//    	
//	    if(mapViewIndex2Pos.get( min).split("\\-")[0].equals(  mapViewIndex2Pos.get( max).split("\\-")[0] )) {
//	    	String chr=mapViewIndex2Pos.get( min).split("\\-")[0];
//	    	if(chr.length()==1) chr="chr0" + chr;
//	    	else chr="chr" + chr;
//
//	    	List listPos=new ArrayList();
//	    	for(int idx=globalidxmin; idx<=globalidxmax; idx++) {
//	    		listPos.add(  BigDecimal.valueOf( Long.valueOf( mapIndex2Pos.get(idx).split("\\-")[1] )));
//	    	}
//
//	    	labelAnnotations.setVisible(false);
//	    	if( listPos.size()>200) {
//	    		labelAnnotations.setValue("Too many markers to annotate. Limit selection to <200 markers.");
//	    		labelAnnotations.setVisible(true);
//	    	} else
//	    		updateMarkerAnnotations(chr, listPos);
//	    	
//	    	updateJBrowse(chr,  mapViewIndex2Pos.get(min).split("\\-")[1],  mapViewIndex2Pos.get( max).split("\\-")[1], "" );
//	    	if(listPos.size()==0)
//	    		labelManhattan.setValue(listPos.size() + " markers, decrease Minumum -logP.");
//	    	else labelManhattan.setValue(listPos.size() + " markers, select a narrow region to zoom, show JBrowse tracks and marker annotations. Click a marker to display allele distributions and varieties.");
//
//
//	    } else {
//	    	msgJbrowse.setValue("Cannot annotate and display multiple contigs in JBrowse. Narrow selected region to a single contig/chromosome");
//	    	msgJbrowse.setVisible(true);
//	    }
//
//    	Map<String, Double> mapZoomManhattan=new LinkedHashMap();
//    	for(int idx=globalidxmin; idx<=globalidxmax; idx++) {
//    		String zoompos = mapIndex2Pos.get(idx);
//    		mapZoomManhattan.put(zoompos , mapPos2Values.get(zoompos));
//    	}
//    	chartManhattan.setModel( displayManhattan(mapZoomManhattan) );
//		chartManhattan.getPlotOptions().getSeries().setLineWidth(0);
//		chartManhattan.getPlotOptions().getSeries().getMarker().setWidth(3);
//
//	}
	

	private Integer[] convertX2ChrPos(Integer val) {
		
		Integer chr=0;
		Integer off=0;
		if(val>345713663) { chr=12; off=345713663;} else if(val>316692557){chr=11; off=316692557;}  else if(val>293485270){chr=10; off=293485270;}  else if(val>270472550){chr=9; off=270472550;}  else if(val>242029528){chr=8; off=242029528;} 
		else if(val>212331907){chr=7; off=212331907;}  else if(val>181083120){chr=6; off=181083120;}    else if(val>151124686){chr=5; off=151124686;}   else if(val>115621992){chr=4; off=115621992;} 
		else if(val>79208173){chr=3; off=79208173;}   else if(val>43270923){chr=2; off=43270923;}   else {chr=1; off=0; };
		return new Integer[]{ chr, val-off };
	}

	
	@Listen("onSelection = #chartManhattanXY")
	public void doSelectionXY(ChartsSelectionEvent event) {
	    // doing the zooming in function
		
		    
	    int min= event.getXAxisMin().intValue();
	    int max = event.getXAxisMax().intValue();
	    msgJbrowse.setVisible(false);
	    iframeJbrowse.setVisible(false);
		borderMarkerVar.setVisible(false);
	    listboxMarkerVar.setVisible(false);
	    this.labelAnnotations.setVisible(false);


	    
	    Integer minpos[] = this.convertX2ChrPos(min);
	    Integer maxpos[] = this.convertX2ChrPos(max);
	    
	    
	    //long interval = Math.max(1,  Double.valueOf((max-min)/nLabels).longValue());
	    AppContext.debug("event.getName()=" + event.getName() + "  min=" + min + " max=" + max ); //+ " interval=" + interval);
	    AppContext.debug("left=" +   minpos[0] + "-" + minpos[1] + ", right=" + maxpos[0] + "-" + maxpos[1]);
	    //chartManhattan.getXAxis().getLabels().setStep( interval );

	    /*
	    if(mapIndex2Pos.get(min)==null) {
	    	AppContext.debug("mapIndex2Pos.get(min)==null" + min);
	    	return;
	    }
	    if(mapIndex2Pos.get(max)==null) {
	    	AppContext.debug("mapIndex2Pos.get(max)==null" + max);
	    	return;
	    }
	    */
	    
    	int globalidxmin =  minpos[1];
    	int globalidxmax = maxpos[1];
    	
	    if(minpos[0].equals(  maxpos[0] )) {
	    	String chr=minpos[0].toString();
	    	String chrstr=minpos[0].toString();
	    	if(chr.length()==1) chr="chr0" + chr;
	    	else chr="chr" + chr;

	    	List listPosstr=new ArrayList();
	    	List listPos=new ArrayList();
	    	for(int idx=globalidxmin; idx<=globalidxmax; idx++) {
	    		String chrpos=chrstr+"-"+idx;
	    		if(this.mapPos2Index.containsKey(chrpos)){
	    			//listPos.add(  BigDecimal.valueOf( Long.valueOf( idx )));
	    			
	    			//mapPos2Values
	    			
	    			//listPos.add( new PositionImpl(chr, BigDecimal.valueOf( Long.valueOf( idx ))));
	    			//String contig, BigDecimal position, Integer chr, Double minuslogp
	    			listPos.add( new PositionLogPvalueImpl(chr, BigDecimal.valueOf( Long.valueOf( idx )), Integer.valueOf(AppContext.guessChrFromString(chr)), mapPos2Values.get(chrpos)));
	    			listPosstr.add(chrpos );
	    		}
	    		//if(mapIndex2Pos.containsKey(idx)) listPos.add(  BigDecimal.valueOf( Long.valueOf( mapIndex2Pos.get(idx).split("\\-")[1] )));
	    	}

	    	labelAnnotations.setVisible(false);
	    	if( listPos.size()>200) {
	    		labelAnnotations.setValue("Too many markers to annotate. Limit selection to <200 markers.");
	    		labelAnnotations.setVisible(true);
	    		this.tabPosition.setDisabled(true);
	    		listboxPosition.setModel(new SimpleListModel(new ArrayList()));
	    		listboxMultiPosition.setModel(new SimpleListModel(new ArrayList()));
	    	} else {
	    		this.tabPosition.setDisabled(false);
	    		updateMarkerAnnotations(chr, listPos);
	    		this.listboxPosition.setModel(new SimpleListModel(listPosstr));
	    		SimpleListModel lmodel=new SimpleListModel(listPosstr);
	    		lmodel.setMultiple(true);
	    		this.listboxMultiPosition.setModel(lmodel);
	    	}
	    	
	    	if(listPos.size()>0) updateJBrowse(chr,  Integer.toString(globalidxmin) ,  Integer.toString(globalidxmax), "" );
			labelManhattan.setValue(listPos.size() + " markers, select a narrow region to zoom, show JBrowse tracks and marker annotations. Click a marker to display allele distributions and varieties.");


	    } else {
	    	
	    	msgJbrowse.setValue("Cannot display multiple contigs/chromosomes in JBrowse. Narrow selected region to a single contig/chromosome");
	    	msgJbrowse.setVisible(true);
	    	
	    	String chr=minpos[0].toString();
	    	String chrstr=minpos[0].toString();
	    	if(chr.length()==1) chr="chr0" + chr;
	    	else chr="chr" + chr;
	    	List listPosstr=new ArrayList();
	    	List listPos=new ArrayList();
	    	for(int idx=globalidxmin; idx<=globalidxmax; idx++) {
	    		String chrpos=chrstr+"-"+idx;
	    		if(this.mapPos2Index.containsKey(chrpos)) {
	    			listPos.add(  BigDecimal.valueOf( Long.valueOf( idx )));
	    			listPosstr.add(chrpos );
	    		}
	    		//if(mapIndex2Pos.containsKey(idx)) listPos.add(  BigDecimal.valueOf( Long.valueOf( mapIndex2Pos.get(idx).split("\\-")[1] )));
	    	}

	    	labelAnnotations.setVisible(false);
	    	if( listPos.size()>200) {
	    		labelAnnotations.setValue("Too many markers to annotate. Limit selection to <200 markers.");
	    		labelAnnotations.setVisible(true);
	    		this.tabPosition.setDisabled(true);
	    		listboxPosition.setModel(new SimpleListModel(new ArrayList()));
	    		listboxMultiPosition.setModel(new SimpleListModel(new ArrayList()));
	    	} else {
	    		this.tabPosition.setDisabled(false);
	    		//updateMarkerAnnotations(chr, listPos);
	    		this.listboxPosition.setModel(new SimpleListModel(listPosstr));
	    		SimpleListModel lmodel=new SimpleListModel(listPosstr);
	    		lmodel.setMultiple(true);
	    		this.listboxMultiPosition.setModel(lmodel);
	    		
	    	}
	    	
	    }
	    
	    AppContext.debug(listboxPosition.getRows() + " positions in listboxPosition");

	    /*
    	Map<String, Double> mapZoomManhattan=new LinkedHashMap();
    	for(int idx=globalidxmin; idx<=globalidxmax; idx++) {
    		String zoompos = mapIndex2Pos.get(idx);
    		mapZoomManhattan.put(zoompos , mapPos2Values.get(zoompos));
    	}
    	chartManhattan.setModel( displayManhattan(mapZoomManhattan) );
		chartManhattan.getPlotOptions().getSeries().setLineWidth(0);
		chartManhattan.getPlotOptions().getSeries().getMarker().setWidth(3);
		*/

	    this.tabRegion.setSelected(true);
	}
	
//	
//	 @Listen("onPlotClick = #chartManhattan")
//	 public void showMessage(ChartsEvent event) {
//	        // Open an invisible popup at where the point clicked.
//			List listpos=new ArrayList();
//			listpos.add( event.getCategory().toString());
//			this.tabPosition.setSelected(true);
//			onselectListboxPositions(listpos);
//			this.listboxPosition.setSelectedIndex(0);
//
//		  /*
//	        anchor.open(chart, "at_pointer");
//	        Point point = event.getPoint();
//	        msgBox.setTitle(event.getSeries().getName());
//	        // Locate the window's position by popup.
//	        msgBox.setTop(anchor.getTop());
//	        msgBox.setLeft(anchor.getLeft());
//	        Label msg = (Label) msgBox.getFellow("msg");
//	        String formattedDate = TimeUtil.getFormattedTime((Long) event.getCategory(),
//	                "EEEEEEEEE, MMM dd, yyyy");
//	        msg.setValue(formattedDate + ":\n" + point.getY() + " visits");
//	        msgBox.setVisible(true);
//	        */
//	 }
//	    
	 @Listen("onPlotClick = #chartManhattanXY")
	 public void showMessageXY(ChartsEvent event) {
	        // Open an invisible popup at where the point clicked.
			List listpos=new ArrayList();
			//event.getPoint().getDataLabels()
			
			int pointindex=event.getPointIndex();
			String clicked = this.mapIndex2Pos.get(pointindex);
			
			long off=0; 
			int chr=Integer.valueOf(event.getSeries().getName().replace("chr0","").replace("chr", "") );
			long  val= event.getPoint().getX().longValue(); 
			if(val>345713663)
			{ chr=12; off=345713663;} else if(val>316692557){chr=11; off=316692557;}  else if(val>293485270){chr=10; off=293485270;}  else if(val>270472550){chr=9; off=270472550;}  else if(val>242029528){chr=8; off=242029528;} 
			 else if(val>212331907){chr=7; off=212331907;}  else if(val>181083120){chr=6; off=181083120;}    else if(val>151124686){chr=5; off=151124686;}   else if(val>115621992){chr=4; off=115621992;} 
			 else if(val>79208173){chr=3; off=79208173;}   else if(val>43270923){chr=2; off=43270923;}   else {chr=1; off=0; }; 
			 
			 //return 'chr ' + chr + '-' +  (val-off) + ',  -logP=' + this.y.toFixed(2);}
	 		clicked=chr+"-"+(val-off);
			
			AppContext.debug("clicked=" + clicked);
			
			listpos.add( clicked );
			this.tabPosition.setSelected(true);
			listboxPosition.setModel(new SimpleListModel(listpos));
			listboxPosition.setSelectedIndex(0);
			SimpleListModel lmodel=new SimpleListModel(listpos);
			lmodel.setMultiple(true);
			this.listboxMultiPosition.setModel(lmodel);
			listboxMultiPosition.setSelectedIndex(0);
			onselectListboxPositions(listpos);
			
			AppContext.debug(listpos + " in listboxPosition");
			

		  /*
	        anchor.open(chart, "at_pointer");
	        Point point = event.getPoint();
	        msgBox.setTitle(event.getSeries().getName());
	        // Locate the window's position by popup.
	        msgBox.setTop(anchor.getTop());
	        msgBox.setLeft(anchor.getLeft());
	        Label msg = (Label) msgBox.getFellow("msg");
	        String formattedDate = TimeUtil.getFormattedTime((Long) event.getCategory(),
	                "EEEEEEEEE, MMM dd, yyyy");
	        msg.setValue(formattedDate + ":\n" + point.getY() + " visits");
	        msgBox.setVisible(true);
	        */
	 }
	
	 //mapGenotype2Phenotype2Count,chartGenotypeHist, "Genotype count", sPhenotype);
	 //plotXYHistogram(mapSubpop2Phenotype2Count,chartSubpopHist, "Variety count", sPhenotype);
	 
	@Listen("onSelection = #chartGenotypeHist")
	public void doSelectionGenotypeHist(ChartsSelectionEvent event) {
	    // doing the zooming in function
		String str=" count"; if(checkboxNormalize.isChecked()) str=" frequency";
		
		List listVarieties=createVarlistFromSelection(event, mapGenotype2Phenotype2Count, this.listboxTrait.getSelectedItem().getLabel(), "Genotype" + str);
	    listboxVariety.setModel(new SimpleListModel(listVarieties));
	    listboxVariety.setItemRenderer( new VarietyListItemRenderer(false));
	    divVarietylist.setVisible(true);

	    
	}

	@Listen("onSelection = #chartAlleleHist")
	public void doSelectionAlleleHist(ChartsSelectionEvent event) {
	    // doing the zooming in function
		String str=" count"; if(checkboxNormalize.isChecked()) str=" frequency";
		
		List listVarieties=createVarlistFromSelection(event,mapAllele2Phenotype2Set,  mapAllele2Phenotype2Count,  this.listboxTrait.getSelectedItem().getLabel(), "Allele" + str);
	    listboxVariety.setModel(new SimpleListModel(listVarieties));
	    listboxVariety.setItemRenderer( new VarietyListItemRenderer(false));
	    divVarietylist.setVisible(true);
	    
	}

	@Listen("onSelection = #chartSubpopHist")
	public void doSelectionSubpopHist(ChartsSelectionEvent event) {
	    // doing the zooming in function
		String str=" count"; if(checkboxNormalize.isChecked()) str=" frequency";
		List listVarieties=createVarlistFromSelection(event, mapSubpop2Phenotype2Count,  this.listboxTrait.getSelectedItem().getLabel(), "Variety" + str);
	    listboxVariety.setModel(new SimpleListModel(listVarieties));
	    listboxVariety.setItemRenderer( new VarietyListItemRenderer(false));
	    divVarietylist.setVisible(true);

	}

	@Listen("onSelection = #chartGenSubpopHist")
	public void doSelectionSubpopgenHist(ChartsSelectionEvent event) {
	    // doing the zooming in function
		String str=" count"; if(checkboxNormalize.isChecked()) str=" frequency";
		List listVarieties=createVarlistFromSelection(event, mapSubpopGen2Phenotype2Count,  this.listboxTrait.getSelectedItem().getLabel(), "Variety" + str);
	    listboxVariety.setModel(new SimpleListModel(listVarieties));
	    listboxVariety.setItemRenderer( new VarietyListItemRenderer(false));
	    divVarietylist.setVisible(true);

	}
	
	
	
	
	private List createVarlistFromSelection(ChartsSelectionEvent event, Map mapGroup2X2Y, String xlabel, String ylabel) {
		return createVarlistFromSelection( event,  mapGroup2X2Y, null,  xlabel,  ylabel);
	}

	/*
	private List createVarlistFromSelection(ChartsSelectionEvent event, Map mapGroup2X2Y, String xlabel, String ylabel) {
		   	Double xmin= (Double)event.getXAxisMin();
		    Double xmax = (Double)event.getXAxisMax();
		    Integer ymin= event.getYAxisMin().intValue()-1;
		    Integer ymax = event.getYAxisMax().intValue()+1;
		    
		    String strait=this.listboxTrait.getSelectedItem().getLabel();
		    listVarieties=new ArrayList();
		    Map<BigDecimal,Variety> mapVarid2Var = variety.getMapId2Variety();
		    Iterator<Map<Object,Collection>> itMapPhen2Col = mapGroup2X2Y.values().iterator();
		    while(itMapPhen2Col.hasNext()) {
		    	Map<Object,Collection> mapPhen2Col=itMapPhen2Col.next();
		    	Iterator itPhen=mapPhen2Col.keySet().iterator();
		    	while(itPhen.hasNext()) {
		    		Comparable x= (Comparable)itPhen.next();
		    		Double dx=  ((BigDecimal)x).doubleValue();
		    		if(dx.compareTo(xmin)>=0 && dx.compareTo(xmax)<=0) {
		    			Collection cols = mapPhen2Col.get(x);
		    			if(cols.size()>=ymin && cols.size()<=ymax) {
		    				Iterator<BigDecimal> itVars=cols.iterator();
			    			while(itVars.hasNext()) {
			    				//listVarieties.add( mapVarid2Var.get(itVars.next()));
			    				Variety thisvar = mapVarid2Var.get(itVars.next());
			    				VarietyPlusPlus phevar = new VarietyPlusPlusImpl(thisvar,strait, mapVarid2Phenotype.get(  thisvar.getVarietyId() ) );
			    				//listVarieties.add( mapVarid2Var.get(itVars.next()));
			    				listVarieties.add(  phevar );

			    			}
		    			}
		    		}
		    	}
		    }
		    NumberFormat formatter = new DecimalFormat("#0.00");     
		    this.labelVarietyMsg.setValue( xlabel + " between " + formatter.format(xmin) + " and " +  formatter.format(xmax) + ", " + ylabel + " between " + ymin + " and " + ymax );
		    labelVarietyMsg.setVisible(true);
		    
		    // update table header
	 		Map<String,String> listheaders = new LinkedHashMap();
	 		listheaders.put("NAME","name"); listheaders.put("IRIS ID","irisId"); listheaders.put("SUBPOPULATION","subpopulation"); listheaders.put("COUNTRY","country");
	 				listheaders.put(strait,  "" );
		    Listhead lhd= this.listboxVariety.getListhead();
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
			
		    return listVarieties;
	}
	*/
	
	private int collectionTotal(Map<Object,Collection> mapPhen2Group) {
		int total=0;
		Iterator<Collection> itCol=mapPhen2Group.values().iterator();
		while(itCol.hasNext()) total+=itCol.next().size();
		return total;
	}
	
	private List createVarlistFromSelection(ChartsSelectionEvent event, Map mapGroup2X2Y, Map mapGroup2X2YCount, String xlabel, String ylabel) {
	   	Double xmin= (Double)event.getXAxisMin();
	    Double xmax = (Double)event.getXAxisMax();
	    /*
	    Integer ymin= event.getYAxisMin().intValue()-1;
	    Integer ymax = event.getYAxisMax().intValue()+1;
	    */

	    Number  ymin=null; 
	    Number  ymax=null; 
	    
	    boolean isNormalized=false;
	    if(this.tabPosition.isSelected()) {
	    	if(this.checkboxNormalize.isChecked()) {
	    		ymin =  event.getYAxisMin().doubleValue() -0.01;
	    		ymax = event.getYAxisMax().doubleValue()  +0.01;
	    		isNormalized=true;
	    	} else {
	    		ymin = event.getYAxisMin().intValue();
	    		ymax = event.getYAxisMax().intValue()+1;
	    	}
	    }
	    else if(this.tabPopulation.isSelected()) {
	    	if(this.checkboxNormalizePopulation.isChecked()) {
	    		ymin =  event.getYAxisMin().doubleValue() -0.01;
	    		ymax = event.getYAxisMax().doubleValue()  +0.01;
	    		isNormalized=true;
	    	} else {
	    		ymin = event.getYAxisMin().intValue();
	    		ymax = event.getYAxisMax().intValue()+1;
	    	}
	    }
	    	
	    //Integer  ymin=yminnum.intValue(); 
	    //Integer  ymax=ymaxnum.intValue(); 
	    
	    
	    String strait=this.listboxTrait.getSelectedItem().getLabel();
	    
	    listVarieties=new ArrayList();
	    Map<BigDecimal,Variety> mapVarid2Var = variety.getMapId2Variety(dataset);
	    //Iterator<Map<Object,Collection>> itMapPhen2Col = mapGroup2X2Y.values().iterator();
	    Iterator itMapGroups = mapGroup2X2Y.keySet().iterator();
	    while(itMapGroups.hasNext()) {
	    	Object groups=itMapGroups.next();
	    	Map<Object,Collection> mapPhen2Col= (Map<Object,Collection>)mapGroup2X2Y.get(groups);
	    	int total=1;
	    	if(isNormalized) total = collectionTotal(mapPhen2Col);
	    	
	    	if(mapGroup2X2YCount!=null) {
		    	
		    	Map<Object,Integer> mapPhen2Count = (Map<Object,Integer>)mapGroup2X2YCount.get(groups);
		    	Iterator itPhen=mapPhen2Col.keySet().iterator();
		    	while(itPhen.hasNext()) {
		    		Comparable x= (Comparable)itPhen.next();
		    		Integer phenvarcount = mapPhen2Count.get(x);
		    		Double dx=  ((BigDecimal)x).doubleValue();
		    		if(dx.compareTo(xmin)>=0 && dx.compareTo(xmax)<=0) {
		    			Collection cols = mapPhen2Col.get(x);
		    			//if(cols.size()>=ymin.doubleValue()*total && cols.size()<=ymax.doubleValue()*total) {
		    			//if(phenvarcount>=ymin && cols.size()<=phenvarcount) {
		    			if(phenvarcount>=ymin.doubleValue()*total && cols.size()<=phenvarcount.doubleValue()*total) {
		    				Iterator<BigDecimal> itVars=cols.iterator();
			    			while(itVars.hasNext()) {
			    				Variety thisvar = mapVarid2Var.get(itVars.next());
			    				VarietyPlusPlus phevar = new VarietyPlusPlusImpl(thisvar,strait, mapVarid2Phenotype.get(  thisvar.getVarietyId() ) );
			    				//listVarieties.add( mapVarid2Var.get(itVars.next()));
			    				phevar.addValue("Genotype", this.mapVarid2Genotype.get( thisvar.getVarietyId().longValue() ) );
			    				listVarieties.add(  phevar );
			    			}
		    			}
		    		}
		    	}
	    	} else {
	    	 	Iterator itPhen=mapPhen2Col.keySet().iterator();
		    	while(itPhen.hasNext()) {
		    		Comparable x= (Comparable)itPhen.next();
		    		Double dx=  ((BigDecimal)x).doubleValue();
		    		if(dx.compareTo(xmin)>=0 && dx.compareTo(xmax)<=0) {
		    			Collection cols = mapPhen2Col.get(x);
		    			//if(cols.size()>=ymin && cols.size()<=ymax) {
		    			if(cols.size()>=ymin.doubleValue()*total && cols.size()<=ymax.doubleValue()*total) {
		    				Iterator<BigDecimal> itVars=cols.iterator();
			    			while(itVars.hasNext()) {
			    				//listVarieties.add( mapVarid2Var.get(itVars.next()));
			    				Variety thisvar = mapVarid2Var.get(itVars.next());
			    				VarietyPlusPlus phevar = new VarietyPlusPlusImpl(thisvar,strait, mapVarid2Phenotype.get(  thisvar.getVarietyId() ) );
			    				//listVarieties.add( mapVarid2Var.get(itVars.next()));
			    				phevar.addValue("Genotype", this.mapVarid2Genotype.get( thisvar.getVarietyId().longValue() ) );
			    				listVarieties.add(  phevar );

			    			}
		    			}
		    		}
		    	}
	    	}
	    }
	    
	    NumberFormat formatter = new DecimalFormat("#0.00");     
	    this.labelVarietyMsg.setValue( xlabel + " between " + formatter.format(xmin) + " and " +  formatter.format(xmax) + ", " + ylabel + " between " + formatter.format(ymin) + " and " + formatter.format(ymax) );
	    labelVarietyMsg.setVisible(true);
	    listheaderPhenotype.setLabel(xlabel);
	    
	    // update table header
 		Map<String,String> listheaders = new LinkedHashMap();
 		listheaders.put("NAME","name"); listheaders.put("IRIS ID","irisId"); listheaders.put("SUBPOPULATION","subpopulation"); listheaders.put("COUNTRY","country");
 				listheaders.put(strait,  "" ); listheaders.put("Genotype",  "" );
	    Listhead lhd= this.listboxVariety.getListhead();
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
		
	    return listVarieties;
	}


	/*
	  @Listen("onClick = #buttonAddToList")
	    public void addToList() {
	    	
	    	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	    	
	    	if(this.listboxVariety.getItems().size()==0) {
	    		Messagebox.show( "EMPTY VARIETY LIST");
	    		return;
	    	}
	    	if(txtboxListnameSnp.getValue().isEmpty()) {
	    		Messagebox.show( "PROVIDE LIST NAME");
	    		return;
	    	}
	    	Set setVarieties = new LinkedHashSet();
	    	workspace.addVarietyList( txtboxListnameSnp.getValue().trim(), new LinkedHashSet(listVarieties), true );
	    	txtboxListnameSnp.setValue("");
	    }
	    */
	    
	  
	private DefaultCategoryModel readManhattan(String trait, String subpop, Double minlogP, String region) {
		gwas=(GwasFacade)AppContext.checkBean(gwas,"GwasFacade");
		AppContext.resetTimer("querying p values");
		mapPos2Values =  gwas.getPosstr2MinusLogP(trait, subpop, minlogP,region);
		if(mapPos2Values==null) {
			Messagebox.show("No GWAS run for trait: " + trait +", population: " + subpop );
			return null;
		}
		AppContext.resetTimer("displaying p values");
		mapPos2Index = new TreeMap();
		mapIndex2Pos = new TreeMap();
		int idx=0;
		Iterator<String> itPos=mapPos2Values.keySet().iterator();
		while(itPos.hasNext()) {
			String pos= itPos.next();
			mapPos2Index.put(pos, idx);
			mapIndex2Pos.put(idx,  pos);
			idx++;
		}
		//displayManhattanXY(mapPos2Values); 
		return displayManhattan(mapPos2Values);
	}

	private void readManhattanXY(String trait, String subpop, Double minlogP, Set snplist, String region) {
		gwas=(GwasFacade)AppContext.checkBean(gwas,"GwasFacade");
		
		AppContext.resetTimer("querying p values");
		mapPos2Values =  gwas.getPosstr2MinusLogP(trait, subpop, minlogP, region);
		if(mapPos2Values==null) {
			Messagebox.show("No GWAS run for trait: " + trait +", population: " + subpop );
			return;
		}

		AppContext.resetTimer("displaying p values");
		
		Set  strsnplist=new HashSet();
		if(snplist!=null) {
			Iterator<Position> itPos=snplist.iterator();
			while(itPos.hasNext()) {
				Position spos=itPos.next();
				strsnplist.add(spos.getChr()+"-"+spos.getPosition());
			}
		}
		
		mapPos2Index = new TreeMap();
		mapIndex2Pos = new TreeMap();
		int idx=0;
		Iterator<String> itPos=mapPos2Values.keySet().iterator();
		while(itPos.hasNext()) {
			String pos= itPos.next();
			if(snplist!=null && !strsnplist.contains(pos)) continue;
			mapPos2Index.put(pos, idx);
			mapIndex2Pos.put(idx,  pos);
			idx++;
		}
		displayManhattanXY(mapPos2Values);
		
		String qqfilename= gwas.getQQPlotFile(trait, subpop);
		if(qqfilename!=null && !qqfilename.isEmpty()) {
			AppContext.debug("displaying "  + qqfilename);
			imageQQ.setSrc(qqfilename);
			imageQQ.invalidate();
			imageQQ.setVisible(true);
		}
		else {
			imageQQ.setSrc(null);
			imageQQ.setVisible(false);
		}
		
		AppContext.resetTimer("displaying p values.. done");
	}
	
	private void displayManhattanXY(Map<String, Double> mapMyPos2Values) {
/*
	0
	43270923
	79208173
	115621992
	151124686
	181083120
	212331907
	242029528
	270472550
	293485270
	316692557
	345713663
	373245519
	*/
		
	

		chartManhattanXY.setVisible(false);
		Iterator<String> itPos =  mapMyPos2Values.keySet().iterator();
		String prevchr="";
		int seriescnt=0;
		Series series=null;
		chartManhattanXY.setModel(new DefaultXYModel());
		chartManhattanXY.getPlotOptions().getSeries().getMarker().setWidth(3);
		
		StringBuffer buffjs=new StringBuffer();
		buffjs.append("function() { var chr=0; var off=0; var val=this.value; if(val>345713663) ");
		buffjs.append(" { chr=12; off=345713663;} else if(val>316692557){chr=11; off=316692557;}  else if(val>293485270){chr=10; off=293485270;}  else if(val>270472550){chr=9; off=270472550;}  else if(val>242029528){chr=8; off=242029528;} " );
		buffjs.append(" else if(val>212331907){chr=7; off=212331907;}  else if(val>181083120){chr=6; off=181083120;}    else if(val>151124686){chr=5; off=151124686;}   else if(val>115621992){chr=4; off=115621992;} " );
		buffjs.append("   else if(val>79208173){chr=3; off=79208173;}   else if(val>43270923){chr=2; off=43270923;}   else {chr=1; off=0; }; var str1=\"\"; return str1.concat(chr).concat(\"-\").concat( val-off);}");
		chartManhattanXY.getXAxis().getLabels().setFormatter(new JavaScriptValue(buffjs.toString()));

		
		
        ScatterPlotOptions plotOptions = chartManhattanXY.getPlotOptions().getScatter();
        Marker marker = plotOptions.getMarker();
        //marker.
        //marker.setRadius(5);
        marker.getStates().getHover().setEnabled(true);
        //marker.getStates().getHover().setLineColor("rgb(100,100,100)");
        plotOptions.getStates().getHover().getMarker().setEnabled(false);
        //plotOptions.getTooltip().setHeaderFormat("<b>{series.name}</b><br>");
        //plotOptions.getTooltip().setPointFormat("{point.x} cm, {point.y} kg");
        //plotOptions.getTooltip().setPointFormat("{point.x}, {point.y}");
        //plotOptions.setTooltip(tooltip);
        
        //chartManhattanXY.setTooltip(tooltip);

        //marker.
        
        //TooltipPlotOptions tooltip = new TooltipPlotOptions();
        //chartManhattanXY.getPlotOptions().getScatter().setTooltip(tooltip); .sett .setTooltip(tooltip);
        
        
        
		
		//chartManhattanXY.getXAxis().getLabels().setFormatter(new JavaScriptValue("function() {  var str1=\"Hello \";  return  str1.concat(this.value); }"));
		//chartManhattanXY.getXAxis().getLabels().setFormatter(new JavaScriptValue("{  return this.value); }"));
		while(itPos.hasNext()) {
			String pos=itPos.next();
			Double y=mapMyPos2Values.get(pos);
			String[] chrpos = pos.split("\\-");
			if(!prevchr.equals(chrpos[0])) {
				if(seriescnt==0) series = this.chartManhattanXY.getSeries();
				else series = this.chartManhattanXY.getSeries(seriescnt);
				if(chrpos[0].length()==1)
					series.setName("chr0" + chrpos[0]);
				else 
					series.setName("chr" + chrpos[0]);
			    AppContext.debug("creating plotxy  chr" + chrpos[0]);
			    prevchr=chrpos[0];

			    
			    //Tooltip tt=new Tooltip();
			    mapChr2Offset.get(chrpos[0]);
			    //tt.setFormatter(new JavaScriptValue("function() { return point.x; }"));
			    JavaScriptValue formatter = new JavaScriptValue("function() {" +
			        " var xyArr=[]; " +
			        " $.each(this.points,function(){ " +
			        "    xyArr.push('chr: ' + this.series.name + ', ' +'bp: ' + this.x + ', -logP: ' + this.y);" +
			        " }); " +
			        " return xyArr.join('<br/>'); }");
			    series.getTooltip().setFormatter(formatter);
			    

			   // AppContext.debug(" series.getTooltip().toJSONString()=" +  series.getTooltip().toJSONString());
			    seriescnt++;
			}
			series.addPoint( Double.valueOf( mapChr2Offset.get(chrpos[0]) + Integer.valueOf(chrpos[1]) ) ,  y);
		}


		/*
	    JavaScriptValue formatterchart = new JavaScriptValue("function() {" +
		        " var xyArr=[]; " +
		        " $.each(this.points,function(){ " +
		        "    xyArr.push('chr: ' + this.series.name + ', ' +'bp: ' + this.x + ', -logP: ' + this.y);" +
		        " }); " +
		        " return xyArr.join('<br/>'); }");

	    //chartManhattanXY.getTooltip().setFormatter(formatterchart);
	    
	    //chartManhattanXY.getPlotData().getTooltip().setFormatter(formatterchart);
		chartManhattanXY.getPlotOptions().getScatter().getTooltip().setFormat("chr: this.series.name  bp: this.x -logP: this.y");
		*/
		
		/*
	    Tooltip ttchart=new Tooltip();
	    ttchart.setFormatter(new JavaScriptValue("function() {" +
	        " var xyArr=[]; " +
	        " $.each(this.points,function(){ " +
	        "    xyArr.push('chr: ' + this.series.name + ', ' +'pos: ' + this.x + ', -logP: ' +this.y);" +
	        " }); " +
	        " return xyArr.join('<br/>'); }"));
	    chartManhattanXY.setTooltip(ttchart);
	    //chartManhattanXY.getPlotData().setTooltip(ttchart);
	    */
	    
	    
		StringBuffer buffjsmarker=new StringBuffer();
		buffjsmarker.append("function() { var chr=0; var off=0; var val=this.x; if(val>345713663) ");
		buffjsmarker.append(" { chr=12; off=345713663;} else if(val>316692557){chr=11; off=316692557;}  else if(val>293485270){chr=10; off=293485270;}  else if(val>270472550){chr=9; off=270472550;}  else if(val>242029528){chr=8; off=242029528;} " );
		buffjsmarker.append(" else if(val>212331907){chr=7; off=212331907;}  else if(val>181083120){chr=6; off=181083120;}    else if(val>151124686){chr=5; off=151124686;}   else if(val>115621992){chr=4; off=115621992;} " );
		buffjsmarker.append("   else if(val>79208173){chr=3; off=79208173;}   else if(val>43270923){chr=2; off=43270923;}   else {chr=1; off=0; };  return 'chr ' + chr + '-' +  (val-off) + ',  -logP=' + this.y.toFixed(2);}");


		/*
		chartManhattanXY .options.formatter = function() {
		        var xyArr=[];
		        $.each(this.points,function(){
		            xyArr.push('Series: ' + this.series.name + ', ' +'X: ' + this.x + ', Y: ' +this.y);
		        });
		        return xyArr.join('<br/>');
		    }
		  */
		
		Tooltip ttc=new Tooltip();
	    //ttc.setFormatter(new JavaScriptValue("function() { return this.x + ',' + this.y; }"));
		ttc.setFormatter(new JavaScriptValue(buffjsmarker.toString()));
		chartManhattanXY.setTooltip(ttc);

		/*

	    AppContext.debug(" chartManhattanXY.getTooltip().toJSONString()=" +  chartManhattanXY.getTooltip().toJSONString());
	    AppContext.debug(" chartManhattanXY.getPlotOptions().getScatter().getTooltip().getFormat()=" + chartManhattanXY.getPlotOptions().getScatter().getTooltip().getFormat());
	    
		
		AppContext.debug("getFormat=" +  chartManhattanXY.getXAxis().getLabels().getFormat());
		AppContext.debug("getFormatter JSON=" +  chartManhattanXY.getXAxis().getLabels().getFormatter().toJSONString());
		AppContext.debug("getFormatter String=" +  chartManhattanXY.getXAxis().getLabels().getFormatter().toString());
		*/
		
		if(mapPos2Index.size()==0)
			labelManhattan.setValue(this.mapPos2Index.size() + " markers, decrease Minimum -logP.");
		else labelManhattan.setValue(this.mapPos2Index.size() + " markers, select a narrow region to zoom, show JBrowse tracks and marker annotations. Click a marker to display allele distributions and varieties.");

		
		chartManhattanXY.setVisible(true);
		
	}
	
	private DefaultCategoryModel displayManhattan(Map<String, Double> mapMyPos2Values) {
	
		DefaultCategoryModel model= new DefaultCategoryModel();
		int skippoints=1;
		if(mapMyPos2Values.size()>nMarkers) {
			skippoints=mapMyPos2Values.size()/nMarkers+1;
		}
		
		Iterator<String> itPos=mapMyPos2Values.keySet().iterator();
		
		mapViewPos2Index = new HashMap();
		mapViewIndex2Pos = new HashMap();
		
		int idx=0;
		int idxview=0;
		double idxmax=0;
		String posmax=null;
		while(itPos.hasNext()) {
			
			String pos= itPos.next();
			
			double curval=mapMyPos2Values.get(pos).doubleValue();
			if(idxmax<curval) {
				idxmax=	curval;
				posmax=pos;
			}
			
			if( (idx % skippoints)==0) {
				mapViewPos2Index.put(posmax, idxview);
				mapViewIndex2Pos.put(idxview, posmax);
				
				int chr=Integer.parseInt(posmax.split("\\-")[0]);
				String chrstr="chr0" + chr; 
				if(chr>9) chrstr="chr" + chr; 
				model.setValue( chrstr, posmax, idxmax);
				idxview++;
				
				posmax=null;
				idxmax=0;
			}
			
			idx++;

		}
		AppContext.debug(mapMyPos2Values.size() + " pvalues,  " + mapViewPos2Index.size() + " displayed" + " , skippoints=" + skippoints);
		
		List listPos=new ArrayList();
		listPos.add("");
		listPos.addAll(new TreeSet(mapMyPos2Values.keySet()));
		listboxPosition.setModel( new SimpleListModel(listPos) );
		
		SimpleListModel listmodel=new SimpleListModel(listPos) ;
		listmodel.setMultiple(true);
		listboxMultiPosition.setModel(listmodel );
		
		return model;
		
	}
	
	@Listen("onClick =#buttonUpdateAnnotations")
	public void onclickupdateannots() {
		
	}
	
	private void updateMarkerAnnotations(String chr, Collection colPos) {
	
		genomics=(GenomicsFacade)AppContext.checkBean(genomics,"GenomicsFacade");
		//Set annotations=new LinkedHashSet();
		setAnnotations = new LinkedHashSet();
		
		Set excludeAnnotation=new HashSet();
		if(!checkboxIncludeInteractions.isChecked()) {
			excludeAnnotation.add(MarkerAnnotation.GENE_INT_PRINEXPT);
			excludeAnnotation.add(MarkerAnnotation.GENE_INT_PRINPRED);
			excludeAnnotation.add(MarkerAnnotation.GENE_INT_RICENETV1);
			excludeAnnotation.add(MarkerAnnotation.GENE_INT_RICENETV2);
		}

		if(!checkboxIncludePromoters.isChecked()) {
			excludeAnnotation.add(MarkerAnnotation.PROM_FGENESH1K);
			excludeAnnotation.add(MarkerAnnotation.PROM_PLANTPROMDB);
		}
		
		if(!checkboxIncludeGO.isChecked()) {
			excludeAnnotation.add(MarkerAnnotation.GENE_GO_BP);
			excludeAnnotation.add(MarkerAnnotation.GENE_GO_MF);
			excludeAnnotation.add(MarkerAnnotation.GENE_GO_CC);
		}
		
		if(!checkboxIncludePOTO.isChecked()) {
			excludeAnnotation.add(MarkerAnnotation.GENE_PO);
			excludeAnnotation.add(MarkerAnnotation.GENE_PO_DEVT);
			excludeAnnotation.add(MarkerAnnotation.GENE_TO);
			excludeAnnotation.add(MarkerAnnotation.GENE_QTARO);
		}

		if(!checkboxIncludeQTL.isChecked()) {
			excludeAnnotation.add(MarkerAnnotation.QTL_GRAMENE);
			excludeAnnotation.add(MarkerAnnotation.QTL_QTARO);
		}
		
		
		markerresult = genomics.getMarkerAnnotsByContigPositions(chr, colPos, Organism.REFERENCE_NIPPONBARE,   0, GenomicsFacade.GENEMODEL_IRIC, setAnnotations, 10,excludeAnnotation);
		this.searchbyMySnpListQtl();
		
		/*
		gridMarker.setRowRenderer(new MarkerGridRenderer());
		gridMarker.setModel( new SimpleListModel( markerresult ));
		gridMarker.setVisible(true);
		*/
			
	}
		
	@Listen("onClick = #checkboxJbrowse")
	public void onclickJbrowse() {
		if(checkboxJbrowse.isChecked() && urljbrowse!=null)	{
			if(!urljbrowse.contains(AppContext.getJbrowseDir())) return;
			AppContext.debug("displaying " + urljbrowse);
			iframeJbrowse.setSrc(urljbrowse);
			msgJbrowse.setVisible(true);
			iframeJbrowse.setVisible(true);
			this.tabRegion.setSelected(true);
		} else {
			msgJbrowse.setVisible(false);
			iframeJbrowse.setVisible(false);
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
		
		String org = Organism.REFERENCE_NIPPONBARE; 
		
		if(org.equals(AppContext.getDefaultOrganism())) 
			chrpad = "loc=" + chr;  // + AppContext.getJbrowseContigSuffix(); //"|msu7";
		else if(org.equals("IR64-21"))
			chrpad = "data=ir6421v1&loc="+  chr + "|ir6421v1";
		else if(org.equals("93-11"))
			chrpad =  "data=9311v1&loc=" + chr + "|9311v1";
		else if(org.equals("DJ123"))
			chrpad = "data=dj123v1&loc=" + chr + "|dj123v1";
		else if(org.equals("Kasalath"))
			chrpad = "data=kasv1&loc=" + chr + "|kasv1";
		
		String displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%222000%22";
		
		String  rendertype="";
			
		if(locus.isEmpty())		
			msgJbrowse.setValue("Chromosome " + chr + " [" + start + ".." + end + "]");
		else
			msgJbrowse.setValue(locus + "  Chromosome " + chr + " [" + start + ".." + end + "]");
		
		iframeJbrowse.setScrolling("yes");
		iframeJbrowse.setAlign("left");		

		//String urltemplate = "..%2F..%2F" + AppContext.getHostDirectory() + "%2Ftmp%2F" + "GFF_FILE";
		String urltemplate = "..%2F..%2Ftemp%2F" + "GFF_FILE";
		


		String snp3kcore="";
		/*
		if(checkboxSNP.isChecked()) { 
			if(checkboxCoreSNP.isChecked()) snp3kcore="msu7coresnpsv2%2C";
		}
		if(checkboxIndel.isChecked())
			snp3kcore += "msu7indelsv2%2C";
			*/
		
				// for all varieties
				rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatchSynIndels%22";
				
				String showTracks =  AppContext.getJBrowseDefaulttracks(AppContext.getDefaultDataset()) ; //+ "%2C" + snp3kcore;
				
				if(org.equals(AppContext.getDefaultOrganism())) 
						{}
				/*
				else if(org.equals("IR64-21"))
					showTracks = "IR64-21%20DNA,ir6421v1rengff,alignir6421v1vsmsu7%2C";
				else if(org.equals("93-11"))
					showTracks  = "9311%20DNA%2C9311v1rengff,";
				else if(org.equals("DJ123"))
					showTracks = "DJ123%20DNA%2C,dj123v1rengff,aligndj123v1vsmsu7%2C";
				else if(org.equals("Kasalath"))
						showTracks = "Kasalath%20DNA%2Ckasrapv1rengff,";
						*/
				else if(org.equals("IR64-21"))
					showTracks = "DNA,ir6421v1gffv2,";
				else if(org.equals("93-11"))
					showTracks  = "DNA%2C9311v1gffv2,";
				else if(org.equals("DJ123"))
					showTracks = "DNA%2C,dj123v1gffv2,";
				else if(org.equals("Kasalath"))
						showTracks = "DNA%2Ckasv1gffrapv2,";
				
				Map<String,String> mapTrack=new HashMap();
				mapTrack.put("Culm diameter (mm) of basal internode at repro.","cudi_repro");
				mapTrack.put("Culm length (cm) at reproductive","cult_repro");
				mapTrack.put("Culm number (count) at reproductive - cultivated","cuno_repro");
				mapTrack.put("Grain length (mm)","grlt");
				mapTrack.put("Grain Width(mm)","grwd");
				mapTrack.put("100-grain weight (gm) - cultivated","grwt100");
				mapTrack.put("Heading (days to 80% fully headed) - cultivated","hdg_80head");
				mapTrack.put("Ligule length (mm) - cultivated","liglt");
				mapTrack.put("Leaf length (cm) - cultivated","llt");
				mapTrack.put("Leaf width (cm) - cultivated","lwd");
				mapTrack.put("Panicle length (cm) at post-harvest","plt_post");
				mapTrack.put("Seedling height (cm)","sdht");

				
				String strait = listboxTrait.getSelectedItem().getLabel().trim();
				if(!strait.isEmpty()) {
					showTracks+=",gwas3k_" + mapTrack.get(strait) + "_emmax_all_density,gwas3k_" + mapTrack.get(strait) + "_emmax_all_xy";
				}
				
				showTracks=showTracks.replace(",msu7indelsv2","");
				
				//String urljbrowse= "172.29.4.215:8080" + /*  AppContext.getHostname() +  */ "/" + AppContext.getJbrowseDir() + "/?" + chrpad + ":" + start + ".." + end +   "&tracks=" +  AppContext.getJBrowseDefaulttracks();
				urljbrowse=  AppContext.getJbrowseDir() + "/?" + chrpad + ":" + start + ".." + end +   "&tracks=" + showTracks; // AppContext.getJBrowseDefaulttracks();
			
			if(checkboxJbrowse.isChecked())	{			
				AppContext.debug("displaying " + urljbrowse);
				iframeJbrowse.setSrc(urljbrowse);
				msgJbrowse.setVisible(true);
				iframeJbrowse.setVisible(true);
			} else {
				msgJbrowse.setVisible(false);
				iframeJbrowse.setVisible(false);
			}
			
	}
	
	@Listen("onClick =#tabPosition")
	public void onclickTabposition() {
		//tabPosition
		/*
		
	    int min= chartManhattanXY.getXAxis(0).getMin().intValue();
	    int max = chartManhattanXY.getXAxis(0).getMax().intValue();
	    
	    
	    
	    AppContext.debug( "min=" + min + "; max=" + max);
	    
	    List listPos=new ArrayList();
	    for(int idx=min; idx<=max; idx++) {
	    	listPos.add( mapIndex2Pos.get(idx) );
	    }
	    listboxPosition.setModel( new SimpleListModel(listPos) );
	    */
	    
	}
	
	@Listen("onClick =#tabPopulation")
	public void onclickTabpopulation() {
		Collection varlist=null;
		
		if( listboxVarietylist.getSelectedIndex()>0 || this.listboxTrait.getSelectedIndex()>0) {}
		else return;
		
		String sPhenotype=listboxTrait.getSelectedItem().getLabel();
		if(listboxVarietylist.getSelectedIndex()>0)
		{
			sPhenotype=listboxVarietylist.getSelectedItem().getLabel();
			varlist = workspace.getVarieties(sPhenotype);
		}
		
		mapSubpopGen2Phenotype2Count=null;
		mapSubpop2Phenotype2Count=null;
		mapVarid2Phenotype=null;
		
		Map[] mapPopHists = gwas.createSubpopDistributions( sPhenotype , varlist, checkboxNormalizePopulation.isChecked());
		
		mapSubpopGen2Phenotype2Count=mapPopHists[1];
		mapSubpop2Phenotype2Count=mapPopHists[0];
		mapVarid2Phenotype=mapPopHists[4];
		
		if(checkboxNormalizePopulation.isChecked()) {
			 plotXYHistogram(mapPopHists[2], this.chartSubpopHist, "Subpopulation frequency", sPhenotype, false);
			 plotXYHistogram(mapPopHists[3], this.chartGenSubpopHist, "Variety group frequency" , sPhenotype, false);
		
		} else {
			 plotXYHistogram(mapPopHists[0],chartSubpopHist, "Subpopulation count", sPhenotype, false);
			 plotXYHistogram(mapPopHists[1],chartGenSubpopHist, "Variety group count", sPhenotype, false);
		}
	}
	
	
	
	@Listen("onSelect =#listboxPosition")
	public void onselectListboxPosition() {
		String pos=listboxPosition.getSelectedItem().getLabel();
		List listpos=new ArrayList();
		listpos.add(pos);
		onselectListboxPositions(listpos);
	}
	
	
	@Listen("onClick =#buttonDisplayHist")
	public void onclickHist() {
		Iterator itsetsel = listboxMultiPosition.getSelectedItems().iterator();
		List listpos=new ArrayList();
		while(itsetsel.hasNext()) {
			Listitem item=(Listitem)itsetsel.next();
			listpos.add(item.getLabel());
		}
		onselectListboxPositions(listpos);
	}
	
	@Listen("onSelect =#listboxGenotype1")
	public void onselectGenotype1() {
		if(listboxGenotype1.getSelectedIndex()==0 || listboxGenotype2.getSelectedIndex()==0 ||  listboxGenotype2.getSelectedItem()==null) return;
		onselectSmoothGenotype();
	}
	
	@Listen("onSelect =#listboxGenotype2")
	public void onselectGenotype2() {
		if(listboxGenotype1.getSelectedIndex()==0 || listboxGenotype2.getSelectedIndex()==0 || listboxGenotype1.getSelectedItem()==null) return;
		onselectSmoothGenotype();
	}
	
	private void onselectSmoothGenotype() {
		Object al1=listboxGenotype1.getSelectedItem().getValue();
		Object al2=listboxGenotype2.getSelectedItem().getValue();
		Map mapBins[]=gwas.createBin(mapGenotype2Phenotype2Count.get(al1)  , mapGenotype2Phenotype2Count.get(al2), intboxBins.getValue());
		Map mapBinsFreq[]=gwas.createBin(mapGenotype2Phenotype2Frequency.get(al1)  , mapGenotype2Phenotype2Frequency.get(al2), intboxBins.getValue());
		//{mapBinValues, norm1, norm2};
		
		Map mapSmoothed=new LinkedHashMap();
		mapSmoothed.put(al1 , mapBins[1]);
		mapSmoothed.put(al2 , mapBins[2]);
		mapSmoothed.put(al1 + " freq" , mapBinsFreq[1]);
		mapSmoothed.put(al2 + " freq", mapBinsFreq[2]);
		
		Map<Integer,Float[]> mapBinsvals = mapBins[0];
		Iterator<Integer> itBin=mapBinsvals.keySet().iterator();
		AppContext.debug("bin ranges");
		while(itBin.hasNext()) {
			Integer bin=itBin.next();
			Float binvals[] = (Float[])mapBinsvals.get(bin);
			AppContext.debug(bin + " "  + binvals[0] + ", " +  binvals[1]);	
		}
		
		
		plotXYHistogram(mapSmoothed, chartSmoothHist, "Bin Smoothed Genotype frequency" , "BIN -" + listboxTrait.getSelectedItem().getLabel(), false);

		Map mapSmoothedPhen=new LinkedHashMap();
		mapSmoothedPhen.put(al1 + " count" , mapGenotype2Phenotype2Count.get(al1) );
		mapSmoothedPhen.put(al2 + " count", mapGenotype2Phenotype2Count.get(al2));
		mapSmoothedPhen.put(al1 + " freq" , mapGenotype2Phenotype2Frequency.get(al1) );
		mapSmoothedPhen.put(al2 + " freq", mapGenotype2Phenotype2Frequency.get(al2));

		plotXYHistogram(mapSmoothedPhen, chartSmoothPhen, "XY Genotype" , this.listboxTrait.getSelectedItem().getLabel(), false);
		
		float binwidth=mapBinsvals.get(0)[1]-mapBinsvals.get(0)[0];
		Float[] binsStats = gwas.calculateOverlapArea( mapBins[1],  mapBins[2]);
		AppContext.debug("bin stats: overlap area=" + binsStats[0]*binwidth + "  mean " + al1 +"=" +  binsStats[1]*binwidth +  "  mean " + al2 + "=" +  binsStats[2]*binwidth + " distinctarea=" + binsStats[3]*binwidth );
		
		
	}
	
	private List selectRandomPos(List poslist, int npos) {
		Random rand = new Random();
		
		if(poslist.isEmpty()) return null;
		Set posSelset=new TreeSet();
		while(posSelset.isEmpty()) {
			for(int i=0; i<npos; i++) {
				posSelset.add( poslist.get(rand.nextInt(poslist.size())));
			}
		}
		List randList=new ArrayList();
		randList.addAll( posSelset );
		return randList;
	}

	@Listen("onSelect =#listboxMLIteration")
	public void onselectIteration() {
		Integer selidx=listboxMLIteration.getSelectedIndex();
		showIteration( (Map[])listMapalleleScores[0].get(selidx), (Stack[])listMapalleleScores[1].get(selidx));
	}
	public void showIteration(Map[] mapAlleleHists, Stack[] bestscores) {
		
		//Map[] mapAlleleHists=listMapAlleleHists.get(idx);
		Set genotypes= new TreeSet();
		genotypes.addAll(mapAlleleHists[5].values());
		List listGen=new ArrayList();
		listGen.addAll(genotypes);
		this.listboxGenotype1.setModel(new SimpleListModel(listGen));
		this.listboxGenotype2.setModel(new SimpleListModel(listGen));
		mapGenotype2Phenotype2Count=mapAlleleHists[1];
		mapGenotype2Phenotype2Frequency=mapAlleleHists[3];
		mapAllele2Phenotype2Count=mapAlleleHists[0];
		mapVarid2Phenotype=mapAlleleHists[4];
		mapVarid2Genotype=mapAlleleHists[5];
		mapAllele2Phenotype2Set=mapAlleleHists[6];

		//List<Stack[]> listbestscores=listListbestscores.get(idx);
		
			List listTops=new ArrayList();
			for(int iscore=0; iscore<bestscores.length; iscore++) {
				ScoreFeature score= (ScoreFeature)bestscores[iscore].peek();
				listTops.add(score);
			}
			listboxBest.setModel(new SimpleListModel(listTops));
			

	}
	
	@Listen("onClick =#buttonMinOverlap")
	public void onclickMinoverlap() {
		
		List listpos=new ArrayList();
		for(int i=0; i<this.listboxMultiPosition.getModel().getSize(); i++) {
			String poschr[]=  ((String)listboxMultiPosition.getModel().getElementAt(i)).split("-");
			listpos.add( new PositionImpl( (poschr[0].length()>1?"chr"+ poschr[0]:"chr0" +  poschr[0]), BigDecimal.valueOf(Long.valueOf(poschr[1]))) );
		}
		
		List randPos = selectRandomPos(listpos, intboxMaxFeatures.getValue());
		//Map mapAlleleHists[] = new HashMap[7];
		listMapalleleScores = gwas.getMinArea("any", randPos, this.listboxTrait.getSelectedItem().getLabel(), this.intboxBins.getValue(),
				this.intboxMinCountPercent.getValue(), intboxMaxFeatures.getValue());

		showIteration( (Map[])listMapalleleScores[0].get(0), (Stack[])listMapalleleScores[1].get(0));
		/*
		
		Map[] mapAlleleHists=listMapAlleleHists.get(listMapAlleleHists.size()-1);
		//ScoreFeature 
		
		Set genotypes= new TreeSet();
		genotypes.addAll(mapAlleleHists[5].values());
		List listGen=new ArrayList();
		listGen.addAll(genotypes);
		this.listboxGenotype1.setModel(new SimpleListModel(listGen));
		this.listboxGenotype2.setModel(new SimpleListModel(listGen));
		mapGenotype2Phenotype2Count=mapAlleleHists[1];
		mapGenotype2Phenotype2Frequency=mapAlleleHists[3];
		mapAllele2Phenotype2Count=mapAlleleHists[0];
		mapVarid2Phenotype=mapAlleleHists[4];
		mapVarid2Genotype=mapAlleleHists[5];
		mapAllele2Phenotype2Set=mapAlleleHists[6];
		
		List listIters=new ArrayList();
		Map<String,List> mapTops = new HashMap();
		Iterator<Stack[]> itStacks = listbestscores.iterator();
		int iter=0;
		while(itStacks.hasNext()) {
			Stack[] bestscores=itStacks.next();
			List listTops=new ArrayList();
			for(int iscore=0; iscore<bestscores.length; iscore++) {
				ScoreFeature score= (ScoreFeature)bestscores[iscore].peek();
				listTops.add(score);
				Stack stack=bestscores[iscore];
				List listScores=new ArrayList();
				
				AppContext.debug("iter=" + iter + " top scores for " + score.getName());
				for(int i=0; i<stack.size(); i++) {
					ScoreFeature scorei= (ScoreFeature)stack.get(i);
					listScores.add( scorei.getScore());
					AppContext.debug(i + " " + scorei.getScore() + "  " +scorei.getFeature1() + "  " + scorei.getFeature2());
				}
				List alllistscore=mapTops.get(score.getName());
				if(alllistscore==null) {
					alllistscore=new ArrayList();
					mapTops.put(score.getName(), alllistscore);
				}
				alllistscore.addAll(listScores);
			}
			listboxBest.setModel(new SimpleListModel(listTops));
			listboxBest.setSelectedIndex(0);
			listIters.add(iter);
			iter++;
		}
		*/
		

		List listIters=new ArrayList();
		Map<String,List> mapTops = new HashMap();
		Iterator itStacks = listMapalleleScores[1].iterator();
		int iter=0;
		while(itStacks.hasNext()) {
			Stack[] bestscores=(Stack[])itStacks.next();
			for(int iscore=0; iscore<bestscores.length; iscore++) {
				ScoreFeature score= (ScoreFeature)bestscores[iscore].peek();
				Stack stack=bestscores[iscore];
				List listScores=new ArrayList();
				AppContext.debug("iter=" + iter + " top scores for " + score.getName());
				for(int i=0; i<stack.size(); i++) {
					ScoreFeature scorei= (ScoreFeature)stack.get(i);
					listScores.add( scorei.getScore());
					AppContext.debug(i + " " + scorei.getScore() + "  " +scorei.getFeature1() + "  " + scorei.getFeature2());
				}
				List alllistscore=mapTops.get(score.getName());
				if(alllistscore==null) {
					alllistscore=new ArrayList();
					mapTops.put(score.getName(), alllistscore);
				}
				alllistscore.addAll(listScores);
			}
			listIters.add(iter);
			iter++;
		}
		listboxMLIteration.setModel(new SimpleListModel(listIters));
		// plot top socres
		listboxMLIteration.setSelectedIndex(listIters.size()-1);
		plotLine(mapTops, this.chartsTopScores, "score","rank");
		
	}

	@Listen("onSelect =#listboxBest")
	public void onselectBest() {
		if(listboxBest.getSelectedIndex()<3) {
			ScoreFeature score = listboxBest.getSelectedItem().getValue();
			int idx1=-1;
			int idx2=-1;
			for(int igen=0; igen<listboxGenotype1.getModel().getSize(); igen++) {
				String gen1= (String)listboxGenotype1.getModel().getElementAt(igen);
				if(gen1.equals(score.getFeature1())) idx1=igen; 
				else if(gen1.equals(score.getFeature2())) idx2=igen;
				if(idx1>-1 && idx2>-1) break;
			}
			listboxGenotype1.setSelectedIndex(idx1);
			listboxGenotype2.setSelectedIndex(idx2);
			onselectSmoothGenotype();
		}
	}
	
	public void onselectListboxPositions(List listpos) {
		
		
		if( (listboxTrait.getSelectedIndex()>0 && listboxSubpopulation.getSelectedIndex()>0) || this.listboxVarietylist.getSelectedIndex()>0	) 
		{ } else {
			Messagebox.show("No variety phenotype data. Select a trait and subpopulation from stored results, OR a variety list with phenotype value");
			return;
		}
			
		this.tabPosition.setDisabled(false);
		 
		
		divVarietylist.setVisible(false);
		
		/*
		mapSubpop2Phenotype2Count=new HashMap();
		mapSubpopGen2Phenotype2Count=new HashMap();
		mapGenotype2Phenotype2Count=new HashMap();
		mapAllele2Phenotype2Count=new HashMap();
		mapAllele2Phenotype2Set=new HashMap();
		listVarieties=null;
		*/
		    
		Set sortPos=new TreeSet(listpos);
		listpos=new ArrayList();
		listpos.addAll(sortPos);
		
		String sSubpopulation= this.listboxSubpopulation.getSelectedItem().getLabel();
		
		String sChr=null;
		boolean isMulticontig=false;
		List poslist=new ArrayList();
		List ckeckedposlist=new ArrayList();
		List multicontlist=new ArrayList();
		Iterator itPos=listpos.iterator();
		while(itPos.hasNext()) {
			
			String pos=(String)itPos.next();
			if(pos.isEmpty()) continue;
			if(sChr!=null && sChr.equals(pos.split("\\-")[0])) isMulticontig=true;
			sChr=pos.split("\\-")[0];
			if(sChr.length()==1) sChr="chr0" + sChr;
			else sChr="chr" + sChr;
			
			poslist.add( BigDecimal.valueOf( Double.valueOf(pos.split("\\-")[1] )));
			multicontlist.add(new MultiReferencePositionImpl(Organism.REFERENCE_NIPPONBARE, sChr, BigDecimal.valueOf( Double.valueOf(pos.split("\\-")[1] ))));
			
		}
		
		if(isMulticontig) {
			sChr="any";
			poslist=multicontlist;
		} else {
	    	//Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(sChr, new HashSet(poslist),  SnpsAllvarsPosDAO.TYPE_3KALLSNP ).iterator();
			
			Set s=new HashSet();
			s.add("3kall");
			Iterator<SnpsAllvarsPos> itSnpsDB = genotype.checkSNPInChromosome(sChr, new HashSet(poslist),  s).iterator();
	    	while(itSnpsDB.hasNext()) {
	    		ckeckedposlist.add( itSnpsDB.next().getPosition() );
	    	}
	    	if(ckeckedposlist.size()==0) {
	    		Messagebox.show("Cannot find variant " +sChr + " " + poslist);
	    		return;
	    	}
	    	poslist=ckeckedposlist;
		}
		
		String sPhenotype= listboxTrait.getSelectedItem().getLabel() ;
		if(poslist.size()==0) {
			 plotXYHistogramCount(new HashMap(),chartAlleleHist, "Allele count", sPhenotype, checkboxNormalize.isChecked());
			 plotXYHistogram(new HashMap() ,chartGenotypeHist, "Genotype count", sPhenotype, checkboxNormalize.isChecked());
			 //plotXYHistogram(new HashMap(),chartSubpopHist, "Variety count", sPhenotype, checkboxNormalize.isChecked(), true );
			 //plotXYHistogram(new HashMap(),chartGenSubpopHist, "Variety count", sPhenotype, checkboxNormalize.isChecked());
			 return;
		}

		Collection varlist=null;
		if(listboxVarietylist.getSelectedIndex()>0)
		{
			varlist = workspace.getVarieties(listboxVarietylist.getSelectedItem().getLabel());
		}

		mapGenotype2Phenotype2Count=null;
		mapGenotype2Phenotype2Frequency=null;
				
		mapAllele2Phenotype2Count=null;
		mapVarid2Phenotype=null;
		mapVarid2Genotype=null;
		mapAllele2Phenotype2Set=null;

		Map[] mapAlleleHists = gwas.createAlleleDistributions(sChr, poslist, sPhenotype, varlist, checkboxNormalize.isChecked());

		mapGenotype2Phenotype2Count=mapAlleleHists[1];
		mapAllele2Phenotype2Count=mapAlleleHists[0];
		mapVarid2Phenotype=mapAlleleHists[4];
		mapVarid2Genotype=mapAlleleHists[5];
		mapAllele2Phenotype2Set=mapAlleleHists[6];
		mapGenotype2Phenotype2Frequency=mapAlleleHists[3];
		
		if(checkboxNormalize.isChecked()) {
			 plotXYHistogramCount(mapAlleleHists[2],chartAlleleHist, "Allele frequency", sPhenotype, false);
			 plotXYHistogram(mapAlleleHists[3], chartGenotypeHist, "Genotype frequency" , sPhenotype, false);
		
		} else {
			 plotXYHistogramCount(mapAlleleHists[0],chartAlleleHist, "Allele count", sPhenotype, false);
			 plotXYHistogram(mapAlleleHists[1],chartGenotypeHist, "Genotype count", sPhenotype, false);
		}
		
		
		List listGenotypes=new ArrayList();
		listGenotypes.add("");
		listGenotypes.addAll(mapGenotype2Phenotype2Count.keySet());
		listboxGenotype1.setModel(new SimpleListModel(listGenotypes));
		listboxGenotype2.setModel(new SimpleListModel(listGenotypes));
		listboxGenotype1.setSelectedIndex(0);listboxGenotype2.setSelectedIndex(0);
		//plotXYHistogram(mapGenotype2Phenotype2Count, chartSmoothHist, "Genotype frequency" , sPhenotype, false);
		
		
		 //plotXYHistogramCount(mapAllele2Phenotype2Count,chartAlleleHist, "Allele " +slabel, sPhenotype, checkboxNormalize.isChecked());
		 //plotXYHistogram(mapGenotype2Phenotype2Count,chartGenotypeHist, "Genotype " + slabel, sPhenotype, checkboxNormalize.isChecked());
		 //plotXYHistogram(mapSubpop2Phenotype2Count,chartSubpopHist, "Variety count", sPhenotype, checkboxNormalize.isChecked(), true );
		 //plotXYHistogram(mapSubpopGen2Phenotype2Count,chartGenSubpopHist, "Variety count", sPhenotype, checkboxNormalize.isChecked());
	
		 labelPositions.setMaxlength(70);
		 labelPositions.setMultiline(true);
		 labelPositions.setValue("Histograms for positions " +  listpos.toString());
		 

		
//		
//		
//		GenotypeQueryParams params= new  GenotypeQueryParams(null, sChr, null, null, true,false, false,
//				false,  poslist, null,
//				null, false, false); 
//		params.setPhenotype(sPhenotype);
//
//		Map<Object,Integer> mapGenotype2Count=new TreeMap();
//		Map<Long,String> mapVarid2Subpop=new HashMap();
//		mapVarid2Genotype=new HashMap();
//		
//		
//		try {
//			VariantStringData data = genotype.queryGenotype( params );
//			VariantTableArray varianttable =  new VariantAlignmentTableArraysImpl();
//			varianttable = (VariantTableArray)genotype.fillGenotypeTable(varianttable, data, params);
//			for(int ivar=0; ivar<varianttable.getVarid().length; ivar++) {
//				//Object allele=varianttable.getVaralleles()[ivar][0];
//				
//				StringBuffer buffallele=new StringBuffer();
//				for(int allelelen=0; allelelen<varianttable.getVaralleles()[ivar].length; allelelen++) {
//					String allelei=varianttable.getVaralleles()[ivar][allelelen].toString();
//					if(allelei.isEmpty()) allelei="?";
//					buffallele.append(allelei);
//				}
//				Object allele=buffallele.toString();
//				
//				mapVarid2Genotype.put( varianttable.getVarid()[ivar], allele );
//				
//				Integer cnt = mapGenotype2Count.get(allele);
//				if(cnt==null) {
//					cnt=Integer.valueOf(0);
//				}
//				mapGenotype2Count.put( allele, cnt+1);
//				String subpop=variety.getMapId2Variety().get( BigDecimal.valueOf(varianttable.getVarid()[ivar]) ).getSubpopulation();
//				mapVarid2Subpop.put(varianttable.getVarid()[ivar] , subpop);
//			}
//			
//		} catch(Exception ex) {
//			ex.printStackTrace();
//			Messagebox.show(ex.getMessage());
//		}
//		
//		
//		mapVarid2Phenotype=null;
//		boolean phenIsNumber=true;
//		if(params.getPhenotype()!=null && !params.getPhenotype().isEmpty()) {
//			sPhenotype=params.getPhenotype();
//			mapVarid2Phenotype = variety.getPhenotypeValues(sPhenotype);
//		} if( !this.listboxVarietylist.getSelectedItem().getLabel().isEmpty() ) {
//			mapVarid2Phenotype=new HashMap();
//			String varlistname=listboxVarietylist.getSelectedItem().getLabel().trim();
//			Iterator<Variety> itvarlist=workspace.getVarieties(varlistname).iterator();
//			while(itvarlist.hasNext()) {
//				VarietyPlusPlus vp = (VarietyPlusPlus)itvarlist.next();
//				mapVarid2Phenotype.put( vp.getVarietyId() , vp.getValue());
//			}
//		}
//	
//
//		
//		Iterator<BigDecimal> itVar=mapVarid2Phenotype.keySet().iterator();
//		Map<Object,Integer> mapPhen2Count=new TreeMap();
//		double maxphen=0;
//		double minphen=Double.MAX_VALUE;
//		while(itVar.hasNext()) {
//			BigDecimal varid=itVar.next();
//			Object phen=mapVarid2Phenotype.get(varid);
//			
//			//AppContext.debug(phen.getClass().toString() + "  " + phen);
//			if(phen instanceof String) {
//				phenIsNumber=false;
//			} else {
//				double phenval = ((BigDecimal)phen).doubleValue();
//				if(phenval>maxphen) maxphen=phenval;
//				if(phenval<minphen) minphen=phenval;
//			}
//			
//			Object vargenotype=mapVarid2Genotype.get(varid.longValue());
//			Map<Object,Collection> mapphencnt = mapGenotype2Phenotype2Count.get( vargenotype );
//			if(mapphencnt==null) {
//				mapphencnt=new HashMap();
//				mapGenotype2Phenotype2Count.put(vargenotype, mapphencnt);
//			}
//			Collection phencnt = mapphencnt.get(phen);
//			if(phencnt==null) {
//				//phencnt=Integer.valueOf(0);
//				phencnt=new HashSet();
//				mapphencnt.put( phen, phencnt);
//			}
//			//mapphencnt.put( phen, phencnt+1 );
//			phencnt.add( varid );
//			mapGenotype2Phenotype2Count.put(vargenotype, mapphencnt);
//			
//			if(params.getPoslist()!=null &&  params.getPoslist().size()==1) {
//			if(vargenotype.toString().contains("/")) {
//				String alleles[] = vargenotype.toString().split("\\/");
//
//				Map<Object,Integer> mapallelecnt = mapAllele2Phenotype2Count.get( alleles[0] );
//				Map<Object,Collection> mapalleleset = mapAllele2Phenotype2Set.get( alleles[0] );
//				if(mapallelecnt==null) {
//					mapallelecnt=new HashMap();
//					mapAllele2Phenotype2Count.put(alleles[0], mapallelecnt);
//					mapalleleset=new HashMap();
//					mapAllele2Phenotype2Set.put(alleles[0], mapalleleset);
//				}
//				Integer phenallelecnt = mapallelecnt.get(phen);
//				Collection phenalleleset=mapalleleset.get(phen);
//				if(phenallelecnt==null) {
//					phenallelecnt=Integer.valueOf(0);
//				}
//				if(phenalleleset==null) {
//					phenalleleset=new HashSet();
//					mapalleleset.put( phen, phenalleleset);
//				}
//				mapallelecnt.put( phen, phenallelecnt);
//				phenalleleset.add( varid );
//				mapAllele2Phenotype2Count.put(alleles[0], mapallelecnt);
//
//				mapallelecnt = mapAllele2Phenotype2Count.get( alleles[1] );
//				mapalleleset = mapAllele2Phenotype2Set.get( alleles[1] );
//				if(mapallelecnt==null) {
//					mapallelecnt=new HashMap();
//					mapAllele2Phenotype2Count.put(alleles[1], mapallelecnt);
//					mapalleleset=new HashMap();
//					mapAllele2Phenotype2Set.put(alleles[1], mapalleleset);
//				}
//				phenallelecnt = mapallelecnt.get(phen);
//				phenalleleset=mapalleleset.get(phen);
//				if(phenallelecnt==null) {
//					phenallelecnt=Integer.valueOf(0);
//				}
//				if(phenalleleset==null) {
//					phenalleleset=new HashSet();
//					mapalleleset.put( phen, phenalleleset);
//				}
//				phenalleleset.add( varid );
//				mapallelecnt.put( phen, phenallelecnt);
//				mapAllele2Phenotype2Count.put(alleles[1], mapallelecnt);
//				
//			} else { // if(!vargenotype.toString().isEmpty()){
//				Map<Object,Integer> mapallelecnt = mapAllele2Phenotype2Count.get( vargenotype );
//				Map<Object,Collection> mapalleleset = mapAllele2Phenotype2Set.get( vargenotype);
//				if(mapallelecnt==null) {
//					mapallelecnt=new HashMap();
//					mapAllele2Phenotype2Count.put(vargenotype, mapallelecnt);
//					mapalleleset=new HashMap();
//					mapAllele2Phenotype2Set.put(vargenotype, mapalleleset);
//				}
//				Integer phenallelecnt = mapallelecnt.get(phen);
//				Collection phenalleleset=mapalleleset.get(phen);
//				if(phenallelecnt==null) {
//					phenallelecnt=Integer.valueOf(0);
//				}
//				if(phenalleleset==null) {
//					phenalleleset=new HashSet();
//					mapalleleset.put( phen, phenalleleset);
//				}
//				phenalleleset.add( varid );
//				mapallelecnt.put( phen, phenallelecnt+2 );
//				mapAllele2Phenotype2Count.put(vargenotype, mapallelecnt);
//			}
//			}
//
//
//			String subpop=mapVarid2Subpop.get(varid.longValue());
//			mapphencnt = mapSubpop2Phenotype2Count.get( subpop );
//			if(mapphencnt==null) {
//				mapphencnt=new HashMap();
//				mapSubpop2Phenotype2Count.put(subpop, mapphencnt);
//			}
//			phencnt = mapphencnt.get(phen);
//			if(phencnt==null) {
//				//phencnt=Integer.valueOf(0);
//				phencnt=new HashSet();
//				mapphencnt.put( phen, phencnt);
//			}
//			//mapphencnt.put( phen, phencnt+1 );
//			phencnt.add( varid );
//			mapSubpop2Phenotype2Count.put(subpop, mapphencnt);
//			
//			String gensubpop= Data.getGeneralSubpopulation(mapVarid2Subpop.get(varid.longValue()));
//			if(gensubpop!=null) {
//				mapphencnt = mapSubpopGen2Phenotype2Count.get( gensubpop );
//				if(mapphencnt==null) {
//					mapphencnt=new HashMap();
//					mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);
//				}
//				phencnt = mapphencnt.get(phen);
//				if(phencnt==null) {
//					//phencnt=Integer.valueOf(0);
//					phencnt=new HashSet();
//					mapphencnt.put( phen, phencnt);
//				}
//				//mapphencnt.put( phen, phencnt+1 );
//				phencnt.add( varid );
//				mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);
//			}
//			
//			gensubpop="all varieties";
//			mapphencnt = mapSubpopGen2Phenotype2Count.get( gensubpop );
//			if(mapphencnt==null) {
//				mapphencnt=new HashMap();
//				mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);
//			}
//			phencnt = mapphencnt.get(phen);
//			if(phencnt==null) {
//				//phencnt=Integer.valueOf(0);
//				phencnt=new HashSet();
//				mapphencnt.put( phen, phencnt);
//			}
//			//mapphencnt.put( phen, phencnt+1 );
//			phencnt.add( varid );
//			mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);
//			
//			
//			
//		}
//		
//		// create xy data
//		/*
//		Iterator itAlleles = mapAllele2Phenotype2Count.keySet().iterator();
//		int allelecount=0;
//		while(itAlleles.hasNext()) {
//			Object allele=itAlleles.next();
//			Series series = null;
//			if(allelecount==0) series = chartAlleleHist.getSeries();
//			else series = chartAlleleHist.getSeries(allelecount);
//			
//			series.setName(allele.toString());
//			for (Double[] value : createXY(mapAllele2Phenotype2Count.get(allele))) {
//		            series.addPoint(value[0], value[1]);
//		    }
//			allelecount++;
//		}
//		*/
//		 plotXYHistogramCount(mapAllele2Phenotype2Count,chartAlleleHist, "Allele count", sPhenotype, checkboxNormalize.isChecked());
//		 plotXYHistogram(mapGenotype2Phenotype2Count,chartGenotypeHist, "Genotype count", sPhenotype, checkboxNormalize.isChecked());
//		 plotXYHistogram(mapSubpop2Phenotype2Count,chartSubpopHist, "Variety count", sPhenotype, checkboxNormalize.isChecked(), true );
//		 plotXYHistogram(mapSubpopGen2Phenotype2Count,chartGenSubpopHist, "Variety count", sPhenotype, checkboxNormalize.isChecked());
//	
//		 labelPositions.setMaxlength(70);
//		 labelPositions.setMultiline(true);
//		 labelPositions.setValue("Histograms for positions " +  listpos.toString());
//		 
//		 //if(listboxPosition.getRows()>-1) listboxPosition.setSelectedIndex(0);
		 
	}
	
	@Listen("onClick = #checkboxNormalize")
	public void onclickNormalize() {
		String sPhenotype= listboxTrait.getSelectedItem().getLabel() ;
		
		String y = " count";
		if(checkboxNormalize.isChecked()) y = " frequency";
		
		if(mapAllele2Phenotype2Count!=null)
		 plotXYHistogramCount(mapAllele2Phenotype2Count,chartAlleleHist, "Allele" + y, sPhenotype, checkboxNormalize.isChecked());
		if(mapGenotype2Phenotype2Count!=null)
		 plotXYHistogram(mapGenotype2Phenotype2Count,chartGenotypeHist, "Genotype" + y, sPhenotype, checkboxNormalize.isChecked());
		 
		 //plotXYHistogram(mapSubpop2Phenotype2Count,chartSubpopHist, "Variety" + y, sPhenotype, checkboxNormalize.isChecked(), true);
		 //plotXYHistogram(mapSubpopGen2Phenotype2Count,chartGenSubpopHist, "Variety" + y, sPhenotype, checkboxNormalize.isChecked());
	}
	
	
	@Listen("onClick = #checkboxNormalizePopulation")
	public void onclickNormalizePop() {
		String sPhenotype= listboxTrait.getSelectedItem().getLabel() ;
		
		String y = " count";
		if(checkboxNormalizePopulation.isChecked()) y = " frequency";
		
		if(mapSubpop2Phenotype2Count!=null)
			plotXYHistogram(mapSubpop2Phenotype2Count,chartSubpopHist, "Variety" + y, sPhenotype, checkboxNormalizePopulation.isChecked());
		if(mapSubpopGen2Phenotype2Count!=null)
			plotXYHistogram(mapSubpopGen2Phenotype2Count,chartGenSubpopHist, "Variety" + y, sPhenotype, checkboxNormalizePopulation.isChecked());
		 
		 //plotXYHistogram(mapSubpop2Phenotype2Count,chartSubpopHist, "Variety" + y, sPhenotype, checkboxNormalize.isChecked(), true);
		 //plotXYHistogram(mapSubpopGen2Phenotype2Count,chartGenSubpopHist, "Variety" + y, sPhenotype, checkboxNormalize.isChecked());
	}
	

	
	private void checkSeries(Charts chart, Vbox vboxSeries) {
		vboxSeries.getChildren().clear();
		
	}
	
	private void plotXYHistogram(Map<Object, Map<Object,Collection>> mapData, Charts histchart, String yaxis, String xaxis, boolean normalize) {
		 plotXYHistogram( mapData,  histchart,  yaxis,  xaxis,  normalize, false);
	}
	

	private void plotLine(Map<String,List> mapData, Charts linechart, String yaxis, String xaxis) {
		CategoryModel model=new DefaultCategoryModel();
		Iterator<String> itName=mapData.keySet().iterator();
		while(itName.hasNext()) {
			String name=itName.next();
			int i=0;
			List tops = mapData.get(name);
			Iterator itScores=tops.iterator();
			while(itScores.hasNext()) {
				model.setValue(name, i, (Number)itScores.next());
				i++;
			}
		}
		
		linechart.setModel(model);
        
		linechart.getYAxis().setTitle(yaxis);
		linechart.getXAxis().setTitle(xaxis);
    
	}
	
	private void plotXYHistogram(Map<Object, Map<Object,Collection>> mapData, Charts histchart, String yaxis, String xaxis, boolean normalize, boolean hideSeries) {
		
		
		//Map< Series
		 Vbox vboxSeries=null;
		List<Component> listchecks=null;
		if(vboxSeries!=null) {
			listchecks = vboxSeries.getChildren();
			listchecks.clear();
		}
		
		histchart.setModel(new DefaultXYModel());
		Iterator itSeries = mapData.keySet().iterator();
		int seriescount=0;
		while(itSeries.hasNext()) {
			Object seriesobj=itSeries.next();
			Series series = null;
			if(seriescount==0) series = histchart.getSeries();
			else series = histchart.getSeries(seriescount);
			
			if(seriesobj==null || seriesobj.toString().isEmpty())
				series.setName("?");
			else
				series.setName(seriesobj.toString());
			
			if(hideSeries) {
				if(seriescount<2) series.setVisible(true);
				else  series.setVisible(false);
			} else  series.setVisible(true);
				
			series.setLineWidth(1);
			
			if(vboxSeries!=null) {
				final Series fseries=series;
				Checkbox newcheck=new Checkbox();
				newcheck.setLabel( seriesobj.toString() );
				if(seriescount<2) newcheck.setChecked(true);
				newcheck.addEventListener(Events.ON_CHECK, new EventListener()
			        {
			          public void onEvent( Event ev ) throws Exception
			          {
			        	  CheckEvent checkev=(CheckEvent)ev;
			        	  fseries.setVisible(checkev.isChecked());
			          }
			        });
				listchecks.add(newcheck);
			}
			
			for (Double[] value : createXY(mapData.get(seriesobj), normalize)) {
		            series.addPoint(value[0], value[1]);
		    }
			
			
			seriescount++;
		}
		
		histchart.getXAxis().setTitle(xaxis);
		histchart.getYAxis().setTitle(yaxis);
	}
	
	
	
	private Double[][] createXY(Map mapX2Y, boolean normalize) {
		double total=0.0;
		List<Double[]> listdouble=new ArrayList();
		Iterator itX= new TreeSet(mapX2Y.keySet()).iterator();
		while(itX.hasNext()) {
			Object x=itX.next();
			String xstr=x.toString(); 
			try {
				Object y=mapX2Y.get(x);
				Double cnt=null;
				
				if(y==null) cnt=Double.valueOf(0);
				else if(y instanceof Number) cnt= ((Number)y).doubleValue();
				else if(y instanceof Collection) cnt= Double.valueOf(((Collection)y).size());	
				
				//else cnt= Double.valueOf(((Collection)y).size());
				total+=cnt;
				Double idouble[] = new Double[]{Double.valueOf(xstr) ,cnt};
				listdouble.add(idouble);
			} catch(Exception ex) {
				AppContext.debug("x=" + x );
				ex.printStackTrace();
			}
		}
		Iterator<Double[]> itXY=listdouble.iterator();
		Double[][] xy=new Double[listdouble.size()][2];
		int ix=0;
		while(itXY.hasNext()) {
			Double[] ixy=itXY.next();
			if(normalize)
				xy[ix]=new Double[] {ixy[0], ixy[1]/total};
			else xy[ix]=ixy;
			ix++;
		}
		return xy;
	}
	
	private void plotXYHistogramCount(Map<Object, Map<Object,Integer>> mapData, Charts histchart, String yaxis, String xaxis, boolean normalize) {
		histchart.setModel(new DefaultXYModel());
		Iterator itSeries = mapData.keySet().iterator();
		int seriescount=0;
		while(itSeries.hasNext()) {
			Object seriesobj=itSeries.next();
			Series series = null;
			if(seriescount==0) series = histchart.getSeries();
			else series = histchart.getSeries(seriescount);
			
			if(seriesobj==null || seriesobj.toString().isEmpty())
				series.setName("?");
			else
				series.setName(seriesobj.toString());
			series.setLineWidth(1);
			
			for (Double[] value : createXYCount(mapData.get(seriesobj), normalize)) {
		            series.addPoint(value[0], value[1]);
		    }
			seriescount++;
		}
		
		histchart.getXAxis().setTitle(xaxis);
		histchart.getYAxis().setTitle(yaxis);
	}
	
	private Double[][] createXYCount(Map mapX2Y, boolean normalize) {
		double total=0;
		List<Double[]> listdouble=new ArrayList();
		Iterator itX=new TreeSet(mapX2Y.keySet()).iterator();
		while(itX.hasNext()) {
			Object x=itX.next();
			String xstr=x.toString(); 
			try {
				Object y=mapX2Y.get(x);
				
				Double cnt=null;
				if(y==null) cnt=Double.valueOf(0);
				else if(y instanceof Number) cnt= ((Number)y).doubleValue();
				else if(y instanceof Collection) cnt= Double.valueOf(((Collection)y).size());	

				//if(y==null) y=Double.valueOf(0);
				//Double cnt=Double.valueOf(y.toString());
				
				total+=cnt;
				Double idouble[] = new Double[]{Double.valueOf(xstr) ,cnt};
				listdouble.add(idouble);
			} catch(Exception ex) {
				AppContext.debug("x=" + x );
				ex.printStackTrace();
			}
		}
		Iterator<Double[]> itXY=listdouble.iterator();
		Double[][] xy=new Double[listdouble.size()][2];
		int ix=0;
		while(itXY.hasNext()) {
			Double[] ixy=itXY.next();
			if(normalize)
				xy[ix]=new Double[]{ixy[0], ixy[1]/total};
			else xy[ix]=ixy;
			ix++;
		}
		return xy;
	}




	

}
