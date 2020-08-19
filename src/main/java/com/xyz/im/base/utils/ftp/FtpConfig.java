package com.xyz.im.base.utils.ftp;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FtpConfig {

//    @Value("${ftp.host}")
    private String host;

//    @Value("${ftp.port}")
    private int port;

//    @Value("${ftp.username}")
    private String username;

//    @Value("${ftp.password}")
    private String password;

//    @Value("${ftp.timeout}")
    private int timeout;

    private FtpConfig() {

    }

    public FtpConfig getFtpConfig() {
        return this;
    }

}
