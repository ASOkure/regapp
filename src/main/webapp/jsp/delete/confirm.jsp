<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<div id="delete_confirm_div">
  <h1>Are you sure you want to delete record <s:property value="registerId"/>?</h1>
  <br/>
  <s:form id="delete_confirm_form" action="delete" theme="simple">
    <s:hidden name="registerId" value="%{registerId}"/>
    <s:hidden name="mode" value="%{mode}"/>
    <table>
      <tbody>
      <tr>
        <td>
          <s:submit value="Confirm" action="delete_confirm" theme="simple"/>
          <s:submit value="Cancel" action="cancel" theme="simple"/>
        </td>
      </tr>
      <tr>
        <td>
          <h1>Identifiers</h1>
          <table id="view_table" class="query-page-table">
            <tr>
              <s:iterator value="dsdIdentifierSections" status="secs" >
              <s:if test="sectionId == 1" >
              <td class="query-page-table-td-left"></s:if >
                <s:if test="sectionId == 5" ></td>
              <td class="query-page-table-td-right"></s:if >
                <s:if test="sectionId == 3 || sectionId == 9" >
                <table class="section-table-bottom"></s:if >
                  <s:else >
                  <table class="section-table"></s:else >
                    <tr>
                      <td colspan="3"><h3><s:property value="name"/></h3></td>
                    </tr>
                    <s:iterator value="parameters" id="para" status="paras">
                      <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
                      <s:else>
                        <tr class="parameter-line-single">
                      </s:else>
                      <td class="parameter-label-add"><s:label value="%{label}"/></td>
                      <s:if test="paramId == 2">
                        <td style="background: transparent">
                          <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                          <br/>
                          <s:textfield name="%{'dsdIdentifier.' + menu.tertiaryParamName}" readonly="true" theme="simple" cssClass="display_only"/>
                        </td>
                      </s:if>
                      <s:elseif test="paramId == 11">
                        <td style="background: transparent">
                          <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                          <br/>
                          <s:textfield name="%{'dsdIdentifier.' + menu.tertiaryParamName}" readonly="true" theme="simple" cssClass="display_only"/>
                        </td>
                      </s:elseif>
                      <s:elseif test="type == 'text_area'">
                        <td>
                          <s:textarea name="%{paramName}" readonly="true" theme="simple" cssClass="free-text-area"/>
                        </td>
                      </s:elseif>
                      <s:else>
                        <td>
                          <s:textfield name="%{paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                        </td>
                      </s:else>
                      </tr>
                    </s:iterator>
                  </table>
                  </s:iterator >
                  </tr>
                </table>
                <br/><br/>
              </td>
            </tr>
            <tr>
              <td><h1>CAH</h1></td>
            </tr>
            <tr>
              <table class="section-table">
                <s:iterator value="dsdCahSection.parameters" status="paras">
                  <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
                  <s:else><tr class="parameter-line-single"></s:else>
                  <td class="parameter-label-read"><s:label value="%{label}"/></td>
                  <td>
                    <s:textfield name="%{'dsdIdentifier.' + paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                  </td>
                  </tr>
                </s:iterator>
              </table>
            </tr>
            <tr>
              <td>
                <h1>Assessments</h1>

                <div id="results_div">
                  <table id="assessments_table" class="dataTable display">
                    <thead>
                    <tr>
                      <s:iterator value="dsdAssessmentSection.parameters">
                        <th id="${paramId}"><s:label value="%{label}"/></th>
                      </s:iterator>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="dsdIdentifier.dsdAssessments" status="assessment">
                      <tr>
                        <s:iterator value="dsdAssessmentSection.parameters" status="para">
                          <td>
                            <s:if test="paramId == 24"><s:property value="assessmentDate"/></s:if>
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
                            <s:if test="paramId == 59"><s:property value="gestation"/></s:if>
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
              <td>
                <h1>Gene Analysis</h1>
                <table id="view_table" class="query-page-table">
                  <tr>
                    <td class="query-page-table-td-left">
                      <table class="section-table-bottom">
                        <s:iterator value="dsdGeneAnalysisSection.parameters" status="paras">
                          <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
                          <s:else><tr class="parameter-line-single"></s:else>
                          <td class="parameter-label-add"><s:label value="%{label}"/></td>
                          <td>
                            <s:textfield name="%{'dsdIdentifier.' + paramName}" readonly="true" theme="simple" cssClass="display_only"/>
                          </td>
                          </tr>
                        </s:iterator>
                      </table>
                    </td>
                    <td class="query-page-table-td-right">
                      <h3>Screened Genes</h3>

                      <div id="screen_gene_div">
                        <table id="screen_gene_table" class="dataTable display">
                          <thead>
                          <tr>
                            <th id="geneName">Gene Name</th>
                            <th id="sequenceMethod">Sequence method</th>
                            <th id="mutationFound">Mutation Found</th>
                            <th id="mutationDetail">Mutation Detail</th>
                          </tr>
                          </thead>
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
                        </table>
                      </div>
                    </td>
                  </tr>
                </table>
                <br/><br/>
              </td>
            </tr>
            <tr>
              <td>
                <h1>Consent & Sample</h1>

                <p>
                  Consent has been obtained to share this data with <b> <s:property value="consentString"/> </b>
                </p>

                <p>
                  Consent has <B><s:if test="dsdidentifier.sampleShare != 1">NOT</s:if></B>
                  been obtained to share sample & perform studies as outlined in the I-DSD programme
                </p>
              </td>
            </tr>
            <tr>
              <td>
                <s:submit value="Confirm" action="delete_confirm" theme="simple"/>
                <s:submit value="Cancel" action="delete_cancel" theme="simple"/>
              </td>
            </tr>
      </tbody>
    </table>
  </s:form>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>