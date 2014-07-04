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
 * Unit test for the <code>Var700Controller</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.Var700Controller
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class Var700ControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editVar700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditVar700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editVar700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newVar700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewVar700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newVar700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveVar700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveVar700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveVar700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteVar700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteVar700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteVar700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteVar700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteVar700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteVar700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectVar700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectVar700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectVar700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listVar700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistVar700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listVar700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexVar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexVar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexVar700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectVar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectVar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectVar700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editVar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditVar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editVar700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveVar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveVar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveVar700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newVar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewVar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newVar700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteVar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteVar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteVar700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteVar700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteVar700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteVar700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>var700Controllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void Getvar700Controllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/var700Controller/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Var700Controller controller = (Var700Controller) context.getBean("Var700Controller");

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