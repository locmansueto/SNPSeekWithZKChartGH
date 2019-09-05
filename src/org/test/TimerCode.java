package org.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TimerCode {

	@Test
	public void testTimer() {
		long startTime = System.nanoTime();
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		
		System.out.println(TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS));
	}
}
