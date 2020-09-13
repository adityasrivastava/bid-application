package com.sample.bidsystem.repository;

import com.sample.bidsystem.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserBidRepositoryIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserBidRepository userBidRepository;

    private Set<UserBid> userBids;

    private Auction auction;

    @Before
    public void setUp(){

        Role seller = new Role(RoleType.ROLE_SELLER);
        Role buyer = new Role(RoleType.ROLE_BUYER);

        seller = entityManager.persist(seller);
        buyer = entityManager.persist(buyer);
        entityManager.flush();

        Set<Role> sellerRoles = new HashSet<>();
        sellerRoles.add(seller);

        Set<Role> buyerRoles = new HashSet<>();
        buyerRoles.add(buyer);

        User sellerUser = new User("Test User", "sellertest", "sellertest@test.com", "test1234", sellerRoles);
        sellerUser = entityManager.persist(sellerUser);
        entityManager.flush();

        User buyerUser = new User("Test User", "buyertest", "buyertest@test.com", "test1234", buyerRoles);
        buyerUser = entityManager.persist(buyerUser);
        entityManager.flush();

        userBids = new HashSet<>();

        Item item = new Item("Item1", UUID.randomUUID().toString(), sellerUser, 1);
        item = entityManager.persist(item);
        entityManager.flush();

        auction = new Auction(item,2, 1, Status.RUNNING, userBids);
        auction = entityManager.persist(auction);
        entityManager.flush();

        UserBid userBid = new UserBid(3, buyerUser, auction);
//        UserBid userBid1 = new UserBid(5, buyerUser, auction);

        userBid = entityManager.persist(userBid);
//        userBid1 = entityManager.persist(userBid1);
        entityManager.flush();

    }

    @Test
    public void whenFindByStatus_userBidIsNotEmpty(){

        final Optional<UserBid> found = userBidRepository.findFirstByAuctionOrderByCreatedDesc(auction);

        Assert.assertTrue(found.isPresent() );

    }

}
