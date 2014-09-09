package org.irri.iric.portal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.genotype.service.GenotypeFacadeImpl;
import org.irri.iric.portal.genotype.views.Snp2linesHome;
import org.springframework.context.ApplicationContext;
import org.zkoss.zkplus.spring.SpringUtil;

/**
 * This class provides application-wide access to the Spring ApplicationContext.
 * The ApplicationContext is injected by the class "ApplicationContextProvider".
 *
 * @author Siegfried Bolz
 */
public class AppContext {

	private static final Log log = LogFactory.getLog(AppContext.class);
	private static final java.util.Random  rand = new   java.util.Random();
	public static String tempdir = "../webapps/iric-portal/tmp/";
	private static final boolean isAWS = true;

    private static ApplicationContext ctx;
    
    private static long startTime;

    public static String createTempFilename() {
    	
    	return  Long.toString(  rand.nextLong() ).replace("-","");
    }
    
    public static void startTimer() {
    	startTime =  System.currentTimeMillis( );	
    }
    public static void resetTimer(String report) {
    	long endTime  =  System.currentTimeMillis( );	
    	System.out.println(endTime- startTime + " ms " + report);
    	startTime=endTime;
    }
    
    
    public static String getHostname() {
    	if(isAWS)
    		return "http://oryzasnp.org";
    	else
    		return "http://pollux";
    	//return "http://localhost";
    }
    
    
    public static boolean isSNPAllvarsFetchMismatchOnly() {
    	return true;
    }
    
    
    /**
     * Injected from the class "ApplicationContextProvider" which is automatically
     * loaded during Spring-Initialization.
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }


    /**
     * Get access to the Spring ApplicationContext from everywhere in your Application.
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
    
    /**
     * Utility to check if object is null (if autowired worked!)
     * 	if null, use  SpringUtil.getBean(name); 
     *  		then AppContext.getApplicationContext().getBean(name);
     *  		then  new as last resort !
     * 
     * @param obj	object to check
     * @param name	bean name
     * @return
     */
    public static Object checkBean(Object obj, String name ) 
    {
    	
    	if (obj==null) {
    		log.debug(name + "==null using static");
    		System.out.println(name + "==null using getApplicationContext");
    		obj = AppContext.getApplicationContext().getBean(name);
    	}   	
    	if (obj==null) {
    		log.debug(name + "==null using SpringUtil");
    		System.out.println(name + "==null using SpringUtil");
    		obj = SpringUtil.getBean(name);
    	}
    	
    	if(obj==null) throw new RuntimeException(name + "==null");
    	
    	/*
    	if (obj==null) {
    		System.out.println(name + "==null using new!");
    		log.debug(name + "==null using new!");
    		Class<?> c;
			try {
				c = Class.forName(name);
	    		Constructor<?> cons;
				try {
					cons = c.getConstructor();
					try {
						obj = cons.newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	if(obj==null) throw new RuntimeException(name + "==null");
    	
    	*/
    	
    	return obj;
    }
    
} // .EOF