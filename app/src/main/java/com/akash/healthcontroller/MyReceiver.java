package com.akash.healthcontroller;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by maniruzzaman_akash on 5/8/2017.
 */

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Intent repeatingIntent = new Intent(context, MainActivity.class);
        //context.startService(intent1);

        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 9122, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);


         NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.side_nav_bar)
                .setContentTitle("Your Health Controller..")
                .setContentText("Simple notification")
                .setAutoCancel(true);

        notificationManager.notify(9122, builder.build());

    }
}
