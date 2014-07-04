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
 * Unit test for the <code>SnpsController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.SnpsController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class SnpsControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editSnpsGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditSnpsGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnpsGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnpsGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewSnpsGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnpsGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnpsGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveSnpsGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnpsGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnpsGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteSnpsGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnpsGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnpsGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteSnpsGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnpsGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnpsGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectSnpsGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnpsGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listSnpsGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistSnpsGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listSnpsGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>snpsControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsnpsControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/snpsController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsController controller = (SnpsController) context.getBean("SnpsController");

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