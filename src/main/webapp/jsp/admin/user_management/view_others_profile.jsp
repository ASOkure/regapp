<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>
  Change Your User Account Profile
</h1>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h5>Please note:</h5>
<ul>
  <li>
    Telephone number should be in format of +44 141 330 2000 or +44-141-330-2000 or +441413302000
  </li>
</ul>
<s:form action="/admin/edit_others_profile" cssStyle="margin-top: 30px;" theme="xhtml" id="edit_profile_form">
  <s:hidden name="mode" value="%{#parameters.mode[0]}"/>
  <s:textfield label="User Name" name="portalUser.username" size="30" readonly="true" cssClass="display_only"/>

  <tr>
    <td><h3>Current Country & Centre</h3></td>
  </tr>
  <tr>
    <td>Country:</td>
    <td><h4><s:property value="portalUser.country"/></h4></td>
  </tr>
  <tr>
    <td>Centre:</td>
    <td><h4><s:property value="portalUser.centre"/></h4></td>
  </tr>

  <tr>
    <td><h3>Setting Country & Centre to: </h3></td>
  </tr>

  <s:doubleselect label="Country & Centre" name="portalUser.country"
                  headerKey="" headerValue="--- Please Select ---"
                  list="countries" listKey="countryName" listValue="countryName"
                  doubleId="%{'search_user_' + countryId}"
                  doubleName="portalUser.centre"
                  doubleHeaderKey="" doubleHeaderValue="--- Please Select ---"
                  doubleList="centres" doubleListKey="centreName" doubleListValue="centreName"
                  cssClass='validate-selection' theme="idsd_xhtml"/>

  <tr>
    <td><h3>Other info of this user:</h3></td>
  </tr>

  <s:textfield label="Full Name" name="portalUser.name" size="30"/>
  <s:textarea label="Full Institute Postal Address" name="portalUser.address" cssClass="free-text-area"/>
  <s:textfield label="Telephone" name="portalUser.tel" size="50"/>
  <s:textfield label="Fax" name="portalUser.fax" size="50"/>
  <s:textfield label="Email" name="portalUser.email" size="50"/>
  <s:textfield label="On-line profile" name="portalUser.profileUrl" size="80" title="Link to CV or group profile" data-validation="url" data-validation-optional="true" data-validation-help="Link to CV or group profile"/>

  <s:textfield label="Position" name="portalUser.position" size="50"/>
  <s:textfield label="Professional Society membership" name="portalUser.society" size="100"/>

  <tr>
    <td><h3>Professional Expertise (Optional)</h3></td>
  </tr>

  <s:select label="Primary Role"
            name="portalUser.primaryRole"
            headerKey="" headerValue="--- Select Your Primary Role ---"
            list="#{
                       'Clinician':'Clinician',
                       'Scientist':'Scientist',
                       'Clinical Investigator':'Clinical Investigator'
                       }"
            cssClass="validate-selection"/>
  <s:checkboxlist label="Secondary Roles" name="portalUser.secondaryRoles"
                  listKey="value" listValue="value"
                  list="#{
                            'Biochemical Evaluation':'Biochemical Evaluation',
                            'Genetic Evaluation':'Genetic Evaluation',
                            'Clinical Evaluation':'Clinical Evaluation',
                            'Medical Care':'Medical Care',
                            'Surgical Care':'Surgical Care',
                            'Psychological Care':'Psychological Care',
                            'Genetic Counselling':'Genetic Counselling'
                           }" value="secondaryRoles"
                  cssStyle="vertical" theme="idsd_xhtml"/>
  <!--
  <s:textfield label="Primary Role" name="portalUser.primaryRole" size="100"/>

  <s:textfield label="Secondary Roles" name="portalUser.secondaryRoles" size="100"/>
  -->

  <s:textarea label="Other Secondary Roles" name="portalUser.secondaryRolesOther" cssClass="free-text-area"/>

  <s:checkboxlist label="Areas of Interest" name="portalUser.interest"
                  listKey="value" listValue="value"
                  list="#{
                            'Gonadal Development':'Gonadal Development',
                            'Androgen Synthesis':'Androgen Synthesis',
                            'Androgen Action':'Androgen Action',
                            'Androgen Excess':'Androgen Excess',
                            'Mullerian Development':'Mullerian Development'
                            }" value="interests"
                  cssStyle="vertical" theme="idsd_xhtml"/>
  <!--
  <s:textfield label="Areas of Interest" name="portalUser.interest" size="100"/>
  -->
  <s:textarea label="Other Areas of Interest" name="portalUser.interestOther" cssClass="free-text-area"/>
  <s:textarea label="Special Interest" name="portalUser.specialInterest" cssClass="free-text-area"/>

  <s:radio label="Searchable by Others?" name="portalUser.searchable" list="#{'true':'Yes','false':'No'}" value="portalUser.searchable"/>


  <s:submit value="Save User Account Profile"/>
</s:form>
<script language="javascript" type="text/javascript">
  new Validation("edit_profile_form", {immediate: true});
</script>


<jsp:include page="/jsp/page/page_foot.jsp"/>