<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/index_head.jsp"/>
<body>
<div id='body' class='body'>
  <div class='left_body'>
    <jsp:include page="/index_banner.jsp"/>
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
    <jsp:include page="/index_foot.jsp"/>
  </div>
</div>
</body>
</html>
