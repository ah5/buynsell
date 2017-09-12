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
	<tr><td><img src="images/res1.gif">	</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b>Catalog Post - Success</td></tr>
	<tr><td align="center"><font face="tahoma" size="2">Your Catalog has succesfully been Posted <br>and an Auction is Registered ! <br>You can add more products to your catalog <br>if you feel like !</td></tr>
	<tr><td align="center"><font face="tahoma" size="3"><b>Thank You !</td></tr>
	<tr><td><font color="white"> FILLER SPACE </td></tr>
	<tr><td><img src="images/disp6.gif">	</td></tr>
</table>



<%@ page import="buyNsell.BusinessObjects.*,buyNsell.DatabaseConnection.*,buyNsell.Utils.*,java.util.*" %> 
<%
	Users u		=	(Users)session.getAttribute("user");
	Auction a	=	(Auction)session.getAttribute("auction");
	Catalog c	=	(Catalog)session.getAttribute("catalog");
%>

	<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>
		<tr bgcolor='#B00000'>
			<td><font color='white' face='tahoma' size='2'><b>Catalog Id :</td>
			<td><font color='white' face='tahoma' size='2'><%=c.getCatalogid()%></td>
			<td></td><td></td></tr>
		<tr>
			<td><font face='tahoma' size='2'><b>Starting Price :</td>
			<td><font face='tahoma' size='2'><%= c.getStartingprice()%></td>
			<td><font face='tahoma' size='2'><b>Bid Increment :</td>
			<td><font face='tahoma' size='2'><%= c.getBidincrement()%></td></tr>
		<tr>	
			<td><font face='tahoma' size='2'><b>Auction ID :</td>
			<td><font face='tahoma' size='2'><%= a.getAuctionid()%></td>
			<td></td><td></td></tr>
		<tr>	
			<td><font face='tahoma' size='2'><b>Auction Starting Date :</td>
			<td><font face='tahoma' size='2'><%= a.getStartingdate()%></td>
			<td><font face='tahoma' size='2'><b>Auction Ending Date :</td>
			<td><font face='tahoma' size='2'><%= a.getEndingdate()%></td></tr>
	</table>

<table border-color='gray' border='1' rules='none' cellpadding='3' cellspacing='0' width='550' style='border-collapse: collapse'>
	<tr bgcolor='darkgray'>
		<td align="left" colspan="100%"><font color="white" face='tahoma' size='2'><b>Products with this Auction</td>
	</tr>
	
<%
	Product product=null;
	ArrayList allProducts=JdbcData.loadProduct(c.getCatalogid());
	if(allProducts.size() > 0)
	{
		String col="#E8E8E8";
		for(int i=0; i<allProducts.size(); i++)
		{
			if(col.equals("#E8E8E8"))	col="#F0F0F0";
			else						col="#E8E8E8";
			product=(Product)allProducts.get(i);
%>
		<tr bgcolor="<%=col%>">
			<td width="20%"><font face='tahoma' size='2'><b>ID :</td>
			<td width="20%"><font face='tahoma' size='2'><%=product.getProductid()%><td>
			<td width="15%"><font face='tahoma' size='2'><b>Name :</td>
			<td width="15%"><font face='tahoma' size='2'><%=product.getName()%><td>
			<td width="15%"><font face='tahoma' size='2'><b>Description :</td>
			<td width="15%"><font face='tahoma' size='2'><%=product.getDesc()%><td>
		</tr>
		<tr bgcolor="<%=col%>">
			<td width="20%"><font face='tahoma' size='2'><b>Category :</td>
			<td width="20%"><font face='tahoma' size='2'><%=product.getCat()%><td>
			<td width="15%"><font face='tahoma' size='2'><b>Quantity :</td>
			<td width="15%"><font face='tahoma' size='2'><%=product.getQty()%><td>
			<td width="15%"><font face='tahoma' size='2'><b>Weight :</td>
			<td width="15%"><font face='tahoma' size='2'><%=product.getWt()%><td>
		</tr>
<%
		}
	}
%>		
	</table>
	<br>
<hr>


<html:link forward="CatalogDone"><font size="2" face="tahoma" color="black">Done with the Catalog</html:link>

<br><br>

<html:form action="/AddProductToCatalog">
<%
	GenerateID obj=new GenerateID();
	String pro	=	obj.getProductID();
%>
<table>	
		<tr height="20"><td><img src="images/h11.gif"></td><tr>
</table>
<table border="1" bordercolor="gray" rules="none" cellpadding="3" cellspacing="2" width="550" style="border-collapse: collapse">
	<tr>
		<td><font size="2"><b>Product ID *</td>
		<td><html:text property="id" value="<%=pro%>" readonly="true"/></td>
		<td><font size="2"><b>Product Name *</td>
		<td><html:text property="name"/></td>		
	</tr>

	<tr>
		<td><font size="2"><b>Description *</td>
		<td><html:textarea property="desc" cols="16"/></td>
		<td><font size="2"><b>Product Category *</td>
		<td><html:select property="cat">
				<html:option value="Antiques & Collectibles">Antiques&amp;Collectibles</html:option>
				<html:option value="Apparel & Accessories">Apparel&amp;Accessories</html:option>
				<html:option value="Art Works">Art Works</html:option>
				<html:option value="Automotives">Automotives</html:option>
				<html:option value="Books & Magazines">Books&amp;Magazines</html:option>
				<html:option value="Cameras & Optics">Cameras&amp;Optics</html:option>
				<html:option value="Cars & Bikes">Cars&amp;Bikes</html:option>
				<html:option value="Coins, Stamps & Hobbies">Coins & Stamps</html:option>
				<html:option value="Computers & Peripherals">Computers</html:option>
				<html:option value="Consumer Electronics">Consumer Electronics</html:option>
				<html:option value="Entertainment & Leisure">Entertainment&amp;Leisure</html:option>
				<html:option value="Fitness & Sports">Fitness&amp;Sports</html:option>
				<html:option value="Health & Beauty">Health&amp;Beauty</html:option>
				<html:option value="Home & Garden">Home&amp;Garden</html:option>
				<html:option value="Jewelley & Watches">Jewelley&amp;Watches</html:option>
				<html:option value="Science & Technology">Science&amp;Technology</html:option>
				<html:option value="Services & Real-Estate">Services&amp;Real-Estate</html:option>
				<html:option value="Toys & Games">Toys&amp;Games</html:option>
			</html:select>
		</td>
	</tr>
	
	<tr>
		<td><font size="2"><b>Quantity *</td>
		<td><html:text property="qty"/></td>		
		<td><font size="2"><b>Product Weight *</td></td>
		<td><html:text property="wt"/></td>
	</tr>
</table>
<br>
	<html:reset value="Clear Product"/>
	<html:submit value="Add Product to Catalog"/>

</html:form>

</center>
</body>
</html:html>