package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockBasicprop;

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
public class VIricstockBasicpropServiceTest {

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
	protected VIricstockBasicpropService service;

	/**
	 * Instantiates a new VIricstockBasicpropServiceTest.
	 *
	 */
	public VIricstockBasicpropServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Load an existing VIricstockBasicprop entity
	 * 
	 */
	@Test
	public void loadVIricstockBasicprops() {
		Set<VIricstockBasicprop> response = null;
		response = service.loadVIricstockBasicprops();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVIricstockBasicprops
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVIricstockBasicpropByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVIricstockBasicpropByPrimaryKey 
		Integer iricStockId = 0;
		VIricstockBasicprop response = null;
		response = service.findVIricstockBasicpropByPrimaryKey(iricStockId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVIricstockBasicpropByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VIricstockBasicprop entity
	 * 
	 */
	@Test
	public void deleteVIricstockBasicprop() {
		// TODO: JUnit - Populate test inputs for operation: deleteVIricstockBasicprop 
		VIricstockBasicprop viricstockbasicprop = new org.irri.iric.portal.chado.domain.VIricstockBasicprop();
		service.deleteVIricstockBasicprop(viricstockbasicprop);
	}

	/**
	 * Operation Unit Test
	 * Return all VIricstockBasicprop entity
	 * 
	 */
	@Test
	public void findAllVIricstockBasicprops() {
		// TODO: JUnit - Populate test inputs for operation: findAllVIricstockBasicprops 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VIricstockBasicprop> response = null;
		response = service.findAllVIricstockBasicprops(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVIricstockBasicprops
	}

	/**
	 * Operation Unit Test
	 * Save an existing VIricstockBasicprop entity
	 * 
	 */
	@Test
	public void saveVIricstockBasicprop() {
		// TODO: JUnit - Populate test inputs for operation: saveVIricstockBasicprop 
		VIricstockBasicprop viricstockbasicprop_1 = new org.irri.iric.portal.chado.domain.VIricstockBasicprop();
		service.saveVIricstockBasicprop(viricstockbasicprop_1);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VIricstockBasicprop entity
	 * 
	 */
	@Test
	public void countVIricstockBasicprops() {
		Integer response = null;
		response = service.countVIricstockBasicprops();
		// TODO: JUnit - Add assertions to test outputs of operation: countVIricstockBasicprops
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
