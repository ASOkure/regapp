package uk.ac.nesc.idsd.model;

import java.util.List;

/**
 * Created by jiangj on 10/03/2015.
 */
public interface DsdSearchCriteriaDao {
    String REGISTER_ID = "registerId";

    List<DsdSearchCriteria> findByProperty(String propertyName, Object value);

    List findAll();

    DsdSearchCriteria merge(DsdSearchCriteria detachedInstance);

    void save(DsdSearchCriteria transientInstance);

    void delete(DsdSearchCriteria persistentInstance);

    List<DsdSearchCriteria> findByExample(DsdSearchCriteria instance);

    List<DsdSearchCriteria> findByRegisterId(Long registerId);

    DsdSearchCriteria findById(Long searchId);
}
