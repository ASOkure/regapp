package uk.ac.nesc.idsd.util.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import uk.ac.nesc.idsd.Constants;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.bean.SearchResultCell;
import uk.ac.nesc.idsd.bean.SearchResultHeadCell;
import uk.ac.nesc.idsd.bean.SearchResultRow;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdGeneScreened;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Gene;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.security.Ownership;
import uk.ac.nesc.idsd.service.GeneException;
import uk.ac.nesc.idsd.service.GeneService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.DsdAssessmentDateComparator;
import uk.ac.nesc.idsd.util.comparator.DsdIdentifierComparator;
import uk.ac.nesc.idsd.util.comparator.ParameterComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DsdSearchFilter {
    private static final Log log = LogFactory.getLog(DsdSearchFilter.class);

    /**
     * set completeness and delete status flag to false
     * to avoid uncompleted and deleted cases.
     *
     * @param dsdIdentifier
     * @return
     */
    public DsdIdentifier dsdIdentifierRecordStatusFilter(DsdIdentifier dsdIdentifier) {
        if (dsdIdentifier != null) {
            //search only completed cases
            dsdIdentifier.setCompleteness(true);
            //search only those not in delete status
            dsdIdentifier.setDeleteStatus(false);
        }
        return dsdIdentifier;
    }

    /**
     * if user is Local Contributor, set country and centre even if user not select.
     *
     * @param dsdIdentifier
     * @param portalUser
     * @return
     */
    public DsdIdentifier dsdIdentifierRoleFilter(DsdIdentifier dsdIdentifier, PortalUser portalUser) {
        if (Authorization.isLocalContributor(portalUser)) {
            dsdIdentifier.getCentre().setCentreName(portalUser.getCentre());
            dsdIdentifier.getCentre().getCountry().setCountryName(portalUser.getCountry());
        }
        return dsdIdentifier;
    }

    /**
     * prepare the example for findByExample() method.
     * set consent = null, when user not select it to search.
     *
     * @param dsdIdentifier
     * @return
     */
    public DsdIdentifier dsdIdentifierConsentFilter(DsdIdentifier dsdIdentifier) {
        //deal with sampleConsent: if false, set to null
        if (dsdIdentifier != null && dsdIdentifier.getSampleConsent() != null && !dsdIdentifier.getSampleConsent()) {
            dsdIdentifier.setSampleConsent(null);
        }
        return dsdIdentifier;
    }

    /**
     * prepare the example for findByExample() method.
     * set actualDiagnosis = null if user select "All of the above"
     *
     * @param dsdIdentifier
     * @return
     */
    public DsdIdentifier dsdIdentifierActualDiagnosisFilter(DsdIdentifier dsdIdentifier) {
        //deal with Actual Diagnosis == All of the above,
        if (null != dsdIdentifier && null != dsdIdentifier.getActualDiagnosis()
                && dsdIdentifier.getActualDiagnosis().equalsIgnoreCase("All of the above")) {
            dsdIdentifier.setActualDiagnosis(null);
        }
        return dsdIdentifier;
    }

    public DsdIdentifier dsdIdentifierDisorderTypeFilter(DsdIdentifier dsdIdentifier) {
        //deal with disorder type == Any
        if (null != dsdIdentifier && null != dsdIdentifier.getDisorderType()
                && dsdIdentifier.getDisorderType().equalsIgnoreCase("Any")) {
            dsdIdentifier.setDisorderType(null);
        }
        return dsdIdentifier;
    }

    /**
     * prepare the example for findByExample() method.
     * set centre = null if user select "all centres"
     *
     * @param dsdIdentifier
     * @return
     */
    public DsdIdentifier dsdIdentifierCentreFilter(DsdIdentifier dsdIdentifier) {
        if (null != dsdIdentifier && null != dsdIdentifier.getCentreName()
                && dsdIdentifier.getCentreName().equalsIgnoreCase(Constants.ALL_CENTRES)) {
            log.debug("before centre = " + dsdIdentifier.getCentreName());
            dsdIdentifier.setCentreName(null);
            log.debug("after centre = " + dsdIdentifier.getCentreName());
            log.debug("after country = " + dsdIdentifier.getCountryName());
        }
        return dsdIdentifier;
    }

    /**
     * Must match all genes given to return true for this one dsdidentifier.
     * e.g. geneString = A, B, C. The dsdList must contains all dsdIdentifiers that contains at least A, B, C.
     *
     * @param dsdList
     * @param geneManager
     * @param geneString
     * @return
     * @throws GeneException
     * @throws Exception
     */
    public List<DsdIdentifier> searchResultListGeneFilter(List<DsdIdentifier> dsdList,
                                                          GeneService geneManager, String geneString) {
        List<DsdIdentifier> dsdIdentifiers = new ArrayList<DsdIdentifier>();
        if (null == geneString || geneString.isEmpty()) {
            return dsdList;
        }
        //translate gene string into a list of genes.
        String[] genes = geneString.trim().split(",");
        List<Gene> searchGeneList = new ArrayList<Gene>();
        for (String gene : genes) {
            searchGeneList.add(geneManager.getGeneByName(gene.trim()));
        }

        //loop through dsd list. for every geneAnalysis, go through all genes contained.
        //must match all to return true
        Set<Integer> dbGenes = new HashSet<Integer>();
        Set<Integer> searchGenes = new HashSet<Integer>();
        for (DsdIdentifier d : dsdList) {
            dbGenes.clear();
            searchGenes.clear();
            if (null != d && null != d.getDsdGeneAnalysis() && null != d.getDsdGeneAnalysis().getDsdGeneScreeneds()) {
                for (DsdGeneScreened geneScreened : d.getDsdGeneAnalysis().getDsdGeneScreeneds()) {
                    dbGenes.add(geneScreened.getGene().getGeneId());
                }
                for (Gene gene : searchGeneList) {
                    searchGenes.add(gene.getGeneId());
                }
                if (dbGenes.containsAll(searchGenes)) {
                    dsdIdentifiers.add(d);
                }
            }
        }
        return dsdIdentifiers;
    }

    /**
     * check list of dsdIdentifer objects, take out those don't fit first/latestAssessment criteria, and return the modified list
     *
     * @param dsdIdentifiers
     * @param firstDsdAssessment
     * @param latestDsdAssessment
     * @return
     */
    public List<DsdIdentifier> searchResultListAssessmentFilter(List<DsdIdentifier> dsdIdentifiers, DsdAssessment firstDsdAssessment, DsdAssessment latestDsdAssessment) {
        if (CollectionUtils.isEmpty(dsdIdentifiers) || (firstDsdAssessment == null && latestDsdAssessment == null)) {
            return dsdIdentifiers;
        }
        //trim the first and latest assessment instances
        firstDsdAssessment = new DsdAssessment(firstDsdAssessment);
        latestDsdAssessment = new DsdAssessment(latestDsdAssessment);

        List<DsdIdentifier> newDsdList = new ArrayList<DsdIdentifier>();
        for (DsdIdentifier d : dsdIdentifiers) {
            if (null != d && (d.getDsdAssessments() == null || d.getDsdAssessments().size() == 0)) {
                newDsdList.add(d);
                continue;
            }
            List<DsdAssessment> dsdAssessmentList = new ArrayList<DsdAssessment>(d.getDsdAssessments());
            //sort the list, and take the first and last element, if they exist in the list.
            Collections.sort(dsdAssessmentList, new DsdAssessmentDateComparator());
            DsdAssessment firstAssessment = dsdAssessmentList.size() > 0 ? dsdAssessmentList.get(0) : null;
            DsdAssessment latestAssessment = dsdAssessmentList.get(dsdAssessmentList.size() - 1);

//            log.info("dsdAssessmentMatchingFilter(firstDsdAssessment, firstAssessment) = " + dsdAssessmentMatchingFilter(firstDsdAssessment, firstAssessment));
//            log.info("dsdAssessmentMatchingFilter(latestDsdAssessment, latestAssessment) = " + dsdAssessmentMatchingFilter(latestDsdAssessment, latestAssessment));
            if (dsdAssessmentMatchingFilter(firstDsdAssessment, firstAssessment) && dsdAssessmentMatchingFilter(latestDsdAssessment, latestAssessment)) {
                newDsdList.add(d);
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("old size: " + dsdIdentifiers.size() + ", new size: " + newDsdList.size());
        }
        return newDsdList;

    }

    /**
     * not a match or compare method,
     * only check if giving database DsdAssessment matches the search criteria defined in search DsdAssessment
     *
     * @param searchDa
     * @param dbDa
     * @return
     */
    private boolean dsdAssessmentMatchingFilter(DsdAssessment searchDa, DsdAssessment dbDa) {
        if (searchDa == null || searchDa.equals(dbDa)) {
            return true;
        }
        if (dbDa == null) {
            return false;//use this line alone is wrong, but after above line, it's correct
        }
        //log.error(searchDa.getPhallusSize() == null || searchDa.getPhallusSize().equals(dbDa.getPhallusSize()));
        //boolean[] flags = new boolean[20];
        return (searchDa.getAssessmentDate() == null || searchDa.getAssessmentDate().equals(dbDa.getAssessmentDate())) &&
                (searchDa.getEms() == null || searchDa.getEms().equals(dbDa.getEms())) &&
                (searchDa.getLabioscrotalFusion() == null || searchDa.getLabioscrotalFusion().equals(dbDa.getLabioscrotalFusion())) &&
                (searchDa.getLeftGonad() == null || searchDa.getLeftGonad().equals(dbDa.getLeftGonad())) &&
                (searchDa.getModifiedPrader() == null || searchDa.getModifiedPrader().equals(dbDa.getModifiedPrader())) &&
                (searchDa.getMullerianStructure() == null || searchDa.getMullerianStructure().equals(dbDa.getMullerianStructure())) &&
                (searchDa.getPhallusLength() == null || searchDa.getPhallusLength().equals(dbDa.getPhallusLength())) &&
                (searchDa.getPhallusSize() == null || searchDa.getPhallusSize().equals(dbDa.getPhallusSize())) &&
                (searchDa.getRightGonad() == null || searchDa.getRightGonad().equals(dbDa.getRightGonad())) &&
                (searchDa.getTannerStage() == null || searchDa.getTannerStage().equals(dbDa.getTannerStage())) &&
                (searchDa.getUrinaryMeatus() == null || searchDa.getUrinaryMeatus().equals(dbDa.getUrinaryMeatus())) &&
                (searchDa.getWolffianStructure() == null || searchDa.getWolffianStructure().equals(dbDa.getWolffianStructure())) &&
                (searchDa.getGonadectomy() == null || searchDa.getGonadectomy().equals(dbDa.getGonadectomy())) &&
                (searchDa.getGestation() == null || searchDa.getGestation().equals(dbDa.getGestation()));
        //&& (searchDa.getDsdIdentifier() == null || searchDa.getDsdIdentifier().equals(dbDa.getDsdIdentifier()));
    }

    private List<Parameter> sortParameterList(List<Parameter> selectedParameters) {
        //sort parameters according to id
        Collections.sort(selectedParameters, new ParameterComparator());

        //then sort the first and latest assessment
        List<Parameter> newParameterList = new ArrayList<Parameter>();
        List<Parameter> firstDsdAssessmentParameterList = new ArrayList<Parameter>();
        List<Parameter> latestDsdAssessmentParameterList = new ArrayList<Parameter>();

        for (Parameter p : selectedParameters) {
            if (p.getLabel().startsWith("First ") && !p.getLabel().trim().equals("Age of First Presentation".trim())) {
                p.setParamName("first" + p.getParamName().substring(0, 1).toUpperCase() + p.getParamName().substring(1));
                firstDsdAssessmentParameterList.add(p);
            } else if (p.getLabel().contains("Latest ")) {
                p.setParamName("latest" + p.getParamName().substring(0, 1).toUpperCase() + p.getParamName().substring(1));
                latestDsdAssessmentParameterList.add(p);
            } else {
                newParameterList.add(p);
            }
        }

        Collections.sort(newParameterList, new ParameterComparator());
        Collections.sort(firstDsdAssessmentParameterList, new ParameterComparator());
        Collections.sort(latestDsdAssessmentParameterList, new ParameterComparator());

        //find the index to insert firstAssessmentParameterList
        int firstDsdAssessmentListInsertIndex = 0;
        if (!firstDsdAssessmentParameterList.isEmpty()) {
            for (Parameter p : newParameterList) {
                if (firstDsdAssessmentParameterList.size() != 0
                        && p.getParamId() < firstDsdAssessmentParameterList.get(0).getParamId()
                        && p.getParamId() > firstDsdAssessmentListInsertIndex) {
                    firstDsdAssessmentListInsertIndex = p.getParamId();
                }
            }
            //insert firstAssessmentParamterList
            newParameterList.addAll(firstDsdAssessmentListInsertIndex, firstDsdAssessmentParameterList);
        }
        if (!latestDsdAssessmentParameterList.isEmpty()) {
            //insert latestAssessmentParameterList
            newParameterList.addAll(firstDsdAssessmentListInsertIndex + firstDsdAssessmentParameterList.size(), latestDsdAssessmentParameterList);
        }

        return newParameterList;
    }

    @SuppressWarnings("unchecked")
    public SearchResult constructSearchResult(ParameterService parameterService, List<Parameter> selectedParameters,
                                              int originalResultSize, List<DsdIdentifier> dsdList, PortalUser portalUser) {
        //if selectedParameters is an empty list, we add registerId
        //make sure that the table has at least one/two columns to show
        if (selectedParameters == null) {
            selectedParameters = new ArrayList<Parameter>();
        }
        if (CollectionUtils.isEmpty(selectedParameters)) {
            selectedParameters.add(parameterService.getParameterById(1));
        }
        //sort the parameter list
        selectedParameters = sortParameterList(selectedParameters);

        //set table head row
        List<SearchResultHeadCell> headRow = new ArrayList<SearchResultHeadCell>();
        for (Parameter p : selectedParameters) {
            if (p.getParamId() == 2) {
                headRow.add(new SearchResultHeadCell(p.getParamId().toString(), "Country"));
                headRow.add(new SearchResultHeadCell("2.5", "Centre"));
            } else if (p.getParamId() == 11) {
                headRow.add(new SearchResultHeadCell(p.getParamId().toString(), "Disorder Type"));
                headRow.add(new SearchResultHeadCell("11.5", "Actual Diagnosis"));
            } else if (p.getParamId() == 43) {
                headRow.add(new SearchResultHeadCell(p.getParamId().toString(), "Cell Line"));
                headRow.add(new SearchResultHeadCell("43.5", "Cell Line Info"));
            } else {
                headRow.add(new SearchResultHeadCell(p.getParamId().toString(), p.getLabel()));
            }
        }
        headRow.add(new SearchResultHeadCell("999997", "Upload Time"));
        headRow.add(new SearchResultHeadCell("999998", "Upload By"));
        headRow.add(new SearchResultHeadCell("999999", "Actions"));

        //set table result rows
        List<SearchResultRow> searchResultRows = new ArrayList<SearchResultRow>();
        for (DsdIdentifier d : dsdList) {
            @SuppressWarnings("rawtypes")
            List<SearchResultCell> cells = new ArrayList<>();
            for (Parameter p : selectedParameters) {
                cells.add(new SearchResultCell(p.getParamId().toString(), Utility.getDsdProperty(d, p.getParamName())));
                if (p.getParamId() == 2 || p.getParamId() == 11 || p.getParamId() == 43) {
                    cells.add(new SearchResultCell(p.getParamId().toString(), Utility.getDsdProperty(d, p.getMenu().getTertiaryParamName())));
                }
            }
            cells.add(new SearchResultCell("999997", Utility.getDsdProperty(d, "dsdIdentifier.uploadTime")));
            cells.add(new SearchResultCell("999998", Utility.getDsdProperty(d, "dsdIdentifier.uploader_id")));
            cells.add(new SearchResultCell("999999", Utility.getDsdProperty(d, "dsdIdentifier.registerId")));
            SearchResultRow searchResultRow = new SearchResultRow(d.getRegisterId(), Ownership.isEditable(d, portalUser), cells);
            searchResultRows.add(searchResultRow);
        }
        return new SearchResult(originalResultSize, dsdList.size(), headRow, searchResultRows);
    }

    /**
     * remove duplicated record, and sort
     *
     * @param dsdList
     * @return
     */
    public List<DsdIdentifier> removeDuplicates(List<DsdIdentifier> dsdList) {
        Set<DsdIdentifier> dsdSet = new TreeSet<DsdIdentifier>(new DsdIdentifierComparator());
        dsdSet.addAll(dsdList);
        return new ArrayList<DsdIdentifier>(dsdSet);
    }

}
