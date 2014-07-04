package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Var700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Var700;

import org.irri.iric.portal.variety.service.Var700Service;

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
 * Spring Rest controller that handles CRUD requests for Var700 entities
 * 
 */

@Controller("Var700RestController")
public class Var700RestController {

	/**
	 * DAO injected by Spring that manages Genotyp700 entities
	 * 
	 */
	@Autowired
	private Genotyp700DAO genotyp700DAO;

	/**
	 * DAO injected by Spring that manages Var700 entities
	 * 
	 */
	@Autowired
	private Var700DAO var700DAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Var700 entities
	 * 
	 */
	@Autowired
	private Var700Service var700Service;

	/**
	 * View an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700/{var700_id}/genotyp700s/{genotyp700_varId}/{genotyp700_snpId}", method = RequestMethod.GET)
	@ResponseBody
	public Genotyp700 loadVar700Genotyp700s(@PathVariable Integer var700_id, @PathVariable Integer related_genotyp700s_varId, @PathVariable Integer related_genotyp700s_snpId) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(related_genotyp700s_varId, related_genotyp700s_snpId, -1, -1);

		return genotyp700;
	}

	/**
	 * Create a new Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700", method = RequestMethod.POST)
	@ResponseBody
	public Var700 newVar700(@RequestBody Var700 var700) {
		var700Service.saveVar700(var700);
		return var700DAO.findVar700ByPrimaryKey(var700.getId());
	}

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700", method = RequestMethod.PUT)
	@ResponseBody
	public Var700 saveVar700(@RequestBody Var700 var700) {
		var700Service.saveVar700(var700);
		return var700DAO.findVar700ByPrimaryKey(var700.getId());
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
	 * Delete an existing Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700/{var700_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteVar700(@PathVariable Integer var700_id) {
		Var700 var700 = var700DAO.findVar700ByPrimaryKey(var700_id);
		var700Service.deleteVar700(var700);
	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700/{var700_id}/genotyp700s", method = RequestMethod.PUT)
	@ResponseBody
	public Genotyp700 saveVar700Genotyp700s(@PathVariable Integer var700_id, @RequestBody Genotyp700 genotyp700s) {
		var700Service.saveVar700Genotyp700s(var700_id, genotyp700s);
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700s.getVarId(), genotyp700s.getSnpId());
	}

	/**
	 * Show all Var700 entities
	 * 
	 */
	@RequestMapping(value = "/Var700", method = RequestMethod.GET)
	@ResponseBody
	public List<Var700> listVar700s() {
		return new java.util.ArrayList<Var700>(var700Service.loadVar700s());
	}

	/**
	 * Show all Genotyp700 entities by Var700
	 * 
	 */
	@RequestMapping(value = "/Var700/{var700_id}/genotyp700s", method = RequestMethod.GET)
	@ResponseBody
	public List<Genotyp700> getVar700Genotyp700s(@PathVariable Integer var700_id) {
		return new java.util.ArrayList<Genotyp700>(var700DAO.findVar700ByPrimaryKey(var700_id).getGenotyp700s());
	}

	/**
	 * Select an existing Var700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700/{var700_id}", method = RequestMethod.GET)
	@ResponseBody
	public Var700 loadVar700(@PathVariable Integer var700_id) {
		return var700DAO.findVar700ByPrimaryKey(var700_id);
	}

	/**
	 * Create a new Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700/{var700_id}/genotyp700s", method = RequestMethod.POST)
	@ResponseBody
	public Genotyp700 newVar700Genotyp700s(@PathVariable Integer var700_id, @RequestBody Genotyp700 genotyp700) {
		var700Service.saveVar700Genotyp700s(var700_id, genotyp700);
		return genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700.getVarId(), genotyp700.getSnpId());
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping(value = "/Var700/{var700_id}/genotyp700s/{genotyp700_varId}/{genotyp700_snpId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteVar700Genotyp700s(@PathVariable Integer var700_id, @PathVariable Integer related_genotyp700s_varId, @PathVariable Integer related_genotyp700s_snpId) {
		var700Service.deleteVar700Genotyp700s(var700_id, related_genotyp700s_varId, related_genotyp700s_snpId);
	}
}