<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<div id="view_record_div">
  <h1>Search Request ID <s:property value="searchId"/> by <s:property value="requester"/></h1><br/>
  <s:form id="search_form" action="view_search_request_result" theme="simple">
    <s:hidden name="searchId" value="%{searchId}"/>
    <s:hidden name="mode" value="%{mode}"/>
    <div id="buttons_top" align="left">
      <s:submit value="Execute Search Request"/>
        <%--<s:url id="executeSearchRequestLink" value="../search/view_search_request_result.action">--%>
        <%--<s:param name="searchId" value="searchId"/>--%>
        <%--<s:param name="mode" value="mode"/>--%>
        <%--</s:url>--%>

        <%--<s:url id="cancelURL" value="cancel">--%>
        <%--<s:param name="mode" value="mode"/>--%>
        <%--</s:url>--%>

        <%--<s:a name="%{'search_request_' + searchId}" href="%{executeSearchRequestLink}" cssStyle="font-size: 120%; font-weight: bold;">Execute Search Request</s:a> |--%>
        <%--<s:a href="%{cancelURL}" cssStyle="font-size: 120%; font-weight: bold;">Cancel</s:a><br/><br/>--%>
    </div>
    <table id="column_table" class="query-page-table">
      <tr id="column_tr">
        <s:iterator value="sections" status="secs">
        <s:if test="sectionId == 1">
        <td id="column_td_left" class="query-page-table-td-left">
          </s:if>
          <s:if test="name.startsWith('First ')"></td>
        <td id="column_td_right" class="query-page-table-td-right"></s:if>
          <s:if test="sectionId == 5 || sectionId == 9">
          <table id="section_table_bottom_${sectionId}" class="section-table-bottom"></s:if>
            <s:else>
            <table id="section_table_${sectionId}" class="section-table"></s:else>
              <tr id="section_tr_${sectionId}">
                <td id="section_td_caption_${sectionId}" colspan="3"><h3><s:property value="name"/></h3></td>
              </tr>
              <s:iterator value="parameters" id="para" status="paras">
                <tr id="section_tr_${paramId}"
                    <s:if test="#paras.odd == true">class="parameter-line-double" </s:if>
                    <s:else>class="parameter-line-single"</s:else>>
                    <%--<td class="parameter-checkbox-td">--%>
                    <%--<s:if test="#sec.sectionId == 6 && #sec.name.startsWith('First ')">--%>
                    <%--<s:checkbox id="%{'first_parameter_checkbox_' + paramId}" name="selectedParameterIds" fieldValue="%{paramId + 'f'}" />--%>
                    <%--</s:if>--%>
                    <%--<s:elseif test="#sec.sectionId == 6 && #sec.name.startsWith('Latest ')">--%>
                    <%--<s:checkbox id="%{'last_parameter_checkbox_' + paramId}" name="selectedParameterIds" fieldValue="%{paramId + 'l'}"/>--%>
                    <%--</s:elseif>--%>
                    <%--<s:else>--%>
                    <%--<s:checkbox id="%{'parameter_checkbox_' + paramId}" name="selectedParameterIds" fieldValue="%{paramId}"/>--%>
                    <%--</s:else>--%>
                    <%--</td>--%>
                  <td id="section_td_parameter_label_${paramId}" class="parameter-label-add">
                    <s:label id="parameter_label" value="%{label}" theme="simple"/>
                    <s:if test="paramId == 12"><span><small> (gram)</small></span></s:if>
                    <s:if test="paramId == 13"><span><small> (cm)</small></span></s:if>
                  </td>
                  <td id="section_td_value_${paramId}" style="background: transparent">
                    <s:if test="type == 'double_menu'">
                      <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/><br/>
                      <s:textfield name="%{'dsdIdentifier.' + menu.tertiaryParamName}" readonly="true" theme="simple" cssClass="display_only"/>
                    </s:if>
                    <s:elseif test="type == 'text_area'">
                      <s:textarea name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                    </s:elseif>
                    <s:else>
                      <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                    </s:else>
                  </td>
                </tr>
              </s:iterator>
            </table>
            </s:iterator>
            <br/><br/>
            </td>
            </tr>
          </table>
          </tbody>
    </table>
    <s:if test="consentString != null">
      <div id="consent_div">
        Consent has been obtained to share this data with <b><s:property value="consentString"/></b>
      </div>
    </s:if>
    <s:if test="dsdIdentifier.sampleShared != null">
      <div id="sample_div">
        Consent has
        <B><s:if test="dsdIdentifier.sampleShared != 1">NOT</s:if></B> been obtained to share sample & perform studies as outlined in the DSD Network programme
      </div>
    </s:if>
    <br/>
    <div id="buttons_bottom" align="left">
      <s:submit value="Execute Search Request"/>
        <%--<s:a name="%{'search_request_' + searchId}" href="%{executeSearchRequestLink}" cssStyle="font-size: 120%; font-weight: bold;">Execute Search Request</s:a> |--%>
        <%--<s:a href="%{cancelURL}" cssStyle="font-size: 120%; font-weight: bold;">Cancel</s:a><br/><br/>--%>
    </div>
  </s:form>
</div>
<!--end of view_record_div-->
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>