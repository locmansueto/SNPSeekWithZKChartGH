package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
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
public class GenotypingServiceTest {

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
	protected GenotypingService service;

	/**
	 * Instantiates a new GenotypingServiceTest.
	 *
	 */
	public GenotypingServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findGenotypingByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findGenotypingByPrimaryKey 
		String snpId = null;
		Integer nsftvId = 0;
		Genotyping response = null;
		response = service.findGenotypingByPrimaryKey(snpId, nsftvId);
		// TODO: JUnit - Add assertions to test outputs of operation: findGenotypingByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Snps entity
	 * 
	 */
	@Test
	public void deleteGenotypingSnps() {
		// TODO: JUnit - Populate test inputs for operation: deleteGenotypingSnps 
		String genotyping_snpId = null;
		Integer genotyping_nsftvId = 0;
		String related_snps_snpId = null;
		Genotyping response = null;
		response = service.deleteGenotypingSnps(genotyping_snpId, genotyping_nsftvId, related_snps_snpId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteGenotypingSnps
	}

	/**
	 * Operation Unit Test
	 * Save an existing Genotyping entity
	 * 
	 */
	@Test
	public void saveGenotyping() {
		// TODO: JUnit - Populate test inputs for operation: saveGenotyping 
		Genotyping genotyping = new org.irri.iric.portal.variety.domain.Genotyping();
		service.saveGenotyping(genotyping);
	}

	/**
	 * Operation Unit Test
	 * Save an existing Germplasm entity
	 * 
	 */
	@Test
	public void saveGenotypingGermplasm() {
		// TODO: JUnit - Populate test inputs for operation: saveGenotypingGermplasm 
		String snpId_1 = null;
		Integer nsftvId_1 = 0;
		Germplasm related_germplasm = new org.irri.iric.portal.variety.domain.Germplasm();
		Genotyping response = null;
		response = service.saveGenotypingGermplasm(snpId_1, nsftvId_1, related_germplasm);
		// TODO: JUnit - Add assertions to test outputs of operation: saveGenotypingGermplasm
	}

	/**
	 * Operation Unit Test
	 * Return all Genotyping entity
	 * 
	 */
	@Test
	public void findAllGenotypings() {
		// TODO: JUnit - Populate test inputs for operation: findAllGenotypings 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Genotyping> response = null;
		response = service.findAllGenotypings(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllGenotypings
	}

	/**
	 * Operation Unit Test
	 * Save an existing Snps entity
	 * 
	 */
	@Test
	public void saveGenotypingSnps() {
		// TODO: JUnit - Populate test inputs for operation: saveGenotypingSnps 
		String snpId_2 = null;
		Integer nsftvId_2 = 0;
		Snps related_snps = new org.irri.iric.portal.variety.domain.Snps();
		Genotyping response = null;
		response = service.saveGenotypingSnps(snpId_2, nsftvId_2, related_snps);
		// TODO: JUnit - Add assertions to test outputs of operation: saveGenotypingSnps
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Genotyping entity
	 * 
	 */
	@Test
	public void countGenotypings() {
		Integer response = null;
		response = service.countGenotypings();
		// TODO: JUnit - Add assertions to test outputs of operation: countGenotypings
	}

	/**
	 * Operation Unit Test
	 * Load an existing Genotyping entity
	 * 
	 */
	@Test
	public void loadGenotypings() {
		Set<Genotyping> response = null;
		response = service.loadGenotypings();
		// TODO: JUnit - Add assertions to test outputs of operation: loadGenotypings
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Germplasm entity
	 * 
	 */
	@Test
	public void deleteGenotypingGermplasm() {
		// TODO: JUnit - Populate test inputs for operation: deleteGenotypingGermplasm 
		String genotyping_snpId_1 = null;
		Integer genotyping_nsftvId_1 = 0;
		Integer related_germplasm_nsftvId = 0;
		Genotyping response = null;
		response = service.deleteGenotypingGermplasm(genotyping_snpId_1, genotyping_nsftvId_1, related_germplasm_nsftvId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteGenotypingGermplasm
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Genotyping entity
	 * 
	 */
	@Test
	public void deleteGenotyping() {
		// TODO: JUnit - Populate test inputs for operation: deleteGenotyping 
		Genotyping genotyping_1 = new org.irri.iric.portal.variety.domain.Genotyping();
		service.deleteGenotyping(genotyping_1);
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
