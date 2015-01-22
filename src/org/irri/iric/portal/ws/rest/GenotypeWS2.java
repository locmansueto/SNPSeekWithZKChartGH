package org.irri.iric.portal.ws.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.irri.iric.portal.ws.entity.VariantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/json")
@Path("/genotype2")
@Singleton
@Controller("GenotypeWSController")
public class GenotypeWS2 {

	@Autowired
	private GenotypeFacade genotype;
	@Autowired
	private VarietyFacade variety;
	
	public GenotypeWS2() {
		super();
		// TODO Auto-generated constructor stub
		genotype = (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		
		
	}

	@GET
	@Path("/variety")
	@Produces("application/json")
	public List<Variety> getVarieties() {
//			@DefaultValue("all") @QueryParam("subpopulation") Object strSubpopulation,
//			@DefaultValue("all") @QueryParam("country") Object strCountry) {
		
		List listVarieties = new ArrayList();
		listVarieties.addAll( variety.getGermplasm() );
		return listVarieties;
		//throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@RequestMapping(value = "/getVariety", method = RequestMethod.GET)
	@ResponseBody
	public List<Variety> getVarieties2() {
//		@DefaultValue("all") @QueryParam("subpopulation") Object strSubpopulation,
//		@DefaultValue("all") @QueryParam("country") Object strCountry) {
	
	List listVarieties = new ArrayList();
	listVarieties.addAll( variety.getGermplasm() );
	return listVarieties;
	//throw new UnsupportedOperationException("Not yet implemented.");
	
	}
	
	
	@POST
	@Path("variant")
	@Produces("application/json")
	@Consumes("application/json")
	public VariantTable getVariantTable() {

		/*
			@QueryParam("varids") List listVarids,
			@DefaultValue("all") @QueryParam("subpopulation") Object strSubpopulation,
			@DefaultValue("all") @QueryParam("country") Object strCountry,
			@QueryParam("chromosome") Object strChromosome,
			@QueryParam("start") Integer intStart,
			@QueryParam("end") Integer intEnd) {
			*/
		
		throw new UnsupportedOperationException("Not yet implemented.");
	}
}