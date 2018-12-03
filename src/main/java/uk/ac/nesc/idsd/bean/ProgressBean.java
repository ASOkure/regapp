package uk.ac.nesc.idsd.bean;

public class ProgressBean {

    private String centre;
    private boolean consent;
    private boolean preparation;
    private boolean ethic;
    private long totalNumber;
    private long bioNumber;
    private long plasmaNumber;
    private long urineNumber;

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }

    public boolean isPreparation() {
        return preparation;
    }

    public void setPreparation(boolean preparation) {
        this.preparation = preparation;
    }

    public boolean isEthic() {
        return ethic;
    }

    public void setEthic(boolean ethic) {
        this.ethic = ethic;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public long getBioNumber() {
        return bioNumber;
    }

    public void setBioNumber(long bioNumber) {
        this.bioNumber = bioNumber;
    }

    public long getPlasmaNumber() {
        return plasmaNumber;
    }

    public void setPlasmaNumber(long plasmaNumber) {
        this.plasmaNumber = plasmaNumber;
    }

    public long getUrineNumber() {
        return urineNumber;
    }

    public void setUrineNumber(long urineNumber) {
        this.urineNumber = urineNumber;
    }

}
