package uk.ac.nesc.idsd.action.search;

import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdCah;
import uk.ac.nesc.idsd.model.DsdGeneAnalysis;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.GeneCategory;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.GeneService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchMyCentreDsdRecordsAction extends AbstractAction {
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

    private DsdIdentifier dsdIdentifier;
    // firstDsdAssessment and latestDsdAssessment property names must not change
    // they are related to the ParameterManagerImpl.getSearchSections()
    private DsdAssessment firstDsdAssessment;
    private DsdAssessment latestDsdAssessment;
    private DsdGeneAnalysis dsdGeneAnalysis;
    private DsdCah dsdCah;
    private String geneSelection;
    private SearchResult searchResult;

    private List<Section> sections = new ArrayList<Section>(0);
    private Map<String, String> consentLevelMap = new HashMap<String, String>(0);
    private List<GeneCategory> geneCategories = new ArrayList<GeneCategory>(0);
    private String leadersJS;
    private PortalUser portalUser;
    private boolean isResearcherView = false;

    public void prepare() {

    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String searchMyCentreView() {
        log.debug("in searchView method");
        try {
            portalUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error while retrieving portalUser from database for current user: " + Utility.getLoginUserName(), e);
            this.addActionError(ErrorCodes.USER_LOOKUP_ERROR.getMessage());
            return ERROR;
        }
        try {
            sections = parameterService.getMyCentreSearchSections(portalUser);
        } catch (ServiceException ue) {
            addActionError("Error when building search interface");
            log.error("UserException occurred when constructing search interface", ue);
            return ERROR;
        }
        consentLevelMap = Utility.getConsentLevels();
        geneCategories = geneService.getCategories();
        leadersJS = userService.getCentreLeaderJsString();

        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String searchMyCentreRecords() {
        List<Parameter> selectedParameters = retrieveSelectedParameters();
        selectedParameters = addRegisterIdAsSelected(selectedParameters);
        try {
            portalUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error while retrieving portalUser from database for current user: " + Utility.getLoginUserName(), e);
            this.addActionError(ErrorCodes.USER_LOOKUP_ERROR.getMessage());
            return ERROR;
        }
        try {
            dsdIdentifier.setDsdGeneAnalysis(dsdGeneAnalysis);
            dsdIdentifier.setDsdCah(dsdCah);
            this.dsdIdentifier.setCentreName(portalUser.getCentre());
            this.dsdIdentifier.setCountryName(portalUser.getCountry());
            this.searchResult = this.dsdIdentifierService.getByExample(selectedParameters, dsdIdentifier, firstDsdAssessment, latestDsdAssessment, geneSelection);
        } catch (Exception e) {
            addActionError("Error when searching for DSD records.");
            log.error("Error when searching for DSD records with selectedParameter = " + selectedParameters + ", dsdIdentifier = " + this.dsdIdentifier + ", firstDsdAssessment = " + firstDsdAssessment +
                    ", latestDsdAssessment = " + latestDsdAssessment + ", geneSelection = " + geneSelection, e);
            return ERROR;
        }

        auditService.auditSearch(dsdIdentifier, searchResult);
        return SUCCESS;
    }

    private List<Parameter> addRegisterIdAsSelected(List<Parameter> selectedParameters) {
        List<Parameter> parameters = new ArrayList<>();
        boolean hasIdSelected = false;
        for (Parameter p : selectedParameters) {
            if (p.getParamId() == 1) {
                hasIdSelected = true;
            }
        }
        if (!hasIdSelected) {
            Parameter p = new Parameter(parameterService.getParameterById(1));
            parameters.add(p);
        }
        parameters.addAll(selectedParameters);
        return parameters;
    }

    private List<Parameter> retrieveSelectedParameters() {
        Map contextMap = ActionContext.getContext().getParameters();
        List<Parameter> selectedParameters = new ArrayList<Parameter>();
        for (String key : (Iterable<String>) contextMap.keySet()) {
            try {
                if (key.contains("parameter_checkbox_")) {
                    // cast check - if cast successful, means true, if
                    // exception, then false.
                    String[] value = (String[]) contextMap.get(key);
                    if (value[0].equalsIgnoreCase("true")) {
                        Integer id = new Integer(key.substring(key.lastIndexOf('_') + 1));
                        String assessmentCheckbox = key.substring(0, key.indexOf('_'));
                        if (assessmentCheckbox.equalsIgnoreCase("a0")) {
                            Parameter p = new Parameter(parameterService.getParameterById(id));
                            p.setLabel("First " + p.getLabel());
                            selectedParameters.add(p);
                        }
                        if (assessmentCheckbox.equalsIgnoreCase("a1")) {
                            Parameter p = new Parameter(parameterService.getParameterById(id));
                            p.setLabel("Latest " + p.getLabel());
                            selectedParameters.add(p);
                        }
                        if (!assessmentCheckbox.equalsIgnoreCase("a0") && !assessmentCheckbox.equalsIgnoreCase("a1")) {
                            Parameter p = parameterService.getParameterById(id);
                            selectedParameters.add(p);
                        }
                    }
                }
            } catch (ClassCastException e) {
                addActionError("Error in backend process when searching records. ");
                log.error("Error in backend process when searching records", e);
            } catch (Exception e) {
                addActionError("Error when processing selected search parameters.");
                log.error("Error when processing selected search parameters: " + contextMap, e);
            }
        }
        return selectedParameters;
    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String viewMyRecords() {
        this.searchResult = this.dsdIdentifierService.getMyRecords(Utility.getLoginUserName());
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String viewMyCentreRecords() {
        this.searchResult = this.dsdIdentifierService.getMyCentreRecords(Utility.getLoginUserName());
        return SUCCESS;
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

    public DsdIdentifier getDsdIdentifier() {
        return dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
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

    public String getGeneSelection() {
        return geneSelection;
    }

    public void setGeneSelection(String geneSelection) {
        this.geneSelection = geneSelection;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
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

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public boolean getIsResearcherView() {
        return isResearcherView;
    }

    public void setIsResearcherView(boolean isResearcherView) {
        this.isResearcherView = isResearcherView;
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
}
