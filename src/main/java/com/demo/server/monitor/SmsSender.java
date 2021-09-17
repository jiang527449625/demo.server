package com.demo.server.monitor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: jky
 * @description: 短信发送实体类
 * @date: 2021/1/6 9:52
 */
@Data
@Component
@ConfigurationProperties(prefix = "monitor.sms")
public class SmsSender {
    //服务器地址
    private String regionId;
    //短信平台url
    private String url;
    //AccessKey ID
    private String apiId;
    //AccessKey Secret
    private String apiKey;
    //城市名称
    private String cityName;
    //签名名称
    private String signName;
    //模板code
    private String templateCode;
    //接收短信手机号码
    private String[] userPhone;
}
