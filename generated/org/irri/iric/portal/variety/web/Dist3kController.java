package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.Dist3kDAO;

import org.irri.iric.portal.variety.domain.Dist3k;

import org.irri.iric.portal.variety.service.Dist3kService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Dist3k entities
 * 
 */

@Controller("Dist3kController")
public class Dist3kController {

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
	 */
	@RequestMapping("/dist3kController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Delete an existing Dist3k entity
	 * 
	 */
	@RequestMapping("/deleteDist3k")
	public String deleteDist3k(@RequestParam String nam1Key, @RequestParam String nam2Key) {
		Dist3k dist3k = dist3kDAO.findDist3kByPrimaryKey(nam1Key, nam2Key);
		dist3kService.deleteDist3k(dist3k);
		return "forward:/indexDist3k";
	}

	/**
	 * Edit an existing Dist3k entity
	 * 
	 */
	@RequestMapping("/editDist3k")
	public ModelAndView editDist3k(@RequestParam String nam1Key, @RequestParam String nam2Key) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("dist3k", dist3kDAO.findDist3kByPrimaryKey(nam1Key, nam2Key));
		mav.setViewName("dist3k/editDist3k.jsp");

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
	 * Select an existing Dist3k entity
	 * 
	 */
	@RequestMapping("/selectDist3k")
	public ModelAndView selectDist3k(@RequestParam String nam1Key, @RequestParam String nam2Key) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("dist3k", dist3kDAO.findDist3kByPrimaryKey(nam1Key, nam2Key));
		mav.setViewName("dist3k/viewDist3k.jsp");

		return mav;
	}

	/**
	 * Select the Dist3k entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteDist3k")
	public ModelAndView confirmDeleteDist3k(@RequestParam String nam1Key, @RequestParam String nam2Key) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("dist3k", dist3kDAO.findDist3kByPrimaryKey(nam1Key, nam2Key));
		mav.setViewName("dist3k/deleteDist3k.jsp");

		return mav;
	}

	/**
	 * Show all Dist3k entities
	 * 
	 */
	@RequestMapping("/indexDist3k")
	public ModelAndView listDist3ks() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("dist3ks", dist3kService.loadDist3ks());

		mav.setViewName("dist3k/listDist3ks.jsp");

		return mav;
	}

	/**
	 * Save an existing Dist3k entity
	 * 
	 */
	@RequestMapping("/saveDist3k")
	public String saveDist3k(@ModelAttribute Dist3k dist3k) {
		dist3kService.saveDist3k(dist3k);
		return "forward:/indexDist3k";
	}

	/**
	 * Entry point to show all Dist3k entities
	 * 
	 */
	public String indexDist3k() {
		return "redirect:/indexDist3k";
	}

	/**
	 * Create a new Dist3k entity
	 * 
	 */
	@RequestMapping("/newDist3k")
	public ModelAndView newDist3k() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("dist3k", new Dist3k());
		mav.addObject("newFlag", true);
		mav.setViewName("dist3k/editDist3k.jsp");

		return mav;
	}
}