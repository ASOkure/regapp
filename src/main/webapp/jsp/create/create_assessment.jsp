<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  YUI().use("node", function (Y) {
    Y.on("domready", create_assessment_init);
  })
</script>

<h1>Upload Record to Database - Assessment History</h1>

<s:if test="registerId != null">
  <h2>Register ID: <s:property value="registerId"/></h2>
</s:if>

<s:include value="tabs.jsp"/>

<div id="tips">
  <p>Please enter the information for one record to upload into the registry database. Enter the appropriate parameters by either selecting from the drop-down menu or entering the relevant string value.</p>
</div>
<s:form id="upload_assessment" theme="simple" cssClass='required-validate'>
  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <div id="assessments_div">
    <table id="assessments_display" class="dataTable display">
      <thead>
      <tr>
        <s:iterator value="dsdAssessmentSection.parameters">
          <th id="${paramId}"><s:label value="%{label}"/></th>
        </s:iterator>
        <th id="999999">Action</th>
      </tr>
      </thead>
      <tbody>
      <s:iterator value="dsdIdentifier.dsdAssessments" status="assessments">
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
              <s:if test="paramId == 59"><s:property value="gestation"/></s:if>
              <s:if test="paramId == 60"><s:property value="freeText"/></s:if>
            </td>
          </s:iterator>
          <td>
            <s:url id="url" value="edit_assessment.action">
              <s:param name="registerId" value="%{registerId}"/>
              <s:param name="assessmentId" value="%{assessmentId}"/>
              <s:param name="mode" value="%{mode}"/>
            </s:url>
            <s:a name="%{assessmentDate}" href="%{url}" theme="simple">edit</s:a>
            <s:url id="url" value="remove_assessment.action">
              <s:param name="registerId" value="%{registerId}"/>
              <s:param name="assessmentId" value="assessmentId"/>
              <s:param name="mode" value="%{mode}"/>
            </s:url>
            <s:a name="%{assessmentDate}" href="%{url}" theme="simple">remove</s:a>
          </td>
        </tr>
      </s:iterator>
      </tbody>
    </table>
  </div>
  <br/><br/>
  <s:actionerror cssClass="error_message"/>
  <s:fielderror cssClass="error_message"/>
  <br/><br/>
  <s:hidden name="dsdAssessment.assessmentId" value="%{assessmentId}"></s:hidden>

  <div id="assessment_div">
    <div id="assessment_float_left">
      <table class="section-table-assessment">
        <s:iterator value="dsdAssessmentSection.parameters" status="paras">
          <s:if test="#paras.odd == true"><tr class="parameter-line-double"></s:if>
          <s:else><tr class="parameter-line-single"></s:else>
          <td><s:label value="%{label}"/></td>
          <td id="<s:property value="%{'td_tooltip_' + paramId}" />" class="tooltip_td">
            <div class="yui3-hastooltip" title="<s:property value="tooltip" />" id="<s:property value="%{'tooltip_' + paramId}" />">[?]</div>
          </td>
          <td class="mandatory"><s:if test="mandatory == 1"> * </s:if><s:else> &nbsp;</s:else></td>
          <td>
            <s:if test="type == 'menu'">
              <s:if test="mandatory == 1">
                <s:select name="%{paramName}" label="%{label}" list="menu.options" listKey="value" listValue="value"
                          emptyOption="false" headerKey="" headerValue="--- Please Select ---" cssClass='required validate-selection'/>
              </s:if>
              <s:else>
                <s:select name="%{paramName}" label="%{label}" list="menu.options" listKey="value" listValue="value"
                          emptyOption="false" headerKey="" headerValue="--- Please Select ---" cssClass='validate-selection'/>
              </s:else>
              <s:if test="paramId >= 26 && paramId <= 30">
                <s:div id="%{'ems_' + paramName}">
                  <s:iterator value="menu.options" status="options">
                    <s:hidden name="%{paramName + '_' + #options.index}" value="%{score}" disabled="true"/>
                  </s:iterator>
                </s:div>
              </s:if>
            </s:if>
            <s:if test="type == 'auto_fill'">
              <s:textfield name="%{paramName}" cssClass='required float-range-0-12 auto-text' readonly="true"/>
            </s:if>
            <s:if test="type == 'date'">
              <s:textfield id="date_field" name="%{paramName}" cssClass='required validate-date-dd/MM/yyyy'/>
              <button type="button" id="show_button" title="Show Calendar">
                <img src="<%=path %>/images/calbtn.gif" width="18" height="18" alt="Calendar" alt="calendar_button"/>
              </button>
            </s:if>
            <s:if test="type == 'float'">
              <s:textfield name="%{paramName}" label="%{label}" cssClass="float-range-0-200"/>
              <s:if test="paramId == 25"><span><small>mm</small></span></s:if>
            </s:if>
            <s:if test="type == 'radio'">
              <s:radio name="%{paramName}" list="menu.options" listKey="value" listValue="value" cssClass='validate-one-required'></s:radio>
            </s:if>
            <s:if test="type == 'text_area'">
              <s:textarea name="%{paramName}" cssClass="free-text-area max-length-10000"/>
            </s:if>
          </td>
          </tr>
        </s:iterator>
        <tr>
          <td colspan="4" align="right" class="button_td">
            <s:submit value="Save Assessment" theme="simple" action="add_assessment"/>
            <s:submit value="Next Section" theme="simple" action="submit_assessment"/>
          </td>
        </tr>
      </table>
      <tr>
        <td colspan="3" align="right" class="button_td">
          <s:submit value="Save Assessment" theme="simple" action="add_assessment"/>
          <s:submit value="Next Section" theme="simple" action="submit_assessment"/>
        </td>
      </tr>
    </div>
    <div id="assessment_float_right">
      <script type="text/javascript">
        var flashvars = {};
        var params = {};
        params.base = ".";
        params.wmode = "transparent";
        var attributes = {};
        swfobject
            .embedSWF(
            "<%=path%>/media/HTBW_SexDev_4-03_CAH_Prader_EN.swf",
            "flashContent1198ffbb-6a58-4cef-b158-0b3fd41d48fe1",
            "590", "400", "9.0.0", "expressInstall.swf", flashvars,
            params, attributes);
      </script>
      <div id=swfobj1198ffbb-6a58-4cef-b158-0b3fd41d48fe>
        <div id=flashContent1198ffbb-6a58-4cef-b158-0b3fd41d48fe1>
          <div style='width:590px'>
            <a href="http://www.adobe.com/go/getflashplayer"><img
                src="http://wwwimages.adobe.com/www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
                alt="Get Adobe Flash player"/></a>
          </div>
          <div class="ExternalClass53F6F974C6FB48CBA627ABA173EF0E5E">
            <p style="font-family:calibri">
					<span style="font-size:12pt">An interactive Flash diagram
						depicting the Prader scale, a scale often used to describe what a
						baby’s external genitals look like. At one end of the scale, the
						genitals look like a typical girl’s, at the other end, they look
						like a typical boy’s, and in between there are variations on these
						patterns—if a baby has a uterus and vagina, the place where the
						vagina opens may vary as well. Drag the slider along the Prader
						Scale to see how genitals appear at each different stage, and
						roll-over the diagram for labels to see where specific parts are
						located.</span>​
            </p>

            <p></p>
          </div>
        </div>
      </div>
      © 2004-2013 <a href="http://www.aboutkidshealth.ca/En/HowTheBodyWorks/SexDevelopmentAnOverview/CongenitalAdrenalHyperplasiaCAH/Pages/ThePraderScale.aspx" target="_blank">AboutKidsHealth</a>

      <script type="text/javascript">
        var flashvars = {};
        var params = {};
        params.base = ".";
        params.wmode = "transparent";
        var attributes = {};
        swfobject
            .embedSWF(
            "<%=path%>/media/HTBW_SexDev_5-03_hypospadias_classifying_EN.swf",
            "flashContente6ac539d-8770-4824-92e3-96f49b0bdcdc1",
            "590", "400", "9.0.0", "expressInstall.swf",
            flashvars, params, attributes);
      </script>
      <div id=swfobje6ac539d-8770-4824-92e3-96f49b0bdcdc>
        <div id=flashContente6ac539d-8770-4824-92e3-96f49b0bdcdc1>
          <div style='width:590px'>
            <a href="http://www.adobe.com/go/getflashplayer"><img
                src="http://wwwimages.adobe.com/www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
                alt="Get Adobe Flash player"/></a>
          </div>
          <div class="ExternalClass838CBF6F4DCE428298E9097293602493">
            <p style="font-family:calibri">
					<span style="font-size:12pt">An interactive Flash diagram
						depicting the various classifications of hypospadias.
						Hypospadias is classified according to where the urethral
						opening is: Glanular or Subcornal Hypospadias have the urethral
						opening at the anterior of the penis; Distal Penile, Midshaft,
						or Proximal Penile Hypospadias have it somewhere along the
						middle underside; Penoscrotal, Scrotal, or Perineal Hypospadias
						have the urethral opening located at the posterior of the penis.
						Drag the slider or select from the list of classifications,
						and/or toggle the potential downward curve effect of hypospadias
						(chordee) on and off for each, to examine the physical
						differences.</span>​
            </p>

            <p></p>
          </div>
        </div>
      </div>
      © 2004-2013 <a href="http://www.aboutkidshealth.ca/En/HowTheBodyWorks/SexDevelopmentAnOverview/Hypospadias/Pages/ClassificationofHypospadias.aspx" target="_blank">AboutKidsHealth</a>
    </div>
  </div>

</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>