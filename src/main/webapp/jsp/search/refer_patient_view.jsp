<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<div id="patient_access_div">
  <h1>This is the exact view that the participant will see for Record ID
    <s:property value="registerId"/> should you approve this access</h1><br/>
  <div id="page-left">
    <h1>Participant Details</h1>
    <s:form id="refer_patient_form" action="refer_patient" theme="simple" cssClass='required-validate'>
      <s:hidden name="registerId" value="%{registerId}"/>
      <s:hidden name="mode" value="%{mode}"/>
      <table class="participant_table">
        <tbody id="participant_table_tbody">
          <s:iterator value="parameters" id="para" status="paras">
            <tr id="participant_table_tr_${paras.index}" class="<s:if test="#paras.odd == true">parameter-line-double</s:if><s:else>parameter-line-single</s:else>">
              <td id="participant_table_td_label_${paras.index}" class="participant_table_td">
                <s:label value="%{label}"/>
                <s:if test="paramId == 12"><span><small> (gram)</small></span></s:if>
                <s:if test="paramId == 11"><span><small> (gram)</small></span></s:if>
              <!--    <s:if test="paramId == 13"><span><small> (cm)</small></span></s:if>  -->
                <s:if test="paramId == 25"><span><small> (mm)</small></span></s:if>
              </td>
              <td id="participant_table_td_value_${paras.index}" class="participant_table_td">
                <s:if test="type == 'double_menu'">
                  <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/><br/>
                  <s:textfield name="%{'dsdIdentifier.' + menu.tertiaryParamName}" readonly="true" theme="simple" cssClass="display_only"/>
                </s:if>
                <s:else>
                  <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                </s:else>
              </td>
            </tr>
          </s:iterator>
          <tr>
            <td colspan="2">
              To provide participant access to this record, please provide the participant’s email address:
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <s:textfield name="patientEmail" id="patientEmail" size="50" cssClass="required validate-email"/>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <s:textfield name="patientEmailRepeat" id="patientEmailRepeat" size="50" cssClass="required validate-email equals-patientEmail"/>
            </td>
          </tr>
          <tr>
            <td colspan="3" align="right">
              <s:submit value="Provide Participant Access" theme="simple"/>
              <s:submit value="Cancel" theme="simple"/>
            </td>
          </tr>
        </tbody>
      </table>
    </s:form>
  </div>
  <div id="page-right">
    <table>
      <tr>
        <td colspan="2">
          <h1>Centre</h1>
          <s:if test="centreInfo.centreLead">
            <s:url id="editURL" value="../search/view_centre.action">
              <s:param name="registerId" value="registerId"/>
              <s:param name="mode" value="%{#parameters.mode}"/>
            </s:url>
            <s:a name="%{'edit_centre_' + registerId}" href="%{editURL}" theme="simple">
              Edit Centre Description
            </s:a>
          </s:if>
        </td>
      </tr>
      <tr>
        <td>Institute: <s:property value="centreInfo.name"/> </td>
        <td><s:property value="centreInfo.name"/></td>
      </tr>
      <tr>
        <td>Address:</td>
        <td><s:property value="centreInfo.address"/></td>
      </tr>
      <tr>
        <td style="text-decoration: underline;" colspan="2"><h2>Centre Lead Clinician</h2></td>
      </tr>
      <tr>
        <td>Name:</td>
        <td><s:property value="centreInfo.leadName"/></td>
      </tr>
      <tr>
        <td>Telephone number:</td>
        <td><s:property value="centreInfo.leadTel"/></td>
      </tr>
      <tr>
        <td>E-mail Address:</td>
        <td>
          <a target="_blank" href="mailto:<s:property value="centreInfo.leadEmail"/>"><s:property value="centreInfo.leadEmail"/></a>
        </td>
      </tr>
      <tr>
        <td>URL:</td>
        <td><a target="_blank" href="<s:property value="centreInfo.leadUrl"/>"><s:property value="centreInfo.leadUrl"/></a></td>
      </tr>
      <tr>
        <td style="text-decoration: underline;" colspan="2"><h2>Centre Information</h2></td>
      </tr>
      <tr>
        <td colspan="2"><s:property value="centreInfo.centreSpiel" escape="false"/></td>
      </tr>
      <tr>
        <td style="text-decoration: underline;" colspan="2"><h2>Clinics dates/time</h2></td>
      </tr>
      <tr>
        <td colspan="2"><s:property value="centreInfo.clinicDates" escape="false"/></td>
      </tr>
      <tr>
        <td style="text-decoration: underline;" colspan="2"><h2>Local Resources For Patients/Parents</h2></td>
      </tr>
      <tr>
        <td colspan="2"><s:property value="centreInfo.localResources" escape="false"/></td>
      </tr>
      <tr>
        <td style="text-decoration: underline;" colspan="2"><h2>Local Education Events For patients/Parents</h2></td>
      </tr>
      <tr>
        <td colspan="2"><s:property value="centreInfo.localEvents" escape="false"/></td>
      </tr>
      <tr>
        <td style="text-decoration: underline;" colspan="2"><h2>Active & Recently Completed Studies</h2></td>
      </tr>
      <tr>
        <td colspan="2"><s:property value="centreInfo.studies" escape="false"/></td>
      </tr>
      <tr>
        <td style="text-decoration: underline;" colspan="2"><h2>Further Information</h2></td>
      </tr>
      <tr>
        <td colspan="2"><s:property value="centreInfo.furtherInfo" escape="false"/></td>
      </tr>
    </table>
  </div>
</div>

<br/>

<jsp:include page="/jsp/page/page_foot.jsp"/>