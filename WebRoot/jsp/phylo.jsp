<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.lang.Long" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Map" %>



<html>
<head>
	
	<link rel="stylesheet" type="text/css" href="css/doublescroll.css">

    <script type="text/javascript" src="js/raphael-min.js" ></script>
    <script type="text/javascript" src="js/jsphylosvg_variety_3k.js"></script>
    <!--  script type="text/javascript" src="js/jsphylosvg-min.js"></script -->
    <script type="text/javascript" src="js/spin.js"></script>
    <script src="http://yui.yahooapis.com/3.18.1/build/yui/yui-min.js"></script>
    
	


    <script type="text/javascript">

function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
  
  
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
    			
            
<%
		System.out.println("in jsp/phylo.jsp  " + request.getRequestURL() +  " map=" + request.getParameterMap());
		String newick = (String)request.getSession().getAttribute("newick");
		Integer objnvars = (Integer)request.getSession().getAttribute("nvars");
		
		if(newick!=null && objnvars!=null) {
		
		int intvars=objnvars;
		int height=20*intvars;
		int width = 2000;
		System.out.println("fetched newick=" + newick.substring(0,10));
	%>
		var nvars= <%= intvars  %>;
        var dataObject = { newick:  <%= newick %> };
        phylocanvas = new Smits.PhyloCanvas(
            dataObject,
            'svgCanvas',
            <%= width  %> ,  <%= height %>
        );
        
<%

		} else {
		
				Map mapParams=request.getParameterMap();
				System.out.println("in jsp/phylo.jsp  " + request.getRequestURL() +  " map=" + mapParams);
				String newickfile=null;
				String nvars=null;
				if(mapParams.containsKey("newick")) newickfile=  ((String[])mapParams.get("newick"))[0];
				if(mapParams.containsKey("nvars")) nvars=  ((String[])mapParams.get("nvars"))[0];
				
				if(newickfile!=null) {
				
		
%>
    			var newickfile= '<%= newickfile %>';
    			var nvars= <%= nvars %>;
    			var height = nvars*20;
    			
    			console.log("newickfile=" + newickfile + ";nvars=" + nvars);
    			if(newickfile!=null) {
    			
			    	YUI().use('oop', 'json-stringify', 'io-base', 'event', 'event-delegate', function(Y){
						//var uri =  "http://snp-seek.irri.org/temp/" +  newickfile; // //  request.getParameter("newick")[0]; // "/trees/2-coffee.xml";
						var uri =  "/temp/" +  newickfile + ".xml";  //  request.getParameter("newick")[0]; // "/trees/2-coffee.xml";
						console.log("fetching " + uri);
						
						function complete(id, o, args) {
							var data =   o.responseXML; //  o.responseText; // .responseXML; // Response data.
							//var data =   o.responseText;
							//if(data!=null) console.log("fetching result: " + data.toString().substr(0,20 ));
							
							var dataObject = {
										xml: data,
										fileSource: true
									};		
							phylocanvas = new Smits.PhyloCanvas(
								dataObject,
								'svgCanvas', 
								2000, height				
							);
							
							console.log("phylocanvas done..");
						};
						Y.on('io:complete', complete, Y);
						
						//var spinner = new Spinner(opts).spin(target);
    					//var spinner = new Spinner(opts).spin();
						var request = Y.io(uri);
						//spinner.stop();
					});
	    		}
	    		else {
	    			alert("No newick file available");
	    		}

<%
				}

		}

%>        
        
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

</div>



<div id="foo"/>
<div id="double-scroll">
	<div id="svgCanvas"/>
</div>




</body>
</html>    


