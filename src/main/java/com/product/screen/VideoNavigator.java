package com.product.screen;

import com.product.screen.entity.GlobalConfig;
import com.product.screen.entity.WebClientCfg;
import com.product.screen.video.OriginalVideoPlayer;
import com.product.screen.video.PlayCfgItem;
import com.product.screen.video.PlayShowItem;
import com.product.screen.video.WebVideoController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VideoNavigator {
    @Autowired
    OriginalVideoPlayer frameVideoController;
    @Autowired
    private WebVideoController webVideoController;
    @Autowired
    private Navigator navigator;

    @PostConstruct
    public void initVideoNavigator(){
        GlobalConfig globalConfig = new GlobalConfig();
        try {
            globalConfig = navigator.getGlobalConfig() == null ? globalConfig : navigator.getGlobalConfig();
        } catch (Exception e) {e.printStackTrace();}
        String videoListUrl = globalConfig.getVideoListUrl();
        System.out.println("初始化video list url: " + videoListUrl);
        webVideoController.setVideoListUrl(videoListUrl);
    }

    @ResponseBody
    @RequestMapping("startVideo")
    public void startVideo(HttpServletRequest request){
        initVideoListUrl();
        frameVideoController.bootstrapVideo();
        String id = request.getParameter("id");
        if(webVideoController.getPlayingItemList() == null || webVideoController.getPlayingItemList().size() == 0){
            webVideoController.setPlayingItemList(webVideoController.getFilePlayItemList());
        }
        webVideoController.setVideoIndex(Integer.parseInt(id));
        webVideoController.startVideo(Integer.parseInt(id));
    }

    @ResponseBody
    @RequestMapping("playVideo")
    public void playVideo(HttpServletRequest request){
        String id = request.getParameter("id");
        webVideoController.playVideo();
    }

    @ResponseBody
    @RequestMapping("pauseVideo")
    public void pauseVideo(HttpServletRequest request){
        String id = request.getParameter("id");
        webVideoController.pauseVideo();
    }

    @ResponseBody
    @RequestMapping("setVideoItemList")
    public void setVideoItemList(@RequestBody List<PlayCfgItem> playCfgItems){
        initVideoListUrl();
        frameVideoController.bootstrapVideo();
        System.out.println("receive playItems ------>" + playCfgItems);
        webVideoController.setPlayCfgItemList(playCfgItems);
        webVideoController.initVideoPlayingItem();
        webVideoController.setAutoVideoIndex(0);
    }

    @ResponseBody
    @RequestMapping("getVideoItemList")
    public List<PlayCfgItem> getVideoItemList(){
        initVideoListUrl();
        return webVideoController.getFilePlayItemList();
    }

    @ResponseBody
    @RequestMapping("getVideoPlayingItemList")
    public List<PlayShowItem> getVideoPlayingItemList() {
        initVideoListUrl();
        List<PlayShowItem> playingItemList = new ArrayList<>();
        List<PlayCfgItem> filePlayCfgItemList = webVideoController.getFilePlayItemList();
        for(int i = 0; i < filePlayCfgItemList.size(); i++){
            PlayShowItem playingItem = new PlayShowItem(filePlayCfgItemList.get(i)).setId(i + 1);
            playingItemList.add(playingItem);
        }
        return playingItemList;
    }

    private void initVideoListUrl(){
        if(StringUtils.isBlank(webVideoController.getVideoListUrl())){
            try {
                webVideoController.setVideoListUrl(navigator.getGlobalConfig().getVideoListUrl());
            } catch (Exception e){ e.printStackTrace(); }
        }
    }
}
