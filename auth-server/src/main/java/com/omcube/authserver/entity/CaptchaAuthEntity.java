package com.omcube.authserver.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CaptchaAuthEntity implements Serializable {


    private String username;

    private String password;

    private String captcha;



}
