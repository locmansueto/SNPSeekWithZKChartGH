 create view v_organism as 
    select
        "organism_id",
        "abbreviation",
        "genus",
        "species",
        "common_name",
        "comment" 
    from
        "public"."organism"
    where organism_id in(9,13,14,15,16);    
    
    create view v_scaffolds_organism  as 
 	select 
    	o.common_name,
    	f.feature_id,
    	f.name,
    	o.organism_id,
    	f.seqlen,
    	ct.name ftype,
    	f.type_id,
    	f.uniquename
   from 	
        public.organism o, public.feature f, public.cvterm ct
   where
   		o.organism_id=f.organism_id 
   		and f.type_id=ct.cvterm_id
   		and ct.name in('chromosome','scaffold','contig')      
  order by f.seqlen desc, f.name;    		
        
        
create view v_locus_notes as 
SELECT o.common_name, fsrc.feature_id contig_id, fsrc.uniquename contig_name, f.feature_id, fl.fmin, fl.fmax, fl.strand,  o.organism_id, fp.value notes
FROM organism o, feature fsrc, featureloc fl, feature f 
  left join featureprop fp
	on f.feature_id=fp.feature_id
	and fp.type_id in ( select cvterm_id from cvterm where name='Note')
WHERE   f.feature_id=fl.feature_id
	and f.organism_id=o.organism_id
	and fl.srcfeature_id=fsrc.feature_id
	
	create view v_locus_cvterm_cvtermpath as 	
SELECT o.common_name, fsrc.feature_id contig_id, fsrc.uniquename contig_name, 
	cv.name cv_name, ctsubj.cvterm_id, f.uniquename "name",
	f.feature_id, fl.fmin, fl.fmax, fl.strand,  o.organism_id, fp.value notes,
	dbxrefobj.accession obj_acc, ctobj.name obj_cvterm, dbxrefsubj.accession subj_acc, ctsubj.name subj_cvterm, ctp.pathdistance
FROM   organism o,  cvtermpath ctp,  cv , cvterm ctsubj, cvterm_dbxref  ctdbxrefsubj, dbxref dbxrefsubj, cvterm ctobj, cvterm_dbxref  ctdbxrefobj,  dbxref dbxrefobj, feature_cvterm fct, feature fsrc, featureloc fl, feature f 
  left join featureprop fp
	on f.feature_id=fp.feature_id
	and fp.type_id in ( select cvterm_id from cvterm where name='Note')
WHERE   f.feature_id=fl.feature_id
	and f.organism_id=o.organism_id
	and fl.srcfeature_id=fsrc.feature_id
	and cv.cv_id=ctsubj.cv_id
	and cv.cv_id=ctobj.cv_id
	and f.feature_id=fct.feature_id
	and fct.cvterm_id=ctsubj.cvterm_id
	and ctp.cv_id=cv.cv_id
	and ctp.subject_id=ctsubj.cvterm_id
	and ctp.object_id=ctobj.cvterm_id
	and ctdbxrefobj.cvterm_id=ctobj.cvterm_id
	and dbxrefobj.dbxref_id=ctdbxrefobj.dbxref_id
	and ctdbxrefsubj.cvterm_id=ctsubj.cvterm_id
	and dbxrefsubj.dbxref_id=ctdbxrefsubj.dbxref_id
	
	
	SELECT
    cv.name AS cv_name,
    ctsubj.cvterm_id,
    dbxrefobj.accession AS obj_acc,
    ctobj.name AS obj_cvterm,
    dbxrefsubj.accession AS subj_acc,
    ctsubj.name AS subj_cvterm,
    ctp.pathdistance
   FROM 
    cvtermpath ctp,
    cv,
    cvterm ctsubj,
    cvterm_dbxref ctdbxrefsubj,
    dbxref dbxrefsubj,
    cvterm ctobj,
    cvterm_dbxref ctdbxrefobj,
    dbxref dbxrefobj
  WHERE  cv.cv_id = ctsubj.cv_id AND cv.cv_id = ctobj.cv_id 
  AND ctp.cv_id = cv.cv_id AND ctp.subject_id = ctsubj.cvterm_id 
  AND ctp.object_id = ctobj.cvterm_id AND ctdbxrefobj.cvterm_id = ctobj.cvterm_id 
  AND dbxrefobj.dbxref_id = ctdbxrefobj.dbxref_id AND ctdbxrefsubj.cvterm_id = ctsubj.cvterm_id 
  AND dbxrefsubj.dbxref_id = ctdbxrefsubj.dbxref_id
   AND ctp.pathdistance>0
   
   
   CREATE OR REPLACE VIEW v_iricstock_basicprop (iric_stock_id, "name", ori_country,  subpopulation,  iris_unique_id, box_code) AS 
SELECT * FROM crosstab(
$$select irs.iric_stock_id, irs.name,  cvterm.name cvterm , isp.value
  from iric_stockprop isp, iric_stock irs, cvterm
  where irs.IRIC_STOCK_ID=isp.IRIC_STOCK_ID
  and isp.type_id = cvterm.cvterm_id
  and cvterm.name in ('country_origin','iris_unique_id','box_code','subpopulation' )
  order by 1$$ 

,$$VALUES ('country_origin'),('subpopulation'), ('iris_unique_id'), ('box_code') $$	
  )
AS ct (iric_stock_id bigint, "name" text, country_origin  text, subpopulation text, iris_unique_id text, box_code text);