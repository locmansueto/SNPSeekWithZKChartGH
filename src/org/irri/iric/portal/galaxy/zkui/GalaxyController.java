package org.irri.iric.portal.galaxy.zkui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.servlet.http.HttpSession;

import org.irri.iric.galaxy.service.GalaxyFacade;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.AsyncJob;
import org.irri.iric.portal.admin.AsyncJobImpl;
import org.irri.iric.portal.admin.AsyncJobReport;
import org.irri.iric.portal.admin.JobsFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.genotype.zkui.Object2StringMultirefsMatrixModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.zul.Biglistbox;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.github.jmchilton.blend4j.galaxy.beans.Tool;
import com.github.jmchilton.blend4j.galaxy.beans.Workflow;

@Controller("GalaxyController")
@Scope(value = "session")
public class GalaxyController extends SelectorComposer<Window> {

	@Autowired
	@Qualifier("GalaxyFacade")
	private GalaxyFacade galaxy;
	@Autowired
	@Qualifier("JobsFacade")
	private JobsFacade jobsfacade;

	
	@Wire
	private Listbox listboxServer;
	@Wire
	private Listbox listData;
	@Wire
	private Listbox listFormat;
	@Wire
	private Listbox listTools;
	@Wire
	private Grid listInputs;

	@Wire
	private Listbox listSection;
	@Wire
	private Button buttonCommon;
	@Wire
	private Button buttonNone;
	@Wire
	private Button buttonAll;
	@Wire
	private Button buttonWorkflows;
	@Wire
	private Listbox listLists;
	
	@Wire
	private Button buttonDiscover;
	@Wire
	private Button buttonRun;
	@Wire
	private Div divDiscovery;
	@Wire
	private Checkbox checkboxAsync;
	@Wire
	private Label labelDownloadProgressMsg;
	@Wire
	private A aDownloadProgressURL;
	
	
	
	public GalaxyController() {
		super();
	}


	public void updateDisplay() {
		
	}
	
	@Listen("onSelect =#listboxServer")
	public void onselectServer() {
		try {
			AppContext.setGalaxyInstance(listboxServer.getSelectedItem().getLabel() );
		} catch(Exception ex) {}
	}
	
	
	public Object[] onclickRun_(boolean sync) {
		
		Object[] ret=null;
		try {
			galaxy= (GalaxyFacade)AppContext.checkBean(galaxy,"GalaxyFacade");
			
			Map<String,String> mapParam2Value=new HashMap();
			for(Object o:listInputs.getRows().getChildren()) {
				List rowcols= ((Row)o).getChildren();
				String param=((Label)rowcols.get(0)).getValue();
				String value=((Textbox)rowcols.get(1)).getValue().trim();
				mapParam2Value.put( param  , value);
			}
			
			
			String jobid=null;
			
			Object[] selobj=listTools.getSelectedItem().getValue();		
			List paramlist=new ArrayList();
			if(selobj[0] instanceof Tool) {
			}
			else if(selobj[0] instanceof Workflow) {
				Workflow wf=(Workflow)selobj[0];
				
				//jobid="galaxywf-"+  wf.getId().replace(" ","_").replace("-","_") + "-" + AppContext.createTempFilename();
				jobid="galaxywf-"+  wf.getName().replace(" ","_").replace(":","_").replace("-","_").toLowerCase() + "-" + AppContext.createTempFilename()+".zip";
				if(sync) {
					System.out.println("Executing wf " + wf.getName() + " with params " + mapParam2Value);
					String status=galaxy.runWorkflow(wf, mapParam2Value, jobid, new File(AppContext.getTempDir()+jobid+".zip")); 
					AppContext.debug("runWorkflow Synch result:" + status);
					Filedownload.save(new File(AppContext.getTempDir()+jobid+".zip"), "application/zip");
					ret=new Object[] {status,null};
				}
				else {
					System.out.println("Submitting asynch wf " + wf.getName() + " with params " + mapParam2Value);
					Future future=galaxy.runWorkflowAsync(wf, mapParam2Value, jobid, new File(AppContext.getTempDir()+jobid+".zip")); 
					ret=new Object[] {jobid,future};
				}
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return ret;
		
	}
	
	@Listen("onClick =#buttonRun")
	public void onclickRun() {
	
		if(!checkboxAsync.isChecked()) {
			labelDownloadProgressMsg.setVisible(false);
			aDownloadProgressURL.setVisible(false);
			onclickRun_(true); 
		} else {
		
			try {
				Callable<AsyncJobReport> callreport = callableGalaxyRun();
				AsyncJobReport report = callreport.call();
				// AsyncJobReport report=null;
		
				if (report != null) {
					if (report.getMessage().equals(JobsFacade.JOBSTATUS_SUBMITTED)) {
						this.labelDownloadProgressMsg.setValue("Job is submitted. Please monitor progress at ");
						this.aDownloadProgressURL.setLabel(report.getUrlProgress());
						this.aDownloadProgressURL.setHref(report.getUrlProgress());
						//enableDownloadButtons(false);
					} else
						this.labelDownloadProgressMsg.setValue(report.getMessage());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				Messagebox.show("galaxyrun:" + ex.getMessage());
			}
		}
	}
	
	public Callable<AsyncJobReport> callableGalaxyRun() {
		return new Callable<AsyncJobReport>() {
			@Override
			public AsyncJobReport call() throws Exception {
				String msg = "";
				String jobid = "";
				String url = "";
				Future futureReportjob = null;
				try {

					jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");

					/*
					if (params.getSubmitter() == null) {
						msg = "Submitter ID required for long jobs.";
					} else if (jobsfacade.checkSubmitter(params.getSubmitter())) {
						msg = "You have a running long job. Please try again when that job is done.";
					} else 
					*/
					if(true){
						Object ret[]=onclickRun_(false); 

						AsyncJob job = new AsyncJobImpl((String)ret[0],"galaxy_run",
								"submitter");
						futureReportjob = (Future)ret[1]; // genotype.querydownloadGenotypeAsync(params);
						job.setFuture(futureReportjob);						
						jobid = job.getJobId();
						url = job.getUrl();
						
						if (jobsfacade.addJob(job)) {
							//Object ret[]=onclickRun_(false); 
							msg = jobsfacade.JOBSTATUS_SUBMITTED;
						} else {
							msg = jobsfacade.JOBSTATUS_REFUSED;
						}
					}
					AppContext.debug("callable galaxy run.. submitted");
				} catch (Exception ex) {
					ex.printStackTrace();
					// Messagebox.show("call():" + ex.getMessage());
					msg = ex.getMessage();

				}
				return new AsyncJobReport(jobid, msg, url, futureReportjob);
			}
		};
	}

	
	
	
	@Listen("onSelect =#listTools")
	public void onselectTools() {
		try {
		Object[] selobj=listTools.getSelectedItem().getValue();
		String haplofilename=(String)getSession().getAttribute("haplofilename");

		List paramlist=new ArrayList();
		if(selobj[0] instanceof Tool) {
			for(String p:(Set<String>)selobj[1]) {
				paramlist.add(new String[] {p,""});
			}
		}
		else if(selobj[0] instanceof Workflow) {
			for(String p:(Set<String>)selobj[1]) {
				
				if(haplofilename!=null) {
					if(p.startsWith("locuslist"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".bed"});
					else if(p.startsWith("snpmatrix"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".ped"});
					else if(p.startsWith("snplist"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".map"});
					else if(p.startsWith("samplelist"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".txt"});
				}
				else paramlist.add(new String[] {p,""});
			}
		}
		listInputs.setRowRenderer(new InputParamsRowRenderer());

		listInputs.setModel(new ListModelList(paramlist));
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	
	@Listen("onClick =#buttonCommon")
	public void onclickCommon() {
		try {
			Set sC=getCommonClass();
			Set<Listitem> selItems=new HashSet();
			for(Listitem i:listData.getItems()) {
				if(sC.contains(  i.getLabel())) {
					selItems.add(i);
				}
			}
			listData.setSelectedItems(selItems);
		
			Set sC2=getCommonExt();
			Set<Listitem> selItems2=new HashSet();
			for(Listitem i:listFormat.getItems()) {
				if(sC2.contains(  i.getLabel())) {
					selItems2.add(i);
				}
			}
			listFormat.setSelectedItems(selItems2);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}

	@Listen("onClick =#buttonNone")
	public void onclickNone() {

	}
	@Listen("onClick =#buttonAll")
	public void onclickAll() {

	}

	
	@Listen("onClick =#buttonWorkflows")
	public void onclickWorkflows() {
		
		try {
			galaxy= (GalaxyFacade)AppContext.checkBean(galaxy,"GalaxyFacade");
			Set sList=new HashSet();
			for(Listitem i:listLists.getSelectedItems()) {
				sList.add(i.getLabel());
			}
			AppContext.debug("galaxy.discoverTools(sName, null, sSection)");
			Map[] m=galaxy.discoverWorkflows(sList);
			Map<String,Set<Workflow>> mapTerm2Wf=m[0];
			
			Map<Workflow,Set<String>> mapTool2Terms=new HashMap();
			for(String name:mapTerm2Wf.keySet()) {
				for(Workflow t:mapTerm2Wf.get(name)) {
					Set terms=mapTool2Terms.get(t);
					if(terms==null) {
						terms=new HashSet();
						mapTool2Terms.put(t, terms);
					}
					terms.add(name);
				}
			}
			Map mapTools2Params=m[1];
			List<Object[]> lTools=new ArrayList();
			for(Workflow t:mapTool2Terms.keySet()) {
				lTools.add( new Object[] {t, mapTools2Params.get(t), mapTool2Terms.get(t)});
			}
			listTools.setItemRenderer(new MatchedWorkflowRenderer());
			listTools.setModel(new SimpleListModel(lTools));
			// display details of tools
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@Listen("onClick =#buttonDiscover")
	public void onclickDiscover() {
		
		try {
		galaxy= (GalaxyFacade)AppContext.checkBean(galaxy,"GalaxyFacade");
		
		AppContext.debug("galaxy=" + galaxy);

		//Set setAvailDatatypes = (Set)getSession().getAttribute("datatypes");
		
		// discover workflows/tools that consumes this datatypes,  
		// workflows are ranked based on number of types it consumes
		
		/*
		Set<String> sName=new HashSet<String>();
		sName.add("locuslist");
		sName.add("samplelist");
		
		Set<String>  sSection=new HashSet<String> ();
		sSection.add("SNP-Seek Tools");
		sSection.add("Clustering");
		*/

		Set sSection=new HashSet();
		for(Listitem i:listSection.getSelectedItems()) {
			sSection.add(i.getLabel());
		}

		Set sClass=new HashSet();
		for(Listitem i:listData.getSelectedItems()) {
			sClass.add(i.getLabel());
		}
		Set sFormat=new HashSet();
		for(Listitem i:listFormat.getSelectedItems()) {
			sFormat.add(i.getLabel());
		}
		if(sSection.isEmpty()) sSection=null;
		
		
		//AppContext.debug("galaxy.discoverWorkflows(sName, null)");
		//galaxy.discoverWorkflows(sName);

		AppContext.debug("galaxy.discoverTools(sName, null, sSection)");
		Map mapst[]=galaxy.discoverTools(new HashSet(), sClass, sFormat, sSection);
		Map<String,Set<Tool>> mapName2Tools=mapst[0];
		Map<Tool,Set<String>> mapTools2Params=mapst[1];
		
		// rank tools by number of matching terms
		Map<Tool,Set<String>> mapTool2Terms=new HashMap();
		for(String name:mapName2Tools.keySet()) {
			for(Tool t:mapName2Tools.get(name)) {
				Set terms=mapTool2Terms.get(t);
				if(terms==null) {
					terms=new HashSet();
					mapTool2Terms.put(t, terms);
				}
				terms.add(name);
			}
		}
		List<Object[]> lTools=new ArrayList();
		for(Tool t:mapTool2Terms.keySet()) {
			lTools.add( new Object[] {t, mapTools2Params.get(t), mapTool2Terms.get(t)});
		}
		listTools.setItemRenderer(new MatchedToolsRenderer());
		listTools.setModel(new SimpleListModel(lTools));
		// display details of tools
		 
		 
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public HttpSession getSession() {
		return (HttpSession) Executions.getCurrent().getSession().getNativeSession();
	}

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		
		super.doAfterCompose(comp);		
		
		try {
			galaxy= (GalaxyFacade)AppContext.checkBean(galaxy,"GalaxyFacade");
			
			ListModelList m=new ListModelList( new ArrayList(galaxy.getDatatypes()));
			m.setMultiple(true);
			listData.setModel(m);
			
			

			List setExt=galaxy.getFileexts();
			setExt.remove("");
			setExt.remove("_export.txt.gz");
			setExt.remove("None");
			
			m=new ListModelList( new ArrayList(setExt));
			m.setMultiple(true);
			listFormat.setModel(m);
			
			 m=new ListModelList( new ArrayList(galaxy.getSections()));
			m.setMultiple(true);
			listSection.setModel(m);
			
			String embed=Executions.getCurrent().getParameter("embed");
			if(embed!=null && embed.equals("genotype")) {
				Set setList=new HashSet();
				setList.add("snplist");
				setList.add("snpmatrix");
				setList.add("samplelist");
				setList.add("locuslist");
				Set setInclude=new HashSet();
				for(Listitem i:listLists.getItems()) {
					if(setList.contains(i.getLabel())) setInclude.add(i);
				};
				
				try {
					listLists.setSelectedItems(setInclude);
					Events.sendEvent(new Event("onClick", buttonWorkflows));

					/*
					Biglistbox bl=(Biglistbox)comp.getParent().query("#biglistboxArray");
					Object2StringMultirefsMatrixModel matrixmodel= (Object2StringMultirefsMatrixModel)bl.getModel();
					VariantAlignmentTableArraysImpl table = (VariantAlignmentTableArraysImpl) matrixmodel.getData();
					AppContext.debug( table.getPosition().length  + " x " +  table.getVarid().length  + "  SNPs matrix");
					*/
					


				} catch(Exception e) {
					e.printStackTrace();
				}
				divDiscovery.setVisible(false);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private Set getCommonClass() {
		
		Set sClass=new HashSet();
		sClass.add("Bam(BamNative)");
	sClass.add("BamNative(CompressedArchive)");
	sClass.add("BaseBcf(CompressedArchive)");
	sClass.add("BigBed(BigWig)");
	sClass.add("Data(object)");
	sClass.add("Genbank(data.Text)");
	sClass.add("Gff(Tabular, _RemoteCallMixin)");
	sClass.add("Image(data.Data)");
	sClass.add("Interval(Tabular)");
	sClass.add("Newick(Text)");
	sClass.add("Nexus(Text)");
	sClass.add("Obo(Text)");
	sClass.add("Phylip(Text)");
	sClass.add("Phyloxml(GenericXml)");
	sClass.add("ProBam(Bam)");
	sClass.add("QualityScore(data.Text)");
	sClass.add("Sam(Tabular)");
	sClass.add("Sbml(GenericXml)");
	sClass.add("Sequence(data.Text)");
	sClass.add("Sequences(sequence.Fasta)");
	sClass.add("Triples(data.Data)");
	return sClass;
	}	
	private Set getCommonExt() {
		
		Set sExt=new HashSet();
	sExt.add("ab1");
	sExt.add("bam");
	sExt.add("bcf");
	sExt.add("bcf_uncompressed");
	sExt.add("bed");
	sExt.add("bed12");
	sExt.add("bed6");
	sExt.add("bedgraph");
	sExt.add("bedstrict");
	sExt.add("bigbed");
	sExt.add("bigwig");
	sExt.add("blastdbd");
	sExt.add("blastdbn");
	sExt.add("blastdbp");
	sExt.add("blastxml");
	sExt.add("blib");
	sExt.add("csv");
	sExt.add("data");
	sExt.add("dbn");
	sExt.add("dcd");
	sExt.add("eps");
	sExt.add("excel.xls");
	sExt.add("fasta");
	sExt.add("fastg");
	sExt.add("fastq");
	sExt.add("fastqcssanger");
	sExt.add("fastqillumina");
	sExt.add("fastqsanger");
	sExt.add("fastqsolexa");
	sExt.add("featurexml");
	sExt.add("genbank");
	sExt.add("gff");
	sExt.add("gff3");
	sExt.add("gif");
	sExt.add("html");
	sExt.add("interval");
	sExt.add("ipynb");
	sExt.add("jpg");
	sExt.add("json");
	sExt.add("maf");
	sExt.add("mol");
	sExt.add("mol2");
	sExt.add("netcdf");
	sExt.add("newick");
	sExt.add("obo");
	sExt.add("owl");
	sExt.add("pbed");
	sExt.add("pbm");
	sExt.add("pcd");
	sExt.add("pcx");
	sExt.add("pdb");
	sExt.add("pdf");
	sExt.add("phar");
	sExt.add("phe");
	sExt.add("pheno");
	sExt.add("phylip");
	sExt.add("phyloxml");
	sExt.add("png");
	sExt.add("postgresql");
	sExt.add("psd");
	sExt.add("raw");
	sExt.add("rdata");
	sExt.add("rdf");
	sExt.add("sam");
	sExt.add("sbml");
	sExt.add("sequences");
	sExt.add("sqlite");
	sExt.add("svslide");
	sExt.add("tiff");
	sExt.add("tsv");
	sExt.add("txt");
	sExt.add("vcf");
	sExt.add("vcf_bgzip");
	sExt.add("wig");
	sExt.add("xlsx");
	sExt.add("xml");
	sExt.add("zip");
	
	return sExt;
	}


}