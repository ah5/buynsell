<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<html:html locale="true">
<head>
	<html:base/>
	<font color="gray" face="tahoma">
	<SCRIPT LANGUAGE="JavaScript" SRC="scripts/CalendarPopup.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript">
	var cal = new CalendarPopup();
	</SCRIPT>
</head>

<body>

<center>
<html:form action="/UserRegistration" target="_top">

<table>	
		<tr height="20"><td><img src="images/h4.gif"></td><tr>
		<tr height="20"><td><img src="images/p_info.gif"></td><tr>
</table>


<table border="1" bordercolor="#336699" rules="none" cellpadding="3" cellspacing="2" width="550" style="border-collapse: collapse">
<tr>
	<td><font size="2"><b>First Name *</td>
	<td><html:text property="firstname"/></td>

	<td><font size="2"><b>Last Name</td>
	<td><html:text property="lastname"/></td>
</tr>
<tr>
	<td><font size="2"><b>Company Name</td>
	<td><html:text property="companyname"/></td>

	<td><font size="2"><b>E-mail ID *</td>
	<td><html:text property="emailid"/></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td><font size="2"><b>Date Of Birth *</td>
	<td><html:text property="dateofbirth" size="17" readonly="true"/>
		<img src="images\calendar.jpg"
				onclick="cal.select(document.forms['userRegistrationForm'].dateofbirth,'dateofbirth','yyyy-MM-dd')">
	</td>
</tr>
</table>

<table width="550">	
		<tr height="20"><td><img src="images/o_info.gif" align="left"></td><tr>
</table>

<table border="1" bordercolor="#336699" rules="none" cellpadding="3" cellspacing="2" width="550" style="border-collapse: collapse">
<tr>
	<td><font size="2"><b>Country *</td>
	<td><html:text property="country"/></td>
	<td><font size="2"><b>Address *</td>
	<td><html:textarea property="address" cols="15"/></td>
</tr>
<tr>
	<td><font size="2"><b>City *</td>
	<td><html:text property="city"/></td>
	<td><font size="2"><b>Shipping Address *</td>
	<td><html:textarea property="shippingaddress" cols="15"/></td>
</tr>
<tr>
	<td><font size="2"><b>State *</td>
	<td><html:text property="state"/></td>
	<td><font size="2"><b>Phone *</td>
	<td><html:text property="phone"/></td>
</tr>
<tr>
	<td><font size="2"><b>Pin *</td>
	<td><html:text property="pin"/></td>
	<td><font size="2"><b>Fax</td>
	<td><html:text property="fax"/></td>
</tr>
</table>

<table width="550">	
		<tr height="20"><td><img src="images/a_info.gif" align="left"></td><tr>
</table>

<table>

<table border="1" bordercolor="#336699" rules="none" cellpadding="3" cellspacing="2" width="550" style="border-collapse: collapse">
<tr>
	<td><font size="2"><b>User ID *</td>
	<td><html:text property="userid"/></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td><font size="2"><b>Enter Password *</td>
	<td><html:password property="p1"/></td>
	<td><font size="2"><b>Hint Question ?</td>
	<td><html:text property="hintques"/></td>
</tr>
<tr>
	<td><font size="2"><b>Confirm Password *</td>
	<td><html:password property="p2"/></td>
	<td><font size="2"><b>Hint Answer !</td>
	<td><html:text property="hintans"/></td>

</tr>
</table>
<br>
<html:reset/>
<html:submit/>


</html:form>

</body>
</html:html>