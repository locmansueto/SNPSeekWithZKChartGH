package org.irri.iric.galaxy.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
//import org.zkoss.json.JSONObject;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.AsyncJob;
import org.irri.iric.portal.admin.AsyncJobImpl;
import org.irri.iric.portal.admin.JobsFacade;
import org.irri.iric.portal.genotype.VariantStringData;
import org.python.core.PyDictionary;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.github.jmchilton.blend4j.galaxy.beans.Dataset;
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
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowStepDefinition;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowStepDefinition.WorkflowStepOutput;
import com.sun.jersey.api.client.ClientResponse;


@Service("GalaxyFacade")
//@EnableAsync
public class GalaxyFacadeImpl implements GalaxyFacade {

	 //private static PythonInterpreter interp=null; 
		@Autowired
		@Qualifier("GalaxyJobsFacade")
		private JobsFacade jobsfacade;
    

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
		  
		  //instance.getWorkflowsClient().showWorkflow("").  .getWebResource();
		  
		  for(Workflow wf:workflowsClient.getWorkflows()) {
			  
			  	WorkflowDetails workflowDetails = workflowsClient.showWorkflow(wf.getId());
			  	//System.out.println(wf.getName() + "  " + workflowDetails.getUrl());
			  	MyWorksflow mwf=new MyWorksflow(wf);
			  	
				 
		    	Set params=new HashSet();
		    	String wfjson=workflowsClient.exportWorkflow(wf.getId());
		    	System.out.println(wfjson);
		    	JSONObject jsonObj = new JSONObject(wfjson); 
		    	JSONObject stepsarr=jsonObj.getJSONObject("steps");
		    	
		    	if(jsonObj.has("annotation"))
		    		mwf.setAnnotation(jsonObj.getString("annotation"));
		    	boolean acceptWf=false;
			    for(int i=0; i<stepsarr.length(); i++) {
			    	JSONObject o=stepsarr.getJSONObject(String.valueOf(i));
			    	String alllabel=null;
			    	String label=null;
			    	if(o.getString("type").equals("parameter_input")) {
				    	//System.out.println( o.getString("label") + "  " + o.getString("annotation") +  "   " + o.getString("tool_state"));
				    	JSONObject jsonstate = new JSONObject(o.getString("tool_state"));
				    	//System.out.println( jsonstate.get("optional") + "   "  + jsonstate.get("parameter_type"));
				    	label= o.getString("label");
				    	//alllabel=label+":"+ jsonstate.get("parameter_type")+":"+jsonstate.get("optional")+":"+o.getString("annotation");
				    	String annotrun[]=o.getString("annotation").split("\\:");
				    	alllabel=label+":"+ jsonstate.get("parameter_type")+":"+ (annotrun.length>1?annotrun[1]:"") +":"+(o.has("value")?o.getString("value"):"") +":"+String.valueOf(i)+"-"+annotrun[0];
			    	}
			    	else if(o.getString("type").equals("data_input")) {
			    		label= o.getString("label");
				    	//System.out.println( o.getString("label") + "  " + o.getString("annotation") +  "   " + o.getString("tool_state"));
				    	alllabel= label+":data_input:"+o.getString("annotation")+":"+(o.has("value")?o.getString("value"):"")+":"+String.valueOf(i);
				    }  else continue;
			    	
				     for(String n:nameStarts) {
				    	  if(label.toLowerCase().startsWith(n.toLowerCase())) {
				    		  Set setWf=mapName2Wf.get(n);
				    		  if(setWf==null) {
				    			  setWf=new HashSet();
				    			  mapName2Wf.put(n,setWf);
				    		  }
				    		  setWf.add(mwf);
				    		  acceptWf=true;
				    	  }
				      }
				      if(acceptWf) params.add(alllabel);
			    }
			    if(params.size()>0) mapWf2Params.put(mwf,params);
		  		
			    /*
			    //System.out.println(workflowsClient.exportWorkflow(wf.getId() ));
			  	//PyList cxxTestGenArgs = new PyList(); 
			  	Set params=new HashSet();
			    for(final Map.Entry<String, WorkflowInputDefinition> inputEntry : workflowDetails.getInputs().entrySet()) {
			    	//inputEntry.getValue().getValue().ge
			    	  String label = inputEntry.getValue().getLabel(); // + ":" + inputEntry.getValue().
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
			    */
			    
		  }
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  return new Map[] {mapName2Wf, mapWf2Params};
	  }
	  
	  private Set findMatches(Set setMatch, JSONArray dfxlist, Map<String,String> mapDFX2Class, String tag) {
		  Set matched=new HashSet();
		  for(int i=0; i<dfxlist.length(); i++) {
			  try {
				  Object o=  dfxlist.get(i);
				  if(o==null) continue;
				  String dfx=o.toString();
				  
				  String classtype=dfx;
				  if(!tag.equals("file_ext")) classtype=mapDFX2Class.get(dfx);
				  
				  //System.out.println("dfx=" + dfx + "  class=" + classtype);
				  if(classtype==null) {
					  AppContext.debug("No " + tag + " class for tag " + dfx);
				  } else {
					  if(setMatch!=null) {
						  if(setMatch.contains(dfx))  matched.add(classtype);
					  } else
						  matched.add(classtype);
				  }
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
		  }
		  return matched;
	  }
	  
	  private Set findMatches(Set setMatch, PyList dfxlist, Map<String,String> mapDFX2Class, String tag) {
		  Set matched=new HashSet();
		  Iterator it=dfxlist.iterator();
		  while(it.hasNext()){
			  Object o=it.next();
			  if(o==null) continue;
			  String dfx=o.toString();
			  String classtype=mapDFX2Class.get(dfx);
			  //System.out.println("dfx=" + dfx + "  class=" + classtype);
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
		          if(jythonThread.isAlive())  jythonThread.interrupt();
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
	  public Map[] discoverTools() {
		  return discoverTools(null,null,null,null);
	  }
	  
	  //@Override
	  @Override
	  public Map[] discoverTools(Set<String> nameStarts, Set<String> classes, Set<String> formats, Set<String> sections)
	  {
			AppContext.debug("discoverTools(Set<String> nameStarts, Set<String> formats, Set<String> sections)");

			 //{mapClass2Dat, mapDat2Class, mapClass2For, mapFor2Class, mapClass2Ext, mapExt2Class,mapClass2Class};
			Set skipToolId=new HashSet();
			/*
			skipToolId.add( "Convert characters1");
			skipToolId.add( "52.76.88.51/repos/dereeper/numeric_matrix_encoder/bglr:encode/1.0.3");
			skipToolId.add( "toolshed.g2.bx.psu.edu/repos/iuc/bedtools/bedtools_jaccard/2.27.1+galaxy1");
			skipToolId.add( "GeneBed_Maf_Fasta2");
			skipToolId.add( "__EXTRACT_DATASET__");
			*/
			
			Map mapClass2Dat=DatatypeMaps.getDatatypes()[0];		
			Map mapClass2Format=DatatypeMaps.getDatatypes()[2];		
			Map mapSection2Section=new LinkedHashMap();
			
			if(classes!=null || formats!=null) {
				if(classes!=null && formats!=null) {
					for(String s:classes) {
						formats.add((String)mapClass2Dat.get(s));
						String f=(String)mapClass2Format.get(s);
						if(f!=null) formats.add(f);
					}
				} else if(classes!=null) {
					formats=classes;
				}
				
				formats.remove("");
				formats.remove("null");
				AppContext.debug("match edams=" + formats);
			}
			
			Map<JSONObject,Set<String>> mapTools2Params=new HashMap();
			
	       Map<String,Set<JSONObject>> mapName2Tools=new HashMap();
	       Map<String,Set<JSONObject>> mapData2Tools=new HashMap();
	       Map<String,Set<JSONObject>> mapExt2Tools=new HashMap();

		  try {
			
	       Map<String,String> mapDatatypes[]=DatatypeMaps.getDatatypes();
	       //{mapClass2Dat, mapDat2Class, mapClass2For, mapFor2Class, mapClass2Ext, mapExt2Class}
	       int sectioncnt=1;
	       Set setMatchData=new HashSet();
	       Set setMatchFormat=new HashSet();
	       Set setMatchExt=new HashSet();
	       
	       /*
	       InetAddress myHost = InetAddress.getLocalHost();
           System.out.println(myHost.getHostName());
           System.out.println(myHost.getHostAddress());
           System.out.println(myHost.getCanonicalHostName());
           */
	       
	       BufferedReader br=AppContext.bufferedReadURL( AppContext.getHostname() + AppContext.getHostDirectory() + "galaxy/galaxytools_" + AppContext.getGalaxyInstance()+".txt", null);
	       //BufferedReader br=new BufferedReader(new FileReader(AppContext.getTomcatWebappsDir()+AppContext.getHostDirectory() +"galaxy/galaxytools_" + AppContext.getGalaxyInstance()+".txt"));
	       //BufferedReader br=new BufferedReader(new FileReader(AppContext.getFlatfilesDir() + "galaxytools_" + AppContext.getGalaxyInstance()+".txt"));
		   
	       AppContext.debug("reading " + AppContext.getFlatfilesDir() + "galaxytools_" + AppContext.getGalaxyInstance()+".txt");
	       String line=null;
	       while((line=br.readLine())!=null) {
			  
	    	   	  JSONObject jo=new JSONObject(line);
	    	  
	    	   	  try {
				  Set<String> matchedExt=new LinkedHashSet();
				  Set<String> matchedData=new LinkedHashSet();
				  Set<String> matchedName=new LinkedHashSet();
		    	if(jo.has("panel_section_id")) {
		    		mapSection2Section.put(jo.getString("panel_section_id"),null);
		    	}

				  
				  Set params=new LinkedHashSet();
				  JSONArray inputs=jo.getJSONArray("inputs");
				  for(int it=0; it<inputs.length(); it++) {
					  JSONObject pdict=inputs.getJSONObject(it);
					  String pname=(String)pdict.get("name");
					  String paramprops=pdict.get("name") + ":"+ pdict.get("type") + ":" + (pdict.has("label")?pdict.get("label"):"") +":"+ 
							  (pdict.has("value")?pdict.get("value"):"");
					  //System.out.println( "    " + paramprops);
					  if(nameStarts!=null) {
						  for(String n:nameStarts) {
							  if(pname.toLowerCase().startsWith(n.toLowerCase())) {
								  //matchedName.add(pname+":"+ pdict.get("extensions"));
								  //matchedName.add(pname);
								  matchedName.add(paramprops);
							  }
						  }
					  }
					  if(pdict.get("type").equals("data")) {
						  if(pdict.has("edam_data")) {
							  JSONArray edams_data= pdict.getJSONArray("edam_data");
							  if(edams_data!=null && edams_data.length()>0) matchedData.addAll(findMatches(formats, edams_data, mapDatatypes[1], "edam_data"));
						  }
						  if(pdict.has("edam_formats")) {
								JSONArray edams_format= pdict.getJSONArray("edam_formats");
								if(edams_format!=null &&  edams_format.length()>0) matchedData.addAll(findMatches(formats, edams_format, mapDatatypes[3], "edam_format"));
						  }
					  }
					  if(pdict.has("extensions" )) {
						  JSONArray ext= pdict.getJSONArray("extensions");
						  if(ext!=null &&  ext.length()>0)   matchedExt.addAll(findMatches(formats, ext, mapDatatypes[5], "file_ext"));
					  }
					  params.add(paramprops);
				  }
				  if(formats!=null) {
					  matchedData.addAll(matchedExt);
					  if(matchedData.size()>0) {
						  mapTools2Params.put(jo, params);
						  //System.out.println("matchedParams=" + matchedData);
					  }
				  } else {
					  mapTools2Params.put(jo, params);
				  }
				  
				  for(String term:matchedData) {
					 Set setTools=mapData2Tools.get(term);
					 if(setTools==null) {
						 setTools=new LinkedHashSet();
						 mapData2Tools.put(term, setTools);
					 }
					 setTools.add(jo);
				  }
				  for(String term:matchedExt) {
					 Set setTools=mapExt2Tools.get(term);
					 if(setTools==null) {
						 setTools=new LinkedHashSet();
						 mapExt2Tools.put(term, setTools);
					 }
					 setTools.add(jo);
				  }
				  for(String term:matchedName) {
					 Set setTools=mapData2Tools.get(term);
					 if(setTools==null) {
						 setTools=new LinkedHashSet();
						 mapName2Tools.put(term, setTools);
					 }
					 setTools.add(jo);
				  }
				  
	    	   	  }catch(Exception ex) {
	    	   		  AppContext.debug("job = " + jo.get("id"));
	    	   		  ex.printStackTrace();
	    	   		  throw ex;
	    	   	  }
			  }
	       	  br.close();
	       
	       /*
	       if(formats!=null || classes!=null) {
	    	   mapData2Tools.putAll(mapName2Tools);
	       }
	       */
	       
		  //interp.close();
		  AppContext.debug("done close");
		  
		  } catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  return new Map[] {mapData2Tools,mapTools2Params,mapName2Tools,mapExt2Tools, mapSection2Section};
	  }
	  
	  
	  public Map[] discoverTools4(Set<String> nameStarts, Set<String> classes, Set<String> formats, Set<String> sections) {
			AppContext.debug("discoverTools(Set<String> nameStarts, Set<String> formats, Set<String> sections)");

			 //{mapClass2Dat, mapDat2Class, mapClass2For, mapFor2Class, mapClass2Ext, mapExt2Class,mapClass2Class};
			Set skipToolId=new HashSet();
			/*
			skipToolId.add( "Convert characters1");
			skipToolId.add( "52.76.88.51/repos/dereeper/numeric_matrix_encoder/bglr:encode/1.0.3");
			skipToolId.add( "toolshed.g2.bx.psu.edu/repos/iuc/bedtools/bedtools_jaccard/2.27.1+galaxy1");
			skipToolId.add( "GeneBed_Maf_Fasta2");
			skipToolId.add( "__EXTRACT_DATASET__");
			*/
			
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
		  interp.exec("try:\n"+
		  "    import bioblend\n"+
		  "except:\n" +    
		  //"    !{sys.executable} -m pip install bioblend");
		  "    print('error import bioblend')");
		  interp.exec("from bioblend import galaxy");
		  interp.exec("gi = galaxy.GalaxyInstance(url='http://13.250.4.164:8080', key='dd7ecdf0096f104c0e3ac8fd7f8f8136')");
		  
		  /*
		  interp.exec("try:\n"+
		  "    import eventlet\n" +
		  "except:\n" +    
		  //"    !{sys.executable} -m pip install eventlet");
		   "    print('error import eventlet')");
		  interp.exec("from eventlet.timeout import Timeout");
		  */
		  
		  interp.exec("from bioblend.galaxy import GalaxyInstance");
		  interp.exec("from bioblend.galaxy.tools import ToolClient");
		  interp.exec("toolClient = ToolClient(gi)");
	   
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
						 interp.exec(
								    "result='error'\n" +
								    "try:\n" +
								    "    showtool=toolClient.show_tool(" + t.getId() + ", io_details=True,link_details=False)\n" +
								    "    result=showtool\n" +
								    "    ti=showtool\n" +
								    "except Exception as ex:\n" +
								    "    result='timeout'\n" +
								    "    print('exception: ' + str(ex))");
					  
					 /* 
					 interp.exec("timeout = Timeout(10,  Exception('Timeout'))\n" +
						    "result='error'\n" +
						    "try:\n" +
						    "    showtool=toolClient.show_tool(" + t.getId() + ", io_details=True,link_details=False)\n" +
						    "    result=showtool\n" +
						    "    ti=showtool\n" +
						    "except Exception as ex:\n" +
						    "    result='timeout'\n" +
						    "finally:\n" +
						    "    timeout.cancel()\n");
						    */
					  
				  } catch(Exception ex) {
					  ex.printStackTrace();
					  Object ins= interp.get("result");
					  System.out.println("result=" + ins);
					  continue;
				  }
				  if(interp.get("ti")==null) continue;
				  interp.exec("if 'inputs' in ti.keys():\n    inputParams = ti['inputs']\nelse:\n    inputParams=[]\n");

				  PyList pl=(PyList)interp.get("inputParams");
				  
				  if(pl.size()==0) {
					  System.out.println("inputParams.len=0");
					  continue;
				  }

				  Set<String> matchedData=new LinkedHashSet();
				  Set<String> matchedName=new LinkedHashSet();
				  
				  Set params=new LinkedHashSet();
				  
				  Iterator itPl=pl.iterator();
				  while(itPl.hasNext()) {
					  PyDictionary  pdict= (PyDictionary)itPl.next();
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
	  
	  
	  public Map[] discoverTools3(Set<String> nameStarts, Set<String> classes, Set<String> formats, Set<String> sections) {
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
					  boolean done=exec_with_timeout(interp,"try:\n    ti=toolClient.show_tool(tool_id='" + t.getId() + "',io_details=True)\n    print(ti)\nexcept Exception as e:\n    print(str(e))\n    instance = sys.exc_info()[1]",1000);
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
				  
				  Iterator itPl=pl.iterator();
				  while(itPl.hasNext()) {
					  PyDictionary  pdict=(PyDictionary)itPl.next();
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
	  
	  @Override
	  public  String[]  runWorkflowAsync(Workflow matchingWorkflow, Map<String,String[]>  mapInputname2Filename, String jobid) { 
			
		  	jobsfacade=(JobsFacade)AppContext.checkBean(jobsfacade,"GalaxyJobsFacade");
 		  
		  	String status[]=runWorkflow_( matchingWorkflow,  mapInputname2Filename, jobid);
		  	
		  	if(status[0].equals("ok")) status= new String[] {JobsFacade.JOBSTATUS_DONE,status[1]};
		  	else if(status[0].equals("failed") || status[0].equals("error")) status=  new String[] {JobsFacade.JOBSTATUS_ERROR,status[1]};
		  	else status=  new String[] {JobsFacade.JOBSTATUS_SUBMITTED ,status[1]};
			
			
			writeStatus(status[0]);
			
			if(status[0].equals( JobsFacade.JOBSTATUS_SUBMITTED)) {
				AsyncJob aj= new AsyncJobImpl(jobid, status[1]);
				jobsfacade.addJob(aj );
			}
			
			return status;
	}
		
	//@Async("threadPoolTaskExecutor")
//	public  Future  runWorkflowAsync(Workflow matchingWorkflow, Map<String,String>  mapInputname2Filename,
//				  String jobid, File outfile) { 
//			while(true) {
//				String status=runWorkflow_( matchingWorkflow,  mapInputname2Filename,
//						   jobid,  outfile);
//				if(status.equals(JobsFacade.JOBSTATUS_DONE)) break;
//				
//				writeStatus(status);
//				/*
//				if(status.equals("new"))
//					writeStatus(JobsFacade.JOBSTATUS_SUBMITTED);
//				else if(status.equals("ok")) 
//					writeStatus(JobsFacade.JOBSTATUS_DONE);
//				else if(status.equals("queued"))
//					writeStatus(JobsFacade.JOBSTATUS_SUBMITTED);
//				else if(status.equals("running"))
//					writeStatus(JobsFacade.JOBSTATUS_STARTING);
//				else { 
//					writeStatus(JobsFacade.JOBSTATUS_ERROR);
//					AppContext.debug("status=" + status);
//					return new AsyncResult<String>(null); //JobsFacade.JOBSTATUS_ERROR;
//				}
//				if(status.equals("ok")) {
//					// copy from galaxy to local server
//					break;				
//				}
//				*/
//				
//				try {
//					TimeUnit.SECONDS.sleep(30);
//				} catch(Exception ex) {
//					ex.printStackTrace();
//				};
//			}
//			return  new AsyncResult<String>(null); //JobsFacade.JOBSTATUS_DONE;
//	}
		
	 @Override 
	 public  String[]  runWorkflow(Workflow matchingWorkflow, Map<String,String[]>  mapInputname2Filename,
			  String jobid) { 
		long maxwaitsec=60*60;
		long seccnt=0;
		while(true) {
			String[] status=runWorkflow_( matchingWorkflow,  mapInputname2Filename, jobid);
			if(status[0].equals("ok")) return new String[] {JobsFacade.JOBSTATUS_DONE,status[1]};
			if(status[0].equals("failed")) return new String[] {JobsFacade.JOBSTATUS_ERROR,status[1]};
			
			writeStatus(status[0]);
			
			/*
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
			*/
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch(Exception ex) {
				ex.printStackTrace();
			};
			seccnt+=30;
			if(seccnt>maxwaitsec) {
				AppContext.debug("runWorkflow wait timeout after " + maxwaitsec + " secs" );
				break;
			}
		}
		return new String[] {JobsFacade.JOBSTATUS_ERROR,null};
	 }
	
	 @Override 
	 public String updateStatus(String jobid, String histid) {
		 
	 		GalaxyInstance instance = GalaxyInstanceFactory.get(AppContext.getGalaxyAddress(), AppContext.getGalaxyKey());
	    	HistoriesClient historyClient = instance.getHistoriesClient();
		 	if(histid==null) {
		    	History histNewjob=(History)exists( historyClient.getHistories(),jobid);
		    	if(histNewjob==null) return  JobsFacade.JOBSTATUS_MISSING;
		    	histid=histNewjob.getId();
		 	}
		    if(histid!=null) {
		    	System.out.println("Retrieving " + jobid + " previous run result"); 
		    	String status[]=updateRunning(histid, instance, historyClient,jobid);
		    	if(status[0].equals("ok")) return JobsFacade.JOBSTATUS_DONE;
		    	else if(status[0].equals("running"))  return JobsFacade.JOBSTATUS_STARTING;
		    	else if(status[0].equals("queued")) return JobsFacade.JOBSTATUS_SUBMITTED;
		    	else if(status[0].equals("new"))  return JobsFacade.JOBSTATUS_SUBMITTED;
		    	return JobsFacade.JOBSTATUS_ERROR;
		    }
		    return JobsFacade.JOBSTATUS_MISSING;
	 }
	 
	 private String[] updateRunning(String historyId , GalaxyInstance instance , HistoriesClient historyClient, String jobid) {
		  	
		 		// already created, get result (last dataset in history)
		    	System.out.println("Retrieving previous run result"); 
		    	
				try {
		    	
				    	boolean success=true;
				    	boolean running=false;
				    	boolean queued=false;
				    	boolean newjob=false;
				    	
				    	JobsClient jobClient = instance.getJobsClient();
				    	for(Job j:jobClient.getJobsForHistory(historyId)) {
				    		System.out.println("job tool:" + j.getToolId() + " " +  j.getState() + " " + j.getUpdated());    
				    		success= success && j.getState().equals("ok"); 
				    		running=running || j.getState().equals("running");
				    		queued=queued || j.getState().equals("queued");
				    		newjob=newjob || j.getState().equals("new");
				    		
				    	}
				    	System.out.println("success=" + success +", running=" + running + ", newjob=" + newjob + ", queued=" + queued);
				    	HistoryContents outputDataset=null;
				    	if(!success ) {
				    		if(running) return new String[] {"running",historyId};
				    		if(queued) return new String[] {"queued",historyId};    		
				    		if(newjob) return new String[] {"new",historyId};    		
				    		return new String[] {"failed",historyId};
				    	}
				    	
				    	try {
				    	// last dataset in history is output
					        for(HistoryContents historyDataset : historyClient.showHistoryContents(historyId)) {
					        		outputDataset=historyDataset;
					        }
					       
					    	System.out.println("outputDataset=" + outputDataset.getId() + ",  Job done..downloading history " + historyId + ", job " + jobid + " from galaxy to snpseek/temp " +  jobid + ".zip" );
					        
					        //historyClient.downloadDataset(historyId, outputDataset.getId(), outfile);	
					    	Dataset ds=historyClient.showDataset(historyId, outputDataset.getId());
					    	System.out.println("ds.getFileSize()=" + ds.getFileSize() 
					    	+" ds.getDownloadUrl()=" + ds.getDownloadUrl()
					    	+" ds.getFullDownloadUrl()=" + ds.getFullDownloadUrl()
					    	+" ds.getGalaxyUrl()=" +  ds.getGalaxyUrl()
					    	+" ds.getUrl()=" +  ds.getUrl());
					    	
					    	AppContext.downloadURL( ds.getFullDownloadUrl(),   new File(AppContext.getTempDir() + jobid + ".zip") );
					    	
					    	//historyClient.downloadDataset(historyId, outputDataset.getId(), new File(AppContext.getTempDir() + jobid + "-2.zip"));
					    	
				    	} catch(Exception ex) {
				    		ex.printStackTrace();
				    		/*
				    		try {
				    		historyClient.downloadDataset(historyId, outputDataset.getId(), new File(jobid + ".zip"));
				    		} catch(Exception ex2) {
				    			ex2.printStackTrace();
				    		}
				    		*/
				    		return new String[] {"failed",historyId};
				    	}
				        
				    	return new String[] {"ok",historyId};
				 } catch(Exception ex) {
					 ex.printStackTrace();
				 }
				 return new String[] {"failed",historyId};
	 }
	 
	 
	 // returns galaxy status (ok,new,running, etc), historyid
	 private  String[]  runWorkflow_(Workflow matchingWorkflow, Map<String, String[]>  mapInputname2Filename,
			  String jobid) {
		 
			 
	    final GalaxyInstance instance = GalaxyInstanceFactory.get(AppContext.getGalaxyAddress(), AppContext.getGalaxyKey());
	    final WorkflowsClient workflowsClient = instance.getWorkflowsClient();
	    
	    // Find history
	    final HistoriesClient historyClient = instance.getHistoriesClient();
	    
	    History histNewjob=(History)exists( historyClient.getHistories(),jobid);
	    if(histNewjob!=null) {
	    	// already created, get result (last dataset in history)
	    	System.out.println("Retrieving " + jobid + " previous run result"); 
	    	return updateRunning(histNewjob.getId(), instance, historyClient,jobid);
	    }
	    
	    try {
	    
		    histNewjob= historyClient.create(new History(jobid));
		    
		    final ToolsClient toolsClient = instance.getToolsClient();
		    
		    for(String inpname:mapInputname2Filename.keySet()) {
		    	String fnames[]=mapInputname2Filename.get(inpname);
		    	if(fnames[1].equals("data_input")) {
			    	AppContext.debug("loading param " + inpname + " from file " + mapInputname2Filename.get(inpname)[0]);
			    	ClientResponse resp=toolsClient.uploadRequest(new FileUploadRequest(histNewjob.getId(),new File(mapInputname2Filename.get(inpname)[0])));
		    	}
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
			    	if(mapInputname2Filename.get(inpname)[1].equals("data_input")) {
				    	String shortname=new File(mapInputname2Filename.get(inpname)[0]).getName();
				    	if(historyDataset.getName().equals(shortname)) {
				    		mapInputname2Datasetid.put(inpname , historyDataset.getId());
				    		break;
				    	}
			    	}			    	
			    }
			  }
		   
		    final WorkflowDetails workflowDetails = workflowsClient.showWorkflow(matchingWorkflow.getId());
		    String workflowInput1Id = null;
		    String workflowInput2Id = null;
		    
		    /*
		    try {
		    System.out.println( AppContext.readURL(AppContext.getGalaxyAddress() + workflowDetails.getUrl() ));
		    } catch(Exception ex) {
		    	ex.printStackTrace();
		    }
		    */
		    Map<String,String> mapInpname2InputEntryKey=new HashMap();
		    
		    
		    Map mapParam2Stepno=new HashMap();
		    
		    for(final Map.Entry<String, WorkflowInputDefinition> inputEntry : workflowDetails.getInputs().entrySet()) {
		      String label = inputEntry.getValue().getLabel();
		      for(String inpname:mapInputname2Filename.keySet()) {
			    	if( label.equals(inpname)) {
			    		mapInpname2InputEntryKey.put(label,  inputEntry.getKey());
			    		break;
			    	}
			    }
		      mapParam2Stepno.put( inputEntry.getValue().getLabel(), inputEntry.getKey());
		      AppContext.debug("WorkflowInputDefinition  " +  inputEntry.getKey() +  " " + inputEntry.getValue().getLabel() + " " +  inputEntry.getValue().getUuid() + "  " + inputEntry.getValue().getValue());
		    }

		    
		    System.out.println("Steps..");
		    for(final Map.Entry<String, WorkflowStepDefinition> inputEntry : workflowDetails.getSteps().entrySet()) {
		    	System.out.println(inputEntry.getKey() + "  " + inputEntry.getValue().getType() + "  " + inputEntry.getValue().toString());
			 }

		    
		    final WorkflowInputs inputs = new WorkflowInputs();
		    inputs.setDestination(new WorkflowInputs.ExistingHistory(histNewjob.getId()));
		    inputs.setWorkflowId(matchingWorkflow.getId());
		    
		    
		    for(String inpname:mapInpname2InputEntryKey.keySet()) {
		    	
		    	System.out.println("setting input_file=" + inpname +  ",  inputentryid=" + mapInpname2InputEntryKey.get(inpname) + ", datasetid=" + mapInputname2Datasetid.get(inpname));
			    inputs.setInput(mapInpname2InputEntryKey.get(inpname), new WorkflowInputs.WorkflowInput(mapInputname2Datasetid.get(inpname), WorkflowInputs.InputSourceType.HDA));
			    //inputs.setInput(workflowInput2Id, new WorkflowInputs.WorkflowInput(input2Id, WorkflowInputs.InputSourceType.HDA));
		    }
		    
		    System.out.println(mapParam2Stepno);
		    for(String param:mapInputname2Filename.keySet()) {
		    	System.out.println(  mapInputname2Filename.get(param)[0] + "   "  + mapInputname2Filename.get(param)[1] +   "   " +  mapInputname2Filename.get(param)[2]);
		    	if(mapInputname2Filename.get(param)[1].equals("data_input")) continue;
		    	//String step=(String)mapParam2Stepno.get(param);
		    	String step= mapInputname2Filename.get(param)[2].split("-")[1];
		    	if(step==null) continue;
		    	System.out.println("setting param="  + param +  "  , step=" + step +", value=" + mapInputname2Filename.get(param)[0]);
		    	//inputs.setStepParameter( Integer.parseInt(step),new ToolParameter(param, mapInputname2Filename.get(param)[0])); 
		    	 //inputs.setStepParameter(Integer.parseInt(step), "Input parameter", mapInputname2Filename.get(param)[0]);
		    	//inputs .setStepParameter(Integer.parseInt(step), "Input parameter", mapInputname2Filename.get(param)[0]);
		    	 
		    	if(mapInputname2Filename.get(param)[0]!=null && !  ((String)mapInputname2Filename.get(param)[0]).trim().isEmpty())
		    	 inputs.setToolParameter(step,param, mapInputname2Filename.get(param)[0]);
		    	 
		    }
		     
			//System.out.println("setting param= reference 'Input parameter'  , step=" + 2 +", value=nipponbare");
		    // inputs.setToolParameter("vcf2fasta_tabgatk", "reference", "nipponbare");

	    	 //workflowsClient.runWorkflowResponse(inputs).
		    
		    //final ClientResponse response = workflowsClient.runWorkflowResponse(inputs);
		    //System.out.println("response=" + response.toString());
		    
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
		    
		    return updateRunning(histNewjob.getId() , instance, historyClient,jobid);
		    //return runWorkflow_( matchingWorkflow, mapInputname2Filename,jobid);
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	    }
		    
	    return new String[] {"failed",null};
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
