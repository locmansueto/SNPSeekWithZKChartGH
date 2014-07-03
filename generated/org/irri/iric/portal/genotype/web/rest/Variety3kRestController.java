package org.irri.iric.portal.genotype.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.genotype.dao.Variety3kDAO;

import org.irri.iric.portal.genotype.domain.Variety3k;

import org.irri.iric.portal.genotype.service.Variety3kService;

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
 * Spring Rest controller that handles CRUD requests for Variety3k entities
 * 
 */

@Controller("Variety3kRestController")
public class Variety3kRestController {

	/**
	 * DAO injected by Spring that manages Variety3k entities
	 * 
	 */
	@Autowired
	private Variety3kDAO variety3kDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Variety3k entities
	 * 
	 */
	@Autowired
	private Variety3kService variety3kService;

	/**
	 * Save an existing Variety3k entity
	 * 
	 */
	@RequestMapping(value = "/Variety3k", method = RequestMethod.PUT)
	@ResponseBody
	public Variety3k saveVariety3k(@RequestBody Variety3k variety3k) {
		variety3kService.saveVariety3k(variety3k);
		return variety3kDAO.findVariety3kByPrimaryKey(variety3k.getVarname());
	}

	/**
	 * Show all Variety3k entities
	 * 
	 */
	@RequestMapping(value = "/Variety3k", method = RequestMethod.GET)
	@ResponseBody
	public List<Variety3k> listVariety3ks() {
		return new java.util.ArrayList<Variety3k>(variety3kService.loadVariety3ks());
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
	 * Delete an existing Variety3k entity
	 * 
	 */
	@RequestMapping(value = "/Variety3k/{variety3k_varname}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteVariety3k(@PathVariable String variety3k_varname) {
		Variety3k variety3k = variety3kDAO.findVariety3kByPrimaryKey(variety3k_varname);
		variety3kService.deleteVariety3k(variety3k);
	}

	/**
	 * Create a new Variety3k entity
	 * 
	 */
	@RequestMapping(value = "/Variety3k", method = RequestMethod.POST)
	@ResponseBody
	public Variety3k newVariety3k(@RequestBody Variety3k variety3k) {
		variety3kService.saveVariety3k(variety3k);
		return variety3kDAO.findVariety3kByPrimaryKey(variety3k.getVarname());
	}

	/**
	 * Select an existing Variety3k entity
	 * 
	 */
	@RequestMapping(value = "/Variety3k/{variety3k_varname}", method = RequestMethod.GET)
	@ResponseBody
	public Variety3k loadVariety3k(@PathVariable String variety3k_varname) {
		return variety3kDAO.findVariety3kByPrimaryKey(variety3k_varname);
	}
}