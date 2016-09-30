package com.nyx_itech.k13.fcm;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nyx_itech.k13.MainActivity;
import com.nyx_itech.k13.R;
import com.nyx_itech.k13.database.Chat;
import com.nyx_itech.k13.database.DatabaseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by NYX_RIFAL on 9/23/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        String senderName = "";
        String senderID = "";

        JSONObject data = (JSONObject) remoteMessage.getData();
        String msg = remoteMessage.getNotification().getBody();
        try {
            senderName = data.getString("senderName");
            senderID = data.getString("senderID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Calling method to generate notification

        //save to database
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.addChat(new Chat(senderID, msg, "2", "2016", "13:00", "1"));

        Intent pushNotification = new Intent("pushnotification");
        pushNotification.putExtra("message", msg);
        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

        if (isAppIsInBackground(getApplicationContext())) {
            sendNotification(msg, remoteMessage.getFrom()); //bunyi
        } else {
            ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            String className = taskInfo.get(0).topActivity.getClassName();
            if (!className.contains("ChatRoom")) {
                sendNotification(msg, remoteMessage.getFrom()); //bunyi
            }
        }

    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(String messageBody, String from) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(from)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setTicker(messageBody)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

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
