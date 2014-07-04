package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.List3kDAO;

import org.irri.iric.portal.variety.domain.List3k;

import org.irri.iric.portal.variety.service.List3kService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for List3k entities
 * 
 */

@Controller("List3kController")
public class List3kController {

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
	 * Edit an existing List3k entity
	 * 
	 */
	@RequestMapping("/editList3k")
	public ModelAndView editList3k(@RequestParam String irisUniqueIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list3k", list3kDAO.findList3kByPrimaryKey(irisUniqueIdKey));
		mav.setViewName("list3k/editList3k.jsp");

		return mav;
	}

	/**
	 * Create a new List3k entity
	 * 
	 */
	@RequestMapping("/newList3k")
	public ModelAndView newList3k() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list3k", new List3k());
		mav.addObject("newFlag", true);
		mav.setViewName("list3k/editList3k.jsp");

		return mav;
	}

	/**
	 * Select an existing List3k entity
	 * 
	 */
	@RequestMapping("/selectList3k")
	public ModelAndView selectList3k(@RequestParam String irisUniqueIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list3k", list3kDAO.findList3kByPrimaryKey(irisUniqueIdKey));
		mav.setViewName("list3k/viewList3k.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/list3kController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Select the List3k entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteList3k")
	public ModelAndView confirmDeleteList3k(@RequestParam String irisUniqueIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list3k", list3kDAO.findList3kByPrimaryKey(irisUniqueIdKey));
		mav.setViewName("list3k/deleteList3k.jsp");

		return mav;
	}

	/**
	 * Save an existing List3k entity
	 * 
	 */
	@RequestMapping("/saveList3k")
	public String saveList3k(@ModelAttribute List3k list3k) {
		list3kService.saveList3k(list3k);
		return "forward:/indexList3k";
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
	 * Show all List3k entities
	 * 
	 */
	@RequestMapping("/indexList3k")
	public ModelAndView listList3ks() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list3ks", list3kService.loadList3ks());

		mav.setViewName("list3k/listList3ks.jsp");

		return mav;
	}

	/**
	 * Delete an existing List3k entity
	 * 
	 */
	@RequestMapping("/deleteList3k")
	public String deleteList3k(@RequestParam String irisUniqueIdKey) {
		List3k list3k = list3kDAO.findList3kByPrimaryKey(irisUniqueIdKey);
		list3kService.deleteList3k(list3k);
		return "forward:/indexList3k";
	}

	/**
	 * Entry point to show all List3k entities
	 * 
	 */
	public String indexList3k() {
		return "redirect:/indexList3k";
	}
}