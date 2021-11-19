package app.dto;

public class Commisdata {

    private String client;
    private String group;
    private String EDRPO;
    private String cust;
    private String custOpenDate;
    private String branch;
    private String RM;
    private double interestMarginAssets;
    private double interestMarginLiabilities;
    private double commisionIncome;
    private double thereofDocInstruments;
    private double thereofLoanCommisions;
    private double thereofFXCommision;
    private double thereofOtherCommisions;
    private double totalIncome;
    private double treasuryIncome;
    private double avAssets;
    private double avLiabilities;
    private double ROA;

    public Commisdata() {
    }

    public Commisdata(String client, String group, String EDRPO, String cust, String custOpenDate, String branch, String RM, double interestMarginAssets, double interestMarginLiabilities, double commisionIncome, double thereofDocInstruments, double thereofLoanCommisions, double thereofFXCommision, double thereofOtherCommisions, double totalIncome, double treasuryIncome, double avAssets, double avLiabilities, double ROA) {
        this.client = client;
        this.group = group;
        this.EDRPO = EDRPO;
        this.cust = cust;
        this.custOpenDate = custOpenDate;
        this.branch = branch;
        this.RM = RM;
        this.interestMarginAssets = interestMarginAssets;
        this.interestMarginLiabilities = interestMarginLiabilities;
        this.commisionIncome = commisionIncome;
        this.thereofDocInstruments = thereofDocInstruments;
        this.thereofLoanCommisions = thereofLoanCommisions;
        this.thereofFXCommision = thereofFXCommision;
        this.thereofOtherCommisions = thereofOtherCommisions;
        this.totalIncome = totalIncome;
        this.treasuryIncome = treasuryIncome;
        this.avAssets = avAssets;
        this.avLiabilities = avLiabilities;
        this.ROA = ROA;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEDRPO() {
        return EDRPO;
    }

    public void setEDRPO(String EDRPO) {
        this.EDRPO = EDRPO;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public String getCustOpenDate() {
        return custOpenDate;
    }

    public void setCustOpenDate(String custOpenDate) {
        this.custOpenDate = custOpenDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRM() {
        return RM;
    }

    public void setRM(String RM) {
        this.RM = RM;
    }

    public double getInterestMarginAssets() {
        return interestMarginAssets;
    }

    public void setInterestMarginAssets(double interestMarginAssets) {
        this.interestMarginAssets = interestMarginAssets;
    }

    public double getInterestMarginLiabilities() {
        return interestMarginLiabilities;
    }

    public void setInterestMarginLiabilities(double interestMarginLiabilities) {
        this.interestMarginLiabilities = interestMarginLiabilities;
    }

    public double getCommisionIncome() {
        return commisionIncome;
    }

    public void setCommisionIncome(double commisionIncome) {
        this.commisionIncome = commisionIncome;
    }

    public double getThereofDocInstruments() {
        return thereofDocInstruments;
    }

    public void setThereofDocInstruments(double thereofDocInstruments) {
        this.thereofDocInstruments = thereofDocInstruments;
    }

    public double getThereofLoanCommisions() {
        return thereofLoanCommisions;
    }

    public void setThereofLoanCommisions(double thereofLoanCommisions) {
        this.thereofLoanCommisions = thereofLoanCommisions;
    }

    public double getThereofFXCommision() {
        return thereofFXCommision;
    }

    public void setThereofFXCommision(double thereofFXCommision) {
        this.thereofFXCommision = thereofFXCommision;
    }

    public double getThereofOtherCommisions() {
        return thereofOtherCommisions;
    }

    public void setThereofOtherCommisions(double thereofOtherCommisions) {
        this.thereofOtherCommisions = thereofOtherCommisions;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTreasuryIncome() {
        return treasuryIncome;
    }

    public void setTreasuryIncome(double treasuryIncome) {
        this.treasuryIncome = treasuryIncome;
    }

    public double getAvAssets() {
        return avAssets;
    }

    public void setAvAssets(double avAssets) {
        this.avAssets = avAssets;
    }

    public double getAvLiabilities() {
        return avLiabilities;
    }

    public void setAvLiabilities(double avLiabilities) {
        this.avLiabilities = avLiabilities;
    }

    public double getROA() {
        return ROA;
    }

    public void setROA(double ROA) {
        this.ROA = ROA;
    }


}
