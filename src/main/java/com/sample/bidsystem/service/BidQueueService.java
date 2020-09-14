package com.sample.bidsystem.service;

import javax.jms.Message;

public interface BidQueueService {
    public void onProcessBidMessage(Message content) throws Exception;
}
