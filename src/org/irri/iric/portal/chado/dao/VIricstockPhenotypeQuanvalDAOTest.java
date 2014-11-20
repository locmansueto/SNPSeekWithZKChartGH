package org.irri.iric.portal.chado.dao;

import org.irri.iric.portal.chado.domain.VIricstockPhenotypeQuanval;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.annotation.Rollback;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.springframework.transaction.annotation.Transactional;

/**
 * Class used to test the basic Data Store Functionality
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = {
		"file:./resources/iric_prod_crud-security-context.xml",
		"file:./resources/iric_prod_crud-service-context.xml",
		"file:./resources/iric_prod_crud-dao-context.xml",
		"file:./resources/iric_prod_crud-web-context.xml" })
public class VIricstockPhenotypeQuanvalDAOTest {
	/**
	 * The DAO being tested, injected by Spring
	 *
	 */
	private VIricstockPhenotypeQuanvalDAO dataStore;

	/**
	 * Instantiates a new VIricstockPhenotypeQuanvalDAOTest.
	 *
	 */
	public VIricstockPhenotypeQuanvalDAOTest() {
	}

	/**
	 * Method to test VIricstockPhenotypeQuanval domain object.
	 *
	 */
	@Rollback(false)
	@Test
	public void VIricstockPhenotypeQuanval() {
		VIricstockPhenotypeQuanval instance = new VIricstockPhenotypeQuanval();

		// Test create				
		// TODO: Populate instance for create.  The store will fail if the primary key fields are blank.				

		// store the object
		dataStore.store(instance);

		// Test update
		// TODO: Modify non-key domain object values for update

		// update the object
		dataStore.store(instance);

		// Test delete
		dataStore.remove(instance);

	}

	/**
	 * Method to allow Spring to inject the DAO that will be tested
	 *
	 */
	@Autowired
	public void setDataStore(VIricstockPhenotypeQuanvalDAO dataStore) {
		this.dataStore = dataStore;
	}
}
