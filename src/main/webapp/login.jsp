<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="SHORTCUT ICON" href="<%=path%>/images/idsd.ico" />
		<title>Registry Login</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="idsd,I-DSD,DSD,registry,EuroDSD,I-DSD,National e-Science Centre,glasgow,Yorkhill" />
		<meta http-equiv="description" content="I-DSD Registry" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/navigation.css" />
		<!-- Validation Prototype -->
		<script type="text/javascript" src="<%=path %>/js/prototype/prototype161.js"></script>
		<script type="text/javascript" src="<%=path %>/js/prototype/scriptaculous/effects183.js"></script>
		<script type="text/javascript" src="<%=path %>/js/prototype/validation.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/validation.css" />
	</head>
	<body>
		<div id='body' class='body'>
			<div class='left_body'>
				<div class="banner">
					<!--
					<div class="logo">
						<a href="<%=path %>">
							<img src="<%=path %>/images/index/idsd.jpg" height="140px" align="left" alt="idsd_logo" />
						</a>
						<br/><br/>
						<div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left; ' >Welcome to the I-DSD Registry</div>
					</div>
					 -->
					<div id="login" class='login'>
						<ul>
							<li>
								<span class="shib_login"> <a href="<%=path%>/jsp/home.jsp">Login to Registry</a> </span>
							</li>
							<li>
								<span> <a href="<%=path%>/register_view.action">Create A New Account</a> </span>
							</li>
							<li>
								<span> <a href="http://www.gla.ac.uk/idsd">Further Information</a> </span>
							</li>
						</ul>
					</div>
				</div>
				<br />

				<div class="indexBody">
					<div id="login_div">
						<h1>Login</h1>
						<c:if test="${not empty param.login_error}">
							<font color="red"> 
								Your login attempt was not successful, try again.<br /><br /> 
								Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. 
							</font>
						</c:if>
				
						<form action="<%=path %>/j_spring_security_check" method="post">
							<input type="hidden" name="mode" value="${param['mode']}" />
							<table>
								<tbody>
									<tr>
										<td>Username:</td>
										<td><input type="text" name="j_username" id="j_username" value="" /></td>
									</tr>
									<tr>
										<td>Password:</td>
										<td><input type="password" name="j_password" id="j_password" value="" /></td>
									</tr>
									<tr>
										<td colspan='2'><input type='checkbox' name='_spring_security_remember_me' /> 
											Remember me on this computer.
										</td>
									</tr>
									<tr>
										<td colspan='2'><input type="submit" value="Login" /></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
				<jsp:include page="index_foot.jsp" />
			</div>
		<jsp:include page="index_right.jsp" />
	</div>
	<jsp:include page="/html/access_tracking.html" />
</body>
</html>