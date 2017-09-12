<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<html:base/>
</head>

<body>	
<center>



<html:form action="/AdminLogin">
<table bgcolor="#F0F0F0" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' height="150" width='350' style='border-collapse: collapse'>
	<tr>
		<td align="center" colspan="2"><img src="images/adminlogin.gif"></td>
	</tr>
	<tr>
		<td align="center">	<font size="2" color="gray" face="tahoma"><b>Administrator Identity :</td>
		<td align="center"><html:text property="userid"/></td>
	</tr>	
	<tr>
		<td align="center">	<font size="2" color="gray" face="tahoma"><b>Authorized Password :</td>
		<td align="center"><html:password property="pass"/></td>
	</tr>	
	<tr>
		<td colspan="2" align="right">	<html:submit value="Login"/>	</td>
	</tr>	
</table>
</html:form>


</center>
</body>
</html:html>
