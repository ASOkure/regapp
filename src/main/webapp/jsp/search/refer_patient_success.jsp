<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<br/><br/>

<div id="grant_patient_access_success">
  <h1>
    Record <s:property value="%{#parameters.registerId}"/>
    has been provided patient access for
    <s:property value="%{#parameters.patientEmail}"/>.
  </h1>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>