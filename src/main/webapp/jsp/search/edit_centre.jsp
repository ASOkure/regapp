<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Edit <s:property value="centre.centreName"/> Centre Information</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="edit_centre_form" action="/search/save_centre.action">
  <s:hidden name="centre.centreId" value="%{centre.centreId}"/>
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>

  <s:textfield label="Centre Name" name="portalUser.centre" size="30" readonly="true" cssClass="display_only"/>
  <s:textfield label="Centre Lead Name" name="portalUser.username" size="30" readonly="true" cssClass="display_only"/>
  <s:textarea label="Full Institute Postal Address" name="portalUser.address" cssClass="free-text-area required" required="true"/>
  <s:textfield label="Telephone" name="portalUser.tel" size="50" required="true" cssClass="required validate-phone"/>
  <!-- <s:textfield label="Fax" name="portalUser.fax" size="50" required="true" cssClass="required validate-phone"/> -->
  <s:textfield label="Email" name="portalUser.email" size="50" required="true" cssClass="required"/>
  <s:textfield label="On-line profile" name="portalUser.profileUrl" size="80" title="Link to CV or group profile" data-validation="url" data-validation-optional="true" data-validation-help="Link to CV or group profile"/>

  <%--<s:textarea name="centre.address" label="Address" size="40"/>--%>
  <s:textarea name="centre.description" label="Description" cssClass="jqte-area" size="40"/>
  <s:textarea name="centre.clinicsDates" label="Clinics Date" cssClass="jqte-area"/>
  <s:textarea name="centre.localResources" label="Local Resources" cssClass="jqte-area"/>
  <s:textarea name="centre.localEvents" label="Local Events" cssClass="jqte-area"/>
  <s:textarea name="centre.activeStudies" label="Active Studies" cssClass="jqte-area"/>
  <s:textarea name="centre.additionalInfo" label="Additional Info" cssClass="jqte-area"/>

  <s:submit value="Save Change"/>
  <s:submit value="Cancel" action="refer_patient_view"/>
</s:form>

<script language="javascript" type="text/javascript">
  new Validation("edit_centre_form", {immediate: true});
</script>

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
  CKEDITOR.replace('edit_centre_form_centre_description');
  CKEDITOR.replace('edit_centre_form_centre_clinicsDates');
  CKEDITOR.replace('edit_centre_form_centre_localResources');
  CKEDITOR.replace('edit_centre_form_centre_localEvents');
  CKEDITOR.replace('edit_centre_form_centre_activeStudies');
  CKEDITOR.replace('edit_centre_form_centre_additionalInfo');

</script>

<jsp:include page="/jsp/page/page_foot.jsp"/>