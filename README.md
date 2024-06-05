# README #

### What is this repository for? ###
 
 This repository is the CannSeek web application Eclipse project forked from the Rice SNP-Seek at [http://bitbucket.org/irridev/iric_portal](http://bitbucket.org/irridev/iric_portal). The major changes include support for multiple references, and option for the Genotype Viewer to display only basic components suitable for embedded application.
 
 Instructions for setting up, and loading data to the application is hosted at [CannSeek Backend](https://github.com/Southern-Cross-Plant-Science/CannSeekBackend) 
 

### SNP-Seek documentation updates ###

This section supplements and update to the original [README](README_ORIG.md).  


The SNPSeek database uses the Chado schema with modifications for efficient storage of genotyping datasets. In the diagram, the blue tables (db,dbxref,stock,feature) are part of the original Chado schema. The red tables (snp_feature, snp_featureloc, snp_featureprop) are derivatives of the original Chado tables (feature,featureloc,featureprop) specificaly for SNP features. 
![](uml/snpseek_cannseek.png)



