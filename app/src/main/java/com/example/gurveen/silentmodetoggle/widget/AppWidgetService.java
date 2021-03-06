package com.example.gurveen.silentmodetoggle.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.example.gurveen.silentmodetoggle.R;
import com.example.gurveen.silentmodetoggle.RingerHelper;

public class AppWidgetService extends IntentService {

    public static String ACTION_DO_TOGGLE = "actionDoToggle";

    AudioManager audioManager;

    public AppWidgetService() {
        super("AppWidgetService");
    }

    @Override
    public void onCreate() {
        //Always call super.onCreate()
        super.onCreate();

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null && intent.getBooleanExtra(ACTION_DO_TOGGLE, false)) {
            RingerHelper.performToggle(audioManager);
        }

        AppWidgetManager mgr = AppWidgetManager.getInstance(this);
        ComponentName name = new ComponentName(this, AppWidget.class);

        mgr.updateAppWidget(name, updateUi());

    }

    private RemoteViews updateUi() {

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.app_widget);

        int phoneImage = RingerHelper.isPhoneSilent(audioManager)
                ? R.mipmap.ringer_off
                : R.mipmap.ringer_on;
        remoteViews.setImageViewResource(R.id.phone_state, phoneImage);

        Intent intent = new Intent(this, AppWidget.class)
                .putExtra(ACTION_DO_TOGGLE, true);

        PendingIntent pendingIntent = PendingIntent.getService(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        remoteViews.setOnClickPendingIntent(R.id.phone_state, pendingIntent);
        return remoteViews;

    }
}
