package org.irri.iric.portal.ws.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantTable;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.genotype.service.VariantTableArraysImpl;
import org.irri.iric.portal.genotype.zkui.FakerMatrixModel;
import org.irri.iric.portal.genotype.zkui.SNPAllvarsRowRenderer;
import org.irri.iric.portal.genotype.zkui.SNPListItemRenderer;
import org.irri.iric.portal.genotype.zkui.SNPMatrixRenderer;
import org.irri.iric.portal.variety.service.VarietyFacade;
//import org.irri.iric.portal.ws.entity.VariantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkmax.zul.MatrixModel;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;

import com.sun.jersey.spi.resource.Singleton;

@Controller("GenotypeWebService")
@Path("/genotype")
public class GenotypeWS {

	@Autowired
	private GenotypeFacade genotype;
	@Autowired
	private VarietyFacade variety;
	
	
	private Map<String,String> mapVarReplace = new HashMap(); 
	
	public GenotypeWS() {
		super();
		// TODO Auto-generated constructor stub
		genotype = (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
		variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		AppContext.debug("CopyOfCopyOfGenotypeWS started");
		
		mapVarReplace.put("iricStockId", "varietyId");
		mapVarReplace.put("irisUniqueId", "irisId");
		mapVarReplace.put("oriCountry", "country");

		
	}

	  @GET
	  @Path("/variety")
	  @Produces("application/json")
	  public Response getVarieties() throws JSONException {
			
		  try {
		Set vars =  variety.getGermplasm() ;
		
		
		/*
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("varieties", vars); 
		//String result = "@Produces(\"application/json\") Output: \n\nList of varieties: \n\n" + jsonObject;
		//String result = "@Produces(\"application/json\") Output: \n\nList of varieties: \n\n" + jsonObject;
		//return Response.status(200).entity(result).build();
		return Response.status(200).entity(jsonObject.toString()).build();
		*/
		
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
		
	  }
	  
 
	  @Path("/variety/{id}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesById(@PathParam("id") long lId) throws JSONException {
 
//		JSONObject jsonObject = new JSONObject();
//		Variety var = variety.getMapId2Variety().get(BigDecimal.valueOf(lId));
//		jsonObject.put("variety", var); 
//		String result = "@Produces(\"application/json\") Output: \n\nVariety Id=" + lId + ": \n\n" + jsonObject;
//		return Response.status(200).entity(result).build();
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString( variety.getMapId2Variety().get(BigDecimal.valueOf(lId)) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }

	  }

	  @Path("/variety/subpopulation/{subpop}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesBySubpopulation(@PathParam("subpop") String sSubpop) throws JSONException {
 
//		JSONObject jsonObject = new JSONObject();
//		Set vars = variety.getGermplasmBySubpopulation(sSubpop);
//		jsonObject.put("varieties", vars); 
//		String result = "@Produces(\"application/json\") Output: \n\nVariety subpopulation=" + sSubpop  + ": \n\n" + jsonObject;
//		return Response.status(200).entity(result).build();

		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString( variety.getGermplasmBySubpopulation(sSubpop) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  
	  }

	  @Path("/variety/subpopulation")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesSubpopulation() throws JSONException {
 
//		JSONObject jsonObject = new JSONObject();
//		Set vars = new LinkedHashSet(variety.getSubpopulations());
//		jsonObject.put("subpopulations", vars); 
//		String result = "@Produces(\"application/json\") Output: \n\nVariety subpopulations: \n\n" + jsonObject;
//		return Response.status(200).entity(result).build();
		  try {
			return Response.status(200).entity(  new ObjectMapper().writeValueAsString( variety.getSubpopulations() )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }

	  
	  @Path("/variety/country/{country}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesByCountry(@PathParam("country") String sCountry) throws JSONException {
 
//		JSONObject jsonObject = new JSONObject();
//		Set vars = variety.getGermplasmByCountry(sCountry);
//		jsonObject.put("varieties", vars); 
//		String result = "@Produces(\"application/json\") Output: \n\nVariety country=" + sCountry  + ": \n\n" + jsonObject;
//		return Response.status(200).entity(result).build();
		  try {
			return Response.status(200).entity( AppContext.replaceString( new ObjectMapper().writeValueAsString(variety.getGermplasmByCountry(sCountry) ), mapVarReplace )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }
	  
	  @Path("/variety/country")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesCountry() throws JSONException {
 
//		JSONObject jsonObject = new JSONObject();
//		Set vars = new LinkedHashSet(variety.getCountries());
//		jsonObject.put("countries", vars); 
//		String result = "@Produces(\"application/json\") Output: \n\nVariety countries: \n\n" + jsonObject;
//		return Response.status(200).entity(result).build();
		  try {
			return Response.status(200).entity(  new ObjectMapper().writeValueAsString( variety.getCountries() ) ).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }
	  
	  @Path("/variety/name")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesNames() throws JSONException {
 
		  try {
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( variety.getVarietyNames() )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
	  }

	  @Path("/variety/namelike/{name}")
	  @GET
	  @Produces("application/json")
	  public Response getVarietiesNameLike(@PathParam("name") String sName) throws JSONException {
 
		  try {
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( variety.getGermplasmByNameLike( sName + "%") )).build();
		  } catch(Exception ex) {
			  throw new JSONException(ex);
		  }
		
	  }


	  @Path("/gettable")
	  @GET
	  @Produces("application/json")
	  public Response getVariantByVarietyId(@DefaultValue("all")  @QueryParam("varid") String sVarids, 
			  @QueryParam("chr") String sChr,  @QueryParam("start") Long lStart, @QueryParam("end") Long lEnd,
			  @DefaultValue("true") @QueryParam("snp") boolean bSNP,  @DefaultValue("false") @QueryParam("indel") boolean bIndel,
			  @DefaultValue("false") @QueryParam("coreonly") boolean bCoreonly, @DefaultValue("false") @QueryParam("mismatchonly") boolean bMismatchonly,
			  @QueryParam("poslist") String sSnppos ,  @QueryParam("subpopulation") String sSubpopulation,  @QueryParam("locus") String sLocus
			   ) throws JSONException {
 		
		//if(sChr==null) throw new JSONException("parameters chr is required");
		if(   sChr==null && sLocus==null && sSnppos==null) throw new JSONException("parameters (chr,start,end) OR locus OR (chr AND poslist) is required");
		  	
		if((sSnppos==null || sSnppos.isEmpty()) && (lStart==null || lEnd==null) && sLocus==null) throw new JSONException("parameters (start AND end) OR poslist OR locus is required");  
		if(sSnppos!=null && !sSnppos.isEmpty() && lStart!=null && lEnd!=null) throw new JSONException("only one of either (start AND end) OR poslist is required");  
		
		//if(!sVarids.equals("all") && sSubpopulation==null)  throw new JSONException("parameter varid OR subpopulation is required");  
		if(!sVarids.equals("all") && sSubpopulation!=null)  throw new JSONException("only one of either varid OR subpopulation is required");  
		
		Collection<BigDecimal> colVarIds = null;  
		if(!sVarids.equals("all")) {
			String vaidss[]=sVarids.split(",");
			colVarIds = new HashSet();
			for(int ivar = 0; ivar<vaidss.length; ivar++) {
				colVarIds.add(  BigDecimal.valueOf( Long.valueOf(vaidss[ivar] )) );
			}
	  	}
		
		Collection<BigDecimal> colPos = null;  
		if(sSnppos!=null) {
			String pos[]=sSnppos.split(",");
			colPos = new HashSet();
			for(int ipos = 0; ipos<pos.length; ipos++) {
				colPos.add(  BigDecimal.valueOf( Long.valueOf(pos[ipos] )) );
			}
	  	}
	  
		AppContext.debug( "params=" + colVarIds + " " + sChr + " " + lStart + " " +lEnd + " " + bSNP + " " +bIndel + " " + bCoreonly + " " + 
				bMismatchonly + " " + colPos + " " + sSubpopulation + " " + sLocus );
		
		try {
			VariantTable table = getVariantTable(colVarIds, sChr, lStart, lEnd, bSNP, bIndel, bCoreonly, bMismatchonly, colPos, sSubpopulation, sLocus);
			if(table==null) throw new JSONException("VariantTable is null");

//			ObjectMapper mapper = new ObjectMapper();
//			JSONObject jsonObject = new JSONObject();
//			/*
//			
//			jsonObject.put("variants", mapper.writeValueAsString(table)); 
//			String result = "";
//			if(sLocus!=null)
//				result = "@Produces(\"application/json\") Output: \n\nVariants table locus=" + sLocus + ": \n\n" + jsonObject;
//			else result = "@Produces(\"application/json\") Output: \n\nVariants table chr=" + sChr + ", start=" + lStart + ", end=" + lEnd + ": \n\n" + jsonObject;
//			*/
//			jsonObject.put("variants", mapper.writeValueAsString(table));
//			//String result = mapper.writeValueAsString(table);
//			return Response.status(200).entity(jsonObject.toString()).build();
			
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( table )).build();

			
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
		
	  }

	  @Path("/posttable")
	  @POST
	  @Produces("application/json")
	  public Response postVariantByVarietyId(@FormParam("varid") List<Long> lVarids, 
			  @FormParam("chr") String sChr,  @FormParam("start") Long lStart, @FormParam("end") Long lEnd,
			  @DefaultValue("true") @FormParam("snp") boolean bSNP,  @DefaultValue("false") @FormParam("indel") boolean bIndel,
			  @DefaultValue("false") @FormParam("coreonly") boolean bCoreonly, @DefaultValue("false") @FormParam("mismatchonly") boolean bMismatchonly ,
			  @FormParam("poslist") List<Long> lSnppos,   @FormParam("subpopulation") String sSubpopulation,  @FormParam("locus") String sLocus
			   ) throws JSONException {
 
		  	if(sChr==null && sLocus==null && lSnppos.isEmpty()) throw new JSONException("parameters (chr,start,end) OR locus OR (chr AND poslist) is required");  
		  
			if(lSnppos.isEmpty() && (lStart==null || lEnd==null) && sLocus==null) throw new JSONException("parameters (start AND end) OR poslist is required");  
			if(!lSnppos.isEmpty() && lStart!=null && lEnd!=null) throw new JSONException("only one of either (start AND end) OR poslist is required");  
		  
			//if(lVarids.isEmpty() && sSubpopulation==null)  throw new JSONException("parameter varid OR subpopulation is required");
			if(!lVarids.isEmpty() && sSubpopulation!=null)  throw new JSONException("only one of either varid OR subpopulation is required");  

			
			Collection<BigDecimal> colVarIds = null;  
			if(lVarids!=null && !lVarids.isEmpty()) {
				Iterator<Long> itvarids = lVarids.iterator();
				while(itvarids.hasNext()) {
					colVarIds.add(  BigDecimal.valueOf( itvarids.next() ));
				}
		  	}
			
			Collection<BigDecimal> colPos = null;  
			if(lSnppos!=null && !lSnppos.isEmpty()) {
				Iterator<Long> itpos = lSnppos.iterator();
				while(itpos.hasNext()) {
					colVarIds.add(  BigDecimal.valueOf( itpos.next() ));
				}
		  	}

			AppContext.debug( "params=" + colVarIds + " " + sChr + " " + lStart + " " +lEnd + " " + bSNP + " " +bIndel + " " + bCoreonly + " " + 
					bMismatchonly + " " + colPos + " " + sSubpopulation + " " + sLocus );
			
			try {
			VariantTable table = getVariantTable(colVarIds, sChr, lStart, lEnd, bSNP, bIndel, bCoreonly,bMismatchonly, colPos,sSubpopulation, sLocus); 
			
			if(table==null) throw new JSONException("VariantTable is null");
			
//			JSONObject jsonObject = new JSONObject();
//			ObjectMapper mapper = new ObjectMapper();
//			jsonObject.put("variants", mapper.writeValueAsString(table));
//			String result = "";
//			if(sLocus!=null)
//				result = "@Produces(\"application/json\") Output: \n\nVariants table locus=" + sLocus + ": \n\n" + jsonObject;
//			else result = "@Produces(\"application/json\") Output: \n\nVariants table chr=" + sChr + ", start=" + lStart + ", end=" + lEnd + ": \n\n" + jsonObject;
//			
//			return Response.status(200).entity(result).build();
//			
			return Response.status(200).entity( new ObjectMapper().writeValueAsString( table )).build();

			} catch(Exception ex) {
				throw new JSONException(ex);
			}
	  }
	  
	  /*
	  	@POST
	    @Path("/sendemail")
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response sendEmail(@FormParam("email") String email) {
	        System.out.println(email);
	        return Response.ok("email=" + email).build();
	    }
	  */
	  
	  
	    //The resource look like this
	  /*
	    @Path("/variants")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void setJsonl(String array){
	        JSONArray o = new JSONArray(last_data);
	        System.out.println(o.toString());
	    }
	    */    
	  
	 private VariantTable getVariantTable(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP, boolean bIndel,
			 boolean bCoreonly, boolean bMismatchonly, Collection poslist, String sSubpopulation, String sLocus) throws Exception {
		 
			
			// true if output is written to file
//			boolean writetofile= filename!=null && !filename.isEmpty();
		 	boolean bAllvars = colVarIds==null;
			
			// to facilitate render on Tab select for JBrowse and phylogenetic tree
			GenotypeFacade.snpQueryMode mode=null;
			
//			if(writetofile)
//				AppContext.debug("Writing to file");
//			else
//			{
//				
//				// empty table from previous results
//				snpallvarsresult.setModel(new SimpleListModel(new java.util.ArrayList()));
//				snpresult.setModel(new SimpleListModel(new java.util.ArrayList()));
//
//				// hide the tables
//				snpallvarsresultpaging.setVisible(false);
//			    snpallvarsresult.setVisible(false);
//			    snpresult.setVisible(false);
//			    
//			    //snpallvarsresult.setWidth("1000px");
//			    snpresult.setWidth("1000px");
//			    
//			    tabTable.setSelected(true);
//			    tabJbrowse.setVisible(false);
//			    tabPhylo.setVisible(false);
//			    tabJBrowsePhylo.setVisible(false);
//			
//			    
//				gfffile=null;
//				urljbrowse = null;	// if has URL address, will render on TabSelect on JBrowse
//				urlphylo = null;   // if has URL address, will render on TabSelect on Phylotree
//				tallJbrowse = false;	// jbrowse if tall (for all variety) or short (for 2 varieties)
//				urljbrowsephylo = null;
//				snpallvars_pagesize=100;	 // initial rows per page
//				origAllVarsListModel = null;
//				list2Vars = null;
//				filteredList = null;
//				hboxFilterAllele.setVisible(false);
//				hboxDownload.setVisible(false);
//				
//				labelFilterResult.setValue("");
//			}
			
			
			//tabTable.setWidth("100%");
			//tabboxDisplay.setWidth("1000px");
			//win.setWidth(labelScreenWidth.getValue());
			
			//AppContext.debug("origwidth=" + labelScreenWidth.getValue());
			
				genotype =  (GenotypeFacade)AppContext.checkBean(genotype , "GenotypeFacade");

//				workspace =  (WorkspaceFacade)AppContext.checkBean(workspace , "WorkspaceFacade");
//			
//				if(this.listboxMySNPList.getSelectedIndex()>0)
//					msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " , list " + listboxMySNPList.getSelectedItem().getLabel() );
//				else if(intStart.getValue()!=null)
//					msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
//				else 
//					msgbox.setValue("SEARCHING: in chromosome " + selectChr.getSelectedItem().getLabel() );
//				
//				String var1= comboVar1.getValue().trim().toUpperCase();
//				String var2 =  comboVar2.getValue().trim().toUpperCase();

				
			String msgbox = "";
			if(poslist==null)
				msgbox="SEARCHING: in chromosome " + sChr + " [" + lStart +  "-" + lEnd +  "]" ;
			else 
				msgbox="SEARCHING: in chromosome " + sChr;
			
				
				
				Set setVarieties = null; 
				
				if(colVarIds==null) {
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
				} else {
					setVarieties = new HashSet(colVarIds);
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
				}
				
		/*			
				} else if( comboSubpopulation.getValue()!=null &&  !comboSubpopulation.getValue().isEmpty() ) {
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
					setVarieties = genotype.getVarietiesForSubpopulation(comboSubpopulation.getValue().trim());
					*/
				if( sSubpopulation!=null && !sSubpopulation.isEmpty()) {
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
					setVarieties = genotype.getVarietiesForSubpopulation( sSubpopulation );
				}

				

				
//				} else if( listboxMyVarieties.getSelectedIndex()>0) {
//					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
//					setVarieties = workspace.getVarieties( listboxMyVarieties.getSelectedItem().getLabel()  );
//					//mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
//					//setVarieties = genotype.getVarietiesForSubpopulation(comboSubpopulation.getValue().trim());
//				}
//				else {
//					if(!var1.isEmpty() && !var2.isEmpty()) {
//						if(checkboxMismatchOnly.isChecked())
//							mode = GenotypeFacade.snpQueryMode.SNPQUERY_VARIETIES;
//						else
//							mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLREFPOS; //SNPQUERY_REFERENCE;
//					}
//					else if(var1.isEmpty() || var2.isEmpty() )
//					{
//						//Messagebox.show("At least one variety is required", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
//						Messagebox.show("Two varieties are required for comparison, or check All Varieties", "INVALID VARIETIES VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
//						
//						return;	
//					}
//					//else
//					//	mode = GenotypeFacade.snpQueryMode.SNPQUERY_REFERENCE;
//				}

		
				
				// set genotype facade state
				genotype.limitVarieties(setVarieties);	
				genotype.setCore( bCoreonly );
				genotype.setColorByNonsyn( false );
				genotype.setNonsynOnly( false );
				genotype.setMismatchOnly( bMismatchonly );
				genotype.setIncludeSNP( bSNP );
				genotype.setIncludeIndel( bIndel );
				
				// initialize empty SNP list
				List listSNPs = new java.util.ArrayList();

				String genename = "";
				if(sLocus!=null) genename = sLocus.toUpperCase();
				//gfffile = "tmp_" + AppContext.createTempFilename() + ".gff";		
				
				AppContext.startTimer();

				if( !genename.isEmpty() )
				{
					Gene gene2 =  genotype.getGeneFromName( genename);
					lStart = Long.valueOf(gene2.getFmin()) ;	
					lEnd =  Long.valueOf(gene2.getFmax());	
					//selectChr.setSelectedIndex( gene2.getChr()-1 );
					//sChr =
					sChr = gene2.getChr().toString();
				}
				

					// SNPs on all varieties in region
					// vier chr pos region
				
					//int chrlen = genotype.getFeatureLength( selectChr.getSelectedItem().getLabel());
			
					Set snpposlist = null;
					
//					if(listboxMySNPList.getSelectedIndex()>0) {
//						
//						String chrlistname[] = listboxMySNPList.getSelectedItem().getLabel().split(":");
//						snpposlist = workspace.getSnpPositions( Integer.valueOf( chrlistname[0].replace("CHR","").trim() ) , chrlistname[1].trim() );
//						
//					} else if(this.selectChr.getSelectedItem().getLabel().equals("whole genome") ) {
//						if( this.listboxSubpopulation.getSelectedIndex() > 0 ) {
//							Messagebox.show("Limit to 100 varieties (using a list) for every whole genome download", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
//							return;  		
//						}
//					}
					
					if(true) {
						int chrlen = genotype.getFeatureLength( sChr );
						
//						if(intStop==null || intStart==null || !intStop.isValid() || !intStart.isValid() || intStop.getValue()==null || intStart.getValue()==null )
//						{
//							Messagebox.show("Please provide start and end positions", "INVALID POSITION VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
//							return;  					
//						}   
						
						if(lEnd> chrlen  ||lStart> chrlen)
						{
							throw new Exception("Positions should be less than length");
						} 
						if(lEnd<1  || lStart<1)
						{
							throw new Exception("Positions should be positive integer");
						}   				
						if(lEnd<lStart)
						{
							throw new Exception("End should be greater than or equal to start");
						}  

						// if length > maxlengthUni, change to core
						
						// if length > maxlengthCore, not allowed

						
						int maxlength = -1;
						String limitmsg = "";
						if(bAllvars && !bCoreonly) {
							maxlength = AppContext.getMaxlengthUni();
							limitmsg = "Limit to " + (maxlength/1000) + " kbp range for All Snps, or " + AppContext.getMaxlengthCore()/1000 + " kbp for Core Snps, All Varieties query.";
						} else if(bAllvars && bCoreonly ) {
							maxlength = AppContext.getMaxlengthCore();
							limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Core Snps, all varieties query";
						} else
						{
							maxlength = AppContext.getMaxlengthPairwise();
							limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Pairwise variety query";
						}
						
						long querylength = lEnd-lStart; 
						//if(!checkboxCoreSNP.isChecked() && querylength > maxlength )
						if( querylength > maxlength )
						{
							throw new Exception(limitmsg);
//							if( querylength > AppContext.getMaxlengthCore() && !bCoreonly ) { 
//								throw new Exception(limitmsg);
//							} else
//							{
//								
//								//Messagebox.show("Query too long for all SNPs, Use Core SNPs instead", "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
//								if( Messagebox.show("Query too long for all SNPs, will use Core SNPs instead. Do you want to porceed?", "OPERATION NOT ALLOWED", Messagebox.NO | Messagebox.YES , Messagebox.QUESTION)
//										== Messagebox.YES  )
//								{
//									checkboxCoreSNP.setChecked(true);
//									genotype.setCore( true );
//									maxlength =  AppContext.getMaxlengthCore();
//								}
//								else return;
//								
//							}
						}   
						
						
						/*
						int maxlength = 2000000000;
						String limitmsg = "Limit to 2 Mbp range for Core SNPs, all varieties query";
						if(checkboxAllvarieties.isChecked()  && !checkboxCoreSNP.isChecked()) {
							maxlength = 200000000;
							limitmsg = "Limit to 200 kbp range for All Snps, all varieties query";
						};
						
						if( (intStop.getValue()-intStart.getValue()) > maxlength )
						{
							Messagebox.show(limitmsg, "OPERATION NOT ALLOWED", Messagebox.OK, Messagebox.EXCLAMATION);
							msgbox.setStyle( "font-color:red" );
							return;  					
						}  
						*/
						
					}
					
					
//					String chr = sChr;
//					if(chr.isEmpty())
//					{
//						Messagebox.show("No chromosome selected", "INVALID CHROMOSOME VALUE", Messagebox.OK, Messagebox.EXCLAMATION);
//						return;
//					}
//					
//					
//					
//					if(!comboGene.getValue().isEmpty())
//						msgbox.setValue("Searching within GENE " + comboGene.getValue().toUpperCase()  + " " + chr + " [" + 
//								intStart.getValue() + ".." + intStop.getValue() + "] ");
//					else if(listboxMySNPList.getSelectedIndex()>0)
//						msgbox.setValue("SEARCHING: in myList " + listboxMySNPList.getSelectedItem().getLabel()  );
//					else
//						msgbox.setValue("SEARCHING: in chromosome " + chr + " [" + intStart.getValue() +  "-" + intStop.getValue()+  "]" );
//					
//					msgbox.setStyle( "font-color:black" );
					
					String chr= sChr;
					if(sLocus!=null && !sLocus.isEmpty())
						msgbox="Searching within GENE " + sLocus.toUpperCase()  + " " + chr + " [" + lStart + ".." + lEnd + "] ";
//					else if(listboxMySNPList.getSelectedIndex()>0)
//						msgbox.setValue("SEARCHING: in myList " + listboxMySNPList.getSelectedItem().getLabel()  );
					else
						msgbox="SEARCHING: in chromosome " + chr + " [" + lStart +  "-" + lEnd +  "]" ;
					
					
					if(mode== GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS ){


						List  newpagelist; 
						
//						if(writetofile) {
//							
//							AppContext.debug("downloading file...");
//							
//							if(snpposlist!=null && !snpposlist.isEmpty()) {
//								
//								newpagelist = genotype.getSNPStringInAllVarieties(snpposlist, Integer.valueOf(chr)); //, checkboxCoreSNP.isChecked());, true,  -1,  -1 );
//								updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + "  SNPLIST: " + listboxMySNPList.getSelectedItem().getLabel() );
//						        AppContext.resetTimer("query allvars region " + snpposlist.size() + " size snplist");	
//							}
//							else if(selectChr.getSelectedItem().getLabel().equals("whole genome")) {
//								
//
//								/*
//								StringBuffer buffMap = new StringBuffer();
//								Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
//								while(itPos.hasNext()) {
//									SnpsAllvarsPos posnuc=itPos.next();
//									buffMap.append(posnuc.getPos());
//								}				
//								
//								File file = new File(filename);
//								FileWriter fw = new FileWriter(file.getAbsoluteFile());
//								BufferedWriter bw = new BufferedWriter(fw);
//								bw.write(buff.toString());
//								bw.flush();
//								bw.close();
//								*/
//								
//								
//								String filenames[] = new String[12];
//								String tmpname = AppContext.createTempFilename();
//								String fileext = ".txt";
//								if(delimiter.equals(",")) fileext = ".csv";
//								else if(delimiter.equals("plink")) fileext = ".ped";
//
//								
//								for(int ichr=1; ichr<=12; ichr++) {
//									Integer chrlen = genotype.getFeatureLength( Integer.toString(ichr));
//									newpagelist = genotype.getSNPStringInAllVarieties(0, chrlen, Integer.valueOf(ichr)); //,  true,  -1,  -1 );
//									filenames[ichr-1]="chr-" + ichr + "-" +  filename + "-" + tmpname +  fileext;
//									updateAllvarsList(newpagelist,true,filenames[ichr-1] ,delimiter,  "REGION: WHOLE CHR " + ichr + " " +  1 + ".." + chrlen, false , Integer.valueOf(ichr));
//									
//									
//									
//								}
//								
//								new CreateZipMultipleFiles(filename + "-" + tmpname + ".zip", filenames ).create();
//								
//								try {
//									Filedownload.save(new File(filename + "-" + tmpname + ".zip") , "application/zip");
//									AppContext.debug("File download complete! Saved to: "+ filename);
//									} catch(Exception ex)
//									{
//										ex.printStackTrace();
//									}
//								
//						        AppContext.resetTimer("query whole genome");	
//								
//							}
//							else {
//								//newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr), false,  -1,  -1 );
//								newpagelist = genotype.getSNPStringInAllVarieties(intStart.getValue(), intStop.getValue(), Integer.valueOf(chr)); //,  true,  -1,  -1 );
//								updateAllvarsList(newpagelist,true,filename,delimiter,  "REGION: CHR " + chr + " " +  intStart.getValue() + ".." + intStop.getValue() );
//						        AppContext.resetTimer("query allvars region " + (intStop.getValue() - intStart.getValue()) + " bp");	
//								
//							}
//
//							return;
//						}
						
						/*
						if( intStop.getValue()- intStart.getValue()>10000)
						{
							serverpaging=true;
							snpallvars_pagesize=50;
						}
						if( intStop.getValue()- intStart.getValue()>25000)
						{
							serverpaging=true;
							snpallvars_pagesize=25;
						}
						if( intStop.getValue()- intStart.getValue()>50000)
						{
							serverpaging=true;
							snpallvars_pagesize=10;
						}
						if( intStop.getValue()- intStart.getValue()>100000)
						{
							serverpaging=true;
							snpallvars_pagesize=5;
						}
						*/

						//snpallvarsresult.setAttribute("org.zkoss.zul.grid.rod", true);
						
						if(snpposlist!=null && !snpposlist.isEmpty()) {
							newpagelist = genotype.getSNPStringInAllVarieties(snpposlist, Integer.valueOf(chr)); //, this.checkboxCoreSNP.isChecked());  true,  -1,  -1 );
							
						}
						else {
//							if( intStop.getValue()- intStart.getValue()>50000)
//								snpallvars_pagesize=25;
//							if( intStop.getValue()- intStart.getValue()>100000)
//								snpallvars_pagesize=10;
//							
							newpagelist = genotype.getSNPStringInAllVarieties(new Long(lStart).intValue(), new Long(lEnd).intValue(), Integer.valueOf(chr)); //,   true,  -1,  -1 );
						}
						
						//updateAllvarsList(newpagelist, true, null, null, "",-1,-1);
						
						listSNPs = newpagelist;
						
						if(genotype.getSnpsposlist()!=null &&  genotype.getMapIndelIdx2Pos()!=null && genotype.getSnpsposlist().size()!=genotype.getMapIndelIdx2Pos().size())
							msgbox += " ... RESULT: " + newpagelist.size() + " varieties x " + (genotype.getSnpsposlist().size()-genotype.getMapIndelIdx2Pos().size()) + 
									" SNP, " + genotype.getMapIndelIdx2Pos().size() + " INDEL positions";
						else if(genotype.getMapIndelIdx2Pos()!=null)
							msgbox +=  " ... RESULT: " + newpagelist.size() + " varieties x " + genotype.getMapIndelIdx2Pos().size() + " INDEL positions" ;
						else if(genotype.getSnpsposlist()!=null)
							msgbox +=  " ... RESULT: " + newpagelist.size() + " varieties x " + genotype.getSnpsposlist().size() + " SNP positions" ;

				        AppContext.resetTimer("create table" );

				        
						VariantTable variants = createGenotypeTable(newpagelist);
						variants.setMessage(msgbox);
						
						return variants;

				        
					}
					
					return null;
					
		}

private VariantTable createGenotypeTable(List<SnpsStringAllvars> listSNPs) //, boolean updateHeaders, String filename, String delimiter, String header, boolean doDownload, Integer chromosome) //, int firstRow, int numRows)
{

	VariantTableArraysImpl vartable = new VariantTableArraysImpl();
	
	boolean fetchMismatchOnly =  AppContext.isSNPAllvarsFetchMismatchOnly();  //listSNPs contains only NULL and referfence mismatches
	
	
	List<SnpsAllvarsPos> snpsposlist = genotype.getSnpsposlist();
		
	
	Long posarr[] = new Long[snpsposlist.size()]; 
	String refnuc[] = new String[snpsposlist.size()];
	Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
	int poscount = 0;
	while(itPos.hasNext()) {
		SnpsAllvarsPos posnuc=itPos.next();
		posarr[poscount] = posnuc.getPos().longValue();
		refnuc[poscount] = posnuc.getRefnuc();
		poscount++;
	}
	vartable.setPosarr(posarr);
	vartable.setRefnuc(refnuc);
		
	
	
		List listTable= listSNPs;
		
			variety = (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
			Map<BigDecimal,Variety> mapVarid2Variety = variety.getMapId2Variety();
			

		String varnames[] = new String[listTable.size()];
		Long varids[] = new Long[listTable.size()];
		Double varmismatch[] = new Double[listTable.size()];
		String allelestring[][] = new String[listTable.size()][snpsposlist.size()];
	
		Iterator<SnpsStringAllvars> itSnpstring = listTable.iterator();
		int varcount = 0;
		while(itSnpstring.hasNext()) {
			SnpsStringAllvars snpstr = itSnpstring.next();
			
			varmismatch[varcount]=snpstr.getMismatch().doubleValue();
			varnames[varcount]=mapVarid2Variety.get( snpstr.getVar() ).getName();
			varids[varcount]= snpstr.getVar().longValue();
			
			allelestring[varcount] = getVarietyIndelString(snpstr,snpsposlist.size() );
			varcount++;
		}
		
		vartable.setVarnames(varnames);
		vartable.setVarids(varids);
		vartable.setAllelestring(allelestring);
		vartable.setVarmismatch(varmismatch);
		
		return vartable;
	
}


private String[] getVarietyIndelString(SnpsStringAllvars snpstr, int cols) {
	Map<Integer,BigDecimal> mapMergedIdx2Pos = genotype.getMapMergedIdx2Pos();
	Map<Integer,BigDecimal> mapIdx2Pos  = genotype.getMapIndelIdx2Pos();
	Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels = genotype.getMapIndelId2Indel();
	Map<Integer, Integer> mapMergedIdx2SnpIdx = genotype.getMapMergedIdx2SnpIdx();

	String allelesstr[] = new String[cols];  
	for(int iCols=0; iCols<cols; iCols++) {
		//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
		StringBuffer buff = new StringBuffer();
		
			if(snpstr instanceof IndelsStringAllvars) {
				
				int j=iCols;
				
				BigDecimal pos = null;
				if(mapMergedIdx2Pos!=null) 
					pos = mapMergedIdx2Pos.get(j);
				else 
					pos = mapIdx2Pos.get(j);
				
				if(pos!=null) {
				
					IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
					
					
					
					BigDecimal alleleid = indelstring.getAllele1( pos );
					IndelsAllvarsPos indelpos = mapIndelId2Indels.get(alleleid);
					
					//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
					if(alleleid!=null && indelpos!=null) {
						
						buff.append( genotype.getIndelAlleleString(indelpos) );
						
						BigDecimal alleleid2 = indelstring.getAllele2( pos );
						indelpos = mapIndelId2Indels.get(alleleid2);
						
						if(alleleid2!=null && !alleleid2.equals(alleleid)) {
							alleleid=alleleid2;
							indelpos = mapIndelId2Indels.get(alleleid);
							if(indelpos!=null) {
								buff.append("/").append( genotype.getIndelAlleleString(indelpos) );
							}								
						}
					}
					
					if(indelstring.getVarnuc()!=null) {
						
						// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
						if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
							j = mapMergedIdx2SnpIdx.get(iCols);

							//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
							char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
							if(element!='0' && element!='.' && element!=' ' && element!='*') {
								buff.append(element);
							
								Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
								if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
									element = mapPosidx2allele2.get(j);
									if(element!='0' && element!='.' && element!=' ' && element!='*') 
										buff.append("/").append(element);
								}
							}
						}
					}
				}
				
			} else {
				int j=iCols;
				// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
				if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
					j = mapMergedIdx2SnpIdx.get(iCols);
				}
				
				if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
					//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
					char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
					if(element!='0' && element!='.' && element!=' ' && element!='*') {
						buff.append(element);
						Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
						if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
							element = mapPosidx2allele2.get(j);
							if(element!='0' && element!='.' && element!=' ' && element!='*') 
								buff.append("/").append(  element );
						}
					}
				}
				
			}
			allelesstr[iCols] = buff.toString();
		}
	return allelesstr;
}

//public static JSONObject quickParse(Object obj) throws IllegalArgumentException, IllegalAccessException, JSONException{
//    JSONObject object = new JSONObject();
//
//    Class<?> objClass = obj.getClass();
//    Field[] fields = objClass.getDeclaredFields();
//    for(Field field : fields) {
//        field.setAccessible(true);
//        Annotation[] annotations = field.getDeclaredAnnotations();
//        for(Annotation annotation : annotations){
//            if(annotation instanceof SerializedName){
//               SerializedName myAnnotation = (SerializedName) annotation;
//               String name = myAnnotation.value();
//               Object value = field.get(obj);
//
//               if(value == null)
//                  value = new String("");
//
//               object.put(name, value);
//            }
//        }
//    }   
//
//    return object;
//}


}