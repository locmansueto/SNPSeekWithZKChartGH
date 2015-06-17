# README #

### What is this repository for? ###

This is the repository of IRIC-Portal Web interface. The application is written in Java, using Hibernate for ORM, Spring to manage the beans and configurations and ZK for the viewers. There are separate repositories for the backend Oracle and data generation pipeline scripts.

* Version 1
	* New Features
	* Feature updates
	* Bug fixed
	* Updates are logged in [http://oryzasnp.org/iric-portal/_about.zul](http://oryzasnp.org/iric-portal/_about.zul "updates")


### How do I get set up? ###

* Setup  
	Download the project and open in Eclipse (or more preferrably MyEclipse). 
	Compile the application and export into a war file.
	Deploy the generated war in Tomcat
  
* Dependencies    
	All required jar files are included in the project in /WebRoot/WEB-INF/lib

* Database configuration  
	To connect to the database, edit the username, password and url for the Data Source Setup in the file /resources/iric\_prod\_crud-dao-context.xml. Please contact us for these values, with your development machine IP address.

		<class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close" name="IRIC_ProductionDS">
			<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
			<property name="username" value="*"/>
			<property name="password" value="*"/>
			<property name="url" value="jdbc:oracle:thin:@*"/>
			<property name="maxIdle" value="16"/>
			<property name="maxActive" value="32"/>
		</bean>


### Project organization ###

The project files are organized in these folders and packages

* /src   
	the source code	organized into java packages described below. The packages are organized as descibed with the UML class diagrams in /uml
* /src/applicationContext-business.xml    
	The configuration files defined in /resources are imported in sequence as defined in this file. When a new package is created, it should be registered in this file and in this order: 
		
		Domains, DAOs, Service, Web or WS or ZK Controllers
to makes sure that the Spring dependency injection works without NULL reference assignments
* /resources    
	configuration files.
		
		iric_prod_crud-dao-context.xml
		iric_prod_crud-security-context.xml
		iric_prod_crud-service-context.xml
		iric_prod_crud-web-context.xml
* /WebRoot/WEB-INF/applicationContext-security.xml      
	Spring-security configuration
* /WebRoot   
	Root directory of the web application, as viewed in the browser. We are using ZK for the viewers and the webpages are defined in *zul files, instead of html.
* /uml
	UML class diagram files


### Source code organization ###


The Java source codes are organized into these major packages  

		org.irri.iric.portal.domain		domain objects interface, used by all services
		org.irri.iric.portal.dao		data access object interface, used by all services
		org.irri.iric.portal.genotype	SNPs and Indels queries
		org.irri.iric.portal.variety	variety phenotype and passport data queries
		org.irri.iric.portal.genomics	gene loci, gene ontology queries 
		org.irri.iric.portal.chado		DAO and Domain implementation for data stored in Oracle using Chado schema
		org.irri.iric.portal.flatfile	DAO and Domain implementation for data stored in text files 
		org.irri.iric.portal.hdf5		DAO and Domain implementation for data stored in HDF5 format
		org.irri.iric.portal.ws			web services
		org.irri.iric.portal.admin		manages workspace, users


* Service

The web application follows the Model-View-Controller (MVC) architecture. There are currently four major functionalities of the application: genotype query, variety query, gene loci query and user workspace management, which is organized in:

		org.irri.iric.portal.genotype
		org.irri.iric.portal.variety
		org.irri.iric.portal.genomics
		org.irri.iric.portal.admin

Within these packages are  **.service**  and **.zkui** subpakages. Each service has a facade interface, which serves as an entry point for the domain. To make the user interfaces, web interfaces and WS-API independent of the actual data sources, the viewers, controllers or web services should use only these Facade interfaces:

		org.irri.iric.portal.genotype.service.GenotypeFacade
		org.irri.iric.portal.variety.service.VarietyFacade
		org.irri.iric.portal.genomics.service.GenomicsFacade
		org.irri.iric.portal.admin.WorkspaceFacade

Each method of these facade call other **Service** objects, which implements the query logic. The service objects then use **DAO** or data access object interfaces to query the database.  

* Views

Our web interface use the ZK framework [http://www.zkoss.org/](http://www.zkoss.org/ "ZK framework"). The view controllers are in the **org.irri.iric.portal.\*.zkui** packages which include the ZK view controller and supporting classes for the components (buttons, listboxes, tables, checkboxes, etc.) in the user interface. The interface termplate itself is defined in the *.zul files in /WebRoot
 

* Web service

The web services API are implemented using Spring REST Controller to map URL paths to service and  facade methods. There are currently three major web services:

		org.irri.iric.portal.ws.rest.GenotypeWS		For methods in *GenotypeFacade
		org.irri.iric.portal.ws.rest.VarietyWS 		For methods in *VarietyFacade
		org.irri.iric.portal.ws.rest.BlastWS		For methods in *LocalAlignmentService

To hide or add more attributes, or change data types to the response objects, WS-specific entities are implemented to wrap the DAO-generated entities. These web service response objects are defined in **org.irri.iric.portal.ws.entity**

The WADL file [http://oryzasnp.org/iric-portal/ws/application.wadl](http://oryzasnp.org/iric-portal/ws/application.wadl "WADL") which can be used to generate client objects, is generated automatically by Spring from the defined mappings. This file is also used to generate the API documentation. The file /WebRoot/api-docs/application-api-docs.json is such documentation in Swagger-json format, can be viewed by swagger-ui in [http://oryzasnp.org/iric-portal/swagger-ui/index.html](http://oryzasnp.org/iric-portal/swagger-ui/index.html "swagger-ui")   


* Data stores

Every data store should implement the Domain and DAO interfaces for each data it can provide. These are the currently implemented data stores:

		org.irri.iric.portal.chado.*	To access Oracle with Chado schema
		org.irri.iric.portal.hdf5.*		To read HDF5 genotype files
		org.irri.iric.portal.flatfile.*	To read ASCII genotype files
	
Within these packages are subpackages:

		*dao 		where the actual data retrieval is implemented, like SQL query or file reading
		*domain 	holds the entity attribute values
	



### Who do I talk to? ###

For comments and suggestions, please email us at iric@irri.org
