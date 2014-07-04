package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Snps;

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
public class SnpsServiceTest {

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
	protected SnpsService service;

	/**
	 * Instantiates a new SnpsServiceTest.
	 *
	 */
	public SnpsServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return all Snps entity
	 * 
	 */
	@Test
	public void findAllSnpss() {
		// TODO: JUnit - Populate test inputs for operation: findAllSnpss 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Snps> response = null;
		response = service.findAllSnpss(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllSnpss
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Snps entity
	 * 
	 */
	@Test
	public void countSnpss() {
		Integer response = null;
		response = service.countSnpss();
		// TODO: JUnit - Add assertions to test outputs of operation: countSnpss
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Genotyping entity
	 * 
	 */
	@Test
	public void deleteSnpsGenotypings() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnpsGenotypings 
		String snps_snpId = null;
		String related_genotypings_snpId = null;
		Integer related_genotypings_nsftvId = 0;
		Snps response = null;
		response = service.deleteSnpsGenotypings(snps_snpId, related_genotypings_snpId, related_genotypings_nsftvId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteSnpsGenotypings
	}

	/**
	 * Operation Unit Test
	 * Load an existing Snps entity
	 * 
	 */
	@Test
	public void loadSnpss() {
		Set<Snps> response = null;
		response = service.loadSnpss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadSnpss
	}

	/**
	 * Operation Unit Test
	 * Save an existing Snps entity
	 * 
	 */
	@Test
	public void saveSnps() {
		// TODO: JUnit - Populate test inputs for operation: saveSnps 
		Snps snps = new org.irri.iric.portal.variety.domain.Snps();
		service.saveSnps(snps);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Snps entity
	 * 
	 */
	@Test
	public void deleteSnps() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnps 
		Snps snps_1 = new org.irri.iric.portal.variety.domain.Snps();
		service.deleteSnps(snps_1);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findSnpsByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findSnpsByPrimaryKey 
		String snpId = null;
		Snps response = null;
		response = service.findSnpsByPrimaryKey(snpId);
		// TODO: JUnit - Add assertions to test outputs of operation: findSnpsByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing Genotyping entity
	 * 
	 */
	@Test
	public void saveSnpsGenotypings() {
		// TODO: JUnit - Populate test inputs for operation: saveSnpsGenotypings 
		String snpId_1 = null;
		Genotyping related_genotypings = new org.irri.iric.portal.variety.domain.Genotyping();
		Snps response = null;
		response = service.saveSnpsGenotypings(snpId_1, related_genotypings);
		// TODO: JUnit - Add assertions to test outputs of operation: saveSnpsGenotypings
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
