package com.product.screen;

import com.product.screen.video.OriginalVideoPlayer;
import com.product.screen.video.PlayCfgItem;
import com.product.screen.video.PlayShowItem;
import com.product.screen.video.WebVideoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VideoNavigator {
    @Autowired
    OriginalVideoPlayer frameVideoController;
    @Autowired
    private WebVideoController webVideoController;

    @ResponseBody
    @RequestMapping("startVideo")
    public void startVideo(HttpServletRequest request){
        frameVideoController.bootstrapVideo();
        String id = request.getParameter("id");
        if(webVideoController.getPlayingItemList() == null || webVideoController.getPlayingItemList().size() == 0){
            webVideoController.setPlayingItemList(webVideoController.getFilePlayItemList());
        }
        webVideoController.setVideoIndex(Integer.parseInt(id));
        webVideoController.invisibleControlsPanel();
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
        frameVideoController.bootstrapVideo();
        System.out.println("receive playItems ------>" + playCfgItems);
        webVideoController.setPlayCfgItemList(playCfgItems);
        webVideoController.initVideoPlayingItem();
        webVideoController.visibleControlsPanel();
        webVideoController.setAutoVideoIndex(0);
    }

    @ResponseBody
    @RequestMapping("getVideoItemList")
    public List<PlayCfgItem> getVideoItemList(){
        return webVideoController.getFilePlayItemList();
    }

    @ResponseBody
    @RequestMapping("getVideoPlayingItemList")
    public List<PlayShowItem> getVideoPlayingItemList() {
        List<PlayShowItem> playingItemList = new ArrayList<>();
        List<PlayCfgItem> filePlayCfgItemList = webVideoController.getFilePlayItemList();
        for(int i = 0; i < filePlayCfgItemList.size(); i++){
            PlayShowItem playingItem = new PlayShowItem(filePlayCfgItemList.get(i)).setId(i + 1);
            playingItemList.add(playingItem);
        }
        return playingItemList;
    }
}
