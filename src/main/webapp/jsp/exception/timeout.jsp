<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
%>
<jsp:include page="/jsp/page/page_head.jsp"/>

<div>
  <h3>
    Session Expired
  </h3>

  <p>
    Session expires due to inactivity. Please click
    <a href="<%=path %>/login.jsp">here</a> to reactivate.
  </p>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>