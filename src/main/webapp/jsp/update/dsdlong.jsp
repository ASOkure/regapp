<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<%@ taglib prefix = "sx" uri="/struts-dojo-tags" %>



<% String path = request.getContextPath();%>
<!DOCTYPE html>
 <html>
 <head>
<s:head/>
 <sx:head />
 



 <style type="text/css">
    .label { font-style:normal; }
    
    .tdLabel {text-align:left;  vertical-align:top; }
    
    
    
    .vl {
  border-left: 2px solid black;
  height: 100%;
  position: absolute;
  left: 50%;
  margin-left: -3px;
  top: 0;
}


   #labs {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
}

#labs td, #labs th {
  border: 1px solid #ddd;
  padding: 8px;
}

#labs tr:nth-child(even){background-color: #f2f2f2;}

#labs tr:hover {background-color: #ddd;}

#labs th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: white;
  color: black;
} 
   
</style>
<script type="text/javascript">


function changetextbox()
{
    if (document.getElementById("leftGonadMorphology").value === "other") {
        document.getElementById("popfreetextbox").disable='false';
    } else {
        document.getElementById("popfreetextbox").disable='true';
    }
}


</script>
 
 
 </head>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

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
    <s:param name="tablecolspan" value="%{2}" />
</s:bean>
  
  <s:form id="upload_neonatal" action="/update/dsdlong" theme="xhtml"> 
 
 <!-- div vertical line 
  <div class="vl"> 
-->
 <tr>
 <td><h3>DSD</h3></td>
 </tr>
  
 <s:textfield label="Neonatal Visit Id "  name="DsdNeonatalVisitsBean.dsd_neonatal_visit_id"  /> 
 <sx:datetimepicker label="Date of Assessment [?]"  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate"  /> 
<s:textfield label= "Age at Assessment [?]" name = "DsdNeonatalVisitsBean.age_at_assessment" />

<!--  date sample
<sx:datetimepicker name="date1" label="Format (dd-MMM-yyyy)" 
displayFormat="dd-MMM-yyyy" value="todayDate" />-->


  <tr>
 <td><h3>General</h3></td>
 </tr>
  
  <s:radio label = "Original Sex Assigned [?]" name="DsdNeonatalVisitsBean.gender" list="{'M', 'F', 'Not Assigned',  'Both', 'Other'}" theme="css_xhtml" />
    <s:radio label = "Current Gender [?]" name="DsdNeonatalVisitsBean.gender " list="{'M','F','Not Assigned', 'Both', 'Other'}" />
    <s:radio label = "Is the child being raised as [?]" name = "DsdNeonatalVisitsBean.current_gender" list="{'M','F', 'Both', 'Other'}" />
    <s:textfield label="Weight [?]"  name="DsdNeonatalVisitsBean.weight" />
               
               
    <s:textfield label = "Height [?]" name="DsdNeonatalVisitsBean.height" />
    <s:textfield label = "BMI [?]" name="DsdNeonatalVisitsBean.bmi" />
    <s:textfield label = "Mothers Height [?]" name = "DsdNeonatalVisitsBean.mothers_height" />
    
    <s:textfield label = "Fathers Height [?]" name="DsdNeonatalVisitsBean.fathers_height" />
    <s:textfield label = "Mid Parental Height [?]" name="DsdNeonatalVisitsBean.mid_parental_height" />
    <s:radio label = "Associated Features [?]" name = "DsdNeonatalVisitsBean.associated_features" list="{'Yes','No'}" />
    <s:radio label="Known Syndrome [?]" name = "DsdNeonatalVisitsBean.known_syndrome" list="{'Yes','No'}" />
    <s:textfield label = "Birth Weight [?]" name = "DsdNeonatalVisitsBean.birth_weight" />
     <s:textfield label = "Birth Length [?]" name="DsdNeonatalVisitsBean.birth_length" />
  <s:textfield label = "Birth Head Circumference [?]"  name = "DsdNeonatalVisitsBean.birth_head_circumference" />
    
   
    
      <tr>
 <td><h3>External Phenotype</h3></td>
 </tr>

     <s:select name = "DsdNeonatalVisitsBean.gestation-age" 
     label = "Gestation Age [?]" list="{'Select','23 weeks','24 weeks','25 weeks','26 weeks','27 weeks','28 weeks','29 weeks',
            '30 weeks','31 weeks','32 weeks','33 weeks','34 weeks','35 weeks','36 weeks','37 weeks',
            '38 weeks','39 weeks','40 weeks','41 weeks','42 weeks','43 weeks'}" />
            
       <s:select name = "DsdNeonatalVisitsBean.meatus" label = "Meatus [?]"
            list = "{ 'Select', 'Typical female','Perineal', 'Scrotal', 'Penoscrotal', 'Penile', 'Coronal', 'Typical male'}" />
        
        <s:select name = "DsdNeonatalVisitsBean.left_gonad_location" label = "Left Gonad Location [?]" 
         list = "{'Select', 'Impalpable', 'Inguinal','Inguinoscrotal','Labioscrotal'}" />
       
       <s:select name = "DsdNeonatalVisitsBean.right_gonad_location" label = "Right Gonad Location [?]"
            list = "{'Select', 'Impalpable', 'Inguinal','Inguinoscrotal','Labioscrotal'}"/> 
       
        <s:select name = "DsdNeonatalVisitsBean.genital_tubercle_length" label = "Genital Tubercle Length [?]" 
        list = "{'Select','<10','10-20','21-25', '26-30', '>30', 'Not known'}"/>
       
        <s:select name = "DsdNeonatalVisitsBean.phallus_size" label = "Phallus Size [?]"
            list = "{'Select','Normal','Small for male','Large for female'}"/>
        <s:radio label = "Labioscrotal Fusion [?]" name = "DsdNeonatalVisitsBean.labiosrotal_fusion" list="{'Yes','No'}" />
      <s:textfield label = "Anogenital Distance (AGD) [?]" name = "DsdNeonatalVisitsBean.anogenital_distance" /> 
     <s:textfield label = "Anoscrotal Distance (ASD) [?]" name = "DsdNeonatalVisitsBean.anoscrotal_distance" /> 
     <s:textfield label="External Masculinisation Score (EMS) [?]" name=" DsdNeonatalVisitsBean.anoscrotal_distance"/>
     <s:textfield label="External Genitalia Score (EGS) [?]" name=" DsdNeonatalVisitsBean.external_genitalia_score"/>
           
             
             <tr>
 <td><h3>Internal Phenotype</h3></td>
 </tr>
 
             <input type="text" name="DsdNeonatalVisitsBean.imaging_modality_left_Gonad_other" id="popfreetextbox" />
             
             <s:select name = "DsdNeonatalVisitsBean.imaging_modality_left_Gonad" label = "Imaging Modality-Left Gonad [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
           
          <s:select name = "DsdNeonatalVisitsBean.imaging_modality_right_gonad"   onChange="changetextbox()"  label = "Imaging Modality-Right Gonad [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}"   />
           
           <s:select id="leftGonadMorphology" name = "DsdNeonatalVisitsBean.left_gonad_morphology"  onChange="changetextbox()" label = "Left Gonad Morphology [?]" 
            list = "{'Select','Absent','Streak','Testis','Ovary','Ovotestis','Other'}" />
            
            <s:select name = "DsdNeonatalVisitsBean.right_gonad_morphology" label = "Right Gonad Morphology [?]" 
            list = "{'Select','Absent','Streak','Testis','Ovary','Ovotestis','Other'}" />
            
             <s:select name = "DsdNeonatalVisitsBean.imaging_modality_uterus" label = "Imaging Modality-Uterus [?]" 
             list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeonatalVisitsBean.uterus_morphology" label = "Uterus Morphology [?]" 
            list = "{'Select','Absent','Mullerian remnants','Rudimentary','Normal','Not known'}"  />
            <s:select name = "DsdNeonatalVisitsBean.imaging_morphology_left_fallopian_tube" label = "Imaging Morphology-Left Fallopian Tube [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeonatalVisitsBean.imaging_modality_right_fallopianTube" label = "Imaging Modality-Right Fallopian Tube [?]" 
             list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeonatalVisitsBean.left_fallopian_tubeMorphology" label = "Left Fallopian Tube Morphology [?]" 
             list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeonatalVisitsBean.right_fallopian_tube_morphology" label = "Right Fallopian Tube Morphology [?]" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeonatalVisitsBean.imaging_modality_left_vasDeferens" label = "Imaging Modality-Left Vas Deferens [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
             <s:select name = "DsdNeonatalVisitsBean.imaging_modality_right_vasdeferens" label = "Imaging Modality-Right Vas Deferens [?]" 
            list = "{'Select','US','MRI','Genitogram','Laparoscopy','Genitoscopy'}" />
            <s:select name = "DsdNeonatalVisitsBean.left_vas_deferens_morphology" label = "Left Vas Deferens Morphology [?]" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:select name = "DsdNeonatalVisitsBean.right_vas_deferen_smorphology" label = "Right Vas Deferens Morphology [?]" 
            list = "{'Select','Absent','Rudimentary','Normal','Not Known'}" />
            <s:textfield label = "Distance- Vaginal Confluence to Bladder Neck [?]" name = "DsdNeonatalVisitsBean.distance_vaginal_confluence_to_bladder_neck"/>
              <s:textfield label = "Distance- Vaginal Confluence to Introitus [?]" name = "DsdNeonatalVisitsBean.distance_vaginal_confluence_to_introitus"/> 
           
              
             <tr>
 <td><h3>Psychosocial</h3></td>
 </tr>
            
              <s:radio label="Has  there been a change in legal sex [?]" name="DsdNeonatalVisitsBean.change_in_legal_sex" list="{'Yes','No','Not Known'}" />
           <s:radio label="Psychosocial Support for parents [?]" name="DsdNeonatalVisitsBean.psycho_social_support_for_parents" list="{'Yes','No','Not Known'}" />
   
       
                <tr>
 <td><h3>Surgery</h3></td>
 </tr>
       
        <s:radio label = "Left Gonad Biospy [?]" name = "DsdNeonatalVisitsBean.left_gonad_biospy" list="{'Yes','No','Not known'}" />
         <s:radio label = "Right Gonad Biospsy [?]" name = "DsdNeonatalVisitsBean.right_gonad_biospsy" list="{'Yes','No','Not known'}" />
       
       
        <s:select name = "DsdNeonatalVisitsBean.genitoplaty_one" label = "Genitoplasty 1 [?]"  
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:select name = "DsdNeonatalVisitsBean.genitoplaty_two" label = "Genitoplasty 2 [?]" 
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:select name = "DsdNeonatalVisitsBean.genitoplaty_three" label = "Genitoplasty 3 [?]" 
            list = "{'Select','None','Feminising inc vaginoplasty','Feminising, no vaginoplasty','Orchidopexy', 'Masculinising'}" />
             <s:radio label = "Left Gonadectomy [?]" name = "DsdNeonatalVisitsBean.left_gonadectomy" list="{'Yes','No','Not known'}" />
              <s:radio label = "Right Gonadectomy [?]" name = "DsdNeonatalVisitsBean.right_gonadectomy" list="{'Yes','No','Not known'}" />
               <s:radio label = "Complications following surgery [?]" name = "DsdNeonatalVisitsBean.complications_following_surgery" list="{'Yes','No','Not known'}"/>
               
             
          
                <tr>
 <td><h3>Medication</h3></td>
 </tr>
               <s:radio label = "Testosterone [?]" name = "DsdNeonatalVisitsBean.testosterone" list="{'Yes','No','Not known'}" />
                 <s:radio label = "DHT [?]" name = "Dht" list="{'Yes','No','Not known'}" />
                  <s:radio label = "Aromatase Inhibitor [?]" name = "DsdNeonatalVisitsBean.aromatase_inhibitor" list="{'Yes','No','Not known'}" />
                   <s:radio label = "GnRH analogues [?]" name = "DsdNeonatalVisitsBean.gnrh_analogues" list="{'Yes','No','Not known'}" />
                    <s:radio label = "Glucocorticoids [?]" name = "DsdNeonatalVisitsBean.glucocorticoids" list="{'Yes','No','Not known'}" />
                     <s:radio label = "Fludrocortisone [?]" name = "DsdNeonatalVisitsBean.fludrocortisone" list="{'Yes','No','Not known'}" />
                      <s:radio label = "Oestrogen [?]" name = "DsdNeonatalVisitsBean.oestrogen" list="{'Yes','No','Not known'}" />
                       <s:radio label = "Other drugs [?]" name = "DsdNeonatalVisitsBean.other_drugs" list="{'Yes','No','Not known'}" />
     
    
              <tr>
 <td><h3>Labs</h3></td>
 </tr>
 <table id="labs">
 
 
  <tr>
     
    <th>Test</th>
     <th>Result</th>
     <th>Date</th>
     
     </tr> 
     <tr>
   <td> LH [?]</td>
   <td><select>
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
   <td><sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate"/></td> </td>
   </tr>
 
          
                      
              <tr>
              <td>FSH [?] </td>  
               <td><select>
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
             
              <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate"/></td>
              </tr>
                      
                        
                   <tr>  
                    <td>AMH [?]</td>  
                     <td><select>
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                    <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate" /></td> 
                    </tr>
                      
                        
                    <tr>   
                    <td>Inhibin B [?]</td>
                    
                      <td><select  name = "DsdNeonatalVisitsBean.inhibin_b">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                    
                    </td>  <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate" /></td> 
                    </tr>
                      
                        
                    <tr>   
                    <td>Androstenedione [?]</td>  
                    
                      <td><select name = "DsdNeonatalVisitsBean.androstenedione">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                    
                    
                    <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> 
                    </tr>
                      
                        
                     <tr> 
                     <td>Total testosterone [?]</td>  
                     
                       <td><select  name = "DsdNeonatalVisitsBean.total_testosterone">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                     
                     <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate"/></td> 
                     </tr>
                      
                        
                    <tr>
                    <td> Free testosterone [?]</td>  
                   
                     <td><select  name = "DsdNeonatalVisitsBean.free_testosterone">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                    <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate" /></td> 
                    </tr>
                      
                        
                   <tr>
                   <td> Dihydrotestosterone [?]</td>  
                   
                     <td><select name = "DsdNeonatalVisitsBean.dihydrotestosterone">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                   
                   <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate"/> </td>
                   </tr>
                      
                        
                     <tr>   
                     <td>Oestradiol [?] </td>  
                    
                      <td><select name = "DsdNeonatalVisitsBean.oestradiol>
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                     <td><sx:datetimepicker   name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate"/></td>
                     
                     </tr>
                      
                      
                        
                     <tr>  
                      <td> 17-OHP [?]</td> 
                      
                      
                         <td><select name = "DsdNeonatalVisitsBean.ohp17">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                      
                      <td><sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate" /></td>
                     
                      </tr>
                      
                      
                        
                    <tr>   
                    <td>Urine steroids [?] </td>  
                    
                      <td><select name = "DsdNeonatalVisitsBean.urine_steroids">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 </select></td>
<td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate"/></td> 
                   
  </tr>
                      
                    <tr>   
                    <td> HCG Stimulation Test [?] </td>
            
              <td><select>
  <option value="select">Select</option>
  <option value="hcg 1500 units x 3 over 1 week">hcg 1500 units x 3 over 1 weekoption>
  <option value="hcg 1500 units x 7 over 3 weeks">hcg 1500 units x 7 over 3 weeks</option>
  <option value="Other- please specify (free text)">Other- please specify (free text)</option>
 
 
</select></td>
            
            
            <td><sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate" /></td> 
            </tr>
                      
                       
                       
                    <tr>   
                    <td>Androstenedione [?]</td>  
                    
                      <td><select name = "DsdNeonatalVisitsBean.androstenedione">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                    
                    
                    <td> <sx:datetimepicker name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate" /></td> 
                    </tr>
                      
                   <tr>   
                   <td>Total testosterone [?]</td> 
                    
                    
                        <td><select name = "DsdNeonatalVisitsBean.total_testosterone">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                    
                    
                    <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate" /></td> 
                    </tr>
                      
                   <tr>   
                   <td> Dihydrotestosterone [?]</td>  
                   
                     <td><select  name = "DsdNeonatalVisitsBean.dihydrotestosterone">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                   
                   
                   <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> 
                   </tr>
                      
                        
                     
                       
                        
                      <tr>   
                      <td>Adrenal Stimulation Test [?] </td> 
                       
                          <td><select name = "DsdNeonatalVisitsBean.AdrenalStimulationTest">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                       
                       
                       <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate" /></td>
                       </tr>
                      
                       
                     <tr>   
                     <td> 17-OHP AST [?]</td> 
                      
                        <td><select  name = "DsdNeonatalVisitsBean.ohp17ast">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                      
                      
                      
                      <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate" /></td> 
                      </tr>
                      
                     <tr>   
                     <td>11-deoxycortisol [?] </td>
                     
                      
                      
                        <td><select name = "DsdNeonatalVisitsBean.Deoxycortisol11">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                      
  <td><sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate"/></td>
                      </tr>
                      
                     <tr>   
                     <td> Pregnenolone [?]</td> 
                     
                         <td><select name = "DsdNeonatalVisitsBean.pregnenolone">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                     
                     <td><sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment" displayFormat="dd-MMM-yyyy" value="todayDate"/></td> </tr>
                      
                     <tr>   
                     <td> 17-OH Pregnenolone [?]</td> 
                     
                          <td><select name = "DsdNeonatalVisitsBean.Ph17Pregnenolone">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                     
                     
                     <td> <sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate"/></td> 
                     </tr>
                      
                       <tr>   
                       <td>  DHEA [?] </td> 
                       
                          <td><select name = "DsdNeonatalVisitsBean.dhea">
  <option value="select">Select</option>
  <option value="low">Low</option>
  <option value="normal">Normal</option>
  <option value="high">High</option>
  <option value="notknown">Not known</option>
 
</select></td>
                       
                       <td><sx:datetimepicker  name="DsdNeonatalVisitsBean.date_of_assessment"  displayFormat="dd-MMM-yyyy" value="todayDate"/></td>
                       </tr>
                      
                       
                      
                        
</table>


<br><br>
                     
 <s:textfield key = "Free Text [?]" name = "DsdNeonatalVisitsBean.free_text"/>
               
 
 <!--  
 <table id="labs">
     <tr>
     
    <th>Test</th>
     <th>Result</th>
     <th>Date</th>
     
     </tr>    
          <tr><td><s:select name = "DsdNeonatalVisitsBean.lh" label = "LH [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"/></td> </tr>
                      
              <tr><td><s:select name = "DsdNeonatalVisitsBean.fsh" label = "FSH [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </td></tr>
                      
                        
                   <tr>   <td>     <s:select name = "DsdNeonatalVisitsBean.amh" label = "AMH [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                        
                    <tr>   <td>    <s:select name = "DsdNeonatalVisitsBean.inhibin_b" label = "Inhibin B [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                        
                    <tr>   <td>    <s:select name = "DsdNeonatalVisitsBean.androstenedione" label = "Androstenedione [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                        
                     <tr>   <td>   <s:select name = "DsdNeonatalVisitsBean.total_testosterone" label = "Total testosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                        
                    <tr>   <td>    <s:select name = "DsdNeonatalVisitsBean.free_testosterone" label = "Free testosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                        
                   <tr>   <td>     <s:select name = "DsdNeonatalVisitsBean.dihydrotestosterone" label = "Dihydrotestosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </td></tr>
                      
                        
                     <tr>   <td>   <s:select name = "DsdNeonatalVisitsBean.oestradiol" label = "Oestradiol [?] "  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </td></tr>
                      
                      
                        
                     <tr>   <td>   <s:select name = "DsdNeonatalVisitsBean.ohp17" label = "17-OHP [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </td></tr>
                      
                      
                        
                    <tr>   <td>    <s:select name = "DsdNeonatalVisitsBean.urine_steroids" label = "Urine steroids [?]"  list = "{'Normal','Abnomal'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                      
                        
                       
                        
                    <tr>   <td>    <s:select name = "DsdNeonatalVisitsBean.hcg_stimulation_test" label = "HCG Stimulation Test [?]"  list = "{'Select',
            'hcg 1500 units x 3 over 1 week','hcg 1500 units x 7 over 3 weeks','Other- please specify (free text)'}" /> </td>  <td><sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </tr>
                      
                       
                       
                    <tr>   <td>    <s:select name = "DsdNeonatalVisitsBean.androstenedione" label = "Androstenedione [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                   <tr>   <td>     <s:select name = "DsdNeonatalVisitsBean.total_testosterone" label = "Total testosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                   <tr>   <td>     <s:select name = "DsdNeonatalVisitsBean.dihydrotestosterone" label = "Dihydrotestosterone [?]"  list = "{'Select','Low','Normal','High','Not known'}"/></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                        
                     
                       
                        
                      <tr>   <td>   <s:textfield key = "Adrenal Stimulation Test [?]" name = "DsdNeonatalVisitsBean.AdrenalStimulationTest" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </td></tr>
                      
                       
                     <tr>   <td>   <s:select name = "DsdNeonatalVisitsBean.ohp17ast" label = "17-OHP AST [?]"  list = "{'Select','Low','Normal','High','Not known'}" /></td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                     <tr>   <td>   <s:select name = "DsdNeonatalVisitsBean.Deoxycortisol11" label = "11-deoxycortisol [?]"  list = "{'Select','Low','Normal','High','Not known'}" /> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </td></tr>
                      
                     <tr>   <td>   <s:select name = "DsdNeonatalVisitsBean.pregnenolone" label = "Pregnenolone [?]"  list = "{'Select','Low','Normal','High','Not known'}" /> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                     <tr>   <td>   <s:select name = "DsdNeonatalVisitsBean.Ph17Pregnenolone" label = "17-OH Pregnenolone [?]"  list = "{'Select','Low','Normal','High','Not known'}" /> </td>  <td> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /></td> </tr>
                      
                       <tr>   <td> <s:select name = "DsdNeonatalVisitsBean.dhea" label = "DHEA [?]"  list = "{'Select','Low','Normal','High','Not known'}" /> <sx:datetimepicker displayFormat="dd-MMM-yyyy" value="todayDate"  /> </td></tr>
                      
                       
                      <tr>   <td>  <s:textfield key = "Free Text [?]" name = "DsdNeonatalVisitsBean.free_text" />
                        
</table>
                     
                     
                 -->    
                     
                     <!--   </div> end verticalline div --> 
                     
                     
                     
                     
                     
                      
                     
                   
        </s:form>
    
    <s:submit type="button" name="btnSave"/>
   
   
   <jsp:include page="/jsp/page/page_foot.jsp"/>
   
    </body>
     </html>
  