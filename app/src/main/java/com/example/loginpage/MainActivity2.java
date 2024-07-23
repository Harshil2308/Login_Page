package com.example.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView name,email;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        name = findViewById(R.id.dashboard_name);
        email = findViewById(R.id.dashboard_email);
        name.setText(sp.getString(ConstantSp.NAME, ""));
        email.setText(sp.getString(ConstantSp.EMAIL,""));
    }
}