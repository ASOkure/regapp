<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <jsp:include page="/index_head.jsp"/>
    <body>
        <div id='body' class='body'>
            <div class='left_body'>
                <jsp:include page="/index_banner.jsp"/>
                <div class="indexBody">
                    <jsp:include page="html/idsd_index.jsp"/>
                </div>

                <jsp:include page="index_foot.jsp"/>
            </div>
            <!-- enf of <div class='left_body'> -->

            <jsp:include page="index_right.jsp"/>
        </div>
        <!-- enf of <div id='body' class='body'> -->

        <jsp:include page="/html/access_tracking.html"/>
    </body>
</html>

