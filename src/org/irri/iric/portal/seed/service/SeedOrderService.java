package org.irri.iric.portal.seed.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.email.EmailService;
import org.irri.iric.portal.seed.InlineEditingViewModel;
import org.irri.iric.portal.seed.OrderData;
import org.irri.iric.portal.seed.Seed;
//import org.irri.iric.portal.seed.SeedPrice;
import org.irri.iric.portal.seed.SeedPriceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.io.Files;

@Service("IRGCSeedOrderService")
public class SeedOrderService {

	public static String STATUS_SUBMITTED = "submitted";
	public static String STATUS_VERIFIED = "verified";
	public static String STATUS_APPROVED = "approved";
	public static String STATUS_DELIVERED = "delivered";

	@Autowired
	@Qualifier("SeedPriceDAO")
	private SeedPriceDAO seedpricedao;
	@Autowired
	private EmailService emailservice;

	// @Autowired
	// private SeedOrderService orderservice;

	public SeedOrderService() {
		super();
		
	}

	private boolean generateHtml4Doc4j(OrderData order, String orderid, int page) throws Exception {
		try {

			copy(new File(AppContext.getFlatfilesDir() + "a_bc083e_template"),
					new File(AppContext.getTempDir() + orderid));
			AppContext.debug("copy " + AppContext.getFlatfilesDir() + "a_bc083e_template  to  "
					+ AppContext.getTempDir() + orderid);

			String govbody = "the Food and Agriculture Organization of the United Nations";
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(AppContext.getTempDir() + orderid + "/word/document.xml"));
			BufferedReader br = new BufferedReader(new FileReader(AppContext.getFlatfilesDir() + "document1.xml"));
			String l = null;
			while ((l = br.readLine()) != null) {

				bw.append(l.replace("RECEPIENTNAMEADDRESS", order.getRecepientNameAddress())
						.replace("PROVIDERNAMEADDRESS", order.getProviderNameAddress())
						.replace("PROVIDERAUTHNAME", order.getProviderAuthname())
						.replace("RECEPIENTAUTHNAME", order.getRecepientAuthname()).replace("GOVERNINGBODY", govbody)
						.replace("MATERIALURL", ""));
			}
			br.close();
			br = new BufferedReader(new FileReader(AppContext.getFlatfilesDir() + "document_list.xml"));
			Map<String, Seed> m = new TreeMap();
			for (Seed s : order.getAllSeeds()) {
				m.put(s.getAccession(), s);
			}
			StringBuffer bl = new StringBuffer();
			while ((l = br.readLine()) != null) {
				bl.append(l);
			}
			br.close();
			String listpattern1 = bl.toString();
			// bw.append("\n");
			AppContext.debug("accessoins=" + m.size());

			for (String acc : m.keySet()) {
				Seed s = m.get(acc);
				String listpattern = listpattern1.copyValueOf(listpattern1.toCharArray()); // new String(listpattern1);
				AppContext.debug(acc + "   seed=" + s.getVarname() + "       listpattern=" + listpattern);
				String strrow = listpattern.replace("ACCESSION1", s.getAccession())
						.replace("DESIGNATION1", s.getVarname())
						.replace("PRICEPERGRAM1", AppContext.decf.format(s.getPricePerGram()))
						.replace("QUANTITY1", s.getGram().toString())
						.replace("PRICE1", AppContext.decf.format(s.getPrice())).replace("�?", "");

				bw.append(strrow); // .append();

				// first page 45
				// 63 lines per page

				// last line 55
			}
			br = new BufferedReader(new FileReader(AppContext.getFlatfilesDir() + "document2.xml"));
			while ((l = br.readLine()) != null) {
				bw.append(l.replace("PROVIDERAUTHNAME", order.getProviderAuthname())
						.replace("RECEPIENTAUTHNAME", order.getRecepientAuthname())
						.replace("PRICEESTIMATE", AppContext.decf.format(order.getTotalPrice()))
						.replace("TOTALWEIGHT", order.getTotalGram().toString())
						.replace("NOOFLINES", Integer.toString(order.getAllSeeds().size()))
						.replace("GOVERNINGBODY", govbody));

			}
			br.close();

			String blank = "<w:p w:rsidR=\"00582E20\" w:rsidRDefault=\"00582E20\"><w:pPr><w:spacing w:line=\"200\" w:lineRule=\"exact\"/><w:rPr><w:sz w:val=\"20\"/><w:szCs w:val=\"20\"/></w:rPr></w:pPr></w:p>";

			for (int i = 0; i < 5; i++)
				bw.append(blank);

			br = new BufferedReader(new FileReader(AppContext.getFlatfilesDir() + "document3.xml"));
			while ((l = br.readLine()) != null) {
				bw.append(l.replace("PROVIDERAUTHNAME", order.getProviderAuthname())
						.replace("RECEPIENTAUTHNAME", order.getRecepientAuthname())
						.replace("PRICEESTIMATE", AppContext.decf.format(order.getTotalPrice())));

			}
			br.close();

			// String blank="<w:szCs w:val=\"20\"/></w:rPr></w:pPr></w:p><w:p
			// w:rsidR=\"00582E20\" w:rsidRDefault=\"00582E20\"><w:pPr><w:spacing
			// w:line=\"200\" w:lineRule=\"exact\"/><w:rPr><w:sz w:val=\"20\"/>";
			bw.close();
			AppContext.debug("created " + AppContext.getTempDir() + orderid + "/document.xml");

			// Files.copy(new File(AppContext.getFlatfilesDir()+ "a_bc083e_template.zip"),
			// new File(AppContext.getTempDir() +orderid + ".zip") );
			// AppContext.debug("created " + AppContext.getTempDir() +orderid + ".zip from
			// template" );

			// Files.copy(new File(AppContext.getTempDir() + orderid+"/document.xml") , new
			// File(AppContext.getTempDir() + orderid +"/word/document.xml"));
			// AppContext.debug("copied to " + AppContext.getTempDir() + orderid
			// +"/word/document.xml");

			CreateZipMultipleFiles.zipDir(AppContext.getTempDir() + orderid + ".zip",
					AppContext.getTempDir() + orderid);
			AppContext.debug("created " + AppContext.getTempDir() + orderid + ".zip");
			new File(AppContext.getTempDir() + orderid + ".zip")
					.renameTo(new File(AppContext.getTempDir() + "smta" + orderid + ".docx"));
			AppContext.debug("created " + AppContext.getTempDir() + "smta" + orderid + ".docx");

			/*
			 * CreateZipMultipleFiles.addFilesToExistingZip(new File(AppContext.getTempDir()
			 * +orderid + ".zip"), new File(AppContext.getTempDir() +
			 * orderid+"/document.xml")); AppContext.debug("added " +
			 * AppContext.getTempDir() + orderid+"/document.xml" +" to " +
			 * AppContext.getTempDir() +orderid + ".zip"); new File(AppContext.getTempDir()
			 * +orderid + ".zip").renameTo(new File(AppContext.getTempDir() + "smta" +
			 * orderid + ".docx")); AppContext.debug("created " + AppContext.getTempDir() +
			 * "smta" + orderid + ".docx");
			 */
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		// return false;
	}

	private boolean generateHtml4Html2PdfPhantomJS(OrderData order, String orderid, int page) throws Exception {
		try {
			if (page == 2) {
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(AppContext.getTempDir() + orderid + "/" + page + ".html"));
				BufferedReader br = new BufferedReader(
						new FileReader(AppContext.getFlatfilesDir() + "a_bc083e/" + page + ".htm"));
				String l = null;

				/*
				 * adjust left: depending on length of ,order.getRecepientPage2_2()
				 * #tn_2{left:435px;top:339px;word-spacing:0.1px;}
				 * #to_2{left:640px;top:339px;letter-spacing:0.1px;}
				 * #tp_2{left:710px;top:339px;}
				 */

				while ((l = br.readLine()) != null) {
					bw.append(l.replace("RECEPIENT_NAMEADDRESS1", order.getRecepientPage2_1())
							.replace("RECEPIENT_ADDRESS2", order.getRecepientPage2_2()));
				}
				bw.flush();
				bw.close();
				br.close();
			} else if (page == 8) {
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(AppContext.getTempDir() + orderid + "/" + page + ".html"));
				BufferedReader br = new BufferedReader(
						new FileReader(AppContext.getFlatfilesDir() + "a_bc083e/" + page + ".htm"));
				String l = null;

				while ((l = br.readLine()) != null) {
					bw.append(l.replace("RECEPIENT_AUTHORIZED", order.getpRecepientPage8_2())
							.replace("PROVIDER_AUTHORIZED", order.getProviderPage8_1()));
				}
				bw.flush();
				bw.close();
				br.close();
			} else if (page == 12) {
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(AppContext.getTempDir() + orderid + "/" + page + ".html"));
				BufferedReader br = new BufferedReader(
						new FileReader(AppContext.getFlatfilesDir() + "a_bc083e/" + page + ".htm"));
				String l = null;

				while ((l = br.readLine()) != null) {
					bw.append(l.replace("RECEPIENT_AUTHORIZED", order.getpRecepientPage8_2())
							.replace("PROVIDER_AUTHORIZED", order.getProviderPage8_1()));
				}
				bw.flush();
				bw.close();
				br.close();
			} else if (page == 9) {
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(AppContext.getTempDir() + orderid + "/" + page + ".html"));
				BufferedReader br = new BufferedReader(
						new FileReader(AppContext.getFlatfilesDir() + "a_bc083e/" + page + ".htm"));
				String l = null;

				while ((l = br.readLine()) != null) {
					bw.append(l).append("\n");
				}

				// sort by accession
				Map<String, Seed> m = new TreeMap();
				for (Seed s : order.getAllSeeds()) {
					m.put(s.getAccession(), s);
				}
				bw.append(
						"<table width=\"80%\"><tr><th>Accession</th><th>Designation</th><th>US$/10 gram</th><th>Quantity (g)</th><th>Price US$</th></tr>\n");
				for (String acc : m.keySet()) {
					Seed s = m.get(acc);
					bw.append("<tr><td>" + s.getAccession() + "</td><td>" + s.getVarname() + "</td><td>"
							+ s.getPricePerGram() + "</td><td>" + s.getGram() + "</td><td>" + s.getPrice()
							+ "</td></tr>\n");
				}
				bw.append("</table>");

				bw.append("<BR><BR>Total Price Estimate: US$ " + order.getTotalPrice() + " + Shipping cost");
				bw.append("<BR>Total weight (g):" + order.getTotalGram());

				// insert list here (first n only)

				bw.append("</span><div class=WordSection3><p class=MsoNormal>&nbsp;</p>");
				bw.append(
						"<p class=MsoNormal align=right style='text-align:right'><span style='font-size:12.0pt'>9</span></p>");
				bw.append("</div></body></html>\n");

				// bw.append("</body></html>\n");
				bw.flush();
				bw.close();
				br.close();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		// return false;
	}

	public boolean sendEmail(List to, List cc, String subject, String message) {
		try {
			emailservice.sendSimpleMessage(to, cc, subject, message);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean sendEmail(String to, String smtppath, String smtfilename, String subject) {
		// On clicking the order button you'd generate an order ID, which you'd send to
		// us (Marionette "Monette" Alana) and to the user, to be quoted in subsequent
		// correspondence.
		// The email to the user user should also alert them to provide any necessary
		// instructions and documents, e.g. shipping instructions (port of entry,
		// shipping address, consignee, seed treatment),
		// import permit, ...
		try {
			emailservice.sendMessageWithAttachment(to, subject,
					"Thank you for ordering seeds from IRRI. Please sign the attached Standard Material Transfer Agreement and send it to Ms. Marionette Alana (m.alana@irri.org).\n"
							+ "Also please provide necessary instructions and documents, e.g. shipping instructions (port of entry, shipping address, consignee, seed treatment), import permit.",
					smtppath, smtfilename);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean addListToOrder(List l, OrderData order, Integer grams) {
		try {
			order.addVarietyList(l, grams);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean clearOrderlist(OrderData order) {
		order.clearSeeds();
		return true;
	}

	private String generateVerifyCode() {
		return AppContext.createTempFilename();
	}

	private String generateOrderCode() {
		return AppContext.createTempFilename();
	}

	public String order(OrderData order, String pdfmethod) {

		// String verifycode= generateVerifyCode() ;
		String ordercode = generateOrderCode();
		// Calendar calNow= AppContext.toCalendar(new Date());

		List lto = new ArrayList();

		// generate email
		StringBuffer buff = new StringBuffer();
		// buff.append("Your seed order #" + ordercode + " please confirm at " + " using
		// verification code " + verifycode);
		buff.append(
				"<h2>Seed Ordering to IRRI International Rice Genebank Collection (IRGC) via SNP-Seek</h2><br><br>");
		buff.append(
				"Your seed order has been sent to Ms. Marionette Alana (m.alana@irri.org) of IRRI Rice Genebank Collection with the information you provided.");
		buff.append(
				"You may send additional necessary instructions and documents, e.g. shipping instructions (port of entry, shipping address, consignee, seed treatment), import permit.");

		buff.append("<br><br><b>Seed Order #" + ordercode + "</b>\n");
		buff.append("<br><br><b>Order date:</b>" + AppContext.datef.format(new Date()) + "</b>\n");
		buff.append("<h3>Requestor</h3><b>Name:</b>" + order.getName() + "<br><b>Institution:</b>"
				+ order.getInstitution() + "<br><b>Address:</b>" + order.getAddress1());
		if (order.getAddress2() != null && !order.getAddress2().isEmpty())
			buff.append("<br>" + order.getAddress2());
		buff.append("<br><b>Postal code:</b>" + order.getPostalCode() + "<br><b>Country:</b>" + order.getCountry());
		buff.append("<br><b>Phone:</b>" + order.getPhoneReq());
		buff.append("<br><b>Email:</b>" + order.getEmailReq());
		buff.append("<br><b>User category:</b>");
		if (order.getCategory().equals("pub"))
			buff.append("Public sector");
		else if (order.getCategory().equals("priv"))
			buff.append("Private sector");
		else if (order.getCategory().equals("irri"))
			buff.append("within IRRI");

		if (order.getCategory().equals("pub")) {
			// orderservice=(SeedOrderService)AppContext.checkBean(orderservice,
			// "SeedOrderService");
			String ccat = getCountryCategory(order.getCountry());
			buff.append("<br><b>Country category:</b>");
			if (ccat.equals("low"))
				buff.append("Public sector in low income country");
			else if (ccat.equals("lowmid"))
				buff.append("Public sector in lower middle income country");
			else if (ccat.equals("upmid"))
				buff.append("Public sector in upper middle income country");
			else if (ccat.equals("high"))
				buff.append("Public sector in high income country");
		}

		lto.add(order.getEmailReq());

		// "shrink-wrap":"signed"):"not-smta"

		buff.append("<br><h3>SMTA Acceptance</h3>");
		if (order.getAcceptanceType().equals("not-smta"))
			buff.append("The requestor did not accept the terms and conditions of the SMTA");
		else if (order.getAcceptanceType().equals("signed"))
			buff.append(
					"Requestor gives advance notification that they have read the terms and conditions of the SMTA/MTA and that they intend to accept these terms and conditions on receipt of the seed with <b>signed SMTA/MTA</b>.");
		else if (order.getAcceptanceType().equals("shrink-wrap"))
			buff.append(
					"Requestor gives advance notification that they have read the terms and conditions of the SMTA/MTA and that they intend to accept these terms and conditions on receipt of the seed with <b>shrinked-wrap SMTA/MTA</b>.");

		if (order.getAcceptanceType().equals("signed")) {
			buff.append("<h3>Official Authorized to Sign for the Organization</h3>");
			buff.append("<b>Name:</b>" + order.getAutname());
			buff.append("<br><b>Position:</b>" + order.getPosition());
			buff.append("<br><b>Address:</b>" + order.getAuthAddress());
			buff.append("<br><b>Phone:</b>" + order.getPhoneAuth());
			buff.append("<br><b>Email:</b>" + order.getEmailAuth());
			lto.add(order.getEmailAuth());
		}

		buff.append("<br><h3>List of Accessions</h3>");
		buff.append(
				"<table><tr><th>Accession</th><th>    </th><th>Variety Designation</th><th>US$ / 10 g</th><th>Quantity (g)</th><th>Price US$</th></tr>\n");
		Map<String, Seed> ms = new TreeMap();
		for (Seed seed : order.getAllSeeds()) {
			ms.put(seed.getAccession(), seed);
		}
		for (String seedacc : ms.keySet()) {
			Seed seed = ms.get(seedacc);
			buff.append("<tr><td>" + seed.getAccession() + "</td><td>    </td><td>" + seed.getVarname() + "</td><td>"
					+ seed.getPricePerGram() + "</td><td><b>" + seed.getGram() + " g</b></td><td>US$ " + seed.getPrice()
					+ "</td></tr>\n");
		}
		buff.append("</table>\n");

		buff.append("<br><h3>Order Summary</h3>");
		buff.append("<br><b>No. of items:</b>" + order.getAllSeeds().size());
		buff.append("<br><b>Total weight (g):</b>" + order.getTotalGram());
		buff.append("<br><b>Price estimate (US$):</b>" + AppContext.decf.format(order.getTotalPrice())
				+ " + shipping at cost");
		buff.append("<br><br><br><br></html>");

		List lcc = new ArrayList();
		if (AppContext.isAWSBeanstalk()) {
			lto.add("m.alana@irri.org");
			lto.add("snpseek@irri.org");
		} else
			lto.add("l.h.barboza@irri.org");

		// lcc.add("l.mansueto@irri.org");
		if (order.getAllSeeds().size() == 0)
			return AppContext.SEED_NO_ORDER;

		if (sendEmail(lto, lcc, "Seed order #" + ordercode, buff.toString()))
			return ordercode;
		return null;
	}

	// public String orderVerify(OrderData order, String pdfmethod) {
	//
	// String verifycode= generateVerifyCode() ;
	// String ordercode= generateOrderCode() ;
	// Calendar calNow= AppContext.toCalendar(new Date());
	// SeedOrderIRGC orderirgc=
	// new SeedOrderIRGC( order.getName() ,order.getInstitution(),
	// order.getAddress1(),
	// order.getAddress2() , order.getCountry() , order.getEmailReq() ,
	// order.getPhoneReq() ,order.getCategory() ,
	// getCountryCategory(order.getCountry()), order.getAcceptanceType()
	// ,order.getAutname() ,order.getPosition(),
	// order.getEmailAuth() ,order.getPhoneAuth() ,order.getPostalCode() ,calNow ,
	// STATUS_SUBMITTED,
	// order.getTotalPrice() ,verifycode ,ordercode);
	// seedorderservice.saveSeedOrder(orderirgc);
	// SeedOrderIRGC orderirgcloaded=
	// seedorderservice.findSeedOrderByDateOrderCode(calNow, ordercode);
	// String orderid=orderirgcloaded.getSeedOrderId().toString();
	// //seedorderservice.
	// //String ordercode= orderirgcloaded.getSeedOrderId().toString();
	//
	// // generate email
	// StringBuffer buff=new StringBuffer();
	// buff.append("Your seed order code " + orderid + " please confirm at " + "
	// using verification code " + verifycode);
	// for(Seed seed:order.getAllSeeds()) {
	// buff.append( seed.getAccession() +"\t" + seed.getVarname() + "\t" +
	// seed.getGram() +" g\tUS$ " + seed.getPrice() +"\n" );
	// }
	//
	// List lto=new ArrayList();
	// lto.add(order.getEmailReq());
	// if(order.getEmailAuth()!=null && !order.getEmailAuth().isEmpty())
	// lto.add(order.getEmailAuth());
	// List lcc=new ArrayList();
	// lcc.add("l.mansueto@irri.org");
	// sendEmail(lto, lcc, "Seed order #" + orderid, buff.toString() );
	// return orderid;
	// }

	// public String orderSMTA(OrderData order, String pdfmethod) {
	// String orderid=null;
	// //HTML2PDFConverter htmp2pdf = new HTML2PDFConverter();
	// //htmp2pdf.convertToPdf("test_pdf");
	// String orderidonly=AppContext.createTempFilename().substring(0,10);
	// orderid=AppContext.getTempDir() + orderidonly;
	// order.setOrderid(orderidonly);
	// try {
	//
	// if(!new File(orderid).mkdir()) {
	// AppContext.debug("failed to create directory " + orderid);
	// return null;
	// } else {
	// AppContext.debug("created directory " + orderid);
	// }
	//
	// //if(!new File(AppContext.getTempDir() +orderid).mkdir()) return false;
	//
	//
	// AppContext.debug("creating smta using " + pdfmethod);
	// if(pdfmethod.equals("phantomjs")) {
	// Html2PdfPhantomJS h2p=new Html2PdfPhantomJS();
	// PDFMergerUtility ut = new PDFMergerUtility();
	// for(int i=1;i<=12;i++) {
	// try {
	//
	//
	// //AppContext.debug("http://localhost:8080/temp/a_bc083e/"+i+".html converting
	// to " + AppContext.getTempDir()+"test_pdf/"+i+".pdf");
	// //new C07E04_CreateFromURL().createPdf(new
	// URL("http://localhost:8080/temp/a_bc083e/"+i+".html"),
	// AppContext.getTempDir()+"test_pdf/"+i+".pdf");
	// if(i==2 || i== 8 || i== 12 || i==9) {
	// if(generateHtml4Html2PdfPhantomJS( order, orderidonly, i ))
	// h2p.createPdf( AppContext.getHostname()+"/temp/"+orderidonly+"/"+i+".html",
	// orderid+"/"+i+".pdf" );
	// }
	// else {
	// //copy pdf
	// //new File( AppContext.getTempDir() + orderid+"/"+i+".pdf").createNewFile();
	// Files.copy(new
	// File(AppContext.getFlatfilesDir()+"a_bc083e/a-bc083e."+i+".pdf"),new File(
	// orderid+"/"+i+".pdf"));
	// }
	// ut.addSource(orderid+"/"+i+".pdf");
	//
	// AppContext.debug("done");
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	// try {
	// ut.setDestinationFileName(orderid+"/smta.pdf");
	// ut.mergeDocuments();
	// }catch(Exception ex) {
	// ex.printStackTrace(); return null;
	// }
	// return AppContext.getHostname()+"/temp/" + orderidonly+ "/smta.pdf";
	// } else if(pdfmethod.equals("docx")) {
	// generateHtml4Doc4j( order, orderidonly, 0);
	// return AppContext.getHostname()+"/temp/smta"+orderidonly+".docx";
	// }
	// else
	// {
	// generateHtml4Doc4j( order, orderidonly, 0);
	//
	// /*
	// DocxToPDFConverter c1=new DocxToPDFConverter(new FileInputStream(new
	// File(AppContext.getTempDir() + "smta" + orderidonly + ".docx")),
	// new FileOutputStream(new File(AppContext.getTempDir() + "smta" + orderidonly
	// + ".pdf")), true, true);
	// c1.convert();
	// */
	// Docx2PdfConverter c=new Docx2PdfConverter();
	// //c.convert2Pdf(AppContext.getFlatfilesDir()+"a_bc083e.docx",
	// AppContext.getTempDir() + "smta" + orderidonly + ".pdf",pdfmethod);
	// c.convert2Pdf(AppContext.getTempDir() + "smta" + orderidonly + ".docx",
	// AppContext.getTempDir() + "smta" + orderidonly + ".pdf",pdfmethod);
	//
	// /*
	// c.convert2Pdf(AppContext.getFlatfilesDir()+"a_bc083e_template.docx",
	// AppContext.getTempDir() + "smta_template.pdf",pdfmethod);
	// c.convert2Pdf(AppContext.getFlatfilesDir()+"a_bc083e_template.docx",
	// AppContext.getTempDir() + "smta" + orderidonly + ".pdf",pdfmethod);
	// */
	// return AppContext.getHostname()+"/temp/"+orderidonly+".pdf";
	// }
	//
	//
	// //List<Seed> lseeds= (List<Seed>)gridOrders.getModel();
	// //textboxTotalGram.setValue( AppContext.decf.format(getTotalGram(lseeds)));
	// //textboxTotalPrice.setValue( AppContext.decf.format( getTotalPrice(lseeds))
	// );
	//
	//
	// /* Messagebox.show(listboxOptions.getSelectedItem().getLabel() + " selected
	// from option BlankModule. " +
	// listboxSubpopulation.getSelectedItem().getLabel() + " selected from
	// Variety.",
	// "Message", Messagebox.OK, Messagebox.INFORMATION);
	// */
	// }catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// return null;
	// }

	public List getCountries() {
		
		seedpricedao = (SeedPriceDAO) AppContext.checkBean(seedpricedao, "SeedPriceDAO");
		return seedpricedao.getCountries();
	}

	public String getCountryCategory(String c) {
		
		seedpricedao = (SeedPriceDAO) AppContext.checkBean(seedpricedao, "SeedPriceDAO");
		return seedpricedao.getCountryCategory(c);
	}

	public void copy(File sourceLocation, File targetLocation) throws IOException {
		if (sourceLocation.isDirectory()) {
			copyDirectory(sourceLocation, targetLocation);
		} else {
			copyFile(sourceLocation, targetLocation);
		}
	}

	private void copyDirectory(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String f : source.list()) {
			copy(new File(source, f), new File(target, f));
		}
	}

	private void copyFile(File source, File target) throws IOException {
		try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
			byte[] buf = new byte[1024];
			int length;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
			}
		}
	}

	public void updatePrice(InlineEditingViewModel vm, String usercat, String c) {
		
		if (usercat.equals("pub")) {
			String ccat = getCountryCategory(c);
			AppContext.debug("pub-" + ccat);
			vm.updatePrice(seedpricedao.getPricePer10gram("PL", ccat), seedpricedao.getMaxFreeGram(ccat));
		} else {
			AppContext.debug(usercat);
			vm.updatePrice(seedpricedao.getPricePer10gram("PL", usercat), 0.0);
		}
	}
}
