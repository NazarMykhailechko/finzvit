package app.dto;

public class Status {

    private String x;
    private int value;

    public Status() {
    }

    public Status(String x, int value) {
        this.x = x;
        this.value = value;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
