package app.dto;

public class Dates {
    private String date;

    public Dates() {
    }

    public Dates(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dates{" +
                "date='" + date + '\'' +
                '}';
    }
}
