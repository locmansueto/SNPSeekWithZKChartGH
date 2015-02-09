package org.irri.iric.portal.genotype.zkui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
 


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
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTable;
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
//import org.zkoss.addon.Biglistbox;
//import org.zkoss.addon.MatrixComparatorProvider;
//import org.zkoss.addon.MatrixRenderer;
import org.zkoss.zkmax.zul.MatrixComparatorProvider;
import org.zkoss.zkmax.zul.MatrixModel;
import org.zkoss.zkmax.zul.MatrixRenderer;
import org.zkoss.zkmax.zul.Biglistbox;
import org.zkoss.zkmax.zul.event.ScrollEventExt;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
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
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.ext.Selectable;



@Controller
@Scope("session")
public class SNPQueryController extends SelectorComposer<Window> { //<Component>  { //implements Initiator {
 
	private String urljbrowse,gfffile,urlphylo,urljbrowsephylo;
	//private String phylochr, phylostart, phyloend;
	
	int screenwidth=0;
	int screenheight=0;
	
	private int biglistPrevRowVisible=0; 
	int frozenCols=3;
	
	private boolean tallJbrowse;
	//private List<SnpsStringAllvars> origAllVarsListModel;
	private List<SnpsStringAllvars> filteredList;
	private List<Snps2Vars>  list2Vars;
	private int snpallvars_pagesize=50;
	private String tmpfile_spath="";
	private Listheader lhr_variety;
	private Listheader lhrMismatch;
	
	private static final Log log = LogFactory.getLog(SNPQueryController.class);
	private static final  String TEXTSTYLE_ERROR="font-color:red";
	private static final  String TEXTSTYLE_SUCCESS="font-folor:black";
	
    private static final long serialVersionUID = 1L;
    private int chrlength = 43270923; 
    
    //final private String classGenotypefacade = "GenotypeFacadeLegacy";
    final private String classGenotypefacade = "GenotypeFacade";
    
    
    //private VariantTable modelBiglisttable;
    //private VariantTable modelBiglistArray;
    private VariantStringData queryRawResult;
    private VariantStringData queryResult;
    private VariantTable varianttable;

	private List listBiglistheaderData;
	private int biglistboxRows=8;


	
	@Wire
	private Hbox divAlignIndels;
	@Wire
	private Div divIndelLegend;
	@Wire
	private Biglistbox biglistboxArray;
	@Wire
	private Grid gridBiglistheader;
	
	@Wire
	private Biglistbox myComp;
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
    private Listbox selectChr;
    @Wire
    private Listbox snpresult;
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
    @Wire
    private Grid snpallvarsresult;
    @Wire
    private Paging snpallvarsresultpaging;
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
//    @Wire
//    private Window win;
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
    @Wire
    private Listbox selectAllele;
    @Wire
    private Listbox listboxPosition;
    @Wire
    private Listbox listboxAllele;
    
    @Wire
    private Button buttonFilterAllele;
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
    
public SNPQueryController() {
	super();
	// TODO Auto-generated constructor stub
	AppContext.debug("created SNPQueryController " + this);
}

public HttpSession getSession() {
	   return (HttpSession) Executions.getCurrent().getSession().getNativeSession();
}


@Listen("onScrollY = #biglistboxArray")
public void onScrollYTable(ScrollEventExt event) {
	
	//biglistboxArray.
	//biglistboxArray.getMatrixRenderer();
	//Clients.scrollIntoView(Component cmp)
	//Clients.
	
	//AppContext.debug("onScrollYTable event: y=" + event.getY() + "  pos=" + event.getPosInDouble() );
	
	int firstrow = event.getY();
	if(	firstrow%2 == 0)
	{
		gridBiglistheader.setOddRowSclass( biglistboxArray.getSclass() );
		gridBiglistheader.setSclass( biglistboxArray.getOddRowSclass() );
	} else {
		gridBiglistheader.setSclass( biglistboxArray.getSclass() );
		gridBiglistheader.setOddRowSclass( biglistboxArray.getOddRowSclass() );
	}
	
	gridBiglistheader.setModel( new SimpleListModel(((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList(biglistboxRows, event.getY()) ));
	
	/*
	Object2StringMatrixModel model = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl data = (VariantAlignmentTableArraysImpl)model.getData();
	Map<BigDecimal,Variety> mapVarid2Var = this.varietyfacade.getMapId2Variety();
	
	listBiglistheaderData.clear();
	int startRow = event.getY(); 
	
	for(int i=startRow; i<=startRow+biglistboxRows && i<=data.getVarid().length ; i++) {
		listBiglistheaderData.add( new Object[]{ mapVarid2Var.get(data.getVarid()[i]).getName() ,data.getVarname()[i], data.getVarmismatch()[i] } );
	}
	*/
	
	//biglistboxArray.getc

}

public void onScrollYTableOld(ScrollEventExt event) {
	
	//biglistboxArray.
	//biglistboxArray.getMatrixRenderer();
	//Clients.scrollIntoView(Component cmp)
	//Clients.
	
	AppContext.debug("onScrollYTable event: y=" + event.getY() + "  pos=" + event.getPosInDouble() );
	
	int showrow = event.getY() + 7;
	if(biglistPrevRowVisible> event.getY())
		showrow = event.getY();
	
	
	biglistPrevRowVisible = event.getY();
	
	
	List<Component> listChildren = gridBiglistheader.getRows().getChildren();
	if(showrow>listChildren.size()-1) showrow=listChildren.size()-1; 
	Clients.scrollIntoView( listChildren.get(showrow) );
	

	AppContext.debug( "scroll to view: " + ((Label)listChildren.get(showrow).getChildren().get(0)).getValue());
	AppContext.debug( "gridBiglistheader.getRows()=" + gridBiglistheader.getRows().getChildren().size() + "    biglistboxArray.getRows()=" +
			biglistboxArray.getRows());
	
	//biglistboxArray.getc

}


@Listen("onClick = #buttonCreateVarlist")
public void onclickCreateVarlist() {
	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade , "VarietyFacade");
	
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
}


public void onclickCreateVarlistOld() {
	workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade , "VarietyFacade");
	
	SimpleListModel filtereddatamodel = (SimpleListModel)snpallvarsresult.getModel();
	Map mapId2Var =  varietyfacade.getMapId2Variety();
	Set setVarieties = new LinkedHashSet();
	for(int iVar=0; iVar<filtereddatamodel.getSize(); iVar++ )
	{
		SnpsAllvars snpvar =  (SnpsAllvars)filtereddatamodel.getElementAt(iVar);
		setVarieties.add( mapId2Var.get( snpvar.getVar() ));
	}
	
	workspace.addVarietyList( textboxVarlistName.getValue().trim(), setVarieties);
	textboxVarlistName.setValue("");	
}


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

@Listen("onClientInfo = #win")
public void onClientInfo(ClientInfoEvent event) {
	AppContext.debug("onClientInfo:");
	screenwidth= event.getDesktopWidth();
	screenheight = event.getDesktopHeight();
	AppContext.debug("screenwidth=" + screenwidth + "  screenheight=" + screenheight );
}



@Listen("onSelect = #listboxMyVarieties")    
public void  onselectMyVarieties() {
	if(listboxMyVarieties.getSelectedIndex()>0) {

		//comboSubpopulation.setValue("");
		listboxSubpopulation.setSelectedIndex(0);
		checkboxAllvarieties.setChecked(false);
		comboVar1.setValue("");
		comboVar2.setValue("");
		
		if( listboxMyVarieties.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety");
		}
		
		
	}
}


@Listen("onSelect = #listboxMySNPList")    
public void  onselectMySNPList() {
	if(listboxMySNPList.getSelectedIndex()>0) {

		if( listboxMySNPList.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=snp");
		}
		else {
			this.selectChr.setSelectedIndex( Integer.parseInt( listboxMySNPList.getSelectedItem().getLabel().split(":")[0].replace("CHR ","") )-1 );
			this.comboGene.setValue("");
			this.intStart.setValue(null);
			this.intStop.setValue(null);
		}
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
		biglistboxArray.setModel( new Object2StringMatrixModel(varianttable));
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
			AppContext.debug("cellvalue = " + varalleles[irow][colIdx]);
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
		
		biglistboxArray.setModel( new Object2StringMatrixModel(newtable));
		labelFilterResult.setValue(listInclude.size() + "/" + queryRawResult.getListVariantsString().size() + " varieties");
		
		//gridBiglistheader.setModel( new SimpleListModel(  newtable.getRowHeaderList() )) ;
		gridBiglistheader.setModel(new SimpleListModel( ((VariantAlignmentTableArraysImpl)newtable).getRowHeaderList( biglistboxRows) ));

		gridBiglistheader.invalidate();
		//biglistboxArray.invalidate();
		
	}
	
}


public void onclickFilterAlleleOld() {

	VariantStringData data = queryResult;
	Map<BigDecimal,IndelsAllvarsPos> mapIndelId2Indel=null;
	Map<Integer,Integer> mapMergedIdx2SnpIdx=null;
	if(data.hasIndel()) {
		mapIndelId2Indel=data.getIndelstringdata().getMapIndelId2Indel();
		if(data.hasSnp()) mapMergedIdx2SnpIdx=data.getSnpstringdata().getMapMergedIdx2SnpIdx();
	}
	
	
	Map<Integer, BigDecimal> mapIdx2Pos =  data.getMapIdx2Pos();

	Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
	Iterator<Integer> itIdx = mapIdx2Pos.keySet().iterator();
	while(itIdx.hasNext()) {
		Integer idx = itIdx.next();
		mapPos2Idx.put( mapIdx2Pos.get(idx), idx);
	}
	//modelmatrix.get
	
	if(filteredList==null)
		filteredList = new ArrayList();
	List newFilteredList = new ArrayList();
	
	int prevFilteredList = filteredList.size();
	
	String selpos = listboxPosition.getSelectedItem().getLabel();
	if(selpos.isEmpty()) {
		newFilteredList = queryResult.getListVariantsString();
		listboxAllele.setModel(new SimpleListModel(new ArrayList()));
	} else {
	
		BigDecimal pos =  BigDecimal.valueOf(Long.valueOf(selpos));
		
		String allele = listboxAllele.getSelectedItem().getLabel(); 
		
		//Iterator<SnpsStringAllvars> itVars = origAllVarsListModel.iterator();
		Iterator<SnpsStringAllvars> itVars;
		if(prevFilteredList>0)
			itVars = filteredList.iterator();
		else	
			itVars = queryResult.getListVariantsString().iterator();
		while(itVars.hasNext()) {
			
			SnpsStringAllvars snpvars = itVars.next();
			
			boolean include = false;
			
			// check for allele equalities,  heterogygous is equal if in one of alleles
			
			if(snpvars instanceof IndelsStringAllvars) {
				IndelsStringAllvars indelstring = (IndelsStringAllvars)snpvars;
				if(indelstring.getAllele1(pos)!=null) {
					//if( allele.equals(   genotype.getMapIndelId2Indel().get( indelstring.getAllele1(pos) ) ) ) {
					if( allele.equals(IndelStringService.getIndelAlleleString(mapIndelId2Indel.get(indelstring.getAllele1(pos)))) ||   
						(indelstring.getAllele2(pos)!=null &&  allele.equals(IndelStringService.getIndelAlleleString(mapIndelId2Indel.get(indelstring.getAllele2(pos))) ) )) {
							include = true;
					};
				} else if(indelstring.getVarnuc()!=null) {
					int idx = mapPos2Idx.get(pos);
					Integer  snpidx = mapMergedIdx2SnpIdx.get(idx);
					if(snpidx!=null) {
						if( allele.equals(indelstring.getVarnuc().substring(snpidx,snpidx+1)) ||   
								(indelstring.getMapPosIdx2Allele2()!=null &&  indelstring.getMapPosIdx2Allele2().get(snpidx)!=null &&
										indelstring.getMapPosIdx2Allele2().get(pos).equals(allele) )) {
									include = true;
							};
					}
				}
				
			} else {
				Integer snpidx = mapPos2Idx.get(pos);
				if(snpidx!=null) {
					if( allele.equals(snpvars.getVarnuc().substring(snpidx,snpidx+1)) ||   
							(snpvars.getMapPosIdx2Allele2()!=null &&  snpvars.getMapPosIdx2Allele2().get(snpidx)!=null &&
									snpvars.getMapPosIdx2Allele2().get(snpidx).equals(allele) )) {
								include = true;
						};
				}
			}
			
			if(include) newFilteredList.add(snpvars); 
		}
		
		filteredList = newFilteredList;
	}
	
	if(prevFilteredList>0)
		labelFilterResult.setValue(newFilteredList.size() + "/" + prevFilteredList + "/" + queryResult.getListVariantsString().size() + " varieties");
	else 
		labelFilterResult.setValue(newFilteredList.size()+ "/" + queryResult.getListVariantsString().size() + " varieties");

	AppContext.debug(  "allvars=" +  queryResult.getListVariantsString().size()  + "  newFilteredList=" + filteredList.size());
	
	if(newFilteredList.size()>0)
	{
		myComp.setModel(new FakerMatrixModel(newFilteredList, data.getListPos())); //strRef));
		myComp.setVisible(true);
		hboxDownload.setVisible(true);
	} else {
		myComp.setVisible(false);
		hboxDownload.setVisible(false);
	}
	

	
}



@Listen("onClick =#buttonAddAlleleConstraint")
public void buttonAddPassConstraint() {
	genotype  =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");
	
	Hbox hboxAllele = new Hbox();
	
	Listbox listboxPassValues = new Listbox();
	listboxPassValues.setMold("select");
	listboxPassValues.setRows(1);
	
	hboxAllele.appendChild( new Label("Position"));
	
	
    java.util.List listPosition = new java.util.ArrayList();
    Iterator<SnpsAllvarsPos> itposlist = genotype.getSnpsposlist().iterator();
    listPosition.add("");
    while(itposlist.hasNext()) {
    	listPosition.add(  itposlist.next().getPos()  );
    }
    
	Listbox listboxMorePosConstraint= new Listbox();
	listboxMorePosConstraint.setRows(1);
	listboxMorePosConstraint.setMold("select");
	
	listboxMorePosConstraint.setModel( new SimpleListModel(listPosition));
   
	hboxAllele.appendChild(listboxMorePosConstraint);
	Hbox boxSep = new Hbox();
	boxSep.setWidth("15px");
	hboxAllele.appendChild(boxSep);

	java.util.List listAlleles = new ArrayList();
	listAlleles.add("");
	listAlleles.add("A");
	listAlleles.add("T");
	listAlleles.add("G");
	listAlleles.add("C");
	

	Listbox listboxMoreAlleleConstraint= new Listbox();
	listboxMoreAlleleConstraint.setRows(1);
	listboxMoreAlleleConstraint.setMold("select");
	listboxMoreAlleleConstraint.setModel(new SimpleListModel( listAlleles ));;

	hboxAllele.appendChild(new Label("Allele"));
	hboxAllele.appendChild(listboxMoreAlleleConstraint);
	
	Hbox boxSep2 = new Hbox();
	boxSep2.setWidth("15px");
	hboxAllele.appendChild(boxSep2);
	
	hboxAllele.appendChild(vboxFilterAllele.getLastChild().getLastChild());
    
    vboxFilterAllele.appendChild( hboxAllele );
}



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
		genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
		Gene gene = genotype.getGeneFromName( comboGene.getValue() );
		selectChr.setSelectedIndex(gene.getChr()-1);
		intStart.setValue(gene.getFmin());
		intStop.setValue(gene.getFmax());
		listboxMySNPList.setSelectedIndex(0);
	}
}

/**
 * Chromosome selected
 */
@Listen("onSelect = #selectChr")
public void setchrlength() {
	
	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
	
	listboxMySNPList.setSelectedIndex(0);
	comboGene.setValue("");
	
	
	if( selectChr.getSelectedItem().getLabel().equals("whole genome") ) {
		this.intStart.setValue(null);
		this.intStop.setValue(null);
		labelLength.setValue("End:");
		
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
		labelLength.setValue("End: (length " + length + " bp)" );
		chrlength=length.intValue();
		
		checkboxCoreSNP.setDisabled(false);
		this.searchButton.setDisabled(false);
		this.intStart.setDisabled(false);
		intStop.setDisabled(false);
		
		buttonsDownloadNodisplay.setVisible(false);
		
		listboxSubpopulation.setDisabled(false);
		comboGene.setDisabled(false);
		listboxMySNPList.setDisabled(false);

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
	return genotype.getFeatureLength(selectChr.getSelectedItem().getLabel());
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

		AppContext.debug("loading " + urljbrowse);
		
		Clients.showBusy("Loading JBrowse");
		if(tallJbrowse) {
			
			int jbrowsebp_margin =     (intStop.getValue()-intStart.getValue())*AppContext.getJBrowseMargin();
			int start = intStart.getValue()-jbrowsebp_margin;
			if(start<0) start=1;

			//if(queryResult.getListVariantsString()!=null) {
			if(queryRawResult!=null) {
				
				//queryRawResult.merge();
					
				List listGFF = new ArrayList();
				if(this.checkboxSNP.isChecked() && checkboxIndel.isChecked())
				{
					List listGFFSNP = new ArrayList();
					List listGFFIndel = new ArrayList();
				
					Iterator<SnpsStringAllvars> itSnpstring = queryRawResult.getListVariantsString().iterator();
					while(itSnpstring.hasNext()) {
						SnpsStringAllvars snpstring = itSnpstring.next();
						if(snpstring instanceof IndelsStringAllvars) 
							listGFFIndel.add(snpstring);
						else
							listGFFSNP.add(snpstring);
					}
					AppContext.debug("listGFFIndel.size=" + listGFFIndel.size() +", listGFFSNP.size=" + listGFFSNP.size() );
					listGFF.addAll( createSNPPointStringVarietyGFF(listGFFSNP,  queryRawResult.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) );
					listGFF.addAll( createIndelStringVarietyGFF(listGFFIndel,   queryRawResult.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected() || !checkboxMismatchOnly.isChecked() ) );
				}
				else if(this.checkboxSNP.isChecked())
				{
					listGFF = createSNPStringVarietyGFF(queryRawResult.getListVariantsString(),   queryRawResult.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) ;
				}
				else if(this.checkboxIndel.isChecked())
				{
					listGFF = createIndelStringVarietyGFF(queryRawResult.getListVariantsString(),  queryRawResult.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  );
				}
				
				writeGFF(listGFF, gfffile);
				
			}
			else {
				throw new RuntimeException("onselectTabJbrowse: queryResult!=null");
				/*
				List listGFFSNP = null;
				List listGFFIndel = null;
				if(this.checkboxSNP.isChecked())
				{
					List newpagelist = genotype.getSNPStringInAllVarieties( start , intStop.getValue(), Integer.valueOf(selectChr.getSelectedItem().getLabel())); //,  true , -1,  -1 );
					listGFFSNP = createSNPStringVarietyGFF(newpagelist,   queryResult.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) ;
				}
	
				if(this.checkboxIndel.isChecked())
				{
					List newpagelist = genotype.getSNPStringInAllVarieties( start , intStop.getValue(), Integer.valueOf(selectChr.getSelectedItem().getLabel())); //,  true , -1,  -1 );
					listGFFIndel = createIndelStringVarietyGFF(newpagelist,  queryResult.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  || !checkboxMismatchOnly.isChecked() );
				}
				
				if(checkboxSNP.isChecked() && checkboxIndel.isChecked()) {
					listGFFSNP.addAll( listGFFIndel);
					writeGFF(listGFFSNP, gfffile);	
				} else if(checkboxSNP.isChecked()) {
					writeGFF(listGFFSNP, gfffile);	
				} else if (checkboxIndel.isChecked()) {
					writeGFF(listGFFIndel, gfffile);	
				}
				*/
			}
			
			
			AppContext.resetTimer("write gff");
			iframeJbrowse.setSrc( urljbrowse );
			
			divBlackSyn.setVisible(false);
			divMismatch.setVisible(false);
			divNucleotide.setVisible(false);
			divNucleotideIndels.setVisible(false);
			
			if(radioColorAllele.isSelected()) {
				if(this.checkboxIndel.isChecked())
					 divNucleotideIndels.setVisible(true);
				else divNucleotide.setVisible(true);
			}
			if(this.radioColorMismatch.isSelected()) divMismatch.setVisible(true);
			if(radioGraySynonymous.isSelected()) divBlackSyn.setVisible(true);
			
		}
		else 
			iframeJbrowse.setSrc( urljbrowse );
		
	
	    msgJbrowse.setVisible(true);
	    iframeJbrowse.setVisible(true);
	    Clients.clearBusy();
	}
    urljbrowse=null;
		
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
				updateJBrowsePhylo( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
				Map mapVariety2Order = genotype.orderVarietiesFromPhylotree(gfffile.replace("gff",""));
				List newpagelist;
				
				int jbrowsebp_margin =     (intStop.getValue()-intStart.getValue())*AppContext.getJBrowseMargin();
				newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue()-jbrowsebp_margin, intStop.getValue()+jbrowsebp_margin, Integer.valueOf(selectChr.getSelectedItem().getLabel())); //,  true , -1,  -1 );
				AppContext.debug(mapVariety2Order.size() + " varieties for ordering;  "  + newpagelist.size() + " snps for phylojbrowse gff");
				//createSNPStringVarietyGFF(newpagelist, gfffile + ".phylo.gff" , mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()-jbrowsebp_margin), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  false );
				List listGFF = createSNPStringVarietyGFF(newpagelist,  mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()-jbrowsebp_margin), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  false );
				writeGFF(listGFF, gfffile + ".phylo.gff");
				
				AppContext.resetTimer("writing  " + gfffile+".phylo.gff");
			}
		}
	
	
	if(urljbrowsephylo!=null) {

		AppContext.debug("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
		while(true) {
			File f = new File( AppContext.getTempDir() + "/" + gfffile.replace(".gff", ".newick") );
			if(f.exists()) break;
			try {
				Thread.sleep(5000);
				AppContext.debug("waiting newick file");
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		iframeJbrowsePhylo.setSrc( urljbrowsephylo );
	    msgJbrowsePhylo.setVisible(true);
	    iframeJbrowsePhylo.setVisible(true);

	}
    Clients.clearBusy();
	urljbrowsephylo=null;
		
}

/**
 * Phylogenetic tree tab selected 
 */


@Listen("onClick = #buttonRenderTree")
//@Listen("onSelect = #tabPhylo")    
public void onselectTabPhylo() {
	//tabboxDisplay.setHeight( "100%");
	AppContext.debug("loading phylotree " + urlphylo);
    //show_phylotree(selectChr.getSelectedItem().getLabel().toUpperCase().replace("CHR0","").replace("CHR",""), intStart.getValue().toString() , intStop.getValue().toString()  );

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
    selectChr.setSelectedIndex(0);
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
	
	Iterator itHead =  snpresult.getHeads().iterator();
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

private void downloadTable(String filename, String delimiter) {
	
	AppContext.debug("downloading from table...");
	
	//SimpleListModel listmodel = (SimpleListModel)snpallvarsresult.getModel();
	
	StringBuffer buff = new StringBuffer();
	
	Iterator itHead = snpallvarsresult.getHeads().iterator();
	while(itHead.hasNext()) {
		Component comp = (Component)itHead.next();
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
	
	//SimpleListModel listmodel = (SimpleListModel)snpallvarsresult.getModel();
	
	//snpallvarsresult.getCell(row, col)
	Map<BigDecimal,Variety> mapVarid2Var = varietyfacade.getMapId2Variety();
	
	List displaylist = filteredList;
	if(displaylist==null) displaylist= queryResult.getListVariantsString();
	Iterator<SnpsStringAllvars> itDisplay = displaylist.iterator();
	while(itDisplay.hasNext()) {
		SnpsStringAllvars snpstring = itDisplay.next();
		Map<Integer,Character> mapPosidx2allele2 = snpstring.getMapPosIdx2Allele2();
		
		buff.append( "\""+ mapVarid2Var.get( snpstring.getVar()).getName() + "\"" + delimiter + snpstring.getMismatch() + delimiter );
		
		String allele1 = snpstring.getVarnuc();
		for(int iStr=0; iStr<allele1.length(); iStr++ ) {
			char charistr = allele1.charAt(iStr);
			if(charistr!='0' && charistr!='.' && charistr!='*' && charistr!='$')
				buff.append(charistr);
			
			if(mapPosidx2allele2!=null && mapPosidx2allele2.get(iStr)!=null) {
				buff.append("/").append( mapPosidx2allele2.get(iStr) );
			}
			if(iStr+1<allele1.length()) buff.append(delimiter);
		}
		buff.append("\n");
	}
	
	
	try {
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


//private void downloadBigListbox(String filename, String delimiter) {
//	
//}

private void downloadBigListbox(String filename, String delimiter) {
	Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
	
	
	StringBuffer buff = new StringBuffer();
	buff.append("VARIETY").append(delimiter).append("IRIS ID").append(delimiter).append("MISMATCH").append(delimiter);
	Double[] positions = table.getPosition(); 
	for(int i=0; i<positions.length; i++) {
		buff.append(positions[i]);
		if(i<positions.length-1) buff.append(delimiter);
	}
	buff.append("\nREFERENCE").append(delimiter).append(delimiter).append(delimiter);
	String refs[] =  table.getReference();
	for(int i=0; i<refs.length; i++) {
		buff.append(refs[i]);
		if(i<refs.length-1) buff.append(delimiter);
	}
	buff.append("\n");
	Object varalleles[][] = table.getVaralleles();
	AppContext.debug( "mxn=" + varalleles.length  + "x" + varalleles[0].length);
	AppContext.debug("positions = " + refs.length);
	AppContext.debug("varids = " + table.getVarname().length);
	
	Map<BigDecimal,Variety> mapVarId2Var = varietyfacade.getMapId2Variety();
	
	for(int i=0; i< table.getVarid().length; i++) {
		buff.append(table.getVarname()[i]).append(delimiter).append(  mapVarId2Var.get(table.getVarid()[i]).getIrisId()  ).append(delimiter).append(table.getVarmismatch()[i]).append(delimiter);
		for(int j=0; j<refs.length; j++) {
			buff.append( varalleles[i][j] );
			if(j<refs.length-1) buff.append(delimiter);
		}
		buff.append("\n");
	}
	
	try {
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
		      
		      
	String chr= this.selectChr.getSelectedItem().getLabel();
	
	
	Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
	
	
	StringBuffer buff = new StringBuffer();
	//buff.append("POSITION").append(delimiter).append("MISMATCH").append(delimiter);
	Double[] positions = table.getPosition();
	String refs[] =  table.getReference();
	for(int i=0; i<positions.length; i++) {
		if(!refs[i].equals("-")) {
			int pos = positions[i].intValue();
			buff.append(chr).append("\t").append("snp" + chr + "-" + pos) .append("\t0\t").append(pos+1).append("\n");
		}
	}
	try {
		String filetype = "text/plain";
		Filedownload.save(  buff.toString(), filetype , filename + ".map");
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
		AppContext.debug("snpallvars download complete!"+ filename +".map Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
		
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	
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
	
	try {
		String filetype = "text/plain";
		Filedownload.save(  buff.toString(), filetype , filename + ".ped");
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
		AppContext.debug("snpallvars download complete!"+ filename +  ".ped Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );
		
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
}


private void downloadBigListboxOld(String filename, String delimiter) {
	
	AppContext.debug("downloading from biglisbox...");
	Map<BigDecimal, Variety> mapVarId2Var = varietyfacade.getMapId2Variety();
	
	//SimpleListModel listmodel = (SimpleListModel)snpallvarsresult.getModel();
	
	StringBuffer buff = new StringBuffer();
	
	MatrixModel matrixModel = myComp.getModel();
	int matrixCols = matrixModel.getColumnSize();
	for(int iHead = 0; iHead<matrixModel.getHeadSize(); iHead++) {
		Object headdata = matrixModel.getHeadAt(iHead);
		for(int iCols=0; iCols<matrixCols; iCols++) {
			
			Object  header = matrixModel.getHeaderAt(headdata, iCols);
			buff.append(header);
			if(iCols+1<matrixCols) buff.append(delimiter);
		}
		buff.append("\n");
	}
	Map<Integer,BigDecimal> mapMergedIdx2Pos = genotype.getMapMergedIdx2Pos();
	Map<Integer,BigDecimal> mapIdx2Pos  = genotype.getMapIndelIdx2Pos();
	Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels = genotype.getMapIndelId2Indel();
	Map<Integer, Integer> mapMergedIdx2SnpIdx = genotype.getMapMergedIdx2SnpIdx();

	for(int iCell = 0; iCell<matrixModel.getSize(); iCell++) {
		Object cellsdata = matrixModel.getElementAt(iCell);
		for(int iCols=0; iCols<matrixCols; iCols++) {
			SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
			if (iCols>2) {
				if(snpstr instanceof IndelsStringAllvars) {
					
					int j=iCols-frozenCols;
					
					BigDecimal pos = null;
					if(mapMergedIdx2Pos!=null) 
						pos = mapMergedIdx2Pos.get(j);
					else 
						pos = mapIdx2Pos.get(j);
					
					if(pos!=null) {
					
						IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
						
						BigDecimal alleleid = indelstring.getAllele1( pos );
						IndelsAllvarsPos indelpos = mapIndelId2Indels.get(alleleid);
						
						//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
						if(alleleid!=null && indelpos!=null) {
							
							int dellen = indelpos.getDellength();
							
							if(dellen>0) {
								String insstr = indelpos.getInsString();
								if(insstr!=null && insstr.length()>0) {
									if(dellen==1 && insstr.length()==1)
										buff.append("snp ->" + insstr );
									else buff.append("del " + dellen + "->" + insstr );
								} else
									buff.append("del " + dellen );	
							} else {
								String insstr = indelpos.getInsString();
								if(insstr!=null && insstr.length()>0) {
									buff.append( insstr );
								} else 
									buff.append("ref");
							}
							
							BigDecimal alleleid2 = indelstring.getAllele2( pos );
							indelpos = mapIndelId2Indels.get(alleleid2);
							
							if(alleleid2!=null && !alleleid2.equals(alleleid)) {
								alleleid=alleleid2;
								indelpos = mapIndelId2Indels.get(alleleid);
								if(indelpos!=null) {
									dellen = indelpos.getDellength();
									if(dellen>0) {
										String insstr = indelpos.getInsString();
										if(insstr!=null && insstr.length()>0) {
											if(dellen==1 && insstr.length()==1)
												buff.append("/snp ->" + insstr );
											else buff.append("/del " + dellen + "->" + insstr);
										} else
											buff.append("/del " + dellen );	
									} else {
										String insstr = indelpos.getInsString();
										if(insstr!=null && insstr.length()>0) {
											buff.append("/"  + insstr );
										} else 
											buff.append("/ref");
									}
									
									
								}								
							}
						}
						
						if(indelstring.getVarnuc()!=null) {
							if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(j) && mapMergedIdx2SnpIdx.get(j)!=null) {
								j = mapMergedIdx2SnpIdx.get(j);
								char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
								if(element!='0' && element!='.' && element!=' ' && element!='*') {
									String hetero = "";
									Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
									if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) hetero = "/" + mapPosidx2allele2.get(j);
									buff.append( element ).append(hetero);
								}
							}
						}
						
					}
					
				} else {
					int j=iCols-frozenCols;
					if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(j) && mapMergedIdx2SnpIdx.get(j)!=null) {
						j = mapMergedIdx2SnpIdx.get(j);
					}
					
					if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols-frozenCols)!=null ) {
						char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
						if(element!='0' && element!='.' && element!=' ' && element!='*') {
							String hetero = "";
							Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
							if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) hetero = "/" + mapPosidx2allele2.get(j);
							buff.append( element ).append(hetero);
						}
					}
				}
			}
			else if (iCols==frozenCols-1)
				buff.append( snpstr.getMismatch() );
			else if(iCols==1) {
				if(delimiter.equals(","))
					buff.append( "\""+  mapVarId2Var.get( snpstr.getVar() ).getIrisId() + "\"");
				else
					buff.append(  mapVarId2Var.get( snpstr.getVar() ).getIrisId() );
			}
			else {
				if(delimiter.equals(","))
					buff.append( "\""+  mapVarId2Var.get( snpstr.getVar() ).getName() + "\"");
				else
					buff.append(  mapVarId2Var.get( snpstr.getVar() ).getName() );
			}

			if(iCols+1<matrixCols) buff.append(delimiter);
		}
		buff.append("\n");
	}
	
	try {
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

private String queryFilename() {
	String filename = comboGene.getValue();
	if(filename.isEmpty()) {
		if( intStart !=null && intStart.getValue()!=null && intStop !=null && intStop.getValue()!=null )
			filename = "chr" + selectChr.getSelectedItem().getLabel() + "-" +  intStart.getValue() + "-" + intStop.getValue();
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
	else if(snpresult.isVisible())
		download2VarsList("snp2vars-" +   queryFilename() + ".txt", "\t");
}


@Listen("onClick = #buttonDownloadPlink")    
public void downloadPlink()    {
	 //downloadDelimited("snpfile.txt", "\t"); 
	
	//if(snpallvarsresult.isVisible())
		//downloadTable("snp3kvars-" + queryFilename() + ".txt", "\t");
	if(biglistboxArray.isVisible())
		downloadBigListboxPlink("snp3kvars-" + queryFilename() );
	
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
	else if(snpresult.isVisible())
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
	
	Clients.showBusy("Fetching from Database");
	searchWithServerpaging(null,null);
	Clients.clearBusy();
	
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
		GenotypeQueryParams params = new GenotypeQueryParams(setVarieties, selectChr.getSelectedItem().getLabel(), null,
				null ,  checkboxSNP.isChecked() , checkboxIndel.isChecked(), checkboxCoreSNP.isChecked() ,
				checkboxMismatchOnly.isChecked(), null, null, null, checkboxAlignIndels.isChecked());			
		params.setDelimiter(delimiter);
		params.setFilename(filename);
		genotype.downloadGenotypeGenome(params);
	} catch(Exception ex)
	{
		ex.printStackTrace();
		Messagebox.show(ex.getMessage(), null, Messagebox.OK, Messagebox.ERROR);
	}
}


@Listen("onClick = #checkboxAlignIndels")
public void onclickAlignIndels() {

	try {
		GenotypeQueryParams params =  fillGenotypeQueryParams();
		params.setbAlignIndels( checkboxAlignIndels.isChecked() );
		varianttable = genotype.fillGenotypeTable(new VariantAlignmentTableArraysImpl() , queryRawResult, params);
		queryResult = varianttable.getVariantStringData();
		
		Object2StringMatrixRenderer renderer  = new Object2StringMatrixRenderer(queryResult,  varietyfacade.getMapId2Variety(), params);
		biglistboxArray.setMatrixRenderer(renderer);
		biglistboxArray.setModel(new Object2StringMatrixModel(varianttable)); //strRef));
		
		if(checkboxIndel.isChecked() &&  !checkboxAlignIndels.isChecked())
			biglistboxArray.setColWidth("60px");
		else biglistboxArray.setColWidth("30px");
	
	} catch (Exception ex) {
		Messagebox.show(ex.getMessage(), null, Messagebox.OK, Messagebox.ERROR);
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
 *	1. newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel().trim(), desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize  );
    2. updateAllvarsList(newpagelist,false,null,null,null, desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize );		
    
    
    
 */

private GenotypeQueryParams fillGenotypeQueryParams() {

	Set setVarieties = null;
	String sSubpopulation = null;
	
	if( listboxSubpopulation.getSelectedIndex()>0) {
		setVarieties = genotype.getVarietiesForSubpopulation( listboxSubpopulation.getSelectedItem().getLabel() );
		if(listboxSubpopulation.getSelectedIndex()>1) sSubpopulation=listboxSubpopulation.getSelectedItem().getLabel();
	
	} else if( listboxMyVarieties.getSelectedIndex()>0) {
		setVarieties = workspace.getVarieties( listboxMyVarieties.getSelectedItem().getLabel()  );
	}
	
	Set snpposlist = null;
	if(listboxMySNPList.getSelectedIndex()>0) {
		String chrlistname[] = listboxMySNPList.getSelectedItem().getLabel().split(":");
		snpposlist = workspace.getSnpPositions( Integer.valueOf( chrlistname[0].replace("CHR","").trim() ) , chrlistname[1].trim() );
	}
	

	String genename = comboGene.getValue().trim().toUpperCase();
	String sLocus = null;
	if( !genename.isEmpty() ) sLocus = genename;
	

	
	GenotypeQueryParams params = new GenotypeQueryParams(setVarieties, selectChr.getSelectedItem().getLabel(), new Long( intStart.getValue()),
			new Long( intStop.intValue() ) ,  checkboxSNP.isChecked() , checkboxIndel.isChecked(), checkboxCoreSNP.isChecked() ,
			checkboxMismatchOnly.isChecked(), snpposlist,  sSubpopulation, sLocus, checkboxAlignIndels.isChecked());			
	return params;
	
}

public void searchWithServerpaging(String filename, String delimiter)    {
	
	// true if output is written to file
	boolean writetofile= filename!=null && !filename.isEmpty();
	
	// to facilitate render on Tab select for JBrowse and phylogenetic tree
	GenotypeFacade.snpQueryMode mode=null;
	

	
	if(writetofile)
		AppContext.debug("Writing to file");
	else
	{
		
		// empty table from previous results
		snpallvarsresult.setModel(new SimpleListModel(new java.util.ArrayList()));
		snpresult.setModel(new SimpleListModel(new java.util.ArrayList()));

		// hide the tables
		snpallvarsresultpaging.setVisible(false);
	    snpallvarsresult.setVisible(false);
	    snpresult.setVisible(false);
	    
	    //snpallvarsresult.setWidth("1000px");
	    snpresult.setWidth("1000px");
	    
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
	}
	
	
	//tabTable.setWidth("100%");
	//tabboxDisplay.setWidth("1000px");
	//win.setWidth(labelScreenWidth.getValue());
	
	//AppContext.debug("origwidth=" + labelScreenWidth.getValue());
	
		genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	
		if(this.listboxMySNPList.getSelectedIndex()>0)
			msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " , list " + listboxMySNPList.getSelectedItem().getLabel() );
		else if(intStart.getValue()!=null)
			msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
		else 
			msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() );
		
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
			if(!var1.isEmpty() && !var2.isEmpty()) {
				if(checkboxMismatchOnly.isChecked())
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
				else
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLREFPOS; //SNPQUERY_REFERENCE;
			}
			else if(var1.isEmpty() || var2.isEmpty() )
			{
				//Messagebox.show("At least one variety is required", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				Messagebox.show("Two varieties are required for comparison, or check All Varieties", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				
				return;	
			}
			//else
			//	mode = GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE;
		}

		
		
		
		
		
		// set genotype facade state
//		genotype.limitVarieties(setVarieties);	
//		genotype.setCore( checkboxCoreSNP.isChecked() );
//		genotype.setColorByNonsyn(  this.radioGraySynonymous.isSelected() );
//		genotype.setNonsynOnly( this.radioNonsynOnly.isSelected() );
//		genotype.setMismatchOnly( this.checkboxMismatchOnly.isChecked() );
//		genotype.setIncludeSNP(this.checkboxSNP.isChecked());
//		genotype.setIncludeIndel(this.checkboxIndel.isChecked());
		
		// initialize empty SNP list
		List listSNPs = new java.util.ArrayList();

		String genename = comboGene.getValue().trim().toUpperCase();
		gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
		
		AppContext.startTimer();

		String sLocus = null;
		if( !genename.isEmpty() )
		{
			String genename2 = comboGene.getValue().trim();
			Gene gene2 =  genotype.getGeneFromName( genename2);
			
			
			intStart.setValue(gene2.getFmin() );	
			intStop.setValue(gene2.getFmax() );	
			selectChr.setSelectedIndex( gene2.getChr()-1 );
			sLocus = genename;
		}
		

			// SNPs on all varieties in region
			// vier chr pos region
		
			//int chrlen = genotype.getFeatureLength( selectChr.getSelectedItem().getLabel());
	
			Set snpposlist = null;

			
			if(listboxMySNPList.getSelectedIndex()>0) {
				
				String chrlistname[] = listboxMySNPList.getSelectedItem().getLabel().split(":");
				snpposlist = workspace.getSnpPositions( Integer.valueOf( chrlistname[0].replace("CHR","").trim() ) , chrlistname[1].trim() );
				
			} else if(this.selectChr.getSelectedItem().getLabel().equals("whole genome") ) {
				if( this.listboxSubpopulation.getSelectedIndex() > 0 ) {
					Messagebox.show("Limit to 100 varieties (using a list) for every whole genome download", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
					return;
				}   		
			}
			else {
				int chrlen = genotype.getFeatureLength( selectChr.getSelectedItem().getLabel());
				
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
							genotype.setCore( true );
							maxlength =  AppContext.getMaxlengthCore();
						}
						else return;
						
					}
				}   
				
			}
			
			
			String chr = selectChr.getSelectedItem().getLabel().trim();
			if(chr.isEmpty())
			{
				Messagebox.show("No chromosome selected", "INVALID CHROMOSOME VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}


			
//			GenotypeQueryParams params = new GenotypeQueryParams(setVarieties, selectChr.getSelectedItem().getLabel(), new Long( intStart.getValue()),
//					new Long( intStop.intValue() ) ,  checkboxSNP.isChecked() , checkboxIndel.isChecked(), checkboxCoreSNP.isChecked() ,
//					checkboxMismatchOnly.isChecked(), snpposlist,  sSubpopulation, sLocus, checkboxAlignIndels.isChecked());			

			GenotypeQueryParams params =  fillGenotypeQueryParams();
			

			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){


				List  newpagelist; 
				
				if(writetofile) {
					
//					AppContext.debug("downloading file...");
//					
//					if(snpposlist!=null && !snpposlist.isEmpty()) {
//						
//						newpagelist = genotype.getSNPStringInAllVarieties(snpposlist, Integer.valueOf(chr)); //, checkboxCoreSNP.isChecked());, true,  -1,  -1 );
//						updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + "  SNPLIST: " + listboxMySNPList.getSelectedItem().getLabel() );
//				        AppContext.resetTimer("query allvars region " + snpposlist.size() + " size snplist");	
//					}
//					else if(selectChr.getSelectedItem().getLabel().equals("whole genome")) {
//						
//
//						/*
//						StringBuffer buffMap = new StringBuffer();
//						Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
//						while(itPos.hasNext()) {
//							SnpsAllvarsPos posnuc=itPos.next();
//							buffMap.append(posnuc.getPos());
//						}				
//						
//						File file = new File(filename);
//						FileWriter fw = new FileWriter(file.getAbsoluteFile());
//						BufferedWriter bw = new BufferedWriter(fw);
//						bw.write(buff.toString());
//						bw.flush();
//						bw.close();
//						*/
//						
//						
//						String filenames[] = new String[12];
//						String tmpname = AppContext.createTempFilename();
//						String fileext = ".txt";
//						if(delimiter.equals(",")) fileext = ".csv";
//						else if(delimiter.equals("plink")) fileext = ".ped";
//
//						
//						for(int ichr=1; ichr<=12; ichr++) {
//							Integer chrlen = genotype.getFeatureLength( Integer.toString(ichr));
//							newpagelist = genotype.getSNPStringInAllVarieties(0, chrlen, Integer.valueOf(ichr)); //,  true,  -1,  -1 );
//							filenames[ichr-1]="chr-" + ichr + "-" +  filename + "-" + tmpname +  fileext;
//							updateAllvarsList(newpagelist,true,filenames[ichr-1] ,delimiter,  "REGION: WHOLE CHR " + ichr + " " +  1 + ".." + chrlen, false , Integer.valueOf(ichr));
//							
//							
//							
//						}
//						
//						new CreateZipMultipleFiles(filename + "-" + tmpname + ".zip", filenames ).create();
//						
//						try {
//							Filedownload.save(new File(filename + "-" + tmpname + ".zip") , "application/zip");
//							AppContext.debug("File download complete! Saved to: "+ filename);
//							} catch(Exception ex)
//							{
//								ex.printStackTrace();
//							}
//						
//				        AppContext.resetTimer("query whole genome");	
//						
//					}
//					else {
//						//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr), false,  -1,  -1 );
//						newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr)); //,  true,  -1,  -1 );
//						updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() );
//				        AppContext.resetTimer("query allvars region " + (intStop.getValue() - intStart.getValue()) + " bp");	
//						
//					}

					return;
				}
				
				
				
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
				
				try {
					
					queryRawResult = genotype.queryGenotype( params);
					varianttable = genotype.fillGenotypeTable(varianttable , queryRawResult, params);
					queryResult = varianttable.getVariantStringData();
					
					Object2StringMatrixRenderer renderer  = new Object2StringMatrixRenderer(queryResult,  varietyfacade.getMapId2Variety(), params);

					//biglistboxArray.setFrozenCols(0);
					//biglistboxArray.setFixFrozenCols(false);
					biglistboxArray.setMatrixRenderer(renderer);
					biglistboxArray.setAutoCols(true);
					//biglistboxArray.setHeight( "400px");
					biglistboxArray.setHeight( "410px");
					tabboxDisplay.setWidth( "100%" );
					if(checkboxIndel.isChecked() &&  !checkboxAlignIndels.isChecked())
						biglistboxArray.setColWidth("60px");
					else biglistboxArray.setColWidth("30px");

					//biglistboxArray.setFixFrozenCols(true);
					//biglistboxArray.setVisible(true);
					//biglistboxArray.setSortAscending(new MyMatrixComparatorProvider<List<String>>(true));
					//biglistboxArray.setSortDescending(new MyMatrixComparatorProvider<List<String>>(false));
					biglistboxArray.setModel(new Object2StringMatrixModel(varianttable)); //strRef));
					biglistboxArray.setRowHeight("32px");
					
					
					
					
					//gridBiglistheader.setHeight("400px");
					gridBiglistheader.setHeight("410px");
					gridBiglistheader.setWidth("200px");
					//Clients.scrollIntoView(rows.getLastChild())
					AppContext.debug( " biglistboxArray.getOddRowSclass()" +  biglistboxArray.getOddRowSclass());
					AppContext.debug( " biglistboxArray.getSclass()" +  biglistboxArray.getSclass());
					
					//gridBiglistheader.setOddRowSclass( biglistboxArray.getOddRowSclass() );
					//gridBiglistheader
					gridBiglistheader.setOddRowSclass(biglistboxArray.getSclass());
					gridBiglistheader.setSclass( biglistboxArray.getOddRowSclass() );
					
					gridBiglistheader.setRowRenderer(new BiglistRowheaderRenderer());
					
					/*
					if(listBiglistheaderData == null)  {
						listBiglistheaderData=new ArrayList();
						gridBiglistheader.setModel( new SimpleListModel(listBiglistheaderData));
					}
					else listBiglistheaderData.clear();
					listBiglistheaderData.addAll( ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList(biglistboxRows) );
					*/
					
					
					gridBiglistheader.setModel( new SimpleListModel(((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList(biglistboxRows) ));
					
					
					//gridBiglistheader.setModel( new SimpleListModel(  ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList()) ) ;
					//gridBiglistheader.setModel( new SimpleListModel(  ((VariantAlignmentTableArraysImpl)varianttable).getRowHeaderList()) ) ;
				        
					
					//msgbox.setValue(msgbox.getValue() +  varianttable.getMessage());
					msgbox.setValue(varianttable.getMessage());
					
					getSession().putValue("queryResult", queryRawResult);
					getSession().putValue("variantTable", varianttable);
					
					if(queryResult==null) throw new RuntimeException("queryResult==null");
 					if(queryResult.getMapIdx2Pos()==null) throw new RuntimeException("queryResult.getMapIdx2Pos==null");
					
					//iframeAlignment.invalidate();

					divIndelLegend.setVisible(checkboxIndel.isChecked());
					
					if(checkboxIndel==null) throw new RuntimeException("checkboxIndel==null");
					if(divAlignIndels==null) throw new RuntimeException("divAlignIndels==null");
					
					divAlignIndels.setVisible(checkboxIndel.isChecked());

					biglistboxArray.invalidate();
					gridBiglistheader.invalidate();
					gridBiglistheader.setVisible(true);
					biglistboxArray.setVisible(true);
					
					
					AppContext.debug( "gridBiglistheader.getRows()=" + gridBiglistheader.getRows().getChildren().size() + "    biglistboxArray.getRows()=" +
							biglistboxArray.getRows());
					
				} catch(Exception ex)
				{
					ex.printStackTrace();
					throw new RuntimeException(ex);
				}
				
				
				//listSNPs = queryResult.getListVariantsString();
				
				listSNPs = queryResult.getListVariantsString();
				
		        tallJbrowse = true;
		        
		        if( queryResult.getListPos()!=null) {
			        if(listSNPs.size()>0 )
			        	{
			        	 	java.util.List listPosition = new java.util.ArrayList();
			        		listPosition.add("");
			        		listPosition.addAll( new TreeSet(queryResult.getMapPos2Alleleset().keySet()) );
			        		listboxPosition.setModel( new SimpleListModel(listPosition));
			        		hboxFilterAllele.setVisible(true);
			        		hboxDownload.setVisible(true);
			        	}
		        } 
		        
			}
			else {	
				// SNPs on 2 varieties in region
				try {
					
					varianttable =  new VariantAlignmentTableArraysImpl();
					
					varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade,"VarietyFacade");
					queryRawResult = genotype.compare2Varieties( varietyfacade.getGermplasmByName(var1).getVarietyId(),
							varietyfacade.getGermplasmByName(var2).getVarietyId(),  params);
					varianttable = genotype.fillGenotypeTable(varianttable , queryRawResult, params);
					queryResult = varianttable.getVariantStringData();

					// update table header
					Listheader lhr = (Listheader)snpresult.getListhead().getFirstChild();    	
					String[] headers = new String[]{"CHROMOSOME","POSITION", "NIPPONBARE", 
							varietyfacade.getMapId2Variety().get( queryResult.getListVariantsString().get(0).getVar() ).getName(), //comboVar1.getValue().toUpperCase() ,
							varietyfacade.getMapId2Variety().get( queryResult.getListVariantsString().get(1).getVar() ).getName() //comboVar2.getValue().toUpperCase()
							};
					int i = 0;
					while(lhr != null) {
						lhr.setLabel(headers[i] );
						lhr = (Listheader)lhr.getNextSibling();
						i++;
					}
					  
					snpresult.setItemRenderer( new PairwiseListItemRenderer(queryResult, params));
					snpresult.setModel( new SimpleListModel( ((VariantAlignmentTableArraysImpl)varianttable).getCompare2VarsList( this.selectChr.getSelectedItem().getLabel() ) ));
					snpresult.setVisible(true);
					
					//queryRawResult.merge();
					listSNPs = queryRawResult.getListVariantsString();
							
					create2VarietiesGFF(listSNPs, gfffile );
					msgbox.setValue( msgbox.getValue() + " ... RESULT: " +  listSNPs.size() + " positions" );
					tallJbrowse= false;
					
					
				} catch(Exception ex) {
					throw new RuntimeException(ex);
				}
			}

			
			if(mode==GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS  && listSNPs.size()>0)
			{
				
				if(listboxMySNPList.getSelectedIndex()>0) {
					tabJbrowse.setVisible(false);
					tabPhylo.setVisible(false);
					tabMSA.setVisible(false);
				}
				else {
					updateJBrowse( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
					tabJbrowse.setVisible(true);
					
					if(checkboxIndel.isChecked() && !checkboxAlignIndels.isChecked()) 
						tabMSA.setVisible(false);	
					else tabMSA.setVisible(true);
					
					if(tallJbrowse) {
						show_phylotree(chr.toUpperCase().replace("CHR0","").replace("CHR",""), intStart.getValue().toString() , intStop.getValue().toString() , listSNPs.size() );
						
						tabPhylo.setVisible(true);
					}
				}
				hboxDownload.setVisible(true);
			}
			else {
				tabJbrowse.setVisible(false);
				tabPhylo.setVisible(false);
				tabMSA.setVisible(false);
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
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
		public GFF(String left, String right, long start, long end) {
			super();
			this.left = left;
			this.right = right;
			this.start = start;
			this.end = end;
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
	 
	 
	 //private void createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end) {
	 //private void createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
	 private List createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs,  Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
			//mismatchOnly =true;
			//boolean isphylojbrowse = filename.endsWith(".phylo.gff");

			List listSnpPos = queryRawResult.getListPos(); // genotype.getSnpsposlist() ;
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
			AppContext.debug(listSnpPos.size() + " SNP Positions");
			
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
					
					//if(isphylojbrowse && var2order.get(snpvars.getVar())==null ) continue;  
					
					Set setidxNonsyn = snpvars.getNonsynIdxset();
					
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
					
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
						String synstr = "1";
						if(setidxNonsyn!=null && setidxNonsyn.contains( iPos )) synstr = "0";
						String al2 = "";
						if(mapPosidx2allele2!=null) {
							Character allele2 = mapPosidx2allele2.get(iPos);
							if(allele2!=null) al2="/" + allele2;
						}
						
						if( showAll || (charAtPos!='0' && cRef[iPos]!= charAtPos) ) {
						//if(!mismatchOnly || (charAtPos!='0' && cRef[iPos]!= charAtPos ) ) {
						//if(true) {
							//AppContext.debug("idx=" +  iPos + "  Synonymous=" + synstr);

							
							String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
									";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  lPos[iPos] + 
									";AlleleRef=" +   cRef[iPos] + 
									";AlleleAlt=" + charAtPos + al2 +
									";Position=" +  lPos[iPos] +
									";Synonymous=" + synstr +
									";order=" + order +
									"\n";	
		
							long bounds[] = mapPos2Bounds.get( lPos[iPos] );
							listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  bounds[0],  bounds[1]));
						}
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return listGFF;
		}
	 
	 private List createSNPPointStringVarietyGFF(List<SnpsStringAllvars> listSNPs,  Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean showAll) {
			
			//mismatchOnly =true;
			//boolean isphylojbrowse = filename.endsWith(".phylo.gff");

			List listSnpPos = queryRawResult.getListPos(); // genotype.getSnpsposlist() ;
			Iterator<SnpsAllvarsPos> itPos = listSnpPos.iterator();
			
			long lPos[] = new long[listSnpPos.size()];
			char cRef[] = new char[listSnpPos.size()];
			
			int iPosCount = 0;
			long prevpos = start.longValue();
			if(itPos.hasNext()) {
				SnpsAllvarsPos pos = itPos.next();
				long curpos = pos.getPos().longValue();
				lPos[iPosCount]=curpos;
				cRef[iPosCount]=pos.getRefnuc().charAt(0);
			}
			
			
			
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
			
			java.util.List listGFF = new java.util.ArrayList();			
			
			try {
		
				Iterator<SnpsStringAllvars> itsnpstring = listSNPs.iterator();
				
				Map<Integer,Integer> mapMergedIdx2SnpIdx = queryRawResult.getSnpstringdata().getMapMergedIdx2SnpIdx();
				
				while(itsnpstring.hasNext()) {
					SnpsStringAllvars snpvars = itsnpstring.next();
					
					//if(isphylojbrowse && var2order.get(snpvars.getVar())==null ) continue;  
					
					Set setidxNonsyn = snpvars.getNonsynIdxset();
					
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
					
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						Integer snpIdx = mapMergedIdx2SnpIdx.get(iPos);
						if(snpIdx==null) continue;
						
						char charAtPos = snpvars.getVarnuc().substring(snpIdx,snpIdx+1).charAt(0);
						String synstr = "";
						if(setidxNonsyn!=null) {
							
							if(setidxNonsyn.contains( snpIdx )) synstr = ";Synonymous=0";
							else synstr= ";Synonymous=1";
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
							
							String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
									";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  lPos[iPos] + 
									";AlleleRef=" +   cRef[iPos] + 
									";AlleleAlt=" + charAtPos + al2 +
									";Position=" +  lPos[iPos] +
									 synstr +
									";order=" + order +
									"\n";	
		
							listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  lPos[iPos],  lPos[iPos]));
						}
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return listGFF;
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
								";Position=" +  pos +
								";order=" + order +
								"\n";	
	
						listGFF.add(new GFF( chr + "\tIRIC\t" + type + "\t", line_right,  pos.longValue(),  pos.longValue() + maxlen -1 ));						
						
					}
					
					if(snpvars.getVarnuc()!=null) {
						Map<Integer,Integer> mapIdx2Snpidx = queryRawResult.getSnpstringdata().getMapMergedIdx2SnpIdx();
						List<SnpsAllvarsPos> listSnpPos = queryRawResult.getListPos();
						
						//Iterator<SnpsAllvarsPos> itMergedPos = listSnpPos.iterator();
						
						Map<BigDecimal,String> mapIndelpos2Refnuc = null;
						if(queryRawResult.hasIndel())
							mapIndelpos2Refnuc = queryRawResult.getIndelstringdata().getMapIndelpos2Refnuc();
						
			
						for(int iMergedPos = 0; iMergedPos<listSnpPos.size(); iMergedPos ++ ) {
							
							if(! mapIdx2Snpidx.containsKey(iMergedPos)) continue;
							
							queryRawResult.getIndelstringdata().getMapIndelpos2Refnuc();
							
							int iPos = mapIdx2Snpidx.get(iMergedPos);
							SnpsAllvarsPos pos = listSnpPos.get(iMergedPos);
							
							String  strRef=null;
							
							if(mapIndelpos2Refnuc!=null)
								strRef = mapIndelpos2Refnuc.get(pos.getPos());
							if(strRef==null) strRef=pos.getRefnuc();
							
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
										";order=" + order +
										"\n";	
			
								listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  pos.getPos().longValue() , pos.getPos().longValue()));
							}
						}
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
			BufferedWriter bw = new BufferedWriter(fw);
				
			int linecount = 0;
			
			Iterator<GFF> itGFF = listGFF.iterator();
			StringBuffer buff = new StringBuffer();
			buff.append("##gff-version 3\n#Note: See http://song.sourceforge.net\n\n");
			
			while(itGFF.hasNext()) {
				GFF gff = itGFF.next();
				
				buff.append(gff.toString());
				
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
									 ";Position=" +  snpvars.getPos() +
									"\n");
						}
		
						if(!mismatchOnly || !snpvars.getRefnuc().equals( snpvars.getVar2nuc()  ) ) {
							buff.append(chr); buff.append("\tIRIC\tsnp\t");
							buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
							buff.append( "\t.\t.\t.\tName=" +  mapId2Var.get( snpvars.getVar2() ).getName()
									+ ";ID=" +  snpvars.getVar2() + "-" + snpvars.getPos() + 
									";AlleleRef=" +   snpvars.getRefnuc() +
									";AlleleAlt=" + snpvars.getVar2nuc() +
									";Position=" +  snpvars.getPos() +
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
	
	
	
	private void create2VarietiesGFF(List<SnpsStringAllvars> listSNPs, String filename) {
		create2VarietiesGFF(listSNPs, filename, false);
	}
	
	/**
	 * Create SNP GFF for 2-varieties query
	 * @param listSNPs
	 * @param filename
	 * @param mismatchOnly
	 */
	private void create2VarietiesGFF(List<SnpsStringAllvars> listSNPs, String filename, boolean mismatchOnly) {
		
		//if(!checkShowsnp.isChecked()) return; 
		
		StringBuffer buff = new StringBuffer();
		
	
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapId2Var=varietyfacade.getMapId2Variety();
		
		try {
			File file = new File( AppContext.getTempDir() + filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			int linecount = 0;
			
			SnpsStringAllvars var1variants = listSNPs.get(0);
			SnpsStringAllvars var2variants = listSNPs.get(1);
			
			if(var1variants instanceof IndelsStringAllvars) {
				IndelsStringAllvars var1indels  = (IndelsStringAllvars)var1variants;
				//var1indels.get
				var1indels.getMapPos2Indels().values();
			}
			
			Iterator<Snps2Vars> itsnp = null ; //listSNPs.iterator();
			while(itsnp.hasNext()) {
					Snps2Vars snpvars = itsnp.next();
				
					
					String chr = snpvars.getChr().toString(); 
					if(chr.length()==1)
						chr= "chr0" + chr + "|msu7";
					else 
						chr= "chr" + chr + "|msu7";

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
									 ";Position=" +  snpvars.getPos() +
									"\n");
						}
		
						if(!mismatchOnly || !snpvars.getRefnuc().equals( snpvars.getVar2nuc()  ) ) {
							buff.append(chr); buff.append("\tIRIC\tsnp\t");
							buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
							buff.append( "\t.\t.\t.\tName=" +  mapId2Var.get( snpvars.getVar2() ).getName()
									+ ";ID=" +  snpvars.getVar2() + "-" + snpvars.getPos() + 
									";AlleleRef=" +   snpvars.getRefnuc() +
									";AlleleAlt=" + snpvars.getVar2nuc() +
									";Position=" +  snpvars.getPos() +
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

		String urltemplate = "..%2F..%2F" + AppContext.getHostDirectory() + "%2Ftmp%2F" + gfffile;
		
		//if(checkShowsnp.isChecked() ) {

		if(true) {
		
			if(tallJbrowse) {
				// for all varieties
				
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
				
				String snp3kcore="";
				if(checkboxSNP.isChecked()) { snp3kcore = "snp3k%2C";
					if(checkboxCoreSNP.isChecked()) snp3kcore="snp3k%2Csnp3kcore%2C";
				}
				if(checkboxIndel.isChecked())
					snp3kcore += "indel3kinterval%2C";
				
				//urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?loc=chr"  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2Csnp3k%2C" + snp3kcore + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
				urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?loc=chr"  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2C" + snp3kcore + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
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
				
				// for 2 varieties
				urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?loc=chr" + chrpad + "|msu7:" + start + ".." + end + 
					"&tracks=DNA,msu7gff,snp3k," + snp3kcore + "SNP%20Genotyping&addStores={%22url%22:{%22type%22:%22JBrowse/Store/SeqFeature/GFF3%22,%22urlTemplate%22:%22" + urltemplate
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

				
				urljbrowsephylo= AppContext.getHostname() + "/jbrowse-dev/?loc=chr"  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2Csnp3k%2CSNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
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
			
			
			urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=" + gfffile.replace(".gff","") + "&mindist=" + intPhyloMindist.getValue();
			log.debug(urlphylo);
			AppContext.debug(urlphylo);
	}
	
	
	
	/**
	 * for bigmatrix viewer
	 */
	
	// images marks
		private String[] images = { "aim", "amazon", "android", "apple", "bebo",
				"bing", "blogger", "delicious", "digg", "facebook", "flickr",
				"friendfeed", "google", "linkedin", "netvibes", "newsvine",
				"reggit", "rss", "sharethis", "stumbleupon", "technorati",
				"twitter", "utorrent", "vimeo", "vkontakte", "wikipedia", "windows",
				"yahoo" };

		
	

			
		public void showSNPBiglist(List listSnpstring, List  listSnpsPos) throws Exception {
			
			
			myComp.setModel(new FakerMatrixModel(listSnpstring, listSnpsPos)); //strRef));
			myComp.setColWidth("60px");
			SNPMatrixRenderer renderer  = new SNPMatrixRenderer();
			//renderer.setColorMode(  );
			myComp.setMatrixRenderer(renderer);
			
			/*
			// specify a trillion faker model
			myComp.setModel(new org.irri.iric.portal.zk.FakerMatrixModel(1000 * 1000, 1000 * 1000));
			//myComp.setModel(new MatrixModel(100, 1000 ) );
			myComp.setColWidth("130px");
			myComp.setMatrixRenderer(new MatrixRenderer<List<String>>() {

				@Override
				public String renderCell(Component owner, List<String> data,
						int rowIndex, int colIndex) throws Exception {
					String d = data.get(colIndex);
					d = d.replace("ZK", "<span class='red' title='ZK'>ZK</span>")
							.replace("Hello", "<span class='blue' title='Hello'>Hello</span>");
					return "<div class='images_" + (colIndex%28) + "' title='x=" + 
					colIndex + ",y=" + rowIndex + "'>" + d + "</div>";
				}

				@Override
				public String renderHeader(Component owner, List<String> data,
						int rowIndex, int colIndex) throws Exception {
					return "<div class='images_" + (colIndex % 28) + "' title='"
							+ images[colIndex % 28] + "'>" + data.get(colIndex)
							+ "</div>";
				}
			});
			myComp.setSortAscending(new MyMatrixComparatorProvider<List<String>>(true));
			myComp.setSortDescending(new MyMatrixComparatorProvider<List<String>>(false));
			*/
		}

		@Listen("onClick=#myComp; onSort=#myComp")
		public void onClickBiglistbox() {
			tip.setVisible(true); // reset first, if the tip is shown at client only.
			tip.setVisible(false);
		}

		@SuppressWarnings("unchecked")
		@Listen("onCellClick=#myComp")
		public void onCellClickBiglistbox(MouseEvent evt) {
			Integer[] axis = (Integer[]) evt.getData();
			
			// shift some pixels to make it look better
			tip.setStyle("left: " + (evt.getPageX() - 30) + "px; top:"
					+ (evt.getPageY() + 20) + "px");
			tip.setVisible(true);
			FakerMatrixModel fmm = (FakerMatrixModel) myComp.getModel();
			content.setValue(String.valueOf(fmm.getCellAt(
					fmm.getElementAt(axis[1]), axis[0])));
			content.setAttribute("axis", axis); // store the change for update
			Clients.evalJavaScript("doPosition()"); // resync the tooltip position
		}

		@Listen("onClick=#update; onOK=#content")
		public void onClickBiglistbox$update() {
			Integer[] axis = (Integer[]) content.getAttribute("axis");
			FakerMatrixModel fmm = (FakerMatrixModel) myComp.getModel();
			fmm.update(axis, content.getValue());
			tip.setVisible(false);
			myComp.focus(); // pass focus to the big listbox
		}

		@Listen("onSelect=#myComp")
		public void onSelectBiglistbox(SelectEvent evt) {
			System.out.println("You listen onSelect: "
					+ Arrays.asList(((Integer[]) evt.getData())));
		}
		
		// Matrix comparator provider 
		private class MyMatrixComparatorProvider<T> implements
				MatrixComparatorProvider<List<String>> {
			private int _x = -1;

			private boolean _acs;

			private MyComparator _cmpr;

			public MyMatrixComparatorProvider(boolean asc) {
				_acs = asc;
				_cmpr = new MyComparator(this);
			}

			@Override
			public Comparator<List<String>> getColumnComparator(int columnIndex) {
				this._x = columnIndex;
				return _cmpr;

			}

			// a real String comparator
			private class MyComparator implements Comparator<List<String>> {
				private MyMatrixComparatorProvider _mmc;

				public MyComparator(MyMatrixComparatorProvider mmc) {
					_mmc = mmc;
				}

				@Override
				public int compare(List<String> o1, List<String> o2) {
					return o1.get(_mmc._x).compareTo(o2.get(_mmc._x))
							* (_acs ? 1 : -1);
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
				
			}
			
		   private void addListcell (Row listitem, Object value) {
		        Label lb = new Label(value.toString());
		        lb.setParent(listitem);
		    }

			
		}

		
		
}