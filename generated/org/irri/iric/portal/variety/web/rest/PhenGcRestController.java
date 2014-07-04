package org.irri.iric.portal.variety.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.irri.iric.portal.variety.dao.PhenGcDAO;

import org.irri.iric.portal.variety.domain.PhenGc;

import org.irri.iric.portal.variety.service.PhenGcService;

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
 * Spring Rest controller that handles CRUD requests for PhenGc entities
 * 
 */

@Controller("PhenGcRestController")
public class PhenGcRestController {

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
	 * Create a new PhenGc entity
	 * 
	 */
	@RequestMapping(value = "/PhenGc", method = RequestMethod.POST)
	@ResponseBody
	public PhenGc newPhenGc(@RequestBody PhenGc phengc) {
		phenGcService.savePhenGc(phengc);
		return phenGcDAO.findPhenGcByPrimaryKey(phengc.getEntno(), phengc.getGid());
	}

	/**
	 * Delete an existing PhenGc entity
	 * 
	 */
	@RequestMapping(value = "/PhenGc/{phengc_entno}/{phengc_gid}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePhenGc(@PathVariable Integer phengc_entno, @PathVariable Integer phengc_gid) {
		PhenGc phengc = phenGcDAO.findPhenGcByPrimaryKey(phengc_entno, phengc_gid);
		phenGcService.deletePhenGc(phengc);
	}

	/**
	 * Show all PhenGc entities
	 * 
	 */
	@RequestMapping(value = "/PhenGc", method = RequestMethod.GET)
	@ResponseBody
	public List<PhenGc> listPhenGcs() {
		return new java.util.ArrayList<PhenGc>(phenGcService.loadPhenGcs());
	}

	/**
	 * Select an existing PhenGc entity
	 * 
	 */
	@RequestMapping(value = "/PhenGc/{phengc_entno}/{phengc_gid}", method = RequestMethod.GET)
	@ResponseBody
	public PhenGc loadPhenGc(@PathVariable Integer phengc_entno, @PathVariable Integer phengc_gid) {
		return phenGcDAO.findPhenGcByPrimaryKey(phengc_entno, phengc_gid);
	}

	/**
	 * Save an existing PhenGc entity
	 * 
	 */
	@RequestMapping(value = "/PhenGc", method = RequestMethod.PUT)
	@ResponseBody
	public PhenGc savePhenGc(@RequestBody PhenGc phengc) {
		phenGcService.savePhenGc(phengc);
		return phenGcDAO.findPhenGcByPrimaryKey(phengc.getEntno(), phengc.getGid());
	}
}