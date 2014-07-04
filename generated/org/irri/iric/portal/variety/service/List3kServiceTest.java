package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.List3k;

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
public class List3kServiceTest {

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
	protected List3kService service;

	/**
	 * Instantiates a new List3kServiceTest.
	 *
	 */
	public List3kServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return all List3k entity
	 * 
	 */
	@Test
	public void findAllList3ks() {
		// TODO: JUnit - Populate test inputs for operation: findAllList3ks 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<List3k> response = null;
		response = service.findAllList3ks(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllList3ks
	}

	/**
	 * Operation Unit Test
	 * Delete an existing List3k entity
	 * 
	 */
	@Test
	public void deleteList3k() {
		// TODO: JUnit - Populate test inputs for operation: deleteList3k 
		List3k list3k = new org.irri.iric.portal.variety.domain.List3k();
		service.deleteList3k(list3k);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findList3kByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findList3kByPrimaryKey 
		String irisUniqueId = null;
		List3k response = null;
		response = service.findList3kByPrimaryKey(irisUniqueId);
		// TODO: JUnit - Add assertions to test outputs of operation: findList3kByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing List3k entity
	 * 
	 */
	@Test
	public void saveList3k() {
		// TODO: JUnit - Populate test inputs for operation: saveList3k 
		List3k list3k_1 = new org.irri.iric.portal.variety.domain.List3k();
		service.saveList3k(list3k_1);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all List3k entity
	 * 
	 */
	@Test
	public void countList3ks() {
		Integer response = null;
		response = service.countList3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: countList3ks
	}

	/**
	 * Operation Unit Test
	 * Load an existing List3k entity
	 * 
	 */
	@Test
	public void loadList3ks() {
		Set<List3k> response = null;
		response = service.loadList3ks();
		// TODO: JUnit - Add assertions to test outputs of operation: loadList3ks
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
