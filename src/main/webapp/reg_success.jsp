<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <jsp:include page="/index_head.jsp"/>
    <body>
        <div id='body' class='body'>
            <div class='left_body'>
                <jsp:include page="/index_banner.jsp"/>
                <s:actionerror cssClass="error_message"/>

                <br/>

                <h2>
                    Register New Account
                </h2>

                <h4>
                    User registration successful
                </h4>

                <p>
                    You have successfully created a username and password to
                    access the I-DSD/I=CAH registry. It is important that you do not
                    forget this. You can login using the username and password. However, at present
                    you cannot search, upload, or delete patient records. Your request has been sent to the Project Management
                    who will, where appropriate, provide you with privileges to use the registry itself.
                </p>

                <jsp:include page="/index_foot.jsp"/>
            </div>
        </div>
    </body>
</html>
