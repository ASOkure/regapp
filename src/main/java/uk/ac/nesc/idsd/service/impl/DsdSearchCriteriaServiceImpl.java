package uk.ac.nesc.idsd.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.model.DsdSearchCriteria;
import uk.ac.nesc.idsd.model.DsdSearchCriteriaDao;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdSearchCriteriaService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.io.Serializable;
import java.sql.Timestamp;

public class DsdSearchCriteriaServiceImpl implements DsdSearchCriteriaService, Serializable {
    private static final Log log = LogFactory.getLog(DsdSearchCriteriaServiceImpl.class);

    @Autowired
    protected AuditService auditService;
    @Autowired
    private DsdSearchCriteriaDao dsdSearchCriteriaDao;

    public void delete(DsdSearchCriteria dsdSearchCriteria) {
        this.dsdSearchCriteriaDao.delete(dsdSearchCriteria);
        this.auditService.logDeleteDsdSearchRequest(dsdSearchCriteria);
    }

    public void save(DsdSearchCriteria dsdSearchCriteria) throws ServiceException {
        dsdSearchCriteria.setRequestTime(new Timestamp(System.currentTimeMillis()));
        dsdSearchCriteria.setRequesterId(Utility.getLoginUserName());
        this.dsdSearchCriteriaDao.save(dsdSearchCriteria);
        this.auditService.logCreateDsdSearchRequest(dsdSearchCriteria);
    }

    public void update(DsdSearchCriteria dsdSearchCriteria) throws ServiceException {

    }

    public DsdSearchCriteria getById(Long searchId) {
        return this.dsdSearchCriteriaDao.findById(searchId);
    }

}
