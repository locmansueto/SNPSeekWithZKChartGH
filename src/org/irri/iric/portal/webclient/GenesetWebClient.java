package org.irri.iric.portal.webclient;

import java.util.*;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.Locus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Executions;

/*
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
*/

@Service("WebclientService")
public class GenesetWebClient {

	public static String RicenetNetworkNGA = "Ricenet Neighbors";
	public static String RicenetContextHub = "Ricenet Context";

	public List<String> submitGeneset(Collection sites, Collection geneset, String delimiter, boolean isDisplay) {
		StringBuffer genelist = new StringBuffer();
		Iterator itGene = geneset.iterator();
		while (itGene.hasNext()) {
			Object gene = itGene.next();
			if (gene instanceof String) {
				genelist.append((String) gene);
			} else if (gene instanceof Locus) {
				genelist.append(((Locus) gene).getUniquename());
			}
			if (itGene.hasNext())
				genelist.append(delimiter);
		}
		return submitGeneset(sites, genelist.toString(), isDisplay);
	}

	public List<String> submitGeneset(Collection sites, String genelist, boolean isDisplay) {

		List listUrls = new ArrayList();
		if (sites.contains(RicenetNetworkNGA)) {
			String url = postRicenetNetworkNGA(genelist.toString());
			listUrls.add(url);
			if (isDisplay)
				Executions.getCurrent().sendRedirect(url, "_ricenetnga");
		}
		if (sites.contains(RicenetContextHub)) {
			String url = postRicenetContexthub(genelist.toString());
			listUrls.add(url);
			if (isDisplay)
				Executions.getCurrent().sendRedirect(url, "_ricenetcontext");
		}
		return listUrls;
	}

	private String postRicenetNetworkNGA(String genelist) {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
		// System.setProperty( "phantomjs.binary.path",
		// "E:\\selenium\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");

		System.setProperty("phantomjs.binary.path", AppContext.getPhantomjsExe());

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		// WebDriver driver = new ChromeDriver();
		WebDriver driver = new PhantomJSDriver();
		// WebDriver driver = new HtmlUnitDriver();

		// And now use this to visit Google
		driver.get("http://www.inetbio.org/ricenet/Network_nga_form_conv.php");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");

		// Find the text input element by its name
		WebElement element = driver.findElement(By.id("genes"));
		// element.sendKeys("LOC_Os01g32460 LOC_Os01g32380 LOC_Os08g34740 LOC_Os09g30412
		// LOC_Os02g33180 LOC_Os12g36880 LOC_Os06g49430 LOC_Os02g54600 LOC_Os01g32660
		// LOC_Os03g17700 LOC_Os10g38950 LOC_Os01g47530 LOC_Os05g45420 LOC_Os01g14810
		// LOC_Os02g57200 LOC_Os10g28610 LOC_Os03g12470 LOC_Os01g49290 LOC_Os01g12900
		// LOC_Os05g02130 LOC_Os06g06090 LOC_Os01g25820");
		element.sendKeys(genelist);
		Select dropdown = new Select(driver.findElement(By.id("modelspecies")));
		dropdown.selectByIndex(0);

		// WebElement element2 = driver.findElement(By.id("modelspecies"));
		// element2.sendKeys("0");

		WebElement element3 = driver.findElement(By.id("submit"));
		element3.submit();

		// Now submit the form. WebDriver will find the form for us from the element
		// element.submit();

		// Check the title of the page
		AppContext.debug("Page title is: " + driver.getTitle());
		AppContext.debug("Page source is: " + driver.getPageSource());
		AppContext.debug("Page currenturl is: " + driver.getCurrentUrl());
		AppContext.debug("Page windowhandle is: " + driver.getWindowHandle());

		// Executions.getCurrent().sendRedirect(driver.getCurrentUrl(), "_ricenetnga");

		// Close the browser
		driver.quit();
		// AppContext.debug("done..");
		return driver.getCurrentUrl();
	}

	private String postRicenetContexthub(String genelist) {
		System.setProperty("phantomjs.binary.path", AppContext.getPhantomjsExe());

		WebDriver driver = new PhantomJSDriver();
		driver.get("http://www.inetbio.org/ricenet/context_associated_hub.php");
		WebElement element = driver.findElement(By.id("genes"));
		element.sendKeys(genelist);
		Select dropdown = new Select(driver.findElement(By.id("modelspecies")));
		dropdown.selectByIndex(0);
		WebElement element3 = driver.findElement(By.id("submit"));
		element3.submit();

		// Check the title of the page
		AppContext.debug("Page title is: " + driver.getTitle());
		AppContext.debug("Page source is: " + driver.getPageSource());
		AppContext.debug("Page currenturl is: " + driver.getCurrentUrl());
		AppContext.debug("Page windowhandle is: " + driver.getWindowHandle());

		// Executions.getCurrent().sendRedirect(driver.getCurrentUrl(),
		// "_ricenetcontext");

		// Close the browser
		driver.quit();
		// AppContext.debug("done..");

		return driver.getCurrentUrl();
	}

	//
	// private void postRicenetNetworkNGA(String genelist){
	// AppContext.debug("postRicenetNetworkNGA .. ");
	// if(genelist==null)
	// genelist="LOC_Os01g32460 LOC_Os01g32380 LOC_Os08g34740 LOC_Os09g30412
	// LOC_Os02g33180 LOC_Os12g36880 LOC_Os06g49430 LOC_Os02g54600 LOC_Os01g32660
	// LOC_Os03g17700 LOC_Os10g38950 LOC_Os01g47530 LOC_Os05g45420 LOC_Os01g14810
	// LOC_Os02g57200 LOC_Os10g28610 LOC_Os03g12470 LOC_Os01g49290 LOC_Os01g12900
	// LOC_Os05g02130 LOC_Os06g06090 LOC_Os01g25820";
	// try {
	// //WebClient webClient = new WebClient(BrowserVersion.CHROME);
	// //WebClient webClient = new WebClient(BrowserVersion.CHROME);
	//
	// WebClient webClient = new WebClient();
	//
	//
	// webClient.getOptions().setJavaScriptEnabled(true);
	// webClient.getOptions().setCssEnabled(true);
	//
	// HtmlPage page1 =
	// webClient.getPage("http://www.inetbio.org/ricenet/Network_nga_form_conv.php");
	// HtmlForm form = page1.getForms().get(0);
	// HtmlInput button = form.getInputByValue("Submit");
	// HtmlTextArea textField = form.getTextAreaByName("genes");
	// HtmlSelect selectOrg = form.getSelectByName("modelspecies");
	// selectOrg.setSelectedIndex(0);
	// textField.setText(genelist);
	// HtmlPage page2 = button.click(); //final line
	//
	//
	// AppContext.debug(page2.asText());
	//
	// AppContext.debug("base URI: " + page2.getBaseURI());
	//
	//
	// webClient.close();
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// AppContext.debug("postRicenetNetworkNGA .. done");
	//
	// }
	//
	//
	//
	// public static void main2(String[] args) throws Exception {
	// System.setProperty( "webdriver.chrome.driver",
	// "E:\\selenium\\chromedriver.exe");
	// // The Firefox driver supports javascript
	// //WebDriver driver = new FirefoxDriver();
	// WebDriver driver = new ChromeDriver();
	// //WebDriver driver = new InternetExplorerDriver();
	//
	// // Go to the Google Suggest home page
	// driver.get("http://www.google.com/webhp?complete=1&hl=en");
	//
	//
	// // Enter the query string "Cheese"
	// WebElement query = driver.findElement(By.name("q"));
	// query.sendKeys("Cheese");
	//
	// AppContext.debug("..done");
	//
	// }
	//
	//
	public static void main(String[] args) {

		AppContext.debug("test");
		GenesetWebClient client = new GenesetWebClient();
		Set sites = new HashSet();
		sites.add(GenesetWebClient.RicenetNetworkNGA);
		client.submitGeneset(sites,
				"LOC_Os01g32460 LOC_Os01g32380 LOC_Os08g34740 LOC_Os09g30412 LOC_Os02g33180 LOC_Os12g36880 LOC_Os06g49430 LOC_Os02g54600 LOC_Os01g32660 LOC_Os03g17700 LOC_Os10g38950 LOC_Os01g47530 LOC_Os05g45420 LOC_Os01g14810 LOC_Os02g57200 LOC_Os10g28610 LOC_Os03g12470 LOC_Os01g49290 LOC_Os01g12900 LOC_Os05g02130 LOC_Os06g06090 LOC_Os01g25820",
				true);

	}
	// public static void main1(String[] args) {
	//
	// postRicenetNetworkNGA(null);
	// }
	//
	//
	// public void mainSample(String[] args) {
	// //WebClient webClient = new WebClient();
	// try {
	// AppContext.debug("starting...");
	//
	// WebClient webClient = new WebClient();
	// final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	// //Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
	// assert("HtmlUnit - Welcome to HtmlUnit".equals(page.getTitleText()));
	//
	// final String pageAsXml = page.asXml();
	// //Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
	// assert(pageAsXml.contains("<body class=\"composite\">"));
	//
	// AppContext.debug("page as xml..");
	// AppContext.debug(pageAsXml);
	//
	// final String pageAsText = page.asText();
	// //Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS
	// protocols"));
	// assert(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	//
	// AppContext.debug("page as text..");
	// AppContext.debug(pageAsText);
	//
	//
	// AppContext.debug("done..");
	//
	//
	// } catch(Exception ex ) {
	// ex.printStackTrace();
	// }
	// }
}
