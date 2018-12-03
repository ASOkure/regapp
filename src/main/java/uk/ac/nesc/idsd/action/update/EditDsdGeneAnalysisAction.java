package uk.ac.nesc.idsd.action.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.model.DsdGeneAnalysis;
import uk.ac.nesc.idsd.model.DsdGeneScreened;
import uk.ac.nesc.idsd.model.GeneCategory;
import uk.ac.nesc.idsd.model.Option;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.service.GeneService;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class EditDsdGeneAnalysisAction extends AbstractEditAction {
    private static final long serialVersionUID = -8264767917077930391L;

    @Autowired
    private GeneService geneService;

    //private List<Parameter> parameters = new ArrayList<Parameter>();
    private DsdGeneAnalysis dsdGeneAnalysis;
    private List<DsdGeneScreened> dsdGeneScreeneds = null;
    private List<Option> yesNoNKOptions = new ArrayList<Option>();
    private List<Option> yesNoOptions = new ArrayList<Option>();
    private List<GeneCategory> geneCategories = new ArrayList<GeneCategory>();

    private Parameter geneticAnalysis = new Parameter();
    private Parameter singleGeneAnalysis = new Parameter();
    private Parameter chromosomalRearrangement = new Parameter();
    private Parameter chromosomalRearrangementDetail = new Parameter();
    private Parameter cgh = new Parameter();
    private Parameter cghDetail = new Parameter();
    private Parameter functionalStudy = new Parameter();
    private Parameter functionalStudyDetail = new Parameter();
    private Parameter publishedCase = new Parameter();
    private Parameter publishDetail = new Parameter();
    private Parameter furtherStudies = new Parameter();

    @Override
    public void prepare() {
        this.yesNoNKOptions = this.parameterService.getYesNoNKOptions();
        this.yesNoOptions = this.parameterService.getYesNoOptions();
        //this.options = this.parameterService.getOptionsByMenuId(8);
        this.geneCategories = this.geneService.getCategories();
        initialiseDsdIdentifierInstance();
        prepareGeneAnalysisParameters();
        this.dsdGeneAnalysis = this.dsdIdentifier.getDsdGeneAnalysis();
        if (null == this.dsdGeneScreeneds && null != this.dsdGeneAnalysis && null != this.dsdGeneAnalysis.getDsdGeneScreeneds() && !this.dsdGeneAnalysis.getDsdGeneScreeneds().isEmpty()) {
            this.dsdGeneScreeneds = new ArrayList<DsdGeneScreened>(this.dsdGeneAnalysis.getDsdGeneScreeneds());
        }
        if (null == this.dsdGeneScreeneds) {
            this.dsdGeneScreeneds = new ArrayList<DsdGeneScreened>();
        }
    }

    private void prepareGeneAnalysisParameters() {
        geneticAnalysis = this.parameterService.getParameterById(18);
        singleGeneAnalysis = this.parameterService.getParameterById(47);
        chromosomalRearrangement = this.parameterService.getParameterById(48);
        chromosomalRearrangementDetail = this.parameterService.getParameterById(49);
        cgh = this.parameterService.getParameterById(50);
        cghDetail = this.parameterService.getParameterById(51);
        functionalStudy = this.parameterService.getParameterById(52);
        functionalStudyDetail = this.parameterService.getParameterById(53);
        publishedCase = this.parameterService.getParameterById(54);
        publishDetail = this.parameterService.getParameterById(55);
        furtherStudies = this.parameterService.getParameterById(56);
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editGeneAnalysisView() {
        return checkConsentAndOwner();
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editGeneAnalysisSubmit() {
        log.debug("gene screen size = " + this.dsdGeneScreeneds.size());
        log.debug("this.dsdIdentifier = " + this.dsdIdentifier);
        log.debug("cgh = " + this.dsdGeneAnalysis.getCgh());
        String returnStatus = checkConsentAndOwner();

        if (null != this.registerId) {
            try {
                this.dsdIdentifier = this.dsdIdentifierService.saveGeneAnalysis(this.registerId, this.dsdGeneAnalysis, this.dsdGeneScreeneds);
            } catch (ServiceException e) {
                this.addActionError("Error when saving Gene Analysis for registerId: " + this.registerId);
                log.error("Error when saving Gene Analysis for registerId: " + this.registerId, e);
                return INPUT;
            }
        } else {
            this.addActionError(ERROR);
            log.error("this.dsdIdentifier is null? " + (this.dsdIdentifier == null));
        }

        return afterSave(returnStatus);
    }

    public List<DsdGeneScreened> getDsdGeneScreeneds() {
        return dsdGeneScreeneds;
    }

    public void setDsdGeneScreeneds(List<DsdGeneScreened> dsdGeneScreeneds) {
        this.dsdGeneScreeneds = dsdGeneScreeneds;
    }

    public List<Option> getYesNoNKOptions() {
        return yesNoNKOptions;
    }

    public void setYesNoNKOptions(List<Option> yesNoNKOptions) {
        this.yesNoNKOptions = yesNoNKOptions;
    }

    public List<GeneCategory> getGeneCategories() {
        return geneCategories;
    }

    public void setGeneCategories(List<GeneCategory> geneCategories) {
        this.geneCategories = geneCategories;
    }

    public void setGeneService(GeneService geneService) {
        this.geneService = geneService;
    }

    public DsdGeneAnalysis getDsdGeneAnalysis() {
        return dsdGeneAnalysis;
    }

    public void setDsdGeneAnalysis(DsdGeneAnalysis dsdGeneAnalysis) {
        this.dsdGeneAnalysis = dsdGeneAnalysis;
    }

    public Parameter getGeneticAnalysis() {
        return geneticAnalysis;
    }

    public void setGeneticAnalysis(Parameter geneticAnalysis) {
        this.geneticAnalysis = geneticAnalysis;
    }

    public Parameter getSingleGeneAnalysis() {
        return singleGeneAnalysis;
    }

    public void setSingleGeneAnalysis(Parameter singleGeneAnalysis) {
        this.singleGeneAnalysis = singleGeneAnalysis;
    }

    public Parameter getChromosomalRearrangement() {
        return chromosomalRearrangement;
    }

    public void setChromosomalRearrangement(Parameter chromosomalRearrangement) {
        this.chromosomalRearrangement = chromosomalRearrangement;
    }

    public Parameter getChromosomalRearrangementDetail() {
        return chromosomalRearrangementDetail;
    }

    public void setChromosomalRearrangementDetail(Parameter chromosomalRearrangementDetail) {
        this.chromosomalRearrangementDetail = chromosomalRearrangementDetail;
    }

    public Parameter getCgh() {
        return cgh;
    }

    public void setCgh(Parameter cgh) {
        this.cgh = cgh;
    }

    public Parameter getCghDetail() {
        return cghDetail;
    }

    public void setCghDetail(Parameter cghDetail) {
        this.cghDetail = cghDetail;
    }

    public Parameter getFunctionalStudy() {
        return functionalStudy;
    }

    public void setFunctionalStudy(Parameter functionalStudy) {
        this.functionalStudy = functionalStudy;
    }

    public Parameter getFunctionalStudyDetail() {
        return functionalStudyDetail;
    }

    public void setFunctionalStudyDetail(Parameter functionalStudyDetail) {
        this.functionalStudyDetail = functionalStudyDetail;
    }

    public Parameter getPublishedCase() {
        return publishedCase;
    }

    public void setPublishedCase(Parameter publishedCase) {
        this.publishedCase = publishedCase;
    }

    public Parameter getPublishDetail() {
        return publishDetail;
    }

    public void setPublishDetail(Parameter publishDetail) {
        this.publishDetail = publishDetail;
    }

    public Parameter getFurtherStudies() {
        return furtherStudies;
    }

    public void setFurtherStudies(Parameter furtherStudies) {
        this.furtherStudies = furtherStudies;
    }

    public List<Option> getYesNoOptions() {
        return yesNoOptions;
    }

    public void setYesNoOptions(List<Option> yesNoOptions) {
        this.yesNoOptions = yesNoOptions;
    }

}
