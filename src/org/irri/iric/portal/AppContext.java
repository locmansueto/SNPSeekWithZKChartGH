package org.irri.iric.portal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	private static final boolean isAWS = true;
	private static final boolean isVMIRRI = false;
	private static final boolean isPollux = false;
	private static final boolean isLocalhost = false;
	
	/**
	 * is development
	 */
	private static final boolean isDev = false;



	/**
	 * using the Chado Schema?
	 */
	private static final boolean isChado = true;
	
	
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
     * default core/all SNP set
     */
    public static boolean isCore() {
    	return false;
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
    	else  if(isVMIRRI)
    		// vm-iric-portal
    		return "/opt/tomcat7/webapps/" +  getHostDirectory() + "/tmp/";
    	else if(isLocalhost)
    		return "E:/MyEclipse for Spring 2014/plugins/com.genuitec.eclipse.easie.tomcat7.myeclipse_11.5.0.me201310302042/tomcat/bin/iric-portal/tmp/";
    	else if(isPollux)
    		return  "/usr/share/apache-tomcat-7.0.42/webapps/" +  getHostDirectory() + "/tmp/";
    	
    	return null;
    }
    
    /**
     * get domain name (for use in embedded wepapps like JBrowse)
     * @return
     */
    public static String getHostname() {
    	if(isAWS)
    		return "http://oryzasnp.org";
    	else if(isVMIRRI)
    		return "http://202.123.56.26:8080";
    	else if(isPollux)
    		return "http://pollux:8080";
    	else
    		return "http://172.29.4.26:8080"; 
    	//return "http://localhost";
    }
    
    
    public static String getFlatfilesDir() {
    	if(isLocalhost)
    		return "E:/My Document/Transfer/3kcore_alleles/";
    	else if(isAWS)
    		return "/data/lmansueto/iric-portal-files/";
    	else
    		return "/home/lmansueto/iric-portal-files/";
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
    	if(isDev) {
    		if(isAWS)
    			return "iric-portal-test";
    		else
    			return "iric-portal-dev";
    	}
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
     * Slice a large set into groups of 1000, (for oracle IN has limit 1000)
     * @param vars
     * @return
     */
    public static Set[] setSlicer(Set vars) {
	    if(vars.size()>2000)
		{	
			java.util.Set set1 = new HashSet();
			Iterator it=vars.iterator();
			for(int i=0; i<1000; i++)
				set1.add(  it.next() );
			java.util.Set set2 = new HashSet();
			for(int i=0; i<1000; i++)
				set2.add(  it.next() );
			java.util.Set set3 = new HashSet();
			while(it.hasNext())
				set3.add(it.next());
			
			return new Set[] {set1, set2, set3};
			
		} else if(vars.size()>1000)
		{
			java.util.Set set1 = new HashSet();
			Iterator it=vars.iterator();
			for(int i=0; i<1000; i++)
				set1.add(  it.next() );
			java.util.Set set2 = new HashSet();
			while(it.hasNext())
				set2.add(it.next());

			return new Set[] {set1, set2};
			
		} else
			return new Set[] {vars};
    }
    
    public static List setSlicerIds(Set varIds) {
    	List<String> listVaridSets= new ArrayList();
		if(varIds!=null && !varIds.isEmpty()) {
			// create varids list
			Set[] sets = AppContext.setSlicer(varIds);
			
			for(int iset=0; iset<sets.length; iset++) {
				
				StringBuffer buff = new StringBuffer();
				Iterator<BigDecimal>  itSet = sets[iset].iterator();
				while(itSet.hasNext()) {
					buff.append( itSet.next() );
					if(itSet.hasNext()) buff.append(",");
				}
				listVaridSets.add( buff.toString() );
			}
			
		}
		return listVaridSets;
    }
    
    
    /**
     * if #mismatch=#snps, dist=Inf. Use this value instead
     * @return
     */
    public static int getMaxPhylodistance(){
    	return 5;
    }
    
    
    public static void displayCollection(String name, Collection col) {
    	StringBuffer buff= new StringBuffer();
    	buff.append(name + ": ");
    	col=new TreeSet(col);
    	Iterator it=col.iterator();
    	while(it.hasNext()) { buff.append(it.next());
    	if(it.hasNext()) buff.append(",");
    	}
    	
    	System.out.println( buff );
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