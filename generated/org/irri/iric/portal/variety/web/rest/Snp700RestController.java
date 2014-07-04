package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Snp700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;

import org.irri.iric.portal.variety.service.Snp700Service;

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
 * Spring Rest controller that handles CRUD requests for Snp700 entities
 * 
 */

@Controller("Snp700RestController")
public class Snp700RestController {

	/**
	 * DAO injected by Spring that manages Genotyp700 entities
	 * 
	 */
	@Autowired
	private Genotyp700DAO genotyp700DAO;

	/**
	 * DAO injected by Spring that manages Snp700 entities
	 * 
	 */
	@Autowired
	private Snp700DAO snp700DAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Snp700 entities
	 * 
	 */
	@Autowired
	private Snp700Service snp700Service;

	/**
	 * Show all Snp700 entities
	 * 
	 */
	@RequestMapping(value = "/Snp700", method = RequestMethod.GET)
	@ResponseBody
	public List<Snp700> listSnp700s() {
		return new java.util.ArrayList<Snp700>(snp700Service.loadSnp700s());
	}

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700", method = RequestMethod.PUT)
	@ResponseBody
	public Snp700 saveSnp700(@RequestBody Snp700 snp700) {
		snp700Service.saveSnp700(snp700);
		return snp700DAO.findSnp700ByPrimaryKey(snp700.getId());
	}

	/**
	 * Create a new Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700", method = RequestMethod.POST)
	@ResponseBody
	public Snp700 newSnp700(@RequestBody Snp700 snp700) {
		snp700Service.saveSnp700(snp700);
		return snp700DAO.findSnp700ByPrimaryKey(snp700.getId());
	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700/{snp700_id}/genotyp700s", method = RequestMethod.PUT)
	@ResponseBody
	public Genotyp700 saveSnp700Genotyp700s(@PathVariable Integer snp700_id, @RequestBody Genotyp700 genotyp700s) {
		snp700Service.saveSnp700Genotyp700s(snp700_id, genotyp700s);
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700s.getVarId(), genotyp700s.getSnpId());
	}

	/**
	 * Delete an existing Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700/{snp700_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteSnp700(@PathVariable Integer snp700_id) {
		Snp700 snp700 = snp700DAO.findSnp700ByPrimaryKey(snp700_id);
		snp700Service.deleteSnp700(snp700);
	}

	/**
	 * Show all Genotyp700 entities by Snp700
	 * 
	 */
	@RequestMapping(value = "/Snp700/{snp700_id}/genotyp700s", method = RequestMethod.GET)
	@ResponseBody
	public List<Genotyp700> getSnp700Genotyp700s(@PathVariable Integer snp700_id) {
		return new java.util.ArrayList<Genotyp700>(snp700DAO.findSnp700ByPrimaryKey(snp700_id).getGenotyp700s());
	}

	/**
	 * Create a new Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700/{snp700_id}/genotyp700s", method = RequestMethod.POST)
	@ResponseBody
	public Genotyp700 newSnp700Genotyp700s(@PathVariable Integer snp700_id, @RequestBody Genotyp700 genotyp700) {
		snp700Service.saveSnp700Genotyp700s(snp700_id, genotyp700);
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700.getVarId(), genotyp700.getSnpId());
	}

	/**
	 * View an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700/{snp700_id}/genotyp700s/{genotyp700_varId}/{genotyp700_snpId}", method = RequestMethod.GET)
	@ResponseBody
	public Genotyp700 loadSnp700Genotyp700s(@PathVariable Integer snp700_id, @PathVariable Integer related_genotyp700s_varId, @PathVariable Integer related_genotyp700s_snpId) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(related_genotyp700s_varId, related_genotyp700s_snpId, -1, -1);

		return genotyp700;
	}

	/**
	 * Select an existing Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700/{snp700_id}", method = RequestMethod.GET)
	@ResponseBody
	public Snp700 loadSnp700(@PathVariable Integer snp700_id) {
		return snp700DAO.findSnp700ByPrimaryKey(snp700_id);
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Snp700/{snp700_id}/genotyp700s/{genotyp700_varId}/{genotyp700_snpId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteSnp700Genotyp700s(@PathVariable Integer snp700_id, @PathVariable Integer related_genotyp700s_varId, @PathVariable Integer related_genotyp700s_snpId) {
		snp700Service.deleteSnp700Genotyp700s(snp700_id, related_genotyp700s_varId, related_genotyp700s_snpId);
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
}