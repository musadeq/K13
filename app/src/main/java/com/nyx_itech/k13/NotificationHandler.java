package com.nyx_itech.k13;

/**
 * Created by NYX_RIFAL on 8/9/2016.
 */

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.List;


/**
 * Created by Ravi on 01/06/15.
 */
public class NotificationHandler {

    private Context mContext;

    public NotificationHandler(Context mContext) {
        this.mContext = mContext;
    }


    //This method would display the notification

    public void showNotificationMessage(String title, String message, String guruID) {
//        Uri sound = Uri.fromFile(new File("file:///android_asset/ohfinally.mp3"));
//        Intent intent = new Intent(mContext, ChatActivity.class);
//        intent.putExtra("guruID",guruID);
//        Log.d("berapasih", String.valueOf(guruID));
//        PendingIntent pi = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        Notification notification = new NotificationCompat.Builder(mContext)
//                .setTicker(message)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setContentIntent(pi)
//                .setSound(sound)
//                .setVibrate(new long[] { 500, 500, 500, 500, 500 })
//                .setAutoCancel(true)
//                .build();
//
//        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notification);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
        notificationManager.notify(123, builder.build());
    }
    //This method will check whether the app is in background or not
    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

}