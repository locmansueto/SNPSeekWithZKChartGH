package org.irri.iric.portal.variety.service;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VarietyFacadeImplTest {

	
	
	
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCountries() {
		//fail("Not yet implemented");
	}

	@Test
	public void testConstructPhylotreeString() {
		
		VarietyFacadeImpl variety = new VarietyFacadeImpl();
		assertNotNull( variety);
		String teststr = "hello world";
		
		//fail("Not yet implemented");
		
		
		assertEquals(variety.constructPhylotree(teststr, "10"), teststr.toUpperCase()) ; 
		
	}

}
