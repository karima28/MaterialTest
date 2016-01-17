package com.testphase.materialtest.pojo;

/**
 * Created by deea on 07/01/16.
 */
public class Thing {
    private long id;
    private String name;
    private String shortdescription;
    private String longdescription;
    private double goodnessvalue;


    public Thing() {

    }


    public Thing(long id,
                 String name,
                 String shortdescription,
                 String longdescription,
                 double goodnessvalue) {
        this.id = id;
        this.name = name;
        this.shortdescription = shortdescription;
        this.longdescription = longdescription;
        this.goodnessvalue = goodnessvalue;
    }

    public Thing(String name,
                 String shortdescription,
                 String longdescription,
                 double goodnessvalue) {
        this.name = name;
        this.shortdescription = shortdescription;
        this.longdescription = longdescription;
        this.goodnessvalue = goodnessvalue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortdescription;
    }

    public void setShortDescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public double getGoodnessValue() {
        return goodnessvalue;
    }

    public void setGoodnessvalue(double goodnessvalue) {
        this.goodnessvalue = goodnessvalue;
    }


    @Override
    public String toString() {
        return "\nID: " + id +
                "\nName " + name +
                "\nShort Description " + shortdescription +
                "\nLong Description " + longdescription +
                "\nGoodnessvalue " + goodnessvalue +
                "\n";
    }



}
