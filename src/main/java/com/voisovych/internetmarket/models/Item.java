package com.voisovych.internetmarket.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Item {


    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String type;
    private String description;
    private int number;
    private float price;
    private int count;

    @CreationTimestamp
    @Temporal (TemporalType.DATE)
    private Date creationDate;

    @Override
    public String toString() {
        return count + " " + name + " " + description + " " +  number + " " + price + " " + type + " " + creationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creation_date) {
        this.creationDate = creation_date;
    }

    public void setId(long id) {
        this.id = id;
    }
}
