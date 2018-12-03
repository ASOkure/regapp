<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<s:form action="/user/edit_profile" cssStyle="margin-top: 30px;" theme="xhtml" id="edit_profile_form">
  <s:textfield label="Full Name" name="portalUser.name" size="30" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Country" name="portalUser.country" size="30" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Centre" name="portalUser.centre" size="50" readonly="true" cssStyle="border:0;"/>
  <s:textarea label="Full Institute Postal Address" name="portalUser.address" cssClass="free-text-area" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Telephone" name="portalUser.tel" size="50" cssClass="validate-phone" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Fax" name="portalUser.fax" size="50" cssClass="validate-phone" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Email" name="portalUser.email" size="50" readonly="true" cssStyle="border:0;"/>

  <s:textfield label="Position" name="portalUser.position" size="50" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Professional Society membership" name="portalUser.society" size="100" readonly="true" cssStyle="border:0;"/>

  <s:textfield label="Primary Role" name="portalUser.primaryRole" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Secondary Roles" name="portalUser.secondRoles" readonly="true" cssStyle="border:0;"/>
  <s:textfield label="Areas of Interest" name="portalUser.interest" readonly="true" cssStyle="border:0;"/>

  <s:textarea label="Other Secondary Roles" name="portalUser.secondaryRolesOther" cssClass="free-text-area" readonly="true" cssStyle="border:0;"/>

  <s:textarea label="Other Areas of Interest" name="portalUser.interestOther" cssClass="free-text-area" readonly="true" cssStyle="border:0;"/>
  <s:textarea label="Special Interest" name="portalUser.specialInterest" cssClass="free-text-area" readonly="true" cssStyle="border:0;"/>
</s:form>


<jsp:include page="/jsp/page/page_foot.jsp"/>