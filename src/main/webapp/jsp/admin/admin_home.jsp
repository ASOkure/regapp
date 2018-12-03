<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Auditor Tools</h1>

<p>This page provides I-DSD registry auditor functions.</p>

<p style="color:red;">
  NOTE: these functions may take long time to run once they are clicked.
  Please be patient during this process.
  You will see the final success page when this process completes.
</p>


<a id="age16Reminder" href="<%=path%>/admin/age16Reminder.action?mode=${param['mode']}">
  Send reminder e-mails
</a>
to clinicians whose patients are turning 16 this year.

<br/><br/>

<a id="passwordReminder" href="<%=path%>/admin/password_reminder.action?mode=${param['mode']}">
  Send reminder e-mails
</a>
to users who have not changed password for 6 months.

<br/><br/>

<a id="forceResetPassword" href="<%=path%>/admin/force_password_reset.action?mode=${param['mode']}">
  Force password reset
</a>
for users who have not changed password for 8 months.

<br/><br/>

<a id="deactivateUserAccounts" href="<%=path%>/admin/deactivate_users.action?mode=${param['mode']}">
  Deactivate user accounts
</a>
that have not been used for 8 months.

<jsp:include page="/jsp/page/page_foot.jsp"/>
