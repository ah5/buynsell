<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<font size="2" color="gray" face="tahoma">
	<html:base/>
</head>

	<body>	
<%@ page import="buyNsell.BusinessObjects.*,buyNsell.DatabaseConnection.*,java.util.*" %> 	
		<center>	

		<table>	
			<tr height="20"><td><img src="images/h6.gif"></td><tr>
		</table>
<logic:present name="message">		
	<br>
	<%String temp=(String)session.getAttribute("message");		
	if(temp.equals("reset success"))	{%><li><b><font size="2" color="gray" face="tahoma">
		You have succesfully reset User Password !
	<%}else if(temp.equals("reset failure")){%><li><b><font size="2" color="gray" face="tahoma">
		You have failed to reset the User password, you might not have selected the user !
	<%}
	session.removeAttribute("message");		%>
</logic:present>		
		<br>
		<table width="550" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
			<tr>
				<td width="35%" align="top" valign="left">
<html:form action="/AdminRequestsUser">
					<table>
						<tr>
							<td><img src="images/01.gif"></td>
							<td align="left"><font size="2" color="gray" face="tahoma"><b>MANAGE USERS</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<html:select property="userSelected">
<%						
	ArrayList users	=	JdbcData.loadOtherUsers("");
	for(int i=0; i<users.size(); i++)
	{
		Users u=(Users)users.get(i);
%>
									<html:option value="<%=u.getUserid()%>"><%=u.getUserid()%></html:option>
<%
	}
%>								
								</html:select>&nbsp<html:submit value="View User Details"/>
							</td>
						</tr>
						
						<tr>
							<td align="right" colspan="2"></td>
						</tr>
					</table>
</html:form>
					
					<hr size="1">
					
					<table>
						<tr>
							<td align="left" valign="center" colspan="3">
								::&nbsp&nbsp
								<html:link forward="adminRequestsResetUser">
									<font size="2" color="gray" face="tahoma"><b>RESET PASSWORD
								</html:link>
							</td>
						</tr>
					</table>
				</td>
				
<logic:notPresent name="selecteduser">
				<td bgcolor="#F0F0F0" align="center" valign="center">
					<font size="3" color="black" face="tahoma"><b>No User Selected !
				</td>
</logic:notPresent>				
<logic:present name="selecteduser">
				<td bgcolor="#F0F0F0">
	<%Users u1=(Users)session.getAttribute("selecteduser");%>	
					<table>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>User id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=u1.getUserid()%></td>
						</tr>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Email id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=u1.getEmailid()%></td>
						</tr>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Company Name</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=u1.getCompanyname()%></td>
						</tr>
						
						<tr><td colspan="4">&nbsp</td></tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Name</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=u1.getFirstname()%>&nbsp<%=u1.getLastname()%></td>
						</tr>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Date of Birth</td>
							<td><font size="2" color="black" face="tahoma"><%=u1.getDateofbirth()%></td>
						</tr>
						
						
						<tr><td colspan="4">&nbsp</td></tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Country</td>
							<td><font size="2" color="black" face="tahoma"><%=u1.getCountry()%></td>
							<td><font size="2" color="black" face="tahoma"><b>City</td>
							<td><font size="2" color="black" face="tahoma"><%=u1.getCity()%></td>
						</tr>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>State</td>
							<td><font size="2" color="black" face="tahoma"><%=u1.getState()%></td>
							<td><font size="2" color="black" face="tahoma"><b>Pin</td>
							<td><font size="2" color="black" face="tahoma"><%=u1.getPin()%></td>
						</tr>
						
						<tr><td colspan="4">&nbsp</td></tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Phone</td>
							<td><font size="2" color="black" face="tahoma"><%=u1.getPhone()%></td>
							<td><font size="2" color="black" face="tahoma"><b>Fax</td>
							<td><font size="2" color="black" face="tahoma"><%=u1.getFax()%></td>
						</tr>
					</table>
				</td>
<%
	session.removeAttribute("selecteduser");
%>
</logic:present>


			</tr>
		</table>
		
		<br>
		<html:link forward="backToAdminHome">
			<img src="images/00.gif">	
		</html:link>
		<br><font size="2" color="gray" face="tahoma"><b>B A C K
	</body>
</html:html>
