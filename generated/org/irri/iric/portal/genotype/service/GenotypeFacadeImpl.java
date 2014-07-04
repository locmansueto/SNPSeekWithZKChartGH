package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.irri.iric.portal.AppContext;

import org.irri.iric.portal.genotype.views.Snp2lines;
import org.irri.iric.portal.genotype.domain.Gene;


import org.irri.iric.portal.genotype.domain.Variety3k;

import org.irri.iric.portal.genotype.views.*;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

//@Secured("ROLE_IRRIUSER")
@Service("GenotypeFacade")
public class GenotypeFacadeImpl implements GenotypeFacade {

	private static final Log log = LogFactory.getLog(GenotypeFacadeImpl.class);
	
	@Autowired
	private ApplicationContext appContext;
	

	
	@Autowired
	private ITestService testservice;
	
	@Autowired
	private GeneService geneservice; // = new org.irri.iric.portal.genotype.service.GeneServiceImpl();
	
	@Autowired 
	private Variety3kService varservice;
	
	
	@Autowired
	private  org.irri.iric.portal.genotype.views.ISnp2linesHome snp2linesService; // = new Snp2linesHome();
	

	private java.util.List<String> varnames;
	private java.util.List<String> genenames;
	
	// temporary, 
	private java.util.Map<String, Gene> mapName2Gene = new java.util.HashMap<String, Gene>();
	
	
	public GenotypeFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub

	}
	
	/**
	 * Get Gene object from name
	 */
	
	@Override
	public Gene getGeneFromName(String name) {
		// TODO Auto-generated method stub
		
		// temporary.. findGeneByName is case-sensitive??
		return mapName2Gene.get(name.toUpperCase());
		
		//return geneservice.findGeneByName(name);
	}



	
	/**
	 * Get varietry names 
	 */
	
	@Override
	public List<String> getVarnames() {
		// TODO Auto-generated method stub
		if(varservice==null) throw new RuntimeException("varservice==null");		
		//return  mockGetVarnames() ;
		
		
		if(varnames==null) {
			varnames = new java.util.ArrayList();		
									
			java.util.Iterator<Variety3k> it = varservice.loadVariety3ks().iterator();

			while(it.hasNext()) {
				String varname = it.next().getVarname();
				varnames.add(varname.toUpperCase());
				varnames.add(varname.toLowerCase());
			}
			
		}
		return varnames;
		
	}
	

	/**
	 * Use for development only
	 */

	private List<String> mockGetVarnames() {
		if(varnames==null) {
			
			if(varservice==null) throw new RuntimeException("varservice==null");
			
			
			varnames = new java.util.ArrayList();		

			 varnames.add("LABRA");
			 varnames.add("LEAD");
			 varnames.add("LEK");
			 varnames.add("LEKATAN");
			 varnames.add("LATA MUNA");
			 varnames.add("LAYANDABU");
			 
							
			}

		return varnames;	
	}
	

	/**
	 * Get all gene names
	 */
	@Override
	public List<String> getGenenames() {
		
		if (geneservice==null) throw new java.lang.RuntimeException("geneservice==null");
 		
		//return mockGetGenenames() ; // mocked.. comment this and uncomment the next codes in actual
		
		
		// TODO Auto-generated method stub
		if(genenames==null) {

			genenames = new java.util.ArrayList();
			
	
				
			java.util.Iterator<Gene> it = geneservice.loadGenes().iterator();
		    while(it.hasNext()) {
		    	Gene gene = it.next();
		    	
		    	String genename = gene.getUniquename();
		    	
		    	mapName2Gene.put(genename.toUpperCase(), gene);
		    	
		    	genenames.add(genename.toUpperCase());
		    	genenames.add(genename.toLowerCase());
		    }
			
		}
		return genenames;
	
				
	}
	
	/**
	 * used in development only
	 * @return
	 */

	private List<String> mockGetGenenames() {
		// TODO Auto-generated method stub
		if(genenames==null) {
			if (geneservice==null) throw new java.lang.RuntimeException("geneservice==null");
			genenames = new java.util.ArrayList();
			

			genenames.add("LOC_Os01g01010");
			genenames.add("LOC_Os01g01019");
			genenames.add("LOC_Os01g01030");
			genenames.add("LOC_Os01g01040");
			genenames.add("LOC_Os01g01050");
			genenames.add("LOC_Os01g01060");
			genenames.add("LOC_Os01g01070");
			genenames.add("LOC_Os01g01080");
			genenames.add("LOC_Os01g01090");
			genenames.add("LOC_Os01g01520");			
			
		}
		return genenames;
	}
	
	/**
	 * Get chromosome names, mocked! replace with DB read later
	 */

	@Override
	//public java.util.Map<String,Integer> getChromosomes() {
	public java.util.List<String> getChromosomes() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		java.util.List<String> chr = new java.util.ArrayList<String>();
		for(int i=1; i<13; i++)  chr.add( Integer.toString(i));
		return chr;
	}
	
	/**
	 * Get length of features (ex. chromosome) from name, mocked! replace with DB read later
	 */

	@Override
	public Integer getFeatureLength(String feature) {
		// TODO Auto-generated method stub
		
		java.util.Map<String,Integer> chrLength = new java.util.HashMap<String,Integer>();		
		chrLength.put("1", 43270923);
		chrLength.put("2", 35937250);
		chrLength.put("3",36413819);
		chrLength.put("4",35502694);
		chrLength.put("5",29958434);
		chrLength.put("6",31248787);
		chrLength.put("7",29697621);
		chrLength.put("8",28443022);
		chrLength.put("9",23012720);
		chrLength.put("10",23207287);
		chrLength.put("11",29021106);
		chrLength.put("12",27531856);
		
		return chrLength.get(feature);
	}

	/**
	 * Get the SNP alleles for varieties
	 * var1	variety 1
	 * var2	variety 2
	 * startPos	start position
	 * endPos	end position
	 * chromosome
	 * querymode	what alleles to query, allowed values
	 * 				enum snpQueryMode { SNPQUERY_VARIETIES		alleles between the two varieties
	 * 								,  SNPQUERY_REFERENCE	allele with the reference
	 * 								, SNPQUERY_ALLREFPOS 	all snp positions in the reference
	 * 	
	 */
	@Override
	public List<Snp2lines> getSNPinVarieties(String var1, String var2,
			 Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode) {
		// TODO Auto-generated method stub

		try {
			if(testservice==null) log.debug("testservice==null"); // throw new RuntimeException("testservice==null");

			
			// use sql query because this object is mapped from a view without primary key
			String sql = "select VAR1, VAR2, CHR, POS, REFNUC, VAR1NUC, VAR2NUC from SNP_2LINES where ";
			
			if(!var1.isEmpty())
			{
				sql += " var1='" + var1 + "' ";
				if(!var2.isEmpty())
					sql += " and var2='" + var2 + "' ";
			} else
			{
				if(!var2.isEmpty())
					sql += " var2='" + var2 + "' ";				
			}
				 
			  sql += " and chr=" + chromosome + " and pos between " +
					(startPos -1) + " and " + (endPos + 1);

			if(querymode==snpQueryMode.SNPQUERY_VARIETIES) {
				sql += " and VAR1NUC<>VAR2NUC ";
			}
			else if(querymode== snpQueryMode.SNPQUERY_REFERENCE) {
				sql += " and (REFNUC<>VAR1NUC or REFNUC<>VAR2NUC) ";
			}
            
		
			// checks in case snp2linesService is null!			
			if(snp2linesService==null) {
			     log.debug("snp2linesService==null, getting from context");
			     if(appContext==null) {
			    	 log.debug("autowired appContext==null, using static");
			    	 appContext=AppContext.getApplicationContext();
			    	 if(appContext==null)  throw new RuntimeException("static appContext is NULL!!!");
			    	 else snp2linesService= (ISnp2linesHome) appContext.getBean( "ISnp2linesHome");		    	 
			     }
			     else snp2linesService= (ISnp2linesHome) appContext.getBean( "ISnp2linesHome");
			}
			
			log.debug( "Querying: " + sql); 
			
			
			List<Snp2lines> snpslist = snp2linesService.executeSQL(sql,"");

			log.debug( "Results: " + snpslist.size() ); 
			
			
			return snpslist;
		}
		catch (RuntimeException re) {
				log.error("getSNPinVarieties failed:", re);
				throw re;
		}

	}
	
	/**
	 * Get snps within a gene. Get the chromosome and location of the gene, then call getSNPinVarieties(String var1, String var2,
	 *		Integer startPos, Integer endPos, String chromosome, snpQueryMode querymode) 
	 *var1	variety 1
	 *var2	variety 2
	 *genename	
	 *plusminusbp	number of basepairs before and after the gene to include
	 *querymode	 what alleles to query, enum snpQueryMode 
	 *
	 */
	
	@Override
	public List<Snp2lines> getSNPinVarieties(String var1, String var2, String genename, Integer plusminusBp, snpQueryMode querymode) {
		// TODO Auto-generated method stub
		
		
		Gene gene = getGeneFromName( genename );
	
		
		log.debug(gene.getUniquename() + " " + gene.getChr() + " " + gene.getFmin() + " " + gene.getFmax());
		
		return getSNPinVarieties(var1, var2, Integer.valueOf(gene.getFmin().toPlainString())  ,Integer.valueOf( gene.getFmax().toPlainString() ), 
				gene.getChr().toUpperCase().replace("CHR", ""), querymode);
		
		
	}



	
	

	@Override
	public List<Snp2lines> getSNPinVarieties(int n) {
		// TODO Auto-generated method stub
		java.util.Random rand = new java.util.Random();
		
		
		java.util.List<Snp2lines> snps = new java.util.ArrayList<Snp2lines>();
		for(int i=0; i<n; i++)
		{
			Snp2linesId snp1 = new Snp2linesId();
			snp1.setVar1nuc(getNuc(rand.nextInt(4) ));
			snp1.setVar2nuc(getNuc(rand.nextInt(4) ));
			snp1.setChr(  Short.valueOf(Integer.toString(rand.nextInt(12)+1 ) ) ); 
			snp1.setPos( Long.valueOf( rand.nextInt(10000)+1) );
			snp1.setRefnuc(getNuc(rand.nextInt(4)));		
			snp1.setVar1("IR64");
			snp1.setVar2("AZUCENA");
			
			snps.add(new Snp2lines(snp1));
		}
		return snps;		
	}


	private String getNuc(int i) {
		switch (i) {
			case 0: return "A"; 
			case 1: return "T"; 
			case 2: return "G";
			case 3: return "C";
		}
		return "N";
	}



	public ITestService getTestservice() {
		return testservice;
	}

	public void setTestservice(ITestService testservice) {
		this.testservice = testservice;
	}

	public GeneService getGeneservice() {
		return geneservice;
	}

	public void setGeneservice(GeneService geneservice) {
		this.geneservice = geneservice;
	}

	public Variety3kService getVarservice() {
		return varservice;
	}

	public void setVarservice(Variety3kService varservice) {
		this.varservice = varservice;
	}

	public ISnp2linesHome getSnp2linesService() {
		return snp2linesService;
	}

	public void setSnp2linesService(ISnp2linesHome snp2linesService) {
		this.snp2linesService = snp2linesService;
	}
	
	
	/*
	 * 	
	 * Subset of SNPs

Not correlating (cc>.9) and closely located
(dist<100000 bps) at the same time

Major allele frequency <.95

Covers >=70% of varieties

Subset of ~100K SNPs often desirable for many analysis and visualizations.
These are the criteria to use for filtering.

SNP and other type of variants

Given two (or from 3 to 10) varieties and genome
region/gene find all SNPs between the varieties

Given genome region /gene find all SNPs for

–

–

Given a SNP ( or from 2 to 10 SNPs) find alleles for all
varieties

Visualize all SNPs for all varieties

Visualize all the results in Jbrowse or other genome
browser together with gene structures

Distinguish synonymous and non-synonymous SNPs

all varieties

within a subpopulation group

	 */
	 
	
	
}
