package com.example.pruebawear;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear.databinding.ActivityMainBinding;


public class MainActivity extends Activity {

    private Button wBoton = null;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent;

    private NotificationCompat.Builder notification;
    private NotificationCompat.Builder notification2;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender werableExtender;

    String idChannel = "Mi canal";
    int idNotification = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;
    String longText = "Without BigStyle, only a single line of text would be visible" +
            "Any additional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        wBoton = findViewById(R.id.wButton);
        intent = new Intent(MainActivity.this,MainActivity.class);
        nm = NotificationManagerCompat.from(MainActivity.this);
        werableExtender = new NotificationCompat.WearableExtender();
        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);
        wBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name= "Notification";

                NotificationChannel notificationChannel =
                        new NotificationChannel(idChannel,name,importance);

                nm.createNotificationChannel(notificationChannel);
                pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                notification2 = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Wear")
                        .setContentText(longText)
                        .setContentIntent(pendingIntent)
                        .extend(werableExtender)
                        .setVibrate(new long[]{100,200,300,400,500})
                        .setStyle(bigTextStyle);



                notification = new NotificationCompat.Builder( MainActivity.this,idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Wear")
                        .setContentText("Esta es una notificacion corta")
                        .extend(werableExtender);

                nm.notify(idNotification,notification.build());
                nm.notify(idNotification,notification2.build());

            }
        });
    }
}
