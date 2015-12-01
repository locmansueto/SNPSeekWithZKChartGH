package org.irri.iric.portal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.zkoss.zkplus.spring.SpringUtil;


import javax.servlet.FilterConfig;
import javax.servlet.http.*;


/**
 * This class provides application-wide access to the Spring ApplicationContext.
 * The ApplicationContext is injected by the class "ApplicationContextProvider".
 * 
 * Used to define global parameters, 
 * will be updated from *.xml file later 
 * 
 */

public class AppContext {

	private static final Log log = LogFactory.getLog(AppContext.class);
	
	private static ApplicationContext ctx;

	static enum WEBSERVER { LOCALHOST, AWS, AWSDEV, VMIRRI, POLLUX, ASTI };
	static enum COMPILETYPE { PROD, DEV, TEST};
	
	/**
	 * random number generator 
	 */
	private static final java.util.Random  rand = new   java.util.Random();
	
	
	// set target webserver and compile type
	static WEBSERVER webserver =  AppContext.WEBSERVER.LOCALHOST;
	//static WEBSERVER webserver =  AppContext.WEBSERVER.POLLUX;
	static COMPILETYPE compiletype =  AppContext.COMPILETYPE.PROD;

	
	/**
	 * is using limited (uploaded for sharing) data
	 * Source code and data downloaded from Bitbucket repository should set this to true
	 */
	static boolean isSharedData = true;
	
	
	/**
	 * config from XML file has been loaded
	 */
	static boolean configloaded = false;

	/**
	 * using the Chado Schema?
	 */
	private static final boolean isChado = true;
	
    
    /**
     * used for timing processes
     */
    private static long startTime=0;
    private static long startTimeDate=0;
    
    static String  defaultorganism;
    static String  flatfilesdir;
    static String  hostdirectory;
    static String  tempdir;
    static String  hostname;
	 
    static String  pathtolocalblast;
    static String  pathtolocalblastdata;
    static String  webappdirectory;
    
    public static Pattern patRemoveZeroes = Pattern.compile("\\.?0*");
    
    
    public AppContext() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    // ********************************  DEPLOYMENT-SPECIFIC SETTINGS (change these to match server congiguration ) ********************************* 
    
    /**
     * Directory to write temp files accessible to the internet
     * but using server file system
     * ex. /path/to/tomcat/webapps/temp
     * @return
     */

    public static String getTempDir() {
    	
    	if( tempdir!=null) return tempdir;    
    	
    	if(isAWS() || isAWSdev())
    		//return  "../webapps/" +  getHostDirectory() + "/tmp/";
    		return "/usr/share/apache-tomcat-7.0.55/webapps/temp/";
    	//else if(isAWSdev())
    		//return  "../webapps/" +  getHostDirectory() + "/tmp/";
    	//	return "/usr/share/apache-tomcat-7.0.55/webapps/" + getHostDirectory() + "/tmp/";
    	else  if(isVMIRRI())
    		// vm-iric-portal
    		return "/opt/tomcat7/webapps/temp/";
    	else if(isLocalhost())
    		return "E:/MyEclipse for Spring 2014/plugins/com.genuitec.eclipse.easie.tomcat7.myeclipse_11.5.0.me201310302042/tomcat/bin/temp/";
    	else if(isPollux())
    		return  "/usr/share/apache-tomcat-7.0.42/webapps/temp/";
    	
    	return "/usr/share/apache-tomcat/webapps/temp/";
    }
    
    /**
     * directory of SNP-Seek data files in the server (using server file system) 
     * @return
     */
    public static String getFlatfilesDir() {
    	if( flatfilesdir!=null) return flatfilesdir;    
    	
    	if(isLocalhost())
    		return "E:/My Document/Transfer/3kcore_alleles/";
    	else if(isAWS() || isAWSdev())
    		return "/data/lmansueto/iric-portal-files/";
    	else if(isASTI())
    		return "/home/iric/iric-portal-files/";
    	else http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=2456312
    		return "/home/lmansueto/iric-portal-files/";
    }
    

    /**
     * get host name (for use in embedded wepapps like JBrowse), or webserver IP address
     * @return
     */
    public static String getHostname() {
    	
    	if(hostname!=null) return hostname;
    	
    	if(isAWS())
    		return "http://oryzasnp.org";
    	else if(isAWSdev())
    		return "http://54.255.100.88";
    	else if(isVMIRRI())
    		return "http://202.123.56.26:8080";
    	else if(isPollux())
    		//return "http://pollux:8080";
    		return "http://172.29.4.215:8080";
    	else if(isASTI())
    		return "http://202.90.159.240:8080";
    	else
    		return "http://172.29.4.26:8080"; 
    	//return "http://localhost";
    }
    
    /**
     * directory of webapp folder in host
     * @return
     */
    public static String getHostDirectory() {
    	if( hostdirectory!=null) return hostdirectory;    
    	
    	if(isDev()) {
    		if(isAWS())
    			return "iric-portal-test";
    		else if(isAWSdev())
    			return "iric-portal";
    		else
    			return "iric-portal-dev";
    	}
    	else if(isTest()) 
    		return "iric-portal-test";
    	else 
    		return "iric-portal";
    }
    
    
    /**
     * JBrowse host directory
     * @return
     */
    public static String getJbrowseDir() {
    	if(isUsingsharedData())
    		return "jbrowse";
    	else return "jbrowse-dev2";
    }
    
    /**
     * Path to BLAST program
     * @return
     */
    public static String getPathToLocalBlast() {
    	if( pathtolocalblast!=null) return pathtolocalblast;    

    	if(isAWS() || isAWSdev())
    		return "/home/ubuntu/lmansueto/ncbi-blast/bin/";
    	else
    		return "/home/lmansueto/ncbi-blast/bin/";
    }

    
    /**
     * Path to BLAST database
     * @return
     */
    public static String getPathToLocalBlastData() {
    	if( pathtolocalblastdata!=null) return pathtolocalblastdata;    
    	if(isAWS() || isAWSdev())
    		return "/home/ubuntu/lmansueto/ncbi-blast/iric-portal/";
    	else	
    		return "/home/lmansueto/ncbi-blast/iric-portal/";
    }
    
    /**
     * Path to BLAST server
     * @return
     */
    public static String getBlastServer() {
    	if(isLocalhost() || isPollux())
    		return "http://pollux:8080/iric-portal-dev";
    	else if(isAWS()) {
    		return "http://oryzasnp.org/iric-portal";
    	}
    	return "";
    }
    

    /**
     * Path to VCF2Fasta converter program
     * @return
     */
    public static String getPathToVCF2FastaGenerator() {
    	return  getFlatfilesDir() + "getvcfseq/getSeqVCFaws.pl";
    	
    }
    
    // ***************************************** DATA-RELATED CONIGURATION *****************************************
    
    
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
     * Ignore heterozygous Indels
     * @return
     */
    public static boolean isIgnoreHeteroIndels() {
    	return false;
    }
    
    /**
     *  Using only downloaded shared data. Will disable some functions
     *  that use the huge data.
     * @return
     */
    public static boolean isUsingsharedData() {
    	return isSharedData;
    }
    
    /**
     * SNP data set to use
     * @return
     */
    public static String getSNPSet() {
						    	//return SnpsAllvarsPosDAO.DATASET_SNPINDELV2;
						    	//return SnpsAllvarsPosDAO.DATASET_SNPINDELV1;
    	return "SNP v2 IUPAC"; //SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC;
    }
    
    /**
     * Use DAO beans with given suffix
     * @return
     */
    public static String getDAOBeanSuffix() {
    	return suffixDAOBean;
    }
    
	//static String suffixDAOBean = "Postges";
	static String suffixDAOBean = "";


    /**
     * Launch in AWS
     * @return
     */
    public static boolean isAWS() {
    	return webserver==AppContext.WEBSERVER.AWS;
    }
    
    /**
     * Launch in AWS-dev
     * @return
     */
	public static boolean isAWSdev() {
		return webserver==AppContext.WEBSERVER.AWSDEV;
	}
	
    /**
     * Launch in IRRI VM
     * @return
     */
	public static boolean isVMIRRI() {
		return webserver==AppContext.WEBSERVER.VMIRRI;
	}
	
    /**
     * Launch in Pollux
     * @return
     */
	public static boolean isPollux() {
		return webserver==AppContext.WEBSERVER.POLLUX;
	}
	
	/**
	 * Launced in ASTI
	 * @return
	 */
	public static boolean isASTI() {
		return webserver==AppContext.WEBSERVER.ASTI;
	}
	
	/**
	 * localhost
	 * @return
	 */
	public static boolean isLocalhost() {
		return webserver==AppContext.WEBSERVER.LOCALHOST;
	}
	
	/**
	 * is development version
	 */
	public static boolean isDev() {
		return compiletype==AppContext.COMPILETYPE.DEV;
	}

	/**
	 * is test version
	 * @return
	 */
	public static boolean isTest() {
		return compiletype==AppContext.COMPILETYPE.TEST;
	}
    

	/**
	 * server is windows
	 * @return
	 */
	public static boolean isWindows() {
    	return isLocalhost();
    }
    
	/**
	 * server in IRRI LAN
	 * @return
	 */
    public static boolean isIRRILAN() {
    	return  (isLocalhost() && !isUsingsharedData() )  || isPollux() || isVMIRRI();
    }
    

    /**
     * generate a temporary file name
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
    	startTimeDate = new Date().getTime();
    }
    
    /**
     * default core/all SNP set
     */
    public static boolean isCore() {
    	return false;
    }
    
    /**
     * Factor of region end-stop to include in gff
     * @return
     */
    public static int getJBrowseMargin() {
    	return 2;
    }
    
    /**
     * write period since start/last reset
     * and restart the timer
     * @param report
     */
    public static void resetTimer(String report) {
    	long endTime  = System.currentTimeMillis();  	
    	long endTimeDate  = new Date().getTime();  
    	debug( "TIMER: " + (endTime- startTime) + " ms (system),  " + ( endTimeDate-startTimeDate) + " ms (date) : " + report);
    	startTime=endTime;
    	startTimeDate = endTimeDate;
    }
    

    /**
     * webapp directory
     * @return
     */
    public static String getWebappdiectory() {
    	
    	
    	//also check: http://stackoverflow.com/questions/1521957/where-how-to-setup-configuration-resources-for-tomcat-war-files
    	// http://archive.oreilly.com/pub/a/java/archive/tomcat-tips.html
    		
    	try {
		    File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
		    File propertyFile = new File( catalinaBase, "webapps/" + getHostDirectory() + "strsproperties/strs.properties" );
		    InputStream inputStream = new FileInputStream( propertyFile );
		    
		    
    	} catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return "";
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
    public static Set[] setSlicer(Set vars, int size) {
    		int nSlices = vars.size()/size + 1; 
    		Set slicedsets[] = new HashSet[nSlices];
    		for(int iset=0; iset<nSlices; iset++) {
    			slicedsets[iset] = new HashSet(); 
    		}
			Iterator it=vars.iterator();
			
			int icount=0;
			while(it.hasNext()) {
				slicedsets[icount/size].add(it.next()); 
				icount++;
			}
			return slicedsets;
    }
    
    
    public static Set[] setSlicer(Set vars) {
    	if(vars.size()>3000) {
    		int nSlices = vars.size()/1000 + 1; 
    		Set slicedsets[] = new HashSet[nSlices];
    		for(int iset=0; iset<nSlices; iset++) {
    			slicedsets[iset] = new HashSet(); 
    		}
			Iterator it=vars.iterator();
			
			int icount=0;
			while(it.hasNext()) {
				slicedsets[icount/1000].add(it.next()); 
				icount++;
			}
			return slicedsets;
    	}
    	
    	
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
    
    public static List setStringSlicer(Set varIds, boolean isQuoted, boolean toUpper) {
    	List<String> listVaridSets= new ArrayList();
		if(varIds!=null && !varIds.isEmpty()) {
			// create varids list
			Set[] sets = AppContext.setSlicer(varIds);
			
			for(int iset=0; iset<sets.length; iset++) {
				
				StringBuffer buff = new StringBuffer();
				Iterator<String>  itSet = sets[iset].iterator();
				while(itSet.hasNext()) {
					String s = itSet.next();
					if(toUpper) s= s.toUpperCase();
					if(isQuoted)
						buff.append("'" +  s + "'" );
					else
						buff.append( s );
					if(itSet.hasNext()) buff.append(",");
				}
				listVaridSets.add( buff.toString() );
			}
			
		}
		return listVaridSets;
    }

    
    /**
     * Convert collection to csv
     * @param col
     * @return
     */
    public static String toCSV(Collection col) {
		StringBuffer buff = new StringBuffer();
		Iterator itSet = col.iterator();
		while(itSet.hasNext()) {
			buff.append( itSet.next() );
			if(itSet.hasNext()) buff.append(",");
		}
		return  buff.toString();
    	
    }
    
    
    /**
     * if #mismatch=#snps, dist=Inf. Use this value instead
     * @return
     */
    public static int getMaxPhylodistance(){
    	return 5;
    }
    

    /**
     * Maximum region length in SNP Universe query
     * @return
     */
    public static int getMaxlengthUni() {
    	if(isDev()|| isTest())
    		return 1000000;
    	else return 50000;
    }

    /**
     * Maximum region length in Core SNPs query
     * @return
     */
    public static int getMaxlengthCore() {
    	if(isDev() || isTest())
    		return 10000000;
    	//return 2000000;
    	return 1000000;
    }
    
    /**
     * Maximum region length in variety comparison SNPs query
     * @return
     */
    public static int getMaxlengthPairwise() {
    	return Integer.MAX_VALUE;
    }

    /**
     * Default organism
     * @return
     */
    public static String getDefaultOrganism() {
    	if(defaultorganism!=null) return defaultorganism;
    	
    	//return "rice";
    	return "Japonica nipponbare";
    	//return "Japonica Nipponbare";
    }
    
    /**
     * Message logger used by the webapp, for easy maintenance and change of loggers
     * @param msg
     */
    public static void logger(StringBuffer msg) {
    	logger(msg.toString());
    }

    public static void logger(String msg) {
    	System.out.println(msg);
    }
    
    // display on expected error
    public static void error(String msg) {
    	logger(msg);
    }
    
    // display on expected warning
    public static void warning(String msg) {
    	logger(msg);
    }

    // display in debug mode
    public static void debug(String msg) {
    	logger(msg);
    }

    // display in production mode
    public static void info(String msg) {
    	logger(msg);
    }

    /**
     * Display the elements in a collection
     * @param name
     * @param col
     */
    public static void displayCollection(String name, Collection col) {
    	StringBuffer buff= new StringBuffer();
    	buff.append(name + ": ");
    	col=new TreeSet(col);
    	Iterator it=col.iterator();
    	while(it.hasNext()) { buff.append(it.next());
    	if(it.hasNext()) buff.append(",");
    	}
    	logger( buff );
    }
    

    /**
     * Guess the chromosome number from contig name
     * @param chr
     * @return
     */
    public static String guessChrFromString(String chr) {
    	return chr.toUpperCase().replace("CHR0", "").replace("CHR", "");
    }


    /**
     * Generate list of uppercase, lowercase, blanks for use in UI listboxes
     * @param col initial list
     * @param upperlower create uppercase and lower case
     * @param addBlank  add blank in first line
     * @return
     */
    public static List createUniqueUpperLowerStrings(Collection col, boolean upperlower, boolean addBlank) {
    	
    	List newlist = new ArrayList(); 
    	if(addBlank) newlist.add("");
    	Iterator<String> it=col.iterator();
    	while(it.hasNext()) {
    		String item = it.next();
    		if(item==null) continue;
    		if(upperlower) {
    		newlist.add( item.toLowerCase());
    		newlist.add( item.toUpperCase());
    		} else {
    			newlist.add(item);
    		}
    	}
    	return newlist;
    }
    
    /**
     * Replace every occurrence of mapReplace.key in instr with mapReplace.value
     * @param instr
     * @param mapReplace
     * @return
     */
    public static String replaceString(String instr, Map<String,String> mapReplace) {
    	Iterator<String> itRep = mapReplace.keySet().iterator();
    	while(itRep.hasNext()) {
    		String rep = itRep.next();
    		instr = instr.replaceAll(rep,  mapReplace.get(rep));
    	}
    	return instr;
    }
    
    /**
     * Convert SQL CLOB field to string
     * @param clb
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static String clobStringConversion(Clob clb) throws IOException, SQLException
    {
      if (clb == null)
    	  return  "";
            
      StringBuffer str = new StringBuffer();
      String strng;
      BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());
      while ((strng=bufferRead .readLine())!=null)
       str.append(strng);
      return str.toString();
    }        
    
    
    /**
     * Log the webserver status
     */
    public static void logSystemStatus() {
    
    	Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(  
                OperatingSystemMXBean.class);  
        
        
        sb.append("free memory kb: " + format.format(freeMemory / 1024) + ";");
        sb.append("allocated memory kb: " + format.format(allocatedMemory / 1024) + ";");
        sb.append("max memory kb: " + format.format(maxMemory / 1024) + ";");
        sb.append("total free memory kb: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + ";");
        sb.append("system load average: " + osBean.getSystemLoadAverage() +";");
        sb.append("avail processors: " + osBean.getAvailableProcessors() +";");
        
        
        org.zkoss.zk.ui.util.Statistic zkmonitor = new  org.zkoss.zk.ui.util.Statistic();
        sb.append("active desktops: " +  zkmonitor.getActiveDesktopCount() +";");
        sb.append("active sessions: " +  zkmonitor.getActiveSessionCount() +";");
        sb.append("active update: " +  zkmonitor.getActiveUpdateCount() +"\n");
        
        info( sb.toString() );
    	
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
    	if(name.endsWith("DAO")) name = name +  suffixDAOBean;
    	
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
    
 
    
    public static String getFastqcURL() {
    	return "http://oryzasnp.org/3kfastqc/";
    }
    public static String getFastqURL() {
    	return "http://www.ncbi.nlm.nih.gov/biosample/?term=";
    }
    
    public static String getBamURL(String boxcode) {
    	return "https://s3.amazonaws.com/3kricegenome/Nipponbare/" + boxcode.trim().replace(" ","_") + ".realigned.bam";
    }
    public static String getVcfURL(String boxcode) {
    	return "https://s3.amazonaws.com/3kricegenome/Nipponbare/" + boxcode.trim().replace(" ","_") + ".snp.vcf.gz";
    }
    
  

    public static String getJbrowseContigSuffix() {
    	return "";
    }

    /**
     * Display array of strings
     * @param allelesstr
     */
	public static void displayStringArray(String allelesstr[]) {
		StringBuffer b=new StringBuffer();
		for(int i=0; i<allelesstr.length; i++) b.append(allelesstr[i]).append(", ");
		debug(b.toString());
	}
	
    /**
     * Default JBrowse tracks in genome browser
     * @return
     */
    public static String getJBrowseDefaulttracks() {
    	return "DNA,msu7gff,msu7snpsv2,msu7indelsv2";
    }

	/**
	 * Wait until file exists
	 * @param string
	 */
	public static void waitForFile(String string) {
		// TODO Auto-generated method stub
		File newfile=new File(string);
		while(!newfile.exists() && newfile.length()>0) {
			try {
				Thread.sleep(5000);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		AppContext.debug("file " + string + " ready");
	}

	/**
	 * get IUPAC symbol for nucleotides
	 * @param alleles
	 * @return
	 */
	public static String getIUPAC(String alleles) {
		if(!alleles.contains("/")) {
			if(alleles.length()==1) 
				return alleles;
			else if(alleles.length()==0)
				return "N";
			else throw new RuntimeException("Invalid Allele " + alleles);
		}
		if(alleles.equals("A/G") || alleles.equals("G/A")) return "R";
		else if(alleles.equals("A/T") || alleles.equals("T/A")) return "W";
		else if(alleles.equals("A/C") || alleles.equals("C/A")) return "M";
		else if(alleles.equals("C/G") || alleles.equals("G/C")) return "S";
		else if(alleles.equals("C/T") || alleles.equals("T/C")) return "Y";
		else if(alleles.equals("G/T") || alleles.equals("T/G")) return "K";
		else {
			//AppContext.debug( "Invalid Allele " + alleles + " , using ?" );
			//throw new RuntimeException("Invalid Allele " + alleles);
			return "?";
		}
	}
	
	
	/**
	 * get nucleotide values for IUPAC symbol
	 * @param alleles
	 * @return
	 */
	public static String getNucsFromIUPAC(String alleles) {
		if(alleles.equals("R")) return "AG";
		else if(alleles.equals("W")) return "AT";
		else if(alleles.equals("M")) return "AC";
		else if(alleles.equals("S")) return "CG";
		else if(alleles.equals("Y")) return "CT";
		else if(alleles.equals("K")) return "GT";
		else if(alleles.equals("N")) return "N";
		else return alleles;
	}
	public static String getNucsFromIUPAC(char alleles) {
		return   getNucsFromIUPAC(String.valueOf(alleles));
	}	

    




// ****  conversion of region to snp_feature_id: TO BE REMOVED SOON AND REPLACED WITH QUERY TO snp_featureloc  table  **************

public static BigDecimal convertRegion2Indelalleleid(Integer chr, Long pos) {
	return convertRegion2Snpfeatureid( chr, pos);
}
public static BigDecimal convertRegion2Indelalleleid(Integer chr, BigDecimal pos) {
	return convertRegion2Snpfeatureid( chr, pos);
}
public static BigDecimal convertRegion2Indelalleleid(Integer chr, Integer pos) {
	return convertRegion2Snpfeatureid( chr, pos);
}

public static BigDecimal convertRegion2Snpfeatureid(String chr, Long pos) {
	chr=guessChrFromString(chr);
	return  convertRegion2Snpfeatureid(Integer.valueOf(chr), pos); 
}

public static BigDecimal convertRegion2Snpfeatureid(Integer chr, Long pos) {
	
	throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, Long pos");
	/*
	try {
		
	return BigDecimal.valueOf( Long.valueOf(  "1" + String.format("%02d" ,chr) +  String.format("%08d" , pos)  ));
	} catch (Exception ex) {
		ex.printStackTrace();
		AppContext.debug("convertRegion2Snpfeatureid chr=" + chr  + "  pos=" + pos);
		throw new RuntimeException("convertRegion2Snpfeatureid error"); 
	}
	*/
}
public static BigDecimal convertRegion2Snpfeatureid(String chr, Integer pos) {
	chr=guessChrFromString(chr);
	return  convertRegion2Snpfeatureid(Integer.valueOf(chr), pos);
}
public static BigDecimal convertRegion2Snpfeatureid(Integer chr, Integer pos) {
	throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, Integer pos");
	/*
	try {
	return BigDecimal.valueOf( Long.valueOf(  "1" + String.format("%02d" ,chr) +  String.format("%08d" , pos)  ));
	} catch (Exception ex) {
		ex.printStackTrace();
		AppContext.debug("convertRegion2Snpfeatureid chr=" + chr  + "  pos=" + pos);
		throw new RuntimeException("convertRegion2Snpfeatureid error"); 
	}
	*/
	
}

public static BigDecimal convertRegion2Snpfeatureid(String chr, BigDecimal pos) {
	chr=guessChrFromString(chr);
	return convertRegion2Snpfeatureid(Integer.valueOf(chr), pos) ;
}
public static BigDecimal convertRegion2Snpfeatureid(Integer chr, BigDecimal pos) {
	throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, BigDecimal pos");
	/*
	try {
	return BigDecimal.valueOf( Long.valueOf(  "1" + String.format("%02d" ,chr) +  String.format("%08d" , pos.longValue())  ));
	} catch (Exception ex) {
		ex.printStackTrace();
		AppContext.debug("convertRegion2Snpfeatureid chr=" + chr  + "  pos=" + pos);
		throw new RuntimeException("convertRegion2Snpfeatureid error"); 
	}
	*/
}
	
public static Collection convertRegion2Snpfeatureid(String chr, Collection poslist) {

  	chr=guessChrFromString(chr);
  	return convertRegion2Snpfeatureid(Integer.valueOf(chr), poslist);

}
public static Collection convertRegion2Snpfeatureid(Integer chr, Collection poslist) {
	Set snpfeatureidSet = new TreeSet();
	Iterator<BigDecimal> it = poslist.iterator();
	while(it.hasNext()) {
		snpfeatureidSet.add(  convertRegion2Snpfeatureid(chr, it.next())) ;
	}
	return snpfeatureidSet;
}

public static Collection convertRegion2SnpfeatureidLongcol(Integer chr, Collection poslist) {
	Set snpfeatureidSet = new TreeSet();
	Iterator<BigDecimal> it = poslist.iterator();
	while(it.hasNext()) {
		snpfeatureidSet.add(  convertRegion2Snpfeatureid(chr, it.next()).longValue()) ;
	}
	return snpfeatureidSet;
}

//*************  PAST CODES RETAINED




///**
// * is Amazon Web Service compile?
// */
//static boolean isAWS = false;
//static boolean isAWSdev = false;
//
///**
// * is IRRI-VM machine 172.29.4.26
// */
//static boolean isVMIRRI = false;
//
///**
// * is Pollux 172.29.4.215
// */
//static boolean isPollux = false;
//
///**
// * is ASTI
// */
//static boolean isASTI = false;
//
///**
// *  is localhost
// */
//static boolean isLocalhost = true;
//



////******* COMPILATION TYPE
///**
// * is development version
// */
//static boolean isDev =  false;
//
///**
// * is test version
// */
//static boolean isTest = false;


//
//public static void sentHttpPostRequest(String url, String args) throws IOException {
//	
//  URLConnection connection = new URL(url).openConnection();
//  
//  // by default, connection with enable input, but won't enable output
//  connection.setDoOutput(true);
//  //connection.setDoInput(true);
//  OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
//  out.write(args);
//  out.flush();
//  out.close();
//  /*
//   * If the page has respond, uncomment these statements the retrieve the respond data
//  InputStream is = connection.getInputStream();
//  FileOutputStream fos = new FileOutputStream("respond.txt");
//  byte[] buffer = new byte[1024];
//  for (int length; (length = is.read(buffer)) > 0;) {
//      fos.write(buffer, 0, length);
//  }
//  fos.close();
//  is.close();
//  */
//}
//
	
	
}