package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.SnpsDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Snps;

import org.irri.iric.portal.variety.service.SnpsService;

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
 * Spring Rest controller that handles CRUD requests for Snps entities
 * 
 */

@Controller("SnpsRestController")
public class SnpsRestController {

	/**
	 * DAO injected by Spring that manages Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingDAO genotypingDAO;

	/**
	 * DAO injected by Spring that manages Snps entities
	 * 
	 */
	@Autowired
	private SnpsDAO snpsDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Snps entities
	 * 
	 */
	@Autowired
	private SnpsService snpsService;

	/**
	 * Save an existing Snps entity
	 * 
	 */
	@RequestMapping(value = "/Snps", method = RequestMethod.PUT)
	@ResponseBody
	public Snps saveSnps(@RequestBody Snps snps) {
		snpsService.saveSnps(snps);
		return snpsDAO.findSnpsByPrimaryKey(snps.getSnpId());
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Snps/{snps_snpId}/genotypings", method = RequestMethod.PUT)
	@ResponseBody
	public Genotyping saveSnpsGenotypings(@PathVariable String snps_snpId, @RequestBody Genotyping genotypings) {
		snpsService.saveSnpsGenotypings(snps_snpId, genotypings);
		return genotypingDAO.findGenotypingByPrimaryKey(genotypings.getSnpId(), genotypings.getNsftvId());
	}

	/**
	 * Show all Genotyping entities by Snps
	 * 
	 */
	@RequestMapping(value = "/Snps/{snps_snpId}/genotypings", method = RequestMethod.GET)
	@ResponseBody
	public List<Genotyping> getSnpsGenotypings(@PathVariable String snps_snpId) {
		return new java.util.ArrayList<Genotyping>(snpsDAO.findSnpsByPrimaryKey(snps_snpId).getGenotypings());
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
	 * View an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Snps/{snps_snpId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}", method = RequestMethod.GET)
	@ResponseBody
	public Genotyping loadSnpsGenotypings(@PathVariable String snps_snpId, @PathVariable String related_genotypings_snpId, @PathVariable Integer related_genotypings_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(related_genotypings_snpId, related_genotypings_nsftvId, -1, -1);

		return genotyping;
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Snps/{snps_snpId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteSnpsGenotypings(@PathVariable String snps_snpId, @PathVariable String related_genotypings_snpId, @PathVariable Integer related_genotypings_nsftvId) {
		snpsService.deleteSnpsGenotypings(snps_snpId, related_genotypings_snpId, related_genotypings_nsftvId);
	}

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	@RequestMapping(value = "/Snps/{snps_snpId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteSnps(@PathVariable String snps_snpId) {
		Snps snps = snpsDAO.findSnpsByPrimaryKey(snps_snpId);
		snpsService.deleteSnps(snps);
	}

	/**
	 * Create a new Snps entity
	 * 
	 */
	@RequestMapping(value = "/Snps", method = RequestMethod.POST)
	@ResponseBody
	public Snps newSnps(@RequestBody Snps snps) {
		snpsService.saveSnps(snps);
		return snpsDAO.findSnpsByPrimaryKey(snps.getSnpId());
	}

	/**
	 * Create a new Genotyping entity
	 * 
	 */
	@RequestMapping(value = "/Snps/{snps_snpId}/genotypings", method = RequestMethod.POST)
	@ResponseBody
	public Genotyping newSnpsGenotypings(@PathVariable String snps_snpId, @RequestBody Genotyping genotyping) {
		snpsService.saveSnpsGenotypings(snps_snpId, genotyping);
		return genotypingDAO.findGenotypingByPrimaryKey(genotyping.getSnpId(), genotyping.getNsftvId());
	}

	/**
	 * Select an existing Snps entity
	 * 
	 */
	@RequestMapping(value = "/Snps/{snps_snpId}", method = RequestMethod.GET)
	@ResponseBody
	public Snps loadSnps(@PathVariable String snps_snpId) {
		return snpsDAO.findSnpsByPrimaryKey(snps_snpId);
	}

	/**
	 * Show all Snps entities
	 * 
	 */
	@RequestMapping(value = "/Snps", method = RequestMethod.GET)
	@ResponseBody
	public List<Snps> listSnpss() {
		return new java.util.ArrayList<Snps>(snpsService.loadSnpss());
	}
}