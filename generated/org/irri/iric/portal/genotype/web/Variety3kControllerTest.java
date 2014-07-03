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
 * Unit test for the <code>Variety3kController</code> controller.
 *
 * @see org.irri.iric.portal.genotype.web.Variety3kController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/zkspring6-security-context.xml",
		"file:./resources/zkspring6-service-context.xml",
		"file:./resources/zkspring6-dao-context.xml",
		"file:./resources/zkspring6-web-context.xml" })
public class Variety3kControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexVariety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexVariety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectVariety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectVariety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editVariety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editVariety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveVariety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveVariety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newVariety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newVariety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteVariety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteVariety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteVariety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteVariety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>variety3kControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void Getvariety3kControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/variety3kController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kController controller = (Variety3kController) context.getBean("Variety3kController");

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