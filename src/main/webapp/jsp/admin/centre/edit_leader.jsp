<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Edit Centre Leader for "<s:property value="%{#parameters.centreName}"/>"</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="edit_leader_form" action="/admin/edit_leader" theme="simple">
  <div id="results_div">
    <table id="edit_leader_table" class="dataTable display" width="100%">
      <thead>
        <tr>
          <th id="button">Pick</th>
          <th id="username">Username</th>
          <th id="name">Name</th>
          <th id="centre">Centre</th>
          <th id="contact">Contact</th>
        </tr>
      </thead>
      <tbody>
        <s:iterator value="portalUsers" var="portalUser">
          <tr>
            <td>
              <input type="radio" name="username" id="leader_<s:property value="username" />" value='<s:property value="username" />'
                  <s:if test="username == currentLeaderUsername"> checked="true" </s:if> />
            </td>
            <td>
              <label for="leader_<s:property value="username" />"><s:property value="username"/></label>
            </td>
            <td>
              <label for="leader_<s:property value="username" />"><s:property value="name"/></label>
            </td>
            <td>
              <label for="leader_<s:property value="username" />"><s:property value="centre"/></label>
            </td>
            <td>
              <label for="leader_<s:property value="username" />"><s:property value="email"/></label>
            </td>
          </tr>
        </s:iterator>
      </tbody>
    </table>
  </div>
  <s:submit theme="simple" value="Save Change"/>
  <s:hidden name="centreId" value="%{#parameters.centreId}"></s:hidden>
  <s:hidden name="centreName" value="%{#parameters.centreName}"></s:hidden>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>