<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<font size="2" color="gray" face="tahoma">
	<html:base/>
	<style>
<!--
 p.MsoNormal
	{mso-style-parent:"";
	margin-bottom:.0001pt;
	font-size:12.0pt;
	font-family:"Times New Roman";
	margin-left:0in; margin-right:0in; margin-top:0in}
p.MsoBodyTextIndent
	{margin-bottom:.0001pt;
	text-align:justify;
	text-indent:.5in;
	line-height:120%;
	font-size:13.0pt;
	font-family:"Times New Roman";
	margin-left:0in; margin-right:0in; margin-top:0in}
p.MsoBodyText2
	{margin-bottom:.0001pt;
	text-align:justify;
	text-autospace:none;
	font-size:13.0pt;
	font-family:"Times New Roman";
	margin-left:0in; margin-right:0in; margin-top:0in}
-->
</style>

</head>

	<body>	
		<center>

<table><tr height="20"><td>	
<logic:present name="admin">
	<img src="images/h6.gif">
</logic:present>
<logic:notPresent name="admin">
	<img src="images/h7.gif">
</logic:notPresent>
</td><tr>
</table>
<BR>

<table width="550" bordercolor="#336699" border='1' rules='none' cellpadding='5' cellspacing='0' style='border-collapse: collapse'>
			<tr>
			<logic:present name="admin">	
				<td><img src="images/14.gif"></td>
				<td align="left"><font size="2" color="gray" face="tahoma"><b>ABOUT BUYNSELL</td>
			</logic:present>
			<logic:notPresent name="admin">	
				<td colspan="100%" align="center"><font size="2" color="gray" face="tahoma"><b>ABOUT BUYNSELL</td>
			</logic:notPresent>
			</tr>
			
			<tr><td colspan="100%"><font size="2" color="gray" face="tahoma">
<p class="MsoNormal" style="text-align:justify;text-indent:.5in;line-height:150%">
This site &quot;<b>buynsell</b>&quot; is a part of the effort put on an academic project 
put in by <b>Aneesh Hamza</b>, a <b>final year MCA student</b> from <b>V.L.B. 
Janakiammal College of Engineering and Technology</b> situated in Kovaipudur, 
Coimbatore. The project details are summarized as below.</p>
<p class="MsoNormal" style="text-align:justify;text-indent:.5in;line-height:150%">
<span style="color:gray">This project “<b>Implementation of Auction services 
for Complex Resource Allocation Problems using CABOB Algorithm</b>” emerges for 
the solution against problems in combinatorial auctions. Combinatorial or 
multi-item, multi-unit <font size="3">Auction events in Industrial procurement 
involve highly customizable goods that pose serious challenges before buyers and 
sellers when trying to determine the best set of offers from the package bids in 
question. Such auctions can p</font></span><span style="font-size:12.0pt;line-height:150%">rove 
tiresome as well as burdening for the seller to find out the best bid.<span style="color:black">
</span>So as a whole, it becomes an important factor that the traders are 
relieved from the burden of choosing the best of available offers, thus making 
the system capable and efficient by allocating the multiple resources in complex 
economic environments.</span></p>
<p class="MsoNormal" style="text-align: justify; text-autospace: none">&nbsp;</p>
<p class="MsoNormal" style="text-align: justify; text-indent: .5in; line-height: 150%; text-autospace: none">
To overcome the above-mentioned hindrances, we go for a modern approach. This 
system mainly aims at designing a transparent, efficient and practical 
combinatorial buyers’ auction packed with an auction solution feature for 
multi-item multi-unit packages. The trader can express his preferences over the 
items. The problem of solving for the winning offers in a combinatorial auction 
is done using the Combinatorial Auction Branch On Bids Algorithm. </p>
<p class="MsoBodyText2" style="text-indent:.5in;line-height:150%">
<span style="font-size:12.0pt;line-height:150%">The software architecture of the 
solution is a Three-tier Web Application based on J2EE (Sun Java 2 Enterprise 
Edition) upon the Struts Framework. The web application is based on MySQL in the 
data layer and Tomcat as the J2EE server to build the interfaces for the buyers 
and sellers. The presentation layer comprises of XML &amp; JSP. </span></p>
<p class="MsoNormal" style="text-align: justify; text-autospace: none">&nbsp;</p>
<p class="MsoNormal" style="text-align: justify; text-autospace: none; line-height: 150%">
<span style="font-size: 12.0pt; font-family: Times New Roman">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
Solving for the best offers available in a combinatorial auction is an 
NP-complete problem. There are many algorithms proposed for solving such NP 
complete problems like CASS Algorithm, Brute-Force Algorithm, VSA Algorithm and<b>
</b>CPLEX. However, it was found that CABOB stands high in case of the 
performance it yields. This solution offers a service for the Buyers to help 
them determine the optimal bundle of received offers based on their constraints 
and preferences. In this way, Buyers are relieved from the burden of solving for 
the best deal. Thus, this system engages in developing hosts, and manages a 
combinatorial auction and serves the purpose of an e-commerce solution for 
brand-centric organizations. </span></p>
			
			</td></tr>
			<tr>
				<td colspan="2" align="center"><font size="2" color="gray" face="tahoma"><b>THANK YOU</td>
			</tr>
			
</table>

<br>

<logic:present name="admin">
	<html:link forward="backToAdminHome">
			<img src="images/00.gif">	
		</html:link>
	<br><font size="2" color="gray" face="tahoma"><b>B A C K			
</logic:present>


	
	</body>
</html:html>
