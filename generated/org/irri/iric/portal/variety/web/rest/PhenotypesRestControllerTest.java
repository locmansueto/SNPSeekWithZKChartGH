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
 * Unit test for the <code>PhenotypesRestController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.rest.PhenotypesRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class PhenotypesRestControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>Phenotypesphenotypes_nsftvIdphenotypes_traitgermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetPhenotypesphenotypes_nsftvIdphenotypes_traitgermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypesphenotypes_nsftvIdphenotypes_traitgermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostPhenotypesphenotypes_nsftvIdphenotypes_traitgermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypesphenotypes_nsftvIdphenotypes_traitgermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutPhenotypesphenotypes_nsftvIdphenotypes_traitgermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypesphenotypes_nsftvIdphenotypes_traitgermplasmgermplasm_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeletePhenotypesphenotypes_nsftvIdphenotypes_traitgermplasmgermplasm_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm/{germplasm_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypesphenotypes_nsftvIdphenotypes_traitgermplasmgermplasm_nsftvId()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetPhenotypesphenotypes_nsftvIdphenotypes_traitgermplasmgermplasm_nsftvId() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}/germplasm/{germplasm_nsftvId}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetPhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypesphenotypes_nsftvIdphenotypes_trait()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetPhenotypesphenotypes_nsftvIdphenotypes_trait() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PutPhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void PostPhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>Phenotypesphenotypes_nsftvIdphenotypes_trait()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void DeletePhenotypesphenotypes_nsftvIdphenotypes_trait() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/Phenotypes/{phenotypes_nsftvId}/{phenotypes_trait}");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesRestController controller = (PhenotypesRestController) context.getBean("PhenotypesRestController");

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