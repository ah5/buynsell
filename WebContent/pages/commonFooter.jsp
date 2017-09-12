<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<html:base/>
</head>
<body>
	<TABLE WIDTH=780 BORDER=0 CELLPADDING=0 CELLSPACING=0  bgcolor="#336699">
		<logic:notPresent name="admin">
		<TR height="20">
			<TD COLSPAN="100%" align="center" valign="center">
				<font size="2" face="tahoma" color="white"><b>
				
				<html:link forward="about">	
					<font size="2" face="tahoma" color="white"><b>About
				</html:link>		&nbsp&nbsp&nbsp	:: 
				
				&nbsp&nbsp&nbsp	Services	&nbsp&nbsp&nbsp	:: 
				&nbsp&nbsp&nbsp	Help	
				
			</TD>
		</TR>
		</logic:notPresent>
		<tr>
			<td colspan="100%"><hr color="white"></td>
		</tr>
		<TR height="20">
			<TD colspan="100%" align="center" valign="center">
				<b><font size="1" face="tahoma" color="white"> • BUY N SELL.Com © 2005 • </b>	</TD>
		</TR>
	</TABLE>	
	<br>
</body>

</html:html>