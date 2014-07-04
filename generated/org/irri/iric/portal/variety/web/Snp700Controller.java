package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Snp700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;

import org.irri.iric.portal.variety.service.Snp700Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Snp700 entities
 * 
 */

@Controller("Snp700Controller")
public class Snp700Controller {

	/**
	 * DAO injected by Spring that manages Genotyp700 entities
	 * 
	 */
	@Autowired
	private Genotyp700DAO genotyp700DAO;

	/**
	 * DAO injected by Spring that manages Snp700 entities
	 * 
	 */
	@Autowired
	private Snp700DAO snp700DAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Snp700 entities
	 * 
	 */
	@Autowired
	private Snp700Service snp700Service;

	/**
	 * Select the Snp700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnp700")
	public ModelAndView confirmDeleteSnp700(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snp700", snp700DAO.findSnp700ByPrimaryKey(idKey));
		mav.setViewName("snp700/deleteSnp700.jsp");

		return mav;
	}

	/**
	 * Edit an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/editSnp700")
	public ModelAndView editSnp700(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snp700", snp700DAO.findSnp700ByPrimaryKey(idKey));
		mav.setViewName("snp700/editSnp700.jsp");

		return mav;
	}

	/**
	 * Edit an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/editSnp700Genotyp700s")
	public ModelAndView editSnp700Genotyp700s(@RequestParam Integer snp700_id, @RequestParam Integer genotyp700s_varId, @RequestParam Integer genotyp700s_snpId) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700s_varId, genotyp700s_snpId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snp700_id", snp700_id);
		mav.addObject("genotyp700", genotyp700);
		mav.setViewName("snp700/genotyp700s/editGenotyp700s.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/snp700Controller/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Delete an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/deleteSnp700")
	public String deleteSnp700(@RequestParam Integer idKey) {
		Snp700 snp700 = snp700DAO.findSnp700ByPrimaryKey(idKey);
		snp700Service.deleteSnp700(snp700);
		return "forward:/indexSnp700";
	}

	/**
	 * Create a new Genotyp700 entity
	 * 
	 */
	@RequestMapping("/newSnp700Genotyp700s")
	public ModelAndView newSnp700Genotyp700s(@RequestParam Integer snp700_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("snp700_id", snp700_id);
		mav.addObject("genotyp700", new Genotyp700());
		mav.addObject("newFlag", true);
		mav.setViewName("snp700/genotyp700s/editGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Snp700 entities
	 * 
	 */
	public String indexSnp700() {
		return "redirect:/indexSnp700";
	}

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/saveSnp700")
	public String saveSnp700(@ModelAttribute Snp700 snp700) {
		snp700Service.saveSnp700(snp700);
		return "forward:/indexSnp700";
	}

	/**
	 * View an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/selectSnp700Genotyp700s")
	public ModelAndView selectSnp700Genotyp700s(@RequestParam Integer snp700_id, @RequestParam Integer genotyp700s_varId, @RequestParam Integer genotyp700s_snpId) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700s_varId, genotyp700s_snpId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snp700_id", snp700_id);
		mav.addObject("genotyp700", genotyp700);
		mav.setViewName("snp700/genotyp700s/viewGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Select the child Genotyp700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnp700Genotyp700s")
	public ModelAndView confirmDeleteSnp700Genotyp700s(@RequestParam Integer snp700_id, @RequestParam Integer related_genotyp700s_varId, @RequestParam Integer related_genotyp700s_snpId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", genotyp700DAO.findGenotyp700ByPrimaryKey(related_genotyp700s_varId, related_genotyp700s_snpId));
		mav.addObject("snp700_id", snp700_id);
		mav.setViewName("snp700/genotyp700s/deleteGenotyp700s.jsp");

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
	 * Show all Genotyp700 entities by Snp700
	 * 
	 */
	@RequestMapping("/listSnp700Genotyp700s")
	public ModelAndView listSnp700Genotyp700s(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snp700", snp700DAO.findSnp700ByPrimaryKey(idKey));
		mav.setViewName("snp700/genotyp700s/listGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/deleteSnp700Genotyp700s")
	public ModelAndView deleteSnp700Genotyp700s(@RequestParam Integer snp700_id, @RequestParam Integer related_genotyp700s_varId, @RequestParam Integer related_genotyp700s_snpId) {
		ModelAndView mav = new ModelAndView();

		Snp700 snp700 = snp700Service.deleteSnp700Genotyp700s(snp700_id, related_genotyp700s_varId, related_genotyp700s_snpId);

		mav.addObject("snp700_id", snp700_id);
		mav.addObject("snp700", snp700);
		mav.setViewName("snp700/viewSnp700.jsp");

		return mav;
	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/saveSnp700Genotyp700s")
	public ModelAndView saveSnp700Genotyp700s(@RequestParam Integer snp700_id, @ModelAttribute Genotyp700 genotyp700s) {
		Snp700 parent_snp700 = snp700Service.saveSnp700Genotyp700s(snp700_id, genotyp700s);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snp700_id", snp700_id);
		mav.addObject("snp700", parent_snp700);
		mav.setViewName("snp700/viewSnp700.jsp");

		return mav;
	}

	/**
	 * Show all Snp700 entities
	 * 
	 */
	@RequestMapping("/indexSnp700")
	public ModelAndView listSnp700s() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snp700s", snp700Service.loadSnp700s());

		mav.setViewName("snp700/listSnp700s.jsp");

		return mav;
	}

	/**
	 * Create a new Snp700 entity
	 * 
	 */
	@RequestMapping("/newSnp700")
	public ModelAndView newSnp700() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snp700", new Snp700());
		mav.addObject("newFlag", true);
		mav.setViewName("snp700/editSnp700.jsp");

		return mav;
	}

	/**
	 * Select an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/selectSnp700")
	public ModelAndView selectSnp700(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snp700", snp700DAO.findSnp700ByPrimaryKey(idKey));
		mav.setViewName("snp700/viewSnp700.jsp");

		return mav;
	}
}