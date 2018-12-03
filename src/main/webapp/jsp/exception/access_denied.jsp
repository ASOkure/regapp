<%@ page language="java" pageEncoding="UTF-8" %>

<%
  String path = request.getContextPath();
%>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<div>
  <img src="<%=path %>/images/restricted.jpg" height="130px" alt="restricted_area"/>

  <p>
    Sorry, your access to the registry has been denied.
    If you think this is an error or wish to acquire these privileges
    then in the first instance please contact the I-DSD Project Manager, Jillian Bryce
    (<a href="mailto:jillian.bryce@glasgow.ac.uk">jillian.bryce@glasgow.ac.uk</a>).
  </p>

</div>

<jsp:include page="/jsp/page/page_foot.jsp"/>
