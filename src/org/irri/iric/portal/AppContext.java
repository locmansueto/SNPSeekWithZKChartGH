package org.irri.iric.portal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
//import org.irri.iric.portal.genotype.service.GenotypeFacade;
//import org.irri.iric.portal.genotype.service.GenotypeFacadeImpl;
//import org.irri.iric.portal.genotype.views.Snp2linesHome;
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
	
	/**
	 * directory to write temporary files in server, should be cleaned by cronjob regularly
	 */
	//private static String tempdir = "../webapps/iric-portal/tmp/";
	
	/**
	 * is Amazon Web Service compile?
	 */
	private static final boolean isAWS = false;
	
	private static final boolean isPollux = true;
	
	/**
	 * using the Chado Schema?
	 */
	private static final boolean isChado = false;
	
	/**
	 * is development
	 */
	private static final boolean isDev = true;

    private static ApplicationContext ctx;
    
    /**
     * used for timing processes
     */
    private static long startTime;

    /**
     * get a temporary file name
     * @return
     */
    public static String createTempFilename() {
    	
    	return  Long.toString(  rand.nextLong() ).replace("-","");
    }
    
    /**
     * start process timer
     */
    public static void startTimer() {
    	startTime =  System.currentTimeMillis( );	
    }
    
    /**
     * write period since start/last reset
     * and restart the timer
     * @param report
     */
    public static void resetTimer(String report) {
    	long endTime  =  System.currentTimeMillis( );	
    	System.out.println(endTime- startTime + " ms " + report);
    	startTime=endTime;
    }
    
    
    public static String getTempDir() {
    	if(isAWS)
    		//return  "../webapps/" +  getHostDirectory() + "/tmp/";
    		return "/usr/share/apache-tomcat-7.0.55/webapps/" + getHostDirectory() + "/tmp/";
    	else if(isPollux)
    		return  "/usr/share/apache-tomcat-7.0.42/webapps/" +  getHostDirectory() + "/tmp/";
    	else 
    		// vm-iric-portal
    		return "/opt/tomcat7/webapps/" +  getHostDirectory() + "/tmp/";
    	
    }
    
    /**
     * get domain name (for use in embedded wepapps like JBrowse)
     * @return
     */
    public static String getHostname() {
    	if(isAWS)
    		return "http://oryzasnp.org";
    	else if(isPollux)
    		return "http://pollux:8080";
    	else
    		return "http://172.29.4.26:8080"; 
    	//return "http://localhost";
    }
    
    /**
     * prefix of DB Views to use (legacy or iric)
     * @return
     */
    public static String getViewPrefix() {
    	if(isChado)
    		return "V";
    	else return "VL";
    	
    }
    
    /**
     * directory of webapp in host
     * @return
     */
    public static String getHostDirectory() {
    	if(isDev) 
    		return "iric-portal-dev";
    	else 
    		return "iric-portal";
    }
    
    /**
     * fetch only SNP-Genotypes which having mismatch with the reference
     * else fetch all
     * @return
     */
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
     *  		then  throw exception !
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