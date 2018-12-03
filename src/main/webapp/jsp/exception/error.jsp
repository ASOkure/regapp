<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<div>
  <h3>
    An error has occurred, please go back to previous page and try again.
    Or contact the system administrator at
    <a href="mailto:jillian.bryce@glasgow.ac.uk">Jillian Bryce</a>.
    <h3>
      Error details:
    </h3>

    <p>
      <s:property value="message"/>
    </p>

    <s:actionerror cssClass="error_message" />
    <s:fielderror cssClass="error_message" />
</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>