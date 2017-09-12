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
		</table><BR>
		<table width="550" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
		<tr>
			<td><img src="images/03.gif"></td>
			<td colspan="2" align="left"><font size="2" color="gray" face="tahoma"><b>VIEW AUCTION TIMERS</td>
		</tr>
		
		</tr>
			<tr bgcolor="#DBEAF5">
				<th><font size="2" color="gray" face="tahoma">Auction ID</th>
				<th><font size="2" color="gray" face="tahoma">Starting Times</th>
				<th><font size="2" color="gray" face="tahoma">Ending Times</th>
			</tr>
<%
	ArrayList auctions	=	JdbcData.loadAllAuctionsToBeActive();
	if(auctions.size()>0){
		String col="#F0F0F0";
	for(int i=0; i<auctions.size(); i++)
	{
		Auction a1	=	(Auction)auctions.get(i);
		if(col=="#F0F0F0")	col="white";
		else				col="#F0F0F0";
%>			
			<tr bgcolor="<%=col%>">
				<td colspan="3" align="left"><font size="2" color="black" face="tahoma"><%=a1.getAuctionid()%></td>
				<td colspan="3" align="left"><font size="2" color="black" face="tahoma"><%=a1.getStartingdate()%></td>
				<td colspan="3" align="left"><font size="2" color="black" face="tahoma"><%=a1.getEndingdate()%></td>
			</tr>
<%
	}//for closed
	}//if closed
	else{
%>		
			<tr><td align="center" colspan="100%" bgcolor="white">
				<font size="2" color="black" face="tahoma"><b>
				NO AUCTION TIMERS RUNNING
			</td></tr>
<%		}
%>
		</table>
			
		<br>
		<html:link forward="backToAdminHome">
			<img src="images/00.gif">	
		</html:link>
		<br><font size="2" color="gray" face="tahoma"><b>B A C K	
	
	</body>
</html:html>
