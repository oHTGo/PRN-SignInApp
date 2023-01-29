package com.example.signinapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.login_activity_et_username);
        etPassword = findViewById(R.id.login_activity_et_password);
        btnLogin = findViewById(R.id.login_activity_btn_login);
        tvRegister = findViewById(R.id.login_activity_tv_register);

        btnLogin.setOnClickListener(view -> {
            validate();
        });
        tvRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            finish();
            startActivity(intent);
        });
    }

    private boolean validate() {
        boolean result = true;
        if (etUsername.length() == 0) {
            etUsername.setError("Username cannot be empty");
            result = false;
        }
        if (etPassword.length() == 0) {
            etPassword.setError("Password cannot be empty");
            result = false;
        }
        return result;
    }
}