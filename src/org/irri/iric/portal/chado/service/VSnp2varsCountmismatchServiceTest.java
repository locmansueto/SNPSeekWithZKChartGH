package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;

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
public class VSnp2varsCountmismatchServiceTest {

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
	protected VSnp2varsCountmismatchService service;

	/**
	 * Instantiates a new VSnp2varsCountmismatchServiceTest.
	 *
	 */
	public VSnp2varsCountmismatchServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVSnp2varsCountmismatchByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVSnp2varsCountmismatchByPrimaryKey 
		Integer var1 = 0;
		Integer var2 = 0;
		VSnp2varsCountmismatch response = null;
		response = service.findVSnp2varsCountmismatchByPrimaryKey(var1, var2);
		// TODO: JUnit - Add assertions to test outputs of operation: findVSnp2varsCountmismatchByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return all VSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void findAllVSnp2varsCountmismatchs() {
		// TODO: JUnit - Populate test inputs for operation: findAllVSnp2varsCountmismatchs 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VSnp2varsCountmismatch> response = null;
		response = service.findAllVSnp2varsCountmismatchs(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVSnp2varsCountmismatchs
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void deleteVSnp2varsCountmismatch() {
		// TODO: JUnit - Populate test inputs for operation: deleteVSnp2varsCountmismatch 
		VSnp2varsCountmismatch vsnp2varscountmismatch = new org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch();
		service.deleteVSnp2varsCountmismatch(vsnp2varscountmismatch);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void countVSnp2varsCountmismatchs() {
		Integer response = null;
		response = service.countVSnp2varsCountmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: countVSnp2varsCountmismatchs
	}

	/**
	 * Operation Unit Test
	 * Load an existing VSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void loadVSnp2varsCountmismatchs() {
		Set<VSnp2varsCountmismatch> response = null;
		response = service.loadVSnp2varsCountmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVSnp2varsCountmismatchs
	}

	/**
	 * Operation Unit Test
	 * Save an existing VSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void saveVSnp2varsCountmismatch() {
		// TODO: JUnit - Populate test inputs for operation: saveVSnp2varsCountmismatch 
		VSnp2varsCountmismatch vsnp2varscountmismatch_1 = new org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch();
		service.saveVSnp2varsCountmismatch(vsnp2varscountmismatch_1);
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
