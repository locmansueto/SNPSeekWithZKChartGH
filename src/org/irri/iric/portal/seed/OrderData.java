package org.irri.iric.portal.seed;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.SeedOrder;
import org.irri.iric.portal.domain.Variety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

@Component()
public class OrderData  {

	public String getAuthAddress() {
		return authAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getAcceptanceType() {
		return acceptanceType;
	}
	public Date getToday() {
		return today;
	}
	public String getName() {
		return name;
	}
	public String getInstitution() {
		return institution;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getCountry() {
		return country;
	}
	public String getCategory() {
		return category;
	}
	public String getAutname() {
		return autname;
	}
	public String getPosition() {
		return position;
	}
	public String getPhoneReq() {
		return phoneReq;
	}
	public String getPhoneAuth() {
		return phoneAuth;
	}
	public Set getSetAccessions() {
		return setAccessions;
	}
	public SeedPriceDAO getSeedpricedao() {
		return seedpricedao;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getEmailReq() {
		return emailReq;
	}
	public String getEmailAuth() {
		return emailAuth;
	}
	

	//private Integer defaultGram=10;
	private final Date today = new Date();
	private List<Seed> allSeeds=new ArrayList<Seed>();
	private List<String> allStockTypes= new ArrayList<String>();
	
	private String name;
	private String institution;
	private String address1;
	private String address2;
	private String country;
	private String category;
	private String autname;
	private String position;
	private String phoneReq;
	private String emailReq;
	private String phoneAuth;
	private String emailAuth;
	private String postalCode;
	private String orderid;
	private String acceptanceType;
	private String authAddress;
	
	private Set setAccessions=new HashSet(); 
	
	@Autowired
	private SeedPriceDAO seedpricedao;
	
	
		
	public OrderData(List<Seed> allSeeds, List<String> allStockTypes, String name, String institution, String address1,
			String address2, String country, String category, String autname, String position, String phoneReq,
			String emailReq, String phoneAuth, String emailAuth, String orderid, Set setAccessions,
			SeedPriceDAO seedpricedao) {
		super();
		this.allSeeds = allSeeds;
		this.allStockTypes = allStockTypes;
		this.name = name;
		this.institution = institution;
		this.address1 = address1;
		this.address2 = address2;
		this.country = country;
		this.category = category;
		this.autname = autname;
		this.position = position;
		this.phoneReq = phoneReq;
		this.emailReq = emailReq;
		this.phoneAuth = phoneAuth;
		this.emailAuth = emailAuth;
		this.orderid = orderid;
		this.setAccessions = setAccessions;
		this.seedpricedao = seedpricedao;
	}
	public OrderData() {
		super();
		// TODO Auto-generated constructor stub
		//(String accession, String varname, String stockType, Integer gram, Double pricePerGram)

		/*
		List allSeeds=new ArrayList();
		List l=new ArrayList();
		
		allSeeds.add(new Seed("IRGC 7474","klii","BL",0,1.0));
		allSeeds.add(new Seed("IRGC 4556","ghgfh","BL",0,1.0));
		allSeeds.add(new Seed("IRGC 6763","xc","PL",0,1.0));
		allSeeds.add(new Seed("IRGC 2456","jhfgyt","PL",0,1.0));
		allSeeds.add(new Seed("IRGC 7563","jhhklk","BL",0,1.0));
		allSeeds.add(new Seed("IRGC 12354","nnvn","BL",0,1.0));
		addVarietyList(allSeeds);
		*/
		
		allStockTypes.add("PL");
		allStockTypes.add("BL");
		
		seedpricedao=(SeedPriceDAO)AppContext.checkBean(seedpricedao, "SeedPriceDAO");
		
	}
	public List<Seed> getAllSeeds() {
		return allSeeds;
	}
	public List<String> getAllStockTypes() {
		return allStockTypes;
	}
	
	public Double getTotalGram() {
		double total=0;
		for(Seed s:allSeeds) {
			total+=s.getGram();
		}
		return total;
	}

	public Double getTotalPrice() {
		double total=0;
		for(Seed s:allSeeds) {
			total+=s.getPrice();
		}
		return total;
	}
	
	@NotifyChange("*")
	public void clearSeeds() {
		setAccessions.clear();
		allSeeds.clear();
	}
	
	@NotifyChange("*")
	public void addVarietyList(List varlist, Integer grams) {
		seedpricedao=(SeedPriceDAO)AppContext.checkBean(seedpricedao, "SeedPriceDAO");
		for(Object s:varlist) {
			if(s instanceof Seed) {
				Seed v=(Seed)s;
				if(v.getAccession()!=null && v.getAccession().startsWith("IRGC")){}else continue;
				if(setAccessions.contains(v.getAccession())) continue;
				allSeeds.add(v);
				setAccessions.add(v.getAccession());
			} else if(s instanceof Variety) {
				Variety v=(Variety)s;
				if(v.getAccession()!=null && v.getAccession().startsWith("IRGC")){}else continue;
				if(setAccessions.contains(v.getAccession())) continue;
				Seed seed=null;
				if(category.equals("pub")) {
				 seed=new Seed(v.getAccession(),v.getName(),"PL",
						 grams,seedpricedao.getPricePer10gram("PL", seedpricedao.getCountryCategory(country) ),
						seedpricedao.getMaxFreeGram(seedpricedao.getCountryCategory(country)));
				} else if (category.equals("irri")) {
				 seed=new Seed(v.getAccession(),v.getName(),"PL",
						 grams,seedpricedao.getPricePer10gram("PL", "irri" ),
							0.0);
				} else
				 seed=new Seed(v.getAccession(),v.getName(),"PL",
						 grams,seedpricedao.getPricePer10gram("PL", "priv" ),
							0.0);
				
				allSeeds.add(seed);
				setAccessions.add((String)seed.getAccession());
			}
		}
		
	}
	
	public CharSequence getRecepientPage2_1() {
		// TODO Auto-generated method stub
		return name + ", " + institution + ", " + address1;
	}
	public CharSequence getpRecepientPage8_2() {
		// TODO Auto-generated method stub
		return autname;
	}
	public CharSequence getRecepientPage2_2() {
		// TODO Auto-generated method stub
		return ", " + address2;
	}
	public CharSequence getProviderPage8_1() {
		// TODO Auto-generated method stub
		return getProviderAuthname();
	}
	

	public void setCategoryCountry(String usercat, String country) {
		category=usercat;
		this.country=country;
	}

	public void setRequestorInfo(String name, String institution, String address1, String address2, String country, String category, String email, String phone, String postalcode)
	{
		this.name = name;
		this.institution = institution;
		this.address1 = address1;
		this.address2 = address2;
		this.country = country;
		this.category = category;
		this.emailReq=email;
		this.phoneReq=phone;
		this.postalCode=postalcode;
	}

	public void setAuthorizedInfo(String acceptanceType, String name,  String position,  String address, String phone, String email) {
		this.autname = name;
		this.position = position;
		this.phoneAuth = phone;
		this.emailAuth = email;
		this.authAddress=address;
		this.acceptanceType=acceptanceType;
	}

	public CharSequence getRecepientNameAddress() {
		String str="";
		if(!name.isEmpty()) str+=name.toUpperCase();
		if(!institution.isEmpty()) { if(str.isEmpty()) str+=", "; str+=institution.toUpperCase();}
		if(!address1.isEmpty()) { if(str.isEmpty()) str+=", "; str+=address1;}
		if(!address2.isEmpty()) { if(str.isEmpty()) str+=", "; str+=address2;}
		if(!country.isEmpty()) { if(str.isEmpty()) str+=", "; str+=country;}
		
		// TODO Auto-generated method stub
		return str;
	}
	public CharSequence getProviderNameAddress() {
		// TODO Auto-generated method stub
		return "INTERNATIONAL RICE RESEARCH INSTITUTE, Los Banos, Laguna, Philippines";
	}
	public CharSequence getRecepientAuthname() {
		// TODO Auto-generated method stub
		return autname;
	}
	public CharSequence getProviderAuthname() {
		// TODO Auto-generated method stub
		return "Dr. Ruaraidh Sackville-Hamilton, PhD";
	}

	
	
}
