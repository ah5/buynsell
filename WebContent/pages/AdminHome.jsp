<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
	<font size="2" color="gray" face="tahoma">
	<html:base/>
	<style>
		.shakeimage
		{position:relative}
	</style>
	<script language="JavaScript1.2">
		var rector=3
		var stopit=0 
		var a=1

		function init(which){
		stopit=0
		shake=which
		shake.style.left=0
		shake.style.top=0
		}

		function rattleimage(){
		if ((!document.all&&!document.getElementById)||stopit==1)
		return
		if (a==1){
		shake.style.top=parseInt(shake.style.top)+rector
		}
		else if (a==2){
		shake.style.left=parseInt(shake.style.left)+rector
		}	
		else if (a==3){
		shake.style.top=parseInt(shake.style.top)-rector
		}	
		else{
		shake.style.left=parseInt(shake.style.left)-rector
		}
		if (a<4)
		a++
		else
		a=1
		setTimeout("rattleimage()",50)
		}

		function stoprattle(which){
		stopit=1
		which.style.left=0
		which.style.top=0
		}
	</script>
</head>

	<body>	
		<center>	
		
		<table>	
			<tr height="20"><td><img src="images/h6.gif"></td><tr>
		</table>
		
		<table width="550">
			<tr>
				<td align="center">
					<html:link forward="adminRequestsManageUsers">
						<img src="images/01.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
					</html:link>
				</td>
				<td align="center">
					<html:link forward="adminRequestsManageAuctions">
						<img src="images/12.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
					</html:link>
				</td>
				<td align="center">
					<html:link forward="adminViewsAuctionTimers">
						<img src="images/03.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
					</html:link>
				</td>
			</tr>
			<tr>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>M A N A G E<br>U S E R S</td>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>M A N A G E<br>A U C T I O N S</td>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>A U C T I O N<br>T I M E R S</td>
			</tr>
			
			<tr>
				<td align="center">
					<html:link forward="adminSettings">
						<img src="images/06.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
					</html:link>
				</td>
				<td align="center">
					<html:link forward="adminChangesPassword">
						<img src="images/02.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
					</html:link>
				</td>
				<td align="center">
					<img src="images/18.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
				</td>
			</tr>
			<tr>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>S E T T I N G S</td>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>C H A N G E<br>A D M I N<br>P A S S W O R D</td>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>R E P O R T S<br>&<br>R A N K I N G S</td>
			</tr>
			
			<tr>
				<td align="center">
					<html:link forward="adminAbout">
						<img src="images/14.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
					</html:link>
				</td>
				<td align="center">
					<img src="images/09.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
				</td>
				<td align="center">
					<html:link forward="adminRequestsLogout">
						<img src="images/22.gif" class="shakeimage" onMouseover="init(this);rattleimage()" onMouseout="stoprattle(this);top.focus()" onClick="top.focus()">
					</html:link>
				</td>
			</tr>
			<tr>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>A B O U T</td>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>H E L P</td>
				<td align="center"><font size="2" color="gray" face="tahoma"><b>L O G O U T</td>
			</tr>
		</table>
	</body>
</html:html>
