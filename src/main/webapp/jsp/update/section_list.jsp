<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<p>
  Case <b><s:property value="%{#parameters.registerId}"/></b> has been saved successfully.
</p>

<p>
  To continue updating other sections, please click on below links:
<ul>
  <li>
    <s:url id="identifier_url" value="core_view.action">
      <s:param name="registerId" value="%{#parameters.registerId}"/>
      <s:param name="mode" value="%{#parameters.mode}"/>
    </s:url>
    <s:a name="identifier_url" href="%{identifier_url}" theme="simple">Core Info & Diagnosis</s:a>
  </li>
  <li>
    <s:url id="diagnosis_url" value="clinical_view.action">
      <s:param name="registerId" value="%{#parameters.registerId}"/>
      <s:param name="mode" value="%{#parameters.mode}"/>
    </s:url>
    <s:a name="diagnosis_url" href="%{diagnosis_url}" theme="simple">Clinical History & Biomaterials</s:a>
  </li>
  <li>
    <s:url id="assessment_url" value="assessment_view.action">
      <s:param name="registerId" value="%{#parameters.registerId}"/>
      <s:param name="mode" value="%{#parameters.mode}"/>
    </s:url>
    <s:a name="assessment_url" href="%{assessment_url}" theme="simple">Assessments</s:a>
  </li>
  <li>
    <s:url id="gene_analysis_url" value="gene_analysis_view.action">
      <s:param name="registerId" value="%{#parameters.registerId}"/>
      <s:param name="mode" value="%{#parameters.mode}"/>
    </s:url>
    <s:a name="gene_analysis_url" href="%{gene_analysis_url}" theme="simple">Gene Analysis</s:a>
  </li>
  <li>
    <s:url id="cah_module_url" value="cah_view.action">
      <s:param name="registerId" value="%{#parameters.registerId}"/>
      <s:param name="mode" value="%{#parameters.mode}"/>
    </s:url>
    <s:a name="cah_module_url" href="%{cah_module_url}" theme="simple">CAH</s:a>
  </li>
</ul>
</p>
<p>
  <s:url id="url" value="../search/read.action">
    <s:param name="registerId" value="%{#parameters.registerId}"/>
    <s:param name="mode" value="%{#parameters.mode}"/>
  </s:url>
  To view the full record you just updated, please click
  <s:a name="view_upload_record" href="%{url}" theme="simple">
    here
  </s:a>.
</p>