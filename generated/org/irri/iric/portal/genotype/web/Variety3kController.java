package org.irri.iric.portal.genotype.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.genotype.dao.Variety3kDAO;

import org.irri.iric.portal.genotype.domain.Variety3k;

import org.irri.iric.portal.genotype.service.Variety3kService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Variety3k entities
 * 
 */

@Controller("Variety3kController")
public class Variety3kController {

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
	 * Delete an existing Variety3k entity
	 * 
	 */
	@RequestMapping("/deleteVariety3k")
	public String deleteVariety3k(@RequestParam String varnameKey) {
		Variety3k variety3k = variety3kDAO.findVariety3kByPrimaryKey(varnameKey);
		variety3kService.deleteVariety3k(variety3k);
		return "forward:/indexVariety3k";
	}

	/**
	 * Edit an existing Variety3k entity
	 * 
	 */
	@RequestMapping("/editVariety3k")
	public ModelAndView editVariety3k(@RequestParam String varnameKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("variety3k", variety3kDAO.findVariety3kByPrimaryKey(varnameKey));
		mav.setViewName("variety3k/editVariety3k.jsp");

		return mav;
	}

	/**
	 * Show all Variety3k entities
	 * 
	 */
	@RequestMapping("/indexVariety3k")
	public ModelAndView listVariety3ks() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("variety3ks", variety3kService.loadVariety3ks());

		mav.setViewName("variety3k/listVariety3ks.jsp");

		return mav;
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
	 * Entry point to show all Variety3k entities
	 * 
	 */
	public String indexVariety3k() {
		return "redirect:/indexVariety3k";
	}

	/**
	 * Create a new Variety3k entity
	 * 
	 */
	@RequestMapping("/newVariety3k")
	public ModelAndView newVariety3k() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("variety3k", new Variety3k());
		mav.addObject("newFlag", true);
		mav.setViewName("variety3k/editVariety3k.jsp");

		return mav;
	}

	/**
	 * Select an existing Variety3k entity
	 * 
	 */
	@RequestMapping("/selectVariety3k")
	public ModelAndView selectVariety3k(@RequestParam String varnameKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("variety3k", variety3kDAO.findVariety3kByPrimaryKey(varnameKey));
		mav.setViewName("variety3k/viewVariety3k.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/variety3kController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Select the Variety3k entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteVariety3k")
	public ModelAndView confirmDeleteVariety3k(@RequestParam String varnameKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("variety3k", variety3kDAO.findVariety3kByPrimaryKey(varnameKey));
		mav.setViewName("variety3k/deleteVariety3k.jsp");

		return mav;
	}

	/**
	 * Save an existing Variety3k entity
	 * 
	 */
	@RequestMapping("/saveVariety3k")
	public String saveVariety3k(@ModelAttribute Variety3k variety3k) {
		variety3kService.saveVariety3k(variety3k);
		return "forward:/indexVariety3k";
	}
}