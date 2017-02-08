package com.pooja.inboxstylenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btn_inbox_notification;
    private static final int INBOX_ID=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_inbox_notification= (Button) findViewById(R.id.button_inbox_notification);
    }
    public void inboxclick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_inbox_notification:
                showinboxstylenotification();
                break;
        }
    }

    private void showinboxstylenotification()
    {
        //Style of notifiction is inbox style
        NotificationCompat.InboxStyle inboxstyle=new NotificationCompat.InboxStyle();
        inboxstyle.setBigContentTitle("Inbox style notification");
        inboxstyle.addLine("Line one");
        inboxstyle.addLine("Line two");
        inboxstyle.addLine("Line three");

        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this);
        builder.setContentTitle("Inbox Style Notification");
        builder.setContentText("Hello all \n This is my first inbox style notification");
        builder.setSmallIcon(R.drawable.ic_action_email);
        builder.setTicker("Inbox Notification");
        builder.setAutoCancel(true);
        builder.setStyle(inboxstyle);

        Intent i=new Intent(MainActivity.this,Inbox_Activity.class);
        TaskStackBuilder stackbuilder=TaskStackBuilder.create(MainActivity.this);
        stackbuilder.addParentStack(Inbox_Activity.class);
        stackbuilder.addNextIntent(i);
        PendingIntent pending_intent=stackbuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        //for the actio button
        Intent replyintent=new Intent(MainActivity.this,Reply_activity.class);
        TaskStackBuilder stackbuilder_reply=TaskStackBuilder.create(MainActivity.this);
        stackbuilder_reply.addParentStack(Reply_activity.class);
        stackbuilder_reply.addNextIntent(replyintent);
        PendingIntent pending_intent_reply=stackbuilder_reply.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pending_intent);
        builder.setContentIntent(pending_intent_reply);
        builder.addAction(R.drawable.ic_action_email,"Reply",pending_intent_reply);

        Notification notification=builder.build();
        NotificationManager manager=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(INBOX_ID,notification);
    }
}
