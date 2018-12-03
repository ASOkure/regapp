<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h1>
  User profile has been successfully updated for <i><s:property value="portalUser.name"/></i>.
</h1>

<a href="<%=path%>/admin/approve_user_view.action?mode=${param['mode']}">Back to User Management</a>

<jsp:include page="/jsp/page/page_foot.jsp"/>