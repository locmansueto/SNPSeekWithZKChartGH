package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnp2vars;

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
public class VSnp2varsServiceTest {

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
	protected VSnp2varsService service;

	/**
	 * Instantiates a new VSnp2varsServiceTest.
	 *
	 */
	public VSnp2varsServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VSnp2vars entity
	 * 
	 */
	@Test
	public void countVSnp2varss() {
		Integer response = null;
		response = service.countVSnp2varss();
		// TODO: JUnit - Add assertions to test outputs of operation: countVSnp2varss
	}

	/**
	 * Operation Unit Test
	 * Load an existing VSnp2vars entity
	 * 
	 */
	@Test
	public void loadVSnp2varss() {
		Set<VSnp2vars> response = null;
		response = service.loadVSnp2varss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVSnp2varss
	}

	/**
	 * Operation Unit Test
	 * Return all VSnp2vars entity
	 * 
	 */
	@Test
	public void findAllVSnp2varss() {
		// TODO: JUnit - Populate test inputs for operation: findAllVSnp2varss 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VSnp2vars> response = null;
		response = service.findAllVSnp2varss(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVSnp2varss
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VSnp2vars entity
	 * 
	 */
	@Test
	public void deleteVSnp2vars() {
		// TODO: JUnit - Populate test inputs for operation: deleteVSnp2vars 
		VSnp2vars vsnp2vars = new org.irri.iric.portal.chado.domain.VSnp2vars();
		service.deleteVSnp2vars(vsnp2vars);
	}

	/**
	 * Operation Unit Test
	 * Save an existing VSnp2vars entity
	 * 
	 */
	@Test
	public void saveVSnp2vars() {
		// TODO: JUnit - Populate test inputs for operation: saveVSnp2vars 
		VSnp2vars vsnp2vars_1 = new org.irri.iric.portal.chado.domain.VSnp2vars();
		service.saveVSnp2vars(vsnp2vars_1);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVSnp2varsByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVSnp2varsByPrimaryKey 
		Integer var1 = 0;
		Integer var2 = 0;
		Integer snpFeatureId = 0;
		VSnp2vars response = null;
		response = service.findVSnp2varsByPrimaryKey(var1, var2, snpFeatureId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVSnp2varsByPrimaryKey
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
