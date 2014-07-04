package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.SnpsDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Snps;

import org.irri.iric.portal.variety.service.GenotypingService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Genotyping entities
 * 
 */

@Controller("GenotypingController")
public class GenotypingController {

	/**
	 * DAO injected by Spring that manages Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingDAO genotypingDAO;

	/**
	 * DAO injected by Spring that manages Germplasm entities
	 * 
	 */
	@Autowired
	private GermplasmDAO germplasmDAO;

	/**
	 * DAO injected by Spring that manages Snps entities
	 * 
	 */
	@Autowired
	private SnpsDAO snpsDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingService genotypingService;

	/**
	 * Edit an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/editGenotyping")
	public ModelAndView editGenotyping(@RequestParam String snpIdKey, @RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", genotypingDAO.findGenotypingByPrimaryKey(snpIdKey, nsftvIdKey));
		mav.setViewName("genotyping/editGenotyping.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/genotypingController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * View an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/selectGenotypingGermplasm")
	public ModelAndView selectGenotypingGermplasm(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam Integer germplasm_nsftvId) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("germplasm", germplasm);
		mav.setViewName("genotyping/germplasm/viewGermplasm.jsp");

		return mav;
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/deleteGenotyping")
	public String deleteGenotyping(@RequestParam String snpIdKey, @RequestParam Integer nsftvIdKey) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(snpIdKey, nsftvIdKey);
		genotypingService.deleteGenotyping(genotyping);
		return "forward:/indexGenotyping";
	}

	/**
	 * Edit an existing Snps entity
	 * 
	 */
	@RequestMapping("/editGenotypingSnps")
	public ModelAndView editGenotypingSnps(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam String snps_snpId) {
		Snps snps = snpsDAO.findSnpsByPrimaryKey(snps_snpId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("snps", snps);
		mav.setViewName("genotyping/snps/editSnps.jsp");

		return mav;
	}

	/**
	 * Edit an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/editGenotypingGermplasm")
	public ModelAndView editGenotypingGermplasm(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam Integer germplasm_nsftvId) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("germplasm", germplasm);
		mav.setViewName("genotyping/germplasm/editGermplasm.jsp");

		return mav;
	}

	/**
	 * Create a new Genotyping entity
	 * 
	 */
	@RequestMapping("/newGenotyping")
	public ModelAndView newGenotyping() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", new Genotyping());
		mav.addObject("newFlag", true);
		mav.setViewName("genotyping/editGenotyping.jsp");

		return mav;
	}

	/**
	 * Create a new Snps entity
	 * 
	 */
	@RequestMapping("/newGenotypingSnps")
	public ModelAndView newGenotypingSnps(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("snps", new Snps());
		mav.addObject("newFlag", true);
		mav.setViewName("genotyping/snps/editSnps.jsp");

		return mav;
	}

	/**
	 * Show all Germplasm entities by Genotyping
	 * 
	 */
	@RequestMapping("/listGenotypingGermplasm")
	public ModelAndView listGenotypingGermplasm(@RequestParam String snpIdKey, @RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", genotypingDAO.findGenotypingByPrimaryKey(snpIdKey, nsftvIdKey));
		mav.setViewName("genotyping/germplasm/listGermplasm.jsp");

		return mav;
	}

	/**
	 * Show all Snps entities by Genotyping
	 * 
	 */
	@RequestMapping("/listGenotypingSnps")
	public ModelAndView listGenotypingSnps(@RequestParam String snpIdKey, @RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", genotypingDAO.findGenotypingByPrimaryKey(snpIdKey, nsftvIdKey));
		mav.setViewName("genotyping/snps/listSnps.jsp");

		return mav;
	}

	/**
	 * Show all Genotyping entities
	 * 
	 */
	@RequestMapping("/indexGenotyping")
	public ModelAndView listGenotypings() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotypings", genotypingService.loadGenotypings());

		mav.setViewName("genotyping/listGenotypings.jsp");

		return mav;
	}

	/**
	 * Select the child Germplasm entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGenotypingGermplasm")
	public ModelAndView confirmDeleteGenotypingGermplasm(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam Integer related_germplasm_nsftvId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", germplasmDAO.findGermplasmByPrimaryKey(related_germplasm_nsftvId));
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.setViewName("genotyping/germplasm/deleteGermplasm.jsp");

		return mav;
	}

	/**
	 * Select the child Snps entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGenotypingSnps")
	public ModelAndView confirmDeleteGenotypingSnps(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam String related_snps_snpId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snps", snpsDAO.findSnpsByPrimaryKey(related_snps_snpId));
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.setViewName("genotyping/snps/deleteSnps.jsp");

		return mav;
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/saveGenotyping")
	public String saveGenotyping(@ModelAttribute Genotyping genotyping) {
		genotypingService.saveGenotyping(genotyping);
		return "forward:/indexGenotyping";
	}

	/**
	 * Entry point to show all Genotyping entities
	 * 
	 */
	public String indexGenotyping() {
		return "redirect:/indexGenotyping";
	}

	/**
	 * Create a new Germplasm entity
	 * 
	 */
	@RequestMapping("/newGenotypingGermplasm")
	public ModelAndView newGenotypingGermplasm(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("germplasm", new Germplasm());
		mav.addObject("newFlag", true);
		mav.setViewName("genotyping/germplasm/editGermplasm.jsp");

		return mav;
	}

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	@RequestMapping("/deleteGenotypingSnps")
	public ModelAndView deleteGenotypingSnps(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam String related_snps_snpId) {
		ModelAndView mav = new ModelAndView();

		Genotyping genotyping = genotypingService.deleteGenotypingSnps(genotyping_snpId, genotyping_nsftvId, related_snps_snpId);

		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("genotyping", genotyping);
		mav.setViewName("genotyping/viewGenotyping.jsp");

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
	 * Save an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/saveGenotypingGermplasm")
	public ModelAndView saveGenotypingGermplasm(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @ModelAttribute Germplasm germplasm) {
		Genotyping parent_genotyping = genotypingService.saveGenotypingGermplasm(genotyping_snpId, genotyping_nsftvId, germplasm);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("genotyping", parent_genotyping);
		mav.setViewName("genotyping/viewGenotyping.jsp");

		return mav;
	}

	/**
	 * Select an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/selectGenotyping")
	public ModelAndView selectGenotyping(@RequestParam String snpIdKey, @RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", genotypingDAO.findGenotypingByPrimaryKey(snpIdKey, nsftvIdKey));
		mav.setViewName("genotyping/viewGenotyping.jsp");

		return mav;
	}

	/**
	 * Save an existing Snps entity
	 * 
	 */
	@RequestMapping("/saveGenotypingSnps")
	public ModelAndView saveGenotypingSnps(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @ModelAttribute Snps snps) {
		Genotyping parent_genotyping = genotypingService.saveGenotypingSnps(genotyping_snpId, genotyping_nsftvId, snps);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("genotyping", parent_genotyping);
		mav.setViewName("genotyping/viewGenotyping.jsp");

		return mav;
	}

	/**
	 * Select the Genotyping entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGenotyping")
	public ModelAndView confirmDeleteGenotyping(@RequestParam String snpIdKey, @RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", genotypingDAO.findGenotypingByPrimaryKey(snpIdKey, nsftvIdKey));
		mav.setViewName("genotyping/deleteGenotyping.jsp");

		return mav;
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/deleteGenotypingGermplasm")
	public ModelAndView deleteGenotypingGermplasm(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam Integer related_germplasm_nsftvId) {
		ModelAndView mav = new ModelAndView();

		Genotyping genotyping = genotypingService.deleteGenotypingGermplasm(genotyping_snpId, genotyping_nsftvId, related_germplasm_nsftvId);

		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("genotyping", genotyping);
		mav.setViewName("genotyping/viewGenotyping.jsp");

		return mav;
	}

	/**
	 * View an existing Snps entity
	 * 
	 */
	@RequestMapping("/selectGenotypingSnps")
	public ModelAndView selectGenotypingSnps(@RequestParam String genotyping_snpId, @RequestParam Integer genotyping_nsftvId, @RequestParam String snps_snpId) {
		Snps snps = snpsDAO.findSnpsByPrimaryKey(snps_snpId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("genotyping_snpId", genotyping_snpId);
		mav.addObject("genotyping_nsftvId", genotyping_nsftvId);
		mav.addObject("snps", snps);
		mav.setViewName("genotyping/snps/viewSnps.jsp");

		return mav;
	}
}