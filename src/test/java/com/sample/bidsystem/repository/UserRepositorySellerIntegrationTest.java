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
public class UserRepositorySellerIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private Set<Role> roles;

    @Before
    public void setUp(){

        Role seller = new Role(RoleType.ROLE_SELLER);

        seller = entityManager.persist(seller);
        entityManager.flush();

        roles = new HashSet<>();
        roles.add(seller);

        User user = new User("Test User", "sellertest", "sellertest@test.com", "test1234", roles);
        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnSellerUser(){
        final Optional<User> sellertest = userRepository.findByUsername("sellertest");

        Assert.assertEquals(sellertest.get().getUsername(), "sellertest");
        Assert.assertTrue(sellertest.get().getRoles().equals(roles));
    }
}
