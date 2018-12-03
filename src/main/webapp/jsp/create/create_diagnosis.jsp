<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  YUI().use("node", function (Y) {
    Y.on("domready", create_diagnosis_init);
  });
</script>

<div id="create-page-div">
  <h1>Upload Record to Database - Clinical History & Biomaterials</h1>

  <s:if test="registerId != null">
    <h2>Register ID: <s:property value="registerId"/></h2>
  </s:if>

  <s:include value="tabs.jsp"/>

  <s:actionerror cssClass="error_message"/>
  <s:fielderror cssClass="error_message"/>

  <s:form id="upload_diagnosis" action="save_clinical" theme="simple">
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <table class="query-page-table">
    <tr>
      <td colspan="3" class="button_td">
        <button type="submit" value="next" name="buttonName">Save Clinical History & Biomaterials</button>
      </td>
    </tr>
    <tr>
      <s:iterator value="sections" status="secs">
      <s:if test="sectionId == 2">
      <td class="query-page-table-td-left"></s:if>
        <s:if test="sectionId == 5"></td>
      <td class="query-page-table-td-right"></s:if>
        <s:if test="sectionId == 3 || sectionId == 11">
        <table class="section-table section-table-bottom"></s:if>
          <s:else>
          <table class="section-table"></s:else>
            <tr>
              <td colspan="3"><h3><s:property value="name"/></h3></td>
            </tr>
            <s:iterator value="parameters" id="para" status="paras">
              <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
              <s:else><tr class="parameter-line-single"></s:else>
              <td class="parameter-label-add"><s:label value="%{label}"/></td>
              <td class="mandatory"><s:if test="mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
              <td id="<s:property value="%{'td_tooltip_' + paramId}" />" class="tooltip_td">
                <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]</div>
              </td>
              <td>
                <s:if test="type == 'double_menu'">
                  <s:if test="paramId == 2">
                    <s:doubleselect name="%{paramName}" headerKey="" headerValue="--- Please Select ---" list="countries" listKey="countryName" listValue="countryName" doubleId="%{'dsdIdentifier_' + menu.tertiaryParamName}" doubleName="%{'dsdIdentifier.' + menu.tertiaryParamName}" doubleHeaderKey="" doubleHeaderValue="--- Please Select ---" doubleList="centres" doubleListKey="centreName" doubleListValue="centreName" cssClass='validate-selection'/>
                  </s:if>
                  <s:else>
                    <s:doubleselect name="%{paramName}" headerKey="" headerValue="--- Please Select ---" list="menu.options" listKey="value" listValue="value" doubleId="%{'dsdIdentifier_' + menu.tertiaryParamName}" doubleName="%{'dsdIdentifier.' + menu.tertiaryParamName}" doubleHeaderKey="" doubleHeaderValue="--- Please Select ---" doubleList="tertiaries" doubleListKey="value" doubleListValue="value" cssClass='validate-selection'/>
                  </s:else>
                </s:if>
                <s:if test="type == 'string'">
                  <s:if test="mandatory == 1">
                    <s:textfield name="%{paramName}" value=""/>
                  </s:if>
                  <s:else>
                    <s:textfield name="%{paramName}" value=""/>
                  </s:else>
                </s:if>
                <s:if test="type == 'year'">
                  <s:textfield name="%{paramName}" cssClass='int-range-1900-9999'/>
                </s:if>
                <s:if test="type == 'menu'">
                  <s:if test="label == 'Age of First Presentation'">
                    <s:select name="%{paramName}" label="%{para.label}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---"/>
                  </s:if>
                  <s:else>
                    <s:select name="%{paramName}" label="%{para.label}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" cssClass='validate-selection'/>
                  </s:else>
                </s:if>
                <s:if test="type == 'auto_fill'">
                  <s:textfield name="%{paramName}" cssClass='auto-text' readonly="true"/>
                </s:if>

                <s:if test="type == 'float'">
                  <s:if test="paramId == 12">
                    <s:textfield name="%{paramName}" cssClass="float-range-0-10000"/>
                    <span><small>gram</small></span>
                  </s:if>
                  <s:if test="paramId == 13">
                    <s:textfield name="%{paramName}" cssClass="float-range-0-80"/>
                    <span><small>cm</small></span>
                  </s:if>
                </s:if>
                <s:if test="type == 'multiple'">
                  <s:checkboxlist name="%{paramName}" list="menu.options" listKey="value" listValue="value" value="assocMalformsList" theme="idsd_simple"/>
                </s:if>
                <s:if test="type == 'text_area'">
                  <s:textarea name="%{paramName}" cssClass="free-text-area max-length-10000"/>
                </s:if>
                <s:if test="type == 'radio'">
                  <s:radio name="%{paramName}" list="menu.options" listKey="value" listValue="value"></s:radio>
                </s:if>
              </td>
              </tr>
            </s:iterator>
          </table>
          </s:iterator></td></tr>
          <tr>
            <td colspan='3'>
              <s:checkbox name="sampleConsent"/>Consent has been obtained to share sample & perform studies as outlined in the EuroDSD programme (*)
            </td>
          </tr>
          <tr>
            <td colspan="3"><br/>
              <button type="submit" value="next" name="buttonName">Save Clinical History & Biomaterials</button>
            </td>
          </tr>
        </table>
        </s:form>
</div>

<div id="dialog_box_div"></div>

<jsp:include page="/jsp/page/page_foot.jsp"/>