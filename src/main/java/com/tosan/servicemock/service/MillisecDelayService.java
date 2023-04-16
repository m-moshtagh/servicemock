package com.tosan.servicemock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MillisecDelayService implements DelayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MillisecDelayService.class);

    @Override
    public void wait(int milliseconds) {
        if (milliseconds > 100) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("An uncounted error happened during thread sleep");
                Thread.currentThread().interrupt();
            }
        }
    }

}
