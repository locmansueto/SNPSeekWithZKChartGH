package org.irri.iric.portal.genotype.zkui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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
    
    @Wire
    private Checkbox checkboxSpliceSNP;
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

@Listen("onCheck = #showsnps") 
public void oncheckShowsnps() {
	if(this.queryRawResult==null) return;
	
	/*
	if(!this.biglistboxArray.isVisible()) {
		Messagebox.show("Dynamic change not yet implemented for pairwise comparison. Click SEARCH again for this view.", "NOT IMPLEMENTED", Messagebox.OK, Messagebox.INFORMATION);
		return;
	}
	*/
	if(this.snpresult.isVisible()) {
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
	snpresult.setItemRenderer( new PairwiseListItemRenderer(queryResult, params));
	snpresult.setModel( new SimpleListModel( ((VariantAlignmentTableArraysImpl)varianttable).getCompare2VarsList( this.selectChr.getSelectedItem().getLabel(), params ) ));
	snpresult.setVisible(true);
	
	//queryRawResult.merge();
	//listSNPs = queryRawResult.getListVariantsString();
	//create2VarietiesGFF(listSNPs, gfffile, queryResult.getListPos(), queryResult.getRefnuc() );
	
	//msgbox.setValue( msgbox.getValue() + " ... RESULT: " +  snpresult.getModel().getSize() + " positions" );
	String msgs[]= msgbox.getValue().split("...");
	if(msgs.length>0)
		msgbox.setValue(msgs[0] + " ... RESULT: " +  snpresult.getModel().getSize() + " positions" );
	else msgbox.setValue(msgbox.getValue() + " ... RESULT: " +  snpresult.getModel().getSize() + " positions" );
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
			//this.selectChr.setSelectedIndex( Integer.parseInt( listboxMySNPList.getSelectedItem().getLabel().split(":")[0].replace("CHR ","") )-1 );
			this.selectChr.setSelectedIndex(0);
			this.comboGene.setValue("");
			this.intStart.setValue(null);
			this.intStop.setValue(null);
			listboxMySNPList.setSelectedIndex(0);
		}

	}
}


@Listen("onSelect = #listboxMySNPList")    
public void  onselectMySNPList() {
	if(listboxMySNPList.getSelectedIndex()>0) {

		if( listboxMySNPList.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=snp&src=snp");
		}
		else {
			this.selectChr.setSelectedIndex( Integer.parseInt( listboxMySNPList.getSelectedItem().getLabel().split(":")[0].replace("CHR ","") )-1 );
			this.comboGene.setValue("");
			this.intStart.setValue(null);
			this.intStop.setValue(null);
			listboxMyLocusList.setSelectedIndex(0);
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
		
		biglistboxArray.setModel( new Object2StringMatrixModel(newtable));
		labelFilterResult.setValue(listInclude.size() + "/" + queryRawResult.getListVariantsString().size() + " varieties");
		
		//gridBiglistheader.setModel( new SimpleListModel(  newtable.getRowHeaderList() )) ;
		gridBiglistheader.setModel(new SimpleListModel( ((VariantAlignmentTableArraysImpl)newtable).getRowHeaderList( biglistboxRows) ));

		gridBiglistheader.invalidate();
		//biglistboxArray.invalidate();
		
	}
	
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
	
	if( selectChr.getSelectedItem().getLabel().trim().isEmpty()) return;
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
					listGFF.addAll( createSNPPointStringVarietyGFF(listGFFSNP,  queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) );
					listGFF.addAll( createIndelStringVarietyGFF(listGFFIndel,   queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected() || !checkboxMismatchOnly.isChecked() ) );
				}
				else if(this.checkboxSNP.isChecked())
				{
					listGFF = createSNPStringVarietyGFF(queryDisplayData.getListVariantsString(),   queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  ) ;
				}
				else if(this.checkboxIndel.isChecked())
				{
					listGFF = createIndelStringVarietyGFF(queryDisplayData.getListVariantsString(),  queryDisplayData.getMapVariety2Order() , 1, BigDecimal.valueOf(start), BigDecimal.valueOf(intStop.getValue()+jbrowsebp_margin),  radioColorAllele.isSelected()  );
				}
				
				writeGFF(listGFF, gfffile);
				
				AppContext.resetTimer("write gff");
				iframeJbrowse.setSrc( urljbrowse );
				
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
//@Listen("onSelect = #tabJBrowsePhylo")    
//public void onselectTabJbrowsePhylo() {
//
//	//tabboxDisplay.setHeight( "100%");
//	AppContext.debug("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
//	Clients.showBusy("Calculating JBrowsePhylo");
//	if(urljbrowsephylo==null) {	
//			
//			if(tallJbrowse) {
//				updateJBrowsePhylo( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
//				Map mapVariety2Order = genotype.orderVarietiesFromPhylotree(gfffile.replace("gff",""));
//				List newpagelist;
//				
//				int jbrowsebp_margin =     (intStop.getValue()-intStart.getValue())*AppContext.getJBrowseMargin();
//				newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue()-jbrowsebp_margin, intStop.getValue()+jbrowsebp_margin, Integer.valueOf(selectChr.getSelectedItem().getLabel())); //,  true , -1,  -1 );
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

private void downloadBigListbox(String filename, String delimiter) {
	Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
	
	
	StringBuffer buff = new StringBuffer();
	buff.append("VARIETY").append(delimiter).append("IRIS ID").append(delimiter).append("MISMATCH").append(delimiter);
	BigDecimal[] positions = table.getPosition(); 
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
		buff.append(table.getVarname()[i]).append(delimiter).append(  mapVarId2Var.get(BigDecimal.valueOf(table.getVarid()[i])).getIrisId()  ).append(delimiter).append(table.getVarmismatch()[i]).append(delimiter);
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
	BigDecimal[] positions = table.getPosition();
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
		AppContext.debug("map: "+ buff.toString() );
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
		
		AppContext.debug("ped: "+ buff.toString() );
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
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
		      
		      
	String chr= this.selectChr.getSelectedItem().getLabel();
	
	
	Object2StringMatrixModel matrixmodel = (Object2StringMatrixModel)biglistboxArray.getModel();
	VariantAlignmentTableArraysImpl  table = (VariantAlignmentTableArraysImpl)matrixmodel.getData();
	
	
	
	StringBuffer buff = new StringBuffer();
	//buff.append("POSITION").append(delimiter).append("MISMATCH").append(delimiter);
	BigDecimal[] positions = table.getPosition();
	String refs[] =  table.getReference();
	for(int i=0; i<positions.length; i++) {
		if(!refs[i].equals("-")) {
			int pos = positions[i].intValue();
			//buff.append(chr).append("\t").append("snp" + chr + "-" + pos) .append("\t0\t").append(pos+1).append("\n");
			buff.append(chr).append("\t").append( "1" +  String.format("%02d", Integer.valueOf(chr)) +  String.format("%08d", pos) ) .append("\t0\t").append(pos).append("\n");
		}
	}
	try {
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
		
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	
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
	
	try {
		
		FileWriter writer = new FileWriter( filename + ".ped");
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
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
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
	
	//Clients.showBusy("Fetching from Database");
	try {
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


@Listen("onClick = #checkboxSNP")
public void onclickSNP() {
	this.checkboxSpliceSNP.setDisabled( !this.checkboxSNP.isChecked() );
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
	biglistboxArray.setModel(new Object2StringMatrixModel(varianttable)); //strRef));
	
	
	biglistboxArray.setWidth("100%");
	gridBiglistheader.invalidate();
	
	this.tabTable.setSelected(true);

	// create new gff for 
	urljbrowse = null;
	gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";	
	updateJBrowse( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), this.comboGene.getValue());
	
	
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
 *	1. newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel().trim(), desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize  );
    2. updateAllvarsList(newpagelist,false,null,null,null, desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize );		
    
    
    
 */

private GenotypeQueryParams fillGenotypeQueryParams() {

	Set setVarieties = null;
	String sSubpopulation = null;
	
	genotype = (GenotypeFacade)AppContext.checkBean(genotype,"GenotypeFacade");
	workspace = (WorkspaceFacade)AppContext.checkBean(workspace,"WorkspaceFacade");
	
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
	
	
	GenotypeQueryParams params = new GenotypeQueryParams(setVarieties, selectChr.getSelectedItem().getLabel(), new Long( intStart.getValue()),
			new Long( intStop.intValue() ) ,  checkboxSNP.isChecked() , checkboxIndel.isChecked(), checkboxCoreSNP.isChecked() ,
			checkboxMismatchOnly.isChecked(), snpposlist,  sSubpopulation, sLocus, checkboxAlignIndels.isChecked());
	//params.setColors(this.radioGraySynonymous.isSelected(), this.radioNonsynOnly.isSelected() , true  , this.radioColorMismatch.isSelected(), this.radioColorAllele.isSelected());
	
	params.setColors(this.radioColorMismatch.isSelected(), this.radioColorAllele.isSelected());
	params.setIncludedSnps(this.radioAllSnps.isSelected(), this.radioNonsynHighlights.isSelected(), this.radioNonsynSnps.isSelected(), this.radioNonsynSnpsPlusSplice.isSelected());
	if(this.comboVar1.getValue()!=null && !this.comboVar1.getValue().isEmpty() && this.comboVar2.getValue()!=null && !this.comboVar2.getValue().isEmpty()) {
		params.setPairwiseComparison(comboVar1.getValue(), comboVar2.getValue());
	}
		
	
	
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
		gridBiglistheader.setVisible(false);
		biglistboxArray.setVisible(false);

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
				
				if(selectChr.getSelectedItem().getLabel().trim().isEmpty()) {
					Messagebox.show("Please select chromosome/contig", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					return;
				}
				
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
					msgbox.setValue(msgbox.getValue() +  varianttable.getMessage());
					
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
					
					//AppContext.debug( "gridBiglistheader.getRows()=" + gridBiglistheader.getRows().getChildren().size() + "    biglistboxArray.getRows()=" + biglistboxArray.getRows());
					
					AppContext.logSystemStatus();
					
					
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
					
					//params.setbMismatchonly(false);
					params.setPairwiseComparison( var1, var2);
					
					varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade,"VarietyFacade");
					queryRawResult = genotype.compare2Varieties( varietyfacade.getGermplasmByName(var1).getVarietyId(),
							varietyfacade.getGermplasmByName(var2).getVarietyId(),  params);
					varianttable = genotype.fillGenotypeTable(varianttable , queryRawResult, params);
					queryResult = varianttable.getVariantStringData();

					// update table header
					Listheader lhr = (Listheader)snpresult.getListhead().getFirstChild();    	
					
					String var1header = this.comboVar1.getValue();
					String var2header = this.comboVar2.getValue();
					
					if(queryResult.getListVariantsString().size()==2) {
						var1header=varietyfacade.getMapId2Variety().get( queryResult.getListVariantsString().get(0).getVar() ).getName();
						var2header=varietyfacade.getMapId2Variety().get( queryResult.getListVariantsString().get(1).getVar() ).getName();
					}
					
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
					  
					snpresult.setItemRenderer( new PairwiseListItemRenderer(queryResult, params));
					snpresult.setModel( new SimpleListModel( ((VariantAlignmentTableArraysImpl)varianttable).getCompare2VarsList( this.selectChr.getSelectedItem().getLabel(), params ) ));
					snpresult.setVisible(true);
					
					//queryRawResult.merge();
					listSNPs = queryRawResult.getListVariantsString();
							
					create2VarietiesGFF(listSNPs, gfffile, queryResult.getListPos(), queryResult.getRefnuc() );
					msgbox.setValue( msgbox.getValue() + " ... RESULT: " +  snpresult.getModel().getSize() + " positions" );
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
							listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  bounds[0],  bounds[1]));
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
	
						listGFF.add(new GFF( chr + "\tIRIC\t" + type + "\t", line_right,  pos.longValue(),  pos.longValue() + maxlen -1 ));						
						
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
								
								listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  pos.getPos().longValue() , pos.getPos().longValue()));
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
						} 
						*/
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