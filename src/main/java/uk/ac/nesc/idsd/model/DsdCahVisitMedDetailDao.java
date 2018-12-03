package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DsdCahVisitMedDetailDao extends AbstractDao<DsdCahVisitMedDetail> {
    public static final String DOSE = "dose";
    public static final String UNIT = "unit";
    public static final String FREQUENCY = "frequency";
    public static final String DETAIL = "detail";

    public static DsdCahVisitMedDetailDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdCahVisitMedDetailDao) ctx.getBean("DsdCahVisitMedDetailDAO");
    }

    public List<DsdCahVisitMedDetail> findByDose(Object dose) {
        return findByProperty(DOSE, dose);
    }

    public List<DsdCahVisitMedDetail> findByUnit(Object unit) {
        return findByProperty(UNIT, unit);
    }

    public List<DsdCahVisitMedDetail> findByFrequency(Object frequency) {
        return findByProperty(FREQUENCY, frequency);
    }

    public List<DsdCahVisitMedDetail> findByDetail(Object detail) {
        return findByProperty(DETAIL, detail);
    }

}