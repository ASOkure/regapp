package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class SectionDao extends AbstractDao<Section> {
    public static final String NAME = "name";

    public static SectionDao getFromApplicationContext(ApplicationContext ctx) {
        return (SectionDao) ctx.getBean("SectionDAO");
    }

//    public List<Section> findByIds(Integer[] ids) {
//        log.error("getting Parameter instance with id: " + ids);
//        List<Section> sections = new ArrayList<Section>(0);
//        try {
//            if(null != ids && ids.length != 0) {
//                String queryString = "from Section as model where model.sectionId = ?";
//                //queryString.append(idsString.substring(0,idsString.length() - 1));
//                sections = getHibernateTemplate().find(queryString, ids);
//            }
//            return sections;
//        } catch (RuntimeException re) {
//            log.error("get failed", re);
//            throw re;
//        }
//    }

    @SuppressWarnings("unchecked")
    public List<Section> findByName(Object name) {
        return findByProperty(NAME, name);
    }
}