package com.sample.bidsystem.repository;

import com.sample.bidsystem.entity.UserBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBidRepository extends JpaRepository<UserBid, Long> {

    Optional<UserBid> findByAuction();

}
