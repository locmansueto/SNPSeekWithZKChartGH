package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.Gene;

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
		"file:./resources/zkspring7-security-context.xml",
		"file:./resources/zkspring7-service-context.xml",
		"file:./resources/zkspring7-dao-context.xml",
		"file:./resources/zkspring7-web-context.xml" })
@Transactional
public class GeneServiceTest {

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
	protected GeneService service;

	/**
	 * Instantiates a new GeneServiceTest.
	 *
	 */
	public GeneServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return all Gene entity
	 * 
	 */
	@Test
	public void findAllGenes() {
		// TODO: JUnit - Populate test inputs for operation: findAllGenes 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Gene> response = null;
		response = service.findAllGenes(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllGenes
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Gene entity
	 * 
	 */
	@Test
	public void countGenes() {
		Integer response = null;
		response = service.countGenes();
		// TODO: JUnit - Add assertions to test outputs of operation: countGenes
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Gene entity
	 * 
	 */
	@Test
	public void deleteGene() {
		// TODO: JUnit - Populate test inputs for operation: deleteGene 
		Gene gene = new org.irri.iric.portal.genotype.domain.Gene();
		service.deleteGene(gene);
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findGeneByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findGeneByPrimaryKey 
		Integer featureId = 0;
		Gene response = null;
		response = service.findGeneByPrimaryKey(featureId);
		// TODO: JUnit - Add assertions to test outputs of operation: findGeneByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing Gene entity
	 * 
	 */
	@Test
	public void saveGene() {
		// TODO: JUnit - Populate test inputs for operation: saveGene 
		Gene gene_1 = new org.irri.iric.portal.genotype.domain.Gene();
		service.saveGene(gene_1);
	}

	/**
	 * Operation Unit Test
	 * Load an existing Gene entity
	 * 
	 */
	@Test
	public void loadGenes() {
		Set<Gene> response = null;
		response = service.loadGenes();
		// TODO: JUnit - Add assertions to test outputs of operation: loadGenes
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
