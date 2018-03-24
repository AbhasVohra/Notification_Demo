package com.notification_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Monita on 3/24/2018.
 */

public class Message_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
     context.startService(new Intent(context,Notification_Service.class));
    }
}
