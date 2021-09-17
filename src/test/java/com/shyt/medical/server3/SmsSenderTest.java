//package com.shyt.medical.server3;
//
//import com.shyt.medical.server.monitor.SmsSendService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SmsSenderTest {
//
//    @Autowired
//    private SmsSendService smsSendService;
//
//    @Test
//    public void sendWarnTest(){
//        System.out.println("test.......");
//        smsSendService.sendWarn("NETWORK-BUS-REALBUS-APP");
//    }
//
//    public static void main(String[] args) {
//        String appName = "NETWORK.BUS.REAL-BUS-DISPATCH";
//        appName = appName.toLowerCase().replace("network.bus.", "").replace("network-bus-", "");
//        System.out.println(appName);
//    }
//}
