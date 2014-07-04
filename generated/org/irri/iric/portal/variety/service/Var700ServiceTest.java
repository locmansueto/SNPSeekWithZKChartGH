package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyp700;
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
public class Var700ServiceTest {

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
	protected Var700Service service;

	/**
	 * Instantiates a new Var700ServiceTest.
	 *
	 */
	public Var700ServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Save an existing Var700 entity
	 * 
	 */
	@Test
	public void saveVar700() {
		// TODO: JUnit - Populate test inputs for operation: saveVar700 
		Var700 var700 = new org.irri.iric.portal.variety.domain.Var700();
		service.saveVar700(var700);
	}

	/**
	 * Operation Unit Test
	 * Load an existing Var700 entity
	 * 
	 */
	@Test
	public void loadVar700s() {
		Set<Var700> response = null;
		response = service.loadVar700s();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVar700s
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Var700 entity
	 * 
	 */
	@Test
	public void countVar700s() {
		Integer response = null;
		response = service.countVar700s();
		// TODO: JUnit - Add assertions to test outputs of operation: countVar700s
	}

	/**
	 * Operation Unit Test
	 * Return all Var700 entity
	 * 
	 */
	@Test
	public void findAllVar700s() {
		// TODO: JUnit - Populate test inputs for operation: findAllVar700s 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Var700> response = null;
		response = service.findAllVar700s(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVar700s
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVar700ByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVar700ByPrimaryKey 
		Integer id = 0;
		Var700 response = null;
		response = service.findVar700ByPrimaryKey(id);
		// TODO: JUnit - Add assertions to test outputs of operation: findVar700ByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@Test
	public void saveVar700Genotyp700s() {
		// TODO: JUnit - Populate test inputs for operation: saveVar700Genotyp700s 
		Integer id_1 = 0;
		Genotyp700 related_genotyp700s = new org.irri.iric.portal.variety.domain.Genotyp700();
		Var700 response = null;
		response = service.saveVar700Genotyp700s(id_1, related_genotyp700s);
		// TODO: JUnit - Add assertions to test outputs of operation: saveVar700Genotyp700s
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@Test
	public void deleteVar700Genotyp700s() {
		// TODO: JUnit - Populate test inputs for operation: deleteVar700Genotyp700s 
		Integer var700_id = 0;
		Integer related_genotyp700s_varId = 0;
		Integer related_genotyp700s_snpId = 0;
		Var700 response = null;
		response = service.deleteVar700Genotyp700s(var700_id, related_genotyp700s_varId, related_genotyp700s_snpId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteVar700Genotyp700s
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Var700 entity
	 * 
	 */
	@Test
	public void deleteVar700() {
		// TODO: JUnit - Populate test inputs for operation: deleteVar700 
		Var700 var700_1 = new org.irri.iric.portal.variety.domain.Var700();
		service.deleteVar700(var700_1);
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
