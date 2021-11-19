package app.dto;

public class ManagerCommisData {
    private String rm;
    private int sort;
    private String featureukr;
    private double averageperyear;
    private double currmonth;
    private double prevmonth;
    private double delta;

    public ManagerCommisData() {
    }

    public ManagerCommisData(String rm, int sort, String featureukr, double averageperyear, double currmonth, double prevmonth, double delta) {
        this.rm = rm;
        this.sort = sort;
        this.featureukr = featureukr;
        this.averageperyear = averageperyear;
        this.currmonth = currmonth;
        this.prevmonth = prevmonth;
        this.delta = delta;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getFeatureukr() {
        return featureukr;
    }

    public void setFeatureukr(String featureukr) {
        this.featureukr = featureukr;
    }

    public double getAverageperyear() {
        return averageperyear;
    }

    public void setAverageperyear(double averageperyear) {
        this.averageperyear = averageperyear;
    }

    public double getCurrmonth() {
        return currmonth;
    }

    public void setCurrmonth(double currmonth) {
        this.currmonth = currmonth;
    }

    public double getPrevmonth() {
        return prevmonth;
    }

    public void setPrevmonth(double prevmonth) {
        this.prevmonth = prevmonth;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
}
