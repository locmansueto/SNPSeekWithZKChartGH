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
 * Unit test for the <code>PhenGcRestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.PhenGcRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class PhenGcRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>PhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetPhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/PhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcRestController controller = (PhenGcRestController) context.getBean("PhenGcRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>PhenGcphengc_entnophengc_gid()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetPhenGcphengc_entnophengc_gid() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/PhenGc/{phengc_entno}/{phengc_gid}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcRestController controller = (PhenGcRestController) context.getBean("PhenGcRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>PhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutPhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/PhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcRestController controller = (PhenGcRestController) context.getBean("PhenGcRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>PhenGc()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostPhenGc() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/PhenGc");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcRestController controller = (PhenGcRestController) context.getBean("PhenGcRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>PhenGcphengc_entnophengc_gid()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeletePhenGcphengc_entnophengc_gid() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/PhenGc/{phengc_entno}/{phengc_gid}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenGcRestController controller = (PhenGcRestController) context.getBean("PhenGcRestController");

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