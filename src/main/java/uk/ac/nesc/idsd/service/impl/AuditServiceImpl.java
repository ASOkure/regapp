package uk.ac.nesc.idsd.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import uk.ac.nesc.idsd.bean.AuditHistoryBean;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.bean.SearchResultRow;
import uk.ac.nesc.idsd.model.Audit;
import uk.ac.nesc.idsd.model.AuditDao;
import uk.ac.nesc.idsd.model.DsdCahVisit;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.DsdSearchCriteria;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.ResearchResult;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AuditServiceImpl implements AuditService, Serializable {
    private static final Log log = LogFactory.getLog(AuditServiceImpl.class);

    @Autowired
    private AuditDao auditDao;
    @Autowired
    private UserService userService;

    private PortalUser getPortalUser() throws ServiceException {
        return this.userService.getPortalUserByUsername(Utility.getLoginUserName());
    }

    public void logCreateCore(DsdIdentifier dsdIdentifier) {
        try {
            if (null == findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.CREATE_CORE)) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.CREATE_CORE.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toCoreString(), getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.CREATE_CORE.getText(), e);
        }
    }

    private Audit findLatestAuditRecord(String id, ACTION action) throws ServiceException {
        Audit instance = new Audit();
        instance.setUserId(Utility.getLoginUserName());
        instance.setRecordId(id);
        instance.setAction(action.getText());
        List<Audit> audits = this.auditDao.findByExample(instance);
        if (!CollectionUtils.isEmpty(audits)) {
            Collections.sort(audits, new Comparator<Audit>() {
                @Override
                public int compare(Audit o1, Audit o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            return audits.get(0);
        } else {
            return null;
        }
    }

    public void logCreateAssessments(DsdIdentifier dsdIdentifier) {
        try {
            if (null == findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.CREATE_ASSESSMENTS)) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.CREATE_ASSESSMENTS.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toAssessmentString(),
                        getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.CREATE_ASSESSMENTS.getText(), e);
        }
    }

    public void logCreateGeneAnalysis(DsdIdentifier dsdIdentifier) {
        try {
            if (null == findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.CREATE_GENE_ANALYSIS)) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.CREATE_GENE_ANALYSIS.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toGeneAnalysisString(), getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.CREATE_GENE_ANALYSIS.getText(), e);
        }
    }

    @Override
    public void logCreateCah(DsdIdentifier dsdIdentifier) {
        try {
            if (null == findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.CREATE_CAH)) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.CREATE_CAH.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toCahString(), getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.CREATE_CAH.getText(), e);
        }
    }

    @Override
    public void logUpdateCah(DsdIdentifier dsdIdentifier) {
        try {
            Audit audit = findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.UPDATE_CAH);
            String dsdString = dsdIdentifier.toString();
            if ((null == audit) || ((null != audit.getParam() && !audit.getParam().equalsIgnoreCase(dsdString)))) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.UPDATE_CAH.getText(), dsdIdentifier.getRegisterId().toString(), dsdString, getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for updating DSD records action", e);
        }
    }

    @Override
    public void logCreateCahVisit(DsdCahVisit dsdCahVisit) {
        try {
            this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                    ACTION.CREATE_CAH_VISIT.getText(), dsdCahVisit.getDsdCah().getDsdIdentifier().getRegisterId().toString(), dsdCahVisit.toString(), getIp()));
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.CREATE_CAH.getText(), e);
        }
    }

    @Override
    public void logUpdateCahVisit(DsdCahVisit dsdCahVisit) {
        try {
            this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                    ACTION.UPDATE_CAH_VISIT.getText(), dsdCahVisit.getDsdCah().getDsdIdentifier().getRegisterId().toString(), dsdCahVisit.toString(), getIp()));
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.CREATE_CAH.getText(), e);
        }
    }

    @Override
    public void logDeleteCahVisit(DsdCahVisit dsdCahVisit) {
        try {
            this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                    ACTION.DELETE_CAH_VISIT.getText(), dsdCahVisit.getDsdCah().getDsdIdentifier().getRegisterId().toString(), dsdCahVisit.toString(), getIp()));
        } catch (ServiceException ue) {
            log.error("User exception occurred while saving auditing record for deleting CAH visit record: " + dsdCahVisit.getDsdCahVisitId(), ue);
        }
    }

    @Override
    public void logBulkCreateCore(DsdIdentifier dsdIdentifier) {
        try {
            if (null == findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.BULK_CREATE_CORE)) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.BULK_CREATE_CORE.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toCoreString(), getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.BULK_CREATE_CORE.getText(), e);
        }
    }

    @Override
    public void logUpdateCore(DsdIdentifier dsdIdentifier) {
        try {
            Audit audit = findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.UPDATE_CORE);
            if (null == audit) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.UPDATE_CORE.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toCoreString(), getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for updating DSD records action", e);
        }
    }

    @Override
    public void logUpdateClinicalHistoryAndBiomaterials(DsdIdentifier dsdIdentifier) {
        try {
            Audit audit = findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.UPDATE_CLINIC_BIOMATERIAL);
            String dsdString = dsdIdentifier.toString();
            if ((null == audit) || ((null != audit.getParam() && !audit.getParam().equalsIgnoreCase(dsdString)))) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.UPDATE_CLINIC_BIOMATERIAL.getText(), dsdIdentifier.getRegisterId().toString(), dsdString, getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for updating DSD records action", e);
        }
    }

    @Override
    public void logUpdateAssessments(DsdIdentifier dsdIdentifier) {
        try {
            Audit audit = findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.UPDATE_ASSESSMENTS);
            String dsdString = dsdIdentifier.toString();
            if ((null == audit) || ((null != audit.getParam() && !audit.getParam().equalsIgnoreCase(dsdString)))) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.UPDATE_ASSESSMENTS.getText(), dsdIdentifier.getRegisterId().toString(), dsdString, getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for updating DSD records action", e);
        }
    }

    @Override
    public void logUpdateGeneAnalysis(DsdIdentifier dsdIdentifier) {
        try {
            Audit audit = findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.UPDATE_GENE_ANALYSIS);
            String dsdString = dsdIdentifier.toString();
            if ((null == audit) || ((null != audit.getParam() && !audit.getParam().equalsIgnoreCase(dsdString)))) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.UPDATE_GENE_ANALYSIS.getText(), dsdIdentifier.getRegisterId().toString(), dsdString, getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for updating DSD records action", e);
        }
    }

    public void auditRead(DsdIdentifier dsdIdentifier) {
        try {
            this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                    Authorization.isAuditor(this.getPortalUser()) ? ACTION.AUDITOR_READ.getText() : ACTION.READ.getText(),
                    dsdIdentifier.getRegisterId().toString(), "", getIp()));
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for reading DSD records action", e);
        }
    }

    public void auditSearch(DsdIdentifier dsdIdentifier, SearchResult searchResult) {
        StringBuilder idStrBuilder = new StringBuilder();
        String ids;
        if (null != searchResult.getResultRows() && !searchResult.getResultRows().isEmpty()) {
            for (SearchResultRow resultRow : searchResult.getResultRows()) {
                idStrBuilder.append(resultRow.getRegisterId());
                idStrBuilder.append(",");
            }
            ids = idStrBuilder.substring(0, idStrBuilder.length() - 1);
        } else {
            ids = "NONE";
        }

        try {
            this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                    Authorization.isAuditor(this.getPortalUser()) ? ACTION.AUDITOR_SEARCH.getText() : ACTION.SEARCH.getText(),
                    ids, dsdIdentifier.toString(), getIp()));
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for searching DSD records action", e);
        }
    }

    public void logDeleteConfirmation(DsdIdentifier dsdIdentifier) {
        try {
            this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                    ACTION.DELETE_CONFIRMED.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toString(), getIp()));
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for confirming DSD records delete action", e);
        }
    }

    public List<AuditHistoryBean> getHistory(String registerId) {
        List<AuditHistoryBean> auditHistoryBeanList = new ArrayList<AuditHistoryBean>();
        List<Audit> audits = this.auditDao.findByRecordId(registerId);
        for (Audit a : audits) {
            if (a.getAction().startsWith("CREATE") || a.getAction().startsWith("UPDATE")) {
                auditHistoryBeanList.add(new AuditHistoryBean(registerId, a.getAction(), a.getTimestamp(), a.getUserId()));
            }
        }
        return auditHistoryBeanList;
    }

    public void logResearchResultFileUpload(String username, String userIp, ResearchResult researchResult) {
        String diskPath = researchResult.getPath() + File.separator + researchResult.getDiskFileName();
        File resultFile = new File(diskPath);
        String access = (researchResult.getAccess() == null) ? "none" : researchResult.getAccess();
        this.auditDao.save(new Audit(username, new Timestamp(System.currentTimeMillis()),
                ACTION.UPLOAD.getText(), researchResult.getDsdIdentifier().getRegisterId().toString(),
                "id=" + researchResult.getResultId() + ",name=" + researchResult.getOriginalFileName() + ",location=" + diskPath +
                        ",type=" + researchResult.getContentType() + ",size=" + resultFile.length() + ",share=" + access, userIp));
    }

    public void logResearchResultFileDelete(String username, String userIp, ResearchResult researchResult) {
        String diskPath = researchResult.getPath() + File.separator + researchResult.getDiskFileName();
        this.auditDao.save(new Audit(username, new Timestamp(System.currentTimeMillis()),
                ACTION.FILE_REMOVED.getText(), researchResult.getDsdIdentifier().getRegisterId().toString(),
                "id=" + researchResult.getResultId() + ",location=" + diskPath, userIp));
    }

    public void logResearchResultFileDownload(String username, String userIp, ResearchResult researchResult) {
        this.auditDao.save(new Audit(username, new Timestamp(System.currentTimeMillis()),
                ACTION.DOWNLOAD.getText(), researchResult.getDsdIdentifier().getRegisterId().toString(), researchResult.getResultId().toString(), userIp));
    }

    @Override
    public void logReferPatientAccess(DsdIdentifier dsdIdentifier) {
        try {
            if (null == findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.REFER_PATIENT_ACCESS)) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.REFER_PATIENT_ACCESS.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toCoreString(), getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.REFER_PATIENT_ACCESS.getText(), e);
        }
    }

    @Override
    public void logPatientRead(DsdIdentifier dsdIdentifier) {
        try {
            if (null == findLatestAuditRecord(dsdIdentifier.getRegisterId().toString(), ACTION.PATIENT_READ)) {
                this.auditDao.save(new Audit(Utility.getLoginUserName(), new Timestamp(System.currentTimeMillis()),
                        ACTION.PATIENT_READ.getText(), dsdIdentifier.getRegisterId().toString(), dsdIdentifier.toCoreString(), getIp()));
            }
        } catch (ServiceException e) {
            log.error("Error occurred while saving auditing record for saving " + ACTION.PATIENT_READ.getText(), e);
        }
    }

    private String getIp() {
        return Utility.getLoginUserIp();
    }

    @Override
    public void logUserLogout(String username, String logoutLocation) {
        this.auditDao.save(new Audit(username, new Timestamp(System.currentTimeMillis()), ACTION.LOG_OUT.getText(), null, logoutLocation, null));
    }

    @Override
    public void logCreateDsdSearchRequest(DsdSearchCriteria dsdSearchCriteria) {
        this.auditDao.save(new Audit(dsdSearchCriteria.getRequesterId(), new Timestamp(System.currentTimeMillis()), ACTION.REQUEST_DSD_SEARCH.toString(), null, dsdSearchCriteria.toString(), getIp()));
    }

    @Override
    public void logDeleteDsdSearchRequest(DsdSearchCriteria dsdSearchCriteria) {
        this.auditDao.save(new Audit(dsdSearchCriteria.getRequesterId(), new Timestamp(System.currentTimeMillis()), ACTION.DELETE_REQUEST_DSD_SEARCH.toString(), null, dsdSearchCriteria.toString(), getIp()));
    }

}
