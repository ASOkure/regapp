<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  YUI().use("node", function (Y) {
    Y.on("domready", result_access_select_all);
  })
</script>

<h1>Research result file upload</h1>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="research_result_upload_form" action="uploadResultPDF" method="POST" theme="simple" enctype="multipart/form-data">
  <s:hidden name="mode" value="%{mode}"/>

  <table>
    <tbody>
      <tr>
        <td><s:label key="Research Result Category"/></td>
        <td><s:select name="categoryId" list="categories" listKey="categoryId" listValue="name"/></td>
      </tr>
      <tr>
        <td><s:label key="Register ID"/></td>
        <td><s:select name="researchResult.dsdIdentifier.registerId" list="registerIds"/></td>
      </tr>
      <tr>
        <td><s:label key="Research Result File"/></td>
        <td><s:file name="upload"/></td>
      </tr>
      <tr>
        <td><s:label key="File Description"/></td>
        <td><s:textarea name="researchResult.comment"/></td>
      </tr>
    </tbody>
  </table>

  <h2>Select the user you want to share your research file with:</h2>
  <table id="user_list" class="dataTable display">
    <thead>
      <tr>
        <th id="select_all"><s:checkbox id="check_all_box" name="checkAll" label="Check/Uncheck all"/></th>
        <th id="username">Username</th>
        <th id="name">Name</th>
        <th id="email">Email</th>
        <th id="country">Country</th>
        <th id="centre">Centre</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="portalUsers" status="u">
        <tr>
          <td>
            <s:checkbox id="%{'research_result_share_to_' + username}" name="researchResult.access" fieldValue="%{username}" theme="simple" cssClass='validate-one-required'/>
          </td>
          <td><b><s:property value="username"/></b></td>
          <td><s:property value="name"/></td>
          <td><s:property value="email"/></td>
          <td><s:property value="country"/></td>
          <td><s:property value="centre"/></td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <br/>
  <button type="submit" value="Upload Research Result" name="buttonName" style="align: right;">
    Upload Research Result
  </button>
</s:form>

<script type="text/javascript" language="javascript" class="init">
  jQuery(function ($) {
    // The dollar sign will equal jQuery in this scope
    $(document).ready(function () {
      $('#user_list').DataTable({
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