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
 * Unit test for the <code>Variety3kRestController</code> controller.
 *
 * @see org.irri.iric.portal.genotype.web.rest.Variety3kRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/zkspring6-security-context.xml",
		"file:./resources/zkspring6-service-context.xml",
		"file:./resources/zkspring6-dao-context.xml",
		"file:./resources/zkspring6-web-context.xml" })
public class Variety3kRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Variety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Variety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kRestController controller = (Variety3kRestController) context.getBean("Variety3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Variety3kvariety3k_varname()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetVariety3kvariety3k_varname() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Variety3k/{variety3k_varname}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kRestController controller = (Variety3kRestController) context.getBean("Variety3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Variety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Variety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kRestController controller = (Variety3kRestController) context.getBean("Variety3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Variety3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostVariety3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Variety3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kRestController controller = (Variety3kRestController) context.getBean("Variety3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Variety3kvariety3k_varname()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteVariety3kvariety3k_varname() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Variety3k/{variety3k_varname}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Variety3kRestController controller = (Variety3kRestController) context.getBean("Variety3kRestController");

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