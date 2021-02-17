package com.product.screen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.product.screen.entity.*;
import com.product.screen.tool.XmlUtil;
import com.product.screen.wsInteract.ServiceInteract;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ScreenNavigator {
    @Autowired
    private XmlUtil xmlUtil;
    @Autowired
    private ServiceInteract serviceInteract;
    @Autowired
    Navigator navigator;

//    private String serverCfgDir = "D:\\CMHIT\\ScreenControl\\HXF6\\";
//    private String serverCfgDir = "C:\\Users\\lizhuangjie.chnet\\Desktop\\server\\";
//    private String clientCfgDir = "C:\\Users\\admin\\Desktop\\ScreenServiceHost\\";
//    private String clientCfgDir = "C:\\Users\\lizhuangjie.chnet\\Desktop\\client\\";

    private String serverCfgDir = "";
    private String clientCfgDir = "";

    @PostConstruct
    public void initScreenNavigator () {
        GlobalConfig globalConfig = new GlobalConfig();
        try {
            globalConfig = navigator.getGlobalConfig() == null ? globalConfig : navigator.getGlobalConfig();
        }catch (Exception e){e.printStackTrace();}
        serverCfgDir = globalConfig.getServerCfgUrl();
        clientCfgDir = globalConfig.getClientCfgUrl();
    }

    @ResponseBody
    @RequestMapping("setClientConfig")
    public void setClientConfig(@RequestBody WebClientCfg clientConfig1){
        System.out.println(clientConfig1);
        System.out.println("---------client bean to xml string---------");
        ClientConfigPool clientCfgPool = new ClientConfigPool(clientConfig1);
        System.out.println(xmlUtil.convertToXmlFile(clientCfgPool, "utf-8", clientCfgDir + "configPool.xml"));
    }

    @ResponseBody
    @RequestMapping("setServerConfig")
    public void setServerConfig(@RequestBody WebServerCfg serverConfig1){
        System.out.println("-----------------" + serverConfig1);
        System.out.println("----------server bean to xml string---------");
        ServerConfig serverConfig = new ServerConfig(serverConfig1);
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", serverCfgDir + "configPool.xml"));
    }

    @ResponseBody
    @RequestMapping("getClientConfig")
    public WebClientCfg getClientConfig(){
        File file = new File(clientCfgDir + "configPool.xml");
        ClientConfigPool objectResult = xmlUtil.convertFileToObject(file, ClientConfigPool.class);
        WebClientCfg webClientCfg = new WebClientCfg(objectResult);
//        webClientCfg = NodeInitTool.initClient();
        System.out.println(webClientCfg);
        return webClientCfg;
    }

    @ResponseBody
    @RequestMapping("getServerConfig")
    public WebServerCfg getServerConfig(){
        File file = new File(serverCfgDir + "configPool.xml");
        ServerConfig objectResult = xmlUtil.convertFileToObject(file, ServerConfig.class);
        WebServerCfg webServerCfg = new WebServerCfg(objectResult);
//        webServerCfg = NodeInitTool.initServer();
        System.out.println(webServerCfg);
        return webServerCfg;
    }

    @ResponseBody
    @RequestMapping("getPlan1")
    public WebClientCfg getPlan1Ids(){
        File file = new File(clientCfgDir + "config1.xml");
        ClientConfig objectResult = xmlUtil.convertFileToObject(file, ClientConfig.class);
        WebClientCfg webClientCfg = new WebClientCfg(objectResult);
        System.out.println(webClientCfg);
        return webClientCfg;
    }

    @ResponseBody
    @RequestMapping("setClientPlan1")
    public void setClientPlan1(@RequestBody WebClientCfg webClientCfg1){
        ClientConfig clientConfig = getClientPlanNodesFromPool(webClientCfg1);
        System.out.println(xmlUtil.convertToXmlFile(clientConfig, "utf-8", clientCfgDir + "config1.xml"));
        System.out.println(xmlUtil.convertToXmlFile(clientConfig, "utf-8", clientCfgDir + "config.xml"));
        serviceInteract.reloadClientCfgFile();
        initAppBootstrap(webClientCfg1);
    }

    @ResponseBody
    @RequestMapping("setServerPlan1")
    public void setServerPlan1(@RequestBody WebClientCfg webClientCfg1){
        ServerConfig serverConfig = getServerPlanNodesFromPool(webClientCfg1);
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", serverCfgDir + "config1.xml"));
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", serverCfgDir + "config.xml"));
        serviceInteract.resetServerIIS();
    }


    @ResponseBody
    @RequestMapping("getPlan2")
    public WebClientCfg getPlan2(){
        File file = new File(clientCfgDir + "config2.xml");
        ClientConfig objectResult = xmlUtil.convertFileToObject(file, ClientConfig.class);
        WebClientCfg webClientCfg = new WebClientCfg(objectResult);
        System.out.println(webClientCfg);
        return webClientCfg;
    }

    @ResponseBody
    @RequestMapping("setClientPlan2")
    public void setClientPlan2(@RequestBody WebClientCfg webClientCfg2){
        ClientConfig clientConfig = getClientPlanNodesFromPool(webClientCfg2);
        System.out.println(xmlUtil.convertToXmlFile(clientConfig, "utf-8", clientCfgDir + "config2.xml"));
        System.out.println(xmlUtil.convertToXmlFile(clientConfig, "utf-8", clientCfgDir + "config.xml"));
        serviceInteract.reloadClientCfgFile();
        initAppBootstrap(webClientCfg2);
    }

    @ResponseBody
    @RequestMapping("setServerPlan2")
    public void setServerPlan2(@RequestBody WebClientCfg webClientCfg2){
        ServerConfig serverConfig = getServerPlanNodesFromPool(webClientCfg2);
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", serverCfgDir + "config2.xml"));
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", serverCfgDir + "config.xml"));
        serviceInteract.resetServerIIS();
    }


    @ResponseBody
    @RequestMapping("getPlan3")
    public WebClientCfg getPlan3(){
        File file = new File(clientCfgDir + "config3.xml");
        ClientConfig objectResult = xmlUtil.convertFileToObject(file, ClientConfig.class);
        WebClientCfg webClientCfg = new WebClientCfg(objectResult);
        System.out.println(webClientCfg);
        return webClientCfg;
    }

    @ResponseBody
    @RequestMapping("setClientPlan3")
    public void setClientPlan3(@RequestBody WebClientCfg webClientCfg3){
        ClientConfig clientConfig = getClientPlanNodesFromPool(webClientCfg3);
        System.out.println(xmlUtil.convertToXmlFile(clientConfig, "utf-8", clientCfgDir + "config3.xml"));
        System.out.println(xmlUtil.convertToXmlFile(clientConfig, "utf-8", clientCfgDir + "config.xml"));
        serviceInteract.reloadClientCfgFile();
        initAppBootstrap(webClientCfg3);
    }

    @ResponseBody
    @RequestMapping("setServerPlan3")
    public void setServerPlan3(@RequestBody WebClientCfg webClientCfg3){
        ServerConfig serverConfig = getServerPlanNodesFromPool(webClientCfg3);
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", serverCfgDir + "config3.xml"));
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", serverCfgDir + "config.xml"));
        serviceInteract.resetServerIIS();
    }



    private ClientConfig getClientPlanNodesFromPool(WebClientCfg webClientCfg){
        List<String> ids = new ArrayList<>();
        List<WebClientCfg.Node> nodes = webClientCfg.getNodes();
        for(int i = 0; i < nodes.size(); i++){
            ids.add(nodes.get(i).getId());
        }
        File file = new File(clientCfgDir + "configPool.xml");
        ClientConfigPool objectResult = xmlUtil.convertFileToObject(file, ClientConfigPool.class);
        WebClientCfg webClientCfgPool = new WebClientCfg(objectResult);
        List<WebClientCfg.Node> collect = webClientCfgPool.getNodes().stream().filter(item -> ids.contains(item.getId())).collect(Collectors.toList());
        webClientCfgPool.setNodes(collect);

        ClientConfig clientConfig = new ClientConfig(webClientCfgPool);
        return clientConfig;
    }

    private ServerConfig getServerPlanNodesFromPool(WebClientCfg webclientCfg) {
        List<String> ids = new ArrayList<>();
        List<WebClientCfg.Node> nodes = webclientCfg.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            ids.add(nodes.get(i).getId());
        }

        File file = new File(serverCfgDir + "configPool.xml");
        ServerConfig objectResult = xmlUtil.convertFileToObject(file, ServerConfig.class);
        WebServerCfg webServerCfgPool = new WebServerCfg(objectResult);
        List<WebServerCfg.Node> collect = webServerCfgPool.getAppNodes().getNodes().stream().filter(item -> ids.contains(item.getId())).collect(Collectors.toList());
        webServerCfgPool.setAppNodes(new WebServerCfg.AppNodes(collect));

        ServerConfig serverConfig = new ServerConfig(webServerCfgPool);
        return serverConfig;
    }

    private ClientConfigPool getClientCfgPool(WebClientCfg webClientCfg){
        List<String> ids = new ArrayList<>();
        List<WebClientCfg.Node> nodes = webClientCfg.getNodes();
        for(int i = 0; i < nodes.size(); i++){
            ids.add(nodes.get(i).getId());
        }
        File file = new File(clientCfgDir + "configPool.xml");
        ClientConfigPool objectResult = xmlUtil.convertFileToObject(file, ClientConfigPool.class);
        List<ClientConfigPool.Node> collect = objectResult.getNodes().stream().filter(item -> ids.contains(item.getId())).collect(Collectors.toList());
        ClientConfigPool clientConfigPool = new ClientConfigPool();
        clientConfigPool.setNodes(collect);
        return clientConfigPool;
    }

    private void initAppBootstrap(WebClientCfg webCLientCfg){
        ClientConfigPool clientCfgPool = getClientCfgPool(webCLientCfg);
        List<String> webWindowTitles = new ArrayList<>();
        List<String> webUrls = new ArrayList<>();

        List<String> videoTitles = new ArrayList<>();
        List<String> videoUrls = new ArrayList<>();

        clientCfgPool.getNodes().stream().filter(
                item -> !StringUtils.isAnyBlank(item.getWindowTitle(), item.getAppType(), item.getUrl()))
                .forEach(item -> {
                    System.out.println("AppType" + item.getAppType());
                    if(item.getAppType().equals("webPage")){
                        webWindowTitles.add(item.getWindowTitle());
                        webUrls.add(item.getUrl());
                    } else if(item.getAppType().equals("video")){
                        videoTitles.add(item.getWindowTitle());
                        videoUrls.add(item.getUrl());
                    }
                });
        Map<String, List<String>> webMap = new HashMap<>();
        webMap.put("windowTitle", webWindowTitles);
        webMap.put("url", webUrls);
        serviceInteract.initAllWebPage(webMap);

        Map<String, List<String>> videoMap = new HashMap();
        videoMap.put("videoTitle", videoTitles);
        videoMap.put("url", videoUrls);
        serviceInteract.initAllVideo(videoMap);
    }

}
