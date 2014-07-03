package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.Variety3k;

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
		"file:./resources/zkspring6-security-context.xml",
		"file:./resources/zkspring6-service-context.xml",
		"file:./resources/zkspring6-dao-context.xml",
		"file:./resources/zkspring6-web-context.xml" })
@Transactional
public class Variety3kServiceTest {

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
	protected Variety3kService service;

	/**
	 * Instantiates a new Variety3kServiceTest.
	 *
	 */
	public Variety3kServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Variety3k entity
	 * 
	 */
	@Test
	public void countVariety3ks() {
		Integer response = null;
		response = service.countVariety3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: countVariety3ks
	}

	/**
	 * Operation Unit Test
	 * Load an existing Variety3k entity
	 * 
	 */
	@Test
	public void loadVariety3ks() {
		Set<Variety3k> response = null;
		response = service.loadVariety3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVariety3ks
	}

	/**
	 * Operation Unit Test
	 * Save an existing Variety3k entity
	 * 
	 */
	@Test
	public void saveVariety3k() {
		// TODO: JUnit - Populate test inputs for operation: saveVariety3k 
		Variety3k variety3k = new org.irri.iric.portal.genotype.domain.Variety3k();
		service.saveVariety3k(variety3k);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Variety3k entity
	 * 
	 */
	@Test
	public void deleteVariety3k() {
		// TODO: JUnit - Populate test inputs for operation: deleteVariety3k 
		Variety3k variety3k_1 = new org.irri.iric.portal.genotype.domain.Variety3k();
		service.deleteVariety3k(variety3k_1);
	}

	/**
	 * Operation Unit Test
	 * Return all Variety3k entity
	 * 
	 */
	@Test
	public void findAllVariety3ks() {
		// TODO: JUnit - Populate test inputs for operation: findAllVariety3ks 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Variety3k> response = null;
		response = service.findAllVariety3ks(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVariety3ks
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVariety3kByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVariety3kByPrimaryKey 
		String varname = null;
		Variety3k response = null;
		response = service.findVariety3kByPrimaryKey(varname);
		// TODO: JUnit - Add assertions to test outputs of operation: findVariety3kByPrimaryKey
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
