<%@ page language="java" import="uk.ac.nesc.idsd.model.PatientUser" pageEncoding="UTF-8" %>
<%@ page import="uk.ac.nesc.idsd.model.PortalUser" %>
<%@ page import="uk.ac.nesc.idsd.security.spring.SecurityUserHolder" %>
<%@ page import="uk.ac.nesc.idsd.security.spring.User" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<%
  User user = SecurityUserHolder.getCurrentUser();
%>

<%
  PortalUser portalUser = null;
  PatientUser patientUser = null;
  try {
    portalUser = (PortalUser) user;
  } catch (Exception e) {
    out.println("We cannot cast to PortalUser, must be patientUser");
    try {
      patientUser = (PatientUser) user;
    } catch (Exception e1) {
      out.println("We cannot cast to PatientUser either");
    }
  }
  if (portalUser == null && patientUser != null && patientUser.getDsdIdentifierId() != null) {
    out.println("redirecting...");
%>
<jsp:forward page="/jsp/patient/view.action"/>
<%
  }
%>

<div id="home_text" class="indexBody">
  <s:if test="#parameters.mode[0] == 'icah'">
    <jsp:include page="/html/icah_home.jsp"/>
  </s:if>
  <s:else>
    <jsp:include page="/html/idsd_home.jsp"/>
  </s:else>
</div>
<jsp:include page="/jsp/page/page_foot.jsp"/>