package com.sample.bidsystem.controller;

import com.sample.bidsystem.service.AuctionService;
import com.sample.bidsystem.service.impl.config.AuctionConfig;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AuctionController.class)
@Import(AuctionConfig.class)
class AuctionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuctionService auctionService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void bid() {

    }
}