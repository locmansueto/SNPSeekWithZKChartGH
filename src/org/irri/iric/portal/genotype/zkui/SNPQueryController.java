package org.irri.iric.portal.genotype.zkui;

import java.util.List;
import java.util.Set;
 










import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.genotype.service.GenotypeFacadeImpl;

import org.irri.iric.portal.genotype.views.Snp2lines;
import org.irri.iric.portal.genotype.views.Snp2linesHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.ext.Selectable;
 

@Controller
public class SNPQueryController extends SelectorComposer<Component> {
 
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
    

    
    @Wire
    private Listbox selectQueryMode;
    
    @Autowired
    private GenotypeFacade  genotype;  //=  // new org.irri.iric.portal.genotype.service.GenotypeFacadeImpl();
  
    @Wire
    private Iframe iframeJbrowse;
    @Wire
    private Label msgJbrowse;
    
    
    
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
    	labelLength.setValue("END: (length " + length + " bp)" );
    	chrlength=length.intValue();
    }
    
@Listen("onClick = #searchButton")    
public void search()    {
	
	genotype =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");
	
	/*
	
	String name = "GenotypeFacade";
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
	
	
		//	List<Snp2lines>  listSNP= new java.util.ArrayList();
			
		msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
		
		                    
		List<Snp2lines> listSNPs = new java.util.ArrayList();
		String var1= comboVar1.getValue().trim().toUpperCase();
		String var2 =  comboVar2.getValue().trim().toUpperCase();
		String genename = comboGene.getValue().trim().toUpperCase();
		if( (var1.isEmpty() ||  var2.isEmpty()  ) )
		{
			Messagebox.show("Both varieties are required", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		GenotypeFacade.snpQueryMode mode=null;
		if (selectQueryMode.getSelectedIndex()==0)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
		else if  (selectQueryMode.getSelectedIndex()==1)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE;
		else if  (selectQueryMode.getSelectedIndex()==2)
		mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLREFPOS;
					
					
		if( !genename.isEmpty() )
		{
		msgbox.setValue("Searching within GENE " + comboGene.getValue() );
		listSNPs = genotype.getSNPinVarieties(var1, var2, comboGene.getValue().trim(), 0, mode);
		
		
			if(listSNPs.size()>0)
			{
				String genename2 = comboGene.getValue().trim();
				genotype.getGeneFromName( genename2);
				
				updateJBrowse( genotype.getGeneFromName( genename2).getChr().toUpperCase().replace("CHR",""),
					 genotype.getGeneFromName( genename2).getFmin().toString(), genotype.getGeneFromName( genename2).getFmax().toString() ,
					 genename2);
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
			
			String chr = selectChr.getSelectedItem().getLabel().trim();
			if(chr.isEmpty())
			{
				Messagebox.show("No chromosome selected", "INVALID CHROMOSOME VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
				msgbox.setValue("INVALID CHROMOSOME VALUE: no chromosome selected" );
				return;
			}
			msgbox.setValue("SEARCHING: in chromosome " + chr + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
			msgbox.setStyle( "STYLE='color=BLACK'" );
			
			listSNPs =  genotype.getSNPinVarieties(var1,var2, intStart.getValue(), intStop.getValue(), chr, mode);
			
			if(listSNPs.size()>0)
				updateJBrowse( selectChr.getSelectedItem().getLabel().trim(),  intStart.getValue().toString() , intStop.getValue().toString());
			else {
				iframeJbrowse.setVisible(false);
				msgJbrowse.setVisible(false);
			}
		}
		
		
		msgbox.setValue( msgbox.getValue() + " ... RESULT: " + listSNPs.size() + " rows" );
		msgbox.setStyle( "font-color=black" );
		

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

}


	private void updateJBrowse(String chr, String start, String end) {
		updateJBrowse(chr,start, end, "");
	}
	private void updateJBrowse(String chr, String start, String end, String locus) {
		chr.trim();
		String chrpad = chr;
		if(chrpad.length()==1) chrpad="0" + chr;
		
		if (iframeJbrowse==null) throw new RuntimeException("jbrowse==null");
		
		iframeJbrowse.setVisible(true);
		
		if(locus.isEmpty())
		
			msgJbrowse.setValue("Chromosome " + chr + " [" + start + ".." + end + "]");
		else
			msgJbrowse.setValue(locus + "  Chromosome " + chr + " [" + start + ".." + end + "]");
		
		msgJbrowse.setVisible(true);

		String urljbrowse="http://pollux:8080/jbrowse/?loc=chr" + chrpad + "%7Cmsu7%3A" + start + ".." + end + "&tracks=DNA%2CGenes&tracklist=0&overview=0&highlight%3D=&highlight=";
		
		log.debug(urljbrowse);
		
		System.out.println(urljbrowse);
		//iframeJbrowse.setSrc("http://jbrowse.org/code/JBrowse-1.11.4/?loc=ctgA%3A9896..31887&tracks=DNA%2CTranscript%2Cvolvox_microarray_bw_density%2Cvolvox_microarray_bw_xyplot%2Cvolvox-sorted-vcf%2Cvolvox-sorted_bam_coverage%2Cvolvox-sorted_bam&data=sample_data%2Fjson%2Fvolvox&highlight=&tracklist=1&nav=1&overview=1");
		
		iframeJbrowse.setSrc( urljbrowse );
		
	}

     
}