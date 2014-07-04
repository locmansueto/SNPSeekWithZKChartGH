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
 * Unit test for the <code>GenotypingController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.GenotypingController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class GenotypingControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editGenotypingSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGenotypingSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGenotypingSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGenotypingSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGenotypingSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGenotypingSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGenotypingSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGenotypingSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGenotypingSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGenotypingSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGenotypingSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGenotypingSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGenotypingSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGenotypingSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGenotypingSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGenotypingSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGenotypingSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGenotypingSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listGenotypingSnps()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistGenotypingSnps() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listGenotypingSnps");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editGenotypingGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGenotypingGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGenotypingGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGenotypingGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGenotypingGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGenotypingGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGenotypingGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGenotypingGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGenotypingGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGenotypingGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGenotypingGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGenotypingGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGenotypingGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGenotypingGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGenotypingGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGenotypingGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGenotypingGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGenotypingGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listGenotypingGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistGenotypingGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listGenotypingGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexGenotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexGenotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGenotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGenotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editGenotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGenotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGenotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGenotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGenotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGenotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGenotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGenotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGenotyping()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGenotyping() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGenotyping");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>genotypingControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetgenotypingControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/genotypingController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GenotypingController controller = (GenotypingController) context.getBean("GenotypingController");

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