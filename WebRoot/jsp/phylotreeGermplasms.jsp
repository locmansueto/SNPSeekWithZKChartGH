<%@ page import="org.irri.iric.portal.variety.service.VarietyFacade" %>
<%@ page import="org.irri.iric.portal.genotype.service.GenotypeFacade" %>
<%@ page import="org.irri.iric.portal.AppContext" %>
<html>
<head>
	
	<link rel="stylesheet" type="text/css" href="css/doublescroll.css">

    <script type="text/javascript" src="js/raphael-min.js" ></script>
    <script type="text/javascript" src="js/jsphylosvg_variety.js"></script>
    <!--  script type="text/javascript" src="js/jsphylosvg-min.js"></script -->
    
	


    <script type="text/javascript">
            
<%
	
		String newick = "";

		int nvars = 1000; 

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
				newick =   variety.constructPhylotree( varlist, request.getParameter("scale") );
				
		} else if(request.getParameter("chr")!=null && request.getParameter("start")!=null && request.getParameter("end")!=null) {
				
			 	
					 
				GenotypeFacade genotype = (GenotypeFacade)request.getSession().getAttribute("GenotypeFacade");
				genotype =  (GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
					
				int topn=-1;
				if(request.getParameter("topn")!=null)
					topn = Integer.parseInt(request.getParameter("topn"));
					
				newick =   genotype.constructPhylotreeTopN(request.getParameter("scale") , request.getParameter("chr") ,Integer.parseInt(request.getParameter("start")) ,
														Integer.parseInt(request.getParameter("end")), topn );
				
				nvars = 1000;
			
		}
					
		System.out.println(newick);		
		//newick=	"'" + newick.replace("'", "").replace("\"", "").replace("-","").replace("_","-") + "'" ; 
		//newick=	"'" + newick.replace("'", "").replace("\"", "").replace("-","").replace("_","-") + "'" ; 
		newick= "'" + newick.replace("\"", "").replace(":-",":")  + "'";
		System.out.println(newick);
		
		int height=20*nvars;
		//int width=20*nvars;
		int width=1450;
		
		//newick=newick.replace("-","");
		

	%>

    window.onload = function(){
        var dataObject = { newick:  <%= newick %> };

        phylocanvas = new Smits.PhyloCanvas(
            dataObject,
            'svgCanvas',
            <%= width  %> ,  <%= height %>
        );

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



<div id="double-scroll">
	<div id="svgCanvas"/>
</div>


</body>
</html>    


