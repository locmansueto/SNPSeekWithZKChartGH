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
 * Unit test for the <code>PhenGcController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.PhenGcController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class PhenGcControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexPhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexPhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexPhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectPhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectPhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectPhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editPhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditPhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editPhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>savePhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsavePhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/savePhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newPhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewPhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newPhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeletePhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeletePhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeletePhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deletePhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeletePhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deletePhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>phengcControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetphengcControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/phengcController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcController controller = (PhenGcController) context.getBean("PhenGcController");

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