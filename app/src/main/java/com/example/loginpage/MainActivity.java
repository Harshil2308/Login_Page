package com.example.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ImageView show,hide;
    Button loginButton,RegisterButton;
    EditText email,password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("signuppage.db",MODE_PRIVATE, null);

        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);

        loginButton = findViewById(R.id.main_btn_submit);
        RegisterButton = findViewById(R.id.main_signup);
        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);

        show = findViewById(R.id.password_show);
        hide = findViewById(R.id.password_hide);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.GONE);
                hide.setVisibility(View.VISIBLE);
                password.setTransformationMethod(null);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.VISIBLE);
                hide.setVisibility(View.GONE);
                password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().trim().isEmpty()){
                    email.setError("Email Id Required!");
                } else if (!email.getText().toString().contains("@")) {
                    email.setError("Invalid email Id, Must use @ sign");
                } else if (!email.getText().toString().contains(".")) {
                    email.setError("Invalid email Id, Must use dot(.) sign");
                } else if (password.getText().toString().trim().isEmpty()) {
                    password.setError("Password Required!");
                } else if (password.getText().toString().trim().length() < 6) {
                    password.setError(("Password is too small"));
                } else {
                    String check_user = "SELECT * FROM USERS WHERE EMAIL = '"+email.getText().toString()+"' AND PASSWORD = '"+password.getText().toString()+"'";
                    Cursor cursor = db.rawQuery(check_user, null);
                    if (cursor.getCount() > 0) {
                        Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);

                        while (cursor.moveToNext()){
                            String sName = cursor.getString(0);
                            String sContact = cursor.getString(1);
                            String sEmail = cursor.getString(2);
                            String sPassword = cursor.getString(3);
                            String sGender = cursor.getString(4);

                            sp.edit().putString(ConstantSp.NAME,sName).commit();
                            sp.edit().putString(ConstantSp.CONTACT,sContact).commit();
                            sp.edit().putString(ConstantSp.EMAIL,sEmail).commit();
                            sp.edit().putString(ConstantSp.PASSWORD,sPassword).commit();
                            sp.edit().putString(ConstantSp.GENDER,sGender).commit();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Wrong Email Id/Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Going to SignUp Page!", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, signupButton.class);
                startActivity(intent);
            }
        });
    }
}