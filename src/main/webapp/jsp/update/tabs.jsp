<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}
</style>

<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>



</head>


<div id="update_nav_tab" class="section_nav_tab">
  <ul id="globalnav">
    <li>
      <s:url id="core_info_url" value="core_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="core_info_url" href="%{core_info_url}" theme="simple">Core Info & Diagnosis</s:a>
    </li>
    <li>
      <s:url id="diagnosis_url" value="clinical_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="diagnosis_url" href="%{diagnosis_url}" theme="simple">Clinical History & Biomaterials</s:a>
    </li>
    <li>
      <s:url id="assessment_url" value="assessment_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="assessment_url" href="%{assessment_url}" theme="simple">Assessments</s:a>
    </li>
    <li>
      <s:url id="gene_analysis_url" value="gene_analysis_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="gene_analysis_url" href="%{gene_analysis_url}" theme="simple">Gene Analysis</s:a>
    </li>
    <li>
      <s:url id="cah_url" value="cah_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="cah_url" href="%{cah_url}" theme="simple">CAH</s:a>
    </li>
    
    <li>
    <div class="dropdown">
    <a onclick="myFunction()" class="dropbtn" >DSD</a>
    
     <div id="myDropdown" class="dropdown-content">
    <a href="#">Link 1</a>
    <a href="#">Link 2</a>
    <a href="#">Link 3</a>
    <a href="#">Link 4</a>
    <a href="#">Link 5</a>
    
     </div>
     </div>
    </li>

   <li>
      <s:url id="dsdlong_url" value="dsdlong_view.action">
        <s:param name="registerId" value="%{registerId}"/>
        <s:param name="mode" value="%{mode}"/>
      </s:url>
      <s:a name="dsdlong_url" href="%{dsdlong_url}" theme="simple">DSD</s:a>
    </li>

  </ul>
</div>
<br/>
</html>