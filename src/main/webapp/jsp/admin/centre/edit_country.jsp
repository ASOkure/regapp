<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Edit Country</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="edit_country_form" action="/admin/edit_country.action">
  <s:textfield name="countryName" label="Country" value="%{#parameters.countryName}" size="40"></s:textfield>
  <s:hidden name="countryId" value="%{#parameters.countryId}"></s:hidden>
  <s:submit value="Save Change"/>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>