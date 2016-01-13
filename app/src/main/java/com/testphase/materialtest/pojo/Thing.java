package com.testphase.materialtest.pojo;

/**
 * Created by deea on 07/01/16.
 */
public class Thing {
    private long id;
    private String name;
    private String description;
    private String colour;
    private double price;
    private String category;


    public Thing() {

    }


    public Thing(long id,
                 String name,
                 String description,
                 String colour,
                 double price,
                 String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.price = price;
        this.category = category;
    }

    public Thing(String name,
                 String description,
                 String colour,
                 double price,
                 String category) {
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.price = price;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nProduct " + name +
                "\nColour " + colour +
                "\nType " + category +
                "\n";
    }



}
