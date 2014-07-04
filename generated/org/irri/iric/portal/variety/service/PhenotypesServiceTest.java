package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

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
public class PhenotypesServiceTest {

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
	protected PhenotypesService service;

	/**
	 * Instantiates a new PhenotypesServiceTest.
	 *
	 */
	public PhenotypesServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Phenotypes entity
	 * 
	 */
	@Test
	public void countPhenotypess() {
		Integer response = null;
		response = service.countPhenotypess();
		// TODO: JUnit - Add assertions to test outputs of operation: countPhenotypess
	}

	/**
	 * Operation Unit Test
	 * Save an existing Germplasm entity
	 * 
	 */
	@Test
	public void savePhenotypesGermplasm() {
		// TODO: JUnit - Populate test inputs for operation: savePhenotypesGermplasm 
		Integer nsftvId = 0;
		String trait = null;
		Germplasm related_germplasm = new org.irri.iric.portal.variety.domain.Germplasm();
		Phenotypes response = null;
		response = service.savePhenotypesGermplasm(nsftvId, trait, related_germplasm);
		// TODO: JUnit - Add assertions to test outputs of operation: savePhenotypesGermplasm
	}

	/**
	 * Operation Unit Test
	 * Load an existing Phenotypes entity
	 * 
	 */
	@Test
	public void loadPhenotypess() {
		Set<Phenotypes> response = null;
		response = service.loadPhenotypess();
		// TODO: JUnit - Add assertions to test outputs of operation: loadPhenotypess
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findPhenotypesByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findPhenotypesByPrimaryKey 
		Integer nsftvId_1 = 0;
		String trait_1 = null;
		Phenotypes response = null;
		response = service.findPhenotypesByPrimaryKey(nsftvId_1, trait_1);
		// TODO: JUnit - Add assertions to test outputs of operation: findPhenotypesByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@Test
	public void deletePhenotypes() {
		// TODO: JUnit - Populate test inputs for operation: deletePhenotypes 
		Phenotypes phenotypes = new org.irri.iric.portal.variety.domain.Phenotypes();
		service.deletePhenotypes(phenotypes);
	}

	/**
	 * Operation Unit Test
	 * Return all Phenotypes entity
	 * 
	 */
	@Test
	public void findAllPhenotypess() {
		// TODO: JUnit - Populate test inputs for operation: findAllPhenotypess 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Phenotypes> response = null;
		response = service.findAllPhenotypess(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllPhenotypess
	}

	/**
	 * Operation Unit Test
	 * Save an existing Phenotypes entity
	 * 
	 */
	@Test
	public void savePhenotypes() {
		// TODO: JUnit - Populate test inputs for operation: savePhenotypes 
		Phenotypes phenotypes_1 = new org.irri.iric.portal.variety.domain.Phenotypes();
		service.savePhenotypes(phenotypes_1);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Germplasm entity
	 * 
	 */
	@Test
	public void deletePhenotypesGermplasm() {
		// TODO: JUnit - Populate test inputs for operation: deletePhenotypesGermplasm 
		Integer phenotypes_nsftvId = 0;
		String phenotypes_trait = null;
		Integer related_germplasm_nsftvId = 0;
		Phenotypes response = null;
		response = service.deletePhenotypesGermplasm(phenotypes_nsftvId, phenotypes_trait, related_germplasm_nsftvId);
		// TODO: JUnit - Add assertions to test outputs of operation: deletePhenotypesGermplasm
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
