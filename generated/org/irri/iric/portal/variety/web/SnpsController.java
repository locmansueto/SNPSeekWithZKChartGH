package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.SnpsDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Snps;

import org.irri.iric.portal.variety.service.SnpsService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Snps entities
 * 
 */

@Controller("SnpsController")
public class SnpsController {

	/**
	 * DAO injected by Spring that manages Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingDAO genotypingDAO;

	/**
	 * DAO injected by Spring that manages Snps entities
	 * 
	 */
	@Autowired
	private SnpsDAO snpsDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Snps entities
	 * 
	 */
	@Autowired
	private SnpsService snpsService;

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/deleteSnpsGenotypings")
	public ModelAndView deleteSnpsGenotypings(@RequestParam String snps_snpId, @RequestParam String related_genotypings_snpId, @RequestParam Integer related_genotypings_nsftvId) {
		ModelAndView mav = new ModelAndView();

		Snps snps = snpsService.deleteSnpsGenotypings(snps_snpId, related_genotypings_snpId, related_genotypings_nsftvId);

		mav.addObject("snps_snpId", snps_snpId);
		mav.addObject("snps", snps);
		mav.setViewName("snps/viewSnps.jsp");

		return mav;
	}

	/**
	 * Edit an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/editSnpsGenotypings")
	public ModelAndView editSnpsGenotypings(@RequestParam String snps_snpId, @RequestParam String genotypings_snpId, @RequestParam Integer genotypings_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(genotypings_snpId, genotypings_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snps_snpId", snps_snpId);
		mav.addObject("genotyping", genotyping);
		mav.setViewName("snps/genotypings/editGenotypings.jsp");

		return mav;
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/saveSnpsGenotypings")
	public ModelAndView saveSnpsGenotypings(@RequestParam String snps_snpId, @ModelAttribute Genotyping genotypings) {
		Snps parent_snps = snpsService.saveSnpsGenotypings(snps_snpId, genotypings);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snps_snpId", snps_snpId);
		mav.addObject("snps", parent_snps);
		mav.setViewName("snps/viewSnps.jsp");

		return mav;
	}

	/**
	 * Select the Snps entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnps")
	public ModelAndView confirmDeleteSnps(@RequestParam String snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snps", snpsDAO.findSnpsByPrimaryKey(snpIdKey));
		mav.setViewName("snps/deleteSnps.jsp");

		return mav;
	}

	/**
	 * Create a new Genotyping entity
	 * 
	 */
	@RequestMapping("/newSnpsGenotypings")
	public ModelAndView newSnpsGenotypings(@RequestParam String snps_snpId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("snps_snpId", snps_snpId);
		mav.addObject("genotyping", new Genotyping());
		mav.addObject("newFlag", true);
		mav.setViewName("snps/genotypings/editGenotypings.jsp");

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
	 * Show all Genotyping entities by Snps
	 * 
	 */
	@RequestMapping("/listSnpsGenotypings")
	public ModelAndView listSnpsGenotypings(@RequestParam String snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snps", snpsDAO.findSnpsByPrimaryKey(snpIdKey));
		mav.setViewName("snps/genotypings/listGenotypings.jsp");

		return mav;
	}

	/**
	 * Create a new Snps entity
	 * 
	 */
	@RequestMapping("/newSnps")
	public ModelAndView newSnps() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snps", new Snps());
		mav.addObject("newFlag", true);
		mav.setViewName("snps/editSnps.jsp");

		return mav;
	}

	/**
	 * Select the child Genotyping entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnpsGenotypings")
	public ModelAndView confirmDeleteSnpsGenotypings(@RequestParam String snps_snpId, @RequestParam String related_genotypings_snpId, @RequestParam Integer related_genotypings_nsftvId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", genotypingDAO.findGenotypingByPrimaryKey(related_genotypings_snpId, related_genotypings_nsftvId));
		mav.addObject("snps_snpId", snps_snpId);
		mav.setViewName("snps/genotypings/deleteGenotypings.jsp");

		return mav;
	}

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	@RequestMapping("/deleteSnps")
	public String deleteSnps(@RequestParam String snpIdKey) {
		Snps snps = snpsDAO.findSnpsByPrimaryKey(snpIdKey);
		snpsService.deleteSnps(snps);
		return "forward:/indexSnps";
	}

	/**
	 * Show all Snps entities
	 * 
	 */
	@RequestMapping("/indexSnps")
	public ModelAndView listSnpss() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snpss", snpsService.loadSnpss());

		mav.setViewName("snps/listSnpss.jsp");

		return mav;
	}

	/**
	 * Edit an existing Snps entity
	 * 
	 */
	@RequestMapping("/editSnps")
	public ModelAndView editSnps(@RequestParam String snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snps", snpsDAO.findSnpsByPrimaryKey(snpIdKey));
		mav.setViewName("snps/editSnps.jsp");

		return mav;
	}

	/**
	 * Save an existing Snps entity
	 * 
	 */
	@RequestMapping("/saveSnps")
	public String saveSnps(@ModelAttribute Snps snps) {
		snpsService.saveSnps(snps);
		return "forward:/indexSnps";
	}

	/**
	 * View an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/selectSnpsGenotypings")
	public ModelAndView selectSnpsGenotypings(@RequestParam String snps_snpId, @RequestParam String genotypings_snpId, @RequestParam Integer genotypings_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(genotypings_snpId, genotypings_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snps_snpId", snps_snpId);
		mav.addObject("genotyping", genotyping);
		mav.setViewName("snps/genotypings/viewGenotypings.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/snpsController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Entry point to show all Snps entities
	 * 
	 */
	public String indexSnps() {
		return "redirect:/indexSnps";
	}

	/**
	 * Select an existing Snps entity
	 * 
	 */
	@RequestMapping("/selectSnps")
	public ModelAndView selectSnps(@RequestParam String snpIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snps", snpsDAO.findSnpsByPrimaryKey(snpIdKey));
		mav.setViewName("snps/viewSnps.jsp");

		return mav;
	}
}