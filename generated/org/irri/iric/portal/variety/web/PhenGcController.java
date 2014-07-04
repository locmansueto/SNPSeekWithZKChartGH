package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.PhenGcDAO;

import org.irri.iric.portal.variety.domain.PhenGc;

import org.irri.iric.portal.variety.service.PhenGcService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for PhenGc entities
 * 
 */

@Controller("PhenGcController")
public class PhenGcController {

	/**
	 * DAO injected by Spring that manages PhenGc entities
	 * 
	 */
	@Autowired
	private PhenGcDAO phenGcDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for PhenGc entities
	 * 
	 */
	@Autowired
	private PhenGcService phenGcService;

	/**
	 * Edit an existing PhenGc entity
	 * 
	 */
	@RequestMapping("/editPhenGc")
	public ModelAndView editPhenGc(@RequestParam Integer entnoKey, @RequestParam Integer gidKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phengc", phenGcDAO.findPhenGcByPrimaryKey(entnoKey, gidKey));
		mav.setViewName("phengc/editPhenGc.jsp");

		return mav;
	}

	/**
	 * Create a new PhenGc entity
	 * 
	 */
	@RequestMapping("/newPhenGc")
	public ModelAndView newPhenGc() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phengc", new PhenGc());
		mav.addObject("newFlag", true);
		mav.setViewName("phengc/editPhenGc.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/phengcController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
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
	 * Save an existing PhenGc entity
	 * 
	 */
	@RequestMapping("/savePhenGc")
	public String savePhenGc(@ModelAttribute PhenGc phengc) {
		phenGcService.savePhenGc(phengc);
		return "forward:/indexPhenGc";
	}

	/**
	 * Show all PhenGc entities
	 * 
	 */
	@RequestMapping("/indexPhenGc")
	public ModelAndView listPhenGcs() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phengcs", phenGcService.loadPhenGcs());

		mav.setViewName("phengc/listPhenGcs.jsp");

		return mav;
	}

	/**
	 * Entry point to show all PhenGc entities
	 * 
	 */
	public String indexPhenGc() {
		return "redirect:/indexPhenGc";
	}

	/**
	 * Delete an existing PhenGc entity
	 * 
	 */
	@RequestMapping("/deletePhenGc")
	public String deletePhenGc(@RequestParam Integer entnoKey, @RequestParam Integer gidKey) {
		PhenGc phengc = phenGcDAO.findPhenGcByPrimaryKey(entnoKey, gidKey);
		phenGcService.deletePhenGc(phengc);
		return "forward:/indexPhenGc";
	}

	/**
	 * Select the PhenGc entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePhenGc")
	public ModelAndView confirmDeletePhenGc(@RequestParam Integer entnoKey, @RequestParam Integer gidKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phengc", phenGcDAO.findPhenGcByPrimaryKey(entnoKey, gidKey));
		mav.setViewName("phengc/deletePhenGc.jsp");

		return mav;
	}

	/**
	 * Select an existing PhenGc entity
	 * 
	 */
	@RequestMapping("/selectPhenGc")
	public ModelAndView selectPhenGc(@RequestParam Integer entnoKey, @RequestParam Integer gidKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phengc", phenGcDAO.findPhenGcByPrimaryKey(entnoKey, gidKey));
		mav.setViewName("phengc/viewPhenGc.jsp");

		return mav;
	}
}