<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<div id="search_result_div" class="pageBody">
  <p>
    There are <strong><s:property value="searchResultCount.actualSize"/></strong> records found matching your criteria.
    <strong><s:property value="searchResultCount.consentSize"/></strong> records can be shared with you based on your consent
    level.
    And you have selected to show <strong><s:property value="searchResultCount.columnSize"/></strong> columns of the
    table. </p>

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