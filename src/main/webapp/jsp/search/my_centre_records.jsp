<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<div id="search_result_div" class="pageBody">
  <h1>Your Centre Contribution</h1>

  <p>
    In total, your centre has contributed
    <strong><s:property value="searchResult.actualSize"/></strong> records.<strong>
  </p>

  <div id="results_div">
    <table id="results_table" class="dataTable display">
      <thead>
        <tr>
          <s:iterator status="headCell" value="searchResult.headRow">
            <th id="${columnId}">
              <s:label value="%{label}" theme="simple"/>
            </th>
          </s:iterator>
        </tr>
      </thead>
      <tbody>
        <s:iterator value="searchResult.resultRows" status="row">
          <tr>
            <s:iterator value="cells" status="cell">
              <td>
                <s:if test="#cell.last">
                  <s:url id="viewURL" value="read.action">
                    <s:param name="registerId" value="registerId"/>
                    <s:param name="mode" value="%{#parameters.mode}"/>
                  </s:url>
                  <s:a name="%{'view_record_' + registerId}" href="%{viewURL}" theme="simple">
                    view
                  </s:a>
                  <s:if test="editable">
                    <s:url id="editURL" value="../update/core_view.action">
                      <s:param name="registerId" value="registerId"/>
                      <s:param name="mode" value="%{#parameters.mode}"/>
                    </s:url>
                    /
                    <s:a name="%{'edit_record_' + registerId}" href="%{editURL}" theme="simple">
                      edit
                    </s:a>

                    <s:url id="deleteURL" value="../delete/delete.action">
                      <s:param name="registerId" value="registerId"/>
                      <s:param name="mode" value="%{#parameters.mode}"/>
                    </s:url>
                    /
                    <s:a name="%{'delete_record_' + registerId}" href="%{deleteURL}" theme="simple">
                      delete
                    </s:a>

                    <s:url id="accessURL" value="../search/refer_patient_view.action">
                      <s:param name="registerId" value="registerId"/>
                      <s:param name="mode" value="%{#parameters.mode}"/>
                    </s:url>
                    /
                    <s:a name="%{'access_record_' + registerId}" href="%{accessURL}" theme="simple">
                      provide participant access
                    </s:a>
                  </s:if>
                </s:if>
                <s:else>
                  <s:property value="value"/>
                </s:else>
              </td>
            </s:iterator>
          </tr>
        </s:iterator>
      </tbody>
    </table>
  </div>
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>