<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html:html>

<head>
	<font size="2" face="tahoma">
	<html:base/>
</head>

	<body>
	<center>
		<table width="530">
			<tr height="50">
				<td><img src="images/h2.gif" width="556" height="28"></td>
			<tr>
				<td>
					<p align="center">
					<img src="images/img1.jpg" align="left" width="145" height="136">
					<img src="images/boardroom.jpg" align="right" width="138" height="136">
					
					<font size="2" face="tahoma">
					Welcome to all ! This is a site mainly targeting 
                    manufacturers for making their business processes much 
                    easier. Register for FREE And become a part of BUY N SELL 
                    Auctions and then you can Sell your items over an Auction, 
                    Bid for items on Auctions and Reverse Auction Processes.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    Thank You !
					
				</td>
			</tr>
			<tr height="50">
				<td><img src="images/h3.gif" width="556" height="28"></td>
			<html:form action="/ListCategory">
				<table width="550" bgcolor="#F0F0F0" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
<tr>
	<td align="left" height="15"><html:radio property="itemCategory" value="Antiques & Collectibles">
		<font size="1" face="tahoma">Antiques &amp; Collectibles</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Apparel & Accessories">
		<font size="1" face="tahoma">Apparel &amp; Accessories</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Art Works">
		<font size="1" face="tahoma">Art Works</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Automotives">
		<font size="1" face="tahoma">Automotives</html:radio></td>
</tr>
<tr>
	<td align="left" height="15"><html:radio property="itemCategory" value="Books & Magazines">
		<font size="1" face="tahoma">Books &amp; Magazines</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Cameras & Optics">
		<font size="1" face="tahoma">Cameras &amp; Optics</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Cars & Bikes">
		<font size="1" face="tahoma">Cars &amp; Bikes</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Coins, Stamps & Hobbies">
		<font size="1" face="tahoma">Coins, Stamps &amp; Hobbies</html:radio></td>
</tr>
<tr>
	<td align="left" height="15"><html:radio property="itemCategory" value="Computers & Peripherals">
		<font size="1" face="tahoma">Computers &amp; Peripherals</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Consumer Electronics">
		<font size="1" face="tahoma">Consumer Electronics</a></html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Entertainment & Leisure">
		<font size="1" face="tahoma">Entertainment &amp; Leisure</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Fitness & Sports">
		<font size="1" face="tahoma">Fitness &amp; Sports</html:radio></td>
</tr>
<tr>
	<td align="left" height="15"><html:radio property="itemCategory" value="Health & Beauty">
		<font size="1" face="tahoma">Health &amp; Beauty</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Home & Garden">
		<font size="1" face="tahoma">Home &amp; Garden</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Jewellery & Watches">
		<font size="1" face="tahoma">Jewellery &amp; Watches</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Science & Technology">
		<font size="1" face="tahoma">Science &amp; Technology</html:radio></td>
</tr>
<tr>
	<td></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Services & Real-Estate">
		<font size="1" face="tahoma">Services &amp; Real-Estate</html:radio></td>
	<td align="left" height="15"><html:radio property="itemCategory" value="Toys & Games">
		<font size="1" face="tahoma">Toys &amp; Games</html:radio></td>
	<td></td>
</tr>

<tr><td colspan="100%" align="center"><br><html:submit value="Show Category Related Items"/></td></tr>
				</table>
</html:form>
			</tr>
			
			
		</table>
		
	</body>
</html:html>