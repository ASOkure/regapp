package uk.ac.nesc.idsd.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DsdNeonatalVisitsDaoImp extends AbstractDao<DsdNeonatalVisits> implements DsdNeonatalVisitsDao {
	
	private static final Logger log = LoggerFactory.getLogger(DsdNeonatalVisitsDaoImp.class);
	
	 public static DsdNeonatalVisitsDaoImp getFromApplicationContext(ApplicationContext ctx) {
	        return (DsdNeonatalVisitsDaoImp) ctx.getBean("dsdNeonatalVisitsDao");
	    }


}

