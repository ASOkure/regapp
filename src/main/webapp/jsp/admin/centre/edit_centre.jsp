<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Edit Centre</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="edit_centre_form" action="/admin/edit_centre.action">
  <s:hidden name="centreId" value="%{centre.centreId}"/>

  Editing Centre ID: <s:property value="centre.centreId"/>
  <s:textfield name="centre.centreName" label="Centre Name" size="40"/>
  <s:textarea name="centre.address" label="Address" size="40"/>
  <s:textarea name="centre.description" label="Description" size="40"/>
  <s:textarea name="centre.clinicsDates" label="Clinics Date"/>
  <s:textarea name="centre.localResources" label="Local Resources"/>
  <s:textarea name="centre.localEvents" label="Local Events"/>
  <s:textarea name="centre.activeStudies" label="Active Studies"/>
  <s:textarea name="centre.additionalInfo" label="Additional Info"/>

  <s:submit value="Save Change"/>
</s:form>

<script language="javascript" type="text/javascript">
  //  jQuery(function ($) {
  //    $('.jqte-area').jqte();
  //  //  $("textarea").jqte();
  //
  //    // settings of status
  //    var jqteStatus = true;
  //    $(".status").click(function()
  //    {
  //      jqteStatus = jqteStatus ? false : true;
  //      $('.jqte-area').jqte({"status" : jqteStatus})
  //    });
  //  });

  CKEDITOR.replace('edit_centre_form_centre_address');
  CKEDITOR.replace('edit_centre_form_centre_description');
  CKEDITOR.replace('edit_centre_form_centre_clinicsDates');
  CKEDITOR.replace('edit_centre_form_centre_localResources');
  CKEDITOR.replace('edit_centre_form_centre_localEvents');
  CKEDITOR.replace('edit_centre_form_centre_activeStudies');
  CKEDITOR.replace('edit_centre_form_centre_additionalInfo');

</script>

<jsp:include page="/jsp/page/page_foot.jsp"/>