<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Change Your User Account Profile</h1>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h5>Please note:</h5>
<ul>
  <li>
    Telephone number should be in format of +44 141 330 2000 or +44-141-330-2000 or +441413302000
  </li>
</ul>

<s:form action="/user/account/edit_profile" cssStyle="margin-top: 30px;" theme="xhtml" id="edit_profile_form">
  <s:hidden name="mode" value="%{#parameters.mode[0]}"/>
  <s:textfield label="User Name" name="portalUser.username" size="50" readonly="true" cssClass="display_only"/>
  <s:textfield label="Full Name" name="portalUser.name" size="50" required="true" cssClass="required"/>
  <s:textfield label="Country" name="portalUser.country" size="50" readonly="true" cssClass="display_only"/>
  <s:textfield label="Centre" name="portalUser.centre" size="50" readonly="true" cssClass="display_only"/>
  <s:textarea label="Full Institute Postal Address" name="portalUser.address" cssClass="free-text-area required" required="true"/>
  <s:textfield label="Telephone" name="portalUser.tel" size="50" required="true" cssClass="required validate-phone"/>
  <!-- <s:textfield label="Fax" name="portalUser.fax" size="50" required="true" cssClass="required validate-phone"/> -->
  <s:textfield label="Email" name="portalUser.email" size="50" required="true" cssClass="required"/>
  <s:textfield label="On-line profile" name="portalUser.profileUrl" size="80" title="Link to CV or group profile" data-validation="url" data-validation-optional="true" data-validation-help="Link to CV or group profile"/>

  <s:textfield label="Position" name="portalUser.position" size="50" required="true" cssClass="required"/>
  <s:textfield label="Professional Society membership" name="portalUser.society" size="100" required="true" cssClass="required"/>

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
  <s:textarea label="Other Areas of Interest" name="portalUser.interestOther" cssClass="free-text-area"/>
  <s:textarea label="Special Interest" name="portalUser.specialInterest" cssClass="free-text-area"/>
  <s:radio label="Searchable by Others?" name="portalUser.searchable" list="#{'true':'Yes','false':'No'}" value="portalUser.searchable"/>

  <tr>
    <td colspan='2'><h2>Please re-enter your password to save new profile</h2></td>
  </tr>
  <s:password label="Password" name="password" size="30" required="true" cssClass="required"/>

  <s:submit value="Edit My Account Profile"/>
</s:form>

<script language="javascript" type="text/javascript">
  new Validation("edit_profile_form", {immediate: true});
</script>

<jsp:include page="/jsp/page/page_foot.jsp"/>