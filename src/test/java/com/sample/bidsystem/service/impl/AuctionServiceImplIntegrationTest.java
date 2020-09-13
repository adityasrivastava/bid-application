package com.sample.bidsystem.service.impl;

import com.sample.bidsystem.entity.*;
import com.sample.bidsystem.repository.AuctionRepository;
import com.sample.bidsystem.repository.UserBidRepository;
import com.sample.bidsystem.service.AuctionService;
import com.sample.bidsystem.service.impl.config.AuctionConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(AuctionConfig.class)
class AuctionServiceImplIntegrationTest {

    @Autowired
    private AuctionService auctionService;

    @MockBean
    private AuctionRepository auctionRepository;

    @MockBean
    private UserBidRepository userBidRepository;

    private User buyerUser;

    @BeforeEach
    void setUp() {
        Role seller = new Role(RoleType.ROLE_SELLER);
        Role buyer = new Role(RoleType.ROLE_BUYER);

        Set<Role> sellerRoles = new HashSet<>();
        sellerRoles.add(seller);

        Set<Role> buyerRoles = new HashSet<>();
        buyerRoles.add(buyer);

        User sellerUser = new User("Test User", "sellertest", "sellertest@test.com", "test1234", sellerRoles);

        buyerUser = new User("Test User", "buyertest", "buyertest@test.com", "test1234", buyerRoles);

        Set userBids = new HashSet<>();

        Item item = new Item("Item1", UUID.randomUUID().toString(), sellerUser, 1);

        Auction auction = new Auction(item,2, 1, Status.RUNNING, userBids);

        UserBid userBid = new UserBid(3, buyerUser, auction);

        auction.getUserBids().add(userBid);

        auction.setId(1L);

        Mockito.when(auctionRepository.findById(1L))
            .thenReturn(Optional.of(auction));

        Mockito.when(userBidRepository.findFirstByAuctionOrderByCreatedDesc(auction))
                .thenReturn(Optional.of(userBid));

    }

    @Test
    public void whenBidExistForAAuction_bidForItem() throws Exception {
        final UserBid userBid = auctionService.bidForItem(1L, 5, buyerUser);
        Assert.assertNotNull(userBid);
    }


}