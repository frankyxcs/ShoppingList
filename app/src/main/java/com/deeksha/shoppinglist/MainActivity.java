package com.deeksha.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.deeksha.shoppinglist.ui.ListShoppingList;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREFER_UNIQUE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ListShoppingList.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    // shared preference to save the subscriber id
    // subscriber id is uniques and ideally should change for each device.
    public synchronized static String getSubscriberId(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = "subscriber-" + UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return uniqueID;
    }
}