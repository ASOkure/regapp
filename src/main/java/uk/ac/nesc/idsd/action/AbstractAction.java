package uk.ac.nesc.idsd.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AbstractAction extends ActionSupport implements Preparable {
    private static final long serialVersionUID = -6415500970180437871L;

    protected static final Log log = LogFactory.getLog(AbstractAction.class);
    /**
     * Action return Strings for various situations.
     */
    public static final String AUTHZ = "authz";
    public static final String CONSENT = "consent";
    //public static final String SESSION = "session";
    public static final String OWNER = "owner";
    public static final String EXPIRE = "expire";

    protected String mode;

    @Override
    public void prepare() {

    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
