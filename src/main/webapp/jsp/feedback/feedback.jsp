<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<h1>Helpdesk</h1>

<p>
  If you have a query regarding the registry or wish to report an issue please send your comments using the box below:
</p>

<s:form action="send_feedback" namespace="/feedback">
  <s:hidden name="mode" value="%{#parameters.mode[0]}"/>
  Write your comment here:
  <s:textarea name="feedback" cols="60" rows="10"/>
  <s:submit value="Send"/>
</s:form>

<jsp:include page="/jsp/page/page_foot.jsp"/>