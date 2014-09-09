package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnp2vars;

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
public class VlSnp2varsServiceTest {

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
	protected VlSnp2varsService service;

	/**
	 * Instantiates a new VlSnp2varsServiceTest.
	 *
	 */
	public VlSnp2varsServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Load an existing VlSnp2vars entity
	 * 
	 */
	@Test
	public void loadVlSnp2varss() {
		Set<VlSnp2vars> response = null;
		response = service.loadVlSnp2varss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVlSnp2varss
	}

	/**
	 * Operation Unit Test
	 * Return all VlSnp2vars entity
	 * 
	 */
	@Test
	public void findAllVlSnp2varss() {
		// TODO: JUnit - Populate test inputs for operation: findAllVlSnp2varss 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VlSnp2vars> response = null;
		response = service.findAllVlSnp2varss(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVlSnp2varss
	}

	/**
	 * Operation Unit Test
	 * Save an existing VlSnp2vars entity
	 * 
	 */
	@Test
	public void saveVlSnp2vars() {
		// TODO: JUnit - Populate test inputs for operation: saveVlSnp2vars 
		VlSnp2vars vlsnp2vars = new org.irri.iric.portal.genotype.domain.VlSnp2vars();
		service.saveVlSnp2vars(vlsnp2vars);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VlSnp2vars entity
	 * 
	 */
	@Test
	public void deleteVlSnp2vars() {
		// TODO: JUnit - Populate test inputs for operation: deleteVlSnp2vars 
		VlSnp2vars vlsnp2vars_1 = new org.irri.iric.portal.genotype.domain.VlSnp2vars();
		service.deleteVlSnp2vars(vlsnp2vars_1);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVlSnp2varsByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVlSnp2varsByPrimaryKey 
		Integer var1 = 0;
		Integer var2 = 0;
		Integer snpFeatureId = 0;
		VlSnp2vars response = null;
		response = service.findVlSnp2varsByPrimaryKey(var1, var2, snpFeatureId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVlSnp2varsByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VlSnp2vars entity
	 * 
	 */
	@Test
	public void countVlSnp2varss() {
		Integer response = null;
		response = service.countVlSnp2varss();
		// TODO: JUnit - Add assertions to test outputs of operation: countVlSnp2varss
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
