package com.example.td2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class activityTwo extends AppCompatActivity {

    private static final String CHANNEL_ID = "defaultChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_two);
    }

    //Switch du layout 2 -> 3 en cliquant sur le bouton
    public void TwoToThree(View view) {
        setContentView(R.layout.activity_three);
    }

    //Switch du layout 3 -> 1 en cliquant sur le bouton
    public void ThreeToOne(View view) {
        setContentView(R.layout.activity_one);
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void NotifyUser(View view) {
        String nom = ((TextView) findViewById(R.id.textViewNom)).getText().toString();
        String prenom = ((TextView) findViewById(R.id.textViewPrenom)).getText().toString();
        String matricule = ((TextView) findViewById(R.id.textViewMatricule)).getText().toString();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                //.setSmallIcon(R.drawable.notification_icon)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification from RegisterFragment")
                .setContentText("Notification envoyé par " + nom + " " + prenom)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Notification envoyé par " + nom + " " + prenom + " de matricule : " + matricule))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        int notificationId = 1;
        notificationManager.notify(notificationId, builder.build());

    }

}