package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Dist3k;

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
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
@Transactional
public class Dist3kServiceTest {

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
	protected Dist3kService service;

	/**
	 * Instantiates a new Dist3kServiceTest.
	 *
	 */
	public Dist3kServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return all Dist3k entity
	 * 
	 */
	@Test
	public void findAllDist3ks() {
		// TODO: JUnit - Populate test inputs for operation: findAllDist3ks 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Dist3k> response = null;
		response = service.findAllDist3ks(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllDist3ks
	}

	/**
	 * Operation Unit Test
	 * Load an existing Dist3k entity
	 * 
	 */
	@Test
	public void loadDist3ks() {
		Set<Dist3k> response = null;
		response = service.loadDist3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: loadDist3ks
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Dist3k entity
	 * 
	 */
	@Test
	public void countDist3ks() {
		Integer response = null;
		response = service.countDist3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: countDist3ks
	}

	/**
	 * Operation Unit Test
	 * Save an existing Dist3k entity
	 * 
	 */
	@Test
	public void saveDist3k() {
		// TODO: JUnit - Populate test inputs for operation: saveDist3k 
		Dist3k dist3k = new org.irri.iric.portal.variety.domain.Dist3k();
		service.saveDist3k(dist3k);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Dist3k entity
	 * 
	 */
	@Test
	public void deleteDist3k() {
		// TODO: JUnit - Populate test inputs for operation: deleteDist3k 
		Dist3k dist3k_1 = new org.irri.iric.portal.variety.domain.Dist3k();
		service.deleteDist3k(dist3k_1);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findDist3kByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findDist3kByPrimaryKey 
		String nam1 = null;
		String nam2 = null;
		Dist3k response = null;
		response = service.findDist3kByPrimaryKey(nam1, nam2);
		// TODO: JUnit - Add assertions to test outputs of operation: findDist3kByPrimaryKey
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
