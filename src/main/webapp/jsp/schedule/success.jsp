<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Reminder Emails are sent. </h1>

<p>
  Here is a summary of what has been sent to who.
</p>

<div id="results_div">
  <table id="results_table" class="dataTable display">
    <thead>
    <tr>
      <th id="username">Username</th>
      <th id="name">Name</th>
      <th id="email">Email</th>
      <th id="timestamp">Last password changed at</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="inactiveUsers">
      <tr>
        <td><s:property value="username"/></td>
        <td><s:property value="name"/></td>
        <td><s:property value="email"/></td>
        <td><s:property value="lastPasswordChange"/></td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>
