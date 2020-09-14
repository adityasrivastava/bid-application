package com.sample.bidsystem.repository;

import com.sample.bidsystem.entity.Role;
import com.sample.bidsystem.entity.RoleType;
import com.sample.bidsystem.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryBuyerIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    private Set<Role> roles;

    @Before
    public void setUp(){

        Role buyer = new Role(RoleType.ROLE_BUYER);

        buyer = entityManager.persist(buyer);
        entityManager.flush();

        roles = new HashSet<>();
        roles.add(buyer);

        User user = new User("Test User", "buyertest", "buyertest@test.com", "test1234", roles);
        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnSellerUser(){
        final Optional<User> buyertest = userRepository.findByUsername("buyertest");

        Assert.assertEquals(buyertest.get().getUsername(), "buyertest");
        Assert.assertTrue(buyertest.get().getRoles().equals(roles));
    }
}
