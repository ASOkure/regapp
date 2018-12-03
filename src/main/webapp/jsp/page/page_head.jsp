<%@ page language="java" import="uk.ac.nesc.idsd.security.spring.SecurityUserHolder, uk.ac.nesc.idsd.security.spring.User" pageEncoding="UTF-8" %>
<%
  String path = request.getContextPath();
  User user = SecurityUserHolder.getCurrentUser();
%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="keywords" content="idsd,I-DSD,DSD,registry,EuroDSD,I-DSD,National e-Science Centre,glasgow,Yorkhill"/>
    <meta http-equiv="description" content="I-DSD Registry"/>
    <link rel="SHORTCUT ICON" href="<%=path%>/images/idsd.ico"/>
    <title><% if ("icah".equalsIgnoreCase(request.getParameter("mode"))) { %>I-CAH<%} else { %>I-DSD<%} %> Registry</title>

    <script type="text/javascript" language="javascript" src="<%=path %>/js/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" language="javascript" src="<%=path %>/js/jquery/jquery-ui.min.js"></script>
    <script type="text/javascript" language="javascript">jQuery.noConflict();</script>
    <script type="text/javascript" language="javascript" src="<%=path %>/js/jquery/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/jquery.dataTables.min.css"/>
    <script type="text/javascript" language="javascript"
            src="<%=path %>/js/jquery/form-validator/jquery.form-validator.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/theme-default.min.css"/>

    <script type="text/javascript" src="<%=path %>/js/prototype/prototype161.js"></script>
    <script type="text/javascript" src="<%=path %>/js/prototype/scriptaculous/effects183.js"></script>
    <script type="text/javascript" src="<%=path %>/js/prototype/validation.js"></script>
    <script type="text/javascript" src="<%=path %>/js/utility.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/3.6/yui/yui-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/yahoo-dom-event/yahoo-dom-event.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/dragdrop/dragdrop-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/element/element-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/button/button-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/container/container-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/calendar/calendar-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/datasource/datasource-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/datatable/datatable-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/paginator/paginator-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/selector/selector-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/dom/dom-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/connection/connection-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/json/json-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/animation/animation-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/yui/2/dragdrop/dragdrop-min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/swfobject.js"></script>

    <!-- Datatable -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/yui/2/datatable/assets/skins/sam/datatable.css"/>
    <!-- Calendar -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/yui/2/button/assets/skins/sam/button.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/yui/2/container/assets/skins/sam/container.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/yui/2/calendar/assets/skins/sam/calendar.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/validation.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css"/>

    <script type="text/javascript" src="<%=path%>/js/jquery/te_140/jquery-te-1.4.0.min.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/jquery/te_140/jquery-te-1.4.0.css"/>
    <script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
  </head>

  <body id="body" class="yui-skin-sam yui3-skin-sam">
    <script type="text/javascript">
      YUI().use("node", initial_validation);
      YUI().use("event-mouseenter", "widget", "widget-position", "widget-stack", initial_tooltip);
    </script>

    <div id="body_div" class="page_body">
      <div id="banner_div" class="banner_div">
        <div id="left_div" class="left_header"></div>
        <div id="right_div" class="right_header">
          Welcome
          <b><% if (user != null) out.print(user.getUsername());%></b> |
          <% if ("icah".equalsIgnoreCase(request.getParameter("mode"))) { %>
          <a href="<%=path%>/logout.action?redirect=/icah/index.jsp">Logout</a>
          <%} else { %>
          <a href="<%=path%>/logout.action?redirect=/index.jsp">Logout</a>
          <%} %>
        </div>
      </div>