<%@ page import="org.irri.iric.portal.variety.VarietyFacade" %>
<%@ page import="org.irri.iric.portal.genotype.GenotypeFacade" %>
<%@ page import="org.irri.iric.portal.genotype.PhylotreeQueryParams" %>
<%@ page import="org.irri.iric.portal.genotype.GenotypeQueryParams" %>
<%@ page import="org.irri.iric.portal.genotype.VariantStringData" %>
<%@ page import="org.irri.iric.portal.genotype.VariantTable" %>

<%@ page import="org.irri.iric.portal.AppContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.lang.Long" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Enumeration" %>

<%@ page import="org.irri.iric.portal.domain.Variety" %>




<html>
<head>
	
	<link rel="stylesheet" type="text/css" href="css/doublescroll.css">

    <script type="text/javascript" src="js/raphael-min.js" ></script>
    <script type="text/javascript" src="js/jsphylosvg_variety_3k.js"></script>
    <!--  script type="text/javascript" src="js/jsphylosvg-min.js"></script -->
    <script type="text/javascript" src="js/spin.js"></script>
    
	


    <script type="text/javascript">
            
<%
	
	
		AppContext.debug("in jsp/phylotreeGermplasms.jsp  " + request.getRequestURL() +  " map=" + request.getParameterMap());
	
		String newick = "";

		int nvars = 3023; 
		long pairs = 0;
		int topn=-1;
		
		Object[] newicknodes = null;

				
		request.getSession().removeAttribute("phyloorder");
		request.getSession().removeAttribute("newick");
					
		if(request.getParameter("varid")!=null) {
			
		
				VarietyFacade variety = (VarietyFacade)request.getSession().getAttribute("VarietyFacade");
				variety =  (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		
				
				if(variety==null) throw new RuntimeException("variety==null");
				
				//if(request.getParameter("nsftvid")==null) throw new RuntimeException("request.getParameter(nsftvid)==null");
				//AppContext.debug(request.getParameter("nsftvid"));
				
				String varlist = (String)request.getParameter("varid");

				if(varlist.equals("session")) {
					varlist =  (String)request.getSession().getAttribute("varlist");
					request.getSession().removeAttribute("varlist");
				}	
				
				if(!varlist.equals("all"))
					nvars = varlist.split(",").length;
					
				
				//String newick =   variety.constructPhylotree( varlist.replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") );
				String newickxml[] =   variety.constructPhylotree( varlist, request.getParameter("scale") , request.getParameter("topn") ,request.getParameter("requestid"), VarietyFacade.DATASET_SNPINDELV2_IUPAC );
				newick=newickxml[0];
				
		} 
		else if(request.getParameter("dataset")!=null || request.getParameter("phyloid")!=null) {
		
			VariantStringData dataset = null; PhylotreeQueryParams params=null; GenotypeFacade genotype=null;
		
			if(request.getParameter("dataset")!=null) {
		
				//dataset =  (VariantStringData)request.getSession().getAttribute("variantdata");
				dataset =  ((VariantTable)request.getSession().getAttribute("variantTable")).getVariantStringData();
				params= (PhylotreeQueryParams)request.getSession().getAttribute("queryparams");
			
				//request.getSession().removeAttribute("variantdata");
				//request.getSession().removeAttribute("queryparams");
		
				//genotype = (GenotypeFacade)request.getSession().getAttribute("GenotypeFacade");
				genotype = (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
				
			}
			
			if(request.getParameter("phyloid")!=null) {
			
					//VariantTable varianttable = (VariantTable)request.getSession().getAttribute("variantTable");
		
		 			Long phyloid= Long.valueOf(request.getParameter("phyloid"));
					dataset =  (VariantStringData)AppContext.popSessionAttr(phyloid, "variantdata");
					params= (PhylotreeQueryParams)AppContext.popSessionAttr(phyloid, "queryparams");
					genotype = (GenotypeFacade)AppContext.popSessionAttr(phyloid, "GenotypeFacade");
			}
			
			if(dataset==null) AppContext.debug("dataset==null");
			else if(dataset.getListVariantsString()==null)  AppContext.debug( "getListVariantsString==null");
			else  AppContext.debug( "getListVariantsString.size()=" + dataset.getListVariantsString().size());
			if(genotype==null)  AppContext.debug("genotype==null");
			
			
				newicknodes = genotype.constructPhylotree(dataset, params);
				newick = (String)newicknodes[0];
				
				if(!newick.isEmpty()) {
				
					//nvars = Integer.valueOf( (String)newicknodes[1] );
					//pairs = Long.valueOf( (String)newicknodes[2]  );
					
					nvars =Integer.valueOf(newicknodes[1].toString());
					pairs =Integer.valueOf(newicknodes[2].toString());
					
					if(request.getParameter("phyloid")!=null) {
						Long phyloid = Long.valueOf(request.getParameter("phyloid"));
						AppContext.addSessionAttr( phyloid, "phyloorder",newicknodes[3]);
						
					}
					
					if(request.getParameter("dataset")!=null) {
						request.getSession().setAttribute("phyloorder",newicknodes[3]);
						request.getSession().putValue("phyloorder",newicknodes[3]);
					}
					
					AppContext.debug("jsp: newick file available");
				}
				
				
				
		} else if(request.getParameter("chr")!=null && request.getParameter("start")!=null && request.getParameter("end")!=null) {
				
					 
				GenotypeFacade genotype = (GenotypeFacade)request.getSession().getAttribute("GenotypeFacade");
				genotype =  (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
					

				
				if(request.getParameter("topn")!=null) {
					topn = Integer.parseInt(request.getParameter("topn"));
				}

				
				AppContext.debug("jsp: constructing tree");
				//newicknodes = genotype.constructPhylotreeMindist(request.getParameter("scale") , request.getParameter("chr") ,Integer.parseInt(request.getParameter("start")) ,
				//										Integer.parseInt(request.getParameter("end")), (String)request.getParameter("mindist"));
				
				//newicknodes = genotype.constructPhylotreeTopN(request.getParameter("scale") , request.getParameter("chr") ,Integer.parseInt(request.getParameter("start")) ,
				//										Integer.parseInt(request.getParameter("end")), topn, "");
				
				GenotypeQueryParams genotypeparams = new  GenotypeQueryParams(null, request.getParameter("chr"), Long.valueOf( request.getParameter("start") ),
						Long.valueOf( request.getParameter("end") ), true, false, GenotypeQueryParams.SNP_FILTERED, true, null, null, null, false, false);
						genotypeparams.setDataset( VarietyFacade.DATASET_SNPINDELV2_IUPAC);
				PhylotreeQueryParams phyloparams = new PhylotreeQueryParams(genotypeparams, "topn", topn, -1, Double.valueOf( request.getParameter("scale") ));
				newicknodes = genotype.constructPhylotree(phyloparams, "");

				newick = (String)newicknodes[0];
				
				if(!newick.isEmpty()) {
				
					//nvars = Integer.valueOf( (String)newicknodes[1] );
					//pairs = Long.valueOf( (String)newicknodes[2]  );
					
					nvars =Integer.valueOf(newicknodes[1].toString());
					pairs =Integer.valueOf(newicknodes[2].toString());
					
					request.getSession().setAttribute("phyloorder",newicknodes[3]);
					 
					
					/*
					if(request.getParameter("tmpfile")!=null) {
						String newickfile=AppContext.getTempDir() + request.getParameter("tmpfile") + ".newick";
						AppContext.debug("writing " + newickfile);
						java.io.File file = new java.io.File(newickfile);	
						java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
						java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
						bw.write(newick);
						bw.flush();
						fw.close();
					}
					*/
					AppContext.debug("jsp: newick file available");
				}
				
			
				
		} 
					
		//AppContext.debug(newick);		
		//newick=	"'" + newick.replace("'", "").replace("\"", "").replace("-","").replace("_","-") + "'" ; 
		//newick=	"'" + newick.replace("'", "").replace("\"", "").replace("-","").replace("_","-") + "'" ; 
		newick= "'" + newick.replace("\"", "").replace(":-",":")  + "'";
		//AppContext.debug(newick);
		
		int height=20*nvars;
		//int width=20*nvars;
		//int width=1450;
		int width=1600;
		
		//newick=newick.replace("-","");
		
		
		
			if(request.getParameter("dataset")!=null) {
					request.getSession().setAttribute("newick",newick);
					request.getSession().putValue("newick",newick);
					
					//Button butnewick= (Button)request.getSession().getAttribute("buttonnewick");
					//butnewick.setVisible(true);
			}
			if(request.getParameter("phyloid")!=null) {
		 			Long phyloid= Long.valueOf(request.getParameter("phyloid"));
		 			AppContext.addSessionAttr(phyloid, "newick",newick);
		 	}
		
		
		StringBuffer buffnames=new StringBuffer();
		for (Enumeration<String> e = request.getSession().getAttributeNames(); e.hasMoreElements();)
			buffnames.append(e.nextElement() + ", ");
		AppContext.debug("session attribute names: " + buffnames);
		
	%>

    window.onload = function(){
    	
    	var opts = {
    			  lines: 13, // The number of lines to draw
    			  length: 20, // The length of each line
    			  width: 10, // The line thickness
    			  radius: 30, // The radius of the inner circle
    			  corners: 1, // Corner roundness (0..1)
    			  rotate: 0, // The rotation offset
    			  direction: 1, // 1: clockwise, -1: counterclockwise
    			  color: '#000', // #rgb or #rrggbb or array of colors
    			  speed: 1, // Rounds per second
    			  trail: 60, // Afterglow percentage
    			  shadow: false, // Whether to render a shadow
    			  hwaccel: false, // Whether to use hardware acceleration
    			  className: 'spinner', // The CSS class to assign to the spinner
    			  zIndex: 2e9, // The z-index (defaults to 2000000000)
    			  top: 100, // Top position relative to parent
    			  left: '50%' // Left position relative to parent
    			};
    			var target = document.getElementById('foo');
    			var spinner = new Spinner(opts).spin(target);
    			//var spinner = new Spinner(opts).spin();
    			
    			
        var dataObject = { newick:  <%= newick %> };

        phylocanvas = new Smits.PhyloCanvas(
            dataObject,
            'svgCanvas',
            <%= width  %> ,  <%= height %>
        );
        
        spinner.stop();
     
	};


	function onClicknode(param) {
		alert(param + ' clicked');
		 (function(window){
	        	window.zAu.send(new window.zk.Event(window.zk.Widget.$('$winVariety'), "onUser","Node " + param + " clicked"));
	     })(parent);
	}
	
	</script>
	

</head>
<body>
<div style="font-family:arial;font-size:12">
<%

if(!newick.isEmpty()) {
		
	if(request.getParameter("topn")!=null) 
		out.println("Tree is constructed using the top " + topn + " out of " +  pairs + " non-zero pair distances, having " + nvars + " nodes.<BR>");
	else
		out.println( "Tree is constructed using all non-zero distance pairs, having " + nvars + " nodes.<BR>");
}
else
{
	out.println("Tree cannot be constructed because there is no SNP in this region.<BR>");	
}
%>
</div>




<div id="foo"/>
<div id="double-scroll">
	<div id="svgCanvas"/>
</div>




<%
	if(newicknodes!=null && newicknodes.length>4) {
		
		VarietyFacade varietyfacade = (VarietyFacade)request.getSession().getAttribute("VarietyFacade");
		varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapVarid2Var = varietyfacade.getMapId2Variety(VarietyFacade.DATASET_SNPINDELV2_IUPAC);

		
		out.println("<br/><br/><br/>Node Groups Members<br/></br>");
		Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset = (Map)newicknodes[4];
		Map<BigDecimal,String> mapGroup2Name = (Map)newicknodes[5];
		Iterator<BigDecimal> itGroup = mapGroup2Varset.keySet().iterator();
		while(itGroup.hasNext()) {
			BigDecimal grp = itGroup.next();
			
			
			out.println("<table><th><td>" + mapGroup2Name.get(grp) + "</td></th>" );
			Iterator itVarset =  mapGroup2Varset.get(grp).iterator();
			while(itVarset.hasNext()) {
				out.println("<tr><td>" +  mapVarid2Var.get( itVarset.next() ).getName() + "</td></tr>" );
			}
			out.println("</table><br/><br/>");
		}
		
	}


%>


</body>
</html>    


