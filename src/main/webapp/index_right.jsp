<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="right_body" class='right_body'>
	<div id='partner_logo' class='partner_logo'>
		<div>
			<a target='_blank' href='http://www.sphsu.mrc.ac.uk/'>
			    <img src="<%=path %>/images/index/mrc.gif" width="100px" alt="mrc_logo" /> </a>
			<p></p>
		</div>
		<div>
			<a target='_blank' href='http://www.gla.ac.uk/'>
			    <img src="<%=path %>/images/index/gu.png" width="100px" alt="gu_logo" />
			</a>
			<p></p>
		</div>
	</div>
	<div id='links' class='links'>
		<!-- 
		<h1>Links</h1>
		<br/>
		<div>
			<a target='_blank' href='http://www.gla.ac.uk/idsd'>
			    I-DSD Website
			</a>
		</div>
		<div>
			<a target='_blank' href='http://www.gla.ac.uk/schools/medicine/medicine/childhealth/i-dsdproject/i-dsdsymposium2013'>
			    I-DSD Symposium 2013
			</a>
		</div>
		<div>
			<a target='_blank' href='http://www.gla.ac.uk/schools/medicine/medicine/childhealth/i-dsdproject/links/meetings'>
			    Meetings
			</a>
		</div>
		<div>
			<a target='_blank' href='http://www.gla.ac.uk/schools/medicine/medicine/childhealth/i-dsdproject/links/societies/'>
			    Societies
			</a>
		</div>
		<div>
			<a target='_blank' href='http://www.gla.ac.uk/schools/medicine/medicine/childhealth/i-dsdproject/links/networksprojects'>
			    Networks
			</a>
		</div>
		<div>
			<a target='_blank' href='http://www.gla.ac.uk/schools/medicine/medicine/childhealth/i-dsdproject/links/patientsparents'>
			    Patients/parents
			</a>
		</div>
		 -->
	</div>
</div>