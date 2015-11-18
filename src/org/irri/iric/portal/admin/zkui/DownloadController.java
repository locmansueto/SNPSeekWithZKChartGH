package org.irri.iric.portal.admin.zkui;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genomics.VariantSequenceQuery;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.VarietyPropertiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;




@Controller()
public class DownloadController   extends SelectorComposer<Component>  {

	@Wire
	private Grid gridRawVarietyFiles;

	
	@Autowired
	private VarietyFacade varietyfacade;
	
	@Autowired
	private GenomicsFacade genomicsfacade;
	
	@Autowired
	@Qualifier("GenotypeFacade")
	private GenotypeFacade genotypefacade;
	
	@Autowired
	@Qualifier("WorkspaceFacade")
	private WorkspaceFacade workspacefacade;

	
	@Wire
	private Checkbox checkboxFastq;
	@Wire
	private Checkbox checkboxBAM;
	@Wire
	private Checkbox checkboxVCF;
	@Wire
	private Listbox listboxVarlist;
	@Wire
	private Button buttonUnmarkAll;
	@Wire  
	private Button buttonDownloadMarked;
	@Wire
	private Listbox listboxSite;
	
	@Wire
	private Listbox listboxQueryvar;
	@Wire
	private Listbox listboxVarlistSeq;
	
	@Wire
	private Listbox listboxLocuslist;
	
	@Wire
	private Combobox comboboxLocus;
	
	@Wire
	private Combobox comboQuerychr;
	@Wire
	private Listbox listboxQuerystrand;
	@Wire
	private Intbox intboxStart;
	@Wire
	private Intbox intboxEnd;
	@Wire
	private Button buttonDownloadSequence;
	
	private boolean renderedDownloadtable=false;
	
	
	
    @Listen("onClick = #buttonDownloadSequence")
    public void onclickDownlaodsequence()  {
    
    	varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade , "VarietyFacade");
    	workspacefacade=(WorkspaceFacade)AppContext.checkBean(workspacefacade , "WorkspaceFacade");
    	
    	try {
    	Set setVarieties=new LinkedHashSet();
    	Set setLoci=new LinkedHashSet();
    	
    	
    	if(listboxQueryvar.getSelectedItem().getLabel().toString().isEmpty()) {
    		if(!this.listboxVarlistSeq.getSelectedItem().getLabel().isEmpty()) {
    			AppContext.debug("querylist " + listboxVarlistSeq.getSelectedItem().getLabel());
    			if(listboxVarlistSeq.getSelectedItem().getLabel().toLowerCase().equals("all"))
    				setVarieties = varietyfacade.getGermplasm();
    			else
    				setVarieties = workspacefacade.getVarieties(listboxVarlistSeq.getSelectedItem().getLabel());
    		} else {
    			Messagebox.show("Please select variety or variety list");
    			return;
    		}
    	}
    	else { 
    		
    		setVarieties.add( varietyfacade.getGermplasmByName(listboxQueryvar.getSelectedItem().getValue().toString()));
    	}

    	
    	if(this.listboxLocuslist.getSelectedItem().getLabel().isEmpty()) {
    		if(comboQuerychr.getValue()==null || comboQuerychr.getValue().isEmpty() ||  intboxEnd.getValue()==null || intboxStart.getValue()==null ||  listboxQuerystrand.getSelectedItem().getLabel().isEmpty() ) {
    			Messagebox.show("Please complete region information, or select locus list");
    			return;
    		}
    		else setLoci.add( new MultiReferenceLocusImpl(AppContext.getDefaultOrganism(), comboQuerychr.getValue(), intboxStart.getValue(),
    			intboxEnd.getValue(), Integer.valueOf(listboxQuerystrand.getSelectedItem().getValue().toString())));
    	} else {
    		setLoci = workspacefacade.getLoci(listboxLocuslist.getSelectedItem().getLabel());
    	}
    	
    	VariantSequenceQuery query=new VariantSequenceQuery(setVarieties, setLoci);
    	genomicsfacade = (GenomicsFacade)AppContext.checkBean(genomicsfacade, "GenomicsFacade");
    	String dir = genomicsfacade.createVariantsFasta(query);
    	
    	List listFiles=new ArrayList();
    	File folder = new File(dir);
    	File[] listOfFiles = folder.listFiles();

    	    for (int i = 0; i < listOfFiles.length; i++) {
    	      if (listOfFiles[i].isFile()) {
    	        //System.out.println("File " + listOfFiles[i].getName());
    	    	  if(listOfFiles[i].getName().endsWith(".fsa") || listOfFiles[i].getName().endsWith(".txt"))
    	    		  listFiles.add(listOfFiles[i].getAbsolutePath());
    	      } else if (listOfFiles[i].isDirectory()) {
    	        AppContext.debug("Directory " + listOfFiles[i].getName() + " not added in ZIP");
    	      }
    	    }
    	String paths[]=dir.split("/");
    	String zipfilename=AppContext.getTempDir() + paths[paths.length-1] + ".zip";
    	CreateZipMultipleFiles zip = new CreateZipMultipleFiles( zipfilename, listFiles);
    	zip.create(false);
    	
		//Filedownload.save(zipfilename, "application/zip");
    	Filedownload.save(new File(zipfilename), "application/zip");
		//AppContext.debug("File download complete! Saved to: "+filename);
		org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
		AppContext.debug("variantsequence download complete!"+ zipfilename +  " Downloaded to:"  +  zksession.getRemoteHost() + "  "  +  zksession.getRemoteAddr()  );

    	
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		Messagebox.show(ex.getMessage());
    	}
    	
    }
    
    
    @Listen("onSelect = #listboxQueryvar")
    public void onSelectlistboxQueryvar()  {
    	listboxVarlistSeq.setSelectedIndex(0);
    }
    
    @Listen("onSelect = #listboxVarlistSeq")
    public void onSelectlistboxVarlistSeq()  {
    	listboxQueryvar.setSelectedIndex(0);
    	if( listboxVarlistSeq.getSelectedItem().getLabel().equals("create new list...") ) {
    		Executions.sendRedirect("_workspace.zul?from=variety&src=download");
    	}
    }
    
		
	@Listen("onSelect = #comboboxLocus")
		public void onSelectcomboboxLocus() {
			if(!comboboxLocus.getValue().isEmpty()) {
				try {
					genotypefacade =  (GenotypeFacade)AppContext.checkBean(genotypefacade , "GenotypeFacade");
					Gene gene = genotypefacade.getGeneFromName( comboboxLocus.getValue(), AppContext.getDefaultOrganism() );
					
					//listboxQuerychr.setValue(gene.getContig().toLowerCase());
					comboQuerychr.setValue(gene.getContig().toLowerCase());
					
					intboxStart.setValue(gene.getFmin());
					intboxEnd.setValue(gene.getFmax());
					if(gene.getStrand()>0)
						listboxQuerystrand.setSelectedIndex(1);
					else 
						listboxQuerystrand.setSelectedIndex(2);
	
					listboxLocuslist.setSelectedIndex(0);
				}catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
    }
    
    @Listen("onSelect = #listboxLocuslist")
    public void onSelectlistboxLocuslist()  {
    	onClearLocuslist();
    	if( listboxLocuslist.getSelectedItem().getLabel().equals("create new list...") ) {
    		Executions.sendRedirect("_workspace.zul?from=locus&src=download");
    	} else {
    		
    	}
    }
    @Listen("onSelect = #listboxQuerystrand")
    public void onSelectlistboxQuerystrand()  {
    	comboboxLocus.setValue("");
    	listboxLocuslist.setSelectedIndex(0);
    	
    }
    @Listen("onSelect = #listboxQuerychr")
    public void onSelectlistboxQuerychr()  {
    	comboboxLocus.setValue("");
    	listboxLocuslist.setSelectedIndex(0);
    }
    
    @Listen("onChange = #intboxStart")
    public void onchangeStart() {
    	comboboxLocus.setValue("");
    	listboxLocuslist.setSelectedIndex(0);
    	
    }
    @Listen("onChange = #intboxEnd")
    public void onchangeEnd() {
    	comboboxLocus.setValue("");
    	listboxLocuslist.setSelectedIndex(0);
   }

    
    private void onClearLocuslist() {
    	intboxStart.setValue(null);
    	intboxEnd.setValue(null);
    	listboxQuerystrand.setSelectedIndex(0);
    	comboQuerychr.setValue("");
    	comboboxLocus.setValue("");
    }
	
	
	
    
    
	@Listen("onClick = #tabRawfiles")
	public void onclickRawfiles() {
		
		if(!renderedDownloadtable) {
		
		try {
			varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
			
			Set<Variety> allvars = new TreeSet(varietyfacade.getGermplasm());
			
			List listvars = new ArrayList();
			listvars.addAll(allvars);
			gridRawVarietyFiles.setModel( new SimpleListModel( listvars ));
			gridRawVarietyFiles.setRowRenderer(new VarietyDownloadsRowRenderer( varietyfacade.getVarietyExternalURL(  VarietyPropertiesService.ID_ERS ), 
					 varietyfacade.getVarietyExternalURL(  VarietyPropertiesService.ID_SRA )));
			
			AppContext.debug(listvars.size() + " varieties in file download list");
			
			renderedDownloadtable=true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		} 
		
		
	}
	
	@Listen("onClick =#buttonUnmarkAll")
	public void onclickUnmarkall() {
		VarietyDownloadsRowRenderer renderer = (VarietyDownloadsRowRenderer)gridRawVarietyFiles.getRowRenderer();
		Iterator<Checkbox> itChecks = renderer.getListchecks().iterator();
		while(itChecks.hasNext()) {
			Checkbox check = itChecks.next();
			check.setChecked(false);
		}
	}
	
	@Listen("onClick =#buttonDownloadMarked")
	public void onclickDownload() {
		
		String selSite = listboxSite.getSelectedItem().getLabel();
		if(selSite.equals("AWS HTTP")) {
			if( this.checkboxFastq.isChecked()) {
				
			}
			if( this.checkboxBAM.isChecked()) {
				
			}
			if( this.checkboxVCF.isChecked()) {
				
			}
		} 
		else if(selSite.equals("ASTI HTTP")) {
			
		}
		else if(selSite.equals("ASTI FTP")) {
			
		}	
	     
	}
	
	@Listen("onSelect =#listboxVarlist")
	public void onselectvarlist() {
		varietyfacade = (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		
		int rowcount=0;
		Iterator<Listitem> itItems =listboxVarlist.getItems().iterator();
		while(itItems.hasNext()) {
			Listitem item=itItems.next();
			if(item.isSelected()) {
				break;
			}
			rowcount++;
		}
		int pageNum= rowcount/100;
		gridRawVarietyFiles.setActivePage(pageNum);

        
	}
	
	public void onselectvarlistOld() {
		
		workspacefacade = (WorkspaceFacade)AppContext.checkBean(workspacefacade, "WorkspaceFacade");
		
		String selitem = listboxVarlist.getSelectedItem().getLabel().trim();
		if(selitem.isEmpty()) return;
		if(selitem.equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety&src=download");
			return;
		}
		
		Set setVarieties = workspacefacade.getVarieties( selitem );
		
		Set setvarnames = new HashSet();
		Iterator<Variety> itVar =  setVarieties.iterator();
		while(itVar.hasNext()) {
			Variety var = itVar.next();
			setvarnames.add(var.getName());
		}
		
		
		VarietyDownloadsRowRenderer renderer = (VarietyDownloadsRowRenderer)gridRawVarietyFiles.getRowRenderer();
		Iterator<String> itNames = renderer.getListNames().iterator();
		
		List listChecks = renderer.getListchecks();
		int icount=0;
		while(itNames.hasNext()) {
			String name = itNames.next();
			if(setvarnames.contains(name)) {
				Checkbox cb = (Checkbox)listChecks.get(icount);
				cb.setChecked(true);
			}
			icount++;
		}
		
	}
	
	
}
