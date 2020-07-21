package com.omcube.biz.contoller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.omcube.comm.annotation.Conductor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class SentinelTestContoller {



    @GetMapping("/get")
    @Conductor
    @SentinelResource(value = "protected-resource")
    public String say(){

        return "hello world";
    }



}
