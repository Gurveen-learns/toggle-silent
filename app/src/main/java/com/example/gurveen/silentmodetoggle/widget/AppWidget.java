package com.example.gurveen.silentmodetoggle.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class AppWidget extends AppWidgetProvider {
    //Since AppWidgetProvider is a type to broadcast Receiver
    //that makes AppWidget class receiver of this app

    public void onUpdate(Context context, AppWidgetManager appWidgetManager
            , int[] appWidgetIds) {

        context.startService(new Intent(context,AppWidgetService.class));
    }

}
