package com.sample.bidsystem.service.impl;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.entity.UserBid;
import com.sample.bidsystem.exception.BidException;
import com.sample.bidsystem.repository.UserBidRepository;
import com.sample.bidsystem.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    UserBidRepository userBidRepository;

    @Override
    public UserBid bid(Auction auction, float price, User buyer) throws BidException {

        UserBid userBid;

        final Optional<UserBid> lastBid = userBidRepository.findByAuction();

        if(isPriceValidForExistingBid(auction, price, lastBid)){

            userBid = placeBid(price, buyer, auction);

        }else if(isPriceValidForFirstBid(auction, price, auction.getBasePrice())){

            userBid = placeBid(price, buyer, auction);

        }else {

            throw new BidException("Bid price is lower than requirements");

        }

        return userBid;
    }

    protected boolean isPriceValidForExistingBid(Auction auction, float price, Optional<UserBid> lastBid) {
        return lastBid.isPresent() &&
                price > lastBid.get().getBidAmount() + auction.getStepRate();
    }

    protected boolean isPriceValidForFirstBid(Auction auction, float price, float basePrice) {
        return price > (basePrice + auction.getStepRate());
    }

    protected UserBid placeBid(float price, User buyer, Auction auction) {
        UserBid userBid = new UserBid(price, buyer, auction);

        userBidRepository.save(userBid);
        return userBid;
    }
}
