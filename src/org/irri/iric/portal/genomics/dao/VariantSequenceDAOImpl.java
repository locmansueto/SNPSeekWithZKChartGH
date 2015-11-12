package org.irri.iric.portal.genomics.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.Iterator;

import org.broadinstitute.gatk.tools.walkers.fasta.FastaAlternateReferenceMaker;
import org.irri.iric.portal.dao.VariantSequenceDAO;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genomics.VariantSequenceQuery;
import org.irri.iric.portal.AppContext;
import org.springframework.stereotype.Repository;

@Repository("VariantSequenceDAO")
public class VariantSequenceDAOImpl implements VariantSequenceDAO {

	
	
	@Override
	public String getFile(VariantSequenceQuery query) throws Exception {
		// TODO Auto-generated method stub
	  

			String destdir=AppContext.getTempDir() + "vcf2fasta-" + AppContext.createTempFilename() + "/";
			new File(destdir).mkdir();
			BufferedWriter bw=new BufferedWriter(new FileWriter(destdir + "loci.gff3"));
			Iterator<MultiReferenceLocus> itLoci=query.getColLocus().iterator();
			while(itLoci.hasNext()) {
				MultiReferenceLocus locus = itLoci.next();
				String st = "P";
				if(locus.getStrand()<0)  st="N";
				bw.append( locus.getContig() + "\tIRGSPv1\tgene\t" + locus.getFmin() + "\t" + locus.getFmax() + "\t.\t" + locus.getStrand() + 
						"\t.\tID=" + locus.getContig() + "_" +  locus.getFmin() + "_" + locus.getFmax() + st +"\n");
			}
			bw.flush();
			bw.close();
			
			bw=new BufferedWriter(new FileWriter(destdir + "vars.txt"));
			Iterator<Variety> itVars=query.getColVars().iterator();
			while(itVars.hasNext()) {
				Variety var=itVars.next();
				String boxcode = var.getIrisId().replace(" ","_").trim();
				bw.append( boxcode).append("\t").append( var.getName() ).append("\n");
			}
			bw.flush();
			bw.close();
		  
			/*
			bw=new BufferedWriter(new FileWriter(destdir + "dirpath.txt"));
			bw.append( destdir +"\n");
			bw.flush();
			bw.close();
			*/

			
			ProcessBuilder pb = new ProcessBuilder(AppContext.getPathToVCF2FastaGenerator(), destdir);
			//pb.directory(new File(AppContext.getFlatfilesDir()));
			String workdir =  AppContext.getFlatfilesDir() + "/getvcfseq/";
			pb.directory(new File(workdir));
			
			  File outfile = new File(destdir + "stdout.txt" );
			  File errfile = new File(destdir + "stderr.txt" );
			  //pb.redirectErrorStream(true);
			  pb.redirectOutput(Redirect.appendTo(outfile));
			  pb.redirectError(Redirect.appendTo(errfile));
			  
			  Process process = pb.start();
			  int exitValue = process.waitFor();
			  AppContext.debug("exitValue=" + exitValue);
			  
			  int len;
			  if ((len = process.getErrorStream().available()) > 0) {
			    byte[] buf = new byte[len]; 
			    process.getErrorStream().read(buf); 
			    AppContext.debug("Command error:\t\""+new String(buf)+"\""); 
			  }
			  
			  // compress directory
			  
			  /*
			  java -jar GenomeAnalysisTK.jar \
			   -T FastaAlternateReferenceMaker \
			   -R reference.fasta \
			   -o output.fasta \
			   -L input.intervals \
			   -V input.vcf \
			  // 
			  FastaAlternateReferenceMaker maker = new FastaAlternateReferenceMaker();
			  */
			  
			  
			  
			  
			  return destdir;
			  
		
	 }
}
