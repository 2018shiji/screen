package com.product.screen;

import com.product.screen.entity.ClientConfig;
import com.product.screen.entity.ServerConfig;
import com.product.screen.entity.WebClientCfg;
import com.product.screen.entity.WebServerCfg;
import com.product.screen.tool.NodeInitTool;
import com.product.screen.tool.XmlUtil;
import com.product.screen.video.OriginalVideoPlayer;
import com.product.screen.video.PlayItem;
import com.product.screen.video.WebVideoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

@Controller
public class ScreenNavigator {
    @Autowired
    private XmlUtil xmlUtil;
    @Autowired
    OriginalVideoPlayer frameVideoController;
    @Autowired
    private WebVideoController webVideoController;

    @ResponseBody
    @RequestMapping("setClientConfig")
    public void setClientConfig(@RequestBody WebClientCfg clientConfig1){
        System.out.println(clientConfig1);
        System.out.println("---------client bean to xml string---------");
        ClientConfig clientConfig = new ClientConfig(clientConfig1);
        System.out.println(xmlUtil.convertToXmlFile(clientConfig, "utf-8", "C:\\Users\\admin\\Desktop\\ScreenServiceHost\\config.xml"));
    }

    @ResponseBody
    @RequestMapping("setServerConfig")
    public void setServerConfig(@RequestBody WebServerCfg serverConfig1){
        System.out.println("-----------------" + serverConfig1);
        System.out.println("----------server bean to xml string---------");
        ServerConfig serverConfig = new ServerConfig(serverConfig1);
        System.out.println(xmlUtil.convertToXmlFile(serverConfig, "utf-8", "D:\\CMHIT\\ScreenControl\\HXF6\\config.xml"));
    }

    @ResponseBody
    @RequestMapping("getClientConfig")
    public WebClientCfg getClientConfig(){
        File file = new File("C:\\Users\\admin\\Desktop\\ScreenServiceHost\\config.xml");
        ClientConfig objectResult = xmlUtil.convertFileToObject(file, ClientConfig.class);
        WebClientCfg webClientCfg = new WebClientCfg(objectResult);
//        webClientCfg = NodeInitTool.initClient();
        System.out.println(webClientCfg);
        return webClientCfg;
    }

    @ResponseBody
    @RequestMapping("getServerConfig")
    public WebServerCfg getServerConfig(){
        File file = new File("D:\\CMHIT\\ScreenControl\\HXF6\\config.xml");
        ServerConfig objectResult = xmlUtil.convertFileToObject(file, ServerConfig.class);
        WebServerCfg webServerCfg = new WebServerCfg(objectResult);
//        webServerCfg = NodeInitTool.initServer();
        System.out.println(webServerCfg);
        return webServerCfg;
    }

    @ResponseBody
    @RequestMapping("getClientPlan1")
    public WebClientCfg getClientPlan1(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan2")
    public WebClientCfg getClientPlan2(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan3")
    public WebClientCfg getClientPlan3(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan4")
    public WebClientCfg getClientPlan4(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan5")
    public WebClientCfg getClientPlan5(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan6")
    public WebClientCfg getClientPlan6(){
        return null;
    }

    @ResponseBody
    @RequestMapping("getClientPlan7")
    public WebClientCfg getClientPlan7(){
        return null;
    }

    @ResponseBody
    @RequestMapping("bootstrapVideo")
    public void bootstrapVideo(){
        frameVideoController.bootstrapVideo();
    }

    @ResponseBody
    @RequestMapping("playVideo")
    public void playVideo(){
        webVideoController.startVideoPlay();
    }

    @ResponseBody
    @RequestMapping("pauseVideo")
    public void pauseVideo(){
        webVideoController.pauseVideo();
    }

    @ResponseBody
    @RequestMapping("rewindVideo")
    public void rewindVideo(){
        webVideoController.rewindVideo();
    }

    @ResponseBody
    @RequestMapping("visibleCtrlPanel")
    public void visibleCtrlPanel() {
        webVideoController.visibleControlsPanel();
    }

    @ResponseBody
    @RequestMapping("invisibleCtrlPanel")
    public void invisibleCtrlPanel() {
        webVideoController.invisibleControlsPanel();
    }

    @ResponseBody
    @RequestMapping("setVideoItemList")
    public void setVideoItemList(@RequestBody List<PlayItem> playItems){
        System.out.println("receive playItems ------>" + playItems);
        webVideoController.setPlayItemList(playItems);
    }

    @ResponseBody
    @RequestMapping("getVideoItemList")
    public List<PlayItem> getVideoItemList(){
        return webVideoController.getPlayItemList();
    }
}
