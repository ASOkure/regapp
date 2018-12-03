<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Add A New Country</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="create_country_form">
  <s:label value="Country"/>
  <s:textfield name="countryName" value="%{country.value}"></s:textfield>
  <s:submit value="Edit Country" action="create_country.action"></s:submit>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>