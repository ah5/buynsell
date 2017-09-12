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
		<br>
		<table width="550" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
			<tr>
				<td width="35%" align="top" valign="left">
<table>
	<tr>
		<td><img src="images/12.gif"></td>
		<td align="center"><font size="2" color="gray" face="tahoma"><b>MANAGE AUCTIONS</td>
	</tr>
</table>

<table>				
	<tr>
		<td align="left" colspan="3"><hr size="1">
			<font size="2" color="gray" face="tahoma"><b>
			<br>
			::&nbsp&nbspAUCTION WISE LISTING <br><br>
		</td>
	</TR>
	<tr>
		<td colspan="2">
<html:form action="/AdminRequestsAuction">
	<html:select property="auctionSelected">
<%	
	ArrayList auctions	=	JdbcData.loadAuctions();
	for(int i=0; i<auctions.size(); i++)
	{
		Auction a=(Auction)auctions.get(i);
%>
		<html:option value="<%=a.getAuctionid()%>"><%=a.getAuctionid()%></html:option>
<%
	}
%>								
	</html:select>&nbsp<html:submit value="View Auction Details"/>
</html:form>
		</td>
	</tr>
</table>

<table>				
	<tr>
		<td align="left" colspan="3"><hr size="1">
			<font size="2" color="gray" face="tahoma"><b>
			<br>
			::&nbsp&nbspUSER WISE AUCTION LISTING <br><br>
		</td>
	</TR>
	
	<tr>
		<td colspan="2">
<html:form action="/AdminRequestsUserAuctions">					
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
	</html:select>&nbsp<html:submit value="View User Auctions"/>
</html:form>				
		</td>
	</tr>
</table>
				</td>
<logic:notPresent name="selectedauction">
	<logic:notPresent name="selecteduser">
				<td bgcolor="#F0F0F0" align="center" valign="center">
					<font size="3" color="black" face="tahoma"><b>No Auction or User Selected !
				</td>
	</logic:notPresent>	
				
	<logic:present name="selecteduser">
				<td><font size="2" color="gray" face="tahoma"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspUSER WISE AUCTION LISTING<br>
<%Users u1=(Users)session.getAttribute("selecteduser");
ArrayList catalogs	=	JdbcData.loadCatalog(u1.getUserid(), true);
for(int i=0; i<catalogs.size(); i++)
{	Catalog c	=	(Catalog)catalogs.get(i);
	Auction a1	=	JdbcData.loadAuction(c.getCatalogid());
	ArrayList products	=	JdbcData.loadProduct(a1.getCatalogid());
%>	
					<table bgcolor="#DBEAF5" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Auction id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=a1.getAuctionid()%></td>
						</tr>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>User id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=c.getUserid()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Starting Times</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=a1.getStartingdate()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Ending Times</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=a1.getEndingdate()%></td>
						</tr>
						
						<tr><td colspan="4">&nbsp</td></tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Catalog id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=c.getCatalogid()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Starting Price</td>
							<td colspan="3"><font size="2" color="black" face="tahoma">Rs.<%=c.getStartingprice()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Bid Increment</td>
							<td colspan="3"><font size="2" color="black" face="tahoma">Rs.<%=c.getBidincrement()%></td>
						</tr>
						
						<tr><td colspan="4">&nbsp</td></tr>
						
						<tr><td colspan="4">
							<table width="250" bgcolor="#F0F0F0" bordercolor="gray" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
<%
	for(int j=0; j<products.size(); j++)
	{
		Product p=(Product)products.get(j);
%>						
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Product ID</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getProductid()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Name</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getName()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Description</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getDesc()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Category</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getCat()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Quantity</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getQty()%></td>
								</tr>
<%
	if( j != (products.size())-1)	{
%>								
								<tr><td colspan="100%"><hr color="gray" size="1">	</td></tr>
						
<%									}
	}
%>							</table>
						</td>		
						</tr>
					</table><br>
<%
}%>					
				</td>
<%
	session.removeAttribute("selecteduser");
%>
	</logic:present>			
</logic:notPresent>					
<logic:present name="selectedauction">
				<td bgcolor="#DBEAF5"><font size="2" color="gray" face="tahoma"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAUCTION WISE LISTING<hr size="1">
	<%Auction a1=(Auction)session.getAttribute("selectedauction");
		Catalog c	=	JdbcData.loadCatalog(a1.getCatalogid());
		ArrayList products	=	JdbcData.loadProduct(a1.getCatalogid());%>	
					<table>
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Auction id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=a1.getAuctionid()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>User id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=c.getUserid()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Starting Times</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=a1.getStartingdate()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Ending Times</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=a1.getEndingdate()%></td>
						</tr>
						
						<tr><td colspan="4">&nbsp</td></tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Catalog id</td>
							<td colspan="3"><font size="2" color="black" face="tahoma"><%=c.getCatalogid()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Starting Price</td>
							<td colspan="3"><font size="2" color="black" face="tahoma">Rs.<%=c.getStartingprice()%></td>
						</tr>
						
						<tr>
							<td><font size="2" color="black" face="tahoma"><b>Bid Increment</td>
							<td colspan="3"><font size="2" color="black" face="tahoma">Rs.<%=c.getBidincrement()%></td>
						</tr>
						
						<tr><td colspan="4">&nbsp</td></tr>
						<tr><td colspan="4">
							<table width="260" bgcolor="#F0F0F0" bordercolor="gray" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
<%
	for(int i=0; i<products.size(); i++)
	{
		Product p=(Product)products.get(i);
%>						
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Product ID</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getProductid()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Name</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getName()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Description</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getDesc()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Category</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getCat()%></td>
								</tr>	
								<tr>	
									<td><font size="2" color="black" face="tahoma"><b>Quantity</td>	
									<td><font size="2" color="black" face="tahoma"><%=p.getQty()%></td>
								</tr>
<%
	if( i != (products.size())-1){
%>								
								<tr><td colspan="100%"><hr color="gray" size="1">	</td></tr>
						
<%	}
	}
%></table></td>		
						</tr>
						
						
					</table>
				</td>
<%
	session.removeAttribute("selectedauction");
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
