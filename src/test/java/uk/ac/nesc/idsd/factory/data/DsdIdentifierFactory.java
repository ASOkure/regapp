package uk.ac.nesc.idsd.factory.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.exception.ServiceException;

/**
 * Created by jiangj on 12/06/2015.
 */
@Service
public class DsdIdentifierFactory {

    @Autowired
    private CentreFactory centreFactory;
    @Autowired
    private DsdIdentifierService dsdIdentifierService;

    public DsdIdentifier create() throws ServiceException {
        DsdIdentifier dsdIdentifier = new DsdIdentifier();

        dsdIdentifier.setCentre(centreFactory.create("Glasgow", "United Kingdom"));
        dsdIdentifierService.save(dsdIdentifier);
        return dsdIdentifier;
    }
}
