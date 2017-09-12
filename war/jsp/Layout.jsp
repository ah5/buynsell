<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html>
	<head>
			<tiles:insert attribute="title"/>			
	</head>

<body bgcolor="white">
<center>

	<table width="780">
		<tr>
			<td align="left"><tiles:insert attribute="header"/></td>
		</tr>
	</table>
	
	<table width="780">
		<tr>
			<logic:present name="user">
				<td width="175" bgcolor="#F0F0F0" align="left" valign="top"><tiles:insert attribute="linker"/></td>	 
			</logic:present>
			
			<logic:notPresent name="user">
				
				<logic:present name="admin">
					<td width="175" bgcolor="#F0F0F0" align="left" valign="top"><tiles:insert attribute="linker"/></td>	 
				</logic:present>
				
				<logic:notPresent name="admin">
					<td width="175" bgcolor="#336699" align="left" valign="top"><tiles:insert attribute="linker"/></td>	 
				</logic:notPresent>
			
			</logic:notPresent>
			
			<td align="left" valign="top"><tiles:insert attribute="content"/></td>
		</tr>
	</table>
	
	<table width="780">
		<tr>
			<td align="left"><tiles:insert attribute="footer"/></td>
		</tr>
	</table>
</center>
	
</body>
</html>