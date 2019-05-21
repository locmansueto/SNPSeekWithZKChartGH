<%@ page import="org.irri.iric.portal.genotype.VariantStringData" %>
<%@ page import="org.irri.iric.portal.genotype.VariantTable" %>
<%@ page import="org.irri.iric.portal.domain.Position" %>
<%@ page import="org.irri.iric.portal.genotype.service.VariantTableArraysImpl" %>
<%@ page import="org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl" %>
<%@ page import="org.irri.iric.portal.dao.ListItemsDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.text.DecimalFormat" %> 
<!-- %@ page import="java.math.BigDecimal" % -->

<html lang="en">
<head>
<title>JSAV</title>

<!-- JQuery and JQuery-UI -->
<link href="jsav/external/jquery.css" rel="stylesheet" />
<script type='text/javascript' src='jsav/external/jquery-1.10.2.min.js'></script>
<script type='text/javascript' src='jsav/external/jquery-ui-1.10.4.custom.min.js'></script>

<!-- JSAV -->
<link href="jsav/JSAV.css" rel="stylesheet" />
<script type='text/javascript' src='jsav/JSAV.js'></script>

<!-- If using tooltipster -->
<link href="jsav/external/tooltipster-master/css/tooltipster.css" rel="stylesheet" />
<script type='text/javascript' src='jsav/external/tooltipster-master/js/jquery.tooltipster.min.js'></script>
<script>
function enableTooltipster()
{
    $(document).ready(function() {
        $('.tooltip').tooltipster();
    });
}
enableTooltipster();
</script>
<!-- END -->

</head>

<body>

<h4>JavaScript Sequence Alignment Viewer (JSAV)</h4>
<p>JSAV is a JavaScript program to display and sort a sequence
  alignment. The sorting method chooses a representative sequence (one
  which is most similar to all the other sequences) placing that first
  in the list. To add to the sorted list, it iteratively chooses the
  sequences that are most similar to the last one in the list and, of
  those chooses the sequence most similar to the representative
  sequence. <a href="http://www.bioinf.org.uk/software/jsav">JSAV site</a><p> 
  <p>Consensus sequence, sorting and export functions are at the page bottom. <div style="color:red">NOTE: The sequence positions are not contiguous but only for SNPs, insertion or deleted regions.
  N represents missing nucleotide value, and dash (-) for gaps.</div></p>

<script type='text/javascript'>
var MySeqs = [];

<%

		VariantTable varianttable = (VariantTable)request.getSession().getAttribute("variantTable");
		StringBuffer buffMapPos = new StringBuffer();
		if(varianttable instanceof VariantAlignmentTableArraysImpl) {
			VariantAlignmentTableArraysImpl alignmentTable = (VariantAlignmentTableArraysImpl)varianttable;
			
			Object[][] tableAlleles = alignmentTable.getVaralleles();
			String[] varnames = alignmentTable.getVarname();
			
			for(int i=0; i<tableAlleles.length; i++) {
				StringBuffer buff=new StringBuffer();
				Object varalleles[] = tableAlleles[i];
				String allele = "";
				buff.append( "MySeqs.push({ id :\"" +  varnames[i] + "\",  sequence :\"" );
				for(int j=0; j<tableAlleles[i].length; j++) {
					allele=((String)varalleles[j]).split("/")[0];
					if(allele.isEmpty()) allele="N";
					buff.append( allele );
				}
				buff.append("\"});");
				out.println(buff.toString());
			}
			
			DecimalFormat formatter = new DecimalFormat("#0.00");     
			AppContext.debug(formatter.format(4.0));
			Position[] pos = alignmentTable.getPosition();
			for(int i=0; i<pos.length; i++) {
				String strpos =  formatter.format(pos[i].getPosition().doubleValue()).replace(".00", "");
				buffMapPos.append( "\""+ i + "\" : \"" + strpos + "\"");
				if(i<pos.length-1) buffMapPos.append(",");
			}
			
			
		} else if(!varianttable.getVariantStringData().hasIndel() &&  varianttable instanceof VariantTableArraysImpl) {
			VariantTableArraysImpl alignmentTable = (VariantTableArraysImpl)varianttable;
			
			Object[][] tableAlleles = alignmentTable.getVaralleles();
			String[] varnames = alignmentTable.getVarname();
			
			for(int i=0; i<tableAlleles.length; i++) {
				StringBuffer buff=new StringBuffer();
				Object varalleles[] = tableAlleles[i];
				String allele = "";
				buff.append( "MySeqs.push({ id :\"" +  varnames[i] + "\",  sequence :\"" );
				for(int j=0; j<tableAlleles[i].length; j++) {
					allele=((String)varalleles[j]).split("/")[0];
					if(allele.isEmpty()) allele="N";
					buff.append( allele );
				}
				buff.append("\"});");
				out.println(buff.toString());
			}
			Long[] pos = alignmentTable.getPosition();
			for(int i=0; i<pos.length; i++) {
				buffMapPos.append( "\""+ i + "\" : \"" +  pos[i] + "\"");
				if(i<pos.length-1) buffMapPos.append(",");
			}
			
			
		}
		

%>


</script>

<script type='text/javascript'>
function myAction(divId, sequences)
{
   var seqString = "";
   for(var i=0; i<sequences.length; i++)
   {
       seqString += ">" + sequences[i].id + "\n";
       seqString += sequences[i].sequence + "\n";
   }
   alert(seqString);
}

var MyOptions = Array();
MyOptions.sortable = true;
MyOptions.selectable = true;
MyOptions.deletable = true;
MyOptions.border = false;
MyOptions.highlight = [3,5,10,14];
MyOptions.submit = "http://www.bioinf.org.uk/cgi-bin/echo.pl";
MyOptions.action = "myAction";
MyOptions.actionLabel = "My Action";
MyOptions.toggleDotify = true;
MyOptions.toggleNocolour = true;
MyOptions.fasta = true;
MyOptions.consensus = true;
MyOptions.colourScheme = "zappo";
MyOptions.selectColour = true;
MyOptions.callback = "enableTooltipster";
MyOptions.mapseqid2pos = {
<%
		out.println(buffMapPos.toString());
%>
};

MyprintJSAV('sequenceDisplay', MySeqs, MyOptions);
</script>



</body>
</html>
