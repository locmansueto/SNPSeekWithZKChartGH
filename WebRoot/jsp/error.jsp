<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ce" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!-- BEGIN error.jsp -->
<div class="error">
  <p class="message"><%=exception.getMessage()%></p>

  <p>An error has occured. If you believe this error is a system problem, and you
  continue to get this error, please contact a special person via
    <a href="mailto:'l.mansueto@irri.org' ">email</a>
  </p>

  
    <%
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      exception.printStackTrace(pw);
    %>
    <ce:set var="exceptionStack"><%=sw.toString()%>
    </ce:set>
    <div class="debug">
      Message : <%=exception.getMessage()%>
      Exception : <%=exception.getClass().getName()%>
      Stack : ${fn:escapeXml(exceptionStack)}
    </div>
  
</div>
<!-- END error.jsp -->
