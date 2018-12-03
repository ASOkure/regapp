<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h1>
  Are you sure you want to delete User "<s:property value="portalUser.username"/>"
</h1>

<s:form id="delete_user_form" action="delete_user_confirm" cssClass='required-validate'>
  <table style="font-size: 80%; border-collapse:collapse; border: 1px solid black;" border="1">
    <tr>
      <th>Username</th>
      <td><s:property value="%{portalUser.username}"/></td>
      <s:hidden name="username" value="%{username}"></s:hidden>
    </tr>
    <tr>
      <th>Full Name</th>
      <td><s:property value="%{portalUser.name}"/></td>
    </tr>
    <tr>
      <th>Country</th>
      <td><s:property value="%{portalUser.country}"/></td>
    </tr>
    <tr>
      <th>Centre</th>
      <td><s:property value="%{portalUser.centre}"/></td>
    </tr>
    <tr>
      <th>Email</th>
      <td><s:property value="%{portalUser.email}"/></td>
    </tr>
    <tr>
      <th>Tel</th>
      <td><s:property value="%{portalUser.tel}"/></td>
    </tr>
    <tr>
      <th>Registration Date</th>
      <td><s:date name="%{portalUser.registerTime}" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <th>Last Visit</th>
      <td><s:date name="%{portalUser.lastLogin}" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <th>Existing Roles</th>
      <td>
        <s:iterator value="%{portalUser.roles}">
          <s:property value="roleName"/><br/>
        </s:iterator>
      </td>
    </tr>
  </table>
  <br/>
  <br/>

  <s:submit theme="simple" value="Confirm to Delete User"/>
</s:form>


<jsp:include page="/jsp/page/page_foot.jsp"/>