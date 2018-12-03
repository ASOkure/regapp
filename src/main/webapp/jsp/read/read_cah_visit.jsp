<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  YUI().use("node", function (Y) {
//    Y.on("domready", create_cah_init);
    Y.on("domready", view_cah_visit_init);
  })
</script>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="view_cah_visit" theme="simple" cssClass='required-validate'>
  <div id="view_cah_visit_div">
    <h1>Viewing CAH Visit ID <s:property value="dsdCahVisitId"/> of DSD record <s:property value="registerId"/></h1>
    <br/>
    <table>
      <tbody>
        <tr>
          <td align="left">
            <s:url id="readURL" value="read.action">
              <s:param name="registerId" value="registerId"/>
              <s:param name="dsdCahVisitId" value="dsdCahVisitId"/>
              <s:param name="mode" value="mode"/>
            </s:url>
            <s:a name="%{'read_record_' + registerId}" href="%{readURL}" cssStyle="font-size: 120%; font-weight: bold;">Back</s:a>
          </td>
        </tr>
        <tr>
          <td>
            <br/>
            <table id="view_table" class="query-page-table">
              <tr>
                <s:iterator value="cahVisitSections" status="secs">
                <s:if test="sectionId == 9">
                <td class="query-page-table-td-left">
                  </s:if>
                  <s:if test="sectionId == 15">
                </td>
                <td class="query-page-table-td-right"></s:if>
                  <s:if test="sectionId == 14 || sectionId == 18">
                  <table class="section-table-bottom"></s:if>
                    <s:else>
                    <table class="section-table">
                      </s:else>
                      <tr>
                        <td colspan="4"><h3><s:property value="name"/></h3></td>
                      </tr>
                      <s:if test="sectionId == 15">
                        <tr>
                          <td>
                            <table>
                              <thead>
                                <tr>
                                  <th>Test</th>
                                  <th></th>
                                  <th>Result</th>
                                  <th>Date</th>
                                  <th>Time</th>
                                  <th>Value</th>
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="parameters" status="paras">
                                  <s:if test="type == 'menu'">
                                    <s:if test="#paras.odd == true">
                                      <tr id="field_<s:property value="paramId"/>" class="parameter-line-double" >
                                    </s:if>
                                    <s:else>
                                      <tr id="field_<s:property value="paramId"/>" class="parameter-line-single">
                                    </s:else>
                                    <td id="<s:property value="%{'td_label_' + paramId}" />" class="parameter-label-add-blood-test">
                                      <s:label value="%{label}" theme="simple"/>
                                    </td>
                                    <td id="<s:property value="%{'td_tooltip_' + paramId}" />">
                                      <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]</div>
                                    </td>
                                    <td>
                                      <s:if test="type == 'menu'">
                                        <s:textfield name="%{paramName}" size="13" cssClass="display_only" readonly="true" theme="simple"/>
                                      </s:if>
                                    </td>
                                  </s:if>
                                  <s:else>
                                    <s:if test="type == 'date'">
                                      <td>
                                        <s:textfield name="%{paramName}" size="8" cssClass="display_only" readonly="true" theme="simple"/>
                                      </td>
                                    </s:if>
                                    <s:if test="type == 'time'">
                                      <td>
                                        <s:textfield id="%{'time_field_' + paramId}" name="%{paramName}" size="8" readonly="true" theme="simple" cssClass="display_only"/>
                                      </td>
                                    </s:if>
                                    <s:if test="type == 'text_area'">
                                      <td>
                                        <s:textfield name="%{paramName}" cssClass="display_only" size="16" readonly="true" theme="simple"/>
                                      </td>
                                      </tr>
                                    </s:if>
                                  </s:else>
                                </s:iterator>
                                </tr>
                              </tbody>
                            </table>
                          </td>
                        </tr>
                      </s:if>
                      <s:else>
                        <s:iterator value="parameters" id="para" status="paras">
                          <s:if test="#paras.odd == true">
                            <tr class="parameter-line-double"></s:if>
                          <s:else>
                            <tr class="parameter-line-single">
                          </s:else>
                          <td class="parameter-label-add">
                            <s:label value="%{label}" theme="simple"/>
                            <s:if test="paramId == 1304">
                              [<a id="view_med_link">View Selected</a>]
                            </s:if>
                            <s:if test="paramId == 1201">
                              [<a id="view_episodes_link">View Selected</a>]
                            </s:if>
                          </td>
                          <td style="background: transparent">
                            <s:if test="type == 'double_menu'">
                              <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/><br/>
                              <s:textfield name="%{'dsdIdentifier.' + menu.tertiaryParamName}" readonly="true" theme="simple" cssClass="display_only"/>
                            </s:if>
                            <s:else>
                              <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                              <s:if test="paramId == 1001"><span><small> kg</small></span></s:if>
                              <s:if test="paramId == 1002 || paramId == 1003 || paramId == 1004"><span><small> cm</small></span></s:if>
                              <s:if test="paramId == 25"><span><small> mm</small></span></s:if>
                            </s:else>
                          </td>
                          </tr>
                        </s:iterator>
                      </s:else>
                    </table>
                    </s:iterator>
                    </tr>
                  </table>
                  <br/><br/>
                </td>
              </tr>
              <tr>
                <td align="left">
                  <s:url id="readURL" value="read.action">
                    <s:param name="registerId" value="registerId"/>
                    <s:param name="dsdCahVisitId" value="dsdCahVisitId"/>
                    <s:param name="mode" value="mode"/>
                  </s:url>
                  <s:a name="%{'read_record_' + registerId}" href="%{readURL}" cssStyle="font-size: 120%; font-weight: bold;">Back</s:a>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div id="hidden_episodes"></div>
  <div id="hidden_med"></div>
</s:form>

<div class="yui3-skin-sam">
  <div id='medPanelContent' style="width: 1000px;">
    <div class="yui3-widget-hd">Enter more details about each medicine</div>
    <div class='yui3-widget-bd'>
      <table id="med_panel_table" align="center" border="1">
        <thead>
          <tr>
            <th>Order</th>
            <th>Medicine</th>
            <th>Dose</th>
            <th>Unit</th>
            <th>Time (24HH:MM)</th>
            <th>Note</th>
          </tr>
        </thead>
        <tbody id="med_panel_tbody">
          <s:if test="dsdCahVisitMedDetails == null || dsdCahVisitMedDetails.size == 0">
            <s:set name="list" value="{0,1,2}"/>
          </s:if>
          <s:else>
            <s:set name="list" value="dsdCahVisitMedDetails"/>
          </s:else>
          <s:iterator value="list" status="med">
            <s:set name="index" value="%{#med.index}"/>
            <s:date name="time" id="timeId" format="HH:mm:ss"/>
            <tr id="med_tr_${index}">
              <td id="med_tr_${index}_td_1" class="text-center-align">${index + 1}</td>
              <td id="med_tr_${index}_td_2">
                <s:textfield id="med_detail_%{index}_medicine" name="dsdCahVisitMedDetails[%{index}].medicineName" theme="simple" readonly="true"/>
              </td>
              <td id="med_tr_${index}_td_3">
                <s:textfield id="med_detail_%{index}_dose" name="dsdCahVisitMedDetails[%{index}].dose" readonly="true" theme="simple"/>
              </td>
              <td id="med_tr_${index}_td_4">
                <s:textfield id="med_detail_%{index}_unit" name="dsdCahVisitMedDetails[%{index}].unit" theme="simple" readonly="true"/>
              </td>
              <td id="med_tr_${index}_td_5">
                <s:textfield id="med_detail_%{index}_time" name="dsdCahVisitMedDetails[%{index}].time" value="%{timeId}" readonly="true" theme="simple"/>
              </td>
              <td id="med_tr_${index}_td_6">
                <s:textarea id="med_detail_%{index}_note" name="dsdCahVisitMedDetails[%{index}].note" readonly="true" theme="simple"/>
              </td>
            </tr>
          </s:iterator>
        </tbody>
      </table>
    </div>
    <div class="yui3-widget-ft"></div>
  </div>
</div>

<div class="yui3-skin-sam">
  <div id="episodePanelContent">
    <div class="yui3-widget-hd">Enter more details about each episode</div>
    <div class="yui3-widget-bd">
      <table id="episode_panel_table" align="center" style="text-align: center;" border='1'>
        <thead id="episode_panel_thead">
          <tr>
            <td class="bold">Episode</td>
            <td class="episode-table-td-width bold">Number Of Days</td>
            <td class="episode-table-td-width bold">Oral Steroids</td>
            <td class="episode-table-td-width bold">HC Injection</td>
            <td class="episode-table-td-width bold">Adrenal Crisis</td>
            <td class="episode-table-td-width bold">Emergency Management</td>
            <td class="episode-table-td-width bold">Predisposing Condition</td>
          </tr>
        </thead>
        <tbody id="episode_panel_tbody">
          <s:if test="dsdCahVisitEpisodes == null || dsdCahVisitEpisodes.size == 0">
            <s:set name="list" value="{0}"/>
          </s:if>
          <s:else>
            <s:set name="list" value="dsdCahVisitEpisodes"/>
          </s:else>
          <s:iterator value="list" status="episode">
            <s:set name="index" value="%{#episode.index}"/>
            <tr id="episode_tr_${index}">
              <td id="episode_tr_${index}_td_1" theme="simple" class="bold">${index + 1}</td>
              <td id="episode_tr_${index}_td_2" theme="simple" class="episode-table-td-width">
                <s:textfield id='episode_%{index}_days' name="dsdCahVisitEpisodes[%{index}].numberOfDays" theme="simple" readonly="true" cssClass="number-days-textfield" />
              </td>
              <td id="episode_tr_${index}_td_3" class="episode-table-td-width">
                <s:textfield id="episode_%{index}_oral_steroids" name="dsdCahVisitEpisodes[%{index}].oralSteroids" theme="simple" readonly="true"/>
              </td>
              <td id="episode_tr_${index}_td_4" class="episode-table-td-width">
                <s:textfield id="episode_%{index}_hc_injection" name="dsdCahVisitEpisodes[%{index}].hcInjection" theme="simple" readonly="true"/>
              </td>
              <td id="episode_tr_${index}_td_5" class="episode-table-td-width">
                <s:textfield id="episode_%{index}_adrenal_crisis" name="dsdCahVisitEpisodes[%{index}].adrenalCrisis" theme="simple" readonly="true"/>
              </td>
              <td id="episode_tr_${index}_td_6" class="episode-table-td-width">
                <s:textfield id="episode_%{index}_emergency" name="dsdCahVisitEpisodes[%{index}].emergencyAttendance" theme="simple" readonly="true" headerKey="-1" headerValue="- Please Select The Highest Level of Care -"/>
              </td>
              <td id="episode_tr_${index}_td_7" class="episode-table-td-width">
                <s:textfield id="episode_%{index}_predisposing_condition" name="dsdCahVisitEpisodes[%{index}].predisposingCondition" theme="simple" readonly="true"/>
              </td>

            </tr>
          </s:iterator>
        </tbody>
      </table>
    </div>
    <div class="yui3-widget-ft"></div>
  </div>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>