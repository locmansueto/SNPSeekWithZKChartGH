package org.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.zkui.GroupMatrixListItemSorter;
import org.irri.iric.portal.genotype.zkui.VargroupListItemRenderer;
import org.junit.Test;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;

public class TestKgroupQuery {

	private AlleleFreqLineData[] groupFreqlines = null;

	@Test
	public void testWrite() throws Exception {
		String input = "snp3kvars-LOC_OS07G15770-1851435389430594342";
		Map mapVars2Props = new LinkedHashMap();
		Map mapVar2Snpstr = new LinkedHashMap();
		List listPosRef = new ArrayList();
		try {
			// Map mapVar2Group=new HashMap();
			BufferedReader br = new BufferedReader(new FileReader(
					"D:\\snpseekdata\\testHaplo\\"+input+".summary.txt.clustered.txt"));
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				line = line.trim();
				line = line.replace("\"", "");
				if (line.isEmpty())
					continue;
				String cols[] = line.split("\t");
				String grp = cols[7];

				if (grp == null)
					AppContext.debug("Null group for " + line);

				String props[] = new String[] { cols[2], cols[4], cols[1], cols[3], grp };

				mapVars2Props.put(cols[3], props);
			}
			br.close();
			br = new BufferedReader(new FileReader(
					"D:\\\\snpseekdata\\\\testHaplo\\\\"+input+".ped"));
			int pos = 0;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				line = line.replace("\"", "");
				if (line.isEmpty())
					continue;
				String cols[] = line.split("\t");
				// String grp=(String)mapVar2Group.get(cols[0]);
				if (pos != 0 && pos != (cols.length - 6) / 2)
					throw new RuntimeException("inconsistent pos length in ped file");
				else
					pos = (cols.length - 6) / 2;

				String snpstr[] = new String[pos];
				for (int i = 0; i < 2 * pos; i += 2) {
					String al1 = cols[6 + i];
					String al2 = cols[6 + i + 1];
					if (al1 == null || al2 == null) {
						AppContext.debug("al1=" + al1 + ", al2=" + al2);
						throw new RuntimeException("al1=" + al1 + ", al2=" + al2);
					}
					if (!al1.equals(al2)) {
						snpstr[i / 2] = al1 + "/" + al2;
					} else if (al1.equals("0")) {
						snpstr[i / 2] = " ";
					} else
						snpstr[i / 2] = al1;

				}
				// mapvar2snp.put(cols[0],snpstr);
				mapVar2Snpstr.put(cols[0], snpstr);
			}
			br.close();

			br = new BufferedReader(new FileReader(
					"D:\\\\snpseekdata\\\\testHaplo\\\\"+input+".map"));
			BufferedReader br2 = new BufferedReader(new FileReader(
					"D:\\\\snpseekdata\\\\testHaplo\\\\"+input+".map.ref"));
			String line2 = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				while ((line2 = br2.readLine()) != null) {
					line2 = line2.trim();
					if (line2.isEmpty())
						continue;
					String cols1[] = line.split("\t");
					String cols2[] = line2.split("\t");
					if (!cols2[0].equals(cols1[1])) {
						AppContext.debug("inconsistent pos in map file");
						throw new RuntimeException("inconsistent pos in map file");
					}
					// snpid,ctg,pos,ref
					listPosRef.add(new String[] { cols1[1], cols1[0], cols1[3], cols2[1] });
					break;
				}
			}
			br.close();
			br2.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/*
		 * "clust_order" "orig_order" "variety" "iris_id" "pop" "mismatch" "snps" 1 2665
		 * "MUXIQIU" "B071" "temp" 1 17 2 2676 "YJ30" "CX391" "temp" 1 17 3 2675
		 * "JINYUAN 85" "CX389" "temp" 1 17 4 2674 "YUNGENG 23" "CX345" "temp" 1 17
		 * 
		 * IRIS_313-15909 IRIS_313-15909 0 0 0 -9 C C T T T T T T T T C C G G C C T T C
		 * C C C G G G G G G A A T T
		 * 
		 */
		/*
		 * Iterator<Map> itMap=mapGroup2Var2Props.values().iterator();
		 * while(itMap.hasNext()) { mapVars2Props.putAll(itMap.next()); }
		 * 
		 * Map mapVar2Snpstr=new LinkedHashMap();
		 * itMap=mapGroup2Var2Snpstr.values().iterator(); while(itMap.hasNext()) {
		 * mapVar2Snpstr.putAll(itMap.next()); }
		 */

		AppContext.debug("displayKgroupAlleleFrequencies mapVars2Props=" + mapVars2Props.size() + ",mapVar2Snpstr="
				+ mapVar2Snpstr.size() + ",listPosRef=" + listPosRef.size());

		Map<String, Map<String, Integer>> mapGroup2SubpopCount = new HashMap();

		groupFreqlines = calculateKgroupAlleleFrequencies(mapVars2Props, mapVar2Snpstr, listPosRef,
				mapGroup2SubpopCount);

		AppContext.debug("mapGroup2SubpopCount=" + mapGroup2SubpopCount);

		// generate blocksnpmatrix
		// groupFreqlines[0].linepercentmajormodel.get

		List listPopStr = new ArrayList();

		Set setNgroup = new TreeSet();
		Iterator itPop = new TreeSet(groupFreqlines[0].mapPop2Majoralleles.keySet()).iterator();
		while (itPop.hasNext()) {
			try {
				setNgroup.add(Integer.valueOf((String) itPop.next()));
			} catch (Exception ex) {
			}
		}
		itPop = setNgroup.iterator();

		// Iterator itPop=new TreeSet(
		// groupFreqlines[0].mapPop2Majoralleles.keySet()).iterator();
		int allvarcnt = mapVar2Snpstr.size();
		while (itPop.hasNext()) {
			String pop = itPop.next().toString();
			if (pop.equals("all")) {
				continue;
			}

			List listsnp = (List) groupFreqlines[0].mapPop2Majoralleles.get(pop);
			List all = new ArrayList();
			all.add(pop);
			String allcount = "";
			String subcount = "";
			int varpoptotal = 0;
			try {
				allcount = mapGroup2SubpopCount.get(pop).get("all").toString();

				StringBuffer buff = new StringBuffer();
				Map<String, Integer> mapVarpop2Count = mapGroup2SubpopCount.get(pop);
				Iterator<String> itVarpop = mapVarpop2Count.keySet().iterator();
				while (itVarpop.hasNext()) {
					String varpop = itVarpop.next();
					if (varpop.equals("all")) {
						allcount = mapVarpop2Count.get("all").toString();
						continue;
					}
					try {
						int varpopcnt = mapVarpop2Count.get(varpop);
						varpoptotal += varpopcnt;
						allcount = varpop + ":" + varpopcnt;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					buff.append(allcount);
					if (itVarpop.hasNext())
						buff.append("/");
				}
				subcount = buff.toString();

			} catch (Exception ex) {
				AppContext.debug("pop=" + pop);
				ex.printStackTrace();
			}

			// all.add(allcount);
			all.add(subcount);
			all.add(varpoptotal);
			all.add(varpoptotal * 1.0 / allvarcnt);

			all.addAll(listsnp);
			listPopStr.add(all);
		}

		AppContext.debug("listPopStr=" + listPopStr.size());
//		try {
////			listboxGroupAlleleMatrix.setItemRenderer(new VargroupListItemRenderer());
//
//			Listhead listHead = (Listhead) listboxGroupAlleleMatrix.getListhead(); // .getChildren().iterator().next();
//			List<Listheader> listheader = listHead.getChildren();
//			listheader.clear();
//			Listheader nheader = new Listheader("KGROUP");
//			nheader.setWidth("40px");
//			nheader.setSortAscending(new GroupMatrixListItemSorter(true, 0));
//			nheader.setSortDescending(new GroupMatrixListItemSorter(false, 0));
//			listheader.add(nheader);
//			// nheader=new Listheader("SAMPLES"); nheader.setWidth("40px");
//			// listheader.add(nheader);
//			nheader = new Listheader("SUBPOPS:COUNT");
//			nheader.setWidth("300px");
//			nheader.setSortAscending(new GroupMatrixListItemSorter(true, 1));
//			nheader.setSortDescending(new GroupMatrixListItemSorter(false, 1));
//			listheader.add(nheader);
//			nheader = new Listheader("VARIETIES");
//			nheader.setWidth("40px");
//			nheader.setSortAscending(new GroupMatrixListItemSorter(true, 2));
//			nheader.setSortDescending(new GroupMatrixListItemSorter(false, 2));
//			listheader.add(nheader);
//			nheader = new Listheader("FREQUENCY");
//			nheader.setWidth("40px");
//			nheader.setSortAscending(new GroupMatrixListItemSorter(true, 3));
//			nheader.setSortDescending(new GroupMatrixListItemSorter(false, 3));
//			listheader.add(nheader);
//
//			Iterator<String[]> itposref = listPosRef.iterator();
//			int colidx = 4;
//			while (itposref.hasNext()) {
//				String headers[] = itposref.next();
//
//				// snpid,ctg,pos,ref
//				nheader = new Listheader(headers[1] + "-" + headers[2]); // nheader.setSort("auto");
//				nheader.setSortAscending(new GroupMatrixListItemSorter(true, colidx));
//				nheader.setSortDescending(new GroupMatrixListItemSorter(false, colidx));
//				colidx++;
//				nheader.setWidth("20px");
//				listheader.add(nheader);
//			}
//			listboxGroupAlleleMatrix.setModel(new SimpleListModel(listPopStr));

//		} catch (Exception ex) {
//			ex.printStackTrace();
//			Messagebox.show("catched exception:" + ex.getMessage(), "Exception", Messagebox.OK, Messagebox.ERROR);
//		}

		Map retmap[] = new Map[] { mapVars2Props, mapVar2Snpstr };
		AppContext.debug("retmap[]=" + retmap);
		
		Iterator iter = listPopStr.iterator();
		
		while(iter.hasNext()){
			System.out.println(iter.next());
		}

	}

	private AlleleFreqLineData[] calculateKgroupAlleleFrequencies(Map<String, String[]> mapVar2Props,
			Map<String, String[]> mapVar2Snpstr, List<String[]> listPos,
			Map<String, Map<String, Integer>> mapGroup2SubpopCount) throws Exception {

		try {

			Map<String, Map<String, Map<String, Integer>>> mapPos2Subpop2Allele2Count = new TreeMap();
			Map<String, Map<String, Map<String, Integer>>> mapPos2Subpop2Genotype2Count = new TreeMap();

			// Object2StringMultirefsMatrixModel model =
			// (Object2StringMultirefsMatrixModel)this.biglistboxArray.getModel();
			// int poscol=-1;

			Set subpops = new LinkedHashSet();
			subpops.add("all");
			// for(int i=0; i<biglistboxArray.getCols(); i++) {

			/*
			 * int startCol=frozenCols;
			 * 
			 * if(listboxPhenotype.getSelectedItem()!=null &&
			 * !listboxPhenotype.getSelectedItem().getLabel().isEmpty()) { startCol++; };
			 */

			Iterator<String[]> itPos = listPos.iterator();

			int i = 0;
			while (itPos.hasNext()) {

				// AppContext.debug(listPos.get(i).getClass() + ", " +
				// listPos.get(i).toString());
				String[] strs = itPos.next();
				String contigpos = strs[1] + "-" + strs[2];
				Map mapSubpop2Allele2Count = new TreeMap();
				Map mapSubpop2Genotype2Count = new TreeMap();

				Iterator<String> itVars = mapVar2Snpstr.keySet().iterator();
				int j = 0;
				while (itVars.hasNext()) {
					String var = itVars.next();

					if (var == null)
						AppContext.debug("var==null");

					String[] snpstr = (String[]) mapVar2Snpstr.get(var);
					// String subpop = model.getCellAt( model.getElementAt(j), 2).toString();

					if (snpstr == null)
						AppContext.debug("snpstr==null for " + var);

					// name, pop,origorder, irisid, grp
					String[] props = (String[]) mapVar2Props.get(var);

					if (props == null)
						AppContext.debug("props==null for " + var);

					String subpop = props[4]; // +"-"+props[1]; // kgroup id

					if (i == 0) {
						String varpop = props[1];
						if (varpop.isEmpty())
							varpop = "no subpop";
						Map<String, Integer> mapSubpop2Count = mapGroup2SubpopCount.get(subpop);
						if (mapSubpop2Count == null) {
							mapSubpop2Count = new LinkedHashMap();
							mapGroup2SubpopCount.put(subpop, mapSubpop2Count);
						}
						Integer varpopcnt = mapSubpop2Count.get(varpop);
						if (varpopcnt == null)
							varpopcnt = 0;
						varpopcnt++;
						mapSubpop2Count.put(varpop, varpopcnt);

						varpopcnt = mapSubpop2Count.get("all");
						if (varpopcnt == null)
							varpopcnt = 0;
						varpopcnt++;
						mapSubpop2Count.put("all", varpopcnt);
					}

					subpops.add(subpop);
					Map mapAllele2Count = (Map) mapSubpop2Allele2Count.get(subpop);
					if (mapAllele2Count == null) {
						mapAllele2Count = new HashMap();
						mapSubpop2Allele2Count.put(subpop, mapAllele2Count);
					}

					String allele = snpstr[i];

					allele = allele.trim();

					// if(allele.isEmpty()) allele=" ";
					if (allele.isEmpty())
						continue;

					if (allele.contains("/")) {

						// heterogygous

						String alleles12[] = null;
						try {
							alleles12 = new String[] { String.valueOf(allele.charAt(0)),
									String.valueOf(allele.charAt(2)) };

						} catch (Exception ex) {
							AppContext.debug("allele=" + allele);
							// ex.printStackTrace();
							// throw new RuntimeException(ex);
							if (allele.startsWith("/"))
								alleles12 = new String[] { String.valueOf(" "), String.valueOf(allele.charAt(1)) };
							else if (allele.endsWith("/"))
								alleles12 = new String[] { String.valueOf(allele.charAt(1)), String.valueOf(" ") };
							else
								alleles12 = new String[] { String.valueOf(" "), String.valueOf(" ") };
						}

						Integer allele1count = (Integer) mapAllele2Count.get(alleles12[0]);
						if (allele1count == null)
							allele1count = 0;
						allele1count = allele1count + 1;
						mapAllele2Count.put(alleles12[0], allele1count);

						Integer allele2count = (Integer) mapAllele2Count.get(alleles12[1]);
						if (allele2count == null)
							allele2count = 0;
						allele2count = allele2count + 1;
						mapAllele2Count.put(alleles12[1], allele2count);

						// AppContext.debug(subpop + " " + contigpos + " " + allele + "
						// mapAllele2Count=" + mapAllele2Count + " added alleles " + alleles12[0] + ", "
						// + alleles12[1]);

						mapSubpop2Allele2Count.put(subpop, mapAllele2Count);

						Map mapAllele2CountAll = (Map) mapSubpop2Allele2Count.get("all");
						if (mapAllele2CountAll == null) {
							mapAllele2CountAll = new HashMap();
							mapSubpop2Allele2Count.put("all", mapAllele2CountAll);
						}

						Integer allele1countall = (Integer) mapAllele2CountAll.get(alleles12[0]);
						if (allele1countall == null)
							allele1countall = 0;
						allele1countall = allele1countall + 1;
						mapAllele2CountAll.put(alleles12[0], allele1countall);

						Integer allele2countall = (Integer) mapAllele2CountAll.get(alleles12[1]);
						if (allele2countall == null)
							allele2countall = 0;
						allele2countall = allele2countall + 1;
						mapAllele2CountAll.put(alleles12[1], allele2countall);

						mapSubpop2Allele2Count.put("all", mapAllele2CountAll);

					} else {

						// homozygous

						Integer allelecount = (Integer) mapAllele2Count.get(allele);
						if (allelecount == null)
							allelecount = 0;
						allelecount = allelecount + 2;
						mapAllele2Count.put(allele, allelecount);

						mapSubpop2Allele2Count.put(subpop, mapAllele2Count);

						Map mapAllele2CountAll = (Map) mapSubpop2Allele2Count.get("all");
						if (mapAllele2CountAll == null) {
							mapAllele2CountAll = new HashMap();
							mapSubpop2Allele2Count.put("all", mapAllele2CountAll);
						}
						Integer allelecountall = (Integer) mapAllele2CountAll.get(allele);
						if (allelecountall == null)
							allelecountall = 0;
						allelecountall = allelecountall + 2;
						mapAllele2CountAll.put(allele, allelecountall);

						// AppContext.debug("i=" + i + " j=" + j + " subpop=" + subpop + " allele=" +
						// allele + " allelecount=" + allelecount + " allelecountall=" +
						// allelecountall);

						mapSubpop2Allele2Count.put("all", mapAllele2CountAll);
					}

					// genotype count

					Map mapGenotype2Count = (Map) mapSubpop2Genotype2Count.get(subpop);
					if (mapGenotype2Count == null) {
						mapGenotype2Count = new HashMap();
						mapSubpop2Genotype2Count.put(subpop, mapGenotype2Count);
					}

					Integer genotypecount = (Integer) mapGenotype2Count.get(allele);
					if (genotypecount == null)
						genotypecount = 0;
					genotypecount = genotypecount + 1;
					mapGenotype2Count.put(allele, genotypecount);

					mapSubpop2Genotype2Count.put(subpop, mapGenotype2Count);

					Map mapGenotype2CountAll = (Map) mapSubpop2Genotype2Count.get("all");
					if (mapGenotype2CountAll == null) {
						mapGenotype2CountAll = new HashMap();
						mapSubpop2Genotype2Count.put("all", mapGenotype2CountAll);
					}
					Integer genotypecountall = (Integer) mapGenotype2CountAll.get(allele);
					if (genotypecountall == null)
						genotypecountall = 0;
					genotypecountall = genotypecountall + 1;
					mapGenotype2CountAll.put(allele, genotypecountall);

					// AppContext.debug("i=" + i + " j=" + j + " subpop=" + subpop + " allele=" +
					// allele + " allelecount=" + allelecount + " allelecountall=" +
					// allelecountall);

					mapSubpop2Genotype2Count.put("all", mapGenotype2CountAll);

					j++;
				}

				mapPos2Subpop2Allele2Count.put(contigpos, mapSubpop2Allele2Count);
				mapPos2Subpop2Genotype2Count.put(contigpos, mapSubpop2Genotype2Count);

				i++;
			}

			AppContext.debug("KGroup  varieties " + mapVar2Props.size());
			AppContext.debug("KGroup mapPos2Subpop2Allele2Count=" + mapPos2Subpop2Allele2Count.size());
			AppContext.debug("KGroup mapPos2Subpop2Genotype2Count=" + mapPos2Subpop2Genotype2Count.size());

			return new AlleleFreqLineData[] { calcFreq(subpops, mapPos2Subpop2Allele2Count),
					calcFreq(subpops, mapPos2Subpop2Genotype2Count) };

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// updateAlleleFrequencyChart();

		return null;

	}

	private AlleleFreqLineData calcFreq(Collection subpops,
			Map<String, Map<String, Map<String, Integer>>> mapPos2Subpop2Allele2Count) throws Exception {

		AppContext.debug("calcFreq subpops:" + subpops);

		AlleleFreqLineData freqlines = new AlleleFreqLineData();

		freqlines.linecountmajormodel = new DefaultCategoryModel();
		freqlines.linecountminormodel = new DefaultCategoryModel();
		freqlines.line3rdallelemodel = new DefaultCategoryModel();
		// freqlines.line4thallelemodel = new DefaultCategoryModel();
		freqlines.linepercentmajormodel = new DefaultCategoryModel();
		freqlines.linepercentminormodel = new DefaultCategoryModel();
		freqlines.linepercent3rdmodel = new DefaultCategoryModel();
		// freqlines.linepercent4thmodel = new DefaultCategoryModel();

		freqlines.mapPos2Alleles = new HashMap();
		freqlines.mapPos2Subpop2AllelesCountPercentStr = new HashMap();

		// Map<String,List> mapPop2Majoralleles=new HashMap();
		freqlines.mapPop2Majoralleles = new LinkedHashMap(); // mapPop2Majoralleles;

		// Map<String,Integer> mapGroup2SubpopCount=new HashMap();
		// if(mapGroup2SubpopCount!=null) mapGroup2SubpopCount.put("all", 0);

		Map<String, Integer> mapPosSubTotal = new HashMap();
		Iterator<String> itSubpop = subpops.iterator();
		while (itSubpop.hasNext()) {
			String subpop = itSubpop.next();
			List<String> majorallelesOnly = new ArrayList();
			Iterator<String> itPos = mapPos2Subpop2Allele2Count.keySet().iterator();
			while (itPos.hasNext()) {
				String pos = itPos.next();
				String majallele = null;
				Map mapSub2Allele = mapPos2Subpop2Allele2Count.get(pos);
				if (mapSub2Allele == null) {
					majallele = "";
					majorallelesOnly.add("");
					continue;
				}

				Map<String, Integer> mapAllele2Count = (Map) mapSub2Allele.get(subpop);
				if (mapAllele2Count == null) {
					majallele = "";
					majorallelesOnly.add("");
					continue;
				}

				// get major, minor allele
				StringBuffer buffLabel = new StringBuffer();

				Map<Integer, List<String>> mapCount2Alleles = new TreeMap(Collections.reverseOrder());
				Iterator<String> itAl = mapAllele2Count.keySet().iterator();
				while (itAl.hasNext()) {
					String al = itAl.next();
					Integer cnt = mapAllele2Count.get(al);
					List<String> listAlleles = mapCount2Alleles.get(cnt);
					if (listAlleles == null) {
						listAlleles = new ArrayList();
						mapCount2Alleles.put(cnt, listAlleles);
					}
					listAlleles.add(al);
				}

				int allallelecount = 0;
				Iterator<Integer> itCount = mapCount2Alleles.keySet().iterator();
				while (itCount.hasNext()) {
					Integer cnt = itCount.next();
					allallelecount += cnt * mapCount2Alleles.get(cnt).size();
				}

				itCount = mapCount2Alleles.keySet().iterator();
				// AppContext.debug( subpop + ", pos=" + pos + ", count=" + allallelecount );

				// Iterator<String> itSortedalleles=mapCount2Allele.values().iterator();

				try {

					String minallele = null;
					String allele3rd = null;
					String allele4th = null;

					if (mapAllele2Count.keySet().size() == 1) {
						// pos = pos + " [" + mapCount2Allele.get(listCount.get(0)) + " 100%" + "]";
						Integer cnt = itCount.next();
						freqlines.linecountmajormodel.setValue(subpop, pos, cnt);
						freqlines.linepercentmajormodel.setValue(subpop, pos, 100);

						majallele = mapCount2Alleles.get(cnt).get(0);
						buffLabel.append(majallele + ":" + cnt + ":100");

					} else if (mapAllele2Count.keySet().size() == 2) {
						/*
						 * String majallele=null; String minallele=null;
						 */
						int major = (Integer) itCount.next();
						int minor = -1;
						majallele = mapCount2Alleles.get(major).get(0);
						if (mapCount2Alleles.get(major).size() > 1) {
							minor = major;
							minallele = mapCount2Alleles.get(major).get(1);
						} else {
							if (itCount.hasNext()) {
								minor = itCount.next();
								minallele = mapCount2Alleles.get(minor).get(0);
							}
						}
						if (itCount.hasNext())
							throw new RuntimeException("mapAllele2Count.keySet().size()==2 but >2 alleles at " + pos
									+ ":" + mapAllele2Count);

						/*
						 * int major = (Integer)itCount.next(); int minor=major; if(itCount.hasNext())
						 * minor=itCount.next();
						 */
						// int minor = (Integer)listCount.get(0);
						double percentmajor = major * 100.0 / allallelecount;
						double percentminor = minor * 100.0 / allallelecount;

						// pos = pos + " [" + mapCount2Allele.get(listCount.get(1)) + " " +
						// String.format( "%.2f", percentmajor) + "%" + ", " +
						// mapCount2Allele.get(listCount.get(0)) + " " + String.format( "%.2f",
						// percentminor) + "%]";

						freqlines.linecountmajormodel.setValue(subpop, pos, major);
						freqlines.linecountminormodel.setValue(subpop, pos, minor);
						freqlines.linepercentmajormodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percentmajor)));
						freqlines.linepercentminormodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percentminor)));

						buffLabel.append(majallele + ":" + major + ":" + String.format("%.2f", percentmajor) + ";"
								+ minallele + ":" + minor + ":" + String.format("%.2f", percentminor));

					} else if (mapAllele2Count.keySet().size() == 3) {
						// int allelecount = mapAllele2Count.keySet().size();
						/*
						 * String majallele=null; String minallele=null; String allele3rd = null;
						 */
						int major = (Integer) itCount.next();
						int minor = -1;
						int allele3 = -1;
						majallele = mapCount2Alleles.get(major).get(0);
						if (mapCount2Alleles.get(major).size() > 1) {
							minor = major;
							minallele = mapCount2Alleles.get(major).get(1);
							if (mapCount2Alleles.get(major).size() > 2) {
								allele3 = major;
								allele3rd = mapCount2Alleles.get(major).get(2);
							} else {
								if (itCount.hasNext()) {
									allele3 = itCount.next();
									allele3rd = mapCount2Alleles.get(allele3).get(0);
								}
							}
						} else {
							if (itCount.hasNext()) {
								minor = itCount.next();
								minallele = mapCount2Alleles.get(minor).get(0);
								if (mapCount2Alleles.get(minor).size() > 1) {
									allele3 = minor;
									allele3rd = mapCount2Alleles.get(minor).get(1);
								} else {
									if (itCount.hasNext()) {
										allele3 = itCount.next();
										allele3rd = mapCount2Alleles.get(allele3).get(0);
									}
								}
							}
						}

						if (itCount.hasNext())
							throw new RuntimeException(">3 alleles at " + pos + ":" + mapAllele2Count);

						double percentmajor = major * 100.0 / allallelecount;
						double percentminor = minor * 100.0 / allallelecount;
						double percent3rd = allele3 * 100.0 / allallelecount;

						// pos = pos + " [" + mapCount2Allele.get(listCount.get(allelecount-1)) + " " +
						// String.format( "%.2f", percentmajor) + "%, " +
						// mapCount2Allele.get(listCount.get(allelecount-2)) + " " + String.format(
						// "%.2f", percentminor) + "%, " +
						// mapCount2Allele.get(listCount.get(allelecount-3)) + " " + String.format(
						// "%.2f", percent3rd) + "%]";

						freqlines.linecountmajormodel.setValue(subpop, pos, major);
						freqlines.linecountminormodel.setValue(subpop, pos, minor);
						freqlines.linepercentmajormodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percentmajor)));
						freqlines.linepercentminormodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percentminor)));
						freqlines.line3rdallelemodel.setValue(subpop, pos, allele3);
						freqlines.linepercent3rdmodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percent3rd)));

						buffLabel.append(majallele + ":" + major + ":" + String.format("%.2f", percentmajor) + ";"
								+ minallele + ":" + minor + ":" + String.format("%.2f", percentminor) + ";" + allele3rd
								+ ":" + allele3 + ":" + String.format("%.2f", percent3rd));

					} else if (mapAllele2Count.keySet().size() > 3) {
						// int allelecount = mapAllele2Count.keySet().size();
						/*
						 * String majallele=null; String minallele=null; String allele3rd = null; String
						 * allele4th = null;
						 */
						int major = (Integer) itCount.next();
						int minor = -1;
						int allele3 = -1;
						int allele4 = -1;
						majallele = mapCount2Alleles.get(major).get(0);
						if (mapCount2Alleles.get(major).size() > 1) {
							minor = major;
							minallele = mapCount2Alleles.get(major).get(1);
							if (mapCount2Alleles.get(major).size() > 2) {
								allele3 = major;
								allele3rd = mapCount2Alleles.get(major).get(2);
								if (mapCount2Alleles.get(major).size() > 3) {
									allele4 = major;
									allele4th = mapCount2Alleles.get(major).get(3);
								}
							}
						} else {
							if (itCount.hasNext()) {
								minor = itCount.next();
								minallele = mapCount2Alleles.get(minor).get(0);
								if (mapCount2Alleles.get(minor).size() > 1) {
									allele3 = minor;
									allele3rd = mapCount2Alleles.get(minor).get(1);
									if (mapCount2Alleles.get(minor).size() > 2) {
										allele4 = minor;
										allele4th = mapCount2Alleles.get(minor).get(2);
									}
								} else {
									if (itCount.hasNext()) {
										allele3 = itCount.next();
										allele3rd = mapCount2Alleles.get(allele3).get(0);
										if (mapCount2Alleles.get(allele3).size() > 1) {
											allele4 = allele3;
											allele4th = mapCount2Alleles.get(allele3).get(1);
										} else {
											if (itCount.hasNext()) {
												allele4 = itCount.next();
												allele4th = mapCount2Alleles.get(allele4).get(0);
											}
										}
									}
								}
							}
						}

						// if(itCount.hasNext()) throw new RuntimeException(">3 alleles at " + pos + ":"
						// + mapAllele2Count);

						double percentmajor = major * 100.0 / allallelecount;
						double percentminor = minor * 100.0 / allallelecount;
						double percent3rd = allele3 * 100.0 / allallelecount;
						double percent4th = allele4 * 100.0 / allallelecount;

						// pos = pos + " [" + mapCount2Allele.get(listCount.get(allelecount-1)) + " " +
						// String.format( "%.2f", percentmajor) + "%, " +
						// mapCount2Allele.get(listCount.get(allelecount-2)) + " " + String.format(
						// "%.2f", percentminor) + "%, " +
						// mapCount2Allele.get(listCount.get(allelecount-3)) + " " + String.format(
						// "%.2f", percent3rd) + "%]";

						freqlines.linecountmajormodel.setValue(subpop, pos, major);
						freqlines.linecountminormodel.setValue(subpop, pos, minor);
						freqlines.line3rdallelemodel.setValue(subpop, pos, allele3);
						freqlines.line4thallelemodel = new DefaultCategoryModel();
						freqlines.line4thallelemodel.setValue(subpop, pos, allele4);

						freqlines.linepercentmajormodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percentmajor)));
						freqlines.linepercentminormodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percentminor)));
						freqlines.linepercent3rdmodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percent3rd)));
						freqlines.linepercent4thmodel = new DefaultCategoryModel();
						freqlines.linepercent4thmodel.setValue(subpop, pos,
								Double.valueOf(String.format("%.2f", percent4th)));

						buffLabel.append(majallele + ":" + major + ":" + String.format("%.2f", percentmajor) + ";"
								+ minallele + ":" + minor + ":" + String.format("%.2f", percentminor) + ";" + allele3rd
								+ ":" + allele3 + ":" + String.format("%.2f", percent3rd) + ";" + allele4th + ":"
								+ allele4 + ":" + String.format("%.2f", percent4th));

						if (allele4 > -1 && mapCount2Alleles.get(allele4).size() > 1) {
							for (int i = 1; i < mapCount2Alleles.get(allele4).size(); i++) {
								buffLabel.append(";" + mapCount2Alleles.get(allele4).get(i) + ":" + allele4 + ":"
										+ String.format("%.2f", allele4 * 100.0 / allallelecount));
							}
						}

						while (itCount.hasNext()) {
							int cnt = itCount.next();
							List cntalleles = mapCount2Alleles.get(cnt);
							Iterator<String> italleles = cntalleles.iterator();
							while (italleles.hasNext()) {
								buffLabel.append(";" + italleles.next() + ":" + cnt + ":"
										+ String.format("%.2f", cnt * 100.0 / allallelecount));
							}
							AppContext.error(">4 alleles at " + pos + ":" + mapAllele2Count);
							// throw new RuntimeException(">3 alleles at " + pos + ":" + mapAllele2Count);
						}

					}

					/*
					 * List listAllele = freqlines.mapPop2Majoralleles.get(subpop);
					 * if(listAllele==null) { listAllele=new ArrayList();
					 * freqlines.mapPop2Majoralleles.put(subpop, listAllele); // //listAllele }
					 * listAllele.add(majallele);
					 */

					majorallelesOnly.add(majallele);

				} catch (Exception ex) {
					ex.printStackTrace();

					AppContext.debug("mapCount2Alleles=" + mapCount2Alleles);
					AppContext.debug("mapAllele2Count=" + mapAllele2Count);
					AppContext.debug("allallelecount=" + allallelecount);

					throw new RuntimeException(ex);
				}

				mapPosSubTotal.put(pos + "-" + subpop, allallelecount);
				Map<String, String> subpop2labels = freqlines.mapPos2Subpop2AllelesCountPercentStr.get(pos);
				if (subpop2labels == null) {
					subpop2labels = new HashMap();
					freqlines.mapPos2Subpop2AllelesCountPercentStr.put(pos, subpop2labels);
				}
				subpop2labels.put(subpop, buffLabel.toString());

			}

			/*
			 * List listAllele = freqlines.mapPop2Majoralleles.get(subpop);
			 * if(listAllele==null) { listAllele=new ArrayList();
			 * freqlines.mapPop2Majoralleles.put(subpop, listAllele); }
			 * listAllele.addAll(majorallelesOnly);
			 */
			freqlines.mapPop2Majoralleles.put(subpop, majorallelesOnly);
		}

		Iterator<String> itPop = freqlines.mapPop2Majoralleles.keySet().iterator();
		while (itPop.hasNext()) {
			String pop = itPop.next();
			List majalleles = (List) freqlines.mapPop2Majoralleles.get(pop);
			if (majalleles.size() != mapPos2Subpop2Allele2Count.size()) {
				AppContext.debug("pop: majalleles.size()!=mapPos.size(): " + pop + " " + majalleles.size() + " != "
						+ mapPos2Subpop2Allele2Count.size());
				AppContext.debug("majalleles=" + majalleles);
				AppContext.debug("mapPos=" + mapPos2Subpop2Allele2Count.keySet());
			}
		}

		Iterator<String> itPos = freqlines.mapPos2Subpop2AllelesCountPercentStr.keySet().iterator();
		StringBuffer buffjsmarker = new StringBuffer();
		buffjsmarker.append("function() { var pos2sub2label={\n");
		while (itPos.hasNext()) {
			String postr = itPos.next();
			buffjsmarker.append("\"" + postr + "\" : {\n");
			Map<String, String> mapsub2labels = freqlines.mapPos2Subpop2AllelesCountPercentStr.get(postr);
			Iterator<String> itSub = mapsub2labels.keySet().iterator();
			while (itSub.hasNext()) {
				String sub = itSub.next();

				/*
				 * buffjsmarker.append("\""+sub+"\" : ["); String labels[]=
				 * mapsub2labels.get(sub).split(";"); for(int ilab=0; ilab<labels.length;
				 * ilab++) { buffjsmarker.append( "\"" + labels[ilab] + "\"" );
				 * if(ilab<labels.length-1) buffjsmarker.append(","); }
				 * buffjsmarker.append("] \n"); if(itSub.hasNext()) buffjsmarker.append(",\n");
				 */

				buffjsmarker.append("\"" + sub + "\" : '");
				String labels[] = mapsub2labels.get(sub).split(";");
				for (int ilab = 0; ilab < labels.length; ilab++) {
					String[] vals = labels[ilab].split(":");
					if (vals.length == 3)
						buffjsmarker.append(vals[0] + ": " + vals[1] + " <b>" + vals[2] + "%</b>");
					else {
						buffjsmarker.append("<b>" + labels[ilab] + "</b>");
						AppContext.debug("wrong allele,count,percent format for " + labels[ilab] + ", sub=" + sub);
					}
					if (ilab < labels.length - 1)
						buffjsmarker.append("<br>");
				}
				buffjsmarker.append("<br><b>Total:</b> " + mapPosSubTotal.get(postr + "-" + sub) + "<br>");
				buffjsmarker.append("'\n");
				if (itSub.hasNext())
					buffjsmarker.append(",");

			}
			buffjsmarker.append("}");
			if (itPos.hasNext())
				buffjsmarker.append(",\n");
		}
		buffjsmarker.append("};\n");

		// buffjsmarker.append("return '<b>' + this.x + ' ' + this.series.name +
		// '</b><br>' + pos2sub2label[this.x][this.series.name];}");
		buffjsmarker.append(
				"return '<b>' + this.x +  '</b>:  <span style=\"font-weight:bold;color:' + this.series.color + '\">' +  this.series.name + '</span></b><br>' + pos2sub2label[this.x][this.series.name] + '<br/>';}");

		// <span style="color:{series.color}">{series.name}</span>:

		// AppContext.debug(buffjsmarker.toString());

		freqlines.tooltipjs = buffjsmarker.toString();

		/*
		 * Tooltip ttc=new Tooltip(); ttc.setFormatter(new
		 * JavaScriptValue(buffjsmarker.toString()));
		 * this.chartAlleleFrequency.setTooltip(ttc);
		 */
		/*
		 * 
		 * StringBuffer buffjsmarker=new StringBuffer(); buffjsmarker.
		 * append("function() { var chr=0; var off=0; var val=this.x; if(val>345713663) "
		 * ); buffjsmarker.
		 * append(" { chr=12; off=345713663;} else if(val>316692557){chr=11; off=316692557;}  else if(val>293485270){chr=10; off=293485270;}  else if(val>270472550){chr=9; off=270472550;}  else if(val>242029528){chr=8; off=242029528;} "
		 * ); buffjsmarker.
		 * append(" else if(val>212331907){chr=7; off=212331907;}  else if(val>181083120){chr=6; off=181083120;}    else if(val>151124686){chr=5; off=151124686;}   else if(val>115621992){chr=4; off=115621992;} "
		 * ); buffjsmarker.
		 * append("   else if(val>79208173){chr=3; off=79208173;}   else if(val>43270923){chr=2; off=43270923;}   else {chr=1; off=0; };  return 'chr ' + chr + '-' +  (val-off) + ',  -logP=' + this.y.toFixed(2);}"
		 * );
		 * 
		 * Tooltip ttc=new Tooltip(); //ttc.setFormatter(new
		 * JavaScriptValue("function() { return this.x + ',' + this.y; }"));
		 * ttc.setFormatter(new JavaScriptValue(buffjsmarker.toString()));
		 * chartManhattanXY.setTooltip(ttc);
		 */

		return freqlines;
	}

	private class AlleleFreqLineData {
		CategoryModel linecountmajormodel;
		CategoryModel linecountminormodel;
		CategoryModel line3rdallelemodel;
		CategoryModel line4thallelemodel;
		CategoryModel linepercentmajormodel;
		CategoryModel linepercentminormodel;
		CategoryModel linepercent3rdmodel;
		CategoryModel linepercent4thmodel;
		String tooltipjs;

		Map<String, List> mapPos2Alleles;
		Map<String, Map<String, String>> mapPos2Subpop2AllelesCountPercentStr;
		public Map<String, List> mapPop2Majoralleles;
	}

}
