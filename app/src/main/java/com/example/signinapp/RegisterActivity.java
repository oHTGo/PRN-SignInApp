package com.example.signinapp;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etFullName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etDateOfBirth;
    private Button btnRegister;

    private static String usernameRegex = "^[\\d\\w]*$";
    private static String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.register_activity_et_username);
        etPassword = findViewById(R.id.register_activity_et_password);
        etConfirmPassword = findViewById(R.id.register_activity_et_confirm_password);
        etFullName = findViewById(R.id.register_activity_et_full_name);
        etPhone = findViewById(R.id.register_activity_et_phone);
        etEmail = findViewById(R.id.register_activity_et_email);
        etDateOfBirth = findViewById(R.id.register_activity_et_date_of_birth);
        btnRegister = findViewById(R.id.register_activity_btn_register);

        btnRegister.setOnClickListener(view -> {
            validate();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean validate() {
        boolean result = true;
        if (etUsername.length() == 0 || !etUsername.getText().toString().matches(usernameRegex)) {
            etUsername.setError("Username cannot be empty and only contain letters and digits");
            result = false;
        }
        if (etPassword.length() < 8) {
            etPassword.setError("Password cannot be shorter than 8 characters");
            result = false;
        }
        if (!etPassword.getText().toString().equals(etPassword.getText().toString())) {
            etConfirmPassword.setError("Confirm password does not match");
            result = false;
        }
        if (etPhone.length() != 10) {
            etPhone.setError("Phone cannot be unequal 10 numbers");
            result = false;
        }
        if (!etEmail.getText().toString().matches(emailRegex)) {
            etEmail.setError("Email is invalid format");
            result = false;
        }
        if (etFullName.length() == 0) {
            etFullName.setError("Full name cannot be empty");
            result = false;
        }

        try {
            LocalDate dateOfBirth = LocalDate.parse(etDateOfBirth.getText().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            long age = Period.between(dateOfBirth, LocalDate.now()).getYears();
            if (age < 18) {
                etDateOfBirth.setError("Age cannot be less than 18");
                result = false;
            }
        } catch (Exception e) {
            etDateOfBirth.setError("Date of birth is invalid date");
            result = false;
        }

        return result;
    }
}