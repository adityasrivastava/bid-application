package com.sample.bidsystem.service.impl.config;

import com.sample.bidsystem.service.AuctionService;
import com.sample.bidsystem.service.impl.AuctionServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AuctionConfig {
    @Bean
    public AuctionService auctionService(){
        return new AuctionServiceImpl();
    }
}
