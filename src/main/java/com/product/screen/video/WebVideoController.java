package com.product.screen.video;

import com.alibaba.fastjson.*;
import com.google.common.io.Files;
import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class WebVideoController {
    private JPanel controlsPanel;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private List<PlayCfgItem> playCfgItemList;
    private List<PlayCfgItem> playingItemList;
    private PlayCfgItem playingItem;
    private int videoIndex;
    private Timer timer;

    public void setPlayingItemList(List<PlayCfgItem> playingItemList){
        this.playingItemList = playingItemList;
    }

    public List<PlayCfgItem> getPlayingItemList(){
        return playingItemList;
    }

    public void initWebVideoController(EmbeddedMediaPlayerComponent mediaPlayerComponent, JPanel controlsPanel){
        this.mediaPlayerComponent = mediaPlayerComponent;
        this.controlsPanel = controlsPanel;
        this.controlsPanel.setVisible(false);
        playCfgItemList = new ArrayList<>();
    }

    public void visibleControlsPanel(){
        this.controlsPanel.setVisible(true);
    }

    public void invisibleControlsPanel(){
        this.controlsPanel.setVisible(false);
    }

    /**
     * 前端submit后触发
     */
    public void initVideoPlayingItem(){
//        String[] options =
//                {"video-filter=motionblur",  "network-caching=2000", "no-plugins-cache"};
//        mediaPlayerComponent.mediaPlayer().media().start("rtmp://58.200.131.2:1935/livetv/gdtv", options);
//        mediaPlayerComponent.mediaPlayer().media().start("C:\\Users\\lizhuangjie.chnet\\Pictures\\高清视频测试报告\\附件2-不同摄像头视频回传\\大华摄像头.mp4");
        playingItemList = new ArrayList<>();
        for(int i = 0; i < playCfgItemList.size(); i++){
            if(playCfgItemList.get(i).isSelected()){
                playingItemList.add(playCfgItemList.get(i));
            }
        }
    }

    /**
     * 前端点击播放按钮后触发，后续增加线程wait与notify，避免空转系统资源
     */
    public void doVideoPlayItemList(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (((JProgressBar) controlsPanel.getComponent(4)).getPercentComplete() == 1.00) {
                    videoIndex++;
                    startVideo(videoIndex);
                }
            }
        }, 0, 1000);
    }

    public void startVideo(int videoIndex){
        if(videoIndex <= 0 || videoIndex > playingItemList.size()){
            if(timer != null) timer.cancel();
            return;
        }

        playingItem = playingItemList.get(videoIndex - 1);
        System.out.println("play item: " + playingItem.getMrl() + "volume: " + playingItem.getVolume());

        setVolume(playingItem.getVolume());
        mediaPlayerComponent.mediaPlayer().media().start(playingItem.getMrl(), playingItem.getOptions());

    }

    public void setVideoIndex(int videoIndex){
        if(timer != null)
            timer.cancel();
        this.videoIndex = videoIndex;
    }

    public void setAutoVideoIndex(int videoIndex) {
        this.videoIndex = videoIndex;
        doVideoPlayItemList();
    }

    public void playVideo(){
        mediaPlayerComponent.mediaPlayer().controls().play();
    }

    public void pauseVideo(){
        mediaPlayerComponent.mediaPlayer().controls().pause();
    }

    public void setVolume(int volume){
        ((JSlider)controlsPanel.getComponent(3)).setValue(volume);
        mediaPlayerComponent.mediaPlayer().audio().setVolume(volume);
    }

    public void setPlayCfgItemList(List<PlayCfgItem> playCfgItemList){
        try {
            this.playCfgItemList = playCfgItemList;
            System.out.println(playCfgItemList);

            File file = new File("C:\\Users\\lizhuangjie.chnet\\Desktop\\工作内容\\JsonWriter.txt");
            FileWriter fileWriter = new FileWriter(file);

            String s = JSON.toJSONString(playCfgItemList);
            fileWriter.write(s);
            fileWriter.flush();
            fileWriter.close();

        }catch(Exception e){e.printStackTrace();}
    }

    public PlayCfgItem getPlayingItem(){
        return playingItem;
    }

    public List<PlayCfgItem> getPlayCfgItemList(){
        return playCfgItemList;
    }

    public List<PlayCfgItem> getFilePlayItemList(){
        List<PlayCfgItem> playCfgItems = new ArrayList<>();
        try {
            String s1 = Files.asCharSource(
                    new File("C:\\Users\\lizhuangjie.chnet\\Desktop\\工作内容\\JsonWriter.txt"), Charset.forName("utf-8")
            ).read();
            playCfgItems = JSON.parseArray(s1, PlayCfgItem.class);

            System.out.println(playCfgItems);
        }catch (Exception e){e.printStackTrace();}
        return playCfgItems;
    }
}
