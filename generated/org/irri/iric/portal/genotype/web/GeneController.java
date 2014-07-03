package org.irri.iric.portal.genotype.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.genotype.dao.GeneDAO;

import org.irri.iric.portal.genotype.domain.Gene;

import org.irri.iric.portal.genotype.service.GeneService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Gene entities
 * 
 */

@Controller("GeneController")
public class GeneController {

	/**
	 * DAO injected by Spring that manages Gene entities
	 * 
	 */
	@Autowired
	private GeneDAO geneDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Gene entities
	 * 
	 */
	@Autowired
	private GeneService geneService;

	/**
	 * Entry point to show all Gene entities
	 * 
	 */
	public String indexGene() {
		return "redirect:/indexGene";
	}

	/**
	 * Delete an existing Gene entity
	 * 
	 */
	@RequestMapping("/deleteGene")
	public String deleteGene(@RequestParam Integer featureIdKey) {
		Gene gene = geneDAO.findGeneByPrimaryKey(featureIdKey);
		geneService.deleteGene(gene);
		return "forward:/indexGene";
	}

	/**
	 * Select an existing Gene entity
	 * 
	 */
	@RequestMapping("/selectGene")
	public ModelAndView selectGene(@RequestParam Integer featureIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("gene", geneDAO.findGeneByPrimaryKey(featureIdKey));
		mav.setViewName("gene/viewGene.jsp");

		return mav;
	}

	/**
	 * Create a new Gene entity
	 * 
	 */
	@RequestMapping("/newGene")
	public ModelAndView newGene() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("gene", new Gene());
		mav.addObject("newFlag", true);
		mav.setViewName("gene/editGene.jsp");

		return mav;
	}

	/**
	 * Select the Gene entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGene")
	public ModelAndView confirmDeleteGene(@RequestParam Integer featureIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("gene", geneDAO.findGeneByPrimaryKey(featureIdKey));
		mav.setViewName("gene/deleteGene.jsp");

		return mav;
	}

	/**
	 * Edit an existing Gene entity
	 * 
	 */
	@RequestMapping("/editGene")
	public ModelAndView editGene(@RequestParam Integer featureIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("gene", geneDAO.findGeneByPrimaryKey(featureIdKey));
		mav.setViewName("gene/editGene.jsp");

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
	 */
	@RequestMapping("/geneController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Save an existing Gene entity
	 * 
	 */
	@RequestMapping("/saveGene")
	public String saveGene(@ModelAttribute Gene gene) {
		geneService.saveGene(gene);
		return "forward:/indexGene";
	}

	/**
	 * Show all Gene entities
	 * 
	 */
	@RequestMapping("/indexGene")
	public ModelAndView listGenes() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genes", geneService.loadGenes());

		mav.setViewName("gene/listGenes.jsp");

		return mav;
	}
}