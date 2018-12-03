package uk.ac.nesc.idsd.bean;

/**
 * Created by jiangj on 20/03/2016.
 */
public class CentreInfo {
    private String name;
    private String address;

    private String leadName;
    private String leadTel;
    private String leadEmail;
    private String leadUrl;

    private String centreSpiel;
    private String clinicDates;
    private String localResources;
    private String localEvents;
    private String studies;
    private String furtherInfo;

    private boolean isCentreLead;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadTel() {
        return leadTel;
    }

    public void setLeadTel(String leadTel) {
        this.leadTel = leadTel;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getLeadUrl() {
        return leadUrl;
    }

    public void setLeadUrl(String leadUrl) {
        this.leadUrl = leadUrl;
    }

    public String getCentreSpiel() {
        return centreSpiel;
    }

    public void setCentreSpiel(String centreSpiel) {
        this.centreSpiel = centreSpiel;
    }

    public String getClinicDates() {
        return clinicDates;
    }

    public void setClinicDates(String clinicDates) {
        this.clinicDates = clinicDates;
    }

    public String getLocalResources() {
        return localResources;
    }

    public void setLocalResources(String localResources) {
        this.localResources = localResources;
    }

    public String getLocalEvents() {
        return localEvents;
    }

    public void setLocalEvents(String localEvents) {
        this.localEvents = localEvents;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getFurtherInfo() {
        return furtherInfo;
    }

    public void setFurtherInfo(String furtherInfo) {
        this.furtherInfo = furtherInfo;
    }

    public boolean isCentreLead() {
        return isCentreLead;
    }

    public void setCentreLead(boolean centreLead) {
        isCentreLead = centreLead;
    }
}
