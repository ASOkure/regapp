<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="delete_country_form" action="/admin/delete_country.action">
  <h1>Are you sure to delete the country "<s:property value="%{#parameters.countryName}"/>" ? </h1>

  <s:hidden name="countryId" value="%{#parameters.countryId}"></s:hidden>
  <s:hidden name="countryName" value="%{#parameters.countryName}"></s:hidden>

  <br/>

  <s:submit value="Confirm"></s:submit>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>