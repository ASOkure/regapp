<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<br/>

<h1>Search the user or <a href="view_all_users">View All</a></h1>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form action="/admin/search_user" cssStyle="margin-top: 30px;" theme="xhtml" id="search_user_form">
  <s:hidden name="mode" value="%{#parameters.mode[0]}"/>
  <s:textfield label="User Name" name="portalUser.username" size="30"/>
  <s:textfield label="Full Name" name="portalUser.name" size="30"/>
  <s:doubleselect label="Country & Centre" name="portalUser.country"
                  headerKey="" headerValue="--- Please Select ---"
                  list="countries" listKey="countryName" listValue="countryName"
                  doubleId="%{'search_user_' + countryId}"
                  doubleName="portalUser.centre"
                  doubleHeaderKey="" doubleHeaderValue="--- Please Select ---"
                  doubleList="centres" doubleListKey="centreName" doubleListValue="centreName"
                  cssClass='validate-selection'/>
  <!--
  <s:textfield label="Country" name="portalUser.country" size="30"/>
  <s:textfield label="Centre" name="portalUser.centre" size="50"/>
  <s:textarea label="Full Institute Postal Address" name="portalUser.address" cssClass="free-text-area"/>
  -->
  <s:textfield label="Email" name="portalUser.email" size="50"/>
  <!--
  <s:textfield label="Position" name="portalUser.position" size="50"/>


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
  <s:textfield label="Professional Society membership" name="portalUser.society" size="100"/>
  -->

  <!--
  <s:textarea label="Other Secondary Roles" name="portalUser.secondaryRolesOther" cssClass="free-text-area"/>
  -->
  <!--
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
  -->
  <!--
  <s:textarea label="Other Areas of Interest" name="portalUser.interestOther" cssClass="free-text-area"/>
  <s:textarea label="Special Interest" name="portalUser.specialInterest" cssClass="free-text-area"/>
  -->
  <!--
  <s:radio label="Searchable by Others?" name="portalUser.searchable" list="#{'true':'Yes','false':'No'}"/>
  -->
  <s:submit value="Search Users"/>
</s:form>
<script language="javascript" type="text/javascript">
  new Validation("edit_profile_form", {immediate: true});
</script>

<jsp:include page="/jsp/page/page_foot.jsp"/>