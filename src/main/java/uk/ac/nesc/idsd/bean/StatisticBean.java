package uk.ac.nesc.idsd.bean;

public class StatisticBean {
    private String centre;
    private String country;
    private long local;
    private long national;
    private long european;
    private long international;
    private long total;
    private long sample;

    public StatisticBean(String centre, String country,
                         long local, long national, long european,
                         long international, long total, long sample) {
        this.centre = centre;
        this.country = country;
        this.local = local;
        this.national = national;
        this.european = european;
        this.international = international;
        this.total = total;
        this.sample = sample;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getLocal() {
        return local;
    }

    public void setLocal(long local) {
        this.local = local;
    }

    public long getNational() {
        return national;
    }

    public void setNational(long national) {
        this.national = national;
    }

    public long getSample() {
        return sample;
    }

    public void setSample(long sample) {
        this.sample = sample;
    }

    public long getEuropean() {
        return european;
    }

    public void setEuropean(long european) {
        this.european = european;
    }

    public long getInternational() {
        return international;
    }

    public void setInternational(long international) {
        this.international = international;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
