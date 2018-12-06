<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>


<% String path = request.getContextPath();%>

 <html>
 <head>
<style type="text/css">
 body{
 
   background-color:#FF9933;
   
    }
    
 </style>
 
 </head>

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
<body>

<h1>Record: Register ID <s:property value="registerId"/></h1>

<s:include value="tabs.jsp"/><br/><br/>

<h1> Neonatal Assessment</h1>


<br/><br>
 <s:hidden name="registerId" value="%{registerId}"/>
  <s:hidden name="mode" value="%{mode}"/>
  <s:hidden name="dsdCahVisitId" value="%{dsdCahVisitId}"/>
  
  <s:bean name="java.util.HashMap" id="qTableLayout">
    <s:param name="tablecolspan" value="%{2.4}" />
</s:bean>
  
  <s:form id="upload_neonatal" action="/update/save_neonatal_visit" theme="qxhtml"  cssClass='required-validate'>
 

 <tr>
 <td><h3>DSD</h3></td>
 </tr>

 
 <s:textfield label="Date of Assessment [?]"  name="DsdNeoLongBean.dateOfAssessment" /> 
 <s:textfield label= "Age at Assessment [?]" name = "DsdNeoLongBean.ageAtAssessment" />
  

 <tr>
 <td><h3>General</h3></td>
 </tr>
   
  
  
  
  
   <s:radio label = "Original Sex Assigned [?]" name="DsdNeoLongBean.gender" list="{'M', 'F', 'Not Assigned',  'Both', 'Other'}" />
    <s:radio label = "Current Gender [?]" name="DsdNeoLongBean.gender " list="{'M','F','Not Assigned', 'Both', 'Other'}" />
    <s:radio label = "Is the child being raised as [?]" name = "DsdNeoLongBean.gender" list="{'M','F', 'Both', 'Other'}" />
    <s:textfield label="Weight [?]"  name="DsdNeoLongBean.Weight" cssClass="float-range-0.7-400"/>
               
               
    <s:textfield label = "Heigh [?]t" name="DsdNeoLongBean.Height" />
    <s:textfield label = "BMI [?]" name="DsdNeoLongBean.bmi" />
    <s:textfield label = "Mothers Height [?]" name = "DsdNeoLongBean.mothersHeight" />
    
    <s:textfield label = "Fathers Height [?]" name="DsdNeoLongBean.fathersHeight" />
    <s:textfield label = "Mid Parental Height [?]" name="DsdNeoLongBean.midParentalHeight" />
    <s:radio label = "Associated Features [?]" name = "DsdNeoLongBean.associatedFeatures" list="{'Yes','No'}" />
    <s:radio label="Known Syndrome [?]" name = "DsdNeoLongBean.associatedFeatures" list="{'Yes','No'}" />
    <s:textfield label = "Birth Weight [?]" name = "DsdNeoLongBean.birthWeight" />
     <s:textfield label = "Birth Length [?]" name="DsdNeoLongBean.birthLength" />
	<s:textfield label = "Birth Head Circumference [?]"  name = "DsdNeoLongBean.birthHeadCircumference" />
     
      <tr>
 <td><h3>External Phenotype</h3></td>
 </tr>
     <s:select name = "DsdNeoLongBean.gestationAge" 
     label = "Gestation Age [?]" list="{'Select','23 weeks','24 weeks','25 weeks','26 weeks','27 weeks','28 weeks','29 weeks',
            '30 weeks','31 weeks','32 weeks','33 weeks','34 weeks','35 weeks','36 weeks','37 weeks',
            '38 weeks','39 weeks','40 weeks','41 weeks','42 weeks','43 weeks'}" />
            
       <s:select name = "DsdNeoLongBean.Meatus" label = "Meatus [?]"
            list = "{ 'Select', 'Typical female','Perineal', 'Scrotal', 'Penoscrotal', 'Penile', 'Coronal', 'Typical male'}" />
        
        <s:select name = "DsdNeoLongBean.leftGonadLocation" label = "Left Gonad Location [?]" 
         list = "{'Select', 'Impalpable', 'Inguinal','Inguinoscrotal','Labioscrotal'}" />
       
       <s:select name = "DsdNeoLongBean.rightGonadLocation" label = "Right Gonad Location [?]"
            list = "{'Select', 'Impalpable', 'Inguinal','Inguinoscrotal','Labioscrotal'}"/> 
       
        <s:select name = "DsdNeoLongBean.genitalTubercleLength" label = "Genital Tubercle Length [?]" 
        list = "{'Select','<10','10-20','21-25', '26-30', '>30', 'Not known'}"/>
       
        <s:select name = "DsdNeoLongBean.phallusSize" label = "Phallus Size [?]"
            list = "{'Select','Normal','Small for male','Large for female'}"/>
        <s:radio label = "Labioscrotal Fusion [?]" name = "DsdNeoLongBean.labiosrotalFusion" list="{'Yes','No'}" />
      <s:textfield label = "Anogenital Distance (AGD) [?]" name = "DsdNeoLongBean.anogenitalDistance" /> 
     <s:textfield label = "Anoscrotal Distance (ASD) [?]" name = "DsdNeoLongBean.anoscrotalDistance" /> 
     <s:textfield label="External Masculinisation Score (EMS) [?]" name=" DsdNeoLongBean.anoscrotalDistance"/>
     <s:textfield label="External Genitalia Score (EGS) [?]" name=" DsdNeoLongBean.anoscrotalDistance"/>
            
             
             <tr>
 <td><h3>Internal Phenotype</h3></td>
 </tr>
             <s:select name = "DsdNeoLongBean.imagingmodalityLeftGonad" label = "Imaging Modality-Left Gonad [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
           
          <s:select name = "DsdNeoLongBean.imagingmodalityrightGonad" label = "Imaging Modality-Right Gonad [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
           
           <s:select name = "DsdNeoLongBean.leftGonadMorphology" label = "Left Gonad Morphology [?]" 
            list = "{'Select','Absent','Streak','Testis','Ovary','Ovotestis','Other'}" />
            
            <s:select name = "DsdNeoLongBean.rightGonadMorphology" label = "Right Gonad Morphology [?]" 
            list = "{'Select','Absent','Streak','Testis','Ovary','Ovotestis','Other'}" />
            
             <s:select name = "DsdNeoLongBean.imagingModalityUterus" label = "Imaging Modality-Uterus [?]" 
             list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.uterusMorphology" label = "Uterus Morphology [?]" 
            list = "{'Select','Absent','Mullerian remnants','Rudimentary','Normal','Not known'}"  />
            <s:select name = "DsdNeoLongBean.imagingMorphologyLeftFallopianTube" label = "Imaging Morphology-Left Fallopian Tube [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.imagingModalityRightFallopianTube" label = "Imaging Modality-Right Fallopian Tube [?]" 
             list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.leftFallopianTubeMorphology" label = "Left Fallopian Tube Morphology [?]" 
             list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeoLongBean.rightFallopianTubeMorphology" label = "Right Fallopian Tube Morphology [?]" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeoLongBean.imagingModalityLeftVasDeferens" label = "Imaging Modality-Left Vas Deferens [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
             <s:select name = "DsdNeoLongBean.imagingModalityRightVasdeferens" label = "Imaging Modality-Right Vas Deferens [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeoLongBean.leftVasDeferensMorphology" label = "Left Vas Deferens Morphology [?]" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeoLongBean.rightvasdeferensmorphology" label = "Right Vas Deferens Morphology [?]" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:textfield label = "Distance- Vaginal Confluence to Bladder Neck [?]" name = "DsdNeoLongBean.distancevaginalconfluencetobladderneck"/>
              <s:textfield label = "Distance- Vaginal Confluence to Introitus [?]" name = "DsdNeoLongBean.distancevaginalconfluencetointroitus"/> 
              
             <tr>
 <td><h3>Psychosocial</h3></td>
 </tr>
            
              <s:radio label="Has  there been a change in legal sex [?]" name="DsdNeoLongBean.changeInLegalSex" list="{'Yes','No','Not Known'}" />
           <s:radio label="Psychosocial Support for parents [?]" name="DsdNeoLongBean.psychosocialSupportForParents" list="{'Yes','No','Not Known'}" />
   
       
       
       
        <s:radio label = "Left Gonad Biospy [?]" name = "DsdNeoLongBean.changeInLegalSex" list="{'Yes','No','Not known'}" />
         <s:radio label = "Right Gonad Biospsy [?]" name = "DsdNeoLongBean.changeInLegalSex" list="{'Yes','No','Not known'}" />
       
       
        <s:select name = "DsdNeoLongBean.genitoplaty" label = "Genitoplasty 1 [?]"  
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:select name = "DsdNeoLongBean.genitoplaty" label = "Genitoplasty 2 [?]" 
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:select name = "DsdNeoLongBean.genitoplaty" label = "Genitoplasty 3 [?]" 
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:radio label = "Left Gonadectomy [?]" name = "DsdNeoLongBean.LeftGonadectomy" list="{'Yes','No','Not known'}" />
              <s:radio label = "Right Gonadectomy [?]" name = "DsdNeoLongBean.RightGonadectomy" list="{'Yes','No','Not known'}" />
               <s:radio label = "Complications following surgery [?]" name = "DsdNeoLongBean.Complicationsfollowingsurgery" list="{'Yes','No','Not known'}"/>
               
             
          
                <tr>
 <td><h3>Medication</h3></td>
 </tr>
               <s:radio label = "Testosterone [?]" name = "DsdNeoLongBean.testosterone" list="{'Yes','No','Not known'}" />
                 <s:radio label = "DHT [?]" name = "Dht" list="{'Yes','No','Not known'}" />
                  <s:radio label = "Aromatase Inhibitor [?]" name = "DsdNeoLongBean.aromataseInhibitor" list="{'Yes','No','Not known'}" />
                   <s:radio label = "GnRH analogues [?]" name = "DsdNeoLongBean.gnrhAnalogues" list="{'Yes','No','Not known'}" />
                    <s:radio label = "Glucocorticoids [?]" name = "DsdNeoLongBean.glucocorticoids" list="{'Yes','No','Not known'}" />
                     <s:radio label = "Fludrocortisone [?]" name = "DsdNeoLongBean.Fludrocortisone" list="{'Yes','No','Not known'}" />
                      <s:radio label = "Oestrogen [?]" name = "DsdNeoLongBean.oestrogen" list="{'Yes','No','Not known'}" />
                       <s:radio label = "Other drugs [?]" name = "DsdNeoLongBean.OtherDrugs" list="{'Yes','No','Not known'}" />
               
              <tr>
 <td><h3>Labs</h3></td>
 </tr>
             
            <s:select name = "lh" label = "LH [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Fsh" label = "FSH [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Amh" label = "AMH [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.inhibinB" label = "Inhibin B [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Androstenedione" label = "Androstenedione [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.totalTestosterone" label = "Total testosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.freeTestosterone" label = "Free testosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Dihydrotestosterone" label = "Dihydrotestosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.Oestradiol" label = "Oestradiol [?] "  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.17-OHP" label = "17-OHP [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "Urine steroids [?]"  list = "{'Normal','Abnomal'}" />
                        
                       
                        
                        <s:select name = "DsdNeoLongBean.hcgStimulationTest" label = "HCG Stimulation Test [?]"  list = "{'Select',
            'hcg 1500 units x 3 over 1 week','hcg 1500 units x 7 over 3 weeks','Other- please specify (free text)'}" />
                       
                       
                        <s:select name = "DsdNeoLongBean.Androstenedione" label = "Androstenedione [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.totalTestosterone" label = "Total testosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.Dihydrotestosterone" label = "Dihydrotestosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}"/>
                        
                     
                       
                        
                         <s:textfield key = "Adrenal Stimulation Test [?]" name = "AdrenalStimulationTest" />
                       
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "17-OHP [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "11-deoxycortisol [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "Pregnenolone [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "17-OH Pregnenolone [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                        <s:select name = "DsdNeoLongBean.urineSteroids" label = "DHEA [?]"  list = "{'Select','Low','Normal','High','Not known'}" />
                       
                        <s:textfield key = "Free Text [?]" name = "DsdNeoLongBean.FreeText" />
                        
                        
                       
                   
        </s:form>
    
    <s:submit type="button" name="btnSave"/>
   
   
   <jsp:include page="/jsp/page/page_foot.jsp"/>
   
    </body>
     </html>
   