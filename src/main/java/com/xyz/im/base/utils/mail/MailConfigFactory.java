package com.xyz.im.base.utils.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MailConfigFactory {

    @Autowired
    private MailConfig mailConfig;

    private static MailConfigFactory factory;

    @PostConstruct
    public void init() {
        factory = this;
    }

    public static MailConfig getConfig() {
        return factory.mailConfig.getMailConfig();
    }
}
