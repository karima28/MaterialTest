package com.testphase.materialtest.pojo;

/**
 * Product class that contains all the product getters, setters and other required methods
 */
public class Product implements Comparable<Product> {
    private long id;
    private String name;
    private String shortdescription;
    private String longdescription;
    private Integer goodnessvalue;


    public Product() {

    }

    public Product(long id,
                   String name,
                   String shortdescription,
                   String longdescription,
                   Integer goodnessvalue) {
        this.id = id;
        this.name = name;
        this.shortdescription = shortdescription;
        this.longdescription = longdescription;
        this.goodnessvalue = goodnessvalue;
    }

    public Product(String name,
                   String shortdescription,
                   String longdescription,
                   Integer goodnessvalue) {
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

    public Integer getGoodnessValue() {
        return goodnessvalue;
    }

    public void setGoodnessValue(Integer goodnessvalue) {
        this.goodnessvalue = goodnessvalue;
    }

    public void updateGoodnessValue(double factor){
        int gValue = this.getGoodnessValue();
        gValue = (gValue * factor < 100) ? (int) (gValue * factor) : 100;

        this.setGoodnessValue(gValue);
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

    @Override
    public int compareTo(Product another) {
        if (this.getGoodnessValue() > another.getGoodnessValue())
            return -1;
        if (this.getGoodnessValue() < another.getGoodnessValue())
            return 1;
        return this.getName().compareTo(another.getName());
    }
}
