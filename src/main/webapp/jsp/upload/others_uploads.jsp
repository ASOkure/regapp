<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<div>
  <h1>Shared Research Results</h1>
  <a href="<%=path %>/jsp/upload/upload_home.jsp?mode=${param['mode']}">Back to Upload Home</a>

  <div id="others_upload_div">
    <table id="others_upload_table" class="dataTable display">
      <thead>
      <tr>
        <th id="fileId">Research File ID</th>
        <th id="registerId">Registry Record ID</th>
        <th id="category">Category</th>
        <th id="uploadTime">Upload Time</th>
        <th id="comment">Comment</th>
        <th id="access">Access List</th>
        <th id="fileType">File Type</th>
        <th id="file">Research Result File</th>
      </tr>
      </thead>
      <tbody>
      <s:iterator value="othersUploads" status="m">
        <tr>
          <td><s:property value="resultId"/></td>
          <td><s:property value="registerId"/></td>
          <td><s:property value="categoryName"/></td>
          <td><s:date name="uploadTime" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:property value="comment"/></td>
          <td>
            <s:iterator value="access">
              <s:property/><br/>
            </s:iterator>
          </td>
          <td><s:property value="contentType"/></td>
          <td>
            <s:url id="fileDownload" namespace="/" action="download">
              <s:param name="pdfId" value="%{resultId}"/>
            </s:url>
            <s:a href="%{fileDownload}"><s:property value="fileName"/></s:a>
          </td>
        </tr>
      </s:iterator>
      </tbody>
    </table>
  </div>

<jsp:include page="/jsp/page/page_foot.jsp"/>