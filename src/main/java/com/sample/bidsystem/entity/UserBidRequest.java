package com.sample.bidsystem.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_bids_request")
public class UserBidRequest extends BaseEntity {

    @OneToOne
    private Auction auction;

    private String bidRequestId;

    private UserBidRequestStatus status;

    private String message;

    public UserBidRequest() {
    }

    public UserBidRequest(Auction auction, String bidRequestId, UserBidRequestStatus status, String message) {
        this.auction = auction;
        this.bidRequestId = bidRequestId;
        this.status = status;
        this.message = message;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public String getBidRequestId() {
        return bidRequestId;
    }

    public void setBidRequestId(String bidRequestId) {
        this.bidRequestId = bidRequestId;
    }

    public UserBidRequestStatus getStatus() {
        return status;
    }

    public void setStatus(UserBidRequestStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
