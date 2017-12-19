package org.irri.iric.portal.seed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

@Repository("SeedPriceDAO")
public class SeedPriceDAO {

	private static Map<String,Map<String, Double>> mapPrice = new HashMap();
	private static Map<String,String> mapCountry2Category=new TreeMap();
	private static Map<String,Double> mapCategory2Maxfree=new HashMap();
	private static List<String> lCountries=new ArrayList();
	
	public SeedPriceDAO() {
		super();
		// TODO Auto-generated constructor stub
		Map mapPricePL=new HashMap();
		Map mapPricePBL=new HashMap();
		mapPricePL.put("irri", 2.64);
		mapPricePL.put("low",2.64);
		mapPricePL.put("lowmid",2.64);
		mapPricePL.put("upmid",5.36);
		mapPricePL.put("high",8.0);
		mapPricePL.put("priv",32.0);
		
		mapPricePBL.put("irri", 11.55);
		mapPricePBL.put("low",2.64);
		mapPricePBL.put("lowmid",11.55);
		mapPricePBL.put("upmid",23.45);
		mapPricePBL.put("high",35.0);
		mapPricePBL.put("priv",140.0);
		mapPrice.put("PL", mapPricePL);
		mapPrice.put("PBL", mapPricePBL);
		
				mapCountry2Category.put("Afghanistan","low");
				mapCountry2Category.put("Benin","low");
				mapCountry2Category.put("Burkina Faso","low");
				mapCountry2Category.put("Burundi","low");
				mapCountry2Category.put("Central African Republic","low");
				mapCountry2Category.put("Chad","low");
				mapCountry2Category.put("Comoros","low");
				mapCountry2Category.put("Congo, Dem. Rep","low");
				mapCountry2Category.put("Eritrea","low");
				mapCountry2Category.put("Ethiopia","low");
				mapCountry2Category.put("Gambia, The","low");
				mapCountry2Category.put("Guinea","low");
				mapCountry2Category.put("Guinea-Bissau","low");
				mapCountry2Category.put("Haiti","low");
				mapCountry2Category.put("Korea, Dem. People's Rep.","low");
				mapCountry2Category.put("Liberia","low");
				mapCountry2Category.put("Madagascar","low");
				mapCountry2Category.put("Malawi","low");
				mapCountry2Category.put("Mali","low");
				mapCountry2Category.put("Mozambique","low");
				mapCountry2Category.put("Nepal","low");
				mapCountry2Category.put("Niger","low");
				mapCountry2Category.put("Rwanda","low");
				mapCountry2Category.put("Senegal","low");
				mapCountry2Category.put("Sierra Leone","low");
				mapCountry2Category.put("Somalia","low");
				mapCountry2Category.put("South Sudan","low");
				mapCountry2Category.put("Tanzania","low");
				mapCountry2Category.put("Togo","low");
				mapCountry2Category.put("Uganda","low");
				mapCountry2Category.put("Zimbabwe","low");

				mapCountry2Category.put("Angola","lowmid");
				mapCountry2Category.put("Armenia","lowmid");
				mapCountry2Category.put("Bangladesh","lowmid");
				mapCountry2Category.put("Bhutan","lowmid");
				mapCountry2Category.put("Bolivia","lowmid");
				mapCountry2Category.put("Cabo Verde","lowmid");
				mapCountry2Category.put("Cambodia","lowmid");
				mapCountry2Category.put("Cameroon","lowmid");
				mapCountry2Category.put("Congo, Rep.","lowmid");
				mapCountry2Category.put("Côte d'Ivoire","lowmid");
				mapCountry2Category.put("Djibouti","lowmid");
				mapCountry2Category.put("Egypt, Arab Rep.","lowmid");
				mapCountry2Category.put("El Salvador","lowmid");
				mapCountry2Category.put("Georgia","lowmid");
				mapCountry2Category.put("Ghana","lowmid");
				mapCountry2Category.put("Guatemala","lowmid");
				mapCountry2Category.put("Honduras","lowmid");
				mapCountry2Category.put("India","lowmid");
				mapCountry2Category.put("Indonesia","lowmid");
				mapCountry2Category.put("Jordan","lowmid");
				mapCountry2Category.put("Kenya","lowmid");
				mapCountry2Category.put("Kiribati","lowmid");
				mapCountry2Category.put("Kosovo  ","lowmid");
				mapCountry2Category.put("Kyrgyz Republic","lowmid");
				mapCountry2Category.put("Lao PDR","lowmid");
				mapCountry2Category.put("Lesotho","lowmid");
				mapCountry2Category.put("Mauritania","lowmid");
				mapCountry2Category.put("Micronesia, Fed. Sts.","lowmid");
				mapCountry2Category.put("Moldova","lowmid");
				mapCountry2Category.put("Mongolia","lowmid");
				mapCountry2Category.put("Morocco","lowmid");
				mapCountry2Category.put("Myanmar","lowmid");
				mapCountry2Category.put("Nicaragua","lowmid");
				mapCountry2Category.put("Nigeria  ","lowmid");
				mapCountry2Category.put("Pakistan  ","lowmid");
				mapCountry2Category.put("Papua New Guinea  ","lowmid");
				mapCountry2Category.put("Philippines","lowmid");
				mapCountry2Category.put("São Tomé and Principe","lowmid");
				mapCountry2Category.put("Solomon Islands","lowmid");
				mapCountry2Category.put("Sri Lanka","lowmid");
				mapCountry2Category.put("Sudan","lowmid");
				mapCountry2Category.put("Swaziland","lowmid");
				mapCountry2Category.put("Syrian Arab Republic","lowmid");
				mapCountry2Category.put("Tajikistan","lowmid");
				mapCountry2Category.put("Timor-Leste","lowmid");
				mapCountry2Category.put("Tunisia","lowmid");
				mapCountry2Category.put("Ukraine","lowmid");
				mapCountry2Category.put("Uzbekistan","lowmid");
				mapCountry2Category.put("Vanuatu","lowmid");
				mapCountry2Category.put("Vietnam","lowmid");
				mapCountry2Category.put("West Bank and Gaza","lowmid");
				mapCountry2Category.put("Yemen, Rep.","lowmid");
				mapCountry2Category.put("Zambia","lowmid");

				
				mapCountry2Category.put("Albania","upmid");
				mapCountry2Category.put("Algeria","upmid");
				mapCountry2Category.put("American Samoa","upmid");
				mapCountry2Category.put("Argentina","upmid");
				mapCountry2Category.put("Azerbaijan","upmid");
				mapCountry2Category.put("Belarus","upmid");
				mapCountry2Category.put("Belize","upmid");
				mapCountry2Category.put("Bosnia and Herzegovina","upmid");
				mapCountry2Category.put("Botswana","upmid");
				mapCountry2Category.put("Brazil","upmid");
				mapCountry2Category.put("Bulgaria","upmid");
				mapCountry2Category.put("China","upmid");
				mapCountry2Category.put("Colombia","upmid");
				mapCountry2Category.put("Costa Rica","upmid");
				mapCountry2Category.put("Croatia","upmid");
				mapCountry2Category.put("Cuba","upmid");
				mapCountry2Category.put("Dominica","upmid");
				mapCountry2Category.put("Dominican Republic  ","upmid");
				mapCountry2Category.put("Equatorial Guinea","upmid");
				mapCountry2Category.put("","upmid");
				mapCountry2Category.put("Ecuador","upmid");
				mapCountry2Category.put("Fiji","upmid");
				mapCountry2Category.put("Gabon","upmid");
				mapCountry2Category.put("Grenada","upmid");
				mapCountry2Category.put("Guyana","upmid");
				mapCountry2Category.put("Iran, Islamic Rep.","upmid");
				mapCountry2Category.put("Iraq","upmid");
				mapCountry2Category.put("Jamaica","upmid");
				mapCountry2Category.put("Kazakhstan","upmid");
				mapCountry2Category.put("Lebanon","upmid");
				mapCountry2Category.put("Libya","upmid");
				mapCountry2Category.put("Macedonia, FYR  ","upmid");
				mapCountry2Category.put("Malaysia","upmid");
				mapCountry2Category.put("Maldives","upmid");
				mapCountry2Category.put("Marshall Islands","upmid");
				mapCountry2Category.put("Mauritius","upmid");
				mapCountry2Category.put("Mexico","upmid");
				mapCountry2Category.put("Montenegro","upmid");
				mapCountry2Category.put("Namibia","upmid");
				mapCountry2Category.put("Nauru","upmid");
				mapCountry2Category.put("Panama","upmid");
				mapCountry2Category.put("Paraguay","upmid");
				mapCountry2Category.put("Peru  ","upmid");
				mapCountry2Category.put("Romania","upmid");
				mapCountry2Category.put("Russian Federation","upmid");
				mapCountry2Category.put("Samoa","upmid");
				mapCountry2Category.put("Serbia","upmid");
				mapCountry2Category.put("South Africa","upmid");
				mapCountry2Category.put("St. Lucia","upmid");
				mapCountry2Category.put("St. Vincent and the Grenadines","upmid");
				mapCountry2Category.put("Suriname","upmid");
				mapCountry2Category.put("Thailand","upmid");
				mapCountry2Category.put("Tonga","upmid");
				mapCountry2Category.put("Turkey","upmid");
				mapCountry2Category.put("Turkmenistan","upmid");
				mapCountry2Category.put("Tuvalu","upmid");
				mapCountry2Category.put("Venezuela, RB","upmid");

				mapCountry2Category.put("Andorra","high");
				mapCountry2Category.put("Antigua and Barbuda","high");
				mapCountry2Category.put("Aruba","high");
				mapCountry2Category.put("Australia","high");
				mapCountry2Category.put("Austria","high");
				mapCountry2Category.put("Bahamas, The","high");
				mapCountry2Category.put("Bahrain","high");
				mapCountry2Category.put("Barbados","high");
				mapCountry2Category.put("Belgium","high");
				mapCountry2Category.put("Bermuda","high");
				mapCountry2Category.put("British Virgin Islands","high");
				mapCountry2Category.put("Brunei Darussalam","high");
				mapCountry2Category.put("Canada","high");
				mapCountry2Category.put("Cayman Islands","high");
				mapCountry2Category.put("Channel Islands","high");
				mapCountry2Category.put("Chile","high");
				mapCountry2Category.put("Curaçao","high");
				mapCountry2Category.put("Cyprus","high");
				mapCountry2Category.put("Czech Republic","high");
				mapCountry2Category.put("Denmark","high");
				mapCountry2Category.put("Estonia","high");
				mapCountry2Category.put("Faroe Islands","high");
				mapCountry2Category.put("Finland","high");
				mapCountry2Category.put("France","high");
				mapCountry2Category.put("French Polynesia","high");
				mapCountry2Category.put("Germany","high");
				mapCountry2Category.put("Gibraltar","high");
				mapCountry2Category.put("Greece","high");
				mapCountry2Category.put("Greenland","high");
				mapCountry2Category.put("Guam","high");
				mapCountry2Category.put("Hong Kong SAR, China","high");
				mapCountry2Category.put("Hungary","high");
				mapCountry2Category.put("Iceland","high");
				mapCountry2Category.put("Ireland","high");
				mapCountry2Category.put("Isle of Man","high");
				mapCountry2Category.put("Israel","high");
				mapCountry2Category.put("Italy","high");
				mapCountry2Category.put("Japan","high");
				mapCountry2Category.put("Korea, Rep.","high");
				mapCountry2Category.put("Kuwait","high");
				mapCountry2Category.put("Latvia","high");
				mapCountry2Category.put("Liechtenstein","high");
				mapCountry2Category.put("Lithuania","high");
				mapCountry2Category.put("Luxembourg","high");
				mapCountry2Category.put("Macao SAR, China","high");
				mapCountry2Category.put("Malta","high");
				mapCountry2Category.put("Monaco","high");
				mapCountry2Category.put("Netherlands","high");
				mapCountry2Category.put("New Caledonia","high");
				mapCountry2Category.put("New Zealand","high");
				mapCountry2Category.put("Northern Mariana Islands","high");
				mapCountry2Category.put("Norway","high");
				mapCountry2Category.put("Oman","high");
				mapCountry2Category.put("Palau","high");
				mapCountry2Category.put("Poland","high");
				mapCountry2Category.put("Portugal","high");
				mapCountry2Category.put("Puerto Rico","high");
				mapCountry2Category.put("Qatar","high");
				mapCountry2Category.put("San Marino","high");
				mapCountry2Category.put("Saudi Arabia","high");
				mapCountry2Category.put("Seychelles","high");
				mapCountry2Category.put("Singapore","high");
				mapCountry2Category.put("Sint Maarten (Dutch part)","high");
				mapCountry2Category.put("Slovak Republic","high");
				mapCountry2Category.put("Slovenia","high");
				mapCountry2Category.put("Spain","high");
				mapCountry2Category.put("St. Kitts and Nevis","high");
				mapCountry2Category.put("St. Martin (French part)","high");
				mapCountry2Category.put("Sweden","high");
				mapCountry2Category.put("Switzerland","high");
				mapCountry2Category.put("Taiwan, China","high");
				mapCountry2Category.put("Trinidad and Tobago","high");
				mapCountry2Category.put("Turks and Caicos Islands","high");
				mapCountry2Category.put("United Arab Emirates","high");
				mapCountry2Category.put("United Kingdom","high");
				mapCountry2Category.put("United States","high");
				mapCountry2Category.put("Uruguay","high");
				mapCountry2Category.put("Virgin Islands (U.S.)","high");
				//lCountries.add("");
				lCountries.addAll(mapCountry2Category.keySet());

				mapCategory2Maxfree.put("low", 10.0);
	}

	public String getCountryCateogory(String country) {
		return mapCountry2Category.get(country);
	}
	/*
	public Double getPrice(int grams, String country, String stockType, String userType) {
		
		if(userType.equals("pub")) {
			String countryCat=getCountryCateogory(country);
			if(countryCat.equals("")) {
				if(grams<=10) return 0.0;
				else return getPricePer10gram( stockType,  countryCat)*(grams-10)/10;
			} else 
				return getPricePer10gram( stockType,  countryCat)*grams/10;
		} else if(userType.equals("priv")) {
			return getPricePer10gram("PL", "priv")*grams/10;
		} else if(userType.equals("irri")) {
			return getPricePer10gram("PL", "irri")*grams/10;
		}
		return null;
	}
*/
	
	public  Double getPricePer10gram(String stockType, String countryCat) {
		return mapPrice.get(stockType).get(countryCat);
	}
	
	public  Double getMaxFreeGram(String countryCat) {
		Double maxfree=mapCategory2Maxfree.get(countryCat);
		if(maxfree==null) return 0.0;
		return maxfree;
	}
	
	public  String getCountryCategory(String country) {
		return mapCountry2Category.get(country);
	}

	public  List getCountries() {
		// TODO Auto-generated method stub
		return lCountries;
	}
	
}
