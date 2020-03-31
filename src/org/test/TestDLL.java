package org.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestDLL {

	@Test
	public void testTimer() {
		System.out.println("test");
		System.loadLibrary("jhdf5");
	}

}
