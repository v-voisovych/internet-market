package com.voisovych.internetmarket.model;


import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    private String type;
    private String description;
    private Integer number;
    private Float price;
    private Integer count;

    @CreationTimestamp
    @Temporal (TemporalType.DATE)
    private Date creationDate;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", count=" + count +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Objects.equals(name, item.name) && Objects.equals(type, item.type) && Objects.equals(description, item.description) && Objects.equals(number, item.number) && Objects.equals(price, item.price) && Objects.equals(count, item.count) && Objects.equals(creationDate, item.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description, number, price, count, creationDate);
    }

    public Item() {
    }

    public Item(@NotNull String name) {
        this.name = name;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creation_date) {
        this.creationDate = creation_date;
    }

}
