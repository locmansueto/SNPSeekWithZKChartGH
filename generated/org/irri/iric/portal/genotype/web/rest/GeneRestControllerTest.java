package org.irri.iric.portal.genotype.web.rest;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

/**
 * Unit test for the <code>GeneRestController</code> controller.
 *
 * @see org.irri.iric.portal.genotype.web.rest.GeneRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/zkspring7-security-context.xml",
		"file:./resources/zkspring7-service-context.xml",
		"file:./resources/zkspring7-dao-context.xml",
		"file:./resources/zkspring7-web-context.xml" })
public class GeneRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Gene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Gene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneRestController controller = (GeneRestController) context.getBean("GeneRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genegene_featureId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenegene_featureId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Gene/{gene_featureId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneRestController controller = (GeneRestController) context.getBean("GeneRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Gene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Gene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneRestController controller = (GeneRestController) context.getBean("GeneRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Gene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Gene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneRestController controller = (GeneRestController) context.getBean("GeneRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genegene_featureId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGenegene_featureId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Gene/{gene_featureId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneRestController controller = (GeneRestController) context.getBean("GeneRestController");

		// TODO Invoke method and Assert return values

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
	 * Returns a mock HttpServletRequest object.
	 *
	 */
	private MockHttpServletRequest getMockHttpServletRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
		return request;
	}

	/**
	 * Returns a mock HttpServletResponse object.
	 *
	 */
	private MockHttpServletResponse getMockHttpServletResponse() {
		return new MockHttpServletResponse();
	}
}