package com.product.screen;

import com.product.screen.tool.NodeInitTool;
import com.product.screen.tool.XmlUtil;
import com.product.screen.entity.ClientConfig;
import com.product.screen.entity.ServerConfig;
import com.product.screen.video.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

@Controller
public class ScreenNavigator {
    @Autowired
    private XmlUtil xmlUtil;
    @Autowired
    private Tutorial videoPlayer;

    @ResponseBody
    @RequestMapping("setClientConfig")
    public void setClientConfig(@RequestBody ClientConfig clientConfig1){
        System.out.println(clientConfig1);
        System.out.println("---------client bean to xml string---------");
        System.out.println(xmlUtil.convertToXmlFile(clientConfig1, "utf-8", "E:\\result\\clientXmlFile.xml"));
    }

    @ResponseBody
    @RequestMapping("setServerConfig")
    public void setServerConfig(@RequestBody ServerConfig serverConfig1){
        System.out.println("-----------------" + serverConfig1);
        System.out.println("----------server bean to xml string---------");
        System.out.println(xmlUtil.convertToXmlFile(serverConfig1, "utf-8", "E:\\result\\serverXmlFile.xml"));
    }

    @ResponseBody
    @RequestMapping("getClientConfig")
    public ClientConfig getClientConfig(){
        File file = new File("E:\\result\\clientXmlFile.xml");
        ClientConfig objectResult = xmlUtil.convertFileToObject(file, ClientConfig.class);
        System.out.println(objectResult);
        return objectResult;
    }

    @ResponseBody
    @RequestMapping("getServerConfig")
    public ServerConfig getServerConfig(){
        File file = new File("E:\\result\\serverXmlFile.xml");
        ServerConfig objectResult = xmlUtil.convertFileToObject(file, ServerConfig.class);
        System.out.println(objectResult);
        return objectResult;
    }

    @ResponseBody
    @RequestMapping("getClientPlan1")
    public ClientConfig getClientPlan1(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan2")
    public ClientConfig getClientPlan2(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan3")
    public ClientConfig getClientPlan3(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan4")
    public ClientConfig getClientPlan4(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan5")
    public ClientConfig getClientPlan5(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan6")
    public ClientConfig getClientPlan6(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan7")
    public ClientConfig getClientPlan7(){
        return null;
    }

    @ResponseBody
    @RequestMapping("bootstrapVideo")
    public void bootstrapVideo(){
        videoPlayer.bootstrapVideo();
    }

    @ResponseBody
    @RequestMapping("playVideo")
    public void playVideo(){
        videoPlayer.playVideo();
    }

    @ResponseBody
    @RequestMapping("pauseVideo")
    public void pauseVideo(){
        videoPlayer.pauseVideo();
    }

    @ResponseBody
    @RequestMapping("rewindVideo")
    public void rewindVideo(){
        videoPlayer.rewindVideo();
    }

}
