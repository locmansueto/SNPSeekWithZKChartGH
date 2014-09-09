package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;

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
public class VSnpAllvarsCountrefmismatchServiceTest {

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
	protected VSnpAllvarsCountrefmismatchService service;

	/**
	 * Instantiates a new VSnpAllvarsCountrefmismatchServiceTest.
	 *
	 */
	public VSnpAllvarsCountrefmismatchServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return all VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Test
	public void findAllVSnpAllvarsCountrefmismatchs() {
		// TODO: JUnit - Populate test inputs for operation: findAllVSnpAllvarsCountrefmismatchs 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VSnpAllvarsCountrefmismatch> response = null;
		response = service.findAllVSnpAllvarsCountrefmismatchs(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVSnpAllvarsCountrefmismatchs
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Test
	public void deleteVSnpAllvarsCountrefmismatch() {
		// TODO: JUnit - Populate test inputs for operation: deleteVSnpAllvarsCountrefmismatch 
		VSnpAllvarsCountrefmismatch vsnpallvarscountrefmismatch = new org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch();
		service.deleteVSnpAllvarsCountrefmismatch(vsnpallvarscountrefmismatch);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Test
	public void countVSnpAllvarsCountrefmismatchs() {
		Integer response = null;
		response = service.countVSnpAllvarsCountrefmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: countVSnpAllvarsCountrefmismatchs
	}

	/**
	 * Operation Unit Test
	 * Load an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Test
	public void loadVSnpAllvarsCountrefmismatchs() {
		Set<VSnpAllvarsCountrefmismatch> response = null;
		response = service.loadVSnpAllvarsCountrefmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVSnpAllvarsCountrefmismatchs
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVSnpAllvarsCountrefmismatchByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVSnpAllvarsCountrefmismatchByPrimaryKey 
		Integer var = 0;
		VSnpAllvarsCountrefmismatch response = null;
		response = service.findVSnpAllvarsCountrefmismatchByPrimaryKey(var);
		// TODO: JUnit - Add assertions to test outputs of operation: findVSnpAllvarsCountrefmismatchByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Test
	public void saveVSnpAllvarsCountrefmismatch() {
		// TODO: JUnit - Populate test inputs for operation: saveVSnpAllvarsCountrefmismatch 
		VSnpAllvarsCountrefmismatch vsnpallvarscountrefmismatch_1 = new org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch();
		service.saveVSnpAllvarsCountrefmismatch(vsnpallvarscountrefmismatch_1);
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
