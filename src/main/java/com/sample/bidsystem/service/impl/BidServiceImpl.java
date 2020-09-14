package com.sample.bidsystem.service.impl;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.entity.UserBid;
import com.sample.bidsystem.exception.BidException;
import com.sample.bidsystem.repository.UserBidRepository;
import com.sample.bidsystem.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    private static final Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);

    @Autowired
    UserBidRepository userBidRepository;

    /**
     * Make bid request and valid price check.
     * @param auction
     * @param price
     * @param buyer
     * @return
     * @throws BidException
     */

    @Override
    public UserBid bid(Auction auction, float price, User buyer) throws BidException {

        logger.info("Check if bid request is valid for price {}", price);

        if(isBidValid(auction, price)){

            return placeBid(price, buyer, auction);

        }

        throw new BidException("Bid is not valid.");

    }

    /**
     * Is bid valid
     * 1. If price is existing bids then ( last bid price + step rate )  < bid_price_current
     * 2. If price for no existing bids then ( base price + step rate ) < bid_price_current
     *
     * @param auction
     * @param price
     * @return
     */
    @Override
    public boolean isBidValid(Auction auction, float price){

        boolean bidValid = false;

        final Optional<UserBid> lastBid = userBidRepository.findFirstByAuctionOrderByCreatedDesc(auction);

        if(isPriceValidForExistingBid(auction, price, lastBid)){

            bidValid = true;

        }else if(isPriceValidForFirstBid(auction, price, lastBid)){

            bidValid = true;

        } else {

        }

        return bidValid;
    }

    protected boolean isPriceValidForExistingBid(Auction auction, float price, Optional<UserBid> lastBid) {
        return lastBid.isPresent() &&
                    price > lastBid.get().getBidAmount() + auction.getStepRate();
    }

    protected boolean isPriceValidForFirstBid(Auction auction, float price, Optional<UserBid> lastBid) {
        return (!lastBid.isPresent()
                    && price > (auction.getBasePrice() + auction.getStepRate()));
    }

    protected UserBid placeBid(float price, User buyer, Auction auction) {

        logger.info("Placing user bid");

        UserBid userBid = new UserBid(price, buyer, auction);

        userBidRepository.save(userBid);
        return userBid;
    }
}
