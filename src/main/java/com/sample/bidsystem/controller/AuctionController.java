package com.sample.bidsystem.controller;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.Status;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.model.request.BidRequest;
import com.sample.bidsystem.repository.UserRepository;
import com.sample.bidsystem.service.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auctions")
public class AuctionController {

    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);

    @Autowired
    AuctionService auctionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @GetMapping
    public ResponseEntity<? extends List<Auction>> auctions(@RequestParam("status") Status status){

        logger.info("Get auction for status " + status);

        final List<Auction> allAuctions = auctionService.getAllAuctions(status);

        return new ResponseEntity<List<Auction>>( allAuctions, HttpStatus.OK);
    }

    @PostMapping(value = "/{auctionId}/{itemCode}/bid", consumes = {"application/json"})
    public ResponseEntity<String> bid(@PathVariable("auctionId") String auctionId,
                                      @PathVariable("itemCode") String itemCode,
                                      @Valid @RequestBody BidRequest bidRequest, Principal principal) throws Exception {

        logger.info("Submit bid request for auction id {}  and item code {} " , auctionId, itemCode);

        final Optional<User> buyer = userRepository.findByUsername(principal.getName());

        String requestId = auctionService.requestBid(Long.parseLong(auctionId), itemCode, bidRequest.getPrice(), buyer.get());

        return new ResponseEntity<String>(requestId, HttpStatus.OK);
    }

}
