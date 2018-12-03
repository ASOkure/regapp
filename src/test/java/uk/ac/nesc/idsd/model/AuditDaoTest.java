package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class AuditDaoTest extends AbstractTest {
    @Autowired
    private AuditDao auditDao;

    @Test
    public void testFindByUserId() throws Exception {
        List<Audit> audits = auditDao.findByUserId("jiangj");
        log.info(audits);
    }

    @Test
    public void testFindByAction() {
        List<Audit> audits = auditDao.findByAction("AUDITOR_SEARCH");
        log.info(audits);
    }

    @Test
    public void testFindByRecordId() {
        List<Audit> audits = auditDao.findByRecordId("1");
        log.info(audits);
    }

    @Test
    public void testFindByParam() {
        List<Audit> audits = auditDao.findByParam("Country = Sudan;Centre = All centres;");
        log.info(audits);
    }

    @Test
    public void testFIndByIp() {
        List<Audit> audits = auditDao.findByIp("10.0.0.1");
        log.info(audits);
    }
}
