<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
<sx:head />
</head>

   
   <body>



<% String path = request.getContextPath();%>

<!--  
<style type="text/css">
    .label {color:black; font-style:normal; font-weight:bold}
 
    #table.tr.td: nth-child(even){
    background-color: yellow;
    }
    
    
</style>
 -->

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<script type="text/javascript">
  YUI().use("node", function (Y) {
    Y.on("domready", create_cah_init);
    Y.on("domready", create_cah_visit_init);
  })
</script>



<br/>
<s:actionerror cssClass="error_message"/>
<s:fielderror cssClass="error_message"/>

<br/>

<h1>Record: Register ID <s:property value="registerId"/></h1>

<s:include value="tabs.jsp"/><br/><br/>

<h1> Neonatal Assessment</h1>


<br/><br>
<!-- External Phenotyp table -->


  <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <s:hidden name="dsdCahVisitId" value="%{dsdCahVisitId}"/>
  
  
  <s:form id="upload_neonatal" action="/update/save_neonatal_visit" theme="xhtml" cssClass='required-validate'>
  <s:textfield label="Date of Assessment" name="DsdNeoLongBean.dateOfAssessment" /> 
 
 <sx:datetimepicker name="myBirthday" label="My Birth Day 
   (dd-MM-yyyy)" displayFormat="dd-MM-yyyy" />
   
  
  
   <s:textfield label = "Age at Assessment" name = "DsdNeoLongBean.ageAtAssessment" />
   <s:radio label = "Original Sex Assigned" name="DsdNeoLongBean.gender" list="{'M', 'F', 'Not Assigned',  'Both', 'Other'}" />
    <s:radio label = "Current Gender" name="DsdNeoLongBean.gender" list="{'M','F','Not Assigned', 'Both', 'Other'}" />
    <s:radio label = "Is the child being raised as" name = "DsdNeoLongBean.gender" list="{'M','F', 'Both', 'Other'}" />
    <s:textfield label="Weight"  name="DsdNeoLongBean.Weight" />
    <s:textfield label = "Height" name="DsdNeoLongBean.Height" />
    <s:textfield label = "BMI" name="DsdNeoLongBean.bmi" />
    <s:textfield label = "Mothers Height" name = "DsdNeoLongBean.mothersHeight" />
    
    <s:textfield label = "Fathers Height" name="DsdNeoLongBean.fathersHeight" />
    <s:textfield label = "Mid Parental Height" name="DsdNeoLongBean.midParentalHeight" />
    <s:radio label = "Associated Features" name = "DsdNeoLongBean.associatedFeatures" list="{'Yes','No'}" />
    <s:radio label="Known Syndrome" name = "DsdNeoLongBean.associatedFeatures" list="{'Yes','No'}" />
    <s:textfield label = "Birth Weight" name = "DsdNeoLongBean.birthWeight" />
     <s:textfield label = "Birth Length" name="DsdNeoLongBean.birthLength" />
	<s:textfield label = "Birth Head Circumference"  name = "DsdNeoLongBean.birthHeadCircumference" />
     <s:select name = "DsdNeoLongBean.gestationAge" 
     label = "Gestation Age" list="{'Select','23 weeks','24 weeks','25 weeks','26 weeks','27 weeks','28 weeks','29 weeks',
            '30 weeks','31 weeks','32 weeks','33 weeks','34 weeks','35 weeks','36 weeks','37 weeks',
            '38 weeks','39 weeks','40 weeks','41 weeks','42 weeks','43 weeks'}" />
            
       <s:select name = "DsdNeoLongBean.Meatus" label = "Meatus"
            list = "{ 'Select', 'Normal','Glandular', 'Coronal', 'Penile Shaft', 'Perineo Scrotal'}" />
        <s:select name = "DsdNeoLongBean.leftGonadLocation" label = "Left Gonad Location" 
            list = "{'Select','Labioscrotal','Inguinal','Impalpable'}" />
       <s:select name = "DsdNeoLongBean.rightGonadLocation" label = "Right Gonad Location"
            list = "{'Select','Labioscrotal','Inguinal','Impalpable'}"/> 
        <s:textfield label = "Genital Tubercle Length" name = "DsdNeoLongBean.genitalTubercleLength" /> 
        <s:select name = "DsdNeoLongBean.phallusSize" label = "Phallus Size"
            list = "{'Select','Normal','Small for male','Large for female'}"/>
        <s:radio label = "Labioscrotal Fusion" name = "DsdNeoLongBean.labiosrotalFusion" list="{'Yes','No'}" />
      <s:textfield label = "Anogenital Distance (AGD)" name = "DsdNeoLongBean.anogenitalDistance" /> 
     <s:textfield label = "Anoscrotal Distance (ASD)" name = "DsdNeoLongBean.anoscrotalDistance" /> 
     <s:textfield label="External Masculinisation Score (EMS)" name=" DsdNeoLongBean.anoscrotalDistance"/>
              <s:textfield label="External Genitalis Score (EGS)" name=" DsdNeoLongBean.anoscrotalDistance"/>
             <!-- 4 -->
             
            
             <s:select name = "DsdNeoLongBean.imagingmodalityLeftGonad" label = "Imaging Modality-Left Gonad" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
           
          <s:select name = "DsdNeoLongBean.imagingmodalityrightGonad" label = "Imaging Modality-Right Gonad" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
           <s:select name = "DsdNeoLongBean.leftGonadMorphology" label = "Left Gonad Morphology" 
            list = "{'Select','Absent','Streak','Testis','Ovary','Ovotestis','Other'}" />
            <s:select name = "DsdNeoLongBean.rightGonadMorphology" label = "Right Gonad Morphology" 
            list = "{'Select','Absent','Streak','Testis','Ovary','Ovotestis','Other'}" />
            <s:select name = "DsdNeoLongBean.imagingModalityUterus" label = "Imaging Modality-Uterus" 
             list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.uterusMorphology" label = "Uterus Morphology" 
            list = "{'Select','Absent','Mullerian remnants','Rudimentary','Normal','Not known'}"  />
            <s:select name = "DsdNeoLongBean.imagingMorphologyLeftFallopianTube" label = "Imaging Morphology-Left Fallopian Tube" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.imagingModalityRightFallopianTube" label = "Imaging Modality-Right Fallopian Tube" 
             list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.leftFallopianTubeMorphology" label = "Left Fallopian Tube Morphology" 
             list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeoLongBean.rightFallopianTubeMorphology" label = "Right Fallopian Tube Morphology" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeoLongBean.imagingModalityLeftVasDeferens" label = "Imaging Modality-Left Vas Deferens" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
             <s:select name = "DsdNeoLongBean.imagingModalityRightVasdeferens" label = "Imaging Modality-Right Vas Deferens" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.leftVasDeferensMorphology" label = "Left Vas Deferens Morphology" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeoLongBean.rightvasdeferensmorphology" label = "Right Vas Deferens Morphology" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:textfield label = "Distance- Vaginal Confluence to Bladder Neck" name = "DsdNeoLongBean.distancevaginalconfluencetobladderneck"/>
              <s:textfield label = "Distance- Vaginal Confluence to Introitus" name = "DsdNeoLongBean.distancevaginalconfluencetointroitus"/> 
              
              <!-- 5 -->     
            
              <s:radio label="Has  there been a change in legal sex" name="DsdNeoLongBean.changeInLegalSex" list="{'Yes','No','Not Known'}" />
           <s:radio label="Psychosocial Support for parents" name="DsdNeoLongBean.psychosocialSupportForParents" list="{'Yes','No','Not Known'}" />
   
       <!-- 6Surgery -->
       
       
       
        <s:radio label = "Left Gonad Biospy" name = "DsdNeoLongBean.changeInLegalSex" list="{'Yes','No','Not known'}" />
         <s:radio label = "Right Gonad Biospsy" name = "DsdNeoLongBean.changeInLegalSex" list="{'Yes','No','Not known'}" />
       
       
        <s:select name = "DsdNeoLongBean.genitoplaty" label = "Genitoplasty 1"  
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:select name = "DsdNeoLongBean.genitoplaty" label = "Genitoplasty 2" 
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:select name = "DsdNeoLongBean.genitoplaty" label = "Genitoplasty 3" 
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:radio label = "Left Gonadectomy" name = "DsdNeoLongBean.LeftGonadectomy" list="{'Yes','No','Not known'}" />
              <s:radio label = "Right Gonadectomy" name = "DsdNeoLongBean.RightGonadectomy" list="{'Yes','No','Not known'}" />
               <s:radio label = "Complications following surgery" name = "DsdNeoLongBean.Complicationsfollowingsurgery" list="{'Yes','No','Not known'}"/>
               
               <!-- 7 Medication-->
          
               
               <s:radio label = "Testosterone" name = "DsdNeoLongBean.testosterone" list="{'Yes','No','Not known'}" />
                 <s:radio label = "DHT" name = "Dht" list="{'Yes','No','Not known'}" />
                  <s:radio label = "Aromatase Inhibitor" name = "DsdNeoLongBean.aromataseInhibitor" list="{'Yes','No','Not known'}" />
                   <s:radio label = "GnRH analogues" name = "DsdNeoLongBean.gnrhAnalogues" list="{'Yes','No','Not known'}" />
                    <s:radio label = "Glucocorticoids" name = "DsdNeoLongBean.glucocorticoids" list="{'Yes','No','Not known'}" />
                     <s:radio label = "Fludrocortisone" name = "DsdNeoLongBean.Fludrocortisone" list="{'Yes','No','Not known'}" />
                      <s:radio label = "Oestrogen" name = "DsdNeoLongBean.oestrogen" list="{'Yes','No','Not known'}" />
                       <s:radio label = "Other drugs" name = "DsdNeoLongBean.OtherDrugs" list="{'Yes','No','Not known'}" />
               
             <!-- 8 Lab Tests: Baseline-->
             
            <s:select name = "lh" label = "LH"  list = "{'Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Fsh" label = "FSH"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Amh" label = "AMH"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.inhibinB" label = "Inhibin B"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Androstenedione" label = "Androstenedione"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.totalTestosterone" label = "Total testosterone"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.freeTestosterone" label = "Free testosterone"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Dihydrotestosterone" label = "Dihydrotestosterone"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Oestradiol" label = "Oestradiol"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.17-OHP" label = "17-OHP"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "Urine steroids"  list = "{'Normal','Abnomal'}" />
                        
                        <!-- 9HCG Stimulation Test -->
                        
                        <s:select name = "DsdNeoLongBean.hcgStimulationTest" label = "HCG Stimulation Test"  list = "{'Select',
            'hcg 1500 units x 3 over 1 week','hcg 1500 units x 7 over 3 weeks','Other- please specify (free text)'}" />
                        <s:select name = "DsdNeoLongBean.Androstenedione" label = "Androstenedione"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.totalTestosterone" label = "Total testosterone"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.Dihydrotestosterone" label = "Dihydrotestosterone"  list = "{'Select','Low','Normal','High','Not known'}"/>
                        
                        <!-- 10 Adrenal Stimulation Test -->
                       
                        
                         <s:textfield key = "Adrenal Stimulation Test" name = "AdrenalStimulationTest" />
                       
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "17-OHP"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "11-deoxycortisol"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "Pregnenolone"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "17-OH Pregnenolone"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "DHEA"  list = "{'Select','Low','Normal','High','Not known'}" />
                       
                        <s:textfield key = "Free Text" name = "DsdNeoLongBean.FreeText" />
                        
                        <s:submit type="button" name="btnSave" />
                        </div>
       
        
       
        
      
  
   </s:form>
    
    
   
   
   <jsp:include page="/jsp/page/page_foot.jsp"/>
   
    </body>
     </html>
   