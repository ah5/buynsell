<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-form" prefix="form" %>

<%@ page import="java.util.*,buyNsell.BusinessObjects.*"%>

<html:html locale="true">
<head>
	<html:base/>
</head>

<body>
<center>
<%String cat=(String)session.getAttribute("category");%>
<table>
	<tr><td><img src="images/res1.gif">													</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b>Category-wise Listing for <%=cat%></td></tr>
</table>


<%
	Product product=null;
	Catalog catalog=null;
	Auction auction=null;
	ArrayList categoryRelatedProducts=(ArrayList)session.getAttribute("catProducts");
	ArrayList categoryRelatedCatalogs=(ArrayList)session.getAttribute("catCatalogs");
	ArrayList categoryRelatedAuctions=(ArrayList)session.getAttribute("catAuctions");
	
	for(int i=0; i<categoryRelatedCatalogs.size(); i++)
	{
		catalog=(Catalog)categoryRelatedCatalogs.get(i);
		auction=(Auction)categoryRelatedAuctions.get(i);
%>
<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>	
	<tr bgcolor='#336699'>
		<td width="20%"><font color='white' face='tahoma' size='2' ><b>
			Auction Id :</td>
		<td width="30%"><font color='white' face='tahoma' size='2'><b>
			<%=auction.getAuctionid()%></td>
		<td width="20%"><font color='white' face='tahoma' size='2'><b>
			Starting Price :</td>
		<td width="30%"><font color='white' face='tahoma' size='2'><b>
			<%=catalog.getStartingprice()%></td></tr>
	<tr>
		<td width="20%"><font face='tahoma' size='2'>
			<b>Starting Date :</td>
		<td width="30%"><font face='tahoma' size='2'>
			<%=auction.getStartingdate()%></td>
		<td width="20%"><font face='tahoma' size='2'>
			<b>Ending Date :</td>
		<td width="30%"><font face='tahoma' size='2'>
			<%=auction.getEndingdate()%></td></tr>
	<tr bgcolor='#336699'>
		<td colspan="4"><font color="white" face='tahoma' size='2'><b>
			Category Related Item Listed in this Auction !
		</td></tr>
<%		String col="#E8E8E8";
		for(int j=0; j<categoryRelatedProducts.size(); j++)
		{
			product=(Product)categoryRelatedProducts.get(j);
			if(product.getCatalogid().equals(catalog.getCatalogid()))
			{
				if(col.equals("#E8E8E8"))	col="#F0F0F0";
				else						col="#E8E8E8";
%>
	<tr bgcolor="<%=col%>">
		<td width="20%"><font face='tahoma' size='2'>
			<b>Product Id :</td>
		<td width="30%"><font face='tahoma' size='2'>
			<%=product.getProductid()%></td>
		<td width="20%"><font face='tahoma' size='2'>
			<b>Product Name :</td>
		<td width="30%"><font face='tahoma' size='2'>
			<%=product.getName()%></td></tr>
	<tr bgcolor="<%=col%>">
		<td width="20%"><font face='tahoma' size='2'>
			<b>Description :</td>
		<td width="30%"><font face='tahoma' size='2'>
			<%=product.getDesc()%></td>
		<td width="20%"><font face='tahoma' size='2'>
			<b>Category :</td>
		<td width="30%"><font face='tahoma' size='2'>
			<%=product.getCat()%></td></tr>
	<tr bgcolor="<%=col%>">
		<td></td><td></td>
		<td width="25%"><font face='tahoma' size='2'>
			<b>Quantity Available:</td>
		<td width="30%"><font face='tahoma' size='2'>
			<%=product.getQty()%></td></tr>
<%
			}
		}
%>
</table><br>
<%
	}
%>

<table>
	<tr><td align="center"><font face="tahoma" size="2">
		If you feel so that your category wise search was worthwhile please feel free <br>
		to login and start bidding for the items you desire for and make<br>
		it yours ! If you have not registered, register for free and <br>
		Start bidding against your Items !
		</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b>Thank You !</td></tr>
</table>
<%
session.removeAttribute("category");
session.removeAttribute("catProducts");
session.removeAttribute("catCatalogs");
session.removeAttribute("catAuctions");
%>

</center>
</body>
</html:html>