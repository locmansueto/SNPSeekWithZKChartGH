package org.irri.iric.portal.genotype.zkui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 


















































import oracle.sql.DATE;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.domain.Gene;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.genotype.service.GenotypeFacadeImpl;
import org.irri.iric.portal.genotype.views.Snp2lines;
import org.irri.iric.portal.genotype.views.Snp2linesHome;
import org.irri.iric.portal.genotype.views.Snp2linesId;
import org.irri.iric.portal.genotype.views.ViewCountVarrefMismatchId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvars;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsId;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos;
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.ext.Selectable;
 

@Controller
public class SNPQueryController extends SelectorComposer<Component>  { //implements Initiator {
 
	private String urljbrowse,gfffile,urlphylo;
	//private String phylochr, phylostart, phyloend;
	private boolean tallJbrowse;
			
	private int snpallvars_pagesize=50;
	private String tmpfile_spath="";
	private Listheader lhr_variety;
	private Listheader lhrMismatch;
	//private String tempdir =  "../webapps/iric-portal/tmp/";

	
	private static final Log log = LogFactory.getLog(Snp2linesHome.class);
	
	private static final  String TEXTSTYLE_ERROR="font-color:red";
	private static final  String TEXTSTYLE_SUCCESS="font-folor:black";
	
    private static final long serialVersionUID = 1L;
    private int chrlength = 43270923; 
    
    @Wire
    private Combobox comboVar1;
    @Wire
    private Combobox comboVar2;
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
    private Checkbox checkboxAllvarieties;
    
    
    @Autowired
    private GenotypeFacade  genotype;  //=  // new org.irri.iric.portal.genotype.service.GenotypeFacadeImpl();
  
    @Wire
    private Iframe iframeJbrowse;
    @Wire
    private Label msgJbrowse;
   // @Wire
   // private Listbox snpallvarsresult;
    
    @Wire
    private Grid snpallvarsresult;
    
    @Wire
    private Label snpallvarsresultMsg;
    
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
    private Iframe iframePhylotree;
    
    @Wire
    private Tab tabJbrowse;
    @Wire
    private Tab tabPhylo;
    
    
    @Listen("onSelect = #selectChr")
    public void setchrlength() {
    	
    	//genotype = (GenotypeFacade)checkBean(genotype, "GenotypeFacade");
  
    	String name = "GenotypeFacade";
    	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");
    	
    	/*
    	if (genotype==null) {
    		log.debug(name + "==null using SpringUtil");
    		System.out.println("genotype==null using SpringUtil");
    		genotype = (GenotypeFacade)SpringUtil.getBean(name);
    	}
    	if (genotype==null) {
    		log.debug(name + "==null using static");
    		System.out.println("genotype==null using static");
    		genotype = (GenotypeFacade)AppContext.getApplicationContext().getBean(name);
    	}   	
    	if (genotype==null) {
    		System.out.println("APP ERROR: null genotype");
    		log.debug("APP ERROR: null genotype");
    		genotype= new GenotypeFacadeImpl();
    		
    	}
    	*/
    	
    	
    	Integer length = genotype.getFeatureLength(selectChr.getSelectedItem().getLabel());
    	labelLength.setValue("end: (length " + length + " bp)" );
    	chrlength=length.intValue();
    }

@Listen("onSelect = #tabJbrowse")    
public void onselectTabJbrowse() {

	if(urljbrowse!=null) {

		System.out.println("loading " + urljbrowse);
		
		if(tallJbrowse) {
			List newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel() , 1, 0  );
			createSNPVarietyGFF(newpagelist, gfffile,  genotype.getMapVariety2Order() , 1, intStart.getValue(), intStop.getValue() );
			AppContext.resetTimer("write gff");
			iframeJbrowse.setSrc( urljbrowse );
		}
		else 
			iframeJbrowse.setSrc( urljbrowse );
		
	
	    msgJbrowse.setVisible(true);
	    iframeJbrowse.setVisible(true);
	}
    urljbrowse=null;
		
}

@Listen("onSelect = #tabPhylo")    
public void onselectTabPhylo() {

	System.out.println("loading phylotree " + urlphylo);
	if(urlphylo!=null) {
		iframePhylotree.setSrc( urlphylo );
		iframePhylotree.setVisible(true);
	}
	urlphylo=null;
}
    

    
@Listen("onPaging = #snpallvarsresultpaging")    
public void onpageAllvarsresult(PagingEvent pe)
{
	
	//PagingEvent pe = (PagingEvent)e;
	int desiredPage = pe.getActivePage();
	
	List newpagelist  = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), selectChr.getSelectedItem().getLabel().trim(), desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize  );

	this.setAllvargridforDBPaging();
	
	//snpallvarsresult.getChildren().clear();
	//snpallvarsresult.setMold("default");
	//snpallvarsresult.setMold("paging");
	//snpallvarsresult.setSizedByContent(true);
	//npallvarsresult.setPageSize(snpallvars_pagesize);

    updateAllvarsList(newpagelist,false,null,null,null, desiredPage*snpallvars_pagesize+1 , snpallvars_pagesize );		
    
    System.out.println("paging from " + (desiredPage*snpallvars_pagesize+1) + "-" + (desiredPage*snpallvars_pagesize));
    
}

@Listen("onClick = #buttonDownloadTab")    
public void downloadTab()    {
	 downloadDelimited("snpfile.txt", "\t"); 
}

@Listen("onClick = #buttonDownloadCsv")    
public void downloadCsv()    {
	 downloadDelimited("snpfile.csv",","); 
}

private void downloadDelimited(String filename, String limitchar)    {


	searchWithServerpaging(filename, limitchar);
	
	/*
	try {
	Filedownload.save(  new File(filename), filename);
	} catch(Exception ex)
	{
		ex.printStackTrace();
	}
	*/
	
}


private void writeSNP2Lines2File(String filename, String header,  String var1, String var2, String delimiter, List<org.irri.iric.portal.genotype.views.Snp2linesId> listSNPs )
{
	
	Iterator<org.irri.iric.portal.genotype.views.Snp2linesId> it=listSNPs.iterator();
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
			org.irri.iric.portal.genotype.views.Snp2linesId snp2lines= it.next();
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
			Filedownload.save(  new File(filename), null);
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
		
	} catch(Exception ex) {
		ex.printStackTrace();
	}
}




@Listen("onClick = #searchButton")    
public void searchWithServerpaging()    {
	searchWithServerpaging(null,null);
}


private void setAllvargridforDBPaging() {
	snpallvarsresultpaging.setPageSize(snpallvars_pagesize);
	snpallvarsresultpaging.setMold("os");
	
	snpallvarsresultpaging.setVisible(true);
	
	snpallvarsresult.setPageSize(snpallvars_pagesize);
	snpallvarsresult.setMold("default");

	
}

private void setAllvargridforGridPaging() {
	//snpallvarsresult.setMold("select");

	snpallvarsresultpaging.setVisible(false);
	snpallvarsresult.setMold("paging");

	snpallvarsresult.setPageSize(snpallvars_pagesize);
	snpallvarsresult.setPagingPosition("both");
	
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
	
	gfffile=null;
	urljbrowse = null;	// if has URL address, will render on TabSelect on JBrowse
	urlphylo = null;   // if has URL address, will render on TabSelect on Phylotree
	tallJbrowse = false;	// jbrowse if tall (for all variety) or short (for 2 varieties)
	
	snpallvars_pagesize=100;	 // initial rows per page
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
	    
	}

		genotype =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");
	
			
		msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );

		
		// get selected qury mode (autodetect later)
		
		
		/*
		if (selectQueryMode.getSelectedIndex()==0)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
		else if  (selectQueryMode.getSelectedIndex()==1)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE;
		else if  (selectQueryMode.getSelectedIndex()==2)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		*/
		
		String var1= comboVar1.getValue().trim().toUpperCase();
		String var2 =  comboVar2.getValue().trim().toUpperCase();
		if(checkboxAllvarieties.isChecked()) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		} else {
			if(!var1.isEmpty() && !var2.isEmpty())
				mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
			else if(var1.isEmpty() && var2.isEmpty() )
			{
				Messagebox.show("At least one variety is required", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				return;	
			}
			else
				mode = GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE;
		}

		// initialize empty SNP list
		List listSNPs = new java.util.ArrayList();
		//List<Snp2lines> listSNPs = new java.util.ArrayList();
		//List<ViewSnpAllvars> listSNPs = new java.util.ArrayList();


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
			
			msgbox.setValue("Searching within GENE " + comboGene.getValue().toUpperCase()  + " " + gene2.getChr() + " [" + 
					gene2.getFmin() + ".." + gene2.getFmax() + "] ");

			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ) {
				
				Gene gene = genotype.getGeneFromName( genename );				
				log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
				
				
				//List<ViewCountVarrefMismatchId> listvarmismatches = genotype.countSNPMismatchesInAlllVarieties(Integer.valueOf(gene.getFmin().toPlainString())  ,Integer.valueOf( gene.getFmax().toPlainString() )
				//		, gene.getChr().toUpperCase().replace("CHR", ""), true);
				
				listSNPs = genotype.getSNPinAllVarieties(Integer.valueOf(gene.getFmin().toPlainString())  ,Integer.valueOf( gene.getFmax().toPlainString() ), 
						gene.getChr().toUpperCase().replace("CHR", ""));
				
				AppContext.resetTimer("gene allvars query");

				
				if(writetofile) {
					updateAllvarsList(listSNPs,true,filename,delimiter,"GENE: " + comboGene.getValue().trim().toUpperCase());
					AppContext.resetTimer("write4download");
					return;
				}
				
				//msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listvarmismatches.size() + " varieties" );
				//msgbox.setStyle( "font-color:black" );			
				msgbox.setValue(msgbox.getValue() + " ... RESULT: "  + genotype.getListSNPAllVarsMismatches().size() + " varieties x " + genotype.getSnpsposlist().size() + " positions" );
				
				setAllvargridforGridPaging();
				
				
				// layout in table
		        updateAllvarsList(listSNPs,true);
		        snpallvarsresult.setSizedByContent(true);
		        
		        AppContext.resetTimer("render");		        
		        
		        gene = genotype.getGeneFromName( comboGene.getValue().trim() );

		        int bpmargin = 1;
		        
		        createSNPVarietyGFF(listSNPs, gfffile, genotype.getMapVariety2Order(), bpmargin,Integer.parseInt(gene.getFmin().toString()),Integer.parseInt(gene.getFmax().toString()));
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
				updateJBrowse( gene2.getChr().toUpperCase().replace("CHR",""),
						gene2.getFmin().toString(), gene2.getFmax().toString() , genename2.toUpperCase());
				tabJbrowse.setVisible(true);
				
				if(tallJbrowse) {
					show_phylotree(gene2.getChr().toUpperCase().replace("CHR",""), gene2.getFmin().toString(), gene2.getFmax().toString() );
					tabPhylo.setVisible(true);
				}
			}
			else {
				tabJbrowse.setVisible(false);
				tabPhylo.setVisible(false);
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
			}
		
		} else
		{
			// SNPs on all varieties in region
			// vier chr pos region
		
			int chrlen = genotype.getFeatureLength( selectChr.getSelectedItem().getLabel());
	
			
			if(intStop==null || intStart==null)
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
			
			if( (intStop.getValue()-intStart.getValue()) > 50000  && mode == GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS )
			{
				Messagebox.show("Limit to 50 kbp range for all variety queries", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
				msgbox.setStyle( "font-color:red" );
			return;  					
			}  
			
			
			String chr = selectChr.getSelectedItem().getLabel().trim();
			if(chr.isEmpty())
			{
				Messagebox.show("No chromosome selected", "INVALID CHROMOSOME VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				msgbox.setValue("INVALID CHROMOSOME VALUE: no chromosome selected" );
				return;
			}
			msgbox.setValue("SEARCHING: in chromosome " + chr + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
			msgbox.setStyle( "font-color:black" );
			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){

				List<ViewCountVarrefMismatchId> listvarmismatches = genotype.countSNPMismatchesInAlllVarieties(intStart.getValue(), intStop.getValue(), chr, true);

				List  newpagelist; // = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, 0  );
				
				if(writetofile) {
					newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, 0  );
			        updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() );						

			        AppContext.resetTimer("query allvars region " + (intStop.getValue() - intStart.getValue()) + " bp");	
					return;
				}
				
				//createSNPVarietyGFF(newpagelist, gfffile,  genotype.getMapVariety2Order() , 1, intStart.getValue(), intStop.getValue() );
				//AppContext.resetTimer("write gff");
				
				//listSNPs = newpagelist;
				
				boolean serverpaging = false;
				
				
				if( intStop.getValue()- intStart.getValue()>7000)
				{
					serverpaging=true;
					snpallvars_pagesize=50;
				}
				if( intStop.getValue()- intStart.getValue()>15000)
				{
					serverpaging=true;
					snpallvars_pagesize=25;
				}
				if( intStop.getValue()- intStart.getValue()>50000)
				{
					serverpaging=true;
					snpallvars_pagesize=10;
				}
				if( intStop.getValue()- intStart.getValue()>75000)
				{
					serverpaging=true;
					snpallvars_pagesize=5;
				}
				

				snpallvarsresult.setAttribute("org.zkoss.zul.grid.rod", true);
				
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
					System.out.println("no server paging");
					
					newpagelist = genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr, 1, 0  );
					
					AppContext.resetTimer("query allvars from DB " +snpallvars_pagesize + " rows" );

					setAllvargridforGridPaging();
							
					updateAllvarsList(newpagelist,true, null,null,null, 1, 0 );	
				}
				
				listSNPs = newpagelist;
				
				msgbox.setValue(msgbox.getValue() + " ... RESULT: " + listvarmismatches.size() + " varieties x " + genotype.getSnpsposlist().size() + " positions" );

		        AppContext.resetTimer("create table" );

		        tallJbrowse = true;
				
				//listSNPs =  genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr);
				//msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listvarmismatches.size() + " varieties" );
				//msgbox.setStyle( "font-color=black" );
			}
			else {	
				// SNPs on 2 varieties in region
				listSNPs =  genotype.getSNPinVarieties(var1,var2, intStart.getValue(), intStop.getValue(), chr, mode);
				
				if(writetofile) {
					writeSNP2Lines2File(filename, "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() ,   var1,  var2,  delimiter,  listSNPs);
					return;
				}
				
				createSNP2linesGFF(listSNPs, gfffile );
				
				//msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " position rows" );
				//msgbox.setStyle( "font-color:black" );
				
				msgbox.setValue( msgbox.getValue() + " ... RESULT: " +  listSNPs.size() + " positions" );
				
				tallJbrowse= false;
				
			}

			
			if(listSNPs.size()>0)
			{
				updateJBrowse( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), "");
				tabJbrowse.setVisible(true);
				
				if(tallJbrowse) {
					show_phylotree(chr.toUpperCase().replace("CHR0","").replace("CHR0",""), intStart.getValue().toString() , intStop.getValue().toString()  );
					tabPhylo.setVisible(true);
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
			snpallvarsresultMsg.setVisible(false);
			
			// update table header
			Listheader lhr = (Listheader)snpresult.getListhead().getFirstChild();    	
			String[] headers = new String[]{"CHROMOSOME","POSITION", "NIPPONBARE", comboVar1.getValue().toUpperCase() ,comboVar2.getValue().toUpperCase()};
			int i = 0;
			while(lhr != null) {
				lhr.setLabel(headers[i] );
				lhr = (Listheader)lhr.getNextSibling();
				i++;
			}
			((SNPListItemRenderer)snpresult.getItemRenderer()).setQuerymode(mode);
			
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
 * Write all varities to file, with delimeter and header (first line)
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
 * Render (filename==null) of write to file varities firstrow to (firstrow-numRows-1)
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
 */
private void updateAllvarsList(List listSNPs, boolean updateHeaders, String filename, String delimiter, String header, int firstRow, int numRows)
{
	
	updateHeaders=false;	// set false for now because rendering is slow with header!
	//snpallvarsresult.setSizedByContent(true);
	
	try {
	
	boolean write2file = filename!=null && !filename.isEmpty();
	
	
	List<ViewSnpAllvarsPosId> snpsposlist = genotype.getSnpsposlist();
	
	StringBuffer buff=new StringBuffer();
	
	if(write2file) {
		
		buff.append(header ); buff.append("\n");
		buff.append("VARIETY"); buff.append(delimiter);  buff.append("MISMATCH");  buff.append(delimiter);
		
		Iterator<ViewSnpAllvarsPosId> itPos = snpsposlist.iterator();
		
		StringBuffer buffRefnuc = new StringBuffer();
		buffRefnuc.append("REFERENCE");  buffRefnuc.append(delimiter);  buffRefnuc.append(delimiter);
		
		while(itPos.hasNext()) {
			ViewSnpAllvarsPosId posnuc=itPos.next();
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
	Map<Long,Integer> mapPos2Index = new java.util.HashMap<Long,Integer>();

	
	// map reference base/allele to column index  
	Map mapIdx2Refnuc = new java.util.HashMap();
		
	if(updateHeaders) {


		snpallvarsresult.setSizedByContent(false);
		/*
    	Auxhead head = new Auxhead();
    	Auxheader header1 = new Auxheader("REFERENCE");
    	header1.setParent(head);
    	Auxheader header2 = new Auxheader("");
    	header2.setParent(head);
		*/
		//snpallvarsresult

		Columns cols = new Columns();
		Column col1 = new Column("VARIETY");
		col1.setParent(cols);
		
		Column col2 = new Column( "MISMATCH");
		col2.setParent(cols);
		

		Iterator<ViewSnpAllvarsPosId> itPos = snpsposlist.iterator();
		
		int posindex = 2;
		while(itPos.hasNext()) {
			ViewSnpAllvarsPosId posnuc=itPos.next();
				mapPos2Index.put(posnuc.getPos() , Integer.valueOf(posindex));
				Column col = new Column( posnuc.getPos().toString() );
				col.setParent(cols);
				//headerpos.add( posnuc.getPos().toString() );
				//headerrefnuc.add(posnuc.getPos().toString());
				
		//		Auxheader header3 = new Auxheader( posnuc.getRefnuc().toString());
		//		header3.setTooltip(posnuc.getRefnuc().toString());
		//   	header3.setParent(head);
				
		    	mapIdx2Refnuc.put(posindex , posnuc.getRefnuc());
		    	
				posindex++;
				
		}
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
	
	//	head.setParent(snpallvarsresult);
		
	//	snpallvarsresult.setSizedByContent(true);
		
		

				
	}
	else
	{
		
		Iterator<ViewSnpAllvarsPosId> itPos = snpsposlist.iterator();
		
		int posindex = 2;
		while(itPos.hasNext()) {
			ViewSnpAllvarsPosId posnuc=itPos.next();
				mapPos2Index.put(posnuc.getPos() , Integer.valueOf(posindex));
				mapIdx2Refnuc.put(posindex , posnuc.getRefnuc());
				posindex++;
		}
	}

 
		// transform into 1 pos per item
		

		if(genotype==null) throw new RuntimeException("genotype==null");
		if(genotype.getMapVariety2Mismatch()==null) throw new RuntimeException("genotype.getMapVariety2Mismatch()==null");
		if(genotype.getMapVariety2Order()==null) throw new RuntimeException("genotype.getMapVariety2Order()==null");

		Map<String,Integer> mapVariety2Order = genotype.getMapVariety2Order();
		
		// set number of rows in table
		
		int nrows = mapVariety2Order.size();	// number of varieties	
		if(numRows>0) 
		{
			nrows=numRows;	// specified  numRows/page
		} else if (numRows==0)
		{
			nrows= nrows-firstRow+1;	// firstRow to end
		}
		
		String[][] viewtable = new String[nrows][snpsposlist.size()+2];
		
		// always true! if(firstRow>-1) 
		//{
			// get vars from firstRow to nrows
			Iterator<ViewCountVarrefMismatchId> itVarsmismatch  = genotype.getListSNPAllVarsMismatches(firstRow, nrows).iterator();
		
			
			int i=0;
			while(itVarsmismatch.hasNext()) {
				ViewCountVarrefMismatchId var = itVarsmismatch.next();
				viewtable[i][0] = var.getVarname();
				viewtable[i][1] = var.getMismatch().toString();
				i++;
			}
			
			System.out.println( i + " varieties added to table");
		//}
		System.out.println(firstRow+ " firstRow");
		System.out.println(nrows+ " nrows");
		System.out.println(numRows+ " numRows");
		
		
		Iterator<ViewSnpAllvarsId> itSnps = listSNPs.iterator();
		while(itSnps.hasNext())
		{
			ViewSnpAllvarsId snps = itSnps.next();
			if(snps.getVar1nuc()!=null && !snps.getVar1nuc().isEmpty() )
				viewtable[mapVariety2Order.get( snps.getVarname() )- (firstRow-1)  ][  mapPos2Index.get(snps.getPos()) ] =   String.valueOf( snps.getVar1nuc().charAt(0) );
			else
				viewtable[mapVariety2Order.get( snps.getVarname() )- (firstRow-1)  ][  mapPos2Index.get(snps.getPos()) ] =   "";
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
					Filedownload.save(  new File(filename), null);
					} catch(Exception ex)
					{
						ex.printStackTrace();
					}
				

			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
			
			return;
			
			
		}
				
	
        ((SNPAllvarsRowRenderer)snpallvarsresult.getRowRenderer()).setMapIdx2Refnuc(mapIdx2Refnuc);  
				        
        snpallvarsresult.setAttribute("org.zkoss.zul.grid.rod", true);        
		
		snpallvarsresult.setModel(   new SimpleListModel(viewtable) );
		
		snpallvarsresult.setSizedByContent(true);
		
		//snpallvarsresultMsg.setValue("");
		snpallvarsresultMsg.setVisible(true);
		snpallvarsresult.setVisible(true);
		
		
        
	} catch(Exception ex) {
		ex.printStackTrace();
	}
        
        
}



/*
private void updateAllvarsListOld(List listSNPs, boolean updateHeaders, String filename, String delimiter, String header)
{
	
	boolean write2file = filename!=null && !filename.isEmpty();
	
	long startQuery = System.currentTimeMillis( );
	
	List<ViewSnpAllvarsPosId> snpsposlist = genotype.getSnpsposlist();
	
	StringBuffer buff=new StringBuffer();
	
	if(write2file) {
		
		buff.append(header ); buff.append("\n");
		buff.append("VARIETY"); buff.append(delimiter);  buff.append("MISMATCH");  buff.append(delimiter);
		
//		java.util.Map<Long, Character> mapRefPos2Nuc = new java.util.HashMap<Long, Character>();
		Iterator<ViewSnpAllvarsPosId> itPos = snpsposlist.iterator();
		
		StringBuffer buffRefnuc = new StringBuffer();
		buffRefnuc.append("REFERENCE");  buffRefnuc.append(delimiter);  buffRefnuc.append(delimiter);
		
		while(itPos.hasNext()) {
			ViewSnpAllvarsPosId posnuc=itPos.next();
			buff.append( posnuc.getPos());
			buffRefnuc.append( posnuc.getRefnuc() );
			
			if(itPos.hasNext()){
				buff.append(delimiter);
				buffRefnuc.append(delimiter);
			}
//			mapRefPos2Nuc.put(posnuc.getPos() , posnuc.getRefnuc() );
		}
		buff.append("\n");
		buffRefnuc.append("\n");
		buff.append( buffRefnuc );
		
	}
	else 
	{
		Listheader lhr;
		Listhead lh=null ;
		Auxheader listheader1;
		
		if(updateHeaders) {
	
			
			lhr_variety =  new Listheader();
			
			lh = snpallvarsresult.getListhead();
			lh.getChildren().clear();
		     
			
			
			lhr_variety.setLabel("VARIETY");
	//		lhr_variety.setSortAscending(  new SNPAllvarsListItemComparator(true, 1) );
	//		lhr_variety.setSortDescending(  new SNPAllvarsListItemComparator(false, 1) );
			lh.appendChild(lhr_variety);
		
			lhrMismatch =  new Listheader();
			lhrMismatch.setLabel("MISMATCH");
	//		lhrMismatch.setSortAscending(new SNPAllvarsListItemComparator(true, 2));
	//		lhrMismatch.setSortDescending(new SNPAllvarsListItemComparator(false, 2));
		
			
			//lhr.setSort("auto");
			lh.appendChild(lhrMismatch);
		
			auxheadpos.getChildren().clear();
			
			listheader1=new Auxheader(); 
			listheader1.setLabel("REFERENCE"); 
			//listheader1.setParent(listhead1); 
			auxheadpos.appendChild( listheader1 );			
			listheader1=new Auxheader(); 
			listheader1.setLabel(""); 
			//listheader1.setParent(listhead1); 
			auxheadpos.appendChild( listheader1 );			
		}
		
		java.util.Map<Long, Character> mapRefPos2Nuc = new java.util.HashMap<Long, Character>();
		Iterator<ViewSnpAllvarsPosId> itPos = snpsposlist.iterator();
		while(itPos.hasNext()) {
			ViewSnpAllvarsPosId posnuc=itPos.next();
			mapRefPos2Nuc.put(posnuc.getPos() , posnuc.getRefnuc() );
			
			if(updateHeaders) {
				
				lhr =  new Listheader();
				//lhr.get
				
				//lhr.setSclass("word-wrap");
				lhr.setLabel(posnuc.getPos().toString());
				
				//lhr.setLabel("<html>"+ posnuc.getPos() + "<BR>" + posnuc.getRefnuc() + "</html>" );
				lh.appendChild(lhr);
				
				listheader1=new Auxheader(); 
				listheader1.setLabel(posnuc.getRefnuc().toString()); 
				//listheader1.setParent(listhead1); 
				auxheadpos.appendChild( listheader1 );
			}
		}
	
	}
	
	

    long endQuery = System.currentTimeMillis( );
    System.out.println("query and setup positions: " +  Long.toString(endQuery-startQuery) + " ms"); 
    startQuery=endQuery;
    
    
		// transform into 1 pos per item
		
		Iterator<ViewSnpAllvarsId> itSnps = listSNPs.iterator();
		java.util.Map<Long, Character> mapPos2Nuc = new java.util.HashMap<Long, Character>() ; 
		
		String prevvar = "";
//		List<SNPAllvarsListItem> viewerlist = new java.util.ArrayList<SNPAllvarsListItem>();
//		List<String> varlist= new java.util.ArrayList<String>();
//		List<Long> mismatchlist= new java.util.ArrayList<Long>();
		
		
		//long mismatchcount = 0;
		if(itSnps.hasNext()) {
			ViewSnpAllvarsId snps = itSnps.next();
			mapPos2Nuc = new java.util.HashMap<Long, Character>() ; 
			mapPos2Nuc.put( snps.getPos() , snps.getVar1nuc().charAt(0));
			prevvar=snps.getVarname();
			
			//if ( !snps.getVar1nuc().isEmpty() &&  !mapRefPos2Nuc.get(snps.getPos()).equals( snps.getVar1nuc().charAt(0)))  mismatchcount++;
			
		}			
		
		java.util.Map<Integer,SNPAllvarsListItem> mapOrder2SNPAllvarsListItem = new java.util.TreeMap<Integer, SNPAllvarsListItem>();

		if(genotype==null) throw new RuntimeException("genotype==null");
		if(genotype.getMapVariety2Mismatch()==null) throw new RuntimeException("genotype.getMapVariety2Mismatch()==null");
		if(genotype.getMapVariety2Order()==null) throw new RuntimeException("genotype.getMapVariety2Order()==null");

		
		while(itSnps.hasNext())
		{
			ViewSnpAllvarsId snps = itSnps.next();
			if(prevvar.equals(snps.getVarname()))
					{
						// add pos
						mapPos2Nuc.put( snps.getPos() , snps.getVar1nuc().charAt(0));
						//if ( !snps.getVar1nuc().isEmpty() &&  !mapRefPos2Nuc.get(snps.getPos()).equals( snps.getVar1nuc().charAt(0)))  mismatchcount++;

					} else
					{
						// new row, new var
						// add map to list
						
						//mapPos2Nuc.put( 0,  varlist.size() + 1);
						
						if(genotype.getMapVariety2Mismatch().get( prevvar ) ==null) throw new RuntimeException("genotype.getMapVariety2Mismatch()get(" + prevvar + ") ==null");

						
						SNPAllvarsListItem newvar = new SNPAllvarsListItem();
						newvar.setMismatches( new Long( genotype.getMapVariety2Mismatch().get( prevvar ))  );
						newvar.setPos2nuc( mapPos2Nuc );
						newvar.setVarname(prevvar);
						
						mapOrder2SNPAllvarsListItem.put( genotype.getMapVariety2Order().get( prevvar )  ,newvar);
						
						//viewerlist.add( newvar );
						
						// create new row
						
						mapPos2Nuc = new java.util.HashMap<Long, Character>() ; 
						mapPos2Nuc.put( snps.getPos() , snps.getVar1nuc().charAt(0));
						
//						varlist.add(prevvar);		
//						mismatchlist.add( mismatchcount );
						
//						mismatchcount=0;
						prevvar=snps.getVarname();
					}					
		}
//		varlist.add(prevvar);
//		mismatchlist.add( mismatchcount );
//		viewerlist.add( mapPos2Nuc );
		
		SNPAllvarsListItem newvar = new SNPAllvarsListItem();
		newvar.setMismatches( (long)genotype.getMapVariety2Mismatch().get( prevvar )  );
		newvar.setPos2nuc( mapPos2Nuc );
		newvar.setVarname(prevvar);
		mapOrder2SNPAllvarsListItem.put( genotype.getMapVariety2Order().get( prevvar )  ,newvar);

		
		List<SNPAllvarsListItem> viewerlist = new java.util.ArrayList<SNPAllvarsListItem>();
		Iterator itOrders = mapOrder2SNPAllvarsListItem.keySet().iterator();
		while(itOrders.hasNext())
		{
			viewerlist.add(  mapOrder2SNPAllvarsListItem.get( itOrders.next()) );
		}
		
		
		if(write2file) {
			
	
			try {
			
				File file = new File(filename);
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				long linecount = 0;
				
				Iterator<SNPAllvarsListItem> it = viewerlist.iterator();
				while(it.hasNext()) {
					SNPAllvarsListItem item = it.next();
					buff.append( item.getVarname() ); buff.append( delimiter );
					buff.append( item.getMismatches() );  buff.append( delimiter );
					
					
					Map<Long, Character> mappos2nuc = item.getPos2nuc();
					for(int col=0; col<  snpsposlist.size(); col++ ){
						Long pos = snpsposlist.get(col).getPos();
						if(mappos2nuc.containsKey(pos ))
						{
							buff.append(mappos2nuc.get(pos) );
						}
						if(col+1< snpsposlist.size() ) buff.append( delimiter );
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
					Filedownload.save(  new File(filename), filename);
					} catch(Exception ex)
					{
						ex.printStackTrace();
					}
				

			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		
			return;
		}
		
		
        endQuery = System.currentTimeMillis( );
        System.out.println("counting mismatches, creating viewer list: " +  Long.toString(endQuery-startQuery) + " ms"); 
        startQuery=endQuery;
        

		((SNPAllvarsListItemRenderer)snpallvarsresult.getItemRenderer()).setArrRefnucPos( snpsposlist );

		//			((SNPAllvarsListItemRenderer)snpallvarsresult.getItemRenderer()).setVarlist(varlist );
		//((SNPAllvarsListItemRenderer)snpallvarsresult.getItemRenderer()).setMismatchlist(mismatchlist );
			
		((SNPAllvarsListItemRenderer)snpallvarsresult.getItemRenderer()).setSNPAllvarsListItemList(viewerlist);
	
		snpallvarsresult.setSizedByContent(true);

		snpallvarsresult.setAttribute("org.zkoss.zul.listbox.rod", true);
		
		snpallvarsresult.setModel(   new SimpleListModel(viewerlist) );
		
	
		//System.out.println("paging: total size" + snpallvarsresult.getPagingChild().getTotalSize() + 
		//		" activepage:" +  snpallvarsresult.getPagingChild().getActivePage());
		
		
		
		
		snpallvarsresultMsg.setValue("");
		snpallvarsresultMsg.setVisible(true);
		snpallvarsresult.setVisible(true);

//		lhrMismatch.setSortDirection("descending");
//		lhrMismatch.sort(false, true);
		
        endQuery = System.currentTimeMillis( );
        System.out.println("rendering: " +  Long.toString(endQuery-startQuery) + " ms"); 
        startQuery=endQuery; 
   
        
        
}
*/

/*

public void search()    {
	
	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");

	
		//	List<Snp2lines>  listSNP= new java.util.ArrayList();
			
		msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );

		
		GenotypeFacade.snpQueryMode mode=null;
		if (selectQueryMode.getSelectedIndex()==0)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
		else if  (selectQueryMode.getSelectedIndex()==1)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE;
		else if  (selectQueryMode.getSelectedIndex()==2)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		

		
		List listSNPs = new java.util.ArrayList();
		//List<Snp2lines> listSNPs = new java.util.ArrayList();
		//List<ViewSnpAllvars> listSNPs = new java.util.ArrayList();

		String var1= comboVar1.getValue().trim().toUpperCase();
		String var2 =  comboVar2.getValue().trim().toUpperCase();
		String genename = comboGene.getValue().trim().toUpperCase();
		
		
		
		if( (var1.isEmpty() ||  var2.isEmpty()  ) && mode != GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS )
		{
			Messagebox.show("Both varieties are required", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		

        long startQuery = System.currentTimeMillis( );					
					
		if( !genename.isEmpty() )
		{
			String genename2 = comboGene.getValue().trim();
			Gene gene2 =  genotype.getGeneFromName( genename2);
			
			msgbox.setValue("Searching within GENE " + comboGene.getValue().toUpperCase()  + " " + gene2.getChr() + " [" + 
					gene2.getFmin() + ".." + gene2.getFmax() + "] ");
			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ) {
				listSNPs = genotype.getSNPinAllVarieties( comboGene.getValue().trim(), 0);
				msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " varietyXposition rows" );
				msgbox.setStyle( "font-color=black" );			
			}
			else
			{
				listSNPs = genotype.getSNPinVarieties(var1, var2, comboGene.getValue().trim(), 0, mode);
				msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " position rows" );
				msgbox.setStyle( "font-color=black" );
			}
		
			if(listSNPs.size()>0)
			{
				updateJBrowse( gene2.getChr().toUpperCase().replace("CHR",""),
						gene2.getFmin().toString(), gene2.getFmax().toString() ,
					 genename2, null,false);
			}
			else {
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
			}
		
		} else
		{
		
			int chrlen = genotype.getFeatureLength( selectChr.getSelectedItem().getLabel());
	
			
			if(intStop==null || intStart==null)
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
				msgbox.setStyle( "font-color=red" );
			return;  					
			}  
			if(  mode == GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS  && ((intStop.getValue()-intStart.getValue()) > 100000 ) )
			{
				Messagebox.show("Limit to 100 kbp range for all variety query", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
				msgbox.setStyle( "font-color=red" );
			return;  					
			}  
			
			String chr = selectChr.getSelectedItem().getLabel().trim();
			if(chr.isEmpty())
			{
				Messagebox.show("No chromosome selected", "INVALID CHROMOSOME VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				msgbox.setValue("INVALID CHROMOSOME VALUE: no chromosome selected" );
				return;
			}
			msgbox.setValue("SEARCHING: in chromosome " + chr + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
			msgbox.setStyle( "font-color:black" );
			
			if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){
				
				listSNPs =  genotype.getSNPinAllVarieties(intStart.getValue(), intStop.getValue(), chr);
				msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " varietyXposition rows" );
				msgbox.setStyle( "font-color=black" );
			}
			else {
				listSNPs =  genotype.getSNPinVarieties(var1,var2, intStart.getValue(), intStop.getValue(), chr, mode);
				msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " position rows" );
				msgbox.setStyle( "font-color=black" );
			}
			if(listSNPs.size()>0)
				updateJBrowse( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString(), null, null,false);
			else {
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
			}
		}
		
		
        long endQuery = System.currentTimeMillis( );
        System.out.println("query snps: " +  Long.toString(endQuery-startQuery) + " ms"); 
        startQuery=endQuery;
		
		
		
		if(mode==GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS) {
	

	        
			snpresult.setModel( new SimpleListModel(new java.util.ArrayList()) );
			snpresult.setVisible(false);
			
		//	snpallvarsresult.setModel(model);
			List snpsposlist = genotype.getSnpsposlist();
			//listSNPs
			
			//snpallvarsresult.getChildren().clear();
			snpallvarsresult.setMold("select");
			snpallvarsresult.setMold("paging");
			snpallvarsresult.setSizedByContent(true);
			snpallvarsresult.setPageSize(snpallvars_pagesize);
			
	
			Listhead lh = snpallvarsresult.getListhead();
			lh.getChildren().clear();
			
			
			//Auxhead listhead1=new Auxhead(); 			
			//listhead1.setParent(snpallvarsresult); 
			
			
			//snpallvarsresult.getFrozen().setRows(1);
			//snpallvarsresult.getFrozen().setStart(1);
		
			//snpsposlist.	

	        
	   
	         
			
			Listheader lhr =  new Listheader();
			lhr.setLabel("VARIETY");
			lhr.setSortAscending(  new SNPAllvarsListItemComparator(true, 1) );
			lhr.setSortDescending(  new SNPAllvarsListItemComparator(false, 1) );
			lh.appendChild(lhr);

			Listheader lhrMismatch =  new Listheader();
			lhrMismatch.setLabel("MISMATCH");
			lhrMismatch.setSortAscending(new SNPAllvarsListItemComparator(true, 2));
			lhrMismatch.setSortDescending(new SNPAllvarsListItemComparator(false, 2));

			
			//lhr.setSort("auto");
			lh.appendChild(lhrMismatch);

			auxheadpos.getChildren().clear();
			
			Auxheader listheader1=new Auxheader(); 
			listheader1.setLabel("REFERENCE"); 
			//listheader1.setParent(listhead1); 
			auxheadpos.appendChild( listheader1 );			
			listheader1=new Auxheader(); 
			listheader1.setLabel(""); 
			//listheader1.setParent(listhead1); 
			auxheadpos.appendChild( listheader1 );			
			
			java.util.Map<Long, Character> mapRefPos2Nuc = new java.util.HashMap<Long, Character>();
			Iterator<ViewSnpAllvarsPosId> itPos = snpsposlist.iterator();
			while(itPos.hasNext()) {
				lhr =  new Listheader();
				ViewSnpAllvarsPosId posnuc=itPos.next();
				
				//lhr.get
				
				//lhr.setSclass("word-wrap");
				lhr.setLabel(posnuc.getPos().toString());
				
				//lhr.setLabel("<html>"+ posnuc.getPos() + "<BR>" + posnuc.getRefnuc() + "</html>" );
				lh.appendChild(lhr);
				
				listheader1=new Auxheader(); 
				listheader1.setLabel(posnuc.getRefnuc().toString()); 
				//listheader1.setParent(listhead1); 
				auxheadpos.appendChild( listheader1 );
				
				mapRefPos2Nuc.put(posnuc.getPos() , posnuc.getRefnuc() );
			}
			
			

	        endQuery = System.currentTimeMillis( );
	        System.out.println("query and setup positions: " +  Long.toString(endQuery-startQuery) + " ms"); 
	        startQuery=endQuery;
	        
	        
			// transform into 1 pos per item
			
			Iterator<ViewSnpAllvarsId> itSnps = listSNPs.iterator();
			java.util.Map<Long, Character> mapPos2Nuc = new java.util.HashMap<Long, Character>() ; 
			
			String prevvar = "";
			List<SNPAllvarsListItem> viewerlist = new java.util.ArrayList<SNPAllvarsListItem>();
//			List<String> varlist= new java.util.ArrayList<String>();
//			List<Long> mismatchlist= new java.util.ArrayList<Long>();
			
			
			long mismatchcount = 0;
			if(itSnps.hasNext()) {
				ViewSnpAllvarsId snps = itSnps.next();
				mapPos2Nuc = new java.util.HashMap<Long, Character>() ; 
				mapPos2Nuc.put( snps.getPos() , snps.getVar1nuc().charAt(0));
				prevvar=snps.getVarname();
				
				if ( !snps.getVar1nuc().isEmpty() &&  !mapRefPos2Nuc.get(snps.getPos()).equals( snps.getVar1nuc().charAt(0)))  mismatchcount++;
				
			}			
			
			
			while(itSnps.hasNext())
			{
				ViewSnpAllvarsId snps = itSnps.next();
				if(prevvar.equals(snps.getVarname()))
						{
							// add pos
							mapPos2Nuc.put( snps.getPos() , snps.getVar1nuc().charAt(0));
							if ( !snps.getVar1nuc().isEmpty() &&  !mapRefPos2Nuc.get(snps.getPos()).equals( snps.getVar1nuc().charAt(0)))  mismatchcount++;

						} else
						{
							// new row, new var
							// add map to list
							
							//mapPos2Nuc.put( 0,  varlist.size() + 1);
							
							SNPAllvarsListItem newvar = new SNPAllvarsListItem();
							newvar.setMismatches( mismatchcount );
							newvar.setPos2nuc( mapPos2Nuc );
							newvar.setVarname(prevvar);
							
							viewerlist.add( newvar );
							
							// create new row
							
							
							
							mapPos2Nuc = new java.util.HashMap<Long, Character>() ; 
							mapPos2Nuc.put( snps.getPos() , snps.getVar1nuc().charAt(0));
							
//							varlist.add(prevvar);		
//							mismatchlist.add( mismatchcount );
							
							mismatchcount=0;
							prevvar=snps.getVarname();
						}					
			}
//			varlist.add(prevvar);
//			mismatchlist.add( mismatchcount );
//			viewerlist.add( mapPos2Nuc );
			
			SNPAllvarsListItem newvar = new SNPAllvarsListItem();
			newvar.setMismatches( mismatchcount );
			newvar.setPos2nuc( mapPos2Nuc );
			newvar.setVarname(prevvar);
			viewerlist.add(newvar);
			
			
	        endQuery = System.currentTimeMillis( );
	        System.out.println("counting mismatches, creating viewer list: " +  Long.toString(endQuery-startQuery) + " ms"); 
	        startQuery=endQuery;
	        

			((SNPAllvarsListItemRenderer)snpallvarsresult.getItemRenderer()).setArrRefnucPos( snpsposlist );

			((SNPAllvarsListItemRenderer)snpallvarsresult.getItemRenderer()).setSNPAllvarsListItemList(viewerlist);
		

			snpallvarsresult.setAttribute("org.zkoss.zul.listbox.rod", true);
			
			snpallvarsresult.setModel(   new SimpleListModel(viewerlist) );
			
		
			System.out.println("paging: total size" + snpallvarsresult.getPagingChild().getTotalSize() + 
					" activepage:" +  snpallvarsresult.getPagingChild().getActivePage());
			
			
			
			
			snpallvarsresultMsg.setValue("Click VARIETY or MISMATCH headers to sort");
			snpallvarsresultMsg.setVisible(true);
			snpallvarsresult.setVisible(true);

			lhrMismatch.setSortDirection("descending");
			lhrMismatch.sort(false, true);
			
	        endQuery = System.currentTimeMillis( );
	        System.out.println("rendering: " +  Long.toString(endQuery-startQuery) + " ms"); 
	        startQuery=endQuery;

		}
		else {
			snpallvarsresult.setModel(   new SimpleListModel(new java.util.ArrayList()) );
			snpallvarsresult.setVisible(false);
			snpallvarsresultMsg.setVisible(false);
			
			// update table header
			Listheader lhr = (Listheader)snpresult.getListhead().getFirstChild();    	
			String[] headers = new String[]{"CHROMOSOME","POSITION", "REFERENCE (NIPPONBARE)",
			"ALLELE (" + comboVar1.getValue().toUpperCase() + ")","ALLELE (" + comboVar2.getValue().toUpperCase() + ")"};
			int i = 0;
			while(lhr != null) {
				lhr.setLabel(headers[i] );
				lhr = (Listheader)lhr.getNextSibling();
				i++;
			}
			((SNPListItemRenderer)snpresult.getItemRenderer()).setQuerymode( (short)selectQueryMode.getSelectedIndex()  );
			
			snpresult.setModel( new SimpleListModel(listSNPs) );
			snpresult.setVisible(true);
			
	        endQuery = System.currentTimeMillis( );
	        System.out.println("rendering: " +  Long.toString(endQuery-startQuery) + " ms"); 
	        startQuery=endQuery;
		}

}
*/
/*
private void createSNPGFF(List listSNPs, String filename) {
	
	//if(!checkShowsnp.isChecked()) return; 
	
	StringBuffer buff = new StringBuffer();
	
	try {
		File file = new File(tempdir + filename);
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		int linecount = 0;
		Iterator<ViewSnpAllvarsId> itsnp = listSNPs.iterator();
		while(itsnp.hasNext()) {
			ViewSnpAllvarsId snpvars = itsnp.next();
			
			if(snpvars.getRefnuc().equals(snpvars.getVar1nuc())) continue;
			
			String chr = snpvars.getChr().toString(); 
			if(chr.length()==1)
				chr= "chr0" + chr + "|msu7";
			else 
				chr= "chr" + chr + "|msu7";
			
			buff.append(chr); buff.append("\tIRIC\tsnp\t");
			buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
			buff.append( "\t.\t.\t.\tName=" + snpvars.getVarname() +
					";ID=" +  snpvars.getVarname() + "-" + snpvars.getPos() + 
					";Allele=" +   snpvars.getRefnuc() + "/" + snpvars.getVar1nuc() +
					";Position=" +  snpvars.getPos() +
					"\n");

			
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
	 private class GFFStartComparatator implements  Comparator {

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
	
	private void createSNPVarietyGFF(List listSNPs, String filename, Map var2order, int bpgap, int start, int end) {
		
		//if(!checkShowsnp.isChecked()) return;
		// sort snps by start
		
		//Set setSNP = new java.util.TreeSet<ViewSnpAllvarsId>(listSNPs);
		
		//java.util.Collections.sort(listSNPs, new ViewSnpAllvarsIdStartComparator());
		
		
		//StringBuffer buff = new StringBuffer();
		
		try {
		//	File file = new File(tempdir + filename);
		//	
		//	FileWriter fw = new FileWriter(file.getAbsoluteFile());
		//	BufferedWriter bw = new BufferedWriter(fw);
			
		//	int linecount = 0;
			Iterator<ViewSnpAllvarsId> itsnp = listSNPs.iterator();
		
			long prevpos = 0;
			long prevstartpos = 0;
			String prevline_left="";
			String prevline_right="";
			String prevvar = "";
			
			if(itsnp.hasNext())
			{
				
				ViewSnpAllvarsId snpvars = itsnp.next();
				String chr = snpvars.getChr().toString(); 
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
				prevline_left = chr + "\tIRIC\tsnp\t"  ;
				prevpos = snpvars.getPos();
				prevstartpos = start;
				prevvar = snpvars.getVarname();
				String  order =  var2order.get(snpvars.getVarname() ).toString();
/*				int orlen = order.length();
				switch(orlen) {
					case 1: order = "000" + order;
					case 2: order = "00" + order;
					case 3: order = "0" + order;
				}
				*/
				prevline_right =  "\t.\t.\t.\tName=" + snpvars.getVarname() +
						";ID=" +  snpvars.getVarname() + "-" + snpvars.getPos() + 
						";AlleleRef=" +   snpvars.getRefnuc() + 
						";AlleleAlt=" + snpvars.getVar1nuc() +					
						";Position=" +  snpvars.getPos() +
						";order=" + order +
						"\n";
				
				
				//buff.append("##gff-version 3\n#Note: See http://song.sourceforge.net\n\n");
			}
			
			java.util.List listGFF = new java.util.ArrayList();
			
			while(itsnp.hasNext()) {
				ViewSnpAllvarsId snpvars = itsnp.next();
				
				if(snpvars.getVarname().equals(prevvar) ) {
				
					if(snpvars.getRefnuc().equals(snpvars.getVar1nuc())) continue;
					
					long prevendpos = (prevpos + snpvars.getPos())/2 - bpgap;
					
					// for very short (adjacent positions)
					if(prevstartpos>snpvars.getPos())
						prevstartpos=snpvars.getPos();

					if(prevendpos>snpvars.getPos())
						prevendpos=snpvars.getPos();
					
					//buff.append(prevline_left);
					//buff.append(prevstartpos);buff.append("\t"); buff.append(prevendpos);
					//buff.append(prevline_right);
					
					
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
					listGFF.add(new GFF(prevline_left, prevline_right,  prevstartpos, end));
					
					
					prevstartpos = start;
					
					
				}
				
				
				String chr = snpvars.getChr().toString(); 
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
				prevline_left = chr + "\tIRIC\tsnp\t"  ;
				prevpos = snpvars.getPos();
				prevvar = snpvars.getVarname();
				String order =  var2order.get(snpvars.getVarname() ).toString();

				
				prevline_right =  "\t.\t.\t.\tName=" + snpvars.getVarname() +
						";ID=" +  snpvars.getVarname() + "-" + snpvars.getPos() + 
						";AlleleRef=" +   snpvars.getRefnuc() + 
						";AlleleAlt=" + snpvars.getVar1nuc() +
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
			listGFF.add(new GFF(prevline_left, prevline_right,  prevstartpos, end));
			java.util.Collections.sort( listGFF, new GFFStartComparatator());
			
			
			File file = new File(AppContext.tempdir + filename);	
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

	private void createSNPVarietyGFF1(List listSNPs, String filename, Map var2order, int bpgap, int start, int end) {
		
		// sort snps by start
		
		//Set setSNP = new java.util.TreeSet<ViewSnpAllvarsId>(listSNPs);
		
		//java.util.Collections.sort(listSNPs, new ViewSnpAllvarsIdStartComparator());
		
		
		StringBuffer buff = new StringBuffer();
		
		try {
			File file = new File(AppContext.tempdir + filename);
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			int linecount = 0;
			Iterator<ViewSnpAllvarsId> itsnp = listSNPs.iterator();
		
			long prevpos = 0;
			long prevstartpos = 0;
			String prevline_left="";
			String prevline_right="";
			String prevvar = "";
			
			if(itsnp.hasNext())
			{
				
				ViewSnpAllvarsId snpvars = itsnp.next();
				String chr = snpvars.getChr().toString(); 
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
				prevline_left = chr + "\tIRIC\tsnp\t"  ;
				prevpos = snpvars.getPos();
				prevstartpos = start;
				prevvar = snpvars.getVarname();
				String  order =  var2order.get(snpvars.getVarname() ).toString();
/*				int orlen = order.length();
				switch(orlen) {
					case 1: order = "000" + order;
					case 2: order = "00" + order;
					case 3: order = "0" + order;
				}
				*/
				prevline_right =  "\t.\t.\t.\tName=" + snpvars.getVarname() +
						";ID=" +  snpvars.getVarname() + "-" + snpvars.getPos() + 
						";AlleleRef=" +   snpvars.getRefnuc() + 
						";AlleleAlt=" + snpvars.getVar1nuc() +					
						";Position=" +  snpvars.getPos() +
						";order=" + order +
						"\n";
				
				
				buff.append("##gff-version 3\n#Note: See http://song.sourceforge.net\n\n");
			}
			
			
			while(itsnp.hasNext()) {
				ViewSnpAllvarsId snpvars = itsnp.next();
				
				if(snpvars.getVarname().equals(prevvar) ) {
				
					if(snpvars.getRefnuc().equals(snpvars.getVar1nuc())) continue;
					
					long prevendpos = (prevpos + snpvars.getPos())/2 - bpgap;
					
					// for very short (adjacent positions)
					if(prevstartpos>snpvars.getPos())
						prevstartpos=snpvars.getPos();

					if(prevendpos>snpvars.getPos())
						prevendpos=snpvars.getPos();
					
					buff.append(prevline_left);
					buff.append(prevstartpos);buff.append("\t"); buff.append(prevendpos);
					buff.append(prevline_right);
					//prevstartpos = prevendpos + bpgap*2;
					prevstartpos = prevendpos + 1;
					
					
				} else {
					// finish last prev

					// for very short (adjacent positions)
					if(prevstartpos>prevpos)
						prevstartpos=prevpos;

					
					buff.append(prevline_left);
					buff.append(prevstartpos);buff.append("\t"); buff.append(end);
					buff.append(prevline_right);
					prevstartpos = start;
					
					
				}
				
				
				String chr = snpvars.getChr().toString(); 
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
				prevline_left = chr + "\tIRIC\tsnp\t"  ;
				prevpos = snpvars.getPos();
				prevvar = snpvars.getVarname();
				String order =  var2order.get(snpvars.getVarname() ).toString();
				/*
				int orlen = order.length();
				switch(orlen) {
					case 1: order = "000" + order;
					case 2: order = "00" + order;
					case 3: order = "0" + order;
				}*/
				
				prevline_right =  "\t.\t.\t.\tName=" + snpvars.getVarname() +
						";ID=" +  snpvars.getVarname() + "-" + snpvars.getPos() + 
						";AlleleRef=" +   snpvars.getRefnuc() + 
						";AlleleAlt=" + snpvars.getVar1nuc() +
						";Position=" +  snpvars.getPos() +
						";order=" + order +
						"\n";	

				if(linecount > 100 ) {
					bw.write(buff.toString());
					buff.delete(0, buff.length());
					buff = new StringBuffer();
					bw.flush();
					linecount = 0;
				}
				linecount++;
			}

			// for very short (adjacent positions)
			if(prevstartpos>prevpos)
				prevstartpos=prevpos;

			
			buff.append(prevline_left);
			buff.append(prevstartpos);buff.append("\t"); buff.append(end);
			buff.append(prevline_right);
			
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
	
	private void createSNP2linesGFF(List listSNPs, String filename) {
		
		//if(!checkShowsnp.isChecked()) return; 
		
		StringBuffer buff = new StringBuffer();
		
		try {
			File file = new File( AppContext.tempdir + filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			int linecount = 0;
			Iterator<Snp2linesId> itsnp = listSNPs.iterator();
			while(itsnp.hasNext()) {
				Snp2linesId snpvars = itsnp.next();
				
				String chr = snpvars.getChr().toString(); 
				if(chr.length()==1)
					chr= "chr0" + chr + "|msu7";
				else 
					chr= "chr" + chr + "|msu7";
				
				if( !snpvars.getRefnuc().equals( snpvars.getVar1nuc()  ) ) {
					buff.append(chr); buff.append("\tIRIC\tsnp\t");
					buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
					buff.append( "\t.\t.\t.\tName=" + snpvars.getVar1()
							+";ID=" +  snpvars.getVar1() + "-" + snpvars.getPos() + 
							 ";AlleleRef=" +   snpvars.getRefnuc() +
							 ";AlleleAlt=" + snpvars.getVar1nuc() +
							 ";Position=" +  snpvars.getPos() +
							"\n");
				}

				if( !snpvars.getRefnuc().equals( snpvars.getVar2nuc()  ) ) {
					buff.append(chr); buff.append("\tIRIC\tsnp\t");
					buff.append( snpvars.getPos() ); buff.append( "\t" ); buff.append( snpvars.getPos() );
					buff.append( "\t.\t.\t.\tName=" + snpvars.getVar2()
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

	/*
	private void updateJBrowse(String chr, String start, String end) {
		updateJBrowse(chr,start, end, "");
	}
	*/
	
	private void updateJBrowse(String chr, String start, String end, String locus) {
		//return;
		
		chr = chr.trim();
		String chrpad = chr;
		if(chrpad.length()==1) chrpad="0" + chr;
		
		if (iframeJbrowse==null) throw new RuntimeException("jbrowse==null");
		
		String displaymode="%22displayMode%22:%22normal%22,%22maxHeight%22:%222000%22";
		
		if(tallJbrowse) {
			iframeJbrowse.setStyle("width:1500px;height:3000px;border:0px inset;" );
			//displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%2232000%22";
			displaymode="%22displayMode%22:%22compact%22,%22maxHeight%22:%222000%22";
		}
		else
			iframeJbrowse.setStyle("width:1500px;height:1000px;border:0px inset;" );
		
		String  rendertype="";
		if(radioColorAllele.isSelected()) {
			rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2Variety%22";
		} else if (radioColorMismatch.isSelected() )
		{			
			 rendertype = "%22JBrowse%2FView%2FTrack%2FAlignments2VarietyMismatch%22";
		}
			
		
		if(locus.isEmpty())		
			msgJbrowse.setValue("Chromosome " + chr + " [" + start + ".." + end + "]");
		else
			msgJbrowse.setValue(locus + "  Chromosome " + chr + " [" + start + ".." + end + "]");
		
		iframeJbrowse.setScrolling("yes");
		iframeJbrowse.setAlign("left");		

		
		//String urljbrowse="http://pollux:8080/jbrowse/?loc=chr" + chrpad + "%7Cmsu7%3A" + start + ".." + end + "&tracks=DNA%2CGenes&tracklist=0&overview=0&highlight%3D=&highlight=";
		
		//String urljbrowse="http://pollux:8080/jbrowse/?loc=chr" + chrpad + "%7Cmsu7%3A" + start + ".." + end + "&tracks=DNA%2CGenes,SNP&tracklist=0&overview=0&" +
		//		"addStores%3D%7B%22url%22%3A%7B%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3%22%2C%22urlTemplate%22%3A%22"
		//		+ "http://pollux:8080/iric-portal/tmp/" + gfffile 
		//		+ "%22%7D%7D%26addTracks%3D%5B%7B%22label%22%3A%22SNP%22%2C%22type%22%3A%22JBrowse%2FView%2FTrack%2FCanvasFeatures%22%2C%22store%22%3A%22url%22%7D%5D";

		//String urljbrowse="http://pollux:8080/jbrowse-dev/?loc=" + chrpad + "|msu7:" + start + ".." + end + "&tracks=DNA,Genes,SNP&addStores={\"url\":{\"type\":\"JBrowse/Store/SeqFeature/GFF3\",\"urlTemplate\":\"http://pollux:8080/iric-portal/tmp/" + gfffile  + "\"}}&" + 
		//		"addTracks=[{\"label\":\"SNP\",\"type\":\"JBrowse/View/Track/CanvasFeatures\",\"store\":\"url\", \"maxHeight\":\"30000\", \"style\":{\"showLabels\":\"false\"}}]";
		
		//String urltemplate = "http://pollux:8080/iric-portal/tmp/" + gfffile;
		String urltemplate = "..%2F..%2Firic-portal%2Ftmp%2F" + gfffile;
		//String urljbrowse;
		
		
		
		//if(checkShowsnp.isChecked() ) {
		if(true) {
		
			if(tallJbrowse) {
			//urljbrowse="http://pollux:8080/jbrowse-dev/?loc=chr" + chrpad + "|msu7:" + start + ".." + end + 
			//		"&tracks=DNA,Genes,SNP&addStores={%22url%22:{%22type%22:%22JBrowse/Store/SeqFeature/GFF3Variety%22,%22urlTemplate%22:%22" + urltemplate
			//		+ "%22}}&addTracks=[{%22label%22:%22SNP%22,%22type%22:%22JBrowse/View/Track/Alignments2Variety%22,%22store%22:%22url%22," + displaymode + ",%22style%22:{%22showLabels%22:%22false%22,%22textFont%22:%22normal 8px Univers,Helvetica,Arial,sans-serif%22," +
			//		"%22showLabels%22:%22false%22,%22label%22:%22Name%22,%22strandArrow%22:%22false%22}}]";
		
			//http://pollux:8080/jbrowse-dev/?loc=chr01|msu7%3A7291..12139&tracks=DNA%2CGenes%2CSNP&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22..%2F..%2Firic-portal%2Ftmp%2Ftmp_5728269420009599070.gff%22}}&addTracks=[{%22label%22%3A%22SNP%22%2C%22type%22%3A%22JBrowse%2FView%2FTrack%2FAlignments2Variety%22%2C%22store%22%3A%22url%22%2C%20%22maxHeight%22%3A%2232000%22%2C%20%22style%22%3A{%22showLabels%22%3A%22false%22%2C%22textFont%22%3A%22normal%208px%20Univers%2CHelvetica%2CArial%2Csans-serif%22}%20}]&highlight=
				urljbrowse="http://pollux:8080/jbrowse-dev/?loc=chr"  + chrpad + "|msu7:" + start + ".." + end +   "&tracks=DNA%2Cmsu7gff%2CSNP&addStores={%22url%22%3A{%22type%22%3A%22JBrowse%2FStore%2FSeqFeature%2FGFF3Variety%22%2C%22urlTemplate%22%3A%22" + urltemplate +
					 "%22}}&addTracks=[{%22label%22%3A%22SNP%22%2C%22type%22%3A" + rendertype + "%2C%22store%22%3A%22url%22%2C%20" + displaymode 
					+ "%2C%20%22style%22%3A{%22showLabels%22%3A%22false%22%2C%22textFont%22%3A%22normal%208px%20Univers%2CHelvetica%2CArial%2Csans-serif%22}}]&highlight=";

			

			}
			else
			{
				urljbrowse="http://pollux:8080/jbrowse-dev/?loc=chr" + chrpad + "|msu7:" + start + ".." + end + 
					"&tracks=DNA,msu7gff,SNP&addStores={%22url%22:{%22type%22:%22JBrowse/Store/SeqFeature/GFF3%22,%22urlTemplate%22:%22" + urltemplate
					+ "%22}}&addTracks=[{%22label%22:%22SNP%22,%22type%22:%22JBrowse/View/Track/CanvasFeatures%22,%22store%22:%22url%22," + displaymode + ",%22style%22:{%22showLabels%22:%22false%22,%22textFont%22:%22normal 8px Univers,Helvetica,Arial,sans-serif%22," +
					"%22showLabels%22:%22false%22,%22label%22:%22Name%22,%22strandArrow%22:%22false%22}}]";
			
			
			
			
			}
		}
		/*
		else
		{
			urljbrowse="http://pollux:8080/jbrowse-dev/?loc=chr" + chrpad + "|msu7:" + start + ".." + end +  "&tracks=DNA,Genes";
		}
		*/
				
		
		//http://pollux:8080/jbrowse-dev/?loc=chr01|msu7:900..1100&tracks=DNA,Genes,SNP&addStores={"url":{"type":"JBrowse/Store/SeqFeature/GFF3","urlTemplate":"http://pollux:8080/jbrowse-dev/rice_data/raw/dynamic/test.gff"}}&addTracks=[{"label":"SNP","type":"JBrowse/View/Track/CanvasFeatures","store":"url"}]

		
		log.debug(urljbrowse);
		
		System.out.println(urljbrowse);
		//iframeJbrowse.setSrc("http://jbrowse.org/code/JBrowse-1.11.4/?loc=ctgA%3A9896..31887&tracks=DNA%2CTranscript%2Cvolvox_microarray_bw_density%2Cvolvox_microarray_bw_xyplot%2Cvolvox-sorted-vcf%2Cvolvox-sorted_bam_coverage%2Cvolvox-sorted_bam&data=sample_data%2Fjson%2Fvolvox&highlight=&tracklist=1&nav=1&overview=1");
		
		
		
		//iframeJbrowse.setSrc( urljbrowse );
		
		//if(showTall) show_phylotree(chr, start, end); 	
		
		//phylochr = chr;
		//phylostart = start;
		//phyloend = end;
			
	}
	
	
	private void show_phylotree(String  chr, String start, String end) {

		
			int nvars = genotype.getListSNPAllVarsMismatches().size();
			int height = nvars*21;
			int width = nvars*30;
			
			int treescale = 1;
			
			iframePhylotree.setStyle("height:" + Integer.toString(height) + "px;width:1400px");
			//iframePhylotree.setStyle("height:" + Integer.toString(height) + "px;width:" + Integer.toString(width) + "px");
			iframePhylotree.setScrolling("yes");
			

			
			urlphylo = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&chr=" + chr + "&start=" + start + "&end=" + end ;
			log.debug(urlphylo);

			
		
	}
	



	/*
	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		// TODO Auto-generated method stub
	     Session session=Executions.getCurrent().getSession();
	     java.util.Date now = new java.util.Date();
	     log.info(now + "\t" + session.getRemoteAddr() + "\t" +  session.getRemoteAddr());
	     
         //user=(user) session.getAttribute("user");
         //System.out.println("user Id="+user);		
	}
	
	private Object getSessionAttribute(String key) {
		Session session=Executions.getCurrent().getSession();
		return session.getAttribute(key);
		
	}
	private void setSessionAttribute(String key, Object value) {
		Session session=Executions.getCurrent().getSession();
		session.setAttribute(key,value);
	}
     */
}