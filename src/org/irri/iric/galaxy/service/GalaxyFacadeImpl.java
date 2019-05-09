package org.irri.iric.galaxy.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.codehaus.jettison.json.JSONObject;
//import org.zkoss.json.JSONObject;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.JobsFacade;
import org.irri.iric.portal.genotype.VariantStringData;
import org.python.core.PyDictionary;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Filedownload;

import com.github.jmchilton.blend4j.galaxy.GalaxyInstance;
import com.github.jmchilton.blend4j.galaxy.GalaxyInstanceFactory;
import com.github.jmchilton.blend4j.galaxy.HistoriesClient;
import com.github.jmchilton.blend4j.galaxy.JobsClient;
import com.github.jmchilton.blend4j.galaxy.ToolsClient;
import com.github.jmchilton.blend4j.galaxy.WorkflowsClient;
import com.github.jmchilton.blend4j.galaxy.ToolsClient.FileUploadRequest;
import com.github.jmchilton.blend4j.galaxy.beans.History;
import com.github.jmchilton.blend4j.galaxy.beans.HistoryContents;
import com.github.jmchilton.blend4j.galaxy.beans.HistoryExport;
import com.github.jmchilton.blend4j.galaxy.beans.Job;
import com.github.jmchilton.blend4j.galaxy.beans.Tool;
import com.github.jmchilton.blend4j.galaxy.beans.ToolInputs;
import com.github.jmchilton.blend4j.galaxy.beans.ToolParameter;
import com.github.jmchilton.blend4j.galaxy.beans.ToolSection;
import com.github.jmchilton.blend4j.galaxy.beans.Workflow;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowDetails;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInputDefinition;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowInputs;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowOutputs;
import com.sun.jersey.api.client.ClientResponse;


@Service("GalaxyFacade")
@EnableAsync
public class GalaxyFacadeImpl implements GalaxyFacade {

	 //private static PythonInterpreter interp=null; 
    

	  public static Object exists(Collection c, String id) {
		  for(Object co:c) {
			  if( ((History)co).getName().equals(id)) {
				  return co;
			  }
		  }
		  return null;
	  }
	  
	  /**
	   * Search for workflows that accepts varlist, snplist or locuslist (tabular,text)
	   * 
	   * snplist or locuslist (gff,BED)
	   * 
	   * genotype matrix (VCF,Plink)
	   */
	  @Override
	  public Map[] discoverWorkflows(Set<String> nameStarts) {

		  AppContext.debug("discoverWorkflows(Set<String> nameStarts)");
		  Map<String,Set<Workflow>> mapName2Wf=new HashMap();
		  Map<Workflow, Set<String>> mapWf2Params=new HashMap();

		  try {
		  final GalaxyInstance instance = GalaxyInstanceFactory.get(AppContext.getGalaxyAddress(),AppContext.getGalaxyKey());
		  final WorkflowsClient workflowsClient = instance.getWorkflowsClient();
		  
		  
		  for(Workflow wf:workflowsClient.getWorkflows()) {
			  System.out.println(wf.getName());
			  
			  	WorkflowDetails workflowDetails = workflowsClient.showWorkflow(wf.getId());
			  	workflowDetails.getUrl();
			  	PyList cxxTestGenArgs = new PyList(); 
			  	Set params=new HashSet();
			    for(final Map.Entry<String, WorkflowInputDefinition> inputEntry : workflowDetails.getInputs().entrySet()) {
			    	  String label = inputEntry.getValue().getLabel();
				      for(String n:nameStarts) {
				    	  if(label.toLowerCase().startsWith(n.toLowerCase())) {
				    		  Set setWf=mapName2Wf.get(n);
				    		  if(setWf==null) {
				    			  setWf=new HashSet();
				    			  mapName2Wf.put(n,setWf);
				    		  }
				    		  setWf.add(wf);
				    	  }
				      }
				      params.add(label);
			    }
			    if(params.size()>0) mapWf2Params.put(wf,params);
		  }
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  return new Map[] {mapName2Wf, mapWf2Params};
	  }
	  
	  private Set findMatches(Set setMatch, PyList dfxlist, Map<String,String> mapDFX2Class, String tag) {
		  Set matched=new HashSet();
		  Iterator<String> it=dfxlist.iterator();
		  while(it.hasNext()){
			  Object o=it.next();
			  if(o==null) continue;
			  String dfx=o.toString();
			  String classtype=mapDFX2Class.get(dfx);
			  System.out.println("dfx=" + dfx + "  class=" + classtype);
			  if(classtype==null) {
				  AppContext.debug("No " + tag + " class for tag " + dfx);
			  } else {
				  if(setMatch.contains(dfx))  matched.add(classtype);
			  }
		  }
		  return matched;
	  }
	  
	  @Override
	  public List getSections(){
		  List list=new ArrayList();
			AppContext.debug("discoverTools(Set<String> nameStarts, Set<String> formats, Set<String> sections)");
			 try {
				  final GalaxyInstance instance = GalaxyInstanceFactory.get(AppContext.getGalaxyAddress(),AppContext.getGalaxyKey());
				  final ToolsClient toolClient = instance.getToolsClient();
				  for(ToolSection ts:toolClient.getTools()) {
					  list.add(ts.getName());
				  }
			 } catch(Exception ex) {
				 ex.printStackTrace();
			 }
			 return list;
	  }
	  
	
	  public boolean exec_with_timeout(PythonInterpreter pi , String cmd, long ms) {
		    Thread jythonThread = new Thread(new Runnable() {
		      @Override
		      public void run() {
		    	  try {
		    		  pi.exec(cmd);
		    	  } catch(Exception ex) {
		    		  ex.printStackTrace();
		    	  }
		      }
		    });
		    jythonThread.start();
	
		    new Thread(new Runnable() {
		      @Override
		      public void run() {
		        try {
		          Thread.sleep(ms);
		          jythonThread.interrupt();
		        }
		        catch (InterruptedException e) {
		          e.printStackTrace();
		        }
		      }
		    }).start();
		    
		    while(jythonThread.isAlive()) {
		    	if(jythonThread.isInterrupted()) return false;
		    	try {
		    		Thread.sleep(1000);
		    	}
		    	catch(Exception ex) {
		    		ex.printStackTrace();
		    	}
		    }
	    	if(jythonThread.isInterrupted()) return false;
		    return true;
	  }
	    
	  @Override
	  public Map[] discoverTools(Set<String> nameStarts, Set<String> classes, Set<String> formats, Set<String> sections) {
			AppContext.debug("discoverTools(Set<String> nameStarts, Set<String> formats, Set<String> sections)");

			 //{mapClass2Dat, mapDat2Class, mapClass2For, mapFor2Class, mapClass2Ext, mapExt2Class,mapClass2Class};
			Set skipToolId=new HashSet();
			skipToolId.add( "Convert characters1");
			skipToolId.add( "52.76.88.51/repos/dereeper/numeric_matrix_encoder/bglr:encode/1.0.3");
			skipToolId.add( "toolshed.g2.bx.psu.edu/repos/iuc/bedtools/bedtools_jaccard/2.27.1+galaxy1");
			skipToolId.add( "GeneBed_Maf_Fasta2");
			skipToolId.add( "__EXTRACT_DATASET__");
			
			Map mapClass2Dat=DatatypeMaps.getDatatypes()[0];		
			Map mapClass2Format=DatatypeMaps.getDatatypes()[2];		
			
			for(String s:classes) {
				formats.add((String)mapClass2Dat.get(s));
				String f=(String)mapClass2Format.get(s);
				if(f!=null) formats.add(f);
			}
			formats.remove("");
			formats.remove("null");
			
			AppContext.debug("match edams=" + formats);
			
			Map<Tool,Set<String>> mapTools2Params=new HashMap();
			
	       Map<String,Set<Tool>> mapName2Tools=new HashMap();
	       Map<String,Set<Tool>> mapData2Tools=new HashMap();

		  try {
		  final GalaxyInstance instance = GalaxyInstanceFactory.get(AppContext.getGalaxyAddress(),AppContext.getGalaxyKey());
		  final ToolsClient toolClient = instance.getToolsClient();
		  
		   //PythonInterpreter interp = AppContext.getPythonInterp();
		  PythonInterpreter interp = new PythonInterpreter(); //AppContext.getPythonInterp();
		  
	       interp.exec("import sys");
	       interp.exec("from java.lang import Exception");
	       interp.exec("from bioblend.galaxy import GalaxyInstance");
	       interp.exec("from bioblend.galaxy.tools import ToolClient");
	       interp.exec("gi = GalaxyInstance(url='" + AppContext.getGalaxyAddress() + "', key='" + AppContext.getGalaxyKey()+ "')");
	       //interp.exec("gi.set_get_max_retries(5)");
	       interp.exec("print(\"retrydelay= \" + str(gi.get_retry_delay))");
	       interp.exec("print(\"maxattempts= \" + str(gi.max_get_attempts))");
	       interp.exec("toolClient = ToolClient(gi)");
	       interp.exec("toolClient.set_max_get_retries(5)");
	   
	       //mapAll=new Map[] {mapClass2Dat, mapDat2Class, mapClass2For, mapFor2Class, mapClass2Ext, mapExt2Class};
			
	       Map<String,String> mapDatatypes[]=DatatypeMaps.getDatatypes();
	       //{mapClass2Dat, mapDat2Class, mapClass2For, mapFor2Class, mapClass2Ext, mapExt2Class}
	       int sectioncnt=1;
	       Set setMatchData=new HashSet();
	       Set setMatchFormat=new HashSet();
	       Set setMatchExt=new HashSet();
		  for(ToolSection wf:toolClient.getTools()) {
			  
			  System.out.println(sectioncnt + "   "  + wf.getName().toUpperCase());
			  sectioncnt++;
			  if(sections!=null && !sections.contains(wf.getName())) continue;	
		       int toolcnt=1;
		       
		       
			  for(Tool t:wf.getElems()) {
				  System.out.println(toolcnt + "   " +  t.getId() + "  " + t.getName() + "  " + t.getDescription() + "  " + t.getUrl());

				  if(t.getName()==null) {
					  continue;
				  }
				  
			      // JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
				  if(t.getUrl()!=null && !t.getUrl().isEmpty()) {
					  JSONObject jsonObj=new JSONObject(AppContext.readURL( AppContext.getGalaxyAddress() + t.getUrl() ));
					  System.out.println(jsonObj.toString(4));
				  }
				  //jsonObj.keys()
				  
				  toolcnt++;

				  if(skipToolId.contains(t.getId())) continue;
				
				  
				  try {
					  //interp.exec("try:\n    ti=toolClient.show_tool(tool_id='" + t.getId() + "',io_details=True)\n    print(ti)\nexcept Exception as e:\n    print(str(e))\n    instance = sys.exc_info()[1]");
					  boolean done=exec_with_timeout(interp,"try:\n    ti=toolClient.show_tool(tool_id='" + t.getId() + "',io_details=True)\n    print(ti)\nexcept Exception as e:\n    print(str(e))\n    instance = sys.exc_info()[1]",250);
					  if(!done) {
						  System.out.println("Interrupted: " + t.getId() + "  " + t.getName());
						  
						  
						   //interp.close();
						   interp = new PythonInterpreter(); //AppContext.getPythonInterp();
						  
					       interp.exec("import sys");
					       interp.exec("from java.lang import Exception");
					       interp.exec("from bioblend.galaxy import GalaxyInstance");
					       interp.exec("from bioblend.galaxy.tools import ToolClient");
					       interp.exec("gi = GalaxyInstance(url='" + AppContext.getGalaxyAddress() + "', key='" + AppContext.getGalaxyKey()+ "')");
					       //interp.exec("gi.set_get_max_retries(5)");
					       interp.exec("print(\"retrydelay= \" + str(gi.get_retry_delay))");
					       interp.exec("print(\"maxattempts= \" + str(gi.max_get_attempts))");
					       interp.exec("toolClient = ToolClient(gi)");
					       interp.exec("toolClient.set_max_get_retries(5)");

					       
						  continue;
					  }
					  
				  } catch(Exception ex) {
					  ex.printStackTrace();
					  Object ins= interp.get("instance");
					  if(ins==null) {
						  System.out.println("interp.get(\"instance\")==null");
						  continue;
					  }
					  System.out.println(ins.getClass());
					  System.out.println(ins);
					  
					  continue;
				  }
				  interp.exec("hasti = 'no'\nif \"ti\" in self.__dict__:\n    hasti = 'yes'");
				  if( interp.get("ti")==null ||  ((PyObject)interp.get("hasti")).toString().equals("no")) {
					  System.out.println("ti not defined");
					  continue;
				  }
				  interp.exec("if 'inputs' in ti.keys():\n    inputParams = ti['inputs']\nelse:\n    inputParams=[]\n");
				  //System.out.println(interp.get("inputParams"));
				  //System.out.println(interp.get("inputParams").getClass());
				  PyList pl=(PyList)interp.get("inputParams");

				  Set<String> matchedData=new LinkedHashSet();
				  Set<String> matchedName=new LinkedHashSet();
				  
				  Set params=new LinkedHashSet();
				  
				  Iterator<PyDictionary> itPl=pl.iterator();
				  while(itPl.hasNext()) {
					  PyDictionary  pdict=itPl.next();
					  String pname=(String)pdict.get("name");
					  String paramprops=pdict.get("name") + ":" +  pdict.get("type") + ":" + pdict.get("extensions") +":" + pdict.get("label");
					  System.out.println( "    " + paramprops);
					  for(String n:nameStarts) {
						  if(pname.toLowerCase().startsWith(n.toLowerCase())) {
							  matchedName.add(pname+":"+ pdict.get("extensions"));
							  //matchedName.add(pname);
						  }
					  }
					  if(pdict.get("type").equals("data")) {
						  PyDictionary edams= (PyDictionary)pdict.get("edam");
						  if(edams==null || edams.equals("None")) {} 
						  else {
							  PyList datalist= (PyList)edams.get("edam_data");
							  PyList formatlist=  (PyList)edams.get("edam_formats");
							  
							  System.out.println(datalist + "\n" + formatlist);
							  if(datalist!=null && !datalist.equals("None")) matchedData.addAll(findMatches(formats, datalist, mapDatatypes[1], "edam_data"));
							  if(formatlist!=null && !formatlist.equals("None")) matchedData.addAll(findMatches(formats, formatlist, mapDatatypes[3], "edam_format"));
						  }
					  }
					  PyList extslist= (PyList)pdict.get("extensions");
					  if(extslist!=null && !extslist.equals("None")) {
						  matchedData.addAll(findMatches(formats, extslist, mapDatatypes[5], "file_ext"));
						  System.out.println(extslist);
					  }
					  params.add(pdict.get("name") + ":" +  pdict.get("type") + ":" + pdict.get("extensions") +":" + pdict.get("label")  );
				  }
				  if(matchedData.size()>0) {
					  mapTools2Params.put(t, params);
					  System.out.println("matchedParams=" + matchedData);
					  interp.exec("print(inputParams)");
				  }
				  interp.exec("del ti");
				  
				  for(String term:matchedData) {
					 Set setTools=mapData2Tools.get(term);
					 if(setTools==null) {
						 setTools=new LinkedHashSet();
						 mapData2Tools.put(term, setTools);
					 }
					 setTools.add(t);
				  }
				  for(String term:matchedName) {
					 Set setTools=mapData2Tools.get(term);
					 if(setTools==null) {
						 setTools=new LinkedHashSet();
						 mapName2Tools.put(term, setTools);
					 }
					 setTools.add(t);
				  }
			  }
		  }
		  
		  interp.exec("del gi"); 
		  
		  mapData2Tools.putAll(mapName2Tools);
		  AppContext.debug("done loop");
		  //interp.cleanup();
		  //interp.close();
		  AppContext.debug("done close");
		  
		  } catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  return new Map[] {mapData2Tools,mapTools2Params};
	  }
	  
	  
	  @Override
	 public List<String> getDatatypes() {
		  return new ArrayList(DatatypeMaps.getDatatypes()[0].keySet());
		  //Map[] {mapClass2Dat, mapDat2Class, mapClass2For, mapFor2Class, mapClass2Ext, mapExt2Class};
			
	 }
	  @Override
	 public List<String> getDataformats() {
		  return new ArrayList(DatatypeMaps.getDatatypes()[3].keySet());
	 }
	  @Override
	 public List<String> getDataclass() {
		  return new ArrayList(DatatypeMaps.getDatatypes()[6].keySet());
	 }

	  @Override
	 public List<String> getFileexts() {
		  return new ArrayList(DatatypeMaps.getDatatypes()[5].keySet());
		 
	 }
	  
	  private void writeStatus(String msg) {
		  
	  }
	  
	@Async("threadPoolTaskExecutor")
	public  Future  runWorkflowAsync(Workflow matchingWorkflow, Map<String,String>  mapInputname2Filename,
				  String jobid, File outfile) { 
			while(true) {
				String status=runWorkflow_( matchingWorkflow,  mapInputname2Filename,
						   jobid,  outfile);

				if(status.equals("new"))
					writeStatus(JobsFacade.JOBSTATUS_SUBMITTED);
				else if(status.equals("ok")) 
					writeStatus(JobsFacade.JOBSTATUS_DONE);
				else if(status.equals("queued"))
					writeStatus(JobsFacade.JOBSTATUS_SUBMITTED);
				else if(status.equals("running"))
					writeStatus(JobsFacade.JOBSTATUS_STARTING);
				else { 
					writeStatus(JobsFacade.JOBSTATUS_ERROR);
					AppContext.debug("status=" + status);
					return new AsyncResult<String>(null); //JobsFacade.JOBSTATUS_ERROR;
				}
				if(status.equals("ok")) {
					// copy from galaxy to local server
					break;				
				}
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch(Exception ex) {
					ex.printStackTrace();
				};
			}
			return  new AsyncResult<String>(null); //JobsFacade.JOBSTATUS_DONE;
	}
		
	 @Override 
	 public  String  runWorkflow(Workflow matchingWorkflow, Map<String,String>  mapInputname2Filename,
			  String jobid, File outfile) { 
		while(true) {
			String status=runWorkflow_( matchingWorkflow,  mapInputname2Filename,
					   jobid,  outfile);

			if(status.equals("new"))
				writeStatus(JobsFacade.JOBSTATUS_SUBMITTED);
			else if(status.equals("ok")) 
				writeStatus(JobsFacade.JOBSTATUS_DONE);
			else if(status.equals("queued"))
				writeStatus(JobsFacade.JOBSTATUS_SUBMITTED);
			else if(status.equals("running"))
				writeStatus(JobsFacade.JOBSTATUS_STARTING);
			else { 
				writeStatus(JobsFacade.JOBSTATUS_ERROR);
				AppContext.debug("status=" + status);
				return JobsFacade.JOBSTATUS_ERROR;
			}
			if(status.equals("ok")) {
				// copy from galaxy to local server
				break;				
			}
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch(Exception ex) {
				ex.printStackTrace();
			};
		}
		return JobsFacade.JOBSTATUS_DONE;
	 }
	 
	 private  String  runWorkflow_(Workflow matchingWorkflow, Map<String,String>  mapInputname2Filename,
			  String jobid, File outfile) {
		 
			 
	    final GalaxyInstance instance = GalaxyInstanceFactory.get(AppContext.getGalaxyAddress(), AppContext.getGalaxyKey());
	    final WorkflowsClient workflowsClient = instance.getWorkflowsClient();
	    
	    // Find history
	    final HistoriesClient historyClient = instance.getHistoriesClient();
	    
	    History histNewjob=(History)exists( historyClient.getHistories(),jobid);
	    if(histNewjob!=null) {
	    	// already created, get result (last dataset in history)
	    	System.out.println("Retrieving previous run result"); 
	    	
			try {
	    	
			    	boolean success=true;
			    	boolean running=false;
			    	boolean queued=false;
			    	boolean newjob=false;
			    	
			    	JobsClient jobClient = instance.getJobsClient();
			    	for(Job j:jobClient.getJobsForHistory(histNewjob.getId())) {
			    		System.out.println("job tool:" + j.getToolId() + " " +  j.getState() + " " + j.getUpdated());    
			    		success= success && j.getState().equals("ok"); 
			    		running=running || j.getState().equals("running");
			    		queued=queued || j.getState().equals("queued");
			    		newjob=newjob || j.getState().equals("new");
			    		
			    	}
			    	if(!success ) {
			    		if(running) return "running";
			    		if(queued) return "queued";    		
			    		if(newjob) return "new";    		
			    		return "failed";
			    	}
			    	
			    	HistoryContents outputDataset=null;
			        for(HistoryContents historyDataset : historyClient.showHistoryContents(histNewjob.getId())) {
			        		outputDataset=historyDataset;
			        }
			        AppContext.debug("Downloading history from galaxy to snpseek/temp " + outfile.getName() );
			        
			        //historyClient.export_history(histNewjob.getId()); //, gzip=True, include_hidden=False, include_deleted=False, wait=False, maxwait=None)
			        
			        HistoryExport histexp=historyClient.exportHistory(histNewjob.getId());
			        long seccnt=0;
			        while(!histexp.isReady()) {
			        	TimeUnit.SECONDS.sleep(30);
			        	seccnt+=30;
			        	if(seccnt>60*60) {
			        		AppContext.debug("Failed historyClient.exportHistory");
			        		return "failed";
			        	}
			        }
			        
			        URL url = new URL(AppContext.getGalaxyAddress() + histexp.getDownloadUrl());
			        try (InputStream in = url.openStream()) {
			           //Files.copy(in, Paths.get("someFile.jpg"), StandardCopyOption.REPLACE_EXISTING);
			        	Files.copy(in, outfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			        } catch (IOException e) {
			           // handle exception
			        	e.printStackTrace();
			        	return "failed";
			        }
			        
			        //historyClient.dow 
			        //historyClient.downloadDataset(histNewjob.getId(), outputDataset.getId(), outfile);
			    	return "ok";
			 } catch(Exception ex) {
				 ex.printStackTrace();
			 }
			return "failed";
	    }
	    
	    try {
	    
		    histNewjob= historyClient.create(new History(jobid));
		    
		    final ToolsClient toolsClient = instance.getToolsClient();
		    
		    for(String inpname:mapInputname2Filename.keySet()) {
		    	System.out.println("loading param " + inpname + " from file " + mapInputname2Filename.get(inpname));
		    	ClientResponse resp=toolsClient.uploadRequest(new FileUploadRequest(histNewjob.getId(),new File(mapInputname2Filename.get(inpname))));
		    }
	
		    //respSAmplelist.getEntity()
		    
		    Map<String,String> mapInputname2Datasetid=new HashMap();
		    //toolsClient.upload(new FileUploadRequest(histNewjob.getId(),snplist));
			//  Assert.assertNotNull(histNewjob);
			  String input1Id = null;
			  String input2Id = null;
			  for(final HistoryContents historyDataset : historyClient.showHistoryContents(histNewjob.getId())) {		  
			    //System.out.println("Hist dataset:" + historyDataset.getId() + " " + historyDataset.getName());		    
			    for(String inpname:mapInputname2Filename.keySet()) {
			    	String shortname=new File(mapInputname2Filename.get(inpname)).getName();
			    	if(historyDataset.getName().equals(shortname)) {
			    		mapInputname2Datasetid.put(inpname , historyDataset.getId());
			    		break;
			    	}
			    	
			    }
			  }
		   
		    final WorkflowDetails workflowDetails = workflowsClient.showWorkflow(matchingWorkflow.getId());
		    String workflowInput1Id = null;
		    String workflowInput2Id = null;
		    
		    Map<String,String> mapInpname2InputEntryKey=new HashMap();
		    
		    
		    for(final Map.Entry<String, WorkflowInputDefinition> inputEntry : workflowDetails.getInputs().entrySet()) {
		      String label = inputEntry.getValue().getLabel();
		      for(String inpname:mapInputname2Filename.keySet()) {
			    	if( label.equals(inpname)) {
			    		mapInpname2InputEntryKey.put(label,  inputEntry.getKey());
			    		break;
			    	}
			    }
		    }
	
		    final WorkflowInputs inputs = new WorkflowInputs();
		    inputs.setDestination(new WorkflowInputs.ExistingHistory(histNewjob.getId()));
		    inputs.setWorkflowId(matchingWorkflow.getId());
		    
		    
		    for(String inpname:mapInpname2InputEntryKey.keySet()) {
		    	
		    	System.out.println("setting param=" + inpname +  ",  inputentryid=" + mapInpname2InputEntryKey.get(inpname) + ", datasetid=" + mapInputname2Datasetid.get(inpname));
		    	
			    inputs.setInput(mapInpname2InputEntryKey.get(inpname), new WorkflowInputs.WorkflowInput(mapInputname2Datasetid.get(inpname), WorkflowInputs.InputSourceType.HDA));
			    //inputs.setInput(workflowInput2Id, new WorkflowInputs.WorkflowInput(input2Id, WorkflowInputs.InputSourceType.HDA));
		    }
		    
		    //workflowsClient.runWorkflowResponse(inputs).
		    
		    final WorkflowOutputs output = workflowsClient.runWorkflow(inputs);
		    
		    System.out.println( "getOutputIds=" + output.getOutputIds());
		    
		    JobsClient jc= instance.getJobsClient();
		    for(Job j:jc.getJobsForHistory(output.getHistoryId())) {
		    	System.out.println("job: "  + j.getId() + " " + j.getState() + "  " + j.getToolId() + "  " +  j.getUpdated() + "  "  + j.getUrl());
		    }

		    //workflowsClient.( ) .showWorkflow(output.)
		    System.out.println("job running in " + histNewjob.getUrl());
		    
		    System.out.println("Running workflow in history " + output.getHistoryId());
		    String output2Id=null;
		    for(String outputId : output.getOutputIds()) {
		      System.out.println("  Workflow writing to output id " + outputId);
		      output2Id=outputId;
		    }
		    
		    
		    return runWorkflow( matchingWorkflow, mapInputname2Filename,jobid,outfile);
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	    }
		    
	    return "failed";
	  }
	  
		
	  public  String  runWorkflow_vcf2fasta_by_gffmerge_gatk(final String url, final String apiKey,
			  String jobid, File snplist, File samplelist, File outfile) throws Exception {
	    final GalaxyInstance instance = GalaxyInstanceFactory.get(url, apiKey);
	    final WorkflowsClient workflowsClient = instance.getWorkflowsClient();

	    
	    // Find history
	    final HistoriesClient historyClient = instance.getHistoriesClient();
	    
	    History histNewjob=(History)exists( historyClient.getHistories(),jobid);
	    if(histNewjob!=null) {
	    	// already created, get result (last dataset in history)
	    	System.out.println("Retrieving previous run result"); 
	    	
	    	
	    	boolean success=true;
	    	boolean running=false;
	    	boolean queued=false;
	    	boolean newjob=false;
	    	
	    	JobsClient jobClient = instance.getJobsClient();
	    	for(Job j:jobClient.getJobsForHistory(histNewjob.getId())) {
	    		System.out.println("job tool:" + j.getToolId() + " " +  j.getState() + " " + j.getUpdated());    
	    		success= success && j.getState().equals("ok"); 
	    		running=running || j.getState().equals("running");
	    		queued=queued || j.getState().equals("queued");
	    		newjob=newjob || j.getState().equals("new");
	    		
	    	}
	    	if(!success ) {
	    		if(running) return "running";
	    		if(queued) return "queued";    		
	    		if(newjob) return "new";    		
	    		return "failed";
	    	}
	    	
	    	HistoryContents outputDataset=null;
	        for(HistoryContents historyDataset : historyClient.showHistoryContents(histNewjob.getId())) {
	        		outputDataset=historyDataset;
	        }
	        historyClient.downloadDataset(histNewjob.getId(), outputDataset.getId(), outfile);
	    	return "ok";
	    }
	    
	    histNewjob= historyClient.create(new History(jobid));
	    
	    final ToolsClient toolsClient = instance.getToolsClient();
	    
	    ClientResponse respSnplist= toolsClient.uploadRequest(new FileUploadRequest(histNewjob.getId(),snplist));
	    ClientResponse respSAmplelist= toolsClient.uploadRequest(new FileUploadRequest(histNewjob.getId(),samplelist));
	    //respSnplist.gete

	    //respSAmplelist.getEntity()
	    
	    
	    //toolsClient.upload(new FileUploadRequest(histNewjob.getId(),snplist));
		//  Assert.assertNotNull(histNewjob);
		  String input1Id = null;
		  String input2Id = null;
		  for(final HistoryContents historyDataset : historyClient.showHistoryContents(histNewjob.getId())) {		  
		    System.out.println("Hist dataset:" + historyDataset.getId() + " " + historyDataset.getName());
		    if(historyDataset.getName().equals("snplist.bed")) {
		      input1Id = historyDataset.getId();
		    }
		    if(historyDataset.getName().equals("varlist.txt")) {
		      input2Id = historyDataset.getId();
		    }
		  }
	   

	    Workflow matchingWorkflow = null;
	    for(Workflow workflow : workflowsClient.getWorkflows()) {
	      if(workflow.getName().equals("vcf2fasta_by_gffmerge_gatk")) {
	        matchingWorkflow = workflow;
	      }
	    }

	    final WorkflowDetails workflowDetails = workflowsClient.showWorkflow(matchingWorkflow.getId());
	    String workflowInput1Id = null;
	    String workflowInput2Id = null;
	    for(final Map.Entry<String, WorkflowInputDefinition> inputEntry : workflowDetails.getInputs().entrySet()) {
	      final String label = inputEntry.getValue().getLabel();
	      if(label.equals("snplist")) {
	        workflowInput1Id = inputEntry.getKey();
	      }
	      if(label.equals("samplelist")) {
	        workflowInput2Id = inputEntry.getKey();
	      }
	    }

	    final WorkflowInputs inputs = new WorkflowInputs();
	    inputs.setDestination(new WorkflowInputs.ExistingHistory(histNewjob.getId()));
	    inputs.setWorkflowId(matchingWorkflow.getId());
	    inputs.setInput(workflowInput1Id, new WorkflowInputs.WorkflowInput(input1Id, WorkflowInputs.InputSourceType.HDA));
	    inputs.setInput(workflowInput2Id, new WorkflowInputs.WorkflowInput(input2Id, WorkflowInputs.InputSourceType.HDA));
	    final WorkflowOutputs output = workflowsClient.runWorkflow(inputs);
	    
	    
	    System.out.println("Running workflow in history " + output.getHistoryId());
	    String output2Id=null;
	    for(String outputId : output.getOutputIds()) {
	      System.out.println("  Workflow writing to output id " + outputId);
	      output2Id=outputId;
	    }
	    
	    return runWorkflow_vcf2fasta_by_gffmerge_gatk(url,apiKey,
	  		  jobid,snplist,samplelist,outfile);
	    
	    
	  }
	  
	
}
