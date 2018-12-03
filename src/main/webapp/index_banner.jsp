<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<div class="banner">
    <% if ("icah".equalsIgnoreCase(request.getParameter("mode"))) { %>
    <div class="logo">
        <a href="<%=path %>">
            <img src="<%=path %>/images/index/icah_logo.jpg" height="140px" align="left" alt="idsd_logo"/>
        </a>
        <br/><br/>

        <div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left; '>Welcome to the I-CAH Registry</div>
    </div>
    <%} else { %>
    <div class="logo">
        <a href="<%=path %>">
            <img src="<%=path %>/images/index/idsd.jpg" height="140px" align="left" alt="idsd_logo"/>
        </a>
        <br/><br/>

        <div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left;'>Welcome to the I-DSD Registry</div>
    </div>
    <%} %>
    <div id="login" class='login'>
        <ul>
            <li>
                <% if ("icah".equalsIgnoreCase(request.getParameter("mode"))) { %>
                <span class="shib_login"> <a href="<%=path%>/jsp/home.jsp?mode=icah">Login to Registry</a> </span>
                <%} else { %>
                <span class="shib_login"> <a href="<%=path%>/jsp/home.jsp?mode=idsd">Login to Registry</a> </span>
                <%} %>
            </li>
            <li>
                <% if ("icah".equalsIgnoreCase(request.getParameter("mode"))) { %>
                <span> <a href="<%=path%>/register_view.action?mode=icah">Create A New Account</a> </span>
                <%} else { %>
                <span> <a href="<%=path%>/register_view.action?mode=idsd">Create A New Account</a> </span>
                <%} %>
            </li>
            <li>
                <span> <a href="http://www.gla.ac.uk/idsd">Further Information</a> </span>
            </li>
        </ul>
    </div>
</div>
<br/>