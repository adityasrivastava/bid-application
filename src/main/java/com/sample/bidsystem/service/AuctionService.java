package com.sample.bidsystem.service;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.Status;
import com.sample.bidsystem.entity.User;

import java.util.List;

public interface AuctionService {

    public List<Auction> getAllAuctions(Status status);

    public void bidForItem(Long auctionId, float price, User buyer) throws Exception;
}
