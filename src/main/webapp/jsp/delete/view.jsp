<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<div id="delete_view_div">
  <h2>Delete Record</h2>

  <p>
    To delete a record from the DSD database, enter the unique register ID of the record into the box below.
  </p>

  <p>
    <b>Please note that delete operation can not be reversed.</b>
  </p>

  <p>
    Upon button click, the record details will be shown and confirmation of the deletion will be required.
  </p>

  <s:actionerror cssClass="error_message"/>
  <s:fielderror cssClass="error_message"/>

  <s:form id="delete_form" action="/delete/delete" theme="simple">
    <input type="hidden" name="mode" value="${param['mode']}"/>
    <s:textfield id="del_record" name="registerId" theme="simple" cssClass="required validate-digits"/>
    <br/><br/>
    <s:submit value="Delete" theme="simple"></s:submit>
    <s:submit value="Cancel" action="cancel" theme="simple"></s:submit>
  </s:form>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>