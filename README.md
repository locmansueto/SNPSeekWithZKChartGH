# README #

### What is this repository for? ###

This is the repository of IRIC-Portal Web interface. The application is written in Java, using Hibernate for ORM, Spring to manage the beans and configurations and ZK for the viewers. There are separate repositories for the backend Oracle and data generation pipeline scripts.

* Version 1
	* New Features
	* Feature updates
	* Bug fixed

### How do I get set up? ###

* Setup
	Download the project and open in Eclipse (or more preferrably MyEclipse). 
	Compile the application and export into a war file.
	Deploy the generated war in Tomcat
  
* Dependencies
     All required jar files are included in the project in /WebRoot/WEB-INF/lib

* Database configuration
     The database configurations are defined in /resources/META-INF/*-dao* files


### Code organization ###

The codes are organized in these folders and packages

* /src
	source code	organized into java packages. The packages are organized as descibed with the UML class diagrams in /uml


* /src/applicationContext-business.xml    
	The configuration files defined in /resources are imported in sequence as defined in this file. When a new package is created, it should be registered in this file and in this order: 
		
		Domains, DAOs, Service, Web or ZKController
               
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

	The application follows the Model-View-Controller (MVC) architecture. There are currently three main functionalities of the application: genotype query, variety query, and user workspace management, which is organized in:

		org.irri.iric.portal.genotype
		org.irri.iric.portal.variety
		org.irri.iric.portal.admin

	Within these packages are  .service  and .zkui subpakages. **service** serves as a facade where the query logic are defined. These are user-interface and data store independent implementations, and can be used as the API to access the data. And **zkui** contains the ZK controller classes, and supporting classes to model the components (buttons, listboxes, tables, checkboxes, etc.) in the user interface. The interface itself is defined in the *.zul files in /WebRoot
 
	To make the API and User Interface transparent to the data store used, the service and user interfaces use only well defined java interfaces in:

		org.irri.iric.portal.domain
		org.irri.iric.portal.dao

	Each domain (or entity) has its corresponding DAO interface. The current defined entities are the following:

			CvTerm
			CvTermUniqueValues
			Gene
			Phenotype
			Snps2Vars
			Snps2VarsCountmismatch
			SnpsAllvars
			SnpsAllvarsPos
			SnpsAllvarsRefMismatch
			SnpsHeteroAllele2
			SnpsNonsynAllele
			SnpsStringAllvars
			Variety
			VarietyDistance
			VarietyPlus
			VarietyPlusPlus

	And the Data Access Objects (DAO) interfaces are:
		
			CvTermDAO
			CvTermUniqueValuesDAO
			DAOLongQueryProcessor
			GeneDAO
			IricstockPassportDAO
			ListItemsDAO
			PhenotypeDAO
			Snps2VarsCountMismatchDAO
			Snps2VarsDAO
			SnpsAllvarsDAO
			SnpsAllvarsPosDAO
			SnpsAllvarsRefMismatchDAO
			SnpsHeteroAllvarsDAO
			SnpsNonsynAllvarsDAO
			SnpsStringAllvarsDAO
			VarietyByPassportDAO
			VarietyByPhenotypeDAO
			VarietyDAO
			VarietyDistanceDAO


	Then every data store will have to implement the Domain and DAO interfaces for each data it can provide. The packages

		org.irri.iric.portal.chado
		org.irri.iric.portal.flatfile
	
	are the data store specific implementations. Within these packages are subpackages:

		*dao 	where the actual data retrieval is implemented, like SQL query or file reading
		*domain stores the entity attribute values
	

* The above architecture and classes are summarized in the following class diagrams:

	User Interface Controllers and Service/API/Facade interface

        ![ui_controllers.jpg](https://bitbucket.org/repo/4AyE6E/images/3820548375-ui_controllers.jpg)                    

	Domain/Model structure

        ![Domain_class_diagram.png](https://bitbucket.org/repo/4AyE6E/images/2085446129-Domain_class_diagram.png)

	Genotype API and DAO references

        ![genotype_facade.jpg](https://bitbucket.org/repo/4AyE6E/images/3045822888-genotype_facade.jpg)

	Variety API and DAO references

         ![variety_facade.jpg](https://bitbucket.org/repo/4AyE6E/images/2817894484-variety_facade.jpg)

	Worksapce API and DAO references

        ![workspace.png](https://bitbucket.org/repo/4AyE6E/images/1317479365-workspace.png)

	DAO structure

         ![DAO_class_diagram.png](https://bitbucket.org/repo/4AyE6E/images/538547513-DAO_class_diagram.png)

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact