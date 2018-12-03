package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DsdCahVisitEpisodeDao extends AbstractDao<DsdCahVisitEpisode> {
    public static final String NUMBER_OF_DAYS = "numberOfDays";

    public static DsdCahVisitEpisodeDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdCahVisitEpisodeDao) ctx.getBean("DsdCahVisitEpisodeDAO");
    }

    public List<DsdCahVisitEpisode> findByNumberOfDays(Object numberOfDays) {
        return findByProperty(NUMBER_OF_DAYS, numberOfDays);
    }

}