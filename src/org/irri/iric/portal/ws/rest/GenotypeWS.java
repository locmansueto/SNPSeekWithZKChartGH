package org.irri.iric.portal.ws.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.genotype.service.VariantTableArraysImpl;
import org.irri.iric.portal.genotype.zkui.FakerMatrixModel;
import org.irri.iric.portal.genotype.zkui.Object2StringMatrixModel;
import org.irri.iric.portal.genotype.zkui.Object2StringMatrixRenderer;
import org.irri.iric.portal.genotype.zkui.PairwiseListItemRenderer;
//import org.irri.iric.portal.genotype.zkui.SNPAllvarsRowRenderer;
//import org.irri.iric.portal.genotype.zkui.SNPListItemRenderer;
//import org.irri.iric.portal.genotype.zkui.SNPMatrixRenderer;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.ws.entity.VariantTableWS;
//import org.irri.iric.portal.ws.entity.VariantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkmax.zul.MatrixModel;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;

import com.sun.jersey.spi.resource.Singleton;

@Controller("GenotypeWebService")
@Path("/genotype")
public class GenotypeWS {

	@Autowired
	@Qualifier("GenotypeFacade")
	private GenotypeFacade genotype;
	
	@Autowired
	private VarietyFacade variety;
	
	
	private Map<String,String> mapVarReplace = new HashMap(); 
	
	public GenotypeWS() {
		super();
		// TODO Auto-generated constructor stub
		genotype = (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		AppContext.debug("GenotypeWS started");

		// rename some variable names
		mapVarReplace.put("iricStockId", "varietyId");
		mapVarReplace.put("irisUniqueId", "irisId");
		mapVarReplace.put("oriCountry", "country");
		
	}

	  @GET
	  @Path("/variety")
	  @Produces("application/json")
	  public Response getVarieties() throws JSONException {
			
	  try {
		Set vars =  variety.getGermplasm() ;
		
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
		
	  }
	  
 
	  @Path("/variety/{id}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesById(@PathParam("id") long lId) throws JSONException {
 
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString( variety.getMapId2Variety().get(BigDecimal.valueOf(lId)) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }

	  }

	  @Path("/variety/subpopulation/{subpop}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesBySubpopulation(@PathParam("subpop") String sSubpop) throws JSONException {
 
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString( variety.getGermplasmBySubpopulation(sSubpop) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  
	  }

	  @Path("/variety/subpopulation")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesSubpopulation() throws JSONException {
 
		  try {
			return Response.status(200).entity(  new ObjectMapper().writeValueAsString( variety.getSubpopulations() )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }

	  
	  @Path("/variety/country/{country}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesByCountry(@PathParam("country") String sCountry) throws JSONException {
 
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString(variety.getGermplasmByCountry(sCountry) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }
	  
	  @Path("/variety/country")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesCountry() throws JSONException {
 
		  try {
			return Response.status(200).entity(  new ObjectMapper().writeValueAsString( variety.getCountries() ) ).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }
	  
	  @Path("/variety/name")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesNames() throws JSONException {
 
		  try {
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( variety.getVarietyNames() )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }

	  @Path("/variety/namelike/{name}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesNameLike(@PathParam("name") String sName) throws JSONException {
 
		  try {
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( variety.getGermplasmByNameLike( sName + "%") )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
		
	  }


	  @Path("/gettable")
	  @GET
	  @Produces("application/json")
	  public Response getVariantByVarietyId(@DefaultValue("all")  @QueryParam("varid") String sVarids, 
			  @QueryParam("chr") String sChr,  @QueryParam("start") Long lStart, @QueryParam("end") Long lEnd,
			  @DefaultValue("true") @QueryParam("snp") boolean bSNP,  @DefaultValue("false") @QueryParam("indel") boolean bIndel,
			  @DefaultValue("false") @QueryParam("coreonly") boolean bCoreonly, @DefaultValue("false") @QueryParam("mismatchonly") boolean bMismatchonly,
			  @QueryParam("poslist") String sSnppos ,  @QueryParam("subpopulation") String sSubpopulation,  @QueryParam("locus") String sLocus,  @DefaultValue("false") @QueryParam("alignindels") boolean bAlignIndels
			   ) throws JSONException {
 		
		//if(sChr==null) throw new JSONException("parameters chr is required");
		if(   sChr==null && sLocus==null && sSnppos==null) throw new JSONException("parameters (chr,start,end) OR locus OR (chr AND poslist) is required");
		  	
		if((sSnppos==null || sSnppos.isEmpty()) && (lStart==null || lEnd==null) && sLocus==null) throw new JSONException("parameters (start AND end) OR poslist OR locus is required");  
		if(sSnppos!=null && !sSnppos.isEmpty() && lStart!=null && lEnd!=null) throw new JSONException("only one of either (start AND end) OR poslist is required");  
		
		//if(!sVarids.equals("all") && sSubpopulation==null)  throw new JSONException("parameter varid OR subpopulation is required");  
		if(!sVarids.equals("all") && sSubpopulation!=null)  throw new JSONException("only one of either varid OR subpopulation is required");  
		
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
	  
		AppContext.debug( "params=" + colVarIds + " " + sChr + " " + lStart + " " +lEnd + " " + bSNP + " " +bIndel + " " + bCoreonly + " " + 
				bMismatchonly + " " + colPos + " " + sSubpopulation + " " + sLocus );
		
		try {
			VariantTable table = getVariantTable(colVarIds, sChr, lStart, lEnd, bSNP, bIndel, bCoreonly, bMismatchonly, colPos, sSubpopulation, sLocus, bAlignIndels);
			if(table==null) throw new JSONException("VariantTable is null");

			
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( table )).build();

			
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
		
	  }

	  @Path("/posttable")
	  @POST
	  @Produces("application/json")
	  public Response postVariantByVarietyId(@FormParam("varid") List<Long> lVarids, 
			  @FormParam("chr") String sChr,  @FormParam("start") Long lStart, @FormParam("end") Long lEnd,
			  @DefaultValue("true") @FormParam("snp") boolean bSNP,  @DefaultValue("false") @FormParam("indel") boolean bIndel,
			  @DefaultValue("false") @FormParam("coreonly") boolean bCoreonly, @DefaultValue("false") @FormParam("mismatchonly") boolean bMismatchonly ,
			  @FormParam("poslist") List<Long> lSnppos,   @FormParam("subpopulation") String sSubpopulation,  @FormParam("locus") String sLocus,  @DefaultValue("false") @FormParam("alignindel") boolean bAlignIndels
			   ) throws JSONException {
 
		  	if(sChr==null && sLocus==null && lSnppos.isEmpty()) throw new JSONException("parameters (chr,start,end) OR locus OR (chr AND poslist) is required");  
		  
			if(lSnppos.isEmpty() && (lStart==null || lEnd==null) && sLocus==null) throw new JSONException("parameters (start AND end) OR poslist is required");  
			if(!lSnppos.isEmpty() && lStart!=null && lEnd!=null) throw new JSONException("only one of either (start AND end) OR poslist is required");  
		  
			//if(lVarids.isEmpty() && sSubpopulation==null)  throw new JSONException("parameter varid OR subpopulation is required");
			if(!lVarids.isEmpty() && sSubpopulation!=null)  throw new JSONException("only one of either varid OR subpopulation is required");  

			
			Collection<BigDecimal> colVarIds = null;  
			if(lVarids!=null && !lVarids.isEmpty()) {
				Iterator<Long> itvarids = lVarids.iterator();
				while(itvarids.hasNext()) {
					colVarIds.add(  BigDecimal.valueOf( itvarids.next() ));
				}
		  	}
			
			Collection<BigDecimal> colPos = null;  
			if(lSnppos!=null && !lSnppos.isEmpty()) {
				Iterator<Long> itpos = lSnppos.iterator();
				while(itpos.hasNext()) {
					colVarIds.add(  BigDecimal.valueOf( itpos.next() ));
				}
		  	}

			AppContext.debug( "params=" + colVarIds + " " + sChr + " " + lStart + " " +lEnd + " " + bSNP + " " +bIndel + " " + bCoreonly + " " + 
					bMismatchonly + " " + colPos + " " + sSubpopulation + " " + sLocus );
			
			try {
			VariantTable table = getVariantTable(colVarIds, sChr, lStart, lEnd, bSNP, bIndel, bCoreonly,bMismatchonly, colPos,sSubpopulation, sLocus, bAlignIndels); 
			
			if(table==null) throw new JSONException("VariantTable is null");
			
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( table )).build();

			} catch(Exception ex) {
				throw new JSONException(ex);
			}
	  }
	  
	
	  
	  private VariantTable getVariantTable(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP, boolean bIndel,
				 boolean bCoreonly, boolean bMismatchonly, Collection poslist, String sSubpopulation, String sLocus, boolean bAlignIndels) throws Exception {
		  
		  		boolean showAllRefsAllele=false;
				GenotypeQueryParams params = new GenotypeQueryParams(colVarIds,sChr, lStart, lEnd, bSNP, bIndel, bCoreonly ,
					bMismatchonly, poslist,  sSubpopulation, sLocus, bAlignIndels, showAllRefsAllele );			
				
				VariantTableArray varianttable =  new VariantAlignmentTableArraysImpl();
				
				VariantStringData queryRawResult = genotype.queryGenotype( params);
				varianttable = new VariantTableWS( (VariantTableArray)genotype.fillGenotypeTable(varianttable , queryRawResult, params) );
				
				return varianttable;
		
	  }

	  
	  /*
	  	@POST
	    @Path("/sendemail")
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response sendEmail(@FormParam("email") String email) {
	        System.out.println(email);
	        return Response.ok("email=" + email).build();
	    }
	  */
	  
	  
	    //The resource look like this
	  /*
	    @Path("/variants")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void setJsonl(String array){
	        JSONArray o = new JSONArray(last_data);
	        System.out.println(o.toString());
	    }
	    */    


}