<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>

<div id="create_nav_tab" class="section_nav_tab">
  <ul id="globalnav">
    <li>
      <s:url id="core_info_url" value="core_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="core_info_url" href="%{core_info_url}" theme="simple">Core Info & Diagnosis</s:a>
    </li>
    <li>
      <s:url id="diagnosis_url" value="clinical_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="diagnosis_url" href="%{diagnosis_url}" theme="simple">Clinical History & Biomaterials</s:a>
    </li>
    <li>
      <s:url id="assessment_url" value="assessment_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="assessment_url" href="%{assessment_url}" theme="simple">Assessments</s:a>
    </li>
    <li>
      <s:url id="gene_analysis_url" value="gene_analysis_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="gene_analysis_url" href="%{gene_analysis_url}" theme="simple">Gene Analysis</s:a>
    </li>
    <li>
      <s:url id="cah_url" value="cah_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="cah_url" href="%{cah_url}" theme="simple">CAH</s:a>
    </li>
  </ul>
</div>
<br/><br/><br/>