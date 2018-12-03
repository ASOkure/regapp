<%@ page language="java" import="uk.ac.nesc.idsd.security.Authorization" pageEncoding="UTF-8" %>
<%@page import="uk.ac.nesc.idsd.security.spring.SecurityUserHolder" %>
<%@page import="uk.ac.nesc.idsd.security.spring.User" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  User user = SecurityUserHolder.getCurrentUser();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<br/><br/>

<h1>Please select the following options to upload your research result file</h1>

<div>
  <%
    if (Authorization.authorizeFileUpload(user)) {
  %>
  <s:url id="upload_file_view" value="%{path}/upload/uploadResultPDFView">
    <s:param name="mode" value="%{#parameters.mode}"/>
  </s:url>
  <p>
    To upload your research result PDF file, please <s:a name="upload_file_view" href="%{upload_file_view}">click here</s:a>.
  </p>
  <s:url id="view_my_uploads" value="%{path}/upload/viewMyUploads">
    <s:param name="mode" value="%{#parameters.mode}"/>
  </s:url>
  <p>
    To view PDF files you uploaded, please <s:a name="view_my_uploads" href="%{view_my_uploads}">click here</s:a>.
  </p>
  <%} %>
  <p>
    <s:url id="view_others" value="%{path}/upload/viewOthersResultPDF">
      <s:param name="mode" value="%{#parameters.mode}"/>
    </s:url>
    To view PDF files shared by others, please <s:a name="view_others" href="%{view_others}">click here</s:a>.
  </p>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>