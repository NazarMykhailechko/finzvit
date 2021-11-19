package app.dto;

public class Clients {

    private String pr;
    private String client_name;
    private String client_name_short;
    private String full_address;
    private int employee_num;
    private String kved;
    private String kved_name;
    private int tender_num;
    private int tender_sum;
    private double revenue_uah;
    private double forex_per_year_usd;
    private double loan_value_uah;
    private double passive_value_uah;
    private String person_details;
    private String last_date_contact;
    private String last_client_result;
    private String client_history;
    private String status;
    private String comments;
    private String login;

    public Clients() {
    }

    public Clients(String pr, String client_name, String client_name_short, String full_address, int employee_num, String kved, String kved_name, int tender_num, int tender_sum, double revenue_uah, double forex_per_year_usd, double loan_value_uah, double passive_value_uah, String person_details, String last_date_contact, String last_client_result, String client_history, String status, String comments, String login) {
        this.pr = pr;
        this.client_name = client_name;
        this.client_name_short = client_name_short;
        this.full_address = full_address;
        this.employee_num = employee_num;
        this.kved = kved;
        this.kved_name = kved_name;
        this.tender_num = tender_num;
        this.tender_sum = tender_sum;
        this.revenue_uah = revenue_uah;
        this.forex_per_year_usd = forex_per_year_usd;
        this.loan_value_uah = loan_value_uah;
        this.passive_value_uah = passive_value_uah;
        this.person_details = person_details;
        this.last_date_contact = last_date_contact;
        this.last_client_result = last_client_result;
        this.client_history = client_history;
        this.status = status;
        this.comments = comments;
        this.login = login;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_name_short() {
        return client_name_short;
    }

    public void setClient_name_short(String client_name_short) {
        this.client_name_short = client_name_short;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public int getEmployee_num() {
        return employee_num;
    }

    public void setEmployee_num(int employee_num) {
        this.employee_num = employee_num;
    }

    public String getKved() {
        return kved;
    }

    public void setKved(String kved) {
        this.kved = kved;
    }

    public String getKved_name() {
        return kved_name;
    }

    public void setKved_name(String kved_name) {
        this.kved_name = kved_name;
    }

    public int getTender_num() {
        return tender_num;
    }

    public void setTender_num(int tender_num) {
        this.tender_num = tender_num;
    }

    public int getTender_sum() {
        return tender_sum;
    }

    public void setTender_sum(int tender_sum) {
        this.tender_sum = tender_sum;
    }

    public double getRevenue_uah() {
        return revenue_uah;
    }

    public void setRevenue_uah(double revenue_uah) {
        this.revenue_uah = revenue_uah;
    }

    public double getForex_per_year_usd() {
        return forex_per_year_usd;
    }

    public void setForex_per_year_usd(double forex_per_year_usd) {
        this.forex_per_year_usd = forex_per_year_usd;
    }

    public double getLoan_value_uah() {
        return loan_value_uah;
    }

    public void setLoan_value_uah(double loan_value_uah) {
        this.loan_value_uah = loan_value_uah;
    }

    public double getPassive_value_uah() {
        return passive_value_uah;
    }

    public void setPassive_value_uah(double passive_value_uah) {
        this.passive_value_uah = passive_value_uah;
    }

    public String getPerson_details() {
        return person_details;
    }

    public void setPerson_details(String person_details) {
        this.person_details = person_details;
    }

    public String getLast_date_contact() {
        return last_date_contact;
    }

    public void setLast_date_contact(String last_date_contact) {
        this.last_date_contact = last_date_contact;
    }

    public String getLast_client_result() {
        return last_client_result;
    }

    public void setLast_client_result(String last_client_result) {
        this.last_client_result = last_client_result;
    }

    public String getClient_history() {
        return client_history;
    }

    public void setClient_history(String client_history) {
        this.client_history = client_history;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
