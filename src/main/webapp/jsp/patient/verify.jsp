<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:include page="/index_head.jsp" />

<body>
<div id='body' class='body'>
  <div class='left_body'>
    <jsp:include page="/index_banner.jsp" />
    <s:actionerror cssClass="error_message" />

    <br/>

    <div id="activate_patient_user">
      <h1>Please set a password for your account</h1>

      <p>Your password must match the following criteria:</p>
      <ul>
        <li>A digit must occur at least once</li>
        <li>A lower case letter must occur at least once</li>
        <li>An upper case letter must occur at least once</li>
        <li>A special character must occur at least once, including @#$%^&+=</li>
        <li>No whitespace allowed in the entire string</li>
        <li>At least eight characters</li>
      </ul>

  	 <s:form action="activate"> 
        <s:password label="Password" name="password" size="30" required="true" cssClass="required validate-password"/>
        <s:password label="Re-enter Password" name="repassword" size="30" required="true" cssClass="required validate-password"/>
        <s:hidden name="u" value="%{#parameters.u[0]}"/>
        <s:submit value="Set Password"/>
      </s:form>
    </div>

<jsp:include page="/jsp/page/page_foot.jsp"/>