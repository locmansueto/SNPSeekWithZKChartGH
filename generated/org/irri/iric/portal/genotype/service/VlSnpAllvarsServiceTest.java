package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnpAllvars;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

/**
 * Class to run the service as a JUnit test. Each operation in the service is a separate test.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
		"file:./resources/iric_prod_crud-security-context.xml",
		"file:./resources/iric_prod_crud-service-context.xml",
		"file:./resources/iric_prod_crud-dao-context.xml",
		"file:./resources/iric_prod_crud-web-context.xml" })
@Transactional
public class VlSnpAllvarsServiceTest {

	/**
	 * The Spring application context.
	 *
	 */
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/**
	 * The service being tested, injected by Spring.
	 *
	 */
	@Autowired
	protected VlSnpAllvarsService service;

	/**
	 * Instantiates a new VlSnpAllvarsServiceTest.
	 *
	 */
	public VlSnpAllvarsServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VlSnpAllvars entity
	 * 
	 */
	@Test
	public void deleteVlSnpAllvars() {
		// TODO: JUnit - Populate test inputs for operation: deleteVlSnpAllvars 
		VlSnpAllvars vlsnpallvars = new org.irri.iric.portal.genotype.domain.VlSnpAllvars();
		service.deleteVlSnpAllvars(vlsnpallvars);
	}

	/**
	 * Operation Unit Test
	 * Return all VlSnpAllvars entity
	 * 
	 */
	@Test
	public void findAllVlSnpAllvarss() {
		// TODO: JUnit - Populate test inputs for operation: findAllVlSnpAllvarss 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VlSnpAllvars> response = null;
		response = service.findAllVlSnpAllvarss(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVlSnpAllvarss
	}

	/**
	 * Operation Unit Test
	 * Load an existing VlSnpAllvars entity
	 * 
	 */
	@Test
	public void loadVlSnpAllvarss() {
		Set<VlSnpAllvars> response = null;
		response = service.loadVlSnpAllvarss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVlSnpAllvarss
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVlSnpAllvarsByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVlSnpAllvarsByPrimaryKey 
		Integer snpGenotypeId = 0;
		VlSnpAllvars response = null;
		response = service.findVlSnpAllvarsByPrimaryKey(snpGenotypeId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVlSnpAllvarsByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VlSnpAllvars entity
	 * 
	 */
	@Test
	public void countVlSnpAllvarss() {
		Integer response = null;
		response = service.countVlSnpAllvarss();
		// TODO: JUnit - Add assertions to test outputs of operation: countVlSnpAllvarss
	}

	/**
	 * Operation Unit Test
	 * Save an existing VlSnpAllvars entity
	 * 
	 */
	@Test
	public void saveVlSnpAllvars() {
		// TODO: JUnit - Populate test inputs for operation: saveVlSnpAllvars 
		VlSnpAllvars vlsnpallvars_1 = new org.irri.iric.portal.genotype.domain.VlSnpAllvars();
		service.saveVlSnpAllvars(vlsnpallvars_1);
	}

	/**
	 * Autowired to set the Spring application context.
	 *
	 */
	@Autowired
	public void setContext(ApplicationContext context) {
		this.context = context;
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("session", new SessionScope());
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("request", new RequestScope());
	}

	/**
	 * Sets Up the Request context
	 *
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
	}
}
