package app.dto;

public class Balance {
    private int tin;
    private String description;
    private String feature;
    private int d01_01_2021;
    private int d01_01_2020;

    public Balance() {
    }

    public Balance(int tin, String description, String feature, int d01_01_2021, int d01_01_2020) {
        this.tin = tin;
        this.description = description;
        this.feature = feature;
        this.d01_01_2021 = d01_01_2021;
        this.d01_01_2020 = d01_01_2020;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getD01_01_2021() {
        return d01_01_2021;
    }

    public void setD01_01_2021(int d01_01_2021) {
        this.d01_01_2021 = d01_01_2021;
    }

    public int getD01_01_2020() {
        return d01_01_2020;
    }

    public void setD01_01_2020(int d01_01_2020) {
        this.d01_01_2020 = d01_01_2020;
    }
}
