package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class MenuDao extends AbstractDao<Menu> {
    public static final String MENU_NAME = "menuName";
    public static final String TERTIARY = "tertiary";
    public static final String TERTIARY_PARAM_NAME = "tertiaryParamName";

    public List<Menu> findByMenuName(Object menuName) {
        return findByProperty(MENU_NAME, menuName);
    }

    public List<Menu> findByTertiary(Object tertiary) {
        return findByProperty(TERTIARY, tertiary);
    }

    public List<Menu> findByTertiaryParamName(Object tertiaryParamName) {
        return findByProperty(TERTIARY_PARAM_NAME, tertiaryParamName);
    }

    public static MenuDao getFromApplicationContext(ApplicationContext ctx) {
        return (MenuDao) ctx.getBean("MenuDAO");
    }
}