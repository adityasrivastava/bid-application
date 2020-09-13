package com.sample.bidsystem.service.impl;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.Status;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.entity.UserBid;
import com.sample.bidsystem.exception.BidException;
import com.sample.bidsystem.repository.AuctionRepository;
import com.sample.bidsystem.repository.UserBidRepository;
import com.sample.bidsystem.service.AuctionService;
import com.sample.bidsystem.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserBidRepository userBidRepository;

    @Autowired
    BidService bidService;

    @Override
    public List<Auction> getAllAuctions(Status status) {
        return auctionRepository.findByStatus(status);
    }

    @Override
    public void bidForItem(Long auctionId, float price, User buyer) throws BidException, Exception {

        final Optional<Auction> auction = auctionRepository.findById(auctionId);

        if(!auction.isPresent())
            throw new Exception("Auction ID not found");

        bidService.bid(auction.get(), price, buyer);

    }

}
