package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyping;
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
public class GermplasmServiceTest {

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
	protected GermplasmService service;

	/**
	 * Instantiates a new GermplasmServiceTest.
	 *
	 */
	public GermplasmServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Germplasm entity
	 * 
	 */
	@Test
	public void countGermplasms() {
		Integer response = null;
		response = service.countGermplasms();
		// TODO: JUnit - Add assertions to test outputs of operation: countGermplasms
	}

	/**
	 * Operation Unit Test
	 * Return all Germplasm entity
	 * 
	 */
	@Test
	public void findAllGermplasms() {
		// TODO: JUnit - Populate test inputs for operation: findAllGermplasms 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Germplasm> response = null;
		response = service.findAllGermplasms(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllGermplasms
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@Test
	public void deleteGermplasmPhenotypeses() {
		// TODO: JUnit - Populate test inputs for operation: deleteGermplasmPhenotypeses 
		Integer germplasm_nsftvId = 0;
		Integer related_phenotypeses_nsftvId = 0;
		String related_phenotypeses_trait = null;
		Germplasm response = null;
		response = service.deleteGermplasmPhenotypeses(germplasm_nsftvId, related_phenotypeses_nsftvId, related_phenotypeses_trait);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteGermplasmPhenotypeses
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Genotyping entity
	 * 
	 */
	@Test
	public void deleteGermplasmGenotypings() {
		// TODO: JUnit - Populate test inputs for operation: deleteGermplasmGenotypings 
		Integer germplasm_nsftvId_1 = 0;
		String related_genotypings_snpId = null;
		Integer related_genotypings_nsftvId = 0;
		Germplasm response = null;
		response = service.deleteGermplasmGenotypings(germplasm_nsftvId_1, related_genotypings_snpId, related_genotypings_nsftvId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteGermplasmGenotypings
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Germplasm entity
	 * 
	 */
	@Test
	public void deleteGermplasm() {
		// TODO: JUnit - Populate test inputs for operation: deleteGermplasm 
		Germplasm germplasm = new org.irri.iric.portal.variety.domain.Germplasm();
		service.deleteGermplasm(germplasm);
	}

	/**
	 * Operation Unit Test
	 * Load an existing Germplasm entity
	 * 
	 */
	@Test
	public void loadGermplasms() {
		Set<Germplasm> response = null;
		response = service.loadGermplasms();
		// TODO: JUnit - Add assertions to test outputs of operation: loadGermplasms
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findGermplasmByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findGermplasmByPrimaryKey 
		Integer nsftvId = 0;
		Germplasm response = null;
		response = service.findGermplasmByPrimaryKey(nsftvId);
		// TODO: JUnit - Add assertions to test outputs of operation: findGermplasmByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing Germplasm entity
	 * 
	 */
	@Test
	public void saveGermplasm() {
		// TODO: JUnit - Populate test inputs for operation: saveGermplasm 
		Germplasm germplasm_1 = new org.irri.iric.portal.variety.domain.Germplasm();
		service.saveGermplasm(germplasm_1);
	}

	/**
	 * Operation Unit Test
	 * Save an existing Genotyping entity
	 * 
	 */
	@Test
	public void saveGermplasmGenotypings() {
		// TODO: JUnit - Populate test inputs for operation: saveGermplasmGenotypings 
		Integer nsftvId_1 = 0;
		Genotyping related_genotypings = new org.irri.iric.portal.variety.domain.Genotyping();
		Germplasm response = null;
		response = service.saveGermplasmGenotypings(nsftvId_1, related_genotypings);
		// TODO: JUnit - Add assertions to test outputs of operation: saveGermplasmGenotypings
	}

	/**
	 * Operation Unit Test
	 * Save an existing Phenotypes entity
	 * 
	 */
	@Test
	public void saveGermplasmPhenotypeses() {
		// TODO: JUnit - Populate test inputs for operation: saveGermplasmPhenotypeses 
		Integer nsftvId_2 = 0;
		Phenotypes related_phenotypeses = new org.irri.iric.portal.variety.domain.Phenotypes();
		Germplasm response = null;
		response = service.saveGermplasmPhenotypeses(nsftvId_2, related_phenotypeses);
		// TODO: JUnit - Add assertions to test outputs of operation: saveGermplasmPhenotypeses
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
