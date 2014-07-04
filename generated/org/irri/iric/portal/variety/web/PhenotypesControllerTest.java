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
 * Unit test for the <code>PhenotypesController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.PhenotypesController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class PhenotypesControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editPhenotypesGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditPhenotypesGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editPhenotypesGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newPhenotypesGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewPhenotypesGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newPhenotypesGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>savePhenotypesGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsavePhenotypesGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/savePhenotypesGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeletePhenotypesGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeletePhenotypesGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeletePhenotypesGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deletePhenotypesGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeletePhenotypesGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deletePhenotypesGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectPhenotypesGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectPhenotypesGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectPhenotypesGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listPhenotypesGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistPhenotypesGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listPhenotypesGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexPhenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexPhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexPhenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectPhenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectPhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectPhenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editPhenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditPhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editPhenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>savePhenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsavePhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/savePhenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newPhenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewPhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newPhenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeletePhenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeletePhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeletePhenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deletePhenotypes()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeletePhenotypes() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deletePhenotypes");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>phenotypesControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetphenotypesControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/phenotypesController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PhenotypesController controller = (PhenotypesController) context.getBean("PhenotypesController");

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