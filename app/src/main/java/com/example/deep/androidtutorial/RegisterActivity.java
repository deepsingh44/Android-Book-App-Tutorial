package com.example.deep.androidtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deep.androidtutorial.database.UserDao;
import com.example.deep.androidtutorial.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText tname, temail, tpass;
    private String name, email, pass;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDao = new UserDao(this);
        tname = findViewById(R.id.regname);
        temail = findViewById(R.id.regemail);
        tpass = findViewById(R.id.regpass);
    }

    public void registerCodeHere(View view) {
        if (valid()) {
            //rest code here
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(pass);

            long t = userDao.register(user);

            if (t > 0) {
                Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registeration Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean valid() {
        name = tname.getText().toString();
        email = temail.getText().toString();
        pass = tpass.getText().toString();
        if (TextUtils.isEmpty(name)) {
            tname.setError("Enter Name");
            tname.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(email)) {
            temail.setError("Enter Email");
            temail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            tpass.setError("Enter Password");
            tpass.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}