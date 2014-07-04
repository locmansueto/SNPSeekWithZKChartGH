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
 * Unit test for the <code>Snp700Controller</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.Snp700Controller
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class Snp700ControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editSnp700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditSnp700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnp700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnp700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewSnp700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnp700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnp700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveSnp700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnp700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnp700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteSnp700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnp700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnp700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteSnp700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnp700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnp700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectSnp700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnp700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listSnp700Genotyp700s()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistSnp700Genotyp700s() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listSnp700Genotyp700s");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexSnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexSnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexSnp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectSnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editSnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditSnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveSnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewSnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteSnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnp700()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteSnp700() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnp700");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>snp700Controllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void Getsnp700Controllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/snp700Controller/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		Snp700Controller controller = (Snp700Controller) context.getBean("Snp700Controller");

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