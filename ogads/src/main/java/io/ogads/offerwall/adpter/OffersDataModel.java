package io.ogads.offerwall.adpter;

public class OffersDataModel {
    private String adcopy;
    private String dsc;
    private String link;
    private String names;
    private String packeg;
    private String picture;
    private int payout;
    private String type;

    public String getNames() {
        return this.names;
    }

    public void setNames(String str) {
        this.names = str;
    }

    public String getPackeg() {
        return this.packeg;
    }

    public void setPackeg(String str) {
        this.packeg = str;
    }

    public String getLink() {
        return this.link;
    }


    public void setLink(String str) {
        this.link = str;
    }

    public String getAdcopy() {
        return this.adcopy;
    }

    public void setAdcopy(String str) {
        this.adcopy = str;
    }

    public String getDsc() {
        return this.dsc;
    }

    public void setDsc(String str) {
        this.dsc = str;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public int getPayout() {
        return this.payout;
    }

    public void setPayout(int i) {
        this.payout = i;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}