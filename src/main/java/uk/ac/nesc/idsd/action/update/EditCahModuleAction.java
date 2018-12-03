package uk.ac.nesc.idsd.action.update;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.model.DsdCah;
import uk.ac.nesc.idsd.model.DsdCahVisit;
import uk.ac.nesc.idsd.model.DsdCahVisitEpisode;
import uk.ac.nesc.idsd.model.DsdCahVisitMedDetail;
import uk.ac.nesc.idsd.model.Option;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EditCahModuleAction extends AbstractEditAction {
    private static final long serialVersionUID = 3364034021765942123L;

    private DsdCah dsdCah;
    private List<Parameter> cahParameters = new ArrayList<Parameter>();

    private List<Section> cahVisitSections;
    private List<Option> unitOptions;
    private List<Option> oralSteroidsOptions;
    private List<Option> yesNoNKOptions;
    private List<Option> emergencyAttendanceOptions;
    private List<Option> glucocorticoidsMedicineOptions;
    private List<Option> predisposingConditionOptions;

    private String episodesString;
    private String medicineDetailString;
    private Long dsdCahVisitId;
    private DsdCahVisit dsdCahVisit;
    private DsdCahVisit lastDsdCahVisit;
    private List<DsdCahVisitEpisode> dsdCahVisitEpisodes;
    private List<DsdCahVisitMedDetail> dsdCahVisitMedDetails;
    private Long dsdCahId;

    private static final String VALUE_SEPARATOR = "\\|";

    @Override
    public void prepare() {
        prepareCah();
        prepareCahVisit();
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editCahView() {
        log.info("viewing birth weight = " + this.dsdCah.getDsdIdentifier().getDsdCah());
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editCah() {
        String returnStatus = checkConsentAndOwner();
        log.info("birth weight = " + this.dsdIdentifier.getBirthWeight());
        if (null != this.dsdIdentifier) {
            try {
                this.dsdIdentifier = this.dsdIdentifierService.saveCah(this.dsdIdentifier, dsdCah);
            } catch (ServiceException e) {
                this.addActionError("Error when editing CAH record for registerId: " + this.registerId);
                log.error("Error when editing CAH record for registerId: " + this.registerId, e);
                return INPUT;
            }
        }
        return afterSave(returnStatus);
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editCahVisitView() {
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editCahVisit() {
        String returnStatus = checkConsentAndOwner();
        if (null != this.dsdIdentifier) {
            try {
                //save CAH
                this.dsdIdentifierService.saveCah(dsdIdentifier, dsdCah);
                //parse the episode string
                this.dsdCahVisit.setDsdCahVisitEpisodes(parseEpisodeString(getEpisodesString()));
                // parse the medicine string
                this.dsdCahVisit.setDsdCahVisitMedDetails(parseMedicineDetailString(getMedicineDetailString()));

                this.dsdCahVisit = this.dsdIdentifierService.saveCahVisit(this.dsdIdentifier, dsdCahVisit);
            } catch (ServiceException e) {
                this.addActionError("Error when editing CAH record for registerId: " + this.registerId);
                log.error("Error when editing CAH record for registerId: " + this.registerId, e);
                return INPUT;
            }
        } else {
            log.warn("DsdIdentifier is null in session. ");
        }
        return afterSave(returnStatus);
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String clearCurrentCahVisit() {
        String returnStatus = checkConsentAndOwner();
        this.dsdCahVisitId = null;
        this.dsdCahVisit = null;
        return afterSave(returnStatus);
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editDsdCahVisitEdit() {
        String returnStatus = checkConsentAndOwner();
//        this.dsdCahVisit = this.dsdIdentifierService.getCahVisitById(this.dsdCahVisitId);
        return afterSave(returnStatus);
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editDsdCahVisitDelete() {
        String returnStatus = checkConsentAndOwner();
        if (null != this.dsdCahVisitId) {
            try {
                log.info("this.dsdCahVisitId: " + this.dsdCahVisitId);
                this.dsdIdentifierService.deleteCahVisit(this.dsdCahVisitId);
                this.dsdIdentifier = this.dsdIdentifierService.getById(this.dsdIdentifier.getRegisterId());
            } catch (ServiceException e) {
                this.addActionError("Error deleting CAH visit record for registerId: " + this.registerId + ", for DsdCahVisitId: " + this.dsdCahVisitId);
                log.error("Error deleting CAH visit record for registerId: " + this.registerId + ", for DsdCahVisitId: " + this.dsdCahVisitId, e);
                return INPUT;
            }
        }
        return afterSave(returnStatus);
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editDsdCahVisitSubmit() {
        String returnStatus = checkConsentAndOwner();
        if (null != this.dsdIdentifier) {
            try {
                //save CAH
                this.dsdIdentifierService.saveCah(dsdIdentifier, dsdCah);
                //parse the episode string
                this.dsdCahVisit.setDsdCahVisitEpisodes(parseEpisodeString(getEpisodesString()));
                // parse the medicine string
                this.dsdCahVisit.setDsdCahVisitMedDetails(parseMedicineDetailString(getMedicineDetailString()));
                this.dsdCahVisit = this.dsdIdentifierService.saveCahVisit(this.dsdIdentifier, dsdCahVisit);
            } catch (ServiceException e) {
                this.addActionError("Error when editing CAH record for registerId: " + this.registerId);
                log.error("Error when editing CAH record for registerId: " + this.registerId, e);
                return INPUT;
            }
        } else {
            log.warn("DsdIdentifier is null in session. ");
        }
        return afterSave(returnStatus);
    }

    protected Set<DsdCahVisitEpisode> parseEpisodeString(String episodesString) {
        // parse the episode string, i.e. [1,1],[2,2],[3,3],
        if (log.isInfoEnabled()) {
            log.info("Episode string = " + episodesString);
        }
        Set<DsdCahVisitEpisode> dsdCahVisitEpisodes = new LinkedHashSet<>();
        if (StringUtils.isNotEmpty(episodesString)) {
            log.info("Episode string length - " + episodesString.split(";").length);
            for (String line : episodesString.split(";")) {
                String values = extractValuesFromBrackets(line);
                String[] array = splitValuesToArray(values);
                if (array.length == 6) {
                    dsdCahVisitEpisodes.add(
                            new DsdCahVisitEpisode(dsdCahVisit,
                                    parseValueToInteger(array[0]),
                                    array[1],
                                    array[2],
                                    array[3],
                                    array[4],
                                    array[5]));
                }
            }
        }
        log.info("Episodes = " + dsdCahVisitEpisodes);
        return dsdCahVisitEpisodes;
    }

    protected Set<DsdCahVisitMedDetail> parseMedicineDetailString(String medicineDetailString) {
        // parse the episode string, i.e. [Hydrocortisone,1,mg,01:00,note1];[Prednisolone,2,mcg,02:00,note2];[Prednisone,3,grams,03:00,note3];;,
        log.info("medicineDetail String = " + medicineDetailString);
        Set<DsdCahVisitMedDetail> dsdCahVisitMedDetailSet = new LinkedHashSet<>();
        if (StringUtils.isNotEmpty(medicineDetailString)) {
            log.debug("medicineDetail String length - " + medicineDetailString.split(";").length);
            for (String line : medicineDetailString.split(";")) {
                String values = extractValuesFromBrackets(line);
                String[] array = splitValuesToArray(values);
                log.info("split value array length: " + array.length);
                if (array != null && array.length != 0) {
                    DsdCahVisitMedDetail medDetail = new DsdCahVisitMedDetail(dsdCahVisit,
                            array[0],
                            parseValueToDouble(array[1]),
                            array[2],
                            parseValueToTime(array[3]),
                            decodeEscapedJavaScriptString(array[4]));
                    dsdCahVisitMedDetailSet.add(medDetail);
                }
            }
        } else {
            log.error("medicineDetail String from JSP is null or empty");
        }
        log.info("Parsed Meds detail set = " + dsdCahVisitMedDetailSet);
        return dsdCahVisitMedDetailSet;
    }

    protected String decodeEscapedJavaScriptString(String escapedString) {
        String unescapedString = "";
        try {
            unescapedString = URLDecoder.decode(escapedString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException while decoding escaped JavaScript string: " + escapedString, e);
        }
        return unescapedString;
    }

    protected Time parseValueToTime(String value) {
        Time time = Time.valueOf("00:00:00");
        if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(value.trim())) {
            try {
                String pattern = "([0]\\d|[1]\\d|[2][0-3]):[0-5]\\d";
                if (value.matches(pattern)) {
                    value += ":00";
                }
                time = Time.valueOf(value);
            } catch (Exception e) {
                log.error("Error while parsing into Time from String: " + value, e);
                throw new ServiceException("Cannot parse input String into Time: " + value);
            }

        } else {
            log.error("Input value String is null or empty: " + value);
        }
        return time;
    }

    protected Integer parseValueToInteger(String value) {
        Integer integer = 0;
        if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(value.trim())) {
            try {
                integer = Integer.parseInt(value.trim());
            } catch (Exception e) {
                log.info("Error while parsing into Integer from String: " + value, e);
                throw new ServiceException("Cannot parse to Integer from String: " + value);
            }
        } else {
            log.error("Input value is null or empty: " + value);
        }
        log.warn("Cannot parse value to Integer, using default value 0 instead for input String: " + value);
        return integer;
    }

    protected Double parseValueToDouble(String value) {
        Double d = 0.0;
        if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(value.trim())) {
            try {
                d = Double.parseDouble(value.trim());
            } catch (Exception e) {
                log.error("Error while parsing into Double from String: " + value, e);
                throw new ServiceException("Cannot parse to Double from String: " + value);
            }
        } else {
            log.error("Input value is null or empty: " + value);
        }
        log.warn("Cannot parse value to Double, using default value 0.0 instead for input String: " + value);
        return d;
    }

    protected String extractValuesFromBrackets(String valueInBracket) {
        String neatValues = "";
        if (StringUtils.isNotEmpty(valueInBracket)) {
            try {
                neatValues = valueInBracket.replace("[||||]", "").replace("[", "").replace("]", "");
            } catch (Exception e) {
                log.error("Error while taking out [, ], [||||]; from input String: " + valueInBracket, e);
                throw new ServiceException("Cannot extract values from String: " + valueInBracket);
            }
        } else {
            log.error("Input valueInBracket is null or empty: " + valueInBracket);
        }
        return neatValues;
    }

    protected String[] splitValuesToArray(String values) {
        List<String> valueList = new ArrayList<>();
        if (StringUtils.isNotEmpty(values)) {
            try {
                Iterable<String> iterator = Splitter.on("|").trimResults().split(values);
                for (String s : iterator) {
                    valueList.add(s);
                }
            } catch (Exception e) {
                log.error("Error while splitting into String[] from String: " + values, e);
                throw new ServiceException("Cannot split values to array for String: " + values);
            }
        } else {
            log.error("Input values String is null or empty: " + values);
        }
        return valueList.toArray(new String[0]);
    }

    public List<Section> getCahVisitSections() {
        return cahVisitSections;
    }

    public void setCahVisitSections(List<Section> cahVisitSections) {
        this.cahVisitSections = cahVisitSections;
    }

    public List<Option> getUnitOptions() {
        return unitOptions;
    }

    public void setUnitOptions(List<Option> unitOptions) {
        this.unitOptions = unitOptions;
    }

    public String getEpisodesString() {
        return episodesString;
    }

    public void setEpisodesString(String episodesString) {
        this.episodesString = episodesString;
    }

    public String getMedicineDetailString() {
        return medicineDetailString;
    }

    public void setMedicineDetailString(String medicineDetailString) {
        this.medicineDetailString = medicineDetailString;
    }

    public Long getDsdCahVisitId() {
        return dsdCahVisitId;
    }

    public void setDsdCahVisitId(Long dsdCahVisitId) {
        this.dsdCahVisitId = dsdCahVisitId;
    }

    public DsdCahVisit getDsdCahVisit() {
        return dsdCahVisit;
    }

    public void setDsdCahVisit(DsdCahVisit dsdCahVisit) {
        this.dsdCahVisit = dsdCahVisit;
    }

    public DsdCah getDsdCah() {
        return dsdCah;
    }

    public void setDsdCah(DsdCah dsdCah) {
        this.dsdCah = dsdCah;
    }

    public List<Parameter> getCahParameters() {
        return cahParameters;
    }

    public void setCahParameters(List<Parameter> cahParameters) {
        this.cahParameters = cahParameters;
    }

    public List<Option> getOralSteroidsOptions() {
        return oralSteroidsOptions;
    }

    public void setOralSteroidsOptions(List<Option> oralSteroidsOptions) {
        this.oralSteroidsOptions = oralSteroidsOptions;
    }

    public List<Option> getYesNoNKOptions() {
        return yesNoNKOptions;
    }

    public void setYesNoNKOptions(List<Option> yesNoNKOptions) {
        this.yesNoNKOptions = yesNoNKOptions;
    }

    public List<Option> getEmergencyAttendanceOptions() {
        return emergencyAttendanceOptions;
    }

    public void setEmergencyAttendanceOptions(List<Option> emergencyAttendanceOptions) {
        this.emergencyAttendanceOptions = emergencyAttendanceOptions;
    }

    public List<Option> getGlucocorticoidsMedicineOptions() {
        return glucocorticoidsMedicineOptions;
    }

    public void setGlucocorticoidsMedicineOptions(List<Option> glucocorticoidsMedicineOptions) {
        this.glucocorticoidsMedicineOptions = glucocorticoidsMedicineOptions;
    }

    public List<DsdCahVisitEpisode> getDsdCahVisitEpisodes() {
        return dsdCahVisitEpisodes;
    }

    public void setDsdCahVisitEpisodes(List<DsdCahVisitEpisode> dsdCahVisitEpisodes) {
        this.dsdCahVisitEpisodes = dsdCahVisitEpisodes;
    }

    public List<DsdCahVisitMedDetail> getDsdCahVisitMedDetails() {
        return dsdCahVisitMedDetails;
    }

    public void setDsdCahVisitMedDetails(List<DsdCahVisitMedDetail> dsdCahVisitMedDetails) {
        this.dsdCahVisitMedDetails = dsdCahVisitMedDetails;
    }

    public DsdCahVisit getLastDsdCahVisit() {
        return lastDsdCahVisit;
    }

    public void setLastDsdCahVisit(DsdCahVisit lastDsdCahVisit) {
        this.lastDsdCahVisit = lastDsdCahVisit;
    }

    public Long getDsdCahId() {
        return dsdCahId;
    }

    public void setDsdCahId(Long dsdCahId) {
        this.dsdCahId = dsdCahId;
    }

    public List<Option> getPredisposingConditionOptions() {
        return predisposingConditionOptions;
    }

    public void setPredisposingConditionOptions(List<Option> predisposingConditionOptions) {
        this.predisposingConditionOptions = predisposingConditionOptions;
    }

    private void prepareCah() {
        this.cahParameters = this.parameterService.getDsdCahSection();
        initialiseDsdIdentifierInstance();
        log.info("DsdIdentifier in prepare CAH = " + this.dsdIdentifier);
        this.dsdCah = this.dsdIdentifier.getDsdCah();
        log.info("Dsd CAH from DsdIdentifier = " + dsdCah);
        if (this.dsdCah == null) {
            this.dsdCah = new DsdCah();
            this.dsdCah.setDsdIdentifier(this.dsdIdentifier);
        }
    }

    private void prepareCahVisit() {
        prepareForJspView();
        initialiseDsdIdentifierInstance();

        cahVisitSections = parameterService.getCahVisitSections(this.dsdIdentifier.getSexAssigned());

        log.info("Loading CAH module, dsdCahVisitId = " + dsdCahVisitId);
        if (dsdCahVisitId != null) {
            log.info("dsdCahVisitId is not null, fetching from DB by ID");
            this.dsdCahVisit = dsdIdentifierService.getCahVisitById(dsdCahVisitId);
            dsdCahVisitEpisodes = new ArrayList<DsdCahVisitEpisode>(dsdCahVisit.getDsdCahVisitEpisodes());
            dsdCahVisitMedDetails = new ArrayList<DsdCahVisitMedDetail>(dsdCahVisit.getDsdCahVisitMedDetails());
            log.info("Found dsdCahVisit = " + dsdCahVisit);
        } else {
            log.info("dsdCahVisitId is null, creating new transit instance");
            this.dsdCahVisit = dsdIdentifierService.createNewTransitCahVisitInstance(this.dsdIdentifier);
            log.info("Default dsdCahVisit created is: " + dsdCahVisit);
        }
    }

    private void prepareForJspView() {
        unitOptions = parameterService.getMedUnitOptions();
        oralSteroidsOptions = parameterService.getOralSteroidsOptions();
        yesNoNKOptions = parameterService.getYesNoNKOptions();
        emergencyAttendanceOptions = parameterService.getEmergencyAttendanceOptions();
        glucocorticoidsMedicineOptions = parameterService.getGlucocorticoidsMedicineOptions();
        predisposingConditionOptions = parameterService.getPredisposingConditionOptions();
    }

    public String retrieveLastDsdCahVisit() {
        this.lastDsdCahVisit = dsdIdentifierService.getLastDsdCahVisit(this.dsdCahId);
        return SUCCESS;
    }

}
