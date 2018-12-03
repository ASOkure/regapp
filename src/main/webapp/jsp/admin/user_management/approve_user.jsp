<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Approve Users</h1>

<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<s:form id="approve_user_form" action="approve_user" cssClass='required-validate'>
  <s:hidden name="mode" value="%{#parameters.mode[0]}"/>
  <table style="border-collapse:collapse; border: 1px solid black;" border="1">
    <tr>
      <th>Username</th>
      <td><s:property value="%{portalUser.username}"/></td>
      <s:hidden name="username" value="%{username}"></s:hidden>
    </tr>
    <tr>
      <th>Full Name</th>
      <td><s:property value="%{portalUser.name}"/></td>
    </tr>
    <tr>
      <th>Country</th>
      <td><s:property value="%{portalUser.country}"/></td>
    </tr>
    <tr>
      <th>Centre</th>
      <td><s:property value="%{portalUser.centre}"/></td>
    </tr>
    <tr>
      <th>Email</th>
      <td><s:property value="%{portalUser.email}"/></td>
    </tr>
    <tr>
      <th>Tel</th>
      <td><s:property value="%{portalUser.tel}"/></td>
    </tr>
    <tr>
      <th>Registration Date</th>
      <td><s:date name="%{portalUser.registerTime}" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <th>Last Visit</th>
      <td><s:date name="%{portalUser.lastLogin}" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <th>Last Password Change</th>
      <td><s:date name="%{portalUser.lastPasswordChange}" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <th>User Account Enabled</th>
      <td><s:property value="%{portalUser.enabled}"/></td>
    </tr>
    <tr>
      <th>Existing Roles</th>
      <td>
        <s:iterator value="%{portalUser.userRoles}">
          <p>
            <s:property value="role.roleName"/>
            <s:if test="validateFrom != null && validateTo != null">
              (Valid from <s:date name="validateFrom" format="yyyy-MMM-dd"/> to
              <s:date name="validateTo" format="yyyy-MMM-dd"/>)
            </s:if>
          </p>
        </s:iterator>
      </td>
    </tr>
  </table>
  <br/>
  <br/>

  <h2>Change User Roles To: </h2>

  <p style="color: red;">NOTE: New roles will overwrite the existing roles.</p>

  <s:checkboxlist list="roles" listKey="roleId" listValue="roleName" name="roleIds" value="existingRoles" theme="idsd_simple"/>

  <br/>

  <p style="color: red;">Please give date range for ROLE_RESEARCHER: (leave blank for other types of roles) </p>

  <script type="text/javascript">
    user_datePicker_init("fromDate", "show_validateFrom");
    user_datePicker_init("toDate", "show_validateTo");
  </script>

  <table id="researcher_validate_date_range">
    <tr>
      <td>
        Researcher Role Validate From:
      </td>
      <td>
        <s:textfield id="fromDate" name="validateFrom" cssClass='validate-date-dd/MM/yyyy' theme="simple"/>
        <button type="button" id="show_validateFrom" title="Show Calendar for Validate From">
          <img src="<%=path %>/images/calbtn.gif" width="18" height="18" alt="Calendar" alt="calendar_button"/>
        </button>
      </td>
    </tr>
    <tr>
      <td>
        Researcher Role Validate To:
      </td>
      <td>
        <s:textfield id="toDate" name="validateTo" cssClass='validate-date-dd/MM/yyyy' theme="simple"/>
        <button type="button" id="show_validateTo" title="Show Calendar for Validate To">
          <img src="<%=path %>/images/calbtn.gif" width="18" height="18" alt="Calendar" alt="calendar_button"/>
        </button>
      </td>
    </tr>
  </table>


  <!--
  <s:iterator value="roles">
    <s:checkbox name="roleIds" fieldValue="true" label="Check Me for testing"/>
  </s:iterator>
  -->
  <s:property value="existingRolesString"/>
  <br/><br/><br/>
  <s:submit theme="simple"></s:submit>
</s:form>


<jsp:include page="/jsp/page/page_foot.jsp"/>