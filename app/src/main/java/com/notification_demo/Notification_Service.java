package com.notification_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Monita on 3/24/2018.
 */

public class Notification_Service extends Service {

    private Timer timer1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*this method is called to show notification on notification bar*/
        notifys();

        /*timer is used to delay the notification for 5 mins*/

        timer1=new Timer();
        timer1.schedule(timerTask,300000,3*100000);
    }

    @Override
    public void onDestroy() {
        timer1.cancel();
        timerTask.cancel();
        Intent intent=new Intent("com.notification_demo");
        intent.putExtra("yourvalue","store");
        sendBroadcast(intent);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            notifys();
        }
    };
    public void notifys()
    {
        IntentFilter filter=new IntentFilter();
        filter.addAction("RSSPullService");
        Intent myintent= new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        PendingIntent pending= PendingIntent.getActivity(getBaseContext(),0,myintent,Intent.FILL_IN_ACTION);
         Context context=getApplicationContext();
        Notification.Builder builder;
        builder=new Notification.Builder(context)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("You are on the break").setContentIntent(pending)
                .setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
        .setSmallIcon(R.drawable.stopwatch);

        Notification noyification= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            noyification = builder.build();
        }
        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,noyification);
    }
}
