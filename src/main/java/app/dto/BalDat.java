package app.dto;

import com.google.gson.JsonElement;

public class BalDat {

    private String tin;
    private String fullname;
    private String article;
    private String row;
    private double y01_01_2021;
    private double y01_01_2020;

    public BalDat() {
    }

    public BalDat(String tin, String fullname, String article, String row, double y01_01_2021, double y01_01_2020) {
        this.tin = tin;
        this.fullname = fullname;
        this.article = article;
        this.row = row;
        this.y01_01_2021 = y01_01_2021;
        this.y01_01_2020 = y01_01_2020;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public double getY01_01_2021() {
        return y01_01_2021;
    }

    public void setY01_01_2021(double y01_01_2021) {
        this.y01_01_2021 = y01_01_2021;
    }

    public double getY01_01_2020() {
        return y01_01_2020;
    }

    public void setY01_01_2020(double y01_01_2020) {
        this.y01_01_2020 = y01_01_2020;
    }

    @Override
    public String toString() {
        return "BalDat{" +
                "tin='" + tin + '\'' +
                ", fullname='" + fullname + '\'' +
                ", article='" + article + '\'' +
                ", row='" + row + '\'' +
                ", y01_01_2021=" + y01_01_2021 +
                ", y01_01_2020=" + y01_01_2020 +
                '}';
    }
}
