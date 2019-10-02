package org.brapi.client;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jettison.json.JSONObject;
import org.irri.iric.portal.domain.Passport;

public class BrapiAttributes {
	JSONObject json;

	public BrapiAttributes(JSONObject json) {
		super();
		this.json = json;
	}
	
	
	
//	
//	public Set getAtrributeValues() {
//		Set s=new LinkedHashSet();
//		Iterator keys=json.keys();
//		while(keys.hasNext()) {
//			try {
//				if(attclass.getSimpleName().equals("Passport")) {
//					Passport p=new BrapiPassport( json.getJSONObject((String)keys.next()) );
//					if (p.getValue()==null || p.getValue().isEmpty()) continue;
//					s.add(p);
//				} else if(attclass.getSimpleName().equals("String")) {
//						//String v= json .getJSONObject((String)keys.next()) );
//						//S
//						if (p.getValue()==null || p.getValue().isEmpty()) continue;
//						s.add(p);
//					}
//				}
//			} catch(Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//		return s;
//	}
	
}
