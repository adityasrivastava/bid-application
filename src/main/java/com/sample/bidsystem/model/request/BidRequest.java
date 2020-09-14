package com.sample.bidsystem.model.request;

import com.sample.bidsystem.entity.User;

import javax.validation.constraints.NotNull;

public class BidRequest {

    @NotNull
    private Long auctionId;

    @NotNull
    private float price;

    @NotNull
    private String itemCode;

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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
