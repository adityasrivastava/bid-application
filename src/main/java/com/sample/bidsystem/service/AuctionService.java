package com.sample.bidsystem.service;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.Status;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.entity.UserBid;
import com.sample.bidsystem.exception.BidException;

import java.util.List;

public interface AuctionService {

    public List<Auction> getAllAuctions(Status status);

    public UserBid bidForItem(Long auctionId, float price, User buyer) throws BidException;

    String requestBid(Long auctionId, String itemCode, float price, User buyer) throws BidException;
}
