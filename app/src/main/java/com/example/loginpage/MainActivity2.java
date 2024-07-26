package com.example.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView name,email;
    SharedPreferences sp;
    Button logout,Delete;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        db = openOrCreateDatabase("signuppage.db",MODE_PRIVATE,null);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        name = findViewById(R.id.dashboard_name);
        email = findViewById(R.id.dashboard_email);
        logout = findViewById(R.id.dashboard_logout);
        Delete = findViewById(R.id.dashboard_delete);

        name.setText(sp.getString(ConstantSp.NAME, ""));
        email.setText(sp.getString(ConstantSp.EMAIL,""));


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String delete_user = "DELETE FROM USERS WHERE EMAIL = '"+sp.getString(ConstantSp.EMAIL,"")+"'";
                    db.execSQL(delete_user);

                    Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
            }
        });
    }
}