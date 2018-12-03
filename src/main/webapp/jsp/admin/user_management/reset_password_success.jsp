<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h1>
  Password have been reset for user <s:property value="username"/>.
</h1>

<p>
  An email has been sent to this user with the new random password.
</p>

<jsp:include page="/jsp/page/page_foot.jsp"/>