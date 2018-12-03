<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="SHORTCUT ICON" href="<%=path%>/images/idsd.ico"/>
    <% if ("icah".equalsIgnoreCase(request.getParameter("mode"))) { %>
    <title>I-CAH Registry</title>
    <%} else { %>
    <title>I-DSD Registry</title>
    <%} %>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords"
          content="idsd,I-DSD,DSD,registry,EuroDSD,I-DSD,National e-Science Centre,glasgow,Yorkhill"/>
    <meta http-equiv="description" content="I-DSD Registry"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/navigation.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/query.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/participant.css"/>
    <!-- Validation Prototype -->
    <script type="text/javascript" src="<%=path %>/js/prototype/prototype161.js"></script>
    <script type="text/javascript" src="<%=path %>/js/prototype/scriptaculous/effects183.js"></script>
    <script type="text/javascript" src="<%=path %>/js/prototype/validation.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/validation.css"/>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/swfobject/2.2/swfobject.js"></script>

</head>
