<%@ page import="org.irri.iric.portal.variety.service.VarietyFacade" %>
<%@ page import="org.irri.iric.portal.genotype.service.GenotypeFacade" %>
<%@ page import="org.irri.iric.portal.genotype.service.PhylotreeQueryParams" %>
<%@ page import="org.irri.iric.portal.genotype.service.GenotypeQueryParams" %>

<%@ page import="org.irri.iric.portal.AppContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Iterator" %>
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
	
		String newick = "";

		int nvars = 3000; 
		long pairs = 0;
		int topn=-1;
		
		Object[] newicknodes = null;

		if(request.getParameter("varid")!=null) {
			
		
				VarietyFacade variety = (VarietyFacade)request.getSession().getAttribute("VarietyFacade");
				variety =  (VarietyFacade)AppContext.checkBean(variety, "VarietyFacade");
		
				
				if(variety==null) throw new RuntimeException("variety==null");
				
				//if(request.getParameter("nsftvid")==null) throw new RuntimeException("request.getParameter(nsftvid)==null");
				//System.out.println(request.getParameter("nsftvid"));
				
				String varlist = (String)request.getParameter("varid");
				
				if(!varlist.equals("all"))
					nvars = varlist.split(",").length;
				
				//String newick =   variety.constructPhylotree( varlist.replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") );
				newick =   variety.constructPhylotree( varlist, request.getParameter("scale") , request.getParameter("topn") ,request.getParameter("requestid") );
				
		} else if(request.getParameter("chr")!=null && request.getParameter("start")!=null && request.getParameter("end")!=null) {
				
			 	
					 
				GenotypeFacade genotype = (GenotypeFacade)request.getSession().getAttribute("GenotypeFacade");
				genotype =  (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
					

				
				if(request.getParameter("topn")!=null) {
					topn = Integer.parseInt(request.getParameter("topn"));
				}
				
				
				System.out.println("jsp: constructing tree");
				//newicknodes = genotype.constructPhylotreeMindist(request.getParameter("scale") , request.getParameter("chr") ,Integer.parseInt(request.getParameter("start")) ,
				//										Integer.parseInt(request.getParameter("end")), (String)request.getParameter("mindist"));
				
				//newicknodes = genotype.constructPhylotreeTopN(request.getParameter("scale") , request.getParameter("chr") ,Integer.parseInt(request.getParameter("start")) ,
				//										Integer.parseInt(request.getParameter("end")), topn, "");
				
				GenotypeQueryParams genotypeparams = new  GenotypeQueryParams(null, request.getParameter("chr"), Long.valueOf( request.getParameter("start") ),
						Long.valueOf( request.getParameter("end") ), true, false, true, true, null, null, null, false, false);
				PhylotreeQueryParams phyloparams = new PhylotreeQueryParams(genotypeparams, "topn", topn, -1, Double.valueOf( request.getParameter("scale") ));
				newicknodes = genotype.constructPhylotree(phyloparams, "");

				newick = (String)newicknodes[0];
				
				if(!newick.isEmpty()) {
				
					nvars = Integer.valueOf( (String)newicknodes[1] );
					pairs = Long.valueOf( (String)newicknodes[2]  );
					
					if(request.getParameter("tmpfile")!=null) {
						java.io.File file = new java.io.File(AppContext.getTempDir() + request.getParameter("tmpfile") + ".newick");	
						java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
						java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
						bw.write(newick);
						bw.flush();
						fw.close();
						System.out.println("jsp: newick file available");
					}
				}
				
			
				
		}
					
		//System.out.println(newick);		
		//newick=	"'" + newick.replace("'", "").replace("\"", "").replace("-","").replace("_","-") + "'" ; 
		//newick=	"'" + newick.replace("'", "").replace("\"", "").replace("-","").replace("_","-") + "'" ; 
		newick= "'" + newick.replace("\"", "").replace(":-",":")  + "'";
		//System.out.println(newick);
		
		int height=20*nvars;
		//int width=20*nvars;
		//int width=1450;
		int width=1600;
		
		//newick=newick.replace("-","");
		

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
	if(newicknodes!=null && newicknodes.length>3) {
		
		VarietyFacade varietyfacade = (VarietyFacade)request.getSession().getAttribute("VarietyFacade");
		varietyfacade =  (VarietyFacade)AppContext.checkBean(varietyfacade, "VarietyFacade");
		Map<BigDecimal,Variety> mapVarid2Var = varietyfacade.getMapId2Variety();

		
		out.println("<br/><br/><br/>Node Groups Members<br/></br>");
		Map<BigDecimal,Set<BigDecimal>> mapGroup2Varset = (Map)newicknodes[3];
		Map<BigDecimal,String> mapGroup2Name = (Map)newicknodes[4];
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


