package org.irri.iric.portal.variety.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.PhenotypesDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

import org.irri.iric.portal.variety.service.GermplasmService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Germplasm entities
 * 
 */

@Controller("GermplasmController")
public class GermplasmController {

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
	 * DAO injected by Spring that manages Phenotypes entities
	 * 
	 */
	@Autowired
	private PhenotypesDAO phenotypesDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Germplasm entities
	 * 
	 */
	@Autowired
	private GermplasmService germplasmService;

	/**
	 * Create a new Genotyping entity
	 * 
	 */
	@RequestMapping("/newGermplasmGenotypings")
	public ModelAndView newGermplasmGenotypings(@RequestParam Integer germplasm_nsftvId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("genotyping", new Genotyping());
		mav.addObject("newFlag", true);
		mav.setViewName("germplasm/genotypings/editGenotypings.jsp");

		return mav;
	}

	/**
	 * Show all Genotyping entities by Germplasm
	 * 
	 */
	@RequestMapping("/listGermplasmGenotypings")
	public ModelAndView listGermplasmGenotypings(@RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", germplasmDAO.findGermplasmByPrimaryKey(nsftvIdKey));
		mav.setViewName("germplasm/genotypings/listGenotypings.jsp");

		return mav;
	}

	/**
	 * Create a new Germplasm entity
	 * 
	 */
	@RequestMapping("/newGermplasm")
	public ModelAndView newGermplasm() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", new Germplasm());
		mav.addObject("newFlag", true);
		mav.setViewName("germplasm/editGermplasm.jsp");

		return mav;
	}

	/**
	 * Create a new Phenotypes entity
	 * 
	 */
	@RequestMapping("/newGermplasmPhenotypeses")
	public ModelAndView newGermplasmPhenotypeses(@RequestParam Integer germplasm_nsftvId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("phenotypes", new Phenotypes());
		mav.addObject("newFlag", true);
		mav.setViewName("germplasm/phenotypeses/editPhenotypeses.jsp");

		return mav;
	}

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/saveGermplasmPhenotypeses")
	public ModelAndView saveGermplasmPhenotypeses(@RequestParam Integer germplasm_nsftvId, @ModelAttribute Phenotypes phenotypeses) {
		Germplasm parent_germplasm = germplasmService.saveGermplasmPhenotypeses(germplasm_nsftvId, phenotypeses);

		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("germplasm", parent_germplasm);
		mav.setViewName("germplasm/viewGermplasm.jsp");

		return mav;
	}

	/**
	 * Select the child Genotyping entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGermplasmGenotypings")
	public ModelAndView confirmDeleteGermplasmGenotypings(@RequestParam Integer germplasm_nsftvId, @RequestParam String related_genotypings_snpId, @RequestParam Integer related_genotypings_nsftvId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("genotyping", genotypingDAO.findGenotypingByPrimaryKey(related_genotypings_snpId, related_genotypings_nsftvId));
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.setViewName("germplasm/genotypings/deleteGenotypings.jsp");

		return mav;
	}

	/**
	 * Show all Phenotypes entities by Germplasm
	 * 
	 */
	@RequestMapping("/listGermplasmPhenotypeses")
	public ModelAndView listGermplasmPhenotypeses(@RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", germplasmDAO.findGermplasmByPrimaryKey(nsftvIdKey));
		mav.setViewName("germplasm/phenotypeses/listPhenotypeses.jsp");

		return mav;
	}

	/**
	 * Edit an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/editGermplasmGenotypings")
	public ModelAndView editGermplasmGenotypings(@RequestParam Integer germplasm_nsftvId, @RequestParam String genotypings_snpId, @RequestParam Integer genotypings_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(genotypings_snpId, genotypings_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("genotyping", genotyping);
		mav.setViewName("germplasm/genotypings/editGenotypings.jsp");

		return mav;
	}

	/**
	 * Edit an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/editGermplasmPhenotypeses")
	public ModelAndView editGermplasmPhenotypeses(@RequestParam Integer germplasm_nsftvId, @RequestParam Integer phenotypeses_nsftvId, @RequestParam String phenotypeses_trait) {
		Phenotypes phenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(phenotypeses_nsftvId, phenotypeses_trait, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("phenotypes", phenotypes);
		mav.setViewName("germplasm/phenotypeses/editPhenotypeses.jsp");

		return mav;
	}

	/**
	 * View an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/selectGermplasmPhenotypeses")
	public ModelAndView selectGermplasmPhenotypeses(@RequestParam Integer germplasm_nsftvId, @RequestParam Integer phenotypeses_nsftvId, @RequestParam String phenotypeses_trait) {
		Phenotypes phenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(phenotypeses_nsftvId, phenotypeses_trait, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("phenotypes", phenotypes);
		mav.setViewName("germplasm/phenotypeses/viewPhenotypeses.jsp");

		return mav;
	}

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@RequestMapping("/deleteGermplasmPhenotypeses")
	public ModelAndView deleteGermplasmPhenotypeses(@RequestParam Integer germplasm_nsftvId, @RequestParam Integer related_phenotypeses_nsftvId, @RequestParam String related_phenotypeses_trait) {
		ModelAndView mav = new ModelAndView();

		Germplasm germplasm = germplasmService.deleteGermplasmPhenotypeses(germplasm_nsftvId, related_phenotypeses_nsftvId, related_phenotypeses_trait);

		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("germplasm", germplasm);
		mav.setViewName("germplasm/viewGermplasm.jsp");

		return mav;
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/deleteGermplasm")
	public String deleteGermplasm(@RequestParam Integer nsftvIdKey) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(nsftvIdKey);
		germplasmService.deleteGermplasm(germplasm);
		return "forward:/indexGermplasm";
	}

	/**
	 * Select an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/selectGermplasm")
	public ModelAndView selectGermplasm(@RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", germplasmDAO.findGermplasmByPrimaryKey(nsftvIdKey));
		mav.setViewName("germplasm/viewGermplasm.jsp");

		return mav;
	}

	/**
	 * Show all Germplasm entities
	 * 
	 */
	@RequestMapping("/indexGermplasm")
	public ModelAndView listGermplasms() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasms", germplasmService.loadGermplasms());

		mav.setViewName("germplasm/listGermplasms.jsp");

		return mav;
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/deleteGermplasmGenotypings")
	public ModelAndView deleteGermplasmGenotypings(@RequestParam Integer germplasm_nsftvId, @RequestParam String related_genotypings_snpId, @RequestParam Integer related_genotypings_nsftvId) {
		ModelAndView mav = new ModelAndView();

		Germplasm germplasm = germplasmService.deleteGermplasmGenotypings(germplasm_nsftvId, related_genotypings_snpId, related_genotypings_nsftvId);

		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("germplasm", germplasm);
		mav.setViewName("germplasm/viewGermplasm.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Germplasm entities
	 * 
	 */
	public String indexGermplasm() {
		return "redirect:/indexGermplasm";
	}

	/**
	 * Select the child Phenotypes entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGermplasmPhenotypeses")
	public ModelAndView confirmDeleteGermplasmPhenotypeses(@RequestParam Integer germplasm_nsftvId, @RequestParam Integer related_phenotypeses_nsftvId, @RequestParam String related_phenotypeses_trait) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("phenotypes", phenotypesDAO.findPhenotypesByPrimaryKey(related_phenotypeses_nsftvId, related_phenotypeses_trait));
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.setViewName("germplasm/phenotypeses/deletePhenotypeses.jsp");

		return mav;
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/saveGermplasmGenotypings")
	public ModelAndView saveGermplasmGenotypings(@RequestParam Integer germplasm_nsftvId, @ModelAttribute Genotyping genotypings) {
		Germplasm parent_germplasm = germplasmService.saveGermplasmGenotypings(germplasm_nsftvId, genotypings);

		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("germplasm", parent_germplasm);
		mav.setViewName("germplasm/viewGermplasm.jsp");

		return mav;
	}

	/**
	 * Edit an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/editGermplasm")
	public ModelAndView editGermplasm(@RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", germplasmDAO.findGermplasmByPrimaryKey(nsftvIdKey));
		mav.setViewName("germplasm/editGermplasm.jsp");

		return mav;
	}

	/**
	 * Select the Germplasm entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteGermplasm")
	public ModelAndView confirmDeleteGermplasm(@RequestParam Integer nsftvIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("germplasm", germplasmDAO.findGermplasmByPrimaryKey(nsftvIdKey));
		mav.setViewName("germplasm/deleteGermplasm.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/germplasmController/binary.action")
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
	 * Save an existing Germplasm entity
	 * 
	 */
	@RequestMapping("/saveGermplasm")
	public String saveGermplasm(@ModelAttribute Germplasm germplasm) {
		germplasmService.saveGermplasm(germplasm);
		return "forward:/indexGermplasm";
	}

	/**
	 * View an existing Genotyping entity
	 * 
	 */
	@RequestMapping("/selectGermplasmGenotypings")
	public ModelAndView selectGermplasmGenotypings(@RequestParam Integer germplasm_nsftvId, @RequestParam String genotypings_snpId, @RequestParam Integer genotypings_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(genotypings_snpId, genotypings_nsftvId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("germplasm_nsftvId", germplasm_nsftvId);
		mav.addObject("genotyping", genotyping);
		mav.setViewName("germplasm/genotypings/viewGenotypings.jsp");

		return mav;
	}
}