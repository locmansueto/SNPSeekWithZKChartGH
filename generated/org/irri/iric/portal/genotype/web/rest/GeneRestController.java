package org.irri.iric.portal.genotype.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.genotype.dao.GeneDAO;

import org.irri.iric.portal.genotype.domain.Gene;

import org.irri.iric.portal.genotype.service.GeneService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Rest controller that handles CRUD requests for Gene entities
 * 
 */

@Controller("GeneRestController")
public class GeneRestController {

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
	 * Show all Gene entities
	 * 
	 */
	@RequestMapping(value = "/Gene", method = RequestMethod.GET)
	@ResponseBody
	public List<Gene> listGenes() {
		return new java.util.ArrayList<Gene>(geneService.loadGenes());
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
	 * Select an existing Gene entity
	 * 
	 */
	@RequestMapping(value = "/Gene/{gene_featureId}", method = RequestMethod.GET)
	@ResponseBody
	public Gene loadGene(@PathVariable Integer gene_featureId) {
		return geneDAO.findGeneByPrimaryKey(gene_featureId);
	}

	/**
	 * Delete an existing Gene entity
	 * 
	 */
	@RequestMapping(value = "/Gene/{gene_featureId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGene(@PathVariable Integer gene_featureId) {
		Gene gene = geneDAO.findGeneByPrimaryKey(gene_featureId);
		geneService.deleteGene(gene);
	}

	/**
	 * Create a new Gene entity
	 * 
	 */
	@RequestMapping(value = "/Gene", method = RequestMethod.POST)
	@ResponseBody
	public Gene newGene(@RequestBody Gene gene) {
		geneService.saveGene(gene);
		return geneDAO.findGeneByPrimaryKey(gene.getFeatureId());
	}

	/**
	 * Save an existing Gene entity
	 * 
	 */
	@RequestMapping(value = "/Gene", method = RequestMethod.PUT)
	@ResponseBody
	public Gene saveGene(@RequestBody Gene gene) {
		geneService.saveGene(gene);
		return geneDAO.findGeneByPrimaryKey(gene.getFeatureId());
	}
}