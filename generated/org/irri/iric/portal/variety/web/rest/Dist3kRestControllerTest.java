package org.irri.iric.portal.variety.web.rest;

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
 * Unit test for the <code>Dist3kRestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.Dist3kRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class Dist3kRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Dist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Dist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kRestController controller = (Dist3kRestController) context.getBean("Dist3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Dist3kdist3k_nam1dist3k_nam2()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetDist3kdist3k_nam1dist3k_nam2() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Dist3k/{dist3k_nam1}/{dist3k_nam2}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kRestController controller = (Dist3kRestController) context.getBean("Dist3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Dist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Dist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kRestController controller = (Dist3kRestController) context.getBean("Dist3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Dist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Dist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kRestController controller = (Dist3kRestController) context.getBean("Dist3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Dist3kdist3k_nam1dist3k_nam2()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteDist3kdist3k_nam1dist3k_nam2() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Dist3k/{dist3k_nam1}/{dist3k_nam2}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kRestController controller = (Dist3kRestController) context.getBean("Dist3kRestController");

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