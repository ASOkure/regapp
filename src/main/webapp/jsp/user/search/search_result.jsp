<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Registered users search results</h1>

<div id="results_div">
  <table id="user_search_results_table" class="dataTable display" width="100%">
    <thead>
      <tr>
        <th id="fullname">Full Name</th>
        <th id="email">Email</th>
        <th id="country">Country</th>
        <th id="centre">Centre</th>
        <th id="interest">Area of Interest</th>
        <th id="detail">Details</th>
      </tr>
    </thead>
    <tbody>
    <s:iterator value="portalUsers">
      <tr>
        <td><s:property value="name"/></td>
        <td><s:property value="email"/></td>
        <td><s:property value="country"/></td>
        <td><s:property value="centre"/></td>
        <td>
          <s:iterator value="interest">
            <s:property/><br/>
          </s:iterator>
        </td>
        <td>
          <s:url id="url" value="user_detail.action">
            <s:param name="username" value="%{username}"/>
            <s:param name="mode" value="%{mode}"/>
          </s:url>
          <s:a name="%{username}" href="%{url}">
            Details
          </s:a>
        </td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>