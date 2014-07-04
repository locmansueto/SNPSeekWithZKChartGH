package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;
import org.irri.iric.portal.variety.domain.Var700;

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
public class Genotyp700ServiceTest {

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
	protected Genotyp700Service service;

	/**
	 * Instantiates a new Genotyp700ServiceTest.
	 *
	 */
	public Genotyp700ServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Genotyp700 entity
	 * 
	 */
	@Test
	public void countGenotyp700s() {
		Integer response = null;
		response = service.countGenotyp700s();
		// TODO: JUnit - Add assertions to test outputs of operation: countGenotyp700s
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@Test
	public void deleteGenotyp700() {
		// TODO: JUnit - Populate test inputs for operation: deleteGenotyp700 
		Genotyp700 genotyp700 = new org.irri.iric.portal.variety.domain.Genotyp700();
		service.deleteGenotyp700(genotyp700);
	}

	/**
	 * Operation Unit Test
	 * Return all Genotyp700 entity
	 * 
	 */
	@Test
	public void findAllGenotyp700s() {
		// TODO: JUnit - Populate test inputs for operation: findAllGenotyp700s 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Genotyp700> response = null;
		response = service.findAllGenotyp700s(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllGenotyp700s
	}

	/**
	 * Operation Unit Test
	 * Load an existing Genotyp700 entity
	 * 
	 */
	@Test
	public void loadGenotyp700s() {
		Set<Genotyp700> response = null;
		response = service.loadGenotyp700s();
		// TODO: JUnit - Add assertions to test outputs of operation: loadGenotyp700s
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findGenotyp700ByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findGenotyp700ByPrimaryKey 
		Integer varId = 0;
		Integer snpId = 0;
		Genotyp700 response = null;
		response = service.findGenotyp700ByPrimaryKey(varId, snpId);
		// TODO: JUnit - Add assertions to test outputs of operation: findGenotyp700ByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing Snp700 entity
	 * 
	 */
	@Test
	public void saveGenotyp700Snp700() {
		// TODO: JUnit - Populate test inputs for operation: saveGenotyp700Snp700 
		Integer varId_1 = 0;
		Integer snpId_1 = 0;
		Snp700 related_snp700 = new org.irri.iric.portal.variety.domain.Snp700();
		Genotyp700 response = null;
		response = service.saveGenotyp700Snp700(varId_1, snpId_1, related_snp700);
		// TODO: JUnit - Add assertions to test outputs of operation: saveGenotyp700Snp700
	}

	/**
	 * Operation Unit Test
	 * Save an existing Var700 entity
	 * 
	 */
	@Test
	public void saveGenotyp700Var700() {
		// TODO: JUnit - Populate test inputs for operation: saveGenotyp700Var700 
		Integer varId_2 = 0;
		Integer snpId_2 = 0;
		Var700 related_var700 = new org.irri.iric.portal.variety.domain.Var700();
		Genotyp700 response = null;
		response = service.saveGenotyp700Var700(varId_2, snpId_2, related_var700);
		// TODO: JUnit - Add assertions to test outputs of operation: saveGenotyp700Var700
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Snp700 entity
	 * 
	 */
	@Test
	public void deleteGenotyp700Snp700() {
		// TODO: JUnit - Populate test inputs for operation: deleteGenotyp700Snp700 
		Integer genotyp700_varId = 0;
		Integer genotyp700_snpId = 0;
		Integer related_snp700_id = 0;
		Genotyp700 response = null;
		response = service.deleteGenotyp700Snp700(genotyp700_varId, genotyp700_snpId, related_snp700_id);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteGenotyp700Snp700
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Var700 entity
	 * 
	 */
	@Test
	public void deleteGenotyp700Var700() {
		// TODO: JUnit - Populate test inputs for operation: deleteGenotyp700Var700 
		Integer genotyp700_varId_1 = 0;
		Integer genotyp700_snpId_1 = 0;
		Integer related_var700_id = 0;
		Genotyp700 response = null;
		response = service.deleteGenotyp700Var700(genotyp700_varId_1, genotyp700_snpId_1, related_var700_id);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteGenotyp700Var700
	}

	/**
	 * Operation Unit Test
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@Test
	public void saveGenotyp700() {
		// TODO: JUnit - Populate test inputs for operation: saveGenotyp700 
		Genotyp700 genotyp700_1 = new org.irri.iric.portal.variety.domain.Genotyp700();
		service.saveGenotyp700(genotyp700_1);
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
