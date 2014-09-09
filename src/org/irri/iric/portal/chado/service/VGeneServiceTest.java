package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VGene;

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
public class VGeneServiceTest {

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
	protected VGeneService service;

	/**
	 * Instantiates a new VGeneServiceTest.
	 *
	 */
	public VGeneServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Save an existing VGene entity
	 * 
	 */
	@Test
	public void saveVGene() {
		// TODO: JUnit - Populate test inputs for operation: saveVGene 
		VGene vgene = new org.irri.iric.portal.chado.domain.VGene();
		service.saveVGene(vgene);
	}

	/**
	 * Operation Unit Test
	 * Return all VGene entity
	 * 
	 */
	@Test
	public void findAllVGenes() {
		// TODO: JUnit - Populate test inputs for operation: findAllVGenes 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<VGene> response = null;
		response = service.findAllVGenes(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllVGenes
	}

	/**
	 * Operation Unit Test
	 * Return a count of all VGene entity
	 * 
	 */
	@Test
	public void countVGenes() {
		Integer response = null;
		response = service.countVGenes();
		// TODO: JUnit - Add assertions to test outputs of operation: countVGenes
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findVGeneByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findVGeneByPrimaryKey 
		Integer geneId = 0;
		VGene response = null;
		response = service.findVGeneByPrimaryKey(geneId);
		// TODO: JUnit - Add assertions to test outputs of operation: findVGeneByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Load an existing VGene entity
	 * 
	 */
	@Test
	public void loadVGenes() {
		Set<VGene> response = null;
		response = service.loadVGenes();
		// TODO: JUnit - Add assertions to test outputs of operation: loadVGenes
	}

	/**
	 * Operation Unit Test
	 * Delete an existing VGene entity
	 * 
	 */
	@Test
	public void deleteVGene() {
		// TODO: JUnit - Populate test inputs for operation: deleteVGene 
		VGene vgene_1 = new org.irri.iric.portal.chado.domain.VGene();
		service.deleteVGene(vgene_1);
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
