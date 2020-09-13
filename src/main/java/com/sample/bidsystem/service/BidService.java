package com.sample.bidsystem.service;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.entity.UserBid;
import com.sample.bidsystem.exception.BidException;

public interface BidService {

    public UserBid bid(Auction auction, float price, User buyer) throws BidException;

}
