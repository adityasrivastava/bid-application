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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuctionRepositoryIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AuctionRepository auctionRepository;

    private Set<UserBid> userBids;

    private Auction auction;

    /**
     * TODO - can done using sql script to pre populate with test data for JUnit will require less build time
     */
    @Before
    public void setUp(){

        Role seller = new Role(RoleType.ROLE_SELLER);

        seller = entityManager.persist(seller);
        entityManager.flush();

        Set<Role> roles = new HashSet<>();
        roles.add(seller);

        User user = new User("Test User", "sellertest", "sellertest@test.com", "test1234", roles);
        user = entityManager.persist(user);
        entityManager.flush();

        userBids = new HashSet<>();

        Item item = new Item("Item1", UUID.randomUUID().toString(), user, 1);
        item = entityManager.persist(item);
        entityManager.flush();

        auction = new Auction(item,2, 1, Status.RUNNING, userBids);
        auction = entityManager.persist(auction);
        entityManager.flush();


    }

    @Test
    public void whenFindByStatus_auctionIsNotEmpty(){

        final List<Auction> auctions = auctionRepository.findByStatus(Status.RUNNING);

        Assert.assertEquals(auctions.size(), 1);

        final Auction auction = auctions.get(0);

        Assert.assertNotNull(auction.getItem());
    }
}
