package org.irri.iric.portal.genotype.dao;

import org.irri.iric.portal.genotype.domain.Variety3k;

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
		"file:./resources/zkspring6-security-context.xml",
		"file:./resources/zkspring6-service-context.xml",
		"file:./resources/zkspring6-dao-context.xml",
		"file:./resources/zkspring6-web-context.xml" })
public class Variety3kDAOTest {
	/**
	 * The DAO being tested, injected by Spring
	 *
	 */
	private Variety3kDAO dataStore;

	/**
	 * Instantiates a new Variety3kDAOTest.
	 *
	 */
	public Variety3kDAOTest() {
	}

	/**
	 * Method to test Variety3k domain object.
	 *
	 */
	@Rollback(false)
	@Test
	public void Variety3k() {
		Variety3k instance = new Variety3k();

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
	public void setDataStore(Variety3kDAO dataStore) {
		this.dataStore = dataStore;
	}
}
