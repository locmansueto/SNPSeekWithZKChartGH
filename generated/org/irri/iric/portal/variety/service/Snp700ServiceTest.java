package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;

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
public class Snp700ServiceTest {

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
	protected Snp700Service service;

	/**
	 * Instantiates a new Snp700ServiceTest.
	 *
	 */
	public Snp700ServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findSnp700ByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findSnp700ByPrimaryKey 
		Integer id = 0;
		Snp700 response = null;
		response = service.findSnp700ByPrimaryKey(id);
		// TODO: JUnit - Add assertions to test outputs of operation: findSnp700ByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return all Snp700 entity
	 * 
	 */
	@Test
	public void findAllSnp700s() {
		// TODO: JUnit - Populate test inputs for operation: findAllSnp700s 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Snp700> response = null;
		response = service.findAllSnp700s(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllSnp700s
	}

	/**
	 * Operation Unit Test
	 * Load an existing Snp700 entity
	 * 
	 */
	@Test
	public void loadSnp700s() {
		Set<Snp700> response = null;
		response = service.loadSnp700s();
		// TODO: JUnit - Add assertions to test outputs of operation: loadSnp700s
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Snp700 entity
	 * 
	 */
	@Test
	public void countSnp700s() {
		Integer response = null;
		response = service.countSnp700s();
		// TODO: JUnit - Add assertions to test outputs of operation: countSnp700s
	}

	/**
	 * Operation Unit Test
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@Test
	public void saveSnp700Genotyp700s() {
		// TODO: JUnit - Populate test inputs for operation: saveSnp700Genotyp700s 
		Integer id_1 = 0;
		Genotyp700 related_genotyp700s = new org.irri.iric.portal.variety.domain.Genotyp700();
		Snp700 response = null;
		response = service.saveSnp700Genotyp700s(id_1, related_genotyp700s);
		// TODO: JUnit - Add assertions to test outputs of operation: saveSnp700Genotyp700s
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Snp700 entity
	 * 
	 */
	@Test
	public void deleteSnp700() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnp700 
		Snp700 snp700 = new org.irri.iric.portal.variety.domain.Snp700();
		service.deleteSnp700(snp700);
	}

	/**
	 * Operation Unit Test
	 * Save an existing Snp700 entity
	 * 
	 */
	@Test
	public void saveSnp700() {
		// TODO: JUnit - Populate test inputs for operation: saveSnp700 
		Snp700 snp700_1 = new org.irri.iric.portal.variety.domain.Snp700();
		service.saveSnp700(snp700_1);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@Test
	public void deleteSnp700Genotyp700s() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnp700Genotyp700s 
		Integer snp700_id = 0;
		Integer related_genotyp700s_varId = 0;
		Integer related_genotyp700s_snpId = 0;
		Snp700 response = null;
		response = service.deleteSnp700Genotyp700s(snp700_id, related_genotyp700s_varId, related_genotyp700s_snpId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteSnp700Genotyp700s
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
