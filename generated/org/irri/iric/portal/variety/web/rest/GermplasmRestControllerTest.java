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
 * Unit test for the <code>GermplasmRestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.GermplasmRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class GermplasmRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdgenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGermplasmgermplasm_nsftvIdgenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/genotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdgenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGermplasmgermplasm_nsftvIdgenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/genotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdgenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGermplasmgermplasm_nsftvIdgenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/genotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdgenotypingsgenotyping_snpIdgenotyping_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGermplasmgermplasm_nsftvIdgenotypingsgenotyping_snpIdgenotyping_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdgenotypingsgenotyping_snpIdgenotyping_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGermplasmgermplasm_nsftvIdgenotypingsgenotyping_snpIdgenotyping_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/genotypings/{genotyping_snpId}/{genotyping_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdphenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGermplasmgermplasm_nsftvIdphenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/phenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdphenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGermplasmgermplasm_nsftvIdphenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/phenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdphenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGermplasmgermplasm_nsftvIdphenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/phenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdphenotypesesphenotypes_nsftvIdphenotypes_trait()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGermplasmgermplasm_nsftvIdphenotypesesphenotypes_nsftvIdphenotypes_trait() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/phenotypeses/{phenotypes_nsftvId}/{phenotypes_trait}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvIdphenotypesesphenotypes_nsftvIdphenotypes_trait()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGermplasmgermplasm_nsftvIdphenotypesesphenotypes_nsftvIdphenotypes_trait() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}/phenotypeses/{phenotypes_nsftvId}/{phenotypes_trait}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetGermplasmgermplasm_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Germplasmgermplasm_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeleteGermplasmgermplasm_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Germplasm/{germplasm_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmRestController controller = (GermplasmRestController) context.getBean("GermplasmRestController");

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