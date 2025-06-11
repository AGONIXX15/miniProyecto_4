package utils;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

// clase para reproducir un sonido
public class ReproduceSound {
    private Clip clip;

    public void loadSound(String path) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            if (is == null) throw new IllegalArgumentException("No se encontró el recurso: " + path);
            BufferedInputStream bis = new BufferedInputStream(is);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Control de volumen
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-10.0f); // Baja el volumen
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loopSound() {
        if (clip != null) {
            clip.setFramePosition(0);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-30.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproduce sin parar
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
