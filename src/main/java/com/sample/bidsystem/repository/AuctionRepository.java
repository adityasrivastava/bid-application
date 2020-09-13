package com.sample.bidsystem.repository;

import com.sample.bidsystem.entity.Auction;
import com.sample.bidsystem.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findByStatus(Status status);
}
