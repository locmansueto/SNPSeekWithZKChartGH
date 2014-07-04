package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Names700;

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
public class Names700ServiceTest {

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
	protected Names700Service service;

	/**
	 * Instantiates a new Names700ServiceTest.
	 *
	 */
	public Names700ServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Load an existing Names700 entity
	 * 
	 */
	@Test
	public void loadNames700s() {
		Set<Names700> response = null;
		response = service.loadNames700s();
		// TODO: JUnit - Add assertions to test outputs of operation: loadNames700s
	}

	/**
	 * Operation Unit Test
	 * Return all Names700 entity
	 * 
	 */
	@Test
	public void findAllNames700s() {
		// TODO: JUnit - Populate test inputs for operation: findAllNames700s 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Names700> response = null;
		response = service.findAllNames700s(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllNames700s
	}

	/**
	 * Operation Unit Test
	 * Save an existing Names700 entity
	 * 
	 */
	@Test
	public void saveNames700() {
		// TODO: JUnit - Populate test inputs for operation: saveNames700 
		Names700 names700 = new org.irri.iric.portal.variety.domain.Names700();
		service.saveNames700(names700);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findNames700ByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findNames700ByPrimaryKey 
		String id = null;
		Names700 response = null;
		response = service.findNames700ByPrimaryKey(id);
		// TODO: JUnit - Add assertions to test outputs of operation: findNames700ByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Names700 entity
	 * 
	 */
	@Test
	public void deleteNames700() {
		// TODO: JUnit - Populate test inputs for operation: deleteNames700 
		Names700 names700_1 = new org.irri.iric.portal.variety.domain.Names700();
		service.deleteNames700(names700_1);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Names700 entity
	 * 
	 */
	@Test
	public void countNames700s() {
		Integer response = null;
		response = service.countNames700s();
		// TODO: JUnit - Add assertions to test outputs of operation: countNames700s
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
