package uk.ac.nesc.idsd.service;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jiangj on 11/06/2015.
 */
public class ParameterServiceImplTest extends AbstractTest {

    @Autowired
    private ParameterService parameterService;

    @Test
    public void testGetDsdCoreSectionsForCreateView() {
        List<Section> coreSections = parameterService.getCoreAndDiagnosisSectionsForCreateView();
        assertFalse("Core Sections should not be empty", CollectionUtils.isEmpty(coreSections));
        Set<Integer> parameterIds = new HashSet<>();
        for (Section s : coreSections) {
            Set<Parameter> parameters = s.getParameters();
            assertFalse("Parameters inside a Section should not be empty", CollectionUtils.isEmpty(coreSections));
            for (Parameter p : parameters) {
                parameterIds.add(p.getParamId());
            }
        }
        assertFalse("Create View should not contain register_id", parameterIds.contains(1));
    }

    @Test
    public void testGetDsdCoreSectionsForUpdateView() {
        List<Section> coreSections = parameterService.getCoreAndDiagnosisSectionsForUpdateView();
        assertFalse("Core Sections should not be empty", CollectionUtils.isEmpty(coreSections));
        Set<Integer> parameterIds = new HashSet<>();
        for (Section s : coreSections) {
            Set<Parameter> parameters = s.getParameters();
            assertFalse("Parameters inside a Section should not be empty", CollectionUtils.isEmpty(coreSections));
            for (Parameter p : parameters) {
                parameterIds.add(p.getParamId());
            }
        }
        assertTrue("Update View should contain register_id", parameterIds.contains(1));
    }

    @Test
    public void testGetAllSearchSections() throws ServiceException {
        List<Section> sections = parameterService.getAllSearchSections(portalUser);
//        log.info(sections);
    }

}
