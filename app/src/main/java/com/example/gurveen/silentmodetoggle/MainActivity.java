package com.example.gurveen.silentmodetoggle;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

   //Making LOG tag the name of the class itself
    private static final String TAG = MainActivity.class.getSimpleName();

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //For API 23 or up asks to give permission for DND settings
        Intent intent = new Intent(
                android.provider.Settings
                        .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        startActivity(intent);

        //Get reference to android Audio Manager
        //so we can use it to toggle our ringer
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        //Find view name content
        FrameLayout contentView = (FrameLayout) findViewById(R.id.content);
        // Set clickListener on that view so it can toggle
        //Update UI to show change
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use RingerHelper class to toggle between Silent and General
                //use performToggle method defined in that class
                RingerHelper.performToggle(audioManager);
                //update UI to show change of state
                updateUi();

            }
        });


    }
    //change image to show on screen
    //choose image using isPhoneSilent method defined in RingerHelper

    private void updateUi() {
        //Find necassary imageview
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
        //Check state of phone and assign image accordingly
        int phoneImage = RingerHelper.isPhoneSilent(audioManager)
                ? R.mipmap.ringer_off
                : R.mipmap.ringer_on;

        imageView.setImageResource(phoneImage);

    }

    //if user closes app and change silent mode and then comes back again
    //so to show that change update the UI onResume
    protected void onResume() {
        super.onResume();

        updateUi();
    }

}
