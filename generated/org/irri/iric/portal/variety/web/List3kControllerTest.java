package org.irri.iric.portal.variety.web;

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
 * Unit test for the <code>List3kController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.List3kController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class List3kControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexList3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexList3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectList3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectList3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editList3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editList3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveList3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveList3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newList3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newList3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteList3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteList3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteList3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteList3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteList3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>list3kControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void Getlist3kControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/list3kController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		List3kController controller = (List3kController) context.getBean("List3kController");

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