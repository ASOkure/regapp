<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:url id="url" value="../search/read.action">
  <s:param name="registerId" value="registerId"/>
  <s:param name="mode" value="mode"/>
</s:url>

<p>
  Record <s:property value="%{#parameters.registerId}"/> has been updated successfully.
  To view the record you just uploaded, please click
  <s:a namespace="search" name="read" href="%{url}" theme="simple">here</s:a>.
</p>

<jsp:include page="/jsp/page/page_foot.jsp"/>