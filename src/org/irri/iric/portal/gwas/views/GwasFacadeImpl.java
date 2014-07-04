package org.irri.iric.portal.gwas.views;

public class GwasFacadeImpl implements GwasFacade {

	
	
//	create  view view_gwas as  
//	SELECT round(pos/1000000,2) as mbpos, round(-log(10,pval),2) as neglog10p 
//	              		from ( select /*+ first_rows */ pos, stats_one_way_anova(var1,val,'SIG') pval, count(1) cnt 
//	              		from nicka.snps s, nicka.phenotypes p, nicka.germplasm l, nicka.genotyping g 
//	              		where 1=1 
//	              		and  s.chrom= 1
//	              		and g.snp_id=s.snp_id 
//	              		and g.nsftv_id = l.nsftv_id
//	              		and p.nsftv_id = l.nsftv_id 
//	                  and trait = 'SEED VOLUME'
//	              		and subpopulation = 'TEJ' 
//	              		group by trait,subpopulation,chrom,pos 
//	              		 ) where pval <0.005 
//	              		order by 2 desc 
	              		
	              		
}
