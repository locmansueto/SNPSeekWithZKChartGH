package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlVariety3k;

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
public class VlVariety3kServiceTest {

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
	protected VlVariety3kService service;

	/**
	 * Instantiates a new VlVariety3kServiceTest.
	 *
	 */
	public VlVariety3kServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VlVariety3k entity
	 * 
	 */
	@Test
	public void countVlVariety3ks() {
		Integer response = null;
		response = service.countVlVariety3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: countVlVariety3ks
	}

	/**
	 * Operation Unit Test
	 * Return all VlVariety3k entity
	 * 
	 */
	@Test
	public void findAllVlVariety3ks() {
		// TODO: JUnit - Populate test inputs for operation: findAllVlVariety3ks 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VlVariety3k> response = null;
		response = service.findAllVlVariety3ks(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVlVariety3ks
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVlVariety3kByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVlVariety3kByPrimaryKey 
		Integer varId = 0;
		VlVariety3k response = null;
		response = service.findVlVariety3kByPrimaryKey(varId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVlVariety3kByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VlVariety3k entity
	 * 
	 */
	@Test
	public void deleteVlVariety3k() {
		// TODO: JUnit - Populate test inputs for operation: deleteVlVariety3k 
		VlVariety3k vlvariety3k = new org.irri.iric.portal.genotype.domain.VlVariety3k();
		service.deleteVlVariety3k(vlvariety3k);
	}

	/**
	 * Operation Unit Test
	 * Load an existing VlVariety3k entity
	 * 
	 */
	@Test
	public void loadVlVariety3ks() {
		Set<VlVariety3k> response = null;
		response = service.loadVlVariety3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVlVariety3ks
	}

	/**
	 * Operation Unit Test
	 * Save an existing VlVariety3k entity
	 * 
	 */
	@Test
	public void saveVlVariety3k() {
		// TODO: JUnit - Populate test inputs for operation: saveVlVariety3k 
		VlVariety3k vlvariety3k_1 = new org.irri.iric.portal.genotype.domain.VlVariety3k();
		service.saveVlVariety3k(vlvariety3k_1);
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
