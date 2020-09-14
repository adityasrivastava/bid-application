package com.sample.bidsystem.service;

import com.sample.bidsystem.model.request.QueueBidMessage;

import javax.jms.JMSException;
import javax.jms.Message;

public interface BidQueueService {
    public void onProcessBidMessage(Message content) throws Exception;
}
