package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Snp700DAO;
import org.irri.iric.portal.variety.dao.Var700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;
import org.irri.iric.portal.variety.domain.Var700;

import org.irri.iric.portal.variety.service.Genotyp700Service;

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
 * Spring Rest controller that handles CRUD requests for Genotyp700 entities
 * 
 */

@Controller("Genotyp700RestController")
public class Genotyp700RestController {

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
	 * DAO injected by Spring that manages Var700 entities
	 * 
	 */
	@Autowired
	private Var700DAO var700DAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Genotyp700 entities
	 * 
	 */
	@Autowired
	private Genotyp700Service genotyp700Service;

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700", method = RequestMethod.PUT)
	@ResponseBody
	public Genotyp700 saveGenotyp700(@RequestBody Genotyp700 genotyp700) {
		genotyp700Service.saveGenotyp700(genotyp700);
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700.getVarId(), genotyp700.getSnpId());
	}

	/**
	 * Get Snp700 entity by Genotyp700
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700", method = RequestMethod.GET)
	@ResponseBody
	public Snp700 getGenotyp700Snp700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId) {
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700_varId, genotyp700_snpId).getSnp700();
	}

	/**
	 * View an existing Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700/{var700_id}", method = RequestMethod.GET)
	@ResponseBody
	public Var700 loadGenotyp700Var700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @PathVariable Integer related_var700_id) {
		Var700 var700 = var700DAO.findVar700ByPrimaryKey(related_var700_id, -1, -1);

		return var700;
	}

	/**
	 * View an existing Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700/{snp700_id}", method = RequestMethod.GET)
	@ResponseBody
	public Snp700 loadGenotyp700Snp700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @PathVariable Integer related_snp700_id) {
		Snp700 snp700 = snp700DAO.findSnp700ByPrimaryKey(related_snp700_id, -1, -1);

		return snp700;
	}

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700", method = RequestMethod.PUT)
	@ResponseBody
	public Var700 saveGenotyp700Var700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @RequestBody Var700 var700) {
		genotyp700Service.saveGenotyp700Var700(genotyp700_varId, genotyp700_snpId, var700);
		return var700DAO.findVar700ByPrimaryKey(var700.getId());
	}

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700", method = RequestMethod.PUT)
	@ResponseBody
	public Snp700 saveGenotyp700Snp700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @RequestBody Snp700 snp700) {
		genotyp700Service.saveGenotyp700Snp700(genotyp700_varId, genotyp700_snpId, snp700);
		return snp700DAO.findSnp700ByPrimaryKey(snp700.getId());
	}

	/**
	 * Create a new Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700", method = RequestMethod.POST)
	@ResponseBody
	public Genotyp700 newGenotyp700(@RequestBody Genotyp700 genotyp700) {
		genotyp700Service.saveGenotyp700(genotyp700);
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700.getVarId(), genotyp700.getSnpId());
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
	 * Delete an existing Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700/{snp700_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGenotyp700Snp700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @PathVariable Integer related_snp700_id) {
		genotyp700Service.deleteGenotyp700Snp700(genotyp700_varId, genotyp700_snpId, related_snp700_id);
	}

	/**
	 * Get Var700 entity by Genotyp700
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700", method = RequestMethod.GET)
	@ResponseBody
	public Var700 getGenotyp700Var700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId) {
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700_varId, genotyp700_snpId).getVar700();
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGenotyp700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700_varId, genotyp700_snpId);
		genotyp700Service.deleteGenotyp700(genotyp700);
	}

	/**
	 * Delete an existing Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700/{var700_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGenotyp700Var700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @PathVariable Integer related_var700_id) {
		genotyp700Service.deleteGenotyp700Var700(genotyp700_varId, genotyp700_snpId, related_var700_id);
	}

	/**
	 * Show all Genotyp700 entities
	 * 
	 */
	@RequestMapping(value = "/Genotyp700", method = RequestMethod.GET)
	@ResponseBody
	public List<Genotyp700> listGenotyp700s() {
		return new java.util.ArrayList<Genotyp700>(genotyp700Service.loadGenotyp700s());
	}

	/**
	 * Select an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}", method = RequestMethod.GET)
	@ResponseBody
	public Genotyp700 loadGenotyp700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId) {
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700_varId, genotyp700_snpId);
	}

	/**
	 * Create a new Snp700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700", method = RequestMethod.POST)
	@ResponseBody
	public Snp700 newGenotyp700Snp700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @RequestBody Snp700 snp700) {
		genotyp700Service.saveGenotyp700Snp700(genotyp700_varId, genotyp700_snpId, snp700);
		return snp700DAO.findSnp700ByPrimaryKey(snp700.getId());
	}

	/**
	 * Create a new Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700", method = RequestMethod.POST)
	@ResponseBody
	public Var700 newGenotyp700Var700(@PathVariable Integer genotyp700_varId, @PathVariable Integer genotyp700_snpId, @RequestBody Var700 var700) {
		genotyp700Service.saveGenotyp700Var700(genotyp700_varId, genotyp700_snpId, var700);
		return var700DAO.findVar700ByPrimaryKey(var700.getId());
	}
}