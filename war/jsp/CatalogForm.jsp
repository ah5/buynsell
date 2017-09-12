<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-form" prefix="form" %>

<%@ page import="buyNsell.BusinessObjects.*,buyNsell.Utils.*"%>
<logic:present name="user">
<%
	Users u=(Users)session.getAttribute("user");
	GenerateID obj=new GenerateID();
	String auc	=	obj.getAuctionID();
	String cat	=	obj.getCatalogID();
	String pro	=	obj.getProductID();
%>
</logic:present>				
<html:html locale="true">
<head>
	<html:base/>
	<font color="gray" face="tahoma">
	<SCRIPT LANGUAGE="JavaScript" SRC="scripts/CalendarPopup.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript">
	var cal = new CalendarPopup();
	</SCRIPT>
</head>

<body>
<%@ page import="buyNsell.AccountOperations.SellerOperations.CatalogPosting.*" %> 
<center>
<html:form action="/PostCatalog">
<table>	
		<tr height="20"><td><img src="images/h10.gif"></td><tr>
</table>

<table border="1" bordercolor="gray" rules="none" cellpadding="3" cellspacing="0" width="550" style="border-collapse: collapse">
	<tr>
		<td><font size="2"><b>User ID *</td>
		<td>
			<html:text property="userid" value="<%=u.getUserid()%>" readonly="true" />
		</td>
		<td></td>
		<td></td>		
	</tr>

	<tr>
		<td><font size="2"><b>Catalog ID *</td>
		<td><html:text property="catalogid" value="<%=cat%>"  readonly="true"  /></td>
		<td></td>
		<td></td>		
	</tr>
	
	<tr>
		<td><font size="2"><b>Catalog Starting Price *</td>
		<td><html:text property="startingprice"/></td>
		<td><font size="2"><b>Bid Increment Rate *</td>
		<td><html:text property="bidincrement"/></td>		
	</tr>
	<tr>
		<td><font size="2"><b>Auction ID *</td>
		<td><html:text property="auctionid" value="<%=auc%>" readonly="true" /></td>
		<td></td>
		<td></td>		
	</tr>
	<tr>
		<td><font size="2"><b>Auction Starting Date *</td>
		<td><font size="2"><html:text property="auctionstartingdate" readonly="true" size="16"/>
			<img src="images\calendar.jpg"
				onclick="cal.select(document.forms['catalogPostForm'].auctionstartingdate,'auctionstartingdate','yyyy-MM-dd')">
			<br>Hrs
			<html:select property="shrs">	
				<%for(int i=0; i<24; i++){
					if(i<10){%>
						<option value="0<%=i%>">0<%=i%></option>
					<%}else{%>
						<option value="<%=i%>"><%=i%></option>
				<%}}%>
			</html:select>&nbspMins
			<html:select property="smins">	
				<%for(int i=0; i<60; i++){
					if(i<10){%>
						<option value="0<%=i%>">0<%=i%></option>
					<%}else{%>	
						<option value="<%=i%>"><%=i%></option>
				<%}}%>		
			</html:select>
		</td>
		<td><font size="2">Auction Ending Date *</td>
		<td><font size="2"><html:text property="auctionendingdate" readonly="true" size="16"/>
					<img src="images\calendar.jpg"
				onclick="cal.select(document.forms['catalogPostForm'].auctionendingdate,'auctionendingdate','yyyy-MM-dd')">
			<br>Hrs
			<html:select property="ehrs">	
				<%for(int i=0; i<24; i++){
					if(i<10){%>
						<option value="0<%=i%>">0<%=i%></option>
					<%}else{%>
						<option value="<%=i%>"><%=i%></option>
				<%}}%>
			</html:select>&nbspMins
			<html:select property="emins">	
				<%for(int i=0; i<60; i++){
					if(i<10){%>
						<option value="0<%=i%>">0<%=i%></option>
					<%}else{%>	
						<option value="<%=i%>"><%=i%></option>
				<%}}%>		
			</html:select>
		</td>		
		
	</tr>
</table>

<br>

<table>	
		<tr height="20"><td><img src="images/h11.gif"></td><tr>
</table>
<table border="1" bordercolor="gray" rules="none" cellpadding="3" cellspacing="2" width="550" style="border-collapse: collapse">
	<tr>
		<td><font size="2"><b>Product ID *</td>
		<td><html:text property="id" value="<%=pro%>" readonly="true" /></td>
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
	<html:reset value="Clear Catalog"/>
	<html:submit value="Post Catalog"/>

</html:form>


</body>
</html:html>