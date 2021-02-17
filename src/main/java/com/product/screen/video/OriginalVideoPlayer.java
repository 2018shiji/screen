package com.product.screen.video;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 * https://www.optbbs.com/forum.php?mod=viewthread&tid=14818551
 */
@Component
public class OriginalVideoPlayer {

    private JFrame frame;

    private JButton pauseButton;

    private JButton rewindButton;

    private JButton skipButton;

    private JSlider slider;

    private JProgressBar progressBar;

    private JPanel contentPane;

    private JPanel controlsPane;

    private EmbeddedMediaPlayerComponent mediaPlayerComponent;

    @Autowired
    private WebVideoController webVideoController;

    public static void main(String[] args) {
        new OriginalVideoPlayer().bootstrapVideo();
    }

    public void bootstrapVideo() {
        if(mediaPlayerComponent == null) {
            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

            frame = new JFrame("My First Media Player");
            frame.setBounds(100, 100, 600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    mediaPlayerComponent.release();
                }
            });


            contentPane = new JPanel();
            contentPane.setLayout(new BorderLayout());

            contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

            controlsPane = new JPanel();
            pauseButton = new JButton("Pause");
            controlsPane.add(pauseButton);
            rewindButton = new JButton("Rewind");
            controlsPane.add(rewindButton);
            skipButton = new JButton("Skip");
            controlsPane.add(skipButton);
            slider = new JSlider(0, 100, 0);
            controlsPane.add(slider);

            progressBar = new JProgressBar();
            progressBar.setFocusable(false);
            controlsPane.add(progressBar, BorderLayout.NORTH);
            controlsPane.setVisible(true);

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

            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent changeEvent) {
                    mediaPlayerComponent.mediaPlayer().audio().setVolume(slider.getValue());
                    webVideoController.getPlayingItem().setVolume(slider.getValue());
                    System.out.println("被调用 ====>" + slider.getValue());
                }
            });

            slider.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    System.out.println("slider 失去焦点");
                    webVideoController.setPlayCfgItemList(webVideoController.getPlayCfgItemList());
                }
            });

            progressBar.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    float per = (float) x / progressBar.getWidth();
                    mediaPlayerComponent.mediaPlayer().controls().setPosition(per);
                }
            });

            new SwingWorker<String, Integer>() {
                @Override
                protected String doInBackground() throws Exception {
                    while (true) {
                        long total = mediaPlayerComponent.mediaPlayer().status().length();
                        long curr = mediaPlayerComponent.mediaPlayer().status().time();
                        float percent = (float) curr / total;
                        progressBar.setValue((int) (percent * 100));
                        if(percent > 0.98) {
                            webVideoController.startPauseAtLastFrame();
                        }
                        Thread.sleep(200);
                    }
                }
            }.execute();

            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice vc = env.getDefaultScreenDevice();

//            frame.setUndecorated(true);
            frame.setResizable(false);

            frame.setContentPane(contentPane);
            frame.setVisible(true);

            vc.setFullScreenWindow(frame);

            mediaPlayerComponent.mediaPlayer().fullScreen().set(true);

            webVideoController.initWebVideoController(mediaPlayerComponent, controlsPane);
        }
    }

}
