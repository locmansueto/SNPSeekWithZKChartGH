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
 * Unit test for the <code>Genotyp700Controller</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.Genotyp700Controller
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class Genotyp700ControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editGenotyp700Snp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGenotyp700Snp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGenotyp700Snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGenotyp700Snp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGenotyp700Snp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGenotyp700Snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGenotyp700Snp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGenotyp700Snp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGenotyp700Snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGenotyp700Snp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGenotyp700Snp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGenotyp700Snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGenotyp700Snp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGenotyp700Snp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGenotyp700Snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGenotyp700Snp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGenotyp700Snp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGenotyp700Snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listGenotyp700Snp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistGenotyp700Snp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listGenotyp700Snp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editGenotyp700Var700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGenotyp700Var700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGenotyp700Var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGenotyp700Var700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGenotyp700Var700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGenotyp700Var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGenotyp700Var700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGenotyp700Var700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGenotyp700Var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGenotyp700Var700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGenotyp700Var700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGenotyp700Var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGenotyp700Var700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGenotyp700Var700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGenotyp700Var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGenotyp700Var700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGenotyp700Var700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGenotyp700Var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listGenotyp700Var700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistGenotyp700Var700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listGenotyp700Var700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexGenotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexGenotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGenotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGenotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editGenotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGenotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGenotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGenotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGenotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGenotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGenotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGenotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGenotyp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGenotyp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGenotyp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>genotyp700Controllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void Getgenotyp700Controllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/genotyp700Controller/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Genotyp700Controller controller = (Genotyp700Controller) context.getBean("Genotyp700Controller");

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