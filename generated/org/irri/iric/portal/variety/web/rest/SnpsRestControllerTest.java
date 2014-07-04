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
 * Unit test for the <code>SnpsRestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.SnpsRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class SnpsRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Snpssnps_snpIdgenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetSnpssnps_snpIdgenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps/{snps_snpId}/genotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snpssnps_snpIdgenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostSnpssnps_snpIdgenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps/{snps_snpId}/genotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snpssnps_snpIdgenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutSnpssnps_snpIdgenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps/{snps_snpId}/genotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snpssnps_snpIdgenotypingsgenotyping_snpIdgenotyping_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteSnpssnps_snpIdgenotypingsgenotyping_snpIdgenotyping_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps/{snps_snpId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snpssnps_snpIdgenotypingsgenotyping_snpIdgenotyping_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetSnpssnps_snpIdgenotypingsgenotyping_snpIdgenotyping_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps/{snps_snpId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snpssnps_snpId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetSnpssnps_snpId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps/{snps_snpId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Snpssnps_snpId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteSnpssnps_snpId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Snps/{snps_snpId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnpsRestController controller = (SnpsRestController) context.getBean("SnpsRestController");

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