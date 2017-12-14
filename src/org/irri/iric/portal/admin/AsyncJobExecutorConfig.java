package org.irri.iric.portal.admin;

import java.util.concurrent.Executor;

import org.irri.iric.portal.AppContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration("AsyncJobExecutorConfig")
@EnableAsync
public class AsyncJobExecutorConfig {

	private Executor threadPoolTaskExecutor;
	
	@Bean(name="threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		
		ThreadPoolTaskExecutor ex= new ThreadPoolTaskExecutor();
		
		if(AppContext.isAWSBeanstalk()) {
			ex.setCorePoolSize(4);
			ex.setMaxPoolSize(10);
		} else if(AppContext.isAWS()) {
			ex.setCorePoolSize(2);
			ex.setMaxPoolSize(2);
		} else if(AppContext.isPollux()) {
			ex.setCorePoolSize(3);
			ex.setMaxPoolSize(5);
		} 
		ex.setQueueCapacity(5);
		threadPoolTaskExecutor=ex;
		return ex;
    }

	@Bean(name="simpleAsyncTaskExecutor")
	public Executor simpleAsyncTaskExecutor() {
		SimpleAsyncTaskExecutor ex = new SimpleAsyncTaskExecutor();
		ex.setConcurrencyLimit(10);
        return ex;
    }

	public Executor getThreadPoolTaskExecutor() {
		return threadPoolTaskExecutor;
	}
	
	
	

}
