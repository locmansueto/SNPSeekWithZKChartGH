package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Var700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Var700;

import org.irri.iric.portal.variety.service.Var700Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Var700 entities
 * 
 */

@Controller("Var700Controller")
public class Var700Controller {

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
	 * Edit an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/editVar700Genotyp700s")
	public ModelAndView editVar700Genotyp700s(@RequestParam Integer var700_id, @RequestParam Integer genotyp700s_varId, @RequestParam Integer genotyp700s_snpId) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700s_varId, genotyp700s_snpId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("var700_id", var700_id);
		mav.addObject("genotyp700", genotyp700);
		mav.setViewName("var700/genotyp700s/editGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Edit an existing Var700 entity
	 * 
	 */
	@RequestMapping("/editVar700")
	public ModelAndView editVar700(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("var700", var700DAO.findVar700ByPrimaryKey(idKey));
		mav.setViewName("var700/editVar700.jsp");

		return mav;
	}

	/**
	 * Select the Var700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteVar700")
	public ModelAndView confirmDeleteVar700(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("var700", var700DAO.findVar700ByPrimaryKey(idKey));
		mav.setViewName("var700/deleteVar700.jsp");

		return mav;
	}

	/**
	 * View an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/selectVar700Genotyp700s")
	public ModelAndView selectVar700Genotyp700s(@RequestParam Integer var700_id, @RequestParam Integer genotyp700s_varId, @RequestParam Integer genotyp700s_snpId) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700s_varId, genotyp700s_snpId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("var700_id", var700_id);
		mav.addObject("genotyp700", genotyp700);
		mav.setViewName("var700/genotyp700s/viewGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Show all Var700 entities
	 * 
	 */
	@RequestMapping("/indexVar700")
	public ModelAndView listVar700s() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("var700s", var700Service.loadVar700s());

		mav.setViewName("var700/listVar700s.jsp");

		return mav;
	}

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	@RequestMapping("/saveVar700")
	public String saveVar700(@ModelAttribute Var700 var700) {
		var700Service.saveVar700(var700);
		return "forward:/indexVar700";
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
	 * Select an existing Var700 entity
	 * 
	 */
	@RequestMapping("/selectVar700")
	public ModelAndView selectVar700(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("var700", var700DAO.findVar700ByPrimaryKey(idKey));
		mav.setViewName("var700/viewVar700.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Var700 entities
	 * 
	 */
	public String indexVar700() {
		return "redirect:/indexVar700";
	}

	/**
	 * Select the child Genotyp700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteVar700Genotyp700s")
	public ModelAndView confirmDeleteVar700Genotyp700s(@RequestParam Integer var700_id, @RequestParam Integer related_genotyp700s_varId, @RequestParam Integer related_genotyp700s_snpId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", genotyp700DAO.findGenotyp700ByPrimaryKey(related_genotyp700s_varId, related_genotyp700s_snpId));
		mav.addObject("var700_id", var700_id);
		mav.setViewName("var700/genotyp700s/deleteGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Delete an existing Var700 entity
	 * 
	 */
	@RequestMapping("/deleteVar700")
	public String deleteVar700(@RequestParam Integer idKey) {
		Var700 var700 = var700DAO.findVar700ByPrimaryKey(idKey);
		var700Service.deleteVar700(var700);
		return "forward:/indexVar700";
	}

	/**
	 * Create a new Var700 entity
	 * 
	 */
	@RequestMapping("/newVar700")
	public ModelAndView newVar700() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("var700", new Var700());
		mav.addObject("newFlag", true);
		mav.setViewName("var700/editVar700.jsp");

		return mav;
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/deleteVar700Genotyp700s")
	public ModelAndView deleteVar700Genotyp700s(@RequestParam Integer var700_id, @RequestParam Integer related_genotyp700s_varId, @RequestParam Integer related_genotyp700s_snpId) {
		ModelAndView mav = new ModelAndView();

		Var700 var700 = var700Service.deleteVar700Genotyp700s(var700_id, related_genotyp700s_varId, related_genotyp700s_snpId);

		mav.addObject("var700_id", var700_id);
		mav.addObject("var700", var700);
		mav.setViewName("var700/viewVar700.jsp");

		return mav;
	}

	/**
	 * Show all Genotyp700 entities by Var700
	 * 
	 */
	@RequestMapping("/listVar700Genotyp700s")
	public ModelAndView listVar700Genotyp700s(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("var700", var700DAO.findVar700ByPrimaryKey(idKey));
		mav.setViewName("var700/genotyp700s/listGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Create a new Genotyp700 entity
	 * 
	 */
	@RequestMapping("/newVar700Genotyp700s")
	public ModelAndView newVar700Genotyp700s(@RequestParam Integer var700_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("var700_id", var700_id);
		mav.addObject("genotyp700", new Genotyp700());
		mav.addObject("newFlag", true);
		mav.setViewName("var700/genotyp700s/editGenotyp700s.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/var700Controller/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/saveVar700Genotyp700s")
	public ModelAndView saveVar700Genotyp700s(@RequestParam Integer var700_id, @ModelAttribute Genotyp700 genotyp700s) {
		Var700 parent_var700 = var700Service.saveVar700Genotyp700s(var700_id, genotyp700s);

		ModelAndView mav = new ModelAndView();
		mav.addObject("var700_id", var700_id);
		mav.addObject("var700", parent_var700);
		mav.setViewName("var700/viewVar700.jsp");

		return mav;
	}
}