package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvarsPos;

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
public class VSnpAllvarsPosServiceTest {

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
	protected VSnpAllvarsPosService service;

	/**
	 * Instantiates a new VSnpAllvarsPosServiceTest.
	 *
	 */
	public VSnpAllvarsPosServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return all VSnpAllvarsPos entity
	 * 
	 */
	@Test
	public void findAllVSnpAllvarsPoss() {
		// TODO: JUnit - Populate test inputs for operation: findAllVSnpAllvarsPoss 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VSnpAllvarsPos> response = null;
		response = service.findAllVSnpAllvarsPoss(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVSnpAllvarsPoss
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VSnpAllvarsPos entity
	 * 
	 */
	@Test
	public void deleteVSnpAllvarsPos() {
		// TODO: JUnit - Populate test inputs for operation: deleteVSnpAllvarsPos 
		VSnpAllvarsPos vsnpallvarspos = new org.irri.iric.portal.chado.domain.VSnpAllvarsPos();
		service.deleteVSnpAllvarsPos(vsnpallvarspos);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVSnpAllvarsPosByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVSnpAllvarsPosByPrimaryKey 
		Integer snpFeatureId = 0;
		VSnpAllvarsPos response = null;
		response = service.findVSnpAllvarsPosByPrimaryKey(snpFeatureId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVSnpAllvarsPosByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VSnpAllvarsPos entity
	 * 
	 */
	@Test
	public void countVSnpAllvarsPoss() {
		Integer response = null;
		response = service.countVSnpAllvarsPoss();
		// TODO: JUnit - Add assertions to test outputs of operation: countVSnpAllvarsPoss
	}

	/**
	 * Operation Unit Test
	 * Load an existing VSnpAllvarsPos entity
	 * 
	 */
	@Test
	public void loadVSnpAllvarsPoss() {
		Set<VSnpAllvarsPos> response = null;
		response = service.loadVSnpAllvarsPoss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVSnpAllvarsPoss
	}

	/**
	 * Operation Unit Test
	 * Save an existing VSnpAllvarsPos entity
	 * 
	 */
	@Test
	public void saveVSnpAllvarsPos() {
		// TODO: JUnit - Populate test inputs for operation: saveVSnpAllvarsPos 
		VSnpAllvarsPos vsnpallvarspos_1 = new org.irri.iric.portal.chado.domain.VSnpAllvarsPos();
		service.saveVSnpAllvarsPos(vsnpallvarspos_1);
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
