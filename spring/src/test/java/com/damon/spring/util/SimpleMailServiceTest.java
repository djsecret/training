package com.damon.spring.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by damon on 16/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mail.xml")
public class SimpleMailServiceTest {

    @Resource
    private SimpleMailService simpleMailService;

    @Resource
    private MimeMailService mimeMailService;

    @Test
    public void sendNotificationMail() throws Exception {
        simpleMailService.sendNotificationMail("damon");
    }

    @Test
    public void sendMimeMail() {
        mimeMailService.sendNotificationMail("damon");
    }

}