<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>
  Roles have been updated for user <s:property value="user.username"/>
</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h3>New set of roles are: </h3>

<s:iterator value="%{user.userRoles}">
  <p>
    <s:property value="role.roleName"/>
    <s:if test="validateFrom != null && validateTo != null">
      (Valid from <s:date name="validateFrom" format="yyyy-MMM-dd"/> to
      <s:date name="validateTo" format="yyyy-MMM-dd"/>)
    </s:if>
  </p>
</s:iterator>
<br/>

<s:url id="url" value="pick_user.action">
  <s:param name="username" value="%{username}"/>
  <s:param name="mode" value="%{#parameters.mode}"/>
</s:url>
<s:a name="%{'pick_user_' + username}" href="%{url}">
  Back to this user
</s:a>
|
<s:url id="url_profile" value="edit_others_profile_view.action">
  <s:param name="username" value="%{username}"/>
  <s:param name="mode" value="%{#parameters.mode}"/>
</s:url>
<s:a name="%{'edit_profile_.' + username}" href="%{url_profile}">
  Edit User Profile
</s:a>
|
<s:url id="url_back_to_search" value="approve_user_view.action">
  <s:param name="mode" value="%{#parameters.mode}"/>
</s:url>
<s:a name="%{'back_to_search_' + username}" href="%{url_back_to_search}">
  Search Other Users
</s:a>
|
<s:url id="url_view_all" value="view_all_users.action">
  <s:param name="mode" value="%{#parameters.mode}"/>
</s:url>
<s:a name="%{'view_all_' + username}" href="%{url_view_all}">
  View All Users
</s:a>

<jsp:include page="/jsp/page/page_foot.jsp"/>