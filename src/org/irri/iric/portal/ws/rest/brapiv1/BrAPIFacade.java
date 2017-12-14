package org.irri.iric.portal.ws.rest.brapiv1;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Service("BrapiService")
public class BrAPIFacade {
	

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

	  public BrAPIFacade() {
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
					
	  public Response postAlleleMatrix(List<String> listmarkerprofileDbId, 
			  List<String> listmarkerDbId,
			  boolean expandHomozygotes,
			  String unknownString,
			  String sepPhased,
			  String sepUnphased, 
			  String format,
			  String pageSize, 
			  String page) throws JSONException {

		  
		  
		  AppContext.debug("postAlleleMatrix: markerDbId=" +listmarkerDbId.size() + " markerprofileDbId=" + listmarkerprofileDbId.size());
		  if(listmarkerDbId.size()<20) AppContext.debug("listmarkerDbId=" + listmarkerDbId);
		  if(listmarkerprofileDbId.size()<20) AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);
		  
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
		
		public Response getAlleleMatrix(List<String> markerprofileDbId, 
			  List<String> markerDbId,
			  boolean expandHomozygotes,
			  String unknownString,
			  String sepPhased,
			  String sepUnphased, 
			  String format,
			  String pageSize, 
			  String page) throws JSONException {

		  
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

	@GET
	@Path("allelematrix")
	@Produces("application/json")
	@Consumes("application/json")
	public Map allelematrix(
			@QueryParam("markerprofileDbId") String markerprofileDbId) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@POST
	@Path("allelematrix-search")
	@Produces("application/json")
	@Consumes("application/json")
	public Response allelematrix_search(
			@FormParam("markerprofileDbId") List markerprofileDbId,
			@FormParam("markerDbId") List markerDbId) {

	//	return this.postAlleleMatrix( markerprofileDbId,  markerDbId, false, "N", "/","|","json", "1", "0");
		//return this.postAlleleMatrix( (List<String>)markerprofileDbId,  (List<String>)markerDbId, false, "N", "/","|","json", "1", "0");
		throw new UnsupportedOperationException("Not yet implemented.");
	}
		
}

