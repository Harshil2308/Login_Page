package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signupButton extends AppCompatActivity {

    ImageView signup_show,signup_hide;
    ImageView signup_confirm_show,signup_confirm_hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_button);

        EditText name = findViewById(R.id.signup_name);
        EditText email = findViewById(R.id.signup_email);
        EditText contact = findViewById(R.id.signup_contactno);
        EditText password = findViewById(R.id.signup_password);
        EditText confirm_password = findViewById(R.id.signup_confirm_password);
        Button signup = findViewById(R.id.signup_button);

        signup_show = findViewById(R.id.signup_password_show);
        signup_hide = findViewById(R.id.signup_password_hide);

        signup_confirm_show = findViewById(R.id.signup_confirm_password_show);
        signup_confirm_hide = findViewById(R.id.signup_confirm_password_hide);

        signup_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_show.setVisibility(View.GONE);
                signup_hide.setVisibility(View.VISIBLE);
                password.setTransformationMethod(null);
            }
        });
        signup_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_show.setVisibility(View.VISIBLE);
                signup_hide.setVisibility(View.GONE);
                password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        signup_confirm_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_confirm_show.setVisibility(View.GONE);
                signup_confirm_hide.setVisibility(View.VISIBLE);
                confirm_password.setTransformationMethod(null);
            }
        });
        signup_confirm_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_confirm_show.setVisibility(View.VISIBLE);
                signup_confirm_hide.setVisibility(View.GONE);
                confirm_password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().isEmpty()) {
                    name.setError("Name Required!");
                } else if (email.getText().toString().trim().isEmpty()) {
                    email.setError("Email Id Required!");
                } else if (!email.getText().toString().contains("@")) {
                    email.setError("Invalid Email Id, Must use @ sign");
                } else if (!email.getText().toString().contains(".")) {
                    email.setError("Invalid email Id, Must use dot(.) sign");
                } else if (contact.getText().toString().trim().isEmpty()) {
                    contact.setError("Contact no required!");
                } else if (contact.getText().toString().length() < 10) {
                    contact.setError("number length should be 10");
                } else if (password.getText().toString().trim().isEmpty()) {
                    password.setError("Password Required!");
                } else if (password.getText().toString().length() < 6) {
                    password.setError("Minimum length should be 6");
                } else if (confirm_password.getText().toString().trim().isEmpty()) {
                    confirm_password.setError("Confirm Password Required!");
                } else if (!password.getText().toString().trim().equals(confirm_password.getText().toString())) {
                    confirm_password.setError("The password is not same");
                } else {
                    Intent intent = new Intent(signupButton.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}