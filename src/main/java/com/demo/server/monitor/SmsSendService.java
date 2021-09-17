package com.demo.server.monitor;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.demo.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: jky
 * @description: 发送短信服务类
 * @date: 2021/1/5 14:50
 */
@Component
public class SmsSendService {
    private static final Logger log = LoggerFactory.getLogger(SmsSendService.class);

    @Autowired
    private SmsSender smsSender;

    /**
     * 发送服务异常警告
     */
    @Async
    public void sendWarn(String appName) {
        try {
            log.info("发送服务异常警告信息......");
            appName = appName.toLowerCase().replace("demo.", "").replace("demo-", "");
            String[] userPhone = smsSender.getUserPhone();
            for (int i = 0; userPhone != null && i < userPhone.length; i++) {
                handleMsg(userPhone[i], appName, smsSender);
            }
        } catch (Exception e) {
            log.error("短信发送失败！", e);
        }
    }

    //短信发送
    private void handleMsg(String phone, String appName, SmsSender smsSender) throws Exception {
        log.info("短信服务发送-" + phone);
        DefaultProfile profile = DefaultProfile.getProfile(smsSender.getRegionId(), smsSender.getApiId(), smsSender.getApiKey());
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        CommonRequest request = new CommonRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        request.setDomain(smsSender.getUrl());
        request.setVersion("2017-05-25");//该API的版本号，格式为YYYY-MM-DD
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", smsSender.getRegionId());
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", smsSender.getSignName());
        request.putQueryParameter("TemplateCode", smsSender.getTemplateCode());
        String msgData = smsSender.getCityName() + appName;
        String strTime = DateUtils.dateToString(new Date());
        request.putQueryParameter("TemplateParam", "{\"name\":\"" + msgData + "\",\"time\":\"" + strTime + "\"}");
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        CommonResponse response = acsClient.getCommonResponse(request);
        log.info("短信服务返回：" + response.getData());
    }

}
