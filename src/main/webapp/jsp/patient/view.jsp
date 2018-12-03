<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <jsp:include page="/index_head.jsp"/>

  <body>
    <div id='body' class='body'>
      <div class='left_body'>
        <jsp:include page="banner.jsp"/>
        <s:actionerror cssClass="error_message"/>
        <s:fielderror cssClass="error_message"/>

        <div id="patient_access_div">
          <div id="page-left">
            <h1>Participant Details</h1>
            <table class="participant_table">
              <s:iterator value="parameters" id="para" status="paras">
                <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
                <s:else><tr class="parameter-line-single"></s:else>
                <td class="participant_table_td">
                  <b><s:label value="%{label}" theme="simple"/></b>
                  <s:if test="paramId == 12"><span><small> (gram)</small></span></s:if>
                  <s:if test="paramId == 13"><span><small> (cm)</small></span></s:if>
                  <s:if test="paramId == 25"><span><small> (mm)</small></span></s:if>
                </td>
                <td style="background: transparent" class="participant_table_td">
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
            </table>
          </div>
          <div id="page-right">
            <table>
              <tr>
                <td colspan="2"><h1>Centre</h1></td>
              </tr>
              <tr>
                <td>Institute: </td>
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
                <td><a target="_blank" href="mailto:<s:property value="centreInfo.leadEmail"/>"><s:property value="centreInfo.leadEmail"/></a></td>
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
                <td style="text-decoration: underline;" colspan="2"><h2>Local Education Events For patients/Parents</h2>
                </td>
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