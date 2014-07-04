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
 * Unit test for the <code>GenotypingRestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.GenotypingRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class GenotypingRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdsnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotypinggenotyping_snpIdgenotyping_nsftvIdsnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdsnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGenotypinggenotyping_snpIdgenotyping_nsftvIdsnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdsnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGenotypinggenotyping_snpIdgenotyping_nsftvIdsnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdsnpssnps_snpId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGenotypinggenotyping_snpIdgenotyping_nsftvIdsnpssnps_snpId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps/{snps_snpId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdsnpssnps_snpId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotypinggenotyping_snpIdgenotyping_nsftvIdsnpssnps_snpId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/snps/{snps_snpId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdgermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotypinggenotyping_snpIdgenotyping_nsftvIdgermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdgermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGenotypinggenotyping_snpIdgenotyping_nsftvIdgermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdgermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGenotypinggenotyping_snpIdgenotyping_nsftvIdgermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdgermplasmgermplasm_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGenotypinggenotyping_snpIdgenotyping_nsftvIdgermplasmgermplasm_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm/{germplasm_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvIdgermplasmgermplasm_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotypinggenotyping_snpIdgenotyping_nsftvIdgermplasmgermplasm_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}/germplasm/{germplasm_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotypinggenotyping_snpIdgenotyping_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotypinggenotyping_snpIdgenotyping_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGenotypinggenotyping_snpIdgenotyping_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyping/{genotyping_snpId}/{genotyping_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingRestController controller = (GenotypingRestController) context.getBean("GenotypingRestController");

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