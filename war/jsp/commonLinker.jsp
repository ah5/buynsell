<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<html:html>
<head>
	<html:base/>
</head>
	<body>	

		<table bgcolor="#F0F0F0" valign="top" border="1" bordercolor="#336699" rules="none" cellpadding="3" cellspacing="0" width="170" style="border-collapse: collapse">
		
<html:form action="/UserLogin">
			<tr><td align="center"><img src="images/sign.gif"></td></tr>

			<tr><td align="center"><font color="gray" size="2" face="tahoma"><b>User ID</td></tr>
			<tr><td align="center"><html:text property="userid"/>	</td></tr>			
			
			
			<tr><td align="center"><font color="gray" size="2" face="tahoma"><b>Password</td></tr>
			<tr><td align="center"><html:password property="pass"/></td></tr>
			
			<tr><td align="right"><html:submit value="Login"/></td></tr>
</html:form>
		</table>
		
<br>

	<table bgcolor="#F0F0F0" border="1" bordercolor="#336699" rules="none" cellpadding="3" cellspacing="0" width="170" style="border-collapse: collapse">
		<tr><td align="center"><img src="images/search.gif"></td></tr>
		
<html:form action="/ItemSearch">
		<tr><td align="center"><font color="gray" size="2" face="tahoma"><b>Enter Item Name</td></tr>
		<tr><td align="center"><html:text property="itemName"/><br></td></tr>
		
		<tr><td align="center"><font color="gray" size="2" face="tahoma"><b>Select Category Name</td></tr>
		<tr><td height="27">
		<html:select property="itemCategory">
			<html:option value="none">Select a Category</html:option>
			<html:option value="none">- - - - - - - - - - - - - -  - - -</html:option>
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
			<html:option value="none">- - - - - - - - - - - - - -  - - -</html:option>
		</html:select>
		</td></tr>
		
		<tr><td align="right"><html:submit value="Search"/></td></tr>
</html:form>
	</table>

	</body>
</html:html>

