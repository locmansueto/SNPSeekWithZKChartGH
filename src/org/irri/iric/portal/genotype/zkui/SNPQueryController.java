package org.irri.iric.portal.genotype.zkui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
 















import javax.servlet.http.HttpSession;

import oracle.sql.DATE;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.Variety;
//import org.irri.iric.portal.genotype.domain.Gene;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
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
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.ext.Selectable;
 

@Controller
@Scope("session")
public class SNPQueryController extends SelectorComposer<Component>  { //implements Initiator {
 
	private String urljbrowse,gfffile,urlphylo,urljbrowsephylo;
	//private String phylochr, phylostart, phyloend;
	private boolean tallJbrowse;
	
	
	private List<SnpsStringAllvars> origAllVarsListModel;
	
	private int snpallvars_pagesize=50;
	private String tmpfile_spath="";
	private Listheader lhr_variety;
	private Listheader lhrMismatch;
	//private String tempdir =  "../webapps/iric-portal/tmp/";

	
	private static final Log log = LogFactory.getLog(SNPQueryController.class);
	
	private static final  String TEXTSTYLE_ERROR="font-color:red";
	private static final  String TEXTSTYLE_SUCCESS="font-folor:black";
	
    private static final long serialVersionUID = 1L;
    private int chrlength = 43270923; 
    
    //final private String classGenotypefacade = "GenotypeFacadeLegacy";
    final private String classGenotypefacade = "GenotypeFacade";
    
    @Wire
    private Label labelScreenWidth;
    
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
    
    
    //@Wire
    //private Listbox selectQueryMode;
    
    
    @Wire
    private Combobox comboVar1;
    @Wire
    private Combobox comboVar2;
    
    @Wire
    private Checkbox checkboxMismatchOnly;
    
    
    @Wire
    private Checkbox checkboxAllvarieties;
    @Wire
    private Combobox comboSubpopulation;
    @Wire
    private Listbox listboxMyVarieties;
        
    
    @Autowired
    @Qualifier("GenotypeFacade")
    //@Qualifier(classGenotypefacade)
    private GenotypeFacade  genotype;  //=  // new org.irri.iric.portal.genotype.service.GenotypeFacadeImpl();
    
    @Autowired
    @Qualifier("VarietyFacade")
    private VarietyFacade  varietyfacade;  //=  // new org.irri.iric.portal.genotype.service.GenotypeFacadeImpl();
    
    @Autowired
    @Qualifier("WorkspaceFacade")
    private WorkspaceFacade workspace;
    
    @Wire
    private Iframe iframeJbrowse;
    @Wire
    private Label msgJbrowse;
    
    @Wire
    private Iframe iframeJbrowsePhylo;
    @Wire
    private Label msgJbrowsePhylo;
    
   // @Wire
   // private Listbox snpallvarsresult;
    
    @Wire
    private Grid snpallvarsresult;
    
    //@Wire
    //private Label snpallvarsresultMsg;
    
    @Wire
    private Paging snpallvarsresultpaging;
    
    @Wire
    private Button buttonDownloadCsv;
    @Wire
    private Button buttonDownloadTab;
   
    
    @Wire
    Auxhead auxheadpos;
    
    
    //@Wire
    //private Checkbox checkShowsnp;
    
    @Wire
    private Radio radioColorAllele;
    @Wire
    private Radio radioColorMismatch;
    
    @Wire
    private Window win;
    
    @Wire
    private Iframe iframePhylotree;
    
    @Wire
    private Tab tabJbrowse;
    @Wire
    private Tab tabPhylo;
    
    @Wire
    private Listbox selectPhyloTopN;
    
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
    private Button buttonFilterAllele;
    
    @Wire
    private Hbox hboxFilterAllele;
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
	
	
    /*
    @Wire
    private Combobox comboVar1;
    @Wire
    private Combobox comboVar2;
    
    @Wire
    private Checkbox checkboxAllvarieties;
    @Wire
    private Combobox comboSubpopulation;
    @Wire
    private Listbox listboxMyVarieties;
    */
    
    
    
    


public SNPQueryController() {
	super();
	// TODO Auto-generated constructor stub
	
	System.out.println("created SNPQueryController " + this);
}


@Listen("onClick = #buttonCreateVarlist")
public void onclickCreateVarlist() {
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


@Listen("onSelect = #listboxMyVarieties")    
public void  onselectMyVarieties() {
	if(listboxMyVarieties.getSelectedIndex()>0) {
		comboSubpopulation.setValue("");
		checkboxAllvarieties.setChecked(false);
		comboVar1.setValue("");
		comboVar2.setValue("");
	}
}


@Listen("onSelect = #listboxMySNPList")    
public void  onselectMySNPList() {
	if(listboxMySNPList.getSelectedIndex()>0) {
		this.selectChr.setSelectedIndex( Integer.parseInt( listboxMySNPList.getSelectedItem().getLabel().split(":")[0].replace("CHR ","") )-1 );
		this.comboGene.setValue("");
		this.intStart.setValue(null);
		this.intStop.setValue(null);
	}
}


@Listen("onClick = #checkboxAllvarieties")
public void  oncheckAllVarieties() {
	if(checkboxAllvarieties.isChecked()) {
		comboSubpopulation.setValue("");
		listboxMyVarieties.setSelectedIndex(0);
		comboVar1.setValue("");
		comboVar2.setValue("");
	}
}

@Listen("onClick = #buttonFilterAllele")
public void onclickFilterAllele() {

	

	Map<BigDecimal,Integer> mapPosition2Index = new HashMap();
	Iterator itCol = snpallvarsresult.getColumns().getChildren().iterator() ;
	itCol.next();itCol.next();
	int poscolumn=0;
	while(itCol.hasNext()) {
		Column col = (Column)itCol.next();
		poscolumn++;
		mapPosition2Index.put( BigDecimal.valueOf(  Long.valueOf(col.getLabel()) ) , poscolumn);
	}

	
	Set setHighlightIndex = new HashSet();
	java.util.Map<Integer,Character> mapIndex2Allele = new HashMap();
	Iterator<Component> itfilters = vboxFilterAllele.getChildren().iterator();
	while(itfilters.hasNext()) {
		Hbox hboxfilter = (Hbox)itfilters.next();
		List hboxchildren = hboxfilter.getChildren();
		
		Listitem itempos= ((Listbox)hboxchildren.get(1)).getSelectedItem();
		Listitem itemallele = ((Listbox)hboxchildren.get(4)).getSelectedItem();
		if(itempos==null || itemallele==null) continue;
		
		String position = itempos.getLabel();
		String allele =  itemallele.getLabel();
		Integer posindex = mapPosition2Index.get(BigDecimal.valueOf(Long.valueOf(position)));
		if(!position.isEmpty() && !allele.isEmpty()) { 
			mapIndex2Allele.put(posindex ,allele.charAt(0));
		}
		setHighlightIndex.add(posindex);
	}

	List filteredList = new ArrayList();
	
	Iterator<SnpsStringAllvars> itVars = origAllVarsListModel.iterator();
	while(itVars.hasNext()) {
		
		SnpsStringAllvars snpvars = itVars.next();
		
		Iterator<Integer> itIndex =  mapIndex2Allele.keySet().iterator();
		boolean include = true;
		while(itIndex.hasNext()) {
			int idx = itIndex.next();
			if(snpvars.getVarnuc().charAt( idx-1) !=  mapIndex2Allele.get(idx) ) {
				include = false;
				break;
			}
		}
		if(include) filteredList.add(snpvars); 
	}

	if(setHighlightIndex.isEmpty())
		((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setSetHighlighColumns(null);
	else
		((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setSetHighlighColumns(setHighlightIndex);

	
	snpallvarsresult.setModel( new SimpleListModel(filteredList));
	
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



@Listen("onSelect = #comboSubpopulation")    
public void  onselectSubpopulation() {
	if(comboSubpopulation.getSelectedIndex()>0) {
		checkboxAllvarieties.setChecked(false); 
		listboxMyVarieties.setSelectedIndex(0);
		comboVar1.setValue("");
		comboVar2.setValue("");
	}
}

@Listen("onChange = #comboVar1") 
public void  onchangedVar1() {
	if(!comboVar1.getValue().isEmpty() || !comboVar2.getValue().isEmpty()) {
		checkboxAllvarieties.setChecked(false); 
		listboxMyVarieties.setSelectedIndex(0);
		comboSubpopulation.setValue("");
	}
}
@Listen("onChange = #comboVar2") 
public void  onchangedVar2() {
	if(!comboVar1.getValue().isEmpty() || !comboVar2.getValue().isEmpty()) {
		checkboxAllvarieties.setChecked(false); 
		listboxMyVarieties.setSelectedIndex(0);
		comboSubpopulation.setValue("");
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
	
	//genotype = (GenotypeFacade)checkBean(genotype, "GenotypeFacade");
  
    	String name = "GenotypeFacade";
	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);

	
	Integer length = getchrLength();
	labelLength.setValue("End: (length " + length + " bp)" );
	
	listboxMySNPList.setSelectedIndex(0);
	comboGene.setValue("");
	
	chrlength=length.intValue();
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

/**
 * JBrowse tab selected    
 */
@Listen("onSelect = #tabJbrowse")    
public void onselectTabJbrowse() {

	if(urljbrowse!=null  ) {

		System.out.println("loading " + urljbrowse);
		
		Clients.showBusy("Loading JBrowse");
		if(tallJbrowse) {
			
			List newpagelist;
			/*
			if(!comboGene.getValue().isEmpty())
			{
				newpagelist = 	genotype.getSNPinAllVarieties(comboGene.getValue(), 0);
				Gene gene =genotype.getGeneFromName(comboGene.getValue() );
				createSNPVarietyGFF(newpagelist, gfffile,  genotype.getMapVariety2Order() , 1, BigDecimal.valueOf(gene.getFmin()), BigDecimal.valueOf(gene.getFmax()) );
			
			}
			else
			{
				newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel() , 1, 0  );
				createSNPVarietyGFF(newpagelist, gfffile,  genotype.getMapVariety2Order() , 1, BigDecimal.valueOf(intStart.getValue()), BigDecimal.valueOf(intStop.getValue()) );
			}
			*/
			
			/*
			newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel() , 1, 0  );
			createSNPVarietyGFF(newpagelist, gfffile,  genotype.getMapVariety2Order() , 1, BigDecimal.valueOf(intStart.getValue()), BigDecimal.valueOf(intStop.getValue()) );
			*/

			
			//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(selectChr.getSelectedItem().getLabel()),  checkboxExactMismatch.isChecked(), -1,  -1 );
			newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(selectChr.getSelectedItem().getLabel()),  true , -1,  -1 );
			createSNPStringVarietyGFF(newpagelist, gfffile,  genotype.getMapVariety2Order() , 1, BigDecimal.valueOf(intStart.getValue()), BigDecimal.valueOf(intStop.getValue()),  radioColorMismatch.isSelected()  );
			
			
			AppContext.resetTimer("write gff");
			iframeJbrowse.setSrc( urljbrowse );
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

	
	System.out.println("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
	
	
	if(urljbrowsephylo==null) {	
			
			if(tallJbrowse) {
/*				
				if(!comboGene.getValue().isEmpty()) {
					String genename2 = comboGene.getValue().trim();
					Gene gene2 =  genotype.getGeneFromName( genename2);
					updateJBrowsePhylo( gene2.getChr().toString(),
						gene2.getFmin().toString(), gene2.getFmax().toString() , genename2.toUpperCase());
				} else
					updateJBrowsePhylo( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
*/			
				updateJBrowsePhylo( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");

				Clients.showBusy("Calculating JBrowsePhylo");
				
				Map mapVariety2Order = genotype.orderVarietiesFromPhylotree(gfffile.replace("gff",""));
				List newpagelist;
				
				/*
				if(!comboGene.getValue().isEmpty())
				{
					Gene gene =genotype.getGeneFromName(comboGene.getValue() );
					newpagelist = genotype.getSNPinAllVarieties(gene.getFmin(), gene.getFmax(), gene.getChr().toString() , 1, 0 , false );
					//newpagelist = 	genotype.getSNPinAllVarieties(comboGene.getValue(), 0);
					createSNPVarietyGFF(newpagelist, gfffile+".phylo.gff",  mapVariety2Order , 1, BigDecimal.valueOf(gene.getFmin()), BigDecimal.valueOf(gene.getFmax()) );
				
				}
				else
				{
					newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel() , 1, 0 , false );
					createSNPVarietyGFF(newpagelist, gfffile+".phylo.gff",  mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()), BigDecimal.valueOf(intStop.getValue()) );
				}
				*/
//				newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel() , 1, 0 , true );
//				System.out.println(mapVariety2Order.size() + " varieties for ordering;  "  + newpagelist.size() + " snps for phylojbrowse gff");
//				createSNPVarietyGFF(newpagelist, gfffile+".phylo.gff",  mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()), BigDecimal.valueOf(intStop.getValue()) );

				
				newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(selectChr.getSelectedItem().getLabel()),  true , -1,  -1 );
				System.out.println(mapVariety2Order.size() + " varieties for ordering;  "  + newpagelist.size() + " snps for phylojbrowse gff");
				createSNPStringVarietyGFF(newpagelist, gfffile + ".phylo.gff" , mapVariety2Order , 1, BigDecimal.valueOf(intStart.getValue()), BigDecimal.valueOf(intStop.getValue()),  false );
				
				
				
				Clients.clearBusy();
				
				AppContext.resetTimer("writing  " + gfffile+".phylo.gff");
			
			
			}
		}
	
	
	if(urljbrowsephylo!=null) {
		
		System.out.println("tabJbrowsePhylo selected, urljbrowsephylo=" + urljbrowsephylo);
		
		while(true) {
			File f = new File( AppContext.getTempDir() + "/" + gfffile.replace(".gff", ".newick") );
			if(f.exists()) break;
			try {
				Thread.sleep(5000);
				System.out.println("waiting newick file");
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		Clients.showBusy("Loading JBrowsePhylo");
		iframeJbrowsePhylo.setSrc( urljbrowsephylo );
	    msgJbrowsePhylo.setVisible(true);
	    iframeJbrowsePhylo.setVisible(true);
	    Clients.clearBusy();
	}
	
	urljbrowsephylo=null;
		
}

/**
 * Phylogenetic tree tab selected 
 */

@Listen("onSelect = #tabPhylo")    
public void onselectTabPhylo() {

	System.out.println("loading phylotree " + urlphylo);
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
 * Next page selected
 * @param pe
 */
    
@Listen("onPaging = #snpallvarsresultpaging")    
public void onpageAllvarsresult(PagingEvent pe)
{
	
	Clients.showBusy("Fetching next page");
	int desiredPage = pe.getActivePage();
	
	/*
	List newpagelist  = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel().trim(), desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize  );
	setAllvargridforDBPaging();
    updateAllvarsList(newpagelist,false,null,null,null, desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize );		
    */
    
    
    List newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(selectChr.getSelectedItem().getLabel().trim()), desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize  );
	setAllvargridforDBPaging();
    //this.setAllvargridforGridPaging();
	updateAllvarsList(newpagelist, false, null, null, "",-1,-1);
	
    
	//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr), 1, snpallvars_pagesize  );
	//updateAllvarsList(newpagelist, false, null, null, "",-1,-1);
	
	
    System.out.println("paging from " + (desiredPage*snpallvars_pagesize+1) + "-" + (desiredPage*snpallvars_pagesize));
    
    Clients.clearBusy();
}


/**
 * Clears all inputs
 */
@Listen("onClick = #resetButton")    
public void reset()    {
    comboVar1.setValue("");
    comboVar2.setValue("");
    checkboxMismatchOnly.setChecked(true);
    checkboxAllvarieties.setChecked(false);
    
    this.listboxMyVarieties.setSelectedIndex(0);
    this.comboSubpopulation.setValue("");
    
    intStart.setValue(null);
    intStop.setValue(null);
    comboGene.setValue("");
    selectChr.setSelectedIndex(0);
    this.listboxMySNPList.setSelectedIndex(0);
    this.listboxMyVarieties.setSelectedIndex(0);
    
    this.checkboxCoreSNP.setChecked(true);
    this.selectPhyloTopN.setSelectedIndex(0);
    
    this.radioColorMismatch.setSelected(true);
    
    msgbox.setValue("Select varieties, then set chromosome region or gene locus");
    setchrlength();
}


/**
 * Download Tab format
 */
@Listen("onClick = #buttonDownloadTab")    
public void downloadTab()    {
	 downloadDelimited("snpfile.txt", "\t"); 
}

/**
 * Download CSV format
 */
@Listen("onClick = #buttonDownloadCsv")    
public void downloadCsv()    {
	 downloadDelimited("snpfile.csv",","); 
}


/**
 * Generate download file
 * @param filename
 * @param limitchar
 */
private void downloadDelimited(String filename, String limitchar)    {

	Clients.showBusy("Fetching from Database");
	searchWithServerpaging(filename, limitchar);
	Clients.clearBusy();
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
	
	
	try {
		File file = new File(filename);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		long linecount = 0;
		while(it.hasNext()) {
			Snps2Vars snp2lines= it.next();
			buff.append( snp2lines.getChr() );  buff.append(delimiter);
			buff.append( snp2lines.getPos() );  buff.append(delimiter);
			buff.append( snp2lines.getRefnuc() );  buff.append(delimiter);
			buff.append( snp2lines.getVar1nuc() );  buff.append(delimiter);
			buff.append( snp2lines.getVar2nuc());  buff.append("\n");		
			
			if(linecount > 100 ) {
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
		
		System.out.println("File write complete! Saved to: "+ file.getAbsolutePath());
		
		try {
			String filetype = "text/plain";
			if(delimiter.equals(",")) filetype="text/csv";
				
			Filedownload.save(  new File(filename), filetype);
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
		
	} catch(Exception ex) {
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
}

/**
 * Set components for Database Paging mode. Each page is fetched from DB separately, on demand
 */
private void setAllvargridforDBPaging() {
	snpallvarsresultpaging.setPageSize(snpallvars_pagesize);
	snpallvarsresultpaging.setMold("os");
	snpallvarsresultpaging.setVisible(true);
	snpallvarsresult.setMold("default");
	snpallvarsresult.setStyle("overflow:auto");
	//snpallvarsresult.setStyle("overflow:scroll");
	
}

/**
 * Set components for Grid paging mode. All records fetched in one DB call 
 */
private void setAllvargridforGridPaging() {
	//snpallvarsresult.setMold("select");

	snpallvarsresultpaging.setVisible(false);
	snpallvarsresult.setMold("paging");
	snpallvarsresult.setPageSize(snpallvars_pagesize);
	snpallvarsresult.setPagingPosition("both");
	snpallvarsresult.setStyle("overflow:auto");
	//snpallvarsresult.setStyle("overflow:scroll");
	
}


private HttpSession getSession() {
	return (HttpSession)Sessions.getCurrent().getNativeSession();
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

public void searchWithServerpaging(String filename, String delimiter)    {
	
	// true if output is written to file
	boolean writetofile= filename!=null && !filename.isEmpty();
	
	// to facilitate render on Tab select for JBrowse and phylogenetic tree
	
	GenotypeFacade.snpQueryMode mode=null;
	

	
	if(writetofile)
		System.out.println("Writing to file");
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
	    tabPhylo.setVisible(false);
	    tabJBrowsePhylo.setVisible(false);
	
	    
		gfffile=null;
		urljbrowse = null;	// if has URL address, will render on TabSelect on JBrowse
		urlphylo = null;   // if has URL address, will render on TabSelect on Phylotree
		tallJbrowse = false;	// jbrowse if tall (for all variety) or short (for 2 varieties)
		urljbrowsephylo = null;
		
		snpallvars_pagesize=100;	 // initial rows per page
		
		origAllVarsListModel = null;
		hboxFilterAllele.setVisible(false);
	
	}
	
	
	
	
	
	//tabTable.setWidth("100%");
	//tabboxDisplay.setWidth("1000px");
	win.setWidth(labelScreenWidth.getValue());
	
	System.out.println("origwidth=" + labelScreenWidth.getValue());
	
		genotype =  (GenotypeFacade)AppContext.checkBean(genotype , classGenotypefacade);
		workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
	
		if(this.listboxMySNPList.getSelectedIndex()>0)
			msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " , list " + listboxMySNPList.getSelectedItem().getLabel() );
		else
			msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );

		
		String var1= comboVar1.getValue().trim().toUpperCase();
		String var2 =  comboVar2.getValue().trim().toUpperCase();
		Set setVarieties = null;
		

		
		if(checkboxAllvarieties.isChecked()) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			
		} else if( comboSubpopulation.getValue()!=null &&  !comboSubpopulation.getValue().isEmpty() ) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = genotype.getVarietiesForSubpopulation(comboSubpopulation.getValue().trim());

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

		
		genotype.limitVarieties(setVarieties);	
		
		genotype.setCore( checkboxCoreSNP.isChecked() );
		
		genotype.setColorByNonsyn(  this.radioGraySynonymous.isSelected() );
		genotype.setNonsynOnly( this.radioNonsynOnly.isSelected() );
		
		// initialize empty SNP list
		List listSNPs = new java.util.ArrayList();


		String genename = comboGene.getValue().trim().toUpperCase();
		gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
		
		/*
		if( (var1.isEmpty() ||  var2.isEmpty()  ) && mode != GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS )
		{
			Messagebox.show("Both varieties are required", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		*/
		AppContext.startTimer();

		if( !genename.isEmpty() )
		{
			String genename2 = comboGene.getValue().trim();
			Gene gene2 =  genotype.getGeneFromName( genename2);
			
			//msgbox.setValue("Searching within GENE " + comboGene.getValue().toUpperCase()  + " " + gene2.getChr() + " [" + 
			//		gene2.getFmin() + ".." + gene2.getFmax() + "] ");
			
			intStart.setValue(gene2.getFmin() );	
			intStop.setValue(gene2.getFmax() );	
			selectChr.setSelectedIndex( gene2.getChr()-1 );
			
			
		}
		
		/*       
		if( !genename.isEmpty() )
		{
			String genename2 = comboGene.getValue().trim();
			Gene gene2 =  genotype.getGeneFromName( genename2);
			
			msgbox.setValue("Searching within GENE " + comboGene.getValue().toUpperCase()  + " " + gene2.getChr() + " [" + 
					gene2.getFmin() + ".." + gene2.getFmax() + "] ");

			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ) {
				
				Gene gene = genotype.getGeneFromName( genename );				
				log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
				
				
				//List<ViewCountVarrefMismatchId> listvarmismatches = genotype.countSNPMismatchesInAlllVarieties(Integer.valueOf(gene.getFmin().toPlainString())  ,Integer.valueOf( gene.getFmax().toPlainString() )
				//		, gene.getChr().toUpperCase().replace("CHR", ""), true);
				
				listSNPs = genotype.getSNPinAllVarieties(gene.getFmin()  , gene.getFmax(),  gene.getChr().toString());
				
				AppContext.resetTimer("gene allvars query");

				
				if(writetofile) {
					updateAllvarsList(listSNPs,true,filename,delimiter,"GENE: " + comboGene.getValue().trim().toUpperCase());
					AppContext.resetTimer("write4download");
					return;
				}
				
				//msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listvarmismatches.size() + " varieties" );
				//msgbox.setStyle( "font-color:black" );			
				
				if(genotype.getListSNPAllVarsMismatches()==null) throw new RuntimeException("genotype.getListSNPAllVarsMismatches()==null");
				if(genotype.getSnpsposlist()==null)  throw new RuntimeException("genotype.getSnpsposlist()==null");
				
				msgbox.setValue(msgbox.getValue() + " ... RESULT: "  + genotype.getListSNPAllVarsMismatches().size() + " varieties x " + genotype.getSnpsposlist().size() + " positions" );
				
				setAllvargridforGridPaging();
				
				
				// layout in table
		        updateAllvarsList(listSNPs,true);
		        setGridWidth(genotype.getSnpsposlist().size());
		        //snpallvarsresult.setSizedByContent(true);

		        
		        AppContext.resetTimer("render");		        
		        
		        gene = genotype.getGeneFromName( comboGene.getValue().trim() );

		        int bpmargin = 1;
		        
		        createSNPVarietyGFF(listSNPs, gfffile, genotype.getMapVariety2Order(), bpmargin,BigDecimal.valueOf(gene.getFmin()), BigDecimal.valueOf(gene.getFmax()));
				AppContext.resetTimer("write gff");	
				tallJbrowse=true;
				
				
			}
			else
			{
				listSNPs = genotype.getSNPinVarieties(var1, var2, comboGene.getValue().trim(), 0, mode);				
				
				if(writetofile) {
					writeSNP2Lines2File(filename, "GENE: " + comboGene.getValue().trim().toUpperCase() ,   var1,  var2,  delimiter,  listSNPs);
					return;
				}

			
				createSNP2linesGFF(listSNPs, gfffile );
				
				msgbox.setValue( msgbox.getValue() + " ... RESULT: " +   listSNPs.size() + " positions" );
				//msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " position rows" );
				//msgbox.setStyle( "font-color:black" );
				tallJbrowse=false;

			}
		
			if(listSNPs.size()>0)
			{
				updateJBrowse( gene2.getChr().toString(),
						gene2.getFmin().toString(), gene2.getFmax().toString() , genename2.toUpperCase());
				tabJbrowse.setVisible(true);
				
				if(tallJbrowse) {
					show_phylotree(gene2.getChr().toString(), gene2.getFmin().toString(), gene2.getFmax().toString() );
					tabPhylo.setVisible(true);
				} else
					tabPhylo.setVisible(false);
			}
			else {
				tabJbrowse.setVisible(false);
				tabPhylo.setVisible(false);
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
			}
		
		} 
		*/
		
		if(false) {
			
		}
		else
		{
			// SNPs on all varieties in region
			// vier chr pos region
		
			int chrlen = genotype.getFeatureLength( selectChr.getSelectedItem().getLabel());
	
			Set snpposlist = null;
			
			if(listboxMySNPList.getSelectedIndex()>0) {
				
				String chrlistname[] = listboxMySNPList.getSelectedItem().getLabel().split(":");
				snpposlist = workspace.getSnpPositions( Integer.valueOf( chrlistname[0].replace("CHR","").trim() ) , chrlistname[1].trim() );
				
			}
			else {
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
				if(intStop.getValue()<=intStart.getValue())
				{
					Messagebox.show("End should be greater than start", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
					return;  					
				}  
				
				if( (intStop.getValue()-intStart.getValue()) > 1000000 )
				{
					Messagebox.show("Limit to 1000 kbp range for now", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
					msgbox.setStyle( "font-color:red" );
				return;  					
				}  
				
				if( (intStop.getValue()-intStart.getValue()) > 500000  && mode == GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS )
				{
					Messagebox.show("Limit to 500 kbp range for all variety queries", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
					msgbox.setStyle( "font-color:red" );
				return;  					
				}  
			}
			
			
			String chr = selectChr.getSelectedItem().getLabel().trim();
			if(chr.isEmpty())
			{
				Messagebox.show("No chromosome selected", "INVALID CHROMOSOME VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
//				msgbox.setValue("INVALID CHROMOSOME VALUE: no chromosome selected" );
				return;
			}
			
			/*
			if(intAllelePos.isValid() && selectAllele.getSelectedItem().getLabel().isEmpty()) {
				Messagebox.show("No allele selected", "INVALID ALLELE VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}
			    
			if(!intAllelePos.isValid() && !selectAllele.getSelectedItem().getLabel().isEmpty()) {
				Messagebox.show("No position for allele constraint", "INVALID ALLELE POSITION", Messagebox.OK, Messagebox.EXCLAMATION);
				return;
			}

			Map<Integer,String> mapPos2Allele = null;
			if(intAllelePos.isValid() && !selectAllele.getSelectedItem().getLabel().isEmpty()) {
				mapPos2Allele= new HashMap();
				mapPos2Allele.put(intAllelePos.getValue(), selectAllele.getSelectedItem().getLabel());
			}
			*/

			
			
			if(!comboGene.getValue().isEmpty())
				msgbox.setValue("Searching within GENE " + comboGene.getValue().toUpperCase()  + " " + chr + " [" + 
						intStart.getValue() + ".." + intStop.getValue() + "] ");
			else if(listboxMySNPList.getSelectedIndex()>0)
				msgbox.setValue("SEARCHING: in " + listboxMySNPList.getSelectedItem().getLabel()  );
			else
				msgbox.setValue("SEARCHING: in chromosome " + chr + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
			
			msgbox.setStyle( "font-color:black" );
			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){

				//List<SnpsAllvars> 

				List  newpagelist; // = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, 0  );
				
				if(writetofile) {
					

					//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr),  checkboxExactMismatch.isChecked(), 1,  snpallvars_pagesize  );
					//updateAllvarsList(newpagelist, true, null, null, "",-1,-1);
					
					
					if(snpposlist!=null && !snpposlist.isEmpty()) {
						
						newpagelist = genotype.getSNPStringInAllVarieties(snpposlist, Integer.valueOf(chr), checkboxCoreSNP.isChecked(), true,  -1,  -1 );
						updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + "  SNPLIST: " + listboxMySNPList.getSelectedItem().getLabel() );
				        AppContext.resetTimer("query allvars region " + snpposlist.size() + " size snplist");	
					}
					else 
					{
						//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr), false,  -1,  -1 );
						newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr),  true,  -1,  -1 );
						updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() );
				        AppContext.resetTimer("query allvars region " + (intStop.getValue() - intStart.getValue()) + " bp");	
						
					}

					return;
				}
				
				//createSNPVarietyGFF(newpagelist, gfffile,  genotype.getMapVariety2Order() , 1, intStart.getValue(), intStop.getValue() );
				//AppContext.resetTimer("write gff");
				
				//listSNPs = newpagelist;
				
				boolean serverpaging = true; //false;
				
				/*
				if( intStop.getValue()- intStart.getValue()>10000)
				{
					serverpaging=true;
					snpallvars_pagesize=50;
				}
				if( intStop.getValue()- intStart.getValue()>25000)
				{
					serverpaging=true;
					snpallvars_pagesize=25;
				}
				if( intStop.getValue()- intStart.getValue()>50000)
				{
					serverpaging=true;
					snpallvars_pagesize=10;
				}
				if( intStop.getValue()- intStart.getValue()>100000)
				{
					serverpaging=true;
					snpallvars_pagesize=5;
				}
				*/

				snpallvarsresult.setAttribute("org.zkoss.zul.grid.rod", true);

				
				if(snpposlist!=null && !snpposlist.isEmpty()) {
					newpagelist = genotype.getSNPStringInAllVarieties(snpposlist, Integer.valueOf(chr), this.checkboxCoreSNP.isChecked(),  true,  -1,  -1 );
					
				}
				else {
					if( intStop.getValue()- intStart.getValue()>50000)
					{
						serverpaging=true;
						snpallvars_pagesize=50;
					}
					if( intStop.getValue()- intStart.getValue()>100000)
					{
						serverpaging=true;
						snpallvars_pagesize=50;
					}
					
					
					//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr),  checkboxExactMismatch.isChecked(), 1,  snpallvars_pagesize  );
					//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr),  checkboxExactMismatch.isChecked(),  -1,  -1 );
					newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr),   true,  -1,  -1 );
					
					//this.setAllvargridforGridPaging();
				}
				updateAllvarsList(newpagelist, true, null, null, "",-1,-1);
				
				
				 
				// 
				// genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, snpallvars_pagesize  );
				// 
				// 
				// 

				/*

				List<SnpsAllvarsRefMismatch> listvarmismatches = genotype.countSNPMismatchesInAlllVarieties(intStart.getValue(), intStop.getValue(), chr, true);

				if(serverpaging) {
					// query from database by page
					
					System.out.println( (intStop.getValue()- intStart.getValue()) + " bp length, paging from 1-" + snpallvars_pagesize);
					newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, snpallvars_pagesize  );		
					AppContext.resetTimer("query page from DB " +snpallvars_pagesize + " rows" );
					setAllvargridforDBPaging();
					snpallvarsresultpaging.setTotalSize(listvarmismatches.size());
					updateAllvarsList(newpagelist,true, null,null,null, 1, snpallvars_pagesize );	
					
				} else {
					
					// query everythingh from databasa
					// use 
					System.out.println("no server paging");
					newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, 0  );
					AppContext.resetTimer("query allvars from DB " +snpallvars_pagesize + " rows" );
					setAllvargridforGridPaging();
					updateAllvarsList(newpagelist,true, null,null,null, 1, 0 );
				
				}
				
				
				
				*/
				
				listSNPs = newpagelist;
				
				msgbox.setValue(msgbox.getValue() + " ... RESULT: " + newpagelist.size() + " varieties x " + genotype.getSnpsposlist().size() + " positions" );

		        AppContext.resetTimer("create table" );

		        tallJbrowse = true;
		        
		        origAllVarsListModel = newpagelist;
		        
		        if(origAllVarsListModel.size()>0 )
		        	{
		        	 	java.util.List listPosition = new java.util.ArrayList();
		        		Iterator<SnpsAllvarsPos> itlistpos = genotype.getSnpsposlist().iterator();
		        		listPosition.add("");
		        		while(itlistpos.hasNext())
		        		{
		        			listPosition.add(itlistpos.next().getPos());
		        		}
		        		listboxPosition.setModel( new SimpleListModel(listPosition));
		        		hboxFilterAllele.setVisible(true);
		        		
		        	}
		        
				
				//listSNPs =  genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr);
				//msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listvarmismatches.size() + " varieties" );
				//msgbox.setStyle( "font-color=black" );
			}
			else {	
				// SNPs on 2 varieties in region
				
				
				if(snpposlist!=null && !snpposlist.isEmpty()) {
					listSNPs = genotype.getSNPinVarieties(var1,var2,  snpposlist, chr , mode, checkboxCoreSNP.isChecked() );
					if(writetofile) {
						writeSNP2Lines2File(filename, "REGION: CHR " + chr + " in position list " +  listboxMySNPList.getSelectedItem().getLabel() ,   var1,  var2,  delimiter,  listSNPs);
						return;
					}
				} else
				{
					listSNPs =  genotype.getSNPinVarieties(var1,var2, intStart.getValue(), intStop.getValue(), chr, mode, checkboxCoreSNP.isChecked());
					if(writetofile) {
						writeSNP2Lines2File(filename, "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() ,   var1,  var2,  delimiter,  listSNPs);
						return;
					}
				}
				
				
				createSNP2linesGFF(listSNPs, gfffile );
				
				//msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " position rows" );
				//msgbox.setStyle( "font-color:black" );
				
				msgbox.setValue( msgbox.getValue() + " ... RESULT: " +  listSNPs.size() + " positions" );
				
				tallJbrowse= false;
				
			}

			
			if(listSNPs.size()>0)
			{
				
				if(listboxMySNPList.getSelectedIndex()>0) {
					tabJbrowse.setVisible(false);
					tabPhylo.setVisible(false);
				}
				else {
					updateJBrowse( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
					tabJbrowse.setVisible(true);
					
					if(tallJbrowse) {
						show_phylotree(chr.toUpperCase().replace("CHR0","").replace("CHR0",""), intStart.getValue().toString() , intStop.getValue().toString()  );
						tabPhylo.setVisible(true);
					}
				}
			}
			else {
				tabJbrowse.setVisible(false);
				tabPhylo.setVisible(false);
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
			}
			
		}
		
		
		if(mode==GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS) {

		}
		else {
			snpallvarsresult.setModel(   new SimpleListModel(new java.util.ArrayList()) );
			snpallvarsresult.setVisible(false);
			//snpallvarsresultMsg.setVisible(false);
			
			// update table header
			Listheader lhr = (Listheader)snpresult.getListhead().getFirstChild();    	
			String[] headers = new String[]{"CHROMOSOME","POSITION", "NIPPONBARE", comboVar1.getValue().toUpperCase() ,comboVar2.getValue().toUpperCase()};
			int i = 0;
			while(lhr != null) {
				lhr.setLabel(headers[i] );
				lhr = (Listheader)lhr.getNextSibling();
				i++;
			}
			//((SNPListItemRenderer)snpresult.getItemRenderer()).setQuerymode(mode);
			
			
		    if(radioColorAllele.isSelected())
	        	((SNPListItemRenderer)snpresult.getItemRenderer()).setColorMode( SNPListItemRenderer.COLOR_NUCLEOTIDE );
	        else if (radioColorMismatch.isSelected())
	        	((SNPListItemRenderer)snpresult.getItemRenderer()).setColorMode( SNPListItemRenderer.COLOR_MISMATCH );
		    
			snpresult.setModel( new SimpleListModel(listSNPs) );
			snpresult.setVisible(true);


			
			AppContext.resetTimer("render 2vars table" );
		}

}

/**
 * Render all varieties to table/client
 * 
 * @param listSNPs
 * @param updateHeaders
 */

private void updateAllvarsList(List listSNPs, boolean updateHeaders)
{
	updateAllvarsList( listSNPs,  updateHeaders, null, null, null, 1, 0);
}

/**
 * Write all varieties to file, with delimeter and header (first line)
 * 
 * @param listSNPs
 * @param updateHeaders
 * @param filename
 * @param delimiter
 * @param header
 */
private void updateAllvarsList(List listSNPs, boolean updateHeaders, String filename, String delimiter, String header)
{
	updateAllvarsList(listSNPs, updateHeaders, filename, delimiter, header, 1, 0);
}


/**
 * Render (filename==null) OR write to file varieties from firstrow to (firstrow-numRows-1)
 * 
 * @param listSNPs	// SNP object list
 * @param updateHeaders	// change table headers
 * 
 * @param filename	// write to file
 * @param delimiter
 * @param header
 * 
 * @param firstRow	1-indexed variety row from orderedQuery
 * @param numRows	varieties per page, if zero, to end
 * @param fetchMismatchOnly  listSNPs contains only NULL and referfence mismatches
 */
private void updateAllvarsList(List<SnpsStringAllvars> listSNPs, boolean updateHeaders, String filename, String delimiter, String header, int firstRow, int numRows) {
	 updateAllvarsListSnpstring( listSNPs,  updateHeaders,  filename,  delimiter,  header); //,  firstRow,  numRows);
}

private void updateAllvarsListOrig(List<SnpsAllvars> listSNPs, boolean updateHeaders, String filename, String delimiter, String header, int firstRow, int numRows)
{

	
	boolean fetchMismatchOnly =  AppContext.isSNPAllvarsFetchMismatchOnly();  //listSNPs contains only NULL and referfence mismatches
	updateHeaders=false;	// set false for now because rendering is slow with header!
	//snpallvarsresult.setSizedByContent(true);
	
	try {
	
	boolean write2file = filename!=null && !filename.isEmpty();
	
	
	List<SnpsAllvarsPos> snpsposlist = genotype.getSnpsposlist();
	
	StringBuffer buff=new StringBuffer();
	
	if(write2file) {
		
		buff.append(header ); buff.append("\n");
		buff.append("VARIETY"); buff.append(delimiter);  buff.append("MISMATCH");  buff.append(delimiter);
		
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		
		StringBuffer buffRefnuc = new StringBuffer();
		buffRefnuc.append("REFERENCE");  buffRefnuc.append(delimiter);  buffRefnuc.append(delimiter);
		
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
			buff.append( posnuc.getPos());
			buffRefnuc.append( posnuc.getRefnuc() );
			
			if(itPos.hasNext()){
				buff.append(delimiter);
				buffRefnuc.append(delimiter);
			}
		}
		buff.append("\n");
		buffRefnuc.append("\n");
		buff.append( buffRefnuc );
		
	}

	// map column index to Reference Base position
	Map<BigDecimal ,Integer> mapPos2Index = new java.util.HashMap<BigDecimal,Integer>();

	
	// map reference base/allele to column index  
	Map<Integer,Character> mapIdx2Refnuc = new java.util.HashMap();
		
	if(updateHeaders) {


		snpallvarsresult.setSizedByContent(false);

		AppContext.resetTimer("start headerupdate");
		
    	Auxhead head = new Auxhead();
    	Auxheader header1 = new Auxheader("REFERENCE");
    	header1.setParent(head);
    	Auxheader header2 = new Auxheader("");
    	header2.setParent(head);
		
		//snpallvarsresult

		Columns cols = new Columns();
		Column col1 = new Column("VARIETY");
		col1.setParent(cols);
		
		Column col2 = new Column( "MISMATCH");
		col2.setParent(cols);
		

		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		
		int posindex = 2;
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
				mapPos2Index.put(posnuc.getPos() , Integer.valueOf(posindex));
				Column col = new Column( posnuc.getPos().toString() );
				col.setParent(cols);
				//headerpos.add( posnuc.getPos().toString() );
				//headerrefnuc.add(posnuc.getPos().toString());
				
				Auxheader header3 = new Auxheader( posnuc.getRefnuc().toString());
				header3.setTooltip(posnuc.getRefnuc().toString());
				header3.setParent(head);
				
				if(posnuc.getRefnuc()!=null && !posnuc.getRefnuc().isEmpty())
					mapIdx2Refnuc.put(posindex , posnuc.getRefnuc().charAt(0));
				else
					mapIdx2Refnuc.put(posindex , null);
				posindex++;
				
		}
		
		AppContext.resetTimer("columns setup");
		
		Columns oldcols = snpallvarsresult.getColumns();
		if( oldcols != null)
			snpallvarsresult.removeChild(oldcols);
		
		
	/*	Collection oldheads =  snpallvarsresult.getHeads();
		if(oldheads!=null) {
			List listHeads = new java.util.ArrayList();
			listHeads.addAll( oldheads );
			
			Iterator<Auxhead> itHeads = listHeads.iterator();
			while(itHeads.hasNext() )
				snpallvarsresult.removeChild( itHeads.next());
		}
		*/
		
		
		cols.setParent(snpallvarsresult);
		

	
		head.setParent(snpallvarsresult);
		
		snpallvarsresult.setSizedByContent(true);
		
		AppContext.resetTimer("columns chaged");	

				
	}
	else
	{
		
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		
		int posindex = 2;
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
				mapPos2Index.put(posnuc.getPos() , Integer.valueOf(posindex));
				//mapIdx2Refnuc.put(posindex , posnuc.getRefnuc());
				
				if(posnuc.getRefnuc()!=null && !posnuc.getRefnuc().isEmpty())
					mapIdx2Refnuc.put(posindex , posnuc.getRefnuc().charAt(0));
				else
					mapIdx2Refnuc.put(posindex , null);
				
				posindex++;
		}
	}

 
		// transform into 1 pos per item
		

		if(genotype==null) throw new RuntimeException("genotype==null");
		if(genotype.getMapVariety2Mismatch()==null) throw new RuntimeException("genotype.getMapVariety2Mismatch()==null");
		if(genotype.getMapVariety2Order()==null) throw new RuntimeException("genotype.getMapVariety2Order()==null");

		Map<BigDecimal, Integer> mapVariety2Order = genotype.getMapVariety2Order();
		
		// set number of rows in table
		
		int nrows = mapVariety2Order.size();	// number of varieties	
		if(numRows>0) 
		{
			if(nrows>=numRows) nrows= numRows;	// specified  numRows/page or nrows
		} else if (numRows==0)
		{
			nrows= nrows-firstRow+1;	// firstRow to end
		}
		
		String[][] viewtable = new String[nrows+2][snpsposlist.size()+2];
		
		
		// use freeze
		
		viewtable[0][0]="VARxPOS";
		viewtable[0][1]="MIS";
		viewtable[1][0]="REFERENCE";
		viewtable[1][1]="";
				
		Iterator<BigDecimal> itPos2Idx = mapPos2Index.keySet().iterator();
		int iViewtable=0;
		while(itPos2Idx.hasNext()) {
		
			BigDecimal pos = itPos2Idx.next();
			Integer idx = mapPos2Index.get(pos);
			viewtable[0][idx] = pos.toString(); 
			
			String refnuc = mapIdx2Refnuc.get(idx).toString();
			
			viewtable[1][idx] = refnuc;
			
			
			if(fetchMismatchOnly) {
				for(iViewtable=2; iViewtable<nrows+2; iViewtable++)  viewtable[iViewtable][idx]=refnuc;
			}
		}
		
		
		// always true! if(firstRow>-1) 
		//{
			// get vars from firstRow to nrows
			Iterator<SnpsAllvarsRefMismatch> itVarsmismatch  = genotype.getListSNPAllVarsMismatches(firstRow, nrows).iterator();
		
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
			
			//int i=0;
			int i=2;
			
			while(itVarsmismatch.hasNext()) {
				SnpsAllvarsRefMismatch var = itVarsmismatch.next();
				
				if( !mapVarid2Variety.containsKey( var.getVar() ) ) throw new RuntimeException("mapVarid2Variety doesnt have key " + var.getVar());
				
				viewtable[i][0] = mapVarid2Variety.get( var.getVar() ).getName();
				viewtable[i][1] = var.getMismatch().toString();
				i++;
			}
			
			System.out.println( (i-2) + " varieties added to table");
		//}
		System.out.println(firstRow+ " firstRow");
		System.out.println(nrows+ " nrows");
		System.out.println(numRows+ " numRows");
		
		
		Iterator<SnpsAllvars> itSnps = listSNPs.iterator();
		while(itSnps.hasNext())
		{
			SnpsAllvars snps = itSnps.next();
			
			if(!mapVariety2Order.containsKey( snps.getVar()  )) throw new RuntimeException("mapVariety2Order doesnt contain " + snps.getVar());
			if(!mapPos2Index.containsKey( snps.getPos()  )) throw new RuntimeException("mapPos2Index doesnt contain " + snps.getPos());
			
			if(snps.getVarnuc()!=null && !snps.getVarnuc().isEmpty() )
				viewtable[mapVariety2Order.get( snps.getVar() )- (firstRow-1) + 2 ][  mapPos2Index.get(snps.getPos()) ] =   String.valueOf( snps.getVarnuc().charAt(0) );
			else
				viewtable[mapVariety2Order.get( snps.getVar() )- (firstRow-1) + 2  ][  mapPos2Index.get(snps.getPos()) ] =   "";
		}
		
		
		if(write2file) {
			
	
			try {
			
				File file = new File(filename);
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				long linecount = 0;
			
				
				for(int varrow=0; varrow<viewtable.length; varrow++)
				{
					for(int poscol=0; poscol< viewtable[varrow].length; poscol++ ) {
						//buff.append(viewtable[varrow][poscol].toString());
						if(viewtable[varrow][poscol]==null)
							buff.append("");
						else
							buff.append(viewtable[varrow][poscol].toString());
						
						
						if( poscol< viewtable[varrow].length-1) buff.append(delimiter);
					}
					buff.append("\n");
				
					if(linecount > 100 ) {
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
				
				System.out.println("File write complete! Saved to: "+ file.getAbsolutePath());
				
				try {
					String filetype = "text/plain";
					if(delimiter.equals(",")) filetype="text/csv";
						
					Filedownload.save(  new File(filename), filetype);
					} catch(Exception ex)
					{
						ex.printStackTrace();
					}
				

			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
			
			return;
			
			
		}
				
	
		//((SNPAllvarsRowRenderer)snpallvarsresult.getRowRenderer())
        ((SNPAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setMapIdx2Refnuc(mapIdx2Refnuc);  
        
        if(radioColorAllele.isSelected())
        	((SNPAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setColorMode( SNPAllvarsRowRenderer.COLOR_NUCLEOTIDE );
        else if (radioColorMismatch.isSelected())
        	((SNPAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setColorMode( SNPAllvarsRowRenderer.COLOR_MISMATCH );
        
				        
        snpallvarsresult.setAttribute("org.zkoss.zul.grid.rod", true);        
		

 
        
        /*
        Columns cols = snpallvarsresult.getColumns();
        
        if(cols==null) cols = new Columns();
        
        cols.setHflex("min");
        
        if(intStop.getValue()>10000000)
        	cols.setWidth("30px");
        else if (intStop.getValue()>100000)
        	cols.setWidth("20px");
        else
        	cols.setWidth("15px");
        	
        if(snpallvarsresult.getColumns()==null)
        	cols.setParent(snpallvarsresult);
        */

        setGridWidth(snpsposlist.size());
        
        /*
        Columns cols = snpallvarsresult.getColumns();
 
        int pxperpos = 35;
        if(cols!=null) {
	        cols.setHflex("min");
	        if(intStop.getValue()>10000000)
	        {
	        	cols.setWidth("38px");
	        	pxperpos=42;
	        } else if (intStop.getValue()>1000000){
	        	cols.setWidth("33px");
	        	pxperpos=38;
	        } else if (intStop.getValue()>100000){
	        	cols.setWidth("28px");
	        	pxperpos=32;
        	}
        	else {
	        	cols.setWidth("20px");
	        	pxperpos=25;
	        }
        }
        
        int tablewidth = snpsposlist.size()*pxperpos + 150;
        String strTableWidth="100%";
        String strTabWidth="100%";
        
 
        if(tablewidth>1300) {

        	win.setWidth(tablewidth + "px");
        } else
        	win.setWidth(labelScreenWidth.getValue());
               
     */
        
		snpallvarsresult.setModel(   new SimpleListModel(viewtable) );
		
		snpallvarsresult.setSizedByContent(true);
		
		
		
		
		//snpallvarsresultMsg.setValue("");
		//snpallvarsresultMsg.setVisible(true);
		snpallvarsresult.setVisible(true);
		
		
        
	} catch(Exception ex) {
		ex.printStackTrace();
	}
        
        
}


private void updateAllvarsListSnpstring(List<SnpsStringAllvars> listSNPs, boolean updateHeaders, String filename, String delimiter, String header) //, int firstRow, int numRows)
{

	
	boolean fetchMismatchOnly =  AppContext.isSNPAllvarsFetchMismatchOnly();  //listSNPs contains only NULL and referfence mismatches
	//updateHeaders=false;	// set false for now because rendering is slow with header!
	//snpallvarsresult.setSizedByContent(true);
	
	try {
	
	boolean write2file = filename!=null && !filename.isEmpty();
	
	
	List<SnpsAllvarsPos> snpsposlist = genotype.getSnpsposlist();
	
	StringBuffer buff=new StringBuffer();


	
	if(write2file) {
		
		buff.append(header ); buff.append("\n");
		buff.append("VARIETY"); buff.append(delimiter);  buff.append("MISMATCH");  buff.append(delimiter);
		
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		
		StringBuffer buffRefnuc = new StringBuffer();
		buffRefnuc.append("REFERENCE");  buffRefnuc.append(delimiter);  buffRefnuc.append(delimiter);
		
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
			buff.append( posnuc.getPos());
			buffRefnuc.append( posnuc.getRefnuc() );
			
			if(itPos.hasNext()){
				buff.append(delimiter);
				buffRefnuc.append(delimiter);
			}
		}
		buff.append("\n");
		buffRefnuc.append("\n");
		buff.append( buffRefnuc );
	}
	
	
	
	if(updateHeaders) {

		snpallvarsresult.setSizedByContent(false);

		AppContext.resetTimer("start headerupdate");
		
    	Auxhead head = new Auxhead();
    	
    	
    	Auxheader header1 = new Auxheader("REFERENCE");
    	header1.setParent(head);
    	Auxheader header2 = new Auxheader("");
    	header2.setParent(head);
		
		//snpallvarsresult

		Columns cols = new Columns();
		Column col1 = new Column("VARIETY");
		col1.setParent(cols);
		
		Column col2 = new Column( "MISMATCH");
		col2.setParent(cols);
		

		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
				Column col = new Column( posnuc.getPos().toString() );
				col.setStyle( "font-weight:normal;color:black;background-color:lightgray;font-family:Arial Narrow;align:center");
				col.setParent(cols);
				//headerpos.add( posnuc.getPos().toString() );
				//headerrefnuc.add(posnuc.getPos().toString());
				
				Auxheader header3 = new Auxheader( posnuc.getRefnuc().toString());
				header3.setStyle("align:center;font-weight:bold");
				header3.setTooltip(posnuc.getRefnuc().toString());
				header3.setParent(head);
		}
		
		AppContext.resetTimer("columns setup");
		
		Columns oldcols = snpallvarsresult.getColumns();
		if( oldcols != null)
			snpallvarsresult.removeChild(oldcols);
		
		
		Collection oldheads =  snpallvarsresult.getHeads();
		if(oldheads!=null) {
			List listHeads = new java.util.ArrayList();
			listHeads.addAll( oldheads );
			
			Iterator<Auxhead> itHeads = listHeads.iterator();
			while(itHeads.hasNext() )
				snpallvarsresult.removeChild( itHeads.next());
		}
		
		cols.setParent(snpallvarsresult);
		head.setParent(snpallvarsresult);
		
		snpallvarsresult.setSizedByContent(true);
		
		AppContext.resetTimer("columns changed");	

				
	}
	
	
	
	char refnuc[] = new char[snpsposlist.size()];
	
	StringBuffer buffPositions = new StringBuffer();
	StringBuffer buffReference = new StringBuffer();
	buffPositions.append("VARIETY").append("\t").append("MIS%");
	buffReference.append("REFERENCE").append("\t");
	Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
	if(itPos.hasNext()){
		buffPositions.append("\t");
		buffReference.append("\t");
	}	
	int posidx=0;
	
	// for use in heterozygous and color codings of some alleles
	/*
	Map<Integer,Long> mapPosidx2Snpid = new HashMap();
	String snpfeatureid = "";
	 if(selectChr.getSelectedItem().getLabel().length()>1)
	 	snpfeatureid = "1" + selectChr.getSelectedItem().getLabel();
	 else snpfeatureid = "10" + selectChr.getSelectedItem().getLabel();
	 */
		 
	
	while(itPos.hasNext()) {
		SnpsAllvarsPos posnuc=itPos.next();
		
		buffPositions.append( posnuc.getPos());
		buffReference.append( posnuc.getRefnuc() );
		refnuc[posidx]=posnuc.getRefnuc().charAt(0);
		
		//mapPosidx2Snpid.put(posidx, Long.valueOf(snpfeatureid + String.format("%08d",posnuc.getPos())) );
		
		if(itPos.hasNext()){
			buffPositions.append("\t");
			buffReference.append("\t");
		}
		posidx++;
	}
	
	System.out.println( posidx + " pos from reference" );
	
	/*
	List listTable = new ArrayList();
	listTable.add(buffPositions.toString());
	listTable.add(buffReference.toString());
	listTable.addAll(listSNPs);
	*/
	List listTable= listSNPs;
		
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapVarid2Variety = varietyfacade.getMapId2Variety();
			
			
			
		if(write2file) {
			
			try {
			
				File file = new File(filename);
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				long linecount = 0;
				Iterator<SnpsStringAllvars> itSnpstring = listTable.iterator();
				while(itSnpstring.hasNext()) {
					SnpsStringAllvars snpstr = itSnpstring.next();
					
					char viewtable[] = snpstr.getVarnuc().toCharArray();
					
					buff.append( mapVarid2Variety.get( snpstr.getVar() ).getName()  ).append(delimiter).append( snpstr.getMismatch().toString()  ).append(delimiter);
					
					for(int poscol=0; poscol< viewtable.length; poscol++ ) {
						//buff.append(viewtable[varrow][poscol].toString());
						if(viewtable[poscol]=='0' || viewtable[poscol]==' ')
							buff.append("");
						else
							buff.append(viewtable[poscol]);
						
						if( poscol< viewtable.length-1) buff.append(delimiter);
					}
					buff.append("\n");
				
					if(linecount > 100 ) {
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
				
				System.out.println("File write complete! Saved to: "+ file.getAbsolutePath());
				
				try {
					String filetype = "text/plain";
					if(delimiter.equals(",")) filetype="text/csv";
						
					Filedownload.save(  new File(filename), filetype);
					} catch(Exception ex)
					{
						ex.printStackTrace();
					}
				

			} catch(Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
	
				
	
		
        ((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setRefnuc(refnuc); 
        ((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setMapVarId2Var(mapVarid2Variety); 
        ((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setAllele2Matrix( genotype.getHeteroAlleleMatrix() );
        
        if(radioGraySynonymous.isSelected() || radioNonsynOnly.isSelected()) {
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setGraySynonymous(true);
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setMapIdx2NonsynFlags( genotype.getMapIdx2Nonsynflags() ); 
        } else {
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setGraySynonymous(false);
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setMapIdx2NonsynFlags( null ); 
        }
        
        /*
        if(radioGraySynonymous.isSelected()) {
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setGraySynonymous(true);
        	//((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setListNonsynFlags(genotype .getListNonsynFlags());
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setListNonsynFlags(genotype.get);
        } else if (radioNonsynOnly.isSelected()) {
        	//((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setMapIdx2NonsynAlleles(  genotype.getMapIdx2NonsynAlleles() );
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setGraySynonymous(true);
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setListNonsynFlags(genotype.getListNonsynFlags());
        }
        */
        
        
        if(radioColorAllele.isSelected())
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setColorMode( SNPAllvarsRowRenderer.COLOR_NUCLEOTIDE );
        else if (radioColorMismatch.isSelected())
        	((SNPStringAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setColorMode( SNPAllvarsRowRenderer.COLOR_MISMATCH );
        
				        
        snpallvarsresult.setAttribute("org.zkoss.zul.grid.rod", true);        
		

 
        
        /*
        Columns cols = snpallvarsresult.getColumns();
        
        if(cols==null) cols = new Columns();
        
        cols.setHflex("min");
        
        if(intStop.getValue()>10000000)
        	cols.setWidth("30px");
        else if (intStop.getValue()>100000)
        	cols.setWidth("20px");
        else
        	cols.setWidth("15px");
        	
        if(snpallvarsresult.getColumns()==null)
        	cols.setParent(snpallvarsresult);
        */

        setGridWidth(snpsposlist.size());
        
        /*
        Columns cols = snpallvarsresult.getColumns();
 
        int pxperpos = 35;
        if(cols!=null) {
	        cols.setHflex("min");
	        if(intStop.getValue()>10000000)
	        {
	        	cols.setWidth("38px");
	        	pxperpos=42;
	        } else if (intStop.getValue()>1000000){
	        	cols.setWidth("33px");
	        	pxperpos=38;
	        } else if (intStop.getValue()>100000){
	        	cols.setWidth("28px");
	        	pxperpos=32;
        	}
        	else {
	        	cols.setWidth("20px");
	        	pxperpos=25;
	        }
        }
        
        int tablewidth = snpsposlist.size()*pxperpos + 150;
        String strTableWidth="100%";
        String strTabWidth="100%";
        
 
        if(tablewidth>1300) {

        	win.setWidth(tablewidth + "px");
        } else
        	win.setWidth(labelScreenWidth.getValue());
               
     */
        //Map mapPosidx2Snpid, Map mapOrder2Var, Map mapVar2Pos2Allele2
        
        

        
        
		snpallvarsresult.setModel(   new SimpleListModel(listTable) );
		
		
		
		//genotype.getMapVariety2Order();
		
		snpallvarsresult.setSizedByContent(true);
		
		
		
		
		//snpallvarsresultMsg.setValue("");
		//snpallvarsresultMsg.setVisible(true);
		snpallvarsresult.setVisible(true);
		
		
        
	} catch(Exception ex) {
		ex.printStackTrace();
	}
        
        
}


/**
 * Set the grid column width based on the number of digits for positions
 * @param npos
 */
private void setGridWidth(int npos) {
	/*
	 Columns cols = snpallvarsresult.getColumns();
	
     int pxperpos = 35;
     if(cols!=null) {
	        cols.setHflex("min");
	        if(intStop.getValue()>10000000)
	        {
	        	cols.setWidth("38px");
	        	pxperpos=44;
	        } else if (intStop.getValue()>1000000){
	        	cols.setWidth("33px");
	        	pxperpos=40;
	        } else if (intStop.getValue()>100000){
	        	cols.setWidth("26px");
	        	pxperpos=38;
	        } else if (intStop.getValue()>10000){
	        	cols.setWidth("24px");
	        	pxperpos=36;
	        }
	     	else {
		        	cols.setWidth("20px");
		        	pxperpos=28;
		        }
     }
     snpallvarsresult.setSizedByContent(true);
     */
     
    /*
     int tablewidth = npos*pxperpos + 150;
     String strTableWidth="100%";
     String strTabWidth="100%";
     

     if(tablewidth>1300) {
     	win.setWidth(tablewidth + "px");
     } else
     	win.setWidth(labelScreenWidth.getValue());
     */       
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
			return 0;
		}
		
		
	}
	
	 
	 private void createSNPVarietyGFF(List<SnpsAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end) {
		 createSNPVarietyGFF(listSNPs, filename, var2order, bpgap,  start,  end, false);
	 }
	
	 /**
	  * 
	  * @param listSNPs listSNPs contains only mismatch or NULL
	  * @param filename
	  * @param var2order
	  * @param bpgap
	  * @param start
	  * @param end
	  * @param mismatchOnly
	  */
	 
	 private void createSNPVarietyGFF(List<SnpsAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean mismatchOnly) {
		
		mismatchOnly =true;
		boolean isphylojbrowse = filename.endsWith(".phylo.gff");
		 
		List listSnpPos = genotype.getSnpsposlist() ;
		Iterator<SnpsAllvarsPos> itPos = listSnpPos.iterator();

		Map<Long, Long> mapPrevPos = new java.util.HashMap<Long, Long>();
		Map<Long, Long> mapNextPos = new java.util.HashMap<Long, Long>();
		

		//		BigDecimal prevPos=start;
		StringBuffer bufPos = new StringBuffer();
		long prevpos = start.longValue();
		if(itPos.hasNext()) {
			SnpsAllvarsPos pos = itPos.next();
			long curpos = pos.getPos().longValue();
			mapPrevPos.put(curpos, prevpos);
			mapNextPos.put(prevpos, curpos);
			prevpos = curpos;
			bufPos.append( curpos ).append(",");
		}

		Map<Long, long[]> mapPos2Bounds = new java.util.HashMap<Long, long[]>();
		
		while(itPos.hasNext()) {
			SnpsAllvarsPos pos = itPos.next();
			long curpos = pos.getPos().longValue();
			mapPrevPos.put(curpos, prevpos);
			mapNextPos.put(prevpos, curpos);
			
			mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2+1  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
			
			prevpos = curpos;
			bufPos.append( curpos ).append(",");
			
		}
		mapNextPos.put(prevpos, end.longValue());
		mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2+1  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
		

		
		System.out.println(bufPos.toString());
		System.out.println(listSnpPos.size() + " SNP Positions");
		
		//if(!checkShowsnp.isChecked()) return;
		// sort snps by start
		
		//Set setSNP = new java.util.TreeSet<ViewSnpAllvarsId>(listSNPs);
		
		//java.util.Collections.sort(listSNPs, new ViewSnpAllvarsIdStartComparator());
		
		
		//StringBuffer buff = new StringBuffer();
		
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
		
		try {
	
			Iterator<SnpsAllvars> itsnp = listSNPs.iterator();
		
			java.util.List listGFF = new java.util.ArrayList();			
			
			while(itsnp.hasNext()) {
				SnpsAllvars snpvars = itsnp.next();
				
				if(isphylojbrowse && var2order.get(snpvars.getVar())==null ) continue;  
				
				
				
				//long longsnpvar = snpvars.getVar().longValueExact();
				long longsnppos = snpvars.getPos().longValueExact();

				
				String chr = snpvars.getChr().toString(); 
				
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
	
				String order =  var2order.get(snpvars.getVar() ).toString();

				Variety var=mapId2Variety.get( snpvars.getVar() );
				
				if(var==null) throw new RuntimeException(snpvars.getVar()  + " not in mapId2Variety");
				
				String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
						";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  snpvars.getPos() + 
						";AlleleRef=" +   snpvars.getRefnuc() + 
						";AlleleAlt=" + snpvars.getVarnuc() +
						";Position=" +  snpvars.getPos() +
						";order=" + order +
						"\n";	

				long bounds[] = mapPos2Bounds.get( longsnppos );
				listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  bounds[0],  bounds[1]));
				
			}
			
			
			
			

			java.util.Collections.sort( listGFF, new GFFStartComparator());
			
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
			
			System.out.println("temgff written in: " + file.getAbsolutePath() );
			log.info( "temgff written in: " + file.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	 
	 //private void createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end) {
	 private void createSNPStringVarietyGFF(List<SnpsStringAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean mismatchOnly) {
			
			//mismatchOnly =true;
			boolean isphylojbrowse = filename.endsWith(".phylo.gff");
			 
			List listSnpPos = genotype.getSnpsposlist() ;
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
				cRef[iPosCount]=pos.getRefnuc().charAt(0);

				mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
				
				prevpos = curpos;
				bufPos.append( curpos ).append(",");
				iPosCount++;
				
			}
			mapNextPos.put(prevpos, end.longValue());
			mapPos2Bounds.put(prevpos, new long[] {  (mapPrevPos.get(prevpos)+prevpos)/2  ,  (mapNextPos.get(prevpos)+prevpos)/2  });
			

			
			System.out.println(bufPos.toString());
			System.out.println(listSnpPos.size() + " SNP Positions");
			
			//if(!checkShowsnp.isChecked()) return;
			// sort snps by start
			
			//Set setSNP = new java.util.TreeSet<ViewSnpAllvarsId>(listSNPs);
			
			//java.util.Collections.sort(listSNPs, new ViewSnpAllvarsIdStartComparator());
			
			
			//StringBuffer buff = new StringBuffer();
			
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
			
			try {
		
				Iterator<SnpsStringAllvars> itsnpstring = listSNPs.iterator();
			
				java.util.List listGFF = new java.util.ArrayList();			
				
				while(itsnpstring.hasNext()) {
					SnpsStringAllvars snpvars = itsnpstring.next();
					
					if(isphylojbrowse && var2order.get(snpvars.getVar())==null ) continue;  
					
					
					
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

					
					for(int iPos = 0; iPos<listSnpPos.size(); iPos ++ ) {
						
						char charAtPos = snpvars.getVarnuc().substring(iPos,iPos+1).charAt(0);
						if(!mismatchOnly || (mismatchOnly && charAtPos!='0' && cRef[iPos]!= charAtPos ) ) { 
							String line_right =  "\t.\t.\t.\tName=" +  var.getName() +
									";ID=VAR" +  var.getVarietyId() + "-CHR" + snpvars.getChr() + "-" +  lPos[iPos] + 
									";AlleleRef=" +   cRef[iPos] + 
									";AlleleAlt=" + charAtPos +
									";Position=" +  lPos[iPos] +
									";order=" + order +
									"\n";	
		
							long bounds[] = mapPos2Bounds.get( lPos[iPos] );
							listGFF.add(new GFF( chr + "\tIRIC\tsnp\t", line_right,  bounds[0],  bounds[1]));
						}
					}
					
				}
				
				
				
				

				java.util.Collections.sort( listGFF, new GFFStartComparator());
				
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
				
				System.out.println("temgff written in: " + file.getAbsolutePath() );
				log.info( "temgff written in: " + file.getAbsolutePath());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	 
	 
	 
/**
 * Orig create GFF when all (match and mismatched) are fetched
 * @param listSNPs
 * @param filename
 * @param var2order
 * @param bpgap
 * @param start
 * @param end
 * @param mismatchOnly
 */
private void createSNPVarietyGFFFetchAll(List<SnpsAllvars> listSNPs, String filename, Map var2order, int bpgap, BigDecimal start, BigDecimal end, boolean mismatchOnly) {
		
		
		
		
		//if(!checkShowsnp.isChecked()) return;
		// sort snps by start
		
		//Set setSNP = new java.util.TreeSet<ViewSnpAllvarsId>(listSNPs);
		
		//java.util.Collections.sort(listSNPs, new ViewSnpAllvarsIdStartComparator());
		
		
		//StringBuffer buff = new StringBuffer();
		
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapId2Variety=varietyfacade.getMapId2Variety();
		
		try {
		//	File file = new File(tempdir + filename);
		//	
		//	FileWriter fw = new FileWriter(file.getAbsoluteFile());
		//	BufferedWriter bw = new BufferedWriter(fw);
			
		//	int linecount = 0;
			Iterator<SnpsAllvars> itsnp = listSNPs.iterator();
		
			long prevpos = 0;
			long prevstartpos = 0;
			String prevline_left="";
			String prevline_right="";
			long prevvar = -1;
			
			long intStart = start.longValueExact();
			long intEnd = end.longValueExact();
			
			
			// include left and right snps
			intStart = intStart - (intEnd-intStart);
			if(intStart<1) intStart=1;
			
			intEnd = intEnd +  (intEnd-intStart);
			if(intEnd>chrlength) intEnd = chrlength;
			
			if(itsnp.hasNext())
			{
				
				SnpsAllvars snpvars = itsnp.next();
				String chr = snpvars.getChr().toString(); 
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
				prevline_left = chr + "\tIRIC\tsnp\t"  ;
				prevpos = snpvars.getPos().longValue();
				prevstartpos = intStart;
				prevvar = snpvars.getVar().longValue();
				String  order =  var2order.get(snpvars.getVar() ).toString();
/*				int orlen = order.length();
				switch(orlen) {
					case 1: order = "000" + order;
					case 2: order = "00" + order;
					case 3: order = "0" + order;
				}
				*/
				Variety var=mapId2Variety.get( snpvars.getVar() );
				
				if(snpvars.getRefnuc().equals(snpvars.getVarnuc()))
						prevline_right="";
				else
					prevline_right =  "\t.\t.\t.\tName=" + var.getName() +
						";ID=var_" +  var.getVarietyId() + "-" + snpvars.getPos() + 
						";AlleleRef=" +  snpvars.getRefnuc() + 
						";AlleleAlt=" + snpvars.getVarnuc() +					
						";Position=" +  snpvars.getPos() +
						";order=" + order +
						"\n";
				
				
				//buff.append("##gff-version 3\n#Note: See http://song.sourceforge.net\n\n");
			}
			
			java.util.List listGFF = new java.util.ArrayList();
			
			while(itsnp.hasNext()) {
				SnpsAllvars snpvars = itsnp.next();
				
				long longsnpvar = snpvars.getVar().longValueExact();
				long longsnppos = snpvars.getPos().longValueExact();
				
				if(longsnpvar==prevvar ) {
				
					if(mismatchOnly && snpvars.getRefnuc().equals(snpvars.getVarnuc())) continue;
					
					//Integer prevendpos =    (prevpos + snpvars.getPos().intValue())/2 - bpgap;
					long prevendpos =    (prevpos + longsnppos)/2 - bpgap;
					
					// for very short (adjacent positions)
					if(prevstartpos>longsnppos)
						prevstartpos=longsnppos;

					if(prevendpos>longsnppos)
						prevendpos=longsnppos;
					
					//buff.append(prevline_left);
					//buff.append(prevstartpos);buff.append("\t"); buff.append(prevendpos);
					//buff.append(prevline_right);
					
					//if(!snpvars.getRefnuc().equals(snpvars.getVarnuc()))
					
					if(!prevline_right.isEmpty())
						listGFF.add(new GFF(prevline_left, prevline_right,  prevstartpos, prevendpos));
					
					prevstartpos = prevendpos + 1;
					
					
				} else {
					// finish last prev

					// for very short (adjacent positions)
					if(prevstartpos>prevpos)
						prevstartpos=prevpos;

					
					//buff.append(prevline_left);
					//buff.append(prevstartpos);buff.append("\t"); buff.append(end);
					//buff.append(prevline_right);
					
					if(!prevline_right.isEmpty())
						listGFF.add(new GFF(prevline_left, prevline_right,  prevstartpos, intEnd));
					
					
					prevstartpos = intStart;
					
					
				}
				
				
				String chr = snpvars.getChr().toString(); 
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
				prevline_left = chr + "\tIRIC\tsnp\t"  ;
				prevpos = snpvars.getPos().intValue();
				prevvar = snpvars.getVar().intValueExact();
				String order =  var2order.get(snpvars.getVar() ).toString();

				Variety var=mapId2Variety.get( snpvars.getVar() );
				
				if(snpvars.getRefnuc().equals( snpvars.getVarnuc()   ))
					prevline_right="";
				else
					prevline_right =  "\t.\t.\t.\tName=" +  var.getName() +
						";ID=var_" +  var.getVarietyId() + "-" + snpvars.getPos() + 
						";AlleleRef=" +   snpvars.getRefnuc() + 
						";AlleleAlt=" + snpvars.getVarnuc() +
						";Position=" +  snpvars.getPos() +
						";order=" + order +
						"\n";	

			}

			// for very short (adjacent positions)
			if(prevstartpos>prevpos)
				prevstartpos=prevpos;

			
			//buff.append(prevline_left);
			//buff.append(prevstartpos);buff.append("\t"); buff.append(end);
			//buff.append(prevline_right);
			
			if(!prevline_right.isEmpty())
				listGFF.add(new GFF(prevline_left, prevline_right,  prevstartpos, intEnd));
			
			java.util.Collections.sort( listGFF, new GFFStartComparator());
			
			
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
			
			System.out.println("temgff written in: " + file.getAbsolutePath() );
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

				
				if(linecount > 100 ) {
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
			
			System.out.println("temgff written in: " + file.getAbsolutePath() );
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
			//displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%2232000%22";
			displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%222000%22";
		}
		else
			iframeJbrowse.setStyle("width:1500px;height:800px;border:0px inset;" );
		
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
				if(radioColorAllele.isSelected()) {
					rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2Variety%22";
				} else if (radioColorMismatch.isSelected() )
				{			
					 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatch%22";
				}
				
				String snp3kcore="";
				if(checkboxCoreSNP.isChecked()) snp3kcore="snp3kcore%2C";
				
				urljbrowse= AppContext.getHostname() + "/jbrowse-dev/?loc=chr"  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2Csnp3k%2C" + snp3kcore + "SNP%20Genotyping&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
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
		
		System.out.println(urljbrowse);
			
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
		
		System.out.println("urljbrowsephylo= " + urljbrowsephylo);
			
	}
	
	
	/**
	 * Prepare phylogenetic tree URL. Tree is not computed/displayed until the tab is clicked
	 * @param chr
	 * @param start
	 * @param end
	 */
	
	private void show_phylotree(String  chr, String start, String end) {

		
			int nvars = genotype.getListSNPAllVarsMismatches().size();
			int height = nvars*21;
			int width = nvars*30;
			
			int treescale = 1;
			
			iframePhylotree.setStyle("height:" + Integer.toString(height) + "px;width:1400px");
			iframePhylotree.setScrolling("yes");
			
			String topN="-1";
			if(!selectPhyloTopN.getSelectedItem().getLabel().equals("all"))
				topN=selectPhyloTopN.getSelectedItem().getLabel();
			
			urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end + "&topn=" + topN + "&tmpfile=" + gfffile.replace(".gff","");
			log.debug(urlphylo);
			System.out.println(urlphylo);
	}
	


}