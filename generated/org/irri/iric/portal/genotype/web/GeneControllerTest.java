package org.irri.iric.portal.genotype.web;

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
 * Unit test for the <code>GeneController</code> controller.
 *
 * @see org.irri.iric.portal.genotype.web.GeneController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/zkspring7-security-context.xml",
		"file:./resources/zkspring7-service-context.xml",
		"file:./resources/zkspring7-dao-context.xml",
		"file:./resources/zkspring7-web-context.xml" })
public class GeneControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexGene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexGene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editGene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGene()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGene() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGene");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>geneControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetgeneControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/geneController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GeneController controller = (GeneController) context.getBean("GeneController");

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