<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<div id="search_result_div" class="pageBody">
  <p>
    There are <strong><s:property value="searchResult.actualSize"/></strong> records found matching your criteria.
    <strong><s:property value="searchResult.consentSize"/></strong> records are displayed based on your consent
    level.
    And you have selected to show <strong><s:property value="searchResult.columnSize"/></strong> columns of the
    table. </p>

  <div id="results_div">
    <s:url id="executeSearchRequestLink" value="../search/save_search_request_result.action">
      <s:param name="searchId" value="searchId"/>
      <s:param name="mode" value="mode"/>
    </s:url>

    <s:url id="cancelURL" value="cancel">
      <s:param name="mode" value="mode"/>
    </s:url>

    <s:a name="%{'search_request_' + searchId}" href="%{executeSearchRequestLink}" cssStyle="font-size: 120%; font-weight: bold;">Save Result as CSV</s:a> |
    <s:a href="%{cancelURL}" cssStyle="font-size: 120%; font-weight: bold;">Cancel</s:a><br/><br/>
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