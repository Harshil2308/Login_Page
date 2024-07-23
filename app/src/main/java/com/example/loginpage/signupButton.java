package com.example.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class signupButton extends AppCompatActivity {

    ImageView signup_show,signup_hide;
    ImageView signup_confirm_show,signup_confirm_hide;
    RadioGroup gender;
    String sGender;
    CheckBox termscheckbox;
    SQLiteDatabase db;
    EditText name,email,contact,password,confirm_password;
    SharedPreferences sp;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_button);

        db = openOrCreateDatabase("signuppage.db", MODE_PRIVATE, null);
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        String tableQuery = "CREATE TABLE IF NOT EXISTS USERS(NAME VARCHAR(20), MOBILE BIGINT(10), EMAIL VARCHAR(20), PASSWORD VARCHAR(10), GENDER VARCHAR(10))";
        db.execSQL(tableQuery);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        contact = findViewById(R.id.signup_contactno);
        password = findViewById(R.id.signup_password);
        confirm_password = findViewById(R.id.signup_confirm_password);
        signup = findViewById(R.id.signup_button);

        gender = findViewById(R.id.signup_radio_group);
        termscheckbox = findViewById(R.id.signup_checkbox);

        signup_show = findViewById(R.id.signup_password_show);
        signup_hide = findViewById(R.id.signup_password_hide);

        signup_confirm_show = findViewById(R.id.signup_confirm_password_show);
        signup_confirm_hide = findViewById(R.id.signup_confirm_password_hide);

        email.setText(sp.getString(ConstantSp.EMAIL,""));
        password.setText(sp.getString(ConstantSp.PASSWORD,""));

        Log.d("DATA",sp.getString(ConstantSp.GENDER,"")+"\n"+
                sp.getString(ConstantSp.PASSWORD,"")+"\n"+
                sp.getString(ConstantSp.CONTACT,"")+"\n"+
                sp.getString(ConstantSp.NAME,"")+"\n"+
                sp.getString(ConstantSp.EMAIL,""));

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
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedGender = findViewById(i);
                sGender = selectedGender.getText().toString();
                Toast.makeText(signupButton.this, sGender, Toast.LENGTH_LONG).show();
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
                } else if (gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(signupButton.this, "Gender is Required", Toast.LENGTH_LONG).show();
                } else if (!termscheckbox.isChecked()) {
                    Toast.makeText(signupButton.this, "Terms & Conditions are required", Toast.LENGTH_LONG).show();
                } else {
                    String exist_check = "SELECT * FROM USERS WHERE EMAIL = '"+email.getText().toString()+"' or MOBILE = '"+contact.getText().toString()+"'";
                    Cursor cursor = db.rawQuery(exist_check, null);

                    if (cursor.getCount() > 0) {
                        Toast.makeText(signupButton.this, "This user is already exists", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(signupButton.this, "Sign Up Successfully!", Toast.LENGTH_LONG).show();

                        String insertQuery = "INSERT INTO USERS VALUES('"+name.getText().toString()+"','"+contact.getText().toString()+"','"+email.getText().toString()+"','"+password.getText().toString()+"','"+sGender+"')";
                        db.execSQL(insertQuery);
                        Intent intent = new Intent(signupButton.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}