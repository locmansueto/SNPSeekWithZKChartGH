package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.PhenotypesDAO;

import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

import org.irri.iric.portal.variety.service.PhenotypesService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Phenotypes entities
 * 
 */

@Controller("PhenotypesController")
public class PhenotypesController {

	/**
	 * DAO injected by Spring that manages Germplasm entities
	 * 
	 */
	@Autowired
	private GermplasmDAO germplasmDAO;

	/**
	 * DAO injected by Spring that manages Phenotypes entities
	 * 
	 */
	@Autowired
	private PhenotypesDAO phenotypesDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Phenotypes entities
	 * 
	 */
	@Autowired
	private PhenotypesService phenotypesService;

	/**
	 * Select the child Germplasm entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePhenotypesGermplasm")
	public ModelAndView confirmDeletePhenotypesGermplasm(@RequestParam Integer phenotypes_nsftvId, @RequestParam String phenotypes_trait, @RequestParam Integer related_germplasm_nsftvId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", germplasmDAO.findGermplasmByPrimaryKey(related_germplasm_nsftvId));
		mav.addObject("phenotypes_nsftvId", phenotypes_nsftvId);
		mav.addObject("phenotypes_trait", phenotypes_trait);
		mav.setViewName("phenotypes/germplasm/deleteGermplasm.jsp");

		return mav;
	}

	/**
	 * Select the Phenotypes entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePhenotypes")
	public ModelAndView confirmDeletePhenotypes(@RequestParam Integer nsftvIdKey, @RequestParam String traitKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phenotypes", phenotypesDAO.findPhenotypesByPrimaryKey(nsftvIdKey, traitKey));
		mav.setViewName("phenotypes/deletePhenotypes.jsp");

		return mav;
	}

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/savePhenotypes")
	public String savePhenotypes(@ModelAttribute Phenotypes phenotypes) {
		phenotypesService.savePhenotypes(phenotypes);
		return "forward:/indexPhenotypes";
	}

	/**
	 * Edit an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/editPhenotypes")
	public ModelAndView editPhenotypes(@RequestParam Integer nsftvIdKey, @RequestParam String traitKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phenotypes", phenotypesDAO.findPhenotypesByPrimaryKey(nsftvIdKey, traitKey));
		mav.setViewName("phenotypes/editPhenotypes.jsp");

		return mav;
	}

	/**
	 * Create a new Phenotypes entity
	 * 
	 */
	@RequestMapping("/newPhenotypes")
	public ModelAndView newPhenotypes() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phenotypes", new Phenotypes());
		mav.addObject("newFlag", true);
		mav.setViewName("phenotypes/editPhenotypes.jsp");

		return mav;
	}

	/**
	 * Show all Germplasm entities by Phenotypes
	 * 
	 */
	@RequestMapping("/listPhenotypesGermplasm")
	public ModelAndView listPhenotypesGermplasm(@RequestParam Integer nsftvIdKey, @RequestParam String traitKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phenotypes", phenotypesDAO.findPhenotypesByPrimaryKey(nsftvIdKey, traitKey));
		mav.setViewName("phenotypes/germplasm/listGermplasm.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Phenotypes entities
	 * 
	 */
	public String indexPhenotypes() {
		return "redirect:/indexPhenotypes";
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/deletePhenotypesGermplasm")
	public ModelAndView deletePhenotypesGermplasm(@RequestParam Integer phenotypes_nsftvId, @RequestParam String phenotypes_trait, @RequestParam Integer related_germplasm_nsftvId) {
		ModelAndView mav = new ModelAndView();

		Phenotypes phenotypes = phenotypesService.deletePhenotypesGermplasm(phenotypes_nsftvId, phenotypes_trait, related_germplasm_nsftvId);

		mav.addObject("phenotypes_nsftvId", phenotypes_nsftvId);
		mav.addObject("phenotypes_trait", phenotypes_trait);
		mav.addObject("phenotypes", phenotypes);
		mav.setViewName("phenotypes/viewPhenotypes.jsp");

		return mav;
	}

	/**
	 * Show all Phenotypes entities
	 * 
	 */
	@RequestMapping("/indexPhenotypes")
	public ModelAndView listPhenotypess() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phenotypess", phenotypesService.loadPhenotypess());

		mav.setViewName("phenotypes/listPhenotypess.jsp");

		return mav;
	}

	/**
	 * Edit an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/editPhenotypesGermplasm")
	public ModelAndView editPhenotypesGermplasm(@RequestParam Integer phenotypes_nsftvId, @RequestParam String phenotypes_trait, @RequestParam Integer germplasm_nsftvId) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("phenotypes_nsftvId", phenotypes_nsftvId);
		mav.addObject("phenotypes_trait", phenotypes_trait);
		mav.addObject("germplasm", germplasm);
		mav.setViewName("phenotypes/germplasm/editGermplasm.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/phenotypesController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * View an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/selectPhenotypesGermplasm")
	public ModelAndView selectPhenotypesGermplasm(@RequestParam Integer phenotypes_nsftvId, @RequestParam String phenotypes_trait, @RequestParam Integer germplasm_nsftvId) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("phenotypes_nsftvId", phenotypes_nsftvId);
		mav.addObject("phenotypes_trait", phenotypes_trait);
		mav.addObject("germplasm", germplasm);
		mav.setViewName("phenotypes/germplasm/viewGermplasm.jsp");

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
	 * Create a new Germplasm entity
	 * 
	 */
	@RequestMapping("/newPhenotypesGermplasm")
	public ModelAndView newPhenotypesGermplasm(@RequestParam Integer phenotypes_nsftvId, @RequestParam String phenotypes_trait) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("phenotypes_nsftvId", phenotypes_nsftvId);
		mav.addObject("phenotypes_trait", phenotypes_trait);
		mav.addObject("germplasm", new Germplasm());
		mav.addObject("newFlag", true);
		mav.setViewName("phenotypes/germplasm/editGermplasm.jsp");

		return mav;
	}

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/savePhenotypesGermplasm")
	public ModelAndView savePhenotypesGermplasm(@RequestParam Integer phenotypes_nsftvId, @RequestParam String phenotypes_trait, @ModelAttribute Germplasm germplasm) {
		Phenotypes parent_phenotypes = phenotypesService.savePhenotypesGermplasm(phenotypes_nsftvId, phenotypes_trait, germplasm);

		ModelAndView mav = new ModelAndView();
		mav.addObject("phenotypes_nsftvId", phenotypes_nsftvId);
		mav.addObject("phenotypes_trait", phenotypes_trait);
		mav.addObject("phenotypes", parent_phenotypes);
		mav.setViewName("phenotypes/viewPhenotypes.jsp");

		return mav;
	}

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/deletePhenotypes")
	public String deletePhenotypes(@RequestParam Integer nsftvIdKey, @RequestParam String traitKey) {
		Phenotypes phenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(nsftvIdKey, traitKey);
		phenotypesService.deletePhenotypes(phenotypes);
		return "forward:/indexPhenotypes";
	}

	/**
	 * Select an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/selectPhenotypes")
	public ModelAndView selectPhenotypes(@RequestParam Integer nsftvIdKey, @RequestParam String traitKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phenotypes", phenotypesDAO.findPhenotypesByPrimaryKey(nsftvIdKey, traitKey));
		mav.setViewName("phenotypes/viewPhenotypes.jsp");

		return mav;
	}
}