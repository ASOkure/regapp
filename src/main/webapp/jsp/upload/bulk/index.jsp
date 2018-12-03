<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Bulk Upload Records</h1>
<p>
  Sample file for bulk upload can be found at <a href="/doc/template/bulk_upload_sample.csv">here</a>.
</p>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="bulk_upload_form" action="upload" method="POST" enctype="multipart/form-data">
  <s:hidden name="mode" value="%{mode}"/>
  <s:file label="Source CSV File" name="upload"/>
  <s:submit/>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>