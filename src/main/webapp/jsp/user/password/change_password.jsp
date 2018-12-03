<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Change Password</h1>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form action="/user/account/change_password.action" cssStyle="margin-top: 30px;" theme="xhtml" id="change_password_form">
  <s:hidden name="mode" value="%{#parameters.mode[0]}"/>
  <s:password label="Old Password" name="oldPassword" size="30" required="true" data-validation="required"/>
  <s:password label="New Password" name="password" id="password" size="30" required="true"
              data-validation="required strength" data-validation-error-msg="Invalid password"
              data-validation-length="6-30"
              data-validation-help="Password length must be between 6 and 30 and strength level not at Very Weak"
              data-validation-strength="1" type="password"/>
  <s:password label="Repeat New Password" name="repassword" id="repassword" size="30" required="true"
              data-validation="required strength confirmation" data-validation-error-msg="Invalid password"
              data-validation-length="6-30" data-validation-strength="1" data-validation-confirm="password"
              type="password"/>
  <s:submit value="Change Password"/>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>