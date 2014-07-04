package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.List3kDAO;

import org.irri.iric.portal.variety.domain.List3k;

import org.irri.iric.portal.variety.service.List3kService;

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
 * Spring Rest controller that handles CRUD requests for List3k entities
 * 
 */

@Controller("List3kRestController")
public class List3kRestController {

	/**
	 * DAO injected by Spring that manages List3k entities
	 * 
	 */
	@Autowired
	private List3kDAO list3kDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for List3k entities
	 * 
	 */
	@Autowired
	private List3kService list3kService;

	/**
	 * Delete an existing List3k entity
	 * 
	 */
	@RequestMapping(value = "/List3k/{list3k_irisUniqueId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteList3k(@PathVariable String list3k_irisUniqueId) {
		List3k list3k = list3kDAO.findList3kByPrimaryKey(list3k_irisUniqueId);
		list3kService.deleteList3k(list3k);
	}

	/**
	 * Create a new List3k entity
	 * 
	 */
	@RequestMapping(value = "/List3k", method = RequestMethod.POST)
	@ResponseBody
	public List3k newList3k(@RequestBody List3k list3k) {
		list3kService.saveList3k(list3k);
		return list3kDAO.findList3kByPrimaryKey(list3k.getIrisUniqueId());
	}

	/**
	 * Select an existing List3k entity
	 * 
	 */
	@RequestMapping(value = "/List3k/{list3k_irisUniqueId}", method = RequestMethod.GET)
	@ResponseBody
	public List3k loadList3k(@PathVariable String list3k_irisUniqueId) {
		return list3kDAO.findList3kByPrimaryKey(list3k_irisUniqueId);
	}

	/**
	 * Show all List3k entities
	 * 
	 */
	@RequestMapping(value = "/List3k", method = RequestMethod.GET)
	@ResponseBody
	public List<List3k> listList3ks() {
		return new java.util.ArrayList<List3k>(list3kService.loadList3ks());
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
	 * Save an existing List3k entity
	 * 
	 */
	@RequestMapping(value = "/List3k", method = RequestMethod.PUT)
	@ResponseBody
	public List3k saveList3k(@RequestBody List3k list3k) {
		list3kService.saveList3k(list3k);
		return list3kDAO.findList3kByPrimaryKey(list3k.getIrisUniqueId());
	}
}