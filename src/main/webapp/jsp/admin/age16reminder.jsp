<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Reminder Emails are sent. </h1>

<p>Here is a summary of what has been sent to who.</p>

<div id="results_div">
  <table id="results_table" class="dataTable display">
    <thead>
    <tr>
      <th id="clinician">Clinician</th>
      <th id="email">Email</th>
      <th id="id">Patient IDs</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="reminderMap">
      <tr>
        <td><s:property value="key.getName()"/></td>
        <td><s:property value="key.getEmail()"/></td>
        <td><s:property value="value"/></td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>
