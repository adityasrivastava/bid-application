package com.sample.bidsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_bids")
public class UserBid extends BaseEntity {

    @Column(name = "bid_amount")
    private float bidAmount;

    @OneToOne
    private User buyer;

    @ManyToOne
    @JsonIgnore
    private Auction auction;

    public UserBid() {
    }

    public UserBid(float bidAmount, User buyer, Auction auction) {
        this.bidAmount = bidAmount;
        this.buyer = buyer;
        this.auction = auction;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
