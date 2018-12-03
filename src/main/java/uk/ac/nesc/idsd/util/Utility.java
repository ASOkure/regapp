package uk.ac.nesc.idsd.util;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Feedback;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.security.spring.SecurityUserHolder;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.comparator.DsdAssessmentDateComparator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Utility {
    private static final Log log = LogFactory.getLog(Utility.class);

    private static final String LOCAL = "Local - Sharing data within centre";
    private static final String NATIONAL = "National - Sharing data within country";
    private static final String EEA = "EEA Partners - Sharing data across EEA";
    //    private static final String EEA = "Registered EEA Collaborators - Sharing data across all EEA partners";
    private static final String INTERNATIONAL = "Registered International Collaborators - Sharing data across all international partners";
    private static final String UPLOAD_SESSION_DSD_IDENTIFIER = "UPLOAD_SESSION_DSD_IDENTIFIER_";
    private static final String UPDATE_SESSION_DSD_IDENTIFIER = "UPDATE_SESSION_DSD_IDENTIFIER_";
    private static final int PARAMETER_LIMIT = 999;

    public static String getLoginUserName() {
        UserDetails userDetails = SecurityUserHolder.getCurrentUser();
        return userDetails.getUsername();
    }

    public static String getLoginUserIp() {
        String ip = "";
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getDetails() != null) {
            ip = ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRemoteAddress();
        }
        return ip;
    }

    public static void setUploadSessionRegisterId(Long registerId) throws ServiceException {
        ActionContext.getContext().getSession().put("UPLOAD_SESSION_DSD_REGISTER_ID" + Utility.getLoginUserName(), registerId);
    }

    public static void setUploadSessionDsdObject(DsdIdentifier dsdIdentifier) throws ServiceException {
        ActionContext.getContext().getSession().put(UPLOAD_SESSION_DSD_IDENTIFIER + Utility.getLoginUserName(), dsdIdentifier);
    }

    public static Long getUpdateSessionRegisterId() throws ServiceException {
        return (Long) ActionContext.getContext().getSession().get("UPDATE_SESSION_DSD_IDENTIFIER" + Utility.getLoginUserName());
    }

    //Update session control
    public static void setUpdateSessionRegisterId(Long registerId) throws ServiceException {
        ActionContext.getContext().getSession().put("UPDATE_SESSION_DSD_REGISTER_ID" + Utility.getLoginUserName(), registerId);
    }

    public static DsdIdentifier getUpdateSessionDsdObject() throws ServiceException {
        return (DsdIdentifier) ActionContext.getContext().getSession().get(UPDATE_SESSION_DSD_IDENTIFIER + Utility.getLoginUserName());
    }

    public static void setUpdateSessionDsdObject(DsdIdentifier dsdIdentifier) throws ServiceException {
        ActionContext.getContext().getSession().put(UPDATE_SESSION_DSD_IDENTIFIER + Utility.getLoginUserName(), dsdIdentifier);
    }

    /**
     * Comma Delimited Strings from db needs to converted into list of Strings for Struts checkboxlist tag
     *
     * @param csvString
     * @return
     */
    public static List<String> csvStringToList(String csvString) {
        List<String> stringList = new ArrayList<String>();
        try {
            if (null != csvString && !csvString.isEmpty()) {
                String[] checklistStrings = csvString.trim().split(", ");
                stringList = Arrays.asList(checklistStrings);
            }
        } catch (Exception e) {
            log.error("Error occurred while splitting CSV String: " + csvString + " into String List. ", e);
        }
        return stringList;
    }

    /**
     * capitalise first letter of user first and last name.
     * returns empty if input is empty
     *
     * @param _name
     * @return
     */
    public static String formatUserName(String _name) {
        if (_name.length() > 0) {
            String[] names = _name.trim().split(" ");
            String name = "";
            for (String name1 : names) {
                String n = Character.toUpperCase(name1.charAt(0)) + name1.substring(1);
                name += n + " ";
            }
            return name.trim();
        } else {
            return _name.trim();
        }
    }

    /**
     * gets Properties instance by giving file name
     *
     * @param fileName
     * @return
     */
    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = Utility.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        InputStream in = null;
        try {
            in = url.openStream();
            properties.load(in);
        } catch (Exception e) {
            log.error("Exception in opening properties file", e);
            return null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("Error closing inputStream when loading properties file", e);
                }
            }
        }
        return properties;
    }

    /**
     * check if the program is under development mode or deploy mode
     *
     * @return
     */
    public static boolean isDebug() {
        return (Utility.getProperties("idsd.properties").get("debug").toString().equalsIgnoreCase("yes"));
    }

    /**
     * Return consent level selection map, sorted
     *
     * @return
     */
    public static Map<String, String> getConsentLevels() {
        Map<String, String> consentMap = new TreeMap<String, String>();
        consentMap.put("1", LOCAL);
        consentMap.put("2", NATIONAL);
        consentMap.put("3", EEA);
        //consentMap.put("4", EEA);
        consentMap.put("4", INTERNATIONAL);
        return consentMap;
    }

    /**
     * Return a property value of DsdIdentifier Instance by given property.
     * Returns RAW type
     *
     * @param <T>
     * @param dsdIdentifier
     * @param propertyName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getDsdProperty(DsdIdentifier dsdIdentifier, String propertyName) {
        if (propertyName != null) {
            if (propertyName.equals("dsdIdentifier.registerId")) {
                return (T) dsdIdentifier.getRegisterId();
            }
            if (propertyName.equals("dsdIdentifier.countryName")) {
                return (T) dsdIdentifier.getCountryName();
            }
            if (propertyName.equals("centreName")) {
                return (T) dsdIdentifier.getCentreName();
            }
            if (propertyName.equals("dsdIdentifier.localId")) {
                return (T) dsdIdentifier.getLocalId();
            }
            if (propertyName.equals("dsdIdentifier.yob")) {
                return (T) dsdIdentifier.getYob();
            }
            if (propertyName.equals("dsdIdentifier.aofp")) {
                return (T) dsdIdentifier.getAofp();
            }
            if (propertyName.equals("dsdIdentifier.clinician")) {
                return (T) dsdIdentifier.getClinician();
            }
            if (propertyName.equals("dsdIdentifier.contact")) {
                return (T) dsdIdentifier.getContact();
            }
            if (propertyName.equals("dsdIdentifier.birthWeight")) {
                return (T) dsdIdentifier.getBirthWeight();
            }
            if (propertyName.equals("dsdIdentifier.birthLength")) {
                return (T) dsdIdentifier.getBirthLength();
            }
            if (propertyName.equals("dsdIdentifier.sexAssigned")) {
                return (T) dsdIdentifier.getSexAssigned();
            }
            if (propertyName.equals("dsdIdentifier.currentGender")) {
                return (T) dsdIdentifier.getCurrentGender();
            }
            if (propertyName.equals("dsdIdentifier.karyotype")) {
                return (T) dsdIdentifier.getKaryotype();
            }
            if (propertyName.equals("actualDiagnosis")) {
                return (T) dsdIdentifier.getActualDiagnosis();
            }
            if (propertyName.equals("dsdIdentifier.disorderType")) {
                return (T) dsdIdentifier.getDisorderType();
            }
            if (propertyName.equals("dsdIdentifier.geneticCertainty")) {
                return (T) dsdIdentifier.getGeneticCertainty();
            }
            if (propertyName.equals("dsdIdentifier.assocMalforms")) {
                return (T) dsdIdentifier.getAssocMalforms();
            }
            if (propertyName.equals("dsdIdentifier.clinicalFeatures")) {
                return (T) dsdIdentifier.getClinicalFeatures();
            }
            if (propertyName.equals("dsdIdentifier.biochemistry")) {
                return (T) dsdIdentifier.getBiochemistry();
            }
            if (propertyName.equals("dsdIdentifier.dnaAnalysis")) {
                return (T) dsdIdentifier.getDnaAnalysis();
            }
            if (propertyName.equals("dsdIdentifier.parentalConsanguinity")) {
                return (T) dsdIdentifier.getParentalConsanguinity();
            }
            if (propertyName.equals("dsdIdentifier.dsdHistory")) {
                return (T) dsdIdentifier.getDsdHistory();
            }
            if (propertyName.equals("dsdIdentifier.infertilityHistory")) {
                return (T) dsdIdentifier.getInfertilityHistory();
            }
            if (propertyName.equals("dsdIdentifier.sampleAvailability")) {
                return (T) dsdIdentifier.getSampleAvailability();
            }

            //prepare Assessment list
            if (null != dsdIdentifier.getDsdAssessments() && !dsdIdentifier.getDsdAssessments().isEmpty()) {
                List<DsdAssessment> dsdAssessmentsList = new ArrayList<DsdAssessment>(dsdIdentifier.getDsdAssessments());
                Collections.sort(dsdAssessmentsList, new DsdAssessmentDateComparator());

                //first & latest assessment
                DsdAssessment firstDsdAssessment = dsdAssessmentsList.get(0);
                DsdAssessment latestDsdAssessment = dsdAssessmentsList.get(dsdAssessmentsList.size() - 1);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                if (null != firstDsdAssessment) {
                    if (propertyName.equals("firstDsdAssessment.assessmentDate")) {
                        return (T) dateFormat.format(firstDsdAssessment.getAssessmentDate());
                    }
                    if (propertyName.equals("firstDsdAssessment.phallusLength")) {
                        return (T) firstDsdAssessment.getPhallusLength();
                    }
                    if ("firstDsdAssessment.phallusSize".equals(propertyName)) {
                        return (T) firstDsdAssessment.getPhallusSize();
                    }
                    if ("firstDsdAssessment.urinaryMeatus".equals(propertyName)) {
                        return (T) firstDsdAssessment.getUrinaryMeatus();
                    }
                    if ("firstDsdAssessment.labioscrotalFusion".equals(propertyName)) {
                        return (T) firstDsdAssessment.getLabioscrotalFusion();
                    }
                    if ("firstDsdAssessment.rightGonad".equals(propertyName)) {
                        return (T) firstDsdAssessment.getRightGonad();
                    }
                    if ("firstDsdAssessment.leftGonad".equals(propertyName)) {
                        return (T) firstDsdAssessment.getLeftGonad();
                    }
                    if ("firstDsdAssessment.mullerianStructure".equals(propertyName)) {
                        return (T) firstDsdAssessment.getMullerianStructure();
                    }
                    if ("firstDsdAssessment.wolffianStructure".equals(propertyName)) {
                        return (T) firstDsdAssessment.getWolffianStructure();
                    }
                    if ("firstDsdAssessment.ems".equals(propertyName)) {
                        return (T) firstDsdAssessment.getEms();
                    }
                    if ("firstDsdAssessment.modifiedPrader".equals(propertyName)) {
                        return (T) firstDsdAssessment.getModifiedPrader();
                    }
                    if ("firstDsdAssessment.tannerStage".equals(propertyName)) {
                        return (T) firstDsdAssessment.getTannerStage();
                    }
                    if ("firstDsdAssessment.gonadectomy".equals(propertyName)) {
                        return (T) firstDsdAssessment.getGonadectomy();
                    }
                    if ("firstDsdAssessment.freeText".equals(propertyName)) {
                        return (T) firstDsdAssessment.getFreeText();
                    }
                }
                if (null != latestDsdAssessment) {
                    //latest assessment
                    if (propertyName.equals("latestDsdAssessment.assessmentDate")) {
                        return (T) dateFormat.format(latestDsdAssessment.getAssessmentDate());
                    }
                    if (propertyName.equals("latestDsdAssessment.phallusLength")) {
                        return (T) latestDsdAssessment.getPhallusLength();
                    }
                    if (propertyName.equals("latestDsdAssessment.phallusSize")) {
                        return (T) latestDsdAssessment.getPhallusSize();
                    }
                    if (propertyName.equals("latestDsdAssessment.urinaryMeatus")) {
                        return (T) latestDsdAssessment.getUrinaryMeatus();
                    }
                    if (propertyName.equals("latestDsdAssessment.labioscrotalFusion")) {
                        return (T) latestDsdAssessment.getLabioscrotalFusion();
                    }
                    if (propertyName.equals("latestDsdAssessment.rightGonad")) {
                        return (T) latestDsdAssessment.getRightGonad();
                    }
                    if (propertyName.equals("latestDsdAssessment.leftGonad")) {
                        return (T) latestDsdAssessment.getLeftGonad();
                    }
                    if (propertyName.equals("latestDsdAssessment.mullerianStructure")) {
                        return (T) latestDsdAssessment.getMullerianStructure();
                    }
                    if (propertyName.equals("latestDsdAssessment.wolffianStructure")) {
                        return (T) latestDsdAssessment.getWolffianStructure();
                    }
                    if (propertyName.equals("latestDsdAssessment.ems")) {
                        return (T) latestDsdAssessment.getEms();
                    }
                    if (propertyName.equals("latestDsdAssessment.modifiedPrader")) {
                        return (T) latestDsdAssessment.getModifiedPrader();
                    }
                    if (propertyName.equals("latestDsdAssessment.tannerStage")) {
                        return (T) latestDsdAssessment.getTannerStage();
                    }
                    if (propertyName.equals("latestDsdAssessment.gonadectomy")) {
                        return (T) latestDsdAssessment.getGonadectomy();
                    }
                    if (propertyName.equals("latestDsdAssessment.freeText")) {
                        return (T) latestDsdAssessment.getFreeText();
                    }
                }
            }

            //end of assessment
            if (propertyName.equals("dsdIdentifier.clinicalInfo")) {
                return (T) dsdIdentifier.getClinicalInfo();
            }
            if (propertyName.equals("dsdIdentifier.caseNotes")) {
                return (T) dsdIdentifier.getCaseNotes();
            }
            if (propertyName.equals("dsdIdentifier.growthData")) {
                return (T) dsdIdentifier.getGrowthData();
            }
            if (propertyName.equals("dsdIdentifier.pubertyData")) {
                return (T) dsdIdentifier.getPubertyData();
            }
            if (propertyName.equals("dsdIdentifier.dna")) {
                return (T) dsdIdentifier.getDna();
            }
            if (propertyName.equals("dsdIdentifier.tissue")) {
                return (T) dsdIdentifier.getTissue();
            }
            if (propertyName.equals("dsdIdentifier.cellLine")) {
                return (T) dsdIdentifier.getCellLine();
            }
            if (propertyName.equals("dsdIdentifier.cellLineInfo")) {
                return (T) dsdIdentifier.getCellLineInfo();
            }
            if (propertyName.equals("dsdIdentifier.urine")) {
                return (T) dsdIdentifier.getUrine();
            }
            if (propertyName.equals("dsdIdentifier.serum")) {
                return (T) dsdIdentifier.getSerum();
            }
            if (propertyName.equals("dsdIdentifier.freeText")) {
                return (T) dsdIdentifier.getFreeText();
            }

            //dsdGeneAnalysis
            if (null != dsdIdentifier.getDsdGeneAnalysis()) {
                if (propertyName.equals("dsdGeneAnalysis.singleGeneAnalysis")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getSingleGeneAnalysis());
                }
                if (propertyName.equals("dsdGeneAnalysis.chromosomalRearrangement")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getChromosomalRearrangement());
                }
                if (propertyName.equals("dsdGeneAnalysis.chromosomalRearrangementDetail")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getChromosomalRearrangementDetail());
                }
                if (propertyName.equals("dsdGeneAnalysis.cgh")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getCgh());
                }
                if (propertyName.equals("dsdGeneAnalysis.cghDetail")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getCghDetail());
                }
                if (propertyName.equals("dsdGeneAnalysis.functionalStudy")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getFunctionalStudy());
                }
                if (propertyName.equals("dsdGeneAnalysis.functionalStudyDetail")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getFunctionalStudyDetail());
                }
                if (propertyName.equals("dsdGeneAnalysis.publishedCase")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getPublishedCase());
                }
                if (propertyName.equals("dsdGeneAnalysis.publishedDetail")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getPublishDetail());
                }
                if (propertyName.equals("dsdGeneAnalysis.furtherStudies")) {
                    return (T) (dsdIdentifier.getDsdGeneAnalysis().getFurtherStudies());
                }
            }

            if (propertyName.equals("dsdIdentifier.sampleShared")) {
                return (T) dsdIdentifier.getSampleShared();
            }
            if (propertyName.equals("dsdIdentifier.coreFreeText")) {
                return (T) dsdIdentifier.getCoreFreeText();
            }
            if (propertyName.equals("dsdIdentifier.uploadTime")) {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(dsdIdentifier.getUploadTime());
                return (T) date;
            }
            if (propertyName.equals("dsdIdentifier.uploader_id")) {
                return (T) dsdIdentifier.getUploader().getName();
            }
            if (null != dsdIdentifier.getDsdCah()) {
                if ("dsdCah.prenatalDiagnosis".equals(propertyName)) {
                    return (T) dsdIdentifier.getDsdCah().getPrenatalDiagnosis();
                }
                if ("dsdCah.gestationalAge".equals(propertyName)) {
                    return (T) dsdIdentifier.getDsdCah().getGestationalAge();
                }
                if ("dsdIdentifier.aofp".equals(propertyName)) {
                    return (T) dsdIdentifier.getAofp();
                }
                if ("dsdCah.dsdIdentifier.birthWeight".equals(propertyName)) {
                    return (T) dsdIdentifier.getBirthWeight();
                }
                if ("dsdCah.dsdIdentifier.birthLength".equals(propertyName)) {
                    return (T) dsdIdentifier.getBirthLength();
                }
                if ("dsdCah.praderStageAtFirstPresentation".equals(propertyName)) {
                    return (T) dsdIdentifier.getDsdCah().getPraderStageAtFirstPresentation();
                }
                if ("dsdCah.swCrisisAtPresentation".equals(propertyName)) {
                    return (T) dsdIdentifier.getDsdCah().getSwCrisisAtPresentation();
                }
                if ("dsdCah.adrenalCrisesAfterFirstPresentation".equals(propertyName)) {
                    return (T) dsdIdentifier.getDsdCah().getAdrenalCrisesAfterFirstPresentation();
                }
                if ("dsdCah.currentSaltReplacement".equals(propertyName)) {
                    return (T) dsdIdentifier.getDsdCah().getCurrentSaltReplacement();
                }
                if ("dsdCah.cahFreeText".equals(propertyName)) {
                    return (T) dsdIdentifier.getDsdCah().getCahFreeText();
                }
            }
        } else {
            log.error("No property name is given. Or property name is null!");
        }
        return null;
    }

    /**
     * check if given DsdAssessment instance's date exist in the DsdAssessment Set
     *
     * @param existingDsdAssessments
     * @param newDsdAssessment
     * @return
     */
    public static boolean isDateExistInDsdAssessmentSet(Set<DsdAssessment> existingDsdAssessments, DsdAssessment newDsdAssessment) {
        if (null == existingDsdAssessments || existingDsdAssessments.isEmpty()) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Set<Integer> existingDates = new HashSet<Integer>();
        for (DsdAssessment da : existingDsdAssessments) {
            Integer assessmentDate = new Integer(dateFormat.format(da.getAssessmentDate()));
            if (assessmentDate > 0) {
                existingDates.add(assessmentDate);
            }
        }
        Integer newAssessmentDate = new Integer(dateFormat.format(newDsdAssessment.getAssessmentDate()));
        return (null == newDsdAssessment.getAssessmentId() && existingDates.contains(newAssessmentDate));
    }

    /**
     * check whether DsdAssessment Set contains objects with same Date.
     *
     * @param dsdAssessments
     * @return
     */
    public static boolean isDuplicateDateExistInDsdAssessmentSet(Set<DsdAssessment> dsdAssessments) {
        Set<Date> assessmentDates = new TreeSet<Date>();
        if (dsdAssessments != null) {
            for (DsdAssessment assessment : dsdAssessments) {
                log.info("looping through all assessments = " + assessment);
                if (assessmentDates.contains(assessment.getAssessmentDate())) {
                    return true;
                } else {
                    assessmentDates.add(assessment.getAssessmentDate());
                }
            }
        }
        return false;
    }

    private static Feedback cloneFeedback(Feedback _feedback) {
        Feedback feedback = new Feedback(_feedback.getUserId(), _feedback.getTime(), _feedback.getFdbkTxt(), _feedback.getParent());
        feedback.setFdbkId(_feedback.getFdbkId());
        return feedback;
    }

    public static List<Feedback> cloneFeedbackList(List<Feedback> _feedbackList) {
        List<Feedback> feedbackList = new ArrayList<Feedback>();
        for (Feedback fb : _feedbackList) {
            feedbackList.add(cloneFeedback(fb));
        }
        return feedbackList;
    }

    public static PortalUser clonePortalUser(PortalUser portalUser) {
        if (portalUser == null) {
            return null;
        }
        PortalUser u = new PortalUser();
        if (portalUser.getUsername() != null && !portalUser.getUsername().isEmpty()) {
            u.setUsername(portalUser.getUsername());
        }
        if (portalUser.getName() != null && !portalUser.getName().isEmpty()) {
            u.setName(portalUser.getName());
        }
        if (portalUser.getEmail() != null && !portalUser.getEmail().isEmpty()) {
            u.setEmail(portalUser.getEmail());
        }
        if (portalUser.getCountry() != null && !portalUser.getCountry().isEmpty()) {
            u.setCountry(portalUser.getCountry());
        }
        if (portalUser.getCentre() != null && !portalUser.getCentre().isEmpty()) {
            u.setCentre(portalUser.getCentre());
        }
        if (portalUser.getTel() != null && !portalUser.getTel().isEmpty()) {
            u.setTel(portalUser.getTel());
        }
        if (portalUser.getFax() != null && !portalUser.getFax().isEmpty()) {
            u.setFax(portalUser.getFax());
        }
        if (portalUser.getAddress() != null && !portalUser.getAddress().isEmpty()) {
            u.setAddress(portalUser.getAddress());
        }
        if (portalUser.getSociety() != null && !portalUser.getSociety().isEmpty()) {
            u.setSociety(portalUser.getSociety());
        }
        if (portalUser.getPosition() != null && !portalUser.getPosition().isEmpty()) {
            u.setPosition(portalUser.getPosition());
        }
        if (portalUser.getPrimaryRole() != null && !portalUser.getPrimaryRole().isEmpty()) {
            u.setPrimaryRole(portalUser.getPrimaryRole());
        }
        if (portalUser.getSecondaryRoles() != null && !portalUser.getSecondaryRoles().isEmpty()) {
            u.setSecondaryRoles(portalUser.getSecondaryRoles());
        }
        if (portalUser.getSecondaryRolesOther() != null && !portalUser.getSecondaryRolesOther().isEmpty()) {
            u.setSecondaryRolesOther(portalUser.getSecondaryRolesOther());
        }
        if (portalUser.getInterest() != null && !portalUser.getInterest().isEmpty()) {
            u.setInterest(portalUser.getInterest());
        }
        if (portalUser.getInterestOther() != null && !portalUser.getInterestOther().isEmpty()) {
            u.setInterestOther(portalUser.getInterestOther());
        }
        if (portalUser.getSpecialInterest() != null && !portalUser.getSpecialInterest().isEmpty()) {
            u.setSpecialInterest(portalUser.getSpecialInterest());
        }
        if (portalUser.getRegisterTime() != null) {
            u.setRegisterTime(portalUser.getRegisterTime());
        }
        if (portalUser.getLastLogin() != null) {
            u.setLastLogin(portalUser.getLastLogin());
        }
        if (portalUser.getLastPasswordChange() != null) {
            u.setLastPasswordChange(portalUser.getLastPasswordChange());
        }
        return u;
    }

    /**
     * trim the value
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T trimValue(T value) {
        if (value instanceof String) {
            String string = (String) value;
            if (StringUtils.isNotBlank(string)) {
                return (T) string.trim();
            } else {
                return null;
            }
        } else {
            return value;
        }
    }

    public static List<String> splitStringToList(String str) {
        List<String> stringList = null;
        try {
            if (null != str && !str.isEmpty()) {
                String[] checklistStrings = str.trim().split(", ");
                stringList = Arrays.asList(checklistStrings);
            }
            return stringList;
        } catch (Exception e) {
            log.error("Error when splitting string to list. The String is: " + str, e);
        }
        return null;
    }

    public static <T> List<List<T>> splitCollection(Collection<T> values) {
        List<List<T>> lists = new ArrayList<>();
        List<T> valueList = new ArrayList<>(values);
        int listSize = values.size();
        for (int i = 0; i < listSize; i += PARAMETER_LIMIT) {
            List subList;
            if (listSize > i + PARAMETER_LIMIT) {
                subList = valueList.subList(i, (i + PARAMETER_LIMIT));
            } else {
                subList = valueList.subList(i, listSize);
            }
            lists.add(subList);
        }
        return lists;
    }

    public static String getLastName(String name) {
        String lastName = "";
        try {
            if (name != null && !name.isEmpty()) {
                String[] names = name.split(" ");
                if (names.length != 0) {
                    lastName = names[names.length - 1];
                }
            }

        } catch (Exception e) {
            log.error("Error while isolating last name from full name", e);
        }
        return lastName;
    }

    public static String getFeedbackEmailRecipients() {
        return Utility.isDebug() ? Utility.getProperties("email.properties").get("DEBUG_FEEDBACK_RECIPIENTS").toString().trim() : Utility.getProperties("email.properties").get("FEEDBACK_RECIPIENTS").toString().trim();
    }

    public static String constructRegistrationEmailToAdmin(String fullName, String organization, String email, String telephone,
                                                           String privilege, String note, String mode) {
        String target = "I-DSD";
        if (null != mode && "icah".equalsIgnoreCase(mode)) {
            target = "I-CAH";
        }
        return "Dear " + target + " consortium, \n" +
                "\n\"" + fullName + "\"(" + email + "), from \"" + organization + "\", is requesting an account with role of \"" + privilege + "\". \n" +
                "\nUser details: \n" +
                "\nFull Name: " + fullName +
                "\nOrganization: " + organization +
                "\nEmail: " + email +
                "\nTelephone: " + telephone +
                "\nPrivilege Requesting: " + privilege +
                "\n\nThe Reason for register message is forwarded below: \n" +
                "\n" + note +
                "\n\nPlease login as ADMIN user to enable this user by assigning proper roles. " +
                "\n\n\nBest regards, " +
                "\nSystem Administrator\n" +
                (new Date()).toString();

    }

    public static String constructRegistrationEmailToUser(String username, String fullName, String mode) {
        String target = "I-DSD";
        if (null != mode && "icah".equalsIgnoreCase(mode)) {
            target = "I-CAH";
        }
        return "Dear " + fullName + ", \n\n" +
                "Your registration request for " + target + " registry has been submitted to the " + target + " project management team. " +
                "Your username (" + username + ") and password have been saved and you will be able to access the registry with these login details when the management team provides you with the appropriate access level. This process can take up to 5 working days. Please be patient during this process. Can we also stress that your login details should not be shared with anybody else. " +
                "\n\n" +
                "Thanks for your interest in the " + target + " registry. \n\n" +
                "Best regards, " +
                "\nSystem Administrator\n" +
                (new Date()).toString();
    }

    public static String constructApprovalEmailToUser(String username, String fullName) {
        return "Dear " + fullName + ", \n\n" +
                "Your registration request for I-DSD registry has been approved by the I-DSD consortium. " +
                "You can now use your previously registered username (" + username + ") and password to access the registry. \n\n" +
                "Thanks for your interest with the I-DSD registry. \n\n" +
                "Best regards, " +
                "\nSystem Administrator\n" +
                (new Date()).toString();
    }

    public static String constructAddFeedbackEmailMsg(String username, String feedback) {
        return "Dear I-DSD consortium members, \n\n" +
                username + " just submitted a feedback to the registry. The content of this feedback is: \n\n" +
                feedback + "\n\nKind regards,\n\nI-DSD System Admin\n" + new Date();
    }

    public static String constructUpdateFeedbackEmailMsg(String username, Long feedbackId, String feedback) {
        return "Dear I-DSD consortium members, \n\n" +
                username + " just updated feedback #" + feedbackId.toString() +
                " in the registry. The new feedback content is: \n\n" + feedback +
                "\n\nKind regards,\n\nI-DSD System Admin\n" + new Date();
    }

    public static String constructAge16ReminderEmailMsg(String name, String registerIds) {
        String[] ids = registerIds.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ");
        sb.append(name);
        sb.append("\n\nThe I-DSD Registry SOP states that all existing participants who turn 16 years old should be supplied with an adult information sheet and a consent form. \n\nThis year one or more of the participant(s) registered by yourself or your team turned 16. ");
        //sb.append(ids.length);
        //sb.append(" participants registered by yourself or your team have turned 16. ");
        sb.append("\n\nThe registry ID of the participant(s) is as follows: \n");
        if (ids.length != 0) {
            for (String id : ids) {
                sb.append("\n").append(id);
            }
        }
        sb.append("\n\nThe latest information leaflet and consent form can be downloaded from the I-DSD website (http://www.gla.ac.uk/idsd). ");
        sb.append(".\n\nKind regards\n");
        sb.append("I-DSD Project Manager\n");
        sb.append(new Date());
        return sb.toString();
    }

    public static String constructPortalUserPasswordResetEmailMsg(String fullName, String password) {
        return "Dear " + fullName + ", \n\n" +
                "Your account in I-DSD registry has had its password reset to: " + password +
                ". You can login to the registry using your username and this NEW password for now. " +
                "We suggest you change this password as soon as possible. \n\n" +
                "Kind regards,\nI-DSD System Admin\nhttp://www.i-dsd.org\n" + new Date();
    }

    public static String constructUser6MonthsPasswordResetReminderEmailMsg(String fullName, String username) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear " + fullName + ",\n\nYour user account " + username +
                " with the I-DSD registry has not undergone a password change in the past 6 months. " +
                "To maintain security standards of the registry and to protect your credentials, " +
                "please reset your password in the I-DSD registry. You can do so by clicking on " +
                "or copying this URL into your browser: " +
                "https://www.i-dsd.org/jsp/user/password/change_password.jsp. " +
                "You will need to log in using your username and current password, " +
                "and then change to a new one. \n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String constructInactiveUserEmailMsg(String fullName, String username) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear " + fullName + ",\n\nYour user account " + username +
                " with I-DSD.org has not been used for 6 months. " +
                "Please log in to the registry to avoid your account being disabled. " +
                "You can do so by clicking on or copying this URL into your browser: " +
                "https://www.i-dsd.org/jsp/home.jsp. " +
                "You'll need to log in using your username and password. " +
                "Thanks.\n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String constructForcePasswordResetEmailMsg(String fullName, String username) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear " + fullName + ",\n\nYour user account " + username +
                " with the I-DSD registry has not undergone a password change in the past 8 months. " +
                "To maintain security standards of the registry and to protect your credentials, " +
                "your password has now automatically been reset. " +
                "You should receive another email telling you the new password for your account. " +
                "If you cannot receive the email with your new password, or for any other request, " +
                "please contact Dr Jillian Bryce at Jillian.Bryce@glasgow.ac.uk\n" +
                "\n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String constructDeactivationInactiveUsersEmailMsg(String fullName, String username) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear " + fullName + ",\n\nYour user account " + username +
                " with I-DSD.org has not been used for 8 months. " +
                "Your account has now been deactivated. " +
                "If you want to access I-DSD registry again, please email Dr Jillian Bryce at Jillian.Bryce@glasgow.ac.uk. " +
                "\n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String constructActivationEmailMsg(String fullName, String username) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear " + fullName + ",\n\nYour user account " + username +
                " with I-DSD.org has just been activated. " +
                "If you have any question regarding this, please email Dr Jillian Bryce at Jillian.Bryce@glasgow.ac.uk. " +
                "\n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String constructDeactivationEmailMsg(String fullName, String username) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear " + fullName + ",\n\nYour user account " + username +
                " with I-DSD.org has just been deactivated. " +
                "If you have any question regarding this, please email Dr Jillian Bryce at Jillian.Bryce@glasgow.ac.uk. " +
                "\n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String constructPatientAccessInvitationEmailMsg(String username) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear participant, \n\n" +
                "A user account has just been created for accessing the I-DSD Registry (i-dsd.org) with username \n\n"
                + username +
                "\n\nPlease activate your user account through this link: " +
                "\nhttp://www.i-dsd.org/patient/verify.action?u=" + username +
                "\n\nOnce you have activated your account, you will be able to see your record. " +
                "\nAlternatively, you can access the registry through this link:" +
                "\nhttp://www.i-dsd.org/patient/index.action" +
                "\n\nIf you have any question regarding this process, please contact the Registry Manager, Dr Jillian Bryce at Jillian.Bryce@glasgow.ac.uk. " +
                "\n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String getEmailRecipient(String email) {
        return Utility.isDebug() ? "jipu.jiang@glasgow.ac.uk" : email;
    }

    public static String getFileStorageLocation() {
        Properties prop = Utility.getProperties("idsd.properties");
        if (OsUtils.isWindows()) {
            return prop.getProperty("file_storage_windows");
        } else if (OsUtils.isMac()) {
            return prop.getProperty("file_storage_mac");
        } else if (OsUtils.isUnix()) {
            return prop.getProperty("file_storage_linux");
        } else {
            return "";
        }
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(constructSearchRequestConfirmationEmail("Joe Black", 12L));
    }

    public static String constructSearchRequestConfirmationEmail(String name, Long searchId) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear " + name + ", \n\n" +
                "This is to confirm that your search request to i-dsd.org has been received with ID: " + searchId + ". \n\n" +
                "The i-dsd.org team will contact you soon regarding your query. " +
                "\n\nIf you have any question regarding this process, please contact the Registry Manager, Dr Jillian Bryce at Jillian.Bryce@glasgow.ac.uk. " +
                "\n\nKind regards,\nI-DSD System Administrator\n" + date;
    }

    public static String constructSearchRequestToResponderEmail(String username, String name, Long searchId) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy");
        String date = sdf.format(now.getTime());
        return "Dear I-DSD consortium members, \n\n" +
                "\"" + name + "\"(" + username + ") is requesting an advanced search with ID: " + searchId + ".\n\n" +
                "http://www.i-dsd.org/search/view_search_request.action?searchId=" + searchId + ".\n\n" +
                "Please review this search and respond to the requester\n\n" +
                "Best regards, \n" +
                "System Administrator\n" + date;
    }
}
