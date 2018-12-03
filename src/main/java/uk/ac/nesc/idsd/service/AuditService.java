package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.bean.AuditHistoryBean;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.model.DsdCahVisit;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.DsdSearchCriteria;
import uk.ac.nesc.idsd.model.ResearchResult;

import java.util.List;

public interface AuditService {

    void auditRead(DsdIdentifier dsdIdentifier);

    void auditSearch(DsdIdentifier dsdIdentifier, SearchResult searchResult);

    void logCreateCore(DsdIdentifier dsdIdentifier);

    void logCreateAssessments(DsdIdentifier dsdIdentifier);

    void logCreateGeneAnalysis(DsdIdentifier dsdIdentifier);

    void logCreateCah(DsdIdentifier dsdIdentifier);

    void logCreateCahVisit(DsdCahVisit dsdCahVisit);

    void logBulkCreateCore(DsdIdentifier dsdIdentifier);


    void logUpdateCore(DsdIdentifier dsdIdentifier);

    void logUpdateClinicalHistoryAndBiomaterials(DsdIdentifier dsdIdentifier);

    void logUpdateAssessments(DsdIdentifier dsdIdentifier);

    void logUpdateGeneAnalysis(DsdIdentifier dsdIdentifier);

    void logUpdateCah(DsdIdentifier dsdIdentifier);

    void logUpdateCahVisit(DsdCahVisit dsdCahVisit);

    void logDeleteCahVisit(DsdCahVisit dsdCahVisit);

    void logDeleteConfirmation(DsdIdentifier dsdIdentifier);

    List<AuditHistoryBean> getHistory(String registerId);

    void logResearchResultFileUpload(String username, String userIp, ResearchResult researchResult);

    void logResearchResultFileDelete(String username, String userIp, ResearchResult researchResult);

    void logResearchResultFileDownload(String user, String userIp, ResearchResult researchResult);

    void logReferPatientAccess(DsdIdentifier dsdIdentifier);

    void logPatientRead(DsdIdentifier dsdIdentifier);

    void logUserLogout(String username, String logoutLocation);

    void logCreateDsdSearchRequest(DsdSearchCriteria dsdSearchCriteria);

    void logDeleteDsdSearchRequest(DsdSearchCriteria dsdSearchCriteria);

    enum ACTION {
        CREATE_CORE("CREATE_CORE"),
        CREATE_CLINIC_BIOMATERIAL("CREATE_CLINIC_BIOMATERIAL"),
        CREATE_ASSESSMENTS("CREATE_ASSESSMENTS"),
        CREATE_GENE_ANALYSIS("CREATE_GENE_ANALYSIS"),
        CREATE_CAH("CREATE_CAH"),
        CREATE_CAH_VISIT("CREATE_CAH_VISIT"),

        UPDATE_CORE("UPDATE_CORE"),
        UPDATE_CLINIC_BIOMATERIAL("UPDATE_CLINIC_BIOMATERIAL"),
        UPDATE_ASSESSMENTS("UPDATE_ASSESSMENTS"),
        UPDATE_GENE_ANALYSIS("UPDATE_GENE_ANALYSIS"),
        UPDATE_CAH("UPDATE_CAH"),
        UPDATE_CAH_VISIT("UPDATE_CAH_VISIT"),

        BULK_CREATE_CORE("BULK_CREATE_CORE"),

        SEARCH("SEARCH"),

        READ("READ"),
        DELETE("DELETE"),
        DELETE_CONFIRMED("DELETE_CONFIRMED"),
        DELETE_CAH_VISIT("DELETE_CAH_VISIT"),

        AUDITOR_SEARCH("AUDITOR_SEARCH"),
        AUDITOR_READ("AUDITOR_READ"),

        UPLOAD("UPLOAD"),
        DOWNLOAD("DOWNLOAD"),
        FILE_REMOVED("FILE_REMOVED"),

        REFER_PATIENT_ACCESS("REFER_PATIENT_ACCESS"),
        PATIENT_READ("PATIENT_READ"),

        REQUEST_DSD_SEARCH("REQUEST_DSD_SEARCH"),
        RESPOND_DSD_SEARCH("RESPOND_DSD_SEARCH"),
        DELETE_REQUEST_DSD_SEARCH("DELETE_REQUEST_DSD_SEARCH"),
        DELETE_RESPOND_DSD_SEARCH("DELETE_RESPOND_DSD_SEARCH"),

        LOG_OUT("LOG_OUT");

        private String text;

        ACTION(String text) {
            this.text = text;
        }

        public static ACTION fromString(String text) {
            if (text != null) {
                for (ACTION ca : ACTION.values()) {
                    if (text.equalsIgnoreCase(ca.text)) {
                        return ca;
                    }
                }
            }
            return null;
        }

        public String getText() {
            return this.text;
        }
    }

}
