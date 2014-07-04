package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.PhenGc;

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
public class PhenGcServiceTest {

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
	protected PhenGcService service;

	/**
	 * Instantiates a new PhenGcServiceTest.
	 *
	 */
	public PhenGcServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Save an existing PhenGc entity
	 * 
	 */
	@Test
	public void savePhenGc() {
		// TODO: JUnit - Populate test inputs for operation: savePhenGc 
		PhenGc phengc = new org.irri.iric.portal.variety.domain.PhenGc();
		service.savePhenGc(phengc);
	}

	/**
	 * Operation Unit Test
	 * Return all PhenGc entity
	 * 
	 */
	@Test
	public void findAllPhenGcs() {
		// TODO: JUnit - Populate test inputs for operation: findAllPhenGcs 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<PhenGc> response = null;
		response = service.findAllPhenGcs(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllPhenGcs
	}

	/**
	 * Operation Unit Test
	 * Load an existing PhenGc entity
	 * 
	 */
	@Test
	public void loadPhenGcs() {
		Set<PhenGc> response = null;
		response = service.loadPhenGcs();
		// TODO: JUnit - Add assertions to test outputs of operation: loadPhenGcs
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findPhenGcByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findPhenGcByPrimaryKey 
		Integer entno = 0;
		Integer gid = 0;
		PhenGc response = null;
		response = service.findPhenGcByPrimaryKey(entno, gid);
		// TODO: JUnit - Add assertions to test outputs of operation: findPhenGcByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return a count of all PhenGc entity
	 * 
	 */
	@Test
	public void countPhenGcs() {
		Integer response = null;
		response = service.countPhenGcs();
		// TODO: JUnit - Add assertions to test outputs of operation: countPhenGcs
	}

	/**
	 * Operation Unit Test
	 * Delete an existing PhenGc entity
	 * 
	 */
	@Test
	public void deletePhenGc() {
		// TODO: JUnit - Populate test inputs for operation: deletePhenGc 
		PhenGc phengc_1 = new org.irri.iric.portal.variety.domain.PhenGc();
		service.deletePhenGc(phengc_1);
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
