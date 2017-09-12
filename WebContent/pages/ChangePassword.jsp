<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<html:base/>
</head>

<body>	
<center>
<table>	
	<tr height="20"><td><img src="images/disp7.gif"></td><tr>
</table>

<br><br>
<html:form action="/ConfirmChangePassword">
<table bgcolor="#F0F0F0" bordercolor="orange" border='1' rules='none' cellpadding='5' cellspacing='0' height="150" width='350' style='border-collapse: collapse'>
	<tr>
		<td align="center" colspan="2"><img src="images/change.gif"></td>
	</tr>
	<tr>
		<td align="right">	<font size="2" color="gray" face="tahoma"><b>Old Password :</td>
		<td align="center"><html:password property="old"/></td>
	</tr>	
	<tr>
		<td align="right">	<font size="2" color="gray" face="tahoma"><b>New Password :</td>
		<td align="center"><html:password property="new1"/></td>
	</tr>	
	<tr>
		<td align="right">	<font size="2" color="gray" face="tahoma"><b>Confirm New Password :</td>
		<td align="center"><html:password property="new2"/></td>
	</tr>	
	<tr>
		<td colspan="2" align="center">	<html:submit value="Change Password"/>	</td>
	</tr>	
</table>
</html:form>


</center>
</body>
</html:html>
