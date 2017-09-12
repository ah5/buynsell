<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<html:html>
<head>
	<html:base/>
</head>
	<body>	
	
		<table bordercolor="orange" valign="top" align="left" border="1" rules="none" cellpadding="5" cellspacing="0" width="180" style='border-collapse: collapse'>
<!--Seller Related Operations-->			
			<tr><td align="center"><img src="images\a1.gif"></td></tr>
			<tr>
				<td  valign="bottom" height="12" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<html:link forward="postingACatalog"><font size="2" face="tahoma" color="black">Post a Catalog</html:link>
				</td>
			</tr>
			<tr>
				<td  height="29" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<html:link forward="viewAuctions"><font size="2" face="tahoma" color="black">View your Auctions</html:link>
				</td>
			</tr>
			
			<tr>
				<td><hr color="gray"></td>
			</tr>
			
<!--Buyer Related Operations-->						
			<tr><td align="center"><img src="images\a2.gif"></td></tr>
			<tr>
				<td  height="29" width="180">
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<html:link forward="viewAvailableAuctions"><font size="2" face="tahoma" color="black">View Available Auctions</html:link>
				</td>
			</tr>
			<tr>
				<td height="29" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<font size="2" face="tahoma" color="black">View Auctioned Items
				</td>
			</tr>
			
			<tr>
				<td><hr color="gray"></td>
			</tr>
			
<!--Account Related Operations-->			
			<tr><td align="center"><img src="images\a3.gif"></td></tr>
			<tr>
				<td height="29" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<html:link forward="accountHome"><font size="2" face="tahoma" color="black">Account Home</html:link>
				</td>
			</tr>
			<tr>
				<td height="29" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<html:link forward="changePassword"><font size="2" face="tahoma" color="black">Change Your Password</html:link>
				</td>
			</tr>
			<tr>
				<td height="29" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<font size="2" face="tahoma" color="black">Modify Account Details
				</td>
			</tr>
			<tr>
				<td height="29" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<html:link forward="creditCardDetails"><font size="2" face="tahoma" color="black">Credit Card Details</html:link>
				</td>
			</tr>
			<tr>
				<td height="29" width="180">		
					<img src="images/dot.gif">&nbsp&nbsp&nbsp
					<html:link forward="logout"><font size="2" face="tahoma" color="black">Logout</html:link>
				</td>
			</tr>

		</table>


	</body>
</html:html>
