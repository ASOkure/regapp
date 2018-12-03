<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>


<h1><s:property value="message"/></h1>


<a href="<%=path %>/admin/centre_management_view.action">Back</a>

<jsp:include page="/jsp/page/page_foot.jsp"/>