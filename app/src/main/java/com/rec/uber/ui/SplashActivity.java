package com.rec.uber.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rec.uber.Login.LauncherActivity;
import com.rec.uber.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check if intro was opened earlier
       if(wasOpened()) {
            startActivity(new Intent(this, LauncherActivity.class));
            finish();
        }
        setContentView(R.layout.activity_splash);

        Button btnStart = findViewById(R.id.btn_get_started);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LauncherActivity.class));
                savePrefsData();
                finish();
            }
        });


    }


    private Boolean wasOpened() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.mypref), MODE_PRIVATE);
        return  sharedPreferences.getBoolean("IsIntroOpened", false);
    }

    // saving boolean value
    private void savePrefsData() {
        SharedPreferences pref =  getApplicationContext().getSharedPreferences(getString(R.string.mypref), MODE_PRIVATE);
        SharedPreferences.Editor  editor = pref.edit();
        editor.putBoolean("IsIntroOpened", true);
        editor.apply();
    }
}