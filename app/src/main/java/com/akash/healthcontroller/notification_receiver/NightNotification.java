package com.akash.healthcontroller.notification_receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.akash.healthcontroller.NotificationActivity;
import com.akash.healthcontroller.R;

/**
 * Created by maniruzzaman_akash on 6/19/2017.
 */

public class NightNotification extends BroadcastReceiver{
    private int night_notification_id = 1004;

    public NightNotification(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Intent repeatingIntent = new Intent(context, NotificationActivity.class);

        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, night_notification_id, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Time to eat Dinner")
                .setContentText("Dear Sir, It's time to take your night dinner please...\nThis is all for your better health.")
                .setAutoCancel(true);

        notificationManager.notify(night_notification_id, builder.build());
    }
}
