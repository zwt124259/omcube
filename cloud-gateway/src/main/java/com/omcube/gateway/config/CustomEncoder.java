package com.omcube.gateway.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.form.spring.SpringFormEncoder;

import java.lang.reflect.Type;

public class CustomEncoder extends SpringFormEncoder {

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        super.encode(object, bodyType, template);
    }
}
