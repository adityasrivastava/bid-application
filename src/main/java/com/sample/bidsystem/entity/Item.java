package com.sample.bidsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "item_code")
    private String itemCode;

    @OneToOne
    private User seller;

    @Column(name = "price")
    private float price;

    public Item() {
    }

    public Item(String name, String itemCode, User seller, float price) {
        this.name = name;
        this.itemCode = itemCode;
        this.seller = seller;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
