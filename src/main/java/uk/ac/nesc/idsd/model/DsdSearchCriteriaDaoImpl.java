package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class DsdSearchCriteriaDaoImpl extends AbstractDao<DsdSearchCriteria> implements DsdSearchCriteriaDao {
    private static final Log log = LogFactory.getLog(DsdSearchCriteriaDaoImpl.class);

    public static DsdIdentifierDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdIdentifierDao) ctx.getBean("dsdSearchCriteriaDao");
    }

    @Override
    public List<DsdSearchCriteria> findByRegisterId(Long registerId) {
        return super.findByProperty(REGISTER_ID, registerId);
    }

    @Override
    public DsdSearchCriteria findById(Long searchId) {
        return super.findById(searchId);
    }

	
}