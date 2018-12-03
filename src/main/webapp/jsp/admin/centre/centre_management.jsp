<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Current registered centres</h1>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<a href="<%=path %>/jsp/admin/centre/create_country.jsp">Create New Country</a>
<br/><br/>

<s:form id="country_centre_form" theme="simple">
  <div id="results_div">
    <table id="country_centre_table" class="dataTable display" width="100%">
      <thead>
        <tr>
          <th id="country">Country</th>
          <th id="countryAction">Country Actions</th>
          <th id="centre">Centre</th>
          <th id="centreAction">Centre Actions</th>
          <th id="centreLeader">Centre Leader</th>
          <th id="centreLeaderAction">Change Leader</th>
        </tr>
      </thead>
      <tbody>
      <s:iterator value="#cn.centres" var="ct" status="centre">
        <tr>
          <td><span style="font-weight:bold;"><s:property value="#cn.countryName"/></span></td>
          <td>
            <s:url id="country_edit" value="%{path}/jsp/admin/centre/edit_country.jsp">
              <s:param name="countryId" value="%{#cn.optionId}"/>
              <s:param name="countryName" value="%{#cn.value}"/>
            </s:url>
            <s:a name="%{value}" href="%{country_edit}">Edit</s:a> |
            <s:url id="country_delete" value="%{path}/jsp/admin/centre/delete_country.jsp">
              <s:param name="countryId" value="%{#cn.optionId}"/>
              <s:param name="countryName" value="%{#cn.value}"/>
            </s:url>
            <s:a name="%{value}" href="%{country_delete}">Remove</s:a> |

          </td>
          <td>

          </td>
        </tr>
        <s:iterator value="countries" var="cn" status="country">

        </s:iterator>
      </s:iterator>

      <s:iterator value="countries" var="cn" status="country">
        <tr>
        <!-- <td rowspan="<s:property value='#cn.tertiaries.size'/>"> -->
        <td>
          <span style="font-weight:bold;"><s:property value="#cn.countryName"/></span>
        </td>
        <!-- <td rowspan="<s:property value='#cn.tertiaries.size'/>"> -->
        <td>
          <s:url id="country_edit" value="%{path}/jsp/admin/centre/edit_country.jsp">
            <s:param name="countryId" value="%{#cn.countryId}"/>
            <s:param name="countryName" value="%{#cn.countryName}"/>
          </s:url>
          <s:a name="%{countryName}" href="%{country_edit}">Edit</s:a> |
          <s:url id="country_delete" value="%{path}/jsp/admin/centre/delete_country.jsp">
            <s:param name="countryId" value="%{#cn.countryId}"/>
            <s:param name="countryName" value="%{#cn.countryName}"/>
          </s:url>
          <s:a name="%{countryName}" href="%{country_delete}">Remove</s:a> |
          <s:url id="country_centre_create" value="%{path}/jsp/admin/centre/create_centre.jsp">
            <s:param name="countryId" value="%{#cn.countryId}"/>
            <s:param name="countryName" value="%{#cn.countryName}"/>
          </s:url>
          <s:a name="%{countryName}" href="%{country_centre_create}">New Centre</s:a>
        </td>

        <s:if test="#cn.centres.size == 0">
          <td></td>
          <td>
            <!--
								<s:url id="centre_create" value="%{path}/jsp/admin/centre/create_centre.jsp">
									<s:param name="countryId" value="%{#cn.countryId}" />
									<s:param name="countryName" value="%{#cn.countryName}" />
								</s:url>
								<s:a name="%{value}" href="%{centre_create}">Add New</s:a>
								 -->
          </td>
          <td></td>
          <td></td>
        </s:if>

        <s:else>
          <s:iterator value="#cn.centres" var="ct" status="centre">
            <s:if test="#centre.count > 1">
              <tr>
              <td><span style="font-weight:bold;"><s:property value="#cn.countryName"/></span></td>
              <td>
                <s:url id="country_edit" value="%{path}/jsp/admin/centre/edit_country.jsp">
                  <s:param name="countryId" value="%{#cn.countryId}"/>
                  <s:param name="countryName" value="%{#cn.countryName}"/>
                </s:url>
                <s:a name="%{centreName}" href="%{country_edit}">Edit</s:a> |
                <s:url id="country_delete" value="%{path}/jsp/admin/centre/delete_country.jsp">
                  <s:param name="countryId" value="%{#cn.countryd}"/>
                  <s:param name="countryName" value="%{#cn.countryName}"/>
                </s:url>
                <s:a name="%{centreName}" href="%{country_delete}">Remove</s:a> |
                <s:url id="country_centre_create" value="%{path}/jsp/admin/centre/create_centre.jsp">
                  <s:param name="countryId" value="%{#cn.countryId}"/>
                  <s:param name="countryName" value="%{#cn.countryName}"/>
                </s:url>
                <s:a name="%{centreName}" href="%{country_centre_create}">New Centre</s:a>
              </td>
            </s:if>
            <td><s:property value="centreName"/></td>
            <td>
              <s:url id="centre_edit" value="edit_centre_view.action">
                <s:param name="centreId" value="%{centreId}"/>
                <%--<s:param name="centreName" value="%{centreName}"/>--%>
              </s:url>
              <s:a name="%{centreName}" href="%{centre_edit}">Edit</s:a> |
              <s:url id="centre_delete" value="%{path}/jsp/admin/centre/delete_centre.jsp">
                <s:param name="centreId" value="%{centreId}"/>
                <s:param name="centreName" value="%{centreName}"/>
              </s:url>
              <s:a name="%{centreName}" href="%{centre_delete}">Remove</s:a>
            </td>
            <td>
              <s:property value="leader.name"/>
            </td>
            <td>
              <s:url id="leader_edit" value="view_leader.action">
                <s:param name="centreId" value="%{centreId}"/>
                <s:param name="centreName" value="%{centreName}"/>
              </s:url>
              <s:a name="%{centreName}" href="%{leader_edit}">Edit</s:a>
            </td>
            </tr>
          </s:iterator>
        </s:else>
      </s:iterator>
      </tbody>
    </table>
  </div>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>