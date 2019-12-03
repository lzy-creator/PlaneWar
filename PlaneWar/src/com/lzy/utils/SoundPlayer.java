package com.lzy.utils;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

    private Clip clip;
    public SoundPlayer(String filePath) throws Exception {
        File file = new File(filePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public SoundPlayer(URL url) throws Exception {
        File file = new File(url.getFile());
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
	    clip.stop();
    }

}
