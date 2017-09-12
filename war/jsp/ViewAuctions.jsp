<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-form" prefix="form" %>

<html:html locale="true">
<head>
	<html:base/>
	<font color="gray" face="tahoma">
</head>

<body>

<center>
<table>	
		<tr height="20"><td><img src="images/disp0.gif"></td><tr>
</table>
<br>
<%@ page import="java.util.*,buyNsell.BusinessObjects.*,buyNsell.DatabaseConnection.*"%>

<%
	Users u		=	(Users)request.getSession().getAttribute("user");
	ArrayList allCatalogs	=	JdbcData.loadCatalog(u.getUserid(), true);
	Catalog catalog=null;
	if(allCatalogs.size() > 0)
	{
%>
<html:form action="/SelectAuctionForStatus">

<%		for(int i=0; i<allCatalogs.size(); i++)
			{
				catalog	=	(Catalog) allCatalogs.get(i);
				Auction auction = JdbcData.loadAuction(catalog.getCatalogid());%>
				<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>
				<tr bgcolor='#B00000'><td><font color='white' face='tahoma' size='2'><b>Auction Id :</td><td><font color='white' face='tahoma' size='2'><b><%=auction.getAuctionid()%></td><td></td><td></td></tr>
				<tr><td><font face='tahoma' size='2'><b>Started On :</td><td><font face='tahoma' size='2'><%=auction.getStartingdate()%></td>
				<td><font face='tahoma' size='2'><b>Ends On :</td><td><font face='tahoma' size='2'><%=auction.getEndingdate()%></td></tr>
				<tr><td><font face='tahoma' size='2'><b>Catalog Id :</td><td><font face='tahoma' size='2'><%=catalog.getCatalogid()%></td></tr>
				<tr><td><font face='tahoma' size='2'><b>Starting Price :</td><td><font face='tahoma' size='2'><%=catalog.getStartingprice()%></td>
				<td><font face='tahoma' size='2'><b>Bid Increment :</td><td><font face='tahoma' size='2'><%=catalog.getBidincrement()%></td></tr>
<%
				ArrayList allProducts	=	JdbcData.loadProduct(catalog.getCatalogid());
				Product product=null;
				if(allProducts.size() > 0)
				{
					String col="#E8E8E8";
					for(int j=0; j<allProducts.size(); j++)
					{
						product =	(Product) allProducts.get(j);
						if(col.equals("#E8E8E8"))	col="#F0F0F0";
						else						col="#E8E8E8";
%>
						<tr bgcolor="<%=col%>"><td colspan='4'><hr></td></tr>
						<tr bgcolor="<%=col%>" width='500'>
						<td><font face='tahoma' size='2'>Product Id :</td><td><font face='tahoma' size='2'><%=product.getProductid()%></td><td></td><td></td></tr>
						<tr bgcolor="<%=col%>" width='500'>
						<td><font face='tahoma' size='2'>Product Name :</td><td><font face='tahoma' size='2'><%=product.getName()%></td>
						<td><font face='tahoma' size='2'>Description :</td><td><font face='tahoma' size='2'><%=product.getDesc()%></td>
						<tr bgcolor="<%=col%>" width='500'>
						<td><font face='tahoma' size='2'>Category :</td><td><font face='tahoma' size='2'><%=product.getCat()%></td>
						<td><font face='tahoma' size='2'>Quantity :</td><td><font face='tahoma' size='2'><%=product.getQty()%></td></tr>
<%
					}
				}
%>
					<tr>
						<td align="right" colspan="4">
							<font face='tahoma' size='2'>
							<html:radio property="auctionSelected" value="<%=catalog.getCatalogid()%>">
								<b>Select this Auction
							</html:radio>
						</td>
					</tr>	
					</table><br>
<%
			}
%>
		<html:submit value="View Auction Status"/>
</html:form>	
<%		}
		else
		{
%>
			<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='3' width='550' style='border-collapse: collapse'>
				<tr bgcolor='#B00000'>
					<td><font color='white' face='tahoma' size='2'>
						<b>You have not hosted any Auctions Yet !
					</td>
				</tr>
			</table>
<%
		}
%>		



</center>
</body>
</html:html>