package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvars;

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
public class VSnpAllvarsServiceTest {

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
	protected VSnpAllvarsService service;

	/**
	 * Instantiates a new VSnpAllvarsServiceTest.
	 *
	 */
	public VSnpAllvarsServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VSnpAllvars entity
	 * 
	 */
	@Test
	public void deleteVSnpAllvars() {
		// TODO: JUnit - Populate test inputs for operation: deleteVSnpAllvars 
		VSnpAllvars vsnpallvars = new org.irri.iric.portal.chado.domain.VSnpAllvars();
		service.deleteVSnpAllvars(vsnpallvars);
	}

	/**
	 * Operation Unit Test
	 * Return all VSnpAllvars entity
	 * 
	 */
	@Test
	public void findAllVSnpAllvarss() {
		// TODO: JUnit - Populate test inputs for operation: findAllVSnpAllvarss 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VSnpAllvars> response = null;
		response = service.findAllVSnpAllvarss(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVSnpAllvarss
	}

	/**
	 * Operation Unit Test
	 * Save an existing VSnpAllvars entity
	 * 
	 */
	@Test
	public void saveVSnpAllvars() {
		// TODO: JUnit - Populate test inputs for operation: saveVSnpAllvars 
		VSnpAllvars vsnpallvars_1 = new org.irri.iric.portal.chado.domain.VSnpAllvars();
		service.saveVSnpAllvars(vsnpallvars_1);
	}

	/**
	 * Operation Unit Test
	 * Load an existing VSnpAllvars entity
	 * 
	 */
	@Test
	public void loadVSnpAllvarss() {
		Set<VSnpAllvars> response = null;
		response = service.loadVSnpAllvarss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVSnpAllvarss
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVSnpAllvarsByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVSnpAllvarsByPrimaryKey 
		Integer snpGenotypeId = 0;
		VSnpAllvars response = null;
		response = service.findVSnpAllvarsByPrimaryKey(snpGenotypeId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVSnpAllvarsByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VSnpAllvars entity
	 * 
	 */
	@Test
	public void countVSnpAllvarss() {
		Integer response = null;
		response = service.countVSnpAllvarss();
		// TODO: JUnit - Add assertions to test outputs of operation: countVSnpAllvarss
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
