<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Found current users in I-DSD registry</h1>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form theme="simple">
  <div id="results_div">
    <table id="results_table" class="dataTable display" width="100%">
      <thead>
      <tr>
        <th id="username">Username</th>
        <th id="name">Full Name</th>
        <th id="country">Country</th>
        <th id="centre">Centre</th>
        <th id="email">Email</th>
        <th id="regDate">Registration</th>
        <th id="lastVisit">Last Visit</th>
        <th id="lastPasswordChange">Last Password Change</th>
        <th id="counter">Login Counter</th>
        <th id="enabled">Enabled</th>
        <th id="roles">Roles</th>
        <th id="actions">Actions</th>
          <%--<th id="deleteUser">Delete User</th>--%>
          <%--<th id="resetPassword">Reset Password</th>--%>
          <%--<th id="editProfile">Edit Profile</th>--%>
          <%--<th id="activateUser">Activate / Deactivate User</th>--%>
        <!-- th id="deactivateUser">Deactivate User</th-->
      </tr>
      </thead>
      <tbody>
      <s:iterator value="portalUsers">
        <tr>
          <td><b><s:property value="username"/></b></td>
          <td><s:property value="name"/></td>
          <td><s:property value="country"/></td>
          <td><s:property value="centre"/></td>
          <td><s:property value="email"/></td>
          <td><s:date name="registerTime" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:date name="lastLogin" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:date name="lastPasswordChange" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:property value="loginCounter"/></td>
          <td><s:property value="enabled"/></td>
          <td>
            <s:iterator value="userRoles">
              <s:property value="role.roleName"/><br/>
            </s:iterator>
          </td>
          <td>
            <span style="white-space:nowrap;text-align:left;">
              <s:url id="url" value="pick_user.action">
                <s:param name="username" value="%{username}"/>
                <s:param name="mode" value="%{#parameters.mode}"/>
              </s:url>
              <s:a name="%{username}" href="%{url}">Change Roles</s:a>
            </span>
            <span style="white-space:nowrap;">
              <s:url id="url_del" value="delete_user_attempt.action">
                <s:param name="username" value="%{username}"/>
                <s:param name="mode" value="%{#parameters.mode}"/>
              </s:url>
              <s:a name="%{username}" href="%{url_del}">Delete</s:a>
            </span>
            <span style="white-space:nowrap;">
              <s:url id="url_reset" value="reset_password.action">
                <s:param name="username" value="%{username}"/>
                <s:param name="mode" value="%{#parameters.mode}"/>
              </s:url>
              <s:a name="%{username}" href="%{url_reset}">Reset Password</s:a>
            </span>
            <span style="white-space:nowrap;">
              <s:url id="url_profile" value="edit_others_profile_view.action">
                <s:param name="username" value="%{username}"/>
                <s:param name="mode" value="%{#parameters.mode}"/>
              </s:url>
              <s:a name="%{username}" href="%{url_profile}">Edit Profile</s:a>
            </span>
            <span style="white-space:nowrap;">
              <s:if test="enabled">
                <s:url id="url_deactivate" value="deactivate_user.action">
                  <s:param name="username" value="%{username}"/>
                  <s:param name="mode" value="%{#parameters.mode}"/>
                </s:url>
                <s:a name="%{username}" href="%{url_deactivate}">Deactivate User</s:a>
              </s:if>
              <s:else>
                <s:url id="url_activate" value="activate_user.action">
                  <s:param name="username" value="%{username}"/>
                  <s:param name="mode" value="%{#parameters.mode}"/>
                </s:url>
                <s:a name="%{username}" href="%{url_activate}">Activate User</s:a>
              </s:else>
            </span>
          </td>
        </tr>
      </s:iterator>
      </tbody>
    </table>
  </div>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>