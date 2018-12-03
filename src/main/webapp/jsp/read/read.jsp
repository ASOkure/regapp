<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<div id="view_record_div">
  <h1>Viewing Record ID <s:property value="registerId"/></h1><br/>
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <s:if test="isEditable">
    <div id="buttons_top" align="left">
      <s:url id="editURL" value="../update/core_view.action">
        <s:param name="registerId" value="registerId"/>
        <s:param name="mode" value="mode"/>
      </s:url>

      <s:url id="cancelURL" value="cancel">
        <s:param name="mode" value="mode"/>
      </s:url>

      <s:url id="referPatientURL" value="../search/refer_patient_view.action">
        <s:param name="registerId" value="registerId"/>
        <s:param name="mode" value="mode"/>
      </s:url>

      <s:a name="%{'edit_record_' + registerId}" href="%{editURL}" cssStyle="font-size: 120%; font-weight: bold;">Edit</s:a> |
      <s:a name="%{'refer_patient_access_record_' + registerId}" href="%{referPatientURL}" cssStyle="font-size: 120%; font-weight: bold;">Provide Participant Access</s:a> |
      <s:a href="%{cancelURL}" cssStyle="font-size: 120%; font-weight: bold;">Cancel</s:a><br/><br/>
    </div>
  </s:if>
  <table id="column_table" class="query-page-table">
    <tr id="column_tr">
      <s:iterator value="dsdIdentifierSections" status="secs">
      <s:if test="sectionId == 1">
      <td id="column_td_left" <s:if test="isFullView">class="query-page-table-td-left"</s:if>>
        </s:if>
        <s:if test="sectionId == 5"></td>
      <td id="column_td_right" class="query-page-table-td-right"></s:if>

        <s:if test="sectionId == 3 || sectionId == 8">
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
                <td id="section_td_parameter_label_${paramId}" class="parameter-label-add">
                  <s:label id="parameter_label" value="%{label}" theme="simple"/>
                  <s:if test="paramId == 12"><span><small> (gram)</small></span></s:if>
                  <s:if test="paramId == 13"><span><small> (cm)</small></span></s:if>
                  <s:if test="paramId == 25"><span><small> (mm)</small></span></s:if>
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
        <s:if test="dsdIdentifier.dsdCah != null">
    <tr id="cah_section_tr_caption">
      <td><h1>CAH</h1></td>
    </tr>
    <tr id="cah_section_tr">
      <td id="cah_section_td">
        <table id="cah_table" class="section-table">
          <s:iterator value="dsdCahSection.parameters" status="paras">
            <tr id="cah_table_tr_${paramId}"
                <s:if test="#paras.odd == true">class="parameter-line-double"</s:if>
                <s:else>class="parameter-line-single"</s:else>
            >
              <td id="cah_table_td_label" class="parameter-label-read">
                <s:label value="%{label}" theme="simple"/>
                <s:if test="paramId ==72"><span><small> (gram)</small></span></s:if>
                <s:if test="paramId >= 73 && paramId <= 76"><span><small> (cm)</small></span></s:if>
              </td>
              <td id="cah_table_td_value">
                <s:if test="type == 'text_area'">
                  <s:textarea name="%{'dsdIdentifier.' + paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                </s:if>
                <s:else>
                  <s:textfield name="%{'dsdIdentifier.' + paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                </s:else>
              </td>
            </tr>
          </s:iterator>
        </table>
      </td>
    </tr>
    <s:if test="isFullView">
      <tr><h1>CAH Visit</h1></tr>
      <tr>
        <td>
          <div id="cah_visit_div">
            <table id="cah_visit_display_table" class="dataTable display">
              <thead id="cah_visit_display_thead">
                <tr>
                  <th id="id">ID</th>
                  <th id="visit_date">Date</th>
                  <th id="age">Age</th>
                  <th id="bmi">BMI</th>
                  <th id="bsa">BSA</th>
                  <th id="systolic">Systolic</th>
                  <th id="diastolic">Diastolic</th>
                  <th id="hypertensive">Hypertensive</th>
                  <th id="sick_day_episodes">Sick Day Episodes</th>
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
                    <td id="hypertensive_value"><s:property value="hypertensive"/></td>
                    <td id="sick_day_episodes_value"><s:property value="sickDayEpisodes"/></td>
                    <td id="treatment_change_reason_value"><s:property value="treatmentChangeReason"/></td>
                    <td>
                      <s:url id="url" value="read_cah_visit.action">
                        <s:param name="registerId" value="%{registerId}"/>
                        <s:param name="dsdCahVisitId" value="%{dsdCahVisitId}"/>
                        <s:param name="mode" value="%{mode}"/>
                      </s:url>
                      <s:a name="%{'read_cah_visit_' + dsdCahVisitId}" href="%{url}" theme="simple">View</s:a>
                    </td>
                  </tr>
                </s:iterator>
              </tbody>
            </table>
          </div>
          <br/><br/>
        </td>
      </tr>
    </s:if>
    </s:if>
    <s:if test="isFullView">
      <tr>
        <td><h1>Assessments</h1></td>
      </tr>
      <tr>
        <td>
          <div id="results_div">
            <table id="assessments_table" class="dataTable display">
              <thead>
                <tr>
                  <s:iterator value="dsdAssessmentSection.parameters">
                    <th id="${paramId}"><s:label value="%{label}" theme="simple"/></th>
                  </s:iterator>
                </tr>
              </thead>
              <tbody>
                <s:iterator value="dsdIdentifier.dsdAssessments" status="assessment">
                  <tr>
                    <s:iterator value="dsdAssessmentSection.parameters" status="para">
                      <td>
                        <s:if test="paramId == 24"><s:date name="assessmentDate" format="yyyy-MM-dd"/></s:if>
                        <s:if test="paramId == 25"><s:property value="phallusLength"/></s:if>
                        <s:if test="paramId == 26"><s:property value="phallusSize"/></s:if>
                        <s:if test="paramId == 27"><s:property value="urinaryMeatus"/></s:if>
                        <s:if test="paramId == 28"><s:property value="labioscrotalFusion"/></s:if>
                        <s:if test="paramId == 29"><s:property value="rightGonad"/></s:if>
                        <s:if test="paramId == 30"><s:property value="leftGonad"/></s:if>
                        <s:if test="paramId == 31"><s:property value="mullerianStructure"/></s:if>
                        <s:if test="paramId == 32"><s:property value="wolffianStructure"/></s:if>
                        <s:if test="paramId == 33"><s:property value="ems"/></s:if>
                        <s:if test="paramId == 34"><s:property value="modifiedPrader"/></s:if>
                        <s:if test="paramId == 35"><s:property value="tannerStage"/></s:if>
                        <s:if test="paramId == 58"><s:property value="gonadectomy"/></s:if>
                        <s:if test="paramId == 60"><s:property value="freeText"/></s:if>
                      </td>
                    </s:iterator>
                  </tr>
                </s:iterator>
              </tbody>
            </table>
          </div>
          <br/><br/>
        </td>
      </tr>
      <tr>
        <td><h1>Gene Analysis</h1></td>
      </tr>
      <tr>
        <td>
          <table id="gene_page_table" class="query-page-table">
            <tr>
              <td id="gene_column_left" class="query-page-table-td-left">
                <table id="gene_section_table" class="section-table-bottom">
                  <s:iterator value="dsdGeneAnalysisSection.parameters" status="paras">
                    <tr id="gene_analysis_section_tr_${paramId}"
                        <s:if test="#paras.odd == true">class="parameter-line-double"</s:if>
                        <s:else>class="parameter-line-single"</s:else>
                    >
                      <td class="parameter-label-read"><s:label value="%{label}" theme="simple"/></td>
                      <td>
                        <s:textfield name="%{'dsdIdentifier.' + paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                      </td>
                    </tr>
                  </s:iterator>
                </table>
              </td>
              <td id="gene_analysis_column_right" class="query-page-table-td-right">
                <h3>Screened Genes</h3>
                <div id="gene_screen_div">
                  <table id="gene_screen_section_table" class="dataTable display">
                    <thead>
                      <tr>
                        <th id="geneName">Gene Name</th>
                        <th id="sequenceMethod">Sequence method</th>
                        <th id="mutationFound">Mutation Found</th>
                        <th id="mutationDetail">Mutation Detail</th>
                      </tr>
                    </thead>
                    <tbody>
                      <s:iterator value="dsdIdentifier.dsdGeneAnalysis.DsdGeneScreeneds" status="paras">
                        <tr>
                          <td><s:property value="gene.geneName"/></td>
                          <td>
                            <s:if test="directSequence == 'Yes'">Direct Sequence</s:if>
                            <s:elseif test="directSequence == 'No'"><s:property value="otherSequence"/></s:elseif>
                            <s:else>Unknown</s:else>
                          </td>
                          <td><s:property value="mutationFound"/></td>
                          <td><s:property value="mutationDetail"/></td>
                        </tr>
                      </s:iterator>
                    </tbody>
                  </table>
                </div>
              </td>
            </tr>
          </table>
          <br/><br/>
        </td>
      </tr>
    </s:if>
    </tbody>
  </table>
  <div id="consent_div">
    Consent has been obtained to share this data with <b><s:property value="consentString"/></b>
  </div>
  <div id="sample_div">
    Consent has
    <B><s:if test="dsdIdentifier.sampleShared != 1">NOT</s:if></B> been obtained to share sample & perform studies as outlined in the DSD Network programme
  </div>
  <br/>
  <s:if test="isEditable">
    <div id="buttons_bottom" align="left">
      <s:a name="%{'edit_record_' + registerId}" href="%{editURL}" cssStyle="font-size: 120%; font-weight: bold;">Edit</s:a> |
      <s:a name="%{'refer_patient_access_record_' + registerId}" href="%{referPatientURL}" cssStyle="font-size: 120%; font-weight: bold;">Provide Participant Access</s:a> |
      <s:a href="%{cancelURL}" cssStyle="font-size: 120%; font-weight: bold;">Cancel</s:a><br/><br/>
    </div>
  </s:if>
</div>
<!--end of view_record_div-->

<h1>Research Result File</h1>
<div id="research_result_div">
  <table id="research_result_table" class="dataTable display">
    <thead>
      <tr>
        <th id="fileId">Research File ID</th>
        <th id="fileCategory">Category</th>
        <th id="fileUploadTime">Timestamp</th>
        <th id="fileComment">Comment</th>
        <th id="file">Research Result File</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="researchResults" status="m">
        <tr>
          <td><s:property value="resultId"/></td>
          <td><s:property value="categoryName"/></td>
          <td><s:date name="uploadTime" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:property value="comment"/></td>
          <td>
            <s:if test="allow">
              <s:url id="fileDownload" namespace="/" action="download">
                <s:param name="pdfId" value="%{resultId}"/>
              </s:url>
              <s:a href="%{fileDownload}"><s:property value="fileName"/></s:a>
            </s:if>
            <s:else>No Access Granted</s:else>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
</div>

<h1>History</h1>
<div id="audit_history_div" class="history">
  <table id="audit_history_table" class="dataTable display">
    <thead>
      <tr>
        <th id="timestamp">Timestamp</th>
        <th id="action">Activities</th>
        <th id="username">Performed By</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="recordHistory" status="h">
        <tr>
          <td><s:date name="timestamp" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:property value="action"/></td>
          <td><s:property value="username"/></td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
</div>

<h1>Patient Access</h1>
<div id="patient_access_div" class="history">
  <table id="patient_access_table" class="dataTable display">
    <thead>
      <tr>
        <%--<th id="patient_account_name">Patient Account</th>--%>
        <th id="granted_by">Participant Access Provided By</th>
        <th id="granted_on">Participant Access Provided On</th>
        <th id="fist_login">Participant First Login</th>
        <th id="last_login">Participant Last Login</th>
        <th id="login_counter">Participant Login Counter</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="patientUsers" status="h">
        <tr>
            <%--<td><s:property value="username"/></td>--%>
          <td><s:property value="introducedBy.username"/></td>
          <td><s:date name="registerTime" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:date name="firstLogin" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:date name="lastLogin" format="yyyy-MM-dd HH:mm:ss"/></td>
          <td><s:property value="loginCounter"/></td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>