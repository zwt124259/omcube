package com.omcube.biz.contoller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class NacosConfiogContoller {


    @Value("${sharding.jdbc.config.props.sql.show}")
    private String  value;


    @GetMapping(value = "/get")
    @ResponseBody
    public String get() throws NacosException {
        return value;
    }




}
