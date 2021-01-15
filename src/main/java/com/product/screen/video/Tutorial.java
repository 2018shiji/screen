package com.product.screen.video;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

@Component
public class Tutorial {

    private JFrame frame;

    private EmbeddedMediaPlayerComponent mediaPlayerComponent;

    private JButton pauseButton;

    private JButton rewindButton;

    private JButton skipButton;

    public static void main(String[] args) {
        new Tutorial().bootstrapVideo();
    }

    public void bootstrapVideo() {
        frame = new JFrame("My First Media Player");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

        JPanel controlsPane = new JPanel();
        pauseButton = new JButton("Pause");
        controlsPane.add(pauseButton);
        rewindButton = new JButton("Rewind");
        controlsPane.add(rewindButton);
        skipButton = new JButton("Skip");
        controlsPane.add(skipButton);
        contentPane.add(controlsPane, BorderLayout.SOUTH);

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().pause();
            }
        });

        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().skipTime(-10000);
            }
        });

        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().skipTime(10000);
            }
        });

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void playVideo(){
        mediaPlayerComponent.mediaPlayer().media().play("C:\\Users\\lizhuangjie.chnet\\Pictures\\高清视频测试报告\\附件2-不同摄像头视频回传\\大华摄像头.mp4");
    }

    public void pauseVideo(){
        mediaPlayerComponent.mediaPlayer().controls().pause();
    }

    public void rewindVideo(){
        mediaPlayerComponent.mediaPlayer().controls().skipTime(-10000);
    }

}
