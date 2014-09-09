package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockPassport;

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
public class VIricstockPassportServiceTest {

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
	protected VIricstockPassportService service;

	/**
	 * Instantiates a new VIricstockPassportServiceTest.
	 *
	 */
	public VIricstockPassportServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VIricstockPassport entity
	 * 
	 */
	@Test
	public void deleteVIricstockPassport() {
		// TODO: JUnit - Populate test inputs for operation: deleteVIricstockPassport 
		VIricstockPassport viricstockpassport = new org.irri.iric.portal.chado.domain.VIricstockPassport();
		service.deleteVIricstockPassport(viricstockpassport);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VIricstockPassport entity
	 * 
	 */
	@Test
	public void countVIricstockPassports() {
		Integer response = null;
		response = service.countVIricstockPassports();
		// TODO: JUnit - Add assertions to test outputs of operation: countVIricstockPassports
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVIricstockPassportByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVIricstockPassportByPrimaryKey 
		Integer iricStockpropId = 0;
		VIricstockPassport response = null;
		response = service.findVIricstockPassportByPrimaryKey(iricStockpropId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVIricstockPassportByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Load an existing VIricstockPassport entity
	 * 
	 */
	@Test
	public void loadVIricstockPassports() {
		Set<VIricstockPassport> response = null;
		response = service.loadVIricstockPassports();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVIricstockPassports
	}

	/**
	 * Operation Unit Test
	 * Save an existing VIricstockPassport entity
	 * 
	 */
	@Test
	public void saveVIricstockPassport() {
		// TODO: JUnit - Populate test inputs for operation: saveVIricstockPassport 
		VIricstockPassport viricstockpassport_1 = new org.irri.iric.portal.chado.domain.VIricstockPassport();
		service.saveVIricstockPassport(viricstockpassport_1);
	}

	/**
	 * Operation Unit Test
	 * Return all VIricstockPassport entity
	 * 
	 */
	@Test
	public void findAllVIricstockPassports() {
		// TODO: JUnit - Populate test inputs for operation: findAllVIricstockPassports 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VIricstockPassport> response = null;
		response = service.findAllVIricstockPassports(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVIricstockPassports
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
