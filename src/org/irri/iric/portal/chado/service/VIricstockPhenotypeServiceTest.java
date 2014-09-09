package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockPhenotype;

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
public class VIricstockPhenotypeServiceTest {

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
	protected VIricstockPhenotypeService service;

	/**
	 * Instantiates a new VIricstockPhenotypeServiceTest.
	 *
	 */
	public VIricstockPhenotypeServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VIricstockPhenotype entity
	 * 
	 */
	@Test
	public void countVIricstockPhenotypes() {
		Integer response = null;
		response = service.countVIricstockPhenotypes();
		// TODO: JUnit - Add assertions to test outputs of operation: countVIricstockPhenotypes
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVIricstockPhenotypeByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVIricstockPhenotypeByPrimaryKey 
		Integer iricStockPhenotypeId = 0;
		VIricstockPhenotype response = null;
		response = service.findVIricstockPhenotypeByPrimaryKey(iricStockPhenotypeId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVIricstockPhenotypeByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return all VIricstockPhenotype entity
	 * 
	 */
	@Test
	public void findAllVIricstockPhenotypes() {
		// TODO: JUnit - Populate test inputs for operation: findAllVIricstockPhenotypes 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VIricstockPhenotype> response = null;
		response = service.findAllVIricstockPhenotypes(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVIricstockPhenotypes
	}

	/**
	 * Operation Unit Test
	 * Save an existing VIricstockPhenotype entity
	 * 
	 */
	@Test
	public void saveVIricstockPhenotype() {
		// TODO: JUnit - Populate test inputs for operation: saveVIricstockPhenotype 
		VIricstockPhenotype viricstockphenotype = new org.irri.iric.portal.chado.domain.VIricstockPhenotype();
		service.saveVIricstockPhenotype(viricstockphenotype);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VIricstockPhenotype entity
	 * 
	 */
	@Test
	public void deleteVIricstockPhenotype() {
		// TODO: JUnit - Populate test inputs for operation: deleteVIricstockPhenotype 
		VIricstockPhenotype viricstockphenotype_1 = new org.irri.iric.portal.chado.domain.VIricstockPhenotype();
		service.deleteVIricstockPhenotype(viricstockphenotype_1);
	}

	/**
	 * Operation Unit Test
	 * Load an existing VIricstockPhenotype entity
	 * 
	 */
	@Test
	public void loadVIricstockPhenotypes() {
		Set<VIricstockPhenotype> response = null;
		response = service.loadVIricstockPhenotypes();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVIricstockPhenotypes
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
