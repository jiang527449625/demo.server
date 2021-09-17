package com.demo.server.monitor;

import com.alibaba.fastjson.JSON;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: jky
 * @description: 服务状态监听
 * @date: 2021/1/5 14:50
 */
@Component
public class EurekaStateChangeListener {

    private static final Logger log = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    @Resource
    private SmsSendService smsSendService;

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        log.info("服务下线 eurekaInstanceCanceledEvent : " + JSON.toJSONString(event));
        smsSendService.sendWarn(event.getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("服务注册 module registered appName : " + instanceInfo.getAppName());
    }

    //@EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        String serverId = event.getServerId();
        log.info("服务续约 module renewed server_id : " + serverId);
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("注册中心启动...");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.info("Eureka Server启动...");
    }
}