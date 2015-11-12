package org.iric.portal.remoteserver;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONException;
import org.irri.iric.portal.domain.LocalAlignmentImpl;


import org.irri.iric.portal.genomics.LocalAlignmentQuery;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class LocalClient {

	
	 private WebResource service;
		
	  public LocalClient(String uri) {
			super();
			// TODO Auto-generated constructor stub
			   ClientConfig config = new DefaultClientConfig();
			   Client client = Client.create(config);
			   service = client.resource(uri);
	  }
	  
	  
	  
	  
	  public List<LocalAlignmentImpl> postBlast(LocalAlignmentQuery query) throws Exception {
		   	MultivaluedMap queryParams = new MultivaluedMapImpl();
		    ObjectMapper mapper = new ObjectMapper();
		    
		    /*
		    public Response postBlast(@FormParam("sequence") String sequence, 
					  @FormParam("program") String program,  @FormParam("db") String dbtype,  
					  @DefaultValue("10") @FormParam("maxe") Double maxevalue
			  		) throws JSONException 
			  		*/
		    
		    queryParams.add("sequence", query.getQueryseq());
		    queryParams.add("program", query.getQuerytype());
		    queryParams.add("db", query.getDbname());
		    queryParams.add("maxe", Double.toString(query.getEvalue() ));

		    //String json = service.path("ws").path("blast").path("postblast").queryParams(queryParams).accept(MediaType.APPLICATION_JSON).get(String.class);
		    String json = service.path("ws").path("blast").path("postblast").queryParams(queryParams).accept(MediaType.APPLICATION_JSON).post(String.class);
		    
		    return (List<LocalAlignmentImpl>)mapper.readValue(json, new TypeReference<List<LocalAlignmentImpl>>() { });
	}
	 
	  public List<LocalAlignmentImpl>getBlast(LocalAlignmentQuery query) throws Exception {
		   	MultivaluedMap queryParams = new MultivaluedMapImpl();
		    ObjectMapper mapper = new ObjectMapper();
		    
		    /*
		    public Response postBlast(@FormParam("sequence") String sequence, 
					  @FormParam("program") String program,  @FormParam("db") String dbtype,  
					  @DefaultValue("10") @FormParam("maxe") Double maxevalue
			  		) throws JSONException 
			  		*/
		    
		    queryParams.add("sequence", query.getQueryseq());
		    queryParams.add("program", query.getQuerytype());
		    queryParams.add("db", query.getDbname());
		    queryParams.add("maxe", Double.toString(query.getEvalue() ));

		    String json = service.path("ws").path("blast").path("getblast").queryParams(queryParams).accept(MediaType.APPLICATION_JSON).get(String.class);
		    //String json = service.path("ws").path("blast").path("postblast").queryParams(queryParams).accept(MediaType.APPLICATION_JSON).post(String.class);
		    
		    return (List<LocalAlignmentImpl>)mapper.readValue(json, new TypeReference<List<LocalAlignmentImpl>>() { });
	}
}
