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
 * Unit test for the <code>Dist3kController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.Dist3kController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class Dist3kControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexDist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexDist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectDist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectDist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editDist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editDist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveDist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveDist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newDist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newDist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteDist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteDist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteDist3k()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteDist3k() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteDist3k");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>dist3kControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void Getdist3kControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/dist3kController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Dist3kController controller = (Dist3kController) context.getBean("Dist3kController");

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