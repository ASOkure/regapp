<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>

<!doctype html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="SHORTCUT ICON" href="<%=path%>/images/idsd.ico"/>
    <title>
      <s:if test="#parameters.mode[0] == 'icah'">I-CAH</s:if>
      <s:else>I-DSD</s:else>
      Registry </title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="idsd,I-DSD,DSD,ICAH,I-CAH,registry,EuroDSD,National e-Science Centre,glasgow,Yorkhill"/>
    <meta http-equiv="description" content="
      <s:if test="#parameters.mode[0] == 'icah'" >I-CAH</s:if>
      <s:else>I-DSD</s:else>
    Registry"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/navigation.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/query.css"/>

    <!-- Validation Prototype -->
    <%--<script type="text/javascript" src="<%=path %>/js/prototype/prototype161.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path %>/js/prototype/scriptaculous/effects183.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path %>/js/prototype/validation.js"></script>--%>
    <script type="text/javascript" src="<%=path %>/js/yui/3.6/yui/yui-min.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="<%=path %>/css/validation.css"/>--%>

    <script type="text/javascript" language="javascript" src="<%=path %>/js/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" language="javascript" src="<%=path %>/js/jquery/form-validator/jquery.form-validator.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/theme-default.min.css"/>

  </head>
  <body>
    <div id='body' class='body'>
      <div class='left_body'>
        <div class="banner">
          <s:if test="#parameters.mode[0] == 'icah'">
            <div class="logo">
              <a href="<%=path %>">
                <img src="<%=path %>/images/index/icah_logo.jpg" height="140px" align="left" alt="idsd_logo"/>
              </a>
              <br/><br/>
              <div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left; '>Welcome to the I-CAH Registry
              </div>
            </div>
          </s:if>
          <s:else>
            <div class="logo">
              <a href="<%=path %>">
                <img src="<%=path %>/images/index/idsd.jpg" height="140px" align="left" alt="idsd_logo"/>
              </a>
              <br/><br/>
              <div style='clear:both; margin-top:10px; font-weight: bold; font-style: italic; float: left;'>Welcome to the I-DSD Registry
              </div>
            </div>
          </s:else>
          <div id="login" class='login'>
            <ul>
              <li>
                <span class="shib_login"> <a href="<%=path%>/jsp/home.jsp?mode=${param['mode']}">Login to Registry</a> </span>
              </li>
              <li>
                <span> <a href="<%=path%>/register_view.action?mode=${param['mode']}">Create A New Account</a> </span>
              </li>
              <li><span>
								<s:if test="#parameters.mode[0] == 'icah'">
                  I-CAH Network (Coming Soon)
                </s:if>
						        <s:else>
                      <a href="http://www.gla.ac.uk/idsd">Further Information</a>
                    </s:else>
							</span></li>
            </ul>
          </div>
        </div>
        <br/>
        <script type="text/javascript">
          <s:property value='leadersJS' escapeHtml='false' escapeJavaScript='false'/>
          YUI().use("node", function (Y) {
            Y.on("domready", function () {
              YUI().use("dump", "node", function (Y) {
                var formName = document.getElementsByTagName("form")[0].id;
                var country = Y.one("#" + formName + "_portalUser_country");
                var centre = Y.one("#" + formName + "_portalUser_centre");
                var clinician = Y.one("#" + formName + "_centre_lead");
                var contact = Y.one("#" + formName + "_centre_lead_contact");

                var onChangeHandle = function (e) {
                  if (country.get('selectedIndex') != 0) {
                    for (var i = 0; i < leaderMap.length; i++) {
                      if (leaderMap[i][0].toLowerCase() == centre.get('value').toLowerCase()) {
                        clinician.set('value', leaderMap[i][1]);
                        contact.set('value', leaderMap[i][2]);
                      }
                    }
                  } else {
                    clinician.set('value', '');
                    contact.set('value', '');
                  }
                };
                //var prefix = Y.one("form").get('id') + "_dsdIdentifier_";
                country.on("change", onChangeHandle);
                centre.on("change", onChangeHandle);
              });
            });
          })
        </script>

        <div class="indexBody">
          <h1>User Registration</h1>
          <p>
            To request a user account for the Registry, please complete the following form. Your request will be sent to the Registry
            panel who will contact you via email. If you have any queries about how to complete the form, please contact
            <s:if test="#parameters.mode[0] != 'icah'">
              the Project Manager
            </s:if>
            <a href="mailto:jillian.bryce@glasgow.ac.uk">Jillian Bryce</a>
          </p>
          <!--p>
            Your password must match the following criteria:
            <ul>
              <li>A digit must occur at least once</li>
              <li>A lower case letter must occur at least once</li>
              <li>An upper case letter must occur at least once</li>
              <li>A special character must occur at least once, including @#$%^&+=</li>
              <li>No whitespace allowed in the entire string</li>
              <li>at least eight characters</li>
            </ul>
          </p-->
          <s:actionerror cssClass="error_message"/>
          <s:fielderror cssClass="error_message"/>
          <s:form action="register" cssStyle="margin-top: 30px;" theme="xhtml" id="register_form">
            <input type="hidden" name="mode" value="${param['mode']}"/>
            <s:textfield label="User Name" name="portalUser.username" required="true" data-validation="length alphanumeric" data-validation-length="6-30" data-validation-error-msg="User name has to be an alphanumeric value (6-30 chars)" data-validation-help="Username length must be between 6 and 30"/>
            <s:password label="Password" name="portalUser.password" id="password" required="true" data-validation="required strength length" data-validation-error-msg="Invalid password" data-validation-strength="1" data-validation-length="6-30" data-validation-help="Password length must be between 6 and 30 and strength level not at Very Weak" type="password"/>
            <s:password label="Repeat Password" name="repassword" id="repassword" required="true" data-validation="required strength length confirmation" data-validation-error-msg="Invalid password" data-validation-strength="1" data-validation-length="6-30" data-validation-confirm="portalUser.password" type="password"/>
            <s:textfield label="Full Name" name="portalUser.name" required="true" data-validation="required confirmation"/>
            <s:textfield label="Email" name="portalUser.email" required="true" data-validation="email"/>
            <s:textfield label="On-line profile" name="portalUser.profileUrl" title="(link to on-line CV or professional group profile)" data-validation="url" data-validation-optional="true" data-validation-help="link to on-line CV or professional group profile"/>

            <s:textfield label="Please leave this field blank" name="fullName"/>

            <td colspan="2"><h3>Please select your Country and Centre</h3></td>
            <s:doubleselect label="Country & Centre" name="portalUser.country" headerKey="" headerValue="--- Please Select ---" list="countries" listKey="countryName" listValue="countryName" doubleName="portalUser.centre" doubleHeaderKey="" doubleHeaderValue="--- Please Select ---" doubleList="centres" doubleListKey="centreName" doubleListValue="centreName" cssClass='validate-selection' theme="idsd_xhtml"/>
            <s:textfield label="Centre Lead" name="centre_lead" cssClass='auto-text' readonly="true"/>
            <s:textfield label="Centre Lead Contact" name="centre_lead_contact" cssClass='auto-text' readonly="true"/>

            <s:div cssClass="hide">
              <td colspan="2">
                <h3>For new centres and countries, please enter below: </h3></td>
              <s:textfield id="new_country" label="Country" name="newCountry" data-validation="country" data-validation-optional="true"/>
              <s:textfield id="new_centre" label="Centre" name="newCentre"/>
              <s:textfield id="new_centre_lead" label="Centre Lead" name="newCentreLead" title="Person clinically responsible for all cases uploaded in the centre" data-validation-help="Person clinically responsible for all cases uploaded in the centre"/>
            </s:div>

            <td colspan="2"><h3>Access privilege required</h3></td>
            <tr>
              <td colspan=2 style="color:red;">To select multiple values, please keep Ctrl key pressed while selecting values with mouse</td>
            </tr>
            <s:select label="Privilege Required" name="privilege" multiple="true" headerKey="" headerValue="--- Select NONE---" list="#{'Enter/modify cases (Clinical role - adding cases - can add, edit and view full dataset of own cases)':'Enter/modify cases (Clinical role - adding cases - can add, edit and view full dataset of own cases)',
					'Search users (User role - Can only search the user registry for networking purposes)':'Search users (User role - Can only search the user registry for networking purposes)'
        	        }" required="true" cssStyle="overflow:hidden; padding-right: 26px;" data-validation="required" data-validation-help="To select multiple values, please keep Ctrl key pressed while selecting values with mouse"/>
            <s:textarea label="Reasons for using the Registry" name="comment" required="true" data-validation-help="Required" data-validation="required"/>

            <td colspan="2"><h3>Personal Information</h3></td>
            <s:textfield label="Position" name="portalUser.position" required="true" data-validation="required"/>
            <s:textarea label="Professional Society membership" name="portalUser.society" required="true" data-validation="required"/>

            <td colspan="2"><h3>Contact Information</h3></td>
            <s:textfield label="Telephone" name="portalUser.tel" required="true" data-validation-help="Please enter a valid phone number. i.e. i.e. +44 141 330 2000" data-validation-error-msg="Must enter a valid phone number. i.e. +44 141 330 2000" data-validation="phone required"/>
            <s:textfield label="Fax" name="portalUser.fax" data-validation-help="Please enter a valid phone number. i.e. i.e. +44 141 330 2000" data-validation-error-msg="Must enter a valid phone number. i.e. +44 141 330 2000" data-validation="custom" data-validation-regexp="^((\+)|(0{2}))?(\d{2}|\d{3})-?\s?(\d{3})-?\s?(\d{3})-?\s?(\d{3,4})$"/>
            <s:textarea label="Institute Postal Address" name="portalUser.address" required="true" data-validation="required"/>

            <td colspan="2"><h3>Professional Expertise</h3></td>
            <s:select label="Primary Role" name="portalUser.primaryRole" headerKey="" headerValue="--- Select Your Primary Role ---" list="#{
							'Clinician':'Clinician',
							'Scientist':'Scientist',
							'Clinical Investigator':'Clinical Investigator'
							}" data-validation-help="Required" data-validation="required"/>
            <s:checkboxlist label="Secondary Roles" name="portalUser.secondaryRoles" listKey="value" list="#{
							'Biochemical Evaluation':'Biochemical Evaluation',
							'Genetic Evaluation':'Genetic Evaluation',
							'Clinical Evaluation':'Clinical Evaluation',
							'Medical Care':'Medical Care',
							'Surgical Care':'Surgical Care',
							'Psychological Care':'Psychological Care',
							'Genetic Counselling':'Genetic Counselling'
							}" listValue="value" cssStyle="vertical" theme="idsd_xhtml"/>
            <s:textarea label="Other Secondary Roles" name="portalUser.secondaryRolesOther"/>
            <s:checkboxlist label="Areas of Interest" name="portalUser.interest" listKey="value" list="#{
                            'Congenital Adrenal Hyperplasia':'Congenital Adrenal Hyperplasia',
                           'Gonadal Development':'Gonadal Development',
                           'Androgen Synthesis':'Androgen Synthesis',
                           'Androgen Action':'Androgen Action',
                           'Androgen Excess':'Androgen Excess',
                           'Mullerian Development':'Mullerian Development'
                           }" listValue="value" cssStyle="vertical" theme="idsd_xhtml"/>
            <s:textarea label="Other Areas of Interest" name="portalUser.interestOther"/>
            <s:textarea label="Special Interest" name="portalUser.specialInterest"/>
            <s:radio label="Searchable by Others?" name="portalUser.searchable" list="#{'true':'Yes','false':'No'}"/>
            <s:submit value="Create My Account"/>
          </s:form>

          <script>
            $.validate({
//			language : {
//				requiredFields: 'Du måste bocka för denna'
//			},
//			validateOnBlur : validateOnBlur,
//			errorMessagePosition : messagePosition,
//			scrollToTopOnError : true,
//			lang : 'sv',
//			sanitizeAll : 'trim', // only used on form C
              // borderColorOnError : 'purple',
              modules: 'security, location',
              onModulesLoaded: function () {
                $('#newCountry').suggestCountry();
//				$('#swedish-county-suggestions').suggestSwedishCounty();
                $('#password').displayPasswordStrength();
              },
              onValidate: function ($f) {

                console.log('about to validate form ' + $f.attr('id'));

                var $callbackInput = $('#callback');
                if ($callbackInput.val() == 1) {
                  return {
                    element: $callbackInput,
                    message: 'This validation was made in a callback'
                  };
                }
              },
              onError: function ($form) {
                alert('Invalid ' + $form.attr('id'));
              },
              onSuccess: function ($form) {
                alert('Valid ' + $form.attr('id'));
                return false;
              }
            });
            //select Other country
            var countryBox = $("#register_form_portalUser_country")
            countryBox.change(function () {
              if ("Other" == countryBox.val()) {
                alert("Other");
              }
            });
          </script>
        </div>
        <jsp:include page="/jsp/page/page_foot.jsp"/>
      </div>
      <jsp:include page="index_right.jsp"/>
    </div>
    <jsp:include page="/html/access_tracking.html"/>
  </body>
</html>