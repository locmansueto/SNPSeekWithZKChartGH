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
 * Unit test for the <code>GermplasmController</code> controller.
 *
 * @see org.irri.iric.portal.variety.web.GermplasmController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./resources/dev_crud_maker-security-context.xml",
		"file:./resources/dev_crud_maker-service-context.xml",
		"file:./resources/dev_crud_maker-dao-context.xml",
		"file:./resources/dev_crud_maker-web-context.xml" })
public class GermplasmControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editGermplasmGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGermplasmGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGermplasmGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGermplasmGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGermplasmGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGermplasmGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGermplasmGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGermplasmGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGermplasmGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGermplasmGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGermplasmGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGermplasmGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGermplasmGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGermplasmGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGermplasmGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGermplasmGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGermplasmGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGermplasmGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listGermplasmGenotypings()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistGermplasmGenotypings() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listGermplasmGenotypings");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editGermplasmPhenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGermplasmPhenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGermplasmPhenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGermplasmPhenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGermplasmPhenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGermplasmPhenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGermplasmPhenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGermplasmPhenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGermplasmPhenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGermplasmPhenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGermplasmPhenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGermplasmPhenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGermplasmPhenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGermplasmPhenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGermplasmPhenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGermplasmPhenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGermplasmPhenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGermplasmPhenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listGermplasmPhenotypeses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetlistGermplasmPhenotypeses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listGermplasmPhenotypeses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteGermplasm()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteGermplasm() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteGermplasm");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>germplasmControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetgermplasmControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/germplasmController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		GermplasmController controller = (GermplasmController) context.getBean("GermplasmController");

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