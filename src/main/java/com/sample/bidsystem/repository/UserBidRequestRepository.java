package com.sample.bidsystem.repository;

import com.sample.bidsystem.entity.UserBidRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBidRequestRepository extends JpaRepository<UserBidRequest, Long> {
}
