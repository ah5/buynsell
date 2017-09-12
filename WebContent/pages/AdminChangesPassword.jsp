<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<html:base/>
</head>

<body>	
<center>
	<table>	
			<tr height="20"><td><img src="images/h6.gif"></td><tr>
		</table>
<logic:present name="message">		
	<br>
	<%String temp=(String)session.getAttribute("message");		
	if(temp.equals("success"))	{%><li><b><font size="2" color="gray" face="tahoma">
		You have succesfully changed the Admin Password !
	<%}else if(temp.equals("failure")){%><li><b><font size="2" color="gray" face="tahoma">
		You have failed to change the Admin Password !
	<%}
	session.removeAttribute("message");		%>
</logic:present>


	<html:form action="/ConfirmAdminChangePassword">
	<table width="550" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' height="150" width='350' style='border-collapse: collapse'>
	<tr>
		<td align="left"><img src="images/02.gif"></td>
		<td align="left"><font size="2" color="gray" face="tahoma"><b>CHANGE ADMIN PASSWORD</td>
	</tr>
	<tr bgcolor="#F0F0F0" >
		<td align="right">	<font size="2" color="gray" face="tahoma"><b>Old Admin Password :</td>
		<td align="center"><html:password property="old"/></td>
	</tr>	
	<tr bgcolor="#F0F0F0" >
		<td align="right">	<font size="2" color="gray" face="tahoma"><b>New Admin Password :</td>
		<td align="center"><html:password property="new1"/></td>
	</tr>	
	<tr bgcolor="#F0F0F0" >
		<td align="right">	<font size="2" color="gray" face="tahoma"><b>Confirm New Admin Password :</td>
		<td align="center"><html:password property="new2"/></td>
	</tr>	
	<tr bgcolor="#F0F0F0" >
		<td colspan="2" align="center">	<html:submit value="Change Admin Password"/>	</td>
	</tr>	
	</table>
	</html:form>

		<html:link forward="backToAdminHome">
			<img src="images/00.gif">	
		</html:link>
		<br><font size="2" color="gray" face="tahoma"><b>B A C K


</center>
</body>
</html:html>
