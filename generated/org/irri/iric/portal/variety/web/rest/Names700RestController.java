package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.Names700DAO;

import org.irri.iric.portal.variety.domain.Names700;

import org.irri.iric.portal.variety.service.Names700Service;

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
 * Spring Rest controller that handles CRUD requests for Names700 entities
 * 
 */

@Controller("Names700RestController")
public class Names700RestController {

	/**
	 * DAO injected by Spring that manages Names700 entities
	 * 
	 */
	@Autowired
	private Names700DAO names700DAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Names700 entities
	 * 
	 */
	@Autowired
	private Names700Service names700Service;

	/**
	 * Show all Names700 entities
	 * 
	 */
	@RequestMapping(value = "/Names700", method = RequestMethod.GET)
	@ResponseBody
	public List<Names700> listNames700s() {
		return new java.util.ArrayList<Names700>(names700Service.loadNames700s());
	}

	/**
	 * Select an existing Names700 entity
	 * 
	 */
	@RequestMapping(value = "/Names700/{names700_id}", method = RequestMethod.GET)
	@ResponseBody
	public Names700 loadNames700(@PathVariable String names700_id) {
		return names700DAO.findNames700ByPrimaryKey(names700_id);
	}

	/**
	 * Create a new Names700 entity
	 * 
	 */
	@RequestMapping(value = "/Names700", method = RequestMethod.POST)
	@ResponseBody
	public Names700 newNames700(@RequestBody Names700 names700) {
		names700Service.saveNames700(names700);
		return names700DAO.findNames700ByPrimaryKey(names700.getId());
	}

	/**
	 * Delete an existing Names700 entity
	 * 
	 */
	@RequestMapping(value = "/Names700/{names700_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteNames700(@PathVariable String names700_id) {
		Names700 names700 = names700DAO.findNames700ByPrimaryKey(names700_id);
		names700Service.deleteNames700(names700);
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
	 * Save an existing Names700 entity
	 * 
	 */
	@RequestMapping(value = "/Names700", method = RequestMethod.PUT)
	@ResponseBody
	public Names700 saveNames700(@RequestBody Names700 names700) {
		names700Service.saveNames700(names700);
		return names700DAO.findNames700ByPrimaryKey(names700.getId());
	}
}