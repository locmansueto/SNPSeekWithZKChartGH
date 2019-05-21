package org.irri.iric.portal.galaxy.zkui;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONObject;
import org.irri.iric.galaxy.service.GalaxyFacade;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.AsyncJob;
import org.irri.iric.portal.admin.AsyncJobImpl;
import org.irri.iric.portal.admin.AsyncJobReport;
import org.irri.iric.portal.admin.JobsFacade;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.genotype.zkui.Object2StringMultirefsMatrixModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.zul.Biglistbox;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
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
	//@Autowired
	//@Qualifier("JobsFacade")
	//private JobsFacade jobsfacade;

	
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
	@Wire
	private Button buttonUpload;
	@Wire
	private Fileupload uploadButton;
	
	public GalaxyController() {
		super();
	}


	public void updateDisplay() {
		
	}
	
	@Listen("onSelect =#listboxServer")
	public void onselectServer() {
		try {
			AppContext.setGalaxyInstance(listboxServer.getSelectedItem().getLabel() );
			init();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public Object[] onclickRun_(boolean sync) {
		
		Object[] ret=null;
		try {
			galaxy= (GalaxyFacade)AppContext.checkBean(galaxy,"GalaxyFacade");
			
			Map<String,String[]> mapParam2Value=new HashMap();
			for(Object o:listInputs.getRows().getChildren()) {
				List rowcols= ((Row)o).getChildren();
				String param=((Label)rowcols.get(0)).getValue();
				String value=((Textbox)rowcols.get(1)).getValue().trim();

				String typestepno[]=((Textbox)rowcols.get(3)).getValue().trim().split("\\:");
				String step=null;
				if(typestepno.length>1) step=typestepno[1];
				mapParam2Value.put( param  ,new String[] {value, typestepno[0],step});
			}
			
			
			String jobid=null;
			
			Object[] selobj=listTools.getSelectedItem().getValue();		
			List paramlist=new ArrayList();
			if(selobj[0] instanceof Tool) {
			}
			else if(selobj[0] instanceof Workflow) {
				Workflow wf=(Workflow)selobj[0];
				
				//jobid="galaxywf-"+  wf.getId().replace(" ","_").replace("-","_") + "-" + AppContext.createTempFilename();
				jobid="galaxywf-"+  wf.getName().replace(" ","_").replace(":","_").replace("-","_").toLowerCase() + "-" + AppContext.createTempFilename() ;
				if(sync) {
					AppContext.debug("running synch job " + jobid);
					AppContext.debug("Executing wf " + wf.getName() + " with params " + mapParam2Value);
					String[] status=galaxy.runWorkflow(wf, mapParam2Value, jobid); 
					AppContext.debug("runWorkflow Synch result:" + status[0]);
					if(status[0].equals(JobsFacade.JOBSTATUS_DONE)) {
						//AppContext.renameFile(new File(AppContext.getTempDir()+status[1]+".zip"),  AppContext.getTempDir()+jobid+".zip");
						try {
						Filedownload.save(new File(AppContext.getTempDir()+jobid+".zip"), "application/zip");
						} catch(Exception ex) {
							ex.printStackTrace();
							try {
							Filedownload.save(new File(jobid+".zip"), "application/zip");
							} catch(Exception ex2) {
								ex2.printStackTrace();
							}
							
						}
					}
					ret=status;
				}
				else {
					AppContext.debug("running asynch job " + jobid);
					AppContext.debug("Submitting asynch wf " + wf.getName() + " with params " + mapParam2Value);
					/*
					Future future=galaxy.runWorkflowAsync(wf, mapParam2Value, jobid, new File(AppContext.getTempDir()+jobid)); 
					ret=new Object[] {jobid,future};
					*/
					String[] status=galaxy.runWorkflowAsync(wf, mapParam2Value, jobid); 
					
					
					if (status[0].equals(JobsFacade.JOBSTATUS_DONE)) {
						try {
							Filedownload.save(new File(AppContext.getTempDir()+jobid+".zip"), "application/zip");
							} catch(Exception ex) {
								ex.printStackTrace();
								try {
								Filedownload.save(new File(jobid+".zip"), "application/zip");
								} catch(Exception ex2) {
									ex2.printStackTrace();
								}
								
							}					
					}
					else if (status[0].equals(JobsFacade.JOBSTATUS_SUBMITTED)) {
						this.labelDownloadProgressMsg.setValue("Job is submitted. Please monitor progress at ");
						this.aDownloadProgressURL.setLabel( AppContext.getHostname() + AppContext.getHostDirectory() + "_jobs.zul?jobid="  + jobid);
						this.aDownloadProgressURL.setHref( AppContext.getHostname() + AppContext.getHostDirectory() + "_jobs.zul?jobid="  + jobid);
						//enableDownloadButtons(false);
					} else
						this.labelDownloadProgressMsg.setValue((String)status[0] + ": " + jobid);
					
					
				}
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			Messagebox.show(ex.getMessage(), "ERROR",  Messagebox.OK,Messagebox.EXCLAMATION);
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
		
			onclickRun_(false);
			labelDownloadProgressMsg.setVisible(true);
			aDownloadProgressURL.setVisible(true);
			
			
		}
	}
	
//	public Callable<AsyncJobReport> callableGalaxyRun() {
//		return new Callable<AsyncJobReport>() {
//			@Override
//			public AsyncJobReport call() throws Exception {
//				String msg = "";
//				String jobid = "";
//				String url = "";
//				Future futureReportjob = null;
//				try {
//
//					jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
//
//					/*
//					if (params.getSubmitter() == null) {
//						msg = "Submitter ID required for long jobs.";
//					} else if (jobsfacade.checkSubmitter(params.getSubmitter())) {
//						msg = "You have a running long job. Please try again when that job is done.";
//					} else 
//					*/
//					if(true){
//						Object ret[]=onclickRun_(false); 
//
//						AsyncJob job = new AsyncJobImpl((String)ret[0],"galaxy_run",
//								"submitter");
//						futureReportjob = (Future)ret[1]; // genotype.querydownloadGenotypeAsync(params);
//						job.setFuture(futureReportjob);						
//						jobid = job.getJobId();
//						url = job.getUrl();
//						msg = jobsfacade.JOBSTATUS_SUBMITTED;
//						if (jobsfacade.addJob(job)) {
//							//Object ret[]=onclickRun_(false); 
//							AppContext.debug( "Added job " + jobid);
//						} else {
//							//msg = jobsfacade.JOBSTATUS_REFUSED;
//							AppContext.debug( "Refused job " + jobid);
//						}
//					}
//						
//					AppContext.debug("callable galaxy run.. submitted");
//				} catch (Exception ex) {
//					ex.printStackTrace();
//					// Messagebox.show("call():" + ex.getMessage());
//					msg = ex.getMessage();
//
//				}
//				return new AsyncJobReport(jobid, msg, url, futureReportjob);
//			}
//		};
//	}
//
//	
	
	@Listen("onUpload =#uploadButton")
	public void onUpload(Event event) {
		AppContext.debug(event.toString());
		try {
			org.zkoss.util.media.Media media = ((UploadEvent) event).getMedia();
				media.getStringData();
				Messagebox.show("Upload " + media.getName());

		} catch (Exception ex) {
			AppContext.debug(ex.getMessage());
			ex.getStackTrace();
			Messagebox.show("Upload failed");
		}
	}

		
	@Command
    public void onFileUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();

        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;

            Media media = upEvent.getMedia();

            //Initialize components
            String name = media.getName();
            String format = media.getFormat();

            byte[] bFile = media.getByteData();

            AppContext.debug(String.format("File Name: %s, Format: %s" , name, format ));
        }
    }
	
	@Listen("onClick =#buttonUpload")
	public void onclickUploadLists() {

		Fileupload.get(new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				

				try {
					org.zkoss.util.media.Media media = ((UploadEvent) event).getMedia();
					media.getStringData();
						Messagebox.show("Upload " + media.getName());

				} catch (Exception ex) {
					AppContext.debug(ex.getMessage());
					ex.getStackTrace();
					Messagebox.show("Upload failed");
				}
			}
		});

	}
	
	@Listen("onSelect =#listTools")
	public void onselectTools() {
		try {
		Object[] selobj=listTools.getSelectedItem().getValue();
		//String haplofilename=(String)getSession().getAttribute("haplofilename");
		Map<String,String> mapParams=(Map)getSession().getAttribute("param_vals");
		
		List paramlist=new ArrayList();
		if(selobj[0] instanceof JSONObject) {
			((Listheader)listTools.getListhead().getFirstChild()).setLabel("Tool name");
			for(String p:(Set<String>)selobj[1]) {
				paramlist.add(new String[] {p,""});
			}
		}
		else if(selobj[0] instanceof Workflow) {
			for(String p:(Set<String>)selobj[1]) {
				
				String paramval="";
				if(mapParams!=null) {
					for(String pembed:mapParams.keySet()) {
						if( p.startsWith(pembed)) {
							paramval=(String)mapParams.get(pembed);
							AppContext.debug(p + " = " + paramval);
						}
					}
				}
				if(paramval==null) paramval="";
				
				paramlist.add(new String[] {p,paramval});
				
				/*
				if(haplofilename!=null) {
					if(p.startsWith("locuslist"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".bed"});
					else if(p.startsWith("snpmatrix"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".ped"});
					else if(p.startsWith("snplist"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".map"});
					else if(p.startsWith("samplelist"))  paramlist.add(new String[] {p,AppContext.getTempDir()+haplofilename+".txt"});
				}
				else {
					String paramval=(String)getSession().getAttribute(p);
					if(paramval==null) paramval="";
					paramlist.add(new String[] {p,paramval});
				}
				*/
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
		((Listheader)listTools.getListhead().getFirstChild()).setLabel("Workflow name");

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
		((Listheader)listTools.getListhead().getFirstChild()).setLabel("Tool name");

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
		Map<String,Set<JSONObject>> mapName2Tools=mapst[2];
		Map<JSONObject,Set<String>> mapTools2Params=mapst[1];
		
		// rank tools by number of matching terms
		Map<JSONObject,Set<String>> mapTool2Terms=new HashMap();
		for(String name:mapName2Tools.keySet()) {
			for(JSONObject t:mapName2Tools.get(name)) {
				Set terms=mapTool2Terms.get(t);
				if(terms==null) {
					terms=new HashSet();
					mapTool2Terms.put(t, terms);
				}
				terms.add(name);
			}
		}
		List<Object[]> lTools=new ArrayList();
		for(JSONObject t:mapTool2Terms.keySet()) {
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

	private void init() {
		try {
			galaxy= (GalaxyFacade)AppContext.checkBean(galaxy,"GalaxyFacade");
			
			
			String embed=Executions.getCurrent().getParameter("embed");
			
			if(embed==null || embed.isEmpty()) {
			
			//mapData2Tools,mapTools2Params,mapName2Tools,mapExt2Tools
			
				Map[] maps=galaxy.discoverTools();

				List l=new ArrayList();
				l.addAll(new TreeSet(maps[0].keySet()));
				ListModelList m=new ListModelList( new ArrayList(l));
				m.setMultiple(true);
				listData.setModel(m);

				l=new ArrayList();
				l.addAll(new TreeSet(maps[3].keySet()));
				m=new ListModelList( new ArrayList(l));
				m.setMultiple(true);
				listFormat.setModel(m);

				l=new ArrayList();
				l.addAll(new TreeSet(maps[4].keySet()));
				m=new ListModelList( new ArrayList(l));
				m.setMultiple(true);
				listSection.setModel(m);

			/*
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
			*/
				listTools.setModel(new ListModelList( new ArrayList()));
				listInputs.setModel(new ListModelList( new ArrayList()));
			
			}
			
		
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
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		
		super.doAfterCompose(comp);		
		init();
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