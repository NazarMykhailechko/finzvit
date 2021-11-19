package app.dto;

import java.util.List;


public class CommisDetails {

    private String client;
    private String edrpo;
    private String cust;
    private String featureukr;
    private String sort;
    private List<Dates> dates;

    public CommisDetails() {
    }

    public CommisDetails(String client, String edrpo, String cust, String featureukr, String sort, List<Dates> dates) {
        this.client = client;
        this.edrpo = edrpo;
        this.cust = cust;
        this.featureukr = featureukr;
        this.sort = sort;
        this.dates = dates;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEdrpo() {
        return edrpo;
    }

    public void setEdrpo(String edrpo) {
        this.edrpo = edrpo;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public String getFeatureukr() {
        return featureukr;
    }

    public void setFeatureukr(String featureukr) {
        this.featureukr = featureukr;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<Dates> getDates() {
        return dates;
    }

    public void setDates(List<Dates> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "CommisDetails{" +
                "client='" + client + '\'' +
                ", edrpo='" + edrpo + '\'' +
                ", cust='" + cust + '\'' +
                ", featureukr='" + featureukr + '\'' +
                ", sort='" + sort + '\'' +
                ", dates=" + dates +
                '}';
    }
}
