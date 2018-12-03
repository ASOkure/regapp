package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.model.DsdSearchCriteria;
import uk.ac.nesc.idsd.service.exception.ServiceException;

public interface DsdSearchCriteriaService {

    void save(DsdSearchCriteria dsdSearchCriteria) throws ServiceException;

    void update(DsdSearchCriteria dsdSearchCriteria) throws ServiceException;

    void delete(DsdSearchCriteria dsdSearchCriteria);

    DsdSearchCriteria getById(Long searchId);
}
