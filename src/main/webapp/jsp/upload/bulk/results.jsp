<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h1>Upload is successful</h1>

<h3>Please save ID mappings for your upload</h3>

<div id="results_div">
  <table id="results_table" class="dataTable display">
    <thead>
      <tr>
        <th id="id">Register ID</th>
        <th id="local_id">Local ID</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="savedDsdCoreIdMap">
        <tr>
          <td><s:property value="key"/></td>
          <td><s:property value="value"/></td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>