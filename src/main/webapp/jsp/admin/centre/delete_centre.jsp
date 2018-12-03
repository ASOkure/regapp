<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="delete_centre_form" action="/admin/delete_centre.action">
  <h1>Are you sure to delete the centre "<s:property value="%{#parameters.centreName}"/>" ? </h1>

  <s:hidden name="centreId" value="%{#parameters.centreId}"></s:hidden>
  <s:hidden name="centreName" value="%{#parameters.centreName}"></s:hidden>

  <br/>

  <s:submit value="Confirm"></s:submit>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>