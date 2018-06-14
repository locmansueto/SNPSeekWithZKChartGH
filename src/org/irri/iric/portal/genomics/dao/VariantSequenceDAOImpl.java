package org.irri.iric.portal.genomics.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.dao.VariantSequenceDAO;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genomics.VariantSequenceQuery;
import org.springframework.stereotype.Repository;

@Repository("VariantSequenceService")
public class VariantSequenceDAOImpl implements VariantSequenceDAO {

	@Override
	public String getFile(VariantSequenceQuery query) throws Exception {
		// TODO Auto-generated method stub

		// AppContext.debug(query.toString());
		AppContext.logQuery(query.toString());

		String destdir = query.getJobid();
		if (destdir == null)
			destdir = AppContext.getTempDir() + "vcf2fasta-" + AppContext.createTempFilename() + "/";
		else
			destdir = AppContext.getTempDir() + destdir + "/";

		// destdir=destdir.replace("vcf2fasta-", "vcf2fasta-" + query.getReference()
		// +"-");

		new File(destdir).mkdir();

		Map mapLoc2Int = new LinkedHashMap();

		BufferedWriter bw = new BufferedWriter(new FileWriter(destdir + "loci.gff3"));
		Iterator<MultiReferenceLocus> itLoci = new TreeSet(query.getColLocus()).iterator();
		while (itLoci.hasNext()) {
			MultiReferenceLocus locus = itLoci.next();
			String st = "P";
			if (locus.getStrand() < 0)
				st = "N";
			bw.append(locus.getContig() + "\tIRGSPv1\tgene\t" + locus.getFmin() + "\t" + locus.getFmax() + "\t.\t"
					+ locus.getStrand() + "\t.\tID=" + locus.getContig() + "_" + locus.getFmin() + "_" + locus.getFmax()
					+ st + "\n");

			String origcontigname = locus.getContig();
			if (query.getReference().equals("9311")) {
				origcontigname = origcontigname.replace("sca", "Sca");
				origcontigname = origcontigname.replace("dun", "dUn");
			} else if (query.getReference().equals("Kasalath")) {
				if (origcontigname.equals("um"))
					origcontigname = "UM";
			}

			mapLoc2Int.put(origcontigname + "_" + locus.getFmin() + "_" + locus.getFmax(),
					origcontigname + ":" + locus.getFmin() + "-" + locus.getFmax());
		}
		bw.flush();
		bw.close();

		bw = new BufferedWriter(new FileWriter(destdir + "vars.txt"));

		bw.append("REFERENCE " + query.getReference() + "\n");
		Iterator<ArrayList> itVars = query.getColVars().iterator();
		while (itVars.hasNext()) {
			List<Variety> list = itVars.next();
			Variety var = list.get(0);
			String boxcode = var.getIrisId().replace(" ", "_").trim();
			bw.append(boxcode).append("\t").append(var.getName()).append("\n");
		}
		bw.flush();
		bw.close();

		/*
		 * bw=new BufferedWriter(new FileWriter(destdir + "dirpath.txt")); bw.append(
		 * destdir +"\n"); bw.flush(); bw.close();
		 */

		if (query.getMethod().equals("gatk")) {

			new GATKAltSeqGenerator(destdir).getAltSequence(destdir + "vars.txt", mapLoc2Int, query.getReference());

		} else if (query.getMethod().equals("vulat")) {

			ProcessBuilder pb = new ProcessBuilder(AppContext.getPathToVCF2FastaGenerator(), destdir);
			// pb.directory(new File(AppContext.getFlatfilesDir()));
			String workdir = AppContext.getFlatfilesDir() + "/getvcfseq/";
			pb.directory(new File(workdir));

			File outfile = new File(destdir + "stdout.txt");
			File errfile = new File(destdir + "stderr.txt");
			// pb.redirectErrorStream(true);
			pb.redirectOutput(Redirect.appendTo(outfile));
			pb.redirectError(Redirect.appendTo(errfile));

			Process process = pb.start();
			int exitValue = process.waitFor();
			AppContext.debug("exitValue=" + exitValue);

			int len;
			if ((len = process.getErrorStream().available()) > 0) {
				byte[] buf = new byte[len];
				process.getErrorStream().read(buf);
				AppContext.debug("Command error:\t\"" + new String(buf) + "\"");
			}

			List listFiles = new ArrayList();
			File folder = new File(destdir);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					// System.out.println("File " + listOfFiles[i].getName());
					if (listOfFiles[i].getName().endsWith(".fsa") || listOfFiles[i].getName().endsWith(".txt"))
						listFiles.add(listOfFiles[i].getAbsolutePath());
				} else if (listOfFiles[i].isDirectory()) {
					AppContext.debug("Directory " + listOfFiles[i].getName() + " not added in ZIP");
				}
			}
			String paths[] = destdir.split("/");
			String zipfilename = AppContext.getTempDir() + paths[paths.length - 1] + ".zip";
			CreateZipMultipleFiles zip = new CreateZipMultipleFiles(zipfilename, listFiles);
			zip.create(false);

		}
		// compress directory

		/*
		 * java -jar GenomeAnalysisTK.jar \ -T FastaAlternateReferenceMaker \ -R
		 * reference.fasta \ -o output.fasta \ -L input.intervals \ -V input.vcf \ //
		 * FastaAlternateReferenceMaker maker = new FastaAlternateReferenceMaker();
		 */

		return destdir;

	}
}
