# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
     The required jar files are included in the project. (change to maven)
* Database configuration
     The database configurations are defined in /resources/META-INF/*-dao* files
     We currently connect to both Development and Production servers
* How to run tests
     The auto-generated Classes also have corresponding test Classes, but not yet used
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines
* Code organization

### Code organization ###


The codes are organized in these folders and packages

* /generated     
		auto-generated source codes from MyEclipse CRUD Scaffolding, organized into packages. The package naming convention is  `org.irri.iric.portal.<module/usecase>.<software layer>`, 
        where 

	`<module/usecase>` are the biologically relevant modules as defined by the use-cases (eg. genotype, variety, expression, etc). 

	`<software design layer>` are defined based on the software architecture which include

 
               domain    domain models/entities, mostly auto-generated from the database tables
               dao       data access objects to perform CRUD operations on the models. The DAOs forms the persistence layer
               service   the service layer where the business logic are performed, using the DAOs to read and write data. 
                         Each service package should have a manually created <ServiceName>Facade interface to be used by the user interface, and its implementation
                         <ServiceName>FacadeImpl uses the other Service Classes in the package to perform its computations
               web       web servlets which handle URL requests in the MVC framework. These are not used because our web presentation
                         are created using ZK as defined in the package zkui

* /src     
		manually created source codes, organized into packages. The package naming convention is           `org.irri.iric.portal.<module/usecase>.<software layer>` as described above. The `<software layer>` includes
      
               views     domain models and DAOs auto generated from database VIEW using the Hibernate Tools. Models generated from
                         views don't have Ids since views don't have primary key, so special <Entity>Id classes are also generated
                         which serves as 'virtual' primary key, and is defined by the properties of <Entity>
               zkui      ZK controllers for the module, which use the <ModuleName>Facade, and webpage template is defined in <modulename>.zul
                         in the WebRoot folder
  

* /resources    
	auto-generated configuration files.

	In our legacy database, some tables are in the Production and some in the Development server. So there are two sets of configuration files:

                    dev_crud_maker-<mvc-layer>-context.xml      for the development server
                    iric-<mvc-layer>-context.xml                for the production server
               
	These context files basically tells Spring to scan in their respective directories for Bean and Autowiring annotations for
               dependency injection. The *-dao* files defines the database connection settings

* /src/applicationContext-business.xml    
	The configuration files above are imported in sequence as defined here. So when a new package is created, it should be registered in this file and in this sequence: 

                    Domains, DAOs, Views, Service, Web or ZKController
               
	to makes sure that dependency injection works without NULL reference assignments

* /WebRoot/WEB-INF/applicationContext-security.xml      
	
	Spring-security configuration
* /WebRoot

	Root directory of the web application, as viewed in the browser

The front end class diagrams for genotype and variety query
![iricportal_uml.png](https://bitbucket.org/repo/4AyE6E/images/1591923158-iricportal_uml.png)
 

and the backend classes for the genotype module
![genotype_uml.png](https://bitbucket.org/repo/4AyE6E/images/2488681733-genotype_uml.png)


### On Change of Schema ###

     Design decision to make:
          1. Design using the chado schema (no data yet) and implement special classes to read the existing legacy schema.
          2. Design using the legacy schema and refactor later to adapt chado

     The schema used for the initial design will be the basis in the creation of interfaces. specially the Entities and DAOs
     How to change to new schema?
               a. create views based on the basis schema
               b. implement the existing interfaces on the new objects

     Currently we are using the legacy schema in NICKA.*, VULAT.*, NALEXANDROV.* (production) and quick fixes in LMANSUETO.*. 
     For the code not to break, the minimum requirement is for new service layers to implement the org.irri.iric.portal.genotype.GenotypeFacade 
     and  org.irri.iric.portal.variety.VarietyFacade interfaces.
          New properties/attributes resulting from the new schema may be added to the Interface or to the User interface, and the new Domain classes
     also have to implement the legacy Domain interfaces.

     
     TO DO:
     Move the Service, DAO and Domain layers out of Module/Usecase.
     	To avoid duplication of Service, DAO and Entity classes derived from the database tables/views

     Service objects concerns only with CRUD operations, based on object ID, plus loadAll and count. Thus they are independent of Entity properties, thus on the schema which they
     were derived from. However, the Domain and DAOs include attribute-sepecific methods. So the Service code is independent, while the Domain and DAOs depends on the schema.

   	 To be flexible on changes in schema, we refactor the DAO and Domain packages into  org.irri.iric.portal.[dao/domain].<schema>  where schema is:
          legacy    for the existing, quick fix solution
          chado     for the proposed chado schema
     and only the Service/Module Facade Interfaces are in the org.irri.iric.portal.<module/usecase> package.

     So for example if we have two entities Gene and Variety and an entity derived from a view SNP2lines, to be viewed by different use case modules Genotype Query and Variety Query, and using 
     two schema versions legacy and chado, the package structure will be

     /src
     	/org.irri.iric.portal.genotype.service
     		GenotypeFacade
     		GenotypeFacadeImpl
     	/org.irri.iric.portal.genotype.zkui
     		GenotypeQueryController
     	/org.irri.iric.portal.variety.service
     		VarietyFacade
     		VarietyFacadeImpl     		
     	/org.irri.iric.portal.variety.zkui
     		VarietyQueryController

     /generated
     	/org.irri.iric.portal.service
     			GeneService
     			GeneServiceImpl
     			VarietyService
     			VaeriteyServiceImpl
     	/org.irri.iric.portal.domain
     			Gene
     			Variety
     	/org.irri.iric.portal.domain.legacy
     				Gene extends domain.Gene
     	/org.irri.iric.portal.domain.chado
     				Gene extends domain.Gene
     	/org.irri.iric.portal.domain.views
     				SNP2lines
     				SNP2linesId

     	/org.irri.iric.portal.dao
     			GeneDAO
     			VarietyDAO
     	/org.irri.iric.portal.dao.views
     					SNP2linesHome

     	/org.irri.iric.portal.dao.legacy
     				GeneDAOImpl
     				VarietyDAOImpl
     	/org.irri.iric.portal.dao.chado
     				GeneDAOImpl
     				VarietyDAOImpl

     	/org.irri.iric.portal.web
     			GeneController
     			VarietyController




The above proposed design will have this UML diagram
![proposed.png](https://bitbucket.org/repo/4AyE6E/images/2896047808-proposed.png)

### Remember Java best coding practices and naming conventions ###

- Classes starts with upper case

-  Class properties are all lowe-case
Class methods starts with lower case but may contain Upper cases in the middle to be readable, and in this format
     `<action><Object/Subject>`
- Use the Eclipse context menu Source> to implement property getters/setters, override superclass methos, implement interface methods, add comments, etc.
- Know the uses of Object.hash, Object.equals and Object.toString
- more ...


### Documentation ###
We generate most documentation from the Javadoc so code comments should be Javadoc compatible.

Leave the auto-generated classes documentation as is, just describe the tools/how are they generated, except when you make modifications

For the manually created, and modified auto-generated classes, we should follow this documentation format. Use the context menu (in the source code select the class/property/method definition, then right-click, select Source>generate unit comment) so that templates are readily generated

Class
     Description     
     Creation:   Author   <date>    <version>     
          
Class properties
     Describe use of the property

Class public methods
     Description     
     Input parameters
     Creation:   <Author>   <date>    <version>   
     Return object
          Pseudocode/or general flow of logic   
     Modification/Update:     <Author>  <date>    <version>
          Description of modification/update

     Template
     /**
      * Description: 
      * Parameters: 
      *	${tags} 
      * Return:
      *	${return_type}
      *
      * Pseudocode or general flow of logic
      * Algorithm and formula used and cite references for novel ones
      *
      * Author ${user} 
      *  
      * Created:	${date}   next_release
      * Modified:
      *	  <author> <date> next_release
      */

Class private methods
     Description     
     Input parameters
     Creation:   <Author>   <date>    <version>   




### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact