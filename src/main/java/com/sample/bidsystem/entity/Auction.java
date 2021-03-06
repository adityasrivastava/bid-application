package com.sample.bidsystem.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auctions")
public class Auction extends BaseEntity {

    @OneToOne
    private Item item;

    @Column(name = "base_price")
    private float basePrice;

    @Column(name = "step_rate")
    private float stepRate;

    @Column(name = "status")
    private Status status;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "user_bids_id")
    private Set<UserBid> userBids;

    public Auction() {
    }

    public Auction(Item item, float basePrice, float stepRate, Status status, Set<UserBid> userBids) {
        this.item = item;
        this.basePrice = basePrice;
        this.stepRate = stepRate;
        this.status = status;
        this.userBids = userBids;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public float getStepRate() {
        return stepRate;
    }

    public void setStepRate(float stepRate) {
        this.stepRate = stepRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<UserBid> getUserBids() {
        return userBids;
    }

    public void setUserBids(Set<UserBid> userBids) {
        this.userBids = userBids;
    }
}
