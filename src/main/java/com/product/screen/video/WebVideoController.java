package com.product.screen.video;

import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebVideoController {
    private JPanel controlsPanel;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private List<PlayItem> playItemList;

    public void initWebVideoController(EmbeddedMediaPlayerComponent mediaPlayerComponent, JPanel controlsPanel){
        this.mediaPlayerComponent = mediaPlayerComponent;
        this.controlsPanel = controlsPanel;
        this.controlsPanel.setVisible(false);
        playItemList = new ArrayList<>();
    }

    public void visibleControlsPanel(){
        this.controlsPanel.setVisible(true);
    }

    public void invisibleControlsPanel(){
        this.controlsPanel.setVisible(false);
    }

    public void startVideoPlay(){
//        String[] options =
//                {"video-filter=motionblur",  "network-caching=2000", "no-plugins-cache"};
//        mediaPlayerComponent.mediaPlayer().media().start("rtmp://58.200.131.2:1935/livetv/gdtv", options);
        mediaPlayerComponent.mediaPlayer().media().start("C:\\Users\\lizhuangjie.chnet\\Pictures\\高清视频测试报告\\附件2-不同摄像头视频回传\\大华摄像头.mp4");
    }

    public void playVideo(){
        mediaPlayerComponent.mediaPlayer().controls().play();
    }

    public void pauseVideo(){
        mediaPlayerComponent.mediaPlayer().controls().pause();
    }

    public void rewindVideo(){
        mediaPlayerComponent.mediaPlayer().controls().skipTime(-10000);
    }

    public void setVolume(){
        mediaPlayerComponent.mediaPlayer().audio().setVolume(12);
    }

    public int getVolume(){
        return mediaPlayerComponent.mediaPlayer().audio().volume();
    }

    public void setPlayItemList(List<PlayItem> playItemList){
        this.playItemList = playItemList;
    }

    public List<PlayItem> getPlayItemList(){
        return playItemList;
    }
}
