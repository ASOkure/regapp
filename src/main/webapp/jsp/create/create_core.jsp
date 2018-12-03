<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  <s:property value='leadersJS' escapeHtml='false' escapeJavaScript='false' />
  YUI().use("node", function (Y) {
    Y.on("domready", create_core_init);
  })
</script>

<div id="create-page-div">
  <h1>Upload Record to Database - Core Information & Diagnosis</h1>

  <s:if test="registerId != null">
    <h2>Register ID: <s:property value="registerId"/></h2>
  </s:if>

  <ul>
    <li class="page_tips_li">Fields marked with * are mandatory to fill in.</li>
    <li class="page_tips_li">Only Core Information & Diagnosis, is mandatory. Other sections are all optional.</li>
  </ul>

  <s:actionerror cssClass="error_message"/>
  <s:fielderror cssClass="error_message"/>

  <s:form id="upload_dsd_identifier" action="save_core" theme="simple" cssClass='required-validate' >
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <table class="query-page-table">
    <tr>
      <td colspan="3" class="button_td">
        <button type="submit" value="next" name="buttonName">Save Core Info & Diagnosis</button>
        <br/>
      </td>
    </tr>
    <tr>
      <s:iterator value="sections" status="secs" >
      <s:if test="sectionId == 1" >
      <td class="query-page-table-td-left"></s:if >
        <s:if test="sectionId == 2" ></td>
      <td class="query-page-table-td-right"></s:if >
        <s:if test="sectionId == 1 || sectionId == 2" >
        <table class="section-table section-table-bottom"></s:if >
          <s:else >
          <table class="section-table"></s:else >
            <tr>
              <td colspan="3"><h3><s:property value="name"/></h3></td>
            </tr>
            <s:iterator value="parameters" id="para" status="paras">
              <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
              <s:else><tr class="parameter-line-single"></s:else>
              <td class="parameter-label-add"><s:label value="%{label}"/></td>
              <td id="<s:property value="%{'td_tooltip_' + paramId}" />" class="tooltip_td">
                <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]</div>
              </td>
              <td class="mandatory"><s:if test="mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
              <td>
                <s:if test="type == 'double_menu'">
                  <s:if test="paramId == 2">
                    <s:doubleselect name="%{paramName}" headerKey="" headerValue="--- Please Select ---"
                                    list="countries" listKey="countryName" listValue="countryName" doubleName="dsdIdentifier.centreName"
                                    doubleHeaderKey="" doubleHeaderValue="--- Please Select ---" doubleList="centres" doubleListKey="centreName"
                                    doubleListValue="centreName" cssClass='required validate-selection'/>
                  </s:if>
                  <s:else>
                    <s:doubleselect name="%{paramName}" headerKey="" headerValue="--- Please Select ---"
                                    list="menu.options" listKey="value" listValue="value"
                                    doubleName="dsdIdentifier.actualDiagnosis" doubleHeaderKey="" doubleHeaderValue="--- Please Select ---"
                                    doubleList="tertiaries" doubleListKey="value" doubleListValue="value" cssClass='required validate-selection'/>
                  </s:else>
                </s:if>
                <s:if test="type == 'string'">
                  <s:if test="mandatory == 1">
                    <s:textfield name="%{paramName}" value="" cssClass='required'/>
                  </s:if>
                  <s:elseif test="paramId == 3">
                    <i>Please keep a local copy of the Registry ID you will be provided with after saving this entry. This ID will map your local patient record with the registry record.</i>
                  </s:elseif>
                  <s:else>
                    <s:textfield name="%{paramName}" value=""/>
                  </s:else>
                </s:if>
                <s:if test="type == 'year'">
                  <s:textfield name="%{paramName}" cssClass='required int-range-1900-9999'/>
                </s:if>
                <s:if test="type == 'menu'">
                  <s:if test="label == 'Age of First Presentation'">
                    <s:select name="%{paramName}" label="%{para.label}" list="menu.options" listKey="value" listValue="value"
                              emptyOption="false" headerKey="" headerValue="--- Please Select ---"/>
                  </s:if>
                  <s:else>
                    <s:select name="%{paramName}" label="%{para.label}" list="menu.options" listKey="value" listValue="value"
                              emptyOption="false" headerKey="" headerValue="--- Please Select ---" cssClass='required validate-selection'/>
                  </s:else>
                </s:if>
                <s:if test="type == 'auto_fill'">
                  <s:textfield name="%{paramName}" cssClass='auto-text' readonly="true"/>
                </s:if>
                <s:if test="type == 'multiple'">
                  <s:checkboxlist name="%{paramName}" list="menu.options" listKey="value" listValue="value" cssStyle="vertical" theme="idsd_simple"/>
                </s:if>
                <s:if test="type == 'text_area'">
                  <s:textarea name="%{paramName}" cssClass="free-text-area max-length-10000"/>
                </s:if>
                <s:if test="type == 'radio'">
                  <s:radio name="%{paramName}" list="menu.options" listKey="value" listValue="value" cssClass='validate-one-required'></s:radio>
                </s:if>
              </td>
              </tr>
            </s:iterator>
          </table>
          </s:iterator ></td>
          </tr>
          <tr>
            <td colspan='4'>Consent has been obtained to share data with (*):
              <s:select name="consent" list="consentLevelMap" listKey="key" listValue="value" emptyOption="false" headerKey=""
                        headerValue="------ Please Select Consent Level------" cssClass='required validate-selection'/>
            </td>
          </tr>
          <tr>
            <td colspan="3">
              <br/>
              <button type="submit" value="next" name="buttonName">Save Core Info & Diagnosis</button>
            </td>
          </tr>
        </table>
        </s:form >
</div>

<div id="dialog_box_div"></div>


<jsp:include page="/jsp/page/page_foot.jsp"/>