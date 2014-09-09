package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnpPos;

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
public class VlSnpPosServiceTest {

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
	protected VlSnpPosService service;

	/**
	 * Instantiates a new VlSnpPosServiceTest.
	 *
	 */
	public VlSnpPosServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVlSnpPosByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVlSnpPosByPrimaryKey 
		Integer chr = 0;
		Integer pos = 0;
		VlSnpPos response = null;
		response = service.findVlSnpPosByPrimaryKey(chr, pos);
		// TODO: JUnit - Add assertions to test outputs of operation: findVlSnpPosByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Return all VlSnpPos entity
	 * 
	 */
	@Test
	public void findAllVlSnpPoss() {
		// TODO: JUnit - Populate test inputs for operation: findAllVlSnpPoss 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VlSnpPos> response = null;
		response = service.findAllVlSnpPoss(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVlSnpPoss
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VlSnpPos entity
	 * 
	 */
	@Test
	public void deleteVlSnpPos() {
		// TODO: JUnit - Populate test inputs for operation: deleteVlSnpPos 
		VlSnpPos vlsnppos = new org.irri.iric.portal.genotype.domain.VlSnpPos();
		service.deleteVlSnpPos(vlsnppos);
	}

	/**
	 * Operation Unit Test
	 * Save an existing VlSnpPos entity
	 * 
	 */
	@Test
	public void saveVlSnpPos() {
		// TODO: JUnit - Populate test inputs for operation: saveVlSnpPos 
		VlSnpPos vlsnppos_1 = new org.irri.iric.portal.genotype.domain.VlSnpPos();
		service.saveVlSnpPos(vlsnppos_1);
	}

	/**
	 * Operation Unit Test
	 * Load an existing VlSnpPos entity
	 * 
	 */
	@Test
	public void loadVlSnpPoss() {
		Set<VlSnpPos> response = null;
		response = service.loadVlSnpPoss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVlSnpPoss
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VlSnpPos entity
	 * 
	 */
	@Test
	public void countVlSnpPoss() {
		Integer response = null;
		response = service.countVlSnpPoss();
		// TODO: JUnit - Add assertions to test outputs of operation: countVlSnpPoss
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
