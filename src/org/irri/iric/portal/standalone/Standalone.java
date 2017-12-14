package org.irri.iric.portal.standalone;

import java.util.List;

import org.irri.iric.portal.AppContext;
//import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.variety.VarietyFacade;
//import org.irri.iric.portal.gwas.GwasFacade;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

@Component
public class Standalone {

	private static final String CONFIG_PATH 
                = "classpath*:org/irri/iric/portal/standalone/applicationContext.xml";

	@Autowired
	GenotypeFacade genotype;
	@Autowired
	VarietyFacade variety;
	@Autowired
	GenomicsFacade genomics;
	//@Autowired
	//@Qualifier("GwasFacade")
	//GwasFacade gwas;
	//@Autowired
	//WorkspaceFacade workspace;
	
	

	void sayHello() {
		
		
		try {
			genomics= (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		List<String> org=genomics.getOrganisms();
			System.out.println( "orgs: " +   org);
			System.out.println( "contig: " + genomics.getContigsByOrganism(org.get(0)));
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Genomics.getOrganisms() exception");
		}

		try {
		//gwas= (GwasFacade)AppContext.checkBean(gwas, "GwasFacade");
		//System.out.println( "GwasFacade: " +  gwas.getTraits());
	}catch(Exception ex) {
		ex.printStackTrace();
	}

		try{

		variety= (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		System.out.println( "variety: " +  variety.getCountries(VarietyFacade.DATASET_SNPINDELV2_IUPAC));
	}catch(Exception ex) {
		ex.printStackTrace();
	}
/*
		try {
		workspace= (WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
		System.out.println( "workspace: " +  workspace.getLocuslistNames());
		}catch(Exception ex) {
			ex.printStackTrace();
		}*/
		
		
		try {
		genotype= (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		System.out.println( "chromosomes: " +  genotype.getChromosomes());
		}catch(Exception ex) {
			ex.printStackTrace();
		}


	}

	public static void main(final String[] args) {
		System.out.println("starting..");
		final ApplicationContext context = 
                        new   ClassPathXmlApplicationContext(CONFIG_PATH);
					//new XmlWebApplicationContext(); //CONFIG_PATH);
		
//		BeanFactory beanFactory=  (BeanFactory)context; // appContext;new DefaultListableBeanFactory();
		final Standalone minimalSpringApp = context.getBean(Standalone.class);
		
//(		(ConfigurableBeanFactory)beanFactory).registerScope("session", new org.springframework.context.support.SimpleThreadScope());
		
		minimalSpringApp.sayHello();
		System.out.println("done..");
	}

}