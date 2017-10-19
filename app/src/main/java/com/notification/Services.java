package com.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

public class Services extends Service {

    public static String START = "START";
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            if(action.equals(START)) {
                int NOTIFICATION_ID = 11;
                Intent main = new Intent(context, MainActivity.class);
                main.setAction(Intent.ACTION_MAIN);
                main.addCategory(Intent.CATEGORY_LAUNCHER);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, main, 0);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setShowWhen(false)
                        .setAutoCancel(true)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("Notification")
                        .setContentText("Notif text")
                        .setContentInfo("Notif info")
                        .setContentIntent(pendingIntent);
                notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
            }
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
