package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Snp700DAO;
import org.irri.iric.portal.variety.dao.Var700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;
import org.irri.iric.portal.variety.domain.Var700;

import org.irri.iric.portal.variety.service.Genotyp700Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Genotyp700 entities
 * 
 */

@Controller("Genotyp700Controller")
public class Genotyp700Controller {

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
	 * DAO injected by Spring that manages Var700 entities
	 * 
	 */
	@Autowired
	private Var700DAO var700DAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Genotyp700 entities
	 * 
	 */
	@Autowired
	private Genotyp700Service genotyp700Service;

	/**
	 * Select the Genotyp700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGenotyp700")
	public ModelAndView confirmDeleteGenotyp700(@RequestParam Integer varIdKey, @RequestParam Integer snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", genotyp700DAO.findGenotyp700ByPrimaryKey(varIdKey, snpIdKey));
		mav.setViewName("genotyp700/deleteGenotyp700.jsp");

		return mav;
	}

	/**
	 * Create a new Snp700 entity
	 * 
	 */
	@RequestMapping("/newGenotyp700Snp700")
	public ModelAndView newGenotyp700Snp700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("snp700", new Snp700());
		mav.addObject("newFlag", true);
		mav.setViewName("genotyp700/snp700/editSnp700.jsp");

		return mav;
	}

	/**
	 * Delete an existing Var700 entity
	 * 
	 */
	@RequestMapping("/deleteGenotyp700Var700")
	public ModelAndView deleteGenotyp700Var700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer related_var700_id) {
		ModelAndView mav = new ModelAndView();

		Genotyp700 genotyp700 = genotyp700Service.deleteGenotyp700Var700(genotyp700_varId, genotyp700_snpId, related_var700_id);

		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("genotyp700", genotyp700);
		mav.setViewName("genotyp700/viewGenotyp700.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/genotyp700Controller/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * View an existing Var700 entity
	 * 
	 */
	@RequestMapping("/selectGenotyp700Var700")
	public ModelAndView selectGenotyp700Var700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer var700_id) {
		Var700 var700 = var700DAO.findVar700ByPrimaryKey(var700_id, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("var700", var700);
		mav.setViewName("genotyp700/var700/viewVar700.jsp");

		return mav;
	}

	/**
	 * Show all Genotyp700 entities
	 * 
	 */
	@RequestMapping("/indexGenotyp700")
	public ModelAndView listGenotyp700s() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700s", genotyp700Service.loadGenotyp700s());

		mav.setViewName("genotyp700/listGenotyp700s.jsp");

		return mav;
	}

	/**
	 * Edit an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/editGenotyp700Snp700")
	public ModelAndView editGenotyp700Snp700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer snp700_id) {
		Snp700 snp700 = snp700DAO.findSnp700ByPrimaryKey(snp700_id, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("snp700", snp700);
		mav.setViewName("genotyp700/snp700/editSnp700.jsp");

		return mav;
	}

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	@RequestMapping("/saveGenotyp700Var700")
	public ModelAndView saveGenotyp700Var700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @ModelAttribute Var700 var700) {
		Genotyp700 parent_genotyp700 = genotyp700Service.saveGenotyp700Var700(genotyp700_varId, genotyp700_snpId, var700);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("genotyp700", parent_genotyp700);
		mav.setViewName("genotyp700/viewGenotyp700.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Genotyp700 entities
	 * 
	 */
	public String indexGenotyp700() {
		return "redirect:/indexGenotyp700";
	}

	/**
	 * Edit an existing Var700 entity
	 * 
	 */
	@RequestMapping("/editGenotyp700Var700")
	public ModelAndView editGenotyp700Var700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer var700_id) {
		Var700 var700 = var700DAO.findVar700ByPrimaryKey(var700_id, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("var700", var700);
		mav.setViewName("genotyp700/var700/editVar700.jsp");

		return mav;
	}

	/**
	 * Select an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/selectGenotyp700")
	public ModelAndView selectGenotyp700(@RequestParam Integer varIdKey, @RequestParam Integer snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", genotyp700DAO.findGenotyp700ByPrimaryKey(varIdKey, snpIdKey));
		mav.setViewName("genotyp700/viewGenotyp700.jsp");

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
	 * Create a new Var700 entity
	 * 
	 */
	@RequestMapping("/newGenotyp700Var700")
	public ModelAndView newGenotyp700Var700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("var700", new Var700());
		mav.addObject("newFlag", true);
		mav.setViewName("genotyp700/var700/editVar700.jsp");

		return mav;
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/deleteGenotyp700")
	public String deleteGenotyp700(@RequestParam Integer varIdKey, @RequestParam Integer snpIdKey) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(varIdKey, snpIdKey);
		genotyp700Service.deleteGenotyp700(genotyp700);
		return "forward:/indexGenotyp700";
	}

	/**
	 * Show all Snp700 entities by Genotyp700
	 * 
	 */
	@RequestMapping("/listGenotyp700Snp700")
	public ModelAndView listGenotyp700Snp700(@RequestParam Integer varIdKey, @RequestParam Integer snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", genotyp700DAO.findGenotyp700ByPrimaryKey(varIdKey, snpIdKey));
		mav.setViewName("genotyp700/snp700/listSnp700.jsp");

		return mav;
	}

	/**
	 * Select the child Snp700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGenotyp700Snp700")
	public ModelAndView confirmDeleteGenotyp700Snp700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer related_snp700_id) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snp700", snp700DAO.findSnp700ByPrimaryKey(related_snp700_id));
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.setViewName("genotyp700/snp700/deleteSnp700.jsp");

		return mav;
	}

	/**
	 * View an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/selectGenotyp700Snp700")
	public ModelAndView selectGenotyp700Snp700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer snp700_id) {
		Snp700 snp700 = snp700DAO.findSnp700ByPrimaryKey(snp700_id, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("snp700", snp700);
		mav.setViewName("genotyp700/snp700/viewSnp700.jsp");

		return mav;
	}

	/**
	 * Edit an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/editGenotyp700")
	public ModelAndView editGenotyp700(@RequestParam Integer varIdKey, @RequestParam Integer snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", genotyp700DAO.findGenotyp700ByPrimaryKey(varIdKey, snpIdKey));
		mav.setViewName("genotyp700/editGenotyp700.jsp");

		return mav;
	}

	/**
	 * Select the child Var700 entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGenotyp700Var700")
	public ModelAndView confirmDeleteGenotyp700Var700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer related_var700_id) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("var700", var700DAO.findVar700ByPrimaryKey(related_var700_id));
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.setViewName("genotyp700/var700/deleteVar700.jsp");

		return mav;
	}

	/**
	 * Delete an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/deleteGenotyp700Snp700")
	public ModelAndView deleteGenotyp700Snp700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @RequestParam Integer related_snp700_id) {
		ModelAndView mav = new ModelAndView();

		Genotyp700 genotyp700 = genotyp700Service.deleteGenotyp700Snp700(genotyp700_varId, genotyp700_snpId, related_snp700_id);

		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("genotyp700", genotyp700);
		mav.setViewName("genotyp700/viewGenotyp700.jsp");

		return mav;
	}

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	@RequestMapping("/saveGenotyp700Snp700")
	public ModelAndView saveGenotyp700Snp700(@RequestParam Integer genotyp700_varId, @RequestParam Integer genotyp700_snpId, @ModelAttribute Snp700 snp700) {
		Genotyp700 parent_genotyp700 = genotyp700Service.saveGenotyp700Snp700(genotyp700_varId, genotyp700_snpId, snp700);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyp700_varId", genotyp700_varId);
		mav.addObject("genotyp700_snpId", genotyp700_snpId);
		mav.addObject("genotyp700", parent_genotyp700);
		mav.setViewName("genotyp700/viewGenotyp700.jsp");

		return mav;
	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@RequestMapping("/saveGenotyp700")
	public String saveGenotyp700(@ModelAttribute Genotyp700 genotyp700) {
		genotyp700Service.saveGenotyp700(genotyp700);
		return "forward:/indexGenotyp700";
	}

	/**
	 * Create a new Genotyp700 entity
	 * 
	 */
	@RequestMapping("/newGenotyp700")
	public ModelAndView newGenotyp700() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", new Genotyp700());
		mav.addObject("newFlag", true);
		mav.setViewName("genotyp700/editGenotyp700.jsp");

		return mav;
	}

	/**
	 * Show all Var700 entities by Genotyp700
	 * 
	 */
	@RequestMapping("/listGenotyp700Var700")
	public ModelAndView listGenotyp700Var700(@RequestParam Integer varIdKey, @RequestParam Integer snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyp700", genotyp700DAO.findGenotyp700ByPrimaryKey(varIdKey, snpIdKey));
		mav.setViewName("genotyp700/var700/listVar700.jsp");

		return mav;
	}
}