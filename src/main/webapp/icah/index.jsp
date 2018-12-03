<%@ page language="java" pageEncoding="UTF-8" %>
<%
  String path = "https://www.i-dsd.org/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="SHORTCUT ICON" href="<%=path%>/images/icah-favicon.png"/>
    <title>I-CAH Registry</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="icah,i-cah,I CAH,idsd,I-DSD,DSD,registry,EuroDSD,I-DSD,National e-Science Centre,glasgow,Yorkhill"/>
    <meta http-equiv="description" content="I-CAH Registry"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/navigation.css"/>
  </head>
  <body>
    <div id='body' class='body'>
      <div class='left_body'>
        <div class="banner">
          <div class="logo">
            <a href="<%=path %>">
              <img src="<%=path %>/images/index/icah_logo.jpg" height="140px" align="left" alt="idsd_logo"/>
            </a>
            <br/><br/>

            <div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left; '>Welcome to the I-CAH Registry</div>
          </div>

          <div id="login" class='login'>
            <ul>
              <li>
                <span class="shib_login"> <a href="<%=path%>/jsp/home.jsp?mode=icah">Login to Registry</a> </span>
              </li>
              <li>
                <span> <a href="<%=path%>/register_view.action?mode=icah">Create A New Account</a> </span>
              </li>
            </ul>
          </div>
        </div>
        <br/>

        <div class="indexBody">
          <h1>The Registry</h1>

          <p>
            The I-CAH Registry provides a means of connecting clinical and research centres around the world within a
            virtual environment and allows these experts to enter standardised information that will improve clinical
            practice, research and understanding of Congenital Adrenal Hyperplasia. For further information please visit the
            I-CAH
            <a target="_blank" href="http://www.i-cah.org/">I-CAH website</a>.
          </p>

          <p>
            The I-CAH Registry is hosted by the <a target="_blank" href="http://www.i-dsd.org/">I-DSD registry</a>.
          </p>

          <p>
            <a target="_blank" href="/doc/IDSD update espe 2014.pdf">I-DSD & I-CAH update</a> (presentation at DSD working group at ESPE 2014)
          </p>

          <h1>To Access the Registry</h1>

          <p>
            Prospective registry users can apply for access through the link '<a href="<%=path%>/register_view.action?mode=icah">Create A New Account</a>'.
          </p>

          <p>There are currently several types of User.</p>
          <table>
            <tr>
              <td style="font-weight: bold;width:120px;">Clinical User</td>
              <td style="padding: 10px;margin: 10px">
                Often part of team in a centre with one Centre Lead.
                Clinical users can upload and edit cases but only the centre lead can delete cases and maintains
                responsibility for all clinical data in their centre.
               <!--  Clinical users can view the full dataset of cases they upload but only the core dataset of all other
                available cases. -->
              </td>
            </tr>
            <tr>
              <td style="font-weight: bold;width:120px;">Network User</td>
              <td style="padding: 10px;margin: 10px">
                Can search other user’s profiles for networking reasons. No access to
                any records.
              </td>
            </tr>
            <tr>
              <td style="font-weight: bold;width:120px;">Centre Lead</td>
              <td style="padding: 10px;margin: 10px">
                A clinician with clinical responsibility for all cases added to the registry in their centre. Only centre leads can delete cases from their centre.
              </td>
            </tr>
          </table>
          <p>
            Access to the Registry is routinely suspended after 12 months of inactivity.
          </p>
          <jsp:include page="/html/contact.jsp"/>
        </div>

        <div id="page-foot" class="page-foot">
          <br/>
          <hr/>
          <div class="page-foot-left">
            <span id="date_time"></span>
            <script type="text/javascript">
              document.getElementById("date_time").innerText = Date();
              //-->
            </script>
          </div>
          <div class="page-foot-right">
            <a href="http://www.nesc.ac.uk/hub/">
              <img class="page-foot-image" src="<%=path %>/images/footer/nesc.png" alt="nesc_logo"/>
            </a>
          </div>
        </div>
      </div>
      <div id="right_body" class='right_body'></div>
    </div>
    <jsp:include page="/html/access_tracking.html"/>
  </body>
</html>

