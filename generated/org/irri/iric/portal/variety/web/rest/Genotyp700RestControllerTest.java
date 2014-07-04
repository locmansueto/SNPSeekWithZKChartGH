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
 * Unit test for the <code>Genotyp700RestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.Genotyp700RestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class Genotyp700RestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdsnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotyp700genotyp700_varIdgenotyp700_snpIdsnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdsnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGenotyp700genotyp700_varIdgenotyp700_snpIdsnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdsnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGenotyp700genotyp700_varIdgenotyp700_snpIdsnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdsnp700snp700_id()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGenotyp700genotyp700_varIdgenotyp700_snpIdsnp700snp700_id() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700/{snp700_id}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdsnp700snp700_id()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotyp700genotyp700_varIdgenotyp700_snpIdsnp700snp700_id() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/snp700/{snp700_id}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdvar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotyp700genotyp700_varIdgenotyp700_snpIdvar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdvar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGenotyp700genotyp700_varIdgenotyp700_snpIdvar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdvar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGenotyp700genotyp700_varIdgenotyp700_snpIdvar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdvar700var700_id()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGenotyp700genotyp700_varIdgenotyp700_snpIdvar700var700_id() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700/{var700_id}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpIdvar700var700_id()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotyp700genotyp700_varIdgenotyp700_snpIdvar700var700_id() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}/var700/{var700_id}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGenotyp700genotyp700_varIdgenotyp700_snpId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Genotyp700genotyp700_varIdgenotyp700_snpId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGenotyp700genotyp700_varIdgenotyp700_snpId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Genotyp700/{genotyp700_varId}/{genotyp700_snpId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700RestController controller = (Genotyp700RestController) context.getBean("Genotyp700RestController");

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