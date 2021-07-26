package com.skincare;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignupProcess1Activity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout layout_email_address, layout_password;
    private TextInputEditText et_email_address, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_process_1);

        //Initializing Views...
        initViews();

    }

    private void initViews() {

        layout_email_address = findViewById(R.id.layout_email_address);
        et_email_address = findViewById(R.id.et_email_address);
        et_email_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (layout_email_address.isErrorEnabled())
                    layout_email_address.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        layout_password = findViewById(R.id.layout_password);
        et_password = findViewById(R.id.et_password);
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (layout_password.isErrorEnabled())
                    layout_password.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                    s.clear();
                    Toast.makeText(getApplicationContext(), "Password must not start with 0",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void validations() {

        String email_address = et_email_address.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email_address.isEmpty()) {
            layout_email_address.setErrorEnabled(true);
            layout_email_address.setError("Enter Email Address");
            layout_email_address.requestFocus();
        } else if (!email_address.matches(emailPattern)) {
            layout_email_address.setErrorEnabled(true);
            layout_email_address.setError("Enter Valid Email Address");
            layout_email_address.requestFocus();
        } else if (password.isEmpty()) {
            layout_password.setErrorEnabled(true);
            layout_password.setError("Enter Password");
            layout_password.requestFocus();
        } else if (password.length() < 6) {
            layout_password.setErrorEnabled(true);
            layout_password.setError("Password Must be 6 characters long");
            layout_password.requestFocus();
        } else {
            Intent intent = new Intent(this, SignupProcess2Activity.class);
            intent.putExtra("email_address", email_address);
            intent.putExtra("password", password);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btn_next) {
            validations();
        }
    }
}