package uk.ac.nesc.idsd.action.search;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.bean.SearchResultCount;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdCah;
import uk.ac.nesc.idsd.model.DsdGeneAnalysis;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.DsdSearchCriteria;
import uk.ac.nesc.idsd.model.GeneCategory;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.DsdSearchCriteriaService;
import uk.ac.nesc.idsd.service.GeneService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchDsdRequestAction extends AbstractAction {
    private static final long serialVersionUID = 7321227198062618019L;

    @Autowired
    private ParameterService parameterService;
    @Autowired
    private GeneService geneService;
    @Autowired
    private UserService userService;
    @Autowired
    private DsdIdentifierService dsdIdentifierService;
    @Autowired
    private AuditService auditService;

    // firstDsdAssessment and latestDsdAssessment property names must not change
    // they are related to the ParameterManagerImpl.getSearchSections()
    private DsdAssessment firstDsdAssessment;
    private DsdAssessment latestDsdAssessment;
    private DsdGeneAnalysis dsdGeneAnalysis;
    private DsdCah dsdCah;

    private List<Section> sections = new ArrayList<Section>(0);
    private Map<String, String> consentLevelMap = new HashMap<String, String>(0);
    private List<GeneCategory> geneCategories = new ArrayList<GeneCategory>(0);
    private String leadersJS;
    private List<Country> countries = new ArrayList<Country>(0);
    private PortalUser portalUser;

    private Long searchId;
    private DsdSearchCriteria dsdSearchCriteria;
    private DsdIdentifier dsdIdentifier;
    private SearchResultCount searchResultCount;
    private SearchResult searchResult;
    private InputStream fileInputStream;
    private String contentType; // The content type of the file
    private String fileName; // The uploaded file name
    private String[] selectedParameterIds;
    private String requester;

    @Autowired
    private DsdSearchCriteriaService dsdSearchCriteriaService;
    @Autowired
    private EmailService emailService;

    public void prepare() {
        try {
            portalUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error while retrieving portalUser from database for current user: " + Utility.getLoginUserName(), e);
            this.addActionError(ErrorCodes.USER_LOOKUP_ERROR.getMessage());
        }
        consentLevelMap = Utility.getConsentLevels();
        geneCategories = geneService.getCategories();
        leadersJS = userService.getCentreLeaderJsString();
        countries = parameterService.getCountriesForSearch();
        try {
            sections = parameterService.getAllSearchSections(portalUser);
        } catch (ServiceException ue) {
            addActionError("Error when building search interface");
            log.error("UserException occurred when constructing search interface", ue);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String searchView() {
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String searchSubmit() {
        log.info("selected param = " + dsdSearchCriteria.getSelectedParameterIds());
        log.info("selected Gene = " + dsdSearchCriteria.getSelectedGeneNames());
        dsdSearchCriteria.setExampleDsdRecord(dsdIdentifier, firstDsdAssessment, latestDsdAssessment, dsdGeneAnalysis, dsdCah);
        dsdSearchCriteriaService.save(dsdSearchCriteria);

        try {
            dsdIdentifier.setDsdGeneAnalysis(dsdGeneAnalysis);
            dsdIdentifier.setDsdCah(dsdCah);
            SearchResult searchResult = this.dsdIdentifierService.searchDsdRecords(dsdSearchCriteria);
            searchResultCount = new SearchResultCount(searchResult.getActualSize(), searchResult.getConsentSize(), searchResult.getColumnSize());
        } catch (Exception e) {
            addActionError("Error when searching for DSD records.");
            log.error("Error when searching for DSD records with selectedParameter = " + dsdSearchCriteria.getSelectedParameterIds() + ", dsdIdentifier = " + this.dsdIdentifier + ", firstDsdAssessment = " + firstDsdAssessment +
                    ", latestDsdAssessment = " + latestDsdAssessment + ", geneSelection = " + dsdSearchCriteria.getSelectedGeneNames(), e);
            return ERROR;
        }

        //send the confirmation email to user
        emailService.sendSearchRequestConfirmationEmail(portalUser.getEmail(), Utility.constructSearchRequestConfirmationEmail(portalUser.getName(), dsdSearchCriteria.getSearchId()));

        emailService.sendSearchRequestToResponder(Utility.constructSearchRequestToResponderEmail(portalUser.getUsername(), portalUser.getName(), dsdSearchCriteria.getSearchId()));

        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String viewSearchRequest() {
        dsdSearchCriteria = dsdSearchCriteriaService.getById(searchId);
        dsdIdentifier = dsdSearchCriteria.getDsdIdentifier();
        dsdGeneAnalysis = dsdSearchCriteria.getDsdGeneAnalysis();
        firstDsdAssessment = dsdSearchCriteria.getFirstDsdAssessment();
        latestDsdAssessment = dsdSearchCriteria.getLastDsdAssessment();
        dsdCah = dsdSearchCriteria.getDsdCah();
        if (StringUtils.isNotBlank(dsdSearchCriteria.getSelectedParameterIds())) {
            selectedParameterIds = dsdSearchCriteria.getSelectedParameterIds().split(",");
        }
        if (StringUtils.isNotBlank(dsdSearchCriteria.getRequesterId())) {
            PortalUser requestUser = userService.getPortalUserByUsername(dsdSearchCriteria.getRequesterId());
            if (requestUser != null) {
                requester = requestUser.getName() + "(" + requestUser.getUsername() + ")";
            }
        }
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String viewSearchRequestResult() {
        dsdSearchCriteria = dsdSearchCriteriaService.getById(searchId);
        dsdIdentifier = dsdSearchCriteria.getDsdIdentifier();
        try {
            dsdIdentifier.setDsdGeneAnalysis(dsdSearchCriteria.getDsdGeneAnalysis());
            dsdIdentifier.setDsdCah(dsdSearchCriteria.getDsdCah());
            searchResult = this.dsdIdentifierService.searchDsdRecords(dsdSearchCriteria);
        } catch (Exception e) {
            addActionError("Error when searching for DSD records.");
            log.error("Error when searching for DSD records with selectedParameter = " + dsdSearchCriteria.getSelectedParameterIds() + ", dsdIdentifier = " + this.dsdIdentifier + ", firstDsdAssessment = " + firstDsdAssessment +
                    ", latestDsdAssessment = " + latestDsdAssessment + ", geneSelection = " + dsdSearchCriteria.getSelectedGeneNames(), e);
            return ERROR;
        }
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String saveSearchRequestResult() {
        dsdSearchCriteria = dsdSearchCriteriaService.getById(searchId);
        dsdIdentifier = dsdSearchCriteria.getDsdIdentifier();
        try {
            dsdIdentifier.setDsdGeneAnalysis(dsdSearchCriteria.getDsdGeneAnalysis());
            dsdIdentifier.setDsdCah(dsdSearchCriteria.getDsdCah());
            searchResult = this.dsdIdentifierService.searchDsdRecords(dsdSearchCriteria);
//            Set<Long> registerIds = new HashSet<Long>();
//            for(SearchResultRow row : searchResult.getResultRows()) {
//                registerIds.add(row.getRegisterId());
//            }
            String content = dsdIdentifierService.getCsvByDsdSearchResult(searchResult);
            fileInputStream = IOUtils.toInputStream(content, "UTF-8");
            fileName = makeFileName(searchId);
        } catch (Exception e) {
            addActionError("Error when searching for DSD records.");
            log.error("Error when searching for DSD records with selectedParameter = " + dsdSearchCriteria.getSelectedParameterIds() + ", dsdIdentifier = " + this.dsdIdentifier + ", firstDsdAssessment = " + firstDsdAssessment +
                    ", latestDsdAssessment = " + latestDsdAssessment + ", geneSelection = " + dsdSearchCriteria.getSelectedGeneNames(), e);
            return ERROR;
        }
        return SUCCESS;
    }

    private String makeFileName(Long searchId) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(now.getTime());
        return "search_request_" + searchId + "_results_" + date + ".csv";
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Map<String, String> getConsentLevelMap() {
        return consentLevelMap;
    }

    public void setConsentLevelMap(Map<String, String> consentLevelMap) {
        this.consentLevelMap = consentLevelMap;
    }

    public DsdAssessment getLatestDsdAssessment() {
        return latestDsdAssessment;
    }

    public void setLatestDsdAssessment(DsdAssessment latestDsdAssessment) {
        this.latestDsdAssessment = latestDsdAssessment;
    }

    public DsdAssessment getFirstDsdAssessment() {
        return firstDsdAssessment;
    }

    public void setFirstDsdAssessment(DsdAssessment firstDsdAssessment) {
        this.firstDsdAssessment = firstDsdAssessment;
    }

    public List<GeneCategory> getGeneCategories() {
        return geneCategories;
    }

    public void setGeneCategories(List<GeneCategory> geneCategories) {
        this.geneCategories = geneCategories;
    }

    public String getLeadersJS() {
        return leadersJS;
    }

    public void setLeadersJS(String leadersJS) {
        this.leadersJS = leadersJS;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public DsdCah getDsdCah() {
        return dsdCah;
    }

    public void setDsdCah(DsdCah dsdCah) {
        this.dsdCah = dsdCah;
    }

    public DsdGeneAnalysis getDsdGeneAnalysis() {
        return dsdGeneAnalysis;
    }

    public void setDsdGeneAnalysis(DsdGeneAnalysis dsdGeneAnalysis) {
        this.dsdGeneAnalysis = dsdGeneAnalysis;
    }

    public DsdSearchCriteria getDsdSearchCriteria() {
        return dsdSearchCriteria;
    }

    public void setDsdSearchCriteria(DsdSearchCriteria dsdSearchCriteria) {
        this.dsdSearchCriteria = dsdSearchCriteria;
    }

    public DsdIdentifier getDsdIdentifier() {
        return dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    public SearchResultCount getSearchResultCount() {
        return searchResultCount;
    }

    public void setSearchResultCount(SearchResultCount searchResultCount) {
        this.searchResultCount = searchResultCount;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getSelectedParameterIds() {
        return selectedParameterIds;
    }

    public void setSelectedParameterIds(String[] selectedParameterIds) {
        this.selectedParameterIds = selectedParameterIds;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }
}
