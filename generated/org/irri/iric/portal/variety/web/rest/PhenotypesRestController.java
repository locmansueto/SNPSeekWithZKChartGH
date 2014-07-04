package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.PhenotypesDAO;

import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

import org.irri.iric.portal.variety.service.PhenotypesService;

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
 * Spring Rest controller that handles CRUD requests for Phenotypes entities
 * 
 */

@Controller("PhenotypesRestController")
public class PhenotypesRestController {

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
	 * Service injected by Spring that provides CRUD operations for Phenotypes entities
	 * 
	 */
	@Autowired
	private PhenotypesService phenotypesService;

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
	 * Select an existing Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}", method = RequestMethod.GET)
	@ResponseBody
	public Phenotypes loadPhenotypes(@PathVariable Integer phenotypes_nsftvId, @PathVariable String phenotypes_trait) {
		return phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes_nsftvId, phenotypes_trait);
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm/{germplasm_nsftvId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePhenotypesGermplasm(@PathVariable Integer phenotypes_nsftvId, @PathVariable String phenotypes_trait, @PathVariable Integer related_germplasm_nsftvId) {
		phenotypesService.deletePhenotypesGermplasm(phenotypes_nsftvId, phenotypes_trait, related_germplasm_nsftvId);
	}

	/**
	 * Get Germplasm entity by Phenotypes
	 * 
	 */
	@RequestMapping(value = "/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm", method = RequestMethod.GET)
	@ResponseBody
	public Germplasm getPhenotypesGermplasm(@PathVariable Integer phenotypes_nsftvId, @PathVariable String phenotypes_trait) {
		return phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes_nsftvId, phenotypes_trait).getGermplasm();
	}

	/**
	 * View an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm/{germplasm_nsftvId}", method = RequestMethod.GET)
	@ResponseBody
	public Germplasm loadPhenotypesGermplasm(@PathVariable Integer phenotypes_nsftvId, @PathVariable String phenotypes_trait, @PathVariable Integer related_germplasm_nsftvId) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(related_germplasm_nsftvId, -1, -1);

		return germplasm;
	}

	/**
	 * Create a new Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes", method = RequestMethod.POST)
	@ResponseBody
	public Phenotypes newPhenotypes(@RequestBody Phenotypes phenotypes) {
		phenotypesService.savePhenotypes(phenotypes);
		return phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes.getNsftvId(), phenotypes.getTrait());
	}

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes", method = RequestMethod.PUT)
	@ResponseBody
	public Phenotypes savePhenotypes(@RequestBody Phenotypes phenotypes) {
		phenotypesService.savePhenotypes(phenotypes);
		return phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes.getNsftvId(), phenotypes.getTrait());
	}

	/**
	 * Show all Phenotypes entities
	 * 
	 */
	@RequestMapping(value = "/Phenotypes", method = RequestMethod.GET)
	@ResponseBody
	public List<Phenotypes> listPhenotypess() {
		return new java.util.ArrayList<Phenotypes>(phenotypesService.loadPhenotypess());
	}

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm", method = RequestMethod.PUT)
	@ResponseBody
	public Germplasm savePhenotypesGermplasm(@PathVariable Integer phenotypes_nsftvId, @PathVariable String phenotypes_trait, @RequestBody Germplasm germplasm) {
		phenotypesService.savePhenotypesGermplasm(phenotypes_nsftvId, phenotypes_trait, germplasm);
		return germplasmDAO.findGermplasmByPrimaryKey(germplasm.getNsftvId());
	}

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePhenotypes(@PathVariable Integer phenotypes_nsftvId, @PathVariable String phenotypes_trait) {
		Phenotypes phenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes_nsftvId, phenotypes_trait);
		phenotypesService.deletePhenotypes(phenotypes);
	}

	/**
	 * Create a new Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm", method = RequestMethod.POST)
	@ResponseBody
	public Germplasm newPhenotypesGermplasm(@PathVariable Integer phenotypes_nsftvId, @PathVariable String phenotypes_trait, @RequestBody Germplasm germplasm) {
		phenotypesService.savePhenotypesGermplasm(phenotypes_nsftvId, phenotypes_trait, germplasm);
		return germplasmDAO.findGermplasmByPrimaryKey(germplasm.getNsftvId());
	}
}