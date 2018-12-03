package uk.ac.nesc.idsd.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SectionDaoTest.class,
        CentreDaoTest.class,
        DsdCahVisitDaoTest.class,
        OptionDaoTest.class,
        MenuDaoTest.class,
        PortalUserDaoTest.class,
        ParameterTest.class
})
public class DaoTestSuite {
}  	