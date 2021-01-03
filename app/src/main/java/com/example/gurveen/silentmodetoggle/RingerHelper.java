package com.example.gurveen.silentmodetoggle;

import android.media.AudioManager;

public class RingerHelper {

    //private constructor because there is no need to make RingerHelper objects
    //we are just using it methods directly by Class itself
    private RingerHelper() {
    }

    //static method to know state to silent mode
    public static boolean isPhoneSilent(AudioManager audioManager) {
        return audioManager.getRingerMode()
                == AudioManager.RINGER_MODE_SILENT; //By Default Silent
    }

    //method to check state of silent mode and toggle it
    public static void performToggle(AudioManager audioManager) {

        audioManager.setRingerMode(
                isPhoneSilent(audioManager)
                        ? AudioManager.RINGER_MODE_NORMAL
                        : AudioManager.RINGER_MODE_SILENT);

    }
}
