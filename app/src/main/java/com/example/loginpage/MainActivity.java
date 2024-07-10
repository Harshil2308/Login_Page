package com.example.loginpage;

import android.content.Intent;
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

    ImageView show,hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.main_btn_submit);
        Button RegisterButton = findViewById(R.id.main_signup);
        EditText email = findViewById(R.id.main_email);
        EditText password = findViewById(R.id.main_password);

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
                    Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
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