<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<div>
  <br/><br/>

  <h2>Your file upload was successful</h2>
  <s:url id="view_my_uploads" value="%{path}/upload/viewMyUploads">
    <s:param name="mode" value="%{#parameters.mode}"/>
  </s:url>
  <p>
    <s:a name="view_my_uploads" href="%{view_my_uploads}">My uploaded Research Results</s:a>
  </p>
  <s:url id="view_others" value="%{path}/upload/viewOthersResultPDF">
    <s:param name="mode" value="%{#parameters.mode}"/>
  </s:url>
  <p>
    <s:a name="view_others" href="%{view_others}">Research Results from other users</s:a>
  </p>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>