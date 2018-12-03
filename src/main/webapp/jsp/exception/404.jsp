<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/index_head.jsp"/>
<body>
<div id='body' class='body'>
  <div class='left_body'>
    <jsp:include page="/index_banner.jsp"/>
    <div class="index_menu">
      <div class="tab-pane">
        <ul>
          <li>
            <a href="<%=path%>/jsp/home.jsp">Home page</a>
          </li>
          <li>
            <a href="<%=path %>/j_spring_security_logout">Logout</a>
          </li>
        </ul>
      </div>
    </div>

    <br/><br/><br/><br/>

    <p style="text-align:center">
      The page you requested does not exist. You can visit the I-DSD home page
      <a href="<%=path%>/jsp/home.jsp">here</a>.
    </p>

    <jsp:include page="/index_foot.jsp"/>
  </div>
</div>
</body>
</html>

