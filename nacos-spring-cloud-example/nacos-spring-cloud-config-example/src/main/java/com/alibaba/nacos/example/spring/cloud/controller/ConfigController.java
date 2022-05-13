package com.alibaba.nacos.example.spring.cloud.controller;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cloud.nacos.NacosConfigManager;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @Value("${useLocalKey:test}")
    private String useLocalKey;


    @Autowired
    private NacosConfigManager nacosConfigManager;

    /**
     * http://localhost:8080/config/get
     */
    @GetMapping("/get")
    public boolean get() {
        return useLocalCache;
    }


    @GetMapping("/getConfigNamespace")
    public Object getKey() {
        ConfigService configService = nacosConfigManager.getConfigService();
        Object namespace = ReflectUtil.getFieldValue(configService, "namespace");

        System.out.println("-------Namespace-------");
        System.out.println(namespace);
        System.out.println("-------Namespace-------");
        return namespace;
    }
}