<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-form" prefix="form" %>

<%@ page import="java.util.*,buyNsell.BusinessObjects.*,buyNsell.DatabaseConnection.*"%>

<html:html locale="true">
<head>
	<html:base/>
</head>

<body>
<center>

<table>
	<tr><td><img src="images/res1.gif">													</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b>Search Success - Items Found	</td></tr>
</table>


<%
	Product product	=	(Product)session.getAttribute("itemSearched");
	ArrayList simProducts	=	JdbcData.loadSimilarProducts(product.getName());
	for(int i=0; i<simProducts.size(); i++)
	{
		product			=	(Product) 	simProducts.get(i);
		Auction auction	=	JdbcData.loadAuction(product.getCatalogid());
		Catalog catalog	=	JdbcData.loadCatalog(product.getCatalogid());
%>
<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>	
	<tr bgcolor='#336699'>
		<td><font color='white' face='tahoma' size='2'><b>
			Auction Id :</td>
		<td><font color='white' face='tahoma' size='2'><b>
			<%=auction.getAuctionid()%></td>
		<td><font color='white' face='tahoma' size='2'><b>
			Starting Price :</td>
		<td><font color='white' face='tahoma' size='2'><b>
			<%=catalog.getStartingprice()%></td></tr>
	<tr>
		<td><font face='tahoma' size='2'>
			Starting Date :</td>
		<td><font face='tahoma' size='2'>
			<%=auction.getStartingdate()%></td>
		<td><font face='tahoma' size='2'>
			Ending Date :</td>
		<td><font face='tahoma' size='2'>
			<%=auction.getEndingdate()%></td></tr>
	<tr bgcolor='#336699'>
		<td colspan="4"><font color="white" face='tahoma' size='2'><b>
			Searched Item Listed in this Auction !
		</td></tr>
	<tr bgcolor="#F0F0F0">
		<td><font face='tahoma' size='2'>
			Product Id :</td>
		<td><font face='tahoma' size='2'>
			<%=product.getProductid()%></td>
		<td><font face='tahoma' size='2'>
			Product Name :</td>
		<td><font face='tahoma' size='2'>
			<%=product.getName()%></td></tr>
	<tr bgcolor="#F0F0F0">
		<td><font face='tahoma' size='2'>
			Description :</td>
		<td><font face='tahoma' size='2'>
			<%=product.getDesc()%></td>
		<td><font face='tahoma' size='2'>
			Category :</td>
		<td><font face='tahoma' size='2'>
			<%=product.getCat()%></td></tr>
	<tr bgcolor="#F0F0F0">
		<td></td><td></td>
		<td><font face='tahoma' size='2'>
			Quantity Available:</td>
		<td><font face='tahoma' size='2'>
			<%=product.getQty()%></td></tr>
</table><br>
<%
	}
%>

<table>
	<tr><td align="center"><font face="tahoma" size="2">
		If you feel so that your search was worthwhile please feel free <br>
		to login and start bidding for the items you desire for and make<br>
		it yours ! If you have not registered, register for free and <br>
		Start bidding for your Items !
		</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b>Thank You !</td></tr>
</table>


</center>
</body>
</html:html>