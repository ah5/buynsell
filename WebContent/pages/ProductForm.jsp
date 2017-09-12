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
		<tr height="20"><td><img src="images/h10.gif"></td><tr>
</table>

<html:form action="/PostCatalog">
<table border="1" bordercolor="gray" rules="none" cellpadding="3" cellspacing="2" width="550" style="border-collapse: collapse">
	<tr>
		<td><font size="2"><b>User ID *</td>
		<td><html:text property="userid"/></td>
		<td></td>
		<td></td>		
	</tr>

	<tr>
		<td><font size="2"><b>Catalog ID *</td>
		<td><html:text property="catalogid"/></td>
		<td><font size="2"><b>Items Added to Catalog :</td>
		<td>			</td>		
	</tr>
	
	<tr>
		<td><font size="2"><b>Catalog Starting Price *</td>
		<td><html:text property="startingprice"/></td>
		<td><font size="2"><b>Bid Increment Rate *</td>
		<td><html:text property="bidincrement"/></td>		
	</tr>
	<tr>
		<td><font size="2"><b>Auction ID *</td>
		<td><html:text property="auctionid"/></td>
		<td></td>
		<td></td>		
	</tr>
	<tr>
		<td><font size="2"><b>Auction Starting Date *</td>
		<td><html:text property="auctionstartingdate"/></td>
		<td><font size="2"><b>Auction Ending Date *</td>
		<td><html:text property="auctionendingdate"/></td>		
	</tr>
	<tr>
		<td></td>	
		<td></td>											<!--Cancel proposal-->
		<td><html:reset value="Clear Catalog"/></td>								
		<td><html:submit value="Post Catalog"/></td>		
	</tr>
</table>
</html:form>

<br>
<table>	
		<tr height="20"><td><img src="images/h11.gif"></td><tr>
</table>
<html:form action="/AddProductToCatalog">
<table border="1" bordercolor="gray" rules="none" cellpadding="3" cellspacing="2" width="550" style="border-collapse: collapse">
	<tr>
		<td><font size="2"><b>Product ID *</td>
		<td><html:text property="id"/></td>
		<td><font size="2"><b>Product Name *</td>
		<td><html:text property="name"/></td>		
	</tr>

	<tr>
		<td><font size="2"><b>Description *</td>
		<td><html:text property="desc"/></td>
		<td><font size="2"><b>Product Image *</td>
		<td><html:text property="img"/></td>		
	</tr>
	
	<tr>
		<td><font size="2"><b>Product Category *</td>
		<td><html:text property="cat"/></td>
		<td><font size="2"><b>Quantity *</td>
		<td><html:text property="qty"/></td>		
	</tr>
	
	<tr>
		<td><font size="2"><b>Product Weight *</td></td>
		<td><html:text property="wt"/></td>
		<td></td>
		<td><html:submit value="Add item to Catalog"/></td>
	</tr>
</table>
</html:form>


</body>
</html:html>