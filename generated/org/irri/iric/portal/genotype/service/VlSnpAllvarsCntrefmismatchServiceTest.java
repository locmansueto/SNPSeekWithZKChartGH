package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch;

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
public class VlSnpAllvarsCntrefmismatchServiceTest {

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
	protected VlSnpAllvarsCntrefmismatchService service;

	/**
	 * Instantiates a new VlSnpAllvarsCntrefmismatchServiceTest.
	 *
	 */
	public VlSnpAllvarsCntrefmismatchServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Save an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Test
	public void saveVlSnpAllvarsCntrefmismatch() {
		// TODO: JUnit - Populate test inputs for operation: saveVlSnpAllvarsCntrefmismatch 
		VlSnpAllvarsCntrefmismatch vlsnpallvarscntrefmismatch = new org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch();
		service.saveVlSnpAllvarsCntrefmismatch(vlsnpallvarscntrefmismatch);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Test
	public void countVlSnpAllvarsCntrefmismatchs() {
		Integer response = null;
		response = service.countVlSnpAllvarsCntrefmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: countVlSnpAllvarsCntrefmismatchs
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVlSnpAllvarsCntrefmismatchByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVlSnpAllvarsCntrefmismatchByPrimaryKey 
		Integer var = 0;
		VlSnpAllvarsCntrefmismatch response = null;
		response = service.findVlSnpAllvarsCntrefmismatchByPrimaryKey(var);
		// TODO: JUnit - Add assertions to test outputs of operation: findVlSnpAllvarsCntrefmismatchByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Test
	public void deleteVlSnpAllvarsCntrefmismatch() {
		// TODO: JUnit - Populate test inputs for operation: deleteVlSnpAllvarsCntrefmismatch 
		VlSnpAllvarsCntrefmismatch vlsnpallvarscntrefmismatch_1 = new org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch();
		service.deleteVlSnpAllvarsCntrefmismatch(vlsnpallvarscntrefmismatch_1);
	}

	/**
	 * Operation Unit Test
	 * Return all VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Test
	public void findAllVlSnpAllvarsCntrefmismatchs() {
		// TODO: JUnit - Populate test inputs for operation: findAllVlSnpAllvarsCntrefmismatchs 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VlSnpAllvarsCntrefmismatch> response = null;
		response = service.findAllVlSnpAllvarsCntrefmismatchs(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVlSnpAllvarsCntrefmismatchs
	}

	/**
	 * Operation Unit Test
	 * Load an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Test
	public void loadVlSnpAllvarsCntrefmismatchs() {
		Set<VlSnpAllvarsCntrefmismatch> response = null;
		response = service.loadVlSnpAllvarsCntrefmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVlSnpAllvarsCntrefmismatchs
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
