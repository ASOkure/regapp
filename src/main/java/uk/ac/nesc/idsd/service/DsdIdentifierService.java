package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.bean.DsdIdentifierResearchResultBean;
import uk.ac.nesc.idsd.bean.ProgressBean;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.bean.StatisticResultBean;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdCah;
import uk.ac.nesc.idsd.model.DsdCahVisit;
import uk.ac.nesc.idsd.model.DsdGeneAnalysis;
import uk.ac.nesc.idsd.model.DsdGeneScreened;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.DsdNeonatalVisits;
import uk.ac.nesc.idsd.model.DsdSearchCriteria;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DsdIdentifierService {

    void save(DsdIdentifier dsdIdentifier) throws ServiceException;

    DsdIdentifier saveCore(Long registerId, DsdIdentifier dsdIdentifier) throws ServiceException;

    DsdIdentifier saveClinical(Long registerId, DsdIdentifier dsdIdentifier);

    DsdIdentifier saveAssessments(Long registerId, DsdIdentifier dsdIdentifier);

    DsdIdentifier saveGeneAnalysis(Long registerId, DsdGeneAnalysis dsdGeneAnalysis, List<DsdGeneScreened> dsdGeneScreeneds) throws ServiceException;

    DsdIdentifier saveCah(DsdIdentifier dsdIdentifier, DsdCah dsdCah) throws ServiceException;

    DsdCahVisit getCahVisitById(Long dsdCahVisitId);

    DsdCahVisit createNewTransitCahVisitInstance(DsdIdentifier dsdIdentifier);

    DsdCahVisit saveCahVisit(DsdIdentifier dsdIdentifier, DsdCahVisit dsdCahVisit) throws ServiceException;

    DsdAssessment getDsdAssessmentById(Long assessmentId) throws ServiceException;

    void saveAssessment(Long registerId, DsdAssessment dsdAssessment);

    void deleteAssessment(Long assessmentId) throws ServiceException;

    void update(DsdIdentifier dsdIdentifier) throws ServiceException;

    void delete(DsdIdentifier dsdIdentifier);

    SearchResult getByExample(List<Parameter> selectedParameters,
                              DsdIdentifier dsdIdentifier,
                              DsdAssessment firstDsdAssessment,
                              DsdAssessment latestDsdAssessment,
                              String geneSelection) throws ServiceException;

    SearchResult searchDsdRecords(DsdSearchCriteria dsdSearchCriteria) throws ServiceException;

    DsdIdentifier getById(Long registerId);

    List<DsdIdentifierResearchResultBean> getDsdIdentifierResearchResultBeans(PortalUser user, DsdIdentifier dsdIdentifier);

    List<String> getRegisterIds();

    StatisticResultBean getRecordStatistic();

    long getTotalRecordNumber();

    List<ProgressBean> getProgressStatistic();

    Map<PortalUser, String> getAge16Reminder();

    DsdIdentifier getDsdIdentifierByIdForPatientAccess(Long registerId);

    void deleteCahVisit(Long dsdCahVisitId) throws ServiceException;

    DsdCahVisit getLastDsdCahVisit(Long dsdCahId);

    Map<Long, String> saveDsdCoreInBulk(List<DsdIdentifier> dsdIdentifiers);

    SearchResult getMyRecords(String username);

    SearchResult getMyCentreRecords(String username);

    String getCsvByIds(Set<Long> registerIds);

    String getCsvByDsdSearchResult(SearchResult searchResult);
    
    //sam added below

	DsdIdentifier saveNeonatal(DsdIdentifier dsdIdentifier, DsdNeonatalVisits dsdneonatalvisits) throws ServiceException;
}
