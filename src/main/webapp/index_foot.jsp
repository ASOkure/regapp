<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<div id="page-foot" class="page-foot">
	<br /><hr />
	<div class="page-foot-left">
		<span id="date_time"></span>
		<script type="text/javascript">
			document.getElementById("date_time").innerText = Date();
		</script>
	</div>
	<div class="page-foot-middle">
		<span class="page-foot-text">Recommend browsers:</span>
		<img height="33" align="middle" alt="Google Chrome" src="<%=path %>/images/browsers/google-chrome.jpg" />
		<img height="36" align="middle" alt="Firefox" src="<%=path %>/images/browsers/firefox.jpg" />
		<img height="40" align="middle" alt="Safari" src="<%=path %>/images/browsers/safari.jpg" />
	</div>
	<div class="page-foot-right">
		<a href="http://www.nesc.ac.uk/hub/">
		    <img class="page-foot-image" src="<%=path %>/images/footer/nesc.png" alt="nesc_logo" />
		</a>
	</div>
</div>


