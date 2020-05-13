package org.test;

import org.junit.Test;

public class testTimer {

	@Test
	public void testTimer1() throws InterruptedException {
		AWSTimer counter = new AWSTimer("i-0c620d6cf53fe8afe");
		counter.start();
		System.out.println("Starting..");
		Thread.sleep(5000);
		counter.getInstanceState();
		Thread.sleep(300000);
		counter.getInstanceState();
		Thread.sleep(5000);
		counter.getInstanceState();
		counter.stopInstance();
		System.out.println("STOPPING..");
		Thread.sleep(5000);
		counter.getInstanceState();
		Thread.sleep(300000);
		counter.getInstanceState();
		Thread.sleep(1800000);

	}

}
