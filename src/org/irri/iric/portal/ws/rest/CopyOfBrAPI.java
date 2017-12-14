package org.irri.iric.portal.ws.rest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;



import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Scaffold;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.ws.entity.VariantTableWS;
import org.irri.iric.portal.ws.rest.brapiv1.BrAPIResponse;
import org.irri.iric.portal.ws.rest.brapiv1.Markers;
import org.irri.iric.portal.ws.rest.brapiv1.Metadata;
import org.irri.iric.portal.ws.rest.brapiv1.Pagination;
import org.irri.iric.portal.ws.rest.brapiv1.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("BrapiWebService2")
//@RestController
@Path("/brapi/v1.1")
public class CopyOfBrAPI {
	

	@Autowired
	@Qualifier("GenotypeFacade")
	private GenotypeFacade genotype;
	
	@Autowired
	@Qualifier("VarietyFacade")
	private VarietyFacade variety;
	
	@Autowired
	@Qualifier("GenomicsFacade")
	private GenomicsFacade genomics;
	
	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsDAO;
	
	private String dataset=VarietyFacade.DATASET_SNPINDELV2_IUPAC;
	private String snpset=GenotypeQueryParams.SNP_CORE;
	
	private Map<String,int[]> mapMap2GroupsMarkers;
	private Map<String,String[]> mapMap2Names;

	  public CopyOfBrAPI() {
		super();
		// TODO Auto-generated constructor stub
		
		  mapMap2GroupsMarkers=new HashMap();
		  /*
		  mapMap2GroupsMarkers.put( "3kfiltered", new int[]{12, 4800000});
		  mapMap2GroupsMarkers.put( "3kcore", new int[]{12, 404000});
		  mapMap2GroupsMarkers.put( "3kbase", new int[]{12, 18000000});
		  mapMap2GroupsMarkers.put( "3kall", new int[]{12, 32180725});
		  mapMap2GroupsMarkers.put( "hdra", new int[]{12, 700000});
		  */
		  mapMap2GroupsMarkers.put( "3kfiltered", new int[]{12, 4817964});
		  mapMap2GroupsMarkers.put( "3kcore", new int[]{12, 404388});
		  mapMap2GroupsMarkers.put( "3kbase", new int[]{12, 18128777});
		  mapMap2GroupsMarkers.put( "3kall", new int[]{12, 32064217});
		  mapMap2GroupsMarkers.put( "hdra", new int[]{12, 700000});

		  mapMap2Names=new HashMap();
		  mapMap2Names.put( "3kfiltered",  new String[]{"3k Rice Genome Project filtered SNPs (4.8Mb)"});
		  mapMap2Names.put( "3kcore", new String[]{"3k Rice Genome Project core SNPs (404kb)"});
		  mapMap2Names.put( "3kbase", new String[]{"3kbase","3k Rice Genome Project base SNPs (18Mb)"});
		  mapMap2Names.put( "3kall", new String[]{"3kall","3k Rice Genome Project all SNPs (32Mb)"});
		  mapMap2Names.put( "hdra", new String[]{"High Density Rice Array HDRA (700kb)"});

		  
	}

	private Map createResponse(List listresult) {
		  Map mapMeta=new LinkedHashMap();
		  Map mapPagi=new LinkedHashMap();
		  
		  mapPagi.put("pageSize",  listresult.size());mapPagi.put("currentPage", 0);mapPagi.put("totalCount", listresult.size());mapPagi.put("totalPages", 1);
		  mapMeta.put("pagination", mapPagi);
		  
		  mapMeta.put("status",  new ArrayList());
		  mapMeta.put("datafiles",  new ArrayList());
			
		Map mapResult=new LinkedHashMap();
		mapResult.put("data", listresult);
		Map mapResponse=new LinkedHashMap();
		mapResponse.put("metadata", mapMeta);
		mapResponse.put("result", mapResult);
		
		return mapResponse;
	  }
	  
	  private Map createResponse(Map mapresult) {

		  Map mapResponse=new LinkedHashMap();

		  Object data= mapresult.get("result");
		  if(data==null || !(data instanceof List)) {
			  Map mapMeta=new LinkedHashMap();
			  mapMeta.put("pagination", new ArrayList());
			  mapMeta.put("status",  new ArrayList());
			  mapMeta.put("datafiles",  new ArrayList());
			  mapResponse.put("metadata", mapMeta);
			  mapResponse.put("result", mapresult);
			
		  } else {
			  List listdata=(List)data;
			  Map mapMeta=new LinkedHashMap();
			  Map mapPagi=new LinkedHashMap();
			  mapPagi.put("pageSize",  listdata.size());mapPagi.put("currentPage", 0);mapPagi.put("totalCount", listdata.size());mapPagi.put("totalPages", 1);
			  mapMeta.put("pagination", mapPagi);
			  
			  mapMeta.put("status",  new ArrayList());
			  mapMeta.put("datafiles",  new ArrayList());
			  mapResponse.put("metadata", mapMeta);
			  mapResponse.put("result", mapresult);
		  }
		return mapResponse;
	  }
	  

	  private Response createFilenameResponse(String filename) {
		  Map mapResult=new LinkedHashMap();
			mapResult.put("data", new ArrayList());

			Map mapMeta=new LinkedHashMap();
			Map mapPagi=new LinkedHashMap();
			mapPagi.put("pageSize", 0);
			mapPagi.put("currentPage", 0);
					mapPagi.put("totalCount", 0);
							mapPagi.put("totalPages", 1);
			
			mapMeta.put("pagination", mapPagi);
			mapMeta.put("status",  new ArrayList());
			List listUrl=new ArrayList();
			Map mapUrl=new HashMap();
			mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder() + filename);
			listUrl.add(mapUrl);
			mapMeta.put("datafiles", listUrl);
			
			Map response=new LinkedHashMap();
			response.put("metadata", mapMeta);
			response.put("result", mapResult);
			
			  String errormsg="";
			  try {
				  	return Response.status(200).entity( new ObjectMapper().writeValueAsString( createResponse(response) )).build();
		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errormsg=e.getMessage();
				}
			  
			  return Response.status(500).entity(errormsg).build();
			
	  }
	  

	  private Response createJSONResponse(Map response) {
		  if(response==null) return Response.status(501).build();
		  
		  String errormsg="";
		  try {
			  	return Response.status(200).entity( new ObjectMapper().writeValueAsString( createResponse(response) )).build();
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errormsg=e.getMessage();
			} 
		  return Response.status(500).entity(errormsg).build();
	  }
	  

	  private Response createJSONResponse(List listresult) {
		  String errormsg="";
		  try {
			  	return Response.status(200).entity( new ObjectMapper().writeValueAsString( createResponse(listresult) )).build();
	
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errormsg=e.getMessage();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errormsg=e.getMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errormsg=e.getMessage();
			}
		  
		  return Response.status(500).entity(errormsg).build();
	  }
	  
//	  private Response createJSONResponse(Map mapResult) {
//		  String errormsg="";
//		  try {
//			  	return Response.status(200).entity( new ObjectMapper().writeValueAsString( createResponse(mapResult) )).build();
//	
//			} catch (JsonGenerationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				errormsg=e.getMessage();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				errormsg=e.getMessage();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				errormsg=e.getMessage();
//			}
//		  
//		  return Response.status(500).entity(errormsg).build();
//	  }
	  
	/*
	Get for identifier:
		/calls/allelematrix

		Json response:
		{
		    "metadata": {   
		        "pagination": {
		            "pageSize": 1,
		            "currentPage": 0,
		            "totalCount": 1,
		            "totalPages": 1
		        },
		        "status":[],
		    },
		    "result" : {
		        "data": [ 
			 	{ "call": "allelematrix", "datatypes": ["tsv", "json"], "methods": ["POST", "GET"] }
			]
		    }
		}
	*/
	  
	  private Map createCall(String name, String[] methods, String[] types) {
		  
		  Map mapCall=new LinkedHashMap();
		  mapCall.put("call", "allelematrix");  mapCall.put("datatypes",Arrays.asList(types));
		  mapCall.put("methods",Arrays.asList(methods));
		  return mapCall;
	  }
	  
	  @Path("/calls")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getCalls(@DefaultValue("") @QueryParam("datatype") String datatype) {

		  List listAllCalls=new ArrayList();
		  Map<String,List> mapType2Calls=new LinkedHashMap();
		  mapType2Calls.put("json", new ArrayList());
		  mapType2Calls.put("tsv", new ArrayList());
		  Map<String,Map> mapName2Call=new LinkedHashMap();
		  
		  Map mapCall=createCall("calls",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "calls", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);

		  mapCall=createCall("germplasm-search",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "germplasm-search", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);
		  
		  mapCall=createCall("germplasm/{germplasmDbid}",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "germplasm/{germplasmDbid}", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);
		  
		  mapCall=createCall("germplasm/{germplasmDbid}/markerprofiles",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "germplasm/{germplasmDbid}/markerprofiles", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);


		  mapCall=createCall("studyTypes",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "studyTypes", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);

		  mapCall=createCall("studies-search",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "studies-search", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);

		  mapCall=createCall("studies/{studiesDbid}",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "studies/{studiesDbid}", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);
		  
		  mapCall=createCall("studies/{studiesDbid}/germplasm",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "studies/{studiesDbid}/germplasm", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);
		  
		  mapCall=createCall("maps",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "maps", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);

		  mapCall=createCall("maps/{mapDbId}",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "maps/{mapDbId}", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);

		  mapCall=createCall("maps/{mapDbId}/positions",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "maps/{mapDbId}/positions", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);
		  
		  mapCall=createCall("maps/{mapDbId}/positions/{linkageGroupId}",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "maps/{mapDbId}/positions{linkageGroupId}", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);

		  
		  mapCall=createCall("markerprofiles",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "markerprofiles", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);

		  mapCall=createCall("markerprofiles/{markerprofileDbId}",new String[]{"GET"}, new String[]{"json"});
		  mapName2Call.put( "markerprofiles/{markerprofileDbId}", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  listAllCalls.add(mapCall);
	  
		  
		  mapCall=createCall("allelematrix-search",new String[]{"POST","GET"}, new String[]{"json","tsv"});
		  mapName2Call.put( "allelematrix-search", mapCall);
		  mapType2Calls.get("json").add(mapCall);
		  mapType2Calls.get("tsv").add(mapCall);
		  listAllCalls.add(mapCall);
		  
		  
		  if(!datatype.isEmpty()) {
			  listAllCalls=mapType2Calls.get(datatype);
		  }
		  
		  return createJSONResponse(createResponse(listAllCalls));
		  
//		  Map mapResponse=new LinkedHashMap();
//		  Map mapMeta=new LinkedHashMap();
//		  Map mapPagi=new LinkedHashMap();
//		  Map mapResult=new LinkedHashMap();
//		  mapPagi.put("pageSize", 1);
//		  mapPagi.put( "currentPage", 0);
//		  mapPagi.put( "totalCount", 1);
//		  mapPagi.put( "totalPages", 1);
//		  
//		
//		  
//		  
//		  Map mapData=new LinkedHashMap();
//		  mapData.put("data", listdata);
//		  
//		  
//		  
//		  mapMeta.put( "pagination", mapPagi);
//		  mapMeta.put("status",new ArrayList());
//		  mapResponse.put("metadata",mapMeta);
//		  mapResponse.put("result",mapData);
//		  
//		
//		  String errormsg="";
//		  try {
//			  return Response.status(200).entity( new ObjectMapper().writeValueAsString(mapResponse )).build();
//			} catch (JsonGenerationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				errormsg=e.getMessage();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				errormsg=e.getMessage();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				errormsg=e.getMessage();
//			}
//		  
//		  return Response.status(500).entity(errormsg).build();
//		  
	  }
	
//	 // /brapi/v1/germplasm?name={name}&#38;matchMethod={matchMethod}&#38;include={synonyms}&#38;pageSize={pageSize}&#38;page={page}")
//	  @Path("/germplasmOld")
//	  @GET
//	  @Produces({"application/json", "text/plain"})
//	  public Response getGermplasmOld(@QueryParam("name") String name,
//			  @DefaultValue("exact") @QueryParam("matchMethod") String matchMethod,  @QueryParam("include") String include, 
//			  @QueryParam("pageSize") String pageSize, @QueryParam("page") String page
//			  ) throws JSONException {
//		
//		  variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
//		  
//		  //Possible values are 'case_insensitive', 'exact' (case sensitive), 'wildcard' (which is case insensitive). Wildcard uses both '*' and '%' for any number of characters and '?' for one-character matching. Default is exact.
//		  
//		  Set vars=null;
//		  if(name==null || name.isEmpty()) {
//			  vars=variety.getGermplasm(dataset);
//		  } else {
//			  if(matchMethod.equals("exact") || matchMethod.equals("case_insensitive"))
//				  vars=variety.getGermplasmsByName(name, dataset);
//		  }
//		  
//		  Iterator<Variety> itVar=vars.iterator();
//		  List listvars=new ArrayList();
//		  while(itVar.hasNext()) {
//			  Variety var=itVar.next();
//			 
//			  Map germ=new HashMap();
//			  
//			  germ.put("germplasmDbId",var.getVarietyId());
//			  germ.put("defaultDisplayName",var.getName());
//			  germ.put("germplasmName",  var.getName());
//			  germ.put("accessionNumber", var.getAccession());
//			  germ.put("germplasmPUI", "http://oryzasnp.org/iric-portal/_variety.zul?irisid=" + var.getIrisId().toLowerCase());
//			  listvars.add(germ);  
//		  }
//		  
//		  return createJSONResponse(listvars);
//		 
//	  }
//	  
	  
	  // ************ GERMPLASM ********************
	  
	  private Map convertGermplasmResult(Variety var) {

		  Map germ=new HashMap();
		  germ.put("germplasmDbId",var.getVarietyId());
		  germ.put("defaultDisplayName",var.getName());
		  germ.put("germplasmName",  var.getName());
		  germ.put("accessionNumber", var.getAccession());
		  germ.put("germplasmPUI", "http://oryzasnp.org/iric-portal/_variety.zul?irisid=" + var.getIrisId().toLowerCase());
	  
		  return germ;
	  }
	  
	  @Path("/germplasm-search")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getGermplasmSearch(@QueryParam("germplasmName") String germplasmName,
			  @QueryParam("germplasmPUI") String germplasmPUI, @QueryParam("germplasmDbId") String germplasmDbId,  
			  @QueryParam("pageSize") String pageSize, @QueryParam("page") String page
			  ) throws JSONException {
		
		  variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
		  
		  Set vars=null;
		  if( (germplasmName==null || germplasmName.isEmpty())  && (germplasmPUI==null || germplasmPUI.isEmpty()) &&
				  (germplasmDbId==null || germplasmDbId.isEmpty())  ) {
			  vars=variety.getGermplasm(dataset);
		  } else {
			  vars=new LinkedHashSet();
			  if(germplasmPUI!=null && !germplasmPUI.isEmpty() && germplasmPUI.toLowerCase().contains( "oryzasnp.org/iric-portal/_variety.zul?irisid=") ) {
				  String irisid=germplasmPUI.split("=")[1].trim();
				  Set listNames=new HashSet();
				  listNames.add( irisid );
				  vars.addAll( variety.getGermplasmByIrisIds(listNames));
			  } else if(germplasmName!=null && !germplasmName.isEmpty()){
				  Set listNames=new HashSet();
				  listNames.add( germplasmName );
				  vars.addAll( variety.getGermplasmByNamesLike( listNames, dataset) );
			  } else if(germplasmDbId!=null && !germplasmDbId.isEmpty()){
				  vars.add( variety.getGermplasmById( BigDecimal.valueOf(Long.valueOf(germplasmDbId)),dataset) );
			  }
		  }
		  
		  Iterator<Variety> itVar=vars.iterator();
		  List listvars=new ArrayList();
		  while(itVar.hasNext()) {
			  Variety var=itVar.next();
			  listvars.add(convertGermplasmResult(var)); 
		  }
		  return createJSONResponse(listvars);
	  }	  
	  
	  @Path("/germplasm/{germplasmDbId}")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getGermplasmByDbId(@PathParam("germplasmDbId") String germplasmDbId,  
			  @QueryParam("pageSize") String pageSize, @QueryParam("page") String page
			  ) throws JSONException {
		
		  variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
		  Variety var= variety.getGermplasmById( BigDecimal.valueOf(Long.valueOf(germplasmDbId)),dataset);
		  return createJSONResponse(convertGermplasmResult(var));
		 
	  }
	  
	  //GET http://private-c2c4f-brapi.apiary-mock.com/brapi/v1/germplasm/id/markerprofiles
	  @Path("/germplasm/{germplasmDbId}/markerprofiles")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getGermplasmMarkerprofiles(@PathParam("germplasmDbId") String germplasmDbId) {
		  
		  List list=new ArrayList();
		  
		  long id=Long.valueOf(germplasmDbId);
		  if(id<=3024) {
			  list.add(Long.toString(id)); // filtered
			  list.add(Long.toString(id+10000)); //core
			  list.add(Long.toString(id+20000)); // base
			  list.add(Long.toString(id+30000)); //all
		  } else 
			  list.add(Long.toString(id));
		  return createJSONResponse(list);
		  
	  }

	  
	  
	  
	  // ************ MAPS ********************
	  

/*
{"path":"/brapi/v1/maps","description":"","operations":[{"nickname":"GET - get all maps","method":"GET","summary":"get all maps", "notes":"get maps for all species","type":"string","produces":["application/json"],
"parameters":[
	{"name":"species","paramType":"query","allowMultiple":false,"description":"Species Default:rice", "required":false,"type":"string" },
	{"name":"pageSize","paramType":"query","allowMultiple":false,"required":false,"type":"int"},
	{"name":"page","paramType":"query","allowMultiple":false,"required":false,"type":"int"}
]}]},

	    
{"path":"/brapi/v1/maps/{mapId}","description":"","operations":[{"nickname":"GET - get map linkage groups","method":"GET","summary":"Get map linkage groups", "notes":"Get map linkage groups","type":"string","produces":["application/json"],"parameters":[
	{"name":"mapId","paramType":"path","allowMultiple":false,"required":true,"type":"string" },
	{"name":"pageSize","paramType":"query","allowMultiple":false,"required":false,"type":"int"},
	{"name":"page","paramType":"query","allowMultiple":false,"required":false,"type":"int"}

]}]},

{"path":"/brapi/v1/maps/{mapId}/positions","description":"","operations":[{"nickname":"GET - get map markers","method":"GET","summary":"Get map markers", "notes":"Get map markers","type":"string","produces":["application/json"],"parameters":[
	{"name":"mapId","paramType":"path","allowMultiple":false,"required":true,"type":"string" },
	{"name":"linkageGroupId","paramType":"query","allowMultiple":false,"required":false,"type":"string" },
	{"name":"format","paramType":"query","allowMultiple":false,"required":false, "type":"string", "description":"result format [json (default), tsv]" },
	{"name":"pageSize","paramType":"query","allowMultiple":false,"required":false,"type":"int"},
	{"name":"page","paramType":"query","allowMultiple":false,"required":false,"type":"int"}
]}]},

		
{"path":"/brapi/v1/maps/{mapId}/positions/{linkageGroupId}","description":"","operations":[{"nickname":"GET - getMarkersByRangePosition","method":"GET","summary":"Get markers in linkage group in range of positions", "notes":"Get markers in linkage group in range of positions","type":"string","produces":["application/json"],"parameters":[
{"name":"mapId","paramType":"path","allowMultiple":false,"required":true,"type":"string", "description":"set to 9 (Rice Genome IRGSPv1 )" },
{"name":"linkageGroupId","paramType":"path","allowMultiple":false,"required":true,"type":"string", "description":"chromosome [chr01, chr02, .. chr12]" },
{"name":"min","paramType":"query","allowMultiple":false,"required":false,"type":"long", "description":"start bp" },
{"name":"max","paramType":"query","allowMultiple":false,"required":false, "type":"long", "description":"stop bp" },
{"name":"format","paramType":"query","allowMultiple":false,"required":false, "type":"string", "description":"result format [json (default), tsv]" },
{"name":"pageSize","paramType":"query","allowMultiple":false,"required":false,"type":"int"},
{"name":"page","paramType":"query","allowMultiple":false,"required":false,"type":"int"}

]}]},
*/
	  
	  
	  @Path("/maps")
	  @GET
	  //@Produces({"application/json", "text/plain"})
	  @Produces({"application/json", "text/plain"})
	  public Response getMaps() {
		  
		  //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		  //genotype=(GenotypeFacade)AppContext.checkBean(genoM
		  /*
		  Map mapRef2Conts=new HashMap();
		  mapRef2Conts.put("Japonica nipponbare",12);
		  mapRef2Conts.put("IR64-21",2919);
		  mapRef2Conts.put("93-11",12730);
		  mapRef2Conts.put("DJ123",2819);
		  mapRef2Conts.put("Kasalath",13);
		  */
		  
	
		  
		  
		  List listMaps=new ArrayList();
		  try {
			  Iterator<String> itMap = mapMap2Names.keySet().iterator();
			  while(itMap.hasNext()) {
				  String mapname=itMap.next();
				  
				  Map mapMap=new HashMap();
				  mapMap.put("mapDbId", mapname);
				  mapMap.put("name",mapMap2Names.get(mapname)[0]);
				  mapMap.put( "species", "Oryza sativa");
				  mapMap.put("type", "Physical");
				  mapMap.put("unit", "bp");
 				  mapMap.put("markerCount", mapMap2GroupsMarkers.get(mapname)[1]); // 32180725
				  mapMap.put("linkageGroupCount",  mapMap2GroupsMarkers.get(mapname)[0]);
				  mapMap.put("comments",mapMap2Names.get(mapname)[0] + ". SNPs called on IRGSP v1. Nipponbare genome");
				  listMaps.add(mapMap);
			  }
		  } catch(Exception ex) {
			  ex.printStackTrace();
		  }
					  
		  return createJSONResponse(listMaps);
		  
		
	  }
	  
	//  GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/maps/mapId
		  @Path("/maps/{mapDbId}")
		  @GET
		  @Produces({"application/json", "text/plain"})
		  public Response getMapDetails(@PathParam("mapDbId") String mapId) {

			  int countidx=0;
			  
			  if(mapId.equals("3kfiltered")) countidx=3;
			  else if(mapId.equals("3kall")) countidx=0;
			  else if(mapId.equals("3kcore")) countidx=1;
			  else if(mapId.equals("3kbase")) countidx=4;
			  else if(mapId.equals("hdra")) countidx=2;
			  
			  Map mapN=new HashMap();
			 // 3k all snps	 		3k core			hdra			filtered base
			  mapN.put("chr01",new int[]{3299626,42466,82248,576146,1911385});
			  mapN.put("chr02",new int[]{2744926,39922,69886,432353,1541096});
			  mapN.put("chr03",new int[]{2639545,30024,67500,399231,1459157});
			  mapN.put("chr04",new int[]{3187893,42598,62651,422737,1803432});
			  mapN.put("chr05",new int[]{2446560,28299,54788,345153,1275199});
			  mapN.put("chr06",new int[]{2666928,29966,57569,445466,1523230});
			  mapN.put("chr07",new int[]{2553359,28476,53534,383360,1430893});
			  mapN.put("chr08",new int[]{2672286,36221,54705,410405,1481796});
			  mapN.put("chr09",new int[]{2053103,23801,43002,285477,1156209});
			  mapN.put("chr10",new int[]{2170855,23548,43570,323654,1206751});
			  mapN.put("chr11",new int[]{2927740,47497,60213,435654,1735748});
			  mapN.put("chr12",new int[]{2701396,31570,50334,358328,1603881});

			  
			  //List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
			  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO,"ListItems");
			  List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(AppContext.getDefaultOrganism());
			  
			  List listLG=new ArrayList();
			  Iterator<Scaffold> itConts =  listConts.iterator();
			  while(itConts.hasNext()) {
				  Scaffold sc=itConts.next();
				  Map mapLG=new LinkedHashMap();
				  mapLG.put("linkageGroupDbId", sc.getName());
				  mapLG.put("maxPosition", sc.getLength());
				  mapLG.put("numberMarkers", ((int[])mapN.get(sc.getName()))[countidx]);
				  listLG.add(mapLG);
			  }
			  
			  Map mapMap=new LinkedHashMap();
			  mapMap.put("mapDbId", mapId);
			  mapMap.put("name", AppContext.getDefaultOrganism() + " reference genome");
			  mapMap.put( "species", AppContext.getDefaultOrganism());
			  mapMap.put("type", "Physical");
			  mapMap.put("unit", "bp");
			  mapMap.put("linkageGroups", listLG);
			  
			  return createJSONResponse(mapMap);
			  /*
			  "result": {
			        "mapId": "id",
			        "name": "Some map",
			        "type": "Genetic",
			        "unit": "cM",
			        "linkageGroups": [
			            {
			                "linkageGroupId": 1,
			                "numberMarkers": 100000,
			                "maxPosition": 10000000
			            },
			            {
			                "linkageGroupId": 2,
			                "numberMarkers": 1247,
			                "maxPostion": 12347889
			            }
			        ]
			    }
			  */
		  }
		  
	  
	  //GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/maps?species=speciesId&pageSize=pageSize&page=page&type=type
	  @Path("/maps/species")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getMapsSpecies() {
		  
		  //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		  //genotype=(GenotypeFacade)AppContext.checkBean(genoM
		  Map mapRef2Conts=new HashMap();
		  mapRef2Conts.put("Japonica nipponbare",12);
		  mapRef2Conts.put("IR64-21",2919);
		  mapRef2Conts.put("93-11",12730);
		  mapRef2Conts.put("DJ123",2819);
		  mapRef2Conts.put("Kasalath",13);
		  
		  
		  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		  List listMaps=new ArrayList();
		  try {
			  List orgs=listitemsDAO.getOrganisms();
			  
			  Iterator<Organism> itOrgs = orgs.iterator();
			  while(itOrgs.hasNext()) {
				  Organism org = itOrgs.next();
				  
				  if(!org.getOrganismId().equals( BigDecimal.valueOf(9))) continue;
				 // List listConts = listitemsDAO.getContigs(org.getName());
				  
				  Map mapMap=new HashMap();
				  mapMap.put("mapDbId", org.getOrganismId());
				  mapMap.put("name", org.getName() + " reference genome");
				  mapMap.put( "species", "Oryza sativa");
				  mapMap.put("type", "Physical");
				  mapMap.put("unit", "bp");
		         
				 if(org.getOrganismId().equals(BigDecimal.valueOf(9)))
					 //mapMap.put("markerCount", 1055366); // 32180725
					 mapMap.put("markerCount", 32180725); // 32180725
				  mapMap.put("linkageGroupCount", mapRef2Conts.get( org.getName()));
				  mapMap.put("comments","This is the Nipponbare rice genome IRGSP v1");
				  
				  listMaps.add(mapMap);
			  }
		  } catch(Exception ex) {
			  ex.printStackTrace();
		  }
					  
		  return createJSONResponse(listMaps);
		  
		
	  }
	  
	  
	//  GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/maps/mapId
	  @Path("/mapsOld/{mapDbId}")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getMapDetailsOld(@PathParam("mapDbId") String mapId) {
		  
		  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		  //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		  int orgid=Integer.valueOf(mapId);
		  Organism org = listitemsDAO.getOrganismById( orgid );
		  
		  Map mapN=new HashMap();
		 // 3k all snps			3k core			hdra			filtered
		  mapN.put("chr01",new int[]{3299626,42466,82248,576146,119269});
		  mapN.put("chr02",new int[]{2744926,39922,69886,432353,104188});
		  mapN.put("chr03",new int[]{2639545,30024,67500,399231,92918});
		  mapN.put("chr04",new int[]{3187893,42598,62651,422737,100586});
		  mapN.put("chr05",new int[]{2446560,28299,54788,345153,79415});
		  mapN.put("chr06",new int[]{2666928,29966,57569,445466,83968});
		  mapN.put("chr07",new int[]{2553359,28476,53534,383360,78484});
		  mapN.put("chr08",new int[]{2672286,36221,54705,410405,86889});
		  mapN.put("chr09",new int[]{2053103,23801,43002,285477,63961});
		  mapN.put("chr10",new int[]{2170855,23548,43570,323654,64391});
		  mapN.put("chr11",new int[]{2927740,47497,60213,435654,102765});
		  mapN.put("chr12",new int[]{2701396,31570,50334,358328,78532});

		  
		  //List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
		  List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
		  
		  List listLG=new ArrayList();
		  Iterator<Scaffold> itConts =  listConts.iterator();
		  while(itConts.hasNext()) {
			  Scaffold sc=itConts.next();
			  Map mapLG=new LinkedHashMap();
			  mapLG.put("linkageGroupDbId", sc.getName());
			  mapLG.put("maxPosition", sc.getLength());
			  if(mapId.equals("9"))
			  mapLG.put("numberMarkers", ((int[])mapN.get(sc.getName()))[4]);
	
			  listLG.add(mapLG);
		  }
		  
		  Map mapMap=new LinkedHashMap();
		  mapMap.put("mapDbId", mapId);
		  mapMap.put("name", org.getName() + " reference genome");
		  mapMap.put( "species", org.getName());
		  mapMap.put("type", "Physical");
		  mapMap.put("unit", "bp");
		  mapMap.put("linkageGroups", listLG);
		  
		  return createJSONResponse(mapMap);
		  /*
		  "result": {
		        "mapId": "id",
		        "name": "Some map",
		        "type": "Genetic",
		        "unit": "cM",
		        "linkageGroups": [
		            {
		                "linkageGroupId": 1,
		                "numberMarkers": 100000,
		                "maxPosition": 10000000
		            },
		            {
		                "linkageGroupId": 2,
		                "numberMarkers": 1247,
		                "maxPostion": 12347889
		            }
		        ]
		    }
		  */
	  }
		  
	  
	  
	  private GenotypeQueryParams setStudy(GenotypeQueryParams params, String studyId) {
		  if(studyId!=null) {
			  if(studyId.equals("3kfiltered")) {
				  params.setSnpSet( GenotypeQueryParams.SNP_FILTERED);
				  params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
			  }
			  else if(studyId.equals("3kcore")) {
				  params.setSnpSet( GenotypeQueryParams.SNP_CORE);
				  params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
			  }
			  else if(studyId.equals("3kbase")) {
				  params.setSnpSet( GenotypeQueryParams.SNP_BASE);
				  params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
			  }
			  else if(studyId.equals("3kall")) {
				  params.setSnpSet( GenotypeQueryParams.SNP_ALL);
				  params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
			  }
			  else if(studyId.equals("hdra")) {
				  params.setSnpSet( GenotypeQueryParams.SNP_FILTERED);
				  params.setDataset(VarietyFacade.DATASET_SNP_HDRA);
			  }
		  } else {

			  params.setSnpSet( GenotypeQueryParams.SNP_FILTERED);
			  params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
		  }
		  return params;
	  }
	    	
	  private String createMarkerName(SnpsAllvarsPos pos) {
		  return pos.getContig()+ "_" + pos.getPosition();
	  }
	  
	  
	  @Path("/maps/{mapDbId}/positions")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  ///maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
	//"/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
	  public Response getMarkers(@PathParam("mapDbId") String mapId,
			  @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
			  @DefaultValue("'") @QueryParam("linkageGroupDbId") String linkageGroupDbId,
			  @DefaultValue("json") @QueryParam("format") String format,
			  @DefaultValue("") @QueryParam("pageSize")  String pageSize, 
			  @DefaultValue("") @QueryParam("page")  String page
			  ) throws JSONException {
		
		  genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		  
		  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		  //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		  //int orgid=Integer.valueOf(mapId);
		  Organism org = listitemsDAO.getOrganismById( 9 );
		  
		  
		  //List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
		  List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
		  
		  List listresult=new ArrayList();
		  
		  BufferedWriter bw=null;
		  String tempfile=null;
		  String delimiter="\t";
		  if(!format.equals("json") ) {
			  tempfile="markers_" + AppContext.createTempFilename() + "." + format;
			  try {
				  bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
			  } catch(Exception ex) {
				  if(format.equals("csv") ) delimiter=",";
				  ex.printStackTrace();
			  }
		  }
		  
		  studyId=mapId;
		  
		  List listLG=new ArrayList();
		  Iterator<Scaffold> itConts =  listConts.iterator();
		  while(itConts.hasNext()) {
			  Scaffold sc=itConts.next();
			  
			  GenotypeQueryParams params=new  GenotypeQueryParams(
						null,  sc.getName(), 1L, sc.getLength(), 
						true, false,false,
						false, null, null, null, false, false);

			  params = setStudy(params, studyId);
		
			  if(linkageGroupDbId!=null && !linkageGroupDbId.isEmpty())
				  params.setsChr(linkageGroupDbId);
			  
			  
			  Iterator<SnpsAllvarsPos> it=genotype.getSNPPoslist(params).iterator();
			  while(it.hasNext()) {
				  SnpsAllvarsPos pos=it.next();
				  if(bw==null) {
					  Map mapMarker=new LinkedHashMap();	
					  mapMarker.put("markerDbId", createMarkerName(pos));
					  mapMarker.put("markerName", createMarkerName(pos));
					  mapMarker.put("location", pos.getPosition());
					  listresult.add( mapMarker );
				  } else {
					  try {
						  bw.append(createMarkerName(pos)).append(delimiter).append( pos.getContig() ).append(delimiter).append(pos.getPosition().toString() );
						  if(it.hasNext()) bw.append("\n");
					  } catch(Exception ex) {
						  ex.printStackTrace();
					  }
				  }
					  
			  }
		  }
	  
		  String errormsg="";
		  if(bw==null)
				return createJSONResponse(listresult);
		  else {
			  try {
				  bw.flush();
				  bw.close();
				  return createFilenameResponse(tempfile);
			  } catch (Exception ex) {
				  ex.printStackTrace();
				  errormsg=ex.getMessage();
			  }
		  }
		  
		  return Response.status(500).entity(errormsg).build();
	
	  }
	  
	  @Path("/maps/{mapDbId}/positions/{linkageGroupDbId}")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  ///maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
	//"/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
	  public Response getMarkers(@PathParam("mapDbId") String mapId,  @PathParam("linkageGroupDbId") String linkageGroupId, 
			  @QueryParam("min") long min, @QueryParam("max") long max, 
			  @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
			  @DefaultValue("json") @QueryParam("format") String format,
			  @DefaultValue("") @QueryParam("pageSize")  String pageSize, 
			  @DefaultValue("") @QueryParam("page")  String page
			  ) throws JSONException {
		
		  genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		  
		  studyId=mapId;
		  
			/**
			 * 
			 * @param colVarIds	Collection of variety IDs
			 * @param sChr	Contig name
			 * @param lStart	Start bp position
			 * @param lEnd	End bp position
			 * @param bSNP	Query SNPs
			 * @param bIndel	Query INDELs
			 * @param bCoreonly	Use core SNPs omly
			 * @param bMismatchonly	Include only varieties with mismatch in region
			 * @param poslist	Position list
			 * @param sSubpopulation	Query only subpopulation
			 * @param sLocus	Query within locus
			 * @param bAlignIndels	Display INDels as multiple sequence alignment
			 * @param bShowAllRefAlleles	Show alleles for all reference genomes
			 */
		  
		  
		  
		  if(min==0 && max==0) {
			  min=1;
			 
			  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
			  //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
			  //int orgid=Integer.valueOf(mapId);
			  Organism org = listitemsDAO.getOrganismById( 9 );
			  
			  List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
			  
				 
			  Iterator<Scaffold> itConts =  listConts.iterator();
			  while(itConts.hasNext()) {
				  Scaffold sc=itConts.next();
				  if(sc.getName().equals(linkageGroupId)){
					  max=sc.getLength();
					  break;
				  }
			  }
		  }
					
		  GenotypeQueryParams params=new  GenotypeQueryParams(
					null, linkageGroupId, min, max, 
					true, false,false,
					false, null, null, null, false, false);
		  
		  params = setStudy(params, studyId);
		  
		  List listresult=new ArrayList();
		  
		  Iterator<SnpsAllvarsPos> it=genotype.getSNPPoslist(params).iterator();

		  if(format.equals("json")) {
			  while(it.hasNext()) {
				  SnpsAllvarsPos pos=it.next();
				  Map mapMarker=new LinkedHashMap();	
				  mapMarker.put("markerDbId", createMarkerName( pos));
				  mapMarker.put("markerName", createMarkerName( pos));
				  mapMarker.put("location", pos.getPosition());
				  listresult.add( mapMarker );
			  }
			  return createJSONResponse(listresult);
		  } else {
			  String delimiter="\t";
			  if(format.equals("tsv")) delimiter="\t";
			  else if(format.equals("csv")) delimiter=",";
			
			  String tempfile="markers_" + AppContext.createTempFilename() + "." + format;
			  try {
				  BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
				  while(it.hasNext()) {
					  SnpsAllvarsPos pos=it.next();
					  bw.append(createMarkerName(pos)).append(delimiter).append(pos.getPosition().toString());
					  if(it.hasNext()) bw.append("\n");
				  }
				  bw.flush();
				  bw.close();
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
			  return createFilenameResponse(tempfile);
		  }
		 
	  }
	  
//	  
//	  
//	  @Path("/maps/{mapDbId}/positions")
//	  @GET
//	  @Produces({"application/json", "text/plain"})
//	  ///maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
//	//"/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
//	  public Response getMarkers(@PathParam("mapDbId") String mapId,
//			  @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
//			  @DefaultValue("'") @QueryParam("linkageGroupDbId") String linkageGroupDbId,
//			  @DefaultValue("json") @QueryParam("format") String format,
//			  @DefaultValue("") @QueryParam("pageSize")  String pageSize, 
//			  @DefaultValue("") @QueryParam("page")  String page
//			  ) throws JSONException {
//		
//		  genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
//		  
//		  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
//		  //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
//		  int orgid=Integer.valueOf(mapId);
//		  Organism org = listitemsDAO.getOrganismById( orgid );
//		  
//		  
//		  //List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
//		  List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
//		  
//		  List listresult=new ArrayList();
//		  
//		  BufferedWriter bw=null;
//		  String tempfile=null;
//		  String delimiter="\t";
//		  if(!format.equals("json") ) {
//			  tempfile="markers_" + AppContext.createTempFilename() + "." + format;
//			  try {
//				  bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
//			  } catch(Exception ex) {
//				  if(format.equals("csv") ) delimiter=",";
//				  ex.printStackTrace();
//			  }
//		  }
//		  
//		  List listLG=new ArrayList();
//		  Iterator<Scaffold> itConts =  listConts.iterator();
//		  while(itConts.hasNext()) {
//			  Scaffold sc=itConts.next();
//			  
//			  GenotypeQueryParams params=new  GenotypeQueryParams(
//						null,  sc.getName(), 1L, sc.getLength(), 
//						true, false,false,
//						false, null, null, null, false, false);
//
//			  params = setStudy(params, studyId);
//		
//			  if(linkageGroupDbId!=null && !linkageGroupDbId.isEmpty())
//				  params.setsChr(linkageGroupDbId);
//			  
//			  
//			  Iterator<SnpsAllvarsPos> it=genotype.getSNPPoslist(params).iterator();
//			  while(it.hasNext()) {
//				  SnpsAllvarsPos pos=it.next();
//				  if(bw==null) {
//					  Map mapMarker=new LinkedHashMap();	
//					  mapMarker.put("markerDbId", pos.getContig()+"_"+pos.getPosition());
//					  mapMarker.put("markerName", pos.getContig()+"_"+pos.getPosition());
//					  mapMarker.put("location", pos.getPosition());
//					  listresult.add( mapMarker );
//				  } else {
//					  try {
//						  bw.append(pos.getContig()+"_"+pos.getPosition()).append(delimiter).append( pos.getContig() ).append(delimiter).append(pos.getPosition().toString() );
//						  if(it.hasNext()) bw.append("\n");
//					  } catch(Exception ex) {
//						  ex.printStackTrace();
//					  }
//				  }
//					  
//			  }
//		  }
//	  
//		  String errormsg="";
//		  if(bw==null)
//				return createJSONResponse(listresult);
//		  else {
//			  try {
//				  bw.flush();
//				  bw.close();
//				  return createFilenameResponse(tempfile);
//			  } catch (Exception ex) {
//				  ex.printStackTrace();
//				  errormsg=ex.getMessage();
//			  }
//		  }
//		  
//		  return Response.status(500).entity(errormsg).build();
//	
//	  }
//	  
//	  
//	  @Path("/maps/{mapDbId}/positions/{linkageGroupDbId}")
//	  @GET
//	  @Produces({"application/json", "text/plain"})
//	  ///maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
//	//"/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
//	  public Response getMarkers(@PathParam("mapDbId") String mapId,  @PathParam("linkageGroupDbId") String linkageGroupId, 
//			  @QueryParam("min") long min, @QueryParam("max") long max, 
//			  @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
//			  @DefaultValue("json") @QueryParam("format") String format,
//			  @DefaultValue("") @QueryParam("pageSize")  String pageSize, 
//			  @DefaultValue("") @QueryParam("page")  String page
//			  ) throws JSONException {
//		
//		  genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
//		  
//		
//			/**
//			 * 
//			 * @param colVarIds	Collection of variety IDs
//			 * @param sChr	Contig name
//			 * @param lStart	Start bp position
//			 * @param lEnd	End bp position
//			 * @param bSNP	Query SNPs
//			 * @param bIndel	Query INDELs
//			 * @param bCoreonly	Use core SNPs omly
//			 * @param bMismatchonly	Include only varieties with mismatch in region
//			 * @param poslist	Position list
//			 * @param sSubpopulation	Query only subpopulation
//			 * @param sLocus	Query within locus
//			 * @param bAlignIndels	Display INDels as multiple sequence alignment
//			 * @param bShowAllRefAlleles	Show alleles for all reference genomes
//			 */
//		  
//		  
//		  
//		  if(min==0 && max==0) {
//			  min=1;
//			 
//			  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
//			  //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
//			  int orgid=Integer.valueOf(mapId);
//			  Organism org = listitemsDAO.getOrganismById( orgid );
//			  
//			  List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
//			  
//				 
//			  Iterator<Scaffold> itConts =  listConts.iterator();
//			  while(itConts.hasNext()) {
//				  Scaffold sc=itConts.next();
//				  if(sc.getName().equals(linkageGroupId)){
//					  max=sc.getLength();
//					  break;
//				  }
//			  }
//		  }
//					
//		  GenotypeQueryParams params=new  GenotypeQueryParams(
//					null, linkageGroupId, min, max, 
//					true, false,false,
//					false, null, null, null, false, false);
//		  
//		  params = setStudy(params, studyId);
//		  
//		  List listresult=new ArrayList();
//		  
//		  Iterator<SnpsAllvarsPos> it=genotype.getSNPPoslist(params).iterator();
//
//		  if(format.equals("json")) {
//			  while(it.hasNext()) {
//				  SnpsAllvarsPos pos=it.next();
//				  Map mapMarker=new LinkedHashMap();	
//				  mapMarker.put("markerDbId", pos.getContig()+"_"+pos.getPosition());
//				  mapMarker.put("markerName", pos.getContig()+"_"+pos.getPosition());
//				  mapMarker.put("location", pos.getPosition());
//				  listresult.add( mapMarker );
//			  }
//			  return createJSONResponse(listresult);
//		  } else {
//			  String delimiter="\t";
//			  if(format.equals("tsv")) delimiter="\t";
//			  else if(format.equals("csv")) delimiter=",";
//			
//			  String tempfile="markers_" + AppContext.createTempFilename() + "." + format;
//			  try {
//				  BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
//				  while(it.hasNext()) {
//					  SnpsAllvarsPos pos=it.next();
//					  bw.append(pos.getContig()+"_"+pos.getPosition() ).append(delimiter).append(pos.getPosition().toString());
//					  if(it.hasNext()) bw.append("\n");
//				  }
//				  bw.flush();
//				  bw.close();
//			  } catch(Exception ex) {
//				  ex.printStackTrace();
//			  }
//			  return createFilenameResponse(tempfile);
//		  }
//		 
//	  }
//	  
	  

	  // *************************** STUDY ***********************************
	  /*
	  mapCall=createCall("studyTypes",new String[]{"GET"}, new String[]{"json"});
	  mapName2Call.put( "studyTypes", mapCall);
	  mapType2Calls.get("json").add(mapCall);
	  listAllCalls.add(mapCall);

	  mapCall=createCall("studies-search",new String[]{"GET"}, new String[]{"json"});
	  mapName2Call.put( "studies-search", mapCall);
	  mapType2Calls.get("json").add(mapCall);
	  listAllCalls.add(mapCall);

	  mapCall=createCall("studies/{studiesDbid}",new String[]{"GET"}, new String[]{"json"});
	  mapName2Call.put( "studies/{studiesDbid}", mapCall);
	  mapType2Calls.get("json").add(mapCall);
	  listAllCalls.add(mapCall);
	  
	  mapCall=createCall("studies/{studiesDbid}/germplasm",new String[]{"GET"}, new String[]{"json"});
	  mapName2Call.put( "studies/{studiesDbid}/germplasm", mapCall);
	  mapType2Calls.get("json").add(mapCall);
	  listAllCalls.add(mapCall);
	  */
	  
	  //GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/studies-search?studyType=studyType&seasonDbId=seasonDbId&locationDbId=locationDbId&programDbId=programDbId&germplasmDbIds=germplasmDbIds&observationVariableDbIds=observationVariableDbIds&pageSize=pageSize&page=page&active=active&sortBy=sortBy&sortOrder=sortOrder
		
	  @Path("/studyTypes")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getStudyTypes() {
		  List listTypes=new ArrayList();
		  Map mapType=new LinkedHashMap();
		  mapType.put("name","Genotype");
		  mapType.put("description", "SNPs called from NextGen rice sequencing projects");
		  listTypes.add( mapType);
		  return createJSONResponse(listTypes);
	  }
	  
	  private Map createStudy(String dbId, String name) {
		  return createStudy(dbId, name, "");
	  }
	  
	  private Map createStudy(String dbId, String name, String expand ) {
		  Map map=new LinkedHashMap();
		  map.put("studyDbId",dbId);
		  map.put("studyName",name);
		  map.put("studyType","Genotype");
		  variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
		  
		  if(expand!=null && expand.contains("germplasm")) {
			  
			  Set vars=new LinkedHashSet();
			  if(dbId.equals("hdra")) {
				  vars=variety.getGermplasm(VarietyFacade.DATASET_SNP_HDRA);
			  }else if(dbId.startsWith("3k")) {
				  vars=variety.getGermplasm(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
			  }
			  List listvars=new ArrayList();
			  Iterator<Variety> itvar=vars.iterator();
			  while(itvar.hasNext()) {
				  listvars.add( this.convertGermplasmResult(itvar.next()));
			  }
			  map.put("germplasm",listvars);
		  }
		  else map.put("germplasm", "/brapi/v1/" + dbId + "/germplasm");
			  
		  return map;
	  }
	  
	  
	  @Path("/studies-search")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getStudies(@DefaultValue("Genotype") @QueryParam("studyType") String studyType){
		  
		  List listStudies=new ArrayList();
		  
		  if(studyType.equals("Genotype" )) {
		  
			  listStudies.add(createStudy("3kfiltered","3k Rice Genome Project filtered SNPs (4.8Mb)"));
			  listStudies.add(createStudy("3kcore","3k Rice Genome Project core SNPs (404kb)"));
			  listStudies.add(createStudy("3kbase","3k Rice Genome Project base SNPs (18Mb)"));
			  listStudies.add(createStudy("3kall","3k Rice Genome Project all SNPs (32Mb)"));
			  listStudies.add(createStudy("hdra","High Density Rice Array HDRA (700kb)"));
		  }
		  
		  /*
		   *   {
        "studyDbId": 35,
        "name": "Earlygenerationtesting",
        "trialDbId": 1,
        "trialName": "InternationalTrialA",
        "studyType": "Trial",
        "seasons": [
          "2007 Spring",
          "2008 Fall"
        ],
        "locationDbId": 23,
        "locationName": "Kenya",
        "programDbId": 27,
        "programName": "Drought Resistance Program A",
        "startDate": "2007-06-01",
        "endDate": "2008-12-31",
        "active": "true",
        "additionalInfo": {
          "property1Name": "property1Value",
          "property2Name": "property2Value",
          "property3Name": "property3Value"
        }
      },
		   */
		  
		  
		  return createJSONResponse(listStudies);
		  
	  }
	  
	  @Path("/studies/{studyDbId}")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getStudiesById( @PathParam("studyDbId") String studyDbId, @QueryParam("expand") String expand){
		  
		  if(studyDbId==null) return createJSONResponse(new ArrayList());
		  
		  if(studyDbId.equals("3kfiltered")) return createJSONResponse(createStudy("3kfiltered","3k Rice Genome Project filtered SNPs (4.8Mb)",expand));
		  if(studyDbId.equals("3kcore")) return createJSONResponse(createStudy("3kcore","3k Rice Genome Project core SNPs (404kb)",expand));
		  if(studyDbId.equals("3kbase")) return createJSONResponse(createStudy("3kbase","3k Rice Genome Project base SNPs (18Mb)",expand));
		  if(studyDbId.equals("3kall")) return createJSONResponse(createStudy("3kall","3k Rice Genome Project all SNPs (32Mb)",expand));
		  if(studyDbId.equals("hdra")) return createJSONResponse(createStudy("hdra","High Density Rice Array HDRA (700kb)",expand));
		  
		  return createJSONResponse(new ArrayList());
	  }

	  
	  @Path("/studies/{studyDbId}/germplasm")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  public Response getStudiesByIdGermplasm(@PathParam("studyDbId") String studyDbId){
		  
		  variety= (VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
		  
		  if(studyDbId==null) return createJSONResponse(new ArrayList());
		  
		  if(studyDbId.equals("3kfiltered") || studyDbId.equals("3kcore") || studyDbId.equals("3kbase")  || studyDbId.equals("3kall")) {
			  
			  List listVars=new ArrayList();
			  Iterator<Variety> itvars=variety.getGermplasm(VarietyFacade.DATASET_SNPINDELV2_IUPAC).iterator();
			  while(itvars.hasNext()) {
				  listVars.add( this.convertGermplasmResult(itvars.next()) );
			  }
			  return createJSONResponse(listVars);
		  }
		  if(studyDbId.equals("hdra")) {
			  List listVars=new ArrayList();
			  Iterator<Variety> itvars=variety.getGermplasm(VarietyFacade.DATASET_SNP_HDRA).iterator();
			  while(itvars.hasNext()) {
				  listVars.add( this.convertGermplasmResult(itvars.next()) );
			  }
			  return createJSONResponse(listVars);
		  }
		  
		  return createJSONResponse(new ArrayList());
	  }

	  // **************** MARKERPROFILE **********************************
	  
	  
	  private Map createMarkerprofile(long profiledbid, long germplasmdbid, String displayname, String sampleid, String extractid, String analysismethod, long resultcount ) {
		  
		  Map map=new LinkedHashMap();
		  
		  map.put("markerProfileDbId", String.valueOf(profiledbid));
				  map.put( "germplasmDbId", String.valueOf(germplasmdbid));
						  map.put("uniqueDisplayName", displayname);
								  map.put("sampleDbId", sampleid);
										  map.put("extractDbId",extractid);
												  map.put("analysisMethod",analysismethod);
														  map.put("resultCount",resultcount);
														  return map;
		  
	  }

		// http://private-c2c4f-brapi.apiary-mock.com/brapi/v1/markerprofiles?germplasm=germplasmDbId&extract=extractDbId&method=methodDbId&pageSize=100&page=4
		  @Path("/markerprofiles")
		  @GET
		  @Produces({"application/json", "text/plain"})
		  public Response getMarkerprofiles( @QueryParam("germplasmDbId") String germplasm,
				  @QueryParam("sampleDbId") String sample,  @QueryParam("extractDbId") String extract, 
				  @QueryParam("studyDbId") String studyId, //  @QueryParam("method") String method, 
				  @QueryParam("pageSize") String pageSize, @QueryParam("page") String page) throws JSONException {
			  
			  listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
			  Map<BigDecimal,Variety> mapVar2Name=listitemsDAO.getMapId2Variety(VarietyFacade.DATASET_SNP_ALL);
			  
			  
			  List listProfiles=new ArrayList();
			  if(germplasm!=null) {
				  Long id=Long.valueOf(germplasm );
				  BigDecimal bdId=BigDecimal.valueOf(id);
				  
				  if(id<=3024) {
					  String study="3kfiltered";
					  listProfiles.add(createMarkerprofile(id,id, mapVar2Name.get(bdId).getName() + ";" +study ,null,null,"SNP calling", mapMap2GroupsMarkers.get(study)[1]));
					  study="3kcore";
					  listProfiles.add(createMarkerprofile(id+10000,id, mapVar2Name.get(bdId).getName() + ";" +study ,null,null,"SNP calling", mapMap2GroupsMarkers.get(study)[1]));
					  study="3kbase";
					  listProfiles.add(createMarkerprofile(id+20000,id, mapVar2Name.get(bdId).getName() + ";" +study ,null,null,"SNP calling", mapMap2GroupsMarkers.get(study)[1]));
					  study="3kall";
					  listProfiles.add(createMarkerprofile(id+30000,id, mapVar2Name.get(bdId).getName() + ";" +study ,null,null,"SNP calling", mapMap2GroupsMarkers.get(study)[1]));
					  
					  /*
					  listProfiles.add(Long.toString(id));
					  listProfiles.add(Long.toString(id+10000));
					  listProfiles.add(Long.toString(id+20000));
					  listProfiles.add(Long.toString(id+30000));
					  */
				  }
				  else  {
					  String study="hdra";
					  listProfiles.add(createMarkerprofile(id,id, mapVar2Name.get(bdId).getName() + ";" +study ,null,null,"SNP calling", mapMap2GroupsMarkers.get(study)[1]));
					  //listProfiles.add(Long.toString(id));
				  }
				  
			  } else if(studyId!=null && studyId.startsWith("3k")) {
				  long offset=0;
				  if(studyId.equals("3kfiltered"));
				  else if(studyId.equals("3kcore")) offset=10000;
				  else if(studyId.equals("3kbase")) offset=20000;
				  else if(studyId.equals("3kall")) offset=30000;
				  for(int i=1; i<=3024; i++) {
					  listProfiles.add(createMarkerprofile(i+offset,i, mapVar2Name.get(BigDecimal.valueOf(i)).getName() + ";" +studyId ,null,null,"SNP calling", mapMap2GroupsMarkers.get(studyId)[1]));
					  
					  //listProfiles.add( String.valueOf(i+offset));
				  }
			  } else if(studyId!=null && studyId.equals("hdra")) {
				  for(int i=3025; i<=4592; i++) {
					  listProfiles.add(createMarkerprofile(i,i, mapVar2Name.get(BigDecimal.valueOf(i)).getName() + "-" +studyId ,null,null,"SNP calling", mapMap2GroupsMarkers.get(studyId)[1]));
					  //listProfiles.add( String.valueOf(i));
				  }
			  };
			  
			  return createJSONResponse(listProfiles);
		  }
		  
		  @Path("/markerprofiles/{markerprofileDbId}")
		  @GET
		  @Produces({"application/json", "text/plain"})
		  public Response getMarkerprofilesDbId( @PathParam("markerprofileDbId") String markerprofileId,
				  //@DefaultValue("") @FormParam("markerDbId") String markerDbId,
				  @DefaultValue("false") @QueryParam("expandHomozygotes") boolean expandHomozygotes,
				  @DefaultValue("N") @QueryParam("unknownString") String unknownString,
				  @DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
				  @DefaultValue("/") @QueryParam("sepUnphased")  String sepUnphased, 
				  @DefaultValue("") @QueryParam("format")  String format,
				  @DefaultValue("")  @QueryParam("pageSize") String pageSize, 
				  @DefaultValue("") @QueryParam("page") String page) throws JSONException {
		
			  
			  String markerprofileDbIdstr[]=markerprofileId.split("[\n,]");
			  List<String> listmarkerprofileDbId=new ArrayList();
			  for(int i=0; i< markerprofileDbIdstr.length; i++) {
				  
				  String s=markerprofileDbIdstr[i].trim();
				  if(!s.isEmpty()) listmarkerprofileDbId.add(s);
			  }
			 
			  List<String> listmarkerDbId=new ArrayList();
		  /*
			  String markerDbIdstr[]=markerDbId.split("[\n,]");
			  List<String> listmarkerDbId=new ArrayList();
			  for(int i=0; i< markerDbIdstr.length; i++) {
				  String s=markerDbIdstr[i].trim();
				  if(!s.isEmpty()) listmarkerDbId.add(s);
			  }
			*/  
			  
			  //Map mapData=allelematrix(listmarkerprofileDbId, listmarkerDbId ,
				//	  expandHomozygotes,unknownString,sepPhased,sepUnphased, format, false);
			  
			  try {
				  
				  AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);
				  
				  return allelematrix(listmarkerprofileDbId, listmarkerDbId ,
						  expandHomozygotes,unknownString,sepPhased,sepUnphased, format, true,"markerprofile" );
						  
				  /*
				  	Object[] objresp= allelematrix(listmarkerprofileDbId, listmarkerDbId ,
						  expandHomozygotes,unknownString,sepPhased,sepUnphased, format, true);
				  	if(objresp[1]!=null) {
				  		Map mapResp=(Map)objresp[1];
				  		
				  		 long profileid=Long.valueOf(markerprofileId);
				  		 long germplasmid=profileid;
				  		 String studyId="3kfiltered";
				  		 
				  		 if(profileid>=1 && profileid<=3024) { studyId= "3kfiltered"; }
						  else if(profileid>=10001 && profileid<=13024) { studyId= "3kcore"; germplasmid-=10000; }
						  else if(profileid>=20001 && profileid<=23024) { studyId= "3kbase";germplasmid-=20000; }
						  else if(profileid>=30001 && profileid<=33024) { studyId= "3kall";germplasmid-=30000; }
						  else if(profileid>=3025 && profileid<=4592) studyId= "hdra";

				  		mapResp.put("germplasmDbId" , germplasmid);
				  		mapResp.put("uniqueDisplayName" , "germplasmDbId=" + germplasmid + ";studyDbId=" + studyId);
				  		mapResp.put("markerProfileDbId",  profileid);
				  		mapResp.put("analysisMethod","SNP calling");
				  		mapResp.put("extractDbId",null);
				  		return createJSONResponse(mapResp);
				  		
				  	}
				  	else return (Response)objresp[0];
				  	*/ 
				
				} catch (Exception ex) {
					ex.printStackTrace();
					return Response.status(500).entity(ex.getMessage()).build();
				}
			  
			    
			  
			  /*
			  
			  if(germplasm!=null) {
				  Long id=Long.valueOf(germplasm );
				  if(id<=3024) {
					  listProfiles.add(Long.toString(id));
					  listProfiles.add(Long.toString(id+10000));
					  listProfiles.add(Long.toString(id+20000));
					  listProfiles.add(Long.toString(id+30000));
				  }
				  else  listProfiles.add(Long.toString(id));
				  
			  } else if(studyId!=null && studyId.startsWith("3k")) {
				  long offset=0;
				  if(studyId.equals("3kfiltered"));
				  else if(studyId.equals("3kcore")) offset=10000;
				  else if(studyId.equals("3kbase")) offset=20000;
				  else if(studyId.equals("3kall")) offset=30000;
				  for(int i=1; i<=3024; i++) {
					  listProfiles.add( String.valueOf(i+offset));
				  }
			  } else if(studyId!=null && studyId.equals("hdra")) {
				  for(int i=3025; i<=4592; i++) {
					  listProfiles.add( String.valueOf(i));
				  }
			  };
			  */
			 // return createJSONResponse(listProfiles);
		  }
	  
	  
	  // ***************** ALLELE MATRIX ************************************
	  
		public class RequestAlleleMatrix {
			List<String> markerprofileDbId;
			List<String> markerDbId;
			boolean expandHomozygotes=false;
			String unknownString="N";
			String sepPhased="|";
			String sepUnphased="/";
			String format="tsv";
			String pageSize;
			String page;

			
			public RequestAlleleMatrix() {
				super();
				// TODO Auto-generated constructor stub
			}


			public List<String> getMarkerprofileDbId() {
				return markerprofileDbId;
			}


			public void setMarkerprofileDbId(List<String> markerprofileDbId) {
				this.markerprofileDbId = markerprofileDbId;
			}


			public List<String> getMarkerDbId() {
				return markerDbId;
			}


			public void setMarkerDbId(List<String> markerDbId) {
				this.markerDbId = markerDbId;
			}


			public boolean isExpandHomozygotes() {
				return expandHomozygotes;
			}


			public void setExpandHomozygotes(boolean expandHomozygotes) {
				this.expandHomozygotes = expandHomozygotes;
			}


			public String getUnknownString() {
				return unknownString;
			}


			public void setUnknownString(String unknownString) {
				this.unknownString = unknownString;
			}


			public String getSepPhased() {
				return sepPhased;
			}


			public void setSepPhased(String sepPhased) {
				this.sepPhased = sepPhased;
			}


			public String getSepUnphased() {
				return sepUnphased;
			}


			public void setSepUnphased(String sepUnphased) {
				this.sepUnphased = sepUnphased;
			}


			public String getFormat() {
				return format;
			}


			public void setFormat(String format) {
				this.format = format;
			}


			public String getPageSize() {
				return pageSize;
			}


			public void setPageSize(String pageSize) {
				this.pageSize = pageSize;
			}


			public String getPage() {
				return page;
			}


			public void setPage(String page) {
				this.page = page;
			}
			
			
			
		}
		  @Path("/allelematrix-search2")
		  @POST
		  //@Consumes({"multipart/form-data", "application/json","application/x-www-form-urlencoded"})
		  //@Consumes("application/x-www-form-urlencoded")
		  @Consumes("application/json")
		  @Produces({"application/json", "text/plain"})
		
		 public Response postAlleleMatrix2(
				  @NotNull @QueryParam("markerprofileDbId") List<String> listmarkerprofileDbId, 
				  @QueryParam("markerDbId") List<String> listmarkerDbId,
				  @DefaultValue("false") @QueryParam("expandHomozygotes") boolean expandHomozygotes,
				  @DefaultValue("N") @QueryParam("unknownString") String unknownString,
				  @DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
				  @DefaultValue("/") @QueryParam("sepUnphased")  String sepUnphased, 
				  @DefaultValue("") @QueryParam("format")  String format,
				  @DefaultValue("")  @QueryParam("pageSize") String pageSize, 
				  @DefaultValue("") @QueryParam("page") String page) { //
				  
		  	/*{ 
			  @NotNull @FormParam("markerprofileDbId") List<String> listmarkerprofileDbId, 
		  }
				  @DefaultValue("") @FormParam("markerDbId") List<String> listmarkerDbId,
				  @DefaultValue("false") @FormParam("expandHomozygotes") boolean expandHomozygotes,
				  @DefaultValue("N") @FormParam("unknownString") String unknownString,
				  @DefaultValue("|") @FormParam("sepPhased") String sepPhased,
				  @DefaultValue("/") @FormParam("sepUnphased")  String sepUnphased, 
				  @DefaultValue("") @FormParam("format")  String format,
				  @DefaultValue("")  @FormParam("pageSize") String pageSize, 
				  @DefaultValue("") @FormParam("page") String page) { // throws JSONException {
			  */
			
		  /*
		  public Response postAlleleMatrix(
				  @NotNull @RequestParam("markerprofileDbId") List<String> listmarkerprofileDbId, 
				  @RequestParam("markerDbId") List<String> listmarkerDbId,
				  @DefaultValue("false") @RequestParam("expandHomozygotes") boolean expandHomozygotes,
				  @DefaultValue("N") @RequestParam("unknownString") String unknownString,
				  @DefaultValue("|") @RequestParam("sepPhased") String sepPhased,
				  @DefaultValue("/") @RequestParam("sepUnphased")  String sepUnphased, 
				  @DefaultValue("") @RequestParam("format")  String format,
				  @DefaultValue("")  @RequestParam("pageSize") String pageSize, 
				  @DefaultValue("") @RequestParam("page") String page) { // throws JSONException {
			  */
			  
			  AppContext.debug("postAlleleMatrix: markerDbId=" + (listmarkerDbId!=null?listmarkerDbId.size():"null") + " markerprofileDbId=" + 
			  (listmarkerprofileDbId!=null?listmarkerprofileDbId.size():"null"));
			  if(listmarkerDbId!=null && listmarkerDbId.size()<20) AppContext.debug("listmarkerDbId=" + listmarkerDbId);
			  if(listmarkerprofileDbId!=null && listmarkerprofileDbId.size()<20) AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);
			  
				  /*
			  	AppContext.debug("postAlleleMatrix: markerDbId=" +markerDbId + " markerprofileDbId=" + markerprofileDbId);

					  String markerprofileDbIdstr[]=markerprofileDbId.split("[\n,]");
					  List<String> listmarkerprofileDbId=new ArrayList();
					  for(int i=0; i< markerprofileDbIdstr.length; i++) {
						  
						  String s=markerprofileDbIdstr[i].trim();
						  if(!s.isEmpty()) listmarkerprofileDbId.add(s);
					  }
					 
				  
					  String markerDbIdstr[]=markerDbId.split("[\n,]");
					  List<String> listmarkerDbId=new ArrayList();
					  for(int i=0; i< markerDbIdstr.length; i++) {
						  String s=markerDbIdstr[i].trim();
						  if(!s.isEmpty()) listmarkerDbId.add(s);
					  }

					  */
			  return allelematrix(listmarkerprofileDbId, listmarkerDbId ,
					  expandHomozygotes,unknownString,sepPhased,sepUnphased, format, true,"allelematrix-search");
	 
		  }
		  
		 
		
	  @Path("/allelematrix-search")
	  @POST
	  //@Consumes({"multipart/form-data", "application/json","application/x-www-form-urlencoded"})
	  //@Consumes("application/x-www-form-urlencoded")
	  @Consumes("application/json")
	  @Produces({"application/json", "text/plain"})

	  /*
	  public Response postAlleleMatrix(@RequestBody RequestAlleleMatrix mapRequest) throws JSONException {
		  AppContext.debug("mapRequest=" + mapRequest );
		  List<String> listmarkerprofileDbId = mapRequest.getMarkerprofileDbId();
		  List<String> listmarkerDbId = mapRequest.getMarkerDbId();
		  
		  boolean expandHomozygotes = mapRequest.isExpandHomozygotes();
		  String unknownString=mapRequest.getUnknownString();
		  String sepPhased=mapRequest.getSepPhased();
		  String sepUnphased=mapRequest.getSepUnphased();
		  String format=mapRequest.getFormat();
*/
		  /*	  
	  public Response postAlleleMatrix(@RequestBody Map mapRequest) throws JSONException {
		  AppContext.debug("mapRequest=" + mapRequest );
		  List<String> listmarkerprofileDbId = (List<String>)mapRequest.get("markerprofileDbId");
		  List<String> listmarkerDbId = (List<String>)mapRequest.get("markerDbId");
		  
		  boolean expandHomozygotes =Boolean.valueOf((String)mapRequest.get("expandHomozygotes"));
		  String unknownString=(String)mapRequest.get("unknownString");
		  String sepPhased=(String)mapRequest.get("sepPhased");
		  String sepUnphased=(String)mapRequest.get("sepUnphased");
		  String format=(String)mapRequest.get("format");
		  */
		  
	  public Response postAlleleMatrix(@NotNull @FormParam("markerprofileDbId") List<String> listmarkerprofileDbId, 
			  @DefaultValue("") @FormParam("markerDbId") List<String> listmarkerDbId,
			  @DefaultValue("false") @FormParam("expandHomozygotes") boolean expandHomozygotes,
			  @DefaultValue("N") @FormParam("unknownString") String unknownString,
			  @DefaultValue("|") @FormParam("sepPhased") String sepPhased,
			  @DefaultValue("/") @FormParam("sepUnphased")  String sepUnphased, 
			  @DefaultValue("") @FormParam("format")  String format,
			  @DefaultValue("")  @FormParam("pageSize") String pageSize, 
			  @DefaultValue("") @FormParam("page") String page) { // throws JSONException {
		  
		
	  /*
	  public Response postAlleleMatrix(
			  @NotNull @RequestParam("markerprofileDbId") List<String> listmarkerprofileDbId, 
			  @RequestParam("markerDbId") List<String> listmarkerDbId,
			  @DefaultValue("false") @RequestParam("expandHomozygotes") boolean expandHomozygotes,
			  @DefaultValue("N") @RequestParam("unknownString") String unknownString,
			  @DefaultValue("|") @RequestParam("sepPhased") String sepPhased,
			  @DefaultValue("/") @RequestParam("sepUnphased")  String sepUnphased, 
			  @DefaultValue("") @RequestParam("format")  String format,
			  @DefaultValue("")  @RequestParam("pageSize") String pageSize, 
			  @DefaultValue("") @RequestParam("page") String page) { // throws JSONException {
		  */
		  
		  AppContext.debug("postAlleleMatrix: markerDbId=" + (listmarkerDbId!=null?listmarkerDbId.size():"null") + " markerprofileDbId=" + 
		  (listmarkerprofileDbId!=null?listmarkerprofileDbId.size():"null"));
		  if(listmarkerDbId!=null && listmarkerDbId.size()<20) AppContext.debug("listmarkerDbId=" + listmarkerDbId);
		  if(listmarkerprofileDbId!=null && listmarkerprofileDbId.size()<20) AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);
		  
			  /*
		  	AppContext.debug("postAlleleMatrix: markerDbId=" +markerDbId + " markerprofileDbId=" + markerprofileDbId);

				  String markerprofileDbIdstr[]=markerprofileDbId.split("[\n,]");
				  List<String> listmarkerprofileDbId=new ArrayList();
				  for(int i=0; i< markerprofileDbIdstr.length; i++) {
					  
					  String s=markerprofileDbIdstr[i].trim();
					  if(!s.isEmpty()) listmarkerprofileDbId.add(s);
				  }
				 
			  
				  String markerDbIdstr[]=markerDbId.split("[\n,]");
				  List<String> listmarkerDbId=new ArrayList();
				  for(int i=0; i< markerDbIdstr.length; i++) {
					  String s=markerDbIdstr[i].trim();
					  if(!s.isEmpty()) listmarkerDbId.add(s);
				  }

				  */
		  return allelematrix(listmarkerprofileDbId, listmarkerDbId ,
				  expandHomozygotes,unknownString,sepPhased,sepUnphased, format, true,"allelematrix-search");
 
	  }
	  
	  @Path("/allelematrix")
	  @GET
	  @Produces({"application/json", "text/plain"})
	  //allelematrix?markerprofileDbId=100&markerprofileDbId=101&markerDbId=322&markerDbId=418&pageSize=&page=
	  
	  public Response getAlleleMatrix(
			  @NotNull @QueryParam("markerprofileDbId") List<String> markerprofileDbId, 
			  @DefaultValue("") @QueryParam("markerDbId") List<String> markerDbId,  
			  @DefaultValue("false") @QueryParam("expandHomozygotes") boolean expandHomozygotes,
			  @DefaultValue("N") @QueryParam("unknownString") String unknownString,
			  @DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
			  @DefaultValue("/") @QueryParam("sepUnphased")  String sepUnphased, 
			  @DefaultValue("")  @QueryParam("format")  String format,
			  @DefaultValue("") @QueryParam("pageSize")  String pageSize, 
			  @DefaultValue("") @QueryParam("page")  String page
			  ) throws JSONException {
		  
		  
		  AppContext.debug("getAlleleMatrix: markerDbId=" +markerDbId + " markerprofileDbId=" + markerprofileDbId);
		  
		  if(markerprofileDbId.size()==1) {
			  String markerprofileDbIdstr[]=markerprofileDbId.get(0).split("[\n,]");
			  List<String> listmarkerprofileDbId=new ArrayList();
			  for(int i=0; i< markerprofileDbIdstr.length; i++) {
				  
				  String s=markerprofileDbIdstr[i].trim();
				  if(!s.isEmpty()) listmarkerprofileDbId.add(s);
			  }
			  markerprofileDbId=listmarkerprofileDbId;
		  }
		 
		  if(markerDbId.size()==1) {
			  String markerDbIdstr[]=markerDbId.get(0).split("[\n,]");
			  List<String> listmarkerDbId=new ArrayList();
			  for(int i=0; i< markerDbIdstr.length; i++) {
				  String s=markerDbIdstr[i].trim();
				  if(!s.isEmpty()) listmarkerDbId.add(s);
			  }
			  markerDbId=listmarkerDbId;
		  }
		  

		  
		  return allelematrix(markerprofileDbId, markerDbId, 
				  expandHomozygotes,unknownString,sepPhased,sepUnphased, format, true,"allelematrix");
		  
	  }
	  
//	  //@RequestMapping(value = "/postallelematrix", method = RequestMethod.POST)
//	  @Path("/postallelematrix")
//	  @POST
//	  //@ResponseBody
//	  @Consumes({ MediaType.APPLICATION_FORM_URLENCODED , MediaType.MULTIPART_FORM_DATA})
//	  @Produces({"application/json", "text/plain"})
//	  public Response postallelematrix(HttpServletRequest request){
//		  
//		  String markerprofileDbIdstr[]=request.getParameter("markerprofileDbId" ).split("\n");
//		  List<String> listmarkerprofileDbId=new ArrayList();
//		  for(int i=0; i< markerprofileDbIdstr.length; i++) {
//			  
//			  String s=markerprofileDbIdstr[i].trim();
//			  if(!s.isEmpty()) listmarkerprofileDbId.add(s);
//		  }
//		  
//		  String markerDbIdstr[]=request.getParameter("markerDbId" ).split("\n");
//		  List<String> listmarkerDbId=new ArrayList();
//		  for(int i=0; i< markerDbIdstr.length; i++) {
//			  String s=markerDbIdstr[i].trim();
//			  if(!s.isEmpty()) listmarkerDbId.add(s);
//		  }
//			  
//	      return allelematrix(listmarkerprofileDbId,listmarkerDbId, request.getParameter("format" )) ;
//	  }
//	
	  
	  
	  private String checkMarkerprofileDbId(Collection markerprofileDbId, Collection listlids) {
		  
		  try {
			  
			  AppContext.debug("checkMarkerprofileDbId markerprofileDbId=" + markerprofileDbId.size() + " listlids=" + listlids.size());
			  String study=null;
			  long min=Long.MAX_VALUE;
			  long max=-1;
			  Iterator<String>  itIds=markerprofileDbId.iterator();
			  while(itIds.hasNext()) {
				  Long lid=Long.valueOf( itIds.next().trim());
				  if(lid<min) min=lid;
				  if(lid>max) max=lid;
				  listlids.add(lid);
			  }
			  if(min>=1 && max<=3024) return "3kfiltered";
			  else if(min>=10001 && max<=13024) return "3kcore";
			  else if(min>=20001 && max<=23024) return "3kbase";
			  else if(min>=30001 && max<=33024) return "3kall";
			  else if(min>=3025 && max<=4592) return "hdra";
				  
		  } catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  
		  return null;
	  }
	  

		  
	  private Response allelematrix(List<String> markerprofileDbId, List<String> markerDbId, 
			  boolean expandHomozygotes, String unknownString, String sepPhased, String sepUnphased, String format, boolean matrix, String call) {

		  Collection listIds=new ArrayList();
		  String studyId = checkMarkerprofileDbId(markerprofileDbId, listIds);
		  if(studyId==null)  Response.status(501).entity("markerprofileDbIds come from different studies").build();
		  
		  String sVarids=null;
		  long offset=0;
		  Set<BigDecimal> setVarids=null;
		  if(studyId.startsWith("3k")) {
			  setVarids=new HashSet();
			  if(studyId.equals("3kfiltered")) ;
			  else if(studyId.equals("3kcore"))  offset=10000;
			  else if(studyId.equals("3kbase"))  offset=20000;
			  else if(studyId.equals("3kall"))  offset=30000;
			  
			  if(listIds.size()==3024) sVarids="all";
			  
			  Iterator<Long> itProfileid=listIds.iterator();
			  while(itProfileid.hasNext()) {
				  setVarids.add(BigDecimal.valueOf( itProfileid.next()-offset ));
			  }
			  
		  }
		  else if(studyId.equals("hdra")) {
			  setVarids=new HashSet();
			  if(listIds.size()==1568) sVarids="all";
			  Iterator<Long> itProfileid=listIds.iterator();
			  while(itProfileid.hasNext()) {
				  setVarids.add(BigDecimal.valueOf( itProfileid.next()-offset ));
			  }

		  }
		  /*
		  if(sVarids==null) {
			  setVarids=new  LinkedHashSet();
			  Iterator<Long> itvarid=listIds.iterator();
			  while(itvarid.hasNext()) {
				  setVarids.add( BigDecimal.valueOf( itvarid.next()-offset  ));
			  }
		  }
		  */

		  
		  /*
		  String study="3k";
		  Set setmarkerprofileDbId=new LinkedHashSet(markerprofileDbId);
		  String sVarids=null;
		  if(setmarkerprofileDbId.size()==3024){
			  sVarids="all";
			  study="3k";
		  }
		  else  if(setmarkerprofileDbId.size()==1568){
			  sVarids="all";
			  study="hdra";
		  }
		  else {
			  StringBuffer sbVarids=new StringBuffer();
			  Iterator<String> itVarid=markerprofileDbId.iterator();
			  while(itVarid.hasNext()) {
				  sbVarids.append( itVarid.next());
				  if(itVarid.hasNext()) sbVarids.append(",");
			  }
			  sVarids=sbVarids.toString();
		  }
		  */
		  
		  
		  if(markerDbId.size()==1) {
			  String[] markerdbs=markerDbId.get(0).split(",");
			  List listNewIds=new ArrayList();
			  for(int i=0; i<markerdbs.length; i++) {
				  listNewIds.add(markerdbs[i] );
			  }
			  markerDbId=listNewIds;
		  }
		  
		  /*
		  if(markerprofileDbId.size()==1) {
			  String[] markerdbs=markerprofileDbId.get(0).split(",");
			  List listNewIds=new ArrayList();
			  for(int i=0; i<markerdbs.length; i++) {
				  listNewIds.add(markerdbs[i] );
			  }
			  markerprofileDbId=listNewIds;
		  }
		  */
		  
		  AppContext.debug("allelematrix for " + markerDbId.size() + " markers, " + markerprofileDbId.size() + " profiles, varids=" + (setVarids==null?"0":setVarids.size()));
		  
		  Map<String,Set> mapCont2Pos=null;
		  Map<String,long[]> mapCont2Range=null;
		  
		  if(markerDbId==null || markerDbId.isEmpty()) {
			  // all 
			  mapCont2Range=new TreeMap();
			  listitemsDAO=(ListItemsDAO)AppContext.checkBean(this.listitemsDAO, "ListItems");
			  Iterator<Scaffold> scaffs= listitemsDAO.getOrganismScaffolds(AppContext.getDefaultOrganism()).iterator();
			  while(scaffs.hasNext()) {
				  Scaffold scaf=scaffs.next();
				  mapCont2Range.put( scaf.getName(),new long[]{1L, scaf.getLength()});
			  }
			  
		  }
		  else {
			  Iterator<String> itMarker=markerDbId.iterator();
			  mapCont2Pos=new TreeMap();
			  while(itMarker.hasNext()) {
				  String markerids[]=itMarker.next().split("_");
				
				  Set setpos=mapCont2Pos.get( markerids[0]);
				  if(setpos==null) {
					  setpos=new TreeSet();
					  mapCont2Pos.put(markerids[0], setpos);
				  }
				  setpos.add(BigDecimal.valueOf( Long.valueOf(markerids[1])));
			  }
		  }
			  
//			  String sSnppos=null;
//			  String sChr=null;
//			  
//			  if(mapCont2Pos.size()==1) {
//				  Iterator<String> itCont= mapCont2Pos.keySet().iterator();
//				  StringBuffer sbSnppos=new StringBuffer();
//				  while(itCont.hasNext()) {
//					  String cont=itCont.next();
//					  Iterator<BigDecimal>  itPos=mapCont2Pos.get(cont).iterator();
//					  while(itPos.hasNext()) {
//						  sbSnppos.append(itPos.next());
//						  if(itPos.hasNext()) sbSnppos.append(",");
//					  }
//					  sChr=cont;
//				  }
//				  sSnppos=sbSnppos.toString();
//			  }
//			  else {
//				  return new Object[]{Response.status(501).entity("multiple chromosomes").build(),null};
				  /*
				  Set setsnps=new LinkedHashSet();
				  Iterator<String> itCont= mapCont2Pos.keySet().iterator();
				  while(itCont.hasNext()) {
					  String cont=itCont.next();
					  setsnps.addAll( genotype.checkSNPInChromosome(cont, mapCont2Pos.get(cont), SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2));
				  }
				  */
//			  } 
//		  }
		  
		  
		
		//if(sChr==null || sSnppos==null) return new Object[]{Response.status(400).entity( "sChr==null || sSnppos==null").build(),null};
		//if(sVarids==null)  return new Object[]{Response.status(400).entity( "sVarids==null").build(),null};
		
		  /*
		Collection<BigDecimal> colVarIds = null;  
		if(!sVarids.equals("all")) {
			String vaidss[]=sVarids.split(",");
			colVarIds = new HashSet();
			for(int ivar = 0; ivar<vaidss.length; ivar++) {
				colVarIds.add(  BigDecimal.valueOf( Long.valueOf(vaidss[ivar] )) );
			}
	  	}
		
		Collection<BigDecimal> colPos = null;  
		if(sSnppos!=null) {
			String pos[]=sSnppos.split(",");
			colPos = new HashSet();
			for(int ipos = 0; ipos<pos.length; ipos++) {
				colPos.add(  BigDecimal.valueOf( Long.valueOf(pos[ipos] )) );
			}
	  	}
	  */
		AppContext.debug( "params=varids" + setVarids + " mapCont2Range=" + mapCont2Range + " mapCont2Pos="  + mapCont2Pos);
		
		try {
			
			Map mapAllconts=new HashMap();
			if(matrix) {
				mapAllconts.put("allVars",new TreeSet());
				mapAllconts.put("markerAlleles",new TreeMap());
				mapAllconts.put("varmarkerAlleles", new ArrayList());
			} else {
				mapAllconts.put("data",new ArrayList());
			}
			
			if(mapCont2Range!=null) {
				
				Iterator<String> itCont=mapCont2Range.keySet().iterator();
				while(itCont.hasNext()) {
					String cont=itCont.next();
					long range[]=mapCont2Range.get(cont);
					Map table = getVariantTable(setVarids, cont, range[0], range[1], true, false, false, false, null, null, null, false, format,
							  expandHomozygotes,  unknownString,  sepPhased,  sepUnphased, studyId, matrix);
					if(table==null) return  Response.status(500).entity( "table==null").build();
					
					if(!matrix) {
						((List)mapAllconts.get("data")).addAll( (List)table.get("data"));
					} else {
						((Collection)mapAllconts.get("allVars")).addAll( (List)table.get("allVars"));
						((Map)mapAllconts.get("markerAlleles")).putAll( (Map)table.get("markerAlleles"));
						((Collection)mapAllconts.get("varmarkerAlleles")).addAll( (List)table.get("varmarkerAlleles"));
					}
				}
				
			} else if (mapCont2Pos!=null){
				
				
				Iterator<String> itCont=mapCont2Pos.keySet().iterator();
				while(itCont.hasNext()) {
					String cont=itCont.next();
					Set colPos=mapCont2Pos.get(cont);
					Map table = getVariantTable(setVarids, cont, null, null, true, false, false, false, colPos, null, null, false, format,
							  expandHomozygotes,  unknownString,  sepPhased,  sepUnphased, studyId, matrix);
					if(table==null) return Response.status(500).entity( "table==null").build();
					
					if(!matrix) {
						((List)mapAllconts.get("data")).addAll( (List)table.get("data"));
					} else {
						((Collection)mapAllconts.get("allVars")).addAll( (Collection)table.get("allVars"));
						((Map)mapAllconts.get("markerAlleles")).putAll( (Map)table.get("markerAlleles"));
						((Collection)mapAllconts.get("varmarkerAlleles")).addAll( (Collection)table.get("varmarkerAlleles"));
					}
				}
				
//				
//				Iterator<String> itCont=mapCont2Pos.keySet().iterator();
//				while(itCont.hasNext()) {
//					String cont=itCont.next();
//					Set colPos=mapCont2Pos.get(cont);
//					Map table = getVariantTable(setVarids, cont, null, null, true, false, false, false, colPos, null, null, false, format,
//							  expandHomozygotes,  unknownString,  sepPhased,  sepUnphased, studyId, matrix);
//					if(table==null) return new Object[]{ Response.status(500).entity( "table==null").build(),null};
//					listData.addAll( (List)table.get("data"));
//				}
//				Map table=new HashMap();
//				table.put("data", listData);
//				return new Object[]{Response.status(200).entity( new ObjectMapper().writeValueAsString( table )).build(), table};
			}
			
			if(!matrix) {
				return Response.status(200).entity( new ObjectMapper().writeValueAsString( mapAllconts )).build();
			} else {
				return createAlleleMatrixResponse( (Set)mapAllconts.get("allVars"), (Map)mapAllconts.get("markerAlleles") ,  (List)mapAllconts.get("varmarkerAlleles"), format,call,studyId);
				//return new Object[]{Response.status(200).entity( new ObjectMapper().writeValueAsString( table )).build(), table};
			}

			
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
		
		//return new Object[]{Response.status(500).entity("mapCont2Range==null and mapCont2Pos==null").build(),null};
		
	  }
	  
	  private Map getVariantTable(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP, boolean bIndel,
				 boolean bCoreonly, boolean bMismatchonly, Collection poslist, String sSubpopulation, String sLocus, boolean bAlignIndels, String format,
				 boolean expandHomozygotes, String unknownString, String sepPhased, String sepUnphased, String studyId, boolean isMatrix) throws Exception {
		  
		  genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		  
		  AppContext.resetTimer("allelequery..");
		  
		  		boolean showAllRefsAllele=false;
				GenotypeQueryParams params = new GenotypeQueryParams(colVarIds,sChr, lStart, lEnd, bSNP, bIndel, bCoreonly ,
					bMismatchonly, poslist,  sSubpopulation, sLocus, bAlignIndels, showAllRefsAllele );			
				
				params = setStudy(params, studyId);
				
				if(!isMatrix) {
					// get position only
					List<SnpsAllvarsPos>  listpos = genotype.getSNPPoslist(params);
					Iterator<SnpsAllvarsPos>  itlistpos=listpos.iterator();
					List listMarkers=new ArrayList();
					while(itlistpos.hasNext()) {
						SnpsAllvarsPos pos=itlistpos.next();
						Map mapM=new HashMap();
						mapM.put( pos.getContig() +"_" + pos.getPosition(),  pos.getRefnuc().trim() + sepUnphased + pos.getAltnuc().trim());
						listMarkers.add(mapM);
					}
					Map mapMP=new LinkedHashMap();
					mapMP.put("data",listMarkers);
					return mapMP;
				}
				
				
				VariantStringData queryRawResult = genotype.queryGenotype( params);
				
				/*
Call:
/allelematrix?markerprofileDbId=100&...&format=tsv

Json response:
{
    "metadata": {   
        "pagination": {
            "pageSize": 0,
            "currentPage": 1,
            "totalCount": 0,
            "totalPages": 1
        },
        "status":[],
	"datafiles": [ {"url": "http://my-server/somethingelse.tsv"}, { "url": "..." } ]
    },
    "result" : {
	"markerprofileDbIds": [],
        "data": []
    }
}

File:
markerprofileDbIds	markerprofileId1	markerprofileId2	...
markerId1	AB	AA	...
markerId2	AA	AB	...
...	...	...


Examples:
- No format -> data in the json result
- Format specified -> If server supports it, return the link to the exported data file
- More than one format requested at a time -> Throw "not supported" error
			    */
				
			
				
				
				List listVars=new ArrayList();
				Iterator<SnpsStringAllvars> itSnpstr= queryRawResult.getListVariantsString().iterator();
				while(itSnpstr.hasNext()) {
					SnpsStringAllvars snpstr=itSnpstr.next();
					listVars.add(snpstr.getVar());
				}
				
				List listVarMarkerAlleles=new ArrayList();
				Map<String,Set<String>> mapMarkerAlleles=new LinkedHashMap();
				Iterator<SnpsAllvarsPos>  itSnppos = queryRawResult.getListPos().iterator();
				int snpposidx=0;
				while(itSnppos.hasNext()) {
					SnpsAllvarsPos snppos=itSnppos.next();
					String marker=snppos.getContig()+"_"+snppos.getPosition();
					Set markerAlleles = mapMarkerAlleles.get(marker);
					if(markerAlleles==null) {
						markerAlleles=new TreeSet();
						mapMarkerAlleles.put(marker, markerAlleles);
					}
					
					itSnpstr= queryRawResult.getListVariantsString().iterator();
					while(itSnpstr.hasNext()) {
						SnpsStringAllvars snpstr=itSnpstr.next();
						Character allele2 = null;
						if(snpstr.getMapPos2Allele2()!=null) allele2=snpstr.getMapPos2Allele2().get( snppos );
						Character allele1 = snpstr.getVarnuc().charAt(snpposidx);
						
						String allele=null;
						if(allele1=='?' || allele1==null) {
							allele=unknownString;
						} else {
							allele= String.valueOf(allele1);
							//expandHomozygotes, String unknownString, String sepPhased, String sepUnphased
							if(expandHomozygotes) {
								if(allele2!=null) {
									allele+=sepUnphased+allele2;
								} else {
									allele+=sepUnphased+allele1;
								}
							} else {
								if(allele2!=null && !allele2.equals(allele1)) {
									allele+=sepUnphased+allele2;
								}
							}
						}
						
						//markerAlleles.add(allele.replace("?",unknownString));
						markerAlleles.add(allele);
						listVarMarkerAlleles.add( new String[]{ snpstr.getVar().toString(), marker, allele });
					}
					snpposidx++;
				}
				
				Map mapResponse=new HashMap();
				mapResponse.put("allVars", listVars);
				mapResponse.put("markerAlleles", mapMarkerAlleles);
				mapResponse.put("varmarkerAlleles", listVarMarkerAlleles );
				
				return mapResponse;
				//return createAlleleMatrixResponse(listVars, format, mapData);
				 
//				List listDataJson=new ArrayList();
//				if(format==null || format.isEmpty() || format.equals("json")) {
//					
//					Iterator<String> itMarker = mapData.keySet().iterator();
//					while(itMarker.hasNext()) {
//						String marker=itMarker.next();
//						Map mapDataJson=new HashMap();
//						mapDataJson.put(marker, mapData.get(marker));
//						listDataJson.add( mapDataJson );
//					}
//					
//					Map mapResult=new LinkedHashMap();
//					mapResult.put("makerprofileDbIds", listVars);
//					mapResult.put("data", listDataJson);
//
//					Map mapMeta=new LinkedHashMap();
//					mapMeta.put("pagination", new ArrayList());
//					mapMeta.put("status",  new ArrayList());
//					
//					Map response=new LinkedHashMap();
//					response.put("metadata", mapMeta);
//					response.put("result", mapResult);
//					
//					AppContext.resetTimer("allelematrix BrAPI format..");
//					
//					return response;
//					
//				} else {
//					String delimiter="\t";
//					if(format.equals("tsv")) {}
//					else if(format.equals("csv")) {
//						delimiter=",";
//					} 
//					
//					/*
//					markerprofileDbIds	markerprofileId1	markerprofileId2	...
//					markerId1	AB	AA	...
//					markerId2	AA	AB	...
//					...	...	...
//					*/
//					String tempfile= "allelematrix_" + AppContext.createTempFilename()+"."+format;
//					try {
//						BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
//						bw.append("markerprofileDbIds").append(delimiter);
//						Iterator<BigDecimal> itVars =  listVars.iterator();
//						while(itVars.hasNext()) {
//							bw.append( itVars.next().toString());
//							if(itVars.hasNext()) bw.append(delimiter);
//						}
//						bw.append("\n");
//						
//						Iterator<String> itMarker = mapData.keySet().iterator();
//						while(itMarker.hasNext()) {
//							String marker=itMarker.next();
//							bw.append(marker).append(delimiter);
//							List alleles = mapData.get(marker);
//							Iterator<String> itAl=alleles.iterator();
//							while(itAl.hasNext()) {
//								bw.append(itAl.next());
//								if(itAl.hasNext()) bw.append(delimiter);
//							}
//							if(itMarker.hasNext()) bw.append("\n");
//						}
//						bw.flush();
//						bw.close();
//						
//						AppContext.debug("filewritten to : " + AppContext.getTempDir() + tempfile);
//						
//					} catch(Exception ex) {
//						ex.printStackTrace();
//					}
//					
//					/*
//					 * "metadata": {   
//					        "pagination": {
//					            "pageSize": 0,
//					            "currentPage": 1,
//					            "totalCount": 0,
//					            "totalPages": 1
//					        },
//					        "status":[],
//						"datafiles": [ {"url": "http://my-server/somethingelse.tsv"}, { "url": "..." } ]
//					    },
//					    "result" : {
//						"markerprofileDbIds": [],
//					        "data": []
//					    }
//					 */
//					Map mapResult=new LinkedHashMap();
//					mapResult.put("makerprofileDbIds", new ArrayList());
//					mapResult.put("data", new ArrayList());
//
//					Map mapMeta=new LinkedHashMap();
//					Map mapPagi=new LinkedHashMap();
//					mapPagi.put("pageSize", 0);
//					mapPagi.put("currentPage", 0);
//							mapPagi.put("totalCount", 0);
//									mapPagi.put("totalPages", 1);
//					
//					mapMeta.put("pagination", mapPagi);
//					mapMeta.put("status",  new ArrayList());
//					List listUrl=new ArrayList();
//					Map mapUrl=new HashMap();
//					mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder() + tempfile);
//					listUrl.add(mapUrl);
//					mapMeta.put("datafiles", listUrl);
//					
//					Map response=new LinkedHashMap();
//					response.put("metadata", mapMeta);
//					response.put("result", mapResult);
//					
//					AppContext.resetTimer("allelematrix BrAPI format..");
//					
//					return response;
//				}
				/*
				  "result" : { 
			        "makerprofileDbIds": ["markerprofileId1","markerprofileId2","markerprofileId3"],
			        "data": [ 
			            { "markerId1":["AB","AA","AA"] },
			            { "markerId2":["AA","AB","AA"] },
			            { "markerId3":["AB","AB","BB"] }
			        ]
			    }mapMeta
				*/
				
	  }
	
	  private  Map createMetaData(long size, long current, long count, long pages, Object status, Object datafiles) {
		  Map map=new LinkedHashMap();
		  Map mapPagi=new LinkedHashMap();
		  mapPagi.put("pageSize", size);
		  mapPagi.put("currentPage", current);
		  mapPagi.put("totalCount", count);
		  mapPagi.put("totalPages", pages);
		  map.put("pagination", mapPagi);
		  map.put("status",status);
		  map.put("datafiles",datafiles);
		  return map;
		  
	  }
	
	private Response  createAlleleMatrixResponse(Set listVars, Map markerAlleles, List varmarkerAlleles, String format, String call, String study) {
		 //createAlleleMatrixResponse(mapAllconts.get("listVars"), mapAllconts.get("markerAlleles") ,  mapAllconts.get("varmarkerAlleles" ), format);
		 
		Map<String,List<String>> mapData=(Map<String,List<String>>)markerAlleles;
		Map response=new LinkedHashMap();

		if(format==null || format.isEmpty() || format.equals("json")) {
			List listDataJson=new ArrayList();
			if(call.equals("allelematrix") || call.equals("allelematrix-search")) {
			
				Iterator<String> itMarker = mapData.keySet().iterator();
				while(itMarker.hasNext()) {
					String marker=itMarker.next();
					Map mapDataJson=new HashMap();
					mapDataJson.put(marker, mapData.get(marker));
					listDataJson.add( mapDataJson );
				}
				
				Map mapResult=new LinkedHashMap();
				//mapResult.put("makerprofileDbIds", listVars);
				//mapResult.put("germplasmDbIds", listVars);
				//mapResult.put("markerAlleles", listDataJson);
				mapResult.put("data", varmarkerAlleles);
		
				response.put("metadata", this.createMetaData(varmarkerAlleles.size(), 0, varmarkerAlleles.size(), 1, new HashMap(), new ArrayList()));
				response.put("result", mapResult);
				
				AppContext.resetTimer("allelematrix BrAPI format..");
			}
			else if(call.equals("markerprofile")) {
				Iterator<String> itMarker = mapData.keySet().iterator();
				while(itMarker.hasNext()) {
					String marker=itMarker.next();
					Map mapDataJson=new HashMap();
					mapDataJson.put(marker, mapData.get(marker).get(0));
					listDataJson.add( mapDataJson );
				}
				
				listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
				
				Long lVarid= Long.valueOf(listVars.iterator().next().toString());
				Map mapResult=new LinkedHashMap();
				String markerprofileDbId=lVarid.toString();
				if(study.equals("3kcore")) markerprofileDbId=Long.toString( lVarid + 10000);
				else if(study.equals("3kbase")) markerprofileDbId=Long.toString( lVarid + 20000);
				else if(study.equals("3kall")) markerprofileDbId=Long.toString( lVarid + 30000);
				
				mapResult.put("markerprofileDbIds",  markerprofileDbId);
				mapResult.put("germplasmDbIds",lVarid.toString());
				mapResult.put("uniqueDisplayName",  listitemsDAO.getMapId2Variety(VarietyFacade.DATASET_SNP_ALL).get(BigDecimal.valueOf(lVarid)).getName()+";"+study);
				mapResult.put("analysisMethod", "SNP calling");
				mapResult.put("data", listDataJson);
				
				response.put("metadata", createMetaData(listDataJson.size(), 0, listDataJson.size(), 1, new HashMap(), new ArrayList()));
				response.put("result", mapResult);
			}
				
			
			
		} else {
			String delimiter="\t";
			if(format.equals("tsv")) {}
			else if(format.equals("csv")) {
				delimiter=",";
			} 
			
			/*
			markerprofileDbIds	markerprofileId1	markerprofileId2	...
			markerId1	AB	AA	...
			markerId2	AA	AB	...
			...	...	...
			*/

				String tempfile= call + "_" + AppContext.createTempFilename()+"."+format;
			
				try {
					if(call.equals("allelematrix") || call.equals("allelematrix-search")) {
						BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
						bw.append("markerprofileDbIds").append(delimiter);
						Iterator<BigDecimal> itVars =  listVars.iterator();
						while(itVars.hasNext()) {
							bw.append( itVars.next().toString());
							if(itVars.hasNext()) bw.append(delimiter);
						}
						bw.append("\n");
						
						Iterator<String> itMarker = mapData.keySet().iterator();
						while(itMarker.hasNext()) {
							
							String marker=itMarker.next();
							bw.append(marker).append(delimiter);
							List alleles = mapData.get(marker);
							Iterator<String> itAl=alleles.iterator();
							while(itAl.hasNext()) {
								bw.append(itAl.next());
								if(itAl.hasNext()) bw.append(delimiter);
							}
	
							/*
							List alleles = mapData.get(marker);
							Iterator<String> itAl=alleles.iterator();
							while(itAl.hasNext()) {
								bw.append(itAl.next());
								if(itAl.hasNext()) bw.append(delimiter);
							}
							*/
							if(itMarker.hasNext()) bw.append("\n");
						}
						bw.flush();
						bw.close();
					} else if(call.equals("markerprofile")) {
						
						
					}

				
				AppContext.debug("filewritten to : " + AppContext.getTempDir() + tempfile);
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			/*
			 * "metadata": {   
			        "pagination": {
			            "pageSize": 0,
			            "currentPage": 1,
			            "totalCount": 0,
			            "totalPages": 1
			        },
			        "status":[],
				"datafiles": [ {"url": "http://my-server/somethingelse.tsv"}, { "url": "..." } ]
			    },
			    "result" : {
				"markerprofileDbIds": [],
			        "data": []
			    }
			 */
			Map mapResult=new LinkedHashMap();
			mapResult.put("makerprofileDbIds", new ArrayList());
			mapResult.put("data", new ArrayList());
	
			Map mapMeta=new LinkedHashMap();
			Map mapPagi=new LinkedHashMap();
			mapPagi.put("pageSize", 0);
			mapPagi.put("currentPage", 0);
					mapPagi.put("totalCount", 0);
							mapPagi.put("totalPages", 1);
			
			mapMeta.put("pagination", mapPagi);
			mapMeta.put("status",  new ArrayList());
			List listUrl=new ArrayList();
			Map mapUrl=new HashMap();
			mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder() + tempfile);
			listUrl.add(mapUrl);
			mapMeta.put("datafiles", listUrl);
			
			
			response.put("metadata", mapMeta);
			response.put("result", mapResult);
			
			AppContext.resetTimer("allelematrix BrAPI format..");
			
			
		}
		
		  String errormsg="";
		  try {
			  	//return Response.status(200).entity( new ObjectMapper().writeValueAsString( createResponse(response) )).build();
			  return Response.status(200).entity( new ObjectMapper().writeValueAsString( response )).build();
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errormsg=e.getMessage();
			}
		  
		  return Response.status(500).entity(errormsg).build();
	}
		
}

