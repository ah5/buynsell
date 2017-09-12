<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-form" prefix="form" %>

<html:html locale="true">
<head>
	<html:base/>
</head>

<body>
<%@ page import="buyNsell.BusinessObjects.*,buyNsell.DatabaseConnection.*,buyNsell.Utils.*,java.sql.*,java.util.*" %> 

<center>

<table>	
		<tr height="20"><td><img src="images/disp3.gif"></td><tr>
</table>
<html:form action="/PostBid">
<%	
	Catalog c		=	(Catalog)session.getAttribute("selectedcatalog");
	String catID	=	c.getCatalogid();
	Users u			=	(Users)session.getAttribute("user");
	Auction auction	=	JdbcData.loadAuction(catID);
	Catalog catalog	=	JdbcData.loadCatalog(catID);
%>
	<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>
		<tr bgcolor='#B00000'>
			<td><font color='white' face='tahoma' size='2'><b>
				Auction Id :
			</td>
			<td><font color='white' face='tahoma' size='2'><b>
				<%=auction.getAuctionid()%>
			</td>
			<td></td><td></td>
		</tr>
		<tr><td><font face='tahoma' size='2'><b>Started On :</td><td><font face='tahoma' size='2'><%=auction.getStartingdate()%></td>
		<td><font face='tahoma' size='2'><b>Ends On :</td><td><font face='tahoma' size='2'><%=auction.getEndingdate()%></td></tr>
		<tr><td><font face='tahoma' size='2'><b>Catalog Id :</td><td><font face='tahoma' size='2'><%=catalog.getCatalogid()%></td></tr>
		<tr><td><font face='tahoma' size='2'><b>Starting Price :</td><td><font face='tahoma' size='2'><%=catalog.getStartingprice()%></td>
		<td><font face='tahoma' size='2'><b>Bid Increment :</td><td><font face='tahoma' size='2'><%=catalog.getBidincrement()%></td></tr>
<%
	ArrayList allProducts	=	JdbcData.loadProduct(catID);
	Product product=null;
%>
<html:hidden property="no" value="<%=String.valueOf(allProducts.size())%>"/>
<%	
	if(allProducts.size() > 0)
	{
		String col="#E8E8E8";
		for(int j=0; j<allProducts.size(); j++)
		{
			product =	(Product) allProducts.get(j);
			if(col.equals("#E8E8E8"))	col="#F0F0F0";
			else						col="#E8E8E8";
			
			String pi	=	"pi"+String.valueOf(j);
			String bq	=	"bq"+String.valueOf(j);
%>
		<tr bgcolor="<%=col%>"><td colspan='4'><hr></td></tr>
		<tr bgcolor="<%=col%>" width='500'>
			<td><font face='tahoma' size='2'>
				Product Id :</td>
			<td>
				<html:text property="<%=pi%>" value="<%=product.getProductid()%>" readonly="true"/>	</td>
			<td><font face='tahoma' size='2'>
				Product Name :</td>
			<td><font face='tahoma' size='2'>
				<%=product.getName()%></td>
		</tr>
		<tr bgcolor="<%=col%>" width='500'>
			<td></td><td></td>
			<td><font face='tahoma' size='2'>
				Bid Quantity :</td>
			<td>
				<html:text property="<%=bq%>" value="<%=product.getQty()%>"/>	</td>
		</tr>
<%
		}
	}
%>
		<tr>
			<td></td><td></td>
			<td><font face='tahoma' size='2'>
				Bid Price :</td>
			<td>
				<html:text property="bp" value="<%=catalog.getStartingprice()%>"/>	</td>
		</tr>
		<tr>
			<td></td><td></td><td></td><td></td>
		</tr>
		<tr>
			<td colspan="4" align="right"><html:submit value="Post this Bid"/></td>
		</tr>	
</table>

</html:form>



</body>
</html:html>