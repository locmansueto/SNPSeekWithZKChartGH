package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.PhenotypesDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

import org.irri.iric.portal.variety.service.GermplasmService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Rest controller that handles CRUD requests for Germplasm entities
 * 
 */

@Controller("GermplasmRestController")
public class GermplasmRestController {

	/**
	 * DAO injected by Spring that manages Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingDAO genotypingDAO;

	/**
	 * DAO injected by Spring that manages Germplasm entities
	 * 
	 */
	@Autowired
	private GermplasmDAO germplasmDAO;

	/**
	 * DAO injected by Spring that manages Phenotypes entities
	 * 
	 */
	@Autowired
	private PhenotypesDAO phenotypesDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Germplasm entities
	 * 
	 */
	@Autowired
	private GermplasmService germplasmService;

	/**
	 * Select an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}", method = RequestMethod.GET)
	@ResponseBody
	public Germplasm loadGermplasm(@PathVariable Integer germplasm_nsftvId) {
		return germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId);
	}

	/**
	 * Show all Phenotypes entities by Germplasm
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/phenotypeses", method = RequestMethod.GET)
	@ResponseBody
	public List<Phenotypes> getGermplasmPhenotypeses(@PathVariable Integer germplasm_nsftvId) {
		return new java.util.ArrayList<Phenotypes>(germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId).getPhenotypeses());
	}

	/**
	 * View an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}", method = RequestMethod.GET)
	@ResponseBody
	public Genotyping loadGermplasmGenotypings(@PathVariable Integer germplasm_nsftvId, @PathVariable String related_genotypings_snpId, @PathVariable Integer related_genotypings_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(related_genotypings_snpId, related_genotypings_nsftvId, -1, -1);

		return genotyping;
	}

	/**
	 * Create a new Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/genotypings", method = RequestMethod.POST)
	@ResponseBody
	public Genotyping newGermplasmGenotypings(@PathVariable Integer germplasm_nsftvId, @RequestBody Genotyping genotyping) {
		germplasmService.saveGermplasmGenotypings(germplasm_nsftvId, genotyping);
		return genotypingDAO.findGenotypingByPrimaryKey(genotyping.getSnpId(), genotyping.getNsftvId());
	}

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm", method = RequestMethod.PUT)
	@ResponseBody
	public Germplasm saveGermplasm(@RequestBody Germplasm germplasm) {
		germplasmService.saveGermplasm(germplasm);
		return germplasmDAO.findGermplasmByPrimaryKey(germplasm.getNsftvId());
	}

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/phenotypeses/{phenotypes_nsftvId}/{phenotypes_trait}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGermplasmPhenotypeses(@PathVariable Integer germplasm_nsftvId, @PathVariable Integer related_phenotypeses_nsftvId, @PathVariable String related_phenotypeses_trait) {
		germplasmService.deleteGermplasmPhenotypeses(germplasm_nsftvId, related_phenotypeses_nsftvId, related_phenotypeses_trait);
	}

	/**
	 * Show all Genotyping entities by Germplasm
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/genotypings", method = RequestMethod.GET)
	@ResponseBody
	public List<Genotyping> getGermplasmGenotypings(@PathVariable Integer germplasm_nsftvId) {
		return new java.util.ArrayList<Genotyping>(germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId).getGenotypings());
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/genotypings", method = RequestMethod.PUT)
	@ResponseBody
	public Genotyping saveGermplasmGenotypings(@PathVariable Integer germplasm_nsftvId, @RequestBody Genotyping genotypings) {
		germplasmService.saveGermplasmGenotypings(germplasm_nsftvId, genotypings);
		return genotypingDAO.findGenotypingByPrimaryKey(genotypings.getSnpId(), genotypings.getNsftvId());
	}

	/**
	 * Create a new Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/phenotypeses", method = RequestMethod.POST)
	@ResponseBody
	public Phenotypes newGermplasmPhenotypeses(@PathVariable Integer germplasm_nsftvId, @RequestBody Phenotypes phenotypes) {
		germplasmService.saveGermplasmPhenotypeses(germplasm_nsftvId, phenotypes);
		return phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes.getNsftvId(), phenotypes.getTrait());
	}

	/**
	 * Show all Germplasm entities
	 * 
	 */
	@RequestMapping(value = "/Germplasm", method = RequestMethod.GET)
	@ResponseBody
	public List<Germplasm> listGermplasms() {
		return new java.util.ArrayList<Germplasm>(germplasmService.loadGermplasms());
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGermplasmGenotypings(@PathVariable Integer germplasm_nsftvId, @PathVariable String related_genotypings_snpId, @PathVariable Integer related_genotypings_nsftvId) {
		germplasmService.deleteGermplasmGenotypings(germplasm_nsftvId, related_genotypings_snpId, related_genotypings_nsftvId);
	}

	/**
	 * View an existing Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/phenotypeses/{phenotypes_nsftvId}/{phenotypes_trait}", method = RequestMethod.GET)
	@ResponseBody
	public Phenotypes loadGermplasmPhenotypeses(@PathVariable Integer germplasm_nsftvId, @PathVariable Integer related_phenotypeses_nsftvId, @PathVariable String related_phenotypeses_trait) {
		Phenotypes phenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(related_phenotypeses_nsftvId, related_phenotypeses_trait, -1, -1);

		return phenotypes;
	}

	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}/phenotypeses", method = RequestMethod.PUT)
	@ResponseBody
	public Phenotypes saveGermplasmPhenotypeses(@PathVariable Integer germplasm_nsftvId, @RequestBody Phenotypes phenotypeses) {
		germplasmService.saveGermplasmPhenotypeses(germplasm_nsftvId, phenotypeses);
		return phenotypesDAO.findPhenotypesByPrimaryKey(phenotypeses.getNsftvId(), phenotypeses.getTrait());
	}

	/**
	 * Create a new Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm", method = RequestMethod.POST)
	@ResponseBody
	public Germplasm newGermplasm(@RequestBody Germplasm germplasm) {
		germplasmService.saveGermplasm(germplasm);
		return germplasmDAO.findGermplasmByPrimaryKey(germplasm.getNsftvId());
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Germplasm/{germplasm_nsftvId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGermplasm(@PathVariable Integer germplasm_nsftvId) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId);
		germplasmService.deleteGermplasm(germplasm);
	}
}