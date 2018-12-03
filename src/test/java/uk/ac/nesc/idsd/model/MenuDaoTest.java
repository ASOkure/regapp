package uk.ac.nesc.idsd.model;

import org.hibernate.NonUniqueResultException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jiangj on 21/03/2015.
 */
public class MenuDaoTest extends AbstractTest {
    @Autowired
    private MenuDao menuDao;
    private static String MENU_NAME_VALUE = "YesNo";

    @Test
    public void testAllFindByProperty() {
        createRecordsWithSameMenuName();
        List<Menu> foundMenus = menuDao.findByMenuName(MENU_NAME_VALUE);
        log.info(foundMenus);
        assertNotNull(foundMenus);
        log.info("Found menu size = " + foundMenus.size());
        assertTrue("Found Menu list size must be > 1", foundMenus.size() > 1);
        for (Menu m : foundMenus) {
            assertEquals("Menu name", MENU_NAME_VALUE, m.getMenuName());
        }
    }

    @Test
    public void testAllFindByPropertyByRange() {
        createRecordsWithSameMenuName();
        List<Menu> foundMenus = menuDao.findByProperty(MenuDao.MENU_NAME, MENU_NAME_VALUE, 1, 2);
        log.info(foundMenus);
        assertNotNull(foundMenus);
        log.info("Found menu size = " + foundMenus.size());
        assertTrue("Found Menu list size must be > 1", foundMenus.size() > 1);
        for (Menu m : foundMenus) {
            assertEquals("Menu name", MENU_NAME_VALUE, m.getMenuName());
        }
    }

    @Test(expected = NonUniqueResultException.class)
    public void testFindUniqueByPropertyWithException() {
        createRecordsWithSameMenuName();
        Menu foundMenu = menuDao.findUniqueByProperty(MenuDao.MENU_NAME, MENU_NAME_VALUE);
        log.info(foundMenu);
        assertEquals("Menu name", MENU_NAME_VALUE, foundMenu.getMenuName());
    }

    @Test
    public void testFindUniqueByProperty() {
        Menu foundMenu = menuDao.findUniqueByProperty(MenuDao.MENU_NAME, MENU_NAME_VALUE);
        log.info(foundMenu);
        assertEquals("Menu name", MENU_NAME_VALUE, foundMenu.getMenuName());
    }

    private void createRecordsWithSameMenuName() {
        createMenuWithName(MENU_NAME_VALUE);
        createMenuWithName(MENU_NAME_VALUE);
    }

    private void createMenuWithName(String menuName) {
        Menu menu1 = new Menu();
        menu1.setMenuName(menuName);
        menuDao.save(menu1);
    }
}
