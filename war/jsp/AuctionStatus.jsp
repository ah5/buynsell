<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-form" prefix="form" %>

<html:html locale="true">
<head>
	<html:base/>
	<font color="gray" face="tahoma">
</head>

<body>
<%@ page import="java.util.*,buyNsell.BusinessObjects.*,buyNsell.DatabaseConnection.*"%>
<center>

<table>	
		<tr height="20"><td><img src="images/disp4.gif"></td><tr>
</table>

<%	
	Catalog c		=	(Catalog)session.getAttribute("sellerselectedcatalog");
	String catID	=	c.getCatalogid();
	Users u			=	(Users)session.getAttribute("user");
	Auction auction	=	JdbcData.loadAuction(catID);
	Catalog catalog	=	JdbcData.loadCatalog(catID);
%>
	<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>
		<tr bgcolor='#B00000'><td><font color='white' face='tahoma' size='2'><b>Auction Id :</td><td><font color='white' face='tahoma' size='2'><b><%=auction.getAuctionid()%></td><td></td><td></td></tr>
		<tr><td><font face='tahoma' size='2'><b>Started On :</td><td><font face='tahoma' size='2'><%=auction.getStartingdate()%></td>
		<td><font face='tahoma' size='2'><b>Ends On :</td><td><font face='tahoma' size='2'><%=auction.getEndingdate()%></td></tr>
		<tr><td><font face='tahoma' size='2'><b>Catalog Id :</td><td><font face='tahoma' size='2'><%=catalog.getCatalogid()%></td></tr>
		<tr><td><font face='tahoma' size='2'><b>Starting Price :</td><td><font face='tahoma' size='2'><%=catalog.getStartingprice()%></td>
		<td><font face='tahoma' size='2'><b>Bid Increment :</td><td><font face='tahoma' size='2'><%=catalog.getBidincrement()%></td></tr>
<%
	ArrayList allProducts	=	JdbcData.loadProduct(catID);
	Product product=null;
	int noProducts	=	allProducts.size();
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
</table>
<br>

<table>	
		<tr height="20"><td><img src="images/disp5.gif"></td><tr>
</table>


<%
	ArrayList 	bids	=	JdbcData.loadBids(auction.getAuctionid());
	Bid bid=null;
	BidDetails biddet=null;
	if(bids.size() > 0)
	{
		for(int i=0; i<bids.size(); i++)
		{
			bid=(Bid)bids.get(i);
%>
	<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>
		<tr bgcolor='#B00000'>
			<td><font color='white' face='tahoma' size='2'><b>Bid Id :</td>
			<td><font color='white' face='tahoma' size='2'><b><%=bid.getBidid()%></td>
			<td><font color='white' face='tahoma' size='2'><b>User Id :</td>
			<td><font color='white' face='tahoma' size='2'><b><%=bid.getUserid()%></td>
		</tr>
		
<%
			ArrayList	biddets	=	JdbcData.loadBidDetails(bid.getBidid());
			for(int j=0; j<biddets.size(); j++)
			{
				biddet=(BidDetails)biddets.get(j);
%>
		<tr bgcolor="#F0F0F0">
			<td><font face='tahoma' size='2'>Bidding Product Id :</td>
			<td><font face='tahoma' size='2'><%=biddet.getProductid()%></td>
			<td><font face='tahoma' size='2'>Bid Quantity :</td>
			<td><font face='tahoma' size='2'><%=biddet.getBidqty()%></td>
		</tr>
<%
			}
%>
		<tr bgcolor="#E8E8E8">
			<td></td><td></td>
			<td><font face='tahoma' size='2'>Bid Price</td>
			<td><font face='tahoma' size='2'><%=bid.getBidprice()%></td>
		</tr>
<%
		}
%>
	</table><br>
<%
	}
	else
		{
%>
		<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='3' width='550' style='border-collapse: collapse'>
			<tr bgcolor='#B00000'>
				<td><font color='white' face='tahoma' size='2'>
					<b>No Bids for this Auctions till now !
				</td>
			</tr>
		</table>
<%
		}
%>		
</center>
</body>
</html:html>