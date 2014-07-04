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
 * Unit test for the <code>List3kRestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.List3kRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class List3kRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>List3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/List3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kRestController controller = (List3kRestController) context.getBean("List3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>List3klist3k_irisUniqueId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetList3klist3k_irisUniqueId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/List3k/{list3k_irisUniqueId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kRestController controller = (List3kRestController) context.getBean("List3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>List3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/List3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kRestController controller = (List3kRestController) context.getBean("List3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>List3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/List3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kRestController controller = (List3kRestController) context.getBean("List3kRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>List3klist3k_irisUniqueId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteList3klist3k_irisUniqueId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/List3k/{list3k_irisUniqueId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kRestController controller = (List3kRestController) context.getBean("List3kRestController");

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