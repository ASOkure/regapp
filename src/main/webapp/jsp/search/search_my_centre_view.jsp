<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  <s:property value='leadersJS' escapeHtml='false' escapeJavaScript='false' />

  YUI().use("node", function (Y) {
    Y.on("domready", search_my_centre_init);
  });
</script>

<div id="basic"></div>
<br/>

<div id="search-record-div">
  <div id="search-tips">
    <h1>Database Query</h1>

    <p>
      Select (by ticking the check-box on the left) from the list below the parameters you would like to search
      the database for and apply the
      conditions that will help refine your search (by either choosing from the drop-down menu or entering a
      string value). </p>
  </div>
  <s:form id="search_form" action="search_my_centre" theme="simple" cssClass='required-validate'>
  <table id="search_table" class="query-page-table">
    <tr>
      <td>
        <s:checkbox id="check_all_box" name="checkAll"/><strong>Check/Uncheck all</strong>
      </td>
      <td colspan="2" align="right" style="height: 50px;">
        <s:submit value="Search" theme="simple"/>
        <s:reset theme="simple"/>
      </td>
    </tr>
    <tr>
      <s:iterator value="sections" status="secs" var="sec">
      <s:if test="isResearcherView == true">
      <s:if test="sectionId == 1">
      <td class="query-page-table-td-left"></s:if>
        <s:if test="sectionId == 2"></td>
      <td class="query-page-table-td-right"></s:if>
        <s:if test="sectionId == 1 || sectionId == 2">
        <table class="section-table-bottom"></s:if>
          </s:if>
          <s:else>
          <s:if test="sectionId == 1">
          <td class="query-page-table-td-left"></s:if>
            <s:if test="name.startsWith('First ')"></td>
          <td class="query-page-table-td-right"></s:if>
            <s:if test="sectionId == 5 || sectionId == sections.size">
            <table class="section-table-bottom"></s:if>
              <s:else>
              <table class="section-table"></s:else>
                </s:else>
                <tr>
                  <td colspan="3">
                    <h3>
                      <s:checkbox id="%{'section_check_' + sectionId}" name="%{'section_check_' + sectionId}" value="false" theme="simple"/>
                      <s:property value="name"/>
                    </h3>
                  </td>
                </tr>
                <s:iterator value="parameters" id="para" status="paras">
                  <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
                  <s:else><tr class="parameter-line-single"></s:else>
                  <td class="parameter-checkbox-td">
                    <s:if test="paramId == 1">
                      <s:checkbox id="%{'parameter_checkbox_' + paramId}" name="%{'parameter_checkbox_' + paramId}" value="true" theme="simple"/>
                    </s:if>
                    <s:else>
                      <s:if test="#sec.sectionId == 6 && #sec.name.startsWith('First ')">
                        <s:checkbox id="%{'a0_parameter_checkbox_' + paramId}" name="%{'a0_parameter_checkbox_' + paramId}" value="false" theme="simple"/>
                      </s:if>
                      <s:elseif test="#sec.sectionId == 6 && #sec.name.startsWith('Latest ')">
                        <s:checkbox id="%{'a1_parameter_checkbox_' + paramId}" name="%{'a1_parameter_checkbox_' + paramId}" value="false" theme="simple"/>
                      </s:elseif>
                      <s:else>
                        <s:checkbox id="%{'parameter_checkbox_' + paramId}" name="%{'parameter_checkbox_' + paramId}" value="false" theme="simple"/>
                      </s:else>
                    </s:else>
                  </td>
                  <td class="parameter-label-search"><s:label value="%{label}"/></td>
                  <td class="parameter-tooltip-td" id="<s:property value="%{'td_tooltip_' + paramId}" />">
                    <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]</div>
                  </td>
                  <td class="parameter-value-search">
                    <s:if test="type == 'int'">
                      <s:textfield id="%{'textfield_' + paramId}" name="%{paramName}" cssClass="validate-digits"/>
                    </s:if>

                    <s:if test="type == 'double_menu'">
                      <s:doubleselect name="%{paramName}" headerKey="" headerValue="--- Please Select ---" list="menu.options" listKey="value" listValue="value" doubleName="%{'dsdIdentifier.' + menu.tertiaryParamName}" doubleHeaderKey="" doubleHeaderValue="--- Please Select ---" doubleList="tertiaries" doubleListKey="value" doubleListValue="value" theme="simple" cssClass='validate-selection'/>
                    </s:if>
                    <s:if test="type == 'string'">
                      <s:textfield name="%{paramName}"/>
                    </s:if>
                    <s:if test="type == 'year'">
                      <s:textfield name="%{paramName}" cssClass='int-range-1900-9999'/>
                    </s:if>
                    <s:if test="type == 'menu'">
                      <s:select name="%{paramName}" emptyOption="false" list="menu.options" listKey="value" listValue="value" headerKey="" headerValue="--- Please Select ---"/>
                      <s:if test="paramId >= 26 && paramId <= 30">
                        <s:div id="%{'ems_' + paramName}">
                          <s:iterator value="menu.options" status="options">
                            <s:hidden name="%{paramName + '_' + #options.index}" value="%{score}" disabled="true"/>
                          </s:iterator>
                        </s:div>
                      </s:if>
                    </s:if>
                    <s:if test="type == 'auto_fill'">
                      <s:if test="paramId == 33">
                        <s:textfield name="%{paramName}" theme="simple" cssClass="float-range-0-12"/>
                      </s:if>
                      <s:else><s:textfield name="%{paramName}" cssClass='auto-text' readonly="true"/></s:else>
                    </s:if>
                    <s:if test="type == 'multiple'">
                      <s:checkboxlist name="%{paramName}" listKey="value" list="menu.options" listValue="value" theme="idsd_simple"/>
                    </s:if>
                    <s:if test="type == 'date'">
                      <s:textfield name="%{paramName}" cssClass='validate-date-dd/MM/yyyy'/>
                      <span><small>E.g. 18/06/2008</small></span>
                    </s:if>
                    <s:if test="type == 'float'">
                      <s:if test="paramId == 12 || paramId == 73">
                        <s:textfield name="%{paramName}" theme="simple" cssClass="float-range-0-10000"/>
                        <span><small>gram</small></span>
                      </s:if>
                      <s:if test="paramId == 13 || paramId == 74">
                        <s:textfield name="%{paramName}" theme="simple" cssClass="float-range-0-80"/>
                        <span><small>cm</small></span>
                      </s:if>
                      <s:if test="paramId == 25">
                        <s:textfield name="%{paramName}" theme="simple" cssClass="float-range-0-200"/>
                        <span><small>mm</small></span>
                      </s:if>
                    </s:if>
                    <s:if test="type == 'text_area'">
                      <s:if test="paramId == 17">
                        <s:textarea name="%{paramName}" cssClass="gene-search-selection max-length-500" readonly="true"/>
                        <br/>
                        <small>Please do not modify this field manually</small>
                      </s:if>
                      <s:else>
                        <s:textarea name="%{paramName}" cssClass="free-text-area max-length-10000"/>
                      </s:else>
                    </s:if>
                    <s:if test="type == 'radio'">
                      <s:radio list="menu.options" listKey="value" listValue="value" name="%{paramName}"></s:radio>
                    </s:if>
                  </td>
                  </tr>
                  <s:if test="paramId == 47">
                    <tr>
                      <td class="parameter-checkbox-td"></td>
                      <td class="parameter-label-search">
                        Search Screened genes (<a id="edit_gene_selection" href="#">Edit</a>)
                      </td>
                      <td class="parameter-tooltip-td" id="<s:property value="%{'td_tooltip_' + paramId}" />">
                        <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]</div>
                      </td>
                      <td class="parameter-value-search">
                        <p>It recommended to search on single gene rather than combination of genes</p>
                        <s:textarea id="selected_gene" name="geneSelection" cssClass="gene-search-selection" readonly="true"/>
                      </td>
                    </tr>
                  </s:if>
                </s:iterator>
              </table>
              </s:iterator>
              </td>
              </tr>
              <tr>
                <td colspan="3" align="left" style="padding: 6px; margin: 3px;">
                  <table>
                    <tr>
                      <td style="width: 20px;">
                      </td>
                      <td>
                        Consent has been obtained to share data with:
                        <s:select name="dsdIdentifier.consent" list="consentLevelMap" listKey="key" listValue="value" emptyOption="false" headerKey="" headerValue="------ Please Select Consent Level------" theme="simple" cssClass='validate-selection'/>
                      </td>
                    </tr>
                    <tr>
                      <td style="width: 20px;">
                        <s:checkbox name="dsdIdentifier.sampleConsent"/>
                      </td>
                      <td>
                        Consent has been obtained to share sample &amp; perform
                        studies as outlined in the I-DSD programme
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
              <tr>
                <td colspan="3" align="right">
                  <s:submit value="Search" theme="simple"></s:submit>
                  <s:reset value="Reset" theme="simple"></s:reset>
                </td>
              </tr>
            </table>
              <s:hidden name="mode" value="%{#parameters.mode[0]}"/>
            </s:form>
</div>

<jsp:include page="/jsp/page/gene_table.jsp"/>

<jsp:include page="/jsp/page/page_foot.jsp"/>