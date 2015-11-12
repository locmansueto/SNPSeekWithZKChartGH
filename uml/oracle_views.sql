
-- count rows
select
   table_name,
   to_number(
   extractvalue(
      xmltype(
         dbms_xmlgen.getxml('select count(*) c from '||table_name))
    ,'/ROWSET/ROW/C')) count
from 
   user_tables
order by 
   table_name;

-- get max ID 
select max(cv_id) from cv;
select max(cvterm_id) from cvterm;
select max(cvterm_dbxref_id) from cvterm_dbxref;
select max(cvterm_relationship_id) from cvterm_relationship;
select max(cvtermpath_id) from cvtermpath;
select max(cvtermprop_id) from cvtermprop;
select max(cvtermsynonym_id) from cvtermsynonym;
select max(db_id) from db;
select max(dbxref_id) from dbxref;
select max(feature_cvterm_id) from feature_cvterm;
select max(pub_id) from pub;
select max(feature_synonym_id) from feature_synonym;
select max(synonym_id) from synonym_;





CREATE OR REPLACE FORCE VIEW "IRIC"."V_GO_CVTERMPATH" ("CV_NAME", "CVTERM_ID", "SUBJ_ACC", "SUBJ_CVTERM", "OBJ_ACC", "OBJ_CVTERM", "PATHDISTANCE") AS 
  select c.name cv_name, cv_subj.cvterm_id, db_subj.accession subj_acc, cv_subj.name subj_cvterm, db_obj.accession obj_acc, cv_obj.name obj_cvterm, cvtp.pathdistance pathdistance
from iric.cvterm cv_subj , iric.dbxref db_subj, iric.cv c, iric.cvterm cv_obj , iric.dbxref db_obj, iric.cvtermpath cvtp
where c.cv_id=cv_subj.cv_id and c.name in('molecular_function','biological_process','cellular_component')
and cv_subj.dbxref_id=db_subj.dbxref_id
and cv_subj.cvterm_id=cvtp.subject_id 
and cv_obj.cvterm_id=cvtp.object_id_ 
and cv_obj.dbxref_id=db_obj.dbxref_id
union
select c.name cv_name, cv_subj.cvterm_id, db_subj.accession subj_acc, cv_subj.name subj_cvterm, db_obj.accession obj_acc, cv_obj.name obj_cvterm, null pathdistance
from iric.cvterm cv_subj , iric.dbxref db_subj, iric.cv c, iric.cvterm cv_obj , iric.dbxref db_obj,  lmansueto.cvterm_transclosure cvtp
where c.cv_id=cv_subj.cv_id and c.name in('molecular_function','biological_process','cellular_component')
and cv_subj.dbxref_id=db_subj.dbxref_id
and 'GO:' || db_subj.accession = cvtp.subject 
and 'GO:' || db_obj.accession = cvtp.object 
--and cv_subj.cvterm_id= cvtp.subject 
--and cv_obj.cvterm_id=cvtp.object_id_ 
and cv_obj.dbxref_id=db_obj.dbxref_id;
 



 CREATE OR REPLACE FORCE VIEW "IRIC"."V_LOCUS_CVTERM_CVTERMPATH" ("FEATURE_ID", "NAME", "FMIN", "FMAX", "STRAND", "CONTIG_ID", "CONTIG_NAME", "NOTES", "CV_NAME", "CVTERM_ID", "SUBJ_ACC", "SUBJ_CVTERM", "OBJ_ACC", "OBJ_CVTERM", "PATHDISTANCE", "ORGANISM_ID", "COMMON_NAME") AS 
  SELECT distinct "FEATURE_ID","NAME","FMIN","FMAX","STRAND","CONTIG_ID","CONTIG_NAME","NOTES","CV_NAME","CVTERM_ID","SUBJ_ACC","SUBJ_CVTERM","OBJ_ACC","OBJ_CVTERM","PATHDISTANCE", "ORGANISM_ID","COMMON_NAME" FROM 
(
select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name,  dbms_lob.substr(f3.value,1000,1) notes, c.name cv_name, cv.cvterm_id, db.accession subj_acc, cv.name subj_cvterm, db.accession obj_acc, cv.name obj_cvterm, 0 pathdistance , f.organism_id, o.common_name 
from  iric.featureloc fl, iric.feature_cvterm fc, iric.cvterm cv , iric.feature fsrc, iric.organism o, iric.dbxref db, iric.cv c, iric.feature f
left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.feature f2 
          where fp.feature_id=f2.feature_id and fp.type_id=78) f3   -- 78=notes 
         on f.feature_id=f3.feature_id 
where fc.feature_id=f.feature_id and  fc.cvterm_id=cv.cvterm_id and fsrc.feature_id=fl.srcfeature_id and fl.feature_id=f.feature_id and f.organism_id=o.organism_id
and c.cv_id=cv.cv_id and c.name in('molecular_function','biological_process','cellular_component')
and cv.dbxref_id=db.dbxref_id
and f.type_id=865 --gene

UNION

-- locus from      feature_cvterm->subj_id->cvtermpath->obj_id
select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name,  dbms_lob.substr(f3.value,1000,1) notes, c.name cv_name, cv_subj.cvterm_id, db_subj.accession subj_acc, cv_subj.name subj_cvterm, db_obj.accession obj_acc, cv_obj.name obj_cvterm, cvtp.pathdistance pathdistance, f.organism_id, o.common_name 
from iric.featureloc fl, iric.feature_cvterm fc, iric.cvterm cv_subj , iric.feature fsrc, iric.organism o, iric.dbxref db_subj, iric.cv c, iric.cvterm cv_obj , iric.dbxref db_obj, iric.cvtermpath cvtp, iric.feature f
left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.feature f2 
          where fp.feature_id=f2.feature_id and fp.type_id=78) f3   -- 78=notes 
         on f.feature_id=f3.feature_id 
where fc.feature_id=f.feature_id and fsrc.feature_id=fl.srcfeature_id and fl.feature_id=f.feature_id and f.organism_id=o.organism_id
and fc.cvterm_id=cv_subj.cvterm_id 
and c.cv_id=cv_subj.cv_id and c.name in('molecular_function','biological_process','cellular_component')
and cv_subj.dbxref_id=db_subj.dbxref_id
and cv_subj.cvterm_id=cvtp.subject_id and cv_obj.cvterm_id=cvtp.object_id_ 
and cvtp.pathdistance>-1
and cv_obj.dbxref_id=db_obj.dbxref_id
and f.type_id=865 --gene

UNION

select f.feature_id, f.uniquename name, fl.fmin, fl.fmax, fl.strand, fsrc.feature_id contig_id, fsrc.uniquename contig_name,  dbms_lob.substr(f3.value,1000,1) notes, c.name cv_name, cv_subj.cvterm_id, db_subj.accession subj_acc, cv_subj.name subj_cvterm, db_obj.accession obj_acc, cv_obj.name obj_cvterm, 11000 pathdistance, f.organism_id, o.common_name 
from  iric.featureloc fl, iric.feature_cvterm fc, iric.cvterm cv_subj , iric.feature fsrc, iric.organism o, iric.dbxref db_subj, iric.cv c, iric.cvterm cv_obj , iric.dbxref db_obj, lmansueto.cvterm_transclosure cvtp, iric.feature f
left join ( select f2.feature_id, fp.value from iric.featureprop fp, iric.feature f2 
          where fp.feature_id=f2.feature_id and fp.type_id=78) f3   -- 78=notes 
         on f.feature_id=f3.feature_id 
where fc.feature_id=f.feature_id and fsrc.feature_id=fl.srcfeature_id and fl.feature_id=f.feature_id and f.organism_id=o.organism_id 
and fc.cvterm_id=cv_subj.cvterm_id 
and c.cv_id=cv_subj.cv_id and c.name in('molecular_function','biological_process','cellular_component')
and cv_subj.dbxref_id=db_subj.dbxref_id
and cv_obj.dbxref_id=db_obj.dbxref_id
and 'GO:' || db_subj.accession = cvtp.subject 
and 'GO:' || db_obj.accession = cvtp.object 
and f.type_id=865 --gene
)
order by contig_name, fmin, fmax, pathdistance desc;
 
 
 
  CREATE OR REPLACE FORCE VIEW "IRIC"."V_SNP_REFPOSINDEX" ("SNP_FEATURE_ID", "CHROMOSOME", "POSITION", "REFCALL", "ALLELE_INDEX", "TYPE_ID") AS 
  SELECT SNP_FEATURE_ID, CHROMOSOME, POSITION+1 AS POSITION, REFCALL, ALLELE_INDEX, TYPE_ID    
FROM LMANSUETO.SNP_REFPOSINDEX order by CHROMOSOME, POSITION;
 
 