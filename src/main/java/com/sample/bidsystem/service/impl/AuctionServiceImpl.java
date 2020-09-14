package com.sample.bidsystem.service.impl;

import com.sample.bidsystem.controller.AuctionController;
import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.Status;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.entity.UserBid;
import com.sample.bidsystem.exception.BidException;
import com.sample.bidsystem.repository.AuctionRepository;
import com.sample.bidsystem.repository.UserBidRepository;
import com.sample.bidsystem.service.AuctionService;
import com.sample.bidsystem.service.BidService;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.*;

@Service
public class AuctionServiceImpl implements AuctionService {

    private static final Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserBidRepository userBidRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    BidService bidService;

    @Override
    public List<Auction> getAllAuctions(Status status) {

        logger.info("Get all auctions for status {}" , status);

        return auctionRepository.findByStatus(status);
    }

    @Override
    public UserBid bidForItem(Long auctionId, float price, User buyer) throws BidException {

        logger.info("bid for item auction id {} " , auctionId);

        final Optional<Auction> auction = auctionRepository.findById(auctionId);

        if(!auction.isPresent())
            throw new BidException("Auction ID not found");

        return bidService.bid(auction.get(), price, buyer);

    }

    @Override
    public String requestBid(Long auctionId, String itemCode, float price, User buyer) throws BidException {

        logger.info("Bid request for auction id {} and item code {} for price {} ", auctionId, itemCode, price);

        final String requestId = UUID.randomUUID().toString();

        final Optional<Auction> found = auctionRepository.findById(auctionId);

        if(!found.isPresent())
            throw new BidException("Auction id is not valid");

        if(!bidService.isBidValid(found.get(), price))
            throw new BidException("Auction bid price is not valid");

        Map<String, String> payload = new HashMap<>();
        payload.put("auctionId", auctionId.toString());
        payload.put("price", ""+price);
        payload.put("buyerId" , buyer.getId().toString());
        payload.put("requestId" , UUID.randomUUID().toString());
        payload.put("itemCode" , itemCode);

        jmsTemplate.convertAndSend(queue, payload);

        return requestId;
    }

}
