package com.sample.bidsystem.controller;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.Status;
import com.sample.bidsystem.entity.User;
import com.sample.bidsystem.repository.UserRepository;
import com.sample.bidsystem.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auctions")
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<? extends List<Auction>> auctions(@RequestParam("status") Status status){

//        Status status1 = Status.valueOf(status);

        final List<Auction> allAuctions = auctionService.getAllAuctions(status);

        return new ResponseEntity<List<Auction>>( allAuctions, HttpStatus.OK);
    }

    @RequestMapping(value = "/{itemCode}/bid", consumes = {"application/json"})
    public void bid(@PathVariable("itemCode") String auctionId, Principal principal) throws Exception {

        final Optional<User> buyer = userRepository.findByUsername(principal.getName());

        auctionService.bidForItem(Long.parseLong(auctionId), 5, buyer.get());
    }

}
