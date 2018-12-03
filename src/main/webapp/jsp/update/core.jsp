<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="my-struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  <s:property value='leadersJS' escapeHtml='false' escapeJavaScript='false' />
  YUI().use("node", function (Y) {
    Y.on("domready", edit_core_init);
  })
</script>

<div id="edit_view_record_div">
  <h1>Update Record: Register ID <s:property value="registerId"/> - Core Info & Diagnosis</h1>
  <s:include value="tabs.jsp"/>
  <br/>
  <ul>
    <li class="page_tips_li">Fields marked with * are mandatory to fill in.</li>
    <li class="page_tips_li">Only Core Information & Diagnosis, is mandatory. Other sections are all optional.</li>
  </ul>
  <s:actionerror cssClass="error_message"/>
  <s:fielderror cssClass="error_message"/>

  <s:form id="core_form" action="save_core" theme="idsd_simple">
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <table id="edit_table" class="query-page-table">
    <tr>
      <td colspan="3" class="button_td">
        <button type="submit" value="next" name="buttonName">Save Core Info & Diagnosis</button>
        <br/>
      </td>
    </tr>
    <tr>
      <s:iterator value="sections" status="secs">
      <s:if test="sectionId == 1">
      <td class="query-page-table-td-left"></s:if>
        <s:if test="sectionId == 2"></td>
      <td class="query-page-table-td-right"></s:if>
        <s:if test="sectionId == 1 || sectionId == 2">
        <table class="section-table-bottom"></s:if>
          <s:else>
          <table class="section-table"></s:else>
            <tr>
              <td colspan="3"><h3><s:property value="name"/></h3></td>
            </tr>
            <s:iterator value="parameters" status="paras">
              <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
              <s:else><tr class="parameter-line-single"></s:else>
              <td class="parameter-label-add"><s:label value="%{label}"/></td>
              <td id="<s:property value="%{'td_tooltip_' + paramId}" />">
                <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]</div>
              </td>
              <td class="mandatory"><s:if test="mandatory == 1"> * </s:if></td>
              <td>
                <s:if test="paramId == 1">
                  <s:textfield name="%{paramName}" readonly="true" cssClass="display_only"/>
                </s:if>
                <s:else>
                  <s:if test="type == 'double_menu'">
                    <s:if test="paramId == 2">
                      <s:doubleselect name="%{paramName}" headerKey="" headerValue="--- Please Select ---" list="countries" listKey="countryName" listValue="countryName" doubleName="dsdIdentifier.centreName" doubleHeaderKey="" doubleHeaderValue="--- Please Select ---" doubleList="centres" doubleListKey="centreName" doubleListValue="centreName" data-validation="required" data-validation-help="data-validationdata-validationdata-validation"/>
                    </s:if>
                    <s:elseif test="paramId == 11">
                      <s:doubleselect name="%{paramName}" headerKey="" headerValue="--- Please Select ---" list="menu.options" listKey="value" listValue="value" doubleName="dsdIdentifier.actualDiagnosis" doubleHeaderKey="" doubleHeaderValue="--- Please Select ---" doubleList="tertiaries" doubleListKey="value" doubleListValue="value" data-validation="required" data-validation-help="data-validationdata-validationdata-validation"/>
                    </s:elseif>
                  </s:if>
                  <s:if test="type == 'string'">
                    <s:if test="mandatory == 1">
                      <s:textfield name="%{paramName}" cssClass='required'/>
                    </s:if>
                    <s:elseif test="paramId == 3">
                      <i>Please keep a local copy of the Registry ID you will be provided with after saving this entry. This ID will map your local patient record with the registry record.</i>
                    </s:elseif>
                    <s:else>
                      <s:textfield name="%{paramName}"/>
                    </s:else>
                  </s:if>
                  <s:if test="type == 'year'">
                    <s:textfield name="%{paramName}" cssClass='required int-range-1900-9999'/>
                  </s:if>
                  <s:if test="type == 'menu'">
                    <s:if test="paramId == 5">
                      <s:select name="%{paramName}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---"/>
                    </s:if>
                    <s:else>
                      <s:select name="%{paramName}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" data-validation="required"/>
                    </s:else>
                  </s:if>
                  <s:if test="type == 'auto_fill'">
                    <s:textfield name="%{paramName}" cssClass='display_only' readonly="true"/>
                  </s:if>
                  <s:if test="type == 'multiple'">
                    <s:checkboxlist name="%{paramName}" listKey="value" listValue="value" list="menu.options" value="checklist" cssStyle="vertical" theme="idsd_simple"/>
                  </s:if>
                  <s:if test="type == 'text_area'">
                    <s:textarea name="%{paramName}" cssClass="free-text-area max-length-10000"/>
                  </s:if>
                  <s:if test="type == 'radio'">
                    <s:radio name="%{paramName}" list="menu.options" listKey="value" listValue="value"/>
                  </s:if>
                </s:else>
              </td>
              </tr>
            </s:iterator>
          </table>
          </s:iterator>
          </tr>
          <tr>
            <td colspan='4'>Consent has been obtained to share data with (*):
              <s:select name="consent" list="consentLevelMap" listKey="key" listValue="value" emptyOption="false" headerKey="" headerValue="------ Please Select Consent Level------" data-validation="required"/>
            </td>
          </tr>
          <tr>
            <td colspan="3"><br/>
              <button type="submit" value="next" name="buttonName">Save Core Info & Diagnosis</button>
            </td>
          </tr>
        </table>
        </s:form>
</div>
<div id="dialog_box_div"></div>

<jsp:include page="/jsp/page/page_foot.jsp"/>