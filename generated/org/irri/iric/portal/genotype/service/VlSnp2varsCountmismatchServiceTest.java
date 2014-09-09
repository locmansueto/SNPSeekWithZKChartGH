package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch;

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
public class VlSnp2varsCountmismatchServiceTest {

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
	protected VlSnp2varsCountmismatchService service;

	/**
	 * Instantiates a new VlSnp2varsCountmismatchServiceTest.
	 *
	 */
	public VlSnp2varsCountmismatchServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVlSnp2varsCountmismatchByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVlSnp2varsCountmismatchByPrimaryKey 
		Integer var1 = 0;
		Integer var2 = 0;
		VlSnp2varsCountmismatch response = null;
		response = service.findVlSnp2varsCountmismatchByPrimaryKey(var1, var2);
		// TODO: JUnit - Add assertions to test outputs of operation: findVlSnp2varsCountmismatchByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Load an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void loadVlSnp2varsCountmismatchs() {
		Set<VlSnp2varsCountmismatch> response = null;
		response = service.loadVlSnp2varsCountmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVlSnp2varsCountmismatchs
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VlSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void countVlSnp2varsCountmismatchs() {
		Integer response = null;
		response = service.countVlSnp2varsCountmismatchs();
		// TODO: JUnit - Add assertions to test outputs of operation: countVlSnp2varsCountmismatchs
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void deleteVlSnp2varsCountmismatch() {
		// TODO: JUnit - Populate test inputs for operation: deleteVlSnp2varsCountmismatch 
		VlSnp2varsCountmismatch vlsnp2varscountmismatch = new org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch();
		service.deleteVlSnp2varsCountmismatch(vlsnp2varscountmismatch);
	}

	/**
	 * Operation Unit Test
	 * Return all VlSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void findAllVlSnp2varsCountmismatchs() {
		// TODO: JUnit - Populate test inputs for operation: findAllVlSnp2varsCountmismatchs 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VlSnp2varsCountmismatch> response = null;
		response = service.findAllVlSnp2varsCountmismatchs(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVlSnp2varsCountmismatchs
	}

	/**
	 * Operation Unit Test
	 * Save an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	@Test
	public void saveVlSnp2varsCountmismatch() {
		// TODO: JUnit - Populate test inputs for operation: saveVlSnp2varsCountmismatch 
		VlSnp2varsCountmismatch vlsnp2varscountmismatch_1 = new org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch();
		service.saveVlSnp2varsCountmismatch(vlsnp2varscountmismatch_1);
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
