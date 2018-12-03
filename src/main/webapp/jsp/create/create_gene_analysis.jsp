<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  YUI().use("node", function (Y) {
    Y.on("domready", create_gene_analysis_init);
  })
</script>

<h1>Upload Record to Database - Genetic Analysis</h1>

<s:if test="registerId != null">
  <h2>Register ID: <s:property value="registerId"/></h2>
</s:if>

<s:include value="tabs.jsp"/>

<div id="tips"></div>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<div id="single_gene_init_value" class="div_hidden">
  <table>
    <thead>
    <tr>
      <th>Gene Id</th>
      <th>Gene Name</th>
      <th>Gene Description</th>
      <th>Sequence method</th>
      <th>Other method</th>
      <th>Mutation Found</th>
      <th>Mutation Detail</th>
      <th>Screen Id</th>
    </tr>
    </thead>
    <tbody id="single_gene_init_tbody">
    <s:iterator value="dsdGeneAnalysis.DsdGeneScreeneds" status="paras">
      <tr id="single_gene_init_tr_<s:property value="gene.geneId" />">
        <td><s:property value="gene.geneId"/></td>
        <td><s:property value="gene.geneName"/></td>
        <td><s:property value="gene.description"/></td>
        <td><s:property value="directSequence"/></td>
        <td><s:property value="otherSequence"/></td>
        <td><s:property value="mutationFound"/></td>
        <td><s:property value="mutationDetail"/></td>
        <td><s:property value="screenId"/></td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</div>

<s:form id="gene_analysis_form" action="save_gene_analysis" theme="simple">
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <table class="gene_analysis_page_table" >
  <tr>
    <td colspan='3' class="button_td">
      <button type="submit" value="save" name="buttonName">Save Gene Analysis</button>
    </td>
  </tr>
  <tr class="gene-analysis-line-double">
  <td class="first_column">
    <div class="gene_analysis_label_div"><s:label value="%{geneticAnalysis.label}"/></div>
  </td>
  <td class="gene_analysis_tooltip_td">
    <s:div id="%{'tooltip_' + geneticAnalysis.paramId}" cssClass="yui3-hastooltip" tooltip="%{geneticAnalysis.tooltip}">[?]</s:div>
  </td>
  <td class="gene_analysis_mandatory_td"><s:if test="geneticAnalysis.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
  <td class="first_field_column">
    <s:select name="dsdGeneAnalysis.geneticAnalysis" list="yesNoNKOptions" listKey="value" listValue="value"
              emptyOption="false" headerKey="" headerValue="- Select -" theme="simple"/>
  </td>
  <td class="sperate_line" colspan="2">
  <s:if test="dsdGeneAnalysis.geneticAnalysis == 'Yes'"><table id="gene_secondary_table" class="secondary_table" ></s:if>
  <s:else><table id="gene_secondary_table" class="secondary_table_hidden" ></s:else>
  <tr class="gene_secondary_table_tr">
    <td class="gene_secondary_table_td_label">
      <div class="gene_analysis_label_div"><s:label value="%{singleGeneAnalysis.label}"/></div>
    </td>
    <td class="gene_analysis_tooltip_td">
      <s:div id="%{'tooltip_' + singleGeneAnalysis.paramId}" cssClass="yui3-hastooltip" tooltip="%{singleGeneAnalysis.tooltip}">[?]</s:div>
    </td>
    <td class="gene_analysis_mandatory_td"><s:if test="singleGeneAnalysis.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
    <td class="gene_secondary_table_td">
      <s:select name="dsdGeneAnalysis.singleGeneAnalysis" list="yesNoNKOptions" listKey="value" listValue="value"
                emptyOption="false" headerKey="" headerValue="- Select -" theme="simple"/>
    </td>
    <td class="gene_secondary_table_td_right" colspan="2">
      <div id="screened_gene_edit_button_div" class="screened_gene_edit_button_class_hidden">
        <br/>
        <s:a id="screened_gene_edit" name="screened_gene_edit_button" href="#">Change Gene Selection</s:a>
        <br/><br/>
      </div>
      <s:if test="dsdGeneAnalysis.singleGeneAnalysis == 'Yes' && dsdGeneAnalysis.DsdGeneScreeneds.size > 0" >
      <table class="tertiary_table" id="single_analysis_table" border="1">
        </s:if >
        <s:else >
        <table class="tertiary_table_hidden" id="single_analysis_table" border="1">
          </s:else >
          <thead>
          <tr>
            <th>Gene</th>
            <th class="direct_sequence_th">Direct Sequence</th>
            <th class="mutation_found_th">Mutation Found</th>
          </tr>
          </thead>
          <tbody id="single_analysis_tbody">
          <s:iterator value="dsdGeneScreeneds" status="screens">
            <tr id="gene_selection_tr_<s:property value="gene.geneId"/>">
              <td id="gene_selection_gene_td_<s:property value="gene.geneId"/>" class="tertiary_table_td_desc">
                <div id="sequence_gene_name_<s:property value="gene.geneId"/>" class="gene_name">
                  <s:label value="%{gene.geneName}"/>
                </div>
                <div id="sequence_gene_desc_<s:property value="gene.geneId"/>" class="gene_description"><s:label value="%{gene.description}"/></div>
                <s:hidden id="%{'sequence_gene_id_' + gene.geneId}"
                          name="dsdGeneScreeneds[%{#screens.index}].gene.geneId"
                          value="%{gene.geneId}"/>
                <s:hidden id="%{'screen_id_' + screenId}"
                          name="dsdGeneScreeneds[%{#screens.index}].screenId"
                          value="%{screenId}"/>
              </td>
              <td id="gene_selection_sequence_td_<s:property value="gene.geneId"/>">
                <s:select id="sequence_select_%{gene.geneId}" name="dsdGeneScreeneds[%{#screens.index}].directSequence" list="yesNoOptions" listKey="value" listValue="value"
                          emptyOption="false" headerKey="" headerValue="- Select -"
                          cssClass="gene_sequence_select"/>
                <s:if test="directSequence == 'No'" >
                <div id="sequence_text_<s:property value="gene.geneId"/>_div" class="div_shown">
                  </s:if >
                  <s:else >
                  <div id="sequence_text_<s:property value="gene.geneId"/>_div" class="div_hidden">
                    </s:else >
                    <div>Other method:</div>
                    <s:textarea id="sequence_text_%{gene.geneId}" rows="2" cols="20"
                                name="dsdGeneScreeneds[%{#screens.index}].otherSequence"
                                cssClass="gene_sequence_text"/>
                  </div>
              </td>
              <td id="gene_selection_mutation_td_<s:property value="gene.geneId"/>">
                <s:checkbox id="gene_mutation_checkbox_%{gene.geneId}"
                            name="dsdGeneScreeneds[%{#screens.index}].mutationFound"
                            cssClass="gene_mutation_checkbox"/>
                <s:if test="mutationFound == true" >
                <div id="mutation_detail_<s:property value="gene.geneId"/>_div" class="div_shown">
                  </s:if >
                  <s:else >
                  <div id="mutation_detail_<s:property value="gene.geneId"/>_div" class="div_hidden">
                    </s:else >
                    <div id="mutation_detail_<s:property value="gene.geneId"/>">Mutation Details:</div>
                    <s:textarea id="mutation_text_%{gene.geneId}" rows="2" cols="20"
                                name="dsdGeneScreeneds[%{#screens.index}].mutationDetail"
                                cssClass="gene_mutation_text"/>
                  </div>
              </td>
            </tr>
          </s:iterator>
          </tbody>
        </table>
        </td>
        </tr>
        <tr class="gene_secondary_table_tr">
          <td class="gene_secondary_table_td_label">
            <div class="gene_analysis_label_div"><s:label value="%{chromosomalRearrangement.label}"/></div>
          </td>
          <td class="gene_analysis_tooltip_td">
            <s:div id="%{'tooltip_' + chromosomalRearrangement.paramId}" cssClass="yui3-hastooltip" tooltip="%{chromosomalRearrangement.tooltip}">[?]</s:div>
          </td>
          <td class="gene_analysis_mandatory_td"><s:if test="chromosomalRearrangement.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
          <td class="gene_secondary_table_td">
            <s:select name="dsdGeneAnalysis.chromosomalRearrangement" list="yesNoNKOptions" listKey="value" listValue="value"
                      emptyOption="false" headerKey="" headerValue="- Select -"/>
          </td>
          <td class="gene_secondary_table_td_right">
            <s:if test="dsdGeneAnalysis.chromosomalRearrangement == 'Yes'" >
            <div id="chromosomal_rearrangement_detail" class="div_shown"></s:if >
              <s:else >
              <div id="chromosomal_rearrangement_detail" class="div_hidden"></s:else >
                <div class="gene_analysis_label_div"><s:label value="%{chromosomalRearrangementDetail.label}"/></div>
                <s:div id="%{'tooltip_' + chromosomalRearrangementDetail.paramId}" cssClass="yui3-hastooltip" tooltip="%{chromosomalRearrangementDetail.tooltip}">[?]</s:div>
                <s:if test="chromosomalRearrangementDetail.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else>
                <div class="gene_analysis_field_div"><s:textarea name="dsdGeneAnalysis.chromosomalRearrangementDetail" cssClass="free-text-area"/></div>
              </div>
          </td>
        </tr>
        <tr class="gene_secondary_table_tr_bottom">
          <td class="gene_secondary_table_td_label">
            <div class="gene_analysis_label_div"><s:label value="%{cgh.label}"/></div>
          </td>
          <td class="gene_analysis_tooltip_td">
            <s:div id="%{'tooltip_' + cgh.paramId}" cssClass="yui3-hastooltip" tooltip="%{cgh.tooltip}">[?]</s:div>
          </td>
          <td class="gene_analysis_mandatory_td"><s:if test="cgh.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
          <td class="gene_secondary_table_td_bottom">
            <s:select name="dsdGeneAnalysis.cgh" list="yesNoNKOptions" listKey="value" listValue="value"
                      emptyOption="false" headerKey="" headerValue="- Select -"/>
          </td>
          <td class="gene_secondary_table_td_bottom">
            <s:if test="dsdGeneAnalysis.cgh == 'Yes'" >
            <div id="cgh_detail" class="div_shown"></s:if >
              <s:else >
              <div id="cgh_detail" class="div_hidden"></s:else >
                <div class="gene_analysis_label_div"><s:label value="%{cghDetail.label}"/></div>
                <s:div id="%{'tooltip_' + cghDetail.paramId}" cssClass="yui3-hastooltip" tooltip="%{cghDetail.tooltip}">[?]</s:div>
                <s:if test="cghDetail.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else>
                <div class="gene_analysis_field_div"><s:textarea name="dsdGeneAnalysis.cghDetail" cssClass="free-text-area"/></div>
              </div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td class="first_column">
      <div class="gene_analysis_label_div"><s:label value="%{functionalStudy.label}"/></div>
    </td>
    <td class="gene_analysis_tooltip_td">
      <s:div id="%{'tooltip_' + functionalStudy.paramId}" cssClass="yui3-hastooltip" tooltip="%{functionalStudy.tooltip}">[?]</s:div>
    </td>
    <td class="gene_analysis_mandatory_td"><s:if test="functionalStudy.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
    <td class="first_field_column">
      <s:select name="dsdGeneAnalysis.functionalStudy" list="yesNoNKOptions" listKey="value" listValue="value"
                emptyOption="false" headerKey="" headerValue="- Select -"/>
    </td>
    <!-- <td class="sperate_line"> -->
    <td class="gene_secondary_table_td">
      <s:if test="dsdGeneAnalysis.functionalStudy == 'Yes'" >
      <div id="functional_study_detail" class="div_shown"></s:if >
        <s:else >
        <div id="functional_study_detail" class="div_hidden"></s:else >
          <div class="gene_analysis_label_div"><s:label value="%{functionalStudyDetail.label}"/></div>
          <s:div id="%{'tooltip_' + functionalStudyDetail.paramId}" cssClass="yui3-hastooltip" tooltip="%{functionalStudyDetail.tooltip}">[?]</s:div>
          <s:if test="functionalStudyDetail.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else>
          <div class="gene_analysis_field_div"><s:textarea name="dsdGeneAnalysis.functionalStudyDetail" cssClass="free-text-area"/></div>
        </div>
    </td>
  </tr>
  <tr class="gene-analysis-line-double">
    <td class="first_column">
      <div class="gene_analysis_label_div"><s:label value="%{publishedCase.label}"/></div>
    </td>
    <td class="gene_analysis_tooltip_td">
      <s:div id="%{'tooltip_' + publishedCase.paramId}" cssClass="yui3-hastooltip" tooltip="%{publishedCase.tooltip}">[?]</s:div>
    </td>
    <td class="gene_analysis_mandatory_td"><s:if test="publishedCase.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
    <td class="first_field_column">
      <s:select name="dsdGeneAnalysis.publishedCase" list="yesNoNKOptions" listKey="value" listValue="value"
                emptyOption="false" headerKey="" headerValue="- Select -"/>
    </td>
    <!-- <td class="sperate_line"> -->
    <td class="gene_secondary_table_td">
      <s:if test="dsdGeneAnalysis.publishedCase == 'Yes'" >
      <div id="publish_detail" class="div_shown"></s:if >
        <s:else >
        <div id="publish_detail" class="div_hidden"></s:else >
          <div class="gene_analysis_label_div"><s:label value="%{publishDetail.label}"/></div>
          <s:div id="%{'tooltip_' + publishDetail.paramId}" cssClass="yui3-hastooltip" tooltip="%{publishDetail.tooltip}">[?]</s:div>
          <s:if test="publishDetail.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else>
          <div class="gene_analysis_field_div"><s:textarea name="dsdGeneAnalysis.publishDetail" cssClass="free-text-area"/></div>
        </div>
    </td>
  </tr>
  <tr>
    <td class="first_column">
      <div class="gene_analysis_label_div"><s:label value="%{furtherStudies.label}"/></div>
    </td>
    <td class="gene_analysis_tooltip_td">
      <s:div id="%{'tooltip_' + furtherStudies.paramId}" cssClass="yui3-hastooltip" tooltip="%{furtherStudies.tooltip}">[?]</s:div>
    </td>
    <td class="gene_analysis_mandatory_td"><s:if test="furtherStudies.mandatory == 1"> * </s:if><s:else>&nbsp;</s:else></td>
    <td class="first_field_column">
      <s:select name="dsdGeneAnalysis.furtherStudies" list="yesNoNKOptions" listKey="value" listValue="value"
                emptyOption="false" headerKey="" headerValue="- Select -"/>
    </td>
    <!-- <td class="sperate_line" colspan="2"></td> -->
    <td class="gene_secondary_table_td"></td>
  </tr>
  <tr>
    <td colspan='3' class="button_td">
      <button type="submit" value="save" name="buttonName">Save Gene Analysis</button>
    </td>
  </tr>
  </table>
</s:form>

<jsp:include page="/jsp/page/gene_table.jsp"/>

<jsp:include page="/jsp/page/page_foot.jsp"/>