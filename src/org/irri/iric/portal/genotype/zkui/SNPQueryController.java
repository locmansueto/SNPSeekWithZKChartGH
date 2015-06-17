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
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTable;
import org.irri.iric.portal.domain.VariantTableArray;
import org.irri.iric.portal.domain.Variety;
//import org.irri.iric.portal.genotype.domain.Gene;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.genotype.service.GenotypeQueryParams;
import org.irri.iric.portal.genotype.service.IndelStringService;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.genotype.service.VariantTableArraysImpl;
import org.irri.iric.portal.genotype.service.VariantTableRandomImpl;
import org.irri.iric.portal.domain.Snps2Vars;

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
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.irri.iric.portal.variety.zkui.ListboxPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.chart.AxisLabels;
import org.zkoss.chart.Chart;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Legend;
import org.zkoss.chart.PlotLine;
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
@Scope("session")
public class SNPQueryController extends SelectorComposer<Window> { //<Component>  { //implements Initiator {
 
	private String urljbrowse,gfffile,urlphylo,urljbrowsephylo;
	//private String phylochr, phylostart, phyloend;
	
	int screenwidth=0;
	int screenheight=0;
	
	private int lastY;
	
	private int biglistPrevRowVisible=0; 
	int frozenCols=3;
	private Map<Integer,BigDecimal> mapIdx2Pos;
	
	private boolean tallJbrowse;
	//private List<SnpsStringAllvars> origAllVarsListModel;
	private List<SnpsStringAllvars> filteredList;
	private List<Snps2Vars>  list2Vars;
	private int snpallvars_pagesize=50;
	private String tmpfile_spath="";
	private Listheader lhr_variety;
	private Listheader lhrMismatch;
	
	
	private String vistaurl;
	private String vistaurlrev;
	
	private List listContigs;
	private List listLoci;
	
	private static final Log log = LogFactory.getLog(SNPQueryController.class);
	private static final  String TEXTSTYLE_ERROR="font-color:red";
	private static final  String TEXTSTYLE_SUCCESS="font-folor:black";
	
    private static final long serialVersionUID = 1L;
    private int chrlength = 43270923; 
    
    //final private String classGenotypefacade = "GenotypeFacadeLegacy";
    final private String classGenotypefacade = "GenotypeFacade";
    
    private Object2StringMatrixModel biglistboxModel=null;
    private MatrixComparatorProvider matriccmpproviderAsc;
	private MatrixComparatorProvider matriccmpproviderDesc;
	
	private AlleleFreqLineData allelefreqlines = new AlleleFreqLineData();
	private AlleleFreqLineData genotypefreqlines = new AlleleFreqLineData();
	
	
	@Wire
	private Div divTablePanel;
	@Wire
	private Charts chartAlleleFrequency;
	
	/*@Wire 
	private Checkbox checkboxShowAlleleFrequency;
	@Wire 
	private Checkbox checkboxShowAlleleCount;
	@Wire 
	private Checkbox checkboxMajorAlleles;
	@Wire 
	private Checkbox checkboxMinorAlleles;
	@Wire 
	private Checkbox checkbox3rdAlleles;
	*/
	
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
    
    
    //private VariantTable modelBiglisttable;
    //private VariantTable modelBiglistArray;
    private VariantStringData queryRawResult;
    private VariantStringData queryResult;
    private VariantTable varianttable;

	//private List listBiglistheaderData;
	private int biglistboxRows=16;

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
	private Label labelLocusExample;
	@Wire
	private Hbox divAlignIndels;
	@Wire
	private Div divIndelLegend;
	@Wire
	private Biglistbox biglistboxArray;
	@Wire
	private Grid gridBiglistheader;

	@Wire
	private Radiogroup showsnps;
	
	
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
    private Button resetButton;
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
    private Button searchButton;
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


    
    @Autowired
    @Qualifier("GenotypeFacade")
    private GenotypeFacade  genotype; 
    
    @Autowired
    @Qualifier("VarietyFacade")
    private VarietyFacade  varietyfacade;
    
    @Autowired
    @Qualifier("WorkspaceFacade")
    private WorkspaceFacade workspace;
    
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
    private Tabbox tabboxDisplay;
    @Wire
    private Checkbox checkboxCoreSNP;
    @Wire
    private Checkbox checkboxExactMismatch;
    
    //@Wire
    //private Checkbox checkboxSpliceSNP;
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
		this.checkboxShowAllRefAlleles.setChecked(false);
		this.checkboxShowAllRefAlleles.setDisabled(true);
	} else {
		this.checkboxShowAllRefAlleles.setDisabled(false);
	}
}

/*
<hbox height="30px"/>
<radio id="radioShowAlleleFrequency" label="Show allele frequency" radiogroup="radiogroupAllelefreqcount" selected="true"/>
<radio id="radioShowAlleleCount" label="Show allele count" radiogroup="radiogroupAllelefreqcount"/>
<radio id="radioShowGenotypeFrequency" label="Show genotype frequency" radiogroup="radiogroupAllelefreqcount"/>
<radio id="radioShowGenotypeCount" label="Show genotype count" radiogroup="radiogroupAllelefreqcount"/>
<hbox height="10px"/>
<radio id="radioMajorAlleles" label="Show major alleles" radiogroup="radiogroupMajorminor" selected="true"/>
<radio id="radioMinorAlleles" label="Show minor alleles" radiogroup="radiogroupMajorminor"/>
<radio id="radio3rdAlleles" label="Show 3rd alleles" radiogroup="radiogroupMajorminor"/>
</vbox>

<charts id="chartAlleleFrequency" type="line"  title="Allele frequency" 
*/

@Listen("onClick=#radioShowGenotypeFrequency")
public void onclickradioShowGenotypeFrequency() {
	this.updateAlleleFrequencyLines();
	radioMajorAlleles.setLabel("Major genotypes");
	radioMinorAlleles.setLabel("Minor genotypes");
	radio3rdAlleles.setLabel("3rd genotypes");


}
@Listen("onClick=#radioShowGenotypeCount")
public void onclickradioShowGenotypeCount() {
	this.updateAlleleFrequencyLines();
	radioMajorAlleles.setLabel("Major genotypes");
	radioMinorAlleles.setLabel("Minor genotypes");
	radio3rdAlleles.setLabel("3rd genotypes");
}

@Listen("onClick=#radioShowAlleleFrequency")
public void onclickradioShowAlleleFrequency() {
	this.updateAlleleFrequencyLines();
	radioMajorAlleles.setLabel("Major alleles");
	radioMinorAlleles.setLabel("Minor alleles");
	radio3rdAlleles.setLabel("3rd alleles");
}
@Listen("onClick=#radioShowAlleleCount")
public void onclickradioShowAlleleCount() {
	this.updateAlleleFrequencyLines();
	radioMajorAlleles.setLabel("Major alleles");
	radioMinorAlleles.setLabel("Minor alleles");
	radio3rdAlleles.setLabel("3rd alleles");
}



@Listen("onClick=#radioMajorAlleles")
public void onclickradioMajorAlleles() {
	this.updateAlleleFrequencyLines();
}
@Listen("onClick=#radioMinorAlleles")
public void onclickradioMinorAlleles() {
	this.updateAlleleFrequencyLines();
}
@Listen("onClick=#radio3rdAlleles")
public void onclickradio3rdAlleles() {
	this.updateAlleleFrequencyLines();
}




@Listen("onClick =#radioColorMismatch")
public void onclickcolorMismatch() {
	try {
	if(biglistboxArray==null) return;
	//Object2StringMatrixRenderer renderer =  (Object2StringMatrixRenderer)this.biglistboxArray.getMatrixRenderer();
	Object2StringMatrixModel model  =  (Object2StringMatrixModel)this.biglistboxArray.getModel();
	 if(model==null || model.getData()==null) return;
	
	Object2StringMatrixRenderer renderer = new Object2StringMatrixRenderer( model.getData().getVariantStringData(), this.varietyfacade.getMapId2Variety(),  fillGenotypeQueryParams() );
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
	
	Object2StringMatrixRenderer renderer = new Object2StringMatrixRenderer( model.getData().getVariantStringData(), this.varietyfacade.getMapId2Variety(),  fillGenotypeQueryParams() );
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
		
		
		listContigs = AppContext.createUniqueUpperLowerStrings(genotype.getContigsForReference(  selOrg ), true,true);
		
		//listContigs = new ArrayList();
		//listContigs.add("");
		//listContigs.addAll(genotype.getContigsForReference(  selOrg ));
		
		//selectChr.setPreloadSize(50);
		//selectChr.set
		//this.selectChr.setModel(new SimpleListModel(listReference));
		//this.selectChr.setModel(new ListModelList(listReference));
		
		this.selectChr.setModel(new SimpleListModel(listContigs));
		
		
		//ListModelList contiglist = (ListModelList)selectChr.getModel();
		//contiglist.clear();
		//contiglist.addAll( listContigs );

		
		
		AppContext.debug(selectChr.getChildren().size() + " contigs in combobox");
		
		
		//listLoci = new ArrayList();
		//listLoci.add("");
		//listLoci.addAll(genotype.getLociForReference( selOrg ));
		//this.comboGene.setModel( new ListModelList(listLoci) );
		
		listLoci = AppContext.createUniqueUpperLowerStrings(  genotype.getLociForReference( selOrg ), true,true);
		
		comboGene.setModel( new SimpleListModel(listLoci) );
		
		
		//labelLocusExample.setValue("(ex. " + listLoci.get(1).toString().toLowerCase() + ") ");
		
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
		
		
		//ListModelList locilist = (ListModelList)comboGene.getModel();
		//locilist.clear();
		//locilist.addAll( listLoci );
		
		
		//selectChr.setSelectedIndex(0);
		selectChr.setValue("");
		//labelLength.setValue("End:");
		
		String selorg = listboxReference.getSelectedItem().getLabel();
		
		if(!selorg.equals( AppContext.getDefaultOrganism())) {
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

@Listen("onCheck = #showsnps") 
public void oncheckShowsnps() {
	if(this.queryRawResult==null) return;
	
	/*
	if(!this.biglistboxArray.isVisible()) {
		Messagebox.show("Dynamic change not yet implemented for pairwise comparison. Click SEARCH again for this view.", "NOT IMPLEMENTED", Messagebox.OK, Messagebox.INFORMATION);
		return;
	}
	*/
	if(this.listboxSnpresult.isVisible()) {
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
	listboxSnpresult.setVisible(true);
	
	//queryRawResult.merge();
	//listSNPs = queryRawResult.getListVariantsString();
	//create2VarietiesGFF(listSNPs, gfffile, queryResult.getListPos(), queryResult.getRefnuc() );
	
	//msgbox.setValue( msgbox.getValue() + " ... RESULT: " +  snpresult.getModel().getSize() + " positions" );
	String msgs[]= msgbox.getValue().split("...");
	if(msgs.length>0)
		msgbox.setValue(msgs[0] + " ... RESULT: " +  listboxSnpresult.getModel().getSize() + " positions" );
	else msgbox.setValue(msgbox.getValue() + " ... RESULT: " +  listboxSnpresult.getModel().getSize() + " positions" );
	//tallJbrowse= false;
}



/*
@Listen("onClick = #radioAllSnps")
public void onclickAllSnps() {
	GenotypeQueryParams params =  fillGenotypeQueryParams();
	updateBiglistboxArray(params);
}

@Listen("onClick = #radioNonsynSnps")
public void onclickNonsynSnps() {
	GenotypeQueryParams params =  fillGenotypeQueryParams();
	updateBiglistboxArray(params);
	
}
@Listen("onClick = #radioNonsynHighlights")
public void onclickHighlightNonsynSnps() {
	GenotypeQueryParams params =  fillGenotypeQueryParams();
	updateBiglistboxArray(params);
	
}
@Listen("onClick = #radioNonsynSnpsPlusSplice")
public void onclickNonsynSnpsPlusSplice() {
	GenotypeQueryParams params =  fillGenotypeQueryParams();
	updateBiglistboxArray(params);
}
*/



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

@Listen("onSort =#biglistboxArray") 
public void onsortBiglistbox(SortEventExt event) {
	
	Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	getSession().putValue("variantTable", model.getData());
	gfffile=null;
	AppContext.debug("reset gff=null");
}



//@Listen("onSort =#biglistboxArray") 
//public void onsortBiglistbox(SortEventExt event) {
//	//biglistboxArray.get
//	//event.getData();
//	
//	try {
//	
//	//Events.postEvent("onScrollY", biglistboxArray, new ScrollEventExt("onScrollY",biglistboxArray,0,0));
//	
//
//	//Events.postEvent("onScrollY", biglistboxArray, 0);
//	
//	
//	//Events.postEvent("onScrollY", biglistboxArray, new Object());
//	//MatrixModel model = biglistboxArray.getModel();
//	//model.getCellAt(model.getElementAt(0),0);
//	//biglistboxArray.getRealMatrixRenderer().
//	//biglistboxArray.get
//	//AppContext.debug("model.getSize()=" + model.getSize() + "   biglistboxArray.getRows()=" + biglistboxArray.getRows());
//	
//	//Component firstRow = ((Object2StringMatrixRenderer)biglistboxArray.getMatrixRenderer()).getFirstRow();
//	//if(firstRow!=null) Clients.scrollIntoView( firstRow );
//	
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}
//}


@Listen("onClick = #buttonCreateVarlist")
public void onclickCreateVarlist() {
	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade , "VarietyFacade");
	
	Map<String,Variety> mapName2Var = varietyfacade.getMapVarname2Variety();
	Set setVarieties = new LinkedHashSet();
	Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	for(int i=0; i<model.getSize(); i++) {
		//AppContext.debug("model.getElementAt(i)=" + model.getElementAt(i));
		//AppContext.debug(model.getElementAt(i).get(0).getClass() + " model.getElementAt(i).get(0)=" + model.getElementAt(i).get(0));
		Object  varname =  ((Object[])model.getElementAt(i).get(0))[0];
		//AppContext.debug(varname.toString());
		setVarieties.add( mapName2Var.get(varname) );
	}
	workspace.addVarietyList( textboxVarlistName.getValue().trim(), setVarieties);
	textboxVarlistName.setValue("");
	
	
	/*
	List createlist = filteredList;  
	if(createlist==null)
		createlist = queryResult.getListVariantsString(); // this.origAllVarsListModel;

	
	Map mapId2Var =  varietyfacade.getMapId2Variety();
	Set setVarieties = new LinkedHashSet();

	Iterator<SnpsStringAllvars> itsnpstring = createlist.iterator();
	while(itsnpstring.hasNext()) {
		SnpsStringAllvars snpstr = itsnpstring.next();
		setVarieties.add( mapId2Var.get( snpstr.getVar() ));
		
	}
	
	workspace.addVarietyList( textboxVarlistName.getValue().trim(), setVarieties);
	textboxVarlistName.setValue("");
	*/
	
//	Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	
}

/*
@Override
public void doBeforeComposeChildren(Window comp) throws Exception {
	// TODO Auto-generated method stub
	super.doBeforeComposeChildren(comp);
	
	AppContext.debug("doBeforeComposeChildren:");
	
	comp.addEventListener(Events.ON_CLIENT_INFO, new EventListener<ClientInfoEvent>() {
	    @Override
	    public void onEvent(ClientInfoEvent event) throws Exception {
	    	screenwidth= event.getDesktopWidth();
			screenheight = event.getDesktopHeight();
			AppContext.debug("screenwidth=" + screenwidth + "  screenheight=" + screenheight );
			
	    }
	  });
}
*/

/*
@Listen("onClientInfo = #win")
public void onClientInfo(ClientInfoEvent event) {
	AppContext.debug("onClientInfo:");
	screenwidth= event.getDesktopWidth();
	screenheight = event.getDesktopHeight();
	AppContext.debug("screenwidth=" + screenwidth + "  screenheight=" + screenheight );
}
*/


@Listen("onSelect = #listboxMyVarieties")    
public void  onselectMyVarieties() {
	if(listboxMyVarieties.getSelectedIndex()>0) {

		//comboSubpopulation.setValue("");
		listboxSubpopulation.setSelectedIndex(0);
		checkboxAllvarieties.setChecked(false);
		comboVar1.setValue("");
		comboVar2.setValue("");
		
		if( listboxMyVarieties.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety&src=snp");
		}
		
		
	}
}

@Listen("onSelect = #listboxMyLocusList")    
public void  onselectMyLocusList() {
	if(listboxMyLocusList.getSelectedIndex()>0) {

		if( listboxMyLocusList.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=locus&src=snp");
		}
		else {
			
			//String selChr = listboxMySNPList.getSelectedItem().getLabel().split(":")[0].toUpperCase();
			//this.selectChr.setSelectedIndex( Integer.parseInt( listboxMySNPList.getSelectedItem().getLabel().split(":")[0].toUpperCase().replace("CHR ","").replace("CHR0","").replace("CHR","") )-1 );
			this.selectChr.setValue("");
			//this.selectChr.setValue("");
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
			AppContext.debug(listPos.get(i).getClass() + ", " +  listPos.get(i).toString());
			if( listPos.get(i).toString().equals(selpos) ) {
				poscol=i;
				break;
			}
		}
		
		AppContext.debug("posco=" +poscol);
		
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


public void onselectpositionOld() {
	
	String selpos = listboxPosition.getSelectedItem().getLabel();
	if(!selpos.isEmpty()) {
		Set alleles = null;
		
		if(this.checkboxAlignIndels.isChecked()) {
			alleles=new TreeSet();
			alleles.add("A");alleles.add("C");alleles.add("G");alleles.add("T");alleles.add("-");
		} else {
			if(queryResult.getMapPos2Alleleset() ==null || queryResult.getMapPos2Alleleset().get( BigDecimal.valueOf(Long.valueOf(selpos)))==null) {
				alleles=new TreeSet();
				alleles.add("A");alleles.add("C");alleles.add("G");alleles.add("T");alleles.add("-");

			} else
				alleles = queryResult.getMapPos2Alleleset().get( BigDecimal.valueOf(Long.valueOf(selpos)));
		}
		
		List listAllele= new ArrayList();
		listAllele.addAll(alleles);
        //listAllele.add("");listAllele.add("A");listAllele.add("T");listAllele.add("G");listAllele.add("C");
        listboxAllele.setModel(new SimpleListModel(listAllele));
        listboxAllele.setSelectedIndex(0);
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


@Listen("onClick = #buttonSNPSorter")
public void onclickSNPSorter() {
	
	try {

	workspace = (WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
	//String snplistname[] = listboxSNPListAlleles.getSelectedItem().getLabel().split(":");
	String snplistname  = listboxSNPListAlleles.getSelectedItem().getLabel().trim();
			
	//String contig = snplistname[0].trim();		
	Set posset = workspace.getSnpPositions( "any" , snplistname);
	Iterator<MultiReferencePositionImplAllelePvalue> itPosAllele=posset.iterator();
	Map<String,String> mapContigpos2Allele=new TreeMap();
	while(itPosAllele.hasNext()) {
		MultiReferencePositionImplAllelePvalue pos = itPosAllele.next();
		mapContigpos2Allele.put( pos.getContig() + "-" + pos.getPosition().toString().replace(".00","").replace(".0","") , pos.getAllele() );
	}

	
	
	java.util.List listPosition = new java.util.ArrayList();
	listPosition.add("");
	
	//listPosition.addAll( new TreeSet(queryResult.getMapPos2Alleleset().keySet()) );
	
	
	
	Object2StringMatrixModel model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
	int poscol=-1;
	List listPos = (List)model.getHeadAt(0);
	//for(int i=0; i<biglistboxArray.getCols(); i++) {
	Map<Integer,String> mapIdx2Allele=new HashMap();
	for(int i=0; i<model.getColumnSize(); i++) {
		String contigpos=listPos.get(i).toString();
		if(!mapContigpos2Allele.containsKey( contigpos )) continue;
		mapIdx2Allele.put(i, mapContigpos2Allele.get(contigpos) );
	}
	
	
	
//	
//	
//	//BigDecimal pos =  BigDecimal.valueOf(Long.valueOf(selpos));
//	//BigDecimal pos =  BigDecimal.valueOf(selpos);
//	BigDecimal pos = null;
//	
//	int selPosIdx=listboxPosition.getSelectedIndex();
//	if(selPosIdx>3) {
//		
//		try {
//		pos =  BigDecimal.valueOf(Double.valueOf(selpos.replace(".00","").replace(".0","")));
//		} catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//	
//	String alleleFilter = listboxAllele.getSelectedItem().getLabel();
//	
//	//Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
//	//VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
//	//Object[][] varalleles = table.getVaralleles();
//	
//	
//	//table.getVariantStringData().getListVariantsString();
	
	boolean include = false;
	List listInclude=new ArrayList();
	List listVarids=new ArrayList();
	List listMismatch=new ArrayList();
	List listNames = new ArrayList();
	

	Object2StringMatrixModel origModel = (Object2StringMatrixModel)this.biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)origModel.getData();
	Object[][] varalleles = table.getVaralleles();

	
	for(int i=0; i<origModel.getSize(); i ++) {
		
		int matchcount=0;
		Iterator<Integer> itIdx = mapIdx2Allele.keySet().iterator();
		while(itIdx.hasNext()) {
			Integer idx=itIdx.next();
			//Object alleleTable = ((Object[])origModel.getElementAt(i).get(idx))[idx];
			String filterallele=mapIdx2Allele.get(idx);
			if(filterallele.isEmpty() || filterallele.equals("N") || ( filterallele.contains( (String)varalleles[i][idx] ) && !((String)varalleles[i][idx]).isEmpty()  ) ) {
				matchcount++;
			}
		}
		
		listInclude.add(varalleles[i]);
		listVarids.add(table.getVarid()[i]);
		listNames.add(table.getVarname()[i]);
		//listMismatch.add(table.getVarmismatch()[i]);
		listMismatch.add( Double.valueOf(mapIdx2Allele.size()-matchcount));
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
	
	/*
	String activefilters=labelFilterResult.getValue().split("\\.\\.")[0].replace("Active Filter:", "").replace("None","").trim();
	if(!activefilters.isEmpty()) activefilters += ",";
	activefilters +=  selpos +"=" + alleleFilter ;
	labelFilterResult.setValue( activefilters + " .. " + listInclude.size() + "/" + queryRawResult.getListVariantsString().size() + " varieties" );
	*/
	
	labelFilterResult.setValue( "SNPLIST " +  listboxSNPListAlleles.getSelectedItem().getLabel());
	
	//labelFilterResult.setValue(listInclude.size() + "/" + queryRawResult.getListVariantsString().size() + " varieties");
	
	//gridBiglistheader.setModel( new SimpleListModel(  newtable.getRowHeaderList() )) ;
	//gridBiglistheader.setModel(new SimpleListModel( ((VariantAlignmentTableArraysImpl)newtable).getRowHeaderList( biglistboxRows) ));
	
	//Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	gridBiglistheader.setModel( new BiglistRowheaderModel(  biglistboxModel.getRowHeaderList(biglistboxRows) ));

	
	allelefreqlines=null;
	genotypefreqlines=null;
	calculateAlleleFrequencies();
	
	//gridBiglistheader.invalidate();
	//biglistboxArray.invalidate();
	
	gfffile=null;
	AppContext.debug("reset gff=null");
	
	} catch(Exception ex) {
		ex.printStackTrace();
	}
}

@Listen("onClick = #buttonFilterAllele")
public void onclickFilterAllele() {

	try {
	
	//Map<Integer, BigDecimal> mapIdx2Pos =  queryResult.getMapIdx2Pos();
	//mapIdx2Pos =  queryResult.getMapIdx2Pos();

	Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
	Iterator<Integer> itIdx = mapIdx2Pos.keySet().iterator();
	while(itIdx.hasNext()) {
		Integer idx = itIdx.next();
		mapPos2Idx.put( mapIdx2Pos.get(idx), idx);
	}

	String selpos = listboxPosition.getSelectedItem().getLabel();
	if(selpos.isEmpty()) {
		biglistboxModel= new Object2StringMatrixModel(varianttable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety(), gridBiglistheader );
		biglistboxModel.setHeaderRows(biglistboxRows, lastY);
		biglistboxArray.setModel(biglistboxModel);
		//gridBiglistheader.setModel(new BiglistRowheaderModel( ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList( biglistboxRows) ));
		gridBiglistheader.setModel(new BiglistRowheaderModel( biglistboxModel.getRowHeaderList( biglistboxRows) ));
	} else {
	
		//BigDecimal pos =  BigDecimal.valueOf(Long.valueOf(selpos));
		//BigDecimal pos =  BigDecimal.valueOf(selpos);
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
		
		//Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
		//VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
		//Object[][] varalleles = table.getVaralleles();
		
		
		//table.getVariantStringData().getListVariantsString();
		
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

//		
//		
//		if(selPosIdx>3) {
//			Integer colIdx = mapPos2Idx.get(pos);
//			//List
//			for(int irow=0; irow<matrixmodel.getSize(); irow++) {
//				//AppContext.debug("cellvalue = " + varalleles[irow][colIdx]);
//				if(allele.equals(varalleles[irow][colIdx])) {
//					listInclude.add(varalleles[irow]);
//					listVarids.add(table.getVarid()[irow]);
//					listNames.add(table.getVarname()[irow]);
//					listMismatch.add(table.getVarmismatch()[irow]);
//				};
//			}
//		} else {
//			
//			for(int irow=0; irow<matrixmodel.getSize(); irow++) {
//				//AppContext.debug("cellvalue = " + varalleles[irow][colIdx]);
//				boolean includeRow=false;
//				
//					switch (selPosIdx ) {
//						case 1: if(allele.equals(table.getVarname()[irow])) includeRow=true; break;
//						case 2: if(allele.equals(table.getVarid()[irow].toString())) includeRow=true; break;
//						case 3: if(Double.valueOf(allele).equals(table.getVarmismatch()[irow])) includeRow=true; break;
//					};
//					
//				 
//				if(includeRow) {
//					listInclude.add(varalleles[irow]);
//					listVarids.add(table.getVarid()[irow]);
//					listNames.add(table.getVarname()[irow]);
//					listMismatch.add(table.getVarmismatch()[irow]);
//				}
//
//			}
//		}
		
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
		
		//labelFilterResult.setValue(listInclude.size() + "/" + queryRawResult.getListVariantsString().size() + " varieties");
		
		//gridBiglistheader.setModel( new SimpleListModel(  newtable.getRowHeaderList() )) ;
		//gridBiglistheader.setModel(new SimpleListModel( ((VariantAlignmentTableArraysImpl)newtable).getRowHeaderList( biglistboxRows) ));
		
		//Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
		gridBiglistheader.setModel( new BiglistRowheaderModel(  biglistboxModel.getRowHeaderList(biglistboxRows) ));

		
		allelefreqlines=null;
		genotypefreqlines=null;
		calculateAlleleFrequencies();
		
		//gridBiglistheader.invalidate();
		//biglistboxArray.invalidate();
		
		gfffile=null;
		AppContext.debug("reset gff=null");
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

	//gridBiglistheader.setModel(new BiglistRowheaderModel( ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList( biglistboxRows) ));
	gridBiglistheader.setModel(new BiglistRowheaderModel( biglistboxModel.getRowHeaderList( biglistboxRows) ));
	labelFilterResult.setValue("None .. " +  queryRawResult.getListVariantsString().size() + " varieties");
	
	//Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	//gridBiglistheader.setModel( new SimpleListModel(  model.getRowHeaderList(biglistboxRows )));

	allelefreqlines=null;
	genotypefreqlines=null;
	calculateAlleleFrequencies();
	
	gfffile=null;
	
	} catch(Exception ex) {
		ex.printStackTrace();
	}
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

public void calculateAlleleFrequencies() {
	
	
	
	Map<String, Map<String, Map<String,Integer>>> mapPos2Subpop2Allele2Count=new TreeMap();
	Map<String, Map<String, Map<String,Integer>>> mapPos2Subpop2Genotype2Count=new TreeMap();
	
	Object2StringMatrixModel model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
	int poscol=-1;
	List listPos = (List)model.getHeadAt(0);
	Set subpops = new LinkedHashSet();
	subpops.add("all");
	//for(int i=0; i<biglistboxArray.getCols(); i++) {
	for(int i= this.frozenCols + 1 ; i<model.getColumnSize(); i++) {
		
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
			 
			 
			 
			 //String allele = (String)model.getCellAt(  model.getElementAt(j), i);
			 String allele  = ((Object[])model.getElementAt(j).get(i))[i].toString();
			 
			 allele=allele.trim();
			 //if(allele.isEmpty()) allele=" ";
			 if(allele.isEmpty()) continue;
			 
			 
			 if(allele.contains("/")) {
				 
				 
				 //String alleles12[]=allele.split("/");
				 String alleles12[]= new String[] { String.valueOf(allele.charAt(0)), String.valueOf(allele.charAt(2)) };
				 
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

				 
				 //AppContext.debug("i=" + i + "  j=" + j + " subpop=" + subpop + "  allele=" + allele + "  allelecount=" +  allelecount + "  allelecountall=" +  allelecountall);
				 
				 //AppContext.debug(subpop + "  "  + contigpos + "  " + allele +  " mapAllele2CountAll=" + mapAllele2CountAll);
				 
				 mapSubpop2Allele2Count.put("all", mapAllele2CountAll);

			 }
			 else {
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

	
	
//	allelefreqlines.linecountmajormodel = new DefaultCategoryModel(); 
//	allelefreqlines.linecountminormodel = new DefaultCategoryModel();
//	allelefreqlines.line3rdallelemodel = new DefaultCategoryModel();
//	allelefreqlines.linepercentmajormodel = new DefaultCategoryModel();
//	allelefreqlines.linepercentminormodel = new DefaultCategoryModel();
//	allelefreqlines.linepercent3rdmodel = new DefaultCategoryModel();
//	allelefreqlines.mapPos2Alleles = new HashMap();
//	
//	
//	Iterator<String> itSubpop =  subpops.iterator();
//	while(itSubpop.hasNext()) {
//		String subpop = itSubpop.next();
//		 
//		Iterator<String> itPos =  mapPos2Subpop2Allele2Count.keySet().iterator();
//		while(itPos.hasNext()) {
//			String pos = itPos.next();
//			Map mapSub2Allele =  mapPos2Subpop2Allele2Count.get(pos);
//			if(mapSub2Allele==null) continue;
//			
//			Map mapAllele2Count = (Map)mapSub2Allele.get(subpop);
//			if(mapAllele2Count==null) continue;
//			
//			// get major, minor allele
//			
//			List listCount = new ArrayList();
//			listCount.addAll(mapAllele2Count.values());
//			Collections.sort(listCount);
//			allelefreqlines.mapPos2Alleles.put( pos , listCount);
//			
//			Map mapCount2Allele=new TreeMap();
//			Iterator<String> itAl=mapAllele2Count.keySet().iterator();
//			while(itAl.hasNext()) {
//				String al = itAl.next();
//				mapCount2Allele.put(mapAllele2Count.get(al),al);
//			}
//			
//			int allallelecount = 0;
//			Iterator<Integer> itCount = listCount.iterator();
//			while(itCount.hasNext()) allallelecount+=itCount.next();
//			
//			//AppContext.debug( subpop + ", pos=" + pos + ",  count=" +  allallelecount );
//			
//			if(mapAllele2Count.keySet().size()==1) {
//				pos = pos + " [" + mapCount2Allele.get(listCount.get(0)) + " 100%" + "]";  
//				allelefreqlines.linecountmajormodel.setValue( subpop, pos, (Integer)listCount.get(0));
//				allelefreqlines.linepercentmajormodel.setValue( subpop, pos, 100);
//			} else if(mapAllele2Count.keySet().size()==2) {
//
//				int major = (Integer)listCount.get(1);
//				int minor = (Integer)listCount.get(0);
//				double percentmajor = major*100.0/allallelecount;
//				double percentminor = minor*100.0/allallelecount;
//
//				pos = pos + " [" + mapCount2Allele.get(listCount.get(1)) + " " + String.format( "%.2f", percentmajor) + "%" + ", " + 
//						mapCount2Allele.get(listCount.get(0)) + " " + String.format( "%.2f", percentminor) + "%]";  
//
//				allelefreqlines.linecountmajormodel.setValue( subpop, pos, major);
//				allelefreqlines.linecountminormodel.setValue( subpop, pos, minor);
//				allelefreqlines.linepercentmajormodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f", percentmajor)));
//				allelefreqlines.linepercentminormodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f", percentminor)));
//			} else if(mapAllele2Count.keySet().size()>2) {
//				int allelecount = mapAllele2Count.keySet().size();
//				int major = (Integer)listCount.get(allelecount-1);
//				int minor = (Integer)listCount.get(allelecount-2);
//				int allele3 = (Integer)listCount.get(allelecount-3);
//				double percentmajor = major*100.0/allallelecount;
//				double percentminor = minor*100.0/allallelecount;
//				double percent3rd = allele3*100.0/allallelecount;
//				
//				pos = pos + " [" + mapCount2Allele.get(listCount.get(allelecount-1)) + " " + String.format( "%.2f", percentmajor) + "%, " + 
//						mapCount2Allele.get(listCount.get(allelecount-2)) + " " + String.format( "%.2f", percentminor) + "%, " + 
//						mapCount2Allele.get(listCount.get(allelecount-3)) + " " + String.format( "%.2f", percent3rd) + "%]";  
//
//				allelefreqlines.linecountmajormodel.setValue( subpop, pos,  major);
//				allelefreqlines.linecountminormodel.setValue( subpop, pos, minor);
//				allelefreqlines.linepercentmajormodel.setValue( subpop, pos,  Double.valueOf(String.format( "%.2f", percentmajor)));
//				allelefreqlines.linepercentminormodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f",percentminor)));
//				allelefreqlines.line3rdallelemodel.setValue( subpop, pos, allele3);
//				allelefreqlines.linepercent3rdmodel.setValue( subpop, pos, Double.valueOf(String.format( "%.2f",percent3rd)));
//			}
//		}
//		
//	}
	
	

	updateAlleleFrequencyLines();
	
}


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

private void updateAlleleFrequencyLines( ) {

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

     //chart.getTooltip().setValueSuffix("°C");
    
    //LinePlotOptions plotOptions = chartAlleleFrequency.getPlotOptions().getLine();
    //plotOptions.getTooltip().setHeaderFormat("");
    //plotOptions.getTooltip().setPointFormat("<span style=\"font-size: 10px\"><br>{series.name}<br>{point.key}<br><b>{point.y}</b><br></span>");
    
    AxisLabels xlabels = chartAlleleFrequency.getXAxis().getLabels();
    xlabels.setRotation(-75);
    xlabels.setAlign("right");
    
    //plotOptions.getDataLabels().setFormat("{point.name}");
    //plotOptions.getDataLabels().setEnabled(false);


     Legend legend = chartAlleleFrequency.getLegend();
     legend.setLayout("vertical");
     legend.setAlign("right");
     legend.setVerticalAlign("middle");
     legend.setBorderWidth(0);
}



/*
@Listen("onClick = #buttonFilterAllele")
public void onclickFilterAllele() {

	Map<Integer, BigDecimal> mapIdx2Pos =  queryResult.getMapIdx2Pos();

	Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
	Iterator<Integer> itIdx = mapIdx2Pos.keySet().iterator();
	while(itIdx.hasNext()) {
		Integer idx = itIdx.next();
		mapPos2Idx.put( mapIdx2Pos.get(idx), idx);
	}

	String selpos = listboxPosition.getSelectedItem().getLabel();
	if(selpos.isEmpty()) {
		biglistboxArray.setModel( new Object2StringMatrixModel(varianttable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety() ));
		gridBiglistheader.setModel(new SimpleListModel( ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList( biglistboxRows) ));
	} else {
	
		BigDecimal pos =  BigDecimal.valueOf(Long.valueOf(selpos));
		
		String allele = listboxAllele.getSelectedItem().getLabel(); 
		Integer colIdx = mapPos2Idx.get(pos);
		
		Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
		VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
		Object[][] varalleles = table.getVaralleles();
		
		boolean include = false;
		List listInclude=new ArrayList();
		List listVarids=new ArrayList();
		List listMismatch=new ArrayList();
		List listNames = new ArrayList();
		for(int irow=0; irow<matrixmodel.getSize(); irow++) {
			//AppContext.debug("cellvalue = " + varalleles[irow][colIdx]);
			if(allele.equals(varalleles[irow][colIdx])) {
				listInclude.add(varalleles[irow]);
				listVarids.add(table.getVarid()[irow]);
				listNames.add(table.getVarname()[irow]);
				listMismatch.add(table.getVarmismatch()[irow]);
			};
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
		
		biglistboxArray.setModel( new Object2StringMatrixModel(newtable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety()));
		labelFilterResult.setValue(listInclude.size() + "/" + queryRawResult.getListVariantsString().size() + " varieties");
		
		//gridBiglistheader.setModel( new SimpleListModel(  newtable.getRowHeaderList() )) ;
		gridBiglistheader.setModel(new SimpleListModel( ((VariantAlignmentTableArraysImpl)newtable).getRowHeaderList( biglistboxRows) ));

		gridBiglistheader.invalidate();
		//biglistboxArray.invalidate();
		
	}
	
}
*/


//@Listen("onSelect = #comboSubpopulation")    
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
		this.searchButton.setDisabled(true);
		
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
		chrlength=length.intValue();
		
		checkboxCoreSNP.setDisabled(false);
		this.searchButton.setDisabled(false);
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


@Listen("onSelect = #tabMSA")    
public void onselectTabMSA() {
	
	iframeAlignment.invalidate();
	

}

/**
 * JBrowse tab selected    
 */
@Listen("onSelect = #tabJbrowse")    
public void onselectTabJbrowse() {
	//tabboxDisplay.setHeight( "100%");
	if(urljbrowse!=null  ) {

		if(gfffile!=null){
			//same data, don't recreate gff
			return;
		}

		AppContext.debug("loading " + urljbrowse);
		
		Clients.showBusy("Loading JBrowse");
		if(tallJbrowse) {
			gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";	

			
			int jbrowsebp_margin =     (intStop.getValue()-intStart.getValue())*AppContext.getJBrowseMargin();
			int start = intStart.getValue()-jbrowsebp_margin;
			if(start<0) start=1;

			//VariantStringData queryDisplayData = queryRawResult;
			VariantStringData queryDisplayData = queryResult;
			
			//if(queryResult.getListVariantsString()!=null) {
			if(queryDisplayData!=null) {
				
				try {
				
				//queryRawResult.merge();
					
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
					//listGFF.addAll( createSNPPointStringVarietyGFF(listGFFSNP,  queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) );
					//listGFF.addAll( createIndelStringVarietyGFF(listGFFIndel,   queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected() || !checkboxMismatchOnly.isChecked() ) );
					listGFF.addAll( createSNPPointStringVarietyGFFFromTable(listGFFSNP,  1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) );
					listGFF.addAll( createIndelStringVarietyGFFFromTable(listGFFIndel,   1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected() || !checkboxMismatchOnly.isChecked() ) );
				}
				else if(this.checkboxSNP.isChecked())
				{
					listGFF = createSNPStringVarietyGFFFromTable(queryDisplayData.getListVariantsString(),    1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) ;
					//listGFF = createSNPStringVarietyGFF(queryDisplayData.getListVariantsString(),   queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) ;
				}
				else if(this.checkboxIndel.isChecked())
				{
					listGFF = createIndelStringVarietyGFFFromTable(queryDisplayData.getListVariantsString(),   1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  );
					//listGFF = createIndelStringVarietyGFF(queryDisplayData.getListVariantsString(),  queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  );
				}
				
				// update positions and refnuc in list gff 
				// listGFF
				
				if(!this.listboxReference.equals(AppContext.getDefaultOrganism())) {
					if(!this.checkboxIndel.isChecked())
						listGFF = convertGFFReferencePosInterval(listGFF);
					else
						listGFF = convertGFFReferencePosPoint(listGFF);
				}
				
				writeGFF(listGFF, gfffile);
				
				AppContext.resetTimer("write gff");
				AppContext.resetTimer("viewing " + urljbrowse.replace("GFF_FILE" ,gfffile) );
				urlphylo = urlphylo.replace("GFF_FILE",  gfffile.replace(".gff", "") ); //  = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=GFF_FILE&mindist=" + intPhyloMindist.getValue();

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
				//if(radioGraySynonymous.isSelected()) divBlackSyn.setVisible(true);
				//divBlackSyn.setVisible( this.radioNonsynHighlights.isSelected() || this.radioNonsynSnpsPlusSplice.isSelected() ||  this.radioNonsynSnps.isSelected() );
				
				
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
	}
    //urljbrowse=null;
		
}

private List convertGFFReferencePosPoint(List listNPB) {
	
	List newGFF= null;
	if(queryResult.isNipponbareReference()) {
		newGFF = listNPB;
	} else {
		newGFF = new ArrayList();
		
		Map mapMSU7Pos2ConvertedPos = queryResult.getMapMSU7Pos2ConvertedPos();
		
		String newSrc = this.selectChr.getValue();
		String org = this.listboxReference.getSelectedItem().getLabel(); 
		if(org.equals("Japonica nipponbare")) 
				newSrc += "|msu7";
		else if(org.equals("IR64-21"))
				newSrc += "|ir6421v1";
		else if(org.equals("93-11"))
				newSrc += "|9311v1";
		else if(org.equals("DJ123"))
				newSrc += "|dj123v1";
		else if(org.equals("Kasalath"))
				newSrc += "|kasv1"; 
	
		
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
			//Long newStart = newpos + (npbGFF.getStart() - npbGFF.getPosition());
			Long newEnd = newpos + (npbGFF.getEnd() - npbGFF.getStart()  );
			//Long newStart = newpos;
			//Long newEnd = newpos + 1;
			
			String npbleft[] = npbGFF.getLeft().split("\t");
			String newRight = npbGFF.getRight().replace("Position=" + npbGFF.getPosition().longValue() ,  "Position=" + newpos).replace("\n","") +
					";Contig=" + refpos.getToContig() + ";Organism=" + refpos.getToOrganism() + ";NPBPosition=" +  npbGFF.getPosition().longValue() + ";NPBContig=" + npbleft[0] + "\n";
			
			//long[] bounds = mapPos2Bounds.get( refpos.getPosition().longValue() );
			
			
			
			//GFF convertedGFF = new GFF(newSrc + "\t" + npbleft[1] +"\t" + npbleft[2] +"\t",newRight,  newpos , newEnd,  newpos );
			GFF convertedGFF = new GFF(newSrc + "\t" + npbleft[1] +"\t" + npbleft[2] +"\t",newRight,  newpos , newEnd,  newpos );
			newGFF.add( convertedGFF );

		}
		
	}
	
	return newGFF; 
	
}

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
			if(refpos==null) continue;
			setPositions.add( refpos.getToPosition().longValue() );
		}
		
		Map<Long, long[]> mapPos2Bounds = createMapGFFPosBounds(setPositions , BigDecimal.valueOf(Long.valueOf(intStart.getValue())), BigDecimal.valueOf(Long.valueOf(intStop.getValue()))); 
		
		String newSrc = this.selectChr.getValue();
		String org = this.listboxReference.getSelectedItem().getLabel(); 
		if(org.equals("Japonica nipponbare")) 
				newSrc += "|msu7";
		else if(org.equals("IR64-21"))
				newSrc += "|ir6421v1";
		else if(org.equals("93-11"))
				newSrc += "|9311v1";
		else if(org.equals("DJ123"))
				newSrc += "|dj123v1";
		else if(org.equals("Kasalath"))
				newSrc += "|kasv1"; 
	
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


/**
 * JBrowse using phylotree ordering, visible only after Phylotree is computed
 */
//@Listen("onSelect = #tabJBrowsePhylo")    
//public void onselectTabJbrowsePhylo() {
//
//	//tabboxDisplay.setHeight( "100%");
//	AppContext.debug("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
//	Clients.showBusy("Calculating JBrowsePhylo");
//	if(urljbrowsephylo==null) {	
//			
//			if(tallJbrowse) {
//				updateJBrowsePhylo( selectChr.getValue().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
//				Map mapVariety2Order = genotype.orderVarietiesFromPhylotree(gfffile.replace("gff",""));
//				List newpagelist;
//				
//				int jbrowsebp_margin =     (intStop.getValue()-intStart.getValue())*AppContext.getJBrowseMargin();
//				newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue()-jbrowsebp_margin, intStop.getValue()+jbrowsebp_margin, Integer.valueOf(selectChr.getValue())); //,  true , -1,  -1 );
//				AppContext.debug(mapVariety2Order.size() + " varieties for ordering;  "  + newpagelist.size() + " snps for phylojbrowse gff");
//				//createSNPStringVarietyGFF(newpagelist, gfffile + ".phylo.gff" , mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()-jbrowsebp_margin), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  false );
//				List listGFF = createSNPStringVarietyGFF(newpagelist,  mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()-jbrowsebp_margin), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  false );
//				writeGFF(listGFF, gfffile + ".phylo.gff");
//				
//				AppContext.resetTimer("writing  " + gfffile+".phylo.gff");
//			}
//		}
//	
//	
//	if(urljbrowsephylo!=null) {
//
//		AppContext.debug("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
//		while(true) {
//			File f = new File( AppContext.getTempDir() + "/" + gfffile.replace(".gff", ".newick") );
//			if(f.exists()) break;
//			try {
//				Thread.sleep(5000);
//				AppContext.debug("waiting newick file");
//			}
//			catch(Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//		
//		iframeJbrowsePhylo.setSrc( urljbrowsephylo );
//	    msgJbrowsePhylo.setVisible(true);
//	    iframeJbrowsePhylo.setVisible(true);
//
//	}
//    Clients.clearBusy();
//	urljbrowsephylo=null;
//		
//}

/**
 * Phylogenetic tree tab selected 
 */


@Listen("onClick = #buttonRenderTree")
//@Listen("onSelect = #tabPhylo")    
public void onselectTabPhylo() {
	//tabboxDisplay.setHeight( "100%");
	AppContext.debug("loading phylotree " + urlphylo);
    //show_phylotree(selectChr.getValue().toUpperCase().replace("CHR0","").replace("CHR",""), intStart.getValue().toString() , intStop.getValue().toString()  );

	if(urlphylo!=null) {
		
		Clients.showBusy("Computing Phylogenetic Tree");
		iframePhylotree.setSrc( urlphylo );
		
		Clients.clearBusy();
	}
	
	iframePhylotree.setVisible(true);
	tabJBrowsePhylo.setVisible(true);
	
	urlphylo=null;

	
}
    

/**
 * Clears all inputs
 */
@Listen("onClick = #resetButton")    
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
    
    this.checkboxCoreSNP.setChecked(true);
    this.selectPhyloTopN.setSelectedIndex(0);
    
    this.radioColorMismatch.setSelected(true);
    
    msgbox.setValue("Select varieties, then set chromosome region or gene locus");
    setchrlength();
}


private void download2VarsList(String filename, String delimiter) {
	AppContext.debug("downloading from 2vars table...");
	StringBuffer buff = new StringBuffer();
	
	Iterator itHead =  listboxSnpresult.getHeads().iterator();
	while(itHead.hasNext()) {
		Component comp = (Component)itHead.next();
		if(comp instanceof Listhead) {
			Listhead cols = ( Listhead )comp;
			Iterator itHeadcol = cols.getChildren().iterator();
			while(itHeadcol.hasNext()) {
				Listheader header =  (Listheader)itHeadcol.next();
				buff.append( header.getLabel() );
				if(itHeadcol.hasNext()) buff.append(delimiter);
			}
		}
		if(comp instanceof Auxhead) {
			Auxhead cols = ( Auxhead )comp;
			Iterator itHeadcol = cols.getChildren().iterator();
			while(itHeadcol.hasNext()) {
				Auxheader header =  (Auxheader)itHeadcol.next();
				buff.append( header.getLabel() );
				if(itHeadcol.hasNext()) buff.append(delimiter);
			}
		} else if(comp instanceof Columns) {
			Columns cols = ( Columns )comp;
			Iterator itCol = cols.getChildren().iterator();
			while(itCol.hasNext()) {
				Column col = (Column)itCol.next();
				buff.append(col.getLabel());
				if(itCol.hasNext()) buff.append(delimiter);
			}

		}
		buff.append("\n");
	}
	
	
	Iterator<Snps2Vars> itDisplay = list2Vars.iterator();
	while(itDisplay.hasNext()) {
		Snps2Vars snppos = itDisplay.next();
		
		String var1allele2 = snppos.getVar1nuc2();
		if(var1allele2!=null && !var1allele2.isEmpty()) var1allele2= "/" + var1allele2;
		else var1allele2="";
		String var2allele2 = snppos.getVar2nuc2();
		if(var2allele2!=null && !var2allele2.isEmpty()) var2allele2= "/" + var1allele2;
		else  var2allele2="";
		
		buff.append(snppos.getChr() + delimiter +  snppos.getPos() + delimiter + snppos.getRefnuc() + delimiter + snppos.getVar1nuc() + var1allele2  + delimiter + snppos.getVar2nuc() + var2allele2 + "\n" );
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
	BigDecimal[] positions = table.getPosition(); 
	for(int i=0; i<positions.length; i++) {
		if(contigs!=null) buff.append( contigs[i] ).append("-");
		buff.append(positions[i]);
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
	BigDecimal[] positions = table.getPosition();
	String refs[] =  table.getReference();
	for(int i=0; i<positions.length; i++) {
		if(!refs[i].equals("-")) {
			int pos = positions[i].intValue();
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
	BigDecimal[] positions = table.getPosition();
	String refs[] =  table.getReference();
	String contigs[] = table.getContigs();
	for(int i=0; i<positions.length; i++) {
		if(!refs[i].equals("-")) {
			int pos = positions[i].intValue();
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
		AppContext.debug("File download complete! Saved to: "+ filename + "-plink-" +  ".zip");


		//String filetype = "text/plain";
		//Filedownload.save(  buff.toString(), filetype , filename + ".ped");
		
		//AppContext.debug("ped: "+ buff.toString() );
		//AppContext.debug("File download complete! Saved to: "+filename);
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
 * Download Tab format
 */
@Listen("onClick = #buttonDownloadTab")    
public void downloadTab()    {
	 //downloadDelimited("snpfile.txt", "\t"); 
	
	//if(snpallvarsresult.isVisible())
		//downloadTable("snp3kvars-" + queryFilename() + ".txt", "\t");
	if(biglistboxArray.isVisible())
		downloadBigListbox("snp3kvars-" + queryFilename() + ".txt", "\t");
	else if(listboxSnpresult.isVisible())
		download2VarsList("snp2vars-" +   queryFilename() + ".txt", "\t");
}


@Listen("onClick = #buttonDownloadPlink")    
public void downloadPlink()    {
	 //downloadDelimited("snpfile.txt", "\t"); 
	
	//if(snpallvarsresult.isVisible())
		//downloadTable("snp3kvars-" + queryFilename() + ".txt", "\t");
	if(biglistboxArray.isVisible())
		downloadBigListboxPlinkZip("snp3kvars-" + queryFilename() + "-" +  AppContext.createTempFilename() );
	
}

/**
 * Download CSV format
 */
@Listen("onClick = #buttonDownloadCsv")    
public void downloadCsv()    {
	//downloadDelimited("snpfile.csv",","); 
	//if(snpallvarsresult.isVisible())
		//downloadTable("snp3kvars-" + queryFilename() + ".csv", ",");
	if(this.biglistboxArray.isVisible())
		downloadBigListbox("snp3kvars-" + queryFilename() + ".csv", ",");
	else if(listboxSnpresult.isVisible())
		download2VarsList("snp2vars-" +   queryFilename() + ".csv", ",");
}

/**
 * Generate download file
 * @param filename
 * @param limitchar
 */
private void downloadDelimited(String filename, String limitchar)    {

	throw new RuntimeException("downloadDelimited??"); 
	/*
	Clients.showBusy("Fetching from Database");
	searchWithServerpaging(filename, limitchar);
	Clients.clearBusy();
	*/
}

/**
 * Write SNP for 2 varieties in file
 * @param filename	filename to write
 * @param header	to write in first line
 * @param var1		variety 1
 * @param var2		variety 2
 * @param delimiter	
 * @param listSNPs	SNPs list
 */
private void writeSNP2Lines2File(String filename, String header,  String var1, String var2, String delimiter, List<Snps2Vars> listSNPs )
{
	
	Iterator<Snps2Vars> it=listSNPs.iterator();
	StringBuffer buff= new StringBuffer();
	
	if(header!=null && !header.isEmpty()) {
		buff.append(header); buff.append("\n");
	}
	
	buff.append("CHR"); buff.append(delimiter); buff.append("POS");  buff.append(delimiter);
	buff.append("REF");  buff.append(delimiter); 
	buff.append(var1.toUpperCase());  buff.append(delimiter); buff.append(var2.toUpperCase());
	buff.append("\n");
	
		while(it.hasNext()) {
			Snps2Vars snp2lines= it.next();
			buff.append( snp2lines.getChr() );  buff.append(delimiter);
			buff.append( snp2lines.getPos() );  buff.append(delimiter);
			buff.append( snp2lines.getRefnuc() );  buff.append(delimiter);
			buff.append( snp2lines.getVar1nuc() );  buff.append(delimiter);
			buff.append( snp2lines.getVar2nuc());  buff.append("\n");		
			
		}		
		
		try {
			String filetype = "text/plain";
			if(delimiter.equals(",")) filetype="text/csv";
				
			Filedownload.save( buff.toString(), filetype, filename);
			AppContext.debug("File download complete! Saved to: "+ filename);
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
		
}

/**
 * Search button clicked
 */


@Listen("onClick = #searchButton")    
public void searchWithServerpaging()    {
	
	//Clients.showBusy("Fetching from Database");
	try {
		
		AppContext.debug("searchGenotype clicked!");
		searchWithServerpaging(null,null);
		
	} catch (Exception ex)
	{
		ex.printStackTrace();
		Messagebox.show(ex.getMessage(), "Exception", Messagebox.OK, Messagebox.ERROR);
	}
	//Clients.clearBusy();
	
	/*
	try {
		showBiglist();
	} catch (Exception ex)
	{
		ex.printStackTrace();
	}
	*/
	
	
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


@Listen("onClick = #checkboxSNP")
public void onclickSNP() {
	//this.checkboxSpliceSNP.setDisabled( !this.checkboxSNP.isChecked() );
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
	
	Object2StringMatrixRenderer renderer  = new Object2StringMatrixRenderer(queryResult,  varietyfacade.getMapId2Variety(), params);
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
	updateJBrowse( selectChr.getValue().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), this.comboGene.getValue());
	
	
	Object2StringMatrixModel model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
	int poscol=-1;
	List listPos = (List)model.getHeadAt(0);
	//for(int i=0; i<biglistboxArray.getCols(); i++) {
	mapIdx2Pos=new HashMap();
	List listPosition=new ArrayList();
	for(int i=0; i<model.getColumnSize(); i++) {
		if(listPos.get(i).toString().isEmpty()) continue;
		listPosition.add( listPos.get(i).toString() );
		try {
		mapIdx2Pos.put(i, BigDecimal.valueOf(Double.valueOf(listPos.get(i).toString()) ));
		} catch(Exception ex) {
			
		}
	}
	listboxPosition.setModel( new SimpleListModel(listPosition));
	labelFilterResult.setValue("None .. " + queryResult.getListVariantsString().size() + " varieties");
	
	
	
	} catch (Exception ex) {
		ex.printStackTrace();
		Messagebox.show(ex.getMessage(), "Exception in updateBiglistboxArray", Messagebox.OK, Messagebox.ERROR);
	}
}



/** Search SNP and render the result in webclient or write it to file
 * 
 * @param filename	filename of output, if null will reder to client
 * @param delimiter	delimeter of output
 *
 *
 * Four main branches in this function
 * 1. Gene constraint
 *		1.a All varieties
 *		1.b 2 Varieties
 * 2. Region (chr, start, end) constraint
 * 		2.a All varieties
 * 		2.b 2 varieties
 *
 *
 *	in each branch, these are the operations
 *  	
	gene, allvariety	
	1. genotype.countSNPMismatchesInAlllVarieties(Integer.valueOf(gene.getFmin().toPlainString())  ,Integer.valueOf( gene.getFmax().toPlainString() ), gene.getChr().toUpperCase().replace("CHR", ""), true);		!! not required because step 2 call this
 *  2.  genotype.getSNPinAllVarieties(Integer.valueOf(gene.getFmin().toPlainString())  ,Integer.valueOf( gene.getFmax().toPlainString() ), gene.getChr().toUpperCase().replace("CHR", ""));
 *  3. if(writetofile)  updateAllvarsList(listSNPs,true,filename,delimiter,"GENE: " + comboGene.getValue().trim().toUpperCase()); return
 *	4. updateAllvarsList(listSNPs,true)
 	5. createSNPVarietyGFF(listSNPs, gfffile, genotype.getMapVariety2Order(), bpmargin,Integer.parseInt(gene.getFmin().toString()),Integer.parseInt(gene.getFmax().toString()));

	gene, 2 variety
	1. genotype.getSNPinVarieties(var1, var2, comboGene.getValue().trim(), 0, mode);				
	2. if(writetofile) writeSNP2Lines2File(filename, "GENE: " + comboGene.getValue().trim().toUpperCase() ,   var1,  var2,  delimiter,  listSNPs); return;
	3. createSNP2linesGFF(listSNPs, gfffile );


	region, allvariety
	1. check inputs
	2. genotype.countSNPMismatchesInAlllVarieties(intStart.getValue(), intStop.getValue(), chr, true);
	3. if(writetofile) {
				newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, 0  );
			        updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() );						
				return;
			    }
	4. if(serverpaging) {
 				newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, snpallvars_pagesize  );		
				setAllvargridforDBPaging();
				updateAllvarsList(newpagelist,true, null,null,null, 1, snpallvars_pagesize );				    
 		} else
		{
			newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, 0  );
			setAllvargridforGridPaging();
			updateAllvarsList(newpagelist,true, null,null,null, 1, 0 );	
			listSNPs = newpagelist;
		}

	region, 2 varieties
	1. genotype.getSNPinVarieties(var1,var2, intStart.getValue(), intStop.getValue(), chr, mode);
	2. if(writetofile) writeSNP2Lines2File(filename, "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() ,   var1,  var2,  delimiter,  listSNPs); return;
	3. createSNP2linesGFF(listSNPs, gfffile );


	render2varsList

 *
 *
 *	if(serverpaging) ON showPage
 *	1. newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getValue().trim(), desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize  );
    2. updateAllvarsList(newpagelist,false,null,null,null, desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize );		
    
    
    
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
	

	//AppContext.debug("setVarieties=" +  setVarieties);
	
	

	/*
	private Radio radioAllSnps;
	@Wire
	private Radio radioNonsynSnps;
	@Wire
	private Radio radioNonsynHighlights;
	@Wire
	private Checkbox checkboxSpliceSnps;
*/
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
	}
	
	params.setColLoci(locuslist);
	params.setOrganism(listboxReference.getSelectedItem().getLabel(), this.checkboxShowNPBPosition.isChecked(), true);
	
	return params;
	
}

public void searchWithServerpaging(String filename, String delimiter)    {
	
	// true if output is written to file
	boolean writetofile= filename!=null && !filename.isEmpty();
	
	// to facilitate render on Tab select for JBrowse and phylogenetic tree
	GenotypeFacade.snpQueryMode mode=null;
	

	try {
	
	if(writetofile)
		AppContext.debug("Writing to file");
	else
	{
		
		// empty table from previous results
		//snpallvarsresult.setModel(new SimpleListModel(new java.util.ArrayList()));
		listboxSnpresult.setModel(new SimpleListModel(new java.util.ArrayList()));

		// hide the tables
		//snpallvarsresultpaging.setVisible(false);
	   // snpallvarsresult.setVisible(false);
		listboxSnpresult.setVisible(false);
		gridBiglistheader.setVisible(false);
		biglistboxArray.setVisible(false);

	    //snpallvarsresult.setWidth("1000px");
		listboxSnpresult.setWidth("1000px");
	    
	    tabTable.setSelected(true);
	    tabJbrowse.setVisible(false);
	    tabMSA.setVisible(false);
	    tabPhylo.setVisible(false);
	    tabJBrowsePhylo.setVisible(false);
	
	    
		gfffile=null;
		urljbrowse = null;	// if has URL address, will render on TabSelect on JBrowse
		urlphylo = null;   // if has URL address, will render on TabSelect on Phylotree
		tallJbrowse = false;	// jbrowse if tall (for all variety) or short (for 2 varieties)
		urljbrowsephylo = null;
		snpallvars_pagesize=100;	 // initial rows per page
		//origAllVarsListModel = null;
		queryResult = null;
		list2Vars = null;
		filteredList = null;
		hboxFilterAllele.setVisible(false);
		hboxDownload.setVisible(false);
		
		labelFilterResult.setValue("");
		lastY=0;

	}
	
	
	//tabTable.setWidth("100%");
	//tabboxDisplay.setWidth("1000px");
	//win.setWidth(labelScreenWidth.getValue());
	
	//AppContext.debug("origwidth=" + labelScreenWidth.getValue());
	
		genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	
		String selcontig = selectChr.getValue();
		if(this.listboxMySNPList.getSelectedIndex()>0)
			msgbox.setValue("SEARCHING: in chromosome " + selcontig + " , list " + listboxMySNPList.getSelectedItem().getLabel() );
		else if(intStart.getValue()!=null && intStop.getValue()!=null && !selcontig.isEmpty())
			msgbox.setValue("SEARCHING: in chromosome " + selcontig+ " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
		else 
			msgbox.setValue("SEARCHING: in chromosome " +selcontig);
		
		String var1= comboVar1.getValue().trim().toUpperCase();
		String var2 =  comboVar2.getValue().trim().toUpperCase();
		Set setVarieties = null;
		
		String sSubpopulation = null;
		
		if(checkboxAllvarieties.isChecked()) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		
/*			
		} else if( comboSubpopulation.getValue()!=null &&  !comboSubpopulation.getValue().isEmpty() ) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = genotype.getVarietiesForSubpopulation(comboSubpopulation.getValue().trim());
			*/
		} else if( listboxSubpopulation.getSelectedIndex()>0) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = genotype.getVarietiesForSubpopulation( listboxSubpopulation.getSelectedItem().getLabel() );
			
			if(listboxSubpopulation.getSelectedIndex()>1) sSubpopulation=listboxSubpopulation.getSelectedItem().getLabel();

		} else if( listboxMyVarieties.getSelectedIndex()>0) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = workspace.getVarieties( listboxMyVarieties.getSelectedItem().getLabel()  );
			//mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			//setVarieties = genotype.getVarietiesForSubpopulation(comboSubpopulation.getValue().trim());
		}
		else {
			
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
			
			/*
			if(!var1.isEmpty() && !var2.isEmpty()) {
				if(checkboxMismatchOnly.isChecked())
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
				else
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLREFPOS; //SNPQUERY_REFERENCE;
			}
			else 
			*/ if(var1.isEmpty() || var2.isEmpty() )
			{
				//Messagebox.show("At least one variety is required", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				Messagebox.show("Two varieties are required for comparison, or check All Varieties", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				
				return;	
			}
			//else
			//	mode = GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE;
		}

		// initialize empty SNP list
		List listSNPs = new java.util.ArrayList();

		String genename = comboGene.getValue().trim().toUpperCase();
		//gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
		
		AppContext.startTimer();

		String sLocus = null;
		if( !genename.isEmpty() )
		{
			String genename2 = comboGene.getValue().trim();
			Gene gene2 =  genotype.getGeneFromName( genename2, this.listboxReference.getSelectedItem().getLabel());
			
			
			intStart.setValue(gene2.getFmin() );	
			intStop.setValue(gene2.getFmax() );	
			//selectChr.setSelectedIndex(  Integer.valueOf(  gene2.getChr().toUpperCase().replace("CHR0","").replace("CHR","")  ));
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

				// if length > maxlengthUni, change to core
				
				// if length > maxlengthCore, not allowed

				
				int maxlength = -1;
				String limitmsg = "";

				if(!checkboxCoreSNP.isChecked()) {
					maxlength = AppContext.getMaxlengthUni();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for All Snps, or " + AppContext.getMaxlengthCore()/1000 + " kbp for Core Snps, All Varieties query.";
				} else {
					maxlength = AppContext.getMaxlengthCore();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Core Snps, all varieties query";
				}
				
				/*
				if(checkboxAllvarieties.isChecked()  && !checkboxCoreSNP.isChecked()) {
					maxlength = AppContext.getMaxlengthUni();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for All Snps, or " + AppContext.getMaxlengthCore()/1000 + " kbp for Core Snps, All Varieties query.";
				} else if(checkboxAllvarieties.isChecked()  && checkboxCoreSNP.isChecked() ) {
					maxlength = AppContext.getMaxlengthCore();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Core Snps, all varieties query";
				} else
				{
					maxlength = AppContext.getMaxlengthPairwise();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Pairwise variety query";
				}
				*/
				
				
				
				int querylength = intStop.getValue()-intStart.getValue(); 
				//if(!checkboxCoreSNP.isChecked() && querylength > maxlength )
				if( querylength > maxlength )
				{
					if( querylength > AppContext.getMaxlengthCore() ) { 
						Messagebox.show(limitmsg, "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
						msgbox.setStyle( "font-color:red" );
						return;
					} else
					{
						
						//Messagebox.show("Query too long for all SNPs, Use Core SNPs instead", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
						if( Messagebox.show("Query too long for all SNPs, will use Core SNPs instead. Do you want to porceed?", "OPERATION NOT ALLOWED", Messagebox.NO | Messagebox.YES , Messagebox.QUESTION)
								== Messagebox.YES  )
						{
							checkboxCoreSNP.setChecked(true);
							//genotype.setCore( true );
							maxlength =  AppContext.getMaxlengthCore();
						}
						else return;
						
					}
				}   
				
			}
			
			


			
//			GenotypeQueryParams params = new GenotypeQueryParams(setVarieties, selectChr.getValue(), new Long( intStart.getValue()),
//					new Long( intStop.intValue() ) ,  checkboxSNP.isChecked() , checkboxIndel.isChecked(), checkboxCoreSNP.isChecked() ,
//					checkboxMismatchOnly.isChecked(), snpposlist,  sSubpopulation, sLocus, checkboxAlignIndels.isChecked());			

			GenotypeQueryParams params =  fillGenotypeQueryParams();
			

			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){


				List  newpagelist; 
				
				
//				modelBiglisttable = new FakerMatrixModel();
//				modelBiglistArray = null;
//				if(this.checkboxAlignIndels.isChecked())
//					modelBiglistArray = new VariantAlignmentTableArraysImpl();
//				else
//					modelBiglistArray = new VariantTableArraysImpl();
				
				
				varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade,"VarietyFacade");
				//VariantStringData vardata = null;  
				varianttable =  new VariantAlignmentTableArraysImpl();
				//VariantStringData viewVariantStringData=null;
				
			//	try {
					
					queryRawResult = genotype.queryGenotype( params);

					if(!params.getOrganism().equals(AppContext.getDefaultOrganism())) {
					if(	queryRawResult.getSnpstringdata()!=null) {
						AppContext.debug( "queryRawResult.getSnpstringdata().getMapMSU7Pos2ConvertedPos()=" + queryRawResult.getSnpstringdata().getMapMSU7Pos2ConvertedPos().size() + ": " + queryRawResult.getSnpstringdata().getMapMSU7Pos2ConvertedPos());
					}
					
					if(	queryRawResult.getIndelstringdata()!=null) {
						AppContext.debug( "queryRawResult.getIndelstringdata().getMapMSU7Pos2ConvertedPos()=" +  queryRawResult.getIndelstringdata().getMapMSU7Pos2ConvertedPos().size() + ": " +  queryRawResult.getIndelstringdata().getMapMSU7Pos2ConvertedPos());
					}
					}
					
					varianttable = genotype.fillGenotypeTable(varianttable , queryRawResult, params);
					
					//if(!params.getOrganism().equals(AppContext.getDefaultOrganism())) {
					//AppContext.debug("varianttable.getVariantStringData().getMapMSU7Pos2ConvertedPos()=" +  varianttable.getVariantStringData().getMapMSU7Pos2ConvertedPos().size() + ": " +  varianttable.getVariantStringData().getMapMSU7Pos2ConvertedPos() );
					//}
					
					queryResult = varianttable.getVariantStringData();
					
					listSNPs = queryRawResult.getListVariantsString();
					
					//if(queryResult.getListPos()!=null && queryResult.getListPos().size()>0) {
					if(true) {
					
						Object2StringMatrixRenderer renderer  = new Object2StringMatrixRenderer(queryResult,  varietyfacade.getMapId2Variety(), params);
	
						//biglistboxArray.setFrozenCols(0);
						//biglistboxArray.setFixFrozenCols(false);
						//biglistboxArray.setAutoCols(true);
						//biglistboxArray.setHeight( "400px");
	
						//biglistboxArray.setFixFrozenCols(true);
						//biglistboxArray.setVisible(true);
						//biglistboxArray.setSortAscending(new MyMatrixComparatorProvider<List<String>>(true));
						//biglistboxArray.setSortDescending(new MyMatrixComparatorProvider<List<String>>(false));
						biglistboxArray.setRowHeight("29px");
						
						
						//biglistboxArray.setHeight( "410px");
						//tabboxDisplay.setWidth( "1200px" );
						biglistboxArray.setWidth( "1100px" );
						
						//if(varianttable.getVariantStringData().getMapVariety2Order().size()==0)
						//	tabboxDisplay.setWidth( "1000px" );
						//else tabboxDisplay.setWidth( "100%" );
						
						 
							
//							if(checkboxIndel.isChecked() &&  !checkboxAlignIndels.isChecked())
//								biglistboxArray.setColWidth("60px");
//							else biglistboxArray.setColWidth("30px");
						
						int biglistcolwidth=60;
						if(checkboxIndel.isChecked() &&  !checkboxAlignIndels.isChecked()) {
							if( queryResult.getListPos().size() < (1000/60-3) )
								//biglistboxArray.setColWidth(  Integer.toString( 1000/(3+queryResult.getListPos().size())) + "px");
								biglistcolwidth = 1000/(3+queryResult.getListPos().size());
							else // biglistboxArray.setColWidth("60px");
								biglistcolwidth = 60;
						}
						else {
							if( queryResult.getListPos().size() < (1000/30-3) )
								//biglistboxArray.setColWidth(  Integer.toString( 1000/(3+queryResult.getListPos().size())) + "px");
								biglistcolwidth = 1000/(3+queryResult.getListPos().size());
							else //biglistboxArray.setColWidth("30px");
								biglistcolwidth = 30;
						}
						
						biglistboxArray.setColWidth(biglistcolwidth + "px");
						
			
						biglistboxArray.setMatrixRenderer(renderer);
						biglistboxModel=new Object2StringMatrixModel(varianttable, fillGenotypeQueryParams(),  varietyfacade.getMapId2Variety(), gridBiglistheader);
						biglistboxModel.setHeaderRows(biglistboxRows, lastY);
						biglistboxArray.setModel(biglistboxModel); //strRef));
						
						//biglistboxArray.setSortAscending(new MyMatrixComparatorProvider<List<String>>(true));
						//biglistboxArray.setSortDescending(new MyMatrixComparatorProvider<List<String>>(false));
						//biglistboxArray.setSortAscending(new Object2StringMatrixComparatorProvider<List<Object[]>>(true));
						//biglistboxArray.setSortDescending(new Object2StringMatrixComparatorProvider<List<Object[]>>(false));
						matriccmpproviderAsc = new Object2StringMatrixComparatorProvider2<Object[]>(true);
						matriccmpproviderDesc = new Object2StringMatrixComparatorProvider2<Object[]>(false);
						
						biglistboxArray.setSortAscending(matriccmpproviderAsc);
						biglistboxArray.setSortDescending(matriccmpproviderDesc);
					        
						biglistboxArray.invalidate();

						
						//win.setWidth
						
						
						//gridBiglistheader.setHeight("400px");
						//gridBiglistheader.setHeight("410px");
						gridBiglistheader.setWidth("200px");
						//Clients.scrollIntoView(rows.getLastChild())
						//AppContext.debug( " biglistboxArray.getOddRowSclass()" +  biglistboxArray.getOddRowSclass());
						//AppContext.debug( " biglistboxArray.getSclass()" +  biglistboxArray.getSclass());
						
						//gridBiglistheader.setOddRowSclass( biglistboxArray.getOddRowSclass() );
						//gridBiglistheader
						gridBiglistheader.setOddRowSclass(biglistboxArray.getSclass());
						gridBiglistheader.setSclass( biglistboxArray.getOddRowSclass() );
						
						gridBiglistheader.setRowRenderer(new BiglistRowheaderRenderer());
						

						/*
						Columns columnRowheader =  gridBiglistheader.getColumns();
						((Column)columnRowheader.getChildren().get(0)).setSortAscending(  this.matriccmpproviderAsc.getColumnComparator(0) );
						((Column)columnRowheader.getChildren().get(1)).setSortAscending(  this.matriccmpproviderAsc.getColumnComparator(1) );
						((Column)columnRowheader.getChildren().get(3)).setSortAscending(  this.matriccmpproviderAsc.getColumnComparator(2) );
				            
						((Column)columnRowheader.getChildren().get(0)).setSortDescending(  this.matriccmpproviderDesc.getColumnComparator(0) );
						((Column)columnRowheader.getChildren().get(1)).setSortDescending(  this.matriccmpproviderDesc.getColumnComparator(1) );
						((Column)columnRowheader.getChildren().get(3)).setSortDescending(  this.matriccmpproviderDesc.getColumnComparator(2) );
						*/
						
						/*
						if(listBiglistheaderData == null)  {
							listBiglistheaderData=new ArrayList();
							gridBiglistheader.setModel( new SimpleListModel(listBiglistheaderData));
						}
						else listBiglistheaderData.clear();
						listBiglistheaderData.addAll( ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList(biglistboxRows) );
						*/
						
						
						
						/*
						if(!params.getOrganism().equals(AppContext.getDefaultOrganism())) {
							
							if(params.isbShowNPBPositions()) {
								this.auxheader11Header.setLabel( params.getOrganism() + " positions" );
								this.auxheader21Header.setLabel(params.getOrganism() + " allele" );
								this.auxheader3Header.setLabel("Nipponbare positions" );
								this.column4Header.setLabel("Nipponbare" );
								this.auxhead1Header.setVisible(true);
								this.auxhead2Header.setVisible(true);
								biglistboxRows = 16;
							} else {
								this.auxheader3Header.setLabel( params.getOrganism() + " positions" );
								this.column4Header.setLabel(params.getOrganism() + " allele" );
								this.auxhead1Header.setVisible(false);
								this.auxhead2Header.setVisible(false);
							}
								
						} else {
							this.auxheader3Header.setLabel("Nipponbare positions" );
							this.column4Header.setLabel("Nipponbare" );
							this.auxhead1Header.setVisible(false);
							this.auxhead2Header.setVisible(false);
						}
						*/
						
						
						
//						if(!params.getOrganism().equals(AppContext.getDefaultOrganism())) {
//							Collection colheads = gridBiglistheader.getHeads();
//							Auxhead posheader = (Auxhead)colheads.iterator().next();
//							Auxheader varnameheader = (Auxheader)posheader.getChildren().get(0);
//							//varnameheader.setLabel(  params.getOrganism() + " " + params.getsChr() + " positions&'#'10;&'#'13;&'#'10;&'#'13;Varieties" );
//							varnameheader.setLabel(  params.getOrganism() + " " + params.getsChr() + " positions" );
//							
//							if(params.isbShowNPBPositions()) {
//								AppContext.debug( "colheads.size=" + colheads.size() ); 
//								if(colheads.size()==2) {
//									// add header
//									//List listheads = (List)colheads;
//									Auxhead auxhead2 = new Auxhead();
//									auxhead2.setHeight("200px");
//									Auxheader auxhead21 = new Auxheader();
//									auxhead21.setLabel(  params.getOrganism()  + " allele" );
//									auxhead2.appendChild(auxhead21 );
//									Auxhead auxhead3 = new Auxhead();
//									auxhead3.setHeight("410px");
//									Auxheader auxhead31 = new Auxheader();
//									auxhead31.setLabel(  "Nipponbare positions");
//									auxhead3.appendChild(auxhead31 );
//									
//									
//									colheads.add(auxhead2);
//									colheads.add(auxhead3);
//									
//									//gridBiglistheader.
//									//listheads.add( 1, auxhead2);
//									//listheads.add( 2, auxhead3);
//									
//									//Columns columns1= (Columns)listheads.get(3);
//									//Column column11  = (Column)columns1.getChildren().get(0);
//									//column11.setLabel( "Nipponbare allele" );
//								}
//							}
//							
//						} else {
//							Collection colheads = gridBiglistheader.getHeads();
//							Auxhead posheader = (Auxhead)colheads.iterator().next();
//							Auxheader varnameheader = (Auxheader)posheader.getChildren().get(0);
//							varnameheader.setLabel( "Nipponbare " + params.getsChr() + " positions" );
//							
//							if(colheads.size()>2) {
//								List listheads = (List)colheads;
//								listheads.remove(1);
//								listheads.remove(2);
//							}
//						}
						
						biglistboxModel.setHeaderRows(biglistboxRows, lastY);
						Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
						
						biglistboxRows = 20;
						
						List headerlist = model.getRowHeaderHeaderList();
						
						auxheadAlleles1.setVisible(false);
						auxheadAlleles2.setVisible(false);
						auxheadAlleles3.setVisible(false);
						auxheadAlleles4.setVisible(false);
						
						AppContext.debug("headers: " + headerlist);
						
						if(!params.getOrganism().equals(AppContext.getDefaultOrganism())) {
							
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
						
						
						
						gridBiglistheader.setModel( new BiglistRowheaderModel(  model.getRowHeaderList(biglistboxRows )));
						
						//gridBiglistheader.setModel( new SimpleListModel(((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList(biglistboxRows) ));
						
						
						//gridBiglistheader.setModel( new SimpleListModel(  ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList()) ) ;
						//gridBiglistheader.setModel( new SimpleListModel(  ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList()) ) ;
					        
						
						//msgbox.setValue(msgbox.getValue() +  varianttable.getMessage());
						msgbox.setValue(new  String(msgbox.getValue() +  varianttable.getMessage()).replace("\n\n", "\n"));
						
						getSession().putValue("queryResult", queryRawResult);
						getSession().putValue("variantTable", varianttable);
						
						if(queryResult==null) throw new RuntimeException("queryResult==null");
	 					if(queryResult.getMapIdx2Pos()==null) throw new RuntimeException("queryResult.getMapIdx2Pos==null");
						
						//iframeAlignment.invalidate();
	
						divIndelLegend.setVisible(checkboxIndel.isChecked());
						
						if(checkboxIndel==null) throw new RuntimeException("checkboxIndel==null");
						if(divAlignIndels==null) throw new RuntimeException("divAlignIndels==null");
						
						divAlignIndels.setVisible(checkboxIndel.isChecked());
	
						//biglistboxArray.invalidate();
						//gridBiglistheader.invalidate();
						gridBiglistheader.setVisible(true);
						biglistboxArray.setVisible(true);
						
						
						buttonDownloadPlink.setDisabled( checkboxIndel.isChecked() );
						
						updateVista(params,varianttable);
					
					} else {
						Messagebox.show( "The region has no SNPs at these settings. Try unchecking 'Core SNPs only', 'Mismatch Only' or widen your query region",
								"GENOTYPE QUERY", Messagebox.OK, Messagebox.EXCLAMATION);
					}
					
					
					//AppContext.debug( "gridBiglistheader.getRows()=" + gridBiglistheader.getRows().getChildren().size() + "    biglistboxArray.getRows()=" + biglistboxArray.getRows());
					
					AppContext.logSystemStatus();
				
//				} catch(InvocationTargetException ex) {
//					
//				} catch(Exception ex)
//				{
//					ex.printStackTrace();
//					throw new RuntimeException(ex);
//				}
//				
				
		        tallJbrowse = true;
		        
		        if( queryResult.getListPos()!=null) {
			        if(listSNPs.size()>0 )
			        	{
			        	 	java.util.List listPosition = new java.util.ArrayList();
			        		listPosition.add("");
			        		
			        		//listPosition.addAll( new TreeSet(queryResult.getMapPos2Alleleset().keySet()) );
			        		
			        		
			        		
			        		Object2StringMatrixModel model = (Object2StringMatrixModel)this.biglistboxArray.getModel();
			        		int poscol=-1;
			        		List listPos = (List)model.getHeadAt(0);
			        		//for(int i=0; i<biglistboxArray.getCols(); i++) {
			        		mapIdx2Pos=new HashMap();
			        		for(int i=0; i<model.getColumnSize(); i++) {
			        			listPosition.add( listPos.get(i).toString() );
			        			try {
			        				mapIdx2Pos.put(i, BigDecimal.valueOf(Double.valueOf(listPos.get(i).toString()) ));
			        			} catch(Exception ex) {
			        				
			        			}
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
				//try {
				
				/*
					if(!var1.isEmpty() && !var2.isEmpty()) {
						if(checkboxMismatchOnly.isChecked())
							mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
						else
							mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLREFPOS; //SNPQUERY_REFERENCE;
					}
					
					*/
					
					
					
					varianttable =  new VariantAlignmentTableArraysImpl();
					
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
					
					/*
					String[] headers = new String[]{"CHROMOSOME","POSITION", "NIPPONBARE", 
							var1header , //comboVar1.getValue().toUpperCase() ,
							var2header //comboVar2.getValue().toUpperCase()
							};
					int i = 0;
					while(lhr != null) {
						lhr.setLabel(headers[i] );
						lhr = (Listheader)lhr.getNextSibling();
						i++;
					}
					*/
					  
					listboxSnpresult.setItemRenderer( new PairwiseListItemRenderer(queryResult, params));
					listboxSnpresult.setModel( new SimpleListModel( ((VariantAlignmentTableArraysImpl)varianttable).getCompare2VarsList( this.selectChr.getValue(), params ) ));
					listboxSnpresult.setVisible(true);
					
					//queryRawResult.merge();
					listSNPs = queryRawResult.getListVariantsString();
							
					gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
					create2VarietiesGFF(listSNPs, gfffile, queryResult.getListPos(), queryResult.getRefnuc() );
					
					msgbox.setValue(new  String(msgbox.getValue() + " ... RESULT: " +  listboxSnpresult.getModel().getSize() + " positions" 
							+  varianttable.getMessage()).replace("\n\n", "\n"));
					
					tallJbrowse= false;
					
					
//				} catch(Exception ex) {
//					throw new RuntimeException(ex);
//				}
			}

			
			if(mode==GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS  && listSNPs.size()>0)
			{
				
				if(listboxMySNPList.getSelectedIndex()>0) {
					tabJbrowse.setVisible(false);
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
							show_phylotree(chr.toUpperCase().replace("CHR0","").replace("CHR",""), intStart.getValue().toString() , intStop.getValue().toString() , listSNPs.size() );
							tabPhylo.setVisible(true);
						}
					}
					
					this.allelefreqlines=null;
					
					
				}
				calculateAlleleFrequencies();
				
				hboxDownload.setVisible(true);
				divTablePanel.setVisible(true);
				listboxSnpresult.setVisible(false);
				
			} else if(mode==GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES && listSNPs.size()>0) {
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
				//listboxSnpresult.setVisible(false);
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
	 
	 
	 private Map<Long, long[]> createMapGFFPosBounds(Set<Long> setPositions , BigDecimal start, BigDecimal end) {
		 
		//mismatchOnly =true;
			//boolean isphylojbrowse = filename.endsWith(".phylo.gff");
		 
		 	//VariantStringData queryDisplayData = queryRawResult;
		 	//VariantStringData queryDisplayData = queryResult;
			//List listSnpPos = queryDisplayData.getListPos(); // genotype.getSnpsposlist() ;
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
	 
	 private Map createMapVar2Order() {
		 
			varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade , "VarietyFacade");
			Map<String,Variety> mapName2Var = varietyfacade.getMapVarname2Variety();
			Map<BigDecimal,Integer> mapVar2Order = new HashMap();
			Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
			for(int i=0; i<model.getSize(); i++) {
				Object  varname =  ((Object[])model.getElementAt(i).get(0))[0];
				
				//AppContext.debug("varname=" + varname);
				//AppContext.debug("mapName2Var.get(varname)=" + mapName2Var.get(varname));
				
				mapVar2Order.put(mapName2Var.get(varname).getVarietyId(),i);
			}

			/* from download biglist
			 * table = matrixmodel.getData()
			Object varalleles[][] = table.getVaralleles();
			AppContext.debug( "mxn=" + varalleles.length  + "x" + varalleles[0].length);
			AppContext.debug("positions = " + refs.length);
			AppContext.debug("varids = " + table.getVarname().length);
			
			Map<BigDecimal,Variety> mapVarId2Var = varietyfacade.getMapId2Variety();
			
			for(int i=0; i< table.getVarid().length; i++) {
				String varname=table.getVarname()[i];
				*/
			

			/*
		 	Object2StringMatrixModel origModel = (Object2StringMatrixModel)this.biglistboxArray.getModel();
			VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)origModel.getData();
			Object[][] varalleles = table.getVaralleles();

			Map<BigDecimal,Integer> mapVar2Order = new HashMap(); 
			for(int i=0; i<origModel.getSize(); i ++) {
				mapVar2Order.put(BigDecimal.valueOf(table.getVarid()[i]),i);
			}
			*/
			
			AppContext.debug("mapVar2Order.size=" + mapVar2Order.size() );
			
			
			
		return 	mapVar2Order;
	 }
	 
	 private List createSNPStringVarietyGFFFromTable(List<SnpsStringAllvars> listSNPs, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			return createSNPStringVarietyGFF(listSNPs,  createMapVar2Order() , bpgap, start, end, showAll);
	 }
	 
	 //private void createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end) {
	 //private void createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
	 private List createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs,  Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
			//mismatchOnly =true;
			//boolean isphylojbrowse = filename.endsWith(".phylo.gff");
		 
		 	//VariantStringData queryDisplayData = queryRawResult;
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
			

			
			//AppContext.debug(bufPos.toString());
			//AppContext.debug("pos=" + Arrays.toString(lPos));
			//AppContext.debug(listSnpPos.size() + " SNP Positions");
			
			//if(!checkShowsnp.isChecked()) return;
			// sort snps by start
			
			//Set setSNP = new java.util.TreeSet<ViewSnpAllvarsId>(listSNPs);
			
			//java.util.Collections.sort(listSNPs, new ViewSnpAllvarsIdStartComparator());
			
			
			//StringBuffer buff = new StringBuffer();
			
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
					
					//if(isphylojbrowse && var2order.get(snpvars.getVar())==null ) continue;  
					
					Set setidxNonsyn = snpvars.getNonsynIdxset();
					
					//AppContext.debug("var=" + snpvars.getVar() + " nonsynidx=" +  setidxNonsyn.toString());
					
					/*if(setidxNonsyn==null)
						throw new RuntimeException("setidxNonsyn==null");
					else {
						AppContext.debug( setidxNonsyn.size() + " setidxNonsyn size");
						AppContext.debug( setidxNonsyn.iterator().next().getClass().getCanonicalName() + " setidx class");
					}
					*/
					
					Map<Integer,Character> mapPosidx2allele2 = snpvars.getMapPosIdx2Allele2();
					
					//long longsnpvar = snpvars.getVar().longValueExact();
					//long longsnppos = snpvars.getPos().longValueExact();

					String chr = snpvars.getChr().toString(); 
					if(chr.length()==1)
						chr= "chr0" + chr + "|msu7";
					else 
						chr= "chr" + chr + "|msu7";
		
					String order =  var2order.get(snpvars.getVar() ).toString();
					Variety var=mapId2Variety.get( snpvars.getVar() );
					if(var==null) throw new RuntimeException(snpvars.getVar()  + " not in mapId2Variety");

					/*
					try {
						AppContext.debug("nonsynidx= " +  AppContext.setSlicerIds( setidxNonsyn ).get(0).toString() );
					} catch	(Exception ex) {
						
					}
					*/
					
					/*
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
						String synstr = "1";
						if(setidxNonsyn!=null && setidxNonsyn.contains( iPos )) synstr = "0";
						String al2 = "";
						if(mapPosidx2allele2!=null) {
							Character allele2 = mapPosidx2allele2.get(iPos);
							if(allele2!=null) al2="/" + allele2;
						}
						
						if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
						
						*/
					
					String splicestr = "";

					String colormode="";
					if(this.radioColorMismatch.isSelected())
						colormode=";colormode=1";
					else
						colormode=";colormode=0";
					
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
						String synstr = "";
						if(setidxNonsyn!=null) {
							if(setidxNonsyn.contains( iPos )) synstr = ";Synonymous=0";
							else {
								synstr = ";Synonymous=1";
								//AppContext.debug("synonymous snpIdx=" + iPos );
							}
						}
						String al2 = "";
						if(mapPosidx2allele2!=null) {
							Character allele2 = mapPosidx2allele2.get(iPos);
							if(allele2!=null) al2="/" + allele2;
						}
						
						if( showAll || (charAtPos!='0' && cRef[iPos]!= charAtPos) ) {
						//if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
						//if(true) {
							//AppContext.debug("idx=" +  iPos + "  Synonymous=" + synstr);

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

						
							String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
									";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  lPos[iPos] + 
									";AlleleRef=" +   cRef[iPos] + 
									";AlleleAlt=" + charAtPos + al2 +
									";Position=" +  lPos[iPos] +
									synstr +
									splicestr + colormode +
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
	 
//	 private List createSNPPointStringVarietyGFFOld(List<SnpsStringAllvars> listSNPs,  Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
//			
//			//mismatchOnly =true;
//			//boolean isphylojbrowse = filename.endsWith(".phylo.gff");
//
//			List listSnpPos = queryRawResult.getListPos(); // genotype.getSnpsposlist() ;
//			Iterator<SnpsAllvarsPos> itPos = listSnpPos.iterator();
//			
//			long lPos[] = new long[listSnpPos.size()];
//			char cRef[] = new char[listSnpPos.size()];
//			
//			int iPosCount = 0;
//			long prevpos = start.longValue();
//			if(itPos.hasNext()) {
//				SnpsAllvarsPos pos = itPos.next();
//				long curpos = pos.getPos().longValue();
//				lPos[iPosCount]=curpos;
//				cRef[iPosCount]=pos.getRefnuc().charAt(0);
//			}
//			
//			
//			
//			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
//			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
//			
//			java.util.List listGFF = new java.util.ArrayList();			
//			
//			try {
//		
//				Iterator<SnpsStringAllvars> itsnpstring = listSNPs.iterator();
//				
//				Map<Integer,Integer> mapMergedIdx2SnpIdx = queryRawResult.getSnpstringdata().getMapMergedIdx2SnpIdx();
//				
//				AppContext.debug("mapMergedIdx2SnpIdx=" + mapMergedIdx2SnpIdx);
//				
//				
//				while(itsnpstring.hasNext()) {
//					SnpsStringAllvars snpvars = itsnpstring.next();
//					
//					//if(isphylojbrowse && var2order.get(snpvars.getVar())==null ) continue;  
//					
//					Set setidxNonsyn = snpvars.getNonsynIdxset();
//					
//					Set setposAcceptor = snpvars.getAcceptorPosset();
//					Set setposDonor = snpvars.getDonorPosset();
//					
//					
//					AppContext.debug("var=" + snpvars.getVar() + " nonsynidx=" +  setidxNonsyn.toString());
//
//					
//					
//					/*if(setidxNonsyn==null)
//						throw new RuntimeException("setidxNonsyn==null");
//					else {
//						AppContext.debug( setidxNonsyn.size() + " setidxNonsyn size");
//						AppContext.debug( setidxNonsyn.iterator().next().getClass().getCanonicalName() + " setidx class");
//					}
//					*/
//					
//					Map<Integer,Character> mapPosidx2allele2 = snpvars.getMapPosIdx2Allele2();
//					
//					//long longsnpvar = snpvars.getVar().longValueExact();
//					//long longsnppos = snpvars.getPos().longValueExact();
//
//					String chr = snpvars.getChr().toString(); 
//					if(chr.length()==1)
//						chr= "chr0" + chr + "|msu7";
//					else 
//						chr= "chr" + chr + "|msu7";
//		
//					String order =  var2order.get(snpvars.getVar() ).toString();
//					Variety var=mapId2Variety.get( snpvars.getVar() );
//					if(var==null) throw new RuntimeException(snpvars.getVar()  + " not in mapId2Variety");
//
//					/*
//					try {
//						AppContext.debug("nonsynidx= " +  AppContext.setSlicerIds( setidxNonsyn ).get(0).toString() );
//					} catch	(Exception ex) {
//						
//					}
//					*/
//					
//					/*
//					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
//						
//						char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
//						String synstr = "1";
//						if(setidxNonsyn!=null && setidxNonsyn.contains( iPos )) synstr = "0";
//						String al2 = "";
//						if(mapPosidx2allele2!=null) {
//							Character allele2 = mapPosidx2allele2.get(iPos);
//							if(allele2!=null) al2="/" + allele2;
//						}
//						
//						if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
//						
//						*/
//					
//					String colormode="";
//					if(this.radioColorMismatch.isSelected())
//						colormode=";colormode=1";
//					else
//						colormode=";colormode=0";
//									
//					
//					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
//						
//						Integer snpIdx = mapMergedIdx2SnpIdx.get(iPos);
//						if(snpIdx==null) continue;
//						
//						
//						
//						
//						char charAtPos = snpvars.getVarnuc().substring(snpIdx,snpIdx+1).charAt(0);
//						String synstr = "";
//						if(setidxNonsyn!=null) {
//							
//							if(setidxNonsyn.contains( snpIdx )) synstr = ";Synonymous=0";
//							else {
//								synstr= ";Synonymous=1";
//								AppContext.debug("synonymous mergedidx=" + iPos + ", snpidx=" + snpIdx);
//							}
//						}
//						String al2 = "";
//						if(mapPosidx2allele2!=null) {
//							Character allele2 = mapPosidx2allele2.get(snpIdx);
//							if(allele2!=null) al2="/" + allele2;
//						}
//						
//						if( showAll || (charAtPos!='0' && cRef[iPos]!= charAtPos) ) {
//						//if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
//						//if(true) {
//							//AppContext.debug("idx=" +  iPos + "  Synonymous=" + synstr);
//						
//							String splicestr="";
//							if(setposAcceptor!=null) {
//							if(setposAcceptor.contains( BigDecimal.valueOf(lPos[iPos]) ))
//								splicestr=";Acceptor=1";	
//								else splicestr=";Acceptor=0";
//							}
//								
//							if(setposDonor!=null) {
//							if(setposDonor.contains( BigDecimal.valueOf( lPos[iPos]) ) )
//								splicestr+=";Donor=1";	
//								else splicestr+=";Donor=0";
//							}
//							
//							String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
//									";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  lPos[iPos] + 
//									";AlleleRef=" +   cRef[iPos] + 
//									";AlleleAlt=" + charAtPos + al2 +
//									";Position=" +  lPos[iPos] +
//									 synstr +
//									 splicestr + colormode +
//									";order=" + order +
//									"\n";	
//		
//							//AppContext.debug("mergedidx=" + iMergedPos + ", snpidx=" + iPos + ", pos=" + pos.getPos() + ", strref="+ strRef + ", gff=" +   line_right);
//							
//							listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  lPos[iPos],  lPos[iPos]));
//						}
//					}
//				}
//				
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//			
//			return listGFF;
//		}
	 

	 private List createSNPPointStringVarietyGFFFromTable(List<SnpsStringAllvars> listSNPs,   int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
		 return createSNPPointStringVarietyGFF(listSNPs,  createMapVar2Order(),bpgap, start,  end,  showAll);
	 }
	 
	 private List createSNPPointStringVarietyGFF(List<SnpsStringAllvars> listSNPs,  Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
			//mismatchOnly =true;
			//boolean isphylojbrowse = filename.endsWith(".phylo.gff");

		 	
		 	//VariantStringData queryDisplayData=queryRawResult;
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
				
				//AppContext.debug("iPos=" + iPosCount + ",pos=" + curpos +",ref=" + pos.getRefnuc());
				iPosCount++;
			}
			
			 //AppContext.debug("pos=" + Arrays.toString(lPos));
			 //AppContext.debug( "merged pos=" +  queryRawResult.getListPos().size() + ", snppos=" +  queryRawResult.getSnpstringdata().getListPos().size() + ", using snppos");
			
			
			
			 Map mapTableIdx2NonsynAlleles = queryDisplayData.getSnpstringdata().getMapIdx2NonsynAlleles();
			 Set setSnpInExonTableIdx = queryDisplayData.getSnpstringdata().getSetSnpInExonTableIdx();
			 
				
				
			 
			 
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
			
			java.util.List listGFF = new java.util.ArrayList();			
			
			try {
		
				Iterator<SnpsStringAllvars> itsnpstring = listSNPs.iterator();
				
				Map<Integer,Integer> mapMergedIdx2SnpIdx = queryDisplayData.getSnpstringdata().getMapMergedIdx2SnpIdx();
				
				AppContext.debug("mapMergedIdx2SnpIdx=" + mapMergedIdx2SnpIdx);
				
				while(itsnpstring.hasNext()) {
					SnpsStringAllvars snpvars = itsnpstring.next();
					
					//if(isphylojbrowse && var2order.get(snpvars.getVar())==null ) continue;  
					
					Set setidxNonsyn = snpvars.getNonsynIdxset();
					
					Set setposAcceptor = snpvars.getAcceptorPosset();
					Set setposDonor = snpvars.getDonorPosset();
					
					/*if(setidxNonsyn==null)
						throw new RuntimeException("setidxNonsyn==null");
					else {
						AppContext.debug( setidxNonsyn.size() + " setidxNonsyn size");
						AppContext.debug( setidxNonsyn.iterator().next().getClass().getCanonicalName() + " setidx class");
					}
					*/
					
					//AppContext.debug("var=" + snpvars.getVar() + " nonsynidx=" +  setidxNonsyn.toString());
					
					Map<Integer,Character> mapPosidx2allele2 = snpvars.getMapPosIdx2Allele2();
					
					//long longsnpvar = snpvars.getVar().longValueExact();
					//long longsnppos = snpvars.getPos().longValueExact();

					String chr = snpvars.getChr().toString(); 
					if(chr.length()==1)
						chr= "chr0" + chr + "|msu7";
					else 
						chr= "chr" + chr + "|msu7";
		
					String order =  var2order.get(snpvars.getVar() ).toString();
					Variety var=mapId2Variety.get( snpvars.getVar() );
					if(var==null) throw new RuntimeException(snpvars.getVar()  + " not in mapId2Variety");

					/*
					try {
						AppContext.debug("nonsynidx= " +  AppContext.setSlicerIds( setidxNonsyn ).get(0).toString() );
					} catch	(Exception ex) {
						
					}
					*/
					
					/*
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
						String synstr = "1";
						if(setidxNonsyn!=null && setidxNonsyn.contains( iPos )) synstr = "0";
						String al2 = "";
						if(mapPosidx2allele2!=null) {
							Character allele2 = mapPosidx2allele2.get(iPos);
							if(allele2!=null) al2="/" + allele2;
						}
						
						if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
						
						*/
					
					String colormode="";
					if(this.radioColorMismatch.isSelected())
						colormode=";colormode=1";
					else
						colormode=";colormode=0";
									
					//Map mapMergedIdx2SnpIdx = snpvars.get
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						//Integer snpIdx = mapMergedIdx2SnpIdx.get(iPos);
						//if(snpIdx==null) continue;
						
						Integer snpIdx = iPos;
					
						
						char charAtPos = snpvars.getVarnuc().substring(snpIdx,snpIdx+1).charAt(0);
						
						String synstr = "";
						/*
						if(setidxNonsyn!=null) {
							
							if(setidxNonsyn.contains( snpIdx )) {
								synstr = ";Synonymous=0";
							}
							else {
								synstr= ";Synonymous=1";
								AppContext.debug("synonymous mergedidx=" + iPos + ", snpidx=" + snpIdx);
							}
						} */
						
						
						
						if(mapTableIdx2NonsynAlleles!=null) {
								if(snpIdx!=null && setSnpInExonTableIdx.contains(snpIdx)) {
									Set setnonsyn =  (Set)mapTableIdx2NonsynAlleles.get(snpIdx);
									if(setnonsyn==null || !setnonsyn.contains(  charAtPos )) {
										// synonymous
										//return  "<div style=\"text-align:center;color:lightgray\">" +  cellval + "</div>";
										synstr= ";Synonymous=1";
										//AppContext.debug("synonymous mergedidx=" + iPos + ", snpidx=" + snpIdx);
									} else 
										synstr = ";Synonymous=0";
								}
						}
						
						
						String al2 = "";
						if(mapPosidx2allele2!=null) {
							Character allele2 = mapPosidx2allele2.get(snpIdx);
							if(allele2!=null) al2="/" + allele2;
						}
						
						if( showAll || (charAtPos!='0' && cRef[iPos]!= charAtPos) ) {
						//if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
						//if(true) {
							//AppContext.debug("idx=" +  iPos + "  Synonymous=" + synstr);
						
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
	 
	 
	 private List createIndelStringVarietyGFFFromTable(List listSNPs, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
		 return createIndelStringVarietyGFF(listSNPs, createMapVar2Order(), bpgap,  start, end, showAll);
	 }
	 
	 //private void createIndelStringVarietyGFF(List<IndelsStringAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
	 //private List createIndelStringVarietyGFF(List<IndelsStringAllvars> listSNPs, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
	 private List createIndelStringVarietyGFF(List listSNPs, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
			java.util.List listGFF = new java.util.ArrayList();
			
			try {
		
				Iterator<IndelsStringAllvars> itsnpstring = listSNPs.iterator();
			
							
				
				while(itsnpstring.hasNext()) {
					IndelsStringAllvars snpvars = itsnpstring.next();
					
					
					Set setidxNonsyn = snpvars.getNonsynIdxset();
					Set setposAcceptor = snpvars.getAcceptorPosset();
					Set setposDonor = snpvars.getDonorPosset();

					Map<Integer,Character> mapPosidx2allele2 = snpvars.getMapPosIdx2Allele2();
					
					//long longsnpvar = snpvars.getVar().longValueExact();
					//long longsnppos = snpvars.getPos().longValueExact();

					String chr = snpvars.getChr().toString(); 
					if(chr.length()==1)
						chr= "chr0" + chr + "|msu7";
					else 
						chr= "chr" + chr + "|msu7";
		
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
							if(indel.getDellength()>0) {
								if(indel.getInsString()==null || indel.getInsString().isEmpty() ) {
									allele1 = "del " + indel.getDellength(); 
									allele1len = indel.getDellength();
									type = "deletion";
								} else if(indel.getDellength()==1 && indel.getInsString().length()==1) {
									allele1 =  "snp ->" + indel.getInsString();
									allele1len = 1;
									type = "snp";
								} else
								{
									allele1 =  "sub " + indel.getDellength() + "->" + indel.getInsString();
									allele1len =  indel.getInsString().length();
									type = "substitution";
								}
								
							} else if(indel.getInsString()==null || indel.getInsString().isEmpty() ) {
								allele1 = "ref";
								allele1len = 1;
								type = "reference";
								if(!showAll) continue;
							} else {
								allele1 = indel.getInsString();
								allele1len = allele1.length();
								type = "insertion";
							}
						}

						int allele2len = 0;
						String allele2 = "";
						if(indels.getAllele2()!=null && !indels.getAllele1().equals(indels.getAllele2())){
							IndelsAllvarsPos indel = mapIndelId2Indel.get( indels.getAllele2() );
							if(indel.getDellength()>0) {
								if(indel.getInsString()==null || indel.getInsString().isEmpty() ) {
									allele1 = "/del " + indel.getDellength(); 
									allele1len = indel.getDellength();
									type = "deletion";
								} else if(indel.getDellength()==1 && indel.getInsString().length()==1) {
									allele1 =  "snp ->" + indel.getInsString();
									allele1len = 1;
									type = "snp";
								} else {
									allele1 =  "/sub " + indel.getDellength() + "->" + indel.getInsString();
									allele1len = indel.getInsString().length();
									type = "substitution";
								}
							} else if(indel.getInsString()==null || indel.getInsString().isEmpty() ) {
								allele2 = "/ref";
								allele2len = 1;
							} else {
								allele2 = "/" + indel.getInsString();
								allele2len = allele1.length();
							}
						}
						
						int maxlen = allele1len;
						if(allele2len>maxlen) maxlen=allele2len;
						
						String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
								";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  pos + 
								";AlleleAlt=" + allele1 + allele2  +
								";Position=" +  pos +  colormode +
								";order=" + order +
								"\n";	
	
						listGFF.add(new GFF( chr + "\tIRIC\t" + type + "\t", line_right,  pos.longValue(),  pos.longValue() + maxlen -1,  pos.longValue() ));						
						
					}
					
					if(snpvars.getVarnuc()!=null) {
						
						
						//Map<Integer,Integer> mapIdx2Snpidx = queryRawResult.getSnpstringdata().getMapMergedIdx2SnpIdx();
						//List<SnpsAllvarsPos> listSnpPos = queryRawResult.getListPos();
						List<SnpsAllvarsPos> listSnpPos = queryRawResult.getSnpstringdata().getListPos();
						
						//Map tempmap = new TreeMap(mapIdx2Snpidx);
						//AppContext.debug("indelstr varnuc!=null mapIdx2Snpidx=" + tempmap.toString());
						
						//Iterator<SnpsAllvarsPos> itMergedPos = listSnpPos.iterator();
						
						Map<BigDecimal,String> mapIndelpos2Refnuc = null;
						if(queryRawResult.hasIndel())
							mapIndelpos2Refnuc = queryRawResult.getIndelstringdata().getMapIndelpos2Refnuc();
						
			
						Set tempsetall=new TreeSet();
						Set tempsetsnps=new TreeSet();
						for(int iMergedPos = 0; iMergedPos<listSnpPos.size(); iMergedPos ++ ) {
							
							//tempsetall.add( listSnpPos.get(iMergedPos).getPos() );
							// merge pos is not snp
							//if(! mapIdx2Snpidx.containsKey(iMergedPos)) continue;
							
							//tempsetsnps.add( listSnpPos.get(iMergedPos).getPos() );
							
							//queryRawResult.getIndelstringdata().getMapIndelpos2Refnuc();
							
							//int iPos = mapIdx2Snpidx.get(iMergedPos);
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
							if(setidxNonsyn!=null && setidxNonsyn.contains( iPos )) synstr = "0";
							String al2 = "";
							if(mapPosidx2allele2!=null) {
								Character allele2 = mapPosidx2allele2.get(iPos);
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
							//if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
							//if(true) {
								//AppContext.debug("idx=" +  iPos + "  Synonymous=" + synstr);
	
								
								String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
										";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  pos.getPos() + 
										";AlleleRef=" +   cRef + 
										";AlleleAlt=" + charAtPos + al2 +
										";Position=" +  pos.getPos() +
										";Synonymous=" + synstr +
										 splicestr + colormode +
										";order=" + order +
										"\n";	
			
								//AppContext.debug("mergedidx=" + iMergedPos + ", snpidx=" + iPos + ", pos=" + pos.getPos() + ", strref="+ strRef + ", gff=" +   line_right);
								
								listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  pos.getPos().longValue() , pos.getPos().longValue(), pos.getPos().longValue() ));
							}
						}						
						
						
						
						
//						Map<Integer,Integer> mapIdx2Snpidx = queryRawResult.getSnpstringdata().getMapMergedIdx2SnpIdx();
//						List<SnpsAllvarsPos> listSnpPos = queryRawResult.getListPos();
//						
//						Map tempmap = new TreeMap(mapIdx2Snpidx);
//						AppContext.debug("indelstr varnuc!=null mapIdx2Snpidx=" + tempmap.toString());
//						
//						//Iterator<SnpsAllvarsPos> itMergedPos = listSnpPos.iterator();
//						
//						Map<BigDecimal,String> mapIndelpos2Refnuc = null;
//						if(queryRawResult.hasIndel())
//							mapIndelpos2Refnuc = queryRawResult.getIndelstringdata().getMapIndelpos2Refnuc();
//						
//			
//						Set tempsetall=new TreeSet();
//						Set tempsetsnps=new TreeSet();
//						for(int iMergedPos = 0; iMergedPos<listSnpPos.size(); iMergedPos ++ ) {
//							
//							tempsetall.add( listSnpPos.get(iMergedPos).getPos() );
//							// merge pos is not snp
//							if(! mapIdx2Snpidx.containsKey(iMergedPos)) continue;
//							
//							tempsetsnps.add( listSnpPos.get(iMergedPos).getPos() );
//							
//							//queryRawResult.getIndelstringdata().getMapIndelpos2Refnuc();
//							
//							int iPos = mapIdx2Snpidx.get(iMergedPos);
//							SnpsAllvarsPos pos = listSnpPos.get(iMergedPos);
//							
//							String  strRef=null;
//							
//							if(mapIndelpos2Refnuc!=null)
//								strRef = mapIndelpos2Refnuc.get(pos.getPos());
//							if(strRef==null) strRef=pos.getRefnuc();
//							
//							// no ref value
//							if(strRef==null || strRef.isEmpty()) continue; 
//							
//							char cRef = strRef.charAt(0);
//							char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
//							String synstr = "1";
//							if(setidxNonsyn!=null && setidxNonsyn.contains( iPos )) synstr = "0";
//							String al2 = "";
//							if(mapPosidx2allele2!=null) {
//								Character allele2 = mapPosidx2allele2.get(iPos);
//								if(allele2!=null) al2="/" + allele2;
//							}
//							
//							
//							String splicestr = "";
//							if(setposAcceptor!=null) {
//							if(setposAcceptor.contains(pos.getPos()))
//								splicestr=";Acceptor=1";	
//								else splicestr=";Acceptor=0";
//							}
//							
//							if(setposDonor!=null) {
//							if(setposDonor.contains(pos.getPos())) 
//								splicestr+=";Donor=1";	
//								else splicestr+=";Donor=0";
//							}
//
//							
//							if( showAll || (charAtPos!='0' && cRef!= charAtPos && charAtPos!='.' && charAtPos!=' ') ) {
//							//if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
//							//if(true) {
//								//AppContext.debug("idx=" +  iPos + "  Synonymous=" + synstr);
//	
//								
//								String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
//										";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  pos.getPos() + 
//										";AlleleRef=" +   cRef + 
//										";AlleleAlt=" + charAtPos + al2 +
//										";Position=" +  pos.getPos() +
//										";Synonymous=" + synstr +
//										 splicestr + colormode +
//										";order=" + order +
//										"\n";	
//			
//								//AppContext.debug("mergedidx=" + iMergedPos + ", snpidx=" + iPos + ", pos=" + pos.getPos() + ", strref="+ strRef + ", gff=" +   line_right);
//								
//								listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  pos.getPos().longValue() , pos.getPos().longValue()));
//							}
//						}
						
						
//						AppContext.debug("pos all=" + tempsetall.toString());
//						AppContext.debug("pos snps=" + tempsetsnps.toString());
					}
					
				}
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return listGFF;
		}
	 
	 
	 private void writeGFF(List listGFF, String filename) {
		
		java.util.Collections.sort( listGFF, new GFFStartComparator());
		 
		try {
			File file = new File(AppContext.getTempDir() + filename);	
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
	 
	 
	private void createSNP2linesGFF(List<Snps2Vars> listSNPs, String filename) {
		createSNP2linesGFF(listSNPs, filename, false);
	}
	
	/**
	 * Create SNP GFF for 2-varieties query
	 * @param listSNPs
	 * @param filename
	 * @param mismatchOnly
	 */
	private void createSNP2linesGFF(List<Snps2Vars> listSNPs, String filename, boolean mismatchOnly) {
		
		//if(!checkShowsnp.isChecked()) return; 
		
		StringBuffer buff = new StringBuffer();
		
	
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapId2Var=varietyfacade.getMapId2Variety();
		
		try {
			File file = new File( AppContext.getTempDir() + filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			int linecount = 0;
			Iterator<Snps2Vars> itsnp = listSNPs.iterator();
			while(itsnp.hasNext()) {
					Snps2Vars snpvars = itsnp.next();
				
					
					String chr = snpvars.getChr().toString(); 
					if(chr.length()==1)
						chr= "chr0" + chr + "|msu7";
					else 
						chr= "chr" + chr + "|msu7";
					
					String colormode="";
					if(this.radioColorMismatch.isSelected())
						colormode=";colormode=1";
					else
						colormode=";colormode=0";

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
									 ";Position=" +  snpvars.getPos() + colormode +
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
									 ";Position=" +  snpvars.getPos() + colormode+
									"\n");
						}
					} 
					else {
						//snps
						
						if(!mismatchOnly || !snpvars.getRefnuc().equals( snpvars.getVar1nuc()  ) ) {
							buff.append(chr); buff.append("\tIRIC\tsnp\t");
							buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
							
							buff.append( "\t.\t.\t.\tName=" + mapId2Var.get( snpvars.getVar1() ).getName()
									+";ID=" +  snpvars.getVar1() + "-" + snpvars.getPos() +
									";AlleleRef=" +   snpvars.getRefnuc() +
									 ";AlleleAlt=" + snpvars.getVar1nuc() +
									 ";Position=" +  snpvars.getPos() + colormode +
									"\n");
						}
		
						if(!mismatchOnly || !snpvars.getRefnuc().equals( snpvars.getVar2nuc()  ) ) {
							buff.append(chr); buff.append("\tIRIC\tsnp\t");
							buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
							buff.append( "\t.\t.\t.\tName=" +  mapId2Var.get( snpvars.getVar2() ).getName()
									+ ";ID=" +  snpvars.getVar2() + "-" + snpvars.getPos() + 
									";AlleleRef=" +   snpvars.getRefnuc() +
									";AlleleAlt=" + snpvars.getVar2nuc() +
									";Position=" +  snpvars.getPos() + colormode +
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
	
				
				String chr="";
				if(var1variants.getChr()<10)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
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
		
		
		String org = this.listboxReference.getSelectedItem().getLabel(); 
		if(org.equals(AppContext.getDefaultOrganism())) 
			chrpad = "loc=" + chr + "|msu7";
		else if(org.equals("IR64-21"))
			chrpad = "data=ir6421v1&loc="+  chr + "|ir6421v1";
		else if(org.equals("93-11"))
			chrpad =  "data=9311v1&loc=" + chr + "|9311v1";
		else if(org.equals("DJ123"))
			chrpad = "data=dj123v1&loc=" + chr + "|dj123v1";
		else if(org.equals("Kasalath"))
			chrpad = "data=kasv1&loc=" + chr + "|kasv1";
		
		
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

		String urltemplate = "..%2F..%2F" + AppContext.getHostDirectory() + "%2Ftmp%2F" + "GFF_FILE";
		
		//if(checkShowsnp.isChecked() ) {

		if(true) {
		
			if(tallJbrowse) {
				// for all varieties

				
				 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatchSynIndels%22";
				
				/*
				if(this.checkboxIndel.isChecked()) {
					 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatchSynIndels%22";
				}
				else {
				
					if(radioGraySynonymous.isSelected()) {
						if(radioColorAllele.isSelected()) {
							 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietySyn%22";
						} else if (radioColorMismatch.isSelected() )
						{			
							 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatchSyn%22";
						}
					}
					else {
						
						if(radioColorAllele.isSelected()) {
							 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2Variety%22";
						} else if (radioColorMismatch.isSelected() )
						{			
							 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatch%22";
						}
					}
				}
				*/
				
				String snp3kcore="";
				if(checkboxSNP.isChecked()) { snp3kcore = "snp3k%2C";
					if(checkboxCoreSNP.isChecked()) snp3kcore="snp3k%2Csnp3kcore%2C";
				}
				if(checkboxIndel.isChecked())
					snp3kcore += "indel3kinterval%2C";
				
				String showTracks = "DNA%2Cmsu7gff%2C" + snp3kcore;
				
				if(org.equals(AppContext.getDefaultOrganism())) 
						;
				else if(org.equals("IR64-21"))
					showTracks = "IR64-21%20DNA,ir6421v1rengff,alignir6421v1vsmsu7%2C";
				else if(org.equals("93-11"))
					showTracks  = "9311%20DNA%2C9311v1rengff,";
				else if(org.equals("DJ123"))
					showTracks = "DJ123%20DNA%2C,dj123v1rengff,aligndj123v1vsmsu7%2C";
				else if(org.equals("Kasalath"))
						showTracks = "Kasalath%20DNA%2Ckasrapv1rengff,";
				
				
				//urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?loc=chr"  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2Csnp3k%2C" + snp3kcore + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
				
				//urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?loc="  + chrpad + ":" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2C" + snp3kcore + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
				urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?" + chrpad + ":" + start + ".." + end +   "&tracks=" + showTracks  + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
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
				String snp3kcore="";
				if(checkboxCoreSNP.isChecked()) snp3kcore="snp3kcore,";
				
				String showTracks = "DNA,msu7gff,snp3k," + snp3kcore;
				if(org.equals(AppContext.getDefaultOrganism())) 
						;
				else if(org.equals("IR64-21"))
					showTracks = "IR64-21%20DNA,ir6121v1rengff,alignir6421v1vsmsu7";
				else if(org.equals("93-11"))
					showTracks  = "9311%20DNA,9311v1rengff";
				else if(org.equals("DJ123"))
					showTracks = "DJ123%20DNA%2Cdj123v1rengff,aligndj123v1vsmsu7";
				else if(org.equals("Kasalath"))
						showTracks = "Kasalath%20DNA,kasrapv1rengff";
				
				
				// for 2 varieties
				//urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?loc=" + chrpad + ":" + start + ".." + end + 
				urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?" +  chrpad + ":" + start + ".." + end +
					//"&tracks=DNA,msu7gff,snp3k," + snp3kcore + "SNP%20Genotyping&addStores={%22url%22:{%22type%22:%22JBrowse/Store/SeqFeature/GFF3%22,%22urlTemplate%22:%22" + urltemplate
					"&tracks=" + showTracks  + "&SNP%20Genotyping&addStores={%22url%22:{%22type%22:%22JBrowse/Store/SeqFeature/GFF3%22,%22urlTemplate%22:%22" + urltemplate
					+ "%22}}&addTracks=[{%22label%22:%22SNP%20Genotyping%22,%22type%22:%22JBrowse/View/Track/" + rendertype + "%22,%22store%22:%22url%22," + displaymode + 
					 ",%22fmtDetailValue_Name%22%3A%20%22function(name)%20%7B%20return%20%27%3Ca%20target%3D%5C%22variety%5C%22%20href%3D%5C%22/" + AppContext.getHostDirectory() + "/_variety.zul%3Fname%3D%27%2Bname%2B%27%5C%22%3E%27%2Bname%2B%27%3C%2Fa%3E%27%3B%20%7D%22%20"
					+ ",%22style%22:{%22showLabels%22:%22false%22,%22textFont%22:%22normal 8px Univers,Helvetica,Arial,sans-serif%22," +
					"%22showLabels%22:%22false%22,%22label%22:%22Name%22,%22strandArrow%22:%22false%22}}]";
			
			}
		}
		/*
		else
		{
			urljbrowse="http://pollux:8080/jbrowse-dev/?loc=chr" + chrpad + "|msu7:" + start + ".." + end +  "&tracks=DNA,Genes";
		}
		*/
				
				
		log.debug(urljbrowse);
		
		AppContext.debug(urljbrowse);
			
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

		String urltemplate = "..%2F..%2F" + AppContext.getHostDirectory() + "%2Ftmp%2F" + gfffile + ".phylo.gff";
		
			if(tallJbrowse) {
				// for all varieties
				
				rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2Variety%22";

				
				urljbrowsephylo= AppContext.getHostname() + "/jbrowse-dev/?loc="  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2Csnp3k%2CSNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
					 "%22}}&addTracks=[{%22label%22%3A%22SNP%20Genotyping%22%2C%22type%22%3A" + rendertype + "%2C%22store%22%3A%22url%22%2C%20" + displaymode 
					 + ",%22metadata%22:{%22Description%22%3A%20%22Varieties%20SNP%20Genotyping%20in%20the%20region.%20Each%20row%20is%20a%20variety.%20Red%20means%20there%20is%20variation%20with%20the%20reference%22}"  
					 + ",%22fmtDetailValue_Name%22%3A%20%22function(name)%20%7B%20return%20%27%3Ca%20target%3D%5C%22variety%5C%22%20href%3D%5C%22/" + AppContext.getHostDirectory()  + "/_variety.zul%3Fname%3D%27%2Bname%2B%27%5C%22%3E%27%2Bname%2B%27%3C%2Fa%3E%27%3B%20%7D%22%20"
					 + "%2C%20%22style%22%3A{%22showLabels%22%3A%22false%22%2C%22textFont%22%3A%22normal%208px%20Univers%2CHelvetica%2CArial%2Csans-serif%22}}]&highlight=";

			}
		
				
		log.debug(urljbrowsephylo);
		
		AppContext.debug("urljbrowsephylo= " + urljbrowsephylo);
			
	}
	
	
	/**
	 * Prepare phylogenetic tree URL. Tree is not computed/displayed until the tab is clicked
	 * @param chr
	 * @param start
	 * @param end
	 */
	
	private void show_phylotree(String  chr, String start, String end, int nvars) {

		
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
			
			
			//urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=" + gfffile.replace(".gff","") + "&mindist=" + intPhyloMindist.getValue();
			//urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=" + gfffile.replace(".gff","") + "&mindist=" + intPhyloMindist.getValue();
			urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=GFF_FILE&mindist=" + intPhyloMindist.getValue();
			//log.debug(urlphylo);
			//AppContext.debug(urlphylo);
	}
	
	
	
	/**
	 * for bigmatrix viewer
	 */
	
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
//		
//		class BiglistRowheaderListitemComparator extends ListitemComparator {
//
//			private int colIdx=-1;
//			private boolean asc;
//			
//			public BiglistRowheaderListitemComparator(int index,
//					boolean ascending, boolean ignoreCase) {
//				
//				super(index, ascending, ignoreCase);
//				// TODO Auto-generated constructor stub
//				asc=ascending;
//				colIdx=index;
//			}
//
//			public int getColIdx() {
//				return colIdx;
//			}
//
//			public boolean isAsc() {
//				return asc;
//			}
//
//			
//		}
//		
//	
//		private class Object2StringMatrixComparatorProvider<T> implements
//					MatrixComparatorProvider<List<Object[]>> {
//				private int _x = -1;
//				
//				private boolean _acs;
//				
//				private Object2StringComparator _cmpr;
//				
//				public Object2StringMatrixComparatorProvider(boolean asc) {
//					_acs = asc;
//					_cmpr = new Object2StringComparator(this);
//				}
//				
//				@Override
//				public Comparator<List<Object[]>> getColumnComparator(int columnIndex) {
//					this._x = columnIndex;
//					
//					//AppContext.debug("sort by column " + columnIndex);
//					
//					return _cmpr;
//				
//				}
//				
//				// a real String comparator
//				private class Object2StringComparator implements Comparator<List<Object[]>> {
//					private Object2StringMatrixComparatorProvider _mmc;
//				
//					public Object2StringComparator(Object2StringMatrixComparatorProvider mmc) {
//						_mmc = mmc;
//					}
//				
//					@Override
//					public int compare(List<Object[]> o1, List<Object[]> o2) {
//						
//						try {
//						//AppContext.debug( "o1_0=" + o1.get(0)  + "  o2_0="  + o2.get(0));
//						AppContext.debug( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//						//System.out.println( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//						
//						//return o1.get(_mmc._x).compareTo(o2.get(_mmc._x)) * (_acs ? 1 : -1);
//						return ((Object[])o1.get(_mmc._x))[_mmc._x].toString().compareTo(  ((Object[])o2.get(_mmc._x))[_mmc._x].toString() ) * (_acs ? 1 : -1);
//						
//						} catch(Exception ex) {
//							ex.printStackTrace();
//							return 0;
//						}
//						
//					}
//				}
//		}
		
	public class Object2StringMatrixComparatorProvider2<T> implements
		MatrixComparatorProvider<Object[]> {
	private int _x = -1;
	
	private boolean _acs;
	
	private Object2StringComparator _cmpr;
	
	public Object2StringMatrixComparatorProvider2(boolean asc) {
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
		private Object2StringMatrixComparatorProvider2 _mmc;
		
		public int getColumn() {
			return _mmc._x;
		}
	
		public Object2StringComparator(Object2StringMatrixComparatorProvider2 mmc) {
			_mmc = mmc;
		}
	
		@Override
		public int compare(Object[] o1, Object[] o2) {
			
			//try {
			//AppContext.debug( "o1_0=" + o1.get(0)  + "  o2_0="  + o2.get(0));
			//AppContext.debug( "o1[" + _mmc._x + "]=" + o1[_mmc._x]  + ", o2[" + _mmc._x + "]=" + o2[_mmc._x]);
			//System.out.println( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
			
			//return o1.get(_mmc._x).compareTo(o2.get(_mmc._x)) * (_acs ? 1 : -1);
			return o1[_mmc._x].toString().compareTo(  o2[_mmc._x].toString() ) * (_acs ? 1 : -1);
			
			//} catch(Exception ex) {
			//	ex.printStackTrace();
			//	return 0;
			//}
			
		}
	}
}
	
//		// Matrix comparator provider 
//		private class MyMatrixComparatorProvider2<T> implements
//				MatrixComparatorProvider<List<String>> {
//			private int _x = -1;
//
//			private boolean _acs;
//
//			private MyComparator _cmpr;
//
//			public MyMatrixComparatorProvider2(boolean asc) {
//				_acs = asc;
//				_cmpr = new MyComparator(this);
//			}
//
//			@Override
//			public Comparator<List<String>> getColumnComparator(int columnIndex) {
//				this._x = columnIndex;
//				
//				//AppContext.debug("sort by column " + columnIndex);
//				
//				return _cmpr;
//
//			}
//
//			// a real String comparator
//			private class MyComparator implements Comparator<List<String>> {
//				private MyMatrixComparatorProvider2 _mmc;
//
//				public MyComparator(MyMatrixComparatorProvider2 mmc) {
//					_mmc = mmc;
//				}
//
//				@Override
//				public int compare(List<String> o1, List<String> o2) {
//					//AppContext.debug( "o1_0=" + o1.get(0)  + "  o2_0="  + o2.get(0));
//					AppContext.debug( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//					System.out.println( "o1[" + _mmc._x + "]=" + o1.get(_mmc._x)  + ", o2[" + _mmc._x + "]=" + o2.get(_mmc._x));
//					
//					return o1.get(_mmc._x).compareTo(o2.get(_mmc._x)) * (_acs ? 1 : -1);
//					//return ((Object[])o1.get(_mmc._x))[_mmc._x].compareTo(  ((Object[])o2.get(_mmc._x))[_mmc._x]) * (_acs ? 1 : -1);
//					
//				}
//			}
//		}

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
				
			}
			
		   private void addListcell (Row listitem, Object value) {
		        Label lb = new Label(value.toString());
		        lb.setParent(listitem);
		    }

			
		}

		
		private void updateVista(GenotypeQueryParams params, VariantTable vartable) {
			
			if(!AppContext.isIRRILAN()) {
				
				tabVista.setVisible(false);
				tabVistaRev.setVisible(false);
				tabVistaNPB.setVisible(false);
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
				
				this.tabVista.setLabel("Compare " + org + " vs. Nipponbare");
				this.tabVistaRev.setLabel( "Compare Nipponbare vs. " + org );
				if(vistaurl!=null) {
					tabVista.setVisible(true);
					tabVistaRev.setVisible(true);
				}

				tabVistaNPB.setVisible(false);
					
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
		
		
		
		
		
		
		
}