package uk.ac.nesc.idsd.action.logout;

import uk.ac.nesc.idsd.action.AbstractAction;

public class LogoutAction extends AbstractAction {
    private static final long serialVersionUID = -1356848455857855488L;

    public String logout() {
        log.info("Mode when logout is: " + mode);
        if ("icah".equalsIgnoreCase(mode)) {
            return "icah";
        } else {
            return "idsd";
        }
    }

}
