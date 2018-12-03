<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Add A New Country</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="country_create_form" action="/admin/create_country" cssClass='required-validate'>
  <s:textfield label="Country" name="countryName" size="40" required="true"/>
  <s:submit value="Create Country"></s:submit>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>