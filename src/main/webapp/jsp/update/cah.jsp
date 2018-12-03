<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<% String path = request.getContextPath();%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  YUI().use("node", function (Y) {
    Y.on("domready", create_cah_init);
    Y.on("domready", create_cah_visit_init);
  })
</script>

<h1>Record: Register ID <s:property value="registerId"/></h1>

<s:include value="tabs.jsp"/><br/><br/>

Please note the CAH module is made of 2 sections:
<a href="#cah_static">CAH 1st Presentation Data</a> and <a href="#cah_longitudinal">CAH Longitudinal Data (Visits)</a>.

<br/>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<h1 id="cah_static">CAH 1st Presentation Data Section</h1>
<a href="#cah_longitudinal">Jump to CAH Longitudinal Data Section</a>
<br/>

<s:form id="upload_cah" action="/update/save_cah" theme="simple" cssClass='required-validate'>
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <s:hidden name="dsdCahVisitId" value="%{dsdCahVisitId}"/>

  <table id="cah_master_table" class="query-page-table">
    <tr>
      <table class="section-table">
        <tr>
          <td colspan="3"><h3><s:property value="name"/></h3></td>
        </tr>
        <s:iterator value="cahParameters" id="para" status="paras">
          <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
          <s:else><tr class="parameter-line-single"></s:else>
          <td class="parameter-label-add"><s:label value="%{label}"/></td>
          <td class="mandatory"><s:if test="mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
          <td id="<s:property value="%{'td_tooltip_' + paramId}" />">
            <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]
            </div>
          </td>
          <td>
            <s:if test="type == 'date'">
              <s:textfield id="date_field" name="%{paramName}" cssClass='required validate-date-dd/MM/yyyy'/>
              <button type="button" id="show_button" title="Show Calendar" class="calendar_button">
                <img src="<%=path %>/images/calbtn.gif" width="18" height="18" alt="Calendar" alt="calendar_button"/>
              </button>
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
              <s:select name="%{paramName}" label="%{para.label}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---"/>
            </s:if>
            <s:if test="type == 'auto_fill'">
              <s:textfield name="%{paramName}" cssClass='auto-text' readonly="true"/>
            </s:if>
            <s:if test="type == 'float'">
              <s:if test="paramId == 72">
                <s:textfield name="%{paramName}" value="%{getFormatted('{0,number,0.00}', paramName)}" cssClass="float-range-400-7000"/>
                <span><small>gram</small></span>
              </s:if>
              <s:elseif test="paramId == 73">
                <s:textfield name="%{paramName}" value="%{getFormatted('{0,number,0.00}', paramName)}" cssClass="float-range-10-60"/>
                <span><small>cm</small></span>
              </s:elseif>
              <s:elseif test="paramId == 74">
                <s:textfield name="%{paramName}" value="%{getFormatted('{0,number,0.00}', paramName)}" cssClass="float-range-100-220"/>
                <span><small>cm</small></span>
              </s:elseif>
              <s:elseif test="paramId == 75">
                <s:textfield name="%{paramName}" value="%{getFormatted('{0,number,0.00}', paramName)}" cssClass="float-range-100-220"/>
                <span><small>cm</small></span>
              </s:elseif>
              <s:elseif test="paramId == 76">
                <s:textfield name="%{paramName}" value="%{getFormatted('{0,number,0.00}', paramName)}" cssClass="float-range-100-220"/>
                <span><small>cm</small></span>
              </s:elseif>
              <s:else>
                <s:textfield name="%{paramName}" cssClass="validate-number"/>
              </s:else>
            </s:if>
            <s:if test="type == 'text_area'">
              <s:textarea name="%{paramName}" cssClass="max-length-10000"/>
            </s:if>
            <s:if test="type == 'radio'">
              <s:radio name="%{paramName}" list="menu.options" listKey="value" listValue="value"/>
            </s:if>
          </td>
          </tr>
        </s:iterator>
      </table>
    </tr>
    <tr>
      <td colspan="3"><br/>
        <s:submit value="Save 1st Presentation Data" theme="simple" action="/update/save_cah"/>
      </td>
    </tr>
  </table>
</s:form>

<br/><br/>
<hr/>

<h1 id="cah_longitudinal">CAH Longitudinal Data Section</h1>
<a href="#cah_static">Jump to CAH 1st Presentation Data Section</a>
<br/><br/><br/>

<s:form id="upload_cah" action="/update/save_cah_visit" theme="simple" cssClass='required-validate'>

  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <s:hidden name="dsdCahVisitId" value="%{dsdCahVisitId}"/>

  <!-- lastDsdVisit -->
  <!-- sam comments: CAH Longitudinal Data Section is below -->

  <div id="cah_visit_display_div">
    <table id="cah_visit_display_table" class="dataTable display" width="100%">
      <thead>
        <tr>
          <th id="id">ID</th>
          <th id="visit_date">Date</th>
          <th id="age">Age</th>
          <th id="bmi">BMI</th>
          <th id="bsa">BSA</th>
          <th id="systolic">Systolic</th>
          <th id="diastolic">Diastolic</th>
          <th id="sick_day_episodes">Sick Day Episodes</th>
          <th id="treatment_change">Treatment Change</th>
          <th id="treatment_change_reason">Treatment Change Reason</th>
          <th id="actions">Action</th>
        </tr>
      </thead>
      <tbody>
        <s:iterator value="dsdIdentifier.dsdCah.dsdCahVisits" status="cahVisit">
          <tr id="${dsdCahVisitId}">
            <td id="id_value"><s:property value="dsdCahVisitId"/></td>
            <td id="date_value"><s:date name="date" format="yyyy-MM-dd"/></td>
            <td id="age_value"><s:property value="age"/></td>
            <td id="bmi_value"><s:property value="bmi"/></td>
            <td id="bsa_value"><s:property value="bsa"/></td>
            <td id="systolic_value"><s:property value="bpSystolic"/></td>
            <td id="diastolic_value"><s:property value="bpDiastolic"/></td>
            <td id="sick_day_episodes_value"><s:property value="sickDayEpisodes"/></td>
            <td id="treatment_change_value"><s:property value="treatmentChange"/></td>
            <td id="treatment_change_reason_value"><s:property value="treatmentChangeReason"/></td>
            <td>
              <s:url id="edit_url" value="/update/edit_cah_visit.action">
                <s:param name="registerId" value="%{registerId}"/>
                <s:param name="dsdCahVisitId" value="%{dsdCahVisitId}"/>
                <s:param name="mode" value="%{mode}"/>
              </s:url>
              <s:a name="%{'edit_' + dsdCahVisitId}" href="%{edit_url}" theme="simple">edit</s:a> |
              <s:url id="delete_url" value="/update/remove_cah_visit.action">
                <s:param name="registerId" value="%{registerId}"/>
                <s:param name="dsdCahVisitId" value="dsdCahVisitId"/>
                <s:param name="mode" value="%{mode}"/>
              </s:url>
              <s:a name="%{'delete_' + dsdCahVisitId}" href="%{delete_url}" theme="simple">remove</s:a> |
              <s:url id="view_url" value="/search/read_cah_visit.action">
                <s:param name="registerId" value="%{registerId}"/>
                <s:param name="dsdCahVisitId" value="%{dsdCahVisitId}"/>
                <s:param name="mode" value="%{mode}"/>
              </s:url>
              <s:a name="%{'view_' + dsdCahVisitId}" href="%{view_url}" theme="simple" target="_blank">view</s:a>
            </td>
          </tr>
        </s:iterator>
      </tbody>
    </table>
  </div>

  <h2>
    <s:if test="dsdCahVisitId != null">
      CAH Visits ID: <s:property value="dsdCahVisitId"/>
    </s:if>
    <s:else>
      A New CAH visit
    </s:else>
  </h2>

  <ul>
    <li>
      Please note that a new CAH visit will be a copy from the last saved visit.
      If this is the first visit, Not Known will be default selection.
    </li>
    <li>
      Please note that CAH 1st Presentation Data Section will not be save when saving CAH visit.
      Please save it separately by click the
      <i>Save 1st Presentation Data</i> button in CAH 1st Presentation Data Section
    </li>
  </ul>


<!-- Sam comment: A New CAH visit section below -->

  <table id="cah_visit_master_table" class="query-page-table">
  <tbody id="cah_visit_master_body">
  <tr>
    <td colspan="3" class="button_td">
      <s:submit value="Save CAH Visit" theme="simple" action="/update/save_cah_visit"/>
        <%--<s:submit value="Clear" theme="simple" action="/update/clear_cah_visit"/>--%>
      <s:submit value="Next Section" theme="simple" action="/update/submit_cah_visit"/>
      <br/>
    </td>
  </tr>
  <tr>
  <s:iterator value="cahVisitSections" status="secs">
    <s:if test="sectionId == 9">
      <td class="query-page-table-td-left"></s:if>
    <s:if test="sectionId == 15"></td>
    <td class="query-page-table-td-right"></s:if>
    <s:if test="sectionId == 14 || sectionId == 19">
      <table class="section-table-bottom"></s:if>
    <s:else>
      <table class="section-table"></s:else>
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
                    <s:label value="%{label}"/>
                  </td>
                  <td id="<s:property value="%{'td_tooltip_' + paramId}" />">
                    <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]
                    </div>
                  </td>
                  <td>
                    <s:if test="type == 'menu'">
                      <s:select name="%{paramName}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="Select" cssClass='validate-selection'/>
                    </s:if>
                  </td>
                </s:if>
                <s:else>
                  <s:if test="type == 'date'">
                    <td>
                      <s:textfield id="%{'date_field_' + paramId}" name="%{paramName}" size="8" cssClass="calendar_text_field validate-date-dd/MM/yyyy"/>
                      <button type="button" id="<s:property value="%{'show_button_' + paramId}" />" title="Show Calendar" class="button-compact calendar_button">
                        <img src="<%=path %>/images/button/calbtn_red.png" alt="Calendar" alt="calendar_button" class="calendar-image"/>
                      </button>
                      <button type="button" id="<s:property value="%{'copy_button_' + paramId}" />" title="Copy this date to other empty date fields" class="button-compact copy_button">
                        <img src="<%=path %>/images/button/copy-16.png" alt="Copy" alt="copy_button" class="calendar-image"/>
                      </button>
                    </td>
                  </s:if>
                  <s:if test="type == 'time'">
                    <td>
                      <s:textfield id="%{'time_field_' + paramId}" name="%{paramName}" theme="simple" 
Class="time-field validate-time"/>
                    </td>
                  </s:if>
                  <s:if test="type == 'text_area'">
                    <td><s:textfield name="%{paramName}" cssClass="blood-test-value"/></td>
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
      <s:iterator value="parameters" status="paras">
        <s:if test="hidden == 0">
          <s:if test="#paras.odd == true">
            <s:if test="type == 'text_area' && paramId != 1607 && paramId != 1901">
              <tr id="tr_note_<s:property value="paramId"/>" class="parameter-line-double-hidden" >
            </s:if>
            <s:else>
              <tr id="tr_<s:property value="paramId"/>" class="parameter-line-double">
            </s:else>
          </s:if>
          <s:else>
            <s:if test="type == 'text_area' && paramId != 1607 && paramId != 1901">
              <tr id="tr_note_<s:property value="paramId"/>" class="parameter-line-single-hidden" >
            </s:if>
            <s:else>
              <tr id="tr_<s:property value="paramId"/>" class="parameter-line-single">
            </s:else>
          </s:else>
          <td id="<s:property value="%{'td_label_' + paramId}" />" class="parameter-label-add">
            <s:label value="%{label}"/>
            <s:if test="paramId == 1304">
              [<a id="view_med_link">View Selected</a>]
            </s:if>
            <s:if test="paramId == 1201">
              [<a id="view_episodes_link">View Selected</a>]
            </s:if>
          </td>
          <td id="<s:property value="%{'td_tooltip_' + paramId}" />" class="tooltip_td">
            <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]
            </div>
          </td>
          <td id="<s:property value="%{'td_mandatory_' + paramId}" />" class="mandatory">
            <s:if test="mandatory == 1"> * </s:if>
          </td>
          <td id="<s:property value="%{'td_field_' + paramId}" />">
            <s:if test="type == 'date'">
              <s:if test="paramId == 991">
                <s:textfield id="%{'date_field_' + paramId}" name="%{paramName}" cssClass="required validate-date-dd/MM/yyyy"/>
              </s:if>
              <s:else>
                <s:textfield id="%{'date_field_' + paramId}" name="%{paramName}" cssClass="validate-date-dd/MM/yyyy"/>
              </s:else>
              <button type="button" id="<s:property value="%{'show_button_' + paramId}" />" title="Show Calendar" class="calendar_button">
                <img src="<%=path %>/images/button/calbtn_red.png" alt="Calendar"/>
              </button>
            </s:if>
            <s:if test="type == 'float'">
              <s:if test="paramId == 992"> <!-- age -->
                <s:textfield name="dsdCahVisit.age" value="%{getFormatted('{0,number,0.00}','dsdCahVisit.age')}" cssClass="float-range-0-120 decimal-places-2"/>
                <span><small>years</small></span>
              </s:if>
              <s:elseif test="paramId == 1001"> <!-- weight -->
                <s:textfield name="%{paramName}" value="%{getFormatted('{0,number,0.00}', paramName)}" cssClass="float-range-0.7-400"/>
                <span><small>kg</small></span>
              </s:elseif>
              <s:elseif test="paramId == 1002 || paramId == 1003 || paramId == 1004"> <!-- height, Waist Circumference, Hip Circumference -->
                <s:textfield name="%{paramName}" value="%{getFormatted('{0,number,0.00}', paramName)}" cssClass="float-range-10-250"/>
                <span><small>cm</small></span>
              </s:elseif>
              <s:elseif test="paramId == 1307">
                <s:textfield name="%{paramName}" cssClass="float-range-20-500"/>
                <span><small>mcg</small></span>
              </s:elseif>
              <s:elseif test="paramId == 1805 || paramId == 1806">
                <s:textfield name="%{paramName}" cssClass="float-range-0-50"/>
                <span><small>ml</small></span>
              </s:elseif>
              <s:else>
                <s:textfield name="%{paramName}" cssClass="validate-number"/>
              </s:else>
            </s:if>
            <s:if test="type == 'int'">
              <s:if test="paramId == 1101"> <!-- BP Systolic -->
                <s:textfield name="%{paramName}" cssClass="int-range-20-250"/>
                <span><small>mm Hg</small></span>
              </s:if>
              <s:elseif test="paramId == 1102"> <!-- BP Diastolic -->
                <s:textfield name="%{paramName}" cssClass="int-range-0-250"/>
                <span><small>mm Hg</small></span>
              </s:elseif>
              <s:elseif test="paramId == 1201"> <!-- Mandatory: number of sick days-->
                <s:textfield name="%{paramName}" cssClass="required int-range-0-30"/>
              </s:elseif>
              <s:else>
                <s:textfield name="%{paramName}"/>
              </s:else>
            </s:if>
            <s:if test="type == 'year'">
              <s:textfield name="%{paramName}" cssClass='required int-range-1900-9999'/>
            </s:if>
            <s:if test="type == 'menu'">
              <s:if test="paramId == 1301 || paramId == 1302">
                <s:select name="%{paramName}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" cssClass='required validate-selection'/>
              </s:if>
              <s:else>
                <s:select name="%{paramName}" list="menu.options" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" cssClass='validate-selection'/>
              </s:else>
            </s:if>
            <s:if test="type == 'auto_fill'">
              <s:textfield name="%{paramName}" cssClass='auto-text' readonly="true"/>
            </s:if>
            <s:if test="type == 'multiple'">
              <s:checkboxlist name="%{paramName}" listKey="value" listValue="value" list="menu.options" value="checklist" cssStyle="vertical" theme="idsd_simple"/>
            </s:if>
            <s:if test="type == 'text_area'">
              <s:textarea name="%{paramName}" cssClass="icah-visit-note max-length-10000"/>
            </s:if>
            <s:if test="type == 'radio'">
              <s:radio name="%{paramName}" list="menu.options" listKey="value" listValue="value" id="%{'radio_param_' + paramId + '_'}"/>
            </s:if>
          </td>
          </tr>
        </s:if>
      </s:iterator>
      </table>
    </s:else>
  </s:iterator>
  </tr>
  <tr>
    <td colspan="3">
      <br/>
      <s:submit value="Save CAH Visit" theme="simple" action="/update/save_cah_visit"/>
        <%--<s:submit value="Clear" theme="simple" action="/update/clear_cah_visit"/>--%>
      <s:submit value="Next Section" theme="simple" action="/update/submit_cah_visit"/>
    </td>
  </tr>
  </tbody>
  </table>

  <div id="hidden_episodes"></div>
  <div id="hidden_med"></div>

  <a href="#cah_static">CAH 1st Presentation Data Section</a> |
  <a href="#cah_longitudinal">CAH Longitudinal Data Section</a>

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
                  <s:select id="med_detail_%{index}_medicine" name="dsdCahVisitMedDetails[%{index}].medicineName" list="glucocorticoidsMedicineOptions" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" theme="simple"/>
                </td>
                <td id="med_tr_${index}_td_3">
                  <s:textfield id="med_detail_%{index}_dose" name="dsdCahVisitMedDetails[%{index}].dose" theme="simple" cssClass="float-range-0-1200"/>
                </td>
                <td id="med_tr_${index}_td_4">
                  <s:select id="med_detail_%{index}_unit" name="dsdCahVisitMedDetails[%{index}].unit" list="unitOptions" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" theme="simple"/>
                </td>
                <td id="med_tr_${index}_td_5">
                  <s:textfield id="med_detail_%{index}_time" name="dsdCahVisitMedDetails[%{index}].time" value="%{timeId}" theme="simple" cssClass="validate-time"/>
                </td>
                <td id="med_tr_${index}_td_6">
                  <s:textarea id="med_detail_%{index}_note" name="dsdCahVisitMedDetails[%{index}].note" theme="simple" cssClass="icah-visit-note max-length-10000"/>
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
                <td id="episode_tr_${index}_td_1" class="bold">${index + 1}</td>
                <td id="episode_tr_${index}_td_2" class="episode-table-td-width">
                  <s:textfield id='episode_%{index}_days' name="dsdCahVisitEpisodes[%{index}].numberOfDays" theme="simple" cssClass='number-days-textfield int-range-0-20'/>
                </td>
                <td id="episode_tr_${index}_td_3" class="episode-table-td-width">
                  <s:select id="episode_%{index}_oral_steroids" name="dsdCahVisitEpisodes[%{index}].oralSteroids" list="oralSteroidsOptions" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" theme="simple"/>
                </td>
                <td id="episode_tr_${index}_td_4" class="episode-table-td-width">
                  <s:select id="episode_%{index}_hc_injection" name="dsdCahVisitEpisodes[%{index}].hcInjection" list="yesNoNKOptions" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" theme="simple"/>
                </td>
                <td id="episode_tr_${index}_td_5" class="episode-table-td-width">
                  <s:select id="episode_%{index}_adrenal_crisis" name="dsdCahVisitEpisodes[%{index}].adrenalCrisis" list="yesNoNKOptions" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" theme="simple"/>
                </td>
                <td id="episode_tr_${index}_td_6" class="episode-table-td-width">
                  <s:select id="episode_%{index}_emergency" name="dsdCahVisitEpisodes[%{index}].emergencyAttendance" list="emergencyAttendanceOptions" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="- Please Select The Highest Level of Care -" theme="simple"/>
                </td>
                <td id="episode_tr_${index}_td_7" class="episode-table-td-width">
                  <s:select id="episode_%{index}_predisposing_condition" name="dsdCahVisitEpisodes[%{index}].predisposingCondition" list="predisposingConditionOptions" listKey="value" listValue="value" emptyOption="false" headerKey="" headerValue="--- Please Select ---" theme="simple"/>
                </td>
              </tr>
            </s:iterator>
          </tbody>
        </table>
      </div>
      <div class="yui3-widget-ft"></div>
    </div>
  </div>
</s:form>

<div id="dialog_box_div"></div>

<script type="text/javascript" language="javascript" class="init">
  jQuery(function ($) {
    // The dollar sign will equal jQuery in this scope
    $(document).ready(function () {
      $('#cah_visit_display_table').DataTable({
        "order": [[1, "asc"]],
        destroy: true,
        "processing": true,
        "autoWidth": false,
        "stateSave": true
      });
    });
  });
</script>

<jsp:include page="/jsp/page/page_foot.jsp"/>