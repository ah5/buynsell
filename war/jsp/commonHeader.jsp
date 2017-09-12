<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>


<html:html>
<head>
	<html:base/>
</head>

<body>

	<TABLE WIDTH=780 BORDER=0 CELLPADDING=0 CELLSPACING=0>

<logic:present name="user">
		<TR>
			<TD WIDTH=780 HEIGHT=26 COLSPAN=2 align="right" bgcolor="#336699"><b>
				<html:link forward="logout"><font size="2" face="tahoma" color="white"><b>Logout</html:link>
				&nbsp&nbsp&nbsp&nbsp&nbsp 
			</TD>
		</TR>
		<tr><td><IMG SRC="images/header.gif" WIDTH=780></td></tr>
		<tr><td background="images/top11.gif" width=780 height="26"><font size="2" face="tahoma" color="white">
			<%@ page import="buyNsell.BusinessObjects.*"%>
			<%Users u=(Users)session.getAttribute("user");%>
			&nbsp<b>Welcome <%=u.getFirstname()%> ,	
		</td></tr>
</logic:present>

<logic:notPresent name="user">
		<logic:present name="admin">
			<TR>
			<TD WIDTH=780 HEIGHT=26 COLSPAN=2 align="right" bgcolor="#336699"><b>
				<html:link forward="home"><font size="2" face="tahoma" color="white"><b>Home</html:link>
					&nbsp&nbsp&nbsp
			</TD>
			</TR>
			<tr><td><IMG SRC="images/header.gif" WIDTH=780></td></tr>
			<tr><td background="images/top11.gif" width=780 height="26"><font size="2" face="tahoma" color="white">
			</td></tr>
		</logic:present>
		
		<logic:notPresent name="admin">
			<TR>
				<TD WIDTH=780 HEIGHT=26 COLSPAN=2 align="right"  bgcolor="#336699"><b>
					<html:link forward="home"><font size="2" face="tahoma" color="white"><b>Home</html:link>
					&nbsp&nbsp&nbsp :: &nbsp&nbsp&nbsp
					<html:link forward="register"><font size="2" face="tahoma" color="white"><b>Register</html:link>
					&nbsp&nbsp&nbsp&nbsp&nbsp 
				</TD>
			</TR>
			<tr><td><IMG SRC="images/header.gif" WIDTH=780></td></tr>
			<tr><td><img src="images/top11.gif" width=780 height="26"></td></tr>
		</logic:notPresent>
</logic:notPresent>
		

	</table>
</body>

</html:html>
