<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Upload Record Successful!</h1>

<p>
  The record has been successfully added to the registry database with register ID =
  <b><s:property value="%{#parameters.registerId}"/></b>.
</p>

<p>
  Please <u>take a note</u> of this register ID for future reference to this case!
</p>

<s:url id="url" value="/search/read.action">
  <s:param name="registerId" value="registerId"/>
  <s:param name="mode" value="%{#parameters.mode}"/>
</s:url>
<p>
  To view the record you just uploaded, please click
  <s:a name="view_upload_record" href="%{url}" theme="simple">here</s:a>.
</p>

<jsp:include page="/jsp/page/page_foot.jsp"/>