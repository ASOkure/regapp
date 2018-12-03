package uk.ac.nesc.idsd.action.search;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class SearchDsdActionTest extends AbstractTest {

    @Autowired
    private SearchDsdAction searchDsdAction;

    @Autowired
    private DsdIdentifierService dsdIdentifierService;

    @Autowired
    private ParameterService parameterService;

    @Test
    public void testSearchView() {
        searchDsdAction.searchView();
    }

    @Test
    public void testSearch() throws ServiceException {
        DsdIdentifier dsdIdentifier = new DsdIdentifier();
        dsdIdentifier.setRegisterId(1598L);
        List<Parameter> selectedParameters = new ArrayList<Parameter>();
        Parameter p = new Parameter(parameterService.getParameterById(1));
        selectedParameters.add(p);
        DsdAssessment first = new DsdAssessment();

        SearchResult searchResult = dsdIdentifierService.getByExample(selectedParameters, dsdIdentifier, first, null, null);
        log.info(searchResult);
    }
}
