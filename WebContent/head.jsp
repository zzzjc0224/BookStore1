<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td><a href="index.jsp"><img src="images/logo.png"
					width="95" height="30" border="0" /> </a></td>
			<td style="text-align:right"><img src="images/cart.gif"
				width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a
				href="cart.jsp">购物车</a> | <a href="#">帮助中心</a> | <a id="account" >我的帐户</a>
				| <a href="register.jsp">新用户注册</a></td>
		</tr>
	</table>
	
	<script type="text/javascript">
		var adom = document.getElementById("account");
		var user = "${user.username}"; 
		console.log("user="+user);
		console.log("type of user is "+typeof user);
		if(user!=null && user!=undefined && user.trim().length!=0){
			adom.setAttribute("href", "myAccount.jsp");
		}else{
			adom.setAttribute("href", "login.jsp");
		}
		
	</script>
	
	
</div>