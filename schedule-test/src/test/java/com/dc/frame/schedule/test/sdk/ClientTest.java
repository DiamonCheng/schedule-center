package com.dc.frame.schedule.test.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/20.
 */
@ContextConfiguration(locations = {"classpath:/spring-test-client.xml"})
public class ClientTest extends AbstractTestNGSpringContextTests{
    private static final Logger LOGGER= LoggerFactory.getLogger(ClientTest.class);
    private final Object lock=new Object();
    
    @Test
    public void test() throws InterruptedException {
        LOGGER.info("Test Start");
        synchronized (lock){
            lock.wait();
        }
    }
}
