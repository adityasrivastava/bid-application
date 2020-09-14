package com.sample.bidsystem.model.request;

import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.model.QueueMessage;

import java.io.Serializable;

public class QueueBidMessage implements QueueMessage, Serializable {

    private Long auctionId;

    private float price;

    private User buyer;

    public QueueBidMessage(Long auctionId, float price, User buyer) {
        this.auctionId = auctionId;
        this.price = price;
        this.buyer = buyer;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "QueueBidMessage{" +
                "auctionId=" + auctionId +
                ", price=" + price +
                ", buyer=" + buyer +
                '}';
    }
}
