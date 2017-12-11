package com.example.vishnuprabha.nfn_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Splash extends AppCompatActivity {
    Context ctx = this;
    static int i=0,j=0;
    static String[][] q=new String[50][20];
    static String[] v=new String[50];
    DatabaseOperations db = new DatabaseOperations(ctx);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * 1000);

                    // After 5 seconds redirect to another intent
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
        db.deleteInformation(db);
        db.putInformation1(db, "11", "What is the body temperature?", "Greater than 100-Less than 100-No", "1");
        db.putInformation1(db, "12", "How long you have Cold?", "Less than 2 days-Less than a week-No","2");
        db.putInformation1(db, "13", "Where do you Pain?", "Forehead-Side of head-Back of head-No","2");
        db.putInformation1(db, "12", "22", "2","2");
        db.putInformation(db, "1", "Do you have Fever?", "Yes-No");
        db.putInformation(db, "2", "Do you have Cold?", "Yes-No");
        db.putInformation(db, "3", "Do you have Head Ache?", "Yes-No");
        db.putInformation(db, "4", "Do you have Caugh?", "Yes-No");
        db.putInformation(db, "5", "Do you have Stomach Ache?", "Yes-No");
        db.putInformation(db, "11", "What is the body temperature?", "Greater than 100-Less than 100-No");
        db.putInformation(db, "12", "How long you have Cold?", "Less than 2 days-Less than a week-No");
        db.putInformation(db, "13", "Where do you Pain?", "Forehead-Side of head-Back of head-No");
        db.putInformation(db, "14", "What kind of caugh you have?", "Dry caugh-Wet caugh-No");
        db.putInformation(db, "15", "Where do you have pain?", "Abdominal pain-OtherPart-No");
        db.putInformation(db, "112", "Do you have Running Nose?", "Yes-No");
        Cursor cursor_2 = db.getInformation(db);
        v[0] = "Fever -";
        v[1] = "Cold -";
        v[2] = "Head Ache on ";
        v[3] = "Caugh-";
        v[4] = "Stomach Ache-";
        v[5] = "Running Nose";

        if (cursor_2.moveToFirst()) {

            do {

                q[i][0] = cursor_2.getString(0);
                q[i][1] = cursor_2.getString(1);
                q[i][2] = cursor_2.getString(2);
                i++;
            } while (cursor_2.moveToNext());

        }

        cursor_2.close();

        Cursor cursor = db.getInformation1(db,"2");
        if(cursor!=null && cursor.getCount() > 0)
        {
            if (cursor.moveToFirst())
            {
                do {
                    Log.d("result",cursor.getString(1)+cursor.getString(2)+cursor.getString(3));
                } while (cursor.moveToNext());
            }
        }

    }
}
