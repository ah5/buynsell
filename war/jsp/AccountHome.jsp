<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<font size="2" color="gray" face="tahoma">
	<html:base/>
</head>
	<body>	<center>
	
	<logic:present name="auction">
		<%session.removeAttribute("auction");%>
	</logic:present>
	<logic:present name="catalog">
		<%session.removeAttribute("catalog");%>
	</logic:present>
	
	<table>	
		<tr height="20"><td><img src="images/disp7.gif"></td><tr>
	</table>
		
		<table width="550">
		
		<tr><td align="center"><font face="tahoma" size="3"><b>-- Your account at BUY N SELL --</td></tr>
		<tr><td align="center"><font face="tahoma" size="2">
					You are entitled to post catalogs and host it as an auction, <br>
					the auction is made noticed to other users by an automatic <br>
					mailing system. As the auction time elapses, BUY N SELL lets <br>
					you know the best of offers put forward by the Bidders.<br><br>
					You can also post Request For Quotes that will be forwarded<br>
					to the the other users in a similar fashion as mentioned before<br>
					and the best of quotes are presented to you that can <br>
					fulfill your requests.
		</td></tr>
		<tr><td align="center"><font face="tahoma" size="3"><b>-- W E L C O M E --</td></tr>
		
		</table>
		
		
	</body>
</html:html>
