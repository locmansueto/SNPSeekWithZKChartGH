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
 * Unit test for the <code>Names700Controller</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.Names700Controller
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class Names700ControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexNames700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexNames700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexNames700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectNames700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectNames700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectNames700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editNames700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditNames700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editNames700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveNames700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveNames700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveNames700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newNames700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewNames700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newNames700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteNames700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteNames700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteNames700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteNames700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteNames700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteNames700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>names700Controllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void Getnames700Controllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/names700Controller/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Names700Controller controller = (Names700Controller) context.getBean("Names700Controller");

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