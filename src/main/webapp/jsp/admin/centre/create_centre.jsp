<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>


<h1>Add A New Centre for <s:property value="%{#parameters.countryName}"/></h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="centre_create_form" action="/admin/create_centre" cssClass='required-validate'>
  <s:textfield label="Centre Name" name="centreName" size="40" required="true"/>

  <s:hidden name="countryId" value="%{#parameters.countryId}"></s:hidden>

  <s:submit value="Create Centre"></s:submit>
</s:form>


<jsp:include page="/jsp/page/page_foot.jsp"/>