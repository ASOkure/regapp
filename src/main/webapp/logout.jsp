<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:property value="#parameters.mode[0]"/>

<s:if test="#parameters.mode[0] == 'icah'" >
	<jsp:include page="/index.jsp?mode=${param['mode']}" />
</s:if>
<s:else>
	<jsp:include page="/icah/index.jsp?mode=${param['mode']}" />
</s:else>
