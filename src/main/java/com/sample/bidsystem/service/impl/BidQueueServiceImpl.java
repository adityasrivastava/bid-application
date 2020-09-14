package com.sample.bidsystem.service.impl;

import com.sample.bidsystem.entity.*;
import com.sample.bidsystem.exception.BidException;
import com.sample.bidsystem.model.request.QueueBidMessage;
import com.sample.bidsystem.repository.AuctionRepository;
import com.sample.bidsystem.repository.UserBidRequestRepository;
import com.sample.bidsystem.repository.UserRepository;
import com.sample.bidsystem.service.AuctionService;
import com.sample.bidsystem.service.BidQueueService;
import com.sample.bidsystem.service.BidService;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Optional;

@Service
public class BidQueueServiceImpl implements BidQueueService {

    @Autowired
    AuctionService auctionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserBidRequestRepository userBidRequestRepository;

    /**
     * jms queue consumer for bid request
     *
     * @param content
     * @throws JMSException
     */
    @JmsListener(destination = "local.inmemory.queue")
    public void onProcessBidMessage(Message content) throws JMSException {

        ActiveMQMapMessage body = (ActiveMQMapMessage) content;

        final Long auctionId = body.getLong("auctionId");

        final Optional<Auction> foundAuction = auctionRepository.findById(auctionId);
        final String requestId = body.getString("requestId");

        try {

            final float price = body.getFloat("price");
            final Long buyerId = body.getLong("buyerId");
            final String itemCode = body.getString("itemCode");

            final Optional<User> buyer = userRepository.findById(buyerId);

            if(!foundAuction.get().getItem().getItemCode().equals(itemCode)){
                throw new BidException("Bid request could not be processed due argument mismatch");
            }

            auctionService.bidForItem(auctionId, price, buyer.get());

            UserBidRequest bidRequest = new UserBidRequest(foundAuction.get(), requestId, UserBidRequestStatus.SUCCESS ,"" );
            userBidRequestRepository.save(bidRequest);

        } catch (BidException ex) {
            UserBidRequest bidRequest = new UserBidRequest(foundAuction.get(), requestId, UserBidRequestStatus.FAILED, ex.getMessage() );
            userBidRequestRepository.save(bidRequest);
        }
    }
}
