package org.irri.iric.portal.ws.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("VarietyWebService")
@Path("/variety")
public class VarietyWS {
	
	@Autowired
	private VarietyFacade variety;
	private Map mapVarReplace;
	

	public VarietyWS() {
		super();
		// TODO Auto-generated constructor stub
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		mapVarReplace = new HashMap();
		
		AppContext.debug("VarietyWS started");

		// rename some variables
		mapVarReplace.put("iricStockId", "varietyId");
		mapVarReplace.put("irisUniqueId", "irisId");
		mapVarReplace.put("oriCountry", "country");
	}

	
	
	  @GET
	  @Path("/")
	  @Produces("application/json")
	  
	  @ResponseBody
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
	  
 
	  @Path("/{id}")
	  @GET
	  @Produces("application/json")

	  public Response getVarietiesById(@PathParam("id") long lId) throws JSONException {
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString( variety.getMapId2Variety().get(BigDecimal.valueOf(lId)) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }

	  }

	  @Path("/subpopulation/{subpop}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesBySubpopulation(@PathParam("subpop") String sSubpop) throws JSONException {
 
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString( variety.getGermplasmBySubpopulation(sSubpop) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  
	  }

	  @Path("/subpopulation")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesSubpopulation() throws JSONException {
 
		  try {
			return Response.status(200).entity(  new ObjectMapper().writeValueAsString( variety.getSubpopulations() )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }

	  
	  @Path("/country/{country}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesByCountry(@PathParam("country") String sCountry) throws JSONException {
 
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString(variety.getGermplasmByCountry(sCountry) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }
	  
	  @Path("/country")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesCountry() throws JSONException {
 
		  try {
			return Response.status(200).entity(  new ObjectMapper().writeValueAsString( variety.getCountries() ) ).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }
	  
	  @Path("/name")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesNames() throws JSONException {
 
		  try {
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( variety.getVarietyNames() )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }

	  @Path("/namelike/{name}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesNameLike(@PathParam("name") String sName) throws JSONException {
 
		  try {
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( variety.getGermplasmByNameLike( sName + "%") )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
		
	  }


	  
	
	  @GET
	  @Path("/phenotypes")
	  @Produces("application/json")
	  public Response getPhenotypes() throws JSONException {
			
		  try {
			Map pnenotypeId =  variety.getPhenotypeDefinitions();
			
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(pnenotypeId), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
	  }

	  @GET
	  @Path("/phenotypes/{phenid}")
	  @Produces("application/json")
	  public Response getPhenotype4AllVarieties(@PathParam("phenid") String sPhenId) throws JSONException {
			
		  try {
			List vars =  variety.getVarietyByPhenotype( sPhenId);
			
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
	  }

	  
	  
	  
	  @GET
	  @Path("/{varid}/phenotypes")
	  @Produces("application/json")
	  public Response getVarietyPhenotypes(@PathParam("varid") String sVarId) throws JSONException {
			
		  try {
			List pnenotypes =  variety.getPhenotypesByGermplasm(  variety.getMapId2Variety().get( BigDecimal.valueOf(Long.valueOf(sVarId)) )  );
			
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(pnenotypes), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
	  }
	  
	  
	  @GET
	  @Path("/passports")
	  @Produces("application/json")
	  public Response getPassports() throws JSONException {
			
		  try {
			Map passportId =   variety.getPassportDefinitions();
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(passportId), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
	  }
	  
	  
	  @GET
	  @Path("/{varid}/passports")
	  @Produces("application/json")
	  public Response getVarietyPassports(@PathParam("varid") String sVarId) throws JSONException {
			
		  try {
			//List passports =  variety.getPhenotypesByGermplasm(  variety.getMapId2Variety().get( BigDecimal.valueOf(Long.valueOf(sVarId)) )  );
			  List passports=new ArrayList();
			  
			  passports.addAll(  variety.getPassportByVarietyid( BigDecimal.valueOf(Long.valueOf(sVarId)) )  );
			
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(passports), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
	  }  
	  
	  @GET
	  @Path("/passports/{passid}")
	  @Produces("application/json")
	  public Response getPassport4AllVarieties(@PathParam("passid") String sPassId) throws JSONException {
			
		  try {
			List vars =  variety.getVarietyByPassport( sPassId);
			
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
	  }

	
}
