<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-form" prefix="form" %>

<%@ page import="buyNsell.BusinessObjects.*" %> 

<html:html locale="true">
<head>
	<html:base/>
</head>

<body>
<center>


<table width="550">
<%	
	ResponseMessage res	=	(ResponseMessage)session.getAttribute("response");
	String img;
	if((res.getType()).equals("Success"))
		img="images/res1.gif";
	else
		img="images/res0.gif";
%>
	<tr><td><img src="<%=img%>">	</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b><%=res.getHeading()%>	</td></tr>
	<tr><td align="center"><font face="tahoma" size="2"><%=res.getContent()%>	</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b><%=res.getFooting()%>	</td></tr>
<%	
	session.removeAttribute("response");
%>
</table>

</center>
</body>
</html:html>