package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.SnpsDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Snps;

import org.irri.iric.portal.variety.service.GenotypingService;

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
 * Spring Rest controller that handles CRUD requests for Genotyping entities
 * 
 */

@Controller("GenotypingRestController")
public class GenotypingRestController {

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
	 * DAO injected by Spring that manages Snps entities
	 * 
	 */
	@Autowired
	private SnpsDAO snpsDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingService genotypingService;

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm/{germplasm_nsftvId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGenotypingGermplasm(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @PathVariable Integer related_germplasm_nsftvId) {
		genotypingService.deleteGenotypingGermplasm(genotyping_snpId, genotyping_nsftvId, related_germplasm_nsftvId);
	}

	/**
	 * Create a new Snps entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps", method = RequestMethod.POST)
	@ResponseBody
	public Snps newGenotypingSnps(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @RequestBody Snps snps) {
		genotypingService.saveGenotypingSnps(genotyping_snpId, genotyping_nsftvId, snps);
		return snpsDAO.findSnpsByPrimaryKey(snps.getSnpId());
	}

	/**
	 * Create a new Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping", method = RequestMethod.POST)
	@ResponseBody
	public Genotyping newGenotyping(@RequestBody Genotyping genotyping) {
		genotypingService.saveGenotyping(genotyping);
		return genotypingDAO.findGenotypingByPrimaryKey(genotyping.getSnpId(), genotyping.getNsftvId());
	}

	/**
	 * Save an existing Snps entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps", method = RequestMethod.PUT)
	@ResponseBody
	public Snps saveGenotypingSnps(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @RequestBody Snps snps) {
		genotypingService.saveGenotypingSnps(genotyping_snpId, genotyping_nsftvId, snps);
		return snpsDAO.findSnpsByPrimaryKey(snps.getSnpId());
	}

	/**
	 * Get Snps entity by Genotyping
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps", method = RequestMethod.GET)
	@ResponseBody
	public Snps getGenotypingSnps(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId) {
		return genotypingDAO.findGenotypingByPrimaryKey(genotyping_snpId, genotyping_nsftvId).getSnps();
	}

	/**
	 * Select an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}", method = RequestMethod.GET)
	@ResponseBody
	public Genotyping loadGenotyping(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId) {
		return genotypingDAO.findGenotypingByPrimaryKey(genotyping_snpId, genotyping_nsftvId);
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
	 * Create a new Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm", method = RequestMethod.POST)
	@ResponseBody
	public Germplasm newGenotypingGermplasm(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @RequestBody Germplasm germplasm) {
		genotypingService.saveGenotypingGermplasm(genotyping_snpId, genotyping_nsftvId, germplasm);
		return germplasmDAO.findGermplasmByPrimaryKey(germplasm.getNsftvId());
	}

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm", method = RequestMethod.PUT)
	@ResponseBody
	public Germplasm saveGenotypingGermplasm(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @RequestBody Germplasm germplasm) {
		genotypingService.saveGenotypingGermplasm(genotyping_snpId, genotyping_nsftvId, germplasm);
		return germplasmDAO.findGermplasmByPrimaryKey(germplasm.getNsftvId());
	}

	/**
	 * Show all Genotyping entities
	 * 
	 */
	@RequestMapping(value = "/Genotyping", method = RequestMethod.GET)
	@ResponseBody
	public List<Genotyping> listGenotypings() {
		return new java.util.ArrayList<Genotyping>(genotypingService.loadGenotypings());
	}

	/**
	 * View an existing Snps entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps/{snps_snpId}", method = RequestMethod.GET)
	@ResponseBody
	public Snps loadGenotypingSnps(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @PathVariable String related_snps_snpId) {
		Snps snps = snpsDAO.findSnpsByPrimaryKey(related_snps_snpId, -1, -1);

		return snps;
	}

	/**
	 * Get Germplasm entity by Genotyping
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm", method = RequestMethod.GET)
	@ResponseBody
	public Germplasm getGenotypingGermplasm(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId) {
		return genotypingDAO.findGenotypingByPrimaryKey(genotyping_snpId, genotyping_nsftvId).getGermplasm();
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping", method = RequestMethod.PUT)
	@ResponseBody
	public Genotyping saveGenotyping(@RequestBody Genotyping genotyping) {
		genotypingService.saveGenotyping(genotyping);
		return genotypingDAO.findGenotypingByPrimaryKey(genotyping.getSnpId(), genotyping.getNsftvId());
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGenotyping(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(genotyping_snpId, genotyping_nsftvId);
		genotypingService.deleteGenotyping(genotyping);
	}

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps/{snps_snpId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGenotypingSnps(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @PathVariable String related_snps_snpId) {
		genotypingService.deleteGenotypingSnps(genotyping_snpId, genotyping_nsftvId, related_snps_snpId);
	}

	/**
	 * View an existing Germplasm entity
	 * 
	 */
	@RequestMapping(value = "/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm/{germplasm_nsftvId}", method = RequestMethod.GET)
	@ResponseBody
	public Germplasm loadGenotypingGermplasm(@PathVariable String genotyping_snpId, @PathVariable Integer genotyping_nsftvId, @PathVariable Integer related_germplasm_nsftvId) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(related_germplasm_nsftvId, -1, -1);

		return germplasm;
	}
}