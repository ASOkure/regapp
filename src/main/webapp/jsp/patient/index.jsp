<%@ page language="java" import="uk.ac.nesc.idsd.model.PatientUser, uk.ac.nesc.idsd.security.spring.SecurityUserHolder" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
  PatientUser user = SecurityUserHolder.getCurrentPatientUser();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/index_head.jsp"/>
<body>
<div id='body' class='body'>
  <div class='left_body'>
    <div class="banner">
      <% if ("icah".equalsIgnoreCase(request.getParameter("mode"))) { %>
      <div class="logo">
        <a href="<%=path %>">
          <img src="<%=path %>/images/index/icah_logo.jpg" height="140px" align="left" alt="idsd_logo"/>
        </a>
        <br/><br/>

        <div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left; '>Welcome to the I-CAH Registry</div>
      </div>
      <%} else { %>
      <div class="logo">
        <a href="<%=path %>">
          <img src="<%=path %>/images/index/idsd.jpg" height="140px" align="left" alt="idsd_logo"/>
        </a>
        <br/><br/>

        <div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left;'>Welcome to the I-DSD Registry</div>
      </div>
      <%} %>
      <div id="login" class='login'>
        <ul>
          <%if (null == user) { %>
          <li><span> <a href="<%=path%>/patient/view.action">Login</a> </span></li>
          <%} else { %>
          <li><span> <a href="<%=path%>/patient/view.action">View Record</a> </span></li>
          <li><span> <a href="<%=path%>/logout.action?redirect=/jsp/patient/index.jsp">Logout</a> </span></li>
          <%} %>
          <li>
            <span> <a href="http://www.gla.ac.uk/idsd">Further Information</a> </span>
          </li>
        </ul>
      </div>
    </div>
    <br/>

    <div class="indexBody">
      <jsp:include page="/html/idsd_index.jsp"/>
    </div>

    <jsp:include page="/index_foot.jsp"/>
  </div>
  <!-- enf of <div class='left_body'> -->

  <jsp:include page="/index_right.jsp"/>
</div>
<!-- enf of <div id='body' class='body'> -->

<jsp:include page="/html/access_tracking.html"/>
</body>
</html>