package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.Names700DAO;

import org.irri.iric.portal.variety.domain.Names700;

import org.irri.iric.portal.variety.service.Names700Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Names700 entities
 * 
 */

@Controller("Names700Controller")
public class Names700Controller {

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
	 * Create a new Names700 entity
	 * 
	 */
	@RequestMapping("/newNames700")
	public ModelAndView newNames700() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("names700", new Names700());
		mav.addObject("newFlag", true);
		mav.setViewName("names700/editNames700.jsp");

		return mav;
	}

	/**
	 * Edit an existing Names700 entity
	 * 
	 */
	@RequestMapping("/editNames700")
	public ModelAndView editNames700(@RequestParam String idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("names700", names700DAO.findNames700ByPrimaryKey(idKey));
		mav.setViewName("names700/editNames700.jsp");

		return mav;
	}

	/**
	 * Select an existing Names700 entity
	 * 
	 */
	@RequestMapping("/selectNames700")
	public ModelAndView selectNames700(@RequestParam String idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("names700", names700DAO.findNames700ByPrimaryKey(idKey));
		mav.setViewName("names700/viewNames700.jsp");

		return mav;
	}

	/**
	 * Delete an existing Names700 entity
	 * 
	 */
	@RequestMapping("/deleteNames700")
	public String deleteNames700(@RequestParam String idKey) {
		Names700 names700 = names700DAO.findNames700ByPrimaryKey(idKey);
		names700Service.deleteNames700(names700);
		return "forward:/indexNames700";
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
	 */
	@RequestMapping("/names700Controller/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Entry point to show all Names700 entities
	 * 
	 */
	public String indexNames700() {
		return "redirect:/indexNames700";
	}

	/**
	 * Select the Names700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteNames700")
	public ModelAndView confirmDeleteNames700(@RequestParam String idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("names700", names700DAO.findNames700ByPrimaryKey(idKey));
		mav.setViewName("names700/deleteNames700.jsp");

		return mav;
	}

	/**
	 * Show all Names700 entities
	 * 
	 */
	@RequestMapping("/indexNames700")
	public ModelAndView listNames700s() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("names700s", names700Service.loadNames700s());

		mav.setViewName("names700/listNames700s.jsp");

		return mav;
	}

	/**
	 * Save an existing Names700 entity
	 * 
	 */
	@RequestMapping("/saveNames700")
	public String saveNames700(@ModelAttribute Names700 names700) {
		names700Service.saveNames700(names700);
		return "forward:/indexNames700";
	}
}