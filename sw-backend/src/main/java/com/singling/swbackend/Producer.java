package com.singling.swbackend;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

/**
 *
 */
public class Producer {

    // Instantiate player classes
    private Player player;
    private PlayerManager singlingPlayer;
    private Thread threadPlayer;
    private Pattern pattern;

    /**
     *
     * @param
     */
    public Producer() {
        //pattern = thisPattern;
    }

    /**
     *
     * @param pattern
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     *
     */
    public void setPlayer() {
        if (player == null) {
            player = new Player();
        } else if (player != null) {
            try {
                player.getManagedPlayer().finish();
            } catch (Exception e) {
            }
            player = new Player();
        }

        if (singlingPlayer == null) {
            singlingPlayer = new PlayerManager();
        } else if (singlingPlayer != null) {
            singlingPlayer.stop();
            singlingPlayer = new PlayerManager();
        }

        threadPlayer = new Thread(singlingPlayer);
    }

    /**
     *
     * @param baseNoteLength
     */
    public void doStartPlayer(double baseNoteLength) {
        singlingPlayer.setPattern(pattern, player, baseNoteLength);

        System.out.println("Start player:" + threadPlayer.getId());
        if (threadPlayer.getState() == Thread.State.NEW) {
            threadPlayer.start();
        }
    }

    /**
     *
     */
    public void doPause() {
        try {
            if (player.getManagedPlayer().isPlaying()) {
                player.getManagedPlayer().pause();
                singlingPlayer.stop();
            } else if (player.getManagedPlayer().isPaused()) {
                player.getManagedPlayer().resume();
                singlingPlayer.resume();
            }
        } catch (Exception e) {
        }
    }

    /**
     *
     */
    //public void doPlay() {
    //    player.play(pattern);
    //    player.getManagedPlayer().finish();
    //}

    /**
     *
     * @param input
     * @param output
     * @throws Exception
     */
    public void doSaveAsMidi(String input, String output) throws Exception {
        File file = new File(output);
        MidiFileManager midiFileManager = new MidiFileManager();
        midiFileManager.savePatternToMidi(pattern, file);
    }

    /**
     *
     * @param input
     * @param output
     * @throws Exception
     */
    public void doSaveAsWAV(String input, String output) throws Exception {
        // Save to MIDI first
        File midiFile = new File(output + ".mid");
        MidiFileManager midiFileManager = new MidiFileManager();
        midiFileManager.savePatternToMidi(pattern, midiFile);

        // Convert MIDI to WAV
        AudioInputStream stream = AudioSystem.getAudioInputStream(midiFile);
        File file = new File(new File(output) + ".wav");
        AudioSystem.write(stream, AudioFileFormat.Type.WAVE, file);

        // Delete MIDI file
        midiFile.delete();

        // Close stream
        stream.close();
    }

}