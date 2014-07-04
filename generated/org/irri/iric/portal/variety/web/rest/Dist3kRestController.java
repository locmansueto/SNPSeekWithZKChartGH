package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.Dist3kDAO;

import org.irri.iric.portal.variety.domain.Dist3k;

import org.irri.iric.portal.variety.service.Dist3kService;

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
 * Spring Rest controller that handles CRUD requests for Dist3k entities
 * 
 */

@Controller("Dist3kRestController")
public class Dist3kRestController {

	/**
	 * DAO injected by Spring that manages Dist3k entities
	 * 
	 */
	@Autowired
	private Dist3kDAO dist3kDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Dist3k entities
	 * 
	 */
	@Autowired
	private Dist3kService dist3kService;

	/**
	 * Delete an existing Dist3k entity
	 * 
	 */
	@RequestMapping(value = "/Dist3k/{dist3k_nam1}/{dist3k_nam2}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteDist3k(@PathVariable String dist3k_nam1, @PathVariable String dist3k_nam2) {
		Dist3k dist3k = dist3kDAO.findDist3kByPrimaryKey(dist3k_nam1, dist3k_nam2);
		dist3kService.deleteDist3k(dist3k);
	}

	/**
	 * Save an existing Dist3k entity
	 * 
	 */
	@RequestMapping(value = "/Dist3k", method = RequestMethod.PUT)
	@ResponseBody
	public Dist3k saveDist3k(@RequestBody Dist3k dist3k) {
		dist3kService.saveDist3k(dist3k);
		return dist3kDAO.findDist3kByPrimaryKey(dist3k.getNam1(), dist3k.getNam2());
	}

	/**
	 * Show all Dist3k entities
	 * 
	 */
	@RequestMapping(value = "/Dist3k", method = RequestMethod.GET)
	@ResponseBody
	public List<Dist3k> listDist3ks() {
		return new java.util.ArrayList<Dist3k>(dist3kService.loadDist3ks());
	}

	/**
	 * Select an existing Dist3k entity
	 * 
	 */
	@RequestMapping(value = "/Dist3k/{dist3k_nam1}/{dist3k_nam2}", method = RequestMethod.GET)
	@ResponseBody
	public Dist3k loadDist3k(@PathVariable String dist3k_nam1, @PathVariable String dist3k_nam2) {
		return dist3kDAO.findDist3kByPrimaryKey(dist3k_nam1, dist3k_nam2);
	}

	/**
	 * Create a new Dist3k entity
	 * 
	 */
	@RequestMapping(value = "/Dist3k", method = RequestMethod.POST)
	@ResponseBody
	public Dist3k newDist3k(@RequestBody Dist3k dist3k) {
		dist3kService.saveDist3k(dist3k);
		return dist3kDAO.findDist3kByPrimaryKey(dist3k.getNam1(), dist3k.getNam2());
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