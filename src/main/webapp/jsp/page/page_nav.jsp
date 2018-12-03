<%@ page language="java" import="uk.ac.nesc.idsd.security.Authorization" pageEncoding="UTF-8" %>
<%@ page language="java" import="uk.ac.nesc.idsd.security.spring.SecurityUserHolder" %>
<%@ page language="java" import="uk.ac.nesc.idsd.security.spring.User" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
  User user = SecurityUserHolder.getCurrentUser();
%><br/>

<div class="yui3-u" id="main">
  <div id="nav_menu_div" class="yui3-menu yui3-menu-horizontal yui3-splitbuttonnav" style="height:26px;">
    <div id="nav_menu_content_div" class="yui3-menu-content" style="display: none;">
      <ul class="first-of-type">
        <li><span class="yui3-menu-label"><a href="<%=path%>/jsp/home.jsp?mode=${param['mode']}">Home</a></span></li>
        <li>
					<span class="yui3-menu-label">
						<a href="#">Records</a> 
						<a href="#record-management" class="yui3-menu-toggle">Records</a> 
					</span>
          <div id="record-management" class="yui3-menu">
            <div class="yui3-menu-content">
              <ul>
                <% if (Authorization.isAuditor(user) || Authorization.isSuperUser(user)) { %>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/search/searchview.action?mode=${param['mode']}">Search Records</a>
                </li>
                <% } %>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/create/core_view.action?mode=${param['mode']}">Create Record</a>
                </li>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/search/view_my_records.action?mode=${param['mode']}">View Records</a>
                </li>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/search/view_my_centre_records.action?mode=${param['mode']}">View Centre Records</a>
                </li>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/search/search_my_centre_view.action?mode=${param['mode']}">Search Centre Records</a>
                </li>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/search/search_request.action?mode=${param['mode']}">Request Advanced Search</a>
                </li>
                <%--<li class="yui3-menuitem">--%>
                <%--<a class="yui3-menuitem-content" href="<%=path%>/jsp/delete/view.jsp?mode=${param['mode']}">Delete Record</a>--%>
                <%--</li>--%>
                <li>
                  <a class="yui3-menu-label" href="#upload">Upload</a>
                  <div id="pim" class="yui3-menu">
                    <div class="yui3-menu-content">
                      <ul>
                        <li class="yui3-menuitem">
                          <a class="yui3-menuitem-content" href="<%=path%>/jsp/upload/upload_home.jsp?mode=${param['mode']}">Research Results</a>
                        </li>
                      </ul>
                      <ul>
                        <li class="yui3-menuitem">
                          <a class="yui3-menuitem-content" href="<%=path%>/bulk/index.action?mode=${param['mode']}">Bulk Upload</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </li>
        <li>
          <span class="yui3-menu-label"><a href="<%=path%>/jsp/stats/total_stats.jsp?mode=${param['mode']}">Statistics</a></span>
        </li>

        <li>
					<span class="yui3-menu-label"> 
						<a href="#">My Account</a> 
						<a href="#user-management" class="yui3-menu-toggle">My Account</a>
					</span>

          <div id="user-management" class="yui3-menu">
            <div class="yui3-menu-content">
              <ul>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/user/account/change_password_view.action?mode=${param['mode']}">Change password</a>
                </li>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/user/account/view_profile.action?mode=${param['mode']}">Edit My Profile</a>
                </li>
              </ul>
            </div>
          </div>
        </li>
        <li>
					<span class="yui3-menu-label">
						<a href="<%=path%>/user/search/search_view.action?mode=${param['mode']}">Search Users</a>
					</span>
        </li>
        <li>
					<span class="yui3-menu-label">
						<a href="<%=path%>/jsp/feedback/feedback.jsp?mode=${param['mode']}">Help Desk</a>
					</span>
        </li>

        <% if (Authorization.isSuperUser(user)) { %>
        <li>
					<span class="yui3-menu-label">
						<a href="#">Admin Tools</a>
						<a href="#mobile-options" class="yui3-menu-toggle">Admin Tools</a>
					</span>

          <div id="mobile-options" class="yui3-menu">
            <div class="yui3-menu-content">
              <ul>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/admin/approve_user_view.action?mode=${param['mode']}">User Management</a>
                </li>
                <li class="yui3-menuitem">
                  <a class="yui3-menuitem-content" href="<%=path%>/admin/centre_management_view.action?mode=${param['mode']}">Centre Management</a>
                </li>
              </ul>
            </div>
          </div>
        </li>
        <% } %>
        <% if (Authorization.isAuditor(user)) { %>
        <li>
					<span class="yui3-menu-label">
						<a href="<%=path%>/jsp/admin/admin_home.jsp?mode=${param['mode']}">Auditor Tools</a>
					</span>
        </li>
        <% } %>
      </ul>
    </div>
  </div>
</div>

<script type="text/javascript">
  //  Call the "use" method, passing in "node-menunav".  This will load the
  //  script and CSS for the MenuNav Node Plugin and all of the required
  //  dependencies.
  YUI({
    modules: {
      "node-menunav-core-css": {
        type: "css",
        path: "node-menunav/assets/node-menunav-core.css"
      },
      "custommenunavskin": {
        type: "css",
        fullpath: "<%=path %>/css/yui_menu.css",
        supersedes: ['skin-sam-node-menunav']
      }
    }
  }).use('event-base',
      'node-menunav',
      "node-menunav-core-css",
      "custommenunavskin",
      function (Y) {
        var menu = Y.one("#nav_menu_content_div");
        menu.plug(Y.Plugin.NodeMenuNav, {
          autoSubmenuDisplay: true,
          mouseOutHideDelay: 1000
        });
        //  Show the menu now that it is ready
        menu.get("ownerDocument").get("documentElement").removeClass("yui3-loading");
        menu.setStyle('display', 'block');
      });
</script>

<script type="text/javascript" language="javascript" class="init">
  jQuery(function ($) {
    // The dollar sign will equal jQuery in this scope
    $(document).ready(function () {
      var tables = $('.dataTable').DataTable({
        "order": [[0, "asc"]],
        "processing": true,
        "autoWidth": true,
        "stateSave": true,
        paging: true,
        searching: true,
        "info": true,
        retrieve: true,
        "lengthMenu": [[10, 25, 50, 100, 200, -1], [10, 25, 50, 100, 200, "All"]]
      });
      tables.draw(false);
    });
  });
</script>

<script>
  jQuery(function ($) {
    $(document).ready(function () {
      $.validate({
        modules: 'security',
        onModulesLoaded: function () {
          var optionalConfig = {
            fontSize: '12pt',
            padding: '4px',
            bad: 'Very Weak',
            weak: 'Weak',
            good: 'Good',
            strong: 'Strong'
          };
          $('input[name="password"]').displayPasswordStrength(optionalConfig);
        }
      });
    });
  });
</script>